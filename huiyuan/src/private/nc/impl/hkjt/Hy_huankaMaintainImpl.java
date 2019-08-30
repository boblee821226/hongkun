package nc.impl.hkjt;

import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.impl.pub.ace.AceHy_huankaPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.hkjt.huiyuan.huanka.HuankaHVO;
import nc.itf.hkjt.IHy_huankaMaintain;
import nc.vo.pub.BusinessException;

public class Hy_huankaMaintainImpl extends AceHy_huankaPubServiceImpl
		implements IHy_huankaMaintain {

	@Override
	public void delete(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public HuankaBillVO[] insert(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public HuankaBillVO[] update(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public HuankaBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public HuankaBillVO[] save(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public HuankaBillVO[] unsave(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public HuankaBillVO[] approve(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public HuankaBillVO[] unapprove(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Object huanka(HuankaHVO huankaHVO, String pk_huanka)
			throws BusinessException {
		
		Object result = null;
		
		try
		{
			
			HuiyuanPlugin plugin = new HuiyuanPlugin();
			result = plugin.huanka_update(huankaHVO, pk_huanka);
			
			
		}catch(Exception ex)
		{
			throw new BusinessException(ex);
		}
		
		return result;
	}

}
