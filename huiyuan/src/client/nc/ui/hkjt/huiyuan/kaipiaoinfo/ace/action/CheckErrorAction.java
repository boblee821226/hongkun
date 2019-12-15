package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

/**
 * ��� ����״̬�ķ�Ʊ
 * @author lb
 *
 */
public class CheckErrorAction extends NCAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5597537533027078884L;

	public CheckErrorAction() {
		setBtnName("������Ʊ");
		setCode("checkErrorAction");
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
	public void doAction(ActionEvent arg0) throws Exception {
		
		/**
		 * ֻ���� ����״̬������
		 */
		ArrayList<String> error_key = new ArrayList<String>();
		
		nc.ui.pub.bill.BillModel billModel = this.getListview().getBillListPanel().getHeadBillModel();
		
		for(int i = 0;i<billModel.getRowCount();i++)
		{
			if( ! "����".equals( billModel.getValueAt(i, "vdef02") ) )
			{// ��Ϊ������  ����Ҫ����
				error_key.add( PuPubVO.getString_TrimZeroLenAsNull( billModel.getValueAt(i, "pk_hk_huiyuan_kaipiaoinfo") ) );
			}
		}
		
		if(error_key.size()<=0)
		{
			MessageDialog.showHintDlg(this.getListview()
					, "������"
	        		, "û��Ҫ�������ݡ�"
	        );
			return;
		}
		
		// ���ýӿ�  ��̨����
		IHy_kaipiaoinfoMaintain iHy_kaipiaoinfoMaintain = (IHy_kaipiaoinfoMaintain)NCLocator.getInstance().lookup(IHy_kaipiaoinfoMaintain.class.getName());
		Object result = iHy_kaipiaoinfoMaintain.checkErrorFP(error_key, null);
		
		Object[] obj = (Object[])result;
		Integer update_num = PuPubVO.getInteger_NullAs(obj[0],0);
		String errorMsg = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
		
		String showMsg = 
				"�����£� "+update_num+"/"+error_key.size() + " \r\n��ˢ�����ݡ�\r\n"
			   +( errorMsg==null
			     ?"��ѡ�����Ѿ�ȫ�����ͨ����"
			     :"������ʾ��\r\n"+errorMsg)
		;
		
		MessageDialog.showHintDlg(this.getListview()
				, "������"
        		, showMsg
        );
		
	}

}
