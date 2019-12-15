package nc.impl.hkjt;

import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.impl.pub.ace.AceHy_cikainfoPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.itf.hkjt.IHy_cikainfoMaintain;
import nc.vo.pub.BusinessException;

public class Hy_cikainfoMaintainImpl extends AceHy_cikainfoPubServiceImpl
		implements IHy_cikainfoMaintain {

	@Override
	public void delete(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public CikainfoBillVO[] insert(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public CikainfoBillVO[] update(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public CikainfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public CikainfoBillVO[] save(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikainfoBillVO[] unsave(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikainfoBillVO[] approve(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikainfoBillVO[] unapprove(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public CikainfoBVO queryCika(String waterNum) throws BusinessException {
		
		System.out.println(""+waterNum);
		
		try
		{
			return new HuiyuanPlugin().getCika(waterNum);
			
		}catch(Exception ex)
		{
			new BusinessException(ex);
		}
		
		return null;
	}

}
