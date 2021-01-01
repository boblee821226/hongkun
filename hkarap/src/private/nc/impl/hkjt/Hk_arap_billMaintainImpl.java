package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_arap_billPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.itf.hkjt.IHk_arap_billMaintain;
import nc.vo.pub.BusinessException;

public class Hk_arap_billMaintainImpl extends AceHk_arap_billPubServiceImpl
		implements IHk_arap_billMaintain {

	@Override
	public void delete(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public BillBillVO[] insert(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public BillBillVO[] update(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public BillBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public BillBillVO[] save(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillBillVO[] unsave(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillBillVO[] approve(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillBillVO[] unapprove(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
