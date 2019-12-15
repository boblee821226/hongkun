package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.hkjt.huiyuan.huanka.HuankaHVO;
import nc.vo.pub.BusinessException;

public interface IHy_huankaMaintain {

	public void delete(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException;

	public HuankaBillVO[] insert(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException;

	public HuankaBillVO[] update(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException;

	public HuankaBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public HuankaBillVO[] save(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException;

	public HuankaBillVO[] unsave(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException;

	public HuankaBillVO[] approve(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException;

	public HuankaBillVO[] unapprove(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException;
	
	/**
	 * »»¿¨
	 */
	public Object huanka(HuankaHVO huankaHVO,String pk_huanka) throws BusinessException;
	
}
