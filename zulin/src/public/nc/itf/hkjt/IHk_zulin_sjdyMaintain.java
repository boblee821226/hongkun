package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_zulin_sjdyMaintain {

	public void delete(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException;

	public SjdyBillVO[] insert(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException;

	public SjdyBillVO[] update(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException;

	public SjdyBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public SjdyBillVO[] save(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException;

	public SjdyBillVO[] unsave(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException;

	public SjdyBillVO[] approve(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException;

	public SjdyBillVO[] unapprove(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException;
}
