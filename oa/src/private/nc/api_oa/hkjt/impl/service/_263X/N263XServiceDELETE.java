package nc.api_oa.hkjt.impl.service._263X;

import nc.api_oa.hkjt.vo._263X.JkVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.ep.bx.JKBXVO;
import nc.vo.pub.BusinessException;

public class N263XServiceDELETE {
	
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
		JkVO[] param = (JkVO[])paramObj;
		nc.vo.ep.bx.JKVO[] billVOs = new nc.vo.ep.bx.JKVO[param.length];
		/**
		 * 查找vo
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = N263XServiceQUERY.getJkVO(param[i], account, billType);
		}		
		/**
		 * 循环处理：弃审、收回、删除
		 */
		for (int i = 0; i < billVOs.length; i++) {
			nc.vo.ep.bx.JKVO billVO = billVOs[i];
			if (billVO == null) throw new BusinessException("单据不存在");
			Integer spzt = billVO.getParentVO().getSpzt();	// 0=审批未通过，1=审批通过，2=审批进行中，3=提交，-1=自由， 
//			if (1 == spzt) { // 弃审
//				Object res = getIplatFormEntry().processAction("UNAPPROVE"+userId, billType, null, billVO, null, null);
//				nc.vo.erm.common.MessageVO msgVO = ((nc.vo.erm.common.MessageVO[])res)[0];
//				if (!msgVO.isSuccess()) {
//					throw new BusinessException(msgVO.getErrorMessage());
//				}
//				billVO = (nc.vo.ep.bx.JKVO)msgVO.getSuccessVO();
//				spzt = billVO.getParentVO().getSpzt();
//			}
			if (3 == spzt) { // 收回
				Object res = getIplatFormEntry().processAction("UNSAVE", billType, null, billVO, null, null);
				billVO = (nc.vo.ep.bx.JKVO)res;
				spzt = billVO.getParentVO().getSpzt();
			}
			if (-1 == spzt) { // 删除
				Object res = getIplatFormEntry().processAction("DELETE", billType, null, billVO, new JKBXVO[]{billVO}, null);
				nc.vo.erm.common.MessageVO msgVO = ((nc.vo.erm.common.MessageVO[])res)[0];
				if (!msgVO.isSuccess()) {
					throw new BusinessException(msgVO.getErrorMessage());
				}
			} else {
				throw new BusinessException("状态不对，无法删除。");
			}
		}
		
		return null;
	}
	
}
