package nc.ui.erm.link;

import nc.ui.pubapp.uif2app.event.card.CardBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.event.list.ListBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.uif2.model.AbstractUIAppModel;


/**
 * OA-url 联查适配器
 */
public class OaLinkMediator {

	private OaCardLinkHandler cardLinkHandler;

	private OaListLinkHandler listLinkHandler;

	private AbstractUIAppModel model;

	public OaLinkMediator() {
		super();
		this.cardLinkHandler = new OaCardLinkHandler();
		this.listLinkHandler = new OaListLinkHandler();
	}

	public AbstractUIAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
		// 列表添加超链接的监听
		this.listLinkHandler.setModel(model);
		((IAppModelEx) this.getModel())
				.addAppEventListener(ListBillItemHyperlinkEvent.class, this.listLinkHandler);
		// 卡片添加超链接的监听
		this.cardLinkHandler.setModel(model);
		((IAppModelEx) this.getModel())
				.addAppEventListener(CardBillItemHyperlinkEvent.class, this.cardLinkHandler);
	}
}
