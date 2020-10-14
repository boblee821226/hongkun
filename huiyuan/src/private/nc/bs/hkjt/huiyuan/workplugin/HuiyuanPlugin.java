package nc.bs.hkjt.huiyuan.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.TimeService;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHy_cikainfoMaintain;
import nc.itf.hkjt.IHy_kainfoMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoHVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoTempVO;
import nc.vo.hkjt.huiyuan.huanka.HuankaHVO;
import nc.vo.hkjt.huiyuan.huanka.HuankaTempVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKJGVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganHKVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganJGCKtempVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganJGVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganJGtempVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganTempVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoHVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoTempVO;
import nc.vo.hkjt.huiyuan.kaxing.KaxingHVO;
import nc.vo.hkjt.huiyuan.kaxing.KaxingTempVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

public class HuiyuanPlugin implements IBackgroundWorkPlugin {

	public static String Plugin_Key = "Plugin_HKJT_huiyuan";	// ��̨����ı�ʶ
	public static HashMap<String,String> MAP_dian_corp = new HashMap<String,String>();	// �� ��Ӧ pk_corp
	public static HashMap<String,String> MAP_corp_dian = new HashMap<String,String>();	// pk_corp  ��Ӧ  ��
	public static HashMap<String,String> MAP_dian_flag = new HashMap<String,String>();	// �� ��Ӧ flag
	public static HashMap<String,String> MAP_corp_flag = new HashMap<String,String>();	// pk_corp  ��Ӧ  flag
	public static HashMap<String,String> MAP_dian_db   = new HashMap<String,String>();	// ��  ��Ӧ  ���ݿ�
	public static String zhengde_pk   = "0001N510000000001SXT";		// ����pk
	public static String zhengde_pk_v = "0001N510000000001SXS";		// ����pk_v
	{
		MAP_dian_corp.put("DK", "0001N510000000001SXT");	// ���¹���
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
		
		MAP_dian_db.put("ĵ��",  "L01.jgmd");
		MAP_dian_db.put("����",  "L02.jggj");
		MAP_dian_db.put("��ɽ",  "L06.jgxs");
		MAP_dian_db.put("�Ƶ�",  "L04.lmt");
		MAP_dian_db.put("������", "L07.jgllz");
		MAP_dian_db.put("������", "L08.jgkfr");
		
	}
	
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
//		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{context.getAlertTypeName()+"_HKJT_huiyuan"});
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{Plugin_Key});
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		try
		{
			
			String[] pk_orgs = context.getPk_orgs();
			UFBoolean iskx = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iskx"),UFBoolean.FALSE);	// ͬ������
			UFBoolean iska = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iska"),UFBoolean.FALSE);	// ͬ������
			UFBoolean iskainfo = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iskainfo"),UFBoolean.FALSE);	// ͬ������Ϣ
			UFBoolean isjg = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isjg"),UFBoolean.FALSE);			// ͬ��������
			UFBoolean ishuanka = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("ishuanka"),UFBoolean.FALSE);	// ͬ��������Ϣ
			
			UFBoolean iscikainfo = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iscikainfo"),UFBoolean.FALSE);	// ͬ���ο���Ϣ
			UFBoolean iscikajg = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iscikajg"),UFBoolean.FALSE);	// ͬ���ο�������
			
			UFDate bdate = PuPubVO.getUFDate( context.getKeyMap().get("bdate") );	// ��ʼ����
			UFDate edate = PuPubVO.getUFDate( context.getKeyMap().get("edate") );	// ��������
			
			String btime_str = PuPubVO.getString_TrimZeroLenAsNull( context.getKeyMap().get("btime") );	// ��ο�ʼʱ��
			String etime_str = PuPubVO.getString_TrimZeroLenAsNull( context.getKeyMap().get("etime") );	// ��ν���ʱ��
			
			// �������Ϊ�գ� ��Ĭ��Ϊ ��ǰ���ڵ� ǰһ��
			if(bdate==null) bdate = new UFDate().getDateBefore(1);
			if(edate==null) edate = new UFDate().getDateBefore(1);
			
			
			if(pk_orgs==null || pk_orgs.length<=0)
			{
				pk_orgs = new String[]{
						 "0001N510000000001SXV"	//����
						,"0001N510000000001SXX"	//ĵ��
						,"0001N510000000001SY7"	//��ɽ
						,"0001N510000000001SY1" //"�Ƶ�"
						,"0001N510000000001SY3" //"������"
						,"0001N510000000001SY5" //"������"
				};
			}
			
			if( iskx.booleanValue() )
			{// ͬ������
				importKaxing();	
			}
			
			if( iska.booleanValue() )
			{// ͬ������
				importKaiKa(pk_orgs,bdate,edate);
			}
			
			if( ishuanka.booleanValue() )
			{// ͬ�� ������Ϣ
				importHuanka(pk_orgs,bdate,edate);
			}
			
			if( iskainfo.booleanValue() )
			{// ͬ�� ��Ա����Ϣ
				importHuiyuanka_info(pk_orgs,bdate,edate,btime_str,etime_str);
			}
			
			if( isjg.booleanValue() )
			{// ͬ�� ������
				importJGyue(pk_orgs,bdate,edate);
			}
			
			if( iscikainfo.booleanValue() )
			{// ͬ�� �ο���Ϣ
				importCika_info(pk_orgs,bdate,edate,btime_str,etime_str);
			}
			
			if( iscikajg.booleanValue() )
			{// ͬ�� �ο�������
				importJGyue_ck(pk_orgs,bdate,edate);
			}
			
			
		}catch(Exception ex)
		{ throw new BusinessException(ex);}
		
		return null;
	}

	/**
	 * ���ڴ������
	 * ��̨�����޷�ִ��
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		String[] pk_orgs = {
//			"0001N510000000001SY7"	// ��ɽ
//			"0001N510000000001SXV"	// ����
//			"0001N510000000001SXX"	// "ĵ��"
			"0001N510000000001SY1", // "������Ƶ�"
//			"0001N510000000001SY3",	// "������"
//			"0001N510000000001SY5", // "������"
				
		};
		
		UFDate bdate = PuPubVO.getUFDate("2020-10-08");
		UFDate edate = PuPubVO.getUFDate("2020-10-08");
		
		try
		{
			// ͬ�� �ο���Ϣ
//			importCika_info(pk_orgs,bdate,edate,"2019-01-06 23:57:31","2019-01-07 23:57:35");
			// ͬ�� ��Ա����Ϣ
//			importHuiyuanka_info(pk_orgs,bdate,edate,"2019-02-26 00:03:27","2019-02-27 00:13:43");
			importHuiyuanka_info(pk_orgs,bdate,edate,null,null);
			
		}catch(Exception ex)
		{
			throw new BusinessException(ex);
		}
		
		return null;
	}
	
	
	/**
	 * ����
	 */
	private void importKaxing() throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		StringBuffer query_kaxing = // �鿴���п���
				new StringBuffer("select CardTypeId,CardTypeName,DefaultMoney,GriftMoney,ShiShou,CardAlias,GroupName,VpnUse,Remark ")
						.append(" from Dt_CardInfo ")
		;
		
		ArrayList<KaxingTempVO> list_kaxing_query = (ArrayList<KaxingTempVO>)hkjt_hg_pub_session.executeQuery(query_kaxing.toString(), new BeanListProcessor(KaxingTempVO.class));
		
		insertVOS(list_kaxing_query);//�����Ͳ��뵽NC��ʱ��
		
		// ��ʱ�� ������ʽ�� ���˳� δ������� ���͡�
		StringBuffer query_kaxing_insert = 
				new StringBuffer("select hk_huiyuan_kaxing_temp.* ")
						.append(" from hk_huiyuan_kaxing_temp ")
						.append(" left join hk_huiyuan_kaxing on hk_huiyuan_kaxing_temp.cardtypeid = hk_huiyuan_kaxing.kaxing_code and nvl(hk_huiyuan_kaxing.dr,0)=0 ")
						.append(" where hk_huiyuan_kaxing.pk_hk_huiyuan_kaxing is null ")
		;
		ArrayList<KaxingTempVO> list_kaxing_insert = (ArrayList<KaxingTempVO>)getBaseDAO().executeQuery(query_kaxing_insert.toString(), new BeanListProcessor(KaxingTempVO.class));
		
		// �� ��ʱ�� ת������ʽVO
		if(list_kaxing_insert.size()>0)
		{
			KaxingHVO[] kaxingHVO_insert = new KaxingHVO[list_kaxing_insert.size()];
			for( int i=0;i<list_kaxing_insert.size();i++ )
			{
				KaxingTempVO tempVO = list_kaxing_insert.get(i);
				kaxingHVO_insert[i] = new KaxingHVO();
				kaxingHVO_insert[i].setKaxing_code( tempVO.getCardtypeid() );	//���ͱ���
				kaxingHVO_insert[i].setKaxing_name( tempVO.getCardtypename() );	//��������
				kaxingHVO_insert[i].setKa_je( tempVO.getDefaultmoney() );	//�����
				kaxingHVO_insert[i].setKabili( tempVO.getGriftmoney() );	//������
				kaxingHVO_insert[i].setKa_ss( tempVO.getShishou() );		//ʵ��
				kaxingHVO_insert[i].setCardalias( tempVO.getCardalias() );
				kaxingHVO_insert[i].setGroupname( tempVO.getGroupname() );
				kaxingHVO_insert[i].setVpnuse( tempVO.getVpnuse() );
				kaxingHVO_insert[i].setRemark( tempVO.getRemark() );
				kaxingHVO_insert[i].setKa_zs( nullAsZero( kaxingHVO_insert[i].getKa_je() ).sub( nullAsZero( kaxingHVO_insert[i].getKa_ss() ) ) );	// ���ͽ�� = �����-ʵ��
				kaxingHVO_insert[i].setPk_group( AppContext.getInstance().getPkGroup() );	//����
				kaxingHVO_insert[i].setPk_org(zhengde_pk);		// pk_org ����pk
				kaxingHVO_insert[i].setPk_org_v(zhengde_pk_v);	// pk_org_v
				kaxingHVO_insert[i].setVbillcode( kaxingHVO_insert[i].getKaxing_code() );
				kaxingHVO_insert[i].setIbillstatus(-1);	// ����̬
				kaxingHVO_insert[i].setCreator(HKJT_PUB.MAKER);
				kaxingHVO_insert[i].setCreationtime(new UFDateTime());
				kaxingHVO_insert[i].setDr(0);
				kaxingHVO_insert[i].setDbilldate(new UFDate());
			}
			this.getBaseDAO().insertVOArray(kaxingHVO_insert);//���뿨�� ��ʽVO
			
			// ��� ��ʱ��
			getBaseDAO().deleteByClause(KaxingTempVO.class, " 1=1 ");
		}
		
	}
	
	
	/**
	 * ��Ա����Ϣ���ճ�ͬ��
	 */
	private void importHuiyuanka_info(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate,String btime_str,String etime_str) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		
		ArrayList<String> list_date = this.getTimeDates(kainfo_bdate,kainfo_edate);
		/** 
		 * ��ÿ���ҵ�����ݣ���˫��ѭ��
		 * ��ѭ��  ��  ��
		 * ��ѭ��  ��  ��
		 */
		for(int org_i=0;org_i<pk_orgs.length;org_i++)
		{// ����  �� ѭ������
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
			String flag = MAP_dian_flag.get(dian);
		
			for (String yw_date : list_date) 
			{// ���� �� ѭ������
				
				//�жϣ��Ƿ����
				StringBuffer query_check = 
						new StringBuffer("select kainfo.pk_hk_huiyuan_kainfo ")
								.append(" from hk_huiyuan_kainfo kainfo ")
								.append(" where nvl(kainfo.dr,0)=0 ")
								.append(" and kainfo.pk_org = '"+corp+"' ")		// ��˾
								.append(" and substr(kainfo.dbilldate,0,10) = '"+yw_date+"' ")	// ҵ������
				;
				Object check_pk = getBaseDAO().executeQuery(query_check.toString(),new ColumnProcessor());
				
				if(check_pk!=null) continue;	// ���������  ��������
				
				String[] yw_time = new String[2];	// ���ʱ��
				
				if( btime_str!=null && etime_str!=null )
				{// ��� ��̨�������  ������  ��ο�ʼ���ڡ���ν������ڣ�   �����õ���
					yw_time[0] = btime_str;
					yw_time[1] = etime_str;
				}
				else
				{// ����ҵ������  ���� ����ʱ�� ����ʼ��������
					yw_time = this.getYwDate(PuPubVO.getUFDate(yw_date), flag);
				}
				
				if( yw_time==null || yw_time.length<2 ) continue;	// �Ҳ��� ҵ��ʱ�� ���˳�
				
				// ��ֵ�����ѡ���ת
				StringBuffer query_kainfo = 
						new StringBuffer("")
								// ��ֵ������
								.append(" SELECT ")
								.append("b.WaterNum,")		// ��ˮ��
								.append("b.Billid,")		// �˵���
								.append("CONVERT(VARCHAR(19),b.FeeInDate,120) FeeInDate,")	// ҵ��ʱ��
								.append("a.Memberid,")		// ����
								.append("b.Money,")			// �����
								.append("b.LeaveCardMoney,")// ������ʱ��
								.append("b.TrueMoney,")		// ʵ��
								.append("b.VpnName,")		// ����
								.append("b.FeeType,")		// ��Ŀ����
								.append("b.MemberGuid,")	// ��id
								.append("b.OperatorName,")	// ����Ա
								.append("null SourceMemberid,")		// Դ����
								.append("null SourceLeavemoney,")	// Դ������ʱ��
								.append("null SourceMemberGuid ")	// Դ��id
								.append("FROM hk_member a ")
								.append("INNER JOIN Dt_MemberCardComeIn b ON a.MemberGuid = b.MemberGuid ")
								.append("WHERE 1=1 ")
								.append(" and b.FeeType in ( '��ֵ','����' ) ")
								.append(" and Remark<>'�ǿ���' ")
								.append(" and b.VpnName='"+dian+"' ")	// ��
								.append(" and CONVERT(VARCHAR(19),b.FeeInDate,120) >  '"+yw_time[0]+"' ")
								.append(" and CONVERT(VARCHAR(19),b.FeeInDate,120) <= '"+yw_time[1]+"' ")
								// ��ת
								.append(" union all ")
								.append(" select ")
								.append(" Waternum ")
								.append(",Waternum Billid ")
								.append(",CONVERT(VARCHAR(19),OperateDate,120) FeeInDate ")
								.append(",DesMemberid ")
								.append(",TransferMoney ")
								.append(",DesLeavemoney + TransferMoney ")
								.append(",TransferMoney TrueMoney ")
								.append(",VpnName ")
								.append(",'��ת' FeeType ")
								.append(",DesMemberGuid ")
								.append(",Operator ")
								.append(",SourceMemberid ")
								.append(",SourceLeavemoney - TransferMoney ")
								.append(",SourceMemberGuid ")
								.append(" from Dt_CardTransferHistory ")
								.append(" where 1=1 ")
								.append(" and VpnName='"+dian+"' ")	// ��
								.append(" and Remark <> '����' ")
								.append(" and remark <> '�ο�ת��'")	// �ο�ת�ƹ��ܣ����ڻ�Ա��������¼  ����Ҫȥ������� 2016��7��31��17:49:02��
								.append(" and CONVERT(VARCHAR(19),OperateDate,120) >  '"+yw_time[0]+"' ")
								.append(" and CONVERT(VARCHAR(19),OperateDate,120) <= '"+yw_time[1]+"' ")
				;
				
				ArrayList<KainfoTempVO> list_kainfo_query = (ArrayList<KainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_kainfo.toString(), new BeanListProcessor(KainfoTempVO.class));
				
				if(list_kainfo_query.size()==0) continue;
				
				insertVOS(list_kainfo_query);//��ҵ�����ݲ��뵽NC��ʱ��
				
				// ���� �������
				StringBuffer query_kainfo_insert = 
						new StringBuffer(" select ")
								.append(" kainfo.feeindate ywsj ")				// ҵ��ʱ��
								.append(",kainfo.feetype xmdl ")				// ��Ŀ����
								.append(",kainfo.billid zdh ")					// �˵���
								.append(",kainfo.memberid ka_code ")			// ��code
								.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")	// ��pk
								.append(",ka.ka_name ka_name ")					// ��name
								.append(",ka.pk_hk_huiyuan_kaxing kaxing_pk ")	// ����pk
								.append(",kaxing.kaxing_code kaxing_code ")		// ����code
								.append(",kaxing.kaxing_name kaxing_name ")		// ����name
								.append(",ka.kabili kabili ")					// ������
								.append(",kainfo.money ka_je ")					// �����
								.append(",kainfo.truemoney ka_ss ")				// ��ʵ��
								.append(",kainfo.leavecardmoney ka_yue ")		// �����
								.append(",kainfo.sourcememberid y_ka_code ")	// Դ��code
								.append(",kainfo.sourceleavemoney y_ka_yue ")	// Դ�����
								.append(",ka_y.kabili y_kabili ")				// Դ������
								.append(",ka_y.pk_hk_huiyuan_kadangan y_ka_pk ")	// Դ��pk
								.append(",ka_y.pk_hk_huiyuan_kaxing y_kaxing_pk ")	// Դ����pk
								.append(",kaxing_y.kaxing_code y_kaxing_code ")		// Դ����code
								.append(",kaxing_y.kaxing_name y_kaxing_name ")		// Դ����name
								.append(",kaxing.ka_je vbdef01 ")		// ���ͽ��
								.append(",kaxing.ka_ss vbdef02 ")		// ����ʵ��
								.append(",kaxing.ka_zs vbdef03 ")		// ��������
								.append(" from hk_huiyuan_kainfo_temp kainfo ")		// ����Ϣ
								.append(" left join hk_huiyuan_kadangan ka on kainfo.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")			// ������
								.append(" left join hk_huiyuan_kaxing kaxing on ka.pk_hk_huiyuan_kaxing = kaxing.pk_hk_huiyuan_kaxing ")	// ����
								.append(" left join hk_huiyuan_kadangan ka_y on kainfo.sourcememberid = ka_y.ka_code and nvl(ka_y.dr,0)=0 ")	// Դ������
								.append(" left join hk_huiyuan_kaxing kaxing_y on ka_y.pk_hk_huiyuan_kaxing = kaxing_y.pk_hk_huiyuan_kaxing ")	// Դ����
								.append(" where (1=1) ")
								/**
								 * HK 2020��10��9��17:26:37
								 * ��ȡ ΢��Ա �Ŀ��͵�����
								 * �����һ�����ͽС�΢��Ա�����Ǽ�¼����̳ǻ�Ա��Ϣ�ģ���󲻻ᷢ���κεĻ�Ա�����񣬵���ÿ��Ļ�Ա����Ϣ�����������ȡ�����ˣ��������ǻ�Ա����Ϣ���ܱ��档����һ�£��û�Ա����Ϣ��ֱ�Ӳ�ȡ������;Ϳ����ˡ�
								 * ��ȡ why ��ͷ�Ļ�Ա��
								 */
								.append(" and kainfo.memberid not like 'why%' ")
								/***END***/
								.append(" order by kainfo.feeindate ")
				;
				ArrayList<KainfoBVO> list_kainfo_insert = (ArrayList<KainfoBVO>)getBaseDAO().executeQuery(query_kainfo_insert.toString(), new BeanListProcessor(KainfoBVO.class));
				
				if( list_kainfo_insert.size()>0 )
				{
					KainfoBVO[] kainfoBVOs = new KainfoBVO[list_kainfo_insert.size()];
					kainfoBVOs = list_kainfo_insert.toArray(kainfoBVOs);
					
					UFDouble cz = UFDouble.ZERO_DBL;	// ��ֵ
					UFDouble yz = UFDouble.ZERO_DBL;	// ��ת
					UFDouble xf = UFDouble.ZERO_DBL;	// ����
					
					HashMap<String,KainfoBVO> map_cz = new HashMap<String, KainfoBVO>();	// ��ֵ���ݣ����˵�  ͬ�� �ж�ʳ�ֵ�������
					Vector<String> v_zfyz = new Vector<String>();	// ��ת���Ͽ�
					
					for( int i=0;i<kainfoBVOs.length;i++ )
					{// ѭ������ �ӱ�VO
						
						kainfoBVOs[i].setVrowno(i+1);
						
						if("����".equals( kainfoBVOs[i].getXmdl() ))
						{
							kainfoBVOs[i].setKa_ss( (nullAsZero(kainfoBVOs[i].getKa_je()).multiply(nullAsZero(kainfoBVOs[i].getKabili()))).setScale(2,UFDouble.ROUND_HALF_UP) );	// ��ʵ�� = ����� * ������
							
							xf = xf.add( kainfoBVOs[i].getKa_je() );	// ���� ����
						}
						else if("��ת".equals( kainfoBVOs[i].getXmdl() ))
						{
							kainfoBVOs[i].setKa_ss( (nullAsZero(kainfoBVOs[i].getKa_je()).multiply(nullAsZero(kainfoBVOs[i].getKabili()))).setScale(2,UFDouble.ROUND_HALF_UP) );	// ��ʵ�� = ����� * ������
							kainfoBVOs[i].setY_ka_je( kainfoBVOs[i].getKa_je() );	// Դ����� = �����
							kainfoBVOs[i].setY_ka_ss( (nullAsZero(kainfoBVOs[i].getY_ka_je()).multiply(nullAsZero(kainfoBVOs[i].getY_kabili()))).setScale(2,UFDouble.ROUND_HALF_UP) );// Դ��ʵ�� = Դ����� * Դ������
						
							if( "�����".equals(kainfoBVOs[i].getKaxing_name()) )
							{// ����� ת�����󿨣� ��ŵ�������Ա� ����  ��������ĳ�ֵ  ����Ϊ���Ͽ���
								v_zfyz.add(kainfoBVOs[i].getY_ka_code());
							}
							
							yz = yz.add( kainfoBVOs[i].getKa_je() );	// ���� ��ת
						}
						else if("��ֵ".equals( kainfoBVOs[i].getXmdl() ))
						{
//							kainfoBVOs[i].setKa_zs( nullAsZero(kainfoBVOs[i].getKa_je()).sub( nullAsZero(kainfoBVOs[i].getKa_ss()) ) );	// ���ͽ�� = ����� - ʵ��
							
							if( 
								nullAsZero(kainfoBVOs[i].getKa_je()).compareTo( nullAsZero(kainfoBVOs[i].getVbdef01()) ) !=0 
							  )
							{// ��� �����  ������  ���ͽ� ����Ϊ��   �س䡣
								kainfoBVOs[i].setXmlx("�س�");
								kainfoBVOs[i].setKa_zs(null);
								kainfoBVOs[i].setKa_ss( PuPubVO.getUFDouble_NullAsZero( kainfoBVOs[i].getKa_je() ).multiply( PuPubVO.getUFDouble_NullAsZero( kainfoBVOs[i].getKabili() ) ).setScale(UFDouble.ROUND_HALF_UP, 2) );
								
							}
							else
							{// �ǻس䣬 ��   ʵ��  ��  ���ͣ�  ���¸�ֵΪ  ���͵�ʵ�� ������
								kainfoBVOs[i].setKa_ss( nullAsZero(kainfoBVOs[i].getVbdef02()) );
								kainfoBVOs[i].setKa_zs( nullAsZero(kainfoBVOs[i].getVbdef03()) );
								
								if( map_cz.containsKey(kainfoBVOs[i].getKa_code()) )
									map_cz.put(kainfoBVOs[i].getKa_code(), null);
								else
									map_cz.put(kainfoBVOs[i].getKa_code(), kainfoBVOs[i]);
								
							}
							
							cz = cz.add( kainfoBVOs[i].getKa_je() );	// ���� ��ֵ
						}
						
					}
					
					// �����Ͽ��Ĵ���
					if( v_zfyz!=null && v_zfyz.size()>0 )
					{
						for(int vi=0;vi<v_zfyz.size();vi++)
						{
							KainfoBVO vo = map_cz.get(v_zfyz.get(vi));
							if(vo!=null)
								vo.setXmlx("���Ͽ�");
						}
					}
					
					
					// ���� ����VO
					KainfoHVO kainfoHVO = new KainfoHVO();
					kainfoHVO.setIbillstatus(-1);
					kainfoHVO.setDbilldate(new UFDate( yw_date ));
					kainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
					kainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
					kainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//����
					kainfoHVO.setPk_org( corp );
					kainfoHVO.setCreator(HKJT_PUB.MAKER);
					kainfoHVO.setCreationtime(new UFDateTime());
					kainfoHVO.setVbillcode( dian+"-"+yw_date );
					kainfoHVO.setVbilltypecode("HK24");
					kainfoHVO.setVdef01(dian);	// ҵ���ŵ�
					kainfoHVO.setVdef02( cz.toString() );	// ��ֵ
					kainfoHVO.setVdef03( yz.toString() );	// ��ת
					kainfoHVO.setVdef04( xf.toString() );	// ����
					// �ۺ�VO
					KainfoBillVO kainfoBillVO = new KainfoBillVO();
					kainfoBillVO.setParent(kainfoHVO);
					kainfoBillVO.setChildrenVO(kainfoBVOs);
					
					// ���� �ۺ�VO
					IHy_kainfoMaintain itf = NCLocator.getInstance().lookup(IHy_kainfoMaintain.class);
					itf.insert(new KainfoBillVO[]{kainfoBillVO}, null);
					
					// ��� ��ʱ��
					getBaseDAO().deleteByClause(KainfoTempVO.class, " 1=1 ");
				}
			
			}
		
		}
		
	}
	
	/**
	 * ��� ҵ��ʱ���
	 * ��Ϊ ������� ����  ���� û��ʱͬ���� ����  ���� �Ӹ���ҵ����� ȡ����
	 */
	private String[] getYwDate(UFDate date,String flag) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		if( "-01".equals(flag) )
		{// ���¥ ĵ��
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_gbl").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-02".equals(flag) )
		{// ���� 
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_gjhg").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-06".equals(flag) )
		{// ��ɽ��Ȫ 
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_xswq").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-04".equals(flag) )
		{// ������ �Ƶ�
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_kfrjd").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-07".equals(flag) )
		{// ������
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_llzjd").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-08".equals(flag) )
		{// ������ ��ɽ��  
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_kfrxsd").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else
		{
			return null;
		}
		
		StringBuffer query_sql = null;
		
		if("-04".equals(flag))
		{// ������Ƶ� ��  �Ƶ�ǰ��  ����ʾҵ������
			
			if( "2015-12-13".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-12-12 00:40:02","2015-12-14 00:27:08"};

			else
			{
				query_sql = 
						new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
								.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
								.append("from Dt_ChangeClass ")	// ҵ����ܿ�
								.append("WHERE ")
								.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 11:59:59' ")	// ʱ�䷶ΧӦ���� ǰһ�졢��һ��  12��ǰ����
								.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
								.append(" and Opersite = '�Ƶ�ǰ��' ")
								.append(" and SUBSTRING(changeclassid,1,3) = 'ZJD' ")	// ͨ�� ������ǰ׺ ������
				;
			}
		}
		else if("-07".equals(flag))
		{// ������ ��  ǰ����  ����ʾҵ������
			query_sql = 
					new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
							.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
							.append("from Dt_ChangeClass ")	// ҵ����ܿ�
							.append("WHERE ")
							.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 11:59:59' ")	// ʱ�䷶ΧӦ���� ǰһ�졢��һ��  12��ǰ����
							.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
							.append(" and Opersite = 'ǰ����' ")
			;
			
		}
		else if("-08".equals(flag))
		{// ��������ɽ��
			
			     if( "2015-10-20".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-10-20 00:30:10","2015-10-21 03:34:56"};
			else if( "2015-10-21".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-10-21 03:34:56","2015-10-22 02:09:54"};
			else if( "2015-10-22".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-10-22 02:09:54","2015-10-23 02:08:27"};
			else if( "2015-10-23".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-10-23 02:08:27","2015-10-24 03:09:12"};
			
			else if( "2015-10-24".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-10-25".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-10-26".equals( date.toString().substring(0,10) ) )
				return null;
			
			else if( "2015-10-27".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-10-24 03:09:12","2015-10-28 01:50:10"};
			
			else if( "2015-11-02".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-11-03".equals( date.toString().substring(0,10) ) )
				return null;
			
			else if( "2015-11-04".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-11-02 00:12:28","2015-11-05 00:35:27"};
			
			else if( "2015-11-05".equals( date.toString().substring(0,10) ) )
				return null;
			
			else if( "2015-11-06".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-11-05 00:35:27","2015-11-07 02:48:06"};
			
			else if( "2015-11-07".equals( date.toString().substring(0,10) ) )
				return null;
			
			else if( "2015-11-08".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-11-07 02:48:06","2015-11-09 00:13:17"};
			
			else if( "2015-11-09".equals( date.toString().substring(0,10) ) )
				return null;
			
			else if( "2015-11-10".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-11-09 00:13:17","2015-11-11 00:29:48"};
			
			else if( "2015-11-11".equals( date.toString().substring(0,10) ) )
				return null;
			
			else if( "2015-11-12".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-11-11 00:29:48","2015-11-13 00:27:33"};
			
			else if( "2015-11-20".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-11-21".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-11-22".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-11-23".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-11-24".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-11-25".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-11-20 01:01:09","2015-11-26 00:04:44"};
			
			else if( "2015-11-26".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-11-27".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-11-26 00:04:44","2015-11-28 03:07:13"};
			
			else if( "2015-12-06".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-12-07".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-12-05 23:54:16","2015-12-08 00:14:08"};
			
			else
			{
				query_sql = 
						new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
								.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
								.append("from Dt_ChangeClass ")	// ��˾ҵ���
								.append("WHERE ")
								.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 11:59:59' ")	// ʱ�䷶ΧӦ���� ǰһ�졢��һ��  6��ǰ����
								.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
								.append(" and SUBSTRING(changeclassid,1,3) = 'ZKF' ")	// ͨ�� ������ǰ׺ ������
								.append(" and Opersite = 'ǰ����' ");
			}
			
		}
		else if("-01".equals(flag))
		{// ���¥
			     if( "2015-12-24".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-12-25".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-12-23 23:50:24","2015-12-25 23:59:09"};
			
			else
				query_sql = 
				new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
						.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
						.append("from Dt_ChangeClass ")	// ҵ����ܿ�
						.append("WHERE ")
						.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 00:59:59' ")	// ʱ�䷶ΧӦ���� ǰһ�졢��һ�� ����һСʱ
						.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
						.append(" and Opersite = 'ɣ�ò�' ")
						.append(" and SUBSTRING(changeclassid,1,3) = 'ZSN' ")	// ͨ�� ������ǰ׺ ������
						;// ͬ�� 
				
		}
		else
		{// �����Ļ��  ��  ɣ�ò�  ����ʾҵ������
			query_sql = 
					new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
							.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
							.append("from Dt_ChangeClass ")	// ҵ����ܿ�
							.append("WHERE ")
							.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 00:59:59' ")	// ʱ�䷶ΧӦ���� ǰһ�졢��һ�� ����һСʱ
							.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
							.append(" and Opersite = 'ɣ�ò�' ")
							.append(" and SUBSTRING(changeclassid,1,3) = 'ZSN' ")	// ͨ�� ������ǰ׺ ������
			;
		}
		
		
		
		List<String[]> list = (List<String[]>)hkjt_hg_pub_session.executeQuery(query_sql.toString(), new ArrayListProcessor());
		
		String[] result = new String[2];
		if(list.size()>0)
		{
			Object[] obj = list.get(0);
			result[0] = PuPubVO.getString_TrimZeroLenAsNull( obj[0] );
			result[1] = PuPubVO.getString_TrimZeroLenAsNull( obj[1] );
		}
			
		
		return result;
	}
	
	/**
	 * ������Ϣ ͬ��
	 */
	private void importKaiKa(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		if(kainfo_bdate==null) kainfo_bdate = new UFDate("1990-01-01");
		if(kainfo_edate==null) kainfo_edate = new UFDate("2990-12-31");
		
		// ���� ����ʱ��   ��ֵΪ   ��ǰ������ʱ�䣬 ȷ���ܽ������ʱ�� NC�ﶼ�ÿ�
		kainfo_edate = new UFDate();
		
		for( int org_i=0;org_i<pk_orgs.length;org_i++ )
		{// ��˾ѭ��
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
			
			// ��ѯ ������Ϣ
			StringBuffer query_kaika = 
					new StringBuffer("select memberid ")
							.append(",CardType cardtypeid ")
							.append(",OutVpn coach ")
							.append(",CONVERT(VARCHAR(19),OutDate,120) lastenterdate ")
//							.append(",OutPerson ")
							.append(" from Dt_MakeCard ")
							.append(" where (1=1) ")
							.append(" and Status = '�ѷ�' ")
							.append(" and OutVpn = '").append( dian ).append("' ")	// ��
							.append(" and CONVERT(VARCHAR(19),isnull(OutDate,'1990-01-01 00:00:00'),120) between '").append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// ʱ��
			;
			
			ArrayList<KadanganTempVO> list_huiyuaka_query = (ArrayList<KadanganTempVO>)hkjt_hg_pub_session.executeQuery(query_kaika.toString(), new BeanListProcessor(KadanganTempVO.class));
			
			insertVOS(list_huiyuaka_query);//�����������뵽NC��ʱ��
			
			// ��ʱ�� ������ʽ�� ���˳� δ������� ��������
			StringBuffer query_huiyuaka_insert = 
					new StringBuffer("select hk_huiyuan_kadangan_temp.memberid ")
							.append(",hk_huiyuan_kadangan_temp.coach ")
							.append(",hk_huiyuan_kaxing.pk_hk_huiyuan_kaxing cardtypeid ")
							.append(",hk_huiyuan_kaxing.kabili ")
							.append(" from hk_huiyuan_kadangan_temp ")
							.append(" left join hk_huiyuan_kadangan on hk_huiyuan_kadangan_temp.memberid = hk_huiyuan_kadangan.ka_code and nvl(hk_huiyuan_kadangan.dr,0)=0 ")	// ������
							.append(" left join hk_huiyuan_kaxing on hk_huiyuan_kadangan_temp.cardtypeid = hk_huiyuan_kaxing.kaxing_code and nvl(hk_huiyuan_kaxing.dr,0)=0 ")	// ����
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
					kadanganHVO_insert[i].setPk_hk_huiyuan_kaxing( tempVO.getCardtypeid() );	// ����
//					kadanganHVO_insert[i].setQc_ye( tempVO.getLeavemoney() );
//					kadanganHVO_insert[i].setDq_ye( tempVO.getLeavemoney() );
					kadanganHVO_insert[i].setKabili( tempVO.getKabili() );
					kadanganHVO_insert[i].setKastatus("����");
					
					kadanganHVO_insert[i].setPk_group( AppContext.getInstance().getPkGroup() );	//����
					kadanganHVO_insert[i].setPk_org( MAP_dian_corp.get(tempVO.getCoach()) );	// pk_rog
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
	 * ͬ�� ������
	 */
	private void importJGyue(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		if(kainfo_bdate==null) kainfo_bdate = new UFDate("1990-01-01");
		if(kainfo_edate==null) kainfo_edate = new UFDate("2990-12-31");
		
		// ����ѭ�� �һ�Ա���������
		// ��˾������
		StringBuffer query_kaxing = 
				new StringBuffer("select hk_huiyuan_kaxing.* ")
						.append(" from hk_huiyuan_kaxing ")
						.append(" where nvl(dr,0)=0 ")
//						.append(" and kaxing_code = '03' ")	// ����
						.append(" order by kaxing_code ")
		;
		
		ArrayList<KaxingHVO> list_kaxing =(ArrayList<KaxingHVO>)getBaseDAO().executeQuery(query_kaxing.toString(), new BeanListProcessor(KaxingHVO.class));
		
		for( int org_i=0;org_i<pk_orgs.length;org_i++ )
		{// ��˾ѭ��
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
//			String flag = MAP_dian_flag.get(dian);
		
			for (int kaxing_i=0;kaxing_i<list_kaxing.size();kaxing_i++) {// ����ѭ��
			
				KaxingHVO kaxingHVO = list_kaxing.get(kaxing_i);
				
				StringBuffer query_jgyue = // �鿴������
						new StringBuffer("SELECT ")
								.append(" Memberid ")
								.append(",LeaveMoney ")
								.append(",CONVERT(VARCHAR(19),LastEnterDate,120) LastEnterDate ")
								.append(" FROM hk_member ")
								.append(" where (1=1) ")
								.append(" and Status != 'ɾ��' ")
								.append(" and cardtypeid='").append(kaxingHVO.getKaxing_code()).append("' ")	// ����
								.append( "����".equals(dian) ? " and coach in ('����','�ϵ�','DK') " : " and coach = '" + dian + "' ")	// ��
//								.append(" and ( ")
//								.append("    CONVERT(VARCHAR(19),isnull(LastEnterDate,'1990-01-01 00:00:00'),120) between '").append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// ʱ��
//								.append(" or CONVERT(VARCHAR(19),isnull(TrueEnterDate,'1990-01-01 00:00:00'),120) between '").append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// ʱ��
//								.append(" ) ")
//								.append(" and Memberid = '0302101237' ")
								;
				
				ArrayList<KadanganJGtempVO> list_jgyue_query = (ArrayList<KadanganJGtempVO>)hkjt_hg_pub_session.executeQuery(query_jgyue.toString(), new BeanListProcessor(KadanganJGtempVO.class));
				insertVOS(list_jgyue_query);//��ҵ�����ݲ��뵽NC��ʱ��
				
				// ��ʱ�� ������ʽ�� ���˳� δ������� �����
				{// ���� ������ʱ��  �� �ж��Ƿ�Ӧ�� ͬ��������
				 // ֻ�� ���ѵ�����  �Ż���� ������ʱ��
					StringBuffer query_jgyue_insert = 
							new StringBuffer("SELECT ")
									.append(" ka.pk_hk_huiyuan_kadangan ")
									.append(",jgtemp.leavemoney jg_yue")
									.append(",jgtemp.lastenterdate ")
									.append(",0 dr")
									.append(" from hk_huiyuan_kadangan_jg_temp jgtemp ")
									.append(" left join hk_huiyuan_kadangan ka on jgtemp.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")
									.append(" left join hk_huiyuan_kadangan_jg jg on ka.pk_hk_huiyuan_kadangan = jg.pk_hk_huiyuan_kadangan and nvl(jg.dr,0)=0 and nvl(jg.lastenterdate,'null') = nvl(jgtemp.lastenterdate,'null') ")
									.append(" where jg.pk_hk_huiyuan_kadangan_jg is null ")
									.append(" and ka.pk_hk_huiyuan_kadangan is not null ")
					;
					ArrayList<KadanganJGVO> list_jgyue_insert = (ArrayList<KadanganJGVO>)getBaseDAO().executeQuery(query_jgyue_insert.toString(), new BeanListProcessor(KadanganJGVO.class));
					
					UFDateTime serverTime = new UFDateTime();
					
					// �� ��ʱ�� ת������ʽVO
					if(list_jgyue_insert.size()>0)
					{
						KadanganJGVO[] kadanganJGVO_insert = new KadanganJGVO[list_jgyue_insert.size()];
						for( int i=0;i<list_jgyue_insert.size();i++ )
						{
							kadanganJGVO_insert[i] = list_jgyue_insert.get(i);
							
						    String drsj = PuPubVO.getString_TrimZeroLenAsNull( kadanganJGVO_insert[i].getLastenterdate() );
						    if(drsj==null)
						    {// ��� ����ʱ��Ϊ�գ� ��ֵΪ ��ǰʱ��
						    	drsj =  PuPubVO.getString_TrimZeroLenAsNull(serverTime);
						    }
							
							kadanganJGVO_insert[i].setVbdef01( drsj );
						}
						this.getBaseDAO().insertVOArray(kadanganJGVO_insert);//���뿨�� ��ʽVO
					}
				}
				
				{// ʱ�� ��ͬ�ģ� ����  ��ͬ��
				 // ʱ�� ��ֵΪ  ��ǰ������ʱ��
					
					StringBuffer query_jgyue_insert_2 = 
							new StringBuffer("SELECT ")
									.append(" distinct ")
									.append(" ka.pk_hk_huiyuan_kadangan ")
									.append(",jgtemp.leavemoney jg_yue")
									.append(",jgtemp.lastenterdate")
									.append(",0 dr")
									.append(" from hk_huiyuan_kadangan_jg_temp jgtemp ")
									.append(" inner join hk_huiyuan_kadangan ka on jgtemp.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")
									.append(" inner join hk_huiyuan_kadangan_jg jg on ka.pk_hk_huiyuan_kadangan = jg.pk_hk_huiyuan_kadangan and nvl(jg.dr,0)=0 and nvl(jg.lastenterdate,'null') = nvl(jgtemp.lastenterdate,'null') ")
									
									.append(" inner join ")
									.append(" ( ")
									.append("      select pk_hk_huiyuan_kadangan,max(vbdef01) vbdef01 from hk_huiyuan_kadangan_jg ")
									.append("      where dr=0 ")
									.append("      group by pk_hk_huiyuan_kadangan ")
									.append(" ) lastdate ")
									.append(" on jg.pk_hk_huiyuan_kadangan = lastdate.pk_hk_huiyuan_kadangan and nvl(jg.vbdef01,'null') = nvl(lastdate.vbdef01,'null') ")
									
									.append(" where jgtemp.leavemoney != jg.jg_yue ")	// ���ϵͳ���  ������  NC�Ľ�����
									.append(" and ka.pk_hk_huiyuan_kadangan is not null ")
					;
					
					ArrayList<KadanganJGVO> list_jgyue_insert_2 = (ArrayList<KadanganJGVO>)getBaseDAO().executeQuery(query_jgyue_insert_2.toString(), new BeanListProcessor(KadanganJGVO.class));
					
					UFDateTime serverTime = new UFDateTime();
					
					// �� ��ʱ�� ת������ʽVO
					if(list_jgyue_insert_2.size()>0)
					{
						KadanganJGVO[] kadanganJGVO_insert_2 = new KadanganJGVO[list_jgyue_insert_2.size()];
						for( int i=0;i<list_jgyue_insert_2.size();i++ )
						{
							kadanganJGVO_insert_2[i] = list_jgyue_insert_2.get(i);
							kadanganJGVO_insert_2[i].setVbdef01( PuPubVO.getString_TrimZeroLenAsNull(serverTime) );	// ��ֵΪ  ��ǰ������ʱ��
						}
						this.getBaseDAO().insertVOArray(kadanganJGVO_insert_2);//���뿨�� ��ʽVO
					}
					
				}
				
				// ��� ��ʱ��
				getBaseDAO().deleteByClause(KadanganJGtempVO.class, " 1=1 ");
				
			}
			
		}
		
		// ȫ���������֮��
		// ����������Զ�����01 ~��Ϊ�գ�����������жϼ����� 
		this.getBaseDAO().executeUpdate(
				  " update hk_huiyuan_kadangan_jg " 
				+ " set vbdef01 = null "
				+ " where vbdef01 = '~' "
		);
		
	}
	
	/**
	 * ͬ�� ������Ϣ
	 */
	private void importHuanka(String[] pk_orgs,UFDate bdate,UFDate edate) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		// ���� ����ʱ��   ��ֵΪ   ��ǰ������ʱ�䣬 ȷ���ܽ������ʱ�� NC�ﶼ�п�
		edate = new UFDate();
		
		StringBuffer query_huanka = 
				new StringBuffer("select ")
						.append(" CardId ")
						.append(",MemberId ")
						.append(",SourceMemberid ")
						.append(",CardType  ")
						.append(",LeaveMoney ")
						.append(",CONVERT(VARCHAR(19),OutDate,120) OutDate ")
						.append(",OutPerson ")
						.append(",OutVpn ")
						.append(" from Dt_MakeCard ")
						.append(" where (1=1) ")
						.append(" and Status='����' ")
						.append(" and CONVERT(VARCHAR(19),isnull(OutDate,'1990-01-01 00:00:00'),120) between '").append( bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")
//						.append(" order by  ")
						;
		
		ArrayList<HuankaTempVO> list_huanka_query = (ArrayList<HuankaTempVO>)hkjt_hg_pub_session.executeQuery(query_huanka.toString(), new BeanListProcessor(HuankaTempVO.class));
		insertVOS(list_huanka_query);//��ҵ�����ݲ��뵽NC��ʱ��
		
		// ��ʱ�� ������ʽ�� ���˳� δ������� �������ݡ�
		StringBuffer query_huanka_insert = 
				new StringBuffer(" select ")
						.append(" temp.cardid ")
						.append(",temp.memberid ka_code ")
						.append(",temp.sourcememberid y_ka_code ")
						.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")// ��pk
						.append(",temp.leavemoney ka_je ")
						.append(",temp.outdate hk_time ")	// ����ʱ��
						.append(",temp.outperson czy ")
						.append(",temp.outvpn vdef01")
						.append(" from hk_huiyuan_huanka_temp temp ")
						.append(" left join hk_huiyuan_huanka huanka on huanka.cardid = temp.cardid and huanka.dr=0 ")
						.append(" left join hk_huiyuan_kadangan ka on ka.ka_code = temp.sourcememberid and ka.dr=0 ")
						.append(" where huanka.pk_hk_huiyuan_huanka is null ")
						.append(" order by temp.outdate ")	// �� ����ʱ������ ȷ�� ʱ�����  �Ƚ��д���
		;
		ArrayList<HuankaHVO> list_jgyue_insert = (ArrayList<HuankaHVO>)getBaseDAO().executeQuery(query_huanka_insert.toString(), new BeanListProcessor(HuankaHVO.class));
		
		if( list_jgyue_insert.size()>0 )
		{
			HuankaHVO[] huankaHVO_insert = new HuankaHVO[list_jgyue_insert.size()];
			huankaHVO_insert = list_jgyue_insert.toArray(huankaHVO_insert);
			
			// ��  �������  �ٴν���ð������  ȷ�� �� ʱ���Ⱥ�˳��  ������
			for(int i = 0 ; i < huankaHVO_insert.length-1 ; i++){
				for(int j = i+1 ; j < huankaHVO_insert.length ; j++){
					String hksj_i = huankaHVO_insert[i].getHk_time().toString();
					String hksj_j = huankaHVO_insert[j].getHk_time().toString();
					
					if(hksj_i.compareTo(hksj_j)>0)
					{// ���н���
						HuankaHVO temp = huankaHVO_insert[i];
						huankaHVO_insert[i] = huankaHVO_insert[j];
						huankaHVO_insert[j] = temp;
					}
				}
			}
			// END
			
			for(int i=0;i<huankaHVO_insert.length;i++)
			{
				huankaHVO_insert[i].setPk_group( AppContext.getInstance().getPkGroup() );	//����
				huankaHVO_insert[i].setPk_org( MAP_dian_corp.get(huankaHVO_insert[i].getVdef01()) );	// pk_rog
				huankaHVO_insert[i].setVbillcode( huankaHVO_insert[i].getKa_code() );
				huankaHVO_insert[i].setIbillstatus(3);	// Ĭ��Ϊ �ύ̬
				huankaHVO_insert[i].setCreator(HKJT_PUB.MAKER);
				huankaHVO_insert[i].setCreationtime(new UFDateTime());
				huankaHVO_insert[i].setDr(0);
				huankaHVO_insert[i].setDbilldate(PuPubVO.getUFDate(huankaHVO_insert[i].getHk_time()));
				
				String insert_pk = this.getBaseDAO().insertVO( huankaHVO_insert[i] );
				
				/////////////////////////////////////////////// �����д
				if(  null!=huankaHVO_insert[i].getKa_pk()
				  && !"~".equals(huankaHVO_insert[i].getKa_pk())	
				)
				{
					this.huanka_insert(huankaHVO_insert[i],insert_pk);
				}
				
			}
			
//			this.getBaseDAO().insertVOArray(huankaHVO_insert);//���뿨�� ��ʽVO
			
			// ��� ��ʱ��
			getBaseDAO().deleteByClause(HuankaTempVO.class, " 1=1 ");
			
		}
		
	}
	
	/**
	 * �����Ļ�д  insert
	 */
	public Object huanka_insert(HuankaHVO huankaHVO,String pk_huanka) throws Exception
	{
		
		if(  null!=huankaHVO.getKa_pk()
		  && !"~".equals(huankaHVO.getKa_pk())	
		)
		{
			//1����Ա������-����
			KadanganHKVO kadanganHKVO = new KadanganHKVO();
			kadanganHKVO.setPk_hk_huiyuan_kadangan( huankaHVO.getKa_pk() );	// ��pk
			kadanganHKVO.setKa_code_new(huankaHVO.getKa_code());	// �ֿ���
			kadanganHKVO.setKa_code_old(huankaHVO.getY_ka_code());	// ԭ����
			kadanganHKVO.setHk_time(huankaHVO.getHk_time());	// ����ʱ��
			kadanganHKVO.setKayue(huankaHVO.getKa_je());		// �����
			
			kadanganHKVO.setCsourcetypecode("HK23");	// ���ε�������
			kadanganHKVO.setCsourcebillid(pk_huanka);	// ���ε���id
			kadanganHKVO.setCsourcebillbid(pk_huanka);	// ���ε�����id
//					kadanganHKVO.setPk_hk_huiyuan_kadangan_hk(pkGenerator.generate());		// ��ֵpk
			kadanganHKVO.setDr(0);
			kadanganHKVO.setVbdef01(huankaHVO.getVdef01());	// �ŵ�
			
			this.getBaseDAO().insertVO(kadanganHKVO);	// ���� ������¼
			
			String ka_code   = huankaHVO.getKa_code();
			String y_ka_code = huankaHVO.getY_ka_code();
			
			// 2����Ա������
			getBaseDAO().executeUpdate(
					" update hk_huiyuan_kadangan " +
					" set " +
					" vbillcode = '" + ka_code + "' " +
					",ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " + 
					" and ka_code = '"+y_ka_code+"' " 
			);
			// 2����Ա����Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_kainfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			getBaseDAO().executeUpdate(	// Y_KA_CODE
					" update hk_huiyuan_kainfo_b " +
					" set " +
					" Y_KA_CODE   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and Y_KA_CODE = '"+y_ka_code+"' " 
			);
			
			// 3���ο���Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_cikainfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			getBaseDAO().executeUpdate(	// Y_KA_CODE
					" update hk_huiyuan_cikainfo_b " +
					" set " +
					" Y_KA_CODE   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and Y_KA_CODE = '"+y_ka_code+"' " 
			);
			
			// 4����Ʊ��Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_kaipiaoinfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			
			// 5���˵����������Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_srgk_hg_zhangdan_b " +
					" set " +
					" vbdef02   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and vbdef02 = '"+y_ka_code+"' " 
			);
		}
		
		return null;
	}
	
	/**
	 * �����Ļ�д  update
	 * ����  ��Ա����  ���桾��������ť�Ĳ���
	 * ֻ��  ��pk Ϊ��  �Ž��л���
	 */
	public Object huanka_update(HuankaHVO huankaHVO,String pk_huanka) throws Exception
	{
		
		if(  null==huankaHVO.getKa_pk()
		  || "~".equals(huankaHVO.getKa_pk())	
		)
		{
			String ka_code   = huankaHVO.getKa_code();
			String y_ka_code = huankaHVO.getY_ka_code();
			
			// ����ԭ����  �ҵ�  ������pk��
			ArrayList<KadanganHVO> query_kadangan = 
					(ArrayList<KadanganHVO>)new BaseDAO().retrieveByClause(KadanganHVO.class, " dr=0 and ka_code ='"+y_ka_code+"' ");
			;
			
			if( query_kadangan.size()<1 )
			{// ����Ҳ���  ������  �򷵻ؿ�
				return null;
			}
			
			KadanganHVO kadanganHVO = query_kadangan.get(0);
			
			//0�����µ� ��Ա����
			huankaHVO.setKa_pk( kadanganHVO.getPk_hk_huiyuan_kadangan() );
			huankaHVO.setDr(0);
			new BaseDAO().updateVO(huankaHVO);
			
			//1����Ա������-����
			KadanganHKVO kadanganHKVO = new KadanganHKVO();
			kadanganHKVO.setPk_hk_huiyuan_kadangan( kadanganHVO.getPk_hk_huiyuan_kadangan() );	// ��pk
			kadanganHKVO.setKa_code_new(huankaHVO.getKa_code());	// �ֿ���
			kadanganHKVO.setKa_code_old(huankaHVO.getY_ka_code());	// ԭ����
			kadanganHKVO.setHk_time(huankaHVO.getHk_time());	// ����ʱ��
			kadanganHKVO.setKayue(huankaHVO.getKa_je());		// �����
			
			kadanganHKVO.setCsourcetypecode("HK23");	// ���ε�������
			kadanganHKVO.setCsourcebillid(pk_huanka);	// ���ε���id
			kadanganHKVO.setCsourcebillbid(pk_huanka);	// ���ε�����id
//					kadanganHKVO.setPk_hk_huiyuan_kadangan_hk(pkGenerator.generate());		// ��ֵpk
			kadanganHKVO.setDr(0);
			kadanganHKVO.setVbdef01(huankaHVO.getVdef01());	// �ŵ�
			
			this.getBaseDAO().insertVO(kadanganHKVO);	// ���� ������¼
			
			
			// 2����Ա������
			getBaseDAO().executeUpdate(
					" update hk_huiyuan_kadangan " +
					" set " +
					" vbillcode = '" + ka_code + "' " +
					",ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " + 
					" and ka_code = '"+y_ka_code+"' " 
			);
			// 2����Ա����Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_kainfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			getBaseDAO().executeUpdate(	// Y_KA_CODE
					" update hk_huiyuan_kainfo_b " +
					" set " +
					" Y_KA_CODE   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and Y_KA_CODE = '"+y_ka_code+"' " 
			);
			
			// 3���ο���Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_cikainfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			getBaseDAO().executeUpdate(	// Y_KA_CODE
					" update hk_huiyuan_cikainfo_b " +
					" set " +
					" Y_KA_CODE   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and Y_KA_CODE = '"+y_ka_code+"' " 
			);
			
			// 4����Ʊ��Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_kaipiaoinfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			
			// 5���˵����������Ϣ
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_srgk_hg_zhangdan_b " +
					" set " +
					" vbdef02   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and vbdef02 = '"+y_ka_code+"' " 
			);
			
			return "ok";
		}
		
		return null;
	}
	
	/**
	 * �ο���Ϣ���ճ�ͬ��
	 */
	private void importCika_info(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate,String btime_str,String etime_str) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		
		ArrayList<String> list_date = this.getTimeDates(kainfo_bdate,kainfo_edate);
		/** 
		 * ��ÿ���ҵ�����ݣ���˫��ѭ��
		 * ��ѭ��  ��  ��
		 * ��ѭ��  ��  ��
		 */
		for(int org_i=0;org_i<pk_orgs.length;org_i++)
		{// ����  �� ѭ������
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
			String flag = MAP_dian_flag.get(dian);
			String db_str = MAP_dian_db.get(dian);	// ����Դ ��ʶ
		
			for (String yw_date : list_date) 
			{// ���� �� ѭ������
				
			
				//�жϣ��Ƿ����
				StringBuffer query_check = 
						new StringBuffer("select ckinfo.pk_hk_huiyuan_cikainfo ")
								.append(" from hk_huiyuan_cikainfo ckinfo ")
								.append(" where nvl(ckinfo.dr,0)=0 ")
								.append(" and ckinfo.pk_org = '"+corp+"' ")		// ��˾
								.append(" and substr(ckinfo.dbilldate,0,10) = '"+yw_date+"' ")	// ҵ������
				;
				Object check_pk = getBaseDAO().executeQuery(query_check.toString(),new ColumnProcessor());
				
				if(check_pk!=null) continue;	// ���������  ��������
				
				// ����ҵ������  ���� ����ʱ�� ����ʼ��������
				String[] yw_time = null;
				try
				{
					yw_time = this.getYwDate(PuPubVO.getUFDate(yw_date), flag);
					
				}catch(Exception ex)
				{
					ex.printStackTrace();
					yw_time = new String[2];
				}
				
				if( btime_str!=null && etime_str!=null )
				{// ��� ��̨�������  ������  ��ο�ʼ���ڡ���ν������ڣ�   �����õ���
					yw_time[0] = btime_str;
					yw_time[1] = etime_str;
				}
				
				if( yw_time==null || yw_time.length<2 ) continue;	// �Ҳ��� ҵ��ʱ�� ���˳�
				
				/**
				 * �ж����������Ƿ�Ӧ�� ���ֵ� ����
				 * ��� ���� ���� 2500 ������Ҫ���� ÿ2000��  ��һ����
				 */
				StringBuffer query_num = 
						new StringBuffer("select count(0) ")
								.append(" FROM Dt_TimesCard a ")
								.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
								.append(" WHERE (isnull(a.Status,'null') <> 'ɾ��') ")
								.append(" and a.VpnName ='").append(dian).append("' ")
								.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >  '"+yw_time[0]+"' ")
								.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <= '"+yw_time[1]+"' ")
				;
				ArrayList list_num = (ArrayList)hkjt_hg_pub_session.executeQuery(query_num.toString(),new ArrayListProcessor());
				if( list_num!=null && list_num.size()>0 )
				{
					int num = PuPubVO.getInteger_NullAs( ((Object[])list_num.get(0))[0] , 0 );	// ����
					
					if( num<=2500 )
					{// ���� С�� 2500   һ�����д���.
						
						// ��ֵ������
						StringBuffer query_cikainfo = 
								new StringBuffer("")
						// ���� ��ϸ
//										.append(" SELECT ")
//										.append(" '����' xmdl ")
//										.append(",'' xmlx ")
//										.append(",a.consumewaternum ")
//										.append(",a.timescardwaternum ")
//										.append(",a.BillId ")
//										.append(",CONVERT(VARCHAR(19),c.OperateDate,120) operateDate ")
//										.append(",b.NumberCount ")
//										.append(",null Price ")
//										.append(",f.Memberid ")
//										.append(",e.ItemId ")
//										.append(",e.ItemName ")
//										.append(",'' StartData ")
//										.append(",'' ExpData ")
//										.append(" from Dt_TimescardDetails a ")
//										.append(" LEFT JOIN goldenDatacenter.dbo.Sn_Consumesellog b ON a.ConsumeWaternum = b.WaterNum ")
//										.append(" LEFT JOIN goldenDatacenter.dbo.Sn_Bill c ON b.BillId = c.BillId ")
//										.append(" LEFT JOIN Dt_TimesCard e ON a.TimesCardWaternum = e.WaterNum ")
//										.append(" LEFT JOIN dbo.hk_member f ON e.MemberGuid = f.MemberGuid ")
//										.append(" where (1=1) ")
//										.append(" and SUBSTRING(a.Billid,15,3)='"+flag+"' ")
//										.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) >= '"+yw_time[0]+"' ")
//										.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) <  '"+yw_time[1]+"' ")
							// ���� ���ܣ��� �ο��š��˵���
							.append(" SELECT ")
							.append(" '����' xmdl ")
							.append(",'' xmlx ")
							.append(",'' consumewaternum ")
							.append(",a.timescardwaternum ")
							.append(",a.BillId ")
							.append(",case when CHARINDEX('��Ʊ',b.goodsname)=0 then '' else replace( b.goodsname,'��ʱ','') end vdef02 ")	// �˵���Ϣ
	//										.append(",case when CHARINDEX('��Ʊ',b.goodsname)=0 then '' else replace(replace( b.goodsname,'��ʱ',''),'��Ʊ','') end vdef02 ")	// �˵���Ϣ
							.append(",max(CONVERT(VARCHAR(19),c.OperateDate,120)) operateDate ")
							.append(",sum(b.NumberCount) NumberCount ")
							.append(",null Price ")
							.append(",max(f.Memberid) Memberid ")
							.append(",max(e.ItemId) itemid ")
							.append(",max(e.ItemName) ItemName ")
							.append(",max(CONVERT(VARCHAR(10),e.startdata,120)) startdata ")
							.append(",max(CONVERT(VARCHAR(10),e.ExpData,120)) expdata ")
							.append(",'' vdef03 ")	// ������
							.append(",'' vdef04 ")	// �Է�����
							.append(",'' vdef05 ")
							.append(" from Dt_TimescardDetails a ")
							.append(" LEFT JOIN "+db_str+".dbo.Sn_Consumesellog b ON a.ConsumeWaternum = b.WaterNum ")
							.append(" LEFT JOIN "+db_str+".dbo.Sn_Bill c ON b.BillId = c.BillId ")
							.append(" LEFT JOIN Dt_TimesCard e ON a.TimesCardWaternum = e.WaterNum ")
							.append(" LEFT JOIN dbo.hk_member f ON e.MemberGuid = f.MemberGuid ")
							.append(" where (1=1) ")
							.append(" and SUBSTRING(a.Billid,15,3)='"+flag+"' ")
							.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) >  '"+yw_time[0]+"' ")
							.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) <= '"+yw_time[1]+"' ")
							.append(" and e.WaterNum is not null ")		// ���������  �ο�����  ��2016��2��21��13:39:13��
							.append("  group by a.BillId,a.timescardwaternum ")	
							.append("          ,case when CHARINDEX('��Ʊ',b.goodsname)=0 then '' else replace( b.goodsname,'��ʱ','') end ")// �� �˵���Ϣ ����
	//										.append("          ,case when CHARINDEX('��Ʊ',b.goodsname)=0 then '' else replace(replace( b.goodsname,'��ʱ',''),'��Ʊ','') end ")// �� �˵���Ϣ ����
							.append(" union all ")
							// ��ֵ
							.append(" select ")
							.append(" case when a.IsSubTimes = '�为��' then '�为��' else '��ֵ' end xmdl ")
							.append(",case when a.IsSubTimes = '�为��' then '' else a.Type end xmlx ")
							.append(",'' ")
							.append(",a.WaterNum ")
							.append(",a.CwBillid ")
							.append(",'' ")
							.append(",CONVERT(VARCHAR(19),a.Operatedate,120) operatedate ")
							.append(",a.TotalTimes ")
							.append(",a.Price ")
							.append(",b.Memberid ")
							.append(",a.ItemId ")
							.append(",a.ItemName ")
							.append(",CONVERT(VARCHAR(10),a.startdata,120) startdata ")
							.append(",CONVERT(VARCHAR(10),a.ExpData,120) expdata ")
							.append(",case when a.IsSubTimes = '��' then 'Y' else '' end vdef03 ")	// ������
							.append(",'' vdef04 ")	// �Է�����
							.append(",'' vdef05 ")
							.append(" FROM Dt_TimesCard a ")
							.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
							.append(" WHERE (isnull(a.Status,'null') <> 'ɾ��') ")
							.append(" and a.VpnName ='").append(dian).append("' ")
							.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >  '"+yw_time[0]+"' ")
							.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <= '"+yw_time[1]+"' ")
							// �´ο�-��ֵ��ת��
							.append(" union all ")
							.append(" select ")
							.append(" '��ֵ' xmdl ")
							.append(",case when cz.CwBillId = 'ת��' then 'ת��' else '' end xmlx ")
							.append(",'' consumewaternum ")
							.append(",ck.TimesCardDefaultId + '@' + ka.memberid timescardwaternum ")
							.append(",cz.CwBillId BillId ")
							.append(",'' vdef02 ")
							.append(",CONVERT(VARCHAR(19),cz.CreateDate,120) operatedate ")
							.append(",cz.TotalCount NumberCount ")
							.append(",cz.TrucePrice Price ")
							.append(",ka.memberid Memberid ")
							.append(",ck.HelpName ItemId ")
							.append(",ck.DefaultTimesCardName ItemName ")
							.append(",CONVERT(VARCHAR(10),cz.StartTime,120) startdata ")
							.append(",CONVERT(VARCHAR(10),cz.EndTime,120) expdata ")
							.append(",case when ck.IsSubTimes = '��' then 'Y' else '' end vdef03  ")
							.append(",kaold.memberid vdef04 ")		// �Է�����
							.append(",'' vdef05 ")
							.append(" from M_TimesCardMember cz ")		// -- ���
							.append(" left join M_TimesCardDefault ck on cz.TimesCardDefaultId = ck.TimesCardDefaultId ")	// -- �ο���Ϣ
							.append(" left join dt_memberaccountinfo ka on cz.MemberGuid = ka.MemberGuid ")				// -- ��Ա�� 
							.append(" left join dt_memberaccountinfo kaold on cz.MemberGuidOld = kaold.MemberGuid ")	// -- ��Ա��old 
							.append(" where (1=1) ")
//							.append(" and cz.Status = '����' ")	// ״̬Ϊ����
							.append(" and CONVERT(VARCHAR(19),cz.CreateDate,120) >  '"+yw_time[0]+"' ")
							.append(" and CONVERT(VARCHAR(19),cz.CreateDate,120) <= '"+yw_time[1]+"' ")
							;
							if("��ɽ".equals(dian))
								query_cikainfo.append( " and (cz.CreateVpnName = '��ɽ' or (cz.CreateVpnName is null and ck.DefaultTimesCardName not like '����ר��%') ) " );
							else if("����".equals(dian))
								query_cikainfo.append( " and (cz.CreateVpnName = '����' or (cz.CreateVpnName is null and ck.DefaultTimesCardName like '����ר��%') ) " );
							else
								query_cikainfo.append( " and (cz.CreateVpnName = '").append(dian).append("' ) ");
							
							// �´ο�-���ѡ�������ת��
			  query_cikainfo.append(" union all ")
							.append(" select ")
							.append(" case when xf.History_Type in ('ת��','����') then '�为��' else '����' end xmdl ")		// ��� �� ת��or���������� ���� �ĳ� �为�� ��HK 2019��1��10��16:50:25��
							.append(",'' xmlx ")	// ��Ŀ���� ���� ��ֵ���ͣ�HK 2019��1��8��15:23:43��
							.append(",'' consumewaternum ")
							.append(",ck.TimesCardDefaultId + '@' + ka.memberid timescardwaternum ")
							.append(",xf.BillId BillId ")
							.append(",'' vdef02	")	// --�˵���Ϣ
							.append(",CONVERT(VARCHAR(19),xf.OperateDate,120) operateDate ")
							.append(",xf.UseCount NumberCount ")
							.append(",null Price ")
							.append(",ka.Memberid Memberid ")
							.append(",ck.HelpName ItemId ")
							.append(",ck.DefaultTimesCardName ItemName ")
							.append(",CONVERT(VARCHAR(10),cz.StartTime,120) startdata ")
							.append(",CONVERT(VARCHAR(10),cz.EndTime,120) expdata ")
							.append(",'' vdef03 ")
							.append(",'' vdef04 ")
							.append(",case when xf.History_Type='ת��' then 'ת��' " +
									"      when xf.History_Type='����' then '����'" +
									"      else '' end vdef05 ")	// // ��������Ŀ���� �����Զ���05 ��HK 2019��1��8��15:23:43��
							.append(" from M_TimesCardMemberHistory xf ")	// -- ���� 
							.append(" left join M_TimesCardMember cz on cz.uuid=xf.TimesCardMember_Uuid ")	// -- ��Σ����� ������ ��ε����� ������
							.append(" left join M_TimesCardDefault ck on cz.TimesCardDefaultId = ck.TimesCardDefaultId ")	// -- �ο���Ϣ 
							.append(" left join dt_memberaccountinfo ka on cz.MemberGuid = ka.MemberGuid ")				// -- ��Ա��
							.append(" left join dt_memberaccountinfo kades on xf.MemberGuid_Des = kades.MemberGuid ")	// -- ��Ա��des
							.append(" where (1=1) ")
							.append(" and xf.VpnName = '").append(dian).append("' ")
							.append(" and CONVERT(VARCHAR(19),xf.OperateDate,120) >  '"+yw_time[0]+"' ")
							.append(" and CONVERT(VARCHAR(19),xf.OperateDate,120) <= '"+yw_time[1]+"' ")
							;
						  	if("��ɽ".equals(dian))
								query_cikainfo.append( " and (xf.VpnName = '��ɽ' or (xf.VpnName is null and ck.DefaultTimesCardName not like '����ר��%') ) " );
							else if("����".equals(dian))
								query_cikainfo.append( " and (xf.VpnName = '����' or (xf.VpnName is null and ck.DefaultTimesCardName like '����ר��%') ) " );
							else
								query_cikainfo.append( " and (xf.VpnName = '").append(dian).append("' ) ");
						  	
//						System.out.println("===="+query_cikainfo);
						
						ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo.toString(), new BeanListProcessor(CikainfoTempVO.class));
						
						if(list_cikainfo_query.size()==0) continue;
						
						insertVOS(list_cikainfo_query);//��ҵ�����ݲ��뵽NC��ʱ��
						
						// ���� �������
						StringBuffer query_cikainfo_insert = 
								new StringBuffer(" select ")
										.append(" ckinfo.xmdl ")
										.append(",ckinfo.xmlx czlx ")
										.append(",ckinfo.consumewaternum ")
										.append(",ckinfo.timescardwaternum ")
										.append(",ckinfo.billid zdh ")
										.append(",ckinfo.operatedate ywsj ")
										.append(",ckinfo.numbercount shuliang ")
										.append(",ckinfo.price danjia ")
										.append(",ckinfo.memberid ka_code ")
										.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")
										.append(",ka.kabili ")
										.append(",ckinfo.startdata ")
										.append(",ckinfo.expdata ")
										.append(",ckinfo.itemid ")
										.append(",ckinfo.itemname ")
										.append(",ckinfo.vdef02 vbdef02 ")	// �˵���Ϣ
										.append(",ckinfo.vdef03 vbdef03 ")	// ������
										.append(",ckinfo.vdef04 y_ka_code ")// Դ����
										.append(",ckinfo.vdef05 xmlx ")		// ���м����Զ���5 ��ֵ�� ��Ŀ���ͣ�HK 2019��1��8��15:25:28��
										.append(",kaold.pk_hk_huiyuan_kadangan y_ka_pk ")// Դ��pk
										.append(" from hk_huiyuan_cikainfo_temp ckinfo ")
										.append(" left join hk_huiyuan_kadangan ka    on ckinfo.memberid = ka.ka_code    and nvl(ka.dr,0)=0 ")		// ��
										.append(" left join hk_huiyuan_kadangan kaold on ckinfo.vdef04   = kaold.ka_code and nvl(kaold.dr,0)=0 ")	// Դ��
										.append(" where (1=1) ")
										/**
										 * ����
										 * HK 2019��1��10��16:34:45
										 */
//										.append(" and ckinfo.memberid in ('GJ19ZSYK01000057','GJZXZSGBK000681') ")
										/***END***/
										.append(" order by ckinfo.operatedate ")
						;
						
						ArrayList<CikainfoBVO> list_cikainfo_insert = (ArrayList<CikainfoBVO>)getBaseDAO().executeQuery(query_cikainfo_insert.toString(), new BeanListProcessor(CikainfoBVO.class));
						
						if( list_cikainfo_insert.size()>0 )
						{
							CikainfoBVO[] cikainfoBVOs = new CikainfoBVO[list_cikainfo_insert.size()];
							cikainfoBVOs = list_cikainfo_insert.toArray(cikainfoBVOs);
							
							for( int i=0;i<cikainfoBVOs.length;i++ )
							{// ѭ������ �ӱ�VO
								
								cikainfoBVOs[i].setVrowno(i+1);
								
								if( "����".equals( cikainfoBVOs[i].getXmdl() ) )
								{
									if( !"ת��".equals( cikainfoBVOs[i].getXmlx() )
									 && !"����".equals( cikainfoBVOs[i].getXmlx() )
									)
									{
										cikainfoBVOs[i].setShuliang(	// ���� ȡ����
											UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
											);
									}
									
									// �ҵ� �ο���ֵ�� �����������и�ֵ�� ���� ����������ʱ�� ���и�ֵ��
									cikainfoBVOs[i].setKabili( null );
									cikainfoBVOs[i].setDanjia( null );
								}
								
								else if( "��ֵ".equals( cikainfoBVOs[i].getXmdl() )	)
								{
									if("���".equals( cikainfoBVOs[i].getCzlx() ))
									{
										cikainfoBVOs[i].setKabili( UFDouble.ZERO_DBL );	// ������ ������ֵ�� ����Ϊ0
									}
									
									cikainfoBVOs[i].setJine( // ��� = ������ * ���� * ����
											PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getKabili())
											.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
											.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getDanjia()))
											.setScale(2, UFDouble.ROUND_HALF_UP)
											);
									
									/**
									 * ת���  ��� ������ �� ���ۣ� ͨ�� ת���������
									 * HK 2019��1��10��17:00:35
									 */
									if("ת��".equals( cikainfoBVOs[i].getCzlx() ))
									{
										cikainfoBVOs[i].setKabili(null);
										cikainfoBVOs[i].setDanjia(null);
										cikainfoBVOs[i].setJine(null);
										cikainfoBVOs[i].setXmlx("ת��");
									}
									
								}
								
								else if( "�为��".equals( cikainfoBVOs[i].getXmdl() )
										)
								{// �为�� Ҫ��� ҵ��ϵͳ��������  �����������ۣ� �� ����������ʱ�� ���и�ֵ
									
									cikainfoBVOs[i].setKabili(null);
									cikainfoBVOs[i].setDanjia(null);
									
								}
								
							}
							
							// ���� ����VO
							CikainfoHVO cikainfoHVO = new CikainfoHVO();
							cikainfoHVO.setIbillstatus(-1);	// ����̬
							cikainfoHVO.setDbilldate(new UFDate( yw_date ));
							cikainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
							cikainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
							cikainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//����
							cikainfoHVO.setPk_org( corp );
							cikainfoHVO.setCreator(HKJT_PUB.MAKER);
							cikainfoHVO.setCreationtime(new UFDateTime());
							cikainfoHVO.setVbillcode( dian+"-"+yw_date );
							cikainfoHVO.setVbilltypecode("HK29");
							cikainfoHVO.setVdef01(dian);	// ҵ���ŵ�
							// �ۺ�VO
							CikainfoBillVO cikainfoBillVO = new CikainfoBillVO();
							cikainfoBillVO.setParent(cikainfoHVO);
							cikainfoBillVO.setChildrenVO(cikainfoBVOs);
							
							// ���� �ۺ�VO
							IHy_cikainfoMaintain itf = NCLocator.getInstance().lookup(IHy_cikainfoMaintain.class);
							itf.insert(new CikainfoBillVO[]{cikainfoBillVO}, null);
							
							// ��� ��ʱ��
							getBaseDAO().deleteByClause(CikainfoTempVO.class, " 1=1 ");
						}
						
					}
					
					else
					{// ���� ���� 2500�� ����Ҫ�𵥴��� 2000�� ��һ���������� �ο���Ŀ��������ȡ ���ҽ��д���
						
						{
							// �ȴ��� ���ѵ�����
							StringBuffer query_cikainfo_xf = 
								new StringBuffer("")
									// ���� ���ܣ��� �ο��š��˵���
									.append(" SELECT ")
									.append(" '����' xmdl ")
									.append(",'' xmlx ")
									.append(",'' consumewaternum ")
									.append(",a.timescardwaternum ")
									.append(",a.BillId ")
									.append(",case when CHARINDEX('��Ʊ',b.goodsname)=0 then '' else replace( b.goodsname,'��ʱ','') end vdef02 ")	// �˵���Ϣ
									.append(",max(CONVERT(VARCHAR(19),c.OperateDate,120)) operateDate ")
									.append(",sum(b.NumberCount) NumberCount ")
									.append(",null Price ")
									.append(",max(f.Memberid) Memberid ")
									.append(",max(e.ItemId) itemid ")
									.append(",max(e.ItemName) ItemName ")
									.append(",max(CONVERT(VARCHAR(10),e.startdata,120)) startdata ")
									.append(",max(CONVERT(VARCHAR(10),e.ExpData,120)) expdata ")
									.append(",'' vdef03 ")	// ������
									.append(" from Dt_TimescardDetails a ")
									.append(" LEFT JOIN "+db_str+".dbo.Sn_Consumesellog b ON a.ConsumeWaternum = b.WaterNum ")
									.append(" LEFT JOIN "+db_str+".dbo.Sn_Bill c ON b.BillId = c.BillId ")
									.append(" LEFT JOIN Dt_TimesCard e ON a.TimesCardWaternum = e.WaterNum ")
									.append(" LEFT JOIN dbo.hk_member f ON e.MemberGuid = f.MemberGuid ")
									.append(" where (1=1) ")
									.append(" and SUBSTRING(a.Billid,15,3)='"+flag+"' ")
									.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) >  '"+yw_time[0]+"' ")
									.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) <= '"+yw_time[1]+"' ")
									.append(" and e.WaterNum is not null ")		// ���������  �ο�����  ��2016��2��21��13:39:13��
									.append("  group by a.BillId,a.timescardwaternum ")	
									.append("          ,case when CHARINDEX('��Ʊ',b.goodsname)=0 then '' else replace( b.goodsname,'��ʱ','') end ")// �� �˵���Ϣ ����
							;
							
							ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo_xf.toString(), new BeanListProcessor(CikainfoTempVO.class));
							
							if(list_cikainfo_query.size()==0) continue;
							
							insertVOS(list_cikainfo_query);//��ҵ�����ݲ��뵽NC��ʱ��
							
							// ���� �������
							StringBuffer query_cikainfo_insert = 
									new StringBuffer(" select ")
											.append(" ckinfo.xmdl ")
											.append(",ckinfo.xmlx czlx ")
											.append(",ckinfo.consumewaternum ")
											.append(",ckinfo.timescardwaternum ")
											.append(",ckinfo.billid zdh ")
											.append(",ckinfo.operatedate ywsj ")
											.append(",ckinfo.numbercount shuliang ")
											.append(",ckinfo.price danjia ")
											.append(",ckinfo.memberid ka_code ")
											.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")
											.append(",ka.kabili ")
											.append(",ckinfo.startdata ")
											.append(",ckinfo.expdata ")
											.append(",ckinfo.itemid ")
											.append(",ckinfo.itemname ")
											.append(",ckinfo.vdef02 vbdef02 ")	// �˵���Ϣ
											.append(",ckinfo.vdef03 vbdef03 ")	// ������
											.append(" from hk_huiyuan_cikainfo_temp ckinfo ")
											.append(" left join hk_huiyuan_kadangan ka on ckinfo.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")
											.append(" where (1=1) ")
											.append(" order by ckinfo.operatedate ")
							;
							
							ArrayList<CikainfoBVO> list_cikainfo_insert = (ArrayList<CikainfoBVO>)getBaseDAO().executeQuery(query_cikainfo_insert.toString(), new BeanListProcessor(CikainfoBVO.class));
							
							if( list_cikainfo_insert.size()>0 )
							{
								CikainfoBVO[] cikainfoBVOs = new CikainfoBVO[list_cikainfo_insert.size()];
								cikainfoBVOs = list_cikainfo_insert.toArray(cikainfoBVOs);
								
								for( int i=0;i<cikainfoBVOs.length;i++ )
								{// ѭ������ �ӱ�VO
									
									cikainfoBVOs[i].setVrowno(i+1);
									
									if("����".equals( cikainfoBVOs[i].getXmdl() ))
									{
										cikainfoBVOs[i].setShuliang(	// ���� ȡ����
												UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
												);
										
										// �ҵ� �ο���ֵ�� �����������и�ֵ�� ���� ����������ʱ�� ���и�ֵ��
										cikainfoBVOs[i].setKabili( null );
										cikainfoBVOs[i].setDanjia(null);
									}
									
									else if( "��ֵ".equals( cikainfoBVOs[i].getXmdl() )	)
									{
										if("���".equals( cikainfoBVOs[i].getCzlx() ))
										{
											cikainfoBVOs[i].setKabili( UFDouble.ZERO_DBL );	// ������ ������ֵ�� ����Ϊ0
										}
										
										cikainfoBVOs[i].setJine( // ��� = ������ * ���� * ����
												PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getKabili())
												.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
												.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getDanjia()))
												.setScale(2, UFDouble.ROUND_HALF_UP)
												);
									}
									
									else if( "�为��".equals( cikainfoBVOs[i].getXmdl() )
											)
									{// �为�� Ҫ��� ҵ��ϵͳ��������  �����������ۣ� �� ����������ʱ�� ���и�ֵ
										
										cikainfoBVOs[i].setKabili(null);
										cikainfoBVOs[i].setDanjia(null);
									}
								}
								
								// ���� ����VO
								CikainfoHVO cikainfoHVO = new CikainfoHVO();
								cikainfoHVO.setIbillstatus(-1);	// ����̬
								cikainfoHVO.setDbilldate(new UFDate( yw_date ));
								cikainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
								cikainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
								cikainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//����
								cikainfoHVO.setPk_org( corp );
								cikainfoHVO.setCreator(HKJT_PUB.MAKER);
								cikainfoHVO.setCreationtime(new UFDateTime());
								cikainfoHVO.setVbillcode( dian+"-"+yw_date );
								cikainfoHVO.setVbilltypecode("HK29");
								cikainfoHVO.setVdef01(dian);	// ҵ���ŵ�
								// �ۺ�VO
								CikainfoBillVO cikainfoBillVO = new CikainfoBillVO();
								cikainfoBillVO.setParent(cikainfoHVO);
								cikainfoBillVO.setChildrenVO(cikainfoBVOs);
								
								// ���� �ۺ�VO
								IHy_cikainfoMaintain itf = NCLocator.getInstance().lookup(IHy_cikainfoMaintain.class);
								itf.insert(new CikainfoBillVO[]{cikainfoBillVO}, null);
								
								// ��� ��ʱ��
								getBaseDAO().deleteByClause(CikainfoTempVO.class, " 1=1 ");
							}
						}
						
						
						
						// ȡ�� ���漰�� �ο���Ŀ
						StringBuffer query_itemname = 
								new StringBuffer("select distinct a.ItemName ")
										.append(" FROM Dt_TimesCard a ")
										.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
										.append(" WHERE (isnull(a.Status,'null') <> 'ɾ��') ")
										.append(" and a.VpnName ='").append(dian).append("' ")
										.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >  '"+yw_time[0]+"' ")
										.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <= '"+yw_time[1]+"' ")
						;
						ArrayList list_itemname = (ArrayList)hkjt_hg_pub_session.executeQuery(query_itemname.toString(),new ArrayListProcessor());
						
						if( list_itemname!=null )
						{
							for( int itemname_i=0;itemname_i<list_itemname.size();itemname_i++ )
							{
								String itemname = PuPubVO.getString_TrimZeroLenAsNull( ((Object[])list_itemname.get(itemname_i))[0] );
								
								StringBuffer query_cikainfo_cz = 
										new StringBuffer("")
											.append(" select ")
											.append(" case when a.IsSubTimes = '�为��' then '�为��' else '��ֵ' end xmdl ")
											.append(",case when a.IsSubTimes = '�为��' then '' else a.Type end xmlx ")
											.append(",'' consumewaternum ")
											.append(",a.WaterNum timescardwaternum ")
											.append(",a.CwBillid BillId ")
											.append(",'' vdef02 ")
											.append(",CONVERT(VARCHAR(19),a.Operatedate,120) operateDate ")
											.append(",a.TotalTimes NumberCount ")
											.append(",a.Price ")
											.append(",b.Memberid ")
											.append(",a.ItemId ")
											.append(",a.ItemName ")
											.append(",CONVERT(VARCHAR(10),a.startdata,120) startdata ")
											.append(",CONVERT(VARCHAR(10),a.ExpData,120) expdata ")
											.append(",case when a.IsSubTimes = '��' then 'Y' else '' end vdef03 ")	// ������
											.append(" FROM Dt_TimesCard a ")
											.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
											.append(" WHERE (isnull(a.Status,'null') <> 'ɾ��') ")
											.append(" and a.VpnName ='").append(dian).append("' ")
											.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >  '"+yw_time[0]+"' ")
											.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <= '"+yw_time[1]+"' ")
											.append(" and a.ItemName ='").append(itemname).append("' ")		// �� ��Ŀ����  ��ѯ
								;
								
								ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo_cz.toString(), new BeanListProcessor(CikainfoTempVO.class));
								
								if(list_cikainfo_query.size()==0) continue;
								
								insertVOS(list_cikainfo_query);//��ҵ�����ݲ��뵽NC��ʱ��
								
								// ���� �������
								StringBuffer query_cikainfo_insert = 
										new StringBuffer(" select ")
												.append(" ckinfo.xmdl ")
												.append(",ckinfo.xmlx czlx ")
												.append(",ckinfo.consumewaternum ")
												.append(",ckinfo.timescardwaternum ")
												.append(",ckinfo.billid zdh ")
												.append(",ckinfo.operatedate ywsj ")
												.append(",ckinfo.numbercount shuliang ")
												.append(",ckinfo.price danjia ")
												.append(",ckinfo.memberid ka_code ")
												.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")
												.append(",ka.kabili ")
												.append(",ckinfo.startdata ")
												.append(",ckinfo.expdata ")
												.append(",ckinfo.itemid ")
												.append(",ckinfo.itemname ")
												.append(",ckinfo.vdef02 vbdef02 ")	// �˵���Ϣ
												.append(",ckinfo.vdef03 vbdef03 ")	// ������
												.append(" from hk_huiyuan_cikainfo_temp ckinfo ")
												.append(" left join hk_huiyuan_kadangan ka on ckinfo.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")
												.append(" where (1=1) ")
												.append(" order by ckinfo.operatedate ")
								;
								
								ArrayList<CikainfoBVO> list_cikainfo_insert = (ArrayList<CikainfoBVO>)getBaseDAO().executeQuery(query_cikainfo_insert.toString(), new BeanListProcessor(CikainfoBVO.class));
								
								CikainfoBVO[] cikainfoBVOs_ALL = new CikainfoBVO[list_cikainfo_insert.size()];
								cikainfoBVOs_ALL = list_cikainfo_insert.toArray(cikainfoBVOs_ALL);
								
								// ���� �ֶ��ٵ�
								int bill_row_MaxCount = 2000;	// ÿ�� �� �������
								int row_count = list_cikainfo_insert.size();	// ������
								int bill_count = row_count/bill_row_MaxCount + ( row_count%bill_row_MaxCount ==0 ? 0 : 1) ;	// �ܵ���
								int lastBill_row_count = list_cikainfo_insert.size()%bill_row_MaxCount;
								
								for( int bill_i=1;bill_i<=bill_count;bill_i++ )
								{
									int bc_row_count = bill_row_MaxCount;	// ����ѭ����  ����
									if( bill_i == bill_count )
									{// ��� ����ѭ�� Ϊ���һ�Σ����� ���һ���� ���� ��Ϊ0�� ��ȡ���һ��������
										bc_row_count = lastBill_row_count;
									}
									
									CikainfoBVO[] cikainfoBVOs = new CikainfoBVO[bc_row_count];
									
									for( int row_i=0;row_i<bc_row_count;row_i++ )
									{// �� ��VO ��ֵ
									 // ������ ��Ӧ�� ����
										cikainfoBVOs[row_i] = cikainfoBVOs_ALL[ bill_row_MaxCount*(bill_i-1)+row_i ];
										
										cikainfoBVOs[row_i].setVrowno(row_i+1);
										
										if("����".equals( cikainfoBVOs[row_i].getXmdl() ))
										{
											cikainfoBVOs[row_i].setShuliang(	// ���� ȡ����
													UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getShuliang()))
													);
											
											// �ҵ� �ο���ֵ�� �����������и�ֵ�� ���� ����������ʱ�� ���и�ֵ��
											cikainfoBVOs[row_i].setKabili( null );
											cikainfoBVOs[row_i].setDanjia(null);
										}
										
										else if( "��ֵ".equals( cikainfoBVOs[row_i].getXmdl() )	)
										{
											if("���".equals( cikainfoBVOs[row_i].getCzlx() ))
											{
												cikainfoBVOs[row_i].setKabili( UFDouble.ZERO_DBL );	// ������ ������ֵ�� ����Ϊ0
											}
											
											cikainfoBVOs[row_i].setJine( // ��� = ������ * ���� * ����
															  PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getKabili())
													.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getShuliang()))
													.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getDanjia()))
													.setScale(2, UFDouble.ROUND_HALF_UP)
													);
										}
										
										else if( "�为��".equals( cikainfoBVOs[row_i].getXmdl() )
												)
										{// �为�� Ҫ��� ҵ��ϵͳ��������  �����������ۣ� �� ����������ʱ�� ���и�ֵ
											
											cikainfoBVOs[row_i].setKabili(null);
											cikainfoBVOs[row_i].setDanjia(null);
										}
										
									}
									
									// ���� ����VO
									CikainfoHVO cikainfoHVO = new CikainfoHVO();
									cikainfoHVO.setIbillstatus(-1);	// ����̬
									cikainfoHVO.setDbilldate(new UFDate( yw_date ));
									cikainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
									cikainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
									cikainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//����
									cikainfoHVO.setPk_org( corp );
									cikainfoHVO.setCreator(HKJT_PUB.MAKER);
									cikainfoHVO.setCreationtime(new UFDateTime());
									cikainfoHVO.setVbillcode( dian+"-"+yw_date+"-"+itemname+"-"+this.buwei(bill_i) );
									cikainfoHVO.setVbilltypecode("HK29");
									cikainfoHVO.setVdef01(dian);	// ҵ���ŵ�
									// �ۺ�VO
									CikainfoBillVO cikainfoBillVO = new CikainfoBillVO();
									cikainfoBillVO.setParent(cikainfoHVO);
									cikainfoBillVO.setChildrenVO(cikainfoBVOs);
									
									// ���� �ۺ�VO
									IHy_cikainfoMaintain itf = NCLocator.getInstance().lookup(IHy_cikainfoMaintain.class);
									itf.insert(new CikainfoBillVO[]{cikainfoBillVO}, null);
									
									
								}
								
								// ��� ��ʱ��
								getBaseDAO().deleteByClause(CikainfoTempVO.class, " 1=1 ");
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
				
	}
	
	
	/**
	 * ͬ�� ��� �ο����
	 */
	private void importJGyue_ck(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		if(kainfo_bdate==null) kainfo_bdate = new UFDate("1990-01-01");
		if(kainfo_edate==null) kainfo_edate = new UFDate("2990-12-31");
		
		for( int org_i=0;org_i<pk_orgs.length;org_i++ )
		{// ��˾ѭ��
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
//			String flag = MAP_dian_flag.get(dian);
		
				
			StringBuffer query_jgckyue = // �鿴��� �ο����
					new StringBuffer("SELECT ")
							.append(" b.Memberid ")
							.append(",CONVERT(VARCHAR(19),a.LastCountTime,120) LastCountTime ")
							.append(",a.ItemId ")
							.append(",a.ItemName ")
							.append(",CONVERT(VARCHAR(10),a.startdata,120) StartData ")
							.append(",CONVERT(VARCHAR(10),a.ExpData,120) ExpData ")
							.append(",a.WaterNum ")
							.append(",a.Times ")
							.append(" from Dt_TimesCard a ")
							.append(" left JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
							.append(" where (1=1) ")
							.append(" and b.coach = '").append( dian ).append("' ")	// ��
							.append(" and CONVERT(VARCHAR(19),isnull(a.LastCountTime,'1990-01-01 00:00:00'),120) between '").append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// ʱ��
			;
			
			ArrayList<KadanganJGCKtempVO> list_jgckyue_query = (ArrayList<KadanganJGCKtempVO>)hkjt_hg_pub_session.executeQuery(query_jgckyue.toString(), new BeanListProcessor(KadanganJGCKtempVO.class));
			insertVOS(list_jgckyue_query);//��ҵ�����ݲ��뵽NC��ʱ��
			
			// ��ʱ�� ������ʽ�� ���˳� δ������� ��� �ο���
			StringBuffer query_jgckyue_insert = 
					new StringBuffer("SELECT ")
							.append(" ka.pk_hk_huiyuan_kadangan ")
							.append(",jgcktemp.LastCountTime yu_time ")
							.append(",jgcktemp.Times yunum ")
							.append(",jgcktemp.ItemId ")
							.append(",jgcktemp.ItemName ")
							.append(",jgcktemp.WaterNum timescardwaternum ")
							.append(",jgcktemp.StartData ")
							.append(",jgcktemp.ExpData ")
							.append(",0 dr")
							.append(" from hk_huiyuan_kadangan_jgck_temp jgcktemp ")
							.append(" left join hk_huiyuan_kadangan ka on jgcktemp.memberid = ka.ka_code and ka.dr=0 ")
							.append(" left join hk_huiyuan_kadangan_ckjg jgck on ka.pk_hk_huiyuan_kadangan = jgck.pk_hk_huiyuan_kadangan and jgck.dr=0 " +
											"and jgck.timescardwaternum = jgcktemp.WaterNum " + 
											"and nvl(jgck.yu_time,'null') = nvl(jgcktemp.LastCountTime,'null') ")
							.append(" where jgck.pk_hk_huiyuan_kadangan_ckjg is null ")
							.append(" and ka.pk_hk_huiyuan_kadangan is not null ")
			;
			ArrayList<KadanganCKJGVO> list_jgckyue_insert = (ArrayList<KadanganCKJGVO>)getBaseDAO().executeQuery(query_jgckyue_insert.toString(), new BeanListProcessor(KadanganCKJGVO.class));
			
			// �� ��ʱ�� ת������ʽVO
			if(list_jgckyue_insert.size()>0)
			{
				this.getBaseDAO().insertVOList(list_jgckyue_insert);//���뿨�� ��ʽVO list
			}
			
			// ��� ��ʱ��
			getBaseDAO().deleteByClause(KadanganJGCKtempVO.class, " 1=1 ");
			
		}
		
	}
	
	/**
	 * �����ֹ�ץȡ ��Ա������
	 */
	public Object insertKadangan(String ka_code) throws Exception
	{
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{Plugin_Key});
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		// ��ѯ ������Ϣ
		StringBuffer query_kaika = 
				new StringBuffer("select memberid ")
						.append(",CardType cardtypeid ")
						.append(",OutVpn coach ")
						.append(",CONVERT(VARCHAR(19),OutDate,120) lastenterdate ")
						.append(" from Dt_MakeCard ")
						.append(" where (1=1) ")
						.append(" and Status = '�ѷ�' ")
						.append(" and memberid = '").append(ka_code).append("' ")	// sql server Ĭ�ϲ����ִ�Сд ��������ִ�Сд����Ҫ�ڵȺ�ǰ����collate Chinese_PRC_CS_AS_WS��
		;
		
		ArrayList<KadanganTempVO> list_huiyuaka_query = (ArrayList<KadanganTempVO>)hkjt_hg_pub_session.executeQuery(query_kaika.toString(), new BeanListProcessor(KadanganTempVO.class));
		
		insertVOS(list_huiyuaka_query);//�����������뵽NC��ʱ��
		
		// ��ʱ�� ������ʽ�� ���˳� δ������� ��������
		StringBuffer query_huiyuaka_insert = 
				new StringBuffer("select hk_huiyuan_kadangan_temp.memberid ")
						.append(",hk_huiyuan_kadangan_temp.coach ")
						.append(",hk_huiyuan_kaxing.pk_hk_huiyuan_kaxing cardtypeid ")
						.append(",hk_huiyuan_kaxing.kabili ")
						.append(" from hk_huiyuan_kadangan_temp ")
						.append(" left join hk_huiyuan_kadangan on hk_huiyuan_kadangan_temp.memberid = hk_huiyuan_kadangan.ka_code and nvl(hk_huiyuan_kadangan.dr,0)=0 ")	// ������
						.append(" left join hk_huiyuan_kaxing on hk_huiyuan_kadangan_temp.cardtypeid = hk_huiyuan_kaxing.kaxing_code and nvl(hk_huiyuan_kaxing.dr,0)=0 ")	// ����
						.append(" where hk_huiyuan_kadangan.pk_hk_huiyuan_kadangan is null ")
		;
		ArrayList<KadanganTempVO> list_huiyuaka_insert = (ArrayList<KadanganTempVO>)getBaseDAO().executeQuery(query_huiyuaka_insert.toString(), new BeanListProcessor(KadanganTempVO.class));

		// �� ��ʱ�� ת������ʽVO
		if(list_huiyuaka_insert.size()>0)
		{
//			KadanganHVO[] kadanganHVO_insert = new KadanganHVO[list_huiyuaka_insert.size()];
			
			KadanganTempVO tempVO = list_huiyuaka_insert.get(0);
			
			KadanganHVO kadanganHVO_insert = new KadanganHVO();
			kadanganHVO_insert.setKa_code( tempVO.getMemberid() );
			kadanganHVO_insert.setKa_name( tempVO.getMembername() );
			kadanganHVO_insert.setPk_hk_huiyuan_kaxing( tempVO.getCardtypeid() );	// ����
			
			kadanganHVO_insert.setKabili( tempVO.getKabili() );
			kadanganHVO_insert.setKastatus("����");
			
			kadanganHVO_insert.setPk_group( AppContext.getInstance().getPkGroup() );	//����
			kadanganHVO_insert.setPk_org( MAP_dian_corp.get(tempVO.getCoach()) );	// pk_rog
			kadanganHVO_insert.setVbillcode( kadanganHVO_insert.getKa_code() );
			kadanganHVO_insert.setIbillstatus(-1);
			kadanganHVO_insert.setCreator(HKJT_PUB.MAKER);
			kadanganHVO_insert.setCreationtime(new UFDateTime());
			kadanganHVO_insert.setDr(0);
			kadanganHVO_insert.setDbilldate(new UFDate());
			kadanganHVO_insert.setVdef01( tempVO.getCoach() );//���� ��
			
			String pk = this.getBaseDAO().insertVO(kadanganHVO_insert);//���뿨�� ��ʽVO
			
			kadanganHVO_insert.setPk_hk_huiyuan_kadangan(pk);
			
			// ��� ��ʱ��
			getBaseDAO().deleteByClause(KadanganTempVO.class, " 1=1 ");
			
			return kadanganHVO_insert;
		}
		
		return null;
			
	}
	
	
	/**
	 * �����ֹ�ץȡ �ο�info
	 */
	public CikainfoBVO getCika(String waterNum) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		StringBuffer query_cikainfo = new StringBuffer("")
				// ��ֵ
				.append(" select ")
				.append(" case when a.IsSubTimes = '�为��' then '�为��' else '��ֵ' end xmdl ")
				.append(",case when a.IsSubTimes = '�为��' then '' else a.Type end xmlx ")
				.append(",a.WaterNum timescardwaternum ")
				.append(",a.CwBillid billid ")
				.append(",CONVERT(VARCHAR(19),a.Operatedate,120) operatedate ")
				.append(",a.TotalTimes NumberCount ")
				.append(",a.Price ")
				.append(",b.Memberid ")
				.append(",a.ItemId ")
				.append(",a.ItemName ")
				.append(",CONVERT(VARCHAR(10),a.startdata,120) startdata ")
				.append(",CONVERT(VARCHAR(10),a.ExpData,120) expdata ")
				.append(" FROM Dt_TimesCard a ")
				.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
				.append(" WHERE (isnull(a.Status,'null') <> 'ɾ��') ")
				.append(" and a.WaterNum = '").append(waterNum).append("' ")
