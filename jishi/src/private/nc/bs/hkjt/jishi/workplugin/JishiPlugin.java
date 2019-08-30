package nc.bs.hkjt.jishi.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.TimeService;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHy_kainfoMaintain;
import nc.itf.hkjt.IJs_shoudanMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.huiyuan.kadangan.KadanganTempVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanBVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanHVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanTempBVO;
import nc.vo.hkjt.jishi.shoudan.ShoudanTempHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class JishiPlugin implements IBackgroundWorkPlugin {

	HashMap<String,String> MAP_dian_corp = new HashMap<String,String>();	// 店 对应 pk_corp
	HashMap<String,String> MAP_corp_dian = new HashMap<String,String>();	// pk_corp  对应  店
	HashMap<String,String> MAP_dian_flag = new HashMap<String,String>();	// 店 对应 flag
	HashMap<String,String> MAP_corp_flag = new HashMap<String,String>();	// pk_corp  对应  flag
	HashMap<String,String> MAP_dian_db   = new HashMap<String,String>();	// 店  对应  数据库
	
	public JishiPlugin()
	{
		MAP_dian_corp.put("国际", "0001N510000000001SXV");	// 国际会馆
		MAP_dian_corp.put("酒店", "0001N510000000001SY1");	// 康福瑞酒店
		MAP_dian_corp.put("朗丽兹", "0001N510000000001SY3");	// 朗丽兹酒店 
		MAP_dian_corp.put("牡丹", "0001N510000000001SXX");	// 贵宾楼
		MAP_dian_corp.put("西山", "0001N510000000001SY7");	// 西山温泉
		MAP_dian_corp.put("康福瑞", "0001N510000000001SY5");	// 康福瑞西山
		
		MAP_corp_dian.put("0001N510000000001SXV", "国际");
		MAP_corp_dian.put("0001N510000000001SXX", "牡丹");
		MAP_corp_dian.put("0001N510000000001SY7", "西山");
		MAP_corp_dian.put("0001N510000000001SY1", "酒店");
		MAP_corp_dian.put("0001N510000000001SY3", "朗丽兹");
		MAP_corp_dian.put("0001N510000000001SY5", "康福瑞");
		
		MAP_dian_flag.put("牡丹", "-01");
		MAP_dian_flag.put("国际", "-02");
		MAP_dian_flag.put("西山", "-06");
		MAP_dian_flag.put("酒店", "-04");
		MAP_dian_flag.put("朗丽兹", "-07");
		MAP_dian_flag.put("康福瑞", "-08");
		
		MAP_corp_flag.put("0001N510000000001SXX", "-01");
		MAP_corp_flag.put("0001N510000000001SXV", "-02");
		MAP_corp_flag.put("0001N510000000001SY7", "-06");
		MAP_corp_flag.put("0001N510000000001SY1", "-04");
		MAP_corp_flag.put("0001N510000000001SY3", "-07");
		MAP_corp_flag.put("0001N510000000001SY5", "-08");
		
		MAP_dian_db.put("牡丹",  "L01.jgmd.dbo.");
		MAP_dian_db.put("国际",  "L02.jggj.dbo.");
		MAP_dian_db.put("西山",  "L06.jgxs.dbo.");
		MAP_dian_db.put("酒店",  "L04.lmt.dbo.");
		MAP_dian_db.put("朗丽兹", "L07.jgllz.dbo.");
		MAP_dian_db.put("康福瑞", "L08.jgkfr.dbo.");
	}
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{context.getAlertTypeName()+"_HKJT_jishi"});
		if(!lock){
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		
		try
		{
			
			String[] pk_orgs = context.getPk_orgs();
			UFDate bdate = PuPubVO.getUFDate( context.getKeyMap().get("beginDate") );	// 开始日期
			UFDate edate = PuPubVO.getUFDate( context.getKeyMap().get("endDate") );		// 结束日期
			
			// 如果日期为空， 则默认为 当前日期的 前一天
			if(bdate==null) bdate = new UFDate().getDateBefore(1);
			if(edate==null) edate = new UFDate().getDateBefore(1);
			
			if(pk_orgs==null || pk_orgs.length<=0)
			{
				pk_orgs = new String[]{
						 "0001N510000000001SXV"	//国际
						,"0001N510000000001SXX"	//牡丹
						,"0001N510000000001SY7"	//西山
						,"0001N510000000001SY1" //酒店
						,"0001N510000000001SY3" //朗丽兹
						,"0001N510000000001SY5" //康福瑞
				};
			}
			
			this.importShoudan_info(pk_orgs, bdate, edate);
			
			
		}catch(Exception ex)
		{ throw new BusinessException(ex);}
		
		return null;
	}
	
	/**
	 * 取 手单数据
	 */
	private void importShoudan_info(String[] pk_orgs,UFDate bdate,UFDate edate) throws Exception
	{
		
		Connection hkjt_hg_pub_conn=null;
		JdbcSession hkjt_hg_pub_session =null;
		hkjt_hg_pub_conn=new JDBCUtils("hkjt_hg_pub").getConn(JDBCUtils.HKJT_HG);
		hkjt_hg_pub_session = new JdbcSession(hkjt_hg_pub_conn);
		
		IJs_shoudanMaintain itf = NCLocator.getInstance().lookup(IJs_shoudanMaintain.class);
		String pk_group = AppContext.getInstance().getPkGroup();
		
		ArrayList<String> list_date = this.getTimeDates(bdate,edate);
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
				
				// 根据业务日期  来找 具体时间 （开始、结束）
				String[] yw_time = this.getYwDate(PuPubVO.getUFDate(yw_date), flag);
				
				if( yw_time==null || yw_time.length<2 ) continue;	// 找不到 业务时间 就退出
				
				// 1、 按照时间  将 表头信息 汇总出来 （技师code）   放到临时表中
				//   暂时 以 endTime， 作为时间条件
				/**
				 * select cr.jishicode,MAX(js.JishiName)
					from Sn_ClockRoom cr
					left join Sn_JiShi js on js.jishicode = cr.jishicode
					where CONVERT(varchar, cr.endtime, 120) between '2016-05-10 00:00:00' and '2016-05-10 23:59:59'
					and cr.type in ('点钟','排钟','加钟','退单','退钟')
					group by cr.jishicode 
				 */
				StringBuffer querySQL_1 = 
					new StringBuffer("select ")
							.append(" cr.jishicode ")
							.append(",MAX(js.JishiName) JishiName ")
							.append(",'"+corp+"' pk_org ")
							.append(",'"+yw_date+"' dbilldate ")
							.append(" from "+db_str+"Sn_ClockRoom cr ")
							.append(" left join "+db_str+"Sn_JiShi js on js.jishicode = cr.jishicode ")
							.append(" where (1=1) ")
							.append(" and cr.type in ('点钟','排钟','加钟','退单','退钟') ")
							.append(" and CONVERT(varchar, cr.endtime, 120) between '"+yw_time[0]+"' and '"+yw_time[1]+"' ")
//							.append(" and cr.jishicode = '1108' ")	// 测试
							.append(" group by cr.jishicode ")
				;
				
				ArrayList<ShoudanTempHVO> list_hvo_temp_query = (ArrayList<ShoudanTempHVO>)hkjt_hg_pub_session.executeQuery(querySQL_1.toString(), new BeanListProcessor(ShoudanTempHVO.class));
				insertVOS(list_hvo_temp_query);//将 手单表头 插入到NC临时表
				
				// 2、 按照时间  将 表体 信息取出来    放到临时表中
				//   暂时 以 endTime， 作为时间条件
				StringBuffer querySQL_2 = 
					new StringBuffer("select ")
							.append(" cr.jishicode ")	// 技师code
							.append(",cr.handnumber ")
							.append(",cr.handerid ")
							.append(",cr.roomid ")
							.append(",cr.goodsid ")
							.append(",cr.goodsname ")
							.append(",CONVERT(varchar, cr.starttime, 120) starttime ")
							.append(",CONVERT(varchar, cr.endtime, 120) endtime ")
							.append(",cr.remark ")
							.append(",case when cr.type in ('点钟','排钟','加钟') then cr.Number end num_add ")
							.append(",case when cr.type in ('退单','退钟') then cr.Number end num_sub ")
							.append(",cr.type typename ")
							.append(",cr.operatorname ")
							.append(",cr.checkname ")
							.append(",CONVERT(varchar, cr.checktime, 120) checktime ")
							.append(",cr.machinename ")
							.append(",cr.waternum ")
							.append(",cr.mainid ")
							.append(",cl.BillId ")
							.append(",cl.Price ")
							.append(",cl.discountprice ")
							.append(",cl.realmoney ")
							.append(" from "+db_str+"Sn_ClockRoom cr ")
							.append(" left join "+db_str+"Sn_Consumesellog cl on cr.WaterNum = cl.WaterNum ")
							.append(" where (1=1) ")
							.append(" and cr.type in ('点钟','排钟','加钟','退单','退钟') ")
							.append(" and CONVERT(varchar, cr.endtime, 120) between '"+yw_time[0]+"' and '"+yw_time[1]+"' ")
				;
				ArrayList<ShoudanTempBVO> list_bvo_temp_query = (ArrayList<ShoudanTempBVO>)hkjt_hg_pub_session.executeQuery(querySQL_2.toString(), new BeanListProcessor(ShoudanTempBVO.class));
				insertVOS(list_bvo_temp_query);//将 手单表体 插入到NC临时表
				
				/**
				 * 3、过滤出 未导入的  手单表头数据
				 */
				StringBuffer querySQL_3 = 
					new StringBuffer("select ")
							.append(" sd_t.jishicode ")
							.append(",sd_t.jishiname ")
							.append(",sd_t.dbilldate ")
							.append(",sd_t.pk_org ")				// 组织
//							.append(",'"+pk_group+"' pk_group ")	// 集团
//							.append(",'HK52' vbilltypecode ")		// 单据类型
//							.append(",'"+HKJT_PUB.MAKER+"' creator ")			// 创建人
//							.append(",'"+new UFDateTime()+"' creationtime ")	// 创建日期
//							.append(",-1 ibillstatus ")		// 单据状态
							.append(" from HK_JISHI_SHOUDAN_TEMP sd_t ")
							.append(" left join HK_JISHI_SHOUDAN sd on ( sd.dr=0 and sd_t.pk_org=sd.pk_org and sd_t.jishicode=sd.jishicode and sd_t.dbilldate=sd.dbilldate ) ")	// 公司、技师、日期
							.append(" where (1=1) ")
							.append(" and sd.PK_HK_JISHI_SHOUDAN is null ")
				;
//				ArrayList<ShoudanHVO> list_hvo_query = (ArrayList<ShoudanHVO>)this.getBaseDAO().executeQuery(querySQL_3.toString(), new BeanListProcessor(ShoudanHVO.class));
				ArrayList<ShoudanTempHVO> list_hvo_query = (ArrayList<ShoudanTempHVO>)this.getBaseDAO().executeQuery(querySQL_3.toString(), new BeanListProcessor(ShoudanTempHVO.class));
				
				System.out.println("=="+list_hvo_query);
				
				/**
				 * 4、根据表头 循环 进行处理
				 */
				for( ShoudanTempHVO tempHVO : list_hvo_query )
				{
					String jishiCode = tempHVO.getJishicode();
					
					StringBuffer querySQL_4 = 
						new StringBuffer("select ")
								.append(" sdb_t.handnumber ")
								.append(",sdb_t.handerid ")
								.append(",sdb_t.roomid ")
								.append(",sdb_t.goodsid ")
								.append(",sdb_t.goodsname ")
								.append(",sdb_t.starttime ")
								.append(",sdb_t.endtime ")
								.append(",sdb_t.remark ")
								.append(",sdb_t.num_add ")
								.append(",sdb_t.num_sub ")
								.append(",sdb_t.typename ")
								.append(",sdb_t.operatorname ")
								.append(",sdb_t.checkname ")
								.append(",sdb_t.checktime ")
								.append(",sdb_t.machinename ")
								.append(",sdb_t.waternum ")
								.append(",sdb_t.mainid ")
								.append(",sdb_t.BillId ")
								.append(",sdb_t.Price ")
								.append(",sdb_t.discountprice ")
								.append(",sdb_t.realmoney ")
								.append(" from HK_JISHI_SHOUDAN_B_TEMP sdb_t ")
								.append(" where (1=1) ")
								.append(" and sdb_t.jishicode = '"+jishiCode+"' ")
					;
					
					ArrayList<ShoudanTempBVO> list_bvo_query = (ArrayList<ShoudanTempBVO>)this.getBaseDAO().executeQuery(querySQL_4.toString(), new BeanListProcessor(ShoudanTempBVO.class));

					ShoudanBillVO billVO = new ShoudanBillVO();
					ShoudanHVO HVO = new ShoudanHVO();
					HVO.setAttributeValue("pk_org" , tempHVO.getPk_org() );
					HVO.setAttributeValue("pk_group" , pk_group );
					HVO.setAttributeValue("jishicode" , tempHVO.getJishicode() );
					HVO.setAttributeValue("jishiname" , tempHVO.getJishiname() );
					HVO.setAttributeValue("dbilldate" , PuPubVO.getUFDate(tempHVO.getDbilldate()) );
					HVO.setAttributeValue("creator" , HKJT_PUB.MAKER );
					HVO.setAttributeValue("creationtime" , new UFDateTime() );
					HVO.setAttributeValue("vbilltypecode" ,"HK52");
					HVO.setAttributeValue("ibillstatus" ,-1);
					billVO.setParentVO(HVO);
					
					ShoudanBVO[] BVOs = new ShoudanBVO[list_bvo_query.size()];
					for( int bvo_i=0;bvo_i<list_bvo_query.size();bvo_i++ )
					{
						BVOs[bvo_i] = new ShoudanBVO();
						BVOs[bvo_i].setAttributeValue("handerid" , list_bvo_query.get(bvo_i).getHanderid() );
						BVOs[bvo_i].setAttributeValue("handnumber" , list_bvo_query.get(bvo_i).getHandnumber() );
						BVOs[bvo_i].setAttributeValue("roomid" , list_bvo_query.get(bvo_i).getRoomid() );
						BVOs[bvo_i].setAttributeValue("goodsid" , list_bvo_query.get(bvo_i).getGoodsid() );
						BVOs[bvo_i].setAttributeValue("goodsname" , list_bvo_query.get(bvo_i).getGoodsname() );
						BVOs[bvo_i].setAttributeValue("starttime" , list_bvo_query.get(bvo_i).getStarttime() );
						BVOs[bvo_i].setAttributeValue("endtime" , list_bvo_query.get(bvo_i).getEndtime() );
						BVOs[bvo_i].setAttributeValue("remark" , list_bvo_query.get(bvo_i).getRemark() );
						BVOs[bvo_i].setAttributeValue("num_add" , list_bvo_query.get(bvo_i).getNum_add() );
						BVOs[bvo_i].setAttributeValue("num_sub" , list_bvo_query.get(bvo_i).getNum_sub() );
						BVOs[bvo_i].setAttributeValue("typename" , list_bvo_query.get(bvo_i).getTypename() );
						BVOs[bvo_i].setAttributeValue("operatorname" , list_bvo_query.get(bvo_i).getOperatorname() );
						BVOs[bvo_i].setAttributeValue("checkname" , list_bvo_query.get(bvo_i).getCheckname() );
						BVOs[bvo_i].setAttributeValue("checktime" , list_bvo_query.get(bvo_i).getChecktime() );
						BVOs[bvo_i].setAttributeValue("machinename" , list_bvo_query.get(bvo_i).getMachinename() );
						BVOs[bvo_i].setAttributeValue("waternum" , list_bvo_query.get(bvo_i).getWaternum() );
						BVOs[bvo_i].setAttributeValue("mainid" , list_bvo_query.get(bvo_i).getMainid() );
						BVOs[bvo_i].setAttributeValue("billid" , list_bvo_query.get(bvo_i).getBillid() );
						BVOs[bvo_i].setAttributeValue("price" , list_bvo_query.get(bvo_i).getPrice() );
						BVOs[bvo_i].setAttributeValue("discountprice" , list_bvo_query.get(bvo_i).getDiscountprice() );
						BVOs[bvo_i].setAttributeValue("realmoney" , list_bvo_query.get(bvo_i).getRealmoney() );
					}
					
					billVO.setChildrenVO( BVOs );
					
					itf.insert(new ShoudanBillVO[]{billVO}, null);
					
				}
				
				// 5、处理完一个循环，  清除 临时表 数据
				getBaseDAO().deleteByClause(ShoudanTempBVO.class, " 1=1 ");
				getBaseDAO().deleteByClause(ShoudanTempHVO.class, " 1=1 ");
				
				
//				/**
//				 * test
//				 */
//				StringBuffer query_test = new StringBuffer("select * from HK_JISHI_SHOUDAN_TEMP");
//				ArrayList<ShoudanTempHVO> list_hvo_test = (ArrayList<ShoudanTempHVO>)this.getBaseDAO().executeQuery(query_test.toString(), new BeanListProcessor(ShoudanTempHVO.class));
//				System.out.println("==="+list_hvo_test);
//				/**END*/
			}
		
		}
		
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

	/**
	 * 获得 业务时间段
	 * 目前按  整点 来计算   00:00:00 到  23:59:59
	 */
	private String[] getYwDate(UFDate date,String flag) throws Exception
	{
		String[] result = new String[2];
		
		result[0] = date.toString().substring(0,10) + " 00:00:00";	// 开始时间
		result[1] = date.toString().substring(0,10) + " 23:59:59";	// 结束时间
		
		return result;
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
