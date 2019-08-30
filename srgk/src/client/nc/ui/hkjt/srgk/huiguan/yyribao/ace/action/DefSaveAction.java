package nc.ui.hkjt.srgk.huiguan.yyribao.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHG_hzshujuMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public class DefSaveAction extends NCAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 168447196809245977L;

	public DefSaveAction() {
		setBtnName("保存");
		setCode("defsaveAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private IHG_hzshujuMaintain hzsjservice = null;

	public IHG_hzshujuMaintain getHzsjservice() {
		if (hzsjservice == null) {
			hzsjservice = NCLocator.getInstance().lookup(
					IHG_hzshujuMaintain.class);
		}
		return hzsjservice;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		YyribaoBillVO srdbvo = (YyribaoBillVO) this.getModel()
				.getSelectedData();
	UFBoolean iscctz=editor.getBillCardPanel().getHeadItem("iscctz").getValueObject()==null?UFBoolean.FALSE:(new UFBoolean(editor.getBillCardPanel().getHeadItem("iscctz").getValueObject().toString()));
	if(srdbvo!=null&&srdbvo.getParentVO()!=null){
		srdbvo.getParentVO().setAttributeValue("iscctz", iscctz);
	}
	YyribaoHVO hvo=srdbvo.getParentVO();
	String where=
			" nvl(dr,0)=0 " +
			" and pk_org='"+hvo.getPk_org()+"' " +
			" and dbilldate='"+hvo.getDbilldate()+"' " +
			" and nvl(replace(vdef10,'~',''),'N') = '"+hvo.getVdef10()+"' " +	// 查询条件增加上 是否酒店 （HK 2018年11月6日21:10:55）
			" and iscctz='N' ";
	
	Object obj=HYPubBO_Client.findColValue("hk_srgk_hg_yyribao", "pk_hk_srgk_hg_yyribao", where);
	
	if(iscctz.booleanValue()){
		if(obj==null){
			throw new BusinessException("当前业务日期单据不存在,请去掉差错调整标志后再进行保存！");
		}
	}else{
		if(obj!=null){
			throw new BusinessException("当前业务日期单据已经存在,请勾选差错调整标志后再进行保存！");
		}
	}
	
	
	YyribaoBillVO hvo_new = getHzsjservice().insert(srdbvo);
		this.getModel().initModel(hvo_new);
		this.setEnabled(false);
		// 提示信息
		ShowStatusBarMsgUtil.showStatusBarMsg("保存完毕!", getEditor().getModel()
				.getContext());
	}

	@Override
	protected boolean isActionEnable() {
		YyribaoBillVO srdbvo = (YyribaoBillVO) this.getModel()
				.getSelectedData();
		if (srdbvo == null) {
			return false;
		} else {
			YyribaoHVO hvo = srdbvo.getParentVO();
			if (hvo.getPrimaryKey() != null) {
				return false;
			} else {
				return true;
			}
		}
	}
}
