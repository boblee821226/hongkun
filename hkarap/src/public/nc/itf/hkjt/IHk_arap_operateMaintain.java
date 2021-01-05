package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_arap_operateMaintain {

	public void delete(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException;

	public OperateBillVO[] insert(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException;

	public OperateBillVO[] update(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException;

	public OperateBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public OperateBillVO[] save(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException;

	public OperateBillVO[] unsave(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException;

	public OperateBillVO[] approve(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException;

	public OperateBillVO[] unapprove(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException;
	/**
	 * Ö´ÐÐupdateSQL
	 */
	public Integer execUpdateSQL(String updateSQL) throws BusinessException;
}
