package nc.ui.hkjt.huiyuan.kainfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kadangan.KadanganHVO;
import nc.vo.pub.lang.UFDouble;

public class CheckAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public CheckAction() {
		setBtnName("È¡Êý");
		setCode("checkAction");
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
		
		KA_MAP.clear();	// Ã¿´ÎÈ¡Êý£¬ÏÈ Çå¿Õ»º´æ
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		int rowCount = billModel.getRowCount();
		
		
		
		for(int i=0;i<rowCount;i++)
		{
			String xmka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "xmka_code") );	// ÐÝÃß¿¨ºÅ
			String ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "ka_code") );	// ¿¨ºÅ
			String ka_pk   = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "ka_pk") );	// ¿¨pk
			String y_ka_code = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "y_ka_code") );	// Ô´¿¨ºÅ
			String y_ka_pk   = PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "y_ka_pk") );	// Ô´¿¨pk
			
			if( xmka_code!=null )
			{// ¸ø ÐÝÃß¿¨  ¸³ÉÏpk
				KadanganHVO vo_MAP = getKadanganHVO(xmka_code,"ÐÝÃß");
				if( vo_MAP!=null )
				{
					 billModel.setValueAt(vo_MAP.getPk_hk_huiyuan_kadangan(), i, "xmka_pk");
				}
			}
			
			if( ka_pk==null )
			{// ¿¨pk
				KadanganHVO vo_MAP = getKadanganHVO(ka_code,null);
				if( vo_MAP!=null )
				{
					 billModel.setValueAt(vo_MAP.getPk_hk_huiyuan_kadangan(), i, "ka_pk");
					 billModel.setValueAt(vo_MAP.getPk_hk_huiyuan_kaxing(), i, "kaxing_pk");
					 billModel.setValueAt(vo_MAP.getKabili(), i, "kabili");
					 billModel.setValueAt(
							 		PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"kabili"))
						.multiply(  PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"ka_je")) )
							 ,i,"ka_ss");
				}
			}
			
			if( y_ka_code!=null && y_ka_pk==null )
			{// ¸ø Ô´¿¨  ¸³ÉÏpk
				KadanganHVO vo_MAP = getKadanganHVO(y_ka_code,null);
				if( vo_MAP!=null )
				{
					 billModel.setValueAt(vo_MAP.getPk_hk_huiyuan_kadangan(), i, "y_ka_pk");
					 billModel.setValueAt(vo_MAP.getPk_hk_huiyuan_kaxing(), i, "y_kaxing_pk");
					 billModel.setValueAt(vo_MAP.getKabili(), i, "y_kabili");
					 billModel.setValueAt(
							 		PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"y_kabili"))
						.multiply(  PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(i,"y_ka_je")) )
							 ,i,"y_ka_ss");
				}
			}
		}
	}
	
	HashMap<String,KadanganHVO> KA_MAP = new HashMap<String,KadanganHVO>();	// ¿¨»º´æ
	private KadanganHVO getKadanganHVO(String ka_code,String flag) throws Exception
	{
		if( !KA_MAP.containsKey(ka_code) )
		{
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			ArrayList<KadanganHVO> list = (ArrayList<KadanganHVO>)iUAPQueryBS.retrieveByClause(KadanganHVO.class
					," dr=0 and ka_code ='"+ka_code+"' " 
					+( "ÐÝÃß".equals(flag) ? " and kastatus = 'ÐÝÃß' " : "" )
					);
			
			if( list.size()>0 )
			{
				KadanganHVO vo = list.get(0);
				KA_MAP.put(ka_code, vo);
			}
			else 
			{
				KA_MAP.put(ka_code, null);
			}
		}
		
		return KA_MAP.get(ka_code);
	}
	
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}

}
