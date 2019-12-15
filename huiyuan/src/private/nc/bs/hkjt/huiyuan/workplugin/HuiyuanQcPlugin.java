package nc.bs.hkjt.huiyuan.workplugin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.dao.BaseDAO;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoTempVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKJGVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganTempVO;
import nc.vo.hkjt.huiyuan.kaxing.KaxingHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class HuiyuanQcPlugin implements IBackgroundWorkPlugin {

	HashMap<String,String> MAP_dian_corp = new HashMap<String,String>();	// �� ��Ӧ pk_corp
	HashMap<String,String> MAP_corp_dian = new HashMap<String,String>();	// pk_corp  ��Ӧ  ��
	HashMap<String,String> MAP_dian_flag = new HashMap<String,String>();	// �� ��Ӧ flag
	HashMap<String,String> MAP_corp_flag = new HashMap<String,String>();	// pk_corp  ��Ӧ  flag
	public HuiyuanQcPlugin()
	{
		MAP_dian_corp.put("DK", "0001NC10000000004AXZ");	// ���¹���
		MAP_dian_corp.put("����", "0001N510000000001SXV");	// ���ʻ��
		MAP_dian_corp.put("�Ƶ�", "0001N510000000001SY1");	// ������Ƶ�
		MAP_dian_corp.put("������", "0001N510000000001SY3");	// �����ȾƵ� 
		MAP_dian_corp.put("ĵ��", "0001N510000000001SXX");	// ���¥
		MAP_dian_corp.put("�ϵ�", "0001N510000000001SXV");	// ���ʻ��
		MAP_dian_corp.put("��ɽ", "0001N510000000001SY7");	// ��ɽ��Ȫ
		MAP_dian_corp.put("������", "0001N510000000001SY5");	// ��������ɽ
		
		MAP_corp_dian.put("0001N510000000001SXV", "����");
		MAP_corp_dian.put("0001N510000000001SXX", "ĵ��");
		MAP_corp_dian.put("0001N510000000001SY7", "��ɽ");
		MAP_corp_dian.put("0001N510000000001SY1", "�Ƶ�");
		MAP_corp_dian.put("0001N510000000001SY3", "������");
		MAP_corp_dian.put("0001N510000000001SY5", "������");
		
		MAP_dian_flag.put("ĵ��", "-01");
		MAP_dian_flag.put("����", "-02");
		MAP_dian_flag.put("��ɽ", "-06");
		MAP_dian_flag.put("�Ƶ�", "-04");
		MAP_dian_flag.put("������", "-07");
		MAP_dian_flag.put("������", "-08");
		
		MAP_corp_flag.put("0001N510000000001SXX", "-01");
		MAP_corp_flag.put("0001N510000000001SXV", "-02");
		MAP_corp_flag.put("0001N510000000001SY7", "-06");
		MAP_corp_flag.put("0001N510000000001SY1", "-04");
		MAP_corp_flag.put("0001N510000000001SY3", "-07");
		MAP_corp_flag.put("0001N510000000001SY5", "-08");
		
	}
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{context.getAlertTypeName()+"_HKJT_huiyuan"});
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		try
		{
			
			UFBoolean iskayue = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iskayue"),UFBoolean.FALSE);	// ͬ�� �����
			UFBoolean isckyue = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isckyue"),UFBoolean.FALSE);	// ͬ�� �ο����
			
			if( iskayue.booleanValue() )
			{
				importHuiyuanka_QC();// ��Ա�� �ڳ�
			}
			
			if( isckyue.booleanValue() )
			{
				importCika_QC();// �ο� �ڳ�
			}
			
		}catch(Exception ex)
		{ throw new BusinessException(ex);}
		
		return null;
	}
	
	
	/**
	 * ��Ա���ڳ�
	 */
	private void importHuiyuanka_QC() throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		
		
		// ������ѭ�� �һ�Ա������
		StringBuffer query_kaxing = 
				new StringBuffer("select hk_huiyuan_kaxing.* ")
						.append(" from hk_huiyuan_kaxing ")
						.append(" where nvl(dr,0)=0 ")
