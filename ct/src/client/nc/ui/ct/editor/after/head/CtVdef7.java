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
		Integer int_jfzq = 0;	// �ɷ�����
		
		if(str_jffs!=null)
		{// �ɷ����ڵĴ���
			int int_fu = str_jffs.indexOf("��");
			if(int_fu>=0)
			{// ����� ��  ����Ҫ����ɷ����� (ȡ �� ����ĵ����) ��ѺX��XX��
				String str_yjzq = str_jffs.substring(int_fu+1, str_jffs.length());
				int_jfzq = GenHtmxAction.getShuZi(str_yjzq);
			}
		}
		
		panel.getHeadItem("vdef8").setValue(int_jfzq);

	}

}
