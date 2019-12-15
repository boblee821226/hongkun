package nc.bs.hkjt.srgk.huiguan.zhangdan.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.TimeService;
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
 * 宏昆后台任务同步数据
 * <p>
 * @author zhangjc
 * @date 2015-06-23
 * 1.会馆
 * 所涉及到的外系统数据库表(sqlserver)
 * --公共表 Dt_CardInfo,P_PartitionGoods,P_PartitionMember,Dt_MemberCardComeIn
 * --会馆表Dt_ChangeClass,Sn_Bill, Sn_BillHistory,Sn_Consumesellog,KF_HouseInfo,Dt_GoodCatalog,Sn_JiShi
 *
 */
public class ImpZhangDanBill implements IBackgroundWorkPlugin {
	public final static int  DECIMALBIT =2;//小数保留位数
	
	HashMap<String,String> MAP_dian_db   = new HashMap<String,String>();	// 店  对应  数据库
	HashMap<String,String> MAP_corp_dian = new HashMap<String,String>();	// pk_corp  对应  店
	public ImpZhangDanBill()
	{
		MAP_dian_db.put("牡丹",  "L01.jgmd");
		MAP_dian_db.put("国际",  "L02.jggj");
		MAP_dian_db.put("西山",  "L06.jgxs");
		MAP_dian_db.put("酒店",  "L04.lmt");
//		MAP_dian_db.put("朗丽兹", "L07.jgllz");	//（HK 2019年5月16日09:57:27）
		MAP_dian_db.put("朗丽兹", "L06.jgxs");
		MAP_dian_db.put("康福瑞", "L08.jgkfr");
		
		MAP_corp_dian.put("0001N510000000001SXV", "国际");
		MAP_corp_dian.put("0001N510000000001SXX", "牡丹");
		MAP_corp_dian.put("0001N510000000001SY7", "西山");
		MAP_corp_dian.put("0001N510000000001SY1", "酒店");
		MAP_corp_dian.put("0001N510000000001SY3", "朗丽兹");
		MAP_corp_dian.put("0001N510000000001SY5", "康福瑞");
	}
	
