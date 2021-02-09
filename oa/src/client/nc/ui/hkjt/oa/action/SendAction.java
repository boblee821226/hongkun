package nc.ui.hkjt.oa.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.api_oa.hkjt.tool.MyHttpUtil;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.filesystem.IQueryFolderTreeNodeService;
import nc.bs.pub.formulaparse.FormulaParse;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.filesystem.FileManageServletClient;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.hkjt.oa.CreateFlowRequest;
import nc.vo.hkjt.oa.CreateFlowResponse;
import nc.vo.hkjt.oa.HkOaInfoVO;
import nc.vo.hkjt.oa.HkOaSettingB1VO;
import nc.vo.hkjt.oa.HkOaSettingB2VO;
import nc.vo.hkjt.oa.HkOaSettingBillVO;
import nc.vo.hkjt.oa.HkOaSettingHVO;
import nc.vo.hkjt.oa.HkOaSettingVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.filesystem.NCFileNode;
import nc.vo.pub.filesystem.NCFileVO;
import nc.vo.pub.formulaset.FormulaParseFather;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.uif2.LoginContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 发送给oa
 */
public class SendAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3031827859694864837L;

	private BillListView listView = null;
	private AbstractAppModel model = null;
	private BillForm editor = null;
	private String billType = null;
	private LoginContext context = null;
	
	static String baseURL = "http://39.102.46.51/api/nctooa";
	private static String createFlowURL = baseURL + "/createFlow";
	private static String uploadFileURL = baseURL + "/uploadFile";
	private static String pre_tail = "##preTail##";
	/**
	 * 所有可能的单据类型字段
	 */
	static String[] billTypeFields = new String[]{
			"pk_tradetype",		// F3
			"vbilltypecode",	// HK38
			"transi_type",		// 4D48
		};
	
	/**
	 * 查询附件的接口
	 */
	private static IQueryFolderTreeNodeService queryFileService = (IQueryFolderTreeNodeService) NCLocator
			.getInstance().lookup(IQueryFolderTreeNodeService.class);

	public SendAction() {
		setBtnName("提交OA");
		setCode("oaSendAction");
	}

	public BillListView getListView() {
		return listView;
	}

	public void setListView(BillListView listView) {
		this.listView = listView;
	}

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	@Override
	public void doAction(ActionEvent e) throws BusinessException {
		// 1、获取 界面上的单据类型（交易类型）
		String pkBillTypeCode = this.getPkBillTypeCode();
		// 2、获取 配置信息
		HkOaSettingBillVO settingVO = getSettingBillVO(pkBillTypeCode);
		String tableCode1 = settingVO.getVo().getTable_code_1();
		String tableCode2 = settingVO.getVo().getTable_code_2();
		// 3、获取对照关系
		Map<String, String>[] mapper = getMapper(settingVO);
		Map<String, String> Mapper_head = mapper[0];
		Map<String, String> Mapper_item = mapper[1];
		Map<String, String> Mapper_item2 = mapper[2];
		
		Map<String, String>[] translateMapper = getTranslateMapper(settingVO);
		Map<String, String> t_mapper_head = translateMapper[0];
		Map<String, String> t_mapper_item = translateMapper[1];
		Map<String, String> t_mapper_item2 = translateMapper[2];
		
		String idKey = this.getIdKey(settingVO);	// 单据id
		String workflowid = this.getWorkFlowId(settingVO);	// oa流程编号
		/**
		 * 提交前，先做判断，如果oa日志表里存在，则不能提交
		 */
		checkExist(idKey, pkBillTypeCode);
		/**END**/

		// 获得界面数据，封装成 oa-data
//		HashMap<String, Object> oa_head = new HashMap<>();
//		List<Integer> oa_file = new ArrayList<>();
		Map<String, Object> billData = new HashMap<>();
//		billData.put("head", oa_head);
//		billData.put("item", oa_item);
//		billData.put("item2", oa_item2);
//		billData.put("creator", "");	// 制单人
//		billData.put("title", "");		// 标题
		// 制单人 和 标题的赋值
		setDataCreator(billData, settingVO);
		setDataTitle(billData, settingVO);
		// 循环表头Mapper，进行赋值
		setDataHead(Mapper_head, billData, t_mapper_head);
		// 循环表体行：页签一
		// 循环表体Mapper，进行赋值
		setDataItem(Mapper_item, billData, t_mapper_item);
		// 如果有页签二的对照关系
		// 则需要处理封装页签二的数据
		if (Mapper_item2 != null && !Mapper_item2.isEmpty()) {
			setDataItem2(Mapper_item2, billData, t_mapper_item2, tableCode2);
		}
		// 获得head
		Map<String, Object> oa_head = (Map<String, Object>)billData.get("head");
		// 上传附件
		List<Integer> fileIds = SendAction.uploadFile((String)oa_head.get("id"), workflowid);
		String fj = JSONObject.toJSONString(fileIds).replace("[", "").replace("]", "");
		oa_head.put("fj", fj);
		// 发送
		String billDataStr = JSONObject.toJSONString(billData);
		System.out.println(billDataStr);
		CreateFlowRequest req = new CreateFlowRequest();
		req.setBillType(workflowid);	// oa的单据类型
		req.setAction("WRITE");
		req.setData(billDataStr);
		String res = MyHttpUtil.doPost(createFlowURL, JSONObject.toJSONString(req));
		CreateFlowResponse resObj = JSONObject.parseObject(res,
				CreateFlowResponse.class);
		if ("1".equals(resObj.getStatus())) {
			// 写入日志
			HkOaInfoVO info = new HkOaInfoVO(
					PuPubVO.getString_TrimZeroLenAsNull(resObj.getData().get(
							"requestid")) // OA-id
					, workflowid	// oa-流程类型id
					, (String)oa_head.get("billtype") // 单据类型
					, (String)oa_head.get("tradetype") // 交易类型code
					, (String)oa_head.get("tradetypeid") // 交易类型id
					, (String)oa_head.get("id") // 单据id
			);
			HYPubBO_Client.insert(info);
			// 更新界面的oa_status 字段， 免刷新
			getEditor().getBillCardPanel().getHeadItem("oa_status").setValue("已发送");
			// 更新按钮状态
			this.updateStatus();
		} else {
			MessageDialog.showErrorDlg(this.getEditor(), "OA返回信息", resObj.getError());
		}
	}

	private void setDataItem2(
		      Map<String, String> Mapper_item2
			, Map<String, Object> billData
			, Map<String, String> translate_Map_item2
			, String tableCode
			) throws BusinessException {
		
		List<HashMap<String, Object>> oa_item2 = new ArrayList<>();
		billData.put("item2", oa_item2);
		
		if (Mapper_item2.isEmpty()) return;
		
		for (int rowIndex = 0; rowIndex < this.getEditor()
				.getBillCardPanel().getBillModel(tableCode).getRowCount(); rowIndex++) {
			HashMap<String, Object> item2 = new HashMap<>();
			for (Entry<String, String> one : Mapper_item2.entrySet()) {
				String ncKey = one.getValue();
				String oaKey = one.getKey();
				try {
					String ncValue = PuPubVO.getString_TrimZeroLenAsNull(
							getEditor().getBillCardPanel()
							.getBillModel("")
							.getValueAt(rowIndex, ncKey)
							);
					item2.put(oaKey, ncValue);
				} catch (Exception ex) {
					throw new BusinessException("表体2【" + ncKey + "】字段不存在");
				}
			}
			oa_item2.add(item2);
		}
		
		// 进行翻译
		for (HashMap<String, Object> item2 : oa_item2) {
			this.translateBillData(item2, translate_Map_item2);
		}
	}

	private void setDataItem(
			  Map<String, String> Mapper_item
			, Map<String, Object> billData
			, Map<String, String> translate_Map_item
			) throws BusinessException {
		
		List<HashMap<String, Object>> oa_item = new ArrayList<>();
		billData.put("item", oa_item);
		
		if (Mapper_item.isEmpty()) return;
		
		for (int rowIndex = 0; rowIndex < this.getEditor().getBillCardPanel()
				.getBillModel().getRowCount(); rowIndex++) {
			HashMap<String, Object> item = new HashMap<>();
			for (Entry<String, String> one : Mapper_item.entrySet()) {
				String ncKey = one.getValue();
				String oaKey = one.getKey();
				try {
					String ncValue = PuPubVO.getString_TrimZeroLenAsNull(
							getEditor().getBillCardPanel()
							.getBillModel()
							.getValueAt(rowIndex, ncKey)
							);
					item.put(oaKey, ncValue);
				} catch (Exception ex) {
					throw new BusinessException("表体【" + ncKey + "】字段不存在");
				}
			}
			oa_item.add(item);
		}
		
		// 进行翻译
		for (HashMap<String, Object> item : oa_item) {
			this.translateBillData(item, translate_Map_item);
		}
	}

	/**
	 * 赋值表头信息
	 */
	private void setDataHead(
			  Map<String, String> Mapper_head
			, Map<String, Object> billData
			, Map<String, String> translateMap
			) throws BusinessException {
		
		HashMap<String, Object> oa_head = new HashMap<>();
		billData.put("head", oa_head);
		
		for (Entry<String, String> one : Mapper_head.entrySet()) {
			String ncKey = one.getValue();
			String oaKey = one.getKey();
			boolean isTail = ncKey.startsWith(pre_tail);
			if (isTail) ncKey = ncKey.replaceFirst(pre_tail, "");
			try {
				String ncValue;
				if (!isTail) {
					ncValue = PuPubVO.getString_TrimZeroLenAsNull(
							getEditor().getBillCardPanel()
							.getHeadItem(ncKey)
							.getValueObject()
							);
				} else {
					ncValue = PuPubVO.getString_TrimZeroLenAsNull(
							getEditor().getBillCardPanel()
							.getTailItem(ncKey)
							.getValueObject()
							);
				}
				oa_head.put(oaKey, ncValue);
			} catch (Exception ex) {
				throw new BusinessException("表头【" + ncKey + "】字段不存在");
			}
		}
		// 进行翻译
		this.translateBillData(oa_head, translateMap);
	}

	/**
	 * 赋值 标题
	 */
	private void setDataTitle(Map<String, Object> billData, HkOaSettingBillVO settingVO) throws BusinessException {
		
		String[] titleInfo = this.getTitleKey(settingVO);
		String key = titleInfo[0];
		String formula = titleInfo[1];
		
		String title = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel()
				.getHeadItem(key)	// 表头
				.getValueObject()
				);
