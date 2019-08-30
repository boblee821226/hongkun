package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_zulin_znjjsMaintain {

	public void delete(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException;

	public ZnjjsBillVO[] insert(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException;

	public ZnjjsBillVO[] update(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException;

	public ZnjjsBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public ZnjjsBillVO[] save(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException;

	public ZnjjsBillVO[] unsave(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException;

	public ZnjjsBillVO[] approve(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException;

	public ZnjjsBillVO[] unapprove(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException;
}
