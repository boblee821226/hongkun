package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.pub.BusinessException;

public interface IHg_srdibiaoMaintain {

	public void delete(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException;

	public SrdibiaoBillVO[] insert(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException;

	public SrdibiaoBillVO[] update(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException;

	public SrdibiaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public SrdibiaoBillVO[] save(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException;

	public SrdibiaoBillVO[] unsave(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException;

	public SrdibiaoBillVO[] approve(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException;

	public SrdibiaoBillVO[] unapprove(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException;
}
