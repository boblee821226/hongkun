package nc.ui.hkjt.zulin.sjdy.ace.action;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import hd.vo.pub.tools.PuPubVO;

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
			
			if(
				 pk_cust==null
			 || beg_date==null
			 || end_date==null
			)
			{
				MessageDialog.showWarningDlg(this.getCardPanel(), "", "请填写表头信息");
				return ;
			}
			
			/**
			 * 查询数据
			 */
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
			
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
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
					getCardPanel().getBillModel().setValueAt(obj[0], i, "zhaiyao");	// 摘要
	//				getCardPanel().getBillModel().setValueAt(obj[1], i, "srxm");		// 收入项目pk
					getCardPanel().getBillModel().setValueAt(obj[2], i, "srxm");		// 收入项目
					getCardPanel().getBillModel().setValueAt(obj[3], i, "skje");		// 收款金额
					getCardPanel().getBillModel().setValueAt(obj[4], i, "vbdef01");	// 收款子表pk
					
					total_skje = total_skje.add(
							PuPubVO.getUFDouble_NullAsZero(obj[3])
					);
					
				}
				
				getCardPanel().getHeadItem("vdef03").setValue(total_skje);	// 表体-合计金额
				
			}
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
}
