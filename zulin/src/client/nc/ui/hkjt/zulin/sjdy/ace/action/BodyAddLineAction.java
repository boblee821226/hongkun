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
			
			if(
				 pk_cust==null
			 || beg_date==null
			 || end_date==null
			)
			{
				MessageDialog.showWarningDlg(this.getCardPanel(), "", "����д��ͷ��Ϣ");
				return ;
			}
			
			/**
			 * ��ѯ����
			 */
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
			
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
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
					getCardPanel().getBillModel().setValueAt(obj[0], i, "zhaiyao");	// ժҪ
	//				getCardPanel().getBillModel().setValueAt(obj[1], i, "srxm");		// ������Ŀpk
					getCardPanel().getBillModel().setValueAt(obj[2], i, "srxm");		// ������Ŀ
					getCardPanel().getBillModel().setValueAt(obj[3], i, "skje");		// �տ���
					getCardPanel().getBillModel().setValueAt(obj[4], i, "vbdef01");	// �տ��ӱ�pk
					
					total_skje = total_skje.add(
							PuPubVO.getUFDouble_NullAsZero(obj[3])
					);
					
				}
				
				getCardPanel().getHeadItem("vdef03").setValue(total_skje);	// ����-�ϼƽ��
				
			}
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
}
