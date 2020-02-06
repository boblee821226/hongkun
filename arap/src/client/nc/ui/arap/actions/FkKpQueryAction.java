package nc.ui.arap.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.view.KpmxDialog;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBVO;
/**
 * 付款开票明细
 */
public class FkKpQueryAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2894949279637738342L;

	public FkKpQueryAction() {
		setBtnName("开票明细");
		setCode("fkKpQueryAction");
	}

	protected BillListView listView = null;
	protected AbstractAppModel model = null;
	protected BillForm editor = null;
	
	public BillListView getListView() {
		return listView;
	}
	public void setListView(BillListView listView) {
		this.listView = listView;
	}
	
	public BillForm getEditor() {
		return editor;
	}
	public void setEditor(BillForm editor) {
		this.editor = editor;
	}
	
	public AbstractAppModel getModel()
    {
        return model;
    }
    public void setModel(AbstractAppModel model)
    {
        this.model = model;
        model.addAppEventListener(this);
    }
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		// 根据 付款单的pk，来查询 开票明细
		AggPayBillVO billVO = (AggPayBillVO)this.getModel().getSelectedData();
		if (billVO == null) return;
		PayBillVO hvo = (PayBillVO)billVO.getParentVO();
		
		StringBuffer querySQL = 
			new StringBuffer(" select ")
					.append(" fp.vbillcode vbdef01 ")	// 单据号
					.append(",fp.dbilldate vbdef02 ")	// 开票日期
					.append(",fp.fphm vbdef03 ")		// 发票号
					.append(",fpb.sk_money ")			// 开票金额
					.append(" from hk_fapiao_sk_bill fp ")
					.append(" inner join hk_fapiao_sk_bill_b fpb on fp.pk_hk_fapiao_sk_bill = fpb.pk_hk_fapiao_sk_bill ")
					.append(" where fp.dr=0 and fpb.dr=0 ")
					.append(" and fp.vbilltypecode = 'HK45' ")
					.append(" and fpb.sk_pk = '"+hvo.getPk_paybill()+"' ")
					.append(" order by fp.dbilldate desc ")
		;
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
		ArrayList<BillSkFpBVO> list = (ArrayList<BillSkFpBVO>)iUAPQueryBS.executeQuery(
				 querySQL.toString()
				,new BeanListProcessor(BillSkFpBVO.class) 
		);
		
		BillSkFpBVO[] bVOs = new BillSkFpBVO[list.size()];
		bVOs = list.toArray(bVOs);
		
		Object data = bVOs;
		
		KpmxDialog dialog = null;
		if(this.getListView().isShowing())
			dialog = new KpmxDialog(this.getListView(),data);
		else if(this.getEditor().isShowing())
			dialog = new KpmxDialog(this.getEditor(),data);
		
		if(dialog!=null)
			dialog.showModal();
		
	}

}
