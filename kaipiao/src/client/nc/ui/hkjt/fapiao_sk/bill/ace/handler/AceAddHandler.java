package nc.ui.hkjt.fapiao_sk.bill.ace.handler;

import java.util.ArrayList;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.arap.actions.SkKaipiaoAction;
import nc.ui.hkjt.pub.Pub_Param;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.ui.pub.bill.BillCardPanel;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		try
		{
			String pk_group = e.getContext().getPk_group();
			String pk_org = e.getContext().getPk_org();
			BillCardPanel panel = e.getBillForm().getBillCardPanel();
			// ��������֯Ĭ��ֵ
			panel.setHeadItem("pk_group", pk_group);
			panel.setHeadItem("pk_org", pk_org);
			// ���õ���״̬������ҵ������Ĭ��ֵ
			panel.setHeadItem("ibillstatus", BillStatusEnum.FREE.value());
			panel.setHeadItem("dbilldate", AppContext.getInstance().getBusiDate());
			
			/**
			 * HK 2018��10��23��10:31:41
			 * ���� ��Ʊ���롢��Ʊ����
			 * ��Ʊ����
			 */
			panel.setHeadItem("fphm", Pub_Param.getInstance().FPHM);		// ��Ʊ����
			panel.setHeadItem("vdef10", Pub_Param.getInstance().FPLENGTH);	// Ʊ�ų���
			/***END***/
			
			/**
			 * HK 2018��10��22��18:00:47
			 * ���� �տ��Ԥ�յ� ����Ϣ
			 */
			if(SkKaipiaoAction.MAP_SkKaiPiao!=null)
			{
				Long sk_ts = (Long)SkKaipiaoAction.MAP_SkKaiPiao.get("ts");
				
				if(System.currentTimeMillis()-sk_ts>300000) return;	// ��� �㿪Ʊ�Ǽǰ�ť  �� ��������ť�� �������300�룬�򲻽��и�ֵ����
				
				String pk_customer = PuPubVO.getString_TrimZeroLenAsNull( SkKaipiaoAction.MAP_SkKaiPiao.get("pk_customer") );
				String[] pk_gatherbill = (String[]) SkKaipiaoAction.MAP_SkKaiPiao.get("pk_gatherbill");
				String[] billno = (String[]) SkKaipiaoAction.MAP_SkKaiPiao.get("billno");
				pk_org = (String)SkKaipiaoAction.MAP_SkKaiPiao.get("pk_org");
				String pk_org_v = (String)SkKaipiaoAction.MAP_SkKaiPiao.get("pk_org_v");
				
				panel.setHeadItem("pk_org", pk_org);
				panel.setHeadItem("pk_org_v", pk_org_v);
				
				panel.setHeadItem("pk_customer", pk_customer);
				
				UFDouble sykpje_total = UFDouble.ZERO_DBL;	// ��Ʊ��� �ϼ�
				
				for(int i=0;i<pk_gatherbill.length;i++)
				{
					panel.addLine();	// ����
					
					panel.setBodyValueAt(pk_gatherbill[i], i, "sk_pk");
					panel.setBodyValueAt(billno[i], i, "sk_code");
					
					// ���� �տpk  ȥ��ѯ 
					// �տ��� vbdef01 �� ֮ǰ��Ʊ��� vbdef02 �� ʣ�࿪Ʊ��� vbdef03
					StringBuffer querySQL = 
						new StringBuffer("select ")
								.append(" gb.money ")
								.append(",nvl(fp.sk_money,0) sk_money ")
								.append(" from ar_gatherbill g ")
								.append(" left join ( ")
								.append("	select ")
								.append("	 gb.pk_gatherbill ")
								.append("	,sum(gb.money_cr) money ")
								.append("	from ar_gatheritem gb ")
								.append("	left join bd_defdoc srxm on gb.def1 = srxm.pk_defdoc ")
								.append("	where gb.dr=0 ")
								.append("	and srxm.name not like '%Ѻ��%' ")
								.append("   and gb.pk_gatherbill = '"+pk_gatherbill[i]+"' ")
								.append("	group by gb.pk_gatherbill ")
								.append(" ) gb on g.pk_gatherbill = gb.pk_gatherbill ")
								.append(" left join ( ")
								.append("  	select ")
								.append("    fpb.sk_pk ")
								.append("  	,sum(nvl(fpb.sk_money,0)) sk_money")
								.append("   from hk_fapiao_sk_bill_b fpb ")
								.append("   inner join hk_fapiao_sk_bill fp on fpb.pk_hk_fapiao_sk_bill = fp.pk_hk_fapiao_sk_bill ")
								.append("   where fpb.dr=0 and fp.dr=0 ")
								.append("	and fp.vbilltypecode = 'HK36' ")
								.append("   and fpb.sk_pk = '"+pk_gatherbill[i]+"' ")
								.append("   group by fpb.sk_pk ")
								.append(" ) fp on g.pk_gatherbill = fp.sk_pk ")
								.append(" where g.pk_gatherbill = '"+pk_gatherbill[i]+"' ")
					;
					
					 IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
					 ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(),new ArrayListProcessor());
					 if(list!=null&&list.size()>0)
					 {
						 Object[] obj = (Object[])list.get(0);
						 UFDouble skje  = PuPubVO.getUFDouble_NullAsZero(obj[0]);	// �տ���
						 UFDouble ykpje = PuPubVO.getUFDouble_NullAsZero(obj[1]);	// �ѿ�Ʊ���
						 
						 UFDouble sykpje = skje.sub(ykpje);		// ʣ�࿪Ʊ��� = �տ���-�ѿ�Ʊ���
						 
						 panel.setBodyValueAt(sykpje, i, "sk_money");	// ���ο�Ʊ���
						 panel.setBodyValueAt(sykpje, i, "vbdef03");	// ʣ�࿪Ʊ���
						 panel.setBodyValueAt(skje,   i, "vbdef01");	// �տ���
						 panel.setBodyValueAt(ykpje,  i, "vbdef02");	// ֮ǰ��Ʊ���
						 
						 sykpje_total = sykpje_total.add(sykpje);	// ��Ʊ�ܶ�
					 }
				}
				
				panel.setHeadItem("fpje", sykpje_total);	// ��ͷ��Ʊ�ܶ�
				
			}
			/***END***/
			 
		}catch(Exception ex)
		{ex.printStackTrace();}
	}
}
