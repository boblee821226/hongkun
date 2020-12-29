package nc.ui.hkjt.oa.action;

import java.awt.event.ActionEvent;

public class UnCommitScriptAction extends
		nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1726291353398909871L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		String billType = this.getBillType();
		CommitScriptAction.needSendOA(billType);
		super.doAction(e);
	}
}
