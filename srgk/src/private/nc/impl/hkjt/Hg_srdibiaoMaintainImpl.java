package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_srdibiaoPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.itf.hkjt.IHg_srdibiaoMaintain;
import nc.vo.pub.BusinessException;

public class Hg_srdibiaoMaintainImpl extends AceHg_srdibiaoPubServiceImpl
		implements IHg_srdibiaoMaintain {

	@Override
	public void delete(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public SrdibiaoBillVO[] insert(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public SrdibiaoBillVO[] update(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public SrdibiaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public SrdibiaoBillVO[] save(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SrdibiaoBillVO[] unsave(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SrdibiaoBillVO[] approve(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public SrdibiaoBillVO[] unapprove(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
