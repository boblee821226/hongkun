package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_cikatongjiMaintain {

	public void delete(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException;

	public CikatongjiBillVO[] insert(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException;

	public CikatongjiBillVO[] update(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException;

	public CikatongjiBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public CikatongjiBillVO[] save(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException;

	public CikatongjiBillVO[] unsave(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException;

	public CikatongjiBillVO[] approve(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException;

	public CikatongjiBillVO[] unapprove(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException;
}
