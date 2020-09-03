package nc.impl.hkjt.report;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pub.smart.context.SmartContext;
import nc.vo.hkjt.store.lvyun.out.report.Type00VO;
import nc.vo.hkjt.store.lvyun.out.report.Type02VO;
import nc.vo.pub.BusinessException;

/**
 * 实际出库成本
 */
public class Type02Action {
	
	public static String queryReportToSQL(
			 SmartContext context
			,HashMap<String,Object> param
			,String flag
			,Object other
	)throws BusinessException
	{
		BaseDAO dao = new BaseDAO();
		
		String v_pk_org = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_pk_org"));// 公司
		String v_pk_dept = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_pk_dept"));// 部门
		String v_begin_date = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_begin_date"));// 开始日期
		String v_end_date = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_end_date"));// 结束日期
		
		/**
		 * 1、判断 公共表里 有没有值，如果没有，就说明0 2是先执行的。
		 * 所以需要 先执行01
		 */
		{
			StringBuffer querySQL_00 = 
				new StringBuffer("select * from hk_report_type00");
			ArrayList<Type00VO> list_00 = (ArrayList)dao.executeQuery(querySQL_00.toString(), new BeanListProcessor(Type00VO.class));
			if (list_00 == null || list_00.isEmpty()) {
				// 先执行01
				Type01Action.queryReportToSQL(context, param, flag, other);
			}
		}
		/**
		 * 2、执行02的逻辑
		 */
		{
			StringBuffer  querySQL_02 = 
				new StringBuffer("select ")
						.append(" inv_code ")
						.append(",sum(base_cost) sale_cost ")
						.append(" from hk_report_type01 ")
						.append(" group by inv_code ")
			;
			ArrayList<Type02VO> list_02 = (ArrayList)dao.executeQuery(querySQL_02.toString(), new BeanListProcessor(Type02VO.class));
			for (Type02VO item : list_02) {
				item.setPk_group("0001N510000000000EGY");
				item.setPk_org(v_pk_org);
				item.setPk_dept(v_pk_dept);
				item.setDr(0);
			}
			dao.insertVOList(list_02);
		}
		/**
		 * 4、返回临时表的SQL
		 */
		StringBuffer resultSQL = 
			new StringBuffer(" select ")
					.append(" pk_group ")
					.append(",pk_org ")
					.append(",inv_code ")
					.append(",inv_name ")
					.append(",inv_unit ")
					.append(",out_price ")
					.append(",out_quantity ")
					.append(",out_amount ")
					.append(",base_quantity ")
					.append(",sale_cost ")
					.append(",differ_quantity ")
					.append(",differ_cost ")
					.append(",vnote ")
					.append(" from hk_report_type02 ")
			;
		return resultSQL.toString();
	}
	
}
