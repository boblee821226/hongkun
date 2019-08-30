package nc.ui.hkjt.huiyuan.huanka.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_huankaMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.hkjt.huiyuan.huanka.HuankaHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganHVO;
import nc.vo.pub.lang.UFDouble;

public class HuanKaAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public HuanKaAction() {
		setBtnName("»»¿¨");
		setCode("huankaAction");
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
		
		if( this.getModel().getSelectedData()==null ) return;
		
		IHy_huankaMaintain iHy_huankaMaintain = (IHy_huankaMaintain)NCLocator.getInstance().lookup(IHy_huankaMaintain.class.getName());
		
		HuankaHVO huankaHVO = ((HuankaBillVO)this.getModel().getSelectedData()).getParentVO();
		
		Object result = iHy_huankaMaintain.huanka(huankaHVO, huankaHVO.getPk_hk_huiyuan_huanka());
	
		if( "ok".equals(result) )
		{
			MessageDialog.showWarningDlg(this.getEditor(), "»»¿¨", "»»¿¨³É¹¦£¬ÇëË¢ÐÂÊý¾Ý¡£");
		}
		
	}
	
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}

}
