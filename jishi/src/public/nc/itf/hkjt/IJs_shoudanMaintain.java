package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.pub.BusinessException;

public interface IJs_shoudanMaintain {

	public void delete(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException;

	public ShoudanBillVO[] insert(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException;

	public ShoudanBillVO[] update(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException;

	public ShoudanBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public ShoudanBillVO[] save(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException;

	public ShoudanBillVO[] unsave(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException;

	public ShoudanBillVO[] approve(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException;

	public ShoudanBillVO[] unapprove(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException;
}
