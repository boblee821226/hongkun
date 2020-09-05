package nc.ui.erm.link;

import nc.ui.pubapp.uif2app.event.card.CardBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.event.list.ListBillItemHyperlinkEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.uif2.model.AbstractUIAppModel;


/**
 * OA-url ����������
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
		// �б���ӳ����ӵļ���
		this.listLinkHandler.setModel(model);
		((IAppModelEx) this.getModel())
				.addAppEventListener(ListBillItemHyperlinkEvent.class, this.listLinkHandler);
		// ��Ƭ��ӳ����ӵļ���
		this.cardLinkHandler.setModel(model);
		((IAppModelEx) this.getModel())
				.addAppEventListener(CardBillItemHyperlinkEvent.class, this.cardLinkHandler);
	}
}
