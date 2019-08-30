package nc.ui.hkjt.hg_srxm.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHg_srxmMaintain;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.IQueryAndRefreshManager;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.pub.BusinessException;

public class FenPeiAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1598197819707945750L;
	public FenPeiAction() {
	setBtnName("分配会计科目");
	setCode("fenpeiAction");
	}
	private nc.ui.pubapp.uif2app.model.HierachicalDataAppModel model;
	private IQueryAndRefreshManager dataManager;
	
	public IQueryAndRefreshManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IQueryAndRefreshManager dataManager) {
		this.dataManager = dataManager;
	}

	public nc.ui.pubapp.uif2app.model.HierachicalDataAppModel getModel() {
		return model;
	}

	public void setModel(
			nc.ui.pubapp.uif2app.model.HierachicalDataAppModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {

		SrxmHVO srxmvo = (SrxmHVO) getModel().getSelectedData();
		if (srxmvo == null) {
			throw new BusinessException("请选中要分配的收入项目项!");
		}
		getService().fenpeiSrxmKjkmInfo(srxmvo);
		getDataManager().refresh();
	}

	@Override
	protected boolean isActionEnable() {
		return super.isActionEnable();
	}

	IHg_srxmMaintain servece = null;

	private IHg_srxmMaintain getService() {
		if (servece == null) {
			servece = NCLocator.getInstance().lookup(IHg_srxmMaintain.class);
		}
		return servece;
	}
}
