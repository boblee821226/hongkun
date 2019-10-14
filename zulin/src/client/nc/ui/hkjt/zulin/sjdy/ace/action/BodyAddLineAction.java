package nc.ui.hkjt.zulin.sjdy.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class BodyAddLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyAddLineAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7346328761197832169L;

	@Override
	public void doAction() {
		
		try
		{
			/**
			 * 判断  客户、收款开始、结束日期 不能为空
			 */
			String pk_cust  = PuPubVO.getString_TrimZeroLenAsNull(
					getCardPanel().getHeadItem("pk_cust").getValueObject()
				);
			UFDate beg_date = PuPubVO.getUFDate(
					getCardPanel().getHeadItem("vdef01").getValueObject()
				);
			UFDate end_date = PuPubVO.getUFDate(
					getCardPanel().getHeadItem("vdef02").getValueObject()
				);
			UFBoolean isSdf = PuPubVO.getUFBoolean_NullAs(
					getCardPanel().getHeadItem("vdef20").getValueObject()
					, UFBoolean.FALSE
				);
			
			if(
				 pk_cust==null
			 || beg_date==null
			 || end_date==null
			)
			{
				MessageDialog.showWarningDlg(this.getCardPanel(), "", "请填写表头信息");
				return ;
			}
			
			// 清空表体
			int rowCount = getCardPanel().getBillModel().getRowCount();
			if(rowCount>0) {
				int[] delRows = new int[rowCount];
				for(int i=0;i<rowCount;i++) {
					delRows[i] = i;
				}
				getCardPanel().getBodyPanel().delLine(delRows);
			}
			
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			
			/**
			 * 1、查询 收款数据
			 */
			if(isSdf.booleanValue()==false) {
			
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" skb.scomment ")				// 摘要			0
							.append(",skb.def1 ")					// 收入项目pk		1
							.append(",max(srxm.name) ")				// 收入项目name	2
							.append(",sum(skb.local_money_cr) ")	// 收款金额		3
							.append(",LISTAGG(skb.pk_gatheritem,',') WITHIN group(order by skb.pk_gatheritem) ")	// 收款子表pk	4
							.append(" from ar_gatherbill sk")
							.append(" inner join ar_gatheritem skb on sk.pk_gatherbill = skb.pk_gatherbill ")
							.append(" left join bd_defdoc srxm on skb.def1 = srxm.pk_defdoc ")
							.append(" where (1=1) ")
							.append(" and sk.dr=0 and skb.dr=0 ")
							.append(" and sk.pk_tradetypeid = '1001N5100000006E8MW5' ")			// 预收单
							.append(" and skb.customer = '"+pk_cust+"' ")						// 客户
							.append(" and substr(sk.billdate,1,10) " +
									"		between '"+(beg_date.toString().substring(0,10))+"' " +
									"		and '"+(end_date.toString().substring(0,10))+"' ")	// 日期范围
							.append(" group by skb.scomment,skb.def1 ")
				;
				
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(
						 querySQL.toString()
						,new ArrayListProcessor()
				);
				
				if(list!=null&&list.size()>0)
				{
					UFDouble total_skje = UFDouble.ZERO_DBL;	// 合计金额
					
					for(int i=0;i<list.size();i++)
					{
						getCardPanel().getBodyPanel().addLine();	// 增行
						
						Object[] obj = (Object[])list.get(i);
						/**
						  		// 摘要			0
								// 收入项目pk		1
								// 收入项目name	2
								// 收款金额		3
								// 收款子表pk		4
						 */
						getCardPanel().getBillModel().setValueAt(obj[0], i, "zhaiyao");		// 摘要
						getCardPanel().getBillModel().setValueAt(obj[2], i, "srxm");		// 收入项目name
						getCardPanel().getBillModel().setValueAt(obj[3], i, "skje");		// 收款金额
						getCardPanel().getBillModel().setValueAt(obj[4], i, "vbdef01");		// 收款子表pk
						
						total_skje = total_skje.add(
								PuPubVO.getUFDouble_NullAsZero(obj[3])
						);
						
					}
					
					getCardPanel().getHeadItem("vdef03").setValue(total_skje);	// 表头-合计金额
					
				}
			} 
			/**
			 * 2、查询 水电费应收数据
			 */
			else {
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" ysb.def8 ")				// 0、房号pk
						.append(",ysb.def1 ")				// 1、项目pk
						.append(",substr(ysb.def3,1,10) || '至' || substr(ysb.def4,1,10) ")	// 2、摘要
						.append(",room.name ")				// 3、房号
						.append(",srxm.name ")				// 4、项目
						.append(",nvl(weizhi.name,'无') ")	// 5、位置
						.append(",ysb.price ")				// 6、单价
						.append(",ysb.def2 ")				// 7、上期数
						.append(",ysb.def6 ")				// 8、本期数
						.append(",ysb.def5 ")				// 9、本期用量
						.append(",ysb.local_money_de ")		//10、金额
						.append(",ysb.pk_recitem ")			//11、应收bid
						.append(",substr(ysb.busidate,1,10) ")	//12、表体-应缴费日期
						.append(" from ar_recbill ys ")
						.append(" inner join ar_recitem ysb on ys.pk_recbill = ysb.pk_recbill ")
						.append(" left join bd_defdoc room on ysb.def8 = room.pk_defdoc ")
						.append(" left join bd_defdoc srxm on ysb.def1 = srxm.pk_defdoc ")
						.append(" left join bd_defdoc weizhi on ysb.def11 = weizhi.pk_defdoc ")
						.append(" ")
						.append(" where ys.dr=0 and ysb.dr=0 ")
						.append(" and ys.pk_tradetypeid = '1001N51000000063ZZH4' ")			// 水电费应收
						.append(" and ysb.customer = '"+pk_cust+"' ")						// 客户
						.append(" and substr(ysb.busidate,1,10) " +
								"		between '"+(beg_date.toString().substring(0,10))+"' " +
								"		and '"+(end_date.toString().substring(0,10))+"' ")	// 日期范围
				;
				
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(
						 querySQL.toString()
						,new ArrayListProcessor()
				);
				
				if(list!=null&&list.size()>0)
				{
					UFDouble total_je = UFDouble.ZERO_DBL;	// 合计金额
					String yjfrq_str = null;
					boolean isSameYjfrq = true;
					
					for(int i=0;i<list.size();i++)
					{
						getCardPanel().getBodyPanel().addLine();	// 增行
						
						Object[] obj = (Object[])list.get(i);
						
						getCardPanel().getBillModel().setValueAt(obj[2], i, "zhaiyao");		// 2、摘要
						getCardPanel().getBillModel().setValueAt(obj[3], i, "vbdef02");		// 3、房号
						getCardPanel().getBillModel().setValueAt(obj[4], i, "srxm");		// 4、项目
						getCardPanel().getBillModel().setValueAt(obj[5], i, "vbdef03");		// 5、位置
						getCardPanel().getBillModel().setValueAt(obj[6], i, "vbdef04");		// 6、单价
						getCardPanel().getBillModel().setValueAt(obj[7], i, "vbdef05");		// 7、上期数
						getCardPanel().getBillModel().setValueAt(obj[8], i, "vbdef06");		// 8、本期数
						getCardPanel().getBillModel().setValueAt(obj[9], i, "vbdef07");		// 9、本期用量
						getCardPanel().getBillModel().setValueAt(obj[10],i, "skje");		// 10、金额
						getCardPanel().getBillModel().setValueAt(obj[11],i, "vbdef01");		// 11、应收bid
						String yjfrq = PuPubVO.getString_TrimZeroLenAsNull(obj[12]);	// 12、应缴费日期
						getCardPanel().getBillModel().setValueAt(yjfrq,i, "vbdef19");	// 备注 = 应缴费日期
						// 04160100000096
						total_je = total_je.add(
								PuPubVO.getUFDouble_NullAsZero(obj[10])
						);
						if(isSameYjfrq) {	// 只有标志为相同，才进行处理
							if(yjfrq_str == null) {	// 如果为空，则先赋值
								yjfrq_str = yjfrq;
							} else {	// 如果不为空，则进行判断
								if(!yjfrq_str.equals(yjfrq)) {	// 如果不一致，则将标志设置为false
									isSameYjfrq = false;
								}
							}
						}
						
					}
					
					getCardPanel().getHeadItem("vdef03").setValue(total_je);	// 表头-合计金额
					if(isSameYjfrq && yjfrq_str!=null) {
						getCardPanel().getHeadItem("vdef19").setValue(new UFDate(yjfrq_str));	// 表头-日期
					}
					
				}
			}
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
}
