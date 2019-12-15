package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kaxingMaintain {

	public void delete(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException;

	public KaxingBillVO[] insert(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException;

	public KaxingBillVO[] update(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException;

	public KaxingBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KaxingBillVO[] save(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException;

	public KaxingBillVO[] unsave(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException;

	public KaxingBillVO[] approve(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException;

	public KaxingBillVO[] unapprove(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException;
}