//				.append(" and a.VpnName ='").append(dian).append("' ")
//				.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >= '"+yw_time[0]+"' ")
//				.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <  '"+yw_time[1]+"' ")
		;
		
		ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo.toString(), new BeanListProcessor(CikainfoTempVO.class));
		
		if(list_cikainfo_query.size()==0) return null;
		
		insertVOS(list_cikainfo_query);//��ҵ�����ݲ��뵽NC��ʱ��
		
		// ���� �������
		StringBuffer query_cikainfo_insert = 
		new StringBuffer(" select ")
				.append("  ckinfo.xmdl ")
				.append(",ckinfo.xmlx czlx ")
				.append(",ckinfo.consumewaternum ")
				.append(",ckinfo.timescardwaternum ")
				.append(",ckinfo.billid zdh ")
				.append(",ckinfo.operatedate ywsj ")
				.append(",ckinfo.numbercount shuliang ")
				.append(",ckinfo.price danjia ")
				.append(",ckinfo.memberid ka_code ")
				.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")
				.append(",ka.kabili ")
				.append(",ckinfo.startdata ")
				.append(",ckinfo.expdata ")
				.append(",ckinfo.itemid ")
				.append(",ckinfo.itemname ")
				.append(" from hk_huiyuan_cikainfo_temp ckinfo ")
				.append(" left join hk_huiyuan_kadangan ka on ckinfo.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")
				.append(" where (1=1) ")
				.append(" order by ckinfo.operatedate ")
		;
		
		ArrayList<CikainfoBVO> list_cikainfo_insert = (ArrayList<CikainfoBVO>)getBaseDAO().executeQuery(query_cikainfo_insert.toString(), new BeanListProcessor(CikainfoBVO.class));
		
		if( list_cikainfo_insert.size()>0 )
		{
			CikainfoBVO cikainfoBVO = list_cikainfo_insert.get(0);
			
			if("���".equals( cikainfoBVO.getCzlx() ))
			{
				cikainfoBVO.setKabili( UFDouble.ZERO_DBL );	// ������ ������ֵ�� ����Ϊ0
			}
			
			cikainfoBVO.setJine( // ��� = ������ * ���� * ����
					PuPubVO.getUFDouble_NullAsZero(cikainfoBVO.getKabili())
					.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVO.getShuliang()))
					.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVO.getDanjia()))
					.setScale(2, UFDouble.ROUND_HALF_UP)
			);
			
			return cikainfoBVO;
		}
		
		return null;
	}
	
	
	/**
	 * ���ڵĴ���, ���� ��ʼ��������, ���� Ҫÿһ��������б�
	 */
	public ArrayList<String> getTimeDates(Object beginTime,Object endTime){
		UFDate beginDate=null;
		UFDate endDate=null;
		
		UFDateTime CurrentTime = new UFDateTime(new Date(TimeService.getInstance().getTime()));
		
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//��ʼ��������Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(endTime.toString());
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//��ʼ��Ϊ�գ�����Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=CurrentTime.getDate().getDateBefore(1);
		}else{//��ʼ������Ϊ��//��ʼΪ�գ�������Ϊ��
			beginDate=CurrentTime.getDate().getDateBefore(1);
			endDate=beginDate;
		}
		ArrayList<String> datesList=new ArrayList<String>();
		for (int i = 0; i <=UFDate.getDaysBetween(beginDate, endDate); i++) {
			String dateStr=beginDate.getDateAfter(i).toString().substring(0,10);
			if(!datesList.contains(dateStr))
			datesList.add(dateStr);
		}
		
		return datesList;
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
	
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}

	private String buwei(int i)
	{
		String result = "";
		
		if(i<10)
			result = "00"+i;
		else if(i<100)
			result = "0"+i;
		else
			result = ""+i;
		
		return result;
	}
	
	/**
	 * �ӽ���� ȡ ����ǰ�� ��Ա��ֵ��Ϣ
	 * 2016��11��1��09:52:01
	 */
	public Object queryJGchongzhi(String ka_code,String sj,String dian,Object other) throws Exception
	{
		int beforDay = 3;	// ȡ ����ǰ������
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		if( sj==null )
		{
			sj = new UFDate().getDateBefore(beforDay).toString();
		}
		
		StringBuffer query_JGinfo = 
				new StringBuffer("")
						// ��ֵ������
						.append(" SELECT ")
						.append(" CONVERT(VARCHAR(19),b.FeeInDate,120) FeeInDate, ")	// ҵ��ʱ��
						.append(" b.TrueMoney ")		// ʵ��
						.append("FROM hk_member a ")
						.append("INNER JOIN Dt_MemberCardComeIn b ON a.MemberGuid = b.MemberGuid ")
						.append("WHERE 1=1 ")
						.append(" and b.FeeType in ( '��ֵ' ) ")
						.append(" and Remark<>'�ǿ���' ")
						.append(" and b.VpnName = '"+dian+"' ")		// ��
						.append(" and a.Memberid = '"+ka_code+"'")	// ����
						.append(" and CONVERT(VARCHAR(19),b.FeeInDate,120) > '"+sj+"' ")	// ʱ��
						;
		ArrayList list_JGinfo = (ArrayList)hkjt_hg_pub_session.executeQuery(query_JGinfo.toString(), new ArrayListProcessor());
		
		if( list_JGinfo!=null && list_JGinfo.size()>0 )
		{
			String result = "";
			UFDouble total = UFDouble.ZERO_DBL;		// �ۼ�ʵ�ʽ��
			for(int i=0;i<list_JGinfo.size();i++)
			{
				Object[] values = (Object[])list_JGinfo.get(i);
				result += values[1] + "    " + values[0] + "\r\n";
				total = total.add( PuPubVO.getUFDouble_NullAsZero( values[1] ) );
			}
			return new Object[]{result,total};
		}
		
		return null;
	}
}
