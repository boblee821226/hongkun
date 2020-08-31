package nc.api_oa.hkjt.impl.service._263X;

import java.util.HashMap;
import java.util.List;

import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._263X.JkHeadVO;
import nc.api_oa.hkjt.vo._263X.JkVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.prv.IBXBillPrivate;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.ep.bx.JKBXVO;
import nc.vo.pub.BusinessException;

public class N263XServiceDELETE {
	
	private IplatFormEntry iplatFormEntry;
	private IplatFormEntry getIplatFormEntry() {
		if (iplatFormEntry == null) {
			iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		}
		return iplatFormEntry;
	}
	
	private IBXBillPrivate iBXBillPrivate;
	private IBXBillPrivate getIBXBillPrivate() {
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
	public Object doAction(String account, String billType, Object paramObj, String action, String userId) 
			throws BusinessException  {
		JkVO[] param = (JkVO[])paramObj;
		nc.vo.ep.bx.JKVO[] billVOs = new nc.vo.ep.bx.JKVO[param.length];
		/**
		 * ����vo
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = getJkVO(param[i], account, billType);
		}		
		/**
		 * ѭ�����������ջء�ɾ��
		 */
		for (int i = 0; i < billVOs.length; i++) {
			nc.vo.ep.bx.JKVO billVO = billVOs[i];
			if (billVO == null) throw new BusinessException("���ݲ�����");
			Integer spzt = billVO.getParentVO().getSpzt();	// 1=����ͨ����3=�ύ��-1=����
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
	
	/**
	 * ���Ҿۺ�VO
	 */
	private nc.vo.ep.bx.JKVO getJkVO(
			  nc.api_oa.hkjt.vo._263X.JkVO srcBillVO
			, String account
			, String billType
			) throws BusinessException {
		JkHeadVO srcHVO = srcBillVO.getHead();
		String szgsStr = srcHVO.getSzgs();		// ���ڹ�˾
		HashMap<String,String> org_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(szgsStr);
		String pk_org = org_map.get("id");
		String djrq = srcHVO.getDjrq();
		String djbh = srcHVO.getDjbh();
		/**
		 * ����������ѯ�� ����
		 * ֻ��ѯ�� oa�������ĵ��ݣ����Ϊ zyx26 �����ڿ�
		 * (pk_org = '0001N510000000001SXL' AND djrq = '2020-08-19 00:00:00' AND djbh = 'jk-20200819-06' and QCBZ='N' and DR = 0 and djlxbm = '263X-Cxx-01')
		 */
		StringBuffer whereSQL = 
		new StringBuffer(" where ( ")
				.append(" pk_org = '").append(pk_org).append("' ")
				.append(" and djrq = '").append(djrq).append("' ")
				.append(" and djbh = '").append(djbh).append("' ")
				.append(" and QCBZ = 'N' and DR = 0 and djlxbm = '263X-Cxx-01' ")
				.append(" and nvl(zyx26,'~') <> '~' ")
				.append(" ) ")
		;
		List<JKBXVO> list = getIBXBillPrivate().queryVOsByWhereSql(whereSQL.toString(), "jk");
		if (list == null || list.isEmpty()) return null;
		nc.vo.ep.bx.JKVO distBillVO = (nc.vo.ep.bx.JKVO)list.get(0);
		
		return distBillVO;
	}
	
}
