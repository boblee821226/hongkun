package nc.impl.hkjt;

import nc.impl.pub.ace.AceJd_rzmxPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.vo.pub.BusinessException;

public class Jd_rzmxMaintainImpl extends AceJd_rzmxPubServiceImpl
		implements IJd_rzmxMaintain {

	@Override
	public void delete(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] insert(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] update(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public RzmxBillVO[] save(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] unsave(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] approve(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RzmxBillVO[] unapprove(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
