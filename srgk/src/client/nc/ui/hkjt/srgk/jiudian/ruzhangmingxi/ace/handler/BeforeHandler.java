package nc.ui.hkjt.srgk.jiudian.ruzhangmingxi.ace.handler;

import nc.itf.hkjt.HKJT_PUB;
import nc.ui.pub.beans.UIRefPane;

public class BeforeHandler {
	
	/**
	 * ������� ������Ŀ ���ˡ�
	 * ��Ϊ ������ϸ �ǾƵ�ר�ã� ���Բ����ж� ֱ�ӷ��� ������PK ���ɣ���ȡ�Ƶ��������Ŀ����HK 2019��1��4��14:15:00��
	 * ���˷�ʽ Ҳ��һ���Ĵ���
	 */
	public void resetSRXMRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
				.getBodyItem(e.getKey()).getComponent();
		String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
		
		if(true)
		{// ���� 0309��pk
//			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// ����
			/**
			 * HK 2020��3��16��18:58:03
			 * �Ƶ��������Ŀ Ϊ����֯
			 */
			pk_org = "LY0-" + pk_org;
		}
		
		zdref.getRefModel().setPk_org(pk_org);
	}
	
	public void resetJZFSRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
				.getBodyItem(e.getKey()).getComponent();
		
		/**
		 * HK 2020��3��16��18:58:03
		 * �Ƶ�Ľ��˷�ʽ Ϊ������
		 */
		String pk_org = "0001N510000000001SY3";
		
		zdref.getRefModel().setPk_org(pk_org);
	}
}
