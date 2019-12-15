package nc.ui.hkjt.huiyuan.cikainfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDouble;

public class QiuheAction extends NCAction {

	private static final long serialVersionUID = -400511641197104988L;

	public QiuheAction() {
		setBtnName("求和");
		setCode("qiuheAction");
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
		
		/**
		 * 13列  数量  shuliang
		 * 14列  金额  jine
		 */
		
		int[] rows = this.getEditor().getBillCardPanel().getBillTable().getSelectedRows();
		int   col  = this.getEditor().getBillCardPanel().getBillTable().getSelectedColumn();
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		if( col==13 )
		{
			UFDouble num = UFDouble.ZERO_DBL;
			for( int i=0;i<rows.length;i++ )
			{
				UFDouble num_temp = PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(rows[i], "shuliang") );
				num = num.add(num_temp);
			}
			
			MessageDialog.showWarningDlg(this.getEditor(), "数量求和", num.toString());
		}
		else if( col==14 )
		{
			UFDouble money = UFDouble.ZERO_DBL;
			for( int i=0;i<rows.length;i++ )
			{
				UFDouble money_temp = PuPubVO.getUFDouble_NullAsZero( billModel.getValueAt(rows[i], "jine") );
				money = money.add(money_temp);
			}
			
			MessageDialog.showWarningDlg(this.getEditor(), "金额求和", money.toString());
		}
		
//		System.out.println("====show===="+rows+"===="+cols+"====");
		
//		int endRow = billModel.getRowCount() - 1;
//		
//		String[] col_code = ckBVO.getAttributeNames();
//		
//		for( int i=0;i<col_code.length;i++ )
//		{
//			billModel.setValueAt(ckBVO.getAttributeValue(col_code[i]), endRow , col_code[i]);
//		}
//		billModel.setValueAt(ckBVO.VROWNO,endRow,""+((endRow+1)*10));	// 赋值 行号
		
	}

}
