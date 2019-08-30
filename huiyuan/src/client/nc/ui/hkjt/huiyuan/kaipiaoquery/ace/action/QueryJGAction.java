package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_huiyuanMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

public class QueryJGAction extends NCAction {

	/**
	 * 查询金贵充值
	 */
	private static final long serialVersionUID = 1049580924829904678L;

	public QueryJGAction() {
		setBtnName("查询金贵充值");
		setCode("queryJGAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;

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
		
		int selectRow = this.getListview().getBillListPanel().getHeadTable().getSelectedRow();
		String ka_code = PuPubVO.getString_TrimZeroLenAsNull(
				this.getListview().getBillListPanel().getHeadBillModel().getValueAt(selectRow, "ka_code") );	// 卡号
		String dian = PuPubVO.getString_TrimZeroLenAsNull(
				this.getListview().getBillListPanel().getHeadBillModel().getValueAt(selectRow, "vdef02") );		// 店名
		
		IHy_huiyuanMaintain iHy_huiyuanMaintain = (IHy_huiyuanMaintain)NCLocator.getInstance().lookup(IHy_huiyuanMaintain.class.getName());
		Object result = iHy_huiyuanMaintain.queryJGchongzhi(ka_code,null,dian,null);
		
		if(result!=null)
		{
			MessageDialog.showWarningDlg(this.getListview(), "金贵3天内充值记录", ((Object[])result)[0].toString());
		}
		else
		{
			MessageDialog.showWarningDlg(this.getListview(), "金贵充值", "3天内，没有充值记录。");
		}
		
	}
}
