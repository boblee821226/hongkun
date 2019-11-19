package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_zulin_yuebaoMaintain {

	public void delete(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException;

	public YuebaoBillVO[] insert(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException;

	public YuebaoBillVO[] update(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException;

	public YuebaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public YuebaoBillVO[] save(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException;

	public YuebaoBillVO[] unsave(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException;

	public YuebaoBillVO[] approve(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException;

	public YuebaoBillVO[] unapprove(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException;
	
	/**
	 * 生成 NC 凭证
	 * flag = 0  生成
	 * flag = 1  删除
	 */
	public String genNCVoucherInfo(YuebaoBillVO ybvo,int flag) throws Exception;
}
