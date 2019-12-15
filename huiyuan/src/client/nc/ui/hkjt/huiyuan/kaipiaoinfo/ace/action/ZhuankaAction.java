package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDouble;

public class ZhuankaAction extends NCAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6373782071509305284L;

	public ZhuankaAction() {
		setBtnName("ת��");
		setCode("zhuankaAction");
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
		
		BillModel billModel = this.getEditor().getBillCardPanel().getBillModel();
		
		int selectRow = this.getEditor().getBillCardPanel().getBillTable().getSelectedRow();
		
		if( selectRow<0 )
		{
			MessageDialog.showErrorDlg(this.getEditor(), "", "��ѡ�б����С�");
			return;
		}
		
		String ka_code = PuPubVO.getString_TrimZeroLenAsNull(
				MessageDialog.showInputDlg(this.getEditor(), "ת��", "������ �Է����ţ�", null)
		);
		
		if( ka_code!=null )
		{
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			// ���� ��Ա�� �����Ϣ��
			StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" ka.pk_hk_huiyuan_kadangan ")	// �� pk
							.append(",kx.kaxing_code ")				// ����code
							.append(",kx.kaxing_name ")				// ��������
							.append(",kx.pk_hk_huiyuan_kaxing ")	// ����pk
							.append(",ka.ka_code ")					// ����
							.append(",nvl(ka.kkpze,0)-to_number( case when ka.vdef03='~' then '0' else nvl(ka.vdef03,'0') end )  ")	// �ɿ�Ʊ�ܶ�
							.append(",nvl(ka.ykpje,0) ")			// �ѿ�Ʊ�ܶ�
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
							.append(" where ka.dr=0  ")
							.append(" and NLS_UPPER(ka.ka_code) = NLS_UPPER('").append(ka_code).append("') ")	// ��ת���ɴ�д���Ƚϣ� Ŀ���� �����ִ�Сд
			;
			ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor());
			
			if(list.size()==0)
			{// �ж�����  ��Ա��
				MessageDialog.showErrorDlg(this.getEditor(), "", "ϵͳ�� û�иû�Ա�������顣");
				return;
			}
			
			Object[] ka_value = (Object[])list.get(0);
			
			this.getBodyAddLineAction().doAction(e);	// ����
			
			this.getEditor().getBillCardPanel().setHeadItem("vdef01", "ת��");
			this.getEditor().getBillCardPanel().setHeadItem("fpje", UFDouble.ZERO_DBL);
			this.getEditor().getBillCardPanel().setHeadItem("fph", "ת��");
			
			int endRow = billModel.getRowCount() -1;
			
			billModel.setValueAt(ka_value[0], endRow, "ka_pk");	// ��pk
			billModel.setValueAt(ka_value[1], endRow, "kaxing_code");	// ����code
			billModel.setValueAt(ka_value[2], endRow, "kaxing_name");	// ��������
			billModel.setValueAt(ka_value[3], endRow, "kaxing_pk");	// ����pk
			billModel.setValueAt(ka_value[4], endRow, "ka_code");	// ����
			billModel.setValueAt(ka_value[5], endRow, "kkpze");	// �ɿ�Ʊ�ܶ�
			billModel.setValueAt(ka_value[6], endRow, "zqkpje");	// �ѿ�Ʊ�ܶ�
			
			// ���� ��ֵ  �Է���pk
			billModel.setValueAt( billModel.getValueAt(selectRow,"ka_pk") , endRow, "vsourcebillcode");	// �Է���pk
			billModel.setValueAt( billModel.getValueAt(endRow,"ka_pk") , selectRow, "vsourcebillcode");	// �Է���pk
			
			// ���� ת�����
			billModel.setValueAt( UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(billModel.getValueAt(selectRow,"fpje"))) , endRow, "fpje");
			
			
		}
		
	}

}
