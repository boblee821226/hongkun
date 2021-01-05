package nc.bs.hkjt.arap.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.hkjt.IHk_arap_billMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.arap.account.AccountHVO;
import nc.vo.hkjt.arap.bill.BillBVO;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.vo.hkjt.arap.bill.BillCVO;
import nc.vo.hkjt.arap.bill.BillDVO;
import nc.vo.hkjt.arap.bill.BillHVO;
import nc.vo.hkjt.arap.unit.UnitHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
/**
 * 导入绿云应收账务数据
 */
public class ImpLyArapData implements IBackgroundWorkPlugin {

	private static String PK_GROUP = "0001N510000000000EGY";
	private static String PK_USER = "NC_USER0000000000000";
	private static String PK_ORG = "0001N51000000001F7R3";	// HKJT
	private static String PK_ORG_V = "0001N51000000001F7R2";
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		// 组织
		String[] pk_orgs = context.getPk_orgs();
		// 同步 协议单位
		UFBoolean isUnit = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isUnit"),UFBoolean.FALSE);
		// 同步 应收账户
		UFBoolean isAccount = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isAccount"),UFBoolean.FALSE);
		// 同步 账务数据
		UFBoolean isBill = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isBill"),UFBoolean.FALSE);
				
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
		
		if (isUnit.booleanValue()) {
			this.tongbu_unit(param, null);		// 协议单位
		}
		if (isAccount.booleanValue()) {
			this.tongbu_account(param, null);	// 应收账户
		}
		if (isBill.booleanValue()) {
			this.import_bill(param, null);		// 账务数据
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
				"0001N510000000001SY3", // 朗丽兹 9
//				"0001N510000000001SY5", // 康西 11
//				"0001N510000000001SY7", // 西山温泉 10
//				"0001N510000000001SY1", // 学院路16
//				"0001N5100000000UVI5I"	// 太申18
		};
		String[] date_list = new String[]{
			"2020-12-31",
		};
		
