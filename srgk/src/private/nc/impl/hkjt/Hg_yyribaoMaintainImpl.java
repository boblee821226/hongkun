package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_yyribaoPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.itf.hkjt.IHg_yyribaoMaintain;
import nc.vo.pub.BusinessException;

public class Hg_yyribaoMaintainImpl extends AceHg_yyribaoPubServiceImpl
		implements IHg_yyribaoMaintain {

	@Override
	public void delete(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public YyribaoBillVO[] insert(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public YyribaoBillVO[] update(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public YyribaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public YyribaoBillVO[] save(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public YyribaoBillVO[] unsave(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public YyribaoBillVO[] approve(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public YyribaoBillVO[] unapprove(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