//						.append(" and kaxing_code between '03' and '09' ")	// ����sql
//						.append(" and kaxing_code in ('109','402') ")	// ����
						.append(" and kaxing_code in ('695','696','849','710','803') ")	// ����
						.append(" order by kaxing_code ")
		;
		
		ArrayList<KaxingHVO> list_kaxing =(ArrayList<KaxingHVO>)getBaseDAO().executeQuery(query_kaxing.toString(), new BeanListProcessor(KaxingHVO.class));
		for (int kaxing_i=0;kaxing_i<list_kaxing.size();kaxing_i++) {
			
			KaxingHVO kaxingHVO = list_kaxing.get(kaxing_i);
			
			StringBuffer query_huiyuaka = 
//					new StringBuffer("SELECT Memberid,MemberName,cardtypeid,coach,LeaveMoney,TrueEnterDate,LastEnterDate ")
					new StringBuffer("SELECT Memberid,cardtypeid,coach,LeaveMoney,TrueEnterDate,LastEnterDate,Status kastatus ")
							.append("FROM hk_member ")
							.append("where Status in ('����','��ʧ','����','ͣ��') ")
//							.append("where Status in ('��ʧ','����','ͣ��') ")
							.append("and cardtypeid = '").append(kaxingHVO.getKaxing_code()).append("' ")
//							.append(" and Memberid in ('GJ05006266','GJSW05002739') ")	// ����
							.append(" and Memberid in ('xs02000252','xs03000568','xs03001251','XSSEZN05000362','XSSEZN05000381','XSWQ05000377','XSYKTCS05000310') ")	// ����
			;
			
			ArrayList<KadanganTempVO> list_huiyuaka_query = (ArrayList<KadanganTempVO>)hkjt_hg_pub_session.executeQuery(query_huiyuaka.toString(), new BeanListProcessor(KadanganTempVO.class));
			
			insertVOS(list_huiyuaka_query);//�����������뵽NC��ʱ��
			
			// ��ʱ�� ������ʽ�� ���˳� δ������� ��������
			StringBuffer query_huiyuaka_insert = 
					new StringBuffer("select hk_huiyuan_kadangan_temp.* ")
							.append(" from hk_huiyuan_kadangan_temp ")
							.append(" left join hk_huiyuan_kadangan on hk_huiyuan_kadangan_temp.memberid = hk_huiyuan_kadangan.ka_code and nvl(hk_huiyuan_kadangan.dr,0)=0 ")
							.append(" where hk_huiyuan_kadangan.pk_hk_huiyuan_kadangan is null ")
			;
			ArrayList<KadanganTempVO> list_huiyuaka_insert = (ArrayList<KadanganTempVO>)getBaseDAO().executeQuery(query_huiyuaka_insert.toString(), new BeanListProcessor(KadanganTempVO.class));

			// �� ��ʱ�� ת������ʽVO
			if(list_huiyuaka_insert.size()>0)
			{
				KadanganHVO[] kadanganHVO_insert = new KadanganHVO[list_huiyuaka_insert.size()];
				for( int i=0;i<list_huiyuaka_insert.size();i++ )
				{
					KadanganTempVO tempVO = list_huiyuaka_insert.get(i);
					kadanganHVO_insert[i] = new KadanganHVO();
					kadanganHVO_insert[i].setKa_code( tempVO.getMemberid() );
					kadanganHVO_insert[i].setKa_name( tempVO.getMembername() );
					kadanganHVO_insert[i].setPk_hk_huiyuan_kaxing( kaxingHVO.getPk_hk_huiyuan_kaxing() );
					kadanganHVO_insert[i].setQc_ye( tempVO.getLeavemoney() );
					kadanganHVO_insert[i].setDq_ye( tempVO.getLeavemoney() );
					kadanganHVO_insert[i].setKabili( kaxingHVO.getKabili() );
					kadanganHVO_insert[i].setKastatus( tempVO.getKastatus() );
					
					kadanganHVO_insert[i].setPk_group( AppContext.getInstance().getPkGroup() );	//����
					kadanganHVO_insert[i].setPk_org( MAP_dian_corp.get(tempVO.getCoach()) );	// pk_rog
//					kadanganHVO_insert[i].setPk_org_v("0001NC10000000004AXY");	// pk_rog_v
					kadanganHVO_insert[i].setVbillcode( kadanganHVO_insert[i].getKa_code() );
					kadanganHVO_insert[i].setIbillstatus(-1);
					kadanganHVO_insert[i].setCreator(HKJT_PUB.MAKER);
					kadanganHVO_insert[i].setCreationtime(new UFDateTime());
					kadanganHVO_insert[i].setDr(0);
					kadanganHVO_insert[i].setDbilldate(new UFDate());
					kadanganHVO_insert[i].setVdef01( tempVO.getCoach() );//���� ��
				}
				this.getBaseDAO().insertVOArray(kadanganHVO_insert);//���뿨�� ��ʽVO
				
				// ��� ��ʱ��
				getBaseDAO().deleteByClause(KadanganTempVO.class, " 1=1 ");
			}
		}
		
	}
	
	
	/**
	 * �ο��ڳ�
	 */
	private void importCika_QC() throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		
		// ������ѭ�� �һ�Ա������
