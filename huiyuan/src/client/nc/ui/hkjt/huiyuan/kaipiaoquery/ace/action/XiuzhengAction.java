package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.dcm.chnlrplstrct.maintain.action.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class XiuzhengAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1049580924829904678L;

	public XiuzhengAction() {
		setBtnName("修正卡档案的开票数据");
		setCode("xiuzhengAction");
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
		 * 1、查询出 不一致的数据
		 */
		StringBuffer querySQL = 
			new StringBuffer(" select * from ")
					.append(" ( ")
					.append(" 	select ")
					.append("   kp_ka.pk_hk_huiyuan_kadangan ")
					.append("  ,kp_ka.已开票总额 ")
					.append("  ,kp_dj.已审核 ")
					.append("  ,kp_ka.卡号 ")
					.append("  from ")
					.append("  ( ")
					.append("  	 select")
					.append("    ka.pk_hk_huiyuan_kadangan ")
					.append("	,max(ka.ka_code) 卡号 ")
					.append("   ,sum( nvl(ka_kkp.ykp_je,0) ) 已开票总额 ")
					.append("  	from hk_huiyuan_kadangan ka ")
					.append("  	inner join hk_huiyuan_kadangan_kkp ka_kkp ")
					.append("   on ka.pk_hk_huiyuan_kadangan = ka_kkp.pk_hk_huiyuan_kadangan ")
					.append("   inner join hk_huiyuan_kaxing kx ")
					.append("   on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
					.append("   where ka.dr=0 and ka_kkp.dr=0 ")
					.append("   group by ka.pk_hk_huiyuan_kadangan ")
					.append("  ) kp_ka ")
					.append("  left join ( ")
					.append("  	 select ")
					.append("  	  kpb.ka_pk ")
					.append("  	 ,sum( case when kp.ibillstatus =1 then kpb.fpje else 0 END ) 已审核 ")
					.append("  	 from hk_huiyuan_kaipiaoinfo kp ")
					.append("  	 inner join hk_huiyuan_kaipiaoinfo_b kpb ")
					.append("  	 on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
					.append("  	 where (1=1) ")
					.append("  	 and kp.dr=0 and kpb.dr=0 ")
					.append("  	 group by kpb.ka_pk ")
					.append("  ) kp_dj ")
					.append("  on kp_dj.ka_pk = kp_ka.pk_hk_huiyuan_kadangan ")
					.append("  where (1=1) ")
					.append(" ) bb ")
					.append(" where bb.已开票总额 <> 已审核 ")
					/**
					 * 测试
					 */
//					.append(" and bb.卡号 = 'GJYHZS02000517' ")
					/***END***/
		;
		
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
		ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor() );
		
		String msg = "没有需要修正的数据";
		
		if( list!=null && list.size()>0 )
		{
			msg  = "总共【"+list.size()+"】条数据需要修正，是否进行修正？"+"\r\n";
			msg += "【卡号】【卡档案的已开票总额】【单据上的已开票总额】"+"\r\n";
			for(int i=0;i<list.size();i++)
			{
				Object[] obj = (Object[])list.get(i);
				msg+= "【"+obj[3]+"】【"+obj[1]+"】【"+obj[2]+"】"+"\r\n";
			}
		}else{
			MessageDialog.showWarningDlg(this.getListview(), "", msg);
			return;
		}
		
		int flag = MessageDialog.showYesNoDlg(this.getListview(), "信息", msg);
		
		/**
		 * 2、针对数据 进行处理
		 */
		if( flag!=MessageDialog.ID_YES ){return;}
		
		/**
		 * 2.1、
		 */
		{
			ArrayList<KadanganKKPVO> updateVOs_kkp = new ArrayList<KadanganKKPVO>();	// 要更新的VO  可开票
			
			for(int i=0;i<list.size();i++)		
			{
				Object[] obj = (Object[])list.get(i);
				
				String ka_pk = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
				
				ArrayList<KadanganKKPVO> kkp_list = 
						(ArrayList<KadanganKKPVO>)iUAPQueryBS.retrieveByClause(
						KadanganKKPVO.class,
						" (1=1) " +
						" and dr = 0 " +
						" and pk_hk_huiyuan_kadangan = '"+ka_pk+"' " +
						" and nvl(kkp_je,0) > 0.00 " +		// 可开票总额 大于 0
						" order by kpjz_time "				// 按 开票截止日期 排序
//						new String[]{" kpjz_time "}	// order by 开票截至时间
				);
				
				UFDouble fpje = PuPubVO.getUFDouble_NullAsZero( obj[2] );
				
				// 首先将 已开票金额 赋值为0
				for( KadanganKKPVO kkpVO:kkp_list )
				{
					kkpVO.setYkp_je(UFDouble.ZERO_DBL);
				}
				
				if( fpje.compareTo(UFDouble.ZERO_DBL)>=0 )
				{//  如果 发票金额 为正数， 则正常 抵扣
					
					for( KadanganKKPVO kkpVO:kkp_list )
					{
						UFDouble kkp_je = // 可开票余额  = 可开票金额 - 已开票金额
								 PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
							.sub(PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
						);
						if( fpje.compareTo(kkp_je)>=0 )
						{// 如果 发票金额 大于等于 可开票金额， 则  本行 实际开票金额 为  可开票金额。 发票金额 进行抵扣。
							
							kkpVO.setYkp_je( // 已开票金额 = 之前已开票金额 + 本次开票金额
									  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
								.add( kkp_je )
							);
							fpje = fpje.sub(kkp_je);
						}
						else
						{// 否则， 则  本行 实际开票金额 为  发票金额。 发票金额 抵扣为0。
							
							kkpVO.setYkp_je( // 已开票金额 = 之前已开票金额 + 本次开票金额
									  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
								.add( fpje )
							);
							fpje = UFDouble.ZERO_DBL;
						}
						
						updateVOs_kkp.add(kkpVO);	// 添加要更新的vo
						
						if(fpje.compareTo(UFDouble.ZERO_DBL)==0)
						{// 如果发票金额  抵扣为0了， 则不再抵扣 退出循环
							break;
						}
					}
				}
				else
				{// 如果 发票金额 为负数，则直接累加
					if( kkp_list!=null && kkp_list.size()>0 )
					{
						for(int kkp_i=0;kkp_i<kkp_list.size();kkp_i++)
						{
							KadanganKKPVO kkpVO = kkp_list.get(kkp_i);
							
							UFDouble kkp_je = // 可开票余额  = 可开票金额 - 已开票金额
									  PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
								.sub( PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
							);
								
							if( kkp_je.compareTo(UFDouble.ZERO_DBL)!=0 
							 || kkp_i == kkp_list.size()-1
							  )
							{
								kkpVO.setYkp_je( // 已开票 = 已开票 + 当前开票
										  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
									.add( PuPubVO.getUFDouble_NullAsZero(fpje) )
								);
								
								updateVOs_kkp.add(kkpVO);	// 添加要更新的vo
								
								fpje = UFDouble.ZERO_DBL;
								
								break;
							}
						}
					}
				}
			}
			
			KadanganKKPVO[] vos = new KadanganKKPVO[updateVOs_kkp.size()];
			vos = updateVOs_kkp.toArray(vos);
			HYPubBO_Client.updateAry(vos);	// 更新 可开票数据
			
			nc.ui.pub.beans.MessageDialog.showWarningDlg(this.getListview(), "", "修正完成");
		}
	}
}
