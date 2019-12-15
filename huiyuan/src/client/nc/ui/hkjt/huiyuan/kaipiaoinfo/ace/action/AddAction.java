package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.itf.hkjt.PUB_kaipiao;
import nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action.KaipiaoAction;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class AddAction extends nc.ui.pubapp.uif2app.actions.AddAction {
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 5198322758771678302L;
	
//	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction bodyAddLineAction;
	
	public BodyAddLineAction getBodyAddLineAction() {
		return bodyAddLineAction;
	}
	public void setBodyAddLineAction(BodyAddLineAction bodyAddLineAction) {
		this.bodyAddLineAction = bodyAddLineAction;
	}
//	public BillManageModel getModel() {
//		return model;
//	}
//	public void setModel(BillManageModel model) {
//		this.model = model;
//	}
	public ShowUpableBillForm getEditor() {
		return editor;
	}
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	public ShowUpableBillListView getListview() {
		return listview;
	}
	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		super.doAction(e);
		
		nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action.BbcxAction bbcxAction = new 
				nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action.BbcxAction();
		
		KaipiaoqueryBillVO[] billVO_source = PUB_kaipiao.bbcx_data(bbcxAction.KA_CODE, bbcxAction.FPH, true,"");
		
		if( billVO_source==null ) return;
		
		int rowCount = billVO_source.length;
		
		if( rowCount<=0 ) return;
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		UFDouble fpje = UFDouble.ZERO_DBL;	// 发票金额 表体合计
		for(int i=0;i<rowCount;i++)
		{
			bodyAddLineAction.doAction();
			
			billVO_source[i].getParentVO().getSykpje();
			
			billModel.setValueAt(billVO_source[i].getParentVO().getKa_code(), i, "ka_code");	// 卡号
			billModel.setValueAt(billVO_source[i].getParentVO().getSykpje(), i, "fpje");		// 发票金额
			billModel.setValueAt(billVO_source[i].getParentVO().getYkpze(), i, "zqkpje");		// 之前可开票总额
			billModel.setValueAt(billVO_source[i].getParentVO().getKkpze(), i, "kkpze");		// 可开票总额
			billModel.setValueAt(billVO_source[i].getParentVO().getVdef03(), i, "vbdef03");		// 转卡金额
			billModel.setValueAt(billVO_source[i].getParentVO().getKa_pk(), i, "ka_pk");		// 卡pk
			billModel.setValueAt(billVO_source[i].getParentVO().getKaxing_code(), i, "kaxing_code");	// 卡型code
			billModel.setValueAt(billVO_source[i].getParentVO().getKaxing_name(), i, "kaxing_name");	// 卡型name
			billModel.setValueAt(billVO_source[i].getParentVO().getKaxing_pk(), i, "kaxing_pk");		// 卡型pk
			billModel.setValueAt(billVO_source[i].getParentVO().getVdef02(), i, "vbdef02");			// 发卡店
			
			
			fpje = fpje.add( PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"fpje")) );
		}
		
		this.getEditor().getBillCardPanel().setHeadItem("fpje", fpje);	// 表头 发票金额
		this.getEditor().getBillCardPanel().setHeadItem("fpsj", new UFDate());	// 发票时间
	}
}
