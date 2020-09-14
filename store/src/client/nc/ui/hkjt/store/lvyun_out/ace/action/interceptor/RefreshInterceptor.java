package nc.ui.hkjt.store.lvyun_out.ace.action.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.uif2.actions.ActionInterceptor;

public class RefreshInterceptor implements ActionInterceptor {

	private nc.ui.pubapp.uif2app.actions.RefreshSingleAction refreshSingleAction;
	
	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		return false;
	}

	@Override
	public void afterDoActionSuccessed(Action action, ActionEvent e) {
		try {
			refreshSingleAction.doAction(e);
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}

	@Override
	public boolean afterDoActionFailed(Action action, ActionEvent e,
			Throwable ex) {
		return false;
	}

	public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getRefreshSingleAction() {
		return refreshSingleAction;
	}

	public void setRefreshSingleAction(
			nc.ui.pubapp.uif2app.actions.RefreshSingleAction refreshSingleAction) {
		this.refreshSingleAction = refreshSingleAction;
	}

}
