package nc.ui.hkjt.huiyuan.kadangan.ace.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.RefreshSingleAction;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganHVO;

public class GhkxAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public GhkxAction() {
		setBtnName("��������");
		setCode("ghkxAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction bodyAddLineAction;
	private RefreshSingleAction refreshCardAction;

	public RefreshSingleAction getRefreshCardAction() {
		return refreshCardAction;
	}

	public void setRefreshCardAction(RefreshSingleAction refreshCardAction) {
		this.refreshCardAction = refreshCardAction;
	}

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
		
		Object selectedData = this.getModel().getSelectedData();
		if( selectedData==null ) return;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		BillItem kxcode_item = new BillItem();
		kxcode_item.setName("�¿��ͱ���");
		kxcode_item.setKey("kxcode");
		kxcode_item.setDataType(IBillItem.STRING);
		kxcode_item.setEdit(true);
		
		BillItem kxname_item = new BillItem();
		kxname_item.setName("�¿�������");
		kxname_item.setKey("kxname");
		kxname_item.setDataType(IBillItem.STRING);
		kxname_item.setEdit(true);
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				 this.getEditor()
				,new BillItem[]{
					 kxcode_item,
					 kxname_item,
				});
		dlg.setTitle("��������");
		
		if(UIDialog.ID_OK == dlg.showModal()){
			
			String kxcode = PuPubVO.getString_TrimZeroLenAsNull(kxcode_item.getValueObject());	// ����code
			String kxname = PuPubVO.getString_TrimZeroLenAsNull(kxname_item.getValueObject());	// ����name
			
			if( kxcode==null && kxname==null )
			{// ���Ͽ��� �� ʱ��  �Ǳ���
				MessageDialog.showErrorDlg(this.getEditor(), "", "�¿��ͱ���  �� �¿������ƣ�������һ ��");
				return;
			}
			
			// ���� ���� �����Ϣ��
			StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" kx.pk_hk_huiyuan_kaxing ")	// ���� pk
							.append(" from hk_huiyuan_kaxing kx ")	// ����
							.append(" where kx.dr=0  ")
							.append( kxcode==null?"":" and kx.kaxing_code = '"+kxcode+"' ")
							.append( kxname==null?"":" and kx.kaxing_name = '"+kxname+"' ")
			;
			
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor());
			
			if(list.size()==0)
			{// �ж�����  ��Ա��
				MessageDialog.showErrorDlg(this.getEditor(), "", "ϵͳ�� û�иÿ��ͣ����顣");
				return;
			}
			
			String kx_pk_new = PuPubVO.getString_TrimZeroLenAsNull( ((Object[])list.get(0))[0] );
			
			KadanganHVO hVO = ((KadanganBillVO)selectedData).getParentVO();
			String kx_pk_old = hVO.getPk_hk_huiyuan_kaxing();
			
			if( !kx_pk_old.equals(kx_pk_new) )
			{// ���  �¾ɿ��Ͳ�һ��  ���޸�
				hVO.setDr(0);
				hVO.setPk_hk_huiyuan_kaxing(kx_pk_new);
				
				HYPubBO_Client.update(hVO);
				
				this.getRefreshCardAction().doAction(e);	// ������ ˢ�¿�Ƭ
			}
		}
	}
}
