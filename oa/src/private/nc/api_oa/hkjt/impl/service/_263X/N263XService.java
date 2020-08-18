package nc.api_oa.hkjt.impl.service._263X;

import nc.api_oa.hkjt.vo._263X.JKVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;

public class N263XService {
	
	private static IplatFormEntry iplatFormEntry;
	static {
		iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
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
	public static Object doAction(String account, String billType, Object paramObj, String action, String userId) throws BusinessException  {
		JKVO[] param = (JKVO[])paramObj;
		nc.vo.ep.bx.JKVO[] billVOs = new nc.vo.ep.bx.JKVO[param.length];
		/**
		 * VO转换
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = getJKVO(param[i]);
		}
		/**
		 * 进行保存
		 */
		Object res = iplatFormEntry.processBatch(action, billType, null, billVOs, null, null);
		
		System.out.println(param);
		
		return "ok";
	}
	
	/**
	 * 翻译聚合VO
	 */
	private static nc.vo.ep.bx.JKVO getJKVO(nc.api_oa.hkjt.vo._263X.JKVO srcBillVO) {
		nc.vo.ep.bx.JKVO distBillVO = new nc.vo.ep.bx.JKVO();
		nc.vo.ep.bx.JKHeaderVO distHVO = getJKHeaderVO(srcBillVO.getHead());
		nc.vo.ep.bx.JKBusItemVO[] distBVOs = new nc.vo.ep.bx.JKBusItemVO[srcBillVO.getItems().length];
		for (int i = 0; i < srcBillVO.getItems().length; i++) {
			distBVOs[i] = getJKBusItemVO(srcBillVO.getItems()[i]);
		}
		
		return distBillVO;
	}
	
	/**
	 * 翻译表头vo
	 */
	private static nc.vo.ep.bx.JKHeaderVO getJKHeaderVO(nc.api_oa.hkjt.vo._263X.JKHeaderVO srcHVO) {
		nc.vo.ep.bx.JKHeaderVO distHVO = new nc.vo.ep.bx.JKHeaderVO();
		return distHVO;
	}
	
	/**
	 * 翻译表体vo
	 */
	private static nc.vo.ep.bx.JKBusItemVO getJKBusItemVO(nc.api_oa.hkjt.vo._263X.JKBusItemVO srcBVO) {
		nc.vo.ep.bx.JKBusItemVO distBVO = new nc.vo.ep.bx.JKBusItemVO();
		return distBVO;
	}
}
