package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pub.lang.UFDouble;

public class SaveAction extends SaveScriptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3650223837762021631L;
	private ShowUpableBillForm editor2;
	
	public ShowUpableBillForm getEditor2() {
		return editor2;
	}

	public void setEditor2(ShowUpableBillForm editor2) {
		this.editor2 = editor2;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		UFDouble fpje = PuPubVO.getUFDouble_NullAsZero(
				this.getEditor2().getBillCardPanel().getHeadItem("fpje").getValueObject()
				);
		
//		/**
//		 * ѭ������  �� ��Ʊ���  �������
//		 * ���  2016��4��23��13:23:44
//		 */
//		UFDouble fpje = UFDouble.ZERO_DBL;
//		int rowCount = this.getEditor2().getBillCardPanel().getBillModel().getRowCount();
//		for(int i=0;i<rowCount;i++)
//		{
//			UFDouble fpje_row = PuPubVO.getUFDouble_NullAsZero(
//					this.getEditor2().getBillCardPanel().getBillModel().getValueObjectAt(i, "fpje")
//					);
//			fpje = fpje.add(fpje_row);
//		}
		/**END*/
		
		if(fpje.compareTo(UFDouble.ZERO_DBL)<0)
		{// ������С��0�� �� ��Ҫ���� ����ѡ��ĶԻ��򣬱���ѡ�����͡�
			BillItem type_item = new BillItem();
			type_item.setName("����");
			type_item.setKey("type");
			type_item.setDataType(IBillItem.COMBO);
			type_item.setEdit(true);
			UIComboBox type_cbb = (UIComboBox)type_item.getComponent();
			type_cbb.addItems(new Object[]{"����","����"});
			
			PubBatchEditDialog dlg = new PubBatchEditDialog(
					 this.getEditor2()
					,new BillItem[]{
						 type_item,
					});
			dlg.setTitle("������Ҫ˵������");
			
			if(UIDialog.ID_OK == dlg.showModal()){
				
				String type = PuPubVO.getString_TrimZeroLenAsNull(type_item.getValueObject());
				
				this.getEditor2().getBillCardPanel().getHeadItem("vdef01").setValue(type);
//				this.getEditor2().getBillCardPanel().getHeadItem("fph").setValue(type);
				
			}
			
		}
		
		super.doAction(e);
		
	}
	
	

}
