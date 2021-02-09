package nc.bs.hkjt.srgk.huiguan.zhangdan.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.TimeService;
import nc.bs.hrss.pub.Logger;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHg_zhangdanMaintain;
import nc.itf.org.IOrgVersionQryService;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.jdbc.framework.processor.VectorProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.srgk.huiguan.othersystem.BanCiVO;
import nc.vo.hkjt.srgk.huiguan.othersystem.BanCi_TempVO;
import nc.vo.hkjt.srgk.huiguan.othersystem.CaiWuChongZhiVO;
import nc.vo.hkjt.srgk.huiguan.othersystem.FenQuVO;
import nc.vo.hkjt.srgk.huiguan.othersystem.ZhangDanB_TempVO;
import nc.vo.hkjt.srgk.huiguan.othersystem.ZhangDanH_TempVO;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFTime;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * ������̨����ͬ������
 * <p>
 * @author zhangjc
 * @date 2015-06-23
 * 1.���
 * ���漰������ϵͳ���ݿ��(sqlserver)
 * --������ Dt_CardInfo,P_PartitionGoods,P_PartitionMember,Dt_MemberCardComeIn
 * --��ݱ�Dt_ChangeClass,Sn_Bill, Sn_BillHistory,Sn_Consumesellog,KF_HouseInfo,Dt_GoodCatalog,Sn_JiShi
 *
 */
public class ImpZhangDanBill implements IBackgroundWorkPlugin {
	public final static int  DECIMALBIT =2;//С������λ��
	
	HashMap<String,String> MAP_dian_db   = new HashMap<String,String>();	// ��  ��Ӧ  ���ݿ�
	HashMap<String,String> MAP_corp_dian = new HashMap<String,String>();	// pk_corp  ��Ӧ  ��
	public ImpZhangDanBill()
	{
		MAP_dian_db.put("ĵ��",  "L01.jgmd");
		MAP_dian_db.put("����",  "L02.jggj");
		MAP_dian_db.put("��ɽ",  "L06.lmt");
		MAP_dian_db.put("�Ƶ�",  "L04.lmt");
//		MAP_dian_db.put("������", "L07.jgllz");	//��HK 2019��5��16��09:57:27��
		MAP_dian_db.put("������", "L06.jgxs");
		MAP_dian_db.put("������", "L08.jgkfr");
//		MAP_dian_db.put("̫��", "L11.jgts");
		
		MAP_corp_dian.put("0001N510000000001SXV", "����");
		MAP_corp_dian.put("0001N510000000001SXX", "ĵ��");
		MAP_corp_dian.put("0001N510000000001SY7", "��ɽ");
		MAP_corp_dian.put("0001N510000000001SY1", "�Ƶ�");
		MAP_corp_dian.put("0001N510000000001SY3", "������");
		MAP_corp_dian.put("0001N510000000001SY5", "������");
	}
	
	/**
	 * HK-����2
	 * 2019��1��8��19:11:25
	 */
	private HashMap<String,String[]> VDEF_Info = null;
	private String[] VDEF_Info_code = null;
	private void get_VDEF_Info() throws BusinessException
	{
		try
		{
			VDEF_Info = new HashMap<String,String[]>();
			
			StringBuffer querySQL = 
					new StringBuffer()
						.append(" select code,name_xiaopiao ")
						.append(" from hk_srgk_hg_zhangdan_vdef v ")
						.append(" where v.isused in ('Y','y') ")
			;
			
			ArrayList list = (ArrayList) this.getBaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
			
			if(list==null || list.size()<=0) return;
			
			for(Object obj_temp:list)
			{
				Object[] obj = (Object[])obj_temp;
				String code = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
				String name_temp = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
				String[] name = name_temp.split("��");
				
				VDEF_Info.put(code, name);
			}
			
			VDEF_Info_code = new String[VDEF_Info.size()];
			VDEF_Info_code = VDEF_Info.keySet().toArray(VDEF_Info_code);
			
		}catch(Exception ex)
		{
			throw new BusinessException(ex);
		}
	}
	
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		long startTime = System.currentTimeMillis();
		
		String[] pk_orgs = bgwc.getPk_orgs();//��֯Ϊ���䣬���Կ϶���ֵ
		
		HashMap<String,String> infoMap = getDefaultInfo(pk_orgs[0]);//�õ����ñ���Ϣ
			
