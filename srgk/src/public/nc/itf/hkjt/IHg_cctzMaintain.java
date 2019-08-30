package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.vo.pub.BusinessException;

public interface IHg_cctzMaintain {

	public void delete(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException;

	public CctzBillVO[] insert(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException;

	public CctzBillVO[] update(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException;

	public CctzBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public CctzBillVO[] save(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException;

	public CctzBillVO[] unsave(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException;

	public CctzBillVO[] approve(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException;

	public CctzBillVO[] unapprove(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException;
}
