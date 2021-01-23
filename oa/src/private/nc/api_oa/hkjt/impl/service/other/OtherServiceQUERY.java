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
		 * 查找vo
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = getBillVO(param[i], account, billType);
		}
		/**
		 * 循环处理：
		 */
		JkBxQueryVO[] queryVOs = new JkBxQueryVO[billVOs.length];
		for (int i = 0; i < billVOs.length; i++) {
			
		}
		
		return queryVOs;
	}
	
	/**
	 * 查找聚合VO
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
