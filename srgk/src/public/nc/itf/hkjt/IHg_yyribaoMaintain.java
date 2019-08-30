package nc.itf.hkjt;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.pub.BusinessException;

public interface IHg_yyribaoMaintain {

	public void delete(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException;

	public YyribaoBillVO[] insert(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException;

	public YyribaoBillVO[] update(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException;

	public YyribaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public YyribaoBillVO[] save(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException;

	public YyribaoBillVO[] unsave(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException;

	public YyribaoBillVO[] approve(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException;

	public YyribaoBillVO[] unapprove(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException;
}
