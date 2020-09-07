package nc.itf.hkjt;

import java.util.HashMap;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pa.CurrEnvVO;

public interface IHy_kainfoMaintain {

	public void delete(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] insert(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] update(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KainfoBillVO[] save(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] unsave(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] approve(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;

	public KainfoBillVO[] unapprove(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException;
	
	/**
	 * HK 2020年9月7日18:35:21
	 * 执行 会员卡取数的后台任务
	 */
	public Object execHuiyuanPlugin(CurrEnvVO context, HashMap<String,Object> param, Object other) 
			throws BusinessException;
}
