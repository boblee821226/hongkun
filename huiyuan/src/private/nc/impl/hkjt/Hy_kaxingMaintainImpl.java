package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_kaxingPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.itf.hkjt.IHy_kaxingMaintain;
import nc.vo.pub.BusinessException;

public class Hy_kaxingMaintainImpl extends AceHy_kaxingPubServiceImpl
		implements IHy_kaxingMaintain {

	@Override
	public void delete(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KaxingBillVO[] insert(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KaxingBillVO[] update(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KaxingBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KaxingBillVO[] save(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaxingBillVO[] unsave(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaxingBillVO[] approve(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaxingBillVO[] unapprove(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
