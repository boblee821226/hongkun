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

public class KadanganAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1049580924829904678L;

	public KadanganAction() {
		setBtnName("取会员卡");
		setCode("kadanganAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BbcxAction bbcxAction;

	public BbcxAction getBbcxAction() {
		return bbcxAction;
	}

	public void setBbcxAction(BbcxAction bbcxAction) {
		this.bbcxAction = bbcxAction;
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
		
		String ka_code = PuPubVO.getString_TrimZeroLenAsNull(
				MessageDialog.showInputDlg(this.getEditor(), "会员卡号", "请输入", null,50)
		);
		
		if( ka_code!=null )
		{
//			System.out.println("========="+ka_code);
			
			IHy_huiyuanMaintain iHy_huiyuanMaintain = (IHy_huiyuanMaintain)NCLocator.getInstance().lookup(IHy_huiyuanMaintain.class.getName());
			Object ka_obj = iHy_huiyuanMaintain.insertKadangan(ka_code);
			
			if(ka_obj!=null)
			{
				MessageDialog.showHintDlg(this.getEditor(), "取卡成功", "取卡成功");
				
				this.getBbcxAction().KA_CODE = "'"+ka_code.toUpperCase()+"'";
				this.getBbcxAction().FPH = null;
				
				this.getBbcxAction().bbcx(this.getBbcxAction().KA_CODE, null,false,false);
				
			}
			else
			{
				MessageDialog.showErrorDlg(this.getEditor(), "取卡失败", "取卡失败！！");
			}
			
		}
	}
}
