package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.view;

import hd.vo.pub.tools.PuPubVO;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.handler.LinkQueryData_kp;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.sm.funcreg.FuncRegisterVO;

public class BillListView extends ShowUpableBillListView
implements MouseListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2747022957389671660L;

	public void initUI()
	{
		super.initUI();
		
		this.getBillListPanel().getBodyTable().addMouseListener(this);	// �б���� ��� ���˫���¼�
		
	}

	@Override
	public void mouseClicked(MouseEvent mouseevent) {
		
		if(mouseevent.getClickCount()>=2)
		{
//			System.out.println("=======mouseClicked=======");
			
			FuncRegisterVO funvo =
			          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20631");
			if (null == funvo) {
				nc.vo.pubapp.pattern.exception.ExceptionUtils
		            .wrappBusinessException("��ǰ�û�û�д򿪽ڵ��Ȩ�ޣ�����");
				return;
		      }
			
			int selectRow = this.getBillListPanel().getBodyTable().getSelectedRow();
			String pk_fp = PuPubVO.getString_TrimZeroLenAsNull( 
					this.getBillListPanel().getBodyBillModel().getValueObjectAt(selectRow, "vbdef09") );	// ��Ʊpk
			
			if(pk_fp==null) return;
			
			Dimension size = new Dimension(800, 500);	// ��ʾ�Ĵ�С
			FuncletInitData initData = new FuncletInitData();
			LinkQueryData_kp data= new LinkQueryData_kp();
//			data.billid = "1001N5100000000QCUZ1";
			data.billid = pk_fp;
			
			initData.setInitType(ILinkType.LINK_TYPE_QUERY);
			initData.setInitData(data);
			
			FuncletWindowLauncher.openFuncNodeForceModalDialog(this,funvo,initData,null,false,size);// ��������
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent mouseevent) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent mouseevent) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent mouseevent) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent mouseevent) {
		// TODO �Զ����ɵķ������
		
	}

}