		param.put("pk_org", pk_org_list);
		param.put("date", date_list);
		
//		this.tongbu_unit(param, null);	// 协议单位
//		this.tongbu_account(param, null);	// 应收账户
		this.import_bill(param, null);	// 账务数据
		return null;
	}
	
	/**
	 * 账务数据
	 */
	public Object import_bill(HashMap<String, Object> param, Object other) throws BusinessException {
		
		String[] pk_org_list = (String[])param.get("pk_org");
		String[] date_list = (String[])param.get("date");
		
		String billType = "HK53";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(PK_USER);//设置制单人
		IHk_arap_billMaintain itf = NCLocator.getInstance().lookup(IHk_arap_billMaintain.class);

		// 先循环公司，再循环天
		for (String pk_org : pk_org_list) {
			// 读取配置表
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
			Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String short_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("short_name"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));

			for (String date : date_list) {
				// 先判断 该组织该日期下，是否存在 单据， 如果存在 则不处理。
				String sql2 = "select pk_hk_arap_bill " +
						" from hk_arap_bill " +
						" where dr = 0 " +
						" and pk_org = '" + pk_org + "' " +
						" and substr(dbilldate, 1, 10) = '" + date + "' "
						;
				ArrayList<String> list2 = (ArrayList<String>)dao.executeQuery(sql2, new ColumnListProcessor());
				if (list2 != null && list2.size() > 0) {
					continue;
				}
				/**
				 * select 
					hotel_id, id, ta_code, gen_date, 
					ta_descript, subaccnt, rmno, guest_name,
					act_tag, charge, charge9,
					pay, pay9, balance9,
					ta_no, ta_remark,
					trans_accnt, create_user,create_datetime,
					biz_date, close_id,
					audit_tag, accnt, number, modu_code
					from ar_account_nc_view
					where biz_date = '2020-12-31'
					and hotel_id = 9
				 */
				StringBuffer querySQL_b = 
					new StringBuffer("select ")
							.append(" id ")
							.append(",ta_code ")
							.append(",DATE_FORMAT(gen_date, '%Y-%m-%d %H:%i:%s') gen_date ")
							.append(",ta_descript ")
							.append(",subaccnt ")
							.append(",rmno ")
							.append(",guest_name ")
							.append(",act_tag ")
							.append(",charge ")
							.append(",charge9 ")
							.append(",pay ")
							.append(",pay9 ")
							.append(",balance9 ")
							.append(",ta_no ")
							.append(",ta_remark ")
							.append(",trans_accnt ")
							.append(",create_user ")
							.append(",DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s') create_datetime ")
							.append(",close_id ")
							.append(",audit_tag ")
							.append(",accnt ")
							.append(",number number_ly ")
							.append(",modu_code ")
							.append(" from ar_account_nc_view ")
							.append(" where (1=1) ")
							.append(" and hotel_id = ").append(hotel_id).append(" ")
							.append(" and biz_date = '").append(date).append("' ")
				;
				/**
				 * select id, accnt, charge, pay, create_user, create_datetime
					from ar_apply_nc_view
					where biz_date = '2020-12-31'
					and hotel_id = 9
				 */
				StringBuffer querySQL_c = 
						new StringBuffer("select ")
							.append(" id ")
							.append(",accnt ")
							.append(",charge ")
							.append(",pay ")
							.append(",create_user ")
							.append(",create_datetime ")
							.append(" from ar_apply_nc_view ")
							.append(" where (1=1) ")
							.append(" and hotel_id = ").append(hotel_id).append(" ")
							.append(" and biz_date = '").append(date).append("' ")
				;
				/**
				 * select b.close_id, b.biz_date, b.ta_code, 
				 * b.ta_descript, b.rmno, b.guest_name, b.ta_remark, 
				 * b.charge, b.pay, b.arAccountId
					from ar_apply_detail_nc_view b
					inner join ar_apply_nc_view h on b.close_id = h.id
					where h.biz_date = '2020-12-31'
					and h.hotel_id = 9
					DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s')
				 */
				StringBuffer querySQL_d = 
						new StringBuffer("select ")
							.append(" b.close_id ")
							.append(",DATE_FORMAT(b.biz_date, '%Y-%m-%d') biz_date ")
							.append(",b.ta_code ")
							.append(",b.ta_descript ")
							.append(",b.rmno ")
							.append(",b.guest_name ")
							.append(",b.ta_remark ")
							.append(",b.charge ")
							.append(",b.pay ")
							.append(",b.arAccountId ar_account_id ")
							.append(" from ar_apply_detail_nc_view b ")
							.append(" inner join ar_apply_nc_view h on b.close_id = h.id ")
							.append(" where (1=1) ")
							.append(" and h.hotel_id = ").append(hotel_id).append(" ")
							.append(" and h.biz_date = '").append(date).append("' ")
				;
				
				ArrayList<BillBVO> list_b = null;
				ArrayList<BillCVO> list_c = null;
				ArrayList<BillDVO> list_d = null;
				
				Connection hkjt_jd_conn= null;
				JdbcSession session = null;
				hkjt_jd_conn = new JDBCUtils(db_name + "_bill").getConn(JDBCUtils.HKJT_LY);
				session = new JdbcSession(hkjt_jd_conn);
				
				try {	
					list_b = (ArrayList)session.executeQuery(querySQL_b.toString(), new BeanListProcessor(BillBVO.class));
					list_c = (ArrayList)session.executeQuery(querySQL_c.toString(), new BeanListProcessor(BillCVO.class));
					list_d = (ArrayList)session.executeQuery(querySQL_d.toString(), new BeanListProcessor(BillDVO.class));
				} catch (Exception ex) {
					throw new BusinessException(ex);
				} finally{
					session.closeAll();
					JDBCUtils.closeConn(hkjt_jd_conn);
				}
				// 封装billVO
				if (list_b != null && !list_b.isEmpty()) {
					BillBillVO billVO = new BillBillVO();
					BillHVO hVO = new BillHVO();
					hVO.setVbilltypecode(billType);
					hVO.setVbillcode(short_name + "#" + date);
					hVO.setDbilldate(new UFDate(date));
					hVO.setIbillstatus(-1);
					hVO.setCreator(PK_USER);
					hVO.setPk_group(PK_GROUP);
					hVO.setPk_org(pk_org);
					hVO.setPk_org_v(pk_org_v);
					hVO.setDirty(false);
					
					billVO.setParentVO(hVO);
					billVO.setChildren(new BillBVO().getMetaData(), list_b.toArray(new BillBVO[0]));
					billVO.setChildren(new BillCVO().getMetaData(), list_c.toArray(new BillCVO[0]));
					billVO.setChildren(new BillDVO().getMetaData(), list_d.toArray(new BillDVO[0]));
					
					BillBillVO[] res = itf.insert(new BillBillVO[]{billVO}, null);
				}
			}
		}
		return null;
	}
	
	/**
	 *  应收账户（公司）
	 */
	private Object tongbu_account(HashMap<String, Object> param, Object other) throws BusinessException {
				
		String[] pk_org_list = (String[])param.get("pk_org");
		
		String billType = "HK51";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(PK_USER);//设置制单人
		UFDateTime nowTime = new UFDateTime();
		
		// 循环公司
		for (String pk_org : pk_org_list) {
			// 读取配置表
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			Map<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
			Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));

			StringBuffer querySQL = 
			new StringBuffer("")
				/**
				 * select accnt, name, coding, create_user, create_datetime,
					modify_user, modify_datetime, profile_type, profile_id,
					sta, arr, dep, ar_cycle, ar_category, last_num, remark,
					group_ar_master_id
					from ar_master_info_nc_view
					where hotel_id = 9
					DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s')
				 */
				.append("select ")
				.append(" accnt acc_code ")	// AR账户
				.append(",name acc_name ")	// 账户名称
				.append(",coding vdef02 ")	// 账户全称
				.append(",create_user vdef03 ")	// 账户建立人
				.append(",DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s') dbilldate ")// 业务日期
				.append(",modify_user vdef04 ")		// 修改人
				.append(",DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef01 ")	// 修改时间
				.append(",profile_type vdef05 ")	// 档案类型
				.append(",profile_id vdef06 ")		// 档案ID
				.append(",sta acc_status ")		// 账户状态
				.append(",DATE_FORMAT(arr, '%Y-%m-%d %H:%i:%s') vdef07 ")			// 开始时间
				.append(",DATE_FORMAT(dep, '%Y-%m-%d %H:%i:%s') vdef08 ")			// 结束日期
				.append(",ar_cycle vdef09 ")	// 催账周期
				.append(",ar_category acc_type ")// 类别
				.append(",last_num vdef10 ")	// 账务笔数
				.append(",remark vmemo ")		// 备注
				.append(",group_ar_master_id vdef11 ")	// 集团级AR账号
				.append(" from ar_master_info_nc_view ")
				.append(" where hotel_id = ").append(hotel_id);
			;
			
			List<AccountHVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bill").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {
				list_source = (ArrayList)session.executeQuery(querySQL.toString(), new BeanListProcessor(AccountHVO.class));					
				System.out.println();
			} catch (Exception ex) {
				throw new BusinessException(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// 取数之后，放到 缓存。(账户id为准)
				Map<String, AccountHVO> MAP_source = new HashMap<>();
				for (AccountHVO vo : list_source) {
					MAP_source.put(vo.getAcc_code(), vo);
				}
				// 取NC的档案
				String whereSQL = " dr = 0 and pk_org = '" + pk_org + "' ";
				List<AccountHVO> list_des = (ArrayList)dao.retrieveByClause(AccountHVO.class, whereSQL);
				Map<String, AccountHVO> MAP_des = new HashMap<>();
				if (list_des != null && list_des.size() > 0) {
					for (AccountHVO vo : list_des) {
						MAP_des.put(vo.getAcc_code(), vo);
					}
				}
				// 新增的集合
				List<AccountHVO> list_add = new ArrayList<>();
				// 修改的集合
				List<AccountHVO> list_mod = new ArrayList<>();
				// 根据source 去循环判断，是新增 还是更新。
				for (Entry<String, AccountHVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					AccountHVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						AccountHVO vo_des = MAP_des.get(code);
						if (vo_source.getVdef01().compareTo(vo_des.getVdef01()) > 0) {
							// 如果 来源的时间戳 大于 目的的时间戳，则 更新
							// 以下是 需要更新的字段
							AccountHVO vo_mod = vo_des;
							vo_mod.setVdef01(vo_source.getVdef01());
							vo_mod.setAcc_name(vo_source.getAcc_name());
							vo_mod.setAcc_status(vo_source.getAcc_status());
							vo_mod.setAcc_type(vo_source.getAcc_type());
							vo_mod.setVmemo(vo_source.getVmemo());
							vo_mod.setVdef02(vo_source.getVdef02());
							vo_mod.setVdef04(vo_source.getVdef04());
							vo_mod.setVdef05(vo_source.getVdef05());
							vo_mod.setVdef06(vo_source.getVdef06());
							vo_mod.setVdef07(vo_source.getVdef07());
							vo_mod.setVdef08(vo_source.getVdef08());
							vo_mod.setVdef09(vo_source.getVdef09());
							vo_mod.setVdef10(vo_source.getVdef10());
							vo_mod.setVdef11(vo_source.getVdef11());
							list_mod.add(vo_mod);
						}
					} else {
						// 新增
						AccountHVO vo_add = vo_source;
						vo_add.setVbillcode(code);
						vo_add.setPk_org(pk_org);
						vo_add.setPk_org_v(pk_org_v);
						vo_add.setPk_group(PK_GROUP);
						vo_add.setIbillstatus(-1);
						vo_add.setCreator(PK_USER);
						vo_add.setCreationtime(nowTime);
						vo_add.setVbilltypecode(billType);
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

	/**
	 * 协议单位（HKJT）
	 */
	private Object tongbu_unit(HashMap<String, Object> param, Object other) throws BusinessException {
		String billType = "HK52";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(PK_USER);//设置制单人
		UFDateTime nowTime = new UFDateTime();
		
		// 只取非连锁库的数据
		{
			String db_name = "hkjt_ly_feiliansuo";
			StringBuffer querySQL = 
			new StringBuffer("")
				/**
				 * select id, name, modify_datetime
				 * ,create_datetime 
					from company_base_nc_view
				 */
				.append("select ")
				.append(" id unit_code ")	// 协议单位id
				.append(",name unit_name ")	// 协议单位名称
				.append(",DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef01 ")	// 修改时间
				.append(",DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s') dbilldate ")// 创建时间
				.append(" from company_base_nc_view ")
				.append(" where hotel_id = 0 ")	// 集团级档案
			;
			
			List<UnitHVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bill").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {
				list_source = (ArrayList)session.executeQuery(querySQL.toString(), new BeanListProcessor(UnitHVO.class));					
				System.out.println();
			} catch (Exception ex) {
				throw new BusinessException(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// 取数之后，放到 缓存。(账户id为准)
				Map<String, UnitHVO> MAP_source = new HashMap<>();
				for (UnitHVO vo : list_source) {
					MAP_source.put(vo.getUnit_code(), vo);
				}
				// 取NC的档案
				String whereSQL = " dr = 0 and pk_org = '" + PK_ORG + "' ";
				List<UnitHVO> list_des = (ArrayList)dao.retrieveByClause(UnitHVO.class, whereSQL);
				Map<String, UnitHVO> MAP_des = new HashMap<>();
				if (list_des != null && list_des.size() > 0) {
					for (UnitHVO vo : list_des) {
						MAP_des.put(vo.getUnit_code(), vo);
					}
				}
				// 新增的集合
				List<UnitHVO> list_add = new ArrayList<>();
				// 修改的集合
				List<UnitHVO> list_mod = new ArrayList<>();
				// 根据source 去循环判断，是新增 还是更新。
				for (Entry<String, UnitHVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					UnitHVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						UnitHVO vo_des = MAP_des.get(code);
						if (vo_source.getVdef01().compareTo(vo_des.getVdef01()) > 0) {
							// 如果 来源的时间戳 大于 目的的时间戳，则 更新
							// 以下是 需要更新的字段
							UnitHVO vo_mod = vo_des;
							vo_mod.setVdef01(vo_source.getVdef01());
							vo_mod.setUnit_name(vo_source.getUnit_name());
							list_mod.add(vo_mod);
						}
					} else {
						// 新增
						UnitHVO vo_add = vo_source;
						vo_add.setVbillcode(code);
						vo_add.setPk_org(PK_ORG);
						vo_add.setPk_org_v(PK_ORG_V);
						vo_add.setPk_group(PK_GROUP);
						vo_add.setIbillstatus(-1);
						vo_add.setCreator(PK_USER);
						vo_add.setCreationtime(nowTime);
						vo_add.setVbilltypecode(billType);
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

	/**
	 * 自定义档案，统一处理方法
	 */
	private Object tongbu_DefDoc(HashMap<String, Object> param, Object other) throws BusinessException {
		return null;
	}
}
