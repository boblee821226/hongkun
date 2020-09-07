package nc.impl.hkjt;

import java.util.HashMap;

import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.impl.pub.ace.AceHy_kainfoPubServiceImpl;
import nc.itf.hkjt.IHy_kainfoMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pa.CurrEnvVO;

public class Hy_kainfoMaintainImpl extends AceHy_kainfoPubServiceImpl
		implements IHy_kainfoMaintain {

	@Override
	public void delete(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KainfoBillVO[] insert(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KainfoBillVO[] update(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KainfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KainfoBillVO[] save(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KainfoBillVO[] unsave(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KainfoBillVO[] approve(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KainfoBillVO[] unapprove(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Object execHuiyuanPlugin(
			  CurrEnvVO context
			, HashMap<String,Object> param
			, Object other) throws BusinessException {
		return new HuiyuanPlugin().executeTask(context);
	}

}
