package nc.ui.hkjt.huiyuan.kadangan.ace.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.RefreshSingleAction;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganHVO;
import nc.vo.pub.lang.UFDouble;

public class QueryKpjeAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public QueryKpjeAction() {
		setBtnName("查询开票");
		setCode("queryKpjeAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction bodyAddLineAction;
	private RefreshSingleAction refreshCardAction;

	public RefreshSingleAction getRefreshCardAction() {
		return refreshCardAction;
	}

	public void setRefreshCardAction(RefreshSingleAction refreshCardAction) {
		this.refreshCardAction = refreshCardAction;
	}

	public BodyAddLineAction getBodyAddLineAction() {
		return bodyAddLineAction;
	}

	public void setBodyAddLineAction(BodyAddLineAction bodyAddLineAction) {
		this.bodyAddLineAction = bodyAddLineAction;
	}

	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(
				IUAPQueryBS.class.getName());
		
		StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" sum(ka.kkpze) ")
						.append(",sum(ka.ykpje) ")
//						.append(",sum(replace(ka.vdef04,'~','')) ")
						.append(" from hk_huiyuan_kadangan ka ")
						.append(" where ka.dr=0 ")
		;
		ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		if( list!=null && list.size()>0 )
		{
			Object[] obj = (Object[])list.get(0);
			UFDouble kkpze = PuPubVO.getUFDouble_NullAsZero(obj[0]);	// 可开票总额
			UFDouble ykpje = PuPubVO.getUFDouble_NullAsZero(obj[1]);	// 已开票金额
//			UFDouble zkje  = PuPubVO.getUFDouble_NullAsZero(obj[2]);	// 转卡金额
			UFDouble syje  = kkpze.sub(ykpje);
			
			MessageDialog.showWarningDlg(this.getEditor(), "", 
					"可开票总额：" + kkpze + "\n\r" +
					"已开票总额：" + ykpje + "\n\r" +
					"    剩余金额：" + syje + "\n\r" 
			);
			
		}
		
	}
	
}
