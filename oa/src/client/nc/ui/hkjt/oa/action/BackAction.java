package nc.ui.hkjt.oa.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.api_oa.hkjt.tool.MyHttpUtil;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.hkjt.oa.BackFlowResponse;
import nc.vo.hkjt.oa.HkOaInfoVO;
import nc.vo.hkjt.oa.HkOaSettingBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.uif2.LoginContext;

import com.alibaba.fastjson.JSONObject;

/**
 * 收回oa
 */
public class BackAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3031827859694864837L;

	private BillListView listView = null;
	private AbstractAppModel model = null;
	private BillForm editor = null;
	private String billType = null;
	private LoginContext context = null;
	
	private static String backFlowURL = SendAction.baseURL + "/backFlow";
	
	public BackAction() {
		setBtnName("收回OA");
		setCode("oaBackAction");
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
		HkOaSettingBillVO settingVO = SendAction.getSettingBillVO(pkBillTypeCode);
		
		String idKey = SendAction.getIdKey(settingVO);		// 单据id
//		String typeKey = "pk_tradetype";	// 交易类型code
		/**
		 * 收回，先做判断，必须在日志表里存在。
		 */
		HkOaInfoVO infoVO = getInfoVO(idKey, pkBillTypeCode);
		/**END**/
		Map<String, Object> req = new HashMap<>();
		req.put("workflowid", SendAction.getWorkFlowId(settingVO));
		req.put("requestid", infoVO.getPk_hk_oa_info());
		String res = MyHttpUtil.doGet(backFlowURL, req);
		BackFlowResponse resObj = JSONObject.parseObject(res,
				BackFlowResponse.class);
		if ("1".equals(resObj.getStatus())) {
			// 删除日志
			HYPubBO_Client.delete(infoVO);
			// 更新界面的oa_status 字段， 免刷新
			getEditor().getBillCardPanel().getHeadItem("oa_status").setValue(null);
			// 更新按钮状态
			this.updateStatus();
		} else {
			MessageDialog.showErrorDlg(this.getEditor(), "OA返回信息", resObj.getError());
		}
	}

	private HkOaInfoVO getInfoVO(String idKey, String pkBillTypeCode)
			throws BusinessException {
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
		if (infoVOs == null || infoVOs.length == 0) {
			throw new BusinessException("未提交到oa，不能收回。");
		}
		HkOaInfoVO infoVO = (HkOaInfoVO)infoVOs[0];
		return infoVO;
	}

	/**
	 * 穷举出当前单据的单据类型
	 */
	private String getPkBillTypeCode() throws BusinessException {
		for (String typeKey : SendAction.billTypeFields) {
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
	 * 按钮状态控制
	 */
	@Override
	protected boolean isActionEnable() {
		if (getEditor() == null || getEditor().getBillCardPanel() == null) return false;
		BillItem item = getEditor().getBillCardPanel().getHeadItem("oa_status");
		if (item != null) {
			String itemValue = PuPubVO.getString_TrimZeroLenAsNull(item.getValueObject());
			if ("已发送".equals(itemValue)) return true;
		}
		return false;
	}
}
