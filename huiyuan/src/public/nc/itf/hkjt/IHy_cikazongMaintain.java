package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_cikazongMaintain {

	public void delete(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException;

	public CikazongBillVO[] insert(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException;

	public CikazongBillVO[] update(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException;

	public CikazongBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public CikazongBillVO[] save(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException;

	public CikazongBillVO[] unsave(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException;

	public CikazongBillVO[] approve(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException;

	public CikazongBillVO[] unapprove(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException;
}
