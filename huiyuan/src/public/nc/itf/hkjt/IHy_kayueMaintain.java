package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kayueMaintain {

	public void delete(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException;

	public KayueBillVO[] insert(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException;

	public KayueBillVO[] update(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException;

	public KayueBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KayueBillVO[] save(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException;

	public KayueBillVO[] unsave(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException;

	public KayueBillVO[] approve(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException;

	public KayueBillVO[] unapprove(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException;
}
