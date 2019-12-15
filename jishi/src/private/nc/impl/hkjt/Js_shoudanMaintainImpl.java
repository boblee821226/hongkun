package nc.impl.hkjt;

import nc.impl.pub.ace.AceJs_shoudanPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.itf.hkjt.IJs_shoudanMaintain;
import nc.vo.pub.BusinessException;

public class Js_shoudanMaintainImpl extends AceJs_shoudanPubServiceImpl
		implements IJs_shoudanMaintain {

	@Override
	public void delete(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public ShoudanBillVO[] insert(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public ShoudanBillVO[] update(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public ShoudanBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public ShoudanBillVO[] save(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ShoudanBillVO[] unsave(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ShoudanBillVO[] approve(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ShoudanBillVO[] unapprove(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
