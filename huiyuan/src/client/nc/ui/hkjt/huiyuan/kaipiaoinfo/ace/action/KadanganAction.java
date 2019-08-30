package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_huiyuanMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

public class KadanganAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public KadanganAction() {
		setBtnName("取会员卡");
		setCode("kadanganAction");
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
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		IHy_huiyuanMaintain iHy_huiyuanMaintain = (IHy_huiyuanMaintain)NCLocator.getInstance().lookup(IHy_huiyuanMaintain.class.getName());
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		int[] rows = this.getEditor().getBillCardPanel().getBillTable().getSelectedRows();
		
		for( int i=0;i<rows.length;i++ )
		{
			String ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(rows[i], "ka_code") );
			Object ka_obj = iHy_huiyuanMaintain.insertKadangan(ka_code);
			int row = rows[i];
			if(ka_obj!=null)
			{
				StringBuffer querySQL = 
						new StringBuffer("select ")
								.append(" ka.pk_hk_huiyuan_kadangan ")	// 卡pk
								.append(",kx.pk_hk_huiyuan_kaxing ")	// 卡型pk
								.append(",kx.kaxing_code ")				// 卡型code
								.append(",kx.kaxing_name ")				// 卡型name
								.append(",ka.ykpje ")					// 已开票金额
								.append(",kx.ka_ss ")					// 总共可开票金额（卡型的实收）
								.append(" from hk_huiyuan_kadangan ka ")
								.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
								.append(" where ka.dr=0 ")
								.append(" and ka.ka_code = '").append( ka_code ).append("' ")
				;
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
				
				if( list.size()>0 )
				{
					Object[] obj = (Object[])list.get(0);
					billModel.setValueAt(obj[0],row, "ka_pk"); 
					billModel.setValueAt(obj[1],row, "kaxing_pk"); 
					billModel.setValueAt(obj[2],row, "kaxing_code"); 
					billModel.setValueAt(obj[3],row, "kaxing_name"); 
					billModel.setValueAt(obj[4],row, "zqkpje"); 
					billModel.setValueAt(obj[5],row, "kkpze"); 
				}
			}
		}
	}

}
