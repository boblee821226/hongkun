package nc.ui.erm.link;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListBillItemHyperlinkEvent;
import nc.ui.uif2.model.AbstractUIAppModel;

/**
 * 列表的处理
 */
public class OaListLinkHandler implements
		IAppEventHandler<ListBillItemHyperlinkEvent> {

	private AbstractUIAppModel model;

	public AbstractUIAppModel getModel() {
		return this.model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}

	@Override
	public void handleAppEvent(ListBillItemHyperlinkEvent e) {
		System.out.print("==列表的处理==");
	}

}
