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
		 * ����vo
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = N263XServiceQUERY.getJkVO(param[i], account, billType);
		}		
		/**
		 * ѭ�����������ջء�ɾ��
		 */
		for (int i = 0; i < billVOs.length; i++) {
			nc.vo.ep.bx.JKVO billVO = billVOs[i];
			if (billVO == null) throw new BusinessException("���ݲ�����");
			Integer spzt = billVO.getParentVO().getSpzt();	// 0=����δͨ����1=����ͨ����2=���������У�3=�ύ��-1=���ɣ� 
//			if (1 == spzt) { // ����
//				Object res = getIplatFormEntry().processAction("UNAPPROVE"+userId, billType, null, billVO, null, null);
//				nc.vo.erm.common.MessageVO msgVO = ((nc.vo.erm.common.MessageVO[])res)[0];
//				if (!msgVO.isSuccess()) {
//					throw new BusinessException(msgVO.getErrorMessage());
//				}
//				billVO = (nc.vo.ep.bx.JKVO)msgVO.getSuccessVO();
//				spzt = billVO.getParentVO().getSpzt();
//			}
			if (3 == spzt) { // �ջ�
				Object res = getIplatFormEntry().processAction("UNSAVE", billType, null, billVO, null, null);
				billVO = (nc.vo.ep.bx.JKVO)res;
				spzt = billVO.getParentVO().getSpzt();
			}
			if (-1 == spzt) { // ɾ��
				Object res = getIplatFormEntry().processAction("DELETE", billType, null, billVO, new JKBXVO[]{billVO}, null);
				nc.vo.erm.common.MessageVO msgVO = ((nc.vo.erm.common.MessageVO[])res)[0];
				if (!msgVO.isSuccess()) {
					throw new BusinessException(msgVO.getErrorMessage());
				}
			} else {
				throw new BusinessException("״̬���ԣ��޷�ɾ����");
			}
		}
		
		return null;
	}
	
}
