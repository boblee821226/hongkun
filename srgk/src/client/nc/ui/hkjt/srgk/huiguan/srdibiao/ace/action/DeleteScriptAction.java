package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.action;

import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;

public class DeleteScriptAction extends
		nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction {
	@Override
	protected boolean isActionEnable() {
		SrdibiaoBillVO billvo = (SrdibiaoBillVO) this.getModel()
				.getSelectedData();
		if (billvo==null||billvo.getPrimaryKey() == null || "".equals(billvo.getPrimaryKey())) {
			return false;
		}
		return super.isActionEnable();
	}
}
