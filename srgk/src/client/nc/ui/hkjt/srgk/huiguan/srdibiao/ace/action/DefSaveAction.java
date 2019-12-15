package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHG_hzshujuMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoHVO;

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
		SrdibiaoBillVO srdbvo = (SrdibiaoBillVO) this.getModel()
				.getSelectedData();
		SrdibiaoBillVO srdbvo_new = getHzsjservice().insert(srdbvo);
		this.getModel().initModel(srdbvo_new);
		this.setEnabled(false);
		// 提示信息
		ShowStatusBarMsgUtil.showStatusBarMsg("保存完毕!", getEditor().getModel()
				.getContext());
	}

	@Override
	protected boolean isActionEnable() {
		SrdibiaoBillVO srdbvo = (SrdibiaoBillVO) this.getModel()
				.getSelectedData();
		if (srdbvo == null) {
			return false;
		} else {
			SrdibiaoHVO hvo = srdbvo.getParentVO();
			if (hvo.getPrimaryKey() != null) {
				return false;
			} else {
				return true;
			}
		}
	}
}
