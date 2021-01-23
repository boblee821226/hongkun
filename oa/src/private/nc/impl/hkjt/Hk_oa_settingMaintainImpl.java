package nc.impl.hkjt;

import nc.impl.pub.ace.AceHk_oa_settingPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.itf.hkjt.IHk_oa_settingMaintain;
import nc.vo.pub.BusinessException;

public class Hk_oa_settingMaintainImpl extends AceHk_oa_settingPubServiceImpl
		implements IHk_oa_settingMaintain {

	@Override
	public void delete(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public OaSettingBillVO[] insert(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public OaSettingBillVO[] update(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public OaSettingBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public OaSettingBillVO[] save(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public OaSettingBillVO[] unsave(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public OaSettingBillVO[] approve(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public OaSettingBillVO[] unapprove(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
