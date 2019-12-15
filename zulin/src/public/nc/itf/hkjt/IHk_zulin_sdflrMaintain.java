package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_zulin_sdflrMaintain {

	public void delete(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException;

	public SdflrBillVO[] insert(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException;

	public SdflrBillVO[] update(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException;

	public SdflrBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public SdflrBillVO[] save(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException;

	public SdflrBillVO[] unsave(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException;

	public SdflrBillVO[] approve(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException;

	public SdflrBillVO[] unapprove(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException;
}
