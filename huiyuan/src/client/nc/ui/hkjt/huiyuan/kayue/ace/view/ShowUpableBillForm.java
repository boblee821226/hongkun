package nc.ui.hkjt.huiyuan.kayue.ace.view;

import java.awt.event.MouseEvent;

import nc.ui.pub.beans.MessageDialog;

public class ShowUpableBillForm extends nc.ui.pubapp.uif2app.view.ShowUpableBillForm 
		implements java.awt.event.MouseListener
		{
	public static String[] rsbgpks = new String[4];
	/**
	 * 
	 */
	private static final long serialVersionUID = 9183710660877424931L;

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		this.getBillCardPanel().getBillTable().addMouseListener(this);// 添加鼠标监听（双击）
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2)
		{
			int row = this.getBillCardPanel().getBillTable().getSelectedRow();
//			Object pk_group = this.getBillCardPanel().getHeadItem(RsbaogaoHVO.PK_GROUP).getValueObject();
//			Object hid = this.getBillCardPanel().getHeadItem(RsbaogaoHVO.PK_HK_SRGK_HG_RSBAOGAO).getValueObject();
//			Object pk_org = this.getBillCardPanel().getHeadItem(RsbaogaoHVO.PK_ORG).getValueObject();
			
			MessageDialog.showErrorDlg(this, "hello", "=="+row);
			
//			FuncRegisterVO funvo =
//			          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20227");
//			if (null == funvo) {
//				nc.vo.pubapp.pattern.exception.ExceptionUtils
//		            .wrappBusinessException("当前用户没有打开节点的权限，请检查");
//				return;
//		      }
//			
//			Dimension size = new Dimension(800, 500);	// 显示的大小
//			FuncletInitData initData = new FuncletInitData();
//			LinkQueryData data=getQueryDatasByPks(hid,bid,pk_group);
//				initData.setInitType(ILinkType.LINK_TYPE_QUERY);
//				initData.setInitData(data);
//			
////			.openFuncNodeForceModalDialog(this,funvo,size);
//			FuncletWindowLauncher.openFuncNodeForceModalDialog(this,funvo,initData,null,false,size);	// 弹出界面
//			.openFuncNodeDialog(ClientToolKit.getApplet(), funvo, initData, null, true, false);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

}
