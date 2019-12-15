package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_kazhangwuPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.itf.hkjt.IHy_kazhangwuMaintain;
import nc.vo.pub.BusinessException;

public class Hy_kazhangwuMaintainImpl extends AceHy_kazhangwuPubServiceImpl
		implements IHy_kazhangwuMaintain {

	@Override
	public void delete(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuBillVO[] insert(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuBillVO[] update(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KazhangwuBillVO[] save(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuBillVO[] unsave(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuBillVO[] approve(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuBillVO[] unapprove(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
