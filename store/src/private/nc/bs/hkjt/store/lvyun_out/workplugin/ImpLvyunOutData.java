package nc.bs.hkjt.store.lvyun_out.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IHk_store_lvyun_outMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.hkjt.store.lvyun.out.LvyunOutStroeData;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreCVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * 导入绿云出库数据
 */
public class ImpLvyunOutData implements IBackgroundWorkPlugin {

	private static IHk_store_lvyun_outMaintain ITF = 
			NCLocator.getInstance().lookup(IHk_store_lvyun_outMaintain.class);
	
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
		// 组织
		String[] pk_orgs = context.getPk_orgs();
		
		/**
		 * 加锁
		 */
		String Plugin_Key = "Plugin_lvyun_store";
		String[] lock_key = new String[pk_orgs.length];
		for (int i = 0; i < lock_key.length; i++) {
			lock_key[i] = Plugin_Key + "-" + pk_orgs[i];
		}
		boolean lock = PKLock.getInstance().addBatchDynamicLock(lock_key);
		if (!lock) {
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		/***END***/
		
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
		
		this.import_bill(param, null);	// 出库明细
		
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
			"2020-07-26",
		};
		
		param.put("pk_org", pk_org_list);
		param.put("date", date_list);
		
		this.import_bill(param, null);	// 出库明细
		return null;
	}
	
	/**
	 * 导入出库单据
	 * 菜品是  NC物料档案的自定义项 存 plu_code
	 * 售品是  中间表里 再提供 NC的物料编码
	 */
	public Object import_bill(HashMap<String, Object> param, Object other) throws BusinessException {
		
		String[] pk_org_list = (String[])param.get("pk_org");
		String[] date_list = (String[])param.get("date");
		
		String pk_group = "0001N510000000000EGY";
		String billType = "HK46";	// 出库明细
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//设置制单人
		
		// 先循环公司，再循环天
		for (String pk_org : pk_org_list) {
			// 读取配置表
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
			Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));
			
			// 取到公司级的档案
			// 商品分类-营业点（通过编码 去对应 LY05 分类下的）
			HashMap<String, SpflHVO> DOC_SPFL_YINGYEDIAN = new HashMap<String, SpflHVO>();
			{
				String whereSQL = " dr = 0 and code like 'LY05-%' and pk_org = '" + pk_org + "' ";
				ArrayList<SpflHVO> list = (ArrayList<SpflHVO>)dao.retrieveByClause(SpflHVO.class, whereSQL);
				if (list != null && list.size() > 0) {
					for (SpflHVO vo : list) {
						String code = vo.getCode();
						String name = vo.getName();
						
						if (code.startsWith("LY05-")) {
							DOC_SPFL_YINGYEDIAN.put(name, vo);
						}
					}
				}
			}
			
			for (String date : date_list) {
				// 先判断 该组织该日期下，是否存在 单据， 如果存在 则不处理。
				String sql2 = "select pk_hk_store_lvyun_out " +
						" from hk_store_lvyun_out " +
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
				 * select 
					 pccode,pccode_des,store_code,store_name
					,plu_code,plu_name,number
					,art_code,art_name,art_number
					from acint_saledetail_history
					where hotel_id = 18
					and biz_date = '2020-07-26'
					order by id
				 */
				StringBuffer querySQL_bill = 
				new StringBuffer("")
					// 出库明细
					.append(" select ")
					.append(" pccode ")
					.append(",pccode_des ")
					.append(",store_code ")
					.append(",store_name ")
					.append(",plu_code ")
					.append(",plu_name ")
					.append(",number ")
					.append(",art_code ")
					.append(",art_name ")
					.append(",art_number ")
					.append(" from acint_saledetail_history ")
					.append(" where (1=1) ")
					.append(" and hotel_id = ").append(hotel_id).append(" ")
					.append(" and biz_date = '").append(date).append("' ")
				;
				ArrayList<LvyunOutStroeData> list = null;
				
				Connection hkjt_jd_conn= null;
				JdbcSession session = null;
				hkjt_jd_conn = new JDBCUtils(db_name + "_bill").getConn(JDBCUtils.HKJT_LY);
				session = new JdbcSession(hkjt_jd_conn);
				
				try {	
					list = (ArrayList)session.executeQuery(querySQL_bill.toString(), new BeanListProcessor(LvyunOutStroeData.class));					
				} catch (Exception ex) {
					System.out.println(ex);
				} finally{
					session.closeAll();
					JDBCUtils.closeConn(hkjt_jd_conn);
				}
				
				if (list != null && list.size() > 0) {
					// 表头vo
					LyOutStoreHVO hVO = new LyOutStoreHVO();
					hVO.setPk_org(pk_org);
					hVO.setPk_org_v(pk_org_v);
					hVO.setPk_group(pk_group);
					hVO.setIbillstatus(-1);
					hVO.setVbilltypecode(billType);
					hVO.setDbilldate(PuPubVO.getUFDate(date));
					
					ArrayList<LyOutStoreBVO> bVO_list = new ArrayList<LyOutStoreBVO>(); // 售品vo
					ArrayList<LyOutStoreCVO> cVO_list = new ArrayList<LyOutStoreCVO>(); // 菜品vo
					
					// 汇总出，菜品、售品的编码。
					ArrayList<String> cp_code_list = new ArrayList<String>();
					ArrayList<String> sp_code_list = new ArrayList<String>();
					
					for (int i = 0; i < list.size(); i++) {
						LvyunOutStroeData data = list.get(i);
						SpflHVO yyd = DOC_SPFL_YINGYEDIAN.get(data.getPccode_des());
						String bm = yyd == null ? null : yyd.getPk_dept();	// 营业点-部门
						String ck = yyd == null ? null : yyd.getVdef1();	// 营业点-仓库
						if (PuPubVO.getString_TrimZeroLenAsNull(data.getArt_code()) == null) {
							// 菜品
							LyOutStoreCVO cVO = new LyOutStoreCVO();
							cVO.setLy_cp_code(data.getPlu_code());
							cVO.setLy_cp_name(data.getPlu_name());
							cVO.setCp_out_quantity(data.getNumber());
							cVO.setYyd_code(data.getPccode());
							cVO.setYyd_name(data.getPccode_des());
							cVO.setPk_dept(bm);
							cVO.setPk_store(ck);
							cVO_list.add(cVO);
							cVO.setVrowno("" + (cVO_list.size()*10));
							if (!cp_code_list.contains(data.getPlu_code())) {
								cp_code_list.add(data.getPlu_code());
							}
						} else {
							// 售品
							LyOutStoreBVO bVO = new LyOutStoreBVO();
							bVO.setLy_sp_code(data.getArt_code());
							bVO.setLy_sp_name(data.getArt_name());
							bVO.setSp_out_quantity(data.getArt_number());
							bVO.setYyd_code(data.getPccode());
							bVO.setYyd_name(data.getPccode_des());
							bVO.setPk_dept(bm);
							bVO.setPk_store(ck);
							bVO_list.add(bVO);
							bVO.setVrowno("" + (bVO_list.size()*10));
							if (!sp_code_list.contains(data.getArt_code())) {
								sp_code_list.add(data.getArt_code());
							}
						}
					}
					
					/**
					 *  物料的查询处理
					 *  自定义01 存储 pk_source
					 *  自定义02 存储 计量单位 pk_measdoc
					 *  自定义03 存储 成本分类 def2
					 */
					
					if (sp_code_list.size() > 0) {
						// 售品
						/**
						 * select def8,pk_material 
							  from bd_material
							 where 11 = 11
							   and (enablestate = 2)
							   and def8 in ('402017','1001017')
							   and (((pk_material in
							       (select pk_material
							             from bd_marorg
							            where pk_org in ('0001N5100000000UVI5I')
							              and enablestate = 2
							           union
							           select pk_material
							             from bd_material
							            where (pk_org = '0001N510000000000EGY' or
							                  pk_org = '0001N5100000000UVI5I')))) and latest = 'Y')
						 */
						String def8WhereSql = PuPubVO.getSqlInByList(sp_code_list);
						StringBuffer querySql = 
							new StringBuffer("select ")
									.append(" bd_material.def8 ")
									.append(",bd_material.pk_material ")	// 1 vid
									.append(",bd_material.pk_source ")		// 2 id
									.append(",bd_material.pk_measdoc ")		// 3 计量单位id
									.append(",cklb.pk_defdoc ")				// 4 根据成本分类 去找 出库类别
									.append(" from bd_material ")
									.append(" left join bd_defdoc cbfl on (cbfl.pk_defdoclist = '1001N5100000000020T0' and bd_material.def2 = cbfl.pk_defdoc) ") // 物料：成本分类
									.append(" left join bd_defdoc cklb on (cklb.pk_defdoclist = '1001N51000000002J29F' and cklb.dr = 0 and cbfl.name = cklb.name) ") // 材料出库：出库类别
									.append(" where 11 = 11 ")
									.append(" and (bd_material.enablestate = 2) ")
									.append(" and bd_material.def8 in ").append(def8WhereSql).append(" ")
									.append(" and (((bd_material.pk_material in ")
									.append("	(select pk_material from bd_marorg ")
									.append("		where pk_org in ('").append(pk_org).append("') ")
									.append("		and enablestate = 2 ")
									.append("	union ")
									.append("	select pk_material from bd_material ")
									.append("		where (pk_org = '").append(pk_group).append("' or ")
									.append("		pk_org = '").append(pk_org).append("')))) and latest = 'Y') ")
						;
						ArrayList list3 = (ArrayList)dao.executeQuery(querySql.toString(), new ArrayListProcessor());
						HashMap<String, String[]> map = new HashMap<String, String[]>();
						if (list3 != null && !list3.isEmpty()) {
							// 先组合map
							for (int i = 0; i < list3.size(); i++) {
								Object[] obj = (Object[])list3.get(i);
								map.put(PuPubVO.getString_TrimZeroLenAsNull(obj[0])
									, new String[]{
									PuPubVO.getString_TrimZeroLenAsNull(obj[1]),
									PuPubVO.getString_TrimZeroLenAsNull(obj[2]),
									PuPubVO.getString_TrimZeroLenAsNull(obj[3]),
									PuPubVO.getString_TrimZeroLenAsNull(obj[4])
								});
							}
							// 再循环售品赋值
							for (LyOutStoreBVO bVO : bVO_list) {
								String[] map_value = map.get(bVO.getLy_sp_code());
								if (map_value != null && map_value.length == 4) {
									bVO.setPk_inv(PuPubVO.getString_TrimZeroLenAsNull(map_value[0]));
									bVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(map_value[1]));
									bVO.setVbdef02(PuPubVO.getString_TrimZeroLenAsNull(map_value[2]));
									bVO.setVbdef03(PuPubVO.getString_TrimZeroLenAsNull(map_value[3]));
								}
							}
						}
					}
					if (cp_code_list.size() > 0) {
						// 菜品
						/**
						 * select def8,pk_material,bom.cbomid
							  from bd_material
							  left join (
							        select hcmaterialid,max(bd_bom.cbomid) cbomid
							         from bd_bom
							         inner join bd_bom_useorg on bd_bom.cbomid = bd_bom_useorg.cbomid
							          where (1=1)
							          and bd_bom.dr = 0 and bd_bom_useorg.dr = 0
							          and hbdefault = 'Y'
							          and fbillstatus = 1
							          and pk_useorg in ('0001N5100000000UVI5I')
							          group by hcmaterialid
							  )bom on bd_material.pk_material = bom.hcmaterialid
							 where 11 = 11
							   and (enablestate = 2)
							   and def8 in ('402017','1001017')
							   and (((pk_material in
							       (select pk_material
							             from bd_marorg
							            where pk_org in ('0001N5100000000UVI5I')
							              and enablestate = 2
							           union
							           select pk_material
							             from bd_material
							            where (pk_org = '0001N510000000000EGY' or
							                  pk_org = '0001N5100000000UVI5I')))) and latest = 'Y')
						 */
						String def8WhereSql = PuPubVO.getSqlInByList(cp_code_list);
						StringBuffer querySql = 
							new StringBuffer("select ")
									.append(" def8 ")
									.append(",pk_material ")	// 1 vid
									.append(",pk_source ")		// 2 id
									.append(",pk_measdoc ")		// 3 计量单位id
									.append(",null ")			// 4 成本类别
									.append(",bom.cbomid ")		// 5 bom
									.append(" from bd_material ")
									.append(" left join ( ")
									.append("	select hcmaterialid,max(bd_bom.cbomid) cbomid ")
									.append("	from bd_bom ")
									.append("	inner join bd_bom_useorg on bd_bom.cbomid = bd_bom_useorg.cbomid ")
									.append("	where (1=1) ")
									.append("	and bd_bom.dr = 0 and bd_bom_useorg.dr = 0 ")
									.append("	and hbdefault = 'Y' ")
									.append("	and fbillstatus = 1 ")
									.append("	and pk_useorg = '").append(pk_org).append("' ")
									.append("	group by hcmaterialid ")
									.append(" ) bom on bd_material.pk_material = bom.hcmaterialid")
									.append(" where 11 = 11 ")
									.append(" and (enablestate = 2) ")
									.append(" and def8 in ").append(def8WhereSql).append(" ")
									.append(" and (((pk_material in ")
									.append("	(select pk_material from bd_marorg ")
									.append("		where pk_org in ('").append(pk_org).append("') ")
									.append("		and enablestate = 2 ")
									.append("	union ")
									.append("	select pk_material from bd_material ")
									.append("		where (pk_org = '").append(pk_group).append("' or ")
									.append("		pk_org = '").append(pk_org).append("')))) and latest = 'Y') ")
						;
						ArrayList list3 = (ArrayList)dao.executeQuery(querySql.toString(), new ArrayListProcessor());
						HashMap<String, String[]> map = new HashMap<String, String[]>();
						if (list3 != null && !list3.isEmpty()) {
							// 先组合map
							for (int i = 0; i < list3.size(); i++) {
								Object[] obj = (Object[])list3.get(i);
								map.put(PuPubVO.getString_TrimZeroLenAsNull(obj[0])
									, new String[]{
										PuPubVO.getString_TrimZeroLenAsNull(obj[1])
									   ,PuPubVO.getString_TrimZeroLenAsNull(obj[2])
									   ,PuPubVO.getString_TrimZeroLenAsNull(obj[3])
									   ,PuPubVO.getString_TrimZeroLenAsNull(obj[4])
									   ,PuPubVO.getString_TrimZeroLenAsNull(obj[5])
								});
							}
							// 再循环售品赋值
							for (LyOutStoreCVO cVO : cVO_list) {
								String[] map_value = map.get(cVO.getLy_cp_code());
								if (map_value != null && map_value.length == 5) {
									cVO.setPk_inv(PuPubVO.getString_TrimZeroLenAsNull(map_value[0]));
									cVO.setVbdef01(PuPubVO.getString_TrimZeroLenAsNull(map_value[1]));
									cVO.setVbdef02(PuPubVO.getString_TrimZeroLenAsNull(map_value[2]));
									cVO.setVbdef03(PuPubVO.getString_TrimZeroLenAsNull(map_value[3]));
									cVO.setPk_bom(PuPubVO.getString_TrimZeroLenAsNull(map_value[4]));
								}
							}
						}
					}
					
					LyOutStoreBillVO billVO = new LyOutStoreBillVO();
					billVO.setParentVO(hVO);
					billVO.setChildren(new LyOutStoreBVO().getMetaData(), bVO_list.toArray(new LyOutStoreBVO[0]));
					billVO.setChildren(new LyOutStoreCVO().getMetaData(), cVO_list.toArray(new LyOutStoreCVO[0]));
					
					LyOutStoreBillVO[] res = ITF.insert(new LyOutStoreBillVO[]{billVO}, null);
					System.out.println(res);
				}
			}
		}
		return null;
	}
}
