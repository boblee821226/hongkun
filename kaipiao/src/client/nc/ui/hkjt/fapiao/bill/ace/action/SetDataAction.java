package nc.ui.hkjt.fapiao.bill.ace.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.hkjt.IHk_fp_billMaintain;
import nc.ui.hkjt.pub.Pub_Param;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

/**
 * ���� ���� Ĭ�ϵ� ��Ʊ���롢��Ʊ���룬  �ڵ�������ʱ Ĭ�ϴ�����
 * @author lb
 *
 */
public class SetDataAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public SetDataAction() {
		setBtnName("��������");
		setCode("setDataAction");
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
		
		IHk_fp_billMaintain itf = (IHk_fp_billMaintain)NCLocator.getInstance().lookup(IHk_fp_billMaintain.class.getName());
		
		String cuserid = WorkbenchEnvironment.getInstance().getLoginUser().getCuserid();	// �û�id
		
		BillItem fpdm_item = new BillItem();
		fpdm_item.setName("��Ʊ����");
		fpdm_item.setKey("fpdm");
		fpdm_item.setDataType(IBillItem.STRING);
		fpdm_item.setValue(Pub_Param.getInstance().FPDM);
		fpdm_item.setEdit(true);
		fpdm_item.setNull(false);	// false ����Ϊ��
		
		BillItem fphm_item = new BillItem();
		fphm_item.setName("��Ʊ����");
		fphm_item.setKey("fphm");
		fphm_item.setDataType(IBillItem.STRING);
		fphm_item.setValue(Pub_Param.getInstance().FPHM);
		fphm_item.setEdit(true);
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				 this.getEditor()
				,new BillItem[]{
					 fpdm_item,
					 fphm_item,
				});
		dlg.setTitle("��������");
		
		if(UIDialog.ID_OK == dlg.showModal()){
			
			String fpdm = PuPubVO.getString_TrimZeroLenAsNull(fpdm_item.getValueObject());		// ��Ʊ����
			String fphm = PuPubVO.getString_TrimZeroLenAsNull(fphm_item.getValueObject());		// ��Ʊ����
			
			if(fpdm==null) fpdm="";
			if(fphm==null) fphm="";
			
			StringBuffer exceSQL = 
				new StringBuffer(" MERGE into hk_fapiao_setdata d1 ")
						.append(" using (select count(0) co from hk_fapiao_setdata where cuserid='"+cuserid+"') d2 ")
						.append(" on (d2.co<>0) ")
						.append(" when matched then ")
						.append(" update set fpdm='"+fpdm+"',fphm='"+fphm+"',TS=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where cuserid='"+cuserid+"' ")
						.append(" when not matched then ")
						.append(" insert(cuserid,fpdm,fphm,dr,ts) values('"+cuserid+"','"+fpdm+"','"+fphm+"',0,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')) ")
			;
			
			itf.exceSQL(exceSQL.toString());
			
			Pub_Param.getInstance().FPDM = fpdm;
			Pub_Param.getInstance().FPHM = fphm;
		}
	}
}
