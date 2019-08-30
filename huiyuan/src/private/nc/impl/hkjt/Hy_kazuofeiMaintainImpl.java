package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_kazuofeiPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.itf.hkjt.IHy_kazuofeiMaintain;
import nc.vo.pub.BusinessException;

public class Hy_kazuofeiMaintainImpl extends AceHy_kazuofeiPubServiceImpl
		implements IHy_kazuofeiMaintain {

	@Override
	public void delete(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KazuofeiBillVO[] insert(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KazuofeiBillVO[] update(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KazuofeiBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KazuofeiBillVO[] save(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazuofeiBillVO[] unsave(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazuofeiBillVO[] approve(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KazuofeiBillVO[] unapprove(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
