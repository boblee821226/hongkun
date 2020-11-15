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
import nc.vo.pub.SuperVO;
import nc.vo.pub.filesystem.NCFileNode;
import nc.vo.pub.filesystem.NCFileVO;
import nc.vo.pub.formulaset.FormulaParseFather;
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
	public void doAction(ActionEvent e) throws Exception {
		// MessageDialog.showOkCancelDlg(null, "测试", "测试？");

		// 获取对照关系
		HashMap[] mapper = getMapper();
		HashMap<String, String> Mapper_head = mapper[0];
		HashMap<String, String> Mapper_item = mapper[1];
		HashMap<String, String> Mapper_item2 = mapper[2];
		String idKey = "pk_paybill";		// 单据id
		String typeKey = "pk_tradetype";	// 交易类型code
		String workflowid = "113";	// oa流程编号
		/**
		 * 提交前，先做判断，如果oa日志表里存在，则不能提交
		 */
		{
			String idValue = PuPubVO.getString_TrimZeroLenAsNull(
					getEditor().getBillCardPanel()
					.getHeadItem(idKey)
					.getValueObject()
					);
			String typeValue = PuPubVO.getString_TrimZeroLenAsNull(
					getEditor().getBillCardPanel()
					.getHeadItem(typeKey)
					.getValueObject()
					);
			SuperVO[] infoVOs = HYPubBO_Client.queryByCondition(
					HkOaInfoVO.class,
					" dr = 0 " +
					" and billid = '" + idValue + "' " +
					" and pk_billtypecode = '" + typeValue + "' "
					);
			if (infoVOs != null && infoVOs.length > 0) {
				throw new Exception("已经提交到oa，不能重复提交。");
			}
		}
		/**END**/

		// 获得界面数据，封装成 oa-data
		HashMap<String, Object> oa_head = new HashMap<>();
		ArrayList<HashMap<String, Object>> oa_item = new ArrayList<>();
		ArrayList<HashMap<String, Object>> oa_item2 = new ArrayList<>();
		ArrayList<Integer> oa_file = new ArrayList<>();
		HashMap billData = new HashMap<>();
		billData.put("head", oa_head);
		billData.put("item", oa_item);
		billData.put("item2", oa_item2);
//		billData.put("creator", "");	// 制单人
//		billData.put("title", "");		// 标题

		String creatorKey = "creator";
		String titleKey = "def10";
		
		// 制单人 和 标题的处理
		String creator = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel()
				.getTailItem(creatorKey)
				.getValueObject()
				);
		billData.put("creator", creator);	// 制单人
		String title = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel()
				.getHeadItem(titleKey)
				.getValueObject()
				);
		billData.put("title", title);		// 标题
		// 循环表头Mapper，进行赋值
		for (Entry<String, String> one : Mapper_head.entrySet()) {
			String ncKey = one.getKey();
			String oaKey = one.getValue();
			if (ncKey.startsWith("pk_tradetype")) {
//				System.out.println();
			}
			try {
				String ncValue = PuPubVO.getString_TrimZeroLenAsNull(
						getEditor().getBillCardPanel()
						.getHeadItem(ncKey)
						.getValueObject()
						);
				oa_head.put(oaKey, ncValue);
			} catch (Exception ex) {
				throw new Exception("表头【" + ncKey + "】字段不存在");
			}
		}
		// 循环表体行：页签一
		// 循环表体Mapper，进行赋值
		for (int rowIndex = 0; rowIndex < this.getEditor().getBillCardPanel()
				.getBillModel().getRowCount(); rowIndex++) {
			HashMap<String, Object> item = new HashMap<>();
			for (Entry<String, String> one : Mapper_item.entrySet()) {
				String ncKey = one.getKey();
				String oaKey = one.getValue();
				try {
					String ncValue = PuPubVO.getString_TrimZeroLenAsNull(
							getEditor().getBillCardPanel()
							.getBillModel()
							.getValueAt(rowIndex, ncKey)
							);
					item.put(oaKey, ncValue);
				} catch (Exception ex) {
					throw new Exception("表体【" + ncKey + "】字段不存在");
				}
			}
			oa_item.add(item);
		}
		// 如果有页签二的对照关系
		// 则需要处理封装页签二的数据
		if (!Mapper_item2.isEmpty()) {
			for (int rowIndex = 0; rowIndex < this.getEditor()
					.getBillCardPanel().getBillModel("").getRowCount(); rowIndex++) {
				HashMap<String, Object> item2 = new HashMap<>();
				for (Entry<String, String> one : Mapper_item.entrySet()) {
					String ncKey = one.getKey();
					String oaKey = one.getValue();
					try {
						String ncValue = PuPubVO.getString_TrimZeroLenAsNull(
								getEditor().getBillCardPanel()
								.getBillModel("")
								.getValueAt(rowIndex, ncKey)
								);
						item2.put(oaKey, ncValue);
					} catch (Exception ex) {
						throw new Exception("表体2【" + ncKey + "】字段不存在");
					}
				}
				oa_item2.add(item2);
			}
		}
		// 翻译
		translateBillData(billData);
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
		String res = MyHttpUtil.doPost(
				"http://39.102.46.51/api/nctooa/createFlow", req);
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
			MessageDialog.showErrorDlg(this.getEditor(), "", resObj.getError());
		}
	}

	/**
	 * 查询 获得配置表 1、主表 2、子表一 3、子表二
	 */
	private HashMap[] getMapper() {
		HashMap<String, String> Mapper_head = new HashMap<>();
		Mapper_head.put("pk_org", "zz"); // 组织
		Mapper_head.put("pk_deptid", "bm"); // 部门
		Mapper_head.put("billmaker", "ry"); // 人员
		Mapper_head.put("billno", "djbh"); // 单据编号
		Mapper_head.put("billdate", "zdrq");// 单据日期
		Mapper_head.put("money", "je"); // 金额
		Mapper_head.put("supplier", "zdy1"); // 供应商（自定义一）
		Mapper_head.put("pk_paybill", "id"); // id
		Mapper_head.put("pk_billtype", "billtype"); // 单据类型
		Mapper_head.put("pk_tradetype", "tradetype"); // 交易类型
		Mapper_head.put("pk_tradetypeid", "tradetypeid"); // 交易类型id
		HashMap<String, String> Mapper_item = new HashMap<>();
		Mapper_item.put("scomment", "zdy1");
		Mapper_item.put("prepay", "zdy2");
		Mapper_item.put("money_de", "zdy3");
		Mapper_item.put("pk_payitem", "id");// id
		HashMap<String, String> Mapper_item2 = new HashMap<>();

		return new HashMap[] { Mapper_head, Mapper_item, Mapper_item2, };
	}

	/**
	 * 进行翻译 组织、部门、人员
	 * 
	 */
	private HashMap translateBillData(HashMap billData) {
		FormulaParseFather m_formulaParse = new FormulaParse();
		HashMap<String, String> translateMap = new HashMap<>();
		translateMap.put("zz",
				"getcolvalue(\"org_orgs\",\"def20\",\"pk_org\",\"@@@@@@\")"); // 组织
		translateMap.put("bm",
				"getcolvalue(\"org_dept\",\"def18\",\"pk_dept\",\"@@@@@@\")"); // 部门
		translateMap
				.put("ry",
						"getcolvalue(\"bd_psndoc\",\"glbdef26\",\"pk_psndoc\",getcolvalue(\"sm_user\",\"pk_psndoc\",\"cuserid\",\"@@@@@@\"))"); // 人员
		translateMap.put("zdy1",
				"getcolvalue(\"org_dept\",\"def18\",\"pk_dept\",\"@@@@@@\")"); // 供应商
		HashMap<String, String> oa_head = (HashMap<String, String>) billData
				.get("head");
		for (Entry<String, String> one : translateMap.entrySet()) {
			String key = one.getKey();
			String formula = one.getValue();
			String value = oa_head.get(key);
			if (value != null) {
				formula = formula.replaceAll("@@@@@@", value);
//				FormulaParseFather m_formulaParse = new FormulaParse();
				if (m_formulaParse.setExpress(formula)) {
					String translateValue = m_formulaParse.getValue();
					oa_head.put(key, translateValue);
				}
			}
		}
		
		// 翻译创建人
		String creator = PuPubVO.getString_TrimZeroLenAsNull(billData.get("creator"));
		m_formulaParse.setExpress(
				"getcolvalue(\"v_hk_creator\",\"code\",\"cuserid\",\""+creator+"\")"
		);
		creator = m_formulaParse.getValue();
		billData.put("creator", creator);
		
		return billData;
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

//	/**
//	 * 发送上传附件的请求
//	 */
//	public static JSONObject sendPostWithFile(InputStream inputStream,
//			String fileName, HashMap<String, Object> map) {
//		DataOutputStream out = null;
//		DataInputStream in = null;
//		final String newLine = "\r\n";
//		final String prefix = "--";
//		JSONObject json = null;
//		try {
//			String fileOCRUrl = "http://39.102.46.51/api/nctooa/uploadFile";
//			URL url = new URL(fileOCRUrl);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//			String BOUNDARY = "-------KingKe0520a";
//			conn.setRequestMethod("POST");
//			// 发送POST请求必须设置如下两行
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			conn.setUseCaches(false);
//			conn.setRequestProperty("connection", "Keep-Alive");
////			conn.setRequestProperty("Charsert", "GBK");
//			conn.setRequestProperty("Content-Type",
//					"multipart/form-data; boundary=" + BOUNDARY);
////			conn.
//			out = new DataOutputStream(conn.getOutputStream());
//
//			// 添加参数file
//			// File file = new File(filePath);
//			StringBuilder sb1 = new StringBuilder();
//			sb1.append(prefix);
//			sb1.append(BOUNDARY);
//			sb1.append(newLine);
//			/**
//			 * name=参数名字
//			 * %25E4%25B8%25AD
//			 */
//			sb1.append("Content-Disposition: form-data"
//					+ ";name=\"file\""
//					+ ";filename=\"中国.png\""
////					+ ";filename=\""+ fileName + "\""
//					+ newLine);
//			sb1.append("Content-Type:application/octet-stream");
//			sb1.append(newLine);
//			sb1.append(newLine);
////			out.write(new String(sb1.toString().getBytes("GBK"),"UTF-8").getBytes());
//			out.write(sb1.toString().getBytes());
//			// (“ISO-8859-1”),”utf-8”
////			out.write(new String(sb1.toString().getBytes("GBK"),"utf-8").getBytes());
//			// in = new DataInputStream(new FileInputStream(file));
//			in = new DataInputStream(inputStream);
//			byte[] bufferOut = new byte[1024];
//			int bytes = 0;
//			while ((bytes = in.read(bufferOut)) != -1) {
//				out.write(bufferOut, 0, bytes);
//			}
//			out.write(newLine.getBytes());
//
//			StringBuilder sb = new StringBuilder();
//			int k = 1;
//			for (String key : map.keySet()) {
//				if (k != 1) {
//					sb.append(newLine);
//				}
//				sb.append(prefix);
//				sb.append(BOUNDARY);
//				sb.append(newLine);
//				sb.append("Content-Disposition: form-data;name=" + key + "");
//				sb.append(newLine);
//				sb.append(newLine);
//				sb.append(map.get(key));
//				out.write(sb.toString().getBytes());
//				sb.delete(0, sb.length());
//				k++;
//			}
//
//			// 添加参数sysName
//			/*
//			 * StringBuilder sb = new StringBuilder(); sb.append(prefix);
//			 * sb.append(BOUNDARY); sb.append(newLine);
//			 * sb.append("Content-Disposition: form-data;name=\"sysName\"");
//			 * sb.append(newLine); sb.append(newLine); sb.append("test");
//			 * out.write(sb.toString().getBytes());
//			 */
//
//			// 添加参数returnImage
//			/*
//			 * StringBuilder sb2 = new StringBuilder(); sb2.append(newLine);
//			 * sb2.append(prefix); sb2.append(BOUNDARY); sb2.append(newLine);
//			 * sb2
//			 * .append("Content-Disposition: form-data;name=\"returnImage\"");
//			 * sb2.append(newLine); sb2.append(newLine); sb2.append("false");
//			 * out.write(sb2.toString().getBytes());
//			 */
//
//			byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
//			out.write(end_data);
//			out.flush();
//
//			// 定义BufferedReader输入流来读取URL的响应
//			BufferedReader reader = new BufferedReader(new InputStreamReader(
//					conn.getInputStream()));
//			String line = null;
//			StringBuffer resultStr = new StringBuffer();
//			while ((line = reader.readLine()) != null) {
//				resultStr.append(line);
//			}
//			json = (JSONObject) JSONObject.parse(
//					resultStr.toString()
////					new String(resultStr.toString().getBytes("utf-8"),"GBK")
//				);
//
//		} catch (Exception e) {
//			System.out.println("发送POST请求出现异常！" + e);
//			e.printStackTrace();
//		} finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return json;
//	}
	
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
			HttpPost httppost = new HttpPost(
					"http://39.102.46.51/api/nctooa/uploadFile");
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
	
//	public static String post(String url, Map<String, Object> paramsMap) throws ClientProtocolException, IOException{
//		HttpPost httpPost = new HttpPost(url);
//		MultipartEntity entity = new MultipartEntity(
//				  HttpMultipartMode.BROWSER_COMPATIBLE
//				, null
//				, Charset.forName("utf-8"));
//		ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8); 
//		//填充参数
//		Set<String> keySet = paramsMap.keySet();
//		for(String key : keySet){
//			Object obj = paramsMap.get(key);
//			if(obj instanceof File){ //参数是File 类型
//				File file = (File) obj;
//				entity.addPart(key, new FileBody(file));
//			}else if(obj instanceof String){ //参数是String 类型
//				String value = (String) obj;
//				entity.addPart(key, new StringBody(value, contentType.getMimeType() ,contentType.getCharset()));
//			}else if(obj instanceof byte[]){
//				byte[] bytes = (byte[]) obj;
//				entity.addPart(key, new ByteArrayBody(bytes, ContentType.DEFAULT_BINARY, key));
//			}else{
//				throw new IllegalArgumentException(key+"的类型是"+obj.getClass()+"（允许的参数类型为File 或者 String）。");
//			}
//		}
//		
//		httpPost.setEntity(entityBuilder.build());
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpResponse response = httpClient.execute(httpPost);
//		if(response.getStatusLine().getStatusCode() == 200){
//			HttpEntity entity = response.getEntity();
//			String result = EntityUtils.toString(entity);
//			return result;
//		}
//		return null;
//	}
	
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
