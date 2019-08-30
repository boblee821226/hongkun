package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_zulin_tiaozhengPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.itf.hkjt.IHk_zulin_tiaozhengMaintain;
import nc.vo.pub.BusinessException;

public class Hk_zulin_tiaozhengMaintainImpl extends AceHk_zulin_tiaozhengPubServiceImpl
		implements IHk_zulin_tiaozhengMaintain {

	@Override
	public void delete(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public TzBillVO[] insert(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public TzBillVO[] update(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public TzBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public TzBillVO[] save(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public TzBillVO[] unsave(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public TzBillVO[] approve(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public TzBillVO[] unapprove(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
