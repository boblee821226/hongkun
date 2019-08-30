package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.RefreshSingleAction;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoHVO;
import nc.vo.pub.SuperVO;

public class SgzfAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public SgzfAction() {
		setBtnName("手工作废");
		setCode("sgzfAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private RefreshSingleAction cardRefreshAction;
	private DefaultRefreshAction defaultRefreshAction;

	public DefaultRefreshAction getDefaultRefreshAction() {
		return defaultRefreshAction;
	}

	public void setDefaultRefreshAction(DefaultRefreshAction defaultRefreshAction) {
		this.defaultRefreshAction = defaultRefreshAction;
	}

	public RefreshSingleAction getCardRefreshAction() {
		return cardRefreshAction;
	}

	public void setCardRefreshAction(RefreshSingleAction cardRefreshAction) {
		this.cardRefreshAction = cardRefreshAction;
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
		
		int[] select_rows = this.getListview().getBillListPanel().getHeadTable().getSelectedRows();
		
//		System.out.println("==="+rows);
		
		if( select_rows==null || select_rows.length<=0 ) return;
		
		IHy_kaipiaoinfoMaintain itf = (IHy_kaipiaoinfoMaintain) NCLocator.getInstance().lookup(IHy_kaipiaoinfoMaintain.class.getName());
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS) NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		for(int i=0;i<select_rows.length;i++ )
		{
			Object type 	  = this.getListview().getBillListPanel().getHeadBillModel().getValueAt( select_rows[i] , "vdef01" );
			Object pk_kaipiao = this.getListview().getBillListPanel().getHeadBillModel().getValueAt( select_rows[i] , "pk_hk_huiyuan_kaipiaoinfo" );
			
			boolean is_chenggong = true;	// 是否成功，  成功之后 才能更改状态。
			
			if( "续卡".equals(type) )
			{
				// 查出 开票子表 数据
				StringBuffer querySQL_1 = 
						new StringBuffer("select kpb.ka_code,kpb.fpje from hk_huiyuan_kaipiaoinfo_b kpb ")
								.append(" where kpb.dr=0 ")
								.append(" and kpb.pk_hk_huiyuan_kaipiaoinfo = '").append(pk_kaipiao).append("' ")
				;
				ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
				
				// 循环子表 进行更新 卡余额
				for( int ii=0;list_1!=null&&ii<list_1.size();ii++ )
				{
					Object[] obj = (Object[])list_1.get(ii);
					Object ka_code = obj[0];
					Object fpje    = obj[1];
					
					StringBuffer updateSQL_2 = 
						new StringBuffer("update hk_huiyuan_kadangan ka ")
								.append(" set ka.ykpje = ka.ykpje - (" + fpje + ") ")
								.append(" where ka.dr=0 ")
								.append(" and ka.ka_code = '" + ka_code + "' ")
								.append(" and ( ka.ykpje - (" + fpje + ") ) <= ka.kkpze - ( to_number(replace(ka.vdef04,'~','0')) ) ")
					;
					Object result_2 = itf.updateSQL(updateSQL_2.toString(), null);
					if( PuPubVO.getInteger_NullAs(result_2,0)<=0 )
					{// 执行sql 后  返回0， 则说明  操作失败， 不能更新状态。
						is_chenggong = false;
					}
						
				}
				
				// 更改状态
				if( is_chenggong )
				{
					StringBuffer updateSQL_3 = 
							new StringBuffer("update hk_huiyuan_kaipiaoinfo kp ")
									.append(" set kp.vdef01 = '作废完成' ")
									.append(" where kp.pk_hk_huiyuan_kaipiaoinfo = '").append(pk_kaipiao).append("' ")
					;
					Object result_3 = itf.updateSQL(updateSQL_3.toString(), null);
					
					MessageDialog.showWarningDlg(this.getEditor(), "", "手工作废成功！");
					
					this.getDefaultRefreshAction().doAction(e);	// 刷新数据
				}
				else
				{
					MessageDialog.showErrorDlg(this.getEditor(), "", "手工作废失败，请检查 开票余额是否足够。");
				}
				
			}
			
		}
		
	}

}
