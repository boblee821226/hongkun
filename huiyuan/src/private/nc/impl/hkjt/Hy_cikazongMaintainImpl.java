package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_cikazongPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.itf.hkjt.IHy_cikazongMaintain;
import nc.vo.pub.BusinessException;

public class Hy_cikazongMaintainImpl extends AceHy_cikazongPubServiceImpl
		implements IHy_cikazongMaintain {

	@Override
	public void delete(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public CikazongBillVO[] insert(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public CikazongBillVO[] update(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public CikazongBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public CikazongBillVO[] save(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikazongBillVO[] unsave(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikazongBillVO[] approve(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikazongBillVO[] unapprove(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