	/**
	 * HK-问题2
	 * 2019年1月8日19:11:25
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
				String[] name = name_temp.split("、");
				
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
		
		String[] pk_orgs = bgwc.getPk_orgs();//组织为必输，所以肯定有值
		
		HashMap<String,String> infoMap = getDefaultInfo(pk_orgs[0]);//得到配置表信息
			
		if(sgyhmap!=null&&sgyhmap.containsKey(pk_orgs[0]))
		{
			sgyhmap.remove(pk_orgs[0]);//清空手工优惠中定义的字段
		}
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{bgwc.getAlertTypeName()+bgwc.getPk_orgs()[0]});
		
		if(!lock){
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		
		if(infoMap!=null){
			
			FENQU_SR_MAP = this.queryFenQuKaXing();		// 获得分区卡型
			
			this.get_VDEF_Info();	// 获得账单应收项目（HK-问题2 2019年1月8日19:32:37）
			
			ArrayList<String> dates=getTimeDates(bgwc.getKeyMap().get("begintime"),bgwc.getKeyMap().get("endtime"));
			for (String date : dates) {
				String timeWhere=getTimeWhere(date);//得到日期，并组成where条件
				getBillBySql(timeWhere,infoMap,date);
			}
		}
		
		System.out.println("处理完成,共耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
	
		return null;
	}

	/**
	 * 用于代码测试
	 * 后台任务无法执行
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		long startTime = System.currentTimeMillis();
		
		String[] pk_orgs = new String[]{
				HKJT_PUB.PK_ORG_HUIGUAN_xswq
		};//西山
		
		String[] dateP = new String[]{
			"2019-05-25",
			"2019-05-25"
		};
		
		HashMap<String,String> infoMap=getDefaultInfo(pk_orgs[0]);//得到配置表信息
		
		if(sgyhmap!=null&&sgyhmap.containsKey(pk_orgs[0]))
		{
			sgyhmap.remove(pk_orgs[0]);//清空手工优惠中定义的字段
		}
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{"后台任务-会馆账单"+pk_orgs[0]});
		
		if(!lock){
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		
		if(infoMap!=null){
			
			FENQU_SR_MAP = this.queryFenQuKaXing();		// 获得分区卡型
			
			this.get_VDEF_Info();	// 获得账单应收项目（HK-问题2 2019年1月8日19:32:37）
			
			ArrayList<String> dates = getTimeDates(dateP[0],dateP[1]);
			for (String date : dates) {
				String timeWhere=getTimeWhere(date);//得到日期，并组成where条件
				getBillBySql(timeWhere,infoMap,date);
			}
		}
		
		System.out.println("处理完成,共耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
	
		return null;
	}

	/**
	 * 
	 * --时间问题  当日期为 6-24取
	 6-23 01点 至 6-24 00点59分59秒
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
	 * 将日期期间分开处理
	 * 如果结束日期为空，业务日期取截止至当天日期前一天
	 * zhangjc 
	 * 2015-8-27下午4:15:54
	 * ArrayList<String>   (date)
	 * 
	 *  * --时间问题  当前服务器时间 6-24
	 1、如果没输入条件取  6-23
	
	 2、如果 开始、结束 全都输入
	取开始至结束（包含）
	
	 3、如果 开始 输入、结束 没输入
	取开始至服务器前一天（6-23）
	
	 4、如果 开始 没输、结束 日期输入
	    则 视同为 情况1 ，不单独考虑结束日期
	 * 
	 *
	 */
	public ArrayList<String> getTimeDates(Object beginTime,Object endTime){
		UFDate beginDate=null;
		UFDate endDate=null;
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//开始结束都不为空
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(endTime.toString());
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//开始不为空，结束为空
			beginDate=new UFDate(beginTime.toString());
			endDate=getCurrentTime().getDate().getDateBefore(1);
		}else{//开始结束都为空//开始为空，结束不为空
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
//					" and Opersite = '桑拿部' " +
					/**
					 * shengji
					 * 2018年5月7日16:57:12
					 * 需要 增加 国际店 养生部的处理
					 */
					 " and ( (Opersite = '桑拿部') or (Opersite = '养生部' and ChangeClassId like 'ZYS%') ) " +
					 /**END*/
					" order by ChangeClassId ";
			ArrayList<BanCi_TempVO> list=(ArrayList<BanCi_TempVO>)hkjt_hg_zd_session.executeQuery(sql, new BeanListProcessor(BanCi_TempVO.class));
			for (int i = 0; i < list.size(); i++) {
				BanCi_TempVO banci=list.get(i);
				banci.setPk_org(infoMap.get("pk_org"));
				banci.setDbilldate(dbilldate);
			}
			insertVOS(list);//将班次信息插入到NC临时表
			//临时表与班次表关联取出未存储的临时表信息
			String sql1="select HK_SRGK_HG_BANCI_temp.* from HK_SRGK_HG_BANCI_temp left join hk_srgk_hg_banci on HK_SRGK_HG_BANCI_temp.changeclassid=hk_srgk_hg_banci.changeclassid where hk_srgk_hg_banci.changeclassid is null";
			ArrayList<BanCiVO> list1=(ArrayList<BanCiVO>)getBaseDAO().executeQuery(sql1, new BeanListProcessor(BanCiVO.class));
			getBaseDAO().insertVOList(list1);//将临时表班次同步到班次表
			
			String []bancipks=new String[list.size()];
			for (int i = 0; i < list.size(); i++) {//班次pks
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
			
			//业务系统账单主表信息
			String sql2="select aa.TurnId,aa.BillId,CONVERT(varchar, aa.OperateDate, 120) OperateDate,ah.Context,aa.OldMoney,aa.FavourMoney,aa.Shishou,aa.MemberId"
				+" from Sn_Bill aa"
				+" left join Sn_BillHistory ah on aa.BillId = ah.Billid"
				+" where 1=1 " 
				+bancipk
				+" and ltrim(aa.Remark) != '作废'"
				+" and OldMoney != 0.00"
				
				/**
				 * 测试 
				 * 2019年1月9日10:58:00
				 * 2019年5月16日11:07:50
				 */
//				 +" and aa.BillId in ('SN201905250259-06') "
				 /***END***/
				;
			
			ArrayList<ZhangDanH_TempVO> list2=(ArrayList<ZhangDanH_TempVO>)hkjt_hg_zd_session.executeQuery(sql2, new BeanListProcessor(ZhangDanH_TempVO.class));
			insertVOS(list2);
			
			//业务系统账单子表信息
			String sql3="select " +
				" ab.WaterNum " +	// 流水号
				",aa.BillId " +		// 账单号
				",ab.GoodsName " +	// 商品
				",gc.NodeName GoodsCatalogName " +	// 商品分类
		//		",case when bt.StoreName='技师部' and isnull(js.ImportLevel,'null')<>'null' then js.ImportLevel else bt.StoreName end StoreName" +	// 技师部的卖品 没关联上技师，所以还是放在技师部   （李彬  2015年7月22日15:25:31）
				",case when isnull(js.ImportLevel,'null')<>'null' and js.ImportLevel<>''  then js.ImportLevel else bt.StoreName end StoreName " + 	// 关联上技师的  则取技师的部门， 没关联上的  就取账单的部门
				",CONVERT(varchar, ab.Starttime, 120) Starttime" +
				",ab.Status status_type " +
				",ab.KeyId " +
				/**
				 * 因为生成凭证数据 有小数尾差的问题， 所以需要 取金贵数据的时候，  就截取到 两位小数
				 * 李彬  2016年5月15日15:42:38
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
				+" and ltrim(aa.Remark) != '作废' "
				+" and OldMoney != 0.00 " 
	
			 	/**
				 * 测试 
				 * 2017年9月14日16:47:11
				 * 2019年1月9日10:32:03
				 * 2019年5月16日11:03:29
				 */
//				 +" and aa.BillId='SN201905250259-06' "
				 /***END***/
				 ;
			
			ArrayList<ZhangDanB_TempVO> list3=(ArrayList<ZhangDanB_TempVO>)hkjt_hg_zd_session.executeQuery(sql3, new BeanListProcessor(ZhangDanB_TempVO.class));
			insertVOS(list3);//将账单子表存储到临时表
			/**
			 * 解析小票信息
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
			 * 根据业务系统账单 表头信息封装NC系统账单聚合VO数据
			 */
			ZhangdanBillVO[] aggvos=getZhangDanAggVOs(infoMap,list4,infoMap.get("pk_org"),hkjt_hg_pub_session);
			/**
			 * 进行保存
			 */
			IHg_zhangdanMaintain itf=NCLocator.getInstance().lookup(IHg_zhangdanMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
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
	 * 根据组织查询出默认系统信息
	 * @param pk_org
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String> getDefaultInfo(String pk_org) throws DAOException{
		String sql="select * from hk_srgk_hg_info where pk_org='"+pk_org+"'";
		HashMap<String,String> map=(HashMap<String,String>)getBaseDAO().executeQuery(sql, new MapProcessor());
		/**
		 * HK 2019年5月16日10:15:20
		 * 0310转0308
		 */
		map.put("pk_org_spfl", map.get("pk_org"));	// 取商品分类的组织
		String temp_pk_org = map.get("pk_org");
		if(HKJT_PUB.PK_ORG_HUIGUAN_xswq.equals(temp_pk_org)){
			map.put("pk_org", HKJT_PUB.PK_ORG_JIUDIAN_llzjd);
			map.put("pk_org_spfl", HKJT_PUB.PK_ORG_JIUDIAN_llzjd);
		}
		/***END***/
		
		return map;
	}
	/**
	 * 得到封装后的账单聚合VO
	 * @param infoMap
	 * @param hvos
	 * @param pk_org
	 * @param session
	 * @return
	 * @throws BusinessException
	 * @throws DbException 
	 */
	public ZhangdanBillVO[] getZhangDanAggVOs(HashMap<String,String> infoMap,ArrayList<ZhangdanHVO> hvos,String pk_org,JdbcSession session) throws BusinessException, DbException{
		
		String pk_org_spfl = infoMap.get("pk_org_spfl");	// 取 商品分类的组织
		
		ArrayList<ZhangdanBillVO> aggvos=new ArrayList<ZhangdanBillVO>();
		HashMap<String,SpflHVO> spfl=getAllSpfl(pk_org_spfl);//得到NC商品分类信息
		HashMap<String,String[]> bmxx=getAllBmxx();//得到NC部门信息
		HashMap<String,String[]> bmNames=getAllBmNameByPk();//根据部门pk得到部门名称
		IOrgVersionQryService orgVersion=NCLocator.getInstance().lookup(IOrgVersionQryService.class);
		String pk_org_v=orgVersion.getOrgUnitLastVersionByOrgID(pk_org).getPk_vid();
		String pk_group=AppContext.getInstance().getPkGroup();
		HashMap<String,ArrayList<ZhangdanBVO>> bodyvoMaps=getZhangDanBodyTempVOS(hvos);//根据主表中的billid加载临时表表体数据
		
		/**
		 * 分区金额的 修改， 直接通过 分区表  获得金额
		 * 李彬  2016年4月27日15:33:43
		 */
//		HashMap<String,HashSet<String>> fenqumap=getFenQuNameByBillIDs(session, hvos, infoMap.get("vpnname"));
		HashMap<String,HashSet<String>> fenqumap = null;	// 不用 这个变量
		/**END*/
		
		for (ZhangdanHVO zhangdanHVO : hvos) {
			ZhangdanBillVO aggvo=new ZhangdanBillVO();
			zhangdanHVO.setPk_org(pk_org);
			zhangdanHVO.setPk_org_v(pk_org_v);
			zhangdanHVO.setPk_group(pk_group);
			zhangdanHVO.setIbillstatus(-1);
			zhangdanHVO.setVbilltypecode("HK01");//单据类型
			aggvo.setParentVO(zhangdanHVO);
			
			ArrayList<ZhangdanBVO> bvos=bodyvoMaps.get(zhangdanHVO.getVbillcode());
			
			/**
			 * 针对 表体进行 尾差的处理， 如果 表体之和 不等于 账单表头的数据， 则需要叠加到 最后一行上， 确保 表头、表体 数据一致
			 * 李彬  2016年5月15日20:10:34
			 */
			if(bvos!=null&&bvos.size()>0)
			{
				UFDouble head_shishou = PuPubVO.getUFDouble_NullAsZero( zhangdanHVO.getShishou() );	// 表头的实收金额
				int last_index = -1;	// 最后一个 有 实收金额的 索引。
				UFDouble body_shishou = UFDouble.ZERO_DBL;	// 表体的 实收金额之和
				for (int i = 0; i < bvos.size(); i++) {
					UFDouble shishou = PuPubVO.getUFDouble_ZeroAsNull( bvos.get(i).getShishou() );
					if( shishou!=null )
					{
						body_shishou = body_shishou.add(shishou);
						last_index = i;
					}
				}
				
				if( head_shishou.compareTo(body_shishou)!=0 )
				{// 如果 表头实收  不等于 表体实收， 则将差额 叠加到  最后一个 有 实收金额的行
					UFDouble cha = head_shishou.sub(body_shishou);	// 差额=表头实收-表体实收
					bvos.get(last_index).setShishou( bvos.get(last_index).getShishou().add(cha) );	// 实收 = 实收 + 差额
					bvos.get(last_index).setYouhui_zidong( bvos.get(last_index).getYingshou().sub( bvos.get(last_index).getShishou() ) );	// 自动优惠 = 应收-实收
				}
			}
			/**END*/
			
			if(bvos!=null)//ZSN201506150002-01 贵宾楼
			{
				for (int i = 0; i < bvos.size(); i++) {
					ZhangdanBVO bvo=bvos.get(i);
					bvo.setVrowno(String.valueOf((i+1)*10));//给行号赋值
					SpflHVO spflVO=spfl.get(pk_org_spfl+"@@"+bvo.getSqfl_name());
					if(spflVO!=null){
						bvo.setSqfl_id(spflVO.getPk_hk_srgk_hg_spfl());//根据业务系统商品分类名称，为账单表体赋值NC系统商品分类id
						bvo.setSrxm_id(spflVO.getPk_hk_srgk_hg_srxm());//收入项目id			
					}
					
					/**
					 * 浴资 的  将 金贵传过来的  部门清空。（因为 实际业务，是 前台给录入的单子， 但是 不能将收入归到前台）
					 * 目的是取 商品分类  默认部门。
					 * 李彬
					 * 2016年4月23日10:45:53
					 */
					if( "门票".equals( bvo.getSq_name() ) )
					{
						bvo.setBm_name(null);
					}
					/**END*/
					
					if(bvo.getBm_name()==null&&spflVO!=null){//部门名称为空，商品分类不为空
						bvo.setBm_id(spflVO.getPk_dept());//根据业务系统商品分类名称，赋值默认部门pk
						String []bm=bmNames.get(bvo.getBm_id());
						bvo.setBm_name(bm==null?null:bm[0]);//根据部门id获得部门名称
						bvo.setBm_fid(bm==null?null:bm[1]);//上级部门id
					}else{//部门不为空则将NC部门档案pk值赋上
						bvo.setBm_id(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[0]);//根据业务系统部门名称赋值档案主键
						bvo.setBm_fid(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[1]);//上级部门id
					}
					
					if(
						// 如果部门名称为空,并且商品分类为 浴资
						( null == PuPubVO.getString_TrimZeroLenAsNull(bvo.getBm_name()) && ("浴资".equals(bvo.getSqfl_name())||"自动加浴资".equals(bvo.getSqfl_name())) )
						// 如果部门为前台，商品分类为 净桑
					 || ( "前台".equals(PuPubVO.getString_TrimZeroLenAsNull(bvo.getBm_name())) && ("净桑".equals(bvo.getSqfl_name())) )
					)
					{
						if(bvo.getSq_name()!=null&&bvo.getSq_name().contains("男")){//商品名称包含男
							if("西山温泉".equals(infoMap.get("org_name")))
								bvo.setBm_name("国王温泉");
							else if("贵宾楼".equals(infoMap.get("org_name")))
								bvo.setBm_name("男浴");
							else
								bvo.setBm_name("男浴");
						}else if(bvo.getSq_name()!=null&&bvo.getSq_name().contains("女")){
							if("西山温泉".equals(infoMap.get("org_name")))
								bvo.setBm_name("皇后温泉");
							else if("贵宾楼".equals(infoMap.get("org_name")))
								bvo.setBm_name("女浴");
							else
								bvo.setBm_name("女浴");
						}
						else
						{// 贵宾楼 有 只有门票的情况，没有分男女，所以要处理这种情况， 默认分配到男浴。
						 // 贵宾楼 有 只有净桑的情况，没有分男女，所以要处理这种情况， 默认分配到男浴。
							if("西山温泉".equals(infoMap.get("org_name"))){
								bvo.setBm_name("国王温泉");
							}else{
								bvo.setBm_name("男浴");
							}
						}
		
						bvo.setBm_id(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[0]);	//根据业务系统部门名称赋值档案主键
						bvo.setBm_fid(bmxx.get(pk_org+"@@"+bvo.getBm_name())==null?null:bmxx.get(pk_org+"@@"+bvo.getBm_name())[1]);	//上级部门id
					}
					
					if(bvo.getBm_fid()==null){//如果上级部门id为空，则将上级部门赋值为本部门的pk
						bvo.setBm_fid(bvo.getBm_id());
					}
				}
			}
		
			if(bvos==null||bvos.size()==0)continue;//如果表体数据为空，则滤掉表头数据
			
			aggvo.setChildrenVO(bvos.toArray(new ZhangdanBVO[]{}));
			/**
			 * 测试测试
			 * 主要分摊算法
			 */
			execBodyValues(aggvo,session,infoMap.get("vpnname"),fenqumap);//计算表体 [卡比例优惠][现金][POS][会员卡][确认收入]的值
			/**END**/
			aggvos.add(aggvo);
		}
		
		return aggvos.toArray(new ZhangdanBillVO[]{});
	}

	/**
	 * 根据账单表头数据，加载账单临时表数据到HashMap，key=vbillcode ，value=ArrayList<ZhangdanBVO>
	 * zhangjc
	 * 2015-7-28上午9:35:39
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
		
		
		String sql="select billid pk_hk_dzpt_hg_zhangdan,WATERNUM,GOODSNAME sq_name,GOODSCATALOGNAME sqfl_name,STORENAME bm_name,MONEY yingshou,REALMONEY shishou,mebercardid,numberxount, round(MONEY,6)-round(REALMONEY,6) youhui_zidong ,keyid,decode(status_type,'次卡',MONEY,0) cika from hk_srgk_hg_zhangdan_b_temp where 1=1 "+vbillcodes+" order by keyid,status_type,starttime";
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
 * 2015-8-28下午1:16:24
 * void
 *清空临时表数据
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
	 * 表体字段赋值
	 * @param aggvo
	 * @throws BusinessException 
	 */
	public void execBodyValues(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException {
		/**
		 * 测试测试
		 */
		execBodySgyhFentan(aggvo,session,vpnname,fenqumap);//表体手工优惠金额分摊
		/**
		 * 测试测试
		 */
		execBodyMoneyFenTan(aggvo);		//表体金额按实收比例 直接分摊  
	}


	/**
	 * 表体金额按实收比例 直接分摊
	 * @param aggvo
	 */
	public void execBodyMoneyFenTan(ZhangdanBillVO aggvo) {
		ZhangdanHVO hvo=aggvo.getParentVO();
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//最后一个实收不为零的行
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			sumShiShou=sumShiShou.add(bvo.getShishou());
			if(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0){
				lastNotZeroRow=i;
			}
		}
		UFDouble sumQrsr=UFDouble.ZERO_DBL;//表体确认收入累加和
		UFDouble hyk_ys=UFDouble.ZERO_DBL;//会员卡冲卡应收
		UFDouble hyk_sr=UFDouble.ZERO_DBL;//会员卡冲卡收入
		UFDouble ck_ys=UFDouble.ZERO_DBL;
		UFDouble ck_sr=UFDouble.ZERO_DBL;
		
		UFDouble yhje_temp=UFDouble.ZERO_DBL;//卡比例优惠金额累加
		UFDouble xj_temp=UFDouble.ZERO_DBL;//现金金额累加
		UFDouble pos_temp=UFDouble.ZERO_DBL;//POS金额累加
		UFDouble hyk_temp=UFDouble.ZERO_DBL;//会员卡金额累加
		UFDouble gzje_temp=UFDouble.ZERO_DBL;//挂账金额累加
		UFDouble wlje_temp=UFDouble.ZERO_DBL;//往来金额累加
		
		String[][] matchField=matchDirectFenTanFields();
		String []headField=matchField[0];
		String []bodyField=matchField[1];
		
		UFDouble []sumField=new UFDouble[headField.length];
		for (int i = 0; i < sumField.length; i++) {
			sumField[i]=UFDouble.ZERO_DBL;
		}
		
		for ( int i=0;i<bvos.length;i++ ) {//优惠金额分摊
			ZhangdanBVO bvo=bvos[i];
			if(isZero(bvo.getShishou())||isZero(sumShiShou))continue;
			UFDouble bili=bvo.getShishou().div(sumShiShou).setScale(4, UFDouble.ROUND_HALF_UP);//比例
			
			yhje_temp=execBodyKblyh(hvo,bvo,i,lastNotZeroRow,bili,yhje_temp);//计算表体卡比例优惠
			xj_temp=execBodyXjFentan(hvo,bvo,i,lastNotZeroRow,bili,xj_temp);//表体现金分摊
			pos_temp=execBodyPOSFentan(hvo,bvo,i,lastNotZeroRow,bili,pos_temp);//表体POS机消费金额分摊
			hyk_temp=execBodyHykFentan(hvo,bvo,i,lastNotZeroRow,bili,hyk_temp);//表体会员卡消费金额分摊
//			gzje_temp=execBodyguazhangFentan(hvo,bvo,i,lastNotZeroRow,bili,gzje_temp);//账单表体挂账金额分摊计算
			wlje_temp=execBodyWangLaiFentan(hvo,bvo,i,lastNotZeroRow,bili,wlje_temp);//账单表体往来信息（团购门票）金额分摊计算
			
			for (int j = 0; j < sumField.length; j++) {
				sumField[j]=execBodyJineFentan(hvo,bvo,i,lastNotZeroRow,bili,sumField[j],headField[j],bodyField[j]);//扩展  其他金额分摊
			}
			
			bvo.setShouru(bvo.getShishou().sub(nullAsZero(bvo.getYouhui_kabili())).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//确认收入=实收-卡比例优惠
			sumQrsr=sumQrsr.add(bvo.getShouru());
			if("会员卡".equals(bvo.getSqfl_name())){//商品分类名称为会员卡
				bvo.setChongka_hyk_ys(bvo.getYingshou());//会员卡应收=应收
				bvo.setChongka_hyk_sr(bvo.getShouru());//会员卡收入=确认收入
				hyk_ys=hyk_ys.add(nullAsZero(bvo.getYingshou()));
				hyk_sr=hyk_sr.add(nullAsZero(bvo.getShouru()));
			}else if(getIsTcfs(bvo)){//"次卡套餐打折".equals(bvo.getSqfl_name())||"套餐方案".equals(bvo.getSqfl_name())
				bvo.setChongka_ck_ys(bvo.getYingshou());//次卡应收=应收
				bvo.setChongka_ck_sr(bvo.getShouru());//次卡收入=确认收入
				ck_ys=ck_ys.add(nullAsZero(bvo.getYingshou()));
				ck_sr=ck_sr.add(nullAsZero(bvo.getShouru()));
			}
		}
		
		hvo.setShouru(sumQrsr);//表头确认收入=表体确认收入和
		hvo.setChongka_hyk_ys(hyk_ys);//会员卡冲卡应收
		hvo.setChongka_hyk_sr(hyk_sr);//会员卡冲卡收入
		hvo.setChongka_ck_ys(ck_ys);
		hvo.setChongka_ck_sr(ck_sr);
	}

	public boolean getIsTcfs(ZhangdanBVO bvo){
		return "次卡套餐打折".equals(bvo.getSqfl_name())||"套餐方案".equals(bvo.getSqfl_name());
	}
	/**
	 * 计算账单卡比例优惠
	 * @param aggvos
	 */
	public UFDouble execBodyKblyh(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble yhje_temp){
		UFDouble yhje=nullAsZero(hvo.getHuiyuanka_bl());//会员卡优惠金额
		if(isZero(yhje)){//如果优惠金额为零,则不在进行计算
			return yhje_temp;	
		}
			if(i==lastNotZeroRow){//最后一行不为零的行的 卡比例优惠=总优惠-前面行优惠和
				bvo.setYouhui_kabili(yhje.sub(yhje_temp));
			}else{
				bvo.setYouhui_kabili(bili.multiply(yhje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//卡比例优惠=原实收金额占总实收金额 比例*优惠金额
				yhje_temp=yhje_temp.add(bvo.getYouhui_kabili());
		}
			return yhje_temp;	
	}
	
	/**
	 * 账单表体现金 金额分摊计算
	 * @param aggvos
	 */
	public UFDouble execBodyXjFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble xj_temp){
		UFDouble xianjin=hvo.getXianjin()==null?UFDouble.ZERO_DBL:hvo.getXianjin();//现金
		if(isZero(xianjin))return xj_temp;
			if(i==lastNotZeroRow){//最后一行不为零的行的 卡比例优惠=总优惠-前面行优惠和
				bvo.setXianjin(xianjin.sub(xj_temp));
			}else{
				bvo.setXianjin(bili.multiply(xianjin).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//表体现金=原实收金额占总实收金额 比例*现金
				xj_temp=xj_temp.add(bvo.getXianjin());
			}
			return xj_temp;
	}
	
	/**
	 * 账单表体POS金额分摊计算
	 * @param aggvos
	 */
	public UFDouble execBodyPOSFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble pos_temp){
		UFDouble pos=hvo.getPos()==null?UFDouble.ZERO_DBL:hvo.getPos();//POS机消费金额
		if(isZero(pos))return pos_temp;
			if(i==lastNotZeroRow){//最后一行不为零的行的 POS分摊=总POS-前面行POS分摊和
				bvo.setPos(pos.sub(pos_temp));
			}else{
				bvo.setPos(bili.multiply(pos).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//POS分摊=原实收金额占总实收金额 比例*POS消费
				pos_temp=pos_temp.add(bvo.getPos());
			}
			return pos_temp;
	}
	
	/**
	 * 账单表体会员卡金额分摊计算
	 * @param aggvos
	 */
	public UFDouble execBodyHykFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble hyk_temp){
		UFDouble hykxfje=hvo.getHuiyuanka()==null?UFDouble.ZERO_DBL:hvo.getHuiyuanka();//会员卡消费金额
		if(isZero(hykxfje))return hyk_temp;
			if(i==lastNotZeroRow){//最后一行不为零的行的 卡比例优惠=总优惠-前面行优惠和
				bvo.setHuiyuanka(hykxfje.sub(hyk_temp));
			}else{
				bvo.setHuiyuanka(bili.multiply(hykxfje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//会员卡=原实收金额占总实收金额 比例*会员卡消费
				hyk_temp=hyk_temp.add(bvo.getHuiyuanka());
			}
			return hyk_temp;
	}
	/**
	 * 账单表体挂账金额分摊计算
	 * @param aggvos
	 */
	public UFDouble execBodyguazhangFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble gzje_temp){
		UFDouble gzje=hvo.getGuazhang()==null?UFDouble.ZERO_DBL:hvo.getGuazhang();//挂账金额
		if(isZero(gzje))return gzje_temp;
			if(i==lastNotZeroRow){//最后一行不为零的行的 挂账金额=总挂账金额-前面行挂账金额和
				bvo.setGuazhang(gzje.sub(gzje_temp));
			}else{
				bvo.setGuazhang(bili.multiply(gzje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//行挂账金额分摊=原实收金额占总实收金额 比例*挂账金额
				gzje_temp=gzje_temp.add(bvo.getGuazhang());
			}
			return gzje_temp;
	}
	
	/**
	 * zhangjc
	 * 2015-7-24下午4:21:13
	 * void
	 *账单表体往来信息（团购门票）金额分摊计算
	 */
	public UFDouble execBodyWangLaiFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble wlje_temp){
		UFDouble wlje=hvo.getWanglai()==null?UFDouble.ZERO_DBL:hvo.getWanglai();//往来金额
		if(isZero(wlje))return wlje_temp;
			if(i==lastNotZeroRow){//最后一行不为零的行的 挂账金额=总挂账金额-前面行挂账金额和
				bvo.setWanglai(wlje.sub(wlje_temp));
			}else{
				bvo.setWanglai(bili.multiply(wlje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//往来金额分摊=原实收金额占总实收金额 比例*往来金额
				wlje_temp=wlje_temp.add(bvo.getWanglai());
			}
			return wlje_temp;
	}
	
	/**
	 * 扩展 表体金额分摊，（按最后的实收（优惠）比例分摊）
	 * zhangjc
	 * 2015-7-29上午9:28:16
	 * UFDouble
	 *
	 */
	public UFDouble execBodyJineFentan(ZhangdanHVO hvo,ZhangdanBVO bvo,int i,int lastNotZeroRow,UFDouble bili,UFDouble tempSum,String headField,String bodyField){
		UFDouble headMoney=nullAsZero(hvo.getAttributeValue(headField));
		if(isZero(headMoney))return tempSum;
			if(i==lastNotZeroRow){//最后一行不为零的行=表头总金额-前面行金额和
				bvo.setAttributeValue(bodyField, headMoney.sub(tempSum).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));
			}else{
				bvo.setAttributeValue(bodyField,bili.multiply(headMoney).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));
				tempSum=tempSum.add(nullAsZero(bvo.getAttributeValue(bodyField)));
			}
			return tempSum;
	}
	
	/**
	 * 账单表体手工优惠分摊计算
	 * @param aggvos
	 * @throws BusinessException 
	 */
	public void execBodySgyhFentan(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException{
		execBodyMianDanFentan(aggvo);//免单
//		setShiShou(aggvo);		// 计算实收
////////		execBodyfqjeFentan(aggvo,session,vpnname,fenqumap);		//分区金额 分摊计算
		execBodyfqjeFentan_2(aggvo,session,vpnname,fenqumap);	//分区金额 分摊计算   李彬   2016年4月27日15:26:17
//		setShiShou(aggvo);		// 计算实收
		execBodyDaijinquanFentan(aggvo);//代金券
		setShiShou(aggvo);		// 计算实收
		execBodySgYouHui_kz(aggvo);//手工优惠金额分摊，扩展
	}
	/**
	 * 设置手工优惠金额以及实收金额
	 * @param aggvo
	 * @throws BusinessException 
	 */
	public void setShiShou(ZhangdanBillVO aggvo) throws BusinessException{
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		for (ZhangdanBVO bvo : bvos) {
			
			/**
			 * 2018年7月23日17:15:57
			 * 遇到 小票里 优惠与 分区收入
			 */
//			if(  PuPubVO.getString_TrimZeroLenAsNull( bvo.getVbdef01() ) == null 
//			  || PuPubVO.getString_TrimZeroLenAsNull( bvo.getVbdef01().replaceAll("~", "") ) == null
//			  )
//			{
				//手工优惠和
				UFDouble sgyh=nullAsZero(bvo.getDaijinquan())//代金券
						  .add(nullAsZero(bvo.getFenqu()))//分区金额
						  .add(nullAsZero(bvo.getMiandan()))//免单
						  .add(nullAsZero(bvo.getYouhui_qt()));//优惠-其他
				for (String field : matchSgyhFenTanFields(aggvo.getParentVO().getPk_org())[1]) {
					if(!ZhangdanBVO.YOUHUI_QT.equals(field))
					sgyh=sgyh.add(nullAsZero(bvo.getAttributeValue(field)));
				}
				
				bvo.setYouhui_shougong(sgyh.setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//设置表体手工优惠
				bvo.setShishou(nullAsZero(bvo.getYingshou()).sub(nullAsZero(bvo.getYouhui_zidong())).sub(nullAsZero(bvo.getYouhui_shougong())));//实收=应收-自动优惠-手工优惠
				
				//空设置为零
				bvo.setYingshou(nullAsZero(bvo.getYingshou()));
				bvo.setYouhui_zidong(nullAsZero(bvo.getYouhui_zidong()));
//			}
		}
	}
	
	/**
	 * 账单分区金额的 计算
	 * 可以 从金贵数据表里  直接取到 每个账单行的  分区金额
	 * 李彬  2016年4月27日15:26:17
	 * P_PartitionConsumeDetails
	 */
	public void execBodyfqjeFentan_2(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException
	{
		try {
			ZhangdanHVO hvo = aggvo.getParentVO();
			UFDouble fqje=nullAsZero(hvo.getFenqu());//账单表头分区金额
			if(isZero(fqje))return;	// 只有 分区金额  不为空，  才往下进行。
			
			String pk_org = hvo.getPk_org();
			String db_str = MAP_dian_db.get( MAP_corp_dian.get(pk_org) );
			
			Connection hkjt_hg_pub_conn=null;
			JdbcSession hkjt_hg_pub_session =null;
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
			
			StringBuffer querySQL = // 连 公共库， 然后通过 db_str 关联各店业务库
					new StringBuffer("select ")
							.append(" a.waternum ")				// 账单行水号
							.append(",sum(b.PartitionMoney) ")	// 分区金额
							.append(",max(a.membercardid) ")	// 卡号
							.append(",max(hk.GriftMoney) ")		// 卡比例
							.append(",max(hk.CardTypeId) ")		// 卡型
							.append(" from "+db_str+".dbo.Sn_Consumesellog a ")
							.append(" left join P_PartitionConsumeDetails b on a.waterNum=b.ConsumeId ")
							.append(" left join hk_member hk on hk.Memberid=a.membercardid ")
							.append(" where (1=1) ")
							.append(" and b.PartitionMoney is not null ")
							.append(" and a.BillId = '"+hvo.getVbillcode()+"' ")	// 账单号
							.append(" group by a.waternum ")	// 按 账单水号 进行汇总
			;
		
			ArrayList<Object> list = (ArrayList<Object>)hkjt_hg_pub_session.executeQuery(querySQL.toString(),new ArrayListProcessor());
			
			if( list!=null && list.size()>0 )
			{// 如果有 分区明细，  才进行 下面的处理
				
				// 1、先将 表体 汇总成 HashMap，  方便第二步的处理
				HashMap<String,ZhangdanBVO> bVO_MAP = new HashMap<String,ZhangdanBVO>();	// key-账单行 水号  value-账单行VO
				ZhangdanBVO[] bVOs = (ZhangdanBVO[])aggvo.getChildrenVO();	// 表体vos
				for(int i=0;i<bVOs.length;i++)
				{
					bVO_MAP.put(bVOs[i].getWaternum(), bVOs[i]);
				}
				
				// 2、对 分区数据，进行循环处理
				for(int i=0;i<list.size();i++)
				{
					Object[] obj = (Object[])list.get(i);
					String waterNum = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					UFDouble fqje_body = PuPubVO.getUFDouble_NullAsZero(obj[1]);
					
					String ka_code  = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);	// 卡号
					UFDouble kabili = PuPubVO.getUFDouble_NullAsZero(obj[3]);		// 卡比例
					String ka_type  = PuPubVO.getString_TrimZeroLenAsNull(obj[4]);	// 卡型
					
					ZhangdanBVO bvo = bVO_MAP.get(waterNum);
					
					if( ka_type!=null && this.FENQU_SR_MAP.containsKey(ka_type))
					{
						/**
						 *  账单表体
						 *  自定义01：分区金额（收入）
						 *  自定义02：分区卡号
						 *  自定义03：分区卡比例
						 */
						bvo.setVbdef01(fqje_body.toString());
						bvo.setVbdef02(ka_code);
						bvo.setVbdef03(kabili.toString());
						
						UFDouble shihou_kabili = fqje_body.multiply(kabili).setScale( 2 , UFDouble.ROUND_HALF_UP );
						UFDouble youhui_kabili = fqje_body.sub(shihou_kabili);
						bvo.setShouru(shihou_kabili);			// 确认收入 = 实收 * 卡比例
						bvo.setYouhui_kabili(youhui_kabili);	// 卡比例优惠 = 实收 - 确认收入
						
						/**
						 * 账单表头
						 * 将 分区金额 累计到 会员卡金额上
						 */
						hvo.setHuiyuanka(	// 会员卡金额 += 表体分区金额
								  PuPubVO.getUFDouble_NullAsZero( hvo.getHuiyuanka() )
							.add( fqje_body )
						);
						hvo.setFenqu( 		// 分区金额 -= 表体分区金额
								  PuPubVO.getUFDouble_NullAsZero( hvo.getFenqu() )
							.sub( fqje_body )
						);
						
						hvo.setHuiyuanka_sj(	// 会员卡实际金额 += 表体确认收入
								  PuPubVO.getUFDouble_NullAsZero( hvo.getHuiyuanka_sj() )
							.add( shihou_kabili )
						);
						
						hvo.setHuiyuanka_bl(	// 卡比例优惠 += 表体卡比例优惠
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
	 * 账单表体分区金额分摊计算
	 * @param aggvos
	 * @throws BusinessException 
	 */
	public void execBodyfqjeFentan(ZhangdanBillVO aggvo,JdbcSession session,String vpnname,HashMap<String,HashSet<String>> fenqumap) throws BusinessException{
		ZhangdanHVO hvo=aggvo.getParentVO();
		//根据账单编号 得到分区对应项目(或项目分类)名称
		HashSet<String> fenquItem=getFenQuNameByBillID(session, hvo.getVbillcode(), vpnname, hvo.getDbilldate());
//		HashSet<String> fenquItem=fenqumap.get(hvo.getVbillcode())==null?new HashSet<String>():fenqumap.get(hvo.getVbillcode());
		UFDouble fqje=nullAsZero(hvo.getFenqu());//账单表头分区金额
		if(isZero(fqje))return;
		
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//最后一个实收不为零的行
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			if(!getIsTcfs(bvo)&&(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0)&&//实收不为零
					(fenquItem.contains(bvo.getSqfl_name())||fenquItem.contains(bvo.getSq_name()))){//并且分区金额匹配到商品分类或匹配到具体商品
				sumShiShou=sumShiShou.add(bvo.getShishou());
				lastNotZeroRow=i;
			}
		}
		boolean isFindfenquItem=false;//是否匹配到了分区项目
		UFDouble fqje_temp=UFDouble.ZERO_DBL;//分区金额累加
		for ( int i=0;i<bvos.length;i++ ) {//金额分摊
			ZhangdanBVO bvo=bvos[i];
			if(getIsTcfs(bvo)||isZero(bvo.getShishou())||
					(!(fenquItem.contains(bvo.getSqfl_name())||fenquItem.contains(bvo.getSq_name())))){
				bvo.setFenqu(UFDouble.ZERO_DBL);//将null赋值为0
				continue;}
			isFindfenquItem=true;
			if(i==lastNotZeroRow){//最后一行不为零的行的 分区金额=总分区金额-前面分区金额和
				bvo.setFenqu(fqje.sub(fqje_temp));
			}else{
				bvo.setFenqu(bvo.getShishou().div(sumShiShou).multiply(fqje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));// 分区金额=原实收金额占总实收金额 比例*总消费分区金额
				fqje_temp=fqje_temp.add(bvo.getFenqu());
			}
		}
		if(!isFindfenquItem){
			 sumShiShou=UFDouble.ZERO_DBL;
			 lastNotZeroRow=-1;//最后一个实收不为零的行
			for (int i=0;i<bvos.length;i++ ) {
				ZhangdanBVO bvo=bvos[i];
				if(!getIsTcfs(bvo)&&(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0)){//实收不为零
					sumShiShou=sumShiShou.add(bvo.getShishou());
					lastNotZeroRow=i;
				}
			}
			if(isZero(sumShiShou))return;
			fqje_temp=execBodyfqjeFentan(hvo,bvos,lastNotZeroRow,sumShiShou,fqje_temp);
		}
	}
	
	/**
	 * 账单表体分区金额匹配不到时分摊计算
	 * @param aggvos
	 * @throws BusinessException 
	 */
	public UFDouble execBodyfqjeFentan(ZhangdanHVO hvo,ZhangdanBVO []bvos,int lastNotZeroRow,UFDouble sumShiShou,UFDouble fqje_temp) throws BusinessException{
		UFDouble fqje=nullAsZero(hvo.getFenqu());//账单表头分区金额
		if(isZero(fqje))return fqje_temp;
		for ( int i=0;i<bvos.length;i++ ) {//金额分摊
			ZhangdanBVO bvo=bvos[i];
			if(getIsTcfs(bvo)||isZero(bvo.getShishou())){
				bvo.setFenqu(UFDouble.ZERO_DBL);//将null赋值为0
				continue;}
			if(i==lastNotZeroRow){//最后一行不为零的行的 分区金额=总分区金额-前面分区金额和
				bvo.setFenqu(fqje.sub(fqje_temp));
			}else{
				bvo.setFenqu(bvo.getShishou().div(sumShiShou).multiply(fqje).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));// 分区金额=原实收金额占总实收金额 比例*总消费分区金额
				fqje_temp=fqje_temp.add(bvo.getFenqu());
			}
		}
		return fqje_temp;
	}
	
	/**
	 * 账单表体免单分摊计算
	 * @param aggvos
	 */
	public void execBodyMianDanFentan(ZhangdanBillVO aggvo){
		ZhangdanHVO hvo=aggvo.getParentVO();
		UFDouble miandan=hvo.getMiandan()==null?UFDouble.ZERO_DBL:hvo.getMiandan();//免单
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//最后一个实收不为零的行
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0&&!getIsTcfs(bvo)){
				sumShiShou=sumShiShou.add(bvo.getShishou());
				lastNotZeroRow=i;
			}
		}
		if(sumShiShou.compareTo(UFDouble.ZERO_DBL)==0)return;
		UFDouble miandan_temp=UFDouble.ZERO_DBL;
		for ( int i=0;i<bvos.length;i++ ) {//金额分摊
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()==null||bvo.getShishou().compareTo(UFDouble.ZERO_DBL)==0||getIsTcfs(bvo)){
				continue;}
			if(i==lastNotZeroRow){//最后一行不为零的行的金额分摊
				bvo.setAttributeValue(ZhangdanHVO.MIANDAN,miandan.sub(miandan_temp));				
			}else{
				UFDouble ftbl=bvo.getShishou().div(sumShiShou);//分摊比例
				bvo.setAttributeValue(ZhangdanHVO.MIANDAN,ftbl.multiply(miandan).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));
				miandan_temp=miandan_temp.add(new UFDouble(bvo.getAttributeValue(ZhangdanHVO.MIANDAN)==null?"0":bvo.getAttributeValue(ZhangdanHVO.MIANDAN).toString()));
			}
			
			
		}
	}
	
	/**
	 * 账单表体代金券分摊计算
	 * @param aggvos
	 */
	public void execBodyDaijinquanFentan(ZhangdanBillVO aggvo){
		ZhangdanHVO hvo=aggvo.getParentVO();
		UFDouble yhq=hvo.getDaijinquan()==null?UFDouble.ZERO_DBL:hvo.getDaijinquan();//代金券
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//最后一个实收不为零的行
		for (int i=0;i<bvos.length;i++ ) {
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()!=null&&bvo.getShishou().compareTo(UFDouble.ZERO_DBL)!=0&&!getIsTcfs(bvo)){
				sumShiShou=sumShiShou.add(bvo.getShishou());
				lastNotZeroRow=i;
			}
		}
		if(sumShiShou.compareTo(UFDouble.ZERO_DBL)==0)return;
		UFDouble yhq_temp=UFDouble.ZERO_DBL;
		for ( int i=0;i<bvos.length;i++ ) {//金额分摊
			ZhangdanBVO bvo=bvos[i];
			if(bvo.getShishou()==null||bvo.getShishou().compareTo(UFDouble.ZERO_DBL)==0||getIsTcfs(bvo)){
				continue;}
			if(i==lastNotZeroRow){//最后一行不为零的行的金额分摊
				bvo.setAttributeValue(ZhangdanBVO.DAIJINQUAN,yhq.sub(yhq_temp));//代金券
			}else{
				UFDouble ftbl=bvo.getShishou().div(sumShiShou);//分摊比例
				bvo.setAttributeValue(ZhangdanBVO.DAIJINQUAN,ftbl.multiply(yhq).setScale(DECIMALBIT, UFDouble.ROUND_HALF_UP));//优惠券
				yhq_temp=yhq_temp.add(new UFDouble(bvo.getAttributeValue(ZhangdanBVO.DAIJINQUAN)==null?"0":bvo.getAttributeValue(ZhangdanBVO.DAIJINQUAN).toString()));
			}
			
			
		}
	}
	
	
	/**
	 * 手工优惠金额分摊，扩展
	 * zhangjc
	 * 2015-7-29上午11:03:46
	 * void
	 * @throws BusinessException 
	 *
	 */
	public void execBodySgYouHui_kz(ZhangdanBillVO aggvo) throws BusinessException{
		ZhangdanHVO hvo=aggvo.getParentVO();
		ZhangdanBVO []bvos=(ZhangdanBVO[]) aggvo.getChildrenVO();
		UFDouble sumShiShou=UFDouble.ZERO_DBL;
		int lastNotZeroRow=-1;//最后一个实收不为零的行
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
		
		for ( int i=0;i<bvos.length;i++ ) {//优惠金额分摊
			ZhangdanBVO bvo=bvos[i];
			if(isZero(bvo.getShishou())||isZero(sumShiShou)||getIsTcfs(bvo))continue;
			UFDouble bili=bvo.getShishou().div(sumShiShou);//比例
			
			for (int j = 0; j < sumField.length; j++) {
				sumField[j]=execBodyJineFentan(hvo,bvo,i,lastNotZeroRow,bili,sumField[j],headField[j],bodyField[j]);//扩展  其他金额分摊
				setShiShou(aggvo);
			}
		}
		
	}
	
	/**
	 * 如果是将表头金额，按照最后实收（优惠后）直接分摊到表体，只需在此写明分配的字段即可
	 * 不影响实收
	 * zhangjc
	 * 2015-7-29上午10:02:40
	 * String[][]
	 * 0 表头字段属性数组
	 * 1 表体字段属性数组
	 * 2 匹配的名称数组
	 */
	private String[][] matchDirectFenTanFields() {
		String text="票";
		String[][] field=new String[][]{
//表头字段属性数组	    
{ZhangdanHVO.ZHIPIAO},
//表体字段属性数组
{ZhangdanBVO.ZHIPIAO},
// 匹配的名称数组
{"支"+text}//支票
	};
		return field;
	}
	
	
	/**
	 * zhangjc
	 * 手工优惠扩展字段金额分摊
	 * 2015-7-29上午10:02:40
	 * String[][]
	 * 0 表头字段属性数组
	 * 1 表体字段属性数组
	 * 2 匹配的名称数组
	 * @throws BusinessException 
	 * @throws DAOException 
	 */
	private String[][] matchSgyhFenTanFields(String pk_org) throws BusinessException{
		
//		String[][] field=new String[][]{
//{ZhangdanHVO.YOUHUI_QT},//其他优惠始终放在最后一个
//{ZhangdanBVO.YOUHUI_QT},
//{"优惠"}
//	};
		return getSgYHFields(HKJT_PUB.PK_ORG_HUIGUAN);
	}
	
	HashMap<String,String[][]> sgyhmap=null;
	public String[][] getSgYHFields(String pk_org) throws BusinessException{
		if(sgyhmap!=null&&sgyhmap.containsKey(pk_org)){
			return sgyhmap.get(pk_org);
		}else{
			sgyhmap=new HashMap<String,String[][]>();
			String sql="select decode(name,'其它优惠',vdef2,name) name,decode(name,'其它优惠','youhui_qt',vdef1) vdef1 from hk_srgk_hg_jzfs where nvl(dr,0)=0 and pk_parent"+ 
					" in(select pk_hk_srgk_hg_jzfs from hk_srgk_hg_jzfs where nvl(dr,0)=0 and pk_org='"+pk_org+"' and name='手工优惠')"+
					" and name not in('代金券','次卡','免单','分区金额') "+
					" and (decode(name,'其它优惠',vdef2,'') <> '~' or (decode(name,'其它优惠','',name)<>'~' and vdef1<>'~'))";

			Vector<Vector<String>> vector=(Vector<Vector<String>>)getBaseDAO().executeQuery(sql, new VectorProcessor());
			String [] youhui_qt_names=new String[]{};
			ArrayList<String[]> list=new ArrayList<String[]>();
			for (Vector<String> v : vector) {
				if(ZhangdanHVO.YOUHUI_QT.equals(v.elementAt(1))){//如果是优惠-其他，则取出优惠项目名称（vdef2）
					youhui_qt_names=v.elementAt(0).split("、");
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
	 * 解析小票信息，并给会员卡相关信息赋值
	 * @param hvos
	 * @param session
	 * @throws BusinessException
	 */
	public void jieXiHuiYuanKa(ArrayList<ZhangdanHVO> hvos,JdbcSession session,HashMap<String,String> infoMap) throws BusinessException{
		HashMap<String,UFDouble> kblMaps=getKaBiliByCardType(session);
		String[][] wlxx=getJzfs_WangLai();//结账方式 往来 中定义的 小票往来对应 值（vdef3）  ;//"团购门票","微信支付",
		for (ZhangdanHVO hvo : hvos) {
			String context=hvo.getPk_hk_dzpt_hg_zhangdan()==null?"":hvo.getPk_hk_dzpt_hg_zhangdan();
			hvo.setPk_hk_dzpt_hg_zhangdan(null);
		if(hvo.getHuiyuanka_info()!=null){
			String[] cards=hvo.getHuiyuanka_info().split(",");
			StringBuffer context_after=new StringBuffer();
			UFDouble sumXiaoFei=UFDouble.ZERO_DBL;//各张卡消费金额合计
			UFDouble sumXiaoFei_sj=UFDouble.ZERO_DBL;//各张卡实际金额合计
			for (String str : cards) {
				Pattern p = Pattern.compile(str.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)")+"\\s*消费:\\s*(-?\\d+)(\\.\\d+)?");
		        Matcher m = p.matcher(context);
		        boolean flag=false;//是否匹配到
		        context_after.append(str+" ");//卡类型
		        while (m.find()) {
		        	flag=true;
		        	String xfjestr=m.group().substring(m.group().indexOf("消费:")+3, m.group().length());//截取出消费金额
		        	UFDouble xfje=nullAsZero(xfjestr);//消费金额
		        	context_after.append(xfje);//消费金额
		        	sumXiaoFei=sumXiaoFei.add(xfje);//将消费金额累加
		        	
		        	UFDouble kbl=kblMaps.get(str.substring(0, str.indexOf(" ")));//卡比例
			       context_after.append(" "+kbl);
			       context_after.append(";");
			       
			      UFDouble sjje=xfje.multiply(nullAsZero(kbl));//会员卡实际金额=消费金额*卡比例
			       sumXiaoFei_sj=sumXiaoFei_sj.add(sjje);//实际金额累加
		        	break;//实际只循环到一次
		        } 
		        if(!flag){//如果没有匹配到，则将消费金额赋值为0
		        	context_after.append(0);
		        	UFDouble kbl=kblMaps.get(str.substring(0, str.indexOf(" ")));//卡比例
				       context_after.append(" "+kbl);
				       context_after.append(";");
		        }
		        
			}
			if(context_after.toString().endsWith(";"))
				context_after.deleteCharAt(context_after.length()-1);
			hvo.setHuiyuanka(sumXiaoFei.setScale(DECIMALBIT,UFDouble.ROUND_HALF_UP));//卡消费金额合计
			hvo.setHuiyuanka_sj(sumXiaoFei_sj.setScale(DECIMALBIT,UFDouble.ROUND_HALF_UP));//卡消费实际金额合计
			hvo.setHuiyuanka_bl((hvo.getHuiyuanka().sub(hvo.getHuiyuanka_sj())).setScale(DECIMALBIT,UFDouble.ROUND_HALF_UP));//会员卡优惠=卡消费金额-实际金额
			hvo.setHuiyuanka_info(context_after.toString());//卡信息
		}
		
		
		String []jzfsFields=new String[]{ZhangdanHVO.XIANJIN,ZhangdanHVO.MIANDAN,ZhangdanHVO.DAIJINQUAN,ZhangdanHVO.FENQU,ZhangdanHVO.POS,ZhangdanHVO.DAIJINQUAN,ZhangdanHVO.DAIJINQUAN};
		String []jzfsNames=new String[]{"现金","免单","代金券","分区金额","信用卡","地推票","浴资券"};
		
		matchMoney(hvo, context,addStrArray(wlxx[0], jzfsFields),addStrArray(wlxx[1], jzfsNames));
		String [][] sgyhfields=matchSgyhFenTanFields(infoMap.get("pk_org"));
		matchMoney(hvo, context,sgyhfields[0],sgyhfields[2]);//手工优惠扩展，其他优惠 一直放在最后
		matchMoney(hvo, context,matchDirectFenTanFields()[0],matchDirectFenTanFields()[2]);
		
		}
	}

	/**
	 * 匹配小票金额,并赋值给VO相应字段
	 * @param hvo
	 * @param context
	 */
	public void matchMoney(SuperVO hvo, String context,String field[],String []matchMoneyName) {
		for (int i = 0; i < field.length; i++) {
			Pattern p = Pattern.compile(matchMoneyName[i]+"\\s*:\\s*(-?\\d+)(\\.\\d+)?");
	        Matcher m = p.matcher(context);
	        while (m.find()) {
	        	String xj=m.group().substring(m.group().indexOf(":")+1);	// 获得 字体匹配出来的 金额
	        	//---如果是其他优惠求和-----
	        	if(ZhangdanHVO.YOUHUI_QT.equals(field[i])&&hvo.getAttributeValue(field[i])!=null&&hvo.getAttributeValue(field[i]).toString().trim().length()>0){//如果是其他优惠则将值累加
	        	hvo.setAttributeValue(field[i],new UFDouble(hvo.getAttributeValue(field[i]).toString()).add(nullAsZero(xj)));
	        	}
	        	//---如果是郎丽兹挂 则求和，并且赋值挂账明细为郎丽兹挂-----
//	        	else if(ZhangdanHVO.GUAZHANG.equals(field[i])){
//	        	hvo.setAttributeValue(field[i],nullAsZero(hvo.getAttributeValue(field[i])).add(nullAsZero(xj)));
//	        	// 将挂账信息的  【挂、挂账】去掉， 只保留 会馆名字
//	        	String guazhang_info = matchMoneyName[i];
//	        	guazhang_info = guazhang_info.replaceAll("挂账", "");
//	        	guazhang_info = guazhang_info.replaceAll("挂", "");
//	        	hvo.setAttributeValue(ZhangdanHVO.GUAZHANG_INFO, (hvo.getAttributeValue(ZhangdanHVO.GUAZHANG_INFO)==null?"": hvo.getAttributeValue(ZhangdanHVO.GUAZHANG_INFO).toString()+",")+guazhang_info);
//	        	}
	        	else if(ZhangdanHVO.WANGLAI.equals(field[i])){
	        		hvo.setAttributeValue(field[i],nullAsZero(hvo.getAttributeValue(field[i])).add(nullAsZero(xj)));
	        		hvo.setAttributeValue(ZhangdanHVO.WANGLAI_INFO, (hvo.getAttributeValue(ZhangdanHVO.WANGLAI_INFO)==null?"": hvo.getAttributeValue(ZhangdanHVO.WANGLAI_INFO).toString()+",")+matchMoneyName[i]);
	        		/**
	        		 * 宏昆
	        		 * 2017年7月19日16:33:12
	        		 * 增加 微信支付  和 支付宝支付 的 区分
	        		 * 微信支付 用 vdef01
	        		 * 支付宝支付 用 vdef02
	        		 * 
	        		 * 2017年10月16日11:10:24
	        		 * 再增加  
	        		   	微信支付
						支付宝支付
						应收美团
						应收携程
						团购门票
	        		 */
//	        		if(	"微信支付".equals(matchMoneyName[i])
//	        		||	"WX".equals(matchMoneyName[i])
//	        		  )
//	        		{
//	        			hvo.setAttributeValue(ZhangdanHVO.VDEF01,xj);
//	        		}
//	        		else 
//	        		if( "支付宝支".equals(matchMoneyName[i]) || "支付宝支付".equals(matchMoneyName[i]) )
//	        		{
//	        			hvo.setAttributeValue(ZhangdanHVO.VDEF02,xj);
//	        		}
//	        		else 
//	        			if( "应收美团".equals(matchMoneyName[i]) )
//	        			{
//	        				hvo.setAttributeValue(ZhangdanHVO.VDEF03,xj);
//	        			}
//        			else 
//        				if( "应收携程".equals(matchMoneyName[i]) )
//        				{
//        					hvo.setAttributeValue(ZhangdanHVO.VDEF04,xj);
//        				}
//    				else 
//    					if( "团购门票".equals(matchMoneyName[i]) )
//    					{
//    						hvo.setAttributeValue(ZhangdanHVO.VDEF05,xj);
//    					}
	        		
	        		/**
	        		 * HK-问题2
	        		 * 2019年1月8日18:11:57
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
	 * 2015-8-26下午4:25:27
	 * String[][] field name
	 *得到 结账方式 往来信息中   自定义项3的值（小票上对应的名称）
	 */
	public String[][] getJzfs_WangLai()throws BusinessException{
		String sql="select distinct vdef3 from hk_srgk_hg_jzfs where nvl(dr,0)=0 and code like('05%') and vdef3 <>'~' and vdef3 is not null and pk_org='"+HKJT_PUB.PK_ORG_HUIGUAN+"' ";
		ArrayList<String> list=(ArrayList<String>)getBaseDAO().executeQuery(sql, new ColumnListProcessor());
		ArrayList<String> names=new ArrayList<String>();
		ArrayList<String> fields=new ArrayList<String>();
		for (String str : list) {
			for (String field : str.split("、")) {//结账方式-往来-vdef3    对应小票的名称
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
	 * 2015-8-26下午4:36:50
	 * String[]
	 *合并两个字符串数组
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
	 * 得到NC系统所有商品分类信息
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,SpflHVO> getAllSpfl(String pk_org_spfl) throws DAOException{
		HashMap<String,SpflHVO> map=new HashMap<String, SpflHVO>();
		String sql="select * from hk_srgk_hg_spfl where dr=0 and pk_org='"+pk_org_spfl+"' ";
		ArrayList<SpflHVO> list=(ArrayList<SpflHVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(SpflHVO.class));
		for (SpflHVO hvo : list) {
			map.put(hvo.getPk_org()+"@@"+hvo.getName(), hvo);//得到商品分类VO
		}
		
		return map;
		
	}
	
	/**
	 * 得到NC系统所有部门信息
	 * 部门pk，上级部门pk
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
			if(key!=null && key.indexOf("、")>-1)
			{	// 如果 包含 分隔符，则说明 是 多个业务部门 对应一个NC部门。 需要拆分处理。
				keys = key.split("、");
			}
			else
			{
				keys = new String[]{key};
			}
			
			for(int i=0;keys!=null&&i<keys.length;i++)
			{
				map.put(v.elementAt(0)+"@@"+keys[i], new String[]{v.elementAt(2),v.elementAt(3)});//得到部门pk，上级部门pk
			}
		}
		
		return map;
		
	}
	/**
	 * 得到NC系统所有部门信息
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String[]> getAllBmNameByPk() throws DAOException{
		HashMap<String,String[]> map=new HashMap<String, String[]>();
		String sql="select pk_dept,name,pk_fatherorg from org_dept";
		Vector<Vector<String>> vector=(Vector<Vector<String>>)getBaseDAO().executeQuery(sql, new VectorProcessor());
		for (Vector<String> v : vector) {
			map.put(v.elementAt(0),new String[]{v.elementAt(1),v.elementAt(2)});//得到部门名称，上级部门pk
		}
		
		return map;
		
	}
	/**
	 * 根据单据号在账单临时表（hk_srgk_hg_zhangdan_temp）中取出小票信息
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
        // 得到流
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        while (s != null) {
            //执行循环将字符串全部取出付值给StringBuffer
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
	 * 根据会员卡类型得到卡比例
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
	 * 根据账单编号
	 * 得到分区对应项目名称(也可能是项目分类名称)
	 * @param session
	 * @param billid    SN201506060094-06
	 * @param vpnname   西山
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
	 * 根据账单编号
	 * 得到分区对应项目名称(也可能是项目分类名称)
	 * @param session
	 * @param billid    SN201506060094-06
	 * @param vpnname   西山
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
		boolean ischeckDate=false;//是否检验单据业务日期 在分区金额中日期有效期内
		
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
	 * 执行售卡信息同步（已作废）
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
//				.append("and ChangeClassId like '%"+infoMap.get("hg_code")+"%' and class.Opersite = '财务部') " )
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
				.append(" and ltrim(aa.Remark) != '作废'")
				.append(" and OldMoney != 0.00 ")
				.append(" and gc.NodeName in('会员卡','套餐方案')")
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
						new String[]{"信用卡","现金","团购门票"});
			caiWuChongZhiVO.setPk_org(infoMap.get("pk_org"));
			lastchongzhiList.add(caiWuChongZhiVO);
		}
		
		getBaseDAO().insertVOList(lastchongzhiList);//将充值信息存储到NC数据表
	}
	/**
	 * 判断是否等于0，null视为零
	 * zhangjc
	 * 2015-7-27上午11:22:53
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
	 * 根据账单号  来取得 金贵的账单
	 * 李彬  2016年8月26日14:59:00
	 */
	public ZhangdanBillVO[] getBillByZDH(String billno,String pk_org) throws BusinessException{
	
//		cleanTempTable();
		
		this.get_VDEF_Info();	// （HK 2019年1月25日20:08:35）
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		Connection hkjt_hg_zd_conn=null;
		JdbcSession hkjt_hg_zd_session =null;
		
		try {
			HashMap<String,String> infoMap=getDefaultInfo(pk_org);//得到配置表信息
			
			hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
			
			hkjt_hg_zd_conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_HG);
			hkjt_hg_zd_session = new JdbcSession(hkjt_hg_zd_conn);
			
			//业务系统账单主表信息
			String sql2="select aa.TurnId,aa.BillId,CONVERT(varchar, aa.OperateDate, 120) OperateDate,ah.Context,aa.OldMoney,aa.FavourMoney,aa.Shishou,aa.MemberId "
					+" ,CONVERT(varchar, cc.ChangeTime, 120) ChangeTime "	// 交班时间
					+" from Sn_Bill aa"		// 账单主表
					+" left join Sn_BillHistory ah on aa.BillId = ah.Billid"	// 小票表
					+" left join Dt_ChangeClass cc on cc.ChangeClassId = aa.TurnId "	// 交班表
					+" where 1=1 " 
					+" and aa.BillId = '" +billno+ "' "	// 账单号
					+" and ltrim(aa.Remark) != '作废'"
					+" and OldMoney != 0.00";
				
			ArrayList<ZhangDanH_TempVO> list2=(ArrayList<ZhangDanH_TempVO>)hkjt_hg_zd_session.executeQuery(sql2, new BeanListProcessor(ZhangDanH_TempVO.class));
			
			if(list2==null || list2.size()<1) return null;
			
			insertVOS(list2);
			
			//业务系统账单子表信息
			String sql3="select " +
					" ab.WaterNum " +	// 流水号
					",aa.BillId " +		// 账单号
					",ab.GoodsName " +	// 商品
					",gc.NodeName GoodsCatalogName " +	// 商品分类
			//		",case when bt.StoreName='技师部' and isnull(js.ImportLevel,'null')<>'null' then js.ImportLevel else bt.StoreName end StoreName" +	// 技师部的卖品 没关联上技师，所以还是放在技师部   （李彬  2015年7月22日15:25:31）
					",case when isnull(js.ImportLevel,'null')<>'null' and js.ImportLevel<>''  then js.ImportLevel else bt.StoreName end StoreName " + 	// 关联上技师的  则取技师的部门， 没关联上的  就取账单的部门
					",CONVERT(varchar, ab.Starttime, 120) Starttime" +
					",ab.Status status_type " +
					",ab.KeyId " +
					/**
					 * 因为生成凭证数据 有小数尾差的问题， 所以需要 取金贵数据的时候，  就截取到 两位小数
					 * 李彬  2016年5月15日15:42:38
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
					+" and aa.BillId = '" +billno+ "' "	// 账单号
					+" and ltrim(aa.Remark) != '作废' "
					+" and OldMoney != 0.00 " +
					 "";
			ArrayList<ZhangDanB_TempVO> list3=(ArrayList<ZhangDanB_TempVO>)hkjt_hg_zd_session.executeQuery(sql3, new BeanListProcessor(ZhangDanB_TempVO.class));
			insertVOS(list3);//将账单子表存储到临时表
			
			/**
			 * 根据交班时间， 反推出 业务日期
			 * 01:00:00 到 00:59:59  为 本天
			 */
			String changeTime = list2.get(0).getChangetime();
//			String date_str = changeTime.substring(0, 10);
//			String hh_str = changeTime.substring(11, 13);
//			if( "23".equals(hh_str) )
//			{// 如果 交班时间 为 23点脚本  认为是 下一天的业务
//				date_str = (new UFDate(date_str).getDateAfter(1)).toString().substring(0,10);
//			}
			
			// 根据班次表 来确定交班时间（早8点 到 第二天的早8点  属于 当天的）
			
			// 根据 班次号  定位 交班时间
			String TurnId = list2.get(0).getTurnid();
			String date_str = TurnId.substring(3, 7) + "-" + TurnId.substring(7, 9) + "-" + TurnId.substring(9, 11);
			String index_str = TurnId.substring(14, 15);	// 班次索引
			String hour_str = changeTime.substring(11, 13);	// 小时  （05，06，07，08，09）
			HashMap<String,String> MAP_1 = new HashMap<String,String>(); // 1段的正常 小时范围
			MAP_1.put("04", "04");
			MAP_1.put("05", "05");
			MAP_1.put("06", "06");
			MAP_1.put("07", "07");
			MAP_1.put("08", "08");
			MAP_1.put("09", "09");
			if( "1".equals( index_str ) 
			&& ! MAP_1.containsKey(hour_str)
			  )
			{// 如果是1  并且不在  正常范围内， 则 认为是前一天
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
			jieXiHuiYuanKa(list4,hkjt_hg_pub_session,infoMap);//解析小票信息
			ZhangdanBillVO[] aggvos=getZhangDanAggVOs(infoMap,list4,infoMap.get("pk_org"),hkjt_hg_pub_session);//根据业务系统账单 表头信息封装NC系统账单聚合VO数据
			IHg_zhangdanMaintain itf=NCLocator.getInstance().lookup(IHg_zhangdanMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
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
	 * 查找分区计收入的 卡型
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