//		StringBuffer query_kaxing = 
//				new StringBuffer("select hk_huiyuan_kaxing.* ")
//						.append(" from hk_huiyuan_kaxing ")
//						.append(" where nvl(dr,0)=0 ")
////						.append(" and kaxing_code between '03' and '09' ")	// ����sql
//						.append(" order by kaxing_code ")
//		;
//		
//		ArrayList<KaxingHVO> list_kaxing =(ArrayList<KaxingHVO>)getBaseDAO().executeQuery(query_kaxing.toString(), new BeanListProcessor(KaxingHVO.class));
//		for (int kaxing_i=0;kaxing_i<list_kaxing.size();kaxing_i++) {
//			
//			KaxingHVO kaxingHVO = list_kaxing.get(kaxing_i);
			
			StringBuffer query_cika = 
					new StringBuffer("SELECT ")
							.append(" a.WaterNum timescardwaternum ")
							.append(",a.CwBillid billid ")
							.append(",a.TotalTimes totaltimes ")
							.append(",a.TotalTimes - a.Times numbercount ")
							.append(",a.Price ")
							.append(",b.GriftMoney kabili ")
							.append(",b.Memberid ")
							.append(",a.VpnName dian ")
							.append(",CONVERT(VARCHAR(19),a.Operatedate,120) Operatedate ")
							.append(",a.ItemId ")
							.append(",a.ItemName ")
							.append(",CONVERT(VARCHAR(10),a.startdata,120) startdata ")
							.append(",CONVERT(VARCHAR(10),a.ExpData,120) expdata ")
							
							.append(",CONVERT(VARCHAR(19),a.LastCountTime,120) LastCountTime ")
							.append(",a.Times ")
							
							.append(" FROM Dt_TimesCard a ")
							.append(" left JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
							.append(" WHERE (1=1) ")
							.append(" and (isnull(a.Status,'null') <> 'ɾ��') ")
							.append(" and a.Times > 0 ")
//							.append(" and CONVERT(VARCHAR(10),a.startdata,120) < '2015-10-26' ")
//							.append(" and a.Status is null ")
//							.append(" and b.CardTypeId = '").append(kaxingHVO.getKaxing_code()).append("' ")
//							.append(" and b.Memberid in ('GJ05006266','GJSW05002739') ")	// ����
							.append(" and b.Memberid in ('xs02000252','xs03000568','xs03001251','XSSEZN05000362','XSSEZN05000381','XSWQ05000377','XSYKTCS05000310') ")
			;
			
//			if(true) 
//			{
//				System.out.println("=="+query_cika);
//				return;
//			}
			
			ArrayList<CikainfoTempVO> list_cika_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cika.toString(), new BeanListProcessor(CikainfoTempVO.class));
			
			insertVOS(list_cika_query);//�����������뵽NC��ʱ��
			
			// ��ʱ�� ������ʽ�� ���˳� δ������� �ο���
			StringBuffer query_cika_insert = 
					new StringBuffer("select ")
							.append(" ka.pk_hk_huiyuan_kadangan ")
							.append(",cika.timescardwaternum ")
							.append(",cika.billid zdh ")
							.append(",cika.operatedate cz_time ")
							.append(",cika.numbercount usednum ")	// ��ʹ�ô���
							.append(",cika.totaltimes totalnum ")
							.append(",cika.itemid ")
							.append(",cika.itemname ")
							.append(",cika.startdata ")
							.append(",cika.expdata ")
							.append(",cika.kabili ")
							.append(",cika.price ")
							.append(",cika.dian vbdef01 ")
							
							.append(",cika.LastCountTime ")
							.append(",cika.Times ")
							
							.append(",0 dr")
							.append(" from hk_huiyuan_cikainfo_temp cika ")
							.append(" left join hk_huiyuan_kadangan ka on cika.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")
							.append(" left join hk_huiyuan_kadangan_ckcz ckcz on cika.timescardwaternum = ckcz.timescardwaternum and nvl(ckcz.dr,0)=0 ")
							.append(" where (1=1) ")
							.append(" and ka.pk_hk_huiyuan_kadangan is not null ")
							.append(" and ckcz.pk_hk_huiyuan_kadangan_ckcz is null ")
			;
			ArrayList<KadanganCKCZVO> list_cika_insert = (ArrayList<KadanganCKCZVO>)getBaseDAO().executeQuery(query_cika_insert.toString(), new BeanListProcessor(KadanganCKCZVO.class));

