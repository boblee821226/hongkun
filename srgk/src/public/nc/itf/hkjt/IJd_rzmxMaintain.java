package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.pub.BusinessException;

public interface IJd_rzmxMaintain {

	public void delete(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException;

	public RzmxBillVO[] insert(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException;

	public RzmxBillVO[] update(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException;

	public RzmxBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public RzmxBillVO[] save(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException;

	public RzmxBillVO[] unsave(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException;

	public RzmxBillVO[] approve(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException;

	public RzmxBillVO[] unapprove(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException;
}
