package nc.ui.hkjt.huiyuan.cikainfo.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_cikainfoMaintain;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;

public class QuCikaAction extends NCAction {

	private static final long serialVersionUID = -400511641197104988L;

	public QuCikaAction() {
		setBtnName("取次卡");
		setCode("quCikaAction");
	}
	
	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction bodyAddLineAction;

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
		
		IHy_cikainfoMaintain iHy_cikainfoMaintain = (IHy_cikainfoMaintain)NCLocator.getInstance().lookup(IHy_cikainfoMaintain.class.getName());
		
		int row = this.getEditor().getBillCardPanel().getBillTable().getSelectedRow();
		
		String waterNum = (String)this.getEditor().getBillCardPanel().getBillModel().getValueAt(row,"timescardwaternum");
		
		CikainfoBVO ckBVO = iHy_cikainfoMaintain.queryCika(waterNum);
		
		if( ckBVO==null ) return;
		
//		System.out.println("===="+ckBVO);
		
		this.getBodyAddLineAction().doAction(e);	// 增行
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		int endRow = billModel.getRowCount() - 1;
		
		String[] col_code = ckBVO.getAttributeNames();
		
		for( int i=0;i<col_code.length;i++ )
		{
			billModel.setValueAt(ckBVO.getAttributeValue(col_code[i]), endRow , col_code[i]);
		}
		billModel.setValueAt(ckBVO.VROWNO,endRow,""+((endRow+1)*10));	// 赋值 行号
		
	}

}