//			 �� ��ʱ�� ת������ʽVO
			if(list_cika_insert.size()>0)
			{
				KadanganCKCZVO[] CZVO_insert = new KadanganCKCZVO[list_cika_insert.size()];
				CZVO_insert = list_cika_insert.toArray(CZVO_insert);
				
				KadanganCKJGVO[] JGVO_insert = new KadanganCKJGVO[list_cika_insert.size()];
				for( int i=0;i<CZVO_insert.length;i++ )
				{// ��װ ���ο����
					
					JGVO_insert[i] = new KadanganCKJGVO();
					JGVO_insert[i].setPk_hk_huiyuan_kadangan( CZVO_insert[i].getPk_hk_huiyuan_kadangan() );
					JGVO_insert[i].setItemid( CZVO_insert[i].getItemid() );
					JGVO_insert[i].setItemname( CZVO_insert[i].getItemname() );
					JGVO_insert[i].setStartdata( CZVO_insert[i].getStartdata() );
					JGVO_insert[i].setExpdata( CZVO_insert[i].getExpdata() );
					JGVO_insert[i].setTimescardwaternum( CZVO_insert[i].getTimescardwaternum() );
					JGVO_insert[i].setYu_time( CZVO_insert[i].getLastcounttime() );
					JGVO_insert[i].setYunum( CZVO_insert[i].getTimes() );
					JGVO_insert[i].setDr(0);
				}
				
				this.getBaseDAO().insertVOArray(CZVO_insert);//���뿨�� ��ֵVO
				this.getBaseDAO().insertVOArray(JGVO_insert);//���뿨�� ���ο����VO
				
				// ��� ��ʱ��
				getBaseDAO().deleteByClause(CikainfoTempVO.class, " 1=1 ");
			}
			
//		}
		
	}
	
	
	public void insertVOS(ArrayList vos)throws BusinessException{
		getBaseDAO().insertVOList(vos);
	}
	
	BaseDAO dao=null;
	public BaseDAO getBaseDAO(){
		if(dao==null)
			dao=new BaseDAO();
		return dao;
	}

}
