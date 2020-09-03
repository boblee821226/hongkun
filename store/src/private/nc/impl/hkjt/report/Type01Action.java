package nc.impl.hkjt.report;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pub.smart.context.SmartContext;
import nc.vo.hkjt.store.lvyun.out.report.Type00VO;
import nc.vo.hkjt.store.lvyun.out.report.Type01VO;
import nc.vo.pub.BusinessException;

/**
 * BOM标准成本
 */
public class Type01Action {
	
	public static String queryReportToSQL(
			 SmartContext context
			,HashMap<String,Object> param
			,String flag
			,Object other
	)throws BusinessException
	{
		BaseDAO dao = new BaseDAO();
		/**
		 * 1、判断公共库里  有无信息，
		 * 如果没有信息，则需要执行逻辑，然后给公共库赋值。
		 */
		StringBuffer querySQL_00 = 
				new StringBuffer("select * from hk_report_type00");
		ArrayList<Type00VO> list_00 = (ArrayList)dao.executeQuery(querySQL_00.toString(), new BeanListProcessor(Type00VO.class));
		if (list_00 == null || list_00.isEmpty()) {
			// 需要执行逻辑
			String v_pk_org = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_pk_org"));// 公司
			String v_pk_dept = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_pk_dept"));// 部门
			String v_begin_date = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_begin_date"));// 开始日期
			String v_end_date = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_end_date"));// 结束日期		// 期间
			
			/**
			 * 01的逻辑处理
			 */
			StringBuffer querySQL_01 = 
				new StringBuffer("select ")
						.append(" d.ly_cp_name as cp_name ")
						.append(",d.cp_out_quantity as sale_quantity ")
						.append(",d.pk_inv ")
						.append(",inv.code as inv_code ")
						.append(",inv.name as inv_name ")
						.append(",unit.name as inv_unit ")
						.append(" from hk_store_lvyun_out a ")
						.append(" inner join hk_store_lvyun_out_d d on a.pk_hk_store_lvyun_out = d.pk_hk_store_lvyun_out ")
						.append(" left join bd_material inv on d.pk_inv = inv.pk_material ")
						.append(" left join bd_measdoc unit on unit.pk_measdoc = inv.pk_measdoc ")
						.append(" where a.dr = 0 and d.dr = 0 ")
						.append(" and a.pk_org = '").append(v_pk_org).append("' ")
						.append(" and substr(a.dbilldate,1,10) between '").append(v_begin_date).append("' and '").append(v_end_date).append("' ")
						.append(" and d.pk_dept = '").append(v_pk_dept).append("' ")
			;
			ArrayList<Type01VO> list_01 = (ArrayList)dao.executeQuery(querySQL_01.toString(), new BeanListProcessor(Type01VO.class));
			for (Type01VO item : list_01) {
				item.setPk_group("0001N510000000000EGY");
				item.setPk_org(v_pk_org);
				item.setPk_dept(v_pk_dept);
				item.setDr(0);
			}
			dao.insertVOList(list_01);
			/***END***/
			
			// 放到公共库
			Type00VO type00VO = new Type00VO();
			type00VO.setPk_org(v_pk_org);
			type00VO.setPk_dept(v_pk_dept);
			type00VO.setBegin_date(v_begin_date);
			type00VO.setEnd_date(v_end_date);
			type00VO.setDr(0);
			dao.insertVO(type00VO);
			
		}
		/**
		 * 4、返回临时表的SQL
		 */
		StringBuffer resultSQL = 
			new StringBuffer(" select ")
					.append(" pk_group ")
					.append(",pk_org ")
					.append(",cp_name ")
					.append(",inv_code ")
					.append(",inv_name ")
					.append(",inv_unit ")
					.append(",base_usage ")
					.append(",use_times ")
					.append(",time_usage ")
					.append(",out_price ")
					.append(",base_cost ")
					.append(",sale_quantity ")
					.append(",sale_cost ")
					.append(" from hk_report_type01 ")
		;
		
		ArrayList<Type01VO> list_01 = (ArrayList)dao.executeQuery(resultSQL.toString(), new BeanListProcessor(Type01VO.class));
		System.out.println(list_01);
		
		return resultSQL.toString();
	}
	
}
