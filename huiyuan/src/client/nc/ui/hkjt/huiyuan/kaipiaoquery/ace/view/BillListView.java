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
		
		this.getBillListPanel().getBodyTable().addMouseListener(this);	// 列表表体 添加 鼠标双击事件
		
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
		            .wrappBusinessException("当前用户没有打开节点的权限，请检查");
				return;
		      }
			
			int selectRow = this.getBillListPanel().getBodyTable().getSelectedRow();
			String pk_fp = PuPubVO.getString_TrimZeroLenAsNull( 
					this.getBillListPanel().getBodyBillModel().getValueObjectAt(selectRow, "vbdef09") );	// 发票pk
			
			if(pk_fp==null) return;
			
			Dimension size = new Dimension(800, 500);	// 显示的大小
			FuncletInitData initData = new FuncletInitData();
			LinkQueryData_kp data= new LinkQueryData_kp();
//			data.billid = "1001N5100000000QCUZ1";
			data.billid = pk_fp;
			
			initData.setInitType(ILinkType.LINK_TYPE_QUERY);
			initData.setInitData(data);
			
			FuncletWindowLauncher.openFuncNodeForceModalDialog(this,funvo,initData,null,false,size);// 弹出界面
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent mouseevent) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent mouseevent) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent mouseevent) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent mouseevent) {
		// TODO 自动生成的方法存根
		
	}

}
