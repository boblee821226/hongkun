package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_fp_sk_billMaintain {

	public void delete(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException;

	public BillSkFpBillVO[] insert(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException;

	public BillSkFpBillVO[] update(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException;

	public BillSkFpBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public BillSkFpBillVO[] save(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException;

	public BillSkFpBillVO[] unsave(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException;

	public BillSkFpBillVO[] approve(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException;

	public BillSkFpBillVO[] unapprove(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException;
}
