package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.BusinessException;

public interface IHg_zhangdanMaintain {

	public void delete(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException;

	public ZhangdanBillVO[] insert(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException;

	public ZhangdanBillVO[] update(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException;

	public ZhangdanBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public ZhangdanBillVO[] save(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException;

	public ZhangdanBillVO[] unsave(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException;

	public ZhangdanBillVO[] approve(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException;

	public ZhangdanBillVO[] unapprove(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException;
}
