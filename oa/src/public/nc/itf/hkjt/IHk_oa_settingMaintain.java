package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_oa_settingMaintain {

	public void delete(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException;

	public OaSettingBillVO[] insert(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException;

	public OaSettingBillVO[] update(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException;

	public OaSettingBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public OaSettingBillVO[] save(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException;

	public OaSettingBillVO[] unsave(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException;

	public OaSettingBillVO[] approve(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException;

	public OaSettingBillVO[] unapprove(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException;
}