		if(sgyhmap!=null&&sgyhmap.containsKey(pk_orgs[0]))
		{
			sgyhmap.remove(pk_orgs[0]);//����ֹ��Ż��ж�����ֶ�
		}
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{bgwc.getAlertTypeName()+bgwc.getPk_orgs()[0]});
		
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		if(infoMap!=null){
			
			FENQU_SR_MAP = this.queryFenQuKaXing();		// ��÷�������
			
			this.get_VDEF_Info();	// ����˵�Ӧ����Ŀ��HK-����2 2019��1��8��19:32:37��
			
			ArrayList<String> dates=getTimeDates(bgwc.getKeyMap().get("begintime"),bgwc.getKeyMap().get("endtime"));
			for (String date : dates) {
				String timeWhere=getTimeWhere(date);//�õ����ڣ������where����
				getBillBySql(timeWhere,infoMap,date);
			}
		}
		
		System.out.println("�������,����ʱ��"+(System.currentTimeMillis()-startTime)+"����");
	
		return null;
	}

	/**
	 * ���ڴ������
	 * ��̨�����޷�ִ��
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		long startTime = System.currentTimeMillis();
		
		String[] pk_orgs = new String[]{
				HKJT_PUB.PK_ORG_HUIGUAN_xswq
		};//��ɽ
		
		String[] dateP = new String[]{
			"2021-02-08",
			"2021-02-08"
		};
		
		HashMap<String,String> infoMap=getDefaultInfo(pk_orgs[0]);//�õ����ñ���Ϣ
		
		if(sgyhmap!=null&&sgyhmap.containsKey(pk_orgs[0]))
		{
			sgyhmap.remove(pk_orgs[0]);//����ֹ��Ż��ж�����ֶ�
		}
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{"��̨����-����˵�"+pk_orgs[0]});
		
		if(!lock){
			throw new BusinessException("�������ڴ�����,�����ظ�������");
		}
		
		if(infoMap!=null){
			
			FENQU_SR_MAP = this.queryFenQuKaXing();		// ��÷�������
			
			this.get_VDEF_Info();	// ����˵�Ӧ����Ŀ��HK-����2 2019��1��8��19:32:37��
			
			ArrayList<String> dates = getTimeDates(dateP[0],dateP[1]);
			for (String date : dates) {
				String timeWhere=getTimeWhere(date);//�õ����ڣ������where����
				getBillBySql(timeWhere,infoMap,date);
			}
		}
		
		System.out.println("�������,����ʱ��"+(System.currentTimeMillis()-startTime)+"����");
	
		return null;
	}

	/**
	 * 
	 * --ʱ������  ������Ϊ 6-24ȡ
	 6-23 01�� �� 6-24 00��59��59��
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public String getTimeWhere(Object date){
		final String timeField="ChangeTime";
		String beginTime=date+" 03:00:00";
		String endTime=new UFDateTime(date+" 02:59:59").getDateTimeAfter(1).toString();
		String where="";
		where="("+timeField+">='"+beginTime+"' and "+timeField+"<='"+endTime+"')" ;
		return where;
	}
	
	
	/**
	 * �������ڼ�ֿ�����
	 * �����������Ϊ�գ�ҵ������ȡ��ֹ����������ǰһ��
	 * zhangjc 
	 * 2015-8-27����4:15:54
	 * ArrayList<String>   (date)
	 * 
	 *  * --ʱ������  ��ǰ������ʱ�� 6-24
	 1�����û��������ȡ  6-23
	
	 2����� ��ʼ������ ȫ������
	ȡ��ʼ��������������
	
	 3����� ��ʼ ���롢���� û����
	ȡ��ʼ��������ǰһ�죨6-23��
	
	 4����� ��ʼ û�䡢���� ��������
	    �� ��ͬΪ ���1 �����������ǽ�������
	 * 
	 *
	 */
	public ArrayList<String> getTimeDates(Object beginTime,Object endTime){
		UFDate beginDate=null;
		UFDate endDate=null;
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//��ʼ��������Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(endTime.toString());
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//��ʼ��Ϊ�գ�����Ϊ��
			beginDate=new UFDate(beginTime.toString());
			endDate=getCurrentTime().getDate().getDateBefore(1);
		}else{//��ʼ������Ϊ��//��ʼΪ�գ�������Ϊ��
			beginDate=getCurrentTime().getDate().getDateBefore(1);
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
	
	private UFDateTime getCurrentTime() {
		return new UFDateTime(new Date(TimeService.getInstance().getTime()));
	}
	public void getBillBySql(String timeWhere,HashMap<String,String> infoMap,String currentDate) throws BusinessException{
		
		cleanTempTable();
		UFDateTime dbilldate=new UFDateTime(currentDate+" 00:00:00");
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		Connection hkjt_hg_zd_conn=null;
		JdbcSession hkjt_hg_zd_session =null;
		
		try {
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
			
			hkjt_hg_zd_conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_zd_session = new JdbcSession(hkjt_hg_zd_conn);
			String sql="select Dt_ChangeClass.*,ChangeTime " +
		//			",case when convert(varchar(10),ChangeTime,108)<='00:59:59' " +
		//			"then convert(varchar(10),ChangeTime-1, 120)+' 00:00:00' " +
		//			"else convert(varchar(10),ChangeTime, 120)+' 00:00:00'  " +
		//			"end dbilldate" +
					" from Dt_ChangeClass where "+timeWhere+"  and ChangeClassId like '%"+infoMap.get("hg_code")+"%' " +
//					" and Opersite = 'ɣ�ò�' " +
					/**
					 * shengji
					 * 2018��5��7��16:57:12
					 * ��Ҫ ���� ���ʵ� �������Ĵ���
					 */
					 " and ( (Opersite = 'ɣ�ò�') or (Opersite = '������' and ChangeClassId like 'ZYS%') ) " +
					 /**END*/
					" order by ChangeClassId ";
			ArrayList<BanCi_TempVO> list=(ArrayList<BanCi_TempVO>)hkjt_hg_zd_session.executeQuery(sql, new BeanListProcessor(BanCi_TempVO.class));
			for (int i = 0; i < list.size(); i++) {
				BanCi_TempVO banci=list.get(i);
				banci.setPk_org(infoMap.get("pk_org"));
				banci.setDbilldate(dbilldate);
			}
			insertVOS(list);//�������Ϣ���뵽NC��ʱ��
			//��ʱ�����α����ȡ��δ�洢����ʱ����Ϣ
			String sql1="select HK_SRGK_HG_BANCI_temp.* from HK_SRGK_HG_BANCI_temp left join hk_srgk_hg_banci on HK_SRGK_HG_BANCI_temp.changeclassid=hk_srgk_hg_banci.changeclassid where hk_srgk_hg_banci.changeclassid is null";
			ArrayList<BanCiVO> list1=(ArrayList<BanCiVO>)getBaseDAO().executeQuery(sql1, new BeanListProcessor(BanCiVO.class));
			getBaseDAO().insertVOList(list1);//����ʱ����ͬ������α�
			
			String []bancipks=new String[list.size()];
			for (int i = 0; i < list.size(); i++) {//���pks
				BanCi_TempVO banci=list.get(i);
				bancipks[i]=banci.getChangeclassid();
			}
			
			StringBuffer bancipk=new StringBuffer();
			if(bancipks.length>0){
			bancipk.append(" and ( aa.TurnId in (");
			for (int i = 0; i < bancipks.length; i++) {
				String string = bancipks[i];
				bancipk.append("'"+string+"',");
				if(i!=0&&i!=bancipks.length-1&&i%900==0){
					bancipk.delete(bancipk.length()-1, bancipk.length());
					bancipk.append(") or aa.TurnId in (");
				}
			}
			bancipk.delete(bancipk.length()-1, bancipk.length());
			bancipk.append("))");
			}else{
				bancipk.append(" and 1=2 ");
				return;
			}
			
			//ҵ��ϵͳ�˵�������Ϣ
			String sql2="select aa.TurnId,aa.BillId,CONVERT(varchar, aa.OperateDate, 120) OperateDate,ah.Context,aa.OldMoney,aa.FavourMoney,aa.Shishou,aa.MemberId"
				+" from Sn_Bill aa"
				+" left join Sn_BillHistory ah on aa.BillId = ah.Billid"
				+" where 1=1 " 
				+bancipk
				+" and ltrim(aa.Remark) != '����'"
				+" and OldMoney != 0.00"
				
				/**
				 * ���� 
				 * 2019��1��9��10:58:00
				 * 2019��5��16��11:07:50
				 * 2020��1��20��12:10:36
				 */
//				 +" and aa.BillId in ('SN202010080132-06') "
//				 +" and aa.BillId in ('SN202010080169-06-302') "
//				 + " and aa.BillId in ('SN202101310139-06') "
//				 + " and aa.BillId in ('SN202102060253-06') "
//				 + " and aa.BillId in ('SN202102060268-06') "
//				 + " and aa.BillId in ('SN202102060165-06') "
//				 + " and aa.BillId in ('SN202102080090-06') "
//				 + " and aa.BillId in ('SN202102080107-06') "
				 /***END***/
				;
			
			ArrayList<ZhangDanH_TempVO> list2=(ArrayList<ZhangDanH_TempVO>)hkjt_hg_zd_session.executeQuery(sql2, new BeanListProcessor(ZhangDanH_TempVO.class));
			insertVOS(list2);
			
			//ҵ��ϵͳ�˵��ӱ���Ϣ
			String sql3="select " +
				" ab.WaterNum " +	// ��ˮ��
				",aa.BillId " +		// �˵���
				",ab.GoodsName " +	// ��Ʒ
				",gc.NodeName GoodsCatalogName " +	// ��Ʒ����
		//		",case when bt.StoreName='��ʦ��' and isnull(js.ImportLevel,'null')<>'null' then js.ImportLevel else bt.StoreName end StoreName" +	// ��ʦ������Ʒ û�����ϼ�ʦ�����Ի��Ƿ��ڼ�ʦ��   �����  2015��7��22��15:25:31��
				",case when isnull(js.ImportLevel,'null')<>'null' and js.ImportLevel<>''  then js.ImportLevel else bt.StoreName end StoreName " + 	// �����ϼ�ʦ��  ��ȡ��ʦ�Ĳ��ţ� û�����ϵ�  ��ȡ�˵��Ĳ���
				",CONVERT(varchar, ab.Starttime, 120) Starttime" +
				",ab.Status status_type " +
				",ab.KeyId " +
				/**
				 * ��Ϊ����ƾ֤���� ��С��β������⣬ ������Ҫ ȡ������ݵ�ʱ��  �ͽ�ȡ�� ��λС��
				 * ���  2016��5��15��15:42:38
				 */
		//		",round(ab.Money,6) Money " +
		//		",round(ab.RealMoney,6) RealMoney " +
				",round(ab.Money,2) Money " +
				",round(ab.RealMoney,2) RealMoney " +
				/**END*/
				",js.ImportLevel " +
				",ab.membercardid mebercardid " +
				",ab.numbercount numberxount "
				+" from Sn_Bill aa "
				+" inner join Sn_Consumesellog ab on aa.BillId = ab.BillId "
				+" left join KF_HouseInfo bt on ab.batai = bt.storeid " 
				+" left join Dt_GoodCatalog gc on gc.CatalogId = ab.GoodsCatalogId "
				+" left join Sn_JiShi js on js.JishiCode=ab.JiShi "
				+" where 1=1 " 
				+bancipk
				+" and ltrim(aa.Remark) != '����' "
				+" and OldMoney != 0.00 " 
	
			 	/**
				 * ���� 
				 * 2017��9��14��16:47:11
				 * 2019��1��9��10:32:03
				 * 2019��5��16��11:03:29
				 * 2020��1��20��12:10:59
				 */
//				 +" and aa.BillId='SN202001190152-06' "
//				 + " and aa.BillId in ('SN202101310139-06') "
//				 + " and aa.BillId in ('SN202102060253-06') "
//				 + " and aa.BillId in ('SN202102060268-06') "
//				 + " and aa.BillId in ('SN202102060165-06') "
//				 + " and aa.BillId in ('SN202102080090-06') "
//				 + " and aa.BillId in ('SN202102080107-06') "
				 /***END***/
				 ;
			
			ArrayList<ZhangDanB_TempVO> list3=(ArrayList<ZhangDanB_TempVO>)hkjt_hg_zd_session.executeQuery(sql3, new BeanListProcessor(ZhangDanB_TempVO.class));
			insertVOS(list3);//���˵��ӱ�洢����ʱ��
			/**
			 * ����СƱ��Ϣ
			 */
			String sql4 = 
				" select temp.TURNID banci,temp.billid vbillcode,temp.context pk_hk_dzpt_hg_zhangdan,temp.OLDMONEY yingshou,temp.OPERATEDATE creationtime,'"+dbilldate+"' dbilldate,temp.SHISHOU,temp.FAVOURMONEY,temp.MEMBERID huiyuanka_info " +
				" from hk_srgk_hg_zhangdan_temp temp " +
				" left join  (select vbillcode from hk_srgk_hg_zhangdan where  dbilldate>='"+dbilldate+"' and pk_org='"+infoMap.get("pk_org")+"' and nvl(dr, 0) = 0 ) hk_srgk_hg_zhangdan on (temp.billid=hk_srgk_hg_zhangdan.vbillcode) " +
				" where hk_srgk_hg_zhangdan.vbillcode is null "
			;
			ArrayList<ZhangdanHVO> list4=(ArrayList<ZhangdanHVO>)getBaseDAO().executeQuery(sql4, new BeanListProcessor(ZhangdanHVO.class));
			jieXiHuiYuanKa(list4,hkjt_hg_pub_session,infoMap);
			/**
			 * ����ҵ��ϵͳ�˵� ��ͷ��Ϣ��װNCϵͳ�˵��ۺ�VO����
			 */
			ZhangdanBillVO[] aggvos=getZhangDanAggVOs(infoMap,list4,infoMap.get("pk_org"),hkjt_hg_pub_session);
			/**
			 * �� ��Ů��Ʊ�Ľ���̯��������Ʊ�����
			 */
			if (true) {
				Map<String, Integer> from_map = new HashMap<>();
				from_map.put("����Ʊ", 1);
				from_map.put("Ů��Ʊ", 1);
				from_map.put("���޹�����", 1);
				from_map.put("Ů�޹�����", 1);
				from_map.put("��������", 1);
				// ��ʱ�������������ӵ���Ʊ�ϣ����ٽ��з�̯��
				from_map.put("��ʱ����Ʊ", 1);
				from_map.put("��ʱŮ��Ʊ", 1);
				from_map.put("��ʱ���޹�����", 1);
				from_map.put("��ʱŮ�޹�����", 1);
				from_map.put("��ʱ��������", 1);
				from_map.put("��ͯԡ��119Ԫ", 1);
				from_map.put("��ͯԡ��169Ԫ", 1);
				from_map.put("��ͯ��Ʊ", 1);
				from_map.put("��ʱ��ͯ��Ʊ", 1);
				Map<String, Integer> distinct_map = new HashMap<>();
				distinct_map.put("������̽����԰", 1);
				distinct_map.put("ˮ����", 1);
				distinct_map.put("Ԫ���ռ���Ʊ", 1);
				distinct_map.put("��Ȫ��Ʊ", 1);
				distinct_map.put("����ҹ��", 1);
				distinct_map.put("�������", 1);
				distinct_map.put("�������", 1);
				distinct_map.put("�������", 1);
				distinct_map.put("��԰�������", 1);
				distinct_map.put("��԰�������", 1);
				String[] field_list = new String[] {
//						"yingshou",		//	Ӧ��
//						"youhui_zidong",//	�Զ��Ż�
//						"youhui_shougong",//�ֹ��Ż�
//						"shishou",		//	ʵ�գ��Żݺ�
//						"youhui_kabili",//	�������Ż�
						"shouru",		//	ȷ�����루Ŀǰֻ��ע�� ��� ���룩
//						"daijinquan",	// 	����ȯ
//						"miandan",		//	�ⵥ
//						"fenqu",		//	�������Żݣ�
//						"youhui_sg_01",	//	΢�ż���
//						"youhui_qt",	//	�Ż�_����
//						"cika",			//	�ο�
//						"xianjin",		//	�ֽ�
//						"pos",			//	POS
//						"zhipiao",		//	֧Ʊ
//						"wanglai",		//	����
//						"huiyuanka",	//	��Ա��
//						"vbdef01",		//	���������룩
				};
				
				// ��ȥ��ͬһ���Ƶ��ظ�����
				// ����from�����ݣ����from����
				// �����ݷ�̯�� to������
				for (ZhangdanBillVO billVO : aggvos) {
					try {
						Map<String, List<ZhangdanBVO>> bVO_map = new HashMap<>();	// �����´���̯������
						Map<String, ZhangdanBVO> total_map = new HashMap<>();	// ��Ʊ�ϼ� key=����
						Map<String, Integer> count_map = new HashMap<>();		// ������Ʊȥ������
						Map<String, Integer> weight_map = new HashMap<>();		// Ȩ�أ��ܣ� key=����
						Map<String, Integer> weight_sq_map = new HashMap<>();	// Ȩ�أ���Ʊ�� key=����+��Ʊ
						ZhangdanBVO[] bVOs = (ZhangdanBVO[])billVO.getChildrenVO();
	//					List<ZhangdanBVO> bVO_list = new ArrayList<>(Arrays.asList(bVOs));
						// ��һ����ѭ����׼������
						for (ZhangdanBVO bVO : bVOs) {
							String keyId = bVO.getKeyid();		// ����
							String sqName = bVO.getSq_name();	// ��Ʊ
							String keySq = keyId + "####" + sqName;	// ���� + ��Ʊ
							UFDouble shouRu = bVO.getShouru();	// ������
							// ���ݱ��ݣ������� ���ݵ� vbdef10
							if (shouRu != null) {
								bVO.setVbdef10(shouRu.toString());
							}
							// ����from������
							if (from_map.containsKey(sqName)) {
								if (!total_map.containsKey(keyId)) {
									total_map.put(keyId, new ZhangdanBVO());
								}
								ZhangdanBVO total = total_map.get(keyId);
								for (String field : field_list) {
									total.setAttributeValue(field, 
											PuPubVO.getUFDouble_NullAsZero(total.getAttributeValue(field))
										.add(PuPubVO.getUFDouble_NullAsZero(bVO.getAttributeValue(field))));
									// ���from����
									bVO.setAttributeValue(field, null);
								}
								// Ȩ��
								if (!sqName.startsWith("��ʱ")
								 || !sqName.startsWith("��ͯ")
								) {
									if (!weight_map.containsKey(keyId)) {
										weight_map.put(keyId, from_map.get(sqName));
									} else {
										weight_map.put(keyId, weight_map.get(keyId) + from_map.get(sqName));
									}
									if (!weight_sq_map.containsKey(keySq)) {
										weight_sq_map.put(keySq, from_map.get(sqName));
									} else {
										weight_sq_map.put(keySq, weight_sq_map.get(keySq) + from_map.get(sqName));
									}
									if (!bVO_map.containsKey(keyId)) {
										List<ZhangdanBVO> list_temp = new ArrayList<>();
										list_temp.add(bVO);
										bVO_map.put(keyId, list_temp);
									} else {
										bVO_map.get(keyId).add(bVO);
									}
								}
								// ��ʱ�Ĵ���
								// ����г�ʱ������û��ԭʼ����Ҳ��Ҫ����ϡ� 
								// ǰ���� �Ѿ�����ȷ��ԭʼ����ǰ����ʱ���ں�
								if (sqName.startsWith("��ʱ")) {
									if (!weight_map.containsKey(keyId)) {
										weight_map.put(keyId, from_map.get(sqName));
									}
									if (!weight_sq_map.containsKey(keySq)) {
										weight_sq_map.put(keySq, from_map.get(sqName));
									}
								}
							}
							// ȥ��
							if (distinct_map.containsKey(sqName)) {
								// ��������ֶ��Ƿ�Ϊ�գ����Ϊ�վ�˵������Ҫ��̯�ġ�
								if (shouRu == null && !count_map.containsKey(keySq)) {
									count_map.put(keySq, 1);
									// Ȩ��
									if (!weight_map.containsKey(keyId)) {
										weight_map.put(keyId, distinct_map.get(sqName));
									} else {
										weight_map.put(keyId, weight_map.get(keyId) + distinct_map.get(sqName));
									}
									if (!weight_sq_map.containsKey(keySq)) {
										weight_sq_map.put(keySq, distinct_map.get(sqName));
									} else {
										weight_sq_map.put(keySq, weight_sq_map.get(keySq) + distinct_map.get(sqName));
									}
									if (!bVO_map.containsKey(keyId)) {
										List<ZhangdanBVO> list_temp = new ArrayList<>();
										list_temp.add(bVO);
										bVO_map.put(keyId, list_temp);
									} else {
										bVO_map.get(keyId).add(bVO);
									}
								} else {
	//								bVO_list.remove(bVO);
								}
							}
						}
						// �ڶ��������з�̯
						for (Map.Entry<String, List<ZhangdanBVO>> entry : bVO_map.entrySet()) {
							String keyId = entry.getKey();
							ZhangdanBVO totalData = total_map.get(keyId);	// ����̯�ܶ�
							if (totalData == null) {
								// ��� �ϼ�Ϊ�գ�����Ҫ���
								continue;
							}
							ZhangdanBVO sharedData = new ZhangdanBVO();	// �Ѿ���̯��
							List<ZhangdanBVO> listTemp = entry.getValue();
							Integer weight = weight_map.get(keyId);	// �����Ƶ���Ȩ��
							if (weight == null) {
								// �����Ȩ��Ϊ�գ�˵�� û����Ʊ�ˣ�����Ҫ���
								continue;
							}
							for (int i = 0; i < listTemp.size(); i++) {
								// ���з�̯����
								ZhangdanBVO bVO = listTemp.get(i);
								String sqName = bVO.getSq_name();	// ��Ʊ
								String keySq = keyId + "####" + sqName;	// ���� + ��Ʊ
								Integer weightSq = weight_sq_map.get(keySq);	// Ȩ�أ���Ʊ�� key=����+��Ʊ
								for (String field : field_list) {
									if (i != listTemp.size() - 1) {
										// �����һ�ʣ���Ȩ�ط�̯
										UFDouble money = PuPubVO.getUFDouble_NullAsZero(totalData.getAttributeValue(field));
										UFDouble share = money.multiply(weightSq).div(weight).setScale(2, UFDouble.ROUND_HALF_UP);
										sharedData.setAttributeValue(field,
											PuPubVO.getUFDouble_NullAsZero(sharedData.getAttributeValue(field))
												.add(share));
										bVO.setAttributeValue(field, share);
									} else {
										// ���һ�� ���е���
										UFDouble share = PuPubVO.getUFDouble_NullAsZero(totalData.getAttributeValue(field))
												.sub(PuPubVO.getUFDouble_NullAsZero(sharedData.getAttributeValue(field)));
										bVO.setAttributeValue(field, share);
									}
								}
							}
						}
						// ������������������ȷ������Ϊ0�Ľ������
						for (ZhangdanBVO bVO : bVOs) {
							UFDouble shouRu = bVO.getShouru();
							if (shouRu != null && shouRu.compareTo(UFDouble.ZERO_DBL) == 0) {
								bVO.setShouru(null);
							}
						}
					} catch (Exception ex) {
						throw new BusinessException("�˵��ţ�" + billVO.getParentVO().getVbillcode() + ex);
					}
//					System.out.println(total_map);
				}
			}
			/**
			 * ����
			 */
//			if (true) {
//				throw new BusinessException("����");
//			}
			/**
			 * ���б���
			 */
			IHg_zhangdanMaintain itf = NCLocator.getInstance().lookup(IHg_zhangdanMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
			if(aggvos!=null&&aggvos.length>0)
			{
				itf.insert(aggvos, null);
			}
		
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
			if(hkjt_hg_pub_session!=null)
			hkjt_hg_pub_session.closeAll();
			if(hkjt_hg_zd_session!=null)
			hkjt_hg_zd_session.closeAll();
			JDBCUtils.closeConn(hkjt_hg_pub_conn);
			JDBCUtils.closeConn(hkjt_hg_zd_conn);
		}
	}	

	BaseDAO dao=null;
	public BaseDAO getBaseDAO(){
		if(dao==null)
			dao=new BaseDAO();
		return dao;
	}
	/**
	 * ������֯��ѯ��Ĭ��ϵͳ��Ϣ
	 * @param pk_org
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String> getDefaultInfo(String pk_org) throws DAOException{
		String sql="select * from hk_srgk_hg_info where pk_org='"+pk_org+"'";
		HashMap<String,String> map=(HashMap<String,String>)getBaseDAO().executeQuery(sql, new MapProcessor());
		/**
		 * HK 2019��5��16��10:15:20
		 * 0310ת0308
		 * TODO 
		 */
		map.put("pk_org_spfl", map.get("pk_org"));	// ȡ��Ʒ�������֯
//		String temp_pk_org = map.get("pk_org");
//		if(HKJT_PUB.PK_ORG_HUIGUAN_xswq.equals(temp_pk_org)){
//			map.put("pk_org", HKJT_PUB.PK_ORG_JIUDIAN_llzjd);
//			map.put("pk_org_spfl", HKJT_PUB.PK_ORG_JIUDIAN_llzjd);
//		}
		/***END***/
		
		return map;
	}
	/**
	 * �õ���װ����˵��ۺ�VO
	 * @param infoMap
	 * @param hvos
	 * @param pk_org
	 * @param session
	 * @return
	 * @throws BusinessException
	 * @throws DbException 
	 */
	public ZhangdanBillVO[] getZhangDanAggVOs(HashMap<String,String> infoMap,ArrayList<ZhangdanHVO> hvos,String pk_org,JdbcSession session) throws BusinessException, DbException{
		
		String pk_org_spfl = infoMap.get("pk_org_spfl");	// ȡ ��Ʒ�������֯
		
		ArrayList<ZhangdanBillVO> aggvos=new ArrayList<ZhangdanBillVO>();
		HashMap<String,SpflHVO> spfl=getAllSpfl(pk_org_spfl);//�õ�NC��Ʒ������Ϣ
		HashMap<String,String[]> bmxx=getAllBmxx();//�õ�NC������Ϣ
		HashMap<String,String[]> bmNames=getAllBmNameByPk();//���ݲ���pk�õ���������
		IOrgVersionQryService orgVersion=NCLocator.getInstance().lookup(IOrgVersionQryService.class);
		String pk_org_v=orgVersion.getOrgUnitLastVersionByOrgID(pk_org).getPk_vid();
		String pk_group=AppContext.getInstance().getPkGroup();
		HashMap<String,ArrayList<ZhangdanBVO>> bodyvoMaps=getZhangDanBodyTempVOS(hvos);//���������е�billid������ʱ���������
		
		/**
		 * �������� �޸ģ� ֱ��ͨ�� ������  ��ý��
		 * ���  2016��4��27��15:33:43
		 */
//		HashMap<String,HashSet<String>> fenqumap=getFenQuNameByBillIDs(session, hvos, infoMap.get("vpnname"));
		HashMap<String,HashSet<String>> fenqumap = null;	// ���� �������
		/**END*/
		
		/**
		 * HK ��������
		 * key=��Ʒ���ơ�value=��Ʒ����
		 */
		Map<String, String> map_man = new HashMap<>();		// ��
		map_man.put("����Ʊ", "����Ʊ");
		map_man.put("��ʱ����Ʊ", "����Ʊ");
		map_man.put("���޹�����", "����Ʊ");
		map_man.put("��ʱ���޹�����", "����Ʊ");
		Map<String, String> map_woman = new HashMap<>();	// Ů
		map_woman.put("Ů��Ʊ", "Ů��Ʊ");
		map_woman.put("��ʱŮ��Ʊ", "Ů��Ʊ");
		map_woman.put("Ů�޹�����", "Ů��Ʊ");
		map_woman.put("��ʱŮ�޹�����", "Ů��Ʊ");
		Map<String, String> map_child = new HashMap<>();	// ��ͯ��Ҫ�ҵ������� ������Ů��
		map_child.put("��ͯԡ��119Ԫ", null);
		map_child.put("��ͯԡ��169Ԫ", null);
		map_child.put("��ͯ��Ʊ", null);
		map_child.put("��ʱ��ͯ��Ʊ", null);
		Map<String, String> map_qinzi = new HashMap<>();	// ����
		map_qinzi.put("��������", "Ů��Ʊ");
		map_qinzi.put("��ʱ��������", "Ů��Ʊ");
		Map<String, String> map_distinct = new HashMap<>();	// Ҫ��̯������Ŀ��
		map_distinct.put("������̽����԰", "������̽����԰����֣�");
		map_distinct.put("Ԫ���ռ���Ʊ", "Ԫ���ռ���Ʊ����֣�");
		map_distinct.put("��Ȫ��Ʊ", "��Ȫ��Ʊ����֣�");
		map_distinct.put("ˮ����", "ˮ���ɣ���֣�");
		map_distinct.put("����ҹ��", "����ҹ������֣�");
		map_distinct.put("�������", "������ͣ���֣�");
		map_distinct.put("�������", "������ͣ���֣�");
		map_distinct.put("�������", "������ͣ���֣�");
		map_distinct.put("��԰�������", "��԰������ͣ���֣�");
		map_distinct.put("��԰�������", "��԰������ͣ���֣�");
		/***END***/
		
		for (ZhangdanHVO zhangdanHVO : hvos) {
			ZhangdanBillVO aggvo=new ZhangdanBillVO();
			zhangdanHVO.setPk_org(pk_org);
			zhangdanHVO.setPk_org_v(pk_org_v);
			zhangdanHVO.setPk_group(pk_group);
			zhangdanHVO.setIbillstatus(-1);
			zhangdanHVO.setVbilltypecode("HK01");//��������
			aggvo.setParentVO(zhangdanHVO);
			
			ArrayList<ZhangdanBVO> bvos_01 = bodyvoMaps.get(zhangdanHVO.getVbillcode());
			
			ZhangdanBVO[] bvos_arr = bvos_01.toArray(new ZhangdanBVO[0]);
			
			Arrays.sort(bvos_arr);	// ����ȷ�� ��ͯ���������
			
			List<ZhangdanBVO> bvos = new ArrayList<>(Arrays.asList(bvos_arr));
			
			/**
			 * ��� ������� β��Ĵ��� ��� ����֮�� ������ �˵���ͷ�����ݣ� ����Ҫ���ӵ� ���һ���ϣ� ȷ�� ��ͷ������ ����һ��
			 * ���  2016��5��15��20:10:34
			 */
			if(bvos!=null&&bvos.size()>0)
			{
				UFDouble head_shishou = PuPubVO.getUFDouble_NullAsZero( zhangdanHVO.getShishou() );	// ��ͷ��ʵ�ս��
				int last_index = -1;	// ���һ�� �� ʵ�ս��� ������
				UFDouble body_shishou = UFDouble.ZERO_DBL;	// ����� ʵ�ս��֮��
				for (int i = 0; i < bvos.size(); i++) {
					UFDouble shishou = PuPubVO.getUFDouble_ZeroAsNull( bvos.get(i).getShishou() );
					if( shishou!=null )
					{
						body_shishou = body_shishou.add(shishou);
						last_index = i;
					}
				}
				
				if( head_shishou.compareTo(body_shishou)!=0 )
				{// ��� ��ͷʵ��  ������ ����ʵ�գ� �򽫲�� ���ӵ�  ���һ�� �� ʵ�ս�����
					UFDouble cha = head_shishou.sub(body_shishou);	// ���=��ͷʵ��-����ʵ��
					bvos.get(last_index).setShishou( bvos.get(last_index).getShishou().add(cha) );	// ʵ�� = ʵ�� + ���
					bvos.get(last_index).setYouhui_zidong( bvos.get(last_index).getYingshou().sub( bvos.get(last_index).getShishou() ) );	// �Զ��Ż� = Ӧ��-ʵ��
				}
			}
			/**END*/
			
			if(bvos==null||bvos.size()==0)continue;//�����������Ϊ�գ����˵���ͷ����
			
			aggvo.setChildrenVO(bvos.toArray(new ZhangdanBVO[]{}));
			/**
			 * ���Բ���
			 * ��Ҫ��̯�㷨
			 */
			execBodyValues(aggvo,session,infoMap.get("vpnname"),fenqumap);//������� [�������Ż�][�ֽ�][POS][��Ա��][ȷ������]��ֵ
			/**END**/
			aggvos.add(aggvo);
			
			if(bvos!=null)//ZSN201506150002-01 ���¥
			{
				Map<String, String> map_hand = new HashMap<>();	// ���ƶ�Ӧ���� ��Ʒ����
				for (int i = 0; i < bvos.size(); i++) {
					ZhangdanBVO bvo=bvos.get(i);
					bvo.setVrowno(String.valueOf((i+1)*10));//���кŸ�ֵ
					/**
					 * HK ������Ʒ������жϸ�ֵ
					 */
					{
						String sqName = bvo.getSq_name();	// ��Ʒ����
//						if ("�������".equals(sqName)) {
//							System.out.println();
//						}
						String keyId = bvo.getKeyid();	// ���ƺ�
						String sqflName = null;	// ��Ʒ��������
						if (map_man.containsKey(sqName)) {// ��
							sqflName = map_man.get(sqName);
						} else if (map_woman.containsKey(sqName)) {// Ů
							sqflName = map_woman.get(sqName);
						} else if (map_qinzi.containsKey(sqName)) {// ����
							sqflName = map_qinzi.get(sqName);
						} else if (map_child.containsKey(sqName)) {// ��ͯ
							// ������ȷ����ͯ�����Ž��д���
							bvo.setSqfl_name(map_hand.get(keyId));
						} else if (map_distinct.containsKey(sqName)) {// Ҫ��ֵ�����Ŀ
							// Ӧ�ղ�Ϊ0������Ҫ��֡�
//							if (PuPubVO.getUFDouble_ZeroAsNull(bvo.getYingshou()) == null) {
//								bvo.setSqfl_name(map_distinct.get(sqName));
//							}
							if (bvo.getShouru() == null) {
								bvo.setSqfl_name(map_distinct.get(sqName));
							}
						}
						
						if (sqflName != null) {
							bvo.setSqfl_name(sqflName);
							map_hand.put(keyId, sqflName);
						}
					}
					/***END***/
					SpflHVO spflVO=spfl.get(pk_org_spfl+"@@"+bvo.getSqfl_name());
					if(spflVO!=null){
						bvo.setSqfl_id(spflVO.getPk_hk_srgk_hg_spfl());//����ҵ��ϵͳ��Ʒ�������ƣ�Ϊ�˵����帳ֵNCϵͳ��Ʒ����id
						bvo.setSrxm_id(spflVO.getPk_hk_srgk_hg_srxm());//������Ŀid			
					}
					
					/**
					 * ԡ�� ��  �� ��󴫹�����  ������ա�����Ϊ ʵ��ҵ���� ǰ̨��¼��ĵ��ӣ� ���� ���ܽ�����鵽ǰ̨��
					 * Ŀ����ȡ ��Ʒ����  Ĭ�ϲ��š�
					 * ���
					 * 2016��4��23��10:45:53
					 */
					if( "��Ʊ".equals( bvo.getSq_name() ) )
					{
						bvo.setBm_name(null);
					}
					/**END*/
					
					if(bvo.getBm_name()==null&&spflVO!=null){//��������Ϊ�գ���Ʒ���಻Ϊ��
						bvo.setBm_id(spflVO.getPk_dept());//����ҵ��ϵͳ��Ʒ�������ƣ���ֵĬ�ϲ���pk
						String []bm=bmNames.get(bvo.getBm_id());
						bvo.setBm_name(bm==null?null:bm[0]);//���ݲ���id��ò�������
						bvo.setBm_fid(bm==null?null:bm[1]);//�ϼ�����id
					}else{//���Ų�Ϊ����NC���ŵ���pkֵ����
						bvo.setBm_id(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[0]);//����ҵ��ϵͳ�������Ƹ�ֵ��������
						bvo.setBm_fid(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[1]);//�ϼ�����id
					}
					
					if(
						// �����������Ϊ��,������Ʒ����Ϊ ԡ��
						( null == PuPubVO.getString_TrimZeroLenAsNull(bvo.getBm_name()) && ("ԡ��".equals(bvo.getSqfl_name())||"�Զ���ԡ��".equals(bvo.getSqfl_name())) )
						// �������Ϊǰ̨����Ʒ����Ϊ ��ɣ
					 || ( "ǰ̨".equals(PuPubVO.getString_TrimZeroLenAsNull(bvo.getBm_name())) && ("��ɣ".equals(bvo.getSqfl_name())) )
					)
					{
						if(bvo.getSq_name()!=null&&bvo.getSq_name().contains("��")){//��Ʒ���ư�����
							if("��ɽ��Ȫ".equals(infoMap.get("org_name")))
								bvo.setBm_name("������Ȫ");
							else if("���¥".equals(infoMap.get("org_name")))
								bvo.setBm_name("��ԡ");
							else
								bvo.setBm_name("��ԡ");
						}else if(bvo.getSq_name()!=null&&bvo.getSq_name().contains("Ů")){
							if("��ɽ��Ȫ".equals(infoMap.get("org_name")))
								bvo.setBm_name("�ʺ���Ȫ");
							else if("���¥".equals(infoMap.get("org_name")))
								bvo.setBm_name("Ůԡ");
							else
								bvo.setBm_name("Ůԡ");
						}
						else
						{// ���¥ �� ֻ����Ʊ�������û�з���Ů������Ҫ������������� Ĭ�Ϸ��䵽��ԡ��
						 // ���¥ �� ֻ�о�ɣ�������û�з���Ů������Ҫ������������� Ĭ�Ϸ��䵽��ԡ��
							if("��ɽ��Ȫ".equals(infoMap.get("org_name"))){
								bvo.setBm_name("������Ȫ");
							}else{
								bvo.setBm_name("��ԡ");
							}
						}
		
						bvo.setBm_id(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[0]);	//����ҵ��ϵͳ�������Ƹ�ֵ��������
						bvo.setBm_fid(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[1]);	//�ϼ�����id
					}
					
					if(bvo.getBm_fid()==null){//����ϼ�����idΪ�գ����ϼ����Ÿ�ֵΪ�����ŵ�pk
						bvo.setBm_fid(bvo.getBm_id());
					}
				}
			}
			
		}
		
		return aggvos.toArray(new ZhangdanBillVO[]{});
	}

	/**
	 * �����˵���ͷ���ݣ������˵���ʱ�����ݵ�HashMap��key=vbillcode ��value=ArrayList<ZhangdanBVO>
	 * zhangjc
	 * 2015-7-28����9:35:39
	 * HashMap<String,ArrayList<ZhangdanBVO>>
	 * @throws DAOException 
	 *
	 */
	public HashMap<String,ArrayList<ZhangdanBVO>> getZhangDanBodyTempVOS(ArrayList<ZhangdanHVO> hvos) throws DAOException{
		HashMap<String,ArrayList<ZhangdanBVO>> bodyVOMaps=new HashMap<String, ArrayList<ZhangdanBVO>>();
		StringBuffer vbillcodes=new StringBuffer();
		if(hvos.size()>0){
			vbillcodes.append(" and ( billid in (");
		for (int i = 0; i < hvos.size(); i++) {
			String vbillcode = hvos.get(i).getVbillcode();
			vbillcodes.append("'"+vbillcode+"',");
			if(i!=0&&i!=hvos.size()-1&&i%900==0){
				vbillcodes.delete(vbillcodes.length()-1, vbillcodes.length());
				vbillcodes.append(") or billid in (");
			}
		}
		vbillcodes.delete(vbillcodes.length()-1, vbillcodes.length());
		vbillcodes.append("))");
		}else{
			vbillcodes.append(" and 1=2 ");
		}
		
		
		String sql="select billid pk_hk_dzpt_hg_zhangdan,WATERNUM,GOODSNAME sq_name,GOODSCATALOGNAME sqfl_name,STORENAME bm_name,MONEY yingshou,REALMONEY shishou,mebercardid,numberxount, round(MONEY,6)-round(REALMONEY,6) youhui_zidong ,keyid,decode(status_type,'�ο�',MONEY,0) cika from hk_srgk_hg_zhangdan_b_temp where 1=1 "+vbillcodes+" order by keyid,status_type,starttime";
		ArrayList<ZhangdanBVO> bvos=(ArrayList<ZhangdanBVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(ZhangdanBVO.class));
		String key="";
		for (ZhangdanBVO zhangdanBVO : bvos) {
			key=zhangdanBVO.getPk_hk_dzpt_hg_zhangdan();
			if(bodyVOMaps.containsKey(key)){
				ArrayList<ZhangdanBVO> bodyvos=bodyVOMaps.get(key);
				zhangdanBVO.setPk_hk_dzpt_hg_zhangdan(null);
				bodyvos.add(zhangdanBVO);
				bodyVOMaps.put(key,bodyvos);
			}else{
				ArrayList<ZhangdanBVO> bodyvos=new ArrayList<ZhangdanBVO>();
				zhangdanBVO.setPk_hk_dzpt_hg_zhangdan(null);
				bodyvos.add(zhangdanBVO);
				bodyVOMaps.put(key, bodyvos);
			}
		}
		
		
		
		return bodyVOMaps;
	}
/**
 * zhangjc
 * 2015-8-28����1:16:24
 * void
 *�����ʱ������
 */
public void cleanTempTable() throws BusinessException{
	String delsql1="delete from hk_srgk_hg_banci_temp";
	String delsql2="delete from hk_srgk_hg_zhangdan_temp";
	String delsql3="delete from hk_srgk_hg_zhangdan_b_temp";
	getBaseDAO().executeUpdate(delsql1);
	getBaseDAO().executeUpdate(delsql2);
	getBaseDAO().executeUpdate(delsql3);
}
public void insertVOS(ArrayList vos)throws BusinessException{
	getBaseDAO().insertVOList(vos);
}
	/**
	 * �����ֶθ�ֵ
	 * @param aggvo
	 * @throws BusinessException 
	 */
	public void execBodyValues(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException {
		/**
		 * ���Բ���
		 */
		execBodySgyhFentan(aggvo,session,vpnname,fenqumap);//�����ֹ��Żݽ���̯
		/**
		 * ���Բ���
		 */
		execBodyMoneyFenTan(aggvo);		//�����ʵ�ձ��� ֱ�ӷ�̯  
	}


	/**
	 * �����ʵ�ձ��� ֱ�ӷ�̯
	 * @param aggvo
	 */
	public void execBodyMoneyFenTan(ZhangdanBillVO aggvo) {
		ZhangdanHVO hvo=aggvo.getParentVO();
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//���һ��ʵ�ղ�Ϊ�����
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			sumShiShou=sumShiShou.add(bvo.getShishou());
			if(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0){
				lastNotZeroRow=i;
			}
		}
		UFDouble sumQrsr=UFDouble.ZERO_DBL;//����ȷ�������ۼӺ�
		UFDouble hyk_ys=UFDouble.ZERO_DBL;//��Ա���忨Ӧ��
		UFDouble hyk_sr=UFDouble.ZERO_DBL;//��Ա���忨����
		UFDouble ck_ys=UFDouble.ZERO_DBL;
		UFDouble ck_sr=UFDouble.ZERO_DBL;
		
		UFDouble yhje_temp=UFDouble.ZERO_DBL;//�������Żݽ���ۼ�
		UFDouble xj_temp=UFDouble.ZERO_DBL;//�ֽ����ۼ�
		UFDouble pos_temp=UFDouble.ZERO_DBL;//POS����ۼ�
		UFDouble hyk_temp=UFDouble.ZERO_DBL;//��Ա������ۼ�
		UFDouble gzje_temp=UFDouble.ZERO_DBL;//���˽���ۼ�
		UFDouble wlje_temp=UFDouble.ZERO_DBL;//��������ۼ�
		
		String[][] matchField=matchDirectFenTanFields();
		String []headField=matchField[0];
		String []bodyField=matchField[1];
		
		UFDouble []sumField=new UFDouble[headField.length];
		for (int i = 0; i < sumField.length; i++) {
			sumField[i]=UFDouble.ZERO_DBL;
		}
		
		for ( int i=0;i<bvos.length;i++ ) {//�Żݽ���̯
			ZhangdanBVO bvo=bvos[i];
			if(isZero(bvo.getShishou())||isZero(sumShiShou))continue;
			UFDouble bili=bvo.getShishou().div(sumShiShou).setScale(4, UFDouble.ROUND_HALF_UP);//����
			
			yhje_temp=execBodyKblyh(hvo,bvo,i,lastNotZeroRow,bili,yhje_temp);//������忨�����Ż�
			xj_temp=execBodyXjFentan(hvo,bvo,i,lastNotZeroRow,bili,xj_temp);//�����ֽ��̯
			pos_temp=execBodyPOSFentan(hvo,bvo,i,lastNotZeroRow,bili,pos_temp);//����POS�����ѽ���̯
			hyk_temp=execBodyHykFentan(hvo,bvo,i,lastNotZeroRow,bili,hyk_temp);//�����Ա�����ѽ���̯
//			gzje_temp=execBodyguazhangFentan(hvo,bvo,i,lastNotZeroRow,bili,gzje_temp);//�˵�������˽���̯����
			wlje_temp=execBodyWangLaiFentan(hvo,bvo,i,lastNotZeroRow,bili,wlje_temp);//�˵�����������Ϣ���Ź���Ʊ������̯����
			
			for (int j = 0; j < sumField.length; j++) {
				sumField[j]=execBodyJineFentan(hvo,bvo,i,lastNotZeroRow,bili,sumField[j],headField[j],bodyField[j]);//��չ  ��������̯
			}
			
			bvo.setShouru(bvo.getShishou().sub(nullAsZero(bvo.getYouhui_kabili())).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//ȷ������=ʵ��-�������Ż�
			sumQrsr=sumQrsr.add(bvo.getShouru());
			if("��Ա��".equals(bvo.getSqfl_name())){//��Ʒ��������Ϊ��Ա��
				bvo.setChongka_hyk_ys(bvo.getYingshou());//��Ա��Ӧ��=Ӧ��
				bvo.setChongka_hyk_sr(bvo.getShouru());//��Ա������=ȷ������
				hyk_ys=hyk_ys.add(nullAsZero(bvo.getYingshou()));
				hyk_sr=hyk_sr.add(nullAsZero(bvo.getShouru()));
			}else if(getIsTcfs(bvo)){//"�ο��ײʹ���".equals(bvo.getSqfl_name())||"�ײͷ���".equals(bvo.getSqfl_name())
				bvo.setChongka_ck_ys(bvo.getYingshou());//�ο�Ӧ��=Ӧ��
				bvo.setChongka_ck_sr(bvo.getShouru());//�ο�����=ȷ������
				ck_ys=ck_ys.add(nullAsZero(bvo.getYingshou()));
				ck_sr=ck_sr.add(nullAsZero(bvo.getShouru()));
			}
		}
		
		hvo.setShouru(sumQrsr);//��ͷȷ������=����ȷ�������
		hvo.setChongka_hyk_ys(hyk_ys);//��Ա���忨Ӧ��
		hvo.setChongka_hyk_sr(hyk_sr);//��Ա���忨����
		hvo.setChongka_ck_ys(ck_ys);
		hvo.setChongka_ck_sr(ck_sr);
	}

	public boolean getIsTcfs(ZhangdanBVO bvo){
		return "�ο��ײʹ���".equals(bvo.getSqfl_name())||"�ײͷ���".equals(bvo.getSqfl_name());
	}
	/**
	 * �����˵��������Ż�
	 * @param aggvos
	 */
	public UFDouble execBodyKblyh(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble yhje_temp){
		UFDouble yhje=nullAsZero(hvo.getHuiyuanka_bl());//��Ա���Żݽ��
		if(isZero(yhje)){//����Żݽ��Ϊ��,���ڽ��м���
			return yhje_temp;	
		}
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� �������Ż�=���Ż�-ǰ�����Żݺ�
				bvo.setYouhui_kabili(yhje.sub(yhje_temp));
			}else{
				bvo.setYouhui_kabili(bili.multiply(yhje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//�������Ż�=ԭʵ�ս��ռ��ʵ�ս�� ����*�Żݽ��
				yhje_temp=yhje_temp.add(bvo.getYouhui_kabili());
		}
			return yhje_temp;	
	}
	
	/**
	 * �˵������ֽ� ����̯����
	 * @param aggvos
	 */
	public UFDouble execBodyXjFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble xj_temp){
		UFDouble xianjin=hvo.getXianjin()==null?UFDouble.ZERO_DBL:hvo.getXianjin();//�ֽ�
		if(isZero(xianjin))return xj_temp;
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� �������Ż�=���Ż�-ǰ�����Żݺ�
				bvo.setXianjin(xianjin.sub(xj_temp));
			}else{
				bvo.setXianjin(bili.multiply(xianjin).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//�����ֽ�=ԭʵ�ս��ռ��ʵ�ս�� ����*�ֽ�
				xj_temp=xj_temp.add(bvo.getXianjin());
			}
			return xj_temp;
	}
	
	/**
	 * �˵�����POS����̯����
	 * @param aggvos
	 */
	public UFDouble execBodyPOSFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble pos_temp){
		UFDouble pos=hvo.getPos()==null?UFDouble.ZERO_DBL:hvo.getPos();//POS�����ѽ��
		if(isZero(pos))return pos_temp;
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� POS��̯=��POS-ǰ����POS��̯��
				bvo.setPos(pos.sub(pos_temp));
			}else{
				bvo.setPos(bili.multiply(pos).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//POS��̯=ԭʵ�ս��ռ��ʵ�ս�� ����*POS����
				pos_temp=pos_temp.add(bvo.getPos());
			}
			return pos_temp;
	}
	
	/**
	 * �˵������Ա������̯����
	 * @param aggvos
	 */
	public UFDouble execBodyHykFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble hyk_temp){
		UFDouble hykxfje=hvo.getHuiyuanka()==null?UFDouble.ZERO_DBL:hvo.getHuiyuanka();//��Ա�����ѽ��
		if(isZero(hykxfje))return hyk_temp;
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� �������Ż�=���Ż�-ǰ�����Żݺ�
				bvo.setHuiyuanka(hykxfje.sub(hyk_temp));
			}else{
				bvo.setHuiyuanka(bili.multiply(hykxfje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//��Ա��=ԭʵ�ս��ռ��ʵ�ս�� ����*��Ա������
				hyk_temp=hyk_temp.add(bvo.getHuiyuanka());
			}
			return hyk_temp;
	}
	/**
	 * �˵�������˽���̯����
	 * @param aggvos
	 */
	public UFDouble execBodyguazhangFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble gzje_temp){
		UFDouble gzje=hvo.getGuazhang()==null?UFDouble.ZERO_DBL:hvo.getGuazhang();//���˽��
		if(isZero(gzje))return gzje_temp;
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� ���˽��=�ܹ��˽��-ǰ���й��˽���
				bvo.setGuazhang(gzje.sub(gzje_temp));
			}else{
				bvo.setGuazhang(bili.multiply(gzje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//�й��˽���̯=ԭʵ�ս��ռ��ʵ�ս�� ����*���˽��
				gzje_temp=gzje_temp.add(bvo.getGuazhang());
			}
			return gzje_temp;
	}
	
	/**
	 * zhangjc
	 * 2015-7-24����4:21:13
	 * void
	 *�˵�����������Ϣ���Ź���Ʊ������̯����
	 */
	public UFDouble execBodyWangLaiFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble wlje_temp){
		UFDouble wlje=hvo.getWanglai()==null?UFDouble.ZERO_DBL:hvo.getWanglai();//�������
		if(isZero(wlje))return wlje_temp;
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� ���˽��=�ܹ��˽��-ǰ���й��˽���
				bvo.setWanglai(wlje.sub(wlje_temp));
			}else{
				bvo.setWanglai(bili.multiply(wlje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//��������̯=ԭʵ�ս��ռ��ʵ�ս�� ����*�������
				wlje_temp=wlje_temp.add(bvo.getWanglai());
			}
			return wlje_temp;
	}
	
	/**
	 * ��չ �������̯����������ʵ�գ��Żݣ�������̯��
	 * zhangjc
	 * 2015-7-29����9:28:16
	 * UFDouble
	 *
	 */
	public UFDouble execBodyJineFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble tempSum,String headField,String bodyField){
		UFDouble headMoney=nullAsZero(hvo.getAttributeValue(headField));
		if(isZero(headMoney))return tempSum;
			if(i==lastNotZeroRow){//���һ�в�Ϊ�����=��ͷ�ܽ��-ǰ���н���
				bvo.setAttributeValue(bodyField, headMoney.sub(tempSum).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));
			}else{
				bvo.setAttributeValue(bodyField,bili.multiply(headMoney).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));
				tempSum=tempSum.add(nullAsZero(bvo.getAttributeValue(bodyField)));
			}
			return tempSum;
	}
	
	/**
	 * �˵������ֹ��Żݷ�̯����
	 * @param aggvos
	 * @throws BusinessException 
	 */
	public void execBodySgyhFentan(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException{
		execBodyMianDanFentan(aggvo);//�ⵥ
//		setShiShou(aggvo);		// ����ʵ��
////////		execBodyfqjeFentan(aggvo,session,vpnname,fenqumap);		//������� ��̯����
		execBodyfqjeFentan_2(aggvo,session,vpnname,fenqumap);	//������� ��̯����   ���   2016��4��27��15:26:17
//		setShiShou(aggvo);		// ����ʵ��
		execBodyDaijinquanFentan(aggvo);//����ȯ
		setShiShou(aggvo);		// ����ʵ��
		execBodySgYouHui_kz(aggvo);//�ֹ��Żݽ���̯����չ
	}
	/**
	 * �����ֹ��Żݽ���Լ�ʵ�ս��
	 * @param aggvo
	 * @throws BusinessException 
	 */
	public void setShiShou(ZhangdanBillVO aggvo) throws BusinessException{
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		for (ZhangdanBVO bvo : bvos) {
			
			/**
			 * 2018��7��23��17:15:57
			 * ���� СƱ�� �Ż��� ��������
			 */
//			if(  PuPubVO.getString_TrimZeroLenAsNull( bvo.getVbdef01() ) == null 
//			  || PuPubVO.getString_TrimZeroLenAsNull( bvo.getVbdef01().replaceAll("~", "") ) == null
//			  )
//			{
				//�ֹ��Żݺ�
				UFDouble sgyh=nullAsZero(bvo.getDaijinquan())//����ȯ
						  .add(nullAsZero(bvo.getFenqu()))//�������
						  .add(nullAsZero(bvo.getMiandan()))//�ⵥ
						  .add(nullAsZero(bvo.getYouhui_qt()));//�Ż�-����
				for (String field : matchSgyhFenTanFields(aggvo.getParentVO().getPk_org())[1]) {
					if(!ZhangdanBVO.YOUHUI_QT.equals(field))
					sgyh=sgyh.add(nullAsZero(bvo.getAttributeValue(field)));
				}
				
				bvo.setYouhui_shougong(sgyh.setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//���ñ����ֹ��Ż�
				bvo.setShishou(nullAsZero(bvo.getYingshou()).sub(nullAsZero(bvo.getYouhui_zidong())).sub(nullAsZero(bvo.getYouhui_shougong())));//ʵ��=Ӧ��-�Զ��Ż�-�ֹ��Ż�
				
				//������Ϊ��
				bvo.setYingshou(nullAsZero(bvo.getYingshou()));
				bvo.setYouhui_zidong(nullAsZero(bvo.getYouhui_zidong()));
//			}
		}
	}
	
	/**
	 * �˵��������� ����
	 * ���� �ӽ�����ݱ���  ֱ��ȡ�� ÿ���˵��е�  �������
	 * ���  2016��4��27��15:26:17
	 * P_PartitionConsumeDetails
	 */
	public void execBodyfqjeFentan_2(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException
	{
		try {
			ZhangdanHVO hvo = aggvo.getParentVO();
			UFDouble fqje=nullAsZero(hvo.getFenqu());//�˵���ͷ�������
			if(isZero(fqje))return;	// ֻ�� �������  ��Ϊ�գ�  �����½��С�
			
			String pk_org = hvo.getPk_org();
			String db_str = MAP_dian_db.get( MAP_corp_dian.get(pk_org) );
			
			Connection hkjt_hg_pub_conn=null;
			JdbcSession hkjt_hg_pub_session =null;
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
			
			StringBuffer querySQL = // �� �����⣬ Ȼ��ͨ�� db_str ��������ҵ���
					new StringBuffer("select ")
							.append(" a.waternum ")				// �˵���ˮ��
							.append(",sum(b.PartitionMoney) ")	// �������
							.append(",max(a.membercardid) ")	// ����
							.append(",max(hk.GriftMoney) ")		// ������
							.append(",max(hk.CardTypeId) ")		// ����
							.append(" from "+db_str+".dbo.Sn_Consumesellog a ")
							.append(" left join P_PartitionConsumeDetails b on a.waterNum=b.ConsumeId ")
							.append(" left join hk_member hk on hk.Memberid=a.membercardid ")
							.append(" where (1=1) ")
							.append(" and b.PartitionMoney is not null ")
							.append(" and a.BillId = '"+hvo.getVbillcode()+"' ")	// �˵���
							.append(" group by a.waternum ")	// �� �˵�ˮ�� ���л���
			;
		
			ArrayList<Object> list = (ArrayList<Object>)hkjt_hg_pub_session.executeQuery(querySQL.toString(),new ArrayListProcessor());
			
			if( list!=null && list.size()>0 )
			{// ����� ������ϸ��  �Ž��� ����Ĵ���
				
				// 1���Ƚ� ���� ���ܳ� HashMap��  ����ڶ����Ĵ���
				HashMap<String,ZhangdanBVO> bVO_MAP = new HashMap<String,ZhangdanBVO>();	// key-�˵��� ˮ��  value-�˵���VO
				ZhangdanBVO[] bVOs = (ZhangdanBVO[])aggvo.getChildrenVO();	// ����vos
				for(int i=0;i<bVOs.length;i++)
				{
					bVO_MAP.put(bVOs[i].getWaternum(), bVOs[i]);
				}
				
				// 2���� �������ݣ�����ѭ������
				for(int i=0;i<list.size();i++)
				{
					Object[] obj = (Object[])list.get(i);
					String waterNum = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					UFDouble fqje_body = PuPubVO.getUFDouble_NullAsZero(obj[1]);
					
					String ka_code  = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);	// ����
					UFDouble kabili = PuPubVO.getUFDouble_NullAsZero(obj[3]);		// ������
					String ka_type  = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);	// ����
					
					ZhangdanBVO bvo = bVO_MAP.get(waterNum);
					
					if( ka_type!=null && this.FENQU_SR_MAP.containsKey(ka_type))
					{
						/**
						 *  �˵�����
						 *  �Զ���01�����������룩
						 *  �Զ���02����������
						 *  �Զ���03������������
						 */
						bvo.setVbdef01(fqje_body.toString());
						bvo.setVbdef02(ka_code);
						bvo.setVbdef03(kabili.toString());
						
						UFDouble shihou_kabili = fqje_body.multiply(kabili).setScale( 2 , UFDouble.ROUND_HALF_UP );
						UFDouble youhui_kabili = fqje_body.sub(shihou_kabili);
						bvo.setShouru(shihou_kabili);			// ȷ������ = ʵ�� * ������
						bvo.setYouhui_kabili(youhui_kabili);	// �������Ż� = ʵ�� - ȷ������
						
						/**
						 * �˵���ͷ
						 * �� ������� �ۼƵ� ��Ա�������
						 */
						hvo.setHuiyuanka(	// ��Ա����� += ����������
								  PuPubVO.getUFDouble_NullAsZero( hvo.getHuiyuanka() )
							.add( fqje_body )
						);
						hvo.setFenqu( 		// ������� -= ����������
								  PuPubVO.getUFDouble_NullAsZero( hvo.getFenqu() )
							.sub( fqje_body )
						);
						
						hvo.setHuiyuanka_sj(	// ��Ա��ʵ�ʽ�� += ����ȷ������
								  PuPubVO.getUFDouble_NullAsZero( hvo.getHuiyuanka_sj() )
							.add( shihou_kabili )
						);
						
						hvo.setHuiyuanka_bl(	// �������Ż� += ���忨�����Ż�
								  PuPubVO.getUFDouble_NullAsZero( hvo.getHuiyuanka_bl() )
							.add( youhui_kabili )
						);
						
					}
					else
					{
						bvo.setFenqu(fqje_body);
					}
				}
				
			}
			
		} catch (DbException e) {
			throw new BusinessException(e);
		}
		
	}
	
	/**
	 * �˵������������̯����
	 * @param aggvos
	 * @throws BusinessException 
	 */
	public void execBodyfqjeFentan(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException{
		ZhangdanHVO hvo=aggvo.getParentVO();
		//�����˵���� �õ�������Ӧ��Ŀ(����Ŀ����)����
		HashSet<String> fenquItem=getFenQuNameByBillID(session, hvo.getVbillcode(), vpnname, hvo.getDbilldate());
//		HashSet<String> fenquItem=fenqumap.get(hvo.getVbillcode())==null?new HashSet<String>():fenqumap.get(hvo.getVbillcode());
		UFDouble fqje=nullAsZero(hvo.getFenqu());//�˵���ͷ�������
		if(isZero(fqje))return;
		
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//���һ��ʵ�ղ�Ϊ�����
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			if(!getIsTcfs(bvo)&&(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0)&&//ʵ�ղ�Ϊ��
					(fenquItem.contains(bvo.getSqfl_name())||fenquItem.contains(bvo.getSq_name()))){//���ҷ������ƥ�䵽��Ʒ�����ƥ�䵽������Ʒ
				sumShiShou=sumShiShou.add(bvo.getShishou());
				lastNotZeroRow=i;
			}
		}
		boolean isFindfenquItem=false;//�Ƿ�ƥ�䵽�˷�����Ŀ
		UFDouble fqje_temp=UFDouble.ZERO_DBL;//��������ۼ�
		for ( int i=0;i<bvos.length;i++ ) {//����̯
			ZhangdanBVO bvo=bvos[i];
			if(getIsTcfs(bvo)||isZero(bvo.getShishou())||
					(!(fenquItem.contains(bvo.getSqfl_name())||fenquItem.contains(bvo.getSq_name())))){
				bvo.setFenqu(UFDouble.ZERO_DBL);//��null��ֵΪ0
				continue;}
			isFindfenquItem=true;
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� �������=�ܷ������-ǰ���������
				bvo.setFenqu(fqje.sub(fqje_temp));
			}else{
				bvo.setFenqu(bvo.getShishou().div(sumShiShou).multiply(fqje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));// �������=ԭʵ�ս��ռ��ʵ�ս�� ����*�����ѷ������
				fqje_temp=fqje_temp.add(bvo.getFenqu());
			}
		}
		if(!isFindfenquItem){
			 sumShiShou=UFDouble.ZERO_DBL;
			 lastNotZeroRow=-1;//���һ��ʵ�ղ�Ϊ�����
			for (int i=0;i<bvos.length;i++ ) {
				ZhangdanBVO bvo=bvos[i];
				if(!getIsTcfs(bvo)&&(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0)){//ʵ�ղ�Ϊ��
					sumShiShou=sumShiShou.add(bvo.getShishou());
					lastNotZeroRow=i;
				}
			}
			if(isZero(sumShiShou))return;
			fqje_temp=execBodyfqjeFentan(hvo,bvos,lastNotZeroRow,sumShiShou,fqje_temp);
		}
	}
	
	/**
	 * �˵�����������ƥ�䲻��ʱ��̯����
	 * @param aggvos
	 * @throws BusinessException 
	 */
	public UFDouble execBodyfqjeFentan(ZhangdanHVO hvo,ZhangdanBVO []bvos,int lastNotZeroRow,UFDouble sumShiShou,UFDouble fqje_temp) throws BusinessException{
		UFDouble fqje=nullAsZero(hvo.getFenqu());//�˵���ͷ�������
		if(isZero(fqje))return fqje_temp;
		for ( int i=0;i<bvos.length;i++ ) {//����̯
			ZhangdanBVO bvo=bvos[i];
			if(getIsTcfs(bvo)||isZero(bvo.getShishou())){
				bvo.setFenqu(UFDouble.ZERO_DBL);//��null��ֵΪ0
				continue;}
			if(i==lastNotZeroRow){//���һ�в�Ϊ����е� �������=�ܷ������-ǰ���������
				bvo.setFenqu(fqje.sub(fqje_temp));
			}else{
				bvo.setFenqu(bvo.getShishou().div(sumShiShou).multiply(fqje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));// �������=ԭʵ�ս��ռ��ʵ�ս�� ����*�����ѷ������
				fqje_temp=fqje_temp.add(bvo.getFenqu());
			}
		}
		return fqje_temp;
	}
	
	/**
	 * �˵������ⵥ��̯����
	 * @param aggvos
	 */
	public void execBodyMianDanFentan(ZhangdanBillVO aggvo){
		ZhangdanHVO hvo=aggvo.getParentVO();
		UFDouble miandan=hvo.getMiandan()==null?UFDouble.ZERO_DBL:hvo.getMiandan();//�ⵥ
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//���һ��ʵ�ղ�Ϊ�����
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0&&!getIsTcfs(bvo)){
				sumShiShou=sumShiShou.add(bvo.getShishou());
				lastNotZeroRow=i;
			}
		}
		if(sumShiShou.compareTo(UFDouble.ZERO_DBL)==0)return;
		UFDouble miandan_temp=UFDouble.ZERO_DBL;
		for ( int i=0;i<bvos.length;i++ ) {//����̯
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()==null||bvo.getShishou().compareTo(UFDouble.ZERO_DBL)==0||getIsTcfs(bvo)){
				continue;}
			if(i==lastNotZeroRow){//���һ�в�Ϊ����еĽ���̯
				bvo.setAttributeValue(ZhangdanHVO.MIANDAN,miandan.sub(miandan_temp));				
			}else{
				UFDouble ftbl=bvo.getShishou().div(sumShiShou);//��̯����
				bvo.setAttributeValue(ZhangdanHVO.MIANDAN,ftbl.multiply(miandan).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));
				miandan_temp=miandan_temp.add(new UFDouble(bvo.getAttributeValue(ZhangdanHVO.MIANDAN)==null?"0":bvo.getAttributeValue(ZhangdanHVO.MIANDAN).toString()));
			}
			
			
		}
	}
	
	/**
	 * �˵��������ȯ��̯����
	 * @param aggvos
	 */
	public void execBodyDaijinquanFentan(ZhangdanBillVO aggvo){
		ZhangdanHVO hvo=aggvo.getParentVO();
		UFDouble yhq=hvo.getDaijinquan()==null?UFDouble.ZERO_DBL:hvo.getDaijinquan();//����ȯ
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//���һ��ʵ�ղ�Ϊ�����
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0&&!getIsTcfs(bvo)){
				sumShiShou=sumShiShou.add(bvo.getShishou());
				lastNotZeroRow=i;
			}
		}
		if(sumShiShou.compareTo(UFDouble.ZERO_DBL)==0)return;
		UFDouble yhq_temp=UFDouble.ZERO_DBL;
		for ( int i=0;i<bvos.length;i++ ) {//����̯
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()==null||bvo.getShishou().compareTo(UFDouble.ZERO_DBL)==0||getIsTcfs(bvo)){
				continue;}
			if(i==lastNotZeroRow){//���һ�в�Ϊ����еĽ���̯
				bvo.setAttributeValue(ZhangdanBVO.DAIJINQUAN,yhq.sub(yhq_temp));//����ȯ
			}else{
				UFDouble ftbl=bvo.getShishou().div(sumShiShou);//��̯����
				bvo.setAttributeValue(ZhangdanBVO.DAIJINQUAN,ftbl.multiply(yhq).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//�Ż�ȯ
				yhq_temp=yhq_temp.add(new UFDouble(bvo.getAttributeValue(ZhangdanBVO.DAIJINQUAN)==null?"0":bvo.getAttributeValue(ZhangdanBVO.DAIJINQUAN).toString()));
			}
			
			
		}
	}
	
	
	/**
	 * �ֹ��Żݽ���̯����չ
	 * zhangjc
	 * 2015-7-29����11:03:46
	 * void
	 * @throws BusinessException 
	 *
	 */
	public void execBodySgYouHui_kz(ZhangdanBillVO aggvo) throws BusinessException{
		ZhangdanHVO hvo=aggvo.getParentVO();
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//���һ��ʵ�ղ�Ϊ�����
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0&&!getIsTcfs(bvo)){
				sumShiShou=sumShiShou.add(bvo.getShishou());
				lastNotZeroRow=i;
			}
		}
		
		String[][] matchSgyhField=matchSgyhFenTanFields(hvo.getPk_org());
		String []headField=matchSgyhField[0];
		String []bodyField=matchSgyhField[1];
		
		UFDouble []sumField=new UFDouble[headField.length];
		for (int i = 0; i < sumField.length; i++) {
			sumField[i]=UFDouble.ZERO_DBL;
		}
		
		for ( int i=0;i<bvos.length;i++ ) {//�Żݽ���̯
			ZhangdanBVO bvo=bvos[i];
			if(isZero(bvo.getShishou())||isZero(sumShiShou)||getIsTcfs(bvo))continue;
			UFDouble bili=bvo.getShishou().div(sumShiShou);//����
			
			for (int j = 0; j < sumField.length; j++) {
				sumField[j]=execBodyJineFentan(hvo,bvo,i,lastNotZeroRow,bili,sumField[j],headField[j],bodyField[j]);//��չ  ��������̯
				setShiShou(aggvo);
			}
		}
		
	}
	
	/**
	 * ����ǽ���ͷ���������ʵ�գ��Żݺ�ֱ�ӷ�̯�����壬ֻ���ڴ�д��������ֶμ���
	 * ��Ӱ��ʵ��
	 * zhangjc
	 * 2015-7-29����10:02:40
	 * String[][]
	 * 0 ��ͷ�ֶ���������
	 * 1 �����ֶ���������
	 * 2 ƥ�����������
	 */
	private String[][] matchDirectFenTanFields() {
		String text="Ʊ";
		String[][] field=new String[][]{
//��ͷ�ֶ���������	    
{ZhangdanHVO.ZHIPIAO},
//�����ֶ���������
{ZhangdanBVO.ZHIPIAO},
// ƥ�����������
{"֧"+text}//֧Ʊ
	};
		return field;
	}
	
	
	/**
	 * zhangjc
	 * �ֹ��Ż���չ�ֶν���̯
	 * 2015-7-29����10:02:40
	 * String[][]
	 * 0 ��ͷ�ֶ���������
	 * 1 �����ֶ���������
	 * 2 ƥ�����������
	 * @throws BusinessException 
	 * @throws DAOException 
	 */
	private String[][] matchSgyhFenTanFields(String pk_org) throws BusinessException{
		
//		String[][] field=new String[][]{
//{ZhangdanHVO.YOUHUI_QT},//�����Ż�ʼ�շ������һ��
//{ZhangdanBVO.YOUHUI_QT},
//{"�Ż�"}
//	};
		return getSgYHFields(HKJT_PUB.PK_ORG_HUIGUAN);
	}
	
	HashMap<String,String[][]> sgyhmap=null;
	public String[][] getSgYHFields(String pk_org) throws BusinessException{
		if(sgyhmap!=null&&sgyhmap.containsKey(pk_org)){
			return sgyhmap.get(pk_org);
		}else{
			sgyhmap=new HashMap<String,String[][]>();
			String sql="select decode(name,'�����Ż�',vdef2,name) name,decode(name,'�����Ż�','youhui_qt',vdef1) vdef1 from hk_srgk_hg_jzfs where nvl(dr,0)=0 and pk_parent"+ 
					" in(select pk_hk_srgk_hg_jzfs from hk_srgk_hg_jzfs where nvl(dr,0)=0 and pk_org='"+pk_org+"' and name='�ֹ��Ż�')"+
					" and name not in('����ȯ','�ο�','�ⵥ','�������') "+
					" and (decode(name,'�����Ż�',vdef2,'') <> '~' or (decode(name,'�����Ż�','',name)<>'~' and vdef1<>'~'))";

			Vector<Vector<String>> vector=(Vector<Vector<String>>)getBaseDAO().executeQuery(sql, new VectorProcessor());
			String [] youhui_qt_names=new String[]{};
			ArrayList<String[]> list=new ArrayList<String[]>();
			for (Vector<String> v : vector) {
				if(ZhangdanHVO.YOUHUI_QT.equals(v.elementAt(1))){//������Ż�-��������ȡ���Ż���Ŀ���ƣ�vdef2��
					youhui_qt_names=v.elementAt(0).split("��");
				}else{
					list.add(new String[]{v.elementAt(1),v.elementAt(1),v.elementAt(0)});
				}
			}
			for (int i = 0; i < youhui_qt_names.length; i++) {
				String string = youhui_qt_names[i];
				list.add(new String[]{ZhangdanHVO.YOUHUI_QT,ZhangdanBVO.YOUHUI_QT,string});
			}
			
			
			ArrayList<String> headField=new ArrayList<String>();
			ArrayList<String> bodyField=new ArrayList<String>();
			ArrayList<String> contentField=new ArrayList<String>();
			for (String []str:list) {
				headField.add(str[0]);
				bodyField.add(str[1]);
				contentField.add(str[2]);
			}
			
			String[][] fields=new String[][]{
					headField.toArray(new String[]{}),
					bodyField.toArray(new String[]{}),
					contentField.toArray(new String[]{})
			};
			sgyhmap.put(pk_org, fields);
			return fields;
		}
		
	}
	
	/**
	 * ����СƱ��Ϣ��������Ա�������Ϣ��ֵ
	 * @param hvos
	 * @param session
	 * @throws BusinessException
	 */
	public void jieXiHuiYuanKa(ArrayList<ZhangdanHVO> hvos,JdbcSession session,HashMap<String,String> infoMap) throws BusinessException{
		HashMap<String,UFDouble> kblMaps=getKaBiliByCardType(session);
		String[][] wlxx=getJzfs_WangLai();//���˷�ʽ ���� �ж���� СƱ������Ӧ ֵ��vdef3��  ;//"�Ź���Ʊ","΢��֧��",
		for (ZhangdanHVO hvo : hvos) {
			String context=hvo.getPk_hk_dzpt_hg_zhangdan()==null?"":hvo.getPk_hk_dzpt_hg_zhangdan();
			hvo.setPk_hk_dzpt_hg_zhangdan(null);
		if(hvo.getHuiyuanka_info()!=null){
			String[] cards=hvo.getHuiyuanka_info().split(",");
			StringBuffer context_after=new StringBuffer();
			UFDouble sumXiaoFei=UFDouble.ZERO_DBL;//���ſ����ѽ��ϼ�
			UFDouble sumXiaoFei_sj=UFDouble.ZERO_DBL;//���ſ�ʵ�ʽ��ϼ�
			for (String str : cards) {
				Pattern p = Pattern.compile(str.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)")+"\\s*\\S*\\s*����:\\s*(-?\\d+)(\\.\\d+)?");
		        Matcher m = p.matcher(context);
		        boolean flag=false;//�Ƿ�ƥ�䵽
		        context_after.append(str+" ");//������
		        while (m.find()) {
		        	flag=true;
		        	String xfjestr=m.group().substring(m.group().indexOf("����:")+3, m.group().length());//��ȡ�����ѽ��
		        	UFDouble xfje=nullAsZero(xfjestr);//���ѽ��
		        	context_after.append(xfje);//���ѽ��
		        	sumXiaoFei=sumXiaoFei.add(xfje);//�����ѽ���ۼ�
		        	
		        	UFDouble kbl=kblMaps.get(str.substring(0, str.indexOf(" ")));//������
			       context_after.append(" "+kbl);
			       context_after.append(";");
			       
			      UFDouble sjje=xfje.multiply(nullAsZero(kbl));//��Ա��ʵ�ʽ��=���ѽ��*������
			       sumXiaoFei_sj=sumXiaoFei_sj.add(sjje);//ʵ�ʽ���ۼ�
		        	break;//ʵ��ֻѭ����һ��
		        } 
		        if(!flag){//���û��ƥ�䵽�������ѽ�ֵΪ0
		        	context_after.append(0);
		        	UFDouble kbl=kblMaps.get(str.substring(0, str.indexOf(" ")));//������
				       context_after.append(" "+kbl);
				       context_after.append(";");
		        }
		        
			}
			if(context_after.toString().endsWith(";"))
				context_after.deleteCharAt(context_after.length()-1);
			hvo.setHuiyuanka(sumXiaoFei.setScale(DECIMALBIT,UFDouble.ROUND_HALF_UP));//�����ѽ��ϼ�
			hvo.setHuiyuanka_sj(sumXiaoFei_sj.setScale(DECIMALBIT,UFDouble.ROUND_HALF_UP));//������ʵ�ʽ��ϼ�
			hvo.setHuiyuanka_bl((hvo.getHuiyuanka().sub(hvo.getHuiyuanka_sj())).setScale(DECIMALBIT,UFDouble.ROUND_HALF_UP));//��Ա���Ż�=�����ѽ��-ʵ�ʽ��
			hvo.setHuiyuanka_info(context_after.toString());//����Ϣ
		}
		
		
		String []jzfsFields=new String[]{ZhangdanHVO.XIANJIN,ZhangdanHVO.MIANDAN,ZhangdanHVO.DAIJINQUAN,ZhangdanHVO.FENQU,ZhangdanHVO.POS,ZhangdanHVO.DAIJINQUAN,ZhangdanHVO.DAIJINQUAN};
		String []jzfsNames=new String[]{"�ֽ�","�ⵥ","����ȯ","�������","���ÿ�","����Ʊ","ԡ��ȯ"};
		
		matchMoney(hvo, context,addStrArray(wlxx[0], jzfsFields),addStrArray(wlxx[1], jzfsNames));
		String [][] sgyhfields=matchSgyhFenTanFields(infoMap.get("pk_org"));
		matchMoney(hvo, context,sgyhfields[0],sgyhfields[2]);//�ֹ��Ż���չ�������Ż� һֱ�������
		matchMoney(hvo, context,matchDirectFenTanFields()[0],matchDirectFenTanFields()[2]);
		
		}
	}

	/**
	 * ƥ��СƱ���,����ֵ��VO��Ӧ�ֶ�
	 * @param hvo
	 * @param context
	 */
	public void matchMoney(SuperVO hvo, String context,String field[],String []matchMoneyName) {
		for (int i = 0; i < field.length; i++) {
			Pattern p = Pattern.compile(matchMoneyName[i]+"\\s*:\\s*(-?\\d+)(\\.\\d+)?");
	        Matcher m = p.matcher(context);
	        while (m.find()) {
	        	String xj=m.group().substring(m.group().indexOf(":")+1);	// ��� ����ƥ������� ���
	        	//---����������Ż����-----
	        	if(ZhangdanHVO.YOUHUI_QT.equals(field[i])&&hvo.getAttributeValue(field[i])!=null&&hvo.getAttributeValue(field[i]).toString().trim().length()>0){//����������Ż���ֵ�ۼ�
	        	hvo.setAttributeValue(field[i],new UFDouble(hvo.getAttributeValue(field[i]).toString()).add(nullAsZero(xj)));
	        	}
	        	//---����������ȹ� ����ͣ����Ҹ�ֵ������ϸΪ�����ȹ�-----
//	        	else if(ZhangdanHVO.GUAZHANG.equals(field[i])){
//	        	hvo.setAttributeValue(field[i],nullAsZero(hvo.getAttributeValue(field[i])).add(nullAsZero(xj)));
//	        	// ��������Ϣ��  ���ҡ����ˡ�ȥ���� ֻ���� �������
//	        	String guazhang_info = matchMoneyName[i];
//	        	guazhang_info = guazhang_info.replaceAll("����", "");
//	        	guazhang_info = guazhang_info.replaceAll("��", "");
//	        	hvo.setAttributeValue(ZhangdanHVO.GUAZHANG_INFO, (hvo.getAttributeValue(ZhangdanHVO.GUAZHANG_INFO)==null?"": hvo.getAttributeValue(ZhangdanHVO.GUAZHANG_INFO).toString()+",")+guazhang_info);
//	        	}
	        	else if(ZhangdanHVO.WANGLAI.equals(field[i])){
	        		hvo.setAttributeValue(field[i],nullAsZero(hvo.getAttributeValue(field[i])).add(nullAsZero(xj)));
	        		hvo.setAttributeValue(ZhangdanHVO.WANGLAI_INFO, (hvo.getAttributeValue(ZhangdanHVO.WANGLAI_INFO)==null?"": hvo.getAttributeValue(ZhangdanHVO.WANGLAI_INFO).toString()+",")+matchMoneyName[i]);
	        		/**
	        		 * ����
	        		 * 2017��7��19��16:33:12
	        		 * ���� ΢��֧��  �� ֧����֧�� �� ����
	        		 * ΢��֧�� �� vdef01
	        		 * ֧����֧�� �� vdef02
	        		 * 
	        		 * 2017��10��16��11:10:24
	        		 * ������  
	        		   	΢��֧��
						֧����֧��
						Ӧ������
						Ӧ��Я��
						�Ź���Ʊ
	        		 */
//	        		if(	"΢��֧��".equals(matchMoneyName[i])
//	        		||	"WX".equals(matchMoneyName[i])
//	        		  )
//	        		{
//	        			hvo.setAttributeValue(ZhangdanHVO.VDEF01,xj);
//	        		}
//	        		else 
//	        		if( "֧����֧".equals(matchMoneyName[i]) || "֧����֧��".equals(matchMoneyName[i]) )
//	        		{
//	        			hvo.setAttributeValue(ZhangdanHVO.VDEF02,xj);
//	        		}
//	        		else 
//	        			if( "Ӧ������".equals(matchMoneyName[i]) )
//	        			{
//	        				hvo.setAttributeValue(ZhangdanHVO.VDEF03,xj);
//	        			}
//        			else 
//        				if( "Ӧ��Я��".equals(matchMoneyName[i]) )
//        				{
//        					hvo.setAttributeValue(ZhangdanHVO.VDEF04,xj);
//        				}
//    				else 
//    					if( "�Ź���Ʊ".equals(matchMoneyName[i]) )
//    					{
//    						hvo.setAttributeValue(ZhangdanHVO.VDEF05,xj);
//    					}
	        		
	        		/**
	        		 * HK-����2
	        		 * 2019��1��8��18:11:57
	        		 */
	        		boolean isBreak = false;
	        		for(int k=0;k<VDEF_Info_code.length;k++)
	        		{
	        			String[] VDEF_name = VDEF_Info.get(VDEF_Info_code[k]);
	        			for(int kk=0;kk<VDEF_name.length;kk++)
	        			{
	        				if(	VDEF_name[kk].equals(matchMoneyName[i]) )
    		        		{
    		        			hvo.setAttributeValue(VDEF_Info_code[k],xj);
    		        			isBreak = true;
    		        			break;
    		        		}
	        			}
	        			if(isBreak)
	        				break;
	        		}
	        		/**END*/
	        		
	        	}else if(ZhangdanHVO.DAIJINQUAN.equals(field[i])){
	        		hvo.setAttributeValue(field[i],nullAsZero(hvo.getAttributeValue(field[i])).add(nullAsZero(xj)));
	        	}
	        	else{
	        		hvo.setAttributeValue(field[i],nullAsZero(xj));
	        	}
	        	
	        }
		}
	}
	
	/**
	 * zhangjc
	 * 2015-8-26����4:25:27
	 * String[][] field name
	 *�õ� ���˷�ʽ ������Ϣ��   �Զ�����3��ֵ��СƱ�϶�Ӧ�����ƣ�
	 */
	public String[][] getJzfs_WangLai()throws BusinessException{
		String sql="select distinct vdef3 from hk_srgk_hg_jzfs where nvl(dr,0)=0 and code like('05%') and vdef3 <>'~' and vdef3 is not null and pk_org='"+HKJT_PUB.PK_ORG_HUIGUAN+"' ";
		ArrayList<String> list=(ArrayList<String>)getBaseDAO().executeQuery(sql, new ColumnListProcessor());
		ArrayList<String> names=new ArrayList<String>();
		ArrayList<String> fields=new ArrayList<String>();
		for (String str : list) {
			for (String field : str.split("��")) {//���˷�ʽ-����-vdef3    ��ӦСƱ������
				if(!names.contains(field)){
				names.add(field);
				fields.add(ZhangdanHVO.WANGLAI);
				}
			}
		}
		return new String[][]{fields.toArray(new String[0]),names.toArray(new String[0])};
	}
	
	/**
	 * zhangjc
	 * 2015-8-26����4:36:50
	 * String[]
	 *�ϲ������ַ�������
	 */
	public String[] addStrArray(String[] str1,String[]str2){
		ArrayList<String> list=new ArrayList<String>();
		for (String s1 : str1) {
			list.add(s1);
		}
		for (String s2 : str2) {
			list.add(s2);
		}
		return list.toArray(new String[]{});
	}
	/**
	 * �õ�NCϵͳ������Ʒ������Ϣ
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,SpflHVO> getAllSpfl(String pk_org_spfl) throws DAOException{
		HashMap<String,SpflHVO> map=new HashMap<String, SpflHVO>();
		String sql=" select * " +
				" from hk_srgk_hg_spfl " +
				" where dr=0 " +
				" and pk_org='"+pk_org_spfl+"' " +
				" and code not like 'LY0%' ";	// HK 2020��3��12��11:04:32 �������Ƶ���Ʒ����  ���и��룩
		ArrayList<SpflHVO> list=(ArrayList<SpflHVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(SpflHVO.class));
		for (SpflHVO hvo : list) {
			map.put(hvo.getPk_org()+"@@"+hvo.getName(), hvo);//�õ���Ʒ����VO
		}
		
		return map;
		
	}
	
	/**
	 * �õ�NCϵͳ���в�����Ϣ
	 * ����pk���ϼ�����pk
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String[]> getAllBmxx() throws DAOException{
		HashMap<String,String[]> map=new HashMap<String, String[]>();
		String sql="select pk_org,def1,pk_dept,pk_fatherorg from org_dept";
		Vector<Vector<String>> vector=(Vector<Vector<String>>)getBaseDAO().executeQuery(sql, new VectorProcessor());
		for (Vector<String> v : vector) {
			
			String key = v.elementAt(1);
			String[] keys = null;
			if(key!=null && key.indexOf("��")>-1)
			{	// ��� ���� �ָ�������˵�� �� ���ҵ���� ��Ӧһ��NC���š� ��Ҫ��ִ���
				keys = key.split("��");
			}
			else
			{
				keys = new String[]{key};
			}
			
			for(int i=0;keys!=null&&i<keys.length;i++)
			{
				map.put(v.elementAt(0)+"@@"+keys[i], new String[]{v.elementAt(2),v.elementAt(3)});//�õ�����pk���ϼ�����pk
			}
		}
		
		return map;
		
	}
	/**
	 * �õ�NCϵͳ���в�����Ϣ
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String[]> getAllBmNameByPk() throws DAOException{
		HashMap<String,String[]> map=new HashMap<String, String[]>();
		String sql="select pk_dept,name,pk_fatherorg from org_dept";
		Vector<Vector<String>> vector=(Vector<Vector<String>>)getBaseDAO().executeQuery(sql, new VectorProcessor());
		for (Vector<String> v : vector) {
			map.put(v.elementAt(0),new String[]{v.elementAt(1),v.elementAt(2)});//�õ��������ƣ��ϼ�����pk
		}
		
		return map;
		
	}
	/**
	 * ���ݵ��ݺ����˵���ʱ��hk_srgk_hg_zhangdan_temp����ȡ��СƱ��Ϣ
	 * @param vbillcode
	 * @return
	 * @throws BusinessException
	 */
	public String getContentByVbillcode(String vbillcode) throws BusinessException {
		
		String sql="select context from hk_srgk_hg_zhangdan_temp where billid='"+vbillcode+"'";
		Clob clob=(Clob)getBaseDAO().executeQuery(sql, new ColumnProcessor());
		 if(clob==null)return "";
		 StringBuffer sb = new StringBuffer();
        Reader is = null;
        try {
            is = clob.getCharacterStream();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // �õ���
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        while (s != null) {
            //ִ��ѭ�����ַ���ȫ��ȡ����ֵ��StringBuffer
            sb.append(s);
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
	
	
	/**
	 * ���ݻ�Ա�����͵õ�������
	 * @param session
	 * @return
	 * @throws DbException 
	 */
	public HashMap<String,UFDouble> getKaBiliByCardType(JdbcSession session) throws BusinessException{
		HashMap<String, UFDouble> map=new HashMap<String,UFDouble>();
		try {
		String sql="select CardTypeName,GriftMoney from Dt_CardInfo";
		Vector<Vector> vector= (Vector<Vector>)session.executeQuery(sql, new VectorProcessor());
		for (Vector v : vector) {
			map.put(v.elementAt(0).toString(),v.elementAt(1)==null?UFDouble.ZERO_DBL:new UFDouble(v.elementAt(1).toString()));
		}
		} catch (DbException e) {
			throw new BusinessException(e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * �����˵����
	 * �õ�������Ӧ��Ŀ����(Ҳ��������Ŀ��������)
	 * @param session
	 * @param billid    SN201506060094-06
	 * @param vpnname   ��ɽ
	 * @param billDate  2015-07-02 08:00:00
	 * @return
	 * @throws BusinessException
	 */
	public HashSet<String> getFenQuNameByBillID(JdbcSession session,String billid,String vpnname,UFDate billDate) throws BusinessException{
		HashSet<String> set=new HashSet<String>();
		try {
		String sql="select  distinct GoodsId,GoodsName \n" +
	"from P_PartitionGoods a\n" + 
	"left join P_PartitionMember b on a.PartitionId=b.PartitionId\n" + 
	"where b.MemberGuid in (select distinct MemberGuid from Dt_MemberCardComeIn  where BillId like '"+billid+"')\n" + 
	"and vpnname like '%"+vpnname+"%'\n" + 
	//"and '"+billDate+"' >= BeginTime\n" + 
	//"and '"+billDate+"' <= EndTime" +
		"";
		
		Vector<Vector<String>> vector= (Vector<Vector<String>>)session.executeQuery(sql, new VectorProcessor());
		for (Vector<String> v : vector) {
			set.add(v.elementAt(1));
		}
		} catch (DbException e) {
			throw new BusinessException(e.getMessage());
		}
		return set;
	}
	
	
	/**
	 * �����˵����
	 * �õ�������Ӧ��Ŀ����(Ҳ��������Ŀ��������)
	 * @param session
	 * @param billid    SN201506060094-06
	 * @param vpnname   ��ɽ
	 * @param billDate  2015-07-02 08:00:00
	 * @return
	 * @throws BusinessException
	 * @throws DbException 
	 */
	public HashMap<String,HashSet<String>> getFenQuNameByBillIDs(JdbcSession session,ArrayList<ZhangdanHVO> hvos,String vpnname) throws BusinessException, DbException{
		StringBuffer vbillcodes=new StringBuffer();
		HashMap<String,UFDate> map0=new HashMap<String, UFDate>();
		if(hvos.size()>0){
			vbillcodes.append(" and ( c.billid in (");
		for (int i = 0; i < hvos.size(); i++) {
			String vbillcode = hvos.get(i).getVbillcode();
			vbillcodes.append("'"+vbillcode+"',");
			if(i!=0&&i!=hvos.size()-1&&i%900==0){
				vbillcodes.delete(vbillcodes.length()-1, vbillcodes.length());
				vbillcodes.append(") or c.billid in (");
			}
			map0.put(vbillcode,  hvos.get(i).getDbilldate());
		}
		vbillcodes.delete(vbillcodes.length()-1, vbillcodes.length());
		vbillcodes.append("))");
		}else{
			vbillcodes.append(" and 1=2 ");
		}
		
		StringBuffer sbsql=new StringBuffer();
		sbsql.append("select  distinct c.billid,CONVERT(varchar, b.BeginTime, 120) begintime,CONVERT(varchar, b.endtime, 120) endtime,GoodsName  from P_PartitionGoods a") 
		.append(" left join P_PartitionMember b on a.PartitionId=b.PartitionId")
		.append(" inner join Dt_MemberCardComeIn c on b.MemberGuid=c.MemberGuid")
		.append(" where 1=1 ")
		.append(vbillcodes)
		.append(" and a.vpnname like '%"+vpnname+"%'");
		
		ArrayList<FenQuVO> fenquvos=(ArrayList<FenQuVO>)session.executeQuery(sbsql.toString(), new BeanListProcessor(FenQuVO.class));
		HashMap<String,ArrayList<FenQuVO>> map1=new HashMap<String, ArrayList<FenQuVO>>();
		for (FenQuVO fenQuVO : fenquvos) {
			if(map1.containsKey(fenQuVO.getBillid())){
				ArrayList<FenQuVO> list=map1.get(fenQuVO.getBillid());
				list.add(fenQuVO);
				map1.put(fenQuVO.getBillid(), list);
			}else{
				ArrayList<FenQuVO> list=new ArrayList<FenQuVO>();
				list.add(fenQuVO);
				map1.put(fenQuVO.getBillid(), list);
			}
		}
		
		
		HashMap<String,HashSet<String>>  billidAndBilldate=new HashMap<String,HashSet<String>> ();
		boolean ischeckDate=false;//�Ƿ���鵥��ҵ������ �ڷ��������������Ч����
		
		for (String billid : map1.keySet()) {
			ArrayList<FenQuVO> fenqu=map1.get(billid);
			HashSet<String> set=new HashSet<String>();
			for (FenQuVO fenQuVO : fenqu) {
				if(ischeckDate){
				UFDateTime billdate=new UFDateTime(map0.get(billid),new UFTime("00:00:00"));
				if(billdate.compareTo(fenQuVO.getBegintime())!=-1&&billdate.compareTo(fenQuVO.getEndtime())!=1){
					set.add(fenQuVO.getGoodsname());
				}}else{
					set.add(fenQuVO.getGoodsname());
				}
			}
			if(set.size()>0){
				billidAndBilldate.put(billid, set);
			}
		}
		
		
		return billidAndBilldate;
	}
	/**
	 * ִ���ۿ���Ϣͬ���������ϣ�
	 * @author zhangjc
	 *
	 */
	public void ImpShouKaBill(String timeWhere,HashMap<String,String> infoMap,JdbcSession session,StringBuffer bancipk)throws BusinessException, DbException{
		StringBuffer querySql=new StringBuffer();
//		querySql.append("select " )
//				.append(" sa.TurnId,sa.BillId,CONVERT(varchar, sa.OperateDate, 120) OperateDate, " )
//				.append(" case when convert(varchar(10),class.ChangeTime,108)<='00:59:59' " )
//				.append(" then convert(varchar(10),class.ChangeTime-1, 120)+' 00:00:00' " ) 
//				.append(" else convert(varchar(10),class.ChangeTime, 120)+' 00:00:00' " )
//				.append(" end dbilldate, " )
//				.append(" sa.memberid,WaterNum,sc.MemberId MemberId_b " )
//				.append(",sc.NumberCount,sc.Money yingshou,sc.RealMoney shishou " ) 
//				.append(",sc.GoodsCatalogId,sc.GoodsCatalogName " )
//				.append(",sc.GoodsId,sc.GoodsName " ) 
//				.append(",sc.PayMethod " )
//				.append(",sb.Context " ) 
//				.append(",'' pos,'' xianjin,'' youhui " )
//				.append(",'' pk_org " )
//				.append("from Sn_Bill sa " )
//				.append("inner join Sn_BillHistory sb on sa.BillId = sb.Billid " ) 
//				.append("inner join Sn_Consumesellog sc on sa.BillId = sc.BillId " ) 
//				.append("inner join Dt_ChangeClass class " )
//				.append("on (class.changeclassid= sa.TurnId ")
//				.append("and  ").append(timeWhere)
//				.append("and ChangeClassId like '%"+infoMap.get("hg_code")+"%' and class.Opersite = '����') " )
//				.append("order by billid");
		
		
		querySql.append("select " )
		.append(" aa.TurnId,aa.BillId,CONVERT(varchar, aa.OperateDate, 120) OperateDate, " )
				.append(" case when convert(varchar(10),class.ChangeTime,108)<='00:59:59' " )
				.append(" then convert(varchar(10),class.ChangeTime-1, 120)+' 00:00:00' " ) 
				.append(" else convert(varchar(10),class.ChangeTime, 120)+' 00:00:00' " )
				.append(" end dbilldate, " )
				.append(" aa.memberid,sc.WaterNum,sc.MemberId MemberId_b " )
				.append(",sc.NumberCount,sc.Money yingshou,sc.RealMoney shishou " ) 
				.append(",sc.GoodsCatalogId,sc.GoodsCatalogName " )
				.append(",sc.GoodsId,sc.GoodsName " ) 
				.append(",sc.PayMethod " )
				.append(",sb.Context " ) 
				.append(",'' pos,'' xianjin,'' wanglai " )
				.append(",'' pk_org " )
				.append(" from Sn_Bill aa")
				.append(" inner join Sn_BillHistory sb on aa.BillId = sb.Billid")
				.append(" inner join Sn_Consumesellog sc on aa.BillId = sc.BillId ")
				.append(" left join KF_HouseInfo bt on sc.batai = bt.storeid ")
				.append(" left join Dt_GoodCatalog gc on gc.CatalogId = sc.GoodsCatalogId")
				.append(" left join Sn_JiShi js on js.JishiCode=sc.JiShi")
				.append(" inner join Dt_ChangeClass class on (class.changeclassid= aa.TurnId and ")
				.append(timeWhere).append(" ) ")
				.append(" where 1=1 ")
				.append(bancipk)
				.append(" and ltrim(aa.Remark) != '����'")
				.append(" and OldMoney != 0.00 ")
				.append(" and gc.NodeName in('��Ա��','�ײͷ���')")
				.append("order by aa.billid");

		ArrayList<CaiWuChongZhiVO> chongzhiList=(ArrayList<CaiWuChongZhiVO>)session.executeQuery(querySql.toString(), new BeanListProcessor(CaiWuChongZhiVO.class));
		String sql="select billid,waternum from hk_srgk_hg_caiwu where nvl(dr,0)=0";
		Vector<Vector<String>> vector=(Vector<Vector<String>>)getBaseDAO().executeQuery(sql, new VectorProcessor());
		Set<String> set=new HashSet<String>();
		for (Vector<String> v : vector) {
			set.add(v.elementAt(1));
		}
		
		ArrayList<CaiWuChongZhiVO> lastchongzhiList=new ArrayList<CaiWuChongZhiVO>();
		for (CaiWuChongZhiVO caiWuChongZhiVO : chongzhiList) {
			if(set.contains(caiWuChongZhiVO.getWaternum())){
				continue;
				}
			matchMoney(caiWuChongZhiVO, caiWuChongZhiVO.getContext(),new String[]{
				CaiWuChongZhiVO.POS,CaiWuChongZhiVO.XIANJIN,CaiWuChongZhiVO.WANGLAI},
						new String[]{"���ÿ�","�ֽ�","�Ź���Ʊ"});
			caiWuChongZhiVO.setPk_org(infoMap.get("pk_org"));
			lastchongzhiList.add(caiWuChongZhiVO);
		}
		
		getBaseDAO().insertVOList(lastchongzhiList);//����ֵ��Ϣ�洢��NC���ݱ�
	}
	/**
	 * �ж��Ƿ����0��null��Ϊ��
	 * zhangjc
	 * 2015-7-27����11:22:53
	 * boolean
	 *
	 */
	public UFDouble nullAsZero(UFDouble ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:ufDouble;
	}
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}
	
	
	/**
	 * �����˵���  ��ȡ�� �����˵�
	 * ���  2016��8��26��14:59:00
	 */
	public ZhangdanBillVO[] getBillByZDH(String billno,String pk_org) throws BusinessException{
	
//		cleanTempTable();
		
		this.get_VDEF_Info();	// ��HK 2019��1��25��20:08:35��
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		Connection hkjt_hg_zd_conn=null;
		JdbcSession hkjt_hg_zd_session =null;
		
		try {
			HashMap<String,String> infoMap=getDefaultInfo(pk_org);//�õ����ñ���Ϣ
			
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
			
			hkjt_hg_zd_conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_zd_session = new JdbcSession(hkjt_hg_zd_conn);
			
			//ҵ��ϵͳ�˵�������Ϣ
			String sql2="select aa.TurnId,aa.BillId,CONVERT(varchar, aa.OperateDate, 120) OperateDate,ah.Context,aa.OldMoney,aa.FavourMoney,aa.Shishou,aa.MemberId "
					+" ,CONVERT(varchar, cc.ChangeTime, 120) ChangeTime "	// ����ʱ��
					+" from Sn_Bill aa"		// �˵�����
					+" left join Sn_BillHistory ah on aa.BillId = ah.Billid"	// СƱ��
					+" left join Dt_ChangeClass cc on cc.ChangeClassId = aa.TurnId "	// �����
					+" where 1=1 " 
					+" and aa.BillId = '" +billno+ "' "	// �˵���
					+" and ltrim(aa.Remark) != '����'"
					+" and OldMoney != 0.00";
				
			ArrayList<ZhangDanH_TempVO> list2=(ArrayList<ZhangDanH_TempVO>)hkjt_hg_zd_session.executeQuery(sql2, new BeanListProcessor(ZhangDanH_TempVO.class));
			
			if(list2==null || list2.size()<1) return null;
			
			insertVOS(list2);
			
			//ҵ��ϵͳ�˵��ӱ���Ϣ
			String sql3="select " +
					" ab.WaterNum " +	// ��ˮ��
					",aa.BillId " +		// �˵���
					",ab.GoodsName " +	// ��Ʒ
					",gc.NodeName GoodsCatalogName " +	// ��Ʒ����
			//		",case when bt.StoreName='��ʦ��' and isnull(js.ImportLevel,'null')<>'null' then js.ImportLevel else bt.StoreName end StoreName" +	// ��ʦ������Ʒ û�����ϼ�ʦ�����Ի��Ƿ��ڼ�ʦ��   �����  2015��7��22��15:25:31��
					",case when isnull(js.ImportLevel,'null')<>'null' and js.ImportLevel<>''  then js.ImportLevel else bt.StoreName end StoreName " + 	// �����ϼ�ʦ��  ��ȡ��ʦ�Ĳ��ţ� û�����ϵ�  ��ȡ�˵��Ĳ���
					",CONVERT(varchar, ab.Starttime, 120) Starttime" +
					",ab.Status status_type " +
					",ab.KeyId " +
					/**
					 * ��Ϊ����ƾ֤���� ��С��β������⣬ ������Ҫ ȡ������ݵ�ʱ��  �ͽ�ȡ�� ��λС��
					 * ���  2016��5��15��15:42:38
					 */
			//		",round(ab.Money,6) Money " +
			//		",round(ab.RealMoney,6) RealMoney " +
					",round(ab.Money,2) Money " +
					",round(ab.RealMoney,2) RealMoney " +
					/**END*/
					",js.ImportLevel " +
					",ab.membercardid mebercardid " +
					",ab.numbercount numberxount "
					+" from Sn_Bill aa "
					+" inner join Sn_Consumesellog ab on aa.BillId = ab.BillId "
					+" left join KF_HouseInfo bt on ab.batai = bt.storeid " 
					+" left join Dt_GoodCatalog gc on gc.CatalogId = ab.GoodsCatalogId "
					+" left join Sn_JiShi js on js.JishiCode=ab.JiShi "
					+" where 1=1 " 
					+" and aa.BillId = '" +billno+ "' "	// �˵���
					+" and ltrim(aa.Remark) != '����' "
					+" and OldMoney != 0.00 " +
					 "";
			ArrayList<ZhangDanB_TempVO> list3=(ArrayList<ZhangDanB_TempVO>)hkjt_hg_zd_session.executeQuery(sql3, new BeanListProcessor(ZhangDanB_TempVO.class));
			insertVOS(list3);//���˵��ӱ�洢����ʱ��
			
			/**
			 * ���ݽ���ʱ�䣬 ���Ƴ� ҵ������
			 * 01:00:00 �� 00:59:59  Ϊ ����
			 */
			String changeTime = list2.get(0).getChangetime();
//			String date_str = changeTime.substring(0, 10);
//			String hh_str = changeTime.substring(11, 13);
//			if( "23".equals(hh_str) )
//			{// ��� ����ʱ�� Ϊ 23��ű�  ��Ϊ�� ��һ���ҵ��
//				date_str = (new UFDate(date_str).getDateAfter(1)).toString().substring(0,10);
//			}
			
			// ���ݰ�α� ��ȷ������ʱ�䣨��8�� �� �ڶ������8��  ���� ����ģ�
			
			// ���� ��κ�  ��λ ����ʱ��
			String TurnId = list2.get(0).getTurnid();
			String date_str = TurnId.substring(3, 7) + "-" + TurnId.substring(7, 9) + "-" + TurnId.substring(9, 11);
			String index_str = TurnId.substring(14, 15);	// �������
			String hour_str = changeTime.substring(11, 13);	// Сʱ  ��05��06��07��08��09��
			HashMap<String,String> MAP_1 = new HashMap<String,String>(); // 1�ε����� Сʱ��Χ
			MAP_1.put("04", "04");
			MAP_1.put("05", "05");
			MAP_1.put("06", "06");
			MAP_1.put("07", "07");
			MAP_1.put("08", "08");
			MAP_1.put("09", "09");
			if( "1".equals( index_str ) 
			&& ! MAP_1.containsKey(hour_str)
			  )
			{// �����1  ���Ҳ���  ������Χ�ڣ� �� ��Ϊ��ǰһ��
				date_str = (new UFDate(date_str).getDateBefore(1)).toString().substring(0,10);
			}
			
			/**END*/
			
			String sql4="select temp.TURNID banci,temp.billid vbillcode,temp.context pk_hk_dzpt_hg_zhangdan" +
					",temp.OLDMONEY yingshou,temp.OPERATEDATE creationtime,'"+date_str+"' dbilldate,temp.SHISHOU" +
					",temp.FAVOURMONEY,temp.MEMBERID huiyuanka_info from hk_srgk_hg_zhangdan_temp temp " +
					" left join  (select vbillcode from hk_srgk_hg_zhangdan " +
					" where pk_org='"+infoMap.get("pk_org")+"' " +
					" and vbillcode = '" + billno + "' " +
					" and nvl(dr, 0) = 0 ) hk_srgk_hg_zhangdan on (temp.billid=hk_srgk_hg_zhangdan.vbillcode) " +
					" where hk_srgk_hg_zhangdan.vbillcode is null";
			ArrayList<ZhangdanHVO> list4=(ArrayList<ZhangdanHVO>)getBaseDAO().executeQuery(sql4, new BeanListProcessor(ZhangdanHVO.class));
			jieXiHuiYuanKa(list4,hkjt_hg_pub_session,infoMap);//����СƱ��Ϣ
			ZhangdanBillVO[] aggvos=getZhangDanAggVOs(infoMap,list4,infoMap.get("pk_org"),hkjt_hg_pub_session);//����ҵ��ϵͳ�˵� ��ͷ��Ϣ��װNCϵͳ�˵��ۺ�VO����
			IHg_zhangdanMaintain itf=NCLocator.getInstance().lookup(IHg_zhangdanMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
			if(aggvos!=null&&aggvos.length>0)
			{
				ZhangdanBillVO[] result = itf.insert(aggvos, null);
				if( result!=null && result.length>0 && result[0].getParentVO().getPk_hk_dzpt_hg_zhangdan()!=null )
					return aggvos;
			}
			
			return null;
		
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
			if(hkjt_hg_pub_session!=null)
			hkjt_hg_pub_session.closeAll();
			if(hkjt_hg_zd_session!=null)
			hkjt_hg_zd_session.closeAll();
			JDBCUtils.closeConn(hkjt_hg_pub_conn);
			JDBCUtils.closeConn(hkjt_hg_zd_conn);
			}
		}
	/**
	 * ���ҷ���������� ����
	 */
	private HashMap<String,String> FENQU_SR_MAP;
	private HashMap<String,String> queryFenQuKaXing() throws BusinessException
	{
		
		HashMap<String,String> result = new HashMap<String,String>();
		
		StringBuffer querySQL = 
			new StringBuffer("select ")
					.append(" fqkx.kaxing_code ")
					.append(",fqkx.kaxing_name ")
					.append(" from hk_srgk_hg_fenqukaxing fqkx ")
					.append(" where nvl(fqkx.isused,'Y') in ('Y','y') ")
					.append(" order by kaxing_code ")
		;
		
		ArrayList list = (ArrayList)this.getBaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		for( Object obj : list )
		{
			Object[] obj_temp = (Object[])obj;
			
			result.put(
					PuPubVO.getString_TrimZeroLenAsNull(obj_temp[0])
				  , PuPubVO.getString_TrimZeroLenAsNull(obj_temp[1])
			);
		}
		
		System.out.println("=="+result);
		
		return result;
	}
	
}
