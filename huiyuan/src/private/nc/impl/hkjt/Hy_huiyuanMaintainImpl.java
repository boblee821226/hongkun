package nc.impl.hkjt;

import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.impl.pub.ace.AceHy_huiyuanPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.itf.hkjt.IHy_huiyuanMaintain;
import nc.vo.pub.BusinessException;

public class Hy_huiyuanMaintainImpl extends AceHy_huiyuanPubServiceImpl
		implements IHy_huiyuanMaintain {

	@Override
	public void delete(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KadanganBillVO[] insert(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KadanganBillVO[] update(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KadanganBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KadanganBillVO[] save(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KadanganBillVO[] unsave(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KadanganBillVO[] approve(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KadanganBillVO[] unapprove(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Object insertKadangan(String ka_code) throws BusinessException {
		
		try
		{
			return new HuiyuanPlugin().insertKadangan(ka_code);
			
		}catch(Exception ex)
		{
			new BusinessException(ex);
		}
		
		return null;
		
	}

	@Override
	public Object queryJGchongzhi(String ka_code,String sj,String dian,Object other) throws BusinessException {
		
		try
		{
			return new HuiyuanPlugin().queryJGchongzhi(ka_code,sj,dian,other);
			
		}catch(Exception ex)
		{
			new BusinessException(ex);
		}
		
		return null;
		
	}
	
}
