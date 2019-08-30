package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_cikayueMaintain {

	public void delete(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException;

	public CikayueBillVO[] insert(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException;

	public CikayueBillVO[] update(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException;

	public CikayueBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public CikayueBillVO[] save(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException;

	public CikayueBillVO[] unsave(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException;

	public CikayueBillVO[] approve(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException;

	public CikayueBillVO[] unapprove(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException;
}
