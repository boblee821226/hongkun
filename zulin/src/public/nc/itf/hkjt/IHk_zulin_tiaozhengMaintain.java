package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_zulin_tiaozhengMaintain {

	public void delete(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException;

	public TzBillVO[] insert(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException;

	public TzBillVO[] update(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException;

	public TzBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public TzBillVO[] save(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException;

	public TzBillVO[] unsave(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException;

	public TzBillVO[] approve(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException;

	public TzBillVO[] unapprove(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException;
}
