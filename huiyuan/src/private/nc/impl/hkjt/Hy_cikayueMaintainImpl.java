package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_cikayuePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.itf.hkjt.IHy_cikayueMaintain;
import nc.vo.pub.BusinessException;

public class Hy_cikayueMaintainImpl extends AceHy_cikayuePubServiceImpl
		implements IHy_cikayueMaintain {

	@Override
	public void delete(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public CikayueBillVO[] insert(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public CikayueBillVO[] update(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public CikayueBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public CikayueBillVO[] save(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikayueBillVO[] unsave(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikayueBillVO[] approve(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikayueBillVO[] unapprove(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
