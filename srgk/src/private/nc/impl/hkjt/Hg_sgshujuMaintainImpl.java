package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_sgshujuPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.itf.hkjt.IHg_sgshujuMaintain;
import nc.vo.pub.BusinessException;

public class Hg_sgshujuMaintainImpl extends AceHg_sgshujuPubServiceImpl
		implements IHg_sgshujuMaintain {

	@Override
	public void delete(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public SgshujuBillVO[] insert(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public SgshujuBillVO[] update(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public SgshujuBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public SgshujuBillVO[] save(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SgshujuBillVO[] unsave(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SgshujuBillVO[] approve(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SgshujuBillVO[] unapprove(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
