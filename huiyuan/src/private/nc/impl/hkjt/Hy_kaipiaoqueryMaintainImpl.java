package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_kaipiaoqueryPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.itf.hkjt.IHy_kaipiaoqueryMaintain;
import nc.vo.pub.BusinessException;

public class Hy_kaipiaoqueryMaintainImpl extends AceHy_kaipiaoqueryPubServiceImpl
		implements IHy_kaipiaoqueryMaintain {

	@Override
	public void delete(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoqueryBillVO[] insert(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoqueryBillVO[] update(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoqueryBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KaipiaoqueryBillVO[] save(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoqueryBillVO[] unsave(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoqueryBillVO[] approve(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoqueryBillVO[] unapprove(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
