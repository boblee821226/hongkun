package nc.ui.hkjt.zulin.sjdy.ace.action;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.lang.UFBoolean;
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
			 * �ж�  �ͻ����տʼ���������� ����Ϊ��
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
				MessageDialog.showWarningDlg(this.getCardPanel(), "", "����д��ͷ��Ϣ");
				return ;
			}
			
			// ��ձ���
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
			 * 1����ѯ �տ�����
			 */
			if(isSdf.booleanValue()==false) {
			
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" skb.scomment ")				// ժҪ			0
							.append(",skb.def1 ")					// ������Ŀpk		1
							.append(",max(srxm.name) ")				// ������Ŀname	2
							.append(",sum(skb.local_money_cr) ")	// �տ���		3
							.append(",LISTAGG(skb.pk_gatheritem,',') WITHIN group(order by skb.pk_gatheritem) ")	// �տ��ӱ�pk	4
							.append(" from ar_gatherbill sk")
							.append(" inner join ar_gatheritem skb on sk.pk_gatherbill = skb.pk_gatherbill ")
							.append(" left join bd_defdoc srxm on skb.def1 = srxm.pk_defdoc ")
							.append(" where (1=1) ")
							.append(" and sk.dr=0 and skb.dr=0 ")
							.append(" and sk.pk_tradetypeid = '1001N5100000006E8MW5' ")			// Ԥ�յ�
							.append(" and skb.customer = '"+pk_cust+"' ")						// �ͻ�
							.append(" and substr(sk.billdate,1,10) " +
									"		between '"+(beg_date.toString().substring(0,10))+"' " +
									"		and '"+(end_date.toString().substring(0,10))+"' ")	// ���ڷ�Χ
							.append(" group by skb.scomment,skb.def1 ")
				;
				
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(
						 querySQL.toString()
						,new ArrayListProcessor()
				);
				
				if(list!=null&&list.size()>0)
				{
					UFDouble total_skje = UFDouble.ZERO_DBL;	// �ϼƽ��
					
					for(int i=0;i<list.size();i++)
					{
						getCardPanel().getBodyPanel().addLine();	// ����
						
						Object[] obj = (Object[])list.get(i);
						/**
						  		// ժҪ			0
								// ������Ŀpk		1
								// ������Ŀname	2
								// �տ���		3
								// �տ��ӱ�pk		4
						 */
						getCardPanel().getBillModel().setValueAt(obj[0], i, "zhaiyao");		// ժҪ
						getCardPanel().getBillModel().setValueAt(obj[2], i, "srxm");		// ������Ŀname
						getCardPanel().getBillModel().setValueAt(obj[3], i, "skje");		// �տ���
						getCardPanel().getBillModel().setValueAt(obj[4], i, "vbdef01");		// �տ��ӱ�pk
						
						total_skje = total_skje.add(
								PuPubVO.getUFDouble_NullAsZero(obj[3])
						);
						
					}
					
					getCardPanel().getHeadItem("vdef03").setValue(total_skje);	// ��ͷ-�ϼƽ��
					
				}
			} 
			/**
			 * 2����ѯ ˮ���Ӧ������
			 */
			else {
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" ysb.def8 ")				// 0������pk
						.append(",ysb.def1 ")				// 1����Ŀpk
						.append(",substr(ysb.def3,1,10) || '��' || substr(ysb.def4,1,10) ")	// 2��ժҪ
						.append(",room.name ")				// 3������
						.append(",srxm.name ")				// 4����Ŀ
						.append(",nvl(weizhi.name,'��') ")	// 5��λ��
						.append(",ysb.price ")				// 6������
						.append(",ysb.def2 ")				// 7��������
						.append(",ysb.def6 ")				// 8��������
						.append(",ysb.def5 ")				// 9����������
						.append(",ysb.local_money_de ")		//10�����
						.append(",ysb.pk_recitem ")			//11��Ӧ��bid
						.append(" from ar_recbill ys ")
						.append(" inner join ar_recitem ysb on ys.pk_recbill = ysb.pk_recbill ")
						.append(" left join bd_defdoc room on ysb.def8 = room.pk_defdoc ")
						.append(" left join bd_defdoc srxm on ysb.def1 = srxm.pk_defdoc ")
						.append(" left join bd_defdoc weizhi on ysb.def11 = weizhi.pk_defdoc ")
						.append(" ")
						.append(" where ys.dr=0 and ysb.dr=0 ")
						.append(" and ys.pk_tradetypeid = '1001N51000000063ZZH4' ")			// ˮ���Ӧ��
						.append(" and ysb.customer = '"+pk_cust+"' ")						// �ͻ�
						.append(" and substr(ysb.busidate,1,10) " +
								"		between '"+(beg_date.toString().substring(0,10))+"' " +
								"		and '"+(end_date.toString().substring(0,10))+"' ")	// ���ڷ�Χ
				;
				
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(
						 querySQL.toString()
						,new ArrayListProcessor()
				);
				
				if(list!=null&&list.size()>0)
				{
					UFDouble total_je = UFDouble.ZERO_DBL;	// �ϼƽ��
					
					for(int i=0;i<list.size();i++)
					{
						getCardPanel().getBodyPanel().addLine();	// ����
						
						Object[] obj = (Object[])list.get(i);
						
						getCardPanel().getBillModel().setValueAt(obj[2], i, "zhaiyao");		// 2��ժҪ
						getCardPanel().getBillModel().setValueAt(obj[3], i, "vbdef02");		// 3������
						getCardPanel().getBillModel().setValueAt(obj[4], i, "srxm");		// 4����Ŀ
						getCardPanel().getBillModel().setValueAt(obj[5], i, "vbdef03");		// 5��λ��
						getCardPanel().getBillModel().setValueAt(obj[6], i, "vbdef04");		// 6������
						getCardPanel().getBillModel().setValueAt(obj[7], i, "vbdef05");		// 7��������
						getCardPanel().getBillModel().setValueAt(obj[8], i, "vbdef06");		// 8��������
						getCardPanel().getBillModel().setValueAt(obj[9], i, "vbdef07");		// 9����������
						getCardPanel().getBillModel().setValueAt(obj[10],i, "skje");		// 10�����
						getCardPanel().getBillModel().setValueAt(obj[11],i, "vbdef01");		// 11��Ӧ��bid
						
						total_je = total_je.add(
								PuPubVO.getUFDouble_NullAsZero(obj[10])
						);
						
					}
					
					getCardPanel().getHeadItem("vdef03").setValue(total_je);	// ��ͷ-�ϼƽ��
					
				}
			}
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
}
