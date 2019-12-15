package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_zulin_znjjmMaintain {

	public void delete(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException;

	public ZnjjmBillVO[] insert(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException;

	public ZnjjmBillVO[] update(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException;

	public ZnjjmBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public ZnjjmBillVO[] save(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException;

	public ZnjjmBillVO[] unsave(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException;

	public ZnjjmBillVO[] approve(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException;

	public ZnjjmBillVO[] unapprove(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException;
}
