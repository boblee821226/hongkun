package nc.ui.ct.editor.after.head;

import nc.ui.ct.editor.listener.IHeadTailAfterEditEventListener;
import nc.ui.ct.saledaily.action.GenHtmxAction;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

public class CtVdef7 implements IHeadTailAfterEditEventListener {

	@Override
	public void afterEdit(CardHeadTailAfterEditEvent event) {
		
		BillCardPanel panel = event.getBillCardPanel();
		UIRefPane ref_vdef7 = (UIRefPane)panel.getHeadItem("vdef7").getComponent();
		
		String str_jffs = ref_vdef7.getRefName();
		Integer int_jfzq = 0;	// 缴费周期
		
		if(str_jffs!=null)
		{// 缴费周期的处理
			int int_fu = str_jffs.indexOf("付");
			if(int_fu>=0)
			{// 如果有 付  则需要处理缴费周期 (取 付 后面的到最后) （押X付XX）
				String str_yjzq = str_jffs.substring(int_fu+1, str_jffs.length());
				int_jfzq = GenHtmxAction.getShuZi(str_yjzq);
			}
		}
		
		panel.getHeadItem("vdef8").setValue(int_jfzq);

	}

}
