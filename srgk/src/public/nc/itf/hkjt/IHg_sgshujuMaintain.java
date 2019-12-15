package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.pub.BusinessException;

public interface IHg_sgshujuMaintain {

	public void delete(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException;

	public SgshujuBillVO[] insert(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException;

	public SgshujuBillVO[] update(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException;

	public SgshujuBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public SgshujuBillVO[] save(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException;

	public SgshujuBillVO[] unsave(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException;

	public SgshujuBillVO[] approve(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException;

	public SgshujuBillVO[] unapprove(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException;
}
