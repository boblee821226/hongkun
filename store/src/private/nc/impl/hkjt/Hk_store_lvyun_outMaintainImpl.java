package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_store_lvyun_outPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.itf.hkjt.IHk_store_lvyun_outMaintain;
import nc.vo.pub.BusinessException;

public class Hk_store_lvyun_outMaintainImpl extends AceHk_store_lvyun_outPubServiceImpl
		implements IHk_store_lvyun_outMaintain {

	@Override
	public void delete(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] insert(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] update(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public LyOutStoreBillVO[] save(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] unsave(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] approve(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] unapprove(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
