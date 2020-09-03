package nc.itf.hkjt;

import java.util.HashMap;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.pub.BusinessException;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;

public interface IHk_store_lvyun_outMaintain {

	public void delete(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] insert(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] update(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public LyOutStoreBillVO[] save(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] unsave(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] approve(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] unapprove(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;
	
	/**
	 * 报表接口
	 */
	/**
	 * 返回DataSet
	 */
	public DataSet queryReportToDataSet(
			 SmartContext context
			, HashMap<String, Object> param
			, String type
			, String flag
			, Object other
	) throws BusinessException;
	
	/**
	 * 返回SQL
	 * 建议 都用返回sql的方式，避免系统给创建临时表，字段长度不够的错误。
	 */
	public String queryReportToSQL(
			 SmartContext context
			, HashMap<String, Object> param
			, String type
			, String flag
			, Object other
	) throws BusinessException;
}
