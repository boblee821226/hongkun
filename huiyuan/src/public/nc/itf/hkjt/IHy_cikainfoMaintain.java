package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_cikainfoMaintain {

	public void delete(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException;

	public CikainfoBillVO[] insert(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException;

	public CikainfoBillVO[] update(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException;

	public CikainfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public CikainfoBillVO[] save(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException;

	public CikainfoBillVO[] unsave(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException;

	public CikainfoBillVO[] approve(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException;

	public CikainfoBillVO[] unapprove(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException;
	
	/**
	 * 手工 取次卡,根据次卡水号
	 */
	public CikainfoBVO queryCika(String waterNum) throws BusinessException;
}
