package nc.impl.hkjt;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceHk_arap_operatePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.itf.hkjt.IHk_arap_operateMaintain;
import nc.vo.pub.BusinessException;

public class Hk_arap_operateMaintainImpl extends AceHk_arap_operatePubServiceImpl
		implements IHk_arap_operateMaintain {

	@Override
	public void delete(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public OperateBillVO[] insert(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public OperateBillVO[] update(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public OperateBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public OperateBillVO[] save(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public OperateBillVO[] unsave(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public OperateBillVO[] approve(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public OperateBillVO[] unapprove(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Integer execUpdateSQL(String updateSQL) throws BusinessException {
		return new BaseDAO().executeUpdate(updateSQL);
	}

}
