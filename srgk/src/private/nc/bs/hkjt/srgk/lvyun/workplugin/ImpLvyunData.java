package nc.bs.hkjt.srgk.lvyun.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.bd.defdoc.DefdoclistVO;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
/**
 * 导入绿云数据
 */
public class ImpLvyunData implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
		// 组织
		String[] pk_orgs = context.getPk_orgs();
		// 同步 入账明细
		UFBoolean isBill = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isBill"),UFBoolean.FALSE);
		// 同步 商品分类
		UFBoolean isSpfl = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isSpfl"),UFBoolean.FALSE);
		// 同步 结账方式
		UFBoolean isJzfs = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isJzfs"),UFBoolean.FALSE);
		// 同步 市场
		UFBoolean isShichang = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isShichang"),UFBoolean.FALSE);
		// 同步 来源
		UFBoolean isLaiyuan = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isLaiyuan"),UFBoolean.FALSE);
		// 同步 渠道
		UFBoolean isQudao = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isQudao"),UFBoolean.FALSE);
		
		UFDate bdate = PuPubVO.getUFDate( context.getKeyMap().get("bdate") );	// 开始日期
		UFDate edate = PuPubVO.getUFDate( context.getKeyMap().get("edate") );	// 结束日期

		UFDate now = new UFDate().getDateBefore(1);	// 默认为 当前日期 前一天
		
		if (bdate == null) {
			bdate = now;
		}
		if (edate == null) {
			edate = now;
		}
		if (edate.compareTo(bdate) < 0) {
			edate = bdate;
		}
		
		ArrayList<String> day_list = new ArrayList<String>();
		Integer days = edate.getDaysAfter(bdate);
		for (int i = 0; i <= days; i++) {
			day_list.add(bdate.getDateAfter(i).toString().substring(0, 10));
		}
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("pk_org", pk_orgs);
		param.put("date", day_list.toArray(new String[0]));
		
		if (isQudao.booleanValue()) {
			this.tongbu_qudao(param, null);		// 渠道（集团）
		}
		if (isLaiyuan.booleanValue()) {
			this.tongbu_laiyuan(param, null);	// 来源（集团）
		}
		if (isShichang.booleanValue()) {
			this.tongbu_shichang(param, null);	// 市场（集团）
		}
		if (isJzfs.booleanValue()) {
			this.tongbu_jzfs(param, null);	// 结账方式（集团）（上层格式调整了，与PMS里不一致）
		}
		if (isSpfl.booleanValue()) {
			this.tongbu_spfl(param, null);	// 商品分类（公司）
		}
		if (isBill.booleanValue()) {
			this.import_bill(param, null);	// 入账明细
		}
		
		return null;
	}
	
	/**
	 * 用于代码测试
	 * 后台任务无法执行
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		String[] pk_org_list = new String[]{
//				"0001N510000000001SY3", // 朗丽兹 9
//				"0001N510000000001SY5", // 康西 11
//				"0001N510000000001SY7", // 西山温泉 10
//				"0001N510000000001SY1", // 学院路16
				"0001N5100000000UVI5I"	// 太申18
		};
		String[] date_list = new String[]{
			"2020-09-12",
		};
		
		param.put("pk_org", pk_org_list);
		param.put("date", date_list);
		
//		this.tongbu_jzfs(param, null);	// 结账方式（集团）（上层格式调整了，与PMS里不一致）
//		this.tongbu_spfl(param, null);	// 商品分类（公司）
//		this.tongbu_shichang(param, null);	// 市场（集团）
//		this.tongbu_laiyuan(param, null);	// 来源（集团）
//		this.tongbu_qudao(param, null);		// 渠道（集团）
		this.import_bill(param, null);	// 入账明细
		return null;
	}
	
	/**
	 * 业务单据
	 */
	public Object import_bill(HashMap<String, Object> param, Object other) throws BusinessException {
//		String[] pk_org_list = new String[]{
//			"0001N510000000001SY3", // 朗丽兹
//				"0001N510000000001SY5", // 康西
////				"0001N510000000001SY7", // 西山温泉
//		};
//		String[] date_list = new String[]{
//			"2020-03-12",
////				"2020-03-03"
//		};
		
		String[] pk_org_list = (String[])param.get("pk_org");
		String[] date_list = (String[])param.get("date");
		
		String pk_group = "0001N510000000000EGY";
		String billType = "HK11";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
		
		// 取到 集团级档案
		// 结账方式（通过名称 去匹配）
		HashMap<String, JzfsHVO> DOC_JZFS = new HashMap<String, JzfsHVO>();
		{
			String whereSQL = " dr = 0 and pk_org = '0001N510000000001SY3' ";
			ArrayList<JzfsHVO> list = (ArrayList<JzfsHVO>)dao.retrieveByClause(JzfsHVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (JzfsHVO vo : list) {
//					String code = vo.getCode();
//					if (code.indexOf("-") > 0) {
//						code = code.substring(code.indexOf("-") + 1);
//					}
//					DOC_JZFS.put(code, vo);
					DOC_JZFS.put(vo.getName(), vo);
				}
			}
		}
		// 市场(通过名称 去匹配)
		HashMap<String, DefdocVO> DOC_SHICHANG = new HashMap<String, DefdocVO>();
		{
			String whereSQL = " dr = 0 and pk_defdoclist = " +
					"( select max(pk_defdoclist) from bd_defdoclist " +
					"  where dr = 0 and code = '200' ) ";
			ArrayList<DefdocVO> list = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (DefdocVO vo : list) {
					DOC_SHICHANG.put(vo.getName(), vo);
				}
			}
		}
		// 来源(通过 名称去匹配)
		HashMap<String, DefdocVO> DOC_LAIYUAN = new HashMap<String, DefdocVO>();
		{
			String whereSQL = " dr = 0 and pk_defdoclist = " +
					"( select max(pk_defdoclist) from bd_defdoclist " +
					"  where dr = 0 and code = '201' ) ";
			ArrayList<DefdocVO> list = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (DefdocVO vo : list) {
					DOC_LAIYUAN.put(vo.getName(), vo);
				}
			}
		}
		// 渠道(通过 名称去匹配)
		HashMap<String, DefdocVO> DOC_QUDAO = new HashMap<String, DefdocVO>();
		{
			String whereSQL = " dr = 0 and pk_defdoclist = " +
					"( select max(pk_defdoclist) from bd_defdoclist " +
					"  where dr = 0 and code = '202' ) ";
			ArrayList<DefdocVO> list = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (DefdocVO vo : list) {
					DOC_QUDAO.put(vo.getName(), vo);
				}
			}
		}
		
		// 先循环公司，再循环天
		for (String pk_org : pk_org_list) {
			// 读取配置表
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
			Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
//				String org_code = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("org_code"));
//				String org_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("org_name"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));
			
			// 取到公司级的档案
			// 商品分类(通过 名称去匹配，只取LY01、LY02、LY03下的)
			HashMap<String, SpflHVO> DOC_SPFL = new HashMap<String, SpflHVO>();
			// 商品分类-包房（通过编码 去对应 LY04 分类下的）
			HashMap<String, SpflHVO> DOC_SPFL_BAOFANG = new HashMap<String, SpflHVO>();
			// 商品分类-营业点（通过编码 去对应 LY05 分类下的）
			HashMap<String, SpflHVO> DOC_SPFL_YINGYEDIAN = new HashMap<String, SpflHVO>();
			// 商品分类-楼层（通过编码 去对应 LY06 分类下的）
			HashMap<String, SpflHVO> DOC_SPFL_LOUCENG = new HashMap<String, SpflHVO>();
			{
				String whereSQL = " dr = 0 and code like 'LY0%' and pk_org = '" + pk_org + "' ";
				ArrayList<SpflHVO> list = (ArrayList<SpflHVO>)dao.retrieveByClause(SpflHVO.class, whereSQL);
				if (list != null && list.size() > 0) {
					for (SpflHVO vo : list) {
						String code = vo.getCode();
						String name = vo.getName();
						
						if (code.startsWith("LY04-")) {
							DOC_SPFL_BAOFANG.put(code.substring(5), vo);
						} else if (code.startsWith("LY05-")) {
							DOC_SPFL_YINGYEDIAN.put(name, vo);
						} else if (code.startsWith("LY06-")) {
							DOC_SPFL_LOUCENG.put(name, vo);
						} else {
							DOC_SPFL.put(name, vo); // 除了以上，其它的都放在DOC_SPFL里
						}
					}
				}
			}
			
			// 取到公司级的档案
			// 收入项目（通过 名称去匹配）
			HashMap<String, SrxmHVO> DOC_SRXM = new HashMap<String, SrxmHVO>();
			HashMap<String, SrxmHVO> DOC_SRXM_KEY = new HashMap<String, SrxmHVO>();
			{
				String whereSQL = " dr = 0 and pk_org = '" + pk_org + "' ";
				ArrayList<SrxmHVO> list = (ArrayList<SrxmHVO>)dao.retrieveByClause(SrxmHVO.class, whereSQL);
				if (list != null && list.size() > 0) {
					for (SrxmHVO vo : list) {
						DOC_SRXM.put(vo.getName(), vo);
						DOC_SRXM_KEY.put(vo.getPk_hk_srgk_hg_srxm(), vo);
					}
				}
			}
			
			for (String date : date_list) {
				// 先判断 该组织该日期下，是否存在 单据， 如果存在 则不处理。
				String sql2 = "select pk_hk_srgk_jd_rzmx " +
						" from hk_srgk_jd_rzmx " +
						" where dr = 0 " +
						" and pk_org = '" + pk_org + "' " +
						" and substr(dbilldate, 1, 10) = '" + date + "' "
						;
				ArrayList<String> list2 = (ArrayList<String>)dao.executeQuery(sql2, new ColumnListProcessor());
				if (list2 != null && list2.size() > 0) {
					continue;
				}
				
				// 读取中间表（业务表）
				/**
					 新入账明细字段	PMS	POS	原入账明细字段
					行号	id	id	ACCID【accid】
					营业日期	biz_date	biz_date	入账时间【transdate】
					部门	dept	pccode_des	部门【bm_name】
					NC部门	根据商品分类默认部门	NC部门【bm_id】
					账单账号	accnt	accnt	　【vbdef04】
					房号/桌号	rmno	tableno	房间号【rmno】
					房间/台号	room_type_des	tableno_des	房间类型【rmtype_name】
					包价代码	package	　	　【vbdef05】
					市场	market_des	market_des	市场【vbdef01】
					渠道	channel_des	　	渠道【vbdef02】
					来源	source_des	source_des	来源【vbdef03】
					入账时间	create_datetime	close_datetime	　【vbdef06】
					入账项目编码	ta_code	code	入账项目编码【item_code】
					入账项目名称	ta_descript	code_descript	入账项目名称【item_name】
					NC商品分类	根据商品分类名称匹配	NC商品分类【spfl_id】
					消费金额	charge	charge	消费金额【charge】
					结算金额	pay	credit	结账金额【payment】
					NC收入项目	根据商品分类收入项目	NC收入项目【srxm_id】
					NC结账方式	根据结账方式名称匹配	NC结账方式【jzfs_id】
					应收账户	ar_name	ar_name	客户名字【khmz】
					储值卡ID	card_no	ar_accnt	　【vbdef07】
					操作标识	act_flag	　	　【vbdef08】
					转移方向	trans_flag	car_posting	　【vbdef09】
					转移账户编号	trans_accnt	　	　【vbdef10】
					信息摘要	ta_remark	　	备注【vbmemo】
					收银员	create_user	close_user	收银员【syy_code】
					卡类型	card_account_type	【csourcetypecode】
				 */
				StringBuffer querySQL_bill = 
				new StringBuffer("")
					// PMS
						.append(" select ")
						.append(" id as accid,")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,")
//						.append(" case when IFNULL(floor_des, 'NULL') in ('NULL', '') then dept else floor_des end as bm_name,")
						.append(" dept bm_name, ")
						.append(" accnt as vbdef04,")
						.append(" rmno as rmno,")
						.append(" room_type_des as rmtype_name,")
						.append(" package as vbdef05,")
						.append(" market_des as vbdef01,")
						.append(" channel_des as vbdef02,")
						.append(" source_des as vbdef03,")
						.append(" DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,")
						.append(" ta_code as item_code,")
						.append(" ta_descript as item_name,")
						.append(" charge as charge,")
						.append(" pay as payment,")
						.append(" ar_name as khmz,")
						.append(" card_no as vbdef07,")
						.append(" act_flag as vbdef08,")
						.append(" trans_flag as vbdef09,")
						.append(" trans_accnt as vbdef10,")
						.append(" ta_remark as vbmemo,")
						.append(" create_user as syy_code,")
						.append(" card_account_type as csourcetypecode, ")
						.append(" modu_code as vsourcebillcode, ")
						.append(" floor_des as csourcebillid ")
						.append(" from account_pms ")
						.append(" where (1=1) ")
//							.append(" and (charge != 0 or pay != 0)")	// 消费金额 或 结账金额 至少有一个不为0
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
//						.append(" and ( IFNULL(trans_flag, 'NULL') not in ('TO', 'FM') or (IFNULL(trans_flag, 'NULL') in ('TO', 'FM') and charge = 0)) ")
//							.append(" order by id ")
					.append(" union all ")
					// POS
						.append(" select ")
						.append(" id as accid,")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,")
						.append(" pccode_des as bm_name,")
						.append(" accnt as vbdef04,")
						.append(" tableno as rmno,")
						.append(" tableno_des as rmtype_name,")
						.append(" null as vbdef05,")
						.append(" market_des as vbdef01,")
						.append(" null as vbdef02,")
						.append(" source_des as vbdef03,")
						.append(" DATE_FORMAT(close_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,")
						.append(" code as item_code,")
						.append(" code_descript as item_name,")
						.append(" charge as charge,")
						.append(" credit as payment,")
						.append(" ar_name as khmz,")
						.append(" card_no as vbdef07,")
						.append(" null as vbdef08,")
						.append(" cat_posting as vbdef09,")
						.append(" null as vbdef10,")
						.append(" null as vbmemo,")
						.append(" close_user as syy_code,")
						.append(" null as csourcetypecode, ")
						.append(" null as vsourcebillcode, ")
						.append(" null as csourcebillid ")
						.append(" from account_pos ")
						.append(" where (1=1) ")
//							.append(" and (charge != 0 or credit != 0)")	// 消费金额 或 结账金额 至少有一个不为0
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
						
				;
				ArrayList<RzmxBVO> list = null;
				
				Connection hkjt_jd_conn= null;
				JdbcSession session = null;
				hkjt_jd_conn = new JDBCUtils(db_name + "_bill").getConn(JDBCUtils.HKJT_LY);
				session = new JdbcSession(hkjt_jd_conn);
				
				try {	
					list = (ArrayList)session.executeQuery(querySQL_bill.toString(), new BeanListProcessor(RzmxBVO.class));					
				} catch (Exception ex) {
					System.out.println(ex);
				} finally{
					session.closeAll();
					JDBCUtils.closeConn(hkjt_jd_conn);
				}
				
				/**
				 *  select 
					 id as accid,
					 DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,
					 '财务' as bm_name,
					 accnt as vbdef04,
					 foliono as rmno,
					 DATE_FORMAT( create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,
					 pccode as item_code,
					 descript as item_name,
					 credit as payment,
					 cardcode as vbdef07,
					 sta as vbdef08,
					 info1 as vbmemo,
					 create_user as syy_code
					 from pos_pay
				 */
				StringBuffer querySQL_bill_2 = 
					new StringBuffer("")
						.append(" select ")
						.append(" id as accid, ")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate, ")
						.append(" '财务' as bm_name, ")
						.append(" accnt as vbdef04, ")
						.append(" foliono as rmno, ")
						.append(" DATE_FORMAT( create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06, ")
						.append(" pccode as item_code, ")
						.append(" descript as item_name, ")
						.append(" credit as payment, ")
						.append(" 0.0 as charge, ")
						.append(" cardcode as vbdef07, ")
						.append(" sta as vbdef08, ")
						.append(" info1 as vbmemo, ")
						.append(" create_user as syy_code ")
						.append(" from pos_pay ")
						.append(" where (1=1) ")
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
						.append(" and sta not in ('X') ") // 不取 X
				;
				ArrayList<RzmxBVO> list_2 = null;
				hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
				session = new JdbcSession(hkjt_jd_conn);
				try {	
					list_2 = (ArrayList)session.executeQuery(querySQL_bill_2.toString(), new BeanListProcessor(RzmxBVO.class));					
				} catch (Exception ex) {
					System.out.println(ex);
				} finally{
					session.closeAll();
					JDBCUtils.closeConn(hkjt_jd_conn);
				}
				if (list_2 != null && list_2.size() > 0) {
					if (list == null) {
						list = new ArrayList<RzmxBVO>();
					}
					for (RzmxBVO bVO : list_2) {
						list.add(bVO);
					}
				}
				
				/**
				 * 如果是 连锁的数据库，需要再连一次 集团库，进行取 充值卡的数据
				 */
				if ("hkjt_ly_liansuo".equals(db_name)) {
					StringBuffer querySQL_bill_3 = 
					new StringBuffer("")
						// PMS
						.append(" select ")
						.append(" id as accid,")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,")
						.append(" dept as bm_name,")
						.append(" accnt as vbdef04,")
						.append(" rmno as rmno,")
						.append(" room_type_des as rmtype_name,")
						.append(" package as vbdef05,")
						.append(" market_des as vbdef01,")
						.append(" channel_des as vbdef02,")
						.append(" source_des as vbdef03,")
						.append(" DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,")
						.append(" ta_code as item_code,")
						.append(" ta_descript as item_name,")
						.append(" charge as charge,")
						.append(" pay as payment,")
						.append(" ar_name as khmz,")
						.append(" card_no as vbdef07,")
						.append(" act_flag as vbdef08,")
						.append(" trans_flag as vbdef09,")
						.append(" trans_accnt as vbdef10,")
						.append(" ta_remark as vbmemo,")
						.append(" create_user as syy_code,")
						.append(" card_account_type as csourcetypecode, ")
						.append(" modu_code as vsourcebillcode ")
						.append(" from account_pms ")
						.append(" where (1=1) ")
//										.append(" and (charge != 0 or pay != 0)")	// 消费金额 或 结账金额 至少有一个不为0
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
					;
					ArrayList<RzmxBVO> list_3 = null;
					hkjt_jd_conn = new JDBCUtils("hkjt_ly_feiliansuo_bill").getConn(JDBCUtils.HKJT_LY);
					session = new JdbcSession(hkjt_jd_conn);
					try {	
						list_3 = (ArrayList)session.executeQuery(querySQL_bill_3.toString(), new BeanListProcessor(RzmxBVO.class));					
					} catch (Exception ex) {
						System.out.println(ex);
					} finally{
						session.closeAll();
						JDBCUtils.closeConn(hkjt_jd_conn);
					}
					if (list_3 != null && list_3.size() > 0) {
						if (list == null) {
							list = new ArrayList<RzmxBVO>();
						}
						for (RzmxBVO bVO : list_3) {
							list.add(bVO);
						}
					}
				}
				/***END***/
				
				if (list != null && list.size() > 0) {
					IJd_rzmxMaintain itf = NCLocator.getInstance().lookup(IJd_rzmxMaintain.class);
					
					RzmxHVO hVO = new RzmxHVO();
					hVO.setPk_org(pk_org);
					hVO.setPk_org_v(pk_org_v);
					hVO.setPk_group(pk_group);
					hVO.setIbillstatus(-1);
					hVO.setVbilltypecode(billType);
					hVO.setDbilldate(PuPubVO.getUFDate(date));
					
					UFDouble charge = UFDouble.ZERO_DBL;
					UFDouble pay = UFDouble.ZERO_DBL;
					RzmxBVO[] bVOs = list.toArray(new RzmxBVO[0]);
					
					/**
					 * 先循环一次，找出 账单对应的数据
					 */
					HashMap<String, ArrayList<RzmxBVO>> MAP_ZHANGDAN = new HashMap<String, ArrayList<RzmxBVO>>();
					for (RzmxBVO bVO: bVOs) {
						String vbdef04 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef04());	// 账单号
						UFDouble charge_temp = PuPubVO.getUFDouble_ZeroAsNull(bVO.getCharge());	// 消费金额
						if (vbdef04 != null && charge_temp != null) {
							if (!MAP_ZHANGDAN.containsKey(vbdef04)) {
								MAP_ZHANGDAN.put(vbdef04, new ArrayList<RzmxBVO>());
							}
							MAP_ZHANGDAN.get(vbdef04).add(bVO);
						}
					}
					
					ArrayList<RzmxBVO> bVO_list = new ArrayList<RzmxBVO>(); // 最终要保存的表体
					Integer rowCount = 0;
					for (int i = 0; i < bVOs.length; i++) {
						// 本次循环 要保存的数据
						ArrayList<RzmxBVO> bVO_list_temp = new ArrayList<RzmxBVO>();
						
						RzmxBVO bVO = bVOs[i];
						/**
						 * 数据加工
						 *  dept = AR 并且 trans_flag in ('TO','FM') 改为转应收
						 *  如果 pay <> 0 则取反
						 *  如果 charge <> 0 则 转移到 pay上
						 *  房型：朗丽兹花园套房
						 *  来源：特殊
						 *  渠道：其他
						 *  市场：其他-其他
						 *  9800、转应收
						 */
						String itemName = PuPubVO.getString_TrimZeroLenAsNull(bVO.getItem_name());	// 入账项目-名称
						String bmName = PuPubVO.getString_TrimZeroLenAsNull(bVO.getBm_name()); // 部门
						String vbdef09 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef09()); // TO FM
						String csourcetypecode = PuPubVO.getString_TrimZeroLenAsNull(bVO.getCsourcetypecode()); // 卡类型
						String vbdef08 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef08()); // 操作标识
						String vsourcebillcode = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVsourcebillcode());	// 来源类型
						String csourcebillid = PuPubVO.getString_TrimZeroLenAsNull(bVO.getCsourcebillid());	// 楼层
						
						if (csourcebillid != null
						&& ("TO".equals(vbdef09) 
						 || "FM".equals(vbdef09))
						) {// 前台数据中floor_des≠空值，且trans_flag=to和fm的 不取的数据不取入酒店入账明细。
							continue;
						} else if ("前台".equals(bmName) 
						&& ("TO".equals(vbdef09) 
						 || "FM".equals(vbdef09))
						) {// dept = 前台 ，且trans_flag=to和fm的 不取的数据
							continue;
						}
						else if ("AR".equals(bmName) 
						&& ("TO".equals(vbdef09) 
						 || "FM".equals(vbdef09))) {
							if (PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null) {
								// pay <> 0
								bVO.setPayment(bVO.getPayment().multiply(-1.0));
								bVO.setVbmemo("NC转应收1："+bVO.getVbmemo());
								bVO.setItem_code("9800");
								bVO.setItem_name("转应收");
							}
							if (PuPubVO.getUFDouble_ZeroAsNull(bVO.getCharge()) != null) {
								// charge <> 0
								bVO.setPayment(bVO.getCharge());
								bVO.setCharge(UFDouble.ZERO_DBL);
								bVO.setVbmemo("NC转收入2："+bVO.getVbmemo());
								bVO.setItem_code("9800");
								bVO.setItem_name("转应收");
								bVO.setVbdef01("其他-其他"); // sc
								bVO.setVbdef02("其他"); // qd
								bVO.setVbdef03("特殊"); // ly
								bVO.setRmtype_name("朗丽兹花园套房"); // rmType
							}
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
						}
						/***END***/
						/**
						 * 增加数据：1
						 * dept=ar值，trans_flag值为空，pay≠0的，遇见此类数据，需要单独做出一行数值，
						 *  复制上一条数据所有字段之后，需要把ta_descript字段数值，
						 *  替换成记账回收(转应收)，Pay字段数值，改为原金额乘以-1
						 */
						else if ("AR".equals(bmName)
						&& PuPubVO.getString_TrimZeroLenAsNull(vbdef09) == null
						&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null
						) {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成3："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.0));
							bVO_clone.setItem_code("9800");
							bVO_clone.setItem_name("转应收");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
							
						}
						/***END***/
						/**
						 * 会员卡的特殊处理
						 * ****** 过滤数据的放在前面 A
						 * 1. dept=储值卡，act_flag=CL，此数据行不取，也不参与任何加工条件的计算
						 * 2. dept=储值卡，trans_flag=CL，此数据行不取，也不参与任何加工条件的计算
						 * 3. dept=储值卡，act_flag=PP，此数据行不取，也不参与任何加工条件的计算
						 * 4. dept=储值卡，card_account_type=BASE，且act_flag=CH，此数据行不取，也不参与任何加工条件的计算
						 * 5. dept=储值卡，且trans_flag=TO或者FM的，此数据行不取，也不参与任何加工条件的计算
						 * ****** 处理的数据 B
						    1. card_account_type=BASE，且act_flag=PA，单独复制原数据一行（并保留原始行数据），PAY字段金额乘以-1，替换ta_descript字段值为储值卡销售。
							2. card_account_type=TIMES，且act_flag=PA，单独复制原数据一行（并保留原始行数据），PAY字段金额乘以-1，替换ta_descript字段值为储值次卡销售。
							3. card_account_type字段为TIMES值，且act_flag字段为CH值，单独复制原数据一行（并保留原始行数据），将charge字段列金额，复制到PAY字段列，替换ta_descript字段值为储值次卡
							4. dept=储值卡，act_flag=AD，card_account_type=TIMES，charge≠0的, 单独复制原数据一行（并保留原始行数据），将charge字段列金额，复制到pay字段列，替换ta_descript字段值为储值次卡
							5. dept=储值卡，act_flag=AD， card_account_type=TIMES，pay≠0的，单独复制原数据一行（并保留原始行数据），将PAY字段金额乘以-1，替换ta_descript字段值为储值次卡销售
							6. dept=储值卡，act_flag=AD， card_account_type=BASE，pay≠0的，单独复制原数据一行（并保留原始行数据），将PAY字段金额乘以-1，替换ta_descript字段值为储值卡销售。
						 */
						else if ("储值卡".equals(bmName)
							  && "CL".equals(vbdef08)
						) {// A-1
							continue;
						}
						else if ("储值卡".equals(bmName)
							  && "CL".equals(vbdef09)
						) {// A-2
							continue;
						}
						else if ("储值卡".equals(bmName)
							  && "PP".equals(vbdef08)
						) {// A-3
							continue;
						}
						else if ("储值卡".equals(bmName)
							&& "BASE".equals(csourcetypecode)
							&& "CH".equals(vbdef08)
						) {// A-4
							continue;
						}
						else if ("储值卡".equals(bmName)
							&& ("TO".equals(vbdef09) 
							 || "FM".equals(vbdef09))
						) {// A-5
							continue;
						}
						else if ("BASE".equals(csourcetypecode)
							&& "PA".equals(vbdef08)
						) {// B-1
							// 2、card_account_type字段为BASE值，且act_flag字段为PA值，单独复制原数据一行（并保留原始行数据），PAY字段乘以-1，替换ta_descript字段值为储值卡销售。
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成4："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("0301");
							bVO_clone.setItem_name("储值卡销售");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
							
						}
						else if ("TIMES".equals(csourcetypecode)
							&& "PA".equals(vbdef08)
						) {// B-2
							// 3、card_account_type字段为TIMES值，且act_flag字段为PA值，单独复制原数据一行（并保留原始行数据），PAY字段乘以-1，替换ta_descript字段值为储值次卡销售。
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成5："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("0302");
							bVO_clone.setItem_name("储值次卡销售");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("TIMES".equals(csourcetypecode)
							&& "CH".equals(vbdef08)
						) {// B-3
							// 4、
							//  1. card_account_type字段为TIMES值，且act_flag字段为CH值，ta_descript里面的值与商品分类里面的账项档案是一样的，对应匹配部门和NC收入项目即可
							//  2. 单独复制原数据一行（并保留原始行数据），在PAY字段，=charge的金额，ta_descript字段值为储值次卡
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成6："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getCharge());
							bVO_clone.setCharge(UFDouble.ZERO_DBL);
							bVO_clone.setItem_code("0303");
							bVO_clone.setItem_name("储值次卡");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("储值卡".equals(bmName)
							&& "AD".equals(vbdef08)
							&& "TIMES".equals(csourcetypecode)
							&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getCharge()) != null
						) {// B-4
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成10："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getCharge());
							bVO_clone.setCharge(UFDouble.ZERO_DBL);
							bVO_clone.setItem_code("0303");
							bVO_clone.setItem_name("储值次卡");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("储值卡".equals(bmName)
							&& "AD".equals(vbdef08)
							&& "TIMES".equals(csourcetypecode)
							&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null
						) {// B-5
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成11："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.0));
							bVO_clone.setItem_code("0302");
							bVO_clone.setItem_name("储值次卡销售");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("储值卡".equals(bmName)
							&& "AD".equals(vbdef08)
							&& "BASE".equals(csourcetypecode)
							&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null
						) {// B-6
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成12："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.0));
							bVO_clone.setItem_code("0301");
							bVO_clone.setItem_name("储值卡销售");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						/***END***/
						/**
						 * 判定条件: 
						 * code_descript=POS-免费招待，复制出一行数据，替换code_descript=调整-POS-免费招待，pay字段金额乘以-1。
						 * 其次，检索accnt字段的账单号，判定该账单号的charge列信息，复制出相关行数据之后，charge金额乘以-1。
						 */
						else if ("POS-免费招待".equals(itemName)) {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成7："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("918101");
							bVO_clone.setItem_name("调整-POS-免费招待");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
							
							// 根据账单号，找到 消费数据
							String vbdef04 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef04());
							ArrayList<RzmxBVO> zhangdanVOs = MAP_ZHANGDAN.get(vbdef04);
							if (zhangdanVOs != null && zhangdanVOs.size() > 0) {
								
//								if (zhangdanVOs.size() > 1){
									/**
									 * 如果 消费是多行，则进行分摊。先判断 消费总额 与 免费总额，是否一致。
									 * 如果 一致，则按照消费金额 去抵扣。
									 * 如果 不一致，则按照比例 去 抵扣。（尾差处理）
									 */
									// 第一次循环，计算消费总额
									UFDouble charge_total = UFDouble.ZERO_DBL;
									for (int k = 0; k < zhangdanVOs.size(); k++) {
										RzmxBVO zdVO = zhangdanVOs.get(k);
										charge_total = charge_total.add(zdVO.getCharge());
									}
									// 第二次循环，进行抵扣或分摊。
									UFDouble free_totale = bVO.getPayment();
									if (charge_total.compareTo(free_totale) == 0) {
										// 如果 消费 与 免费 一致,则直接取数。
										for (RzmxBVO zdVO : zhangdanVOs) {
											RzmxBVO zdVO_clone = (RzmxBVO)zdVO.clone();
											zdVO_clone.setVbmemo("NC生成8："+zdVO_clone.getVbmemo());
											zdVO_clone.setCharge(zdVO_clone.getCharge().multiply(-1.00));
											zdVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
											bVO_list_temp.add(zdVO_clone);
										}
									} else {
										// 否则 不一致，进行按比例 抵扣。
										charge_total = free_totale;	// 待分摊的 消费 = 免费
										UFDouble charge_use = UFDouble.ZERO_DBL;	// 已经分摊的消费合计
										for (int k = 0; k < zhangdanVOs.size(); k++) {
											RzmxBVO zdVO = zhangdanVOs.get(k);
											RzmxBVO zdVO_clone = (RzmxBVO)zdVO.clone();
											UFDouble banci = null;
											if (k == zhangdanVOs.size()-1) {
												// 如果是最后一笔，则直接用余额
												banci = charge_total.sub(charge_use);
											} else {
												// 非最后一笔，则按比例分摊。本行的消费/消费总额*免费总额
												banci = zdVO_clone.getCharge().div(charge_total).multiply(free_totale).setScale(2, UFDouble.ROUND_HALF_UP);
											}
											zdVO_clone.setVbmemo("NC生成8："+zdVO_clone.getVbmemo());
											zdVO_clone.setCharge(banci.multiply(-1.00));
											zdVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
											bVO_list_temp.add(zdVO_clone);
											charge_use = charge_use.add(banci);
										}
									}
//								} else {
//									for (RzmxBVO zdVO : zhangdanVOs) {
//										RzmxBVO zdVO_clone = (RzmxBVO)zdVO.clone();
//										zdVO_clone.setVbmemo("NC生成8："+zdVO_clone.getVbmemo());
//										/**
//										 * HK 2020年9月14日17:18:26
//										 * NC生8 跟 7 的金额是一致的
//										 */
//	//									zdVO_clone.setCharge(zdVO_clone.getCharge().multiply(-1.00));
//										zdVO_clone.setCharge(bVO_clone.getPayment());
//										/***END***/
//										zdVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
//										bVO_list_temp.add(zdVO_clone);
//									}
//								}
							}
						}
						/**
						 * 1. 所以我们要把PMS系统里面，这条数据隐藏不取它。隐藏条件，是modu_code=04，且trans_flag为空值的。
						 * 2. 我们的散客押金在计算的时候，需要加上POS系统产生的所有{转应收}的credit那个金额。才是最正确的散客押金。（之前西软也处理过这个情况）
						 * ** 不处理 第二条
						 */
						else if ("04".equals(vsourcebillcode)
							  && null == vbdef09
						) {
							continue;
						}
						/***END***/
						/**
						 * 发现I状态单据，单独复制原数据一行（并保留原始行数据），descript字段替换成 餐饮预付金，credit字段乘以-1
						 * 发现T状态单据，单独复制原数据一行（并保留原始行数据），descript字段替换成 餐饮预付金，credit字段乘以-1
						 */
						else if ("I".equals(vbdef08)
							 ||  "T".equals(vbdef08)
							 ||  "O".equals(vbdef08)
						) {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC生成9："+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("992001");
							bVO_clone.setItem_name("餐饮预付金");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						
						/**
						 * 其它情况
						 */
						else {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
						}
						
						/**
						 * 如果楼层有值，则将部门给替换掉
						 */
						if (csourcebillid != null) {
							bVO.setBm_name(csourcebillid);
						}
						/***END***/
						
						Object[] res = (Object[])this.doBodyVO(
							bVO_list, 
							bVO_list_temp, 
							charge, 
							pay, 
							DOC_JZFS, 
							DOC_SPFL_BAOFANG, 
							DOC_SPFL, 
							DOC_SRXM, 
							DOC_SRXM_KEY, 
							DOC_SPFL_YINGYEDIAN, 
							DOC_QUDAO, 
							DOC_SHICHANG, 
							DOC_LAIYUAN,
							DOC_SPFL_LOUCENG
						);
						
						bVO_list = (ArrayList<RzmxBVO>)res[0];
						charge = (UFDouble)res[1];
						pay = (UFDouble)res[2];
					}
					
					hVO.setXfje(charge);
					hVO.setJzje(pay);
					
					RzmxBillVO billVO = new RzmxBillVO();
					billVO.setParentVO(hVO);
					billVO.setChildrenVO(bVO_list.toArray(new RzmxBVO[0]));
					
//					for (Object one : billVO.getChildrenVO()) {
//						RzmxBVO bVO = (RzmxBVO)one;
//						if (bVO.getPk_hk_srgk_jd_rzmx_b() != null) {
//							System.out.println(bVO);
//						}
//					}
					
					/**
					 * 查询5项指数
					 * 出租率	ffl			150000
					 * 平均房价	pjfj	140000
					 * REVPAR	revpar	160000
					 * 房晚	kfsr		120000
					 * 房间数	kczfs		110000
					 * 
					 *  110000	a、客房总数	224.00
						120000	b、房晚(按市场码)	22.00
						140000	d、平均房价(按市场码)	736.09
						150000	e、出租率%(不含自用)	9.82
						160000	f、Revpar	72.29
					 * 
					 */
					{
						StringBuffer querySQL_5 = 
							new StringBuffer("SELECT ")
									.append(" code,")
									.append(" descript,")
									.append(" day ")
									.append(" FROM rep_jour_history ")
									.append(" WHERE (1=1) ")
									.append(" AND CODE IN ('140000','150000','160000','110000','120000') ")
									.append(" and hotel_id = ").append(hotel_id).append(" ")
									.append(" and biz_date = '").append(date).append("' ")
						;
						ArrayList<Object> list_5 = null;
						hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
						session = new JdbcSession(hkjt_jd_conn);
						try {	
							list_5 = (ArrayList)session.executeQuery(querySQL_5.toString(), new ArrayListProcessor());					
						} catch (Exception ex) {
							System.out.println(ex);
						} finally{
							session.closeAll();
							JDBCUtils.closeConn(hkjt_jd_conn);
						}
						if (list_5 != null && list_5.size() > 0) {
							for (Object obj : list_5) {
								Object[] item = (Object[])obj;
								String code = PuPubVO.getString_TrimZeroLenAsNull(item[0]);
								UFDouble day = PuPubVO.getUFDouble_ValueAsValue(item[2]);
								if ("150000".equals(code)) { // 出租率
									hVO.setFfl(day);
								} else if ("140000".equals(code)) { // 平均房价
									hVO.setPjfj(day);
								} else if ("160000".equals(code)) { // Revpar
									hVO.setRevpar(day);
								} else if ("120000".equals(code)) { // 房晚
									hVO.setKfsr(day);
								} else if ("110000".equals(code)) { // 客房总数
									hVO.setKczfs(day);
								}
							}
						}
					}
					
					RzmxBillVO[] res = itf.insert(new RzmxBillVO[]{billVO}, null);
					System.out.println(res);
				}
				// 读取中间表（档案表）
				
			}
		}
		return null;
	}
	
	/**
	 * 进行表体数据的档案翻译，以及金额的叠加
	 */
	private Object doBodyVO(
			ArrayList<RzmxBVO> bVO_list,
			ArrayList<RzmxBVO> bVO_list_temp, 
			UFDouble charge,
			UFDouble pay,
			HashMap<String, JzfsHVO> DOC_JZFS,
			HashMap<String, SpflHVO> DOC_SPFL_BAOFANG,
			HashMap<String, SpflHVO> DOC_SPFL,
			HashMap<String, SrxmHVO> DOC_SRXM,
			HashMap<String, SrxmHVO> DOC_SRXM_KEY,
			HashMap<String, SpflHVO> DOC_SPFL_YINGYEDIAN,
			HashMap<String, DefdocVO> DOC_QUDAO,
			HashMap<String, DefdocVO> DOC_SHICHANG,
			HashMap<String, DefdocVO> DOC_LAIYUAN,
			HashMap<String, SpflHVO> DOC_SPFL_LOUCENG
			
	) throws BusinessException {
		
		for (RzmxBVO bVO : bVO_list_temp) {
		
			charge = charge.add(bVO.getCharge());	// 累计消费
			pay = pay.add(bVO.getPayment());		// 累计结账
			/** 进行档案的翻译
			* 根据入账项目编码，去找 NC的相应的字段
			* 如果 结账金额 不为零，说明该行是 结账信息，需要 找 NC结账方式
			* 否则 ，说明该行是 消费信息，需要找 NC的商品分类，以及对应的收入项目 以及部门 
			*/
			String bmName = bVO.getBm_name();	// 部门name
//			String itemCode = bVO.getItem_code();	// 入账项目代码
			String itemName = bVO.getItem_name();	// 入账项目名称
			if (PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null) {
				// 结账 => NC结账方式
				JzfsHVO doc = DOC_JZFS.get(itemName);
				if (doc != null) {
					bVO.setJzfs_id(doc.getPk_hk_srgk_hg_jzfs());
				}
			} else {
				SpflHVO doc = null;
				// 消费 => NC商品分类 => NC收入项目 & NC部门 => NC上级部门
				// 如果 入账项目名称 是 房费，则按房间类型 去 找收入项目。
				// 如果 入账项目名称 是 包房费，则去 房间号 去找 桌台类商品分类
	//				String rmtypeName = bVO.getRmtype_name();
				if ("包房费".equals(itemName)) {
					String rmno = bVO.getRmno();
					doc = DOC_SPFL_BAOFANG.get(rmno);
				}
	//			else if ("房费".equalsIgnoreCase(itemName)) {
				else if (itemName.indexOf("房费") >= 0
					|| itemName.indexOf("加床费") >= 0
				) {
					String rmtypeName = bVO.getRmtype_name();
					doc = DOC_SPFL.get(rmtypeName);
				}
				/**
				 * 零点及套餐（总）：如果绿云传过来的是 总，则需要根据 时间段，去找到 具体的商品分类。
				 * 零点及套餐（午）零点及套餐（晚）零点及套餐（早）零点及套餐（夜）
				 * 2020年7月9日17:29:28
				 */
				else if ("零点及套餐（总）".equals(itemName)) {
					SpflHVO zong = DOC_SPFL.get("零点及套餐（总）");
					String sjd = zong.getVdef2(); // 时间段：午=00:00-17:00;晚=17:00-24:00
					// 按照最大化需求，构造出 早、午、晚、夜 的数据模型。
					// 大于等于 开始时间，小于 结束时间
					// 入账时间 vbdef06
					List<String[]> sjd_list = this.getSjdList(sjd);
					String[] sjd_str_list = this.getSjdStrList();
					// 入账时间-例如：2020-07-01 12:19:10
					String rzsj = bVO.getVbdef06().substring(11, 19);	// 入账时间
					String sjd_str = this.getSjdStr(sjd_list, sjd_str_list, rzsj); // 时间段描述
					// 找到 真实的商品分类， 按时间段的。
					String trueItemName = itemName.replaceFirst("总", sjd_str);
					doc = DOC_SPFL.get(trueItemName);
				}
				else {
					doc = DOC_SPFL.get(itemName);
				}
				if (doc != null) {
					bVO.setSpfl_id(doc.getPk_hk_srgk_hg_spfl());
					bVO.setSrxm_id(doc.getPk_hk_srgk_hg_srxm());
					String pk_dept = doc.getPk_dept();
					String srxmId = doc.getPk_hk_srgk_hg_srxm();
					SrxmHVO srxmVO = DOC_SRXM_KEY.get(srxmId);
					if (srxmVO != null) {
						String srxmName = srxmVO.getName();
						if (srxmName.endsWith("（总）")) {
	//						String bmName = bVO.getBm_name();
							String srxmName2 = srxmName.replaceFirst("（总）", "") + "-" + bmName;
							SrxmHVO srxmVO2 = DOC_SRXM.get(srxmName2);
							if (srxmVO2 != null) {
								bVO.setSrxm_id(srxmVO2.getPk_hk_srgk_hg_srxm());
							}
						} else if (srxmName.endsWith("（房）")) {
							String rmno = bVO.getRmno();
							String srxmName2 = srxmName.replaceFirst("（房）", "") + "-" + rmno;
							SrxmHVO srxmVO2 = DOC_SRXM.get(srxmName2);
							if (srxmVO2 != null) {
								bVO.setSrxm_id(srxmVO2.getPk_hk_srgk_hg_srxm());
							}
						} else {
							bVO.setSrxm_id(srxmId);
						}
					}
					if (pk_dept == null) {
						// 如果 商品分类上的 部门为空，就去 部门对应的商品分类营业点（LY05）上配的部门
						SpflHVO doc_yyd = DOC_SPFL_YINGYEDIAN.get(bmName);
						if (doc_yyd != null) {
							pk_dept = doc_yyd.getPk_dept();
						}
					}
					if (pk_dept == null) {
						// 如果 商品分类上的 部门为空，就去 部门对应的商品分类楼层档案（LY06）上配的部门
						SpflHVO doc_lc = DOC_SPFL_LOUCENG.get(bmName);
						if (doc_lc != null) {
							pk_dept = doc_lc.getPk_dept();
						}
					}
					bVO.setBm_id(pk_dept);
					bVO.setBm_fid(pk_dept);
				}
			}
			// 三个自定义档案的 翻译
			if (bVO.getVbdef01() != null) {
				// 市场
				DefdocVO doc = DOC_SHICHANG.get(bVO.getVbdef01());
				if (doc != null) {
					bVO.setVbdef01(doc.getPk_defdoc());
				}
			}
			if (bVO.getVbdef02() != null) {
				// 渠道
				DefdocVO doc = DOC_QUDAO.get(bVO.getVbdef02());
				if (doc != null) {
					bVO.setVbdef02(doc.getPk_defdoc());
				}
			}
			if (bVO.getVbdef03() != null) {
				// 来源
				DefdocVO doc = DOC_LAIYUAN.get(bVO.getVbdef03());
				if (doc != null) {
					bVO.setVbdef03(doc.getPk_defdoc());
				}
			}
			bVO_list.add(bVO);	// 添加到表体数据
		}
		
		return new Object[]{
			bVO_list,
			charge,
			pay,
		};
	}
	
	/**
	 * 获得时间段
	 */
	private List<String[]> getSjdList(String sjd) {
		List<String[]> sjd_list = new ArrayList<>();
		String[] sjd_0 = new String[]{null, null};	// 早
		String[] sjd_1 = new String[]{null, null};	// 午
		String[] sjd_2 = new String[]{null, null};	// 晚
		String[] sjd_3 = new String[]{null, null};	// 夜
		sjd_list.add(sjd_0);
		sjd_list.add(sjd_1);
		sjd_list.add(sjd_2);
		sjd_list.add(sjd_3);
		
		if (PuPubVO.getString_TrimZeroLenAsNull(sjd) != null) {
			// 考虑半角全角的差异，统一转成 半角。
			sjd = sjd.replaceAll("；", ";");
			sjd = sjd.replaceAll("：", ":");
			sjd = sjd.replaceAll(" ", "");
			// 1、先按; 分割时间段
			String[] _1 = sjd.split(";");
			for (int i = 0; i < _1.length; i++) {
				String _1_str = _1[i];
				// 2、按= 分割时间描述
				String[] _2 = _1_str.split("=");
				Integer sjdIndex = getSjdIndex(_2[0]); // 获得时间段索引
				// 3、按- 分割开始时间、结束时间
				String[] _3 = _2[1].split("-");
				// 封装
				sjd_list.get(sjdIndex)[0] = _3[0] + ":00";
				sjd_list.get(sjdIndex)[1] = _3[1] + ":00";
			}
			
		} else {
			/**
			 * 	早餐经营时间 06:00—10:30
				午餐经营时间 10:30—17:30
				晚餐经营时间 17:30—06:00（次日）
				NC的配置 ↓
				早=06:00-10:30;午=10:30-17:30;晚=17:30-06:00
			 */
			// 早
			sjd_list.get(0)[0] = "06:00:00";
			sjd_list.get(0)[1] = "10:30:00";
			// 午
			sjd_list.get(1)[0] = "10:30:00";
			sjd_list.get(1)[1] = "17:30:00";
			// 晚
			sjd_list.get(2)[0] = "17:30:00";
			sjd_list.get(2)[1] = "06:00:00";
		}
		
		return sjd_list;
	}
	
	/**
	 * 获得时间段描述
	 */
	private String[] getSjdStrList() {
		return new String[]{"早", "午", "晚", "夜"};
	}
	
	/**
	 * 根据 时间段描述返回索引
	 */
	private Integer getSjdIndex(String sjdStr) {
		String[] sjdStrList = getSjdStrList();
		for (int i = 0; i < sjdStrList.length; i++) {
			if (sjdStr.equals(sjdStrList[i])) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 根据 时间 获取 时间段描述
	 * 约定，最后一期 是 跨天的。
	 */
	private String getSjdStr(List<String[]> sjd_list, String[] sjd_str_list, String sj) {
		String sjd_str = "总";
		for (int i = 0; i < sjd_list.size(); i++) {
			// 循环时间段，如果入账时间 小于 时间段的结束日期，就说明再此时间内。
			String beginTime = sjd_list.get(i)[0]; 	// 时间段的开始日期
			String endTime = sjd_list.get(i)[1]; 	// 时间段的结束日期
			// 如果开始日期 或 结束日期 为空，则不处理
			if (beginTime == null || endTime == null) {
				continue;
			}
			// 若 结束日期 大于 开始日期，说明是 前面几期， 则按 范围来判断
			if (endTime.compareTo(beginTime) > 0) {
				if (sj.compareTo(beginTime) >= 0 && sj.compareTo(endTime) < 0) {
					sjd_str = sjd_str_list[i];
					break;
				}
			}
			// 否则，就是这一期。
			else {
				sjd_str = sjd_str_list[i];
				break;
			}
		}
		return sjd_str;
	}
	
	/**
	 * 结账方式（集团）
	 * 取 非连锁的库即可
	 * 放在 朗丽兹、0001N510000000001SY3
	 * vdef5 时间戳
	 */
	public Object tongbu_jzfs(HashMap<String, Object> param, Object other) throws BusinessException {
			
		String[] pk_org_list = new String[]{
			"0001N510000000001SY3" // 朗丽兹
		};
		
		String pk_group = "0001N510000000000EGY";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
		
		for (String pk_org : pk_org_list) {
			// 读取配置表
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
//				Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));

			StringBuffer querySQL = 
			new StringBuffer(" SELECT ")
					.append(" code, ")
					.append(" descript as name, ")
					.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') as vdef5 ")
					.append(" from code_transaction ")
					.append(" where ")
					.append(" code like '9%' ")
					.append(" and hotel_id = 0 ")
					.append(" and is_halt = 'F' ") // 非停用
					.append(" order by code ")
			;
			
			ArrayList<JzfsHVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {	
				list_source = (ArrayList)session.executeQuery(querySQL.toString(), new BeanListProcessor(JzfsHVO.class));					
			} catch (Exception ex) {
				System.out.println(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// 取数之后，放到 缓存。(编码为准)
				HashMap<String, JzfsHVO> MAP_source = new HashMap<>();
				for (JzfsHVO vo : list_source) {
					MAP_source.put(vo.getCode(), vo);
				}
				// 取NC的档案
				String whereSQL = " dr = 0 and pk_org = '" + pk_org + "' ";
				ArrayList<JzfsHVO> list_des = (ArrayList<JzfsHVO>)dao.retrieveByClause(JzfsHVO.class, whereSQL);
				HashMap<String, JzfsHVO> MAP_des = new HashMap<>();
				if (list_des != null && list_des.size() > 0) {
					for (JzfsHVO vo : list_des) {
						String code = vo.getCode();
						if (code.indexOf("-") > 0) {
							code = code.substring(code.indexOf("-") + 1);
						}
						MAP_des.put(code, vo);
					}
				}
				// 新增的集合
				ArrayList<JzfsHVO> list_add = new ArrayList<JzfsHVO>();
				// 修改的集合
				ArrayList<JzfsHVO> list_mod = new ArrayList<JzfsHVO>();
				// 根据source 去循环判断，是新增 还是更新。
				for (Entry<String, JzfsHVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					JzfsHVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						JzfsHVO vo_des = MAP_des.get(code);
						if (vo_source.getVdef5().compareTo(vo_des.getVdef5()) > 0) {
							// 如果 来源的时间戳 大于 目的的时间戳，则 更新
							// 以下是 需要更新的字段
							JzfsHVO vo_mod = (JzfsHVO)vo_des.clone();
							vo_mod.setVdef5(vo_source.getVdef5());
							vo_mod.setName(vo_source.getName());
							list_mod.add(vo_mod);
						}
					} else {
						// 新增
						JzfsHVO vo_add = new JzfsHVO();
						vo_add.setCode(code);
						vo_add.setName(vo_source.getName());
						vo_add.setVdef5(vo_source.getVdef5());
						vo_add.setPk_org(pk_org);
						vo_add.setPk_org_v(pk_org_v);
						vo_add.setPk_group(pk_group);
						vo_add.setAttributeValue("dr", 0);
						vo_add.setLevelno(1);
						list_add.add(vo_add);
					}
				}
				if (list_add.size() > 0) {
					String[] res = dao.insertVOList(list_add);
				}
				if (list_mod.size() > 0) {
					dao.updateVOList(list_mod);
				}
			}
		}
		return null;
	}
	
	/**
	 *  商品分类（公司）
	 *  vdef4 时间戳
	 */
	public Object tongbu_spfl(HashMap<String, Object> param, Object other) throws BusinessException {
		
//		String[] pk_org_list = new String[]{
//			"0001N510000000001SY3", // 朗丽兹
//			"0001N510000000001SY7", // 西山温泉
//			"0001N510000000001SY5", // 康西
//		};
		
		String[] pk_org_list = (String[])param.get("pk_org");
		
		String pk_group = "0001N510000000000EGY";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
		
		// 循环公司
		for (String pk_org : pk_org_list) {
			// 读取配置表
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
			Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));

			StringBuffer querySQL = 
			new StringBuffer("")
				// 账项代码
				.append(" SELECT ")
				.append(" 'LY01' as pk_parent, ")
				.append(" CONCAT('LY01-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" FROM  code_transaction ")
				.append(" where code not like '9%' ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // 非停用
			.append(" union all ")
				// 报表数据项
				.append(" SELECT ")
				.append(" 'LY02' as pk_parent, ")
				.append(" CONCAT('LY02-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" FROM code_base ")
				.append(" WHERE parent_code='pos_rep_item' ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // 非停用
			.append(" union all ")
				// 房间类型
				.append(" SELECT ")
				.append(" 'LY03' as pk_parent, ")
				.append(" CONCAT('LY03-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" FROM room_type ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // 非停用
			.append(" union all ")
				// 餐饮桌台
				.append(" SELECT ")
				.append(" 'LY04' as pk_parent, ")
				.append(" CONCAT('LY04-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" from pos_pccode_table ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // 非停用
			.append(" union all ")
				// 营业点
				.append(" SELECT ")
				.append(" 'LY05' as pk_parent, ")
				.append(" CONCAT('LY05-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" from pos_pccode ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // 非停用
			.append(" union all ")
				// 楼层
				.append(" SELECT ")
				.append(" 'LY06' as pk_parent, ")
				.append(" CONCAT('LY06-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" from room_floor ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // 非停用
			;
			
			ArrayList<SpflHVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {
				list_source = (ArrayList)session.executeQuery(querySQL.toString(), new BeanListProcessor(SpflHVO.class));					
			} catch (Exception ex) {
				System.out.println(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// 取数之后，放到 缓存。(编码为准)
				HashMap<String, SpflHVO> MAP_source = new HashMap<>();
				for (SpflHVO vo : list_source) {
					MAP_source.put(vo.getCode(), vo);
				}
				// 取NC的档案
				String whereSQL = " dr = 0 and code like 'LY0%' and pk_org = '" + pk_org + "' ";
				ArrayList<SpflHVO> list_des = (ArrayList<SpflHVO>)dao.retrieveByClause(SpflHVO.class, whereSQL);
				HashMap<String, SpflHVO> MAP_des = new HashMap<>();
				if (list_des != null && list_des.size() > 0) {
					for (SpflHVO vo : list_des) {
						MAP_des.put(vo.getCode(), vo);
					}
				}
				// 新增的集合
				ArrayList<SpflHVO> list_add = new ArrayList<SpflHVO>();
				// 修改的集合
				ArrayList<SpflHVO> list_mod = new ArrayList<SpflHVO>();
				// 根据source 去循环判断，是新增 还是更新。
				for (Entry<String, SpflHVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					SpflHVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						SpflHVO vo_des = MAP_des.get(code);
						if (vo_source.getVdef4().compareTo(vo_des.getVdef4()) > 0) {
							// 如果 来源的时间戳 大于 目的的时间戳，则 更新
							// 以下是 需要更新的字段
							SpflHVO vo_mod = (SpflHVO)vo_des.clone();
							vo_mod.setVdef4(vo_source.getVdef4());
							vo_mod.setName(vo_source.getName());
							list_mod.add(vo_mod);
						}
					} else {
						// 新增
						SpflHVO vo_add = new SpflHVO();
						vo_add.setCode(code);
						vo_add.setName(vo_source.getName());
						vo_add.setVdef4(vo_source.getVdef4());
						vo_add.setPk_org(pk_org);
						vo_add.setPk_org_v(pk_org_v);
						vo_add.setPk_group(pk_group);
						vo_add.setAttributeValue("dr", 0);
//						if (MAP_des.get(vo_source.getPk_parent()) == null) {
//							System.out.println("======");
//						}
						vo_add.setPk_parent(MAP_des.get(vo_source.getPk_parent()).getPk_hk_srgk_hg_spfl());
						list_add.add(vo_add);
					}
				}
				if (list_add.size() > 0) {
					String[] res = dao.insertVOList(list_add);
				}
				if (list_mod.size() > 0) {
					dao.updateVOList(list_mod);
				}
			}
		}
		return null;
	}
	
	/**
	 * 市场档案（集团）
	 * 取 非连锁的库即可 0001N510000000001SY3
	 * 放在 自定义档案 200
	 * def1 时间戳
	 * market_code
	 */
	public Object tongbu_shichang(HashMap<String, Object> param, Object other) throws BusinessException {
		param.put("ncTypeCode", "200");
		param.put("lyTypeCode", "market_code");
		return tongbu_DefDoc(param, other);
	}

	/**
	 * 来源档案（集团）
	 * 取 非连锁的库即可 0001N510000000001SY3
	 * 放在 自定义档案 201
	 * src_code
	 */
	public Object tongbu_laiyuan(HashMap<String, Object> param, Object other) throws BusinessException {
		param.put("ncTypeCode", "201");
		param.put("lyTypeCode", "src_code");
		return tongbu_DefDoc(param, other);
	}
	
	/**
	 * 渠道档案（集团）
	 * 取 非连锁的库即可 0001N510000000001SY3
	 * 放在 自定义档案 202
	 * channel
	 */
	public Object tongbu_qudao(HashMap<String, Object> param, Object other) throws BusinessException {
		param.put("ncTypeCode", "202");
		param.put("lyTypeCode", "channel");
		return tongbu_DefDoc(param, other);
	}
	
	/**
	 * 自定义档案，统一处理方法
	 */
	private Object tongbu_DefDoc(HashMap<String, Object> param, Object other) throws BusinessException {
		
		String ncTypeCode = PuPubVO.getString_TrimZeroLenAsNull(param.get("ncTypeCode"));
		String lyTypeCode = PuPubVO.getString_TrimZeroLenAsNull(param.get("lyTypeCode"));
		
		String[] pk_org_list = new String[]{
				"0001N510000000001SY3" // 朗丽兹
			};
			
		String pk_group = "0001N510000000000EGY";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
		
		for (String pk_org : pk_org_list) {
			// 读取配置表
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
//				String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));
			
			StringBuffer querySQL = 
			new StringBuffer(" SELECT ")
					.append(" code, ")
					.append(" descript as name, ")
					.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') def1 ")
					.append(" from code_base ")
					.append(" where ")
					.append(" parent_code = '").append(lyTypeCode).append("' ")
					.append(" and hotel_id = 0 ")	// 集团
					.append(" and is_halt = 'F' ") 	// 非停用
					.append(" order by list_order ")
			;
			
			ArrayList<DefdocVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {	
				list_source = (ArrayList<DefdocVO>)session.executeQuery(querySQL.toString(), new BeanListProcessor(DefdocVO.class));					
			} catch (Exception ex) {
				System.out.println(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// 取数之后，放到 缓存。(编码为准)
				HashMap<String, DefdocVO> MAP_source = new HashMap<>();
				for (DefdocVO vo : list_source) {
					MAP_source.put(vo.getCode(), vo);
				}
				// 取NC的档案
				// 先取 分类，后取 档案
				HashMap<String, DefdocVO> MAP_des = new HashMap<>();
				ArrayList<DefdoclistVO> defdoclistVO = (ArrayList<DefdoclistVO>)dao.retrieveByClause(DefdoclistVO.class, " dr = 0 and code = '" + ncTypeCode + "' ");
				String pk_defdoclist = null;
				if (defdoclistVO != null && defdoclistVO.size() > 0) {
					pk_defdoclist = defdoclistVO.get(0).getPk_defdoclist();
					String whereSQL = " dr = 0 and pk_defdoclist = '" + pk_defdoclist + "' ";
					ArrayList<DefdocVO> list_des = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
					if (list_des != null && list_des.size() > 0) {
						for (DefdocVO vo : list_des) {
							MAP_des.put(vo.getCode(), vo);
						}
					}
				}
				// 新增的集合
				ArrayList<DefdocVO> list_add = new ArrayList<DefdocVO>();
				// 修改的集合
				ArrayList<DefdocVO> list_mod = new ArrayList<DefdocVO>();
				// 根据source 去循环判断，是新增 还是更新。
				for (Entry<String, DefdocVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					DefdocVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						DefdocVO vo_des = MAP_des.get(code);
						if (vo_source.getDef1().compareTo(vo_des.getDef1()) > 0) {
							// 如果 来源的时间戳 大于 目的的时间戳，则 更新
							// 以下是 需要更新的字段
							DefdocVO vo_mod = (DefdocVO)vo_des.clone();
							vo_mod.setDef1(vo_source.getDef1());
							vo_mod.setName(vo_source.getName());
							list_mod.add(vo_mod);
						}
					} else {
						// 新增
						DefdocVO vo_add = new DefdocVO();
						vo_add.setCode(code);
						vo_add.setName(vo_source.getName());
						vo_add.setDef1(vo_source.getDef1());
						vo_add.setPk_org(pk_group);	// 集团级档案
						vo_add.setPk_group(pk_group);
						vo_add.setPk_defdoclist(pk_defdoclist);
						vo_add.setDatatype(1);
						vo_add.setEnablestate(2);
						vo_add.setAttributeValue("dr", 0);
						list_add.add(vo_add);
					}
				}
				if (list_add.size() > 0) {
					String[] res = dao.insertVOList(list_add);
				}
				if (list_mod.size() > 0) {
					dao.updateVOList(list_mod);
				}
			}
		}
		
		return null;
	}
}
