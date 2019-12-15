package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_zhangdanPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.itf.hkjt.IHg_zhangdanMaintain;
import nc.vo.pub.BusinessException;

public class Hg_zhangdanMaintainImpl extends AceHg_zhangdanPubServiceImpl
		implements IHg_zhangdanMaintain {

	@Override
	public void delete(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public ZhangdanBillVO[] insert(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public ZhangdanBillVO[] update(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public ZhangdanBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public ZhangdanBillVO[] save(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZhangdanBillVO[] unsave(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZhangdanBillVO[] approve(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public ZhangdanBillVO[] unapprove(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
