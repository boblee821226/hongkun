package nc.api_oa.hkjt.impl.service._264X;

import java.util.HashMap;
import java.util.List;

import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._264X.BxHeadVO;
import nc.api_oa.hkjt.vo._264X.BxVO;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.prv.IBXBillPrivate;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.ep.bx.JKBXVO;
import nc.vo.pub.BusinessException;

public class N264XServiceDELETE {
	
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
		BxVO[] param = (BxVO[])paramObj;
		nc.vo.ep.bx.BXVO[] billVOs = new nc.vo.ep.bx.BXVO[param.length];
		/**
		 * 查找vo
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = getBxVO(param[i], account, billType);
		}		
		/**
		 * 循环处理：弃审、收回、删除
		 */
		for (int i = 0; i < billVOs.length; i++) {
			nc.vo.ep.bx.BXVO billVO = billVOs[i];
			if (billVO == null) throw new BusinessException("单据不存在");
			Integer spzt = billVO.getParentVO().getSpzt();	// 1=审批通过，3=提交，-1=自由
			if (1 == spzt) { // 弃审
				Object res = getIplatFormEntry().processAction("UNAPPROVE", "264X", null, billVO, null, null);
				billVO = ((nc.vo.ep.bx.BXVO[])res)[0];
			}
			if (3 == spzt) { // 收回
				Object res = getIplatFormEntry().processAction("UNSAVE", "264X", null, billVO, null, null);
				billVO = ((nc.vo.ep.bx.BXVO[])res)[0];
			}
			if (-1 == spzt) { // 删除
				Object res = getIplatFormEntry().processAction("DELETE", "264X", null, billVO, null, null);
				billVO = ((nc.vo.ep.bx.BXVO[])res)[0];
			}
		}
		
		System.out.println(param);
		
		return null;
	}
	
	/**
	 * 查找聚合VO
	 */
	private nc.vo.ep.bx.BXVO getBxVO(
			  BxVO srcBillVO
			, String account
			, String billType
			) throws BusinessException {
		BxHeadVO srcHVO = srcBillVO.getHead();
		String szgsStr = srcHVO.getSzgs();		// 所在公司
		HashMap<String,String> org_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(szgsStr);
		String pk_org = org_map.get("id");
		String djrq = srcHVO.getDjrq();
		String djbh = srcHVO.getDjbh();
		/**
		 * 根据条件查询出 报销单。
		 * 只查询出 oa传过来的单据，标记为 zyx26 不等于空
		 * (pk_org = '0001N510000000001SXL' AND djrq = '2020-08-19 00:00:00' AND djbh = 'jk-20200819-06' and QCBZ='N' and DR = 0 and djlxbm = '264X-Cxx-01')
		 */
		StringBuffer whereSQL = 
		new StringBuffer(" ( ")
				.append(" pk_org = '").append(pk_org).append("' ")
				.append(" and djrq = '").append(djrq).append("' ")
				.append(" and djbh = '").append(djbh).append("' ")
				.append(" and QCBZ = 'N' and DR = 0 and djlxbm like '264X-Cxx-%' ")
				.append(" and nvl(zyx26,'~') <> '~' ")
				.append(" ) ")
		;
		List<JKBXVO> list = getIBXBillPrivate().queryVOsByWhereSql(whereSQL.toString(), "bx");
		if (list == null || list.isEmpty()) return null;
		nc.vo.ep.bx.BXVO distBillVO = (nc.vo.ep.bx.BXVO)list.get(0);
		
		return distBillVO;
	}
	
}
