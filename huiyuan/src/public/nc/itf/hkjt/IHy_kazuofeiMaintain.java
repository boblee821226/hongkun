package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kazuofeiMaintain {

	public void delete(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException;

	public KazuofeiBillVO[] insert(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException;

	public KazuofeiBillVO[] update(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException;

	public KazuofeiBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KazuofeiBillVO[] save(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException;

	public KazuofeiBillVO[] unsave(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException;

	public KazuofeiBillVO[] approve(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException;

	public KazuofeiBillVO[] unapprove(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException;
}
