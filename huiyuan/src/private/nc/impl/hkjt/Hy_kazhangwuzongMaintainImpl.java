package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_kazhangwuzongPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.itf.hkjt.IHy_kazhangwuzongMaintain;
import nc.vo.pub.BusinessException;

public class Hy_kazhangwuzongMaintainImpl extends AceHy_kazhangwuzongPubServiceImpl
		implements IHy_kazhangwuzongMaintain {

	@Override
	public void delete(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuzongBillVO[] insert(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuzongBillVO[] update(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuzongBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KazhangwuzongBillVO[] save(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuzongBillVO[] unsave(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuzongBillVO[] approve(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazhangwuzongBillVO[] unapprove(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
