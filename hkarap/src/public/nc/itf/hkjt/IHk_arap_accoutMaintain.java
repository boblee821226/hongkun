package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_arap_accoutMaintain {

	public void delete(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException;

	public AccountBillVO[] insert(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException;

	public AccountBillVO[] update(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException;

	public AccountBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AccountBillVO[] save(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException;

	public AccountBillVO[] unsave(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException;

	public AccountBillVO[] approve(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException;

	public AccountBillVO[] unapprove(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException;
}
