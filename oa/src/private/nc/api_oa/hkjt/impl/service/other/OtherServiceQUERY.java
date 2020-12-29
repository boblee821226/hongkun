package nc.api_oa.hkjt.impl.service.other;

import java.util.HashMap;

import nc.api_oa.hkjt.vo._263X.JkBxQueryVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.prv.IBXBillPrivate;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

public class OtherServiceQUERY {
		
	private static IBXBillPrivate iBXBillPrivate;
	private static IBXBillPrivate getIBXBillPrivate() {
		if (iBXBillPrivate == null) {
			iBXBillPrivate = (IBXBillPrivate) NCLocator.getInstance().lookup(IBXBillPrivate.class.getName());
		}
		return iBXBillPrivate;
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
		AggregatedValueObject[] billVOs = new AggregatedValueObject[param.length];
		/**
		 * ����vo
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = getBillVO(param[i], account, billType);
		}
		/**
		 * ѭ������
		 */
		JkBxQueryVO[] queryVOs = new JkBxQueryVO[billVOs.length];
		for (int i = 0; i < billVOs.length; i++) {
//			nc.vo.ep.bx.JKVO billVO = billVOs[i];
//			if (billVO == null) throw new BusinessException("���ݲ�����");
//			Integer spzt = billVO.getParentVO().getSpzt();	// 0=����δͨ����1=����ͨ����2=���������У�3=�ύ��-1=���ɣ� 
//			Boolean callable = Boolean.FALSE;
//			if (spzt == 3 || spzt == -1) { // ֻ�� �ύ̬ �� ����̬ �����ջ�
//				callable = Boolean.TRUE;
//			}
//			JkHeadVO jkHeadVO = param[i].getHead();
//			queryVOs[i] = new JkBxQueryVO();
//			queryVOs[i].setCallable(callable);
//			queryVOs[i].setDjbh(jkHeadVO.getDjbh());
//			queryVOs[i].setDjrq(jkHeadVO.getDjrq());
//			queryVOs[i].setSzgs(jkHeadVO.getSzgs());
		}
		
		return queryVOs;
	}
	
	/**
	 * ���Ҿۺ�VO
	 */
	public static AggregatedValueObject getBillVO(
			  HashMap param
			, String account
			, String billType
			) throws BusinessException {
		AggregatedValueObject billVO = null;
		HashMap head = (HashMap)param.get("head");
		Integer requestid = (Integer)head.get("requestid");
		return billVO;
	}
	
}
