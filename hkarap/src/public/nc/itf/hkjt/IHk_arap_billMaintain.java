package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_arap_billMaintain {

	public void delete(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException;

	public BillBillVO[] insert(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException;

	public BillBillVO[] update(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException;

	public BillBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public BillBillVO[] save(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException;

	public BillBillVO[] unsave(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException;

	public BillBillVO[] approve(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException;

	public BillBillVO[] unapprove(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException;
}
