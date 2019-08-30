package nc.ui.hkjt.huiyuan.kainfo.ace.action;

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
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class ZfkAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ZfkAction() {
		setBtnName("���Ͽ�");
		setCode("zfkAction");
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
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		Object dbilldate = this.getEditor().getBillCardPanel().getHeadItem("dbilldate").getValue();
		
		String dqsj = new UFDateTime().toString();
		
		String zfsj_init = dbilldate.toString().substring(0, 10) + " " + dqsj.substring(11);
		
		BillItem zfka_item = new BillItem();
		zfka_item.setName("���Ͽ���");
		zfka_item.setKey("zfka_code");
		zfka_item.setDataType(IBillItem.STRING);
//		zfka_item.setRefType("��ͬ(�Զ��嵵��)");
		zfka_item.setEdit(true);
//		zfka_item.setNull(false);
//		zfka_item.setValue(ht);
		
		BillItem zfsj_item = new BillItem();
		zfsj_item.setName("����ʱ��");
		zfsj_item.setKey("zfsj");
		zfsj_item.setDataType(IBillItem.DATETIME);
		zfsj_item.setEdit(true);
		zfsj_item.setValue(zfsj_init);
		
		BillItem kje_item = new BillItem();	// �����
		kje_item.setName("�����");
		kje_item.setKey("kje");
		kje_item.setDataType(IBillItem.DECIMAL);
		kje_item.setEdit(true);
		
		BillItem ssje_item = new BillItem();	// ʵ�ս��
		ssje_item.setName("ʵ�ս��");
		ssje_item.setKey("ssje");
		ssje_item.setDataType(IBillItem.DECIMAL);
		ssje_item.setEdit(true);
		
		BillItem zsje_item = new BillItem();	// ���ͽ��
		zsje_item.setName("���ͽ��");
		zsje_item.setKey("zsje");
		zsje_item.setDataType(IBillItem.DECIMAL);
		zsje_item.setEdit(true);
		
		BillItem isdk_item = new BillItem();	// �Ƿ�һ����
		isdk_item.setName("�Ƿ�һ����");
		isdk_item.setKey("dk");
		isdk_item.setDataType(IBillItem.BOOLEAN);
		isdk_item.setEdit(true);
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				 this.getEditor()
				,new BillItem[]{
					 zfka_item,
					 zfsj_item,
					 kje_item,
					 ssje_item,
					 zsje_item,
					 isdk_item,
				});
		dlg.setTitle("���Ͽ�¼��");
		
		if(UIDialog.ID_OK == dlg.showModal()){
			
			String zfka_code = PuPubVO.getString_TrimZeroLenAsNull(zfka_item.getValueObject());	// ���Ͽ���
			String zfsj = PuPubVO.getString_TrimZeroLenAsNull(zfsj_item.getValueObject());		// ����ʱ��
			
			UFDouble kje = PuPubVO.getUFDouble_NullAsZero( kje_item.getValueObject() );		// �����
			UFDouble ssje = PuPubVO.getUFDouble_NullAsZero( ssje_item.getValueObject() );	// ʵ�ս��
			UFDouble zsje = PuPubVO.getUFDouble_NullAsZero( zsje_item.getValueObject() );	// ���ͽ��
			
			UFBoolean isDk = PuPubVO.getUFBoolean_NullAs(isdk_item.getValueObject(), UFBoolean.FALSE);	// �Ƿ�һ����
			
			if( zfka_code==null || zfsj==null )
			{// ���Ͽ��� �� ʱ��  �Ǳ���
				MessageDialog.showErrorDlg(this.getEditor(), "", "������д ���Ͽ��� �� ʱ�䡣");
				return;
			}
			
			// ���� ��Ա�� �����Ϣ��
			StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" ka.pk_hk_huiyuan_kadangan ")	// �� pk
							.append(",kx.kaxing_code ")				// ����code
							.append(",kx.kaxing_name ")				// ��������
							.append(",kx.pk_hk_huiyuan_kaxing ")	// ����pk
							.append(",ka.ka_code ")		// ����
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
							.append(" where ka.dr=0  ")
							.append(" and NLS_UPPER(ka.ka_code) = NLS_UPPER('").append(zfka_code).append("') ")	// ��ת���ɴ�д���Ƚϣ� Ŀ���� �����ִ�Сд
			;
			
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor());
			
			if(list.size()==0)
			{// �ж�����  ��Ա��
				MessageDialog.showErrorDlg(this.getEditor(), "", "ϵͳ�� û�иû�Ա�������顣");
				return;
			}
			
			Object[] ka_value = (Object[])list.get(0);
			
			this.getBodyAddLineAction().doAction(e);	// ����
			
			BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
			
			int endRow = billModel.getRowCount() -1;
			
			billModel.setValueAt("��ֵ", endRow , "xmdl");	// ��Ŀ����
			billModel.setValueAt("���Ͽ�", endRow , "xmlx");	// ��Ŀ����
			billModel.setValueAt(zfsj, endRow , "ywsj");	// ҵ��ʱ��
			billModel.setValueAt(ka_value[0], endRow , "ka_pk");	// ��pk
			billModel.setValueAt(ka_value[1], endRow , "kaxing_code");	// ����code
			billModel.setValueAt(ka_value[2], endRow , "kaxing_name");	// ����name
			billModel.setValueAt(ka_value[3], endRow , "kaxing_pk");	// ����pk
			billModel.setValueAt(ka_value[4], endRow , "ka_code");		// ��code
			
			billModel.setValueAt( kje, endRow , "ka_je");	// �����
			billModel.setValueAt(ssje, endRow , "ka_ss");	// ʵ�ս��
			billModel.setValueAt(zsje, endRow , "ka_zs");	// ���ͽ��
			
			if(isDk.booleanValue())
			{// ����� ɾ��һ���󿨣�   �� ������һ�� ��ת��
				
				this.getBodyAddLineAction().doAction(e);	// ����
				endRow = billModel.getRowCount() -1;
				
				billModel.setValueAt("��ת", endRow , "xmdl");	// ��Ŀ����
				billModel.setValueAt(zfsj, endRow , "ywsj");	// ҵ��ʱ��
				
				billModel.setValueAt("0000000000000000XUNI", endRow , "ka_pk");		// ��pk
				billModel.setValueAt("77", endRow , "kaxing_code");					// ����code
				billModel.setValueAt("�����", endRow , "kaxing_name");				// ����name
				billModel.setValueAt("0001ZZ1000000001CU7L", endRow , "kaxing_pk");	// ����pk
				billModel.setValueAt("���⿨", endRow , "ka_code");					// ��code
				
				billModel.setValueAt(ka_value[0], endRow , "y_ka_pk");		// Դ��pk
				billModel.setValueAt(ka_value[1], endRow , "y_kaxing_code");// Դ����code
				billModel.setValueAt(ka_value[2], endRow , "y_kaxing_name");// Դ����name
				billModel.setValueAt(ka_value[3], endRow , "y_kaxing_pk");	// Դ����pk
				billModel.setValueAt(ka_value[4], endRow , "y_ka_code");	// Դ��code
				
				billModel.setValueAt( kje, endRow , "ka_je");	// �����
				billModel.setValueAt(ssje, endRow , "ka_ss");	// ʵ�ս��
				billModel.setValueAt(zsje, endRow , "ka_zs");	// ���ͽ��
				
				billModel.setValueAt( kje, endRow , "y_ka_je");	// �����
				billModel.setValueAt(ssje, endRow , "y_ka_ss");	// ʵ�ս��
				billModel.setValueAt(zsje, endRow , "y_ka_zs");	// ���ͽ��
				
			}
			
		}
		
	}

}
