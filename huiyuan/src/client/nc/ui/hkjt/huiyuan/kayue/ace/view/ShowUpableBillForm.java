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
		// TODO �Զ����ɵķ������
		super.initUI();
		this.getBillCardPanel().getBillTable().addMouseListener(this);// �����������˫����
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
//		            .wrappBusinessException("��ǰ�û�û�д򿪽ڵ��Ȩ�ޣ�����");
//				return;
//		      }
//			
//			Dimension size = new Dimension(800, 500);	// ��ʾ�Ĵ�С
//			FuncletInitData initData = new FuncletInitData();
//			LinkQueryData data=getQueryDatasByPks(hid,bid,pk_group);
//				initData.setInitType(ILinkType.LINK_TYPE_QUERY);
//				initData.setInitData(data);
//			
////			.openFuncNodeForceModalDialog(this,funvo,size);
//			FuncletWindowLauncher.openFuncNodeForceModalDialog(this,funvo,initData,null,false,size);	// ��������
//			.openFuncNodeDialog(ClientToolKit.getApplet(), funvo, initData, null, true, false);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

}
