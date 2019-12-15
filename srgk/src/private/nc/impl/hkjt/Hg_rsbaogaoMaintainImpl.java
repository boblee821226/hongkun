package nc.impl.hkjt;

import nc.impl.pub.ace.AceHg_rsbaogaoPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.itf.hkjt.IHg_rsbaogaoMaintain;
import nc.vo.pub.BusinessException;

public class Hg_rsbaogaoMaintainImpl extends AceHg_rsbaogaoPubServiceImpl
		implements IHg_rsbaogaoMaintain {

	@Override
	public void delete(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public RsbaogaoBillVO[] insert(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public RsbaogaoBillVO[] update(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public RsbaogaoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public RsbaogaoBillVO[] save(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RsbaogaoBillVO[] unsave(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RsbaogaoBillVO[] approve(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public RsbaogaoBillVO[] unapprove(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