//		if (title == null) throw new BusinessException("单据上没有标题。");
		// 翻译标题
		if (formula == null) billData.put("title", title);		// 标题
		FormulaParseFather m_formulaParse = new FormulaParse();
		
		formula = formula.replaceAll("@@@@@@", title);
		if (m_formulaParse.setExpress(formula)) {
			String translateValue = m_formulaParse.getValue();
			if (translateValue == null) throw new BusinessException("标题翻译错误。");
			billData.put("title", translateValue);	// 标题
		}
	}

	/**
	 * 赋值 创建人
	 */
	private void setDataCreator(Map<String, Object> billData, HkOaSettingBillVO settingVO) throws BusinessException {
		
		String[] creatorInfo = this.getCreatorKey(settingVO);
		String key = creatorInfo[0];
		String formula = creatorInfo[1];
		
		String creator = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel()
				.getTailItem(key)	// 表尾
				.getValueObject()
				);
		if (creator == null) throw new BusinessException("单据上没有创建人。");
		// 翻译创建人
		if (formula == null) formula = "getcolvalue(\"v_hk_creator\",\"code\",\"cuserid\",\"@@@@@@\")";
		FormulaParseFather m_formulaParse = new FormulaParse();
		
		formula = formula.replaceAll("@@@@@@", creator);
		if (m_formulaParse.setExpress(formula)) {
			String translateValue = m_formulaParse.getValue();
			if (translateValue == null) throw new BusinessException("创建人没有oaID。");
			billData.put("creator", translateValue);	// 制单人
		}
	}

	/**
	 * 判断是否已经提交过OA
	 */
	private void checkExist(String idKey, String pkBillTypeCode) throws BusinessException {
		String idValue = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel()
				.getHeadItem(idKey)
				.getValueObject()
				);
		SuperVO[] infoVOs = HYPubBO_Client.queryByCondition(
				HkOaInfoVO.class,
				" dr = 0 " +
				" and billid = '" + idValue + "' " +
				" and pk_billtypecode = '" + pkBillTypeCode + "' "
				);
		if (infoVOs != null && infoVOs.length > 0) {
			throw new BusinessException("已经提交到oa，不能重复提交。");
		}
	}

	/**
	 * 获取字段对照
	 * key：oa  value：nc
	 */
	private Map<String, String>[] getMapper(HkOaSettingBillVO settingVO) {
		Map<String, String> Mapper_head = new HashMap<>();
//		Mapper_head.put("pk_org", "zz"); // 组织
//		Mapper_head.put("pk_deptid", "bm"); // 部门
//		Mapper_head.put("billmaker", "ry"); // 人员
//		Mapper_head.put("billno", "djbh"); // 单据编号
//		Mapper_head.put("billdate", "zdrq");// 单据日期
//		Mapper_head.put("money", "je"); // 金额
//		Mapper_head.put("supplier", "zdy1"); // 供应商（自定义一）
//		Mapper_head.put("pk_paybill", "id"); // id
//		Mapper_head.put("pk_billtype", "billtype"); // 单据类型
//		Mapper_head.put("pk_tradetype", "tradetype"); // 交易类型
//		Mapper_head.put("pk_tradetypeid", "tradetypeid"); // 交易类型id
		Map<String, String> Mapper_item = new HashMap<>();
//		Mapper_item.put("scomment", "zdy1");
//		Mapper_item.put("prepay", "zdy2");
//		Mapper_item.put("money_de", "zdy3");
//		Mapper_item.put("pk_payitem", "id");// id
		Map<String, String> Mapper_item2 = new HashMap<>();
		
		HkOaSettingHVO[] hVOs = settingVO.getHVOs();
		HkOaSettingB1VO[] b1VOs = settingVO.getB1VOs();
		HkOaSettingB2VO[] b2VOs = settingVO.getB2VOs();
		for (HkOaSettingHVO hVO : hVOs) {
			// 制单人  和  标题  跳过
			if (PuPubVO.getUFBoolean_NullAs(hVO.getIs_creator(), UFBoolean.FALSE).booleanValue()) continue;
			if (PuPubVO.getUFBoolean_NullAs(hVO.getIs_title(), UFBoolean.FALSE).booleanValue()) continue;

			String value = hVO.getField_nc();
			// 是表尾 ，需要加前缀做处理
			if (PuPubVO.getUFBoolean_NullAs(hVO.getIs_tail(), UFBoolean.FALSE).booleanValue()) {
				value = pre_tail + value;
			}
			Mapper_head.put(hVO.getField_oa(), value);
		}
		for (HkOaSettingB1VO b1VO : b1VOs) {
			Mapper_item.put(b1VO.getField_oa(), b1VO.getField_nc());
		}
		for (HkOaSettingB2VO b2VO : b2VOs) {
			Mapper_item2.put(b2VO.getField_oa(), b2VO.getField_nc());
		}

		return new Map[] {Mapper_head, Mapper_item, Mapper_item2};
	}
	
	/**
	 * 获取翻译字段
	 * key：oa  value：formula
	 */
	private Map<String, String>[] getTranslateMapper(HkOaSettingBillVO settingVO) {
		Map<String, String> t_mapper_head = new HashMap<>();
		Map<String, String> t_mapper_item = new HashMap<>();
		Map<String, String> t_mapper_item2 = new HashMap<>();
		
		HkOaSettingHVO[] hVOs = settingVO.getHVOs();
		HkOaSettingB1VO[] b1VOs = settingVO.getB1VOs();
		HkOaSettingB2VO[] b2VOs = settingVO.getB2VOs();
		
		for (HkOaSettingHVO hVO : hVOs) {
			String formula = PuPubVO.getString_TrimZeroLenAsNull(hVO.getFormula());
			if (formula == null) continue;
			t_mapper_head.put(hVO.getField_oa(), formula);
		}
		for (HkOaSettingB1VO b1VO : b1VOs) {
			String formula = PuPubVO.getString_TrimZeroLenAsNull(b1VO.getFormula());
			if (formula == null) continue;
			t_mapper_item.put(b1VO.getField_oa(), formula);
		}
		for (HkOaSettingB2VO b2VO : b2VOs) {
			String formula = PuPubVO.getString_TrimZeroLenAsNull(b2VO.getFormula());
			if (formula == null) continue;
			t_mapper_item2.put(b2VO.getField_oa(), formula);
		}

		return new Map[] {t_mapper_head, t_mapper_item, t_mapper_item2};
	}

	/**
	 * 进行翻译
	 */
	private void translateBillData(
			Map<String, Object> data
			, Map<String, String> translateMap
			) throws BusinessException {
		FormulaParseFather m_formulaParse = new FormulaParse();
//		Map<String, String> translateMap = new HashMap<>();
//		translateMap.put("zz",
//				"getcolvalue(\"org_orgs\",\"def20\",\"pk_org\",\"@@@@@@\")"); // 组织
//		translateMap.put("bm",
//				"getcolvalue(\"org_dept\",\"def18\",\"pk_dept\",\"@@@@@@\")"); // 部门
//		translateMap
//				.put("ry",
//						"getcolvalue(\"bd_psndoc\",\"glbdef26\",\"pk_psndoc\",getcolvalue(\"sm_user\",\"pk_psndoc\",\"cuserid\",\"@@@@@@\"))"); // 人员
//		translateMap.put("zdy1",
//				"getcolvalue(\"org_dept\",\"def18\",\"pk_dept\",\"@@@@@@\")"); // 供应商
		
//		HashMap<String, String> oa_head = (HashMap<String, String>)billData.get("head");
		for (Entry<String, String> one : translateMap.entrySet()) {
			String key = one.getKey();
			String formula = one.getValue();
			String value = PuPubVO.getString_TrimZeroLenAsNull(data.get(key));
			if (value != null) {
				formula = formula.replaceAll("@@@@@@", value);
				if (m_formulaParse.setExpress(formula)) {
					String translateValue = m_formulaParse.getValue();
					data.put(key, translateValue);
				}
			}
		}
//		return billData;
	}

	/**
	 * 读取NC附件 进行OA上传附件
	 */
	private static List<Integer> uploadFile(String billId, String workflowid) {
		List<Integer> fileIds = new ArrayList<>();
		String dsName = WorkbenchEnvironment.getInstance().getLoginBusiCenter()
				.getDataSourceName();
		String baseDir = "C:/temp-nc2oa/";
		try {
			/**
			 * 1、根据billId，查询出附件信息
			 */
			NCFileNode rootNode = queryFileService.getNCFileNodeTreeAndCreateAsNeed(billId, WorkbenchEnvironment.getInstance().getLoginUser().getCuserid());
			/**
			 * 2、循环附件信息，下载NC附件，发送附件给oa
			 * 需要递归文件夹
			 */
			Enumeration<NCFileNode> enumer = rootNode.breadthFirstEnumeration();
			while (enumer.hasMoreElements()) {
				NCFileNode tempNode = (NCFileNode) enumer.nextElement();
				for (NCFileVO item : tempNode.getFilemap().values()) {
					String filePath = item.getFullPath();	// 附件表路径
					String fileName = item.getName();		// 文件名
					String myPath = filePath.replaceFirst(fileName, "");	// 附件表目录
					if (myPath.endsWith("/")) {
						myPath = myPath.substring(0, myPath.length() - 1);
					}
					File base = new File(baseDir);
			        if (!base.exists()) {
			        	base.mkdir();
			        }
					String fullDir = baseDir + myPath;		// 本地目录
					String fullPath = fullDir + "/" + fileName;	// 本地路径
					File dir = new File(fullDir);
			        if (!dir.exists()) {
			        	dir.mkdir();
			        }
					FileOutputStream outputStream = new FileOutputStream(fullPath);
//					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					// 下载NC附件
					FileManageServletClient.downloadFile(dsName, filePath, outputStream);
//					InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//					FileInputStream inputStream = new FileInputStream(fullPath);
					File inputFile = new File(fullPath);
					HashMap<String, Object> param = new HashMap<>();
					param.put("name", fileName);			// 文件名
					param.put("workflowid", workflowid);	// 流程id
					// 上传OA附件
					JSONObject resObj = sendPostWithFile_v2(inputFile, fileName, workflowid, param);
					System.out.println("==res==" + resObj);
					Integer status = resObj.getIntValue("status");
					if (1 == status) {
						JSONObject data = resObj.getJSONObject("data");
						Integer fileid = data.getIntValue("fileid");
						if (fileid != null && fileid >= 0) {
							fileIds.add(fileid);
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return fileIds;
	}

	/**
	 * 发送上传附件的请求v2
	 */
	public static JSONObject sendPostWithFile_v2(
			File file,
			String fileName,
			String workflowid,
			HashMap<String, Object> map) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		JSONObject json = null;
		try {
			HttpPost httppost = new HttpPost(uploadFileURL);
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(200000)
//					.setSocketTimeout(200000)
					.build();
			httppost.setConfig(requestConfig);

			FileBody bin = new FileBody(file
//					new File(
////					"C:/Users/libin/Desktop/favicon附件.png"
//					"C:/Users/libin/Desktop/2222中文测试.xlsx"
//					)
					);
			StringBody nameItem = new StringBody(fileName);
			StringBody workflowidItem = new StringBody(workflowid);

			MultipartEntity reqEntity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE, null,
					Charset.forName("UTF-8"));
			reqEntity.addPart("file", bin);
			reqEntity.addPart("name", nameItem);
			reqEntity.addPart("workflowid", workflowidItem);
			httppost.setEntity(reqEntity);

//			System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String responseEntityStr = EntityUtils.toString(response
							.getEntity());
//					System.out.println(responseEntityStr);
//					System.out.println("Response content length: " + resEntity.getContentLength());
					json = (JSONObject) JSONObject.parse(responseEntityStr);
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return json;
	}
	
	/**
	 * 穷举出当前单据的单据类型
	 */
	private String getPkBillTypeCode() throws BusinessException {
		for (String typeKey : billTypeFields) {
			String typeValue = null;
			try {
				typeValue = PuPubVO.getString_TrimZeroLenAsNull(
						getEditor().getBillCardPanel()
						.getHeadItem(typeKey)
						.getValueObject()
						);
			} catch (NullPointerException ex) {}
			if (typeValue != null) return typeValue;
		}
		throw new BusinessException("获取不到当前单据类型。");
	}
	
	/**
	 * 获得 配置信息
	 */
	static HkOaSettingBillVO getSettingBillVO(String pk_billTypeCode) throws BusinessException {		
		HkOaSettingBillVO billVO = new HkOaSettingBillVO();
		try {
			HkOaSettingVO vo = (HkOaSettingVO)(HYPubBO_Client.queryByCondition(HkOaSettingVO.class, "dr=0 and pk_billtypecode = '"+pk_billTypeCode+"'")[0]);
			String pk_setting = vo.getPk_hk_oa_setting();
			HkOaSettingHVO[] hVOs = (HkOaSettingHVO[])HYPubBO_Client.queryByCondition(HkOaSettingHVO.class, "dr=0 and pk_hk_oa_setting = '"+pk_setting+"'");
			HkOaSettingB1VO[] b1VOs = (HkOaSettingB1VO[])HYPubBO_Client.queryByCondition(HkOaSettingB1VO.class, "dr=0 and pk_hk_oa_setting = '"+pk_setting+"'");
			HkOaSettingB2VO[] b2VOs = (HkOaSettingB2VO[])HYPubBO_Client.queryByCondition(HkOaSettingB2VO.class, "dr=0 and pk_hk_oa_setting = '"+pk_setting+"'");
			billVO.setVo(vo);
			billVO.setHVOs(hVOs);
			billVO.setB1VOs(b1VOs);
			billVO.setB2VOs(b2VOs);
			
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
		return billVO;
	}
	
	/**
	 * 获得id字段
	 */
	static String getIdKey(HkOaSettingBillVO settingVO) throws BusinessException {
		for(HkOaSettingHVO hVO : settingVO.getHVOs()) {
			if (PuPubVO.getUFBoolean_NullAs(hVO.getIs_id(), UFBoolean.FALSE).booleanValue()) {
				return hVO.getField_nc();
			}
		}
		throw new BusinessException("配置里没有标明id字段。");
	}
	
	/**
	 * 获得title字段
	 */
	private String[] getTitleKey(HkOaSettingBillVO settingVO) throws BusinessException {
		for(HkOaSettingHVO hVO : settingVO.getHVOs()) {
			if (PuPubVO.getUFBoolean_NullAs(hVO.getIs_title(), UFBoolean.FALSE).booleanValue()) {
				return new String[]{
						hVO.getField_nc()
						, hVO.getFormula()
				};
			}
		}
		throw new BusinessException("配置里没有标明title字段。");
	}
	
	/**
	 * 获得creator字段
	 */
	private String[] getCreatorKey(HkOaSettingBillVO settingVO) throws BusinessException {
		for(HkOaSettingHVO hVO : settingVO.getHVOs()) {
			if (PuPubVO.getUFBoolean_NullAs(hVO.getIs_creator(), UFBoolean.FALSE).booleanValue()) {
				return new String[]{
						hVO.getField_nc()
						, hVO.getFormula()
				};
			}
		}
		throw new BusinessException("配置里没有标明creator字段。");
	}
	
	/**
	 * 获得 workflowid
	 */
	static String getWorkFlowId(HkOaSettingBillVO settingVO) throws BusinessException {
		String workflowid = PuPubVO.getString_TrimZeroLenAsNull(settingVO.getVo().getWorkflowid());
		if (workflowid != null) return workflowid;
		throw new BusinessException();
	}
	
	/**
	 * 按钮状态控制
	 */
	@Override
	protected boolean isActionEnable() {
//		this.getModel().getAppUiState() == AppUiState.NOT_EDIT;
		if (getEditor() == null || getEditor().getBillCardPanel() == null) return false;
		BillItem item = getEditor().getBillCardPanel().getHeadItem("oa_status");
		if (item != null) {
			String itemValue = PuPubVO.getString_TrimZeroLenAsNull(item.getValueObject());
			if (itemValue == null) return true;
		}
		return false;
	}
	
}
