package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_kayuePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.itf.hkjt.IHy_kayueMaintain;
import nc.vo.pub.BusinessException;

public class Hy_kayueMaintainImpl extends AceHy_kayuePubServiceImpl
		implements IHy_kayueMaintain {

	@Override
	public void delete(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KayueBillVO[] insert(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KayueBillVO[] update(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KayueBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KayueBillVO[] save(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KayueBillVO[] unsave(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KayueBillVO[] approve(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KayueBillVO[] unapprove(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
