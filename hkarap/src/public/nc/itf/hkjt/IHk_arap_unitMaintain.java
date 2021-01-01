package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_arap_unitMaintain {

	public void delete(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException;

	public UnitBillVO[] insert(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException;

	public UnitBillVO[] update(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException;

	public UnitBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public UnitBillVO[] save(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException;

	public UnitBillVO[] unsave(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException;

	public UnitBillVO[] approve(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException;

	public UnitBillVO[] unapprove(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException;
}
