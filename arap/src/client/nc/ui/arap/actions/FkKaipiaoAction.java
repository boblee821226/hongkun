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
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * 付款开票登记
 *
 */
public class FkKaipiaoAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public FkKaipiaoAction() {
		setBtnName("开票登记");
		setCode("fkKaipiaoAction");
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
		
//		MessageDialog.showErrorDlg(null, "测试", "测试");
		
		FuncRegisterVO funvo =
		          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20643");
		if (null == funvo) {
			nc.vo.pubapp.pattern.exception.ExceptionUtils
	            .wrappBusinessException("当前用户没有打开节点的权限，请检查");
			return;
	      }
		
		FuncletInitData initData = new FuncletInitData();
		
		initData.setInitType(ILinkType.LINK_TYPE_ADD);
		
		if(this.getEditor().isShowing())
		{// 卡片界面
//			Object obj = this.getModel().getSelectedData();
//			System.out.println(obj);
			AggPayBillVO billVO = (AggPayBillVO)this.getModel().getSelectedData();
			PayBillVO hvo = (PayBillVO)billVO.getParentVO();
			
			String pk_supplier = hvo.getSupplier();
			String pk_paybill = hvo.getPk_paybill();
			String billno = hvo.getBillno();
			String pk_org   = hvo.getPk_org();
			String pk_org_v = hvo.getPk_org_v();
			
			MAP_FkKaiPiao.put("pk_supplier", pk_supplier);
			MAP_FkKaiPiao.put("pk_paybill", new String[]{pk_paybill});
			MAP_FkKaiPiao.put("billno", new String[]{billno});
			MAP_FkKaiPiao.put("ts", System.currentTimeMillis());	// 记录点按钮的时间戳
			MAP_FkKaiPiao.put("pk_org", pk_org);
			MAP_FkKaiPiao.put("pk_org_v", pk_org_v);
			
		}else if(this.getListView().isShowing())
		{// 列表界面
//			this.getListView().getBillListPanel().getHeadTable().getModel();
			int[] rows = this.getListView().getBillListPanel().getHeadTable().getSelectedRows();
			
			if (rows == null || rows.length == 0) {
				return;
			}
			
			BillModel hModel = this.getListView().getBillListPanel().getHeadBillModel();
			
			String[] str_pk_paybill = new String[rows.length];
			String[] str_billno = new String[rows.length];
			
			String pk_supplier = null;
			String pk_org = null;
			String pk_org_v = null;
			
			for(int i = 0; i < rows.length; i++)
			{
				String temp_pk_supplier = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "supplier") );
				if (pk_supplier == null)
				{
					pk_supplier = temp_pk_supplier;
				}
				else
				{
					if(!pk_supplier.equals(temp_pk_supplier))
					{
						MessageDialog.showErrorDlg(this.getListView() , "", "对方不同 不能开票。");
						return;
					}
				}
				
				String temp_pk_org = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "pk_org") );
				String temp_pk_org_v = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "pk_org_v") );
				if(pk_org == null)
				{
					pk_org = temp_pk_org;
					pk_org_v = temp_pk_org_v;
				}
				else
				{
					if(!pk_org.equals(temp_pk_org))
					{
						MessageDialog.showErrorDlg(this.getListView() , "", "组织不同 不能开票。");
						return;
					}
				}
				
				str_pk_paybill[i] = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "pk_paybill") );
				str_billno[i] = PuPubVO.getString_TrimZeroLenAsNull( hModel.getValueAt(rows[i], "billno") );
				
			}
			
			MAP_FkKaiPiao.put("pk_supplier", pk_supplier);
			MAP_FkKaiPiao.put("pk_paybill", str_pk_paybill);
			MAP_FkKaiPiao.put("billno", str_billno);
			MAP_FkKaiPiao.put("ts", System.currentTimeMillis());	// 记录点按钮的时间戳
			MAP_FkKaiPiao.put("pk_org", pk_org);
			MAP_FkKaiPiao.put("pk_org_v", pk_org_v);
			
		}
		
		java.awt.Dimension size = ClientToolKit.getUserClientSize();
        size.setSize(size.width * 0.9, size.height * 0.7);
//		Dimension size = new Dimension(800, 500);	// 显示的大小
		
        FuncletWindowLauncher.openFuncNodeDialog(
       		 WorkbenchEnvironment.getInstance().getWorkbench()
       		, funvo, initData
       		, null, true, true
       		, size, true
       );
		
	}
	
	public static HashMap<String,Object> MAP_FkKaiPiao = new HashMap<String,Object>();
	
}
