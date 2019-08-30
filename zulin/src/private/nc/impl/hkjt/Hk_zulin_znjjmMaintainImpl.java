package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_zulin_znjjmPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.itf.hkjt.IHk_zulin_znjjmMaintain;
import nc.vo.pub.BusinessException;

public class Hk_zulin_znjjmMaintainImpl extends AceHk_zulin_znjjmPubServiceImpl
		implements IHk_zulin_znjjmMaintain {

	@Override
	public void delete(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjmBillVO[] insert(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjmBillVO[] update(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjmBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public ZnjjmBillVO[] save(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjmBillVO[] unsave(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjmBillVO[] approve(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjmBillVO[] unapprove(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
