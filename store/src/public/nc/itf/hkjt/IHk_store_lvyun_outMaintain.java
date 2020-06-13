package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.pub.BusinessException;

public interface IHk_store_lvyun_outMaintain {

	public void delete(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] insert(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] update(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public LyOutStoreBillVO[] save(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] unsave(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] approve(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;

	public LyOutStoreBillVO[] unapprove(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException;
}
