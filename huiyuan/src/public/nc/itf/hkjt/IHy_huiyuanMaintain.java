package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.vo.pub.BusinessException;

public interface IHy_huiyuanMaintain {

	public void delete(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException;

	public KadanganBillVO[] insert(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException;

	public KadanganBillVO[] update(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException;

	public KadanganBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public KadanganBillVO[] save(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException;

	public KadanganBillVO[] unsave(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException;

	public KadanganBillVO[] approve(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException;

	public KadanganBillVO[] unapprove(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException;
	
	/**
	 * 手工抓取 会员数据，从金贵系统里
	 */
	public Object insertKadangan(String ka_code) throws BusinessException;
	
	/**
	 * 查询金贵充值信息，根据卡号
	 * 2016年10月31日21:24:14
	 */
	public Object queryJGchongzhi(String ka_code,String sj,String dian,Object other) throws BusinessException;
}
