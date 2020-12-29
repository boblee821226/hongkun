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
		 * ����vo
		 */
		for (int i = 0; i < param.length; i++) {
//			billVOs[i] = OtherServiceQUERY.getJkVO(param[i], account, billType);
		}		
		/**
		 * ѭ�������ջ�
		 */
		for (int i = 0; i < billVOs.length; i++) {
			nc.vo.ep.bx.JKVO billVO = billVOs[i];
			if (billVO == null) throw new BusinessException("���ݲ�����");
			Integer spzt = billVO.getParentVO().getSpzt();	// 0=����δͨ����1=����ͨ����2=���������У�3=�ύ��-1=���ɣ� 
			if (3 == spzt) { // �ջ�
				Object res = getIplatFormEntry().processAction("UNSAVE", billType, null, billVO, null, null);
				billVO = (nc.vo.ep.bx.JKVO)res;
				spzt = billVO.getParentVO().getSpzt();
				// TODO��������־���״̬��
			} else {
				throw new BusinessException("״̬���ԣ��޷��ջء�");
			}
		}
		
		return null;
	}
	
}
