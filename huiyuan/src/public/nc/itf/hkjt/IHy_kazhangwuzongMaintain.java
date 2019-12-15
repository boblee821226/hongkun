package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kazhangwuzongMaintain {

	public void delete(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException;

	public KazhangwuzongBillVO[] insert(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException;

	public KazhangwuzongBillVO[] update(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException;

	public KazhangwuzongBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KazhangwuzongBillVO[] save(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException;

	public KazhangwuzongBillVO[] unsave(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException;

	public KazhangwuzongBillVO[] approve(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException;

	public KazhangwuzongBillVO[] unapprove(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException;
}
