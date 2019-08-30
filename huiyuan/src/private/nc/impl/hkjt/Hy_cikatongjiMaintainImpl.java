package nc.impl.hkjt;

import nc.impl.pub.ace.AceHy_cikatongjiPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.itf.hkjt.IHy_cikatongjiMaintain;
import nc.vo.pub.BusinessException;

public class Hy_cikatongjiMaintainImpl extends AceHy_cikatongjiPubServiceImpl
		implements IHy_cikatongjiMaintain {

	@Override
	public void delete(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public CikatongjiBillVO[] insert(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public CikatongjiBillVO[] update(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public CikatongjiBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public CikatongjiBillVO[] save(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikatongjiBillVO[] unsave(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikatongjiBillVO[] approve(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikatongjiBillVO[] unapprove(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
