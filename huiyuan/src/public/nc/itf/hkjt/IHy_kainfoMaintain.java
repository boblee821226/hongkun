package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kainfoMaintain {

	public void delete(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] insert(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] update(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KainfoBillVO[] save(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] unsave(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] approve(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] unapprove(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;
}
