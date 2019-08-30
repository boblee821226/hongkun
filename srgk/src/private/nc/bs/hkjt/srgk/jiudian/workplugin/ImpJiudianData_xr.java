package nc.bs.hkjt.srgk.jiudian.workplugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.org.IOrgVersionQryService;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.VectorProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

public class ImpJiudianData_xr extends ImpJiudianData implements IBackgroundWorkPlugin {

	
	/**
	 * ���ڴ������
	 * ��̨�����޷�ִ��
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		
		String[] pk_orgs = new String[]{
				"0001N510000000001SY3"
			};//������
		
		String[] dateP = new String[]{
				"2019-01-31",
				"2019-01-31"
			};

		long startTime=System.currentTimeMillis();
		
		if(pk_orgs==null||pk_orgs.length==0)return null;
		
		HashMap<String,String> infoMap=getDefaultInfo(pk_orgs[0]);//�õ����ñ���Ϣ
		
		if(infoMap!=null){
			
			boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{"HK-�������ݵ���"+pk_orgs[0]});
			
			if(!lock){
				throw new BusinessException("�������ڴ�����,�����ظ�������");
			}
			
			String[] timeDates=getTimeDates(dateP[0],dateP[1]);
			
			for (String date : timeDates) {
				
				String timeWhere=getTimeWhere(date,date,getTimeWhereField());//�õ���̨�����ж�������ڣ������where����
				executeDateTongBu(infoMap,timeWhere);
			}
		}
		
		System.out.println("�������,����ʱ��"+(System.currentTimeMillis()-startTime)+"����");
	
		return null;
	
	}
	
	/**
	 * zhangjc
	 * 2015-8-6����10:58:58
	 * void
	 * ������ϸͬ��
	 *
	 */
	public void executeDateTongBu(HashMap<String,String> infoMap,String timeWhere) throws BusinessException{
		try{
		ArrayList<RzmxBVO> list=getBodyVOs(timeWhere,infoMap);
		RzmxBillVO[] aggvos=getRzmxAggVO(list,infoMap);//ת��ΪNC������ϸ�ۺ�VO
		saveRzmxAggVOs(aggvos);//����
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	public ArrayList<RzmxBVO> getBodyVOs(String timeWhere,HashMap<String,String> infoMap) throws BusinessException {
		Connection hkjt_hg_pub_conn=null;
		try
		{
			hkjt_hg_pub_conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_JD_KFRXS);
			Statement stmt = hkjt_hg_pub_conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			StringBuffer sql = new StringBuffer();
			
			String org_name = infoMap.get("org_name");	// �Ƶ� ����
			
			if( "������Ƶ�".equals(org_name) )
			{
				///////////////////////////////////////////////  ������ϸ
				sql.append("select")
				.append(" cc.bdate ") //bdate
				.append(",cc.charge charge") //����
				.append(",cc.credit payment")//����
				.append(",cc.accnt accid")//accid
				.append(",'ǰ̨' bm_name")//���ţ�Ĭ�ϣ�
				.append(",cc.log_date transdate")//����ʱ��
				.append(",cc.empno syy_code")//����Ա
				.append(",cc.roomno rmno")//�����
				.append(",(select rt.descript from rmsta r,typim rt where r.type *=rt.type and r.roomno=cc.roomno) rmtype_name")//����    ��rmsta r�����typim rt ���ͱ�
				.append(",cc.pccode item_code")// ��Ŀ����
				.append(",p.descript item_name")// ��Ŀ����
				.append(",ar.name khmz")// �ͻ�
	//			.append(",substring(cc.ref2,1,patindex('%[/]%',cc.ref2)-1) khmz ")//�ͻ�
				.append(" from haccount cc")// ������ϸ��
				.append(",pccode p ")// ��Ŀ��
				.append(",armst ar ")// AR�ͻ���
				.append(" where ")
				.append(" cc.pccode *= p.pccode  ")
				.append(" and cc.accntof *= ar.accnt ")
				.append(" and ")
				.append(timeWhere);
				sql.append(" union all ");
				sql.append(" select")
				.append(" cc.bdate") //bdate
				.append(",cc.charge charge") //����
				.append(",cc.credit payment")//����
				.append(",cc.accnt accid")//accid
				.append(",'ǰ̨' bm_name")//���ţ�Ĭ�ϣ�
				.append(",cc.log_date transdate")//����ʱ��
				.append(",cc.empno syy_code")//����Ա
				.append(",cc.roomno rmno")//�����
				.append(",(select rt.descript from rmsta r,typim rt where r.type *=rt.type and r.roomno=cc.roomno) rmtype_name")//����    ��rmsta r�����typim rt ���ͱ�
				.append(",cc.pccode item_code")// ��Ŀ����
				.append(",p.descript item_name")// ��Ŀ����
				.append(",cc.accntof khmz")// �ͻ�
				.append(" from account cc")// ������ϸ��
				.append(",pccode p")// ��Ŀ��
				.append(" where ")
				.append(" cc.pccode *= p.pccode  ")
				.append(" and ")
				.append(timeWhere);
				////////////////////////////////////////////  POS
				sql.append(" union all ");
				sql.append("select ")  
					.append(" cc.bdate bdate ")
					.append(",cc.amount charge")  
					.append(",0 payment")
					.append(",cc.menu  accid")
					.append(",'ǰ̨' bm_name")
					.append(",cc.date0 transdate") 
					.append(",'' syy_code")
					.append(",'' rmno")
					.append(",'' rmtype_name")
					.append(",b.pccode  item_code")  
					.append(",b.descript item_name") 
					.append(",'' khmz")
					.append(" from  pos_hmenu cc, pos_pccode b")  
					.append(" where cc.pccode *= b.pccode")
					.append(" and cc.menu in ( select  menu from pos_hpay where  (accnt = '' or accnt like 'AR%') and "+timeWhere+" )")
					.append(" and ")
					.append(timeWhere);
				sql.append(" union all ");
				sql.append("select ")
					.append(" cc.bdate ")
					.append(",0 charge")
					.append(",cc.amount payment")  
					.append(",cc.menu  accid")
					.append(",'ǰ̨' bm_name")
					.append(",cc.log_date transdate") 
					.append(",'' syy_code")
					.append(",'' rmno")
					.append(",'' rmtype_name")
					.append(",b.pccode item_code") //������Ŀ
					.append(",b.descript item_name") //������Ŀname 
					.append(",ar.name khmz")
					.append(" from  pos_hpay cc, pccode b, armst ar ")
					.append(" where cc.paycode *= b.pccode and cc.accnt *= ar.accnt ")
					.append(" and  (cc.accnt = '' or cc.accnt like 'AR%')") 
					.append(" and ")
					.append(timeWhere);
				sql.append(" union all ");
				sql.append("select ")  
					.append(" cc.bdate bdate")
					.append(",cc.amount charge")  
					.append(",0 payment")
					.append(",cc.menu  accid")
					.append(",'ǰ̨' bm_name")
					.append(",cc.date0 transdate") 
					.append(",'' syy_code")
					.append(",'' rmno")
					.append(",'' rmtype_name")
					.append(",b.pccode item_code")  
					.append(",b.descript item_name") 
					.append(",'' khmz")
					.append(" from  pos_menu cc, pos_pccode b")  
					.append(" where cc.pccode *= b.pccode")
					.append(" and cc.menu in ( select  menu from pos_pay where  (accnt = '' or accnt like 'AR%') and "+timeWhere+" )")
					.append(" and ")
					.append(timeWhere);
				sql.append(" union all ");
				sql.append("select ")
					.append(" cc.bdate ")
					.append(",0 charge")
					.append(",cc.amount payment")  
					.append(",cc.menu  accid")
					.append(",'ǰ̨' bm_name")
					.append(",cc.log_date transdate") 
					.append(",'' syy_code")
					.append(",'' rmno")
					.append(",'' rmtype_name")
					.append(",b.pccode item_code") 
					.append(",b.descript item_name")  
					.append(",cc.accnt khmz")
					.append(" from  pos_pay cc, pccode b")
					.append(" where cc.paycode *= b.pccode")
					.append(" and  (cc.accnt = '' or cc.accnt like 'AR%')") 
					.append(" and ")
					.append(timeWhere);
				////////////////////////////////////////////////////////////  AR��
				sql.append(" union all ");//ar�� ����
				sql.append(" select ")
					.append("  cc.bdate ")
					.append(" ,cc.charge")
					.append(" ,0 payment")
					.append(" ,cc.accnt accid")
					.append(" ,'ǰ̨' bm_name")
					.append(" ,cc.log_date transdate")
					.append(" ,'' syy_code")
					.append(" ,'' rmno")
					.append(" ,'' rmtype_name")
					.append(" ,'' item_code")
					.append(" ,b.descript item_name")
					.append(" ,ar.name khmz") 
					.append("  from ar_account cc, pccode b,armst ar")
					.append("  where cc.pccode *= b.pccode and cc.ar_accnt *= ar.accnt ")
					.append(" and ") 
					.append(timeWhere)
					.append(" and cc.ar_subtotal = 'F' ")
					.append(" and cc.accnt like 'AR%' ")
					.append(" and cc.charge != 0.00")
					.append(" and cc.ref1 not in ( select  menu from pos_hpay where  (accnt = '' or accnt like 'AR%') and "+timeWhere+" ) ")
					.append(" and cc.ref1 not in ( select  menu from pos_pay where  (accnt = '' or accnt like 'AR%') and "+timeWhere+" ) ")
				// ��AR����  ת���� ���ˣ��������ݣ�
				.append(" union all ")
					.append(" select ")
					.append("  cc.bdate ")
					.append(" ,0 charge")
					.append(" ,cc.charge payment")
					.append(" ,cc.accnt accid")
					.append(" ,'ǰ̨' bm_name")
					.append(" ,cc.log_date transdate")
					.append(" ,'' syy_code")
					.append(" ,'' rmno")
					.append(" ,'' rmtype_name")
					.append(" ,'9002' item_code")
					.append(" ,'���й���' item_name")
					.append(" ,ar.name khmz") 
					.append("  from ar_account cc, pccode b ,armst ar ")
					.append("  where cc.pccode *= b.pccode and cc.ar_accnt *= ar.accnt ")
					.append(" and ") 
					.append(timeWhere)
					.append(" and cc.ar_subtotal = 'F' ")
					.append(" and cc.accnt like 'AR%' ")
					.append(" and cc.charge != 0.00")
					.append(" and cc.ref1 not in ( select  menu from pos_hpay where  (accnt = '' or accnt like 'AR%') and "+timeWhere+" ) ")
					.append(" and cc.ref1 not in ( select  menu from pos_pay where  (accnt = '' or accnt like 'AR%') and "+timeWhere+" ) ")
				;
					sql.append(" union all ")
						.append(" select ")
						.append("  cc.bdate ")
						.append(" ,0 charge ")
						.append(" ,cc.quantity payment")
						.append(" ,cc.accnt+' ���˻���' accid")	// ��ʶΪ ���˻���
						.append(" ,'ǰ̨' bm_name")
						.append(" ,cc.log_date transdate")
						.append(" ,'' syy_code")
						.append(" ,'' rmno")
						.append(" ,'' rmtype_name")
						.append(" ,cc.pccode item_code")		// ��Ŀ����
						.append(" ,b.descript item_name")	// ��Ŀ����
						.append(" ,cc.guestname khmz") 		// �ͻ�����
						.append("  from ar_detail cc, pccode b")
						.append("  where cc.pccode *= b.pccode")
						.append(" and ") 
						.append(timeWhere)
						.append(" and cc.pccode<>'' ")
						.append(" and cc.quantity <> 1 ")
					;
					// �� ���˻��գ� ת����  �������տ����ݡ�
					sql.append(" union all ")
						.append(" select ")
						.append("  cc.bdate ")
						.append(" ,0 charge ")
						.append(" ,0-cc.quantity payment")	// ȡ����
						.append(" ,cc.accnt+' ���˻���' accid")	// ��ʶΪ ���˻���
						.append(" ,'ǰ̨' bm_name")
						.append(" ,cc.log_date transdate")
						.append(" ,'' syy_code")
						.append(" ,'' rmno")
						.append(" ,'' rmtype_name")
						.append(" ,'' item_code")		// ��Ŀ����
						.append(" ,'���й���' item_name")	// ��Ŀ����
						.append(" ,cc.guestname + ' ���˻���' khmz") 		// �ͻ����� + ���˻���
						.append("  from ar_detail cc, pccode b")
						.append("  where cc.pccode *= b.pccode")
						.append(" and ") 
						.append(timeWhere)
						.append(" and cc.pccode<>'' ")
						.append(" and cc.quantity <> 1 ")
					;
			}
			else if( "�����ȾƵ�".equals(org_name) )
			{
				 sql.append(" select ")
					.append(" cc.bdate ")
					.append(" ,cc.charge charge ")
					.append(" ,cc.credit payment ")
					.append(" ,cc.accnt accid ")
					.append(" ,'ǰ̨' bm_name ")
					.append(" ,cc.log_date transdate ")
					.append(" ,cc.empno syy_code ")
					.append(" ,cc.roomno rmno ")
					.append(" ,(select rt.descript from rmsta r,typim rt where r.type *=rt.type and r.roomno=cc.roomno) rmtype_name ")
					.append(" ,cc.pccode item_code ")
					.append(" ,p.descript item_name ")
					.append(" ,ar.name khmz ")
					.append("  from haccount cc,pccode p ,armst ar ")
					.append("  where ")
					.append("  cc.pccode *= p.pccode  ")
					.append("  and cc.accntof *= ar.accnt  ")
					.append("  and " + timeWhere )
					.append("  and ( len(cc.ref1)<>10 ")
					.append("	  or cc.pccode in ('3060','3070','8120','8130','8139','3069','3079','8129','1420','1500','7070','7110','5200') ")
					.append("	) ")
				.append(" union all ")
					.append("  select ")
					.append("  cc.bdate ")
					.append(" ,cc.charge charge ")
					.append(" ,cc.credit payment ")
					.append(" ,cc.accnt accid ")
					.append(" ,'ǰ̨' bm_name ")
					.append(" ,cc.log_date transdate ")
					.append(" ,cc.empno syy_code ")
					.append(" ,cc.roomno rmno ")
					.append(" ,(select rt.descript from rmsta r,typim rt where r.type *=rt.type and r.roomno=cc.roomno) rmtype_name  ")
					.append(" ,cc.pccode item_code ")
					.append(" ,p.descript item_name ")
					.append(" ,cc.accntof khmz ")
					.append("  from account cc ")
					.append(" ,pccode p ")
					.append("  where  ")
					.append("  cc.pccode *= p.pccode  ")
					.append("  and " + timeWhere )
					.append("  and (cc.charge<>0 or cc.credit<>0) ")	// 2019��3��29��10:18:51
					.append("  and ( len(cc.ref1)<>10 ")	// ȥ���ظ����� ��HK 2019��1��25��13:56:37��
					.append("	  or cc.pccode in ('3060','3070','8120','8130','8139','3069','3079','8129','1420','1500','7070','7110','5200') ")
					.append("	) ")
				.append(" union all ")
					.append(" select  ")
					.append("  cc.bdate bdate ")
					.append(" ,cc.amount charge  ")
					.append(" ,0 payment ")
					.append(" ,cc.menu  accid ")
					.append(" ,'ǰ̨' bm_name ")
					.append(" ,cc.date0 transdate ")
					.append(" ,'' syy_code ")
					.append(" ,'' rmno ")
					.append(" ,'' rmtype_name ")
					.append(" ,b.pccode item_code  ")
					.append(" ,b.descript item_name ")
					.append(" ,'' khmz ")
					.append("  from  pos_menu cc, pos_pccode b  ")
					.append("  where cc.pccode *= b.pccode ")
					.append("  and cc.menu in ( " +
							"		select  menu from pos_pay " +
							"		where (accnt = '' or accnt like 'AR%') " +
							"       and " + timeWhere + 
							"  ) ")
					.append("  and " + timeWhere )
				 .append(" union all ")
					 .append(" select  ")
					 .append(" cc.bdate  ")
					 .append(" ,cc.charge ")
					 .append(" ,0 payment ")
					 .append(" ,cc.accnt accid ")
					 .append(" ,'ǰ̨' bm_name ")
					 .append(" ,cc.log_date transdate ")
					 .append(" ,'' syy_code ")
					 .append(" ,'' rmno ")
					 .append(" ,'' rmtype_name ")
					 .append(" ,'' item_code ")
					 .append(" ,b.descript item_name ")
					 .append(" ,ar.name khmz ")
					 .append("  from ar_account cc, pccode b,armst ar ")
					 .append("  where cc.pccode *= b.pccode and cc.ar_accnt *= ar.accnt  ")
					 .append("  and " + timeWhere )
					 .append("  and cc.ar_subtotal = 'F'  ")
					 .append("  and cc.accnt like 'AR%'  ")
					 .append("  and cc.charge != 0.00 ")
					 .append("  and cc.ref1 not in (  ")
					 .append(" 		 select  menu from pos_hpay where  (accnt = '' or accnt like 'AR%')  ")
					 .append("       and " + timeWhere )
					 .append("  )  ")
					 .append("  and cc.ref1 not in ( ")
					 .append(" 		 select  menu from pos_pay where  (accnt = '' or accnt like 'AR%')  ")
					 .append("       and " + timeWhere )
					 .append("  )  ")
				.append(" union all ")
					.append(" select  ")
					.append("   cc.bdate  ")
					.append("  ,0 charge ")
					.append("  ,cc.charge payment ")
					.append(" ,cc.accnt accid ")
					.append("  ,'ǰ̨' bm_name ")
					.append("  ,cc.log_date transdate ")
					.append("  ,'' syy_code ")
					.append("  ,'' rmno ")
					.append("  ,'' rmtype_name ")
					.append("  ,'9002' item_code ")
					.append("  ,'���й���' item_name ")
					.append("  ,ar.name khmz ")
					.append("   from ar_account cc, pccode b ,armst ar  ")
					.append("   where cc.pccode *= b.pccode and cc.ar_accnt *= ar.accnt  ")
					.append("   and " + timeWhere )
					.append("   and cc.ar_subtotal = 'F'  ")
					.append("   and cc.accnt like 'AR%'  ")
					.append("   and cc.charge != 0.00 ")
					.append("   and cc.ref1 not in (  ")
					.append(" 		select  menu from pos_hpay  ")
					.append(" 		where  (accnt = '' or accnt like 'AR%')  ")
					.append("       and " + timeWhere )
					.append(" 		)  ")
					.append("   and cc.ref1 not in (  ")
					.append(" 		select  menu from pos_pay  ")
					.append(" 		where  (accnt = '' or accnt like 'AR%')  ")
					.append("       and " + timeWhere )
					.append(" 		)  ")
				.append(" union all ")
					.append(" select  ")
					.append("  cc.bdate ")
					.append(" ,0 charge ")
					.append(" ,cc.quantity payment ")
					.append(" ,cc.accnt+' ���˻���' accid ")
					.append(" ,'ǰ̨' bm_name ")
					.append(" ,cc.log_date transdate ")
					.append(" ,'' syy_code ")
					.append(" ,'' rmno ")
					.append(" ,'' rmtype_name ")
					.append(" ,cc.pccode item_code ")
					.append(" ,b.descript item_name ")
					.append(" ,cc.guestname khmz ")
					.append("  from ar_detail cc, pccode b ")
					.append("  where cc.pccode *= b.pccode ")
					.append("  and " + timeWhere )
					.append("  and cc.pccode<>''  ")
					.append("  and cc.quantity <> 1  ")
					.append("  and cc.quantity <> 0  ")
					.append("  and cc.charge = 0  ")
				 .append(" union all ")
					 .append("  select  ")
					 .append("  cc.bdate ")
					 .append(" ,0 charge ")
					 .append(" ,0-cc.quantity payment	 ") // ȡ����
					 .append(" ,cc.accnt+' ���˻���' accid  ")	// ��ʶΪ ���˻���
					 .append(" ,'ǰ̨' bm_name ")
					 .append(" ,cc.log_date transdate ")
					 .append(" ,'' syy_code ")
					 .append(" ,'' rmno ")
					 .append(" ,'' rmtype_name ")
					 .append(" ,'' item_code ")
					 .append(" ,'���й���' item_name ")
					 .append(" ,cc.guestname + ' ���˻���' khmz ")
					 .append("  from ar_detail cc, pccode b ")
					 .append("  where cc.pccode *= b.pccode ")
					 .append("  and " + timeWhere )
					 .append("  and cc.pccode<>''  ")
					 .append("  and cc.quantity <> 1  ")
					 .append("  and cc.quantity <> 0  ")
					 .append("  and cc.charge = 0  ")
				 .append(" union all ")
					 .append(" select  ")
					 .append(" a.bdate ")
					 .append(" ,a.amount+a.srv-a.dsc charge ")
					 .append(" , 0 payment ")
					 .append(" ,a.menu accid ")
					 .append(" ,d.descript bm_name ")
					 .append(" ,a.date0 transdate ")
					 .append(" ,a.empno syy_code ")
					 .append(" ,'' rmno ")
					 .append(" , c.descript1 rmtype_name ")
					 .append(" ,a.sort item_code ")
					 .append(" ,f.name1 item_name  ")
					 .append(" ,'' khmz ")
					 .append(" from pos_hdish a ,pos_hmenu b ,pos_tblsta c  ,pos_pccode d ,pos_sort f ")
					 .append(" where a.menu = b.menu and b.tableno=c.tableno  and c.pccode=d.pccode and a.sort=f.sort ")
					 .append(" and a.amount+a.srv-a.dsc<>0 ")	// 2019��3��29��10:17:04
	//				 .append(" and (a.bdate >= '2018-06-01' and a.bdate <= '2018-06-02') ")
					 .append(" and " + timeWhere.replaceAll("cc.bdate", "a.bdate") )
				 .append(" union all ")
//					 .append(" select  ")
//					 .append(" bdate ")
//					 .append(" ,0 charge ")
//					 .append(" ,amount payment ")
//					 .append(" ,case when  paycode in('9086','9002')  then accnt else menu end as accid ")
//					 .append(" ,'ǰ̨' bm_name ")
//					 .append(" ,log_date transdate ")
//					 .append(" ,empno syy_code ")
//					 .append(" ,'' rmno ")
//					 .append(" ,'' rmtype_name ")
//					 .append(" ,paycode item_code ")
//					 .append(" ,b.descript item_name ")
//					 .append(" ,case when  left(remark,2)='AR'   then ref else '' end as KHMZ ")
//					 .append(" from pos_hpay a, pccode b  ")
//					 .append(" where  b.pccode=a.paycode and pccode >'8999'  ")
//	//			     .append(" and (a.bdate >= '2018-06-01' and a.bdate <= '2018-06-02')  ")
//					 .append(" and " + timeWhere.replaceAll("cc.bdate", "a.bdate") )
				 	 .append(" select ")
				 	 .append("  bdate ")
				 	 .append(" ,0 charge ")
				 	 .append(" ,amount payment ")
				 	 .append(" ,case when  paycode in('9086','9002')  then accnt else menu end as accid ")
				 	 .append(" ,'ǰ̨' bm_name ")
				 	 .append(" ,log_date transdate ")
				 	 .append(" ,empno syy_code ")
				 	 .append(" ,'' rmno ")
				 	 .append(" ,'' rmtype_name ")
				 	 .append(" ,paycode item_code ")
				 	 .append(" ,b.descript item_name ")
				 	 .append(" ,isnull((select ff.name from guest ff where no in (select f1.haccnt from ar_master f1 where f1.accnt = a.accnt ) ),'') as KHMZ ")
				 	 .append(" from pos_hpay a, pccode b ")
				 	 .append(" where b.pccode=a.paycode and pccode >'8999'  ")
				 	 .append(" and amount<>0 ")
				 	 .append(" and " + timeWhere.replaceAll("cc.bdate", "a.bdate") )
				 ;
			
			}
			
			ResultSet rs = stmt.executeQuery(new String(sql.toString().getBytes(),"cp850"));
			ArrayList<RzmxBVO> bodyvos=new ArrayList<RzmxBVO>();
			while(rs.next())
			{
				RzmxBVO bvo=new RzmxBVO();
				bvo.setPk_hk_srgk_jd_rzmx(getString(rs,"bdate").substring(0,10));	
				bvo.setCharge(nullAsZero(getString(rs,"charge")));
				bvo.setPayment(nullAsZero(getString(rs, "payment")));
				bvo.setAccid(getString(rs,"accid"));
				bvo.setBm_name(getString(rs,"bm_name"));	// ����
				bvo.setTransdate(new UFDateTime(getString(rs, "transdate").substring(0,19)));
				bvo.setSyy_code(getString(rs, "syy_code"));
				bvo.setRmno(getString(rs, "rmno"));
				bvo.setRmtype_name(getString(rs, "rmtype_name"));
				bvo.setItem_code(getString(rs, "item_code"));
				bvo.setItem_name(getString(rs, "item_name"));	// ������Ŀ
				bvo.setKhmz(getString(rs, "khmz"));
				
				/**
				 * HK  2019��2��28��20:25:25
				 * �����ȾƵ�  ��Ҫ �� �����-�����  ���ݲ��� ϸ�ֵ������ ��Ŀ�ϡ�
				 */
				if( "�����ȾƵ�".equals(org_name) )
				{
					if( "�����-�����".equals( bvo.getItem_name() ) )
					{
						bvo.setItem_name( "������-"+bvo.getBm_name() );
					}
				}
				/***END***/
				
				bodyvos.add(bvo);
			}
			
			/**
			 * HK 2019��1��24��14:22:46
			 * ���ڲ���
			 */
//			if(true)
//			{
//				throw new BusinessException("����"+bodyvos.size());
//			}
			/***END***/
			
			return bodyvos;
		}
		catch(Exception ex)
		{
			throw new BusinessException(ex.getMessage());
		}
		finally{
			JDBCUtils.closeConn(hkjt_hg_pub_conn);
		}
	}
	/**
	 * zhangjc
	 * 2015-8-20����13:23:07
	 * RzmxBillVO[]
	 *���������ϸ�ۺ�VO����
	 */
	public RzmxBillVO[] getRzmxAggVO(ArrayList<RzmxBVO> list,HashMap<String,String> infoMap) throws BusinessException{
		IOrgVersionQryService orgVersion=NCLocator.getInstance().lookup(IOrgVersionQryService.class);
		String pk_org = infoMap.get("pk_org");	// ����ǧ����
		String pk_org_v=orgVersion.getOrgUnitLastVersionByOrgID(pk_org).getPk_vid();
		String pk_group=AppContext.getInstance().getPkGroup();
		HashMap<String,ArrayList<RzmxBVO>> mapvos=new HashMap<String, ArrayList<RzmxBVO>>();
		for (RzmxBVO bvo : list) {//�������ڷ���
			String dbilldate=bvo.getPk_hk_srgk_jd_rzmx();
			bvo.setPk_hk_srgk_jd_rzmx(null);
			if(mapvos.containsKey(dbilldate)){
				ArrayList<RzmxBVO> blist=mapvos.get(dbilldate);
				blist.add(bvo);
				mapvos.put(dbilldate, blist);
			}else{
				ArrayList<RzmxBVO> blist=new ArrayList<RzmxBVO>();
				blist.add(bvo);
				mapvos.put(dbilldate, blist);
			}
		}
		ArrayList<RzmxBillVO> listvo=new ArrayList<RzmxBillVO>();
		HashSet<String> exists=getNCBillsByPk_org(pk_org);//��NC���Ѿ����ڵ�������ϸ���ݣ����������ж���
		
		Connection conn=null;
		JdbcSession conn_session =null;
		HashMap<String,UFDouble[]> otherdate=null;
		try
		{
			conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_JD_KFRXS);
			conn_session = new JdbcSession(conn);
			otherdate=getOtherData(conn_session, mapvos.keySet());//�����ʣ�ƽ�����ۣ��ͷ����룬�ɳ��ⷿ����, REVPAR 
			}finally{
				if(conn_session!=null)
				conn_session.closeAll();
				JDBCUtils.closeConn(conn);
			}
		
		for (String key : mapvos.keySet()) {//��װ������ϸ�ۺ�VO
			if(exists.contains(key)){continue;}
			RzmxBillVO aggvo=new RzmxBillVO();
			
			RzmxHVO hvo = getHeadVOs(infoMap,pk_org, pk_org_v, pk_group,key);
//			RzmxHVO hvo = getHeadVOs(infoMap, pk_org_v, pk_group,key);
			
			UFDouble[] datas=otherdate.get(key);
			if(datas!=null&&datas.length>0){
				hvo.setFfl(datas[0]);
				hvo.setPjfj(datas[1]);
				hvo.setKfsr(datas[2]);
				hvo.setKczfs(datas[3]);
				hvo.setRevpar(datas[4]);
			}
			aggvo.setChildrenVO(getBodyVos(mapvos.get(key),hvo));
			aggvo.setParentVO(hvo);
			listvo.add(aggvo);
		}
		return listvo.toArray(new RzmxBillVO[]{});
	}
	/**
	 * //�����ʣ�ƽ�����ۣ��ͷ����룬�ɳ��ⷿ����, REVPAR
	 * zhangjc
	 * 2015-8-11����9:28:24
	 * void
	 *  ������    rptitemgroup==null and rpitemname = '������'  ȡ rptincome                          
 	 *	ƽ������  rptitemgroup==null and rpitemname = 'ƽ������'  ȡ rptincome
 	 *	REVPAR  = �ͷ�����/�ɳ��ⷿ����
 	 *	�ͷ�����  rpitemname = '�ͷ�����'  ȡ  sum(rptincome)
 	 *	�ɳ��ⷿ���� select count(code) from room
	 *
	 */
	public HashMap<String,UFDouble[]>  getOtherData(JdbcSession session,Set<String> dates) throws BusinessException{
		try{
//		String sql=" select class,descript,day from yjourrep  where date='2015-08-23' and class in ('010012','010080','010189','010300') order by class ";
		StringBuffer sb=new StringBuffer();
		sb.append("select convert(varchar(10),date,102) czldate,day czl,'' pjfjdate,0 pjfj,'' kfsrdate,0 kfsr ,'' kfzsdate,0 kfzs from yjourrep where class ='010080' ") 
		.append(" union all")
		.append(" select '' czldate,0 czl,convert(varchar(10),date,102) pjfjdate,day pjfj,'' kfsrdate,0 kfsr ,'' kfzsdate,0 kfzs from yjourrep where class ='010189' ")
		.append(" union all")
		.append(" select '' czldate,0 czl, '' pjfjdate,0 pjfj,convert(varchar(10),date,102) kfsrdate,day kfsr ,'' kfzsdate,0 kfzs from yjourrep where class ='010300'")
		.append(" union all")
		.append(" select '' czldate,0 czl, '' pjfjdate,0 pjfj,'' kfsrdate,0 kfsr,convert(varchar(10),date,102) kfzsdate,day kfzs from yjourrep where class ='010012'");
		
	HashMap<String,UFDouble> czlmap=new HashMap<String, UFDouble>();
	HashMap<String,UFDouble> pjfjmap=new HashMap<String, UFDouble>();
	HashMap<String,UFDouble> kfsrmap=new HashMap<String, UFDouble>();
	HashMap<String,UFDouble> kfzsmap=new HashMap<String, UFDouble>();
		Vector<Vector> vector=(Vector<Vector>)session.executeQuery(sb.toString(), new VectorProcessor());
		for (Vector v : vector) {
			UFDouble czl=nullAsZero(v.elementAt(1));
			if(!isZero(czl)){
				czlmap.put(v.elementAt(0).toString().replace(".","-"), czl);//������(������)
			}
			UFDouble pjfj=nullAsZero(v.elementAt(3));
			if(!isZero(pjfj)){
				pjfjmap.put(v.elementAt(2).toString().replace(".","-"), pjfj);//ƽ������
			}
			
			UFDouble kfsr=nullAsZero(v.elementAt(5));
			if(!isZero(kfsr)){
				kfsrmap.put(v.elementAt(4).toString().replace(".","-"), kfsr);//�ͷ�����
			}
			
			UFDouble kfzs=nullAsZero(v.elementAt(7));
			if(!isZero(kfzs)){
				kfzsmap.put(v.elementAt(6).toString().replace(".","-"), kfzs);//�ͷ�����
			}
		}
		
		HashMap<String,UFDouble[]> resultMap=new HashMap<String, UFDouble[]>();
		for (String date :dates) {
			resultMap.put(date, new UFDouble[]{nullAsZero(czlmap.get(date)),nullAsZero(pjfjmap.get(date)),nullAsZero(kfsrmap.get(date)),nullAsZero(kfzsmap.get(date)),isZero(nullAsZero(kfzsmap.get(date)))?UFDouble.ZERO_DBL:(nullAsZero(kfsrmap.get(date)).div(nullAsZero(kfzsmap.get(date))))});//�����ʣ�ƽ�����ۣ��ͷ����룬�ɳ��ⷿ����, REVPAR  (�ͷ�����/�ɳ��ⷿ����)
		}
		return resultMap;
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	public String getString (ResultSet rs,String field){
		try {
			return new String(rs.getString(field).getBytes("cp850"),"GBK").trim();
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public String getTimeWhereField() {
		return "cc.bdate";
	}
}
