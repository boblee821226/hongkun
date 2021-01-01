package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_arap_accoutPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.itf.hkjt.IHk_arap_accoutMaintain;
import nc.vo.pub.BusinessException;

public class Hk_arap_accoutMaintainImpl extends AceHk_arap_accoutPubServiceImpl
		implements IHk_arap_accoutMaintain {

	@Override
	public void delete(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AccountBillVO[] insert(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AccountBillVO[] update(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AccountBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AccountBillVO[] save(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AccountBillVO[] unsave(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AccountBillVO[] approve(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AccountBillVO[] unapprove(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
