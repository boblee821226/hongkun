package nc.ui.hkjt.srgk.huiguan.rsbaogao.ace.view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.uif2.UIState;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoHVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.queryscheme.SimpleQuerySchemeVO;
import nc.vo.sm.funcreg.FuncRegisterVO;

public class ShowUpableBillForm extends
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm 
		implements java.awt.event.MouseListener
		{
	public static String[] rsbgpks=new String[4];
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
		if(e.getClickCount()==2&&UIState.NOT_EDIT.equals(this.getModel().getUiState()))
		{
			int row = this.getBillCardPanel().getBillTable().getSelectedRow();
			Object pk_group = this.getBillCardPanel().getHeadItem(RsbaogaoHVO.PK_GROUP).getValueObject();
			Object hid = this.getBillCardPanel().getHeadItem(RsbaogaoHVO.PK_HK_SRGK_HG_RSBAOGAO).getValueObject();
			Object pk_org = this.getBillCardPanel().getHeadItem(RsbaogaoHVO.PK_ORG).getValueObject();
			Object bid = this.getBillCardPanel().getBillModel().getValueAt(row, RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO_B);
			Object zhangtai = this.getBillCardPanel().getBillModel().getValueAt(row, RsbaogaoBVO.ZHANGTAI);//状态
			if(zhangtai==null||"正常".equals(zhangtai.toString())){
				return;
				}
			rsbgpks[0]=hid.toString();
			rsbgpks[1]=bid.toString();
			rsbgpks[2]=pk_group.toString();
			rsbgpks[3]=pk_org.toString();
//			MessageDialog.showErrorDlg(this, "hello", "=="+row+"=="+bid);
			
			FuncRegisterVO funvo =
			          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20227");
			if (null == funvo) {
				nc.vo.pubapp.pattern.exception.ExceptionUtils
		            .wrappBusinessException("当前用户没有打开节点的权限，请检查");
				return;
		      }
			
			Dimension size = new Dimension(800, 500);	// 显示的大小
			FuncletInitData initData = new FuncletInitData();
			LinkQueryData data=getQueryDatasByPks(hid,bid,pk_group);
				initData.setInitType(ILinkType.LINK_TYPE_QUERY);
				initData.setInitData(data);
			
//			.openFuncNodeForceModalDialog(this,funvo,size);
			FuncletWindowLauncher.openFuncNodeForceModalDialog(this,funvo,initData,null,false,size);	// 弹出界面
//			.openFuncNodeDialog(ClientToolKit.getApplet(), funvo, initData, null, true, false);
		}
		
	}
	IUAPQueryBS ibs=null;
	public LinkQueryData getQueryDatasByPks(Object pk_rsbaogao_h,Object pk_rsbaogao_b,Object pk_group){
		if(ibs==null){
			ibs=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		LinkQueryData linkQueryDate=null;
		try{
		String sql="select  distinct c.* from hk_srgk_hg_rsbaogao_c c where nvl(dr,0)=0 and c.pk_hk_srgk_hg_rsbaogao='"+pk_rsbaogao_h+"' and c.pk_hk_srgk_hg_rsbaogao_b='"+pk_rsbaogao_b+"' and c.pk_group='"+pk_group+"'";
		ArrayList<RsbaogaoCVO> cvos=(ArrayList<RsbaogaoCVO>)ibs.executeQuery(sql, new BeanListProcessor(RsbaogaoCVO.class));
		linkQueryDate=new LinkQueryData();
		linkQueryDate.obj=cvos.toArray(new RsbaogaoCVO[]{});
		
		}catch(Exception e){
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return linkQueryDate;
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
	
	public class LinkQueryData implements ILinkQueryData
	{
		String billid;
		Object obj;

		@Override
		public String getBillID() {
			return billid;
		}

		@Override
		public String getBillType() {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public String getPkOrg() {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public Object getUserObject() {
			// TODO 自动生成的方法存根
			return obj;
		}
		
	}

}
