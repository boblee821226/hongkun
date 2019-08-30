package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_zulin_znjjsPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;
import nc.vo.pub.BusinessException;

public class Hk_zulin_znjjsMaintainImpl extends AceHk_zulin_znjjsPubServiceImpl
		implements IHk_zulin_znjjsMaintain {

	@Override
	public void delete(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] insert(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] update(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public ZnjjsBillVO[] save(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] unsave(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] approve(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZnjjsBillVO[] unapprove(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
