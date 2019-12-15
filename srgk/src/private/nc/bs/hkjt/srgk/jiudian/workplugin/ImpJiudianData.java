package nc.bs.hkjt.srgk.jiudian.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

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
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.itf.org.IOrgVersionQryService;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.jdbc.framework.processor.VectorProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * 宏昆酒店数据 后台任务
 * <p>
 * @author zhangjc
 * @date 2015-08-04
 *
 */
public class ImpJiudianData implements IBackgroundWorkPlugin {
	public final static int  DECIMALBIT =2;//小数保留位数
	
	/**
	 * 用于代码测试
	 * 后台任务无法执行
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		
		String[] pk_orgs = new String[]{
				"0001N510000000001SXV"	// 国际
//				"0001N510000000001SY5"	// 康西
			};
		
		String[] dateP = new String[]{
				"2019-01-13",
				"2019-01-13"
			};

		long startTime=System.currentTimeMillis();
		
		if(pk_orgs==null||pk_orgs.length==0)return null;
		
		HashMap<String,String> infoMap=getDefaultInfo(pk_orgs[0]);//得到配置表信息
		
		if(infoMap!=null){
			
			boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{"HK-千里马数据导入"+pk_orgs[0]});
			
			if(!lock){
				throw new BusinessException("事务正在处理中,不能重复操作！");
			}
			
//			infoMap.put("pk_org", "0001N510000000001SXV");	// 国际 （HK 2018年11月5日19:14:48）
//			infoMap.put("org_code", "0302");				// 国际 （HK 2018年11月5日19:21:24）
//			infoMap.put("org_name", "国际会馆");				// 国际 （HK 2018年11月5日19:24:13）
			infoMap.put("db_name", "hkjt_jd_kfrxsd");		// 康西 （HK 2018年11月7日14:42:09）
			
			String[] timeDates=getTimeDates(dateP[0],dateP[1]);
			
			for (String date : timeDates) {
				
				String timeWhere=getTimeWhere(date,date,getTimeWhereField());//得到后台任务中定义的日期，并组成where条件
				executeDateTongBu(infoMap,timeWhere);
			}
		}
		
		System.out.println("处理完成,共耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
	
		return null;
	
	}
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		long startTime=System.currentTimeMillis();
		String []pk_orgs=bgwc.getPk_orgs();//组织为必输，所以肯定有值
		if(pk_orgs==null||pk_orgs.length==0)return null;
			HashMap<String,String> infoMap=getDefaultInfo(pk_orgs[0]);//得到配置表信息
			if(infoMap!=null){
				boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{bgwc.getAlertTypeName()+bgwc.getPk_orgs()[0]});
				if(!lock){
					throw new BusinessException("事务正在处理中,不能重复操作！");
				}
				
				if("国际会馆".equals(infoMap.get("org_name")))	// 如果是 国际会馆，则要改成 康西的数据库
				{
					infoMap.put("db_name", "hkjt_jd_kfrxsd");		// 康西 （HK 2018年11月7日14:42:09）
				}
				
				String[] timeDates=getTimeDates(bgwc.getKeyMap().get("begindate"),bgwc.getKeyMap().get("enddate"));
//				String timeWhere=getTimeWhere(bgwc.getKeyMap().get("begindate"),bgwc.getKeyMap().get("enddate"),getTimeWhereField());//得到后台任务中定义的日期，并组成where条件
				for (String date : timeDates) {
					String timeWhere=getTimeWhere(date,date,getTimeWhereField());//得到后台任务中定义的日期，并组成where条件
					executeDateTongBu(infoMap,timeWhere);
				}
				
			}
	System.out.println("处理完成,共耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
	
		return null;
	}

	/**
	 * zhangjc
	 * 2015-8-6上午10:58:58
	 * void
	 * 入账明细同步
	 *
	 */
	public void executeDateTongBu(HashMap<String,String> infoMap,String timeWhere) throws BusinessException{
		Connection hkjt_jd_conn=null;
		JdbcSession hkjt_jd_conn_session =null;
		hkjt_jd_conn=new JDBCUtils(infoMap.get("db_name")).getConn(JDBCUtils.HKJT_HG);
		hkjt_jd_conn_session = new JdbcSession(hkjt_jd_conn);
		try{
		ArrayList<RzmxBVO> list=(ArrayList<RzmxBVO>)hkjt_jd_conn_session.executeQuery(getQuerySql(timeWhere), new BeanListProcessor(RzmxBVO.class));
		RzmxBillVO[] aggvos=getRzmxAggVO(list,infoMap,hkjt_jd_conn_session);//转换为NC入账明细聚合VO
		
		saveRzmxAggVOs(aggvos);//保存
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}finally{
			hkjt_jd_conn_session.closeAll();
			JDBCUtils.closeConn(hkjt_jd_conn);
		}
		
	}
	public void saveRzmxAggVOs(RzmxBillVO[] aggvos) throws BusinessException {
		if(aggvos!=null&&aggvos.length>0){
			IJd_rzmxMaintain itf=NCLocator.getInstance().lookup(IJd_rzmxMaintain.class);
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
			
//			/**
//			 * HK 2018年11月5日20:04:50
//			 */
//			for(RzmxBillVO billVO : aggvos)
//			{
//				billVO.getParentVO().setPk_org("0001N510000000001SXV");		// 国际
//				billVO.getParentVO().setPk_org_v("0001N510000000001SXU");	// 国际
//			}
//			/***END***/
			
			itf.insert(aggvos, null);
		}
	}
	/**
	 * zhangjc
	 * 2015-8-5上午10:23:07
	 * RzmxBillVO[]
	 *获得入账明细聚合VO数组
	 */
	public RzmxBillVO[] getRzmxAggVO(ArrayList<RzmxBVO> list,HashMap<String,String> infoMap,JdbcSession session) throws BusinessException{
		IOrgVersionQryService orgVersion=NCLocator.getInstance().lookup(IOrgVersionQryService.class);
		//国际千里马（改成固定的 国际店的pk）
		String pk_org = infoMap.get("pk_org");
//		String pk_org = "0001N510000000001SXV";		// 国际 （HK 2018年11月5日19:56:26）
		String pk_org_v=orgVersion.getOrgUnitLastVersionByOrgID(pk_org).getPk_vid();
		String pk_group=AppContext.getInstance().getPkGroup();
		
		HashMap<String,ArrayList<RzmxBVO>> mapvos=new HashMap<String, ArrayList<RzmxBVO>>();
		for (RzmxBVO bvo : list) {//依据日期分组
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
		HashSet<String> exists=getNCBillsByPk_org(pk_org);//在NC中已经存在的入账明细单据（依据日期判定）
		HashMap<String,UFDouble[]> otherdate=getOtherData(session, mapvos.keySet());//出租率，平均房价，客房收入，可出租房间数, REVPAR 
		for (String key : mapvos.keySet()) {//封装入账明细聚合VO
			if(exists.contains(key)){continue;}
			RzmxBillVO aggvo=new RzmxBillVO();
			RzmxHVO hvo = getHeadVOs(infoMap ,pk_org ,pk_org_v, pk_group,key);
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
	 * zhangjc
	 * 2015-8-5上午10:23:25
	 * RzmxHVO
	 *处理表头VO数据
	 */
	public RzmxHVO getHeadVOs(HashMap<String, String> infoMap,String pk_org,
			String pk_org_v, String pk_group,String dbilldate) {
		RzmxHVO hvo=new RzmxHVO();
		hvo.setPk_org(pk_org);
		hvo.setPk_org_v(pk_org_v);
		hvo.setPk_group(pk_group);
		hvo.setIbillstatus(-1);
		hvo.setVbilltypecode("HK11");//单据类型
		hvo.setDbilldate(dbilldate==null?AppContext.getInstance().getBusiDate():new UFDate(dbilldate));
		return hvo;
	}
/**
 * zhangjc
 * 2015-8-5上午10:15:29
 * RzmxBVO[]
 *对表体值做处理
 * @throws DAOException 
 */
public RzmxBVO[] getBodyVos(ArrayList<RzmxBVO> bodyvos,RzmxHVO hvo) throws DAOException{
	HashMap<String,String[]> bmxx=getAllBmxxByPk_org(hvo.getPk_org());//得到部门信息 key=外 部门名称 value={部门主键，父级部门主键}
	HashMap<String,SpflHVO> spfl=getAllSpflByPk_org(hvo.getPk_org()) ;//得到商品分类信息 
	HashMap<String,JzfsHVO> jzfs=getAllJzfsByPk_org(HKJT_PUB.PK_ORG_JIUDIAN) ;//得到结账方式信息
	int vrowno=10;
	UFDouble xfje=UFDouble.ZERO_DBL;
	UFDouble jzje=UFDouble.ZERO_DBL;
	for (RzmxBVO rzmxBVO : bodyvos) {
		rzmxBVO.setVrowno(String.valueOf(vrowno));
		vrowno+=10;
		UFDouble xf=nullAsZero(rzmxBVO.getCharge());
		String itemName = rzmxBVO.getItem_name();	// 入账项目编码
		String itemCode = rzmxBVO.getItem_code();	// 入账项目名称
		if(!isZero(xf)){//如果charge 不为0需要翻译商品分类。依据item_name 来对应（房租or全日租,半日租-依据房型对应、其他的依据 item_name 对应）
			String itemname=null;
			if( itemName!=null &&
				(
				   strEqual(new String[]{"全日租","半日租","加床费"},itemName)||itemName.trim().indexOf("房租")!=-1||itemName.trim().indexOf("房费")!=-1
				)
			){
				itemname=rzmxBVO.getRmtype_name();//房型
			}else{
				itemname=itemName==null?itemName:itemName.toLowerCase();
			}
			SpflHVO spflvo=spfl.get(itemname);
			if(spflvo!=null){
				rzmxBVO.setSpfl_name(spflvo.getName());
				rzmxBVO.setSpfl_id(spflvo.getPk_hk_srgk_hg_spfl());
				rzmxBVO.setSrxm_id(spflvo.getPk_hk_srgk_hg_srxm());//收入项目 取 NC商品分类 所属收入项目
				/**
				 * 李彬
				 * 2016年3月20日14:49:59
				 */
				rzmxBVO.setBm_id(  spflvo.getPk_dept() );	// 赋值 商品分类所配置的部门
				rzmxBVO.setBm_fid( spflvo.getPk_dept() );	// 赋值 商品分类所配置的部门
				/**END*/
				
			}
		}
		UFDouble jz=nullAsZero(rzmxBVO.getPayment());
		if(!isZero(jz)){//payment 结账金额 不为0的 需要翻译结账方式。依据item_name（人民币、退押金- 现金、银联卡-pos、转应收-消费客户往来款）
			String jzfsname=null;
			if(strEqual(new String[]{"现金退款","人民币","退押金","收押金","预付定金"
									,"零币差额","现金","现金支出","人民币现金","预订押金"
									,"POS现金"
					},itemName)){
				jzfsname="现金";
				
			}else if(strEqual(new String[]{"银联卡","信用卡","信用卡银行回款","信用卡手续费"
										  ,"POS-银联卡"
					},itemName)){
				jzfsname="POS";
				
			}else if(strEqual(new String[]{"转应收","城市挂账","挂前台账"
										  ,"招待","信用住（淘宝）"
										  ,"信用住调整","坏账"
										  ,"应收调整","POS冲预付"
//										  ,"转后台账","转财务后台账"	// 李彬 2016年9月1日16:10:10  （姚美玲 提出 取消这两个的特殊处理）
					},itemName)){
				jzfsname="消费客户往来款";
				
			}else if(strEqual(new String[]{"转账支票","转帐支票"},itemName)){
				jzfsname="支票";
				
			}else if(strEqual(new String[]{"会员卡","一卡通","一卡通结账","康福瑞银卡","康福瑞金卡","康福瑞钻石卡"
										  ,"银卡","金卡","钻石卡","储值卡记账"
					},itemName)){
				jzfsname="会员卡卡结";
				
			}else if(itemName!=null&&itemName.contains("余额转入")){
				jzfsname="余额转入";
				
			}else if(itemName!=null&&itemName.contains("余额转出")){
				jzfsname="余额转出";
				
			}else if(strEqual(new String[]{"代金券","免费房券","积分冲减消费","COUPON"
										  ,"免费券","积分付款"
										  ,"POS代金券"
					},itemName)){
				jzfsname="赠券";
			}
			
			else if( PuPubVO.getString_TrimZeroLenAsNull(itemName)==null
				  && "9".equals(itemCode)
			) // 入账编码为 9 的，  西软没有对应的档案，  所以 在NC默认为  内部转账。
			{
				jzfsname = "内部转账";
			}
			/**
			 * 转前台  散客押金-手工调整（HK-2019年2月2日15:26:04）
			 */
			else if(strEqual(new String[]{"转前台"},itemName)){
				jzfsname="散客押金-手工调整";
			}
			/***END***/
			
			else{
				jzfsname=itemName;//  内部款待
			}
			rzmxBVO.setJzfs_name(jzfsname);//结账方式name
			String pk_jzfs=jzfs.get(jzfsname)==null?null:jzfs.get(jzfsname).getPk_hk_srgk_hg_jzfs();
			rzmxBVO.setJzfs_id(pk_jzfs);//结账方式id
			
			/**
			 * 如果：入账名称 = 转财务后台账    则将客户赋值为  财务室
			 * 李彬  2015年12月28日20:18:33
			 */
			if(strEqual(new String[]{"转财务后台账"},itemName))
			{
				rzmxBVO.setKhmz("财务室");
			}
			/**END*/
		}
		/**
		 * 李彬
		 * 2016年3月20日14:52:48
		 */
		if( PuPubVO.getString_TrimZeroLenAsNull(rzmxBVO.getBm_id())==null )
		{// 如果之前没有 赋值上  商品分类的 默认部门， 则需要取  业务系统的默认部门。  
			String[] bmid = bmxx.get(rzmxBVO.getBm_name())==null?new String[2]:bmxx.get(rzmxBVO.getBm_name());//
			rzmxBVO.setBm_id(bmid[0]);
			rzmxBVO.setBm_fid(bmid[1]);
		}
		/**END*/
		jzje=jzje.add(nullAsZero(rzmxBVO.getPayment()));//结账金额
		xfje=xfje.add(nullAsZero(rzmxBVO.getCharge()));//消费金额
	}
	
	hvo.setXfje(xfje);
	hvo.setJzje(jzje);
	return bodyvos.toArray(new RzmxBVO[]{});
}
/**
 * zhangjc
 * 2015-8-25上午10:22:59
 * boolean
 * 判断itemName是否包含于 strArray中，如果被包含则返回true
 *
 */
public boolean strEqual(String [] strArray,String itemName){
	for (String string : strArray) {
		if(string.equals(itemName))
			return true;
	}
	return false;
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
		return map;
	}
	
	BaseDAO dao=null;
	public BaseDAO getBaseDAO(){
		if(dao==null)
			dao=new BaseDAO();
		return dao;
	}
	
	public String getTimeWhereField(){
		return "convert(varchar(10),trans.accdate,120)";
	}
	/**
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	protected String getTimeWhere(Object beginTime,Object endTime,String timeField){
		String defaultBeginTime=getCurrentTime().getDate().getDateBefore(1).toString().substring(0,10);
		String defaultEndTime=getCurrentTime().getDate().toString().substring(0,10);
		
		String where="";
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//开始结束都不为空
			where="("+timeField+">='"+beginTime+"' and "+timeField+"<='"+endTime+"')" ;
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//开始不为空，结束为空
			where="("+timeField+">='"+beginTime+"' and "+timeField+"<'"+defaultEndTime+"')" ;
		}else{
//		if(((beginTime==null||beginTime.trim().length()==0)&&(endTime==null||endTime.trim().length()==0))//开始结束都为空
//		  ||((beginTime==null||beginTime.trim().length()==0)&&(endTime!=null&&endTime.trim().length()>0))){//开始为空，结束不为空
		   where="("+timeField+"='"+defaultBeginTime+"')" ;
//			}
		}
		return where;
	}
	
	protected String[] getTimeDates(Object beginTime,Object endTime){
		UFDate beginDate=null;
		UFDate endDate=null;
		if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime!=null&&endTime.toString().trim().length()>0)){//开始结束都不为空
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(endTime.toString());
		}else if((beginTime!=null&&beginTime.toString().trim().length()>0)&&(endTime==null||endTime.toString().trim().length()==0)){//开始不为空，结束为空
			beginDate=new UFDate(beginTime.toString());
			endDate=new UFDate(getCurrentTime().getDate().getDateBefore(1).toString().substring(0,10));
		}else{
			return new String[]{getCurrentTime().getDate().getDateBefore(1).toString().substring(0,10)};
		}
		
		
		ArrayList<String> datesList=new ArrayList<String>();
		for (int i = 0; i <=UFDate.getDaysBetween(beginDate, endDate); i++) {
			String dateStr=beginDate.getDateAfter(i).toString().substring(0,10);
			if(!datesList.contains(dateStr))
			datesList.add(dateStr);
		}
		
		return datesList.toArray(new String[]{});
	}
	
	public String getQuerySql(String where){
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("SELECT")
				 .append(" convert(varchar(10),trans.accdate,120) PK_HK_SRGK_JD_RZMX,")	//单据日期
				 .append(" rtrim(trans.cashier) syy_code,")	//收银员code
				 .append(" rtrim(trans.category) jylb_code ,")//交易类别code
				 .append(" rtrim(jylb.cname) jylb_name ,") //交易类别name
				 .append(" rtrim(outlet.cname) bm_name,")  //部门name
				 .append(" rtrim(trans.item) item_code,")	//项目code
				 .append(" rtrim(item.cname) item_name,")	//项目name (去掉 右边空格)
				 .append(" convert(varchar(19),trans.transdate,120) transdate,")//结账时点
				 .append(" rtrim(trans.refno) refno,")	 //refno
				 .append(" rtrim(trans.descript) descript,") //descript
				 .append(" rtrim(trans.accid) accid,")	 //accid
				 .append(" trans.charge ,")	//消费金额
				 .append(" trans.payment,") //结账金额
				 .append(" rtrim(gres.gstname) khmz,") //客户名字
//				 .append(" case when item.cname='转应收' then trans.descript else gres.gstname END khmz,")	//客户名字（如果是转应收 要取往来客户的名字）
				 .append(" rtrim(gres.rmno) rmno,")		//房间号
				 .append(" rtrim(trans.transid) transid,")  //transid  
				 .append(" rtrim(rmtype.cname) rmtype_name")	//房间类型
				 .append(" FROM  trans")
			.append(" left join gres  on trans.accid = gres.accid")		
			.append(" left join room on gres.rmno = room.code	")		//房间
			.append(" left join rmtype on room.rmtype = rmtype.code")	//房间类型
			.append(" left join outlet on trans.outlet = outlet.code")  //部门（核算点）
			.append(" left join item on trans.item = item.code")		//项目
			.append(" left join codes jylb on trans.category = jylb.code and jylb.category='transcat'")  // 交易类别
		    .append(" WHERE  (1=1)")
			.append(" and ( trans.voidflag=-1 or trans.voidpath=0 )") 
			.append(" and item.cname<>'转应收' ")	// 去掉转应收的数据
//			.append(" and convert(varchar(10),trans.accdate,120)='2015-07-25' ") 
			.append(" and "+where) 
			
			// 取 转应收的数据
			.append(" union all ")
				.append(" SELECT ")
				.append(" convert(varchar(10),a.t_date,120) PK_HK_SRGK_JD_RZMX ")	//单据日期
				.append(",null ")	//收银员code
				.append(",null ")	//交易类别code
				.append(",null ")	//交易类别name
				.append(",'前台' ")	//部门name
				.append(",null ")	//项目code
				.append(",'转应收' ")	//项目name
				.append(",null ")	//结账时点
				.append(",null ")	//refno
				.append(",rtrim(a.t_par) ")//descript
				.append(",null ")	//accid
				.append(",null ")	//消费金额
				.append(",case a.t_class when 0 then a.t_amt else -1*t_amt end payment ")	//结账金额
				.append(",rtrim(b.c_name) khmz ")	//客户名字
				.append(",null ")	//房间号
				.append(",null ")	//transid
				.append(",null ")	//房间类型
				.append(" FROM ascbos7.dbo.artran a left join ascbos7.dbo.cust b on a.t_cust=b.c_code ")
				.append(" where (1=1) ")
				.append(" and "+ (where.replaceAll("trans.accdate","a.t_date")) ) 
			
			// 取银行转账数据（HK-问题7 2019年1月9日18:34:31）
			.append(" union all ")
				.append(" SELECT ")
				.append(" convert(varchar(10),a.t_date,120) PK_HK_SRGK_JD_RZMX ")	//单据日期
				.append(",null ")	//收银员code
				.append(",null ")	//交易类别code
				.append(",null ")	//交易类别name
				.append(",'前台' ")	//部门name
				.append(",t_item ")	//项目code
				.append(",c.name ")	//项目name
				.append(",null ")	//结账时点
				.append(",null ")	//refno
				.append(",rtrim(a.t_par) ")//descript
				.append(",null ")	//accid
				.append(",null ")	//消费金额
				.append(",a.t_amt payment ")	//结账金额
				.append(",rtrim(b.c_name) khmz ")	//客户名字
				.append(",null ")	//房间号
				.append(",null ")	//transid
				.append(",null ")	//房间类型
				.append(" FROM ascbos7.dbo.artran a ")
				.append(" left join ascbos7.dbo.cust b on a.t_cust=b.c_code ")
				.append(" left join ascbos7.dbo.aritem c  on a.t_item=c.code ")
				.append("  where (1=1) and a.t_class=1 ")
				.append(" and a.t_item in ('0101','0103','0115','0122','0123','0124','0125','0126') ")
				.append(" and "+ (where.replaceAll("trans.accdate","a.t_date")) ) 
			
			.append(" ORDER BY rtrim(trans.cashier),rtrim(trans.category) ")  //,trans.item,trans.transdate
		//	 .append(" group by rmtype.cname")

		.append("");
		
		return sqlBuffer.toString();
	}
	
	
	/**
	 * 得到NC系统所有部门信息
	 * 部门pk，上级部门pk
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,String[]> getAllBmxxByPk_org(String pk_org) throws DAOException{

		HashMap<String,String[]> map=new HashMap<String, String[]>();
		String sql="select pk_org,def1,pk_dept,decode (pk_fatherorg,'~',pk_dept,pk_fatherorg) pk_fatherorg from org_dept where pk_org='"+pk_org+"' and nvl(dr,0)=0 ";
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
				map.put(keys[i], new String[]{v.elementAt(2),v.elementAt(3)});//得到部门pk，上级部门pk
			}
		}
		
		return map;
		
	}
	
	/**
	 * 得到NC系统所有商品分类信息
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,SpflHVO> getAllSpflByPk_org(String pk_org) throws DAOException{
		HashMap<String,SpflHVO> map=new HashMap<String, SpflHVO>();
		String sql="select * from hk_srgk_hg_spfl where nvl(dr,0)=0 and pk_org='"+pk_org+"'";
		ArrayList<SpflHVO> list=(ArrayList<SpflHVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(SpflHVO.class));
		for (SpflHVO hvo : list) {
			map.put(hvo.getName().toLowerCase(), hvo);//得到商品分类VO
		}
		
		return map;
		
	}
	
	/**
	 * 依据组织得到NC系统所有结账方式信息
	 * @return
	 * @throws DAOException
	 */
	public HashMap<String,JzfsHVO> getAllJzfsByPk_org(String pk_org) throws DAOException{
		HashMap<String,JzfsHVO> map=new HashMap<String, JzfsHVO>();
		String sql="select * from hk_srgk_hg_jzfs where nvl(dr,0)=0 and nvl(dr,0)=0 and pk_org='"+pk_org+"'";
		ArrayList<JzfsHVO> list=(ArrayList<JzfsHVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(JzfsHVO.class));
		for (JzfsHVO hvo : list) {
			map.put(hvo.getName(), hvo);//得到商品分类VO
		}
		
		return map;
		
	}
	/**
	 * //出租率，平均房价，客房收入，可出租房间数, REVPAR
	 * zhangjc
	 * 2015-8-11上午9:28:24
	 * void
	 *  翻房率    rptitemgroup==null and rpitemname = '出租率'  取 rptincome                          
 	 *	平均房价  rptitemgroup==null and rpitemname = '平均房价'  取 rptincome
 	 *	REVPAR  = 客房收入/可出租房间数
 	 *	客房收入  rpitemname = '客房收入'  取  sum(rptincome)
 	 *	可出租房间数 select count(code) from room
	 *
	 */
	public HashMap<String,UFDouble[]>  getOtherData(JdbcSession session,Set<String> dates) throws BusinessException{
		try{
//		String sql="select rptdate,rptitemgroup,rptitemname,rptincome,rptkey from AppRpt where rtrim(rptItemName) in( '平均房价','出租率','客房收入') and rptincome >0.00 and rptDate ='2015-07-01' ";
		StringBuffer sb=new StringBuffer();
		sb.append("select convert(varchar(10),rptdate,120) czldate, rptincome czl,'' pjfjdate,'' pjfj,'' kfsrdate,'' kfsr from AppRpt where (rptitemgroup is null or rtrim(rptitemgroup)='') and rtrim(rptItemName) = '出租率' and rptincome >0.00") 
		  .append("	UNION ALL")
		  .append(" select '' czldate,'' czl,convert(varchar(10),rptdate,120) pjfjdate,rptincome pjfj,'' kfsrdate,'' kfsr from AppRpt where (rptitemgroup is null or rtrim(rptitemgroup)='') and rtrim(rptItemName) = '平均房价'  and rptincome >0.00") 
		  .append(" UNION ALL")
		  .append("	select '' czldate, '' czl,'' pjfjdate,'' pjfj, convert(varchar(10),max(rptdate),120) kfsrdate,sum(rptincome) kfsr from AppRpt where rtrim(rptItemName) = '客房收入' and rptincome >0.00   group by rptDate"); 
	String rooms="select count(code) from room";
		
	HashMap<String,UFDouble> czlmap=new HashMap<String, UFDouble>();
	HashMap<String,UFDouble> pjfjmap=new HashMap<String, UFDouble>();
	HashMap<String,UFDouble> kfsrmap=new HashMap<String, UFDouble>();
		Vector<Vector> vector=(Vector<Vector>)session.executeQuery(sb.toString(), new VectorProcessor());
		for (Vector v : vector) {
			UFDouble czl=nullAsZero(v.elementAt(1));
			if(!isZero(czl)){
				czlmap.put(v.elementAt(0).toString(), czl);//出租率(翻房率)
			}
			UFDouble pjfj=nullAsZero(v.elementAt(3));
			if(!isZero(pjfj)){
				pjfjmap.put(v.elementAt(2).toString(), pjfj);//平均房价
			}
			
			UFDouble kfsr=nullAsZero(v.elementAt(5));
			if(!isZero(kfsr)){
				kfsrmap.put(v.elementAt(4).toString(), kfsr);//客房收入
			}
		}
		
		Object obj=session.executeQuery(rooms.toString(), new ColumnProcessor());
		UFDouble room=nullAsZero(obj);
		HashMap<String,UFDouble[]> resultMap=new HashMap<String, UFDouble[]>();
		for (String date :dates) {
			resultMap.put(date, new UFDouble[]{nullAsZero(czlmap.get(date)),nullAsZero(pjfjmap.get(date)),nullAsZero(kfsrmap.get(date)),room,isZero(room)?UFDouble.ZERO_DBL:(nullAsZero(nullAsZero(kfsrmap.get(date))).div(room))});//出租率，平均房价，客房收入，可出租房间数, REVPAR  (客房收入/可出租房间数)
		}
		return resultMap;
		}catch(Exception e){
			throw new BusinessException(e.getMessage());
		}
	}
	
	/**
	 * zhangjc
	 * 2015-8-6上午10:37:30
	 * HashSet<String>
	 *得到NC中已经导入过的入账明细单据信息
	 */
	public HashSet<String> getNCBillsByPk_org(String pk_org) throws DAOException{
		String sql="select distinct substr(dbilldate,0,10) dbilldate from hk_srgk_jd_rzmx where nvl(dr,0)=0 and pk_org='"+pk_org+"'";
		ArrayList<String> datelist=(ArrayList<String>)getBaseDAO().executeQuery(sql, new ColumnListProcessor());
		HashSet<String> set=new HashSet<String>();
		for (String string : datelist) {
			set.add(string);
		}
		return set;
	}
	public UFDouble nullAsZero(UFDouble ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:ufDouble;
	}
	public boolean isZero(UFDouble ufDouble){
		return ufDouble==null?true:ufDouble.compareTo(UFDouble.ZERO_DBL)==0;
		
	}
	public UFDouble nullAsZero(Object ufDouble){
		return ufDouble==null?UFDouble.ZERO_DBL:new UFDouble(ufDouble.toString().trim());
	}
	public UFDateTime getCurrentTime() {
		return new UFDateTime(new Date(TimeService.getInstance().getTime()));
	}
}
