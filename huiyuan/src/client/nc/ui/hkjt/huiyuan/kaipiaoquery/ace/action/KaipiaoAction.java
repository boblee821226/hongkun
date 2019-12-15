package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.handler.LinkQueryData_kp;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sm.funcreg.FuncRegisterVO;

public class KaipiaoAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public KaipiaoAction() {
		setBtnName("��Ʊ");
		setCode("kaipiaoAction");
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
		
		/**
		 * ��� ��ѡ���  ������
		 */
		int[] selectRows = this.getListview().getBillListPanel().getHeadTable().getSelectedRows();
		
		if(selectRows==null || selectRows.length<=0)
			return;
		
		/**
		 * �ж� ��� ʣ�࿪Ʊ��� �� 0 �� ������ ��Ʊ����
		 * 2017��5��27��16:58:33
		 */
		for( int i=0;i<selectRows.length;i++ )
		{
			UFDouble sykpje = PuPubVO.getUFDouble_ZeroAsNull(
					this.getListview().getBillListPanel().getHeadBillModel().getValueAt(selectRows[i], "sykpje")
					);
			
			if( sykpje==null )
			{
				MessageDialog.showErrorDlg(this.getListview(), "", "ʣ�࿪Ʊ���Ϊ0�����ÿ�Ʊ��");
				return ; 
			}
		}
		/**END*/
		
		String kaCode_str = "''";
		for(int i=0;i<selectRows.length;i++)
		{
			String ka_code = PuPubVO.getString_TrimZeroLenAsNull(
					this.getListview().getBillListPanel().getHeadBillModel().getValueAt(selectRows[i], "ka_code")
					);
			kaCode_str = kaCode_str + ",'" + ka_code.toUpperCase() + "'";
		}
		
		nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action.BbcxAction.KA_CODE = kaCode_str;
		
		/**END*/
		
		FuncRegisterVO funvo =
		          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20631");
		if (null == funvo) {
			nc.vo.pubapp.pattern.exception.ExceptionUtils
	            .wrappBusinessException("��ǰ�û�û�д򿪽ڵ��Ȩ�ޣ�����");
			return;
	      }
		
		Dimension size = new Dimension(800, 500);	// ��ʾ�Ĵ�С
		FuncletInitData initData = new FuncletInitData();
		LinkQueryData_kp data= new LinkQueryData_kp();
		
		initData.setInitType(ILinkType.LINK_TYPE_ADD);
		initData.setInitData(data);
		
		FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getEditor(),funvo,initData,null,false,size);	// ��������
		
//		System.out.println("++++"+this.getModel());
		
		KaiPiaoInfo_model = this.getListview().getBillListPanel().getHeadBillModel();
		
//		System.out.println("=="+KaiPiaoInfo_model);
		
	}
	
	public static KaipiaoinfoBillVO KaiPiaoInfo_Cache = null;
	public static nc.ui.pub.bill.BillModel KaiPiaoInfo_model = null;
	
}
