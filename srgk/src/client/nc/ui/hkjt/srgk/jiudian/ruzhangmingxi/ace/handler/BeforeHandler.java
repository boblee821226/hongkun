package nc.ui.hkjt.srgk.jiudian.ruzhangmingxi.ace.handler;

import nc.itf.hkjt.HKJT_PUB;
import nc.ui.pub.beans.UIRefPane;

public class BeforeHandler {
	
	/**
	 * 表体参照 收入项目 过滤。
	 * 因为 入账明细 是酒店专用， 所以不用判断 直接返回 康西的PK 即可，获取酒店的收入项目。（HK 2019年1月4日14:15:00）
	 * 结账方式 也是一样的处理。
	 */
	public void resetSRXMRefSql(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		UIRefPane zdref = (UIRefPane) e.getBillCardPanel()
				.getBodyItem(e.getKey()).getComponent();
		String pk_org = e.getBillCardPanel().getHeadItem("pk_org").getValue();
		
		if(true)
		{// 返回 0309的pk
//			pk_org = HKJT_PUB.PK_ORG_JIUDIAN;	// 康西
			/**
			 * HK 2020年3月16日18:58:03
			 * 酒店的收入项目 为本组织
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
		 * HK 2020年3月16日18:58:03
		 * 酒店的结账方式 为朗丽兹
		 */
		String pk_org = "0001N510000000001SY3";
		
		zdref.getRefModel().setPk_org(pk_org);
	}
}
