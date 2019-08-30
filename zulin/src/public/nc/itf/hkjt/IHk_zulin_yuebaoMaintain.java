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
	 * ���� NC ƾ֤
	 * flag = 0  ����
	 * flag = 1  ɾ��
	 */
	public String genNCVoucherInfo(YuebaoBillVO ybvo,int flag) throws Exception;
}
