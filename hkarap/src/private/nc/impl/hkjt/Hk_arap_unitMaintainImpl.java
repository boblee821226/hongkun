package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_arap_unitPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.itf.hkjt.IHk_arap_unitMaintain;
import nc.vo.pub.BusinessException;

public class Hk_arap_unitMaintainImpl extends AceHk_arap_unitPubServiceImpl
		implements IHk_arap_unitMaintain {

	@Override
	public void delete(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public UnitBillVO[] insert(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public UnitBillVO[] update(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public UnitBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public UnitBillVO[] save(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public UnitBillVO[] unsave(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public UnitBillVO[] approve(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public UnitBillVO[] unapprove(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
