package nc.ui.arap.actions;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.sfbase.client.ClientToolKit;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * �±� ȡ��
 *
 */
public class SkKaipiaoAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public SkKaipiaoAction() {
		setBtnName("��Ʊ�Ǽ�");
		setCode("skKaipiaoAction");
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
	public void doAction(ActionEvent e) throws Exception {
		
//		MessageDialog.showErrorDlg(null, "����", "����");
		
		FuncRegisterVO funvo =
		          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20634");
		if (null == funvo) {
			nc.vo.pubapp.pattern.exception.ExceptionUtils
	            .wrappBusinessException("��ǰ�û�û�д򿪽ڵ��Ȩ�ޣ�����");
			return;
	      }
		
		FuncletInitData initData = new FuncletInitData();
		
		initData.setInitType(ILinkType.LINK_TYPE_ADD);
		
		if(this.getEditor().isShowing())
		{// ��Ƭ����
		
			AggGatheringBillVO billVO = (AggGatheringBillVO)this.getModel().getSelectedData();
			
			GatheringBillVO hvo = (GatheringBillVO)billVO.getParentVO();
			
			String pk_customer = hvo.getCustomer();
			String pk_gatherbill = hvo.getPk_gatherbill();
			String billno = hvo.getBillno();
			String pk_org   = hvo.getPk_org();
			String pk_org_v = hvo.getPk_org_v();
			
			MAP_SkKaiPiao.put("pk_customer", pk_customer);
			MAP_SkKaiPiao.put("pk_gatherbill", new String[]{pk_gatherbill});
			MAP_SkKaiPiao.put("billno", new String[]{billno});
			MAP_SkKaiPiao.put("ts", System.currentTimeMillis());	// ��¼�㰴ť��ʱ���
			MAP_SkKaiPiao.put("pk_org", pk_org);
			MAP_SkKaiPiao.put("pk_org_v", pk_org_v);
			
		}else if(this.getListView().isShowing())
		{// �б����
//			this.getListView().getBillListPanel().getHeadTable().getModel();
			int[] rows = this.getListView().getBillListPanel().getHeadTable().getSelectedRows();
			BillModel hModel = this.getListView().getBillListPanel().getHeadBillModel();
			
			String[] str_pk_gatherbill = new String[rows.length];
			String[] str_billno = new String[rows.length];
			
			String pk_customer = null;
			
			for(int i=0;i<rows.length;i++)
			{
				String temp_pk_customer = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "customer") );
				if(pk_customer==null)
				{
					pk_customer = temp_pk_customer;
				}
				else
				{
					if(!pk_customer.equals(temp_pk_customer))
					{
						MessageDialog.showErrorDlg(this.getListView() , "", "�ͻ���ͬ ���ܿ�Ʊ��");
						return;
					}
				}
				
				str_pk_gatherbill[i] = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "pk_gatherbill") );
				str_billno[i] = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "billno") );
				
			}
			
			MAP_SkKaiPiao.put("pk_customer", pk_customer);
			MAP_SkKaiPiao.put("pk_gatherbill", str_pk_gatherbill);
			MAP_SkKaiPiao.put("billno", str_billno);
			MAP_SkKaiPiao.put("ts", System.currentTimeMillis());	// ��¼�㰴ť��ʱ���
			
		}
		
		java.awt.Dimension size = ClientToolKit.getUserClientSize();
        size.setSize(size.width * 0.9, size.height * 0.7);
//		Dimension size = new Dimension(800, 500);	// ��ʾ�Ĵ�С
		
        FuncletWindowLauncher.openFuncNodeDialog(
       		 WorkbenchEnvironment.getInstance().getWorkbench()
       		, funvo, initData
       		, null, true, true
       		, size, true
       );
		
	}
	
	public static HashMap<String,Object> MAP_SkKaiPiao = new HashMap<String,Object>();
	
}
