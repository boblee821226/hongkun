package nc.bs.hkjt.srgk.lvyun.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
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
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.bd.defdoc.DefdoclistVO;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
/**
 * 导入绿云数据
 */
public class ImpLvyunData implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		return null;
	}
	
	/**
	 * 用于代码测试
	 * 后台任务无法执行
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		HashMap<String, Object> param = new HashMap<String, Object>();
//		this.tongbu_jzfs(param, null);	// 结账方式
//		this.tongbu_spfl(param, null);	// 商品分类
//		this.tongbu_shichang(param, null);	// 市场
//		this.tongbu_laiyuan(param, null);	// 来源
//		this.tongbu_qudao(param, null);		// 渠道
		this.import_bill(param, null);	// 入账明细
		return null;
	}
	
	/**
	 * 业务单据
	 */
	public Object import_bill(HashMap<String, Object> param, Object other) throws BusinessException {
		String[] pk_org_list = new String[]{
				"0001N510000000001SY3" // 朗丽兹
			};
			String[] date_list = new String[]{
				"2020-03-08",
//				"2020-03-03"
			};
			String pk_group = "0001N510000000000EGY";
			String billType = "HK11";
			BaseDAO dao = new BaseDAO();
			InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
			
			// 取到 集团级档案
			// 结账方式
			HashMap<String, JzfsHVO> DOC_JZFS = new HashMap<String, JzfsHVO>();
			{
				String whereSQL = " dr = 0 and pk_org = '0001N510000000001SY3' ";
				ArrayList<JzfsHVO> list = (ArrayList<JzfsHVO>)dao.retrieveByClause(JzfsHVO.class, whereSQL);
				if (list != null && list.size() > 0) {
					for (JzfsHVO vo : list) {
						DOC_JZFS.put(vo.getCode(), vo);
					}
				}
			}
			// 市场(通过 名称去匹配)
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
				// 商品分类(通过 名称去匹配)
				HashMap<String, SpflHVO> DOC_SPFL = new HashMap<String, SpflHVO>();
				{
					String whereSQL = " dr = 0 and code like 'LY0%' and pk_org = '" + pk_org + "' ";
					ArrayList<SpflHVO> list = (ArrayList<SpflHVO>)dao.retrieveByClause(SpflHVO.class, whereSQL);
					if (list != null && list.size() > 0) {
						for (SpflHVO vo : list) {
							DOC_SPFL.put(vo.getName(), vo);
						}
					}
				}
				
				for (String date : date_list) {
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
						储值卡ID	card_id	ar_accnt	　【vbdef07】
						操作标识	act_flag	　	　【vbdef08】
						转移方向	trans_flag	car_posting	　【vbdef09】
						转移账户编号	trans_accnt	　	　【vbdef10】
						信息摘要	ta_remark	　	备注【vbmemo】
						收银员	create_user	close_user	收银员【syy_code】
					 */
					StringBuffer querySQL_bill = 
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
							.append(" card_id as vbdef07,")
							.append(" act_flag as vbdef08,")
							.append(" trans_flag as vbdef09,")
							.append(" trans_accnt as vbdef10,")
							.append(" ta_remark as vbmemo,")
							.append(" create_user as syy_code ")
							.append(" from account_pms ")
							.append(" where (1=1) ")
//							.append(" and (charge != 0 or pay != 0)")	// 消费金额 或 结账金额 至少有一个不为0
							.append(" and hotel_id = ").append(hotel_id).append(" ")
							.append(" and biz_date = '").append(date).append("' ")
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
							.append(" ar_accnt as vbdef07,")
							.append(" null as vbdef08,")
							.append(" cat_posting as vbdef09,")
							.append(" null as vbdef10,")
							.append(" null as vbmemo,")
							.append(" close_user as syy_code ")
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
						for (int i = 0; i < bVOs.length; i++) {
							RzmxBVO bVO = bVOs[i];
							bVO.setVrowno("" + ((i+1) * 10));
							charge = charge.add(bVO.getCharge());	// 累计消费
							pay = pay.add(bVO.getPayment());		// 累计结账
							/** 进行档案的翻译
							* 根据入账项目编码，去找 NC的相应的字段
							* 如果 结账金额 不为零，说明该行是 结账信息，需要 找 NC结账方式
							* 否则 ，说明该行是 消费信息，需要找 NC的商品分类，以及对应的收入项目 以及部门 
							*/
							String itemCode = bVO.getItem_code();	// 入账项目代码
							String itemName = bVO.getItem_name();	// 入账项目名称
							if (PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null) {
								// 结账 => NC结账方式
								JzfsHVO doc = DOC_JZFS.get(itemCode);
								if (doc != null) {
									bVO.setJzfs_id(doc.getPk_hk_srgk_hg_jzfs());
								}
							} else {
								// 消费 => NC商品分类 => NC收入项目 & NC部门 => NC上级部门
								SpflHVO doc = DOC_SPFL.get(itemName);
								if (doc != null) {
									bVO.setSpfl_id(doc.getPk_hk_srgk_hg_spfl());
									bVO.setSrxm_id(doc.getPk_hk_srgk_hg_srxm());
									bVO.setBm_id(doc.getPk_dept());
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
						}
						
						hVO.setXfje(charge);
						hVO.setJzje(pay);
						
						RzmxBillVO billVO = new RzmxBillVO();
						billVO.setParentVO(hVO);
						billVO.setChildrenVO(bVOs);
						
						RzmxBillVO[] res = itf.insert(new RzmxBillVO[]{billVO}, null);
						System.out.println(res);
					}
					// 读取中间表（档案表）
					
				}
			}
			return null;
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
		
		// 先循环公司，再循环天
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
						MAP_des.put(vo.getCode(), vo);
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
						if (vo_source.getVdef5().compareTo(vo_source.getVdef5()) > 0) {
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
		String[] pk_org_list = new String[]{
			 "0001N510000000001SY3" // 朗丽兹
//			,"0001N510000000001SY3" // 西山温泉
//			,"0001N510000000001SY5" // 康西
		};
		
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
						if (vo_source.getVdef5().compareTo(vo_source.getVdef5()) > 0) {
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
						if (vo_source.getDef1().compareTo(vo_source.getDef1()) > 0) {
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
