package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_cctzPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.itf.hkjt.IHg_cctzMaintain;
import nc.vo.pub.BusinessException;

public class Hg_cctzMaintainImpl extends AceHg_cctzPubServiceImpl
		implements IHg_cctzMaintain {

	@Override
	public void delete(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public CctzBillVO[] insert(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public CctzBillVO[] update(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public CctzBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public CctzBillVO[] save(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CctzBillVO[] unsave(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CctzBillVO[] approve(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CctzBillVO[] unapprove(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
