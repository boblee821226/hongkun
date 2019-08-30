package nc.ui.hkjt.srgk.huiguan.yyribao.ace.action;

import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;

public class DeleteScriptAction extends
		nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction {
	@Override
	protected boolean isActionEnable() {
		YyribaoBillVO billvo = (YyribaoBillVO) this.getModel()
				.getSelectedData();
		if (billvo==null||billvo.getPrimaryKey() == null || "".equals(billvo.getPrimaryKey())) {
			return false;
		}
		return super.isActionEnable();
	}
}
