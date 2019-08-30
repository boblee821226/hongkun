package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kaipiaoqueryMaintain {

	public void delete(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException;

	public KaipiaoqueryBillVO[] insert(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException;

	public KaipiaoqueryBillVO[] update(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException;

	public KaipiaoqueryBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KaipiaoqueryBillVO[] save(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException;

	public KaipiaoqueryBillVO[] unsave(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException;

	public KaipiaoqueryBillVO[] approve(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException;

	public KaipiaoqueryBillVO[] unapprove(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException;
}
