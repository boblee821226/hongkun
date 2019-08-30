package nc.ui.hkjt.huiyuan.kayue.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kayue.KayueBVO;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.vo.hkjt.huiyuan.kayue.KayueHVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.sm.funcreg.FuncRegisterVO;

public class SymxAction extends NCAction {

	private static final long serialVersionUID = -5244330074432692395L;

	public SymxAction() {
		setBtnName("卡型明细");
		setCode("symxAction");
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
		
		int row = -1;
		BillModel billModel = null;
		
		if( this.getEditor().isShowing() )
		{// 卡片界面
			row = this.getEditor().getBillCardPanel().getBillTable().getSelectedRow();
			billModel = this.getEditor().getBillCardPanel().getBillModel();
		}
		else
		{// 列表界面
			row = this.getListview().getBillListPanel().getBodyTable().getSelectedRow();
			billModel = this.getListview().getBillListPanel().getBodyBillModel();
		}
		
		
		Object kaxing_pk = billModel.getValueAt(row, "kaxing_pk");
		Object kaxing_name = billModel.getValueAt(row, "kaxing_name");
		String ks_date = (String)billModel.getValueAt(row, "vbdef01");
		String js_date = (String)billModel.getValueAt(row, "vbdef02");
		
//		MessageDialog.showErrorDlg(this.getEditor(), "hello", "=="+row+"===="+kaxing_name+"===="+kaxing_pk+"====");
		
		StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" kainfo.ka_pk, ")
						.append(" max(kainfo.ka_code) ka_code, ")
						.append(" sum(kainfo.qichu) qichu, ")
						.append(" sum(kainfo.chongzhi) chongzhi, ")
						.append(" sum(kainfo.xiaofei) xiaofei, ")
						.append(" sum(kainfo.zhuanru) zhuanru, ")
						.append(" sum(kainfo.zhuanchu) zhuanchu, ")
						.append(" sum(kainfo.yue_jg) yue_jg ")
						.append(" from ( ")
							// 期初余额
							.append(" select  ")
							.append(" ka.pk_hk_huiyuan_kadangan ka_pk, ")
							.append(" ka.ka_code ka_code, ")
							.append(" ka.qc_ye qichu, ")
							.append(" 0 chongzhi, ")
							.append(" 0 xiaofei, ")
							.append(" 0 zhuanru, ")
							.append(" 0 zhuanchu, ")
							.append(" 0 yue_jg ")
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" where nvl(ka.dr, 0) = 0 ")
							.append(" and pk_hk_huiyuan_kaxing = '").append(kaxing_pk).append("' ")
							// 业务期初
						.append(" union all ")
							.append("select ")
							.append(" kainfo.ka_pk ")
							.append(",max(kainfo.ka_code) ")
							.append(",sum( nvl(kainfo.chongzhi,0) + nvl(kainfo.xiaofei,0) + nvl(kainfo.zhuanru,0) - nvl(kainfo.zhuanchu,0) ) qichu ")
							.append(",0 chongzhi ")
							.append(",0 xiaofei ")
							.append(",0 zhuanru ")
							.append(",0 zhuanchu ")
							.append(",0 yue_jg ")
							.append("from")
							.append("( ")
								.append(" select ")
								.append(" kib.ka_pk ")
								.append(",max(kib.ka_code) ka_code ")
								.append(",sum( case when kib.xmdl='充值' then kib.ka_je else 0 end ) chongzhi")
								.append(",sum( case when kib.xmdl='消费' then kib.ka_je else 0 end ) xiaofei")
								.append(",sum( case when kib.xmdl='余转' then kib.ka_je else 0 end ) zhuanru")
								.append(",0 zhuanchu ")
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")			// 关联会员卡档案
								.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// 由会员卡的卡型，去管理卡型档案
								.append(" where nvl(ki.dr,0)=0 and nvl(kib.dr,0)=0 ")
								.append(" and nvl(kib.zdh,'null')!='null' ")	// 不取手工作废的数据
								.append(" and substr(ki.dbilldate,0,10) < '"+ks_date+"' ")
								.append(" and kx.pk_hk_huiyuan_kaxing = '").append(kaxing_pk).append("' ")
								.append(" group by kib.ka_pk ")
							.append(" union all ")
								.append(" select ")
								.append(" kib.y_ka_pk ")
								.append(",max(kib.y_ka_code) ")
								.append(",0 ")
								.append(",0 ")
								.append(",0 ")
								.append(",sum( case when kib.xmdl='余转' then kib.ka_je else 0 end ) zhuanchu ")
								.append(" from hk_huiyuan_kainfo ki ")
								.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
								.append(" left join hk_huiyuan_kadangan ka on kib.y_ka_pk = ka.pk_hk_huiyuan_kadangan ")	// 关联会员卡档案
								.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")	// 由会员卡的卡型，去管理卡型档案
								.append(" where nvl(ki.dr,0)=0 and nvl(kib.dr,0)=0 ")
								.append(" and substr(ki.dbilldate,0,10) < '"+ks_date+"' ")
								.append(" and kx.pk_hk_huiyuan_kaxing = '").append(kaxing_pk).append("' ")
								.append(" group by kib.y_ka_pk ")
							.append(") kainfo ")
							.append(" group by kainfo.ka_pk ")
							
						// 本期发生
						.append(" union all ")
							.append(" select ")
							.append(" kib.ka_pk, ")
							.append(" max(kib.ka_code) ka_code, ")
							.append(" 0 qichu, ")
							.append(" sum(case when kib.xmdl = '充值' then kib.ka_je else 0 end) chongzhi, ")
							.append(" sum(case when kib.xmdl = '消费' then kib.ka_je else 0 end) xiaofei, ")
							.append(" sum(case when kib.xmdl = '余转' then kib.ka_je else 0 end) zhuanru, ")
							.append(" 0 zhuanchu, ")
							.append(" 0 yue_jg ")
							.append(" from hk_huiyuan_kainfo ki ")
							.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
							.append(" left join hk_huiyuan_kadangan ka on kib.ka_pk = ka.pk_hk_huiyuan_kadangan ")			// 关联会员卡档案
							.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// 由会员卡的卡型，去管理卡型档案
							.append(" where nvl(ki.dr, 0) = 0 and nvl(kib.dr, 0) = 0 ")
							.append(" and nvl(kib.zdh,'null')!='null' ")	// 不取手工作废的数据
							.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
							.append(" and kx.pk_hk_huiyuan_kaxing = '").append(kaxing_pk).append("' ")
							.append(" group by kib.ka_pk ")
						.append(" union all ")
							.append(" select ")
							.append(" kib.y_ka_pk, ")
							.append(" max(kib.y_ka_code), ")
							.append(" 0, ")
							.append(" 0, ")
							.append(" 0, ")
							.append(" 0, ")
							.append(" sum(case when kib.xmdl = '余转' then kib.ka_je else 0 end) zhuanchu, ")
							.append(" 0 yue_jg ")
							.append(" from hk_huiyuan_kainfo ki ")
							.append(" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo ")
							.append(" left join hk_huiyuan_kadangan ka on kib.y_ka_pk = ka.pk_hk_huiyuan_kadangan ")		// 关联会员卡档案
							.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// 由会员卡的卡型，去管理卡型档案
							.append(" where nvl(ki.dr, 0) = 0 and nvl(kib.dr, 0) = 0 ")
							.append(" and substr(ki.dbilldate,0,10) between '").append(ks_date).append("' and '").append(js_date).append("' ")
							.append(" and kx.pk_hk_huiyuan_kaxing = '").append(kaxing_pk).append("' ")
							.append(" group by kib.y_ka_pk ")
							// 金贵余额
						.append(" union all ")
							.append(" select ")
							.append(" ka.pk_hk_huiyuan_kadangan ka_pk, ")
							.append(" ka.ka_code ka_code, ")
							.append(" 0, ")
							.append(" 0, ")
							.append(" 0, ")
							.append(" 0, ")
							.append(" 0, ")
							.append(" jg.jg_yue yue_jg ")
							.append(" from hk_huiyuan_kadangan ka ")
							.append(" inner join hk_huiyuan_kadangan_jg jg on ka.pk_hk_huiyuan_kadangan = jg.pk_hk_huiyuan_kadangan ")
							.append(" inner join  ")
								.append(" ( ")
								.append(" select ")
								.append("  jg.pk_hk_huiyuan_kadangan ")
								.append(" ,nvl( max( jg.vbdef01 ),'null' )  vbdef01 ")
								.append(" from hk_huiyuan_kadangan_jg jg ")
								.append(" inner join hk_huiyuan_kadangan jgka on jg.pk_hk_huiyuan_kadangan = jgka.pk_hk_huiyuan_kadangan ")
								.append(" where nvl(jg.dr, 0) = 0 and nvl(jgka.dr, 0) = 0 ")
								.append(" and nvl(vbdef01,'1990-01-01 00:00:00')<='"+js_date+" 23:59:59' ")
								.append(" and jgka.pk_hk_huiyuan_kaxing = '").append(kaxing_pk).append("' ")
								.append(" group by jg.pk_hk_huiyuan_kadangan ")
								.append(" ) jgsj on ka.pk_hk_huiyuan_kadangan = jgsj.pk_hk_huiyuan_kadangan ")
							.append(" where nvl(ka.dr, 0) = 0 and nvl(jg.dr, 0) = 0 ")
							.append(" and nvl(jg.vbdef01, 'null') = nvl(jgsj.vbdef01, 'null') ")
							.append(" and ka.pk_hk_huiyuan_kaxing = '").append(kaxing_pk).append("' ")
						.append(" ) kainfo ")
						.append(" group by kainfo.ka_pk ")
