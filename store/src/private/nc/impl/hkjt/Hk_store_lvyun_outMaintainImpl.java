package nc.impl.hkjt;

import java.util.HashMap;

import nc.impl.hkjt.report.Type01Action;
import nc.impl.hkjt.report.Type02Action;
import nc.impl.pub.ace.AceHk_store_lvyun_outPubServiceImpl;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.itf.hkjt.IHk_store_lvyun_outMaintain;
import nc.vo.pub.BusinessException;

public class Hk_store_lvyun_outMaintainImpl extends AceHk_store_lvyun_outPubServiceImpl
		implements IHk_store_lvyun_outMaintain {

	@Override
	public void delete(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] insert(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] update(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public LyOutStoreBillVO[] save(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] unsave(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] approve(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public LyOutStoreBillVO[] unapprove(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	public static String TYPE_01 = "BOM标准成本";
	public static String TYPE_02 = "实际出库成本";
	
	@Override
	public DataSet queryReportToDataSet(SmartContext context,
			HashMap<String, Object> param, String type, String flag,
			Object other) throws BusinessException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String queryReportToSQL(
			  SmartContext context
			, HashMap<String, Object> param
			, String type
			, String flag
			,Object other
			) throws BusinessException {
		
		String resultSQL = null;
		
		if(TYPE_01.equals(type))
		{// BOM标准成本
			resultSQL = Type01Action.queryReportToSQL(context, param, flag, other);
		}
		else if(TYPE_02.equals(type))
		{// 实际出库成本
			resultSQL = Type02Action.queryReportToSQL(context, param, flag, other);
		}
		
		return resultSQL;
	}

}
