package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_zulin_sjdyPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.itf.hkjt.IHk_zulin_sjdyMaintain;
import nc.vo.pub.BusinessException;

public class Hk_zulin_sjdyMaintainImpl extends AceHk_zulin_sjdyPubServiceImpl
		implements IHk_zulin_sjdyMaintain {

	@Override
	public void delete(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public SjdyBillVO[] insert(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public SjdyBillVO[] update(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public SjdyBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public SjdyBillVO[] save(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SjdyBillVO[] unsave(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SjdyBillVO[] approve(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SjdyBillVO[] unapprove(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