//						.append(" having sum(kainfo.yue_jg) != sum(kainfo.qichu)+sum(kainfo.chongzhi)+sum(kainfo.xiaofei)+sum(kainfo.zhuanru)-sum(kainfo.zhuanchu) ")
		;
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		ArrayList<KayueBVO> list_vo = (ArrayList<KayueBVO>)iUAPQueryBS.executeQuery(querySQL.toString(), new BeanListProcessor(KayueBVO.class));
		
		
		KayueBillVO billVO = new KayueBillVO();
		KayueBVO[] result_bvos = new KayueBVO[list_vo.size()];
		result_bvos = list_vo.toArray(result_bvos);
		billVO = new KayueBillVO();
		billVO.setChildrenVO(result_bvos);
		KayueHVO result_hvo = new KayueHVO();
		result_hvo.setIbillstatus(-1);
		result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
		result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org 正德pk
		result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
		result_hvo.setVdef01( ks_date.equals(js_date)?ks_date:(ks_date +" 至 "+js_date) );	// 业务日期
		billVO.setParentVO(result_hvo);
		
		for( int i=0;i<result_bvos.length;i++ )
		{
			result_bvos[i].setZhuanchu( UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getZhuanchu())) );	// 转出 （取负数）
			
			result_bvos[i].setYue( 
					 PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getQichu())		//  期初
				.add(PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getChongzhi()))	// +充值
				.add(PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getZhuanru()))	// +转入
				.add(PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getXiaofei()))	// -消费
				.add(PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getZhuanchu()))	// -转出
			);
			
			result_bvos[i].setChae(	// 差额 = NC余额 - 金贵余额
					 PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getYue())
				.sub(PuPubVO.getUFDouble_NullAsZero(result_bvos[i].getYue_jg()))
			);
		}
		
