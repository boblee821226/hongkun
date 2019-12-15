package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kazhangwuMaintain {

	public void delete(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException;

	public KazhangwuBillVO[] insert(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException;

	public KazhangwuBillVO[] update(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException;

	public KazhangwuBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KazhangwuBillVO[] save(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException;

	public KazhangwuBillVO[] unsave(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException;

	public KazhangwuBillVO[] approve(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException;

	public KazhangwuBillVO[] unapprove(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException;
}
