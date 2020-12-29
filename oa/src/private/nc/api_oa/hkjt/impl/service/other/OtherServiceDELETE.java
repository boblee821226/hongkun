package nc.api_oa.hkjt.impl.service.other;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;

public class OtherServiceDELETE {
	
	private static IplatFormEntry iplatFormEntry;
	private static IplatFormEntry getIplatFormEntry() {
		if (iplatFormEntry == null) {
			iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		}
		return iplatFormEntry;
	}
	
	/**
	 * @param account
	 * @param billType
	 * @param paramObj
	 * @param action
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	public Object doAction(
			  String account
			, String billType
			, Object paramObj
			, String action
			, String userId) 
			throws BusinessException  {
		HashMap[] param = (HashMap[])paramObj;
		nc.vo.ep.bx.JKVO[] billVOs = new nc.vo.ep.bx.JKVO[param.length];
		/**
		 * 查找vo
		 */
		for (int i = 0; i < param.length; i++) {
//			billVOs[i] = OtherServiceQUERY.getJkVO(param[i], account, billType);
		}		
		/**
		 * 循环处理：收回
		 */
		for (int i = 0; i < billVOs.length; i++) {
			nc.vo.ep.bx.JKVO billVO = billVOs[i];
			if (billVO == null) throw new BusinessException("单据不存在");
			Integer spzt = billVO.getParentVO().getSpzt();	// 0=审批未通过，1=审批通过，2=审批进行中，3=提交，-1=自由， 
			if (3 == spzt) { // 收回
				Object res = getIplatFormEntry().processAction("UNSAVE", billType, null, billVO, null, null);
				billVO = (nc.vo.ep.bx.JKVO)res;
				spzt = billVO.getParentVO().getSpzt();
				// TODO：更改日志表的状态。
			} else {
				throw new BusinessException("状态不对，无法收回。");
			}
		}
		
		return null;
	}
	
}
