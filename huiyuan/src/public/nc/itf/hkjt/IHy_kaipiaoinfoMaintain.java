package nc.itf.hkjt;

import java.util.ArrayList;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_kaipiaoinfoMaintain {

	public void delete(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException;

	public KaipiaoinfoBillVO[] insert(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException;

	public KaipiaoinfoBillVO[] update(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException;

	public KaipiaoinfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KaipiaoinfoBillVO[] save(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException;

	public KaipiaoinfoBillVO[] unsave(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException;

	public KaipiaoinfoBillVO[] approve(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException;

	public KaipiaoinfoBillVO[] unapprove(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException;
	
	public Object updateSQL(String sql,Object other )throws BusinessException;
	
	/**
	 * ¼ì²é´íÎóµÄ·¢Æ±
	 * @return
	 * @throws BusinessException
	 */
	public Object checkErrorFP(ArrayList<String> error_key,Object other)throws BusinessException;
}
