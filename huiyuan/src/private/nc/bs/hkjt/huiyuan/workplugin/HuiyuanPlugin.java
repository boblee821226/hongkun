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

	public static String Plugin_Key = "Plugin_HKJT_huiyuan";	// 后台任务的标识
	public static HashMap<String,String> MAP_dian_corp = new HashMap<String,String>();	// 店 对应 pk_corp
	public static HashMap<String,String> MAP_corp_dian = new HashMap<String,String>();	// pk_corp  对应  店
	public static HashMap<String,String> MAP_dian_flag = new HashMap<String,String>();	// 店 对应 flag
	public static HashMap<String,String> MAP_corp_flag = new HashMap<String,String>();	// pk_corp  对应  flag
	public static HashMap<String,String> MAP_dian_db   = new HashMap<String,String>();	// 店  对应  数据库
	public static String zhengde_pk   = "0001N510000000001SXT";		// 正德pk
	public static String zhengde_pk_v = "0001N510000000001SXS";		// 正德pk_v
	{
		MAP_dian_corp.put("DK", "0001N510000000001SXT");	// 正德国际
		MAP_dian_corp.put("国际", "0001N510000000001SXV");	// 国际会馆
		MAP_dian_corp.put("酒店", "0001N510000000001SY1");	// 康福瑞酒店
		MAP_dian_corp.put("朗丽兹", "0001N510000000001SY3");	// 朗丽兹酒店 
		MAP_dian_corp.put("牡丹", "0001N510000000001SXX");	// 贵宾楼
		MAP_dian_corp.put("上地", "0001N510000000001SXV");	// 国际会馆
		MAP_dian_corp.put("西山", "0001N510000000001SY7");	// 西山温泉
		MAP_dian_corp.put("康福瑞", "0001N510000000001SY5");	// 康福瑞西山
		MAP_dian_corp.put("太申", "0001N5100000000UVI5I");	// 太申（2020年11月9日23:15:18）
		
		MAP_corp_dian.put("0001N510000000001SXV", "国际");
		MAP_corp_dian.put("0001N510000000001SXX", "牡丹");
		MAP_corp_dian.put("0001N510000000001SY7", "西山");
		MAP_corp_dian.put("0001N510000000001SY1", "酒店");
		MAP_corp_dian.put("0001N510000000001SY3", "朗丽兹");
		MAP_corp_dian.put("0001N510000000001SY5", "康福瑞");
		MAP_corp_dian.put("0001N5100000000UVI5I", "太申");
		
		MAP_dian_flag.put("牡丹", "-01");
		MAP_dian_flag.put("国际", "-02");
		MAP_dian_flag.put("西山", "-06");
		MAP_dian_flag.put("酒店", "-04");
		MAP_dian_flag.put("朗丽兹", "-07");
		MAP_dian_flag.put("康福瑞", "-08");
		MAP_dian_flag.put("太申", "-11");
		
		MAP_corp_flag.put("0001N510000000001SXX", "-01");
		MAP_corp_flag.put("0001N510000000001SXV", "-02");
		MAP_corp_flag.put("0001N510000000001SY7", "-06");
		MAP_corp_flag.put("0001N510000000001SY1", "-04");
		MAP_corp_flag.put("0001N510000000001SY3", "-07");
		MAP_corp_flag.put("0001N510000000001SY5", "-08");
		MAP_corp_flag.put("0001N5100000000UVI5I", "-11");
		
		MAP_dian_db.put("牡丹",  "L01.jgmd");
		MAP_dian_db.put("国际",  "L02.jggj");
		MAP_dian_db.put("西山",  "L06.jgxs");
		MAP_dian_db.put("酒店",  "L04.lmt");
		MAP_dian_db.put("朗丽兹", "L07.jgllz");
		MAP_dian_db.put("康福瑞", "L08.jgkfr");
		MAP_dian_db.put("太申", "L11.jgts");
		
	}
	
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
//		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{context.getAlertTypeName()+"_HKJT_huiyuan"});
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{Plugin_Key});
		if(!lock){
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		
		try
		{
			
			String[] pk_orgs = context.getPk_orgs();
			UFBoolean iskx = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iskx"),UFBoolean.FALSE);	// 同步卡型
			UFBoolean iska = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iska"),UFBoolean.FALSE);	// 同步开卡
			UFBoolean iskainfo = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iskainfo"),UFBoolean.FALSE);	// 同步卡信息
			UFBoolean isjg = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isjg"),UFBoolean.FALSE);			// 同步金贵余额
			UFBoolean ishuanka = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("ishuanka"),UFBoolean.FALSE);	// 同步换卡信息
			
			UFBoolean iscikainfo = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iscikainfo"),UFBoolean.FALSE);	// 同步次卡信息
			UFBoolean iscikajg = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("iscikajg"),UFBoolean.FALSE);	// 同步次卡金贵余额
			
			UFDate bdate = PuPubVO.getUFDate( context.getKeyMap().get("bdate") );	// 开始日期
			UFDate edate = PuPubVO.getUFDate( context.getKeyMap().get("edate") );	// 结束日期
			
			String btime_str = PuPubVO.getString_TrimZeroLenAsNull( context.getKeyMap().get("btime") );	// 班次开始时间
			String etime_str = PuPubVO.getString_TrimZeroLenAsNull( context.getKeyMap().get("etime") );	// 班次结束时间
			
			// 如果日期为空， 则默认为 当前日期的 前一天
			if(bdate==null) bdate = new UFDate().getDateBefore(1);
			if(edate==null) edate = new UFDate().getDateBefore(1);
			
			
			if(pk_orgs==null || pk_orgs.length<=0)
			{
				pk_orgs = new String[]{
						 "0001N510000000001SXV"	//国际
						,"0001N510000000001SXX"	//牡丹
						,"0001N510000000001SY7"	//西山
						,"0001N510000000001SY1" //"酒店"
						,"0001N510000000001SY3" //"朗丽兹"
						,"0001N510000000001SY5" //"康福瑞"
						,"0001N5100000000UVI5I" //"太申"
				};
			}
			
			if( iskx.booleanValue() )
			{// 同步卡型
				importKaxing();	
			}
			
			if( iska.booleanValue() )
			{// 同步开卡
				importKaiKa(pk_orgs,bdate,edate);
			}
			
			if( ishuanka.booleanValue() )
			{// 同步 换卡信息
				importHuanka(pk_orgs,bdate,edate);
			}
			
			if( iskainfo.booleanValue() )
			{// 同步 会员卡信息
				importHuiyuanka_info(pk_orgs,bdate,edate,btime_str,etime_str);
			}
			
			if( isjg.booleanValue() )
			{// 同步 金贵余额
				importJGyue(pk_orgs,bdate,edate);
			}
			
			if( iscikainfo.booleanValue() )
			{// 同步 次卡信息
				importCika_info(pk_orgs,bdate,edate,btime_str,etime_str);
			}
			
			if( iscikajg.booleanValue() )
			{// 同步 次卡金贵余额
				importJGyue_ck(pk_orgs,bdate,edate);
			}
			
			
		}catch(Exception ex)
		{ throw new BusinessException(ex);}
		
		return null;
	}

	/**
	 * 用于代码测试
	 * 后台任务无法执行
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		String[] pk_orgs = {
//			"0001N510000000001SY7"	// 西山
//			"0001N510000000001SXV"	// 国际
//			"0001N510000000001SXX"	// "牡丹"
//			"0001N510000000001SY1", // "康福瑞酒店"
//			"0001N510000000001SY3",	// "朗丽兹"
//			"0001N510000000001SY5", // "康福瑞"
			"0001N5100000000UVI5I"	// 太申
		};
		
		UFDate bdate = PuPubVO.getUFDate("2020-11-08");
		UFDate edate = PuPubVO.getUFDate("2020-11-09");
		
		try
		{
			// 同步 次卡信息
//			importCika_info(pk_orgs,bdate,edate,"2019-01-06 23:57:31","2019-01-07 23:57:35");
			// 同步 会员卡信息
//			importHuiyuanka_info(pk_orgs,bdate,edate,"2019-02-26 00:03:27","2019-02-27 00:13:43");
//			importHuiyuanka_info(pk_orgs,bdate,edate,null,null);
			// 同步 开卡
//			importKaiKa(pk_orgs, bdate, edate);
			
			// 同步卡型
			importKaxing();	
			// 同步开卡
			importKaiKa(pk_orgs,bdate,edate);
			// 同步 换卡信息
			importHuanka(pk_orgs,bdate,edate);
			// 同步 会员卡信息
			importHuiyuanka_info(pk_orgs,bdate,edate,null,null);
			// 同步 次卡信息
//			importCika_info(pk_orgs,bdate,edate,null,null);
			
		}catch(Exception ex)
		{
			throw new BusinessException(ex);
		}
		
		return null;
	}
	
	
	/**
	 * 卡型
	 */
	private void importKaxing() throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		StringBuffer query_kaxing = // 查看所有卡型
				new StringBuffer("select CardTypeId,CardTypeName,DefaultMoney,GriftMoney,ShiShou,CardAlias,GroupName,VpnUse,Remark ")
						.append(" from Dt_CardInfo ")
		;
		
		ArrayList<KaxingTempVO> list_kaxing_query = (ArrayList<KaxingTempVO>)hkjt_hg_pub_session.executeQuery(query_kaxing.toString(), new BeanListProcessor(KaxingTempVO.class));
		
		insertVOS(list_kaxing_query);//将卡型插入到NC临时表
		
		// 临时表 关联正式表， 过滤出 未导入过的 卡型。
		StringBuffer query_kaxing_insert = 
				new StringBuffer("select hk_huiyuan_kaxing_temp.* ")
						.append(" from hk_huiyuan_kaxing_temp ")
						.append(" left join hk_huiyuan_kaxing on hk_huiyuan_kaxing_temp.cardtypeid = hk_huiyuan_kaxing.kaxing_code and nvl(hk_huiyuan_kaxing.dr,0)=0 ")
						.append(" where hk_huiyuan_kaxing.pk_hk_huiyuan_kaxing is null ")
		;
		ArrayList<KaxingTempVO> list_kaxing_insert = (ArrayList<KaxingTempVO>)getBaseDAO().executeQuery(query_kaxing_insert.toString(), new BeanListProcessor(KaxingTempVO.class));
		
		// 将 临时表 转换成正式VO
		if(list_kaxing_insert.size()>0)
		{
			KaxingHVO[] kaxingHVO_insert = new KaxingHVO[list_kaxing_insert.size()];
			for( int i=0;i<list_kaxing_insert.size();i++ )
			{
				KaxingTempVO tempVO = list_kaxing_insert.get(i);
				kaxingHVO_insert[i] = new KaxingHVO();
				kaxingHVO_insert[i].setKaxing_code( tempVO.getCardtypeid() );	//卡型编码
				kaxingHVO_insert[i].setKaxing_name( tempVO.getCardtypename() );	//卡型名称
				kaxingHVO_insert[i].setKa_je( tempVO.getDefaultmoney() );	//卡金额
				kaxingHVO_insert[i].setKabili( tempVO.getGriftmoney() );	//卡比例
				kaxingHVO_insert[i].setKa_ss( tempVO.getShishou() );		//实收
				kaxingHVO_insert[i].setCardalias( tempVO.getCardalias() );
				kaxingHVO_insert[i].setGroupname( tempVO.getGroupname() );
				kaxingHVO_insert[i].setVpnuse( tempVO.getVpnuse() );
				kaxingHVO_insert[i].setRemark( tempVO.getRemark() );
				kaxingHVO_insert[i].setKa_zs( nullAsZero( kaxingHVO_insert[i].getKa_je() ).sub( nullAsZero( kaxingHVO_insert[i].getKa_ss() ) ) );	// 赠送金额 = 卡金额-实收
				kaxingHVO_insert[i].setPk_group( AppContext.getInstance().getPkGroup() );	//集团
				kaxingHVO_insert[i].setPk_org(zhengde_pk);		// pk_org 正德pk
				kaxingHVO_insert[i].setPk_org_v(zhengde_pk_v);	// pk_org_v
				kaxingHVO_insert[i].setVbillcode( kaxingHVO_insert[i].getKaxing_code() );
				kaxingHVO_insert[i].setIbillstatus(-1);	// 保存态
				kaxingHVO_insert[i].setCreator(HKJT_PUB.MAKER);
				kaxingHVO_insert[i].setCreationtime(new UFDateTime());
				kaxingHVO_insert[i].setDr(0);
				kaxingHVO_insert[i].setDbilldate(new UFDate());
			}
			this.getBaseDAO().insertVOArray(kaxingHVO_insert);//插入卡型 正式VO
			
			// 清空 临时表
			getBaseDAO().deleteByClause(KaxingTempVO.class, " 1=1 ");
		}
		
	}
	
	
	/**
	 * 会员卡信息，日常同步
	 */
	private void importHuiyuanka_info(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate,String btime_str,String etime_str) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		
		ArrayList<String> list_date = this.getTimeDates(kainfo_bdate,kainfo_edate);
		/** 
		 * 导每天的业务数据，用双层循环
		 * 外循环  是  店
		 * 内循环  是  天
		 */
		for(int org_i=0;org_i<pk_orgs.length;org_i++)
		{// 按店  来 循环处理
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
			String flag = MAP_dian_flag.get(dian);
		
			for (String yw_date : list_date) 
			{// 按天 来 循环处理
				
				//判断，是否导入过
				StringBuffer query_check = 
						new StringBuffer("select kainfo.pk_hk_huiyuan_kainfo ")
								.append(" from hk_huiyuan_kainfo kainfo ")
								.append(" where nvl(kainfo.dr,0)=0 ")
								.append(" and kainfo.pk_org = '"+corp+"' ")		// 公司
								.append(" and substr(kainfo.dbilldate,0,10) = '"+yw_date+"' ")	// 业务日期
				;
				Object check_pk = getBaseDAO().executeQuery(query_check.toString(),new ColumnProcessor());
				
				if(check_pk!=null) continue;	// 如果导入了  就跳过。
				
				String[] yw_time = new String[2];	// 班次时间
				
				if( btime_str!=null && etime_str!=null )
				{// 如果 后台任务参数  设置了  班次开始日期、班次结束日期，   则按设置的走
					yw_time[0] = btime_str;
					yw_time[1] = etime_str;
				}
				else
				{// 根据业务日期  来找 具体时间 （开始、结束）
					yw_time = this.getYwDate(PuPubVO.getUFDate(yw_date), flag);
				}
				
				if( yw_time==null || yw_time.length<2 ) continue;	// 找不到 业务时间 就退出
				
				// 充值、消费、余转
				StringBuffer query_kainfo = 
						new StringBuffer("")
								// 充值、消费
								.append(" SELECT ")
								.append("b.WaterNum,")		// 流水号
								.append("b.Billid,")		// 账单号
								.append("CONVERT(VARCHAR(19),b.FeeInDate,120) FeeInDate,")	// 业务时间
								.append("a.Memberid,")		// 卡号
								.append("b.Money,")			// 卡金额
								.append("b.LeaveCardMoney,")// 卡余额（当时）
								.append("b.TrueMoney,")		// 实收
								.append("b.VpnName,")		// 店名
								.append("b.FeeType,")		// 项目大类
								.append("b.MemberGuid,")	// 卡id
								.append("b.OperatorName,")	// 操作员
								.append("null SourceMemberid,")		// 源卡号
								.append("null SourceLeavemoney,")	// 源卡余额（当时）
								.append("null SourceMemberGuid ")	// 源卡id
								.append("FROM hk_member a ")
								.append("INNER JOIN Dt_MemberCardComeIn b ON a.MemberGuid = b.MemberGuid ")
								.append("WHERE 1=1 ")
								.append(" and b.FeeType in ( '充值','消费' ) ")
								.append(" and Remark<>'非卡结' ")
								.append(" and b.VpnName='"+dian+"' ")	// 店
								.append(" and CONVERT(VARCHAR(19),b.FeeInDate,120) >  '"+yw_time[0]+"' ")
								.append(" and CONVERT(VARCHAR(19),b.FeeInDate,120) <= '"+yw_time[1]+"' ")
								// 余转
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
								.append(",'余转' FeeType ")
								.append(",DesMemberGuid ")
								.append(",Operator ")
								.append(",SourceMemberid ")
								.append(",SourceLeavemoney - TransferMoney ")
								.append(",SourceMemberGuid ")
								.append(" from Dt_CardTransferHistory ")
								.append(" where 1=1 ")
								.append(" and VpnName='"+dian+"' ")	// 店
								.append(" and Remark <> '换卡' ")
								.append(" and remark <> '次卡转移'")	// 次卡转移功能，会在会员卡里插入记录  所以要去掉（李彬 2016年7月31日17:49:02）
								.append(" and CONVERT(VARCHAR(19),OperateDate,120) >  '"+yw_time[0]+"' ")
								.append(" and CONVERT(VARCHAR(19),OperateDate,120) <= '"+yw_time[1]+"' ")
				;
				
				ArrayList<KainfoTempVO> list_kainfo_query = (ArrayList<KainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_kainfo.toString(), new BeanListProcessor(KainfoTempVO.class));
				
				if(list_kainfo_query.size()==0) continue;
				
				insertVOS(list_kainfo_query);//将业务数据插入到NC临时表
				
				// 整理 插入语句
				StringBuffer query_kainfo_insert = 
						new StringBuffer(" select ")
								.append(" kainfo.feeindate ywsj ")				// 业务时间
								.append(",kainfo.feetype xmdl ")				// 项目大类
								.append(",kainfo.billid zdh ")					// 账单号
								.append(",kainfo.memberid ka_code ")			// 卡code
								.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")	// 卡pk
								.append(",ka.ka_name ka_name ")					// 卡name
								.append(",ka.pk_hk_huiyuan_kaxing kaxing_pk ")	// 卡型pk
								.append(",kaxing.kaxing_code kaxing_code ")		// 卡型code
								.append(",kaxing.kaxing_name kaxing_name ")		// 卡型name
								.append(",ka.kabili kabili ")					// 卡比例
								.append(",kainfo.money ka_je ")					// 卡金额
								.append(",kainfo.truemoney ka_ss ")				// 卡实收
								.append(",kainfo.leavecardmoney ka_yue ")		// 卡余额
								.append(",kainfo.sourcememberid y_ka_code ")	// 源卡code
								.append(",kainfo.sourceleavemoney y_ka_yue ")	// 源卡余额
								.append(",ka_y.kabili y_kabili ")				// 源卡比例
								.append(",ka_y.pk_hk_huiyuan_kadangan y_ka_pk ")	// 源卡pk
								.append(",ka_y.pk_hk_huiyuan_kaxing y_kaxing_pk ")	// 源卡型pk
								.append(",kaxing_y.kaxing_code y_kaxing_code ")		// 源卡型code
								.append(",kaxing_y.kaxing_name y_kaxing_name ")		// 源卡型name
								.append(",kaxing.ka_je vbdef01 ")		// 卡型金额
								.append(",kaxing.ka_ss vbdef02 ")		// 卡型实收
								.append(",kaxing.ka_zs vbdef03 ")		// 卡型赠送
								.append(" from hk_huiyuan_kainfo_temp kainfo ")		// 卡信息
								.append(" left join hk_huiyuan_kadangan ka on kainfo.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")			// 卡档案
								.append(" left join hk_huiyuan_kaxing kaxing on ka.pk_hk_huiyuan_kaxing = kaxing.pk_hk_huiyuan_kaxing ")	// 卡型
								.append(" left join hk_huiyuan_kadangan ka_y on kainfo.sourcememberid = ka_y.ka_code and nvl(ka_y.dr,0)=0 ")	// 源卡档案
								.append(" left join hk_huiyuan_kaxing kaxing_y on ka_y.pk_hk_huiyuan_kaxing = kaxing_y.pk_hk_huiyuan_kaxing ")	// 源卡型
								.append(" where (1=1) ")
								/**
								 * HK 2020年10月9日17:26:37
								 * 不取 微会员 的卡型的数据
								 * 金贵有一个卡型叫【微会员】，是记录金贵商城会员信息的，今后不会发生任何的会员卡账务，但是每天的会员卡信息表，把这个卡型取过来了，导致我们会员卡信息表不能保存。设置一下，让会员卡信息表直接不取这个卡型就可以了。
								 * 不取 why 开头的会员卡
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
					
					UFDouble cz = UFDouble.ZERO_DBL;	// 充值
					UFDouble yz = UFDouble.ZERO_DBL;	// 余转
					UFDouble xf = UFDouble.ZERO_DBL;	// 消费
					
					HashMap<String,KainfoBVO> map_cz = new HashMap<String, KainfoBVO>();	// 充值数据（过滤掉  同号 有多笔充值的情况）
					Vector<String> v_zfyz = new Vector<String>();	// 余转作废卡
					
					for( int i=0;i<kainfoBVOs.length;i++ )
					{// 循环处理 子表VO
						
						kainfoBVOs[i].setVrowno(i+1);
						
						if("消费".equals( kainfoBVOs[i].getXmdl() ))
						{
							kainfoBVOs[i].setKa_ss( (nullAsZero(kainfoBVOs[i].getKa_je()).multiply(nullAsZero(kainfoBVOs[i].getKabili()))).setScale(2,UFDouble.ROUND_HALF_UP) );	// 卡实收 = 卡金额 * 卡比例
							
							xf = xf.add( kainfoBVOs[i].getKa_je() );	// 叠加 消费
						}
						else if("余转".equals( kainfoBVOs[i].getXmdl() ))
						{
							kainfoBVOs[i].setKa_ss( (nullAsZero(kainfoBVOs[i].getKa_je()).multiply(nullAsZero(kainfoBVOs[i].getKabili()))).setScale(2,UFDouble.ROUND_HALF_UP) );	// 卡实收 = 卡金额 * 卡比例
							kainfoBVOs[i].setY_ka_je( kainfoBVOs[i].getKa_je() );	// 源卡金额 = 卡金额
							kainfoBVOs[i].setY_ka_ss( (nullAsZero(kainfoBVOs[i].getY_ka_je()).multiply(nullAsZero(kainfoBVOs[i].getY_kabili()))).setScale(2,UFDouble.ROUND_HALF_UP) );// 源卡实收 = 源卡金额 * 源卡比例
						
							if( "财务大卡".equals(kainfoBVOs[i].getKaxing_name()) )
							{// 如果是 转入财务大卡， 则放到缓存里，以备 处理  （将当天的充值  设置为作废卡）
								v_zfyz.add(kainfoBVOs[i].getY_ka_code());
							}
							
							yz = yz.add( kainfoBVOs[i].getKa_je() );	// 叠加 余转
						}
						else if("充值".equals( kainfoBVOs[i].getXmdl() ))
						{
//							kainfoBVOs[i].setKa_zs( nullAsZero(kainfoBVOs[i].getKa_je()).sub( nullAsZero(kainfoBVOs[i].getKa_ss()) ) );	// 赠送金额 = 卡金额 - 实收
							
							if( 
								nullAsZero(kainfoBVOs[i].getKa_je()).compareTo( nullAsZero(kainfoBVOs[i].getVbdef01()) ) !=0 
							  )
							{// 如果 卡金额  不等于  卡型金额， 则认为是   回充。
								kainfoBVOs[i].setXmlx("回充");
								kainfoBVOs[i].setKa_zs(null);
								kainfoBVOs[i].setKa_ss( PuPubVO.getUFDouble_NullAsZero( kainfoBVOs[i].getKa_je() ).multiply( PuPubVO.getUFDouble_NullAsZero( kainfoBVOs[i].getKabili() ) ).setScale(UFDouble.ROUND_HALF_UP, 2) );
								
							}
							else
							{// 非回充， 则将   实收  和  赠送，  重新赋值为  卡型的实收 、赠送
								kainfoBVOs[i].setKa_ss( nullAsZero(kainfoBVOs[i].getVbdef02()) );
								kainfoBVOs[i].setKa_zs( nullAsZero(kainfoBVOs[i].getVbdef03()) );
								
								if( map_cz.containsKey(kainfoBVOs[i].getKa_code()) )
									map_cz.put(kainfoBVOs[i].getKa_code(), null);
								else
									map_cz.put(kainfoBVOs[i].getKa_code(), kainfoBVOs[i]);
								
							}
							
							cz = cz.add( kainfoBVOs[i].getKa_je() );	// 叠加 充值
						}
						
					}
					
					// 对作废卡的处理
					if( v_zfyz!=null && v_zfyz.size()>0 )
					{
						for(int vi=0;vi<v_zfyz.size();vi++)
						{
							KainfoBVO vo = map_cz.get(v_zfyz.get(vi));
							if(vo!=null)
								vo.setXmlx("作废卡");
						}
					}
					
					
					// 处理 主表VO
					KainfoHVO kainfoHVO = new KainfoHVO();
					kainfoHVO.setIbillstatus(-1);
					kainfoHVO.setDbilldate(new UFDate( yw_date ));
					kainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
					kainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
					kainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
					kainfoHVO.setPk_org( corp );
					kainfoHVO.setCreator(HKJT_PUB.MAKER);
					kainfoHVO.setCreationtime(new UFDateTime());
					kainfoHVO.setVbillcode( dian+"-"+yw_date );
					kainfoHVO.setVbilltypecode("HK24");
					kainfoHVO.setVdef01(dian);	// 业务门店
					kainfoHVO.setVdef02( cz.toString() );	// 充值
					kainfoHVO.setVdef03( yz.toString() );	// 余转
					kainfoHVO.setVdef04( xf.toString() );	// 消费
					// 聚合VO
					KainfoBillVO kainfoBillVO = new KainfoBillVO();
					kainfoBillVO.setParent(kainfoHVO);
					kainfoBillVO.setChildrenVO(kainfoBVOs);
					
					// 插入 聚合VO
					IHy_kainfoMaintain itf = NCLocator.getInstance().lookup(IHy_kainfoMaintain.class);
					itf.insert(new KainfoBillVO[]{kainfoBillVO}, null);
					
					// 清空 临时表
					getBaseDAO().deleteByClause(KainfoTempVO.class, " 1=1 ");
				}
			
			}
		
		}
		
	}
	
	/**
	 * 获得 业务时间段
	 * 因为 公共库的 数据  总是 没及时同步， 所以  还是 从各个业务库里 取数。
	 */
	private String[] getYwDate(UFDate date,String flag) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		if( "-01".equals(flag) )
		{// 贵宾楼 牡丹
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_gbl").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-02".equals(flag) )
		{// 国际 
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_gjhg").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-06".equals(flag) )
		{// 西山温泉 
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_xswq").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-04".equals(flag) )
		{// 康福瑞 酒店
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_kfrjd").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-07".equals(flag) )
		{// 朗丽兹
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_llzjd").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-08".equals(flag) )
		{// 康福瑞 西山店  
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_kfrxsd").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else if( "-11".equals(flag) )
		{// 太申 TODO
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_taishen").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		}
		else
		{
			return null;
		}
		
		StringBuffer query_sql = null;
		
		if("-04".equals(flag))
		{// 康福瑞酒店 用  酒店前厅  来表示业务数据
			
			if( "2015-12-13".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-12-12 00:40:02","2015-12-14 00:27:08"};

			else
			{
//				query_sql = 
//						new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
//								.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
//								.append("from Dt_ChangeClass ")	// 业务汇总库
//								.append("WHERE ")
//								.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 11:59:59' ")	// 时间范围应该是 前一天、后一天  12点前结账
//								.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
//								.append(" and Opersite = '酒店前厅' ")
//								.append(" and SUBSTRING(changeclassid,1,3) = 'ZJD' ")	// 通过 交班编号前缀 来过滤
//				;
				String yyyymmdd = date.toString().substring(0,10);
				return new String[]{
					yyyymmdd + " 00:00:00",
					yyyymmdd + " 23:59:59"
				};
			}
		}
		else if("-02".equals(flag)) {
			// 国际
			String yyyymmdd = date.toString().substring(0,10);
			return new String[]{
				yyyymmdd + " 00:00:00",
				yyyymmdd + " 23:59:59"
			};
		}
		else if("-07".equals(flag))
		{// 朗丽兹 用  前厅部  来表示业务数据
//			query_sql = 
//					new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
//							.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
//							.append("from Dt_ChangeClass ")	// 业务汇总库
//							.append("WHERE ")
//							.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 11:59:59' ")	// 时间范围应该是 前一天、后一天  12点前结账
//							.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
//							.append(" and Opersite = '前厅部' ")
//			;
			String yyyymmdd = date.toString().substring(0,10);
			return new String[]{
				yyyymmdd + " 00:00:00",
				yyyymmdd + " 23:59:59"
			};
			
		}
		else if("-08".equals(flag))
		{// 康福瑞西山店
			
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
//				query_sql = 
//						new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
//								.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
//								.append("from Dt_ChangeClass ")	// 公司业务库
//								.append("WHERE ")
//								.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 11:59:59' ")	// 时间范围应该是 前一天、后一天  6点前结账
//								.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
//								.append(" and SUBSTRING(changeclassid,1,3) = 'ZKF' ")	// 通过 交班编号前缀 来过滤
//								.append(" and Opersite = '前厅部' ");
				String yyyymmdd = date.toString().substring(0,10);
				return new String[]{
					yyyymmdd + " 00:00:00",
					yyyymmdd + " 23:59:59"
				};
			}
			
		}
		else if("-01".equals(flag))
		{// 贵宾楼
			     if( "2015-12-24".equals( date.toString().substring(0,10) ) )
				return null;
			else if( "2015-12-25".equals( date.toString().substring(0,10) ) )
				return new String[]{"2015-12-23 23:50:24","2015-12-25 23:59:09"};
			
			else
				query_sql = 
				new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
						.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
						.append("from Dt_ChangeClass ")	// 业务汇总库
						.append("WHERE ")
						.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 00:59:59' ")	// 时间范围应该是 前一天、后一天 各推一小时
						.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
						.append(" and Opersite = '桑拿部' ")
						.append(" and SUBSTRING(changeclassid,1,3) = 'ZSN' ")	// 通过 交班编号前缀 来过滤
						;// 同下 
				
		} else if ("-11".equals(flag)) {
			// 太申
			String yyyymmdd = date.toString().substring(0,10);
			return new String[]{
				yyyymmdd + " 00:00:00",
				yyyymmdd + " 23:59:59"
			};
		} else
		{// 其它的会馆  用  桑拿部  来表示业务数据
			query_sql = 
			new StringBuffer("SELECT  min(CONVERT(VARCHAR(19),ChangeTime,120)) kssj ")
					.append(",MAX(CONVERT(VARCHAR(19),ChangeTime,120)) jssj ")
					.append("from Dt_ChangeClass ")	// 业务汇总库
					.append("WHERE ")
					.append(" CONVERT(VARCHAR(19),ChangeTime,120) between '"+(date.getDateBefore(1).toString().substring(0,10))+" 23:00:00' and '"+(date.getDateAfter(1).toString().substring(0,10))+" 00:59:59' ")	// 时间范围应该是 前一天、后一天 各推一小时
					.append(" and SUBSTRING(changeclassid,16,3)='"+flag+"' ")
					.append(" and Opersite = '桑拿部' ")
					.append(" and SUBSTRING(changeclassid,1,3) = 'ZSN' ")	// 通过 交班编号前缀 来过滤
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
	 * 开卡信息 同步
	 */
	private void importKaiKa(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		if(kainfo_bdate==null) kainfo_bdate = new UFDate("1990-01-01");
		if(kainfo_edate==null) kainfo_edate = new UFDate("2990-12-31");
		
		// 开卡 结束时间   赋值为   当前服务器时间， 确保跑金贵余额的时候， NC里都用卡
		kainfo_edate = new UFDate();
		
		for(int org_i=0; org_i<pk_orgs.length; org_i++)
		{// 公司循环
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
			
			// 查询 开卡信息
			StringBuffer query_kaika = 
			new StringBuffer()
					.append("select memberid ")
					.append(",CardType cardtypeid ")
					.append(",OutVpn coach ")
					.append(",CONVERT(VARCHAR(19),OutDate,120) lastenterdate ")
					.append(",OutPerson ")
					.append(" from Dt_MakeCard ")
					.append(" where (1=1) ")
					.append(" and Status = '已发' ")
					.append(" and OutVpn = '").append( dian ).append("' ")	// 店
					.append(" and CONVERT(VARCHAR(19),isnull(OutDate,'1990-01-01 00:00:00'),120) between '")
					.append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" )
					.append("' and '")
					.append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// 时间
				.append(" union all ")
					.append("select memberid ")
					.append(",max(cardtype) cardtypeid ")
					.append(",max(coach) coach ")
					.append(",min(CONVERT(VARCHAR(19), enterdate, 120)) lastenterdate ")
					.append(",max(employName) outperson ")
					.append(" from V_GetMemberOperateHistory ")
					.append(" where remark = '接口充值' and left(Memberid,3) <> 'why' ")
					.append(" and coach = '").append( dian ).append("' ")	// 店
					.append(" and CONVERT(VARCHAR(19),isnull(enterdate,'1990-01-01 00:00:00'),120) between '")
					.append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" )
					.append("' and '")
					.append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// 时间
					.append(" group by memberid ")
			;
			
			ArrayList<KadanganTempVO> list_huiyuaka_query = (ArrayList<KadanganTempVO>)hkjt_hg_pub_session.executeQuery(query_kaika.toString(), new BeanListProcessor(KadanganTempVO.class));
			
			insertVOS(list_huiyuaka_query);//将卡档案插入到NC临时表
			
			// 临时表 关联正式表， 过滤出 未导入过的 卡档案。
			StringBuffer query_huiyuaka_insert = 
					new StringBuffer("select hk_huiyuan_kadangan_temp.memberid ")
							.append(",hk_huiyuan_kadangan_temp.coach ")
							.append(",hk_huiyuan_kaxing.pk_hk_huiyuan_kaxing cardtypeid ")
							.append(",hk_huiyuan_kaxing.kabili ")
							.append(" from hk_huiyuan_kadangan_temp ")
							.append(" left join hk_huiyuan_kadangan on hk_huiyuan_kadangan_temp.memberid = hk_huiyuan_kadangan.ka_code and nvl(hk_huiyuan_kadangan.dr,0)=0 ")	// 卡档案
							.append(" left join hk_huiyuan_kaxing on hk_huiyuan_kadangan_temp.cardtypeid = hk_huiyuan_kaxing.kaxing_code and nvl(hk_huiyuan_kaxing.dr,0)=0 ")	// 卡型
							.append(" where hk_huiyuan_kadangan.pk_hk_huiyuan_kadangan is null ")
			;
			ArrayList<KadanganTempVO> list_huiyuaka_insert = (ArrayList<KadanganTempVO>)getBaseDAO().executeQuery(query_huiyuaka_insert.toString(), new BeanListProcessor(KadanganTempVO.class));

			// 将 临时表 转换成正式VO
			if(list_huiyuaka_insert.size()>0)
			{
				KadanganHVO[] kadanganHVO_insert = new KadanganHVO[list_huiyuaka_insert.size()];
				for( int i=0;i<list_huiyuaka_insert.size();i++ )
				{
					KadanganTempVO tempVO = list_huiyuaka_insert.get(i);
					kadanganHVO_insert[i] = new KadanganHVO();
					kadanganHVO_insert[i].setKa_code( tempVO.getMemberid() );
					kadanganHVO_insert[i].setKa_name( tempVO.getMembername() );
					kadanganHVO_insert[i].setPk_hk_huiyuan_kaxing( tempVO.getCardtypeid() );	// 卡型
//					kadanganHVO_insert[i].setQc_ye( tempVO.getLeavemoney() );
//					kadanganHVO_insert[i].setDq_ye( tempVO.getLeavemoney() );
					kadanganHVO_insert[i].setKabili( tempVO.getKabili() );
					kadanganHVO_insert[i].setKastatus("正常");
					
					kadanganHVO_insert[i].setPk_group( AppContext.getInstance().getPkGroup() );	//集团
					kadanganHVO_insert[i].setPk_org( MAP_dian_corp.get(tempVO.getCoach()) );	// pk_rog
					kadanganHVO_insert[i].setVbillcode( kadanganHVO_insert[i].getKa_code() );
					kadanganHVO_insert[i].setIbillstatus(-1);
					kadanganHVO_insert[i].setCreator(HKJT_PUB.MAKER);
					kadanganHVO_insert[i].setCreationtime(new UFDateTime());
					kadanganHVO_insert[i].setDr(0);
					kadanganHVO_insert[i].setDbilldate(new UFDate());
					kadanganHVO_insert[i].setVdef01( tempVO.getCoach() );//开卡 店
				}
				this.getBaseDAO().insertVOArray(kadanganHVO_insert);//插入卡型 正式VO
				
				// 清空 临时表
				getBaseDAO().deleteByClause(KadanganTempVO.class, " 1=1 ");
			}
			
		}
	}
	
	/**
	 * 同步 金贵余额
	 */
	private void importJGyue(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		if(kainfo_bdate==null) kainfo_bdate = new UFDate("1990-01-01");
		if(kainfo_edate==null) kainfo_edate = new UFDate("2990-12-31");
		
		// 两层循环 找会员卡余额数据
		// 公司、卡型
		StringBuffer query_kaxing = 
				new StringBuffer("select hk_huiyuan_kaxing.* ")
						.append(" from hk_huiyuan_kaxing ")
						.append(" where nvl(dr,0)=0 ")
//						.append(" and kaxing_code = '03' ")	// 测试
						.append(" order by kaxing_code ")
		;
		
		ArrayList<KaxingHVO> list_kaxing =(ArrayList<KaxingHVO>)getBaseDAO().executeQuery(query_kaxing.toString(), new BeanListProcessor(KaxingHVO.class));
		
		for( int org_i=0;org_i<pk_orgs.length;org_i++ )
		{// 公司循环
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
//			String flag = MAP_dian_flag.get(dian);
		
			for (int kaxing_i=0;kaxing_i<list_kaxing.size();kaxing_i++) {// 卡型循环
			
				KaxingHVO kaxingHVO = list_kaxing.get(kaxing_i);
				
				StringBuffer query_jgyue = // 查看金贵余额
						new StringBuffer("SELECT ")
								.append(" Memberid ")
								.append(",LeaveMoney ")
								.append(",CONVERT(VARCHAR(19),LastEnterDate,120) LastEnterDate ")
								.append(" FROM hk_member ")
								.append(" where (1=1) ")
								.append(" and Status != '删除' ")
								.append(" and cardtypeid='").append(kaxingHVO.getKaxing_code()).append("' ")	// 卡型
								.append( "国际".equals(dian) ? " and coach in ('国际','上地','DK') " : " and coach = '" + dian + "' ")	// 店
//								.append(" and ( ")
//								.append("    CONVERT(VARCHAR(19),isnull(LastEnterDate,'1990-01-01 00:00:00'),120) between '").append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// 时间
//								.append(" or CONVERT(VARCHAR(19),isnull(TrueEnterDate,'1990-01-01 00:00:00'),120) between '").append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// 时间
//								.append(" ) ")
//								.append(" and Memberid = '0302101237' ")
								;
				
				ArrayList<KadanganJGtempVO> list_jgyue_query = (ArrayList<KadanganJGtempVO>)hkjt_hg_pub_session.executeQuery(query_jgyue.toString(), new BeanListProcessor(KadanganJGtempVO.class));
				insertVOS(list_jgyue_query);//将业务数据插入到NC临时表
				
				// 临时表 关联正式表， 过滤出 未导入过的 金贵余额。
				{// 根据 最后登入时间  来 判断是否应该 同步金贵余额
				 // 只有 消费的数据  才会更改 最后登入时间
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
					
					// 将 临时表 转换成正式VO
					if(list_jgyue_insert.size()>0)
					{
						KadanganJGVO[] kadanganJGVO_insert = new KadanganJGVO[list_jgyue_insert.size()];
						for( int i=0;i<list_jgyue_insert.size();i++ )
						{
							kadanganJGVO_insert[i] = list_jgyue_insert.get(i);
							
						    String drsj = PuPubVO.getString_TrimZeroLenAsNull( kadanganJGVO_insert[i].getLastenterdate() );
						    if(drsj==null)
						    {// 如果 金贵的时间为空， 则赋值为 当前时间
						    	drsj =  PuPubVO.getString_TrimZeroLenAsNull(serverTime);
						    }
							
							kadanganJGVO_insert[i].setVbdef01( drsj );
						}
						this.getBaseDAO().insertVOArray(kadanganJGVO_insert);//插入卡型 正式VO
					}
				}
				
				{// 时间 相同的， 并且  金额不同的
				 // 时间 赋值为  当前服务器时间
					
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
									
									.append(" where jgtemp.leavemoney != jg.jg_yue ")	// 金贵系统余额  不等于  NC的金贵余额
									.append(" and ka.pk_hk_huiyuan_kadangan is not null ")
					;
					
					ArrayList<KadanganJGVO> list_jgyue_insert_2 = (ArrayList<KadanganJGVO>)getBaseDAO().executeQuery(query_jgyue_insert_2.toString(), new BeanListProcessor(KadanganJGVO.class));
					
					UFDateTime serverTime = new UFDateTime();
					
					// 将 临时表 转换成正式VO
					if(list_jgyue_insert_2.size()>0)
					{
						KadanganJGVO[] kadanganJGVO_insert_2 = new KadanganJGVO[list_jgyue_insert_2.size()];
						for( int i=0;i<list_jgyue_insert_2.size();i++ )
						{
							kadanganJGVO_insert_2[i] = list_jgyue_insert_2.get(i);
							kadanganJGVO_insert_2[i].setVbdef01( PuPubVO.getString_TrimZeroLenAsNull(serverTime) );	// 赋值为  当前服务器时间
						}
						this.getBaseDAO().insertVOArray(kadanganJGVO_insert_2);//插入卡型 正式VO
					}
					
				}
				
				// 清空 临时表
				getBaseDAO().deleteByClause(KadanganJGtempVO.class, " 1=1 ");
				
			}
			
		}
		
		// 全部处理完毕之后
		// 将金贵余额的自定义项01 ~置为空，方便后续的判断及处理 
		this.getBaseDAO().executeUpdate(
				  " update hk_huiyuan_kadangan_jg " 
				+ " set vbdef01 = null "
				+ " where vbdef01 = '~' "
		);
		
	}
	
	/**
	 * 同步 换卡信息
	 */
	private void importHuanka(String[] pk_orgs,UFDate bdate,UFDate edate) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		// 开卡 结束时间   赋值为   当前服务器时间， 确保跑金贵余额的时候， NC里都有卡
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
						.append(" and Status='换卡' ")
						.append(" and CONVERT(VARCHAR(19),isnull(OutDate,'1990-01-01 00:00:00'),120) between '").append( bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")
//						.append(" order by  ")
						;
		
		ArrayList<HuankaTempVO> list_huanka_query = (ArrayList<HuankaTempVO>)hkjt_hg_pub_session.executeQuery(query_huanka.toString(), new BeanListProcessor(HuankaTempVO.class));
		insertVOS(list_huanka_query);//将业务数据插入到NC临时表
		
		// 临时表 关联正式表， 过滤出 未导入过的 换卡数据。
		StringBuffer query_huanka_insert = 
				new StringBuffer(" select ")
						.append(" temp.cardid ")
						.append(",temp.memberid ka_code ")
						.append(",temp.sourcememberid y_ka_code ")
						.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")// 卡pk
						.append(",temp.leavemoney ka_je ")
						.append(",temp.outdate hk_time ")	// 换卡时间
						.append(",temp.outperson czy ")
						.append(",temp.outvpn vdef01")
						.append(" from hk_huiyuan_huanka_temp temp ")
						.append(" left join hk_huiyuan_huanka huanka on huanka.cardid = temp.cardid and huanka.dr=0 ")
						.append(" left join hk_huiyuan_kadangan ka on ka.ka_code = temp.sourcememberid and ka.dr=0 ")
						.append(" where huanka.pk_hk_huiyuan_huanka is null ")
						.append(" order by temp.outdate ")	// 按 换卡时间排序， 确保 时间早的  先进行处理
		;
		ArrayList<HuankaHVO> list_jgyue_insert = (ArrayList<HuankaHVO>)getBaseDAO().executeQuery(query_huanka_insert.toString(), new BeanListProcessor(HuankaHVO.class));
		
		if( list_jgyue_insert.size()>0 )
		{
			HuankaHVO[] huankaHVO_insert = new HuankaHVO[list_jgyue_insert.size()];
			huankaHVO_insert = list_jgyue_insert.toArray(huankaHVO_insert);
			
			// 对  换卡结果  再次进行冒泡排序，  确保 按 时间先后顺序  来处理
			for(int i = 0 ; i < huankaHVO_insert.length-1 ; i++){
				for(int j = i+1 ; j < huankaHVO_insert.length ; j++){
					String hksj_i = huankaHVO_insert[i].getHk_time().toString();
					String hksj_j = huankaHVO_insert[j].getHk_time().toString();
					
					if(hksj_i.compareTo(hksj_j)>0)
					{// 进行交换
						HuankaHVO temp = huankaHVO_insert[i];
						huankaHVO_insert[i] = huankaHVO_insert[j];
						huankaHVO_insert[j] = temp;
					}
				}
			}
			// END
			
			for(int i=0;i<huankaHVO_insert.length;i++)
			{
				huankaHVO_insert[i].setPk_group( AppContext.getInstance().getPkGroup() );	//集团
				huankaHVO_insert[i].setPk_org( MAP_dian_corp.get(huankaHVO_insert[i].getVdef01()) );	// pk_rog
				huankaHVO_insert[i].setVbillcode( huankaHVO_insert[i].getKa_code() );
				huankaHVO_insert[i].setIbillstatus(3);	// 默认为 提交态
				huankaHVO_insert[i].setCreator(HKJT_PUB.MAKER);
				huankaHVO_insert[i].setCreationtime(new UFDateTime());
				huankaHVO_insert[i].setDr(0);
				huankaHVO_insert[i].setDbilldate(PuPubVO.getUFDate(huankaHVO_insert[i].getHk_time()));
				
				String insert_pk = this.getBaseDAO().insertVO( huankaHVO_insert[i] );
				
				/////////////////////////////////////////////// 处理回写
				if(  null!=huankaHVO_insert[i].getKa_pk()
				  && !"~".equals(huankaHVO_insert[i].getKa_pk())	
				)
				{
					this.huanka_insert(huankaHVO_insert[i],insert_pk);
				}
				
			}
			
//			this.getBaseDAO().insertVOArray(huankaHVO_insert);//插入卡型 正式VO
			
			// 清空 临时表
			getBaseDAO().deleteByClause(HuankaTempVO.class, " 1=1 ");
			
		}
		
	}
	
	/**
	 * 换卡的回写  insert
	 */
	public Object huanka_insert(HuankaHVO huankaHVO,String pk_huanka) throws Exception
	{
		
		if(  null!=huankaHVO.getKa_pk()
		  && !"~".equals(huankaHVO.getKa_pk())	
		)
		{
			//1、会员卡档案-换卡
			KadanganHKVO kadanganHKVO = new KadanganHKVO();
			kadanganHKVO.setPk_hk_huiyuan_kadangan( huankaHVO.getKa_pk() );	// 卡pk
			kadanganHKVO.setKa_code_new(huankaHVO.getKa_code());	// 现卡号
			kadanganHKVO.setKa_code_old(huankaHVO.getY_ka_code());	// 原卡号
			kadanganHKVO.setHk_time(huankaHVO.getHk_time());	// 换卡时间
			kadanganHKVO.setKayue(huankaHVO.getKa_je());		// 卡余额
			
			kadanganHKVO.setCsourcetypecode("HK23");	// 上游单据类型
			kadanganHKVO.setCsourcebillid(pk_huanka);	// 上游单据id
			kadanganHKVO.setCsourcebillbid(pk_huanka);	// 上游单据行id
//					kadanganHKVO.setPk_hk_huiyuan_kadangan_hk(pkGenerator.generate());		// 赋值pk
			kadanganHKVO.setDr(0);
			kadanganHKVO.setVbdef01(huankaHVO.getVdef01());	// 门店
			
			this.getBaseDAO().insertVO(kadanganHKVO);	// 插入 换卡记录
			
			String ka_code   = huankaHVO.getKa_code();
			String y_ka_code = huankaHVO.getY_ka_code();
			
			// 2、会员卡档案
			getBaseDAO().executeUpdate(
					" update hk_huiyuan_kadangan " +
					" set " +
					" vbillcode = '" + ka_code + "' " +
					",ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " + 
					" and ka_code = '"+y_ka_code+"' " 
			);
			// 2、会员卡信息
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
			
			// 3、次卡信息
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
			
			// 4、开票信息
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_kaipiaoinfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			
			// 5、账单表体分区信息
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
	 * 换卡的回写  update
	 * 用于  会员换卡  界面【换卡】按钮的操作
	 * 只有  卡pk 为空  才进行换卡
	 */
	public Object huanka_update(HuankaHVO huankaHVO,String pk_huanka) throws Exception
	{
		
		if(  null==huankaHVO.getKa_pk()
		  || "~".equals(huankaHVO.getKa_pk())	
		)
		{
			String ka_code   = huankaHVO.getKa_code();
			String y_ka_code = huankaHVO.getY_ka_code();
			
			// 根据原卡号  找到  卡档案pk。
			ArrayList<KadanganHVO> query_kadangan = 
					(ArrayList<KadanganHVO>)new BaseDAO().retrieveByClause(KadanganHVO.class, " dr=0 and ka_code ='"+y_ka_code+"' ");
			;
			
			if( query_kadangan.size()<1 )
			{// 如果找不到  卡档案  则返回空
				return null;
			}
			
			KadanganHVO kadanganHVO = query_kadangan.get(0);
			
			//0、更新到 会员换卡
			huankaHVO.setKa_pk( kadanganHVO.getPk_hk_huiyuan_kadangan() );
			huankaHVO.setDr(0);
			new BaseDAO().updateVO(huankaHVO);
			
			//1、会员卡档案-换卡
			KadanganHKVO kadanganHKVO = new KadanganHKVO();
			kadanganHKVO.setPk_hk_huiyuan_kadangan( kadanganHVO.getPk_hk_huiyuan_kadangan() );	// 卡pk
			kadanganHKVO.setKa_code_new(huankaHVO.getKa_code());	// 现卡号
			kadanganHKVO.setKa_code_old(huankaHVO.getY_ka_code());	// 原卡号
			kadanganHKVO.setHk_time(huankaHVO.getHk_time());	// 换卡时间
			kadanganHKVO.setKayue(huankaHVO.getKa_je());		// 卡余额
			
			kadanganHKVO.setCsourcetypecode("HK23");	// 上游单据类型
			kadanganHKVO.setCsourcebillid(pk_huanka);	// 上游单据id
			kadanganHKVO.setCsourcebillbid(pk_huanka);	// 上游单据行id
//					kadanganHKVO.setPk_hk_huiyuan_kadangan_hk(pkGenerator.generate());		// 赋值pk
			kadanganHKVO.setDr(0);
			kadanganHKVO.setVbdef01(huankaHVO.getVdef01());	// 门店
			
			this.getBaseDAO().insertVO(kadanganHKVO);	// 插入 换卡记录
			
			
			// 2、会员卡档案
			getBaseDAO().executeUpdate(
					" update hk_huiyuan_kadangan " +
					" set " +
					" vbillcode = '" + ka_code + "' " +
					",ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " + 
					" and ka_code = '"+y_ka_code+"' " 
			);
			// 2、会员卡信息
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
			
			// 3、次卡信息
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
			
			// 4、开票信息
			getBaseDAO().executeUpdate(	// KA_CODE
					" update hk_huiyuan_kaipiaoinfo_b " +
					" set " +
					" ka_code   = '" + ka_code + "' " +
					" where (1=1) " +
					" and dr=0 " +
					" and ka_code = '"+y_ka_code+"' " 
			);
			
			// 5、账单表体分区信息
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
	 * 次卡信息，日常同步
	 */
	private void importCika_info(String[] pk_orgs,UFDate kainfo_bdate,UFDate kainfo_edate,String btime_str,String etime_str) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		
		ArrayList<String> list_date = this.getTimeDates(kainfo_bdate,kainfo_edate);
		/** 
		 * 导每天的业务数据，用双层循环
		 * 外循环  是  店
		 * 内循环  是  天
		 */
		for(int org_i=0;org_i<pk_orgs.length;org_i++)
		{// 按店  来 循环处理
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
			String flag = MAP_dian_flag.get(dian);
			String db_str = MAP_dian_db.get(dian);	// 数据源 标识
		
			for (String yw_date : list_date) 
			{// 按天 来 循环处理
				
			
				//判断，是否导入过
				StringBuffer query_check = 
						new StringBuffer("select ckinfo.pk_hk_huiyuan_cikainfo ")
								.append(" from hk_huiyuan_cikainfo ckinfo ")
								.append(" where nvl(ckinfo.dr,0)=0 ")
								.append(" and ckinfo.pk_org = '"+corp+"' ")		// 公司
								.append(" and substr(ckinfo.dbilldate,0,10) = '"+yw_date+"' ")	// 业务日期
				;
				Object check_pk = getBaseDAO().executeQuery(query_check.toString(),new ColumnProcessor());
				
				if(check_pk!=null) continue;	// 如果导入了  就跳过。
				
				// 根据业务日期  来找 具体时间 （开始、结束）
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
				{// 如果 后台任务参数  设置了  班次开始日期、班次结束日期，   则按设置的走
					yw_time[0] = btime_str;
					yw_time[1] = etime_str;
				}
				
				if( yw_time==null || yw_time.length<2 ) continue;	// 找不到 业务时间 就退出
				
				/**
				 * 判断数据量，是否应该 做分单 处理
				 * 如果 行数 大于 2500 ，则需要单， 每2000行  分一单。
				 */
				StringBuffer query_num = 
						new StringBuffer("select count(0) ")
								.append(" FROM Dt_TimesCard a ")
								.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
								.append(" WHERE (isnull(a.Status,'null') <> '删除') ")
								.append(" and a.VpnName ='").append(dian).append("' ")
								.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >  '"+yw_time[0]+"' ")
								.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <= '"+yw_time[1]+"' ")
				;
				ArrayList list_num = (ArrayList)hkjt_hg_pub_session.executeQuery(query_num.toString(),new ArrayListProcessor());
				if( list_num!=null && list_num.size()>0 )
				{
					int num = PuPubVO.getInteger_NullAs( ((Object[])list_num.get(0))[0] , 0 );	// 行数
					
					if( num<=2500 )
					{// 行数 小于 2500   一单进行处理.
						
						// 充值、消费
						StringBuffer query_cikainfo = 
								new StringBuffer("")
						// 消费 明细
//										.append(" SELECT ")
//										.append(" '消费' xmdl ")
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
							// 消费 汇总，按 次卡号、账单号
							.append(" SELECT ")
							.append(" '消费' xmdl ")
							.append(",'' xmlx ")
							.append(",'' consumewaternum ")
							.append(",a.timescardwaternum ")
							.append(",a.BillId ")
							.append(",case when CHARINDEX('门票',b.goodsname)=0 then '' else replace( b.goodsname,'超时','') end vdef02 ")	// 账单信息
	//										.append(",case when CHARINDEX('门票',b.goodsname)=0 then '' else replace(replace( b.goodsname,'超时',''),'门票','') end vdef02 ")	// 账单信息
							.append(",max(CONVERT(VARCHAR(19),c.OperateDate,120)) operateDate ")
							.append(",sum(b.NumberCount) NumberCount ")
							.append(",null Price ")
							.append(",max(f.Memberid) Memberid ")
							.append(",max(e.ItemId) itemid ")
							.append(",max(e.ItemName) ItemName ")
							.append(",max(CONVERT(VARCHAR(10),e.startdata,120)) startdata ")
							.append(",max(CONVERT(VARCHAR(10),e.ExpData,120)) expdata ")
							.append(",'' vdef03 ")	// 不减次
							.append(",'' vdef04 ")	// 对方卡号
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
							.append(" and e.WaterNum is not null ")		// 必须关联到  次卡档案  （2016年2月21日13:39:13）
							.append("  group by a.BillId,a.timescardwaternum ")	
							.append("          ,case when CHARINDEX('门票',b.goodsname)=0 then '' else replace( b.goodsname,'超时','') end ")// 按 账单信息 汇总
	//										.append("          ,case when CHARINDEX('门票',b.goodsname)=0 then '' else replace(replace( b.goodsname,'超时',''),'门票','') end ")// 按 账单信息 汇总
							.append(" union all ")
							// 充值
							.append(" select ")
							.append(" case when a.IsSubTimes = '充负数' then '充负数' else '充值' end xmdl ")
							.append(",case when a.IsSubTimes = '充负数' then '' else a.Type end xmlx ")
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
							.append(",case when a.IsSubTimes = '否' then 'Y' else '' end vdef03 ")	// 不减次
							.append(",'' vdef04 ")	// 对方卡号
							.append(",'' vdef05 ")
							.append(" FROM Dt_TimesCard a ")
							.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
							.append(" WHERE (isnull(a.Status,'null') <> '删除') ")
							.append(" and a.VpnName ='").append(dian).append("' ")
							.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >  '"+yw_time[0]+"' ")
							.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <= '"+yw_time[1]+"' ")
							// 新次卡-充值、转入
							.append(" union all ")
							.append(" select ")
							.append(" '充值' xmdl ")
							.append(",case when cz.CwBillId = '转入' then '转入' else '' end xmlx ")
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
							.append(",case when ck.IsSubTimes = '否' then 'Y' else '' end vdef03  ")
							.append(",kaold.memberid vdef04 ")		// 对方卡号
							.append(",'' vdef05 ")
							.append(" from M_TimesCardMember cz ")		// -- 充次
							.append(" left join M_TimesCardDefault ck on cz.TimesCardDefaultId = ck.TimesCardDefaultId ")	// -- 次卡信息
							.append(" left join dt_memberaccountinfo ka on cz.MemberGuid = ka.MemberGuid ")				// -- 会员卡 
							.append(" left join dt_memberaccountinfo kaold on cz.MemberGuidOld = kaold.MemberGuid ")	// -- 会员卡old 
							.append(" where (1=1) ")
//							.append(" and cz.Status = '正常' ")	// 状态为正常
							.append(" and CONVERT(VARCHAR(19),cz.CreateDate,120) >  '"+yw_time[0]+"' ")
							.append(" and CONVERT(VARCHAR(19),cz.CreateDate,120) <= '"+yw_time[1]+"' ")
							;
							if("西山".equals(dian))
								query_cikainfo.append( " and (cz.CreateVpnName = '西山' or (cz.CreateVpnName is null and ck.DefaultTimesCardName not like '国际专用%') ) " );
							else if("国际".equals(dian))
								query_cikainfo.append( " and (cz.CreateVpnName = '国际' or (cz.CreateVpnName is null and ck.DefaultTimesCardName like '国际专用%') ) " );
							else
								query_cikainfo.append( " and (cz.CreateVpnName = '").append(dian).append("' ) ");
							
							// 新次卡-消费、调整、转出
			  query_cikainfo.append(" union all ")
							.append(" select ")
							.append(" case when xf.History_Type in ('转出','调整') then '充负数' else '消费' end xmdl ")		// 如果 是 转出or调减，则由 消费 改成 充负数 （HK 2019年1月10日16:50:25）
							.append(",'' xmlx ")	// 项目类型 用作 充值类型（HK 2019年1月8日15:23:43）
							.append(",'' consumewaternum ")
							.append(",ck.TimesCardDefaultId + '@' + ka.memberid timescardwaternum ")
							.append(",xf.BillId BillId ")
							.append(",'' vdef02	")	// --账单信息
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
							.append(",case when xf.History_Type='转出' then '转出' " +
									"      when xf.History_Type='调整' then '调减'" +
									"      else '' end vdef05 ")	// // 真正的项目类型 借用自定义05 （HK 2019年1月8日15:23:43）
							.append(" from M_TimesCardMemberHistory xf ")	// -- 减次 
							.append(" left join M_TimesCardMember cz on cz.uuid=xf.TimesCardMember_Uuid ")	// -- 充次（减次 是依据 充次的数据 来减）
							.append(" left join M_TimesCardDefault ck on cz.TimesCardDefaultId = ck.TimesCardDefaultId ")	// -- 次卡信息 
							.append(" left join dt_memberaccountinfo ka on cz.MemberGuid = ka.MemberGuid ")				// -- 会员卡
							.append(" left join dt_memberaccountinfo kades on xf.MemberGuid_Des = kades.MemberGuid ")	// -- 会员卡des
							.append(" where (1=1) ")
							.append(" and xf.VpnName = '").append(dian).append("' ")
							.append(" and CONVERT(VARCHAR(19),xf.OperateDate,120) >  '"+yw_time[0]+"' ")
							.append(" and CONVERT(VARCHAR(19),xf.OperateDate,120) <= '"+yw_time[1]+"' ")
							;
						  	if("西山".equals(dian))
								query_cikainfo.append( " and (xf.VpnName = '西山' or (xf.VpnName is null and ck.DefaultTimesCardName not like '国际专用%') ) " );
							else if("国际".equals(dian))
								query_cikainfo.append( " and (xf.VpnName = '国际' or (xf.VpnName is null and ck.DefaultTimesCardName like '国际专用%') ) " );
							else
								query_cikainfo.append( " and (xf.VpnName = '").append(dian).append("' ) ");
						  	
//						System.out.println("===="+query_cikainfo);
						
						ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo.toString(), new BeanListProcessor(CikainfoTempVO.class));
						
						if(list_cikainfo_query.size()==0) continue;
						
						insertVOS(list_cikainfo_query);//将业务数据插入到NC临时表
						
						// 整理 插入语句
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
										.append(",ckinfo.vdef02 vbdef02 ")	// 账单信息
										.append(",ckinfo.vdef03 vbdef03 ")	// 不减次
										.append(",ckinfo.vdef04 y_ka_code ")// 源卡号
										.append(",ckinfo.vdef05 xmlx ")		// 将中间表的自定义5 赋值给 项目类型（HK 2019年1月8日15:25:28）
										.append(",kaold.pk_hk_huiyuan_kadangan y_ka_pk ")// 源卡pk
										.append(" from hk_huiyuan_cikainfo_temp ckinfo ")
										.append(" left join hk_huiyuan_kadangan ka    on ckinfo.memberid = ka.ka_code    and nvl(ka.dr,0)=0 ")		// 卡
										.append(" left join hk_huiyuan_kadangan kaold on ckinfo.vdef04   = kaold.ka_code and nvl(kaold.dr,0)=0 ")	// 源卡
										.append(" where (1=1) ")
										/**
										 * 测试
										 * HK 2019年1月10日16:34:45
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
							{// 循环处理 子表VO
								
								cikainfoBVOs[i].setVrowno(i+1);
								
								if( "消费".equals( cikainfoBVOs[i].getXmdl() ) )
								{
									if( !"转出".equals( cikainfoBVOs[i].getXmlx() )
									 && !"调减".equals( cikainfoBVOs[i].getXmlx() )
									)
									{
										cikainfoBVOs[i].setShuliang(	// 消费 取负数
											UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
											);
									}
									
									// 找到 次卡充值的 卡比例，进行赋值。 （在 界面做检查的时候 进行赋值）
									cikainfoBVOs[i].setKabili( null );
									cikainfoBVOs[i].setDanjia( null );
								}
								
								else if( "充值".equals( cikainfoBVOs[i].getXmdl() )	)
								{
									if("免费".equals( cikainfoBVOs[i].getCzlx() ))
									{
										cikainfoBVOs[i].setKabili( UFDouble.ZERO_DBL );	// 卡比例 必须有值， 可以为0
									}
									
									cikainfoBVOs[i].setJine( // 金额 = 卡比例 * 数量 * 单价
											PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getKabili())
											.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
											.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getDanjia()))
											.setScale(2, UFDouble.ROUND_HALF_UP)
											);
									
									/**
									 * 转入的  清空 卡比例 和 单价， 通过 转出的来获得
									 * HK 2019年1月10日17:00:35
									 */
									if("转入".equals( cikainfoBVOs[i].getCzlx() ))
									{
										cikainfoBVOs[i].setKabili(null);
										cikainfoBVOs[i].setDanjia(null);
										cikainfoBVOs[i].setJine(null);
										cikainfoBVOs[i].setXmlx("转入");
									}
									
								}
								
								else if( "充负数".equals( cikainfoBVOs[i].getXmdl() )
										)
								{// 充负数 要清空 业务系统传过来的  卡比例、单价， 在 界面做检查的时候 进行赋值
									
									cikainfoBVOs[i].setKabili(null);
									cikainfoBVOs[i].setDanjia(null);
									
								}
								
							}
							
							// 处理 主表VO
							CikainfoHVO cikainfoHVO = new CikainfoHVO();
							cikainfoHVO.setIbillstatus(-1);	// 保存态
							cikainfoHVO.setDbilldate(new UFDate( yw_date ));
							cikainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
							cikainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
							cikainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
							cikainfoHVO.setPk_org( corp );
							cikainfoHVO.setCreator(HKJT_PUB.MAKER);
							cikainfoHVO.setCreationtime(new UFDateTime());
							cikainfoHVO.setVbillcode( dian+"-"+yw_date );
							cikainfoHVO.setVbilltypecode("HK29");
							cikainfoHVO.setVdef01(dian);	// 业务门店
							// 聚合VO
							CikainfoBillVO cikainfoBillVO = new CikainfoBillVO();
							cikainfoBillVO.setParent(cikainfoHVO);
							cikainfoBillVO.setChildrenVO(cikainfoBVOs);
							
							// 插入 聚合VO
							IHy_cikainfoMaintain itf = NCLocator.getInstance().lookup(IHy_cikainfoMaintain.class);
							itf.insert(new CikainfoBillVO[]{cikainfoBillVO}, null);
							
							// 清空 临时表
							getBaseDAO().deleteByClause(CikainfoTempVO.class, " 1=1 ");
						}
						
					}
					
					else
					{// 行数 大于 2500， 则需要拆单处理， 2000行 拆一单。（按照 次卡项目，分批读取 并且进行处理）
						
						{
							// 先处理 消费的数据
							StringBuffer query_cikainfo_xf = 
								new StringBuffer("")
									// 消费 汇总，按 次卡号、账单号
									.append(" SELECT ")
									.append(" '消费' xmdl ")
									.append(",'' xmlx ")
									.append(",'' consumewaternum ")
									.append(",a.timescardwaternum ")
									.append(",a.BillId ")
									.append(",case when CHARINDEX('门票',b.goodsname)=0 then '' else replace( b.goodsname,'超时','') end vdef02 ")	// 账单信息
									.append(",max(CONVERT(VARCHAR(19),c.OperateDate,120)) operateDate ")
									.append(",sum(b.NumberCount) NumberCount ")
									.append(",null Price ")
									.append(",max(f.Memberid) Memberid ")
									.append(",max(e.ItemId) itemid ")
									.append(",max(e.ItemName) ItemName ")
									.append(",max(CONVERT(VARCHAR(10),e.startdata,120)) startdata ")
									.append(",max(CONVERT(VARCHAR(10),e.ExpData,120)) expdata ")
									.append(",'' vdef03 ")	// 不减次
									.append(" from Dt_TimescardDetails a ")
									.append(" LEFT JOIN "+db_str+".dbo.Sn_Consumesellog b ON a.ConsumeWaternum = b.WaterNum ")
									.append(" LEFT JOIN "+db_str+".dbo.Sn_Bill c ON b.BillId = c.BillId ")
									.append(" LEFT JOIN Dt_TimesCard e ON a.TimesCardWaternum = e.WaterNum ")
									.append(" LEFT JOIN dbo.hk_member f ON e.MemberGuid = f.MemberGuid ")
									.append(" where (1=1) ")
									.append(" and SUBSTRING(a.Billid,15,3)='"+flag+"' ")
									.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) >  '"+yw_time[0]+"' ")
									.append(" and CONVERT(VARCHAR(19),c.OperateDate,120) <= '"+yw_time[1]+"' ")
									.append(" and e.WaterNum is not null ")		// 必须关联到  次卡档案  （2016年2月21日13:39:13）
									.append("  group by a.BillId,a.timescardwaternum ")	
									.append("          ,case when CHARINDEX('门票',b.goodsname)=0 then '' else replace( b.goodsname,'超时','') end ")// 按 账单信息 汇总
							;
							
							ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo_xf.toString(), new BeanListProcessor(CikainfoTempVO.class));
							
							if(list_cikainfo_query.size()==0) continue;
							
							insertVOS(list_cikainfo_query);//将业务数据插入到NC临时表
							
							// 整理 插入语句
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
											.append(",ckinfo.vdef02 vbdef02 ")	// 账单信息
											.append(",ckinfo.vdef03 vbdef03 ")	// 不减次
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
								{// 循环处理 子表VO
									
									cikainfoBVOs[i].setVrowno(i+1);
									
									if("消费".equals( cikainfoBVOs[i].getXmdl() ))
									{
										cikainfoBVOs[i].setShuliang(	// 消费 取负数
												UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
												);
										
										// 找到 次卡充值的 卡比例，进行赋值。 （在 界面做检查的时候 进行赋值）
										cikainfoBVOs[i].setKabili( null );
										cikainfoBVOs[i].setDanjia(null);
									}
									
									else if( "充值".equals( cikainfoBVOs[i].getXmdl() )	)
									{
										if("免费".equals( cikainfoBVOs[i].getCzlx() ))
										{
											cikainfoBVOs[i].setKabili( UFDouble.ZERO_DBL );	// 卡比例 必须有值， 可以为0
										}
										
										cikainfoBVOs[i].setJine( // 金额 = 卡比例 * 数量 * 单价
												PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getKabili())
												.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getShuliang()))
												.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[i].getDanjia()))
												.setScale(2, UFDouble.ROUND_HALF_UP)
												);
									}
									
									else if( "充负数".equals( cikainfoBVOs[i].getXmdl() )
											)
									{// 充负数 要清空 业务系统传过来的  卡比例、单价， 在 界面做检查的时候 进行赋值
										
										cikainfoBVOs[i].setKabili(null);
										cikainfoBVOs[i].setDanjia(null);
									}
								}
								
								// 处理 主表VO
								CikainfoHVO cikainfoHVO = new CikainfoHVO();
								cikainfoHVO.setIbillstatus(-1);	// 保存态
								cikainfoHVO.setDbilldate(new UFDate( yw_date ));
								cikainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
								cikainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
								cikainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
								cikainfoHVO.setPk_org( corp );
								cikainfoHVO.setCreator(HKJT_PUB.MAKER);
								cikainfoHVO.setCreationtime(new UFDateTime());
								cikainfoHVO.setVbillcode( dian+"-"+yw_date );
								cikainfoHVO.setVbilltypecode("HK29");
								cikainfoHVO.setVdef01(dian);	// 业务门店
								// 聚合VO
								CikainfoBillVO cikainfoBillVO = new CikainfoBillVO();
								cikainfoBillVO.setParent(cikainfoHVO);
								cikainfoBillVO.setChildrenVO(cikainfoBVOs);
								
								// 插入 聚合VO
								IHy_cikainfoMaintain itf = NCLocator.getInstance().lookup(IHy_cikainfoMaintain.class);
								itf.insert(new CikainfoBillVO[]{cikainfoBillVO}, null);
								
								// 清空 临时表
								getBaseDAO().deleteByClause(CikainfoTempVO.class, " 1=1 ");
							}
						}
						
						
						
						// 取出 所涉及的 次卡项目
						StringBuffer query_itemname = 
								new StringBuffer("select distinct a.ItemName ")
										.append(" FROM Dt_TimesCard a ")
										.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
										.append(" WHERE (isnull(a.Status,'null') <> '删除') ")
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
											.append(" case when a.IsSubTimes = '充负数' then '充负数' else '充值' end xmdl ")
											.append(",case when a.IsSubTimes = '充负数' then '' else a.Type end xmlx ")
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
											.append(",case when a.IsSubTimes = '否' then 'Y' else '' end vdef03 ")	// 不减次
											.append(" FROM Dt_TimesCard a ")
											.append(" INNER JOIN hk_member b ON a.MemberGuid = b.MemberGuid ")
											.append(" WHERE (isnull(a.Status,'null') <> '删除') ")
											.append(" and a.VpnName ='").append(dian).append("' ")
											.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >  '"+yw_time[0]+"' ")
											.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <= '"+yw_time[1]+"' ")
											.append(" and a.ItemName ='").append(itemname).append("' ")		// 按 项目名称  查询
								;
								
								ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo_cz.toString(), new BeanListProcessor(CikainfoTempVO.class));
								
								if(list_cikainfo_query.size()==0) continue;
								
								insertVOS(list_cikainfo_query);//将业务数据插入到NC临时表
								
								// 整理 插入语句
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
												.append(",ckinfo.vdef02 vbdef02 ")	// 账单信息
												.append(",ckinfo.vdef03 vbdef03 ")	// 不减次
												.append(" from hk_huiyuan_cikainfo_temp ckinfo ")
												.append(" left join hk_huiyuan_kadangan ka on ckinfo.memberid = ka.ka_code and nvl(ka.dr,0)=0 ")
												.append(" where (1=1) ")
												.append(" order by ckinfo.operatedate ")
								;
								
								ArrayList<CikainfoBVO> list_cikainfo_insert = (ArrayList<CikainfoBVO>)getBaseDAO().executeQuery(query_cikainfo_insert.toString(), new BeanListProcessor(CikainfoBVO.class));
								
								CikainfoBVO[] cikainfoBVOs_ALL = new CikainfoBVO[list_cikainfo_insert.size()];
								cikainfoBVOs_ALL = list_cikainfo_insert.toArray(cikainfoBVOs_ALL);
								
								// 计算 分多少单
								int bill_row_MaxCount = 2000;	// 每单 的 最大行数
								int row_count = list_cikainfo_insert.size();	// 总行数
								int bill_count = row_count/bill_row_MaxCount + ( row_count%bill_row_MaxCount ==0 ? 0 : 1) ;	// 总单数
								int lastBill_row_count = list_cikainfo_insert.size()%bill_row_MaxCount;
								
								for( int bill_i=1;bill_i<=bill_count;bill_i++ )
								{
									int bc_row_count = bill_row_MaxCount;	// 本次循环的  行数
									if( bill_i == bill_count )
									{// 如果 本次循环 为最后一次，并且 最后一单的 行数 不为0， 则取最后一单行数。
										bc_row_count = lastBill_row_count;
									}
									
									CikainfoBVO[] cikainfoBVOs = new CikainfoBVO[bc_row_count];
									
									for( int row_i=0;row_i<bc_row_count;row_i++ )
									{// 给 行VO 赋值
									 // 并且做 相应的 处理
										cikainfoBVOs[row_i] = cikainfoBVOs_ALL[ bill_row_MaxCount*(bill_i-1)+row_i ];
										
										cikainfoBVOs[row_i].setVrowno(row_i+1);
										
										if("消费".equals( cikainfoBVOs[row_i].getXmdl() ))
										{
											cikainfoBVOs[row_i].setShuliang(	// 消费 取负数
													UFDouble.ZERO_DBL.sub(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getShuliang()))
													);
											
											// 找到 次卡充值的 卡比例，进行赋值。 （在 界面做检查的时候 进行赋值）
											cikainfoBVOs[row_i].setKabili( null );
											cikainfoBVOs[row_i].setDanjia(null);
										}
										
										else if( "充值".equals( cikainfoBVOs[row_i].getXmdl() )	)
										{
											if("免费".equals( cikainfoBVOs[row_i].getCzlx() ))
											{
												cikainfoBVOs[row_i].setKabili( UFDouble.ZERO_DBL );	// 卡比例 必须有值， 可以为0
											}
											
											cikainfoBVOs[row_i].setJine( // 金额 = 卡比例 * 数量 * 单价
															  PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getKabili())
													.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getShuliang()))
													.multiply(PuPubVO.getUFDouble_NullAsZero(cikainfoBVOs[row_i].getDanjia()))
													.setScale(2, UFDouble.ROUND_HALF_UP)
													);
										}
										
										else if( "充负数".equals( cikainfoBVOs[row_i].getXmdl() )
												)
										{// 充负数 要清空 业务系统传过来的  卡比例、单价， 在 界面做检查的时候 进行赋值
											
											cikainfoBVOs[row_i].setKabili(null);
											cikainfoBVOs[row_i].setDanjia(null);
										}
										
									}
									
									// 处理 主表VO
									CikainfoHVO cikainfoHVO = new CikainfoHVO();
									cikainfoHVO.setIbillstatus(-1);	// 保存态
									cikainfoHVO.setDbilldate(new UFDate( yw_date ));
									cikainfoHVO.setKssj(new UFDateTime( yw_time[0] ));
									cikainfoHVO.setJssj(new UFDateTime( yw_time[1] ));
									cikainfoHVO.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
									cikainfoHVO.setPk_org( corp );
									cikainfoHVO.setCreator(HKJT_PUB.MAKER);
									cikainfoHVO.setCreationtime(new UFDateTime());
									cikainfoHVO.setVbillcode( dian+"-"+yw_date+"-"+itemname+"-"+this.buwei(bill_i) );
									cikainfoHVO.setVbilltypecode("HK29");
									cikainfoHVO.setVdef01(dian);	// 业务门店
									// 聚合VO
									CikainfoBillVO cikainfoBillVO = new CikainfoBillVO();
									cikainfoBillVO.setParent(cikainfoHVO);
									cikainfoBillVO.setChildrenVO(cikainfoBVOs);
									
									// 插入 聚合VO
									IHy_cikainfoMaintain itf = NCLocator.getInstance().lookup(IHy_cikainfoMaintain.class);
									itf.insert(new CikainfoBillVO[]{cikainfoBillVO}, null);
									
									
								}
								
								// 清空 临时表
								getBaseDAO().deleteByClause(CikainfoTempVO.class, " 1=1 ");
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
				
	}
	
	
	/**
	 * 同步 金贵 次卡余额
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
		{// 公司循环
			
			String corp = pk_orgs[org_i];
			String dian = MAP_corp_dian.get(corp);
//			String flag = MAP_dian_flag.get(dian);
		
				
			StringBuffer query_jgckyue = // 查看金贵 次卡余额
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
							.append(" and b.coach = '").append( dian ).append("' ")	// 店
							.append(" and CONVERT(VARCHAR(19),isnull(a.LastCountTime,'1990-01-01 00:00:00'),120) between '").append( kainfo_bdate.getDateBefore(1).toString().substring(0,10)+" 23:00:00" ).append("' and '").append((kainfo_edate.getDateAfter(1).toString().substring(0,10))+" 00:59:59").append("' ")	// 时间
			;
			
			ArrayList<KadanganJGCKtempVO> list_jgckyue_query = (ArrayList<KadanganJGCKtempVO>)hkjt_hg_pub_session.executeQuery(query_jgckyue.toString(), new BeanListProcessor(KadanganJGCKtempVO.class));
			insertVOS(list_jgckyue_query);//将业务数据插入到NC临时表
			
			// 临时表 关联正式表， 过滤出 未导入过的 金贵 次卡余额。
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
			
			// 将 临时表 转换成正式VO
			if(list_jgckyue_insert.size()>0)
			{
				this.getBaseDAO().insertVOList(list_jgckyue_insert);//插入卡型 正式VO list
			}
			
			// 清空 临时表
			getBaseDAO().deleteByClause(KadanganJGCKtempVO.class, " 1=1 ");
			
		}
		
	}
	
	/**
	 * 用于手工抓取 会员卡数据
	 */
	public Object insertKadangan(String ka_code) throws Exception
	{
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{Plugin_Key});
		if(!lock){
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		// 查询 开卡信息
		StringBuffer query_kaika = 
				new StringBuffer("select memberid ")
						.append(",CardType cardtypeid ")
						.append(",OutVpn coach ")
						.append(",CONVERT(VARCHAR(19),OutDate,120) lastenterdate ")
						.append(" from Dt_MakeCard ")
						.append(" where (1=1) ")
						.append(" and Status = '已发' ")
						.append(" and memberid = '").append(ka_code).append("' ")	// sql server 默认不区分大小写 （如果区分大小写，需要在等号前加上collate Chinese_PRC_CS_AS_WS）
		;
		
		ArrayList<KadanganTempVO> list_huiyuaka_query = (ArrayList<KadanganTempVO>)hkjt_hg_pub_session.executeQuery(query_kaika.toString(), new BeanListProcessor(KadanganTempVO.class));
		
		insertVOS(list_huiyuaka_query);//将卡档案插入到NC临时表
		
		// 临时表 关联正式表， 过滤出 未导入过的 卡档案。
		StringBuffer query_huiyuaka_insert = 
				new StringBuffer("select hk_huiyuan_kadangan_temp.memberid ")
						.append(",hk_huiyuan_kadangan_temp.coach ")
						.append(",hk_huiyuan_kaxing.pk_hk_huiyuan_kaxing cardtypeid ")
						.append(",hk_huiyuan_kaxing.kabili ")
						.append(" from hk_huiyuan_kadangan_temp ")
						.append(" left join hk_huiyuan_kadangan on hk_huiyuan_kadangan_temp.memberid = hk_huiyuan_kadangan.ka_code and nvl(hk_huiyuan_kadangan.dr,0)=0 ")	// 卡档案
						.append(" left join hk_huiyuan_kaxing on hk_huiyuan_kadangan_temp.cardtypeid = hk_huiyuan_kaxing.kaxing_code and nvl(hk_huiyuan_kaxing.dr,0)=0 ")	// 卡型
						.append(" where hk_huiyuan_kadangan.pk_hk_huiyuan_kadangan is null ")
		;
		ArrayList<KadanganTempVO> list_huiyuaka_insert = (ArrayList<KadanganTempVO>)getBaseDAO().executeQuery(query_huiyuaka_insert.toString(), new BeanListProcessor(KadanganTempVO.class));

		// 将 临时表 转换成正式VO
		if(list_huiyuaka_insert.size()>0)
		{
//			KadanganHVO[] kadanganHVO_insert = new KadanganHVO[list_huiyuaka_insert.size()];
			
			KadanganTempVO tempVO = list_huiyuaka_insert.get(0);
			
			KadanganHVO kadanganHVO_insert = new KadanganHVO();
			kadanganHVO_insert.setKa_code( tempVO.getMemberid() );
			kadanganHVO_insert.setKa_name( tempVO.getMembername() );
			kadanganHVO_insert.setPk_hk_huiyuan_kaxing( tempVO.getCardtypeid() );	// 卡型
			
			kadanganHVO_insert.setKabili( tempVO.getKabili() );
			kadanganHVO_insert.setKastatus("正常");
			
			kadanganHVO_insert.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
			kadanganHVO_insert.setPk_org( MAP_dian_corp.get(tempVO.getCoach()) );	// pk_rog
			kadanganHVO_insert.setVbillcode( kadanganHVO_insert.getKa_code() );
			kadanganHVO_insert.setIbillstatus(-1);
			kadanganHVO_insert.setCreator(HKJT_PUB.MAKER);
			kadanganHVO_insert.setCreationtime(new UFDateTime());
			kadanganHVO_insert.setDr(0);
			kadanganHVO_insert.setDbilldate(new UFDate());
			kadanganHVO_insert.setVdef01( tempVO.getCoach() );//开卡 店
			
			String pk = this.getBaseDAO().insertVO(kadanganHVO_insert);//插入卡型 正式VO
			
			kadanganHVO_insert.setPk_hk_huiyuan_kadangan(pk);
			
			// 清空 临时表
			getBaseDAO().deleteByClause(KadanganTempVO.class, " 1=1 ");
			
			return kadanganHVO_insert;
		}
		
		return null;
			
	}
	
	
	/**
	 * 用于手工抓取 次卡info
	 */
	public CikainfoBVO getCika(String waterNum) throws Exception
	{
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		StringBuffer query_cikainfo = new StringBuffer("")
				// 充值
				.append(" select ")
				.append(" case when a.IsSubTimes = '充负数' then '充负数' else '充值' end xmdl ")
				.append(",case when a.IsSubTimes = '充负数' then '' else a.Type end xmlx ")
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
				.append(" WHERE (isnull(a.Status,'null') <> '删除') ")
				.append(" and a.WaterNum = '").append(waterNum).append("' ")
//				.append(" and a.VpnName ='").append(dian).append("' ")
//				.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) >= '"+yw_time[0]+"' ")
//				.append(" and CONVERT(VARCHAR(19),a.OperateDate,120) <  '"+yw_time[1]+"' ")
		;
		
		ArrayList<CikainfoTempVO> list_cikainfo_query = (ArrayList<CikainfoTempVO>)hkjt_hg_pub_session.executeQuery(query_cikainfo.toString(), new BeanListProcessor(CikainfoTempVO.class));
		
		if(list_cikainfo_query.size()==0) return null;
		
		insertVOS(list_cikainfo_query);//将业务数据插入到NC临时表
		
		// 整理 插入语句
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
			
			if("免费".equals( cikainfoBVO.getCzlx() ))
			{
				cikainfoBVO.setKabili( UFDouble.ZERO_DBL );	// 卡比例 必须有值， 可以为0
			}
			
			cikainfoBVO.setJine( // 金额 = 卡比例 * 数量 * 单价
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
	 * 日期的处理, 根据 开始结束日期, 给出 要每一天的日期列表
	 */
	public ArrayList<String> getTimeDates(Object beginTime,Object endTime){
		UFDate beginDate=null;
		UFDate endDate=null;
		
		UFDateTime CurrentTime = new UFDateTime(new Date(TimeService.getInstance().getTime()));
		
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//开始结束都不为空
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(endTime.toString());
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//开始不为空，结束为空
			beginDate=new UFDate(beginTime.toString());
			endDate=CurrentTime.getDate().getDateBefore(1);
		}else{//开始结束都为空//开始为空，结束不为空
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
	 * 从金贵里 取 几天前的 会员充值信息
	 * 2016年11月1日09:52:01
	 */
	public Object queryJGchongzhi(String ka_code,String sj,String dian,Object other) throws Exception
	{
		int beforDay = 3;	// 取 几天前的数据
		
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
						// 充值、消费
						.append(" SELECT ")
						.append(" CONVERT(VARCHAR(19),b.FeeInDate,120) FeeInDate, ")	// 业务时间
						.append(" b.TrueMoney ")		// 实收
						.append("FROM hk_member a ")
						.append("INNER JOIN Dt_MemberCardComeIn b ON a.MemberGuid = b.MemberGuid ")
						.append("WHERE 1=1 ")
						.append(" and b.FeeType in ( '充值' ) ")
						.append(" and Remark<>'非卡结' ")
						.append(" and b.VpnName = '"+dian+"' ")		// 店
						.append(" and a.Memberid = '"+ka_code+"'")	// 卡号
						.append(" and CONVERT(VARCHAR(19),b.FeeInDate,120) > '"+sj+"' ")	// 时间
						;
		ArrayList list_JGinfo = (ArrayList)hkjt_hg_pub_session.executeQuery(query_JGinfo.toString(), new ArrayListProcessor());
		
		if( list_JGinfo!=null && list_JGinfo.size()>0 )
		{
			String result = "";
			UFDouble total = UFDouble.ZERO_DBL;		// 累计实际金额
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