//		KayueBillVO billVO = (KayueBillVO)this.getEditor().getModel().getSelectedData();
		
		
		
		
		
		
		FuncRegisterVO funvo =
		          WorkbenchEnvironment.getInstance().getFuncRegisterVO("HKJ20605");
		if (null == funvo) {
			nc.vo.pubapp.pattern.exception.ExceptionUtils
	            .wrappBusinessException("当前用户没有打开节点的权限，请检查");
			return;
	      }
		
		Dimension size = new Dimension(800, 500);	// 显示的大小
		FuncletInitData initData = new FuncletInitData();
		LinkQueryData_hy data= new LinkQueryData_hy();
		data.obj = billVO;
		initData.setInitType(ILinkType.LINK_TYPE_QUERY);
		initData.setInitData(data);
		
		FuncletWindowLauncher.openFuncNodeForceModalDialog(this.getEditor(),funvo,initData,null,false,size);	// 弹出界面
//		.openFuncNodeForceModalDialog(this,funvo,size);
//		.openFuncNodeDialog(ClientToolKit.getApplet(), funvo, initData, null, true, false);
		
		
//		BillItem billetem = getEditor().getBillCardPanel().getBodyItem(
//				"shouru_bm" + bh);
//		billetem.setShow(false);
//		getEditor().getBillCardPanel().setBillData(
//				getEditor().getBillCardPanel().getBillData());
//		getEditor().getModel().initModel(vos);
		
	}

}
