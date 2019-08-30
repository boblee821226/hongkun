package nc.ui.hkjt.pub.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;

public class DeptFilterUtils {
	public void resetDeptRefSql(CardHeadTailBeforeEditEvent e) {
		String pk_org = e.getBillCardPanel().getHeadItem("pk_org")
				.getValueObject().toString();
		String pk_group = e.getBillCardPanel().getHeadItem("pk_group")
				.getValueObject().toString();
		UIRefPane deptpane = (UIRefPane) e.getBillCardPanel()
				.getHeadItem(e.getKey()).getComponent();
//		deptpane.setMultiSelectedEnabled(true);
		deptpane.getRefModel().setAddEnvWherePart(false);
		deptpane.getRefModel().setWherePart(
				" pk_group='" + pk_group + "' and  pk_org = '" + pk_org
						+ "' and nvl(dr,0)=0");
	}
}
