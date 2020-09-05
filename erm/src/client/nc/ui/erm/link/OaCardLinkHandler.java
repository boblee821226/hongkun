package nc.ui.erm.link;

import hd.vo.pub.tools.PuPubVO;
import nc.api_oa.hkjt.itf.ApiPubTool;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBillItemHyperlinkEvent;
import nc.ui.uif2.model.AbstractUIAppModel;

/**
 * 卡片的处理
 */
public class OaCardLinkHandler implements
		IAppEventHandler<CardBillItemHyperlinkEvent> {

	private AbstractUIAppModel model;
	private Long lastTime = null;	// 最后一次的点击时间

	public AbstractUIAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}

	@Override
	public void handleAppEvent(CardBillItemHyperlinkEvent e) {
		String key = e.getItem().getKey();
		if (key.equals("zyx26")) {
			/**
			 * HK 2020年9月4日18:41:18
			 * 需要加时间锁，防止多次点击。10s
			 */
			Long minTime = (long)(10 * 1000);	// 最小间隔时间 10s 
			Long currTime = System.currentTimeMillis();
			if (lastTime != null && currTime - lastTime < minTime) {
				return;
			} else {
				lastTime = currTime;
			}
			/***END***/
			try {
				String url = PuPubVO.getString_TrimZeroLenAsNull(e.getValue());
				if (url != null) {
					// 先申请token 18610372523
					String token = OaHttpUtil.getToken("18701546487");	// 手机号
					// 调用url
					url = "http://111.204.181.93:93/workflow/request/ViewRequest.jsp?requestid=34034" +
							"&ssoToken=" + token;
					ApiPubTool.openURL(url);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				// TODO
			}
		}
	}
}
