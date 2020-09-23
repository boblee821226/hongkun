package nc.api_oa.hkjt.impl.service._263X;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._263X.JkVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.pub.IBXBillPublic;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class N263XService {
	
	private IplatFormEntry iplatFormEntry;
	private IplatFormEntry getIplatFormEntry() {
		if (iplatFormEntry == null) {
			iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		}
		return iplatFormEntry;
	}
	
	private IBXBillPublic iBXBillPublic;
	private IBXBillPublic getIBXBillPublic() {
		if (iBXBillPublic == null) {
			iBXBillPublic = (IBXBillPublic) NCLocator.getInstance().lookup(IBXBillPublic.class.getName());
		}
		return iBXBillPublic;
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
//		nc.vo.ep.bx.JKVO[] billVOs = new nc.vo.ep.bx.JKVO[param.length];
		ArrayList<nc.vo.ep.bx.JKVO> billVOlist = new ArrayList<>();
		/**
		 * VOת��
		 */
		for (int i = 0; i < param.length; i++) {
			/**
			 * �Ȳ�ѯ���Ƿ���ڵ���
			 */
			nc.vo.ep.bx.JKVO dbVO = N263XServiceQUERY.getJkVO(param[i], account, billType);
			if (dbVO != null) {
				// ������ڣ��Ͳ���Ҫ�ٱ��棬ֱ�ӷ���200
			} else {
				nc.vo.ep.bx.JKVO billVO = getJkVO(param[i], account, billType);
				billVOlist.add(billVO);
			}
		}		
		/**
		 * ѭ���������桢���
		 */
		for (int i = 0; i < billVOlist.size(); i++) {
			nc.vo.ep.bx.JKVO billVO = billVOlist.get(i);
			// ����
//			Object saveRes = getIplatFormEntry().processAction("WRITE", "263X-Cxx-01", null, billVO, null, null);
//			billVO = ((nc.vo.ep.bx.JKVO[])saveRes)[0];
			nc.vo.ep.bx.JKBXVO[] writeRes = getIBXBillPublic().save(new nc.vo.ep.bx.JKBXVO[]{billVO});
			billVO = (nc.vo.ep.bx.JKVO)writeRes[0];
			// �ύ
			Object saveRes = getIplatFormEntry().processAction("SAVE", billType, null, billVO, null, null);
			billVO = (nc.vo.ep.bx.JKVO)saveRes;
			/**
			 * ���
			 * ��Ҫ��ֵ����������ص��ֶ�
			 */
			/**
			HashMap<String,String> hmPfExParams = new HashMap<>();
			hmPfExParams.put("notechecked", "notechecked");
			billVO.setCmpIdMap(null);
			billVO.setChildrenFetched(true);
//			billVO.setNCClient(true);
			billVO.setJkdMap(null);
			billVO.setSettlementMap(null);
//			billVO.getParentVO().setApprover(userId);	// �����
//			billVO.getParentVO().setDjzt(1);			// ����״̬
//			UFDateTime currTime = new UFDateTime();
//			billVO.getParentVO().setShrq(currTime);		// �������
//			billVO.getParentVO().setShrq_show(currTime.getDate());	// �������
			PfBusinessLock pfLock = null;
			try {
				pfLock = new PfBusinessLock();
				pfLock.lock(new VOLockData(billVO, billType), new VOConsistenceCheck(billVO, billType));
				Object approveRes = getIplatFormEntry().processAction("APPROVE"+userId, "263X-Cxx-01", null, billVO, null, hmPfExParams);
				nc.vo.erm.common.MessageVO approveMsgVO = ((nc.vo.erm.common.MessageVO[])approveRes)[0];
				if (!approveMsgVO.isSuccess()) {
					throw new BusinessException(approveMsgVO.getErrorMessage());
				}
				billVO = (nc.vo.ep.bx.JKVO)approveMsgVO.getSuccessVO();
				
			} catch (Exception ex) {
				throw new BusinessException(ex);
			} finally {
				if (pfLock != null) {
					pfLock.unLock(); // �ͷ���
				}
			}
//			String billId = billVO.getParentVO().getPk_jkbx();
			// ������������û���Զ��ͷţ�ֻ���ֹ���Ҫ�ͷš�
//			PKLock.getInstance().releaseBatchLock(new String[]{billId}, userId, account);
			 */
		}
		
		return null;
	}
	
	/**
	 * ����ۺ�VO
	 */
	private nc.vo.ep.bx.JKVO getJkVO(
			  nc.api_oa.hkjt.vo._263X.JkVO srcBillVO
			, String account
			, String billType
			) throws BusinessException {
		/**
		 * ��ͷ��ͷ�������
		 */
		HashMap<String,String> param = new HashMap<>();
		param.put("account", account); // ����
//		param.put("szxm", srcBillVO.getItems()[0].getJkxm()); // ��֧��Ŀ�������һ�е� ��ֵ����ͷ
		/***END***/
		nc.vo.ep.bx.JKVO distBillVO = new nc.vo.ep.bx.JKVO();
		nc.vo.ep.bx.JKHeaderVO distHVO = getJkHeadVO(srcBillVO.getHead(), param);
		nc.vo.ep.bx.JKBusItemVO[] distBVOs = new nc.vo.ep.bx.JKBusItemVO[srcBillVO.getItems().length];
		for (int i = 0; i < srcBillVO.getItems().length; i++) {
//			param.put("rowno", ""+(i+1));
			distBVOs[i] = getJkItemVO(srcBillVO.getItems()[i], param);
		}
		
		distBillVO.setParentVO(distHVO);
		distBillVO.setChildrenVO(distBVOs);
		return distBillVO;
	}
	
	/**
	 * �����ͷvo
	 */
	private nc.vo.ep.bx.JKHeaderVO getJkHeadVO(
			  nc.api_oa.hkjt.vo._263X.JkHeadVO srcHVO
			, HashMap<String,String> param 
			) throws BusinessException {
		
		String account = param.get("account");		// ����
		String billTypeCode = "263X-Cxx-01";		// ��������code
		String billTypeId = "1001N51000000004ILKQ";	// ��������id
		UFDouble spje = PuPubVO.getUFDouble_NullAsZero(srcHVO.getSpje());	// ���
		UFDateTime currTime = new UFDateTime();		// ��ǰʱ��
		String djbh = srcHVO.getDjbh();				// ���ݱ��
		UFDate djrq = new UFDateTime(srcHVO.getDjrq()).getDate();	// ��������
		String bz = "1002Z0100000000001K1";		// ����
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();	// ����
		String fycdgsStr = srcHVO.getFycdgs();	// ���óе���˾��������Ա���ڹ�˾�������Ķ��÷��óе���˾��
		String fycdbmStr = srcHVO.getFycdbm();	// ���óе�����
		String szgsStr = srcHVO.getSzgs();		// ���ڹ�˾��ֻ�� ��Ա���ڹ�˾��
		String szbmStr = srcHVO.getSzbm();		// ���ڲ���
		HashMap<String,String> szgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(szgsStr);
		if (szgs_map == null) {throw new BusinessException("���ڹ�˾��ƥ�䣺" + szgsStr);}
		String szgs = szgs_map.get("id");
		String szgs_v = szgs_map.get("vid");
		HashMap<String,String> szbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(szbmStr);
		if (szbm_map == null) {throw new BusinessException("���ڲ��Ų�ƥ�䣺" + szbmStr);}
		String szbm = szbm_map.get("id");
		String szbm_v = szbm_map.get("vid");
		HashMap<String,String> fycdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(fycdgsStr);
		if (fycdgs_map == null) {throw new BusinessException("���óе���˾��ƥ�䣺" + fycdgsStr);}
		String fycdgs = fycdgs_map.get("id");
		String fycdgs_v = fycdgs_map.get("vid");
		String fkyhzh = fycdgs_map.get("account");		// ���������˻�
		HashMap<String,String> fycdbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(fycdbmStr);
		if (fycdbm_map == null) {throw new BusinessException("���óе����Ų�ƥ�䣺" + fycdbmStr);}
		String fycdbm = fycdbm_map.get("id");
		String fycdbm_v = fycdbm_map.get("vid");
		String userId = InvocationInfoProxy.getInstance().getUserId();
		String zdrStr = srcHVO.getZdr();	// �Ƶ���
		HashMap<String,String> zdr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(zdrStr);
		if (zdr_map == null) {throw new BusinessException("�Ƶ��˲�ƥ�䣺" + zdrStr);}
		String zdr = zdr_map.get("id");
		String zdrUserId = PuPubVO.getString_TrimZeroLenAsNull(zdr_map.get("userId"));
		if (zdrUserId == null) zdrUserId = ApiPubInfo.USER;
		/**
		 * HK 2020��9��17��19:07:30
		 * ���㷽ʽ��Ҫƥ���������ģʽ��name �� pk
		 * �������Ϊ 20������Ϊ��pk
		 * ��Ϊ 20������Ϊ�� name
		 */
		String jsfsStr = PuPubVO.getString_TrimZeroLenAsNull(srcHVO.getJsfs());	// ���㷽ʽ
		String jsfs = null;
		if (jsfsStr == null) {
			// �ӿڴ�����Ϊ�գ���������
		} else if (jsfsStr.length() == 20) {
			jsfs = jsfsStr;
		} else {
			HashMap<String,String> jsfs_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_balatype").get(jsfsStr);
			if (jsfs_map == null) {throw new BusinessException("���㷽ʽ��ƥ�䣺" + jsfsStr);}
			jsfs = jsfs_map.get("id");
		}
//		String szxm = param.get("szxm");	// ��֧��Ŀ
//		String skdxStr = srcHVO.getSkdx();	// �տ����0Ա�� 1��Ӧ�� 2�ͻ�
		Integer skdx = 0;
		
		String skr = null;
		String skyhzh = null;		// �տ����˻�
	
		String url = srcHVO.getUrl();	// url
		
		if (0 == skdx) { // Ա��
			String skrStr = srcHVO.getSkr();
			HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
			if (skr_map == null) {throw new BusinessException("�տ��˲�ƥ�䣺" + skrStr);}
			skr = skr_map.get("id");
			skyhzh = srcHVO.getGryhzh();
		}
		
		nc.vo.ep.bx.JKHeaderVO distHVO = new nc.vo.ep.bx.JKHeaderVO();
		distHVO.setBbhl(UFDouble.ONE_DBL);	// ����
		distHVO.setBbje(spje);		// ���
		distHVO.setBbye(spje);
		distHVO.setTotal(spje);
		distHVO.setYbje(spje);
		distHVO.setYbye(spje);
		distHVO.setZfbbje(spje);
		distHVO.setZfybje(spje);
		distHVO.setBzbm(bz);		// ����-�����
		
		distHVO.setCreationtime(currTime);	// ����ʱ��
		distHVO.setCreator(zdrUserId);		// ������
		distHVO.setOperator(userId);		// ����Ա
		
		distHVO.setDjbh(djbh);				// ���ݱ��
		distHVO.setDjdl("jk");				// ���ݴ���-����
		distHVO.setDjlxbm(billTypeCode);	// ��������
		distHVO.setPk_tradetypeid(billTypeId);// ��������id
		distHVO.setDjrq(djrq);			// ��������
		distHVO.setDjzt(1);				// ����״̬
		distHVO.setDr(0);
		
		distHVO.setPk_group(pk_group);	// ����
		distHVO.setPk_org(fycdgs);
		distHVO.setPk_org_v(fycdgs_v);
		distHVO.setPk_payorg(fycdgs);
		distHVO.setPk_payorg_v(fycdgs_v);
		distHVO.setPk_fiorg(fycdgs);
		distHVO.setFydwbm(fycdgs);		// ���óе���˾
		distHVO.setFydwbm_v(fycdgs_v);
		
		distHVO.setFydeptid(fycdbm);	// ���óе�����
		distHVO.setFydeptid_v(fycdbm_v);
		
		/**
		 * ��Ա��������֯������
		 */
		distHVO.setDwbm(szgs);			// ���ڹ�˾
		distHVO.setDwbm_v(szgs_v);		// ���ڹ�˾v
		distHVO.setDeptid(szbm);		// ���ڲ���
		distHVO.setDeptid_v(szbm_v);	// ���ڲ���v
		
		distHVO.setGlobalbbhl(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbye(UFDouble.ZERO_DBL);
		distHVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupbbhl(UFDouble.ZERO_DBL);
		distHVO.setGroupbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupbbye(UFDouble.ZERO_DBL);
		distHVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distHVO.setHkbbje(UFDouble.ZERO_DBL);
		distHVO.setHkybje(UFDouble.ZERO_DBL);

		distHVO.setIscheck(UFBoolean.FALSE);
		distHVO.setIsexpedited(UFBoolean.FALSE);
		distHVO.setIsinitgroup(UFBoolean.FALSE);
		distHVO.setIsmashare(UFBoolean.FALSE);
		distHVO.setIsneedimag(UFBoolean.FALSE);
		distHVO.setQcbz(UFBoolean.FALSE);
		
		distHVO.setJsfs(jsfs);			// ���㷽ʽ
		distHVO.setPaytarget(skdx);		// ��������
		distHVO.setFkyhzh(fkyhzh);		// ���������˻���������֯������ �����˻���
		distHVO.setJkbxr(zdr);			// ������-��Ա����
		
		distHVO.setReceiver(skr);		// �տ���-��Ա����
		distHVO.setSkyhzh(skyhzh);		// ���������˻�
		
		distHVO.setPayflag(1);			// ֧��״̬��1=δ֧����2=֧���У�3=֧����ɣ�4=֧��ʧ�ܣ�20=����֧����ɣ�99=�ֹ�֧����101=ȫ�������102=������ 
		distHVO.setQzzt(0);				// ����״̬��0=��1=�ǣ� 
		distHVO.setSpzt(-1);			// ����״̬��3=�ύ��-1=���ɣ�������ʱ����Ϊ�ύ״̬��
		distHVO.setSxbz(0);				// ��Ч��־
		
		distHVO.setZhrq(distHVO.getDjrq().getDateAfter(30)); // ��ٻ������ڣ���������+30�죩
//		distHVO.setZyx16("0.00");
//		distHVO.setZyx17("10000.00");
//		distHVO.setZyx18("10000.00");
		distHVO.setZyx19("1001N51000000011M2L4");	// �Ƿ�������ĸ����ʱĬ��Ϊ��
		distHVO.setZyx26(url);	// url
		
		/**
		 * param
		 */
		param.put("pk_org", fycdgs);	// ��˾
		param.put("pk_dept", fycdbm);	// ����
		param.put("skr", skr);			// �տ���
		param.put("skyhzh", skyhzh);	// �����տ��˻�
		param.put("skdx", ""+skdx);		// �տ����
		param.put("bxr", zdr);			// �����ˣ���Ա��
		/***END***/
		
		return distHVO;
	}
	
	/**
	 * �������vo
	 */
	private nc.vo.ep.bx.JKBusItemVO getJkItemVO(
			  nc.api_oa.hkjt.vo._263X.JkItemVO srcBVO
			, HashMap<String,String> param 
			) throws BusinessException {
		
//		String account = param.get("account");	// ����
		String szxm = srcBVO.getJkxm();			// ��֧��Ŀ�������Ŀ��
		UFDouble jshj = PuPubVO.getUFDouble_NullAsZero(srcBVO.getJe());	// ��˰�ϼ�
		String sxsm = srcBVO.getSxsm();	// ����˵��
//		String pk_org = param.get("pk_org");
//		String pk_dept = param.get("pk_dept");
//		String skr = param.get("skr");
//		String skyhzh = param.get("skyhzh");
//		Integer skdx = PuPubVO.getInteger_NullAs(param.get("skdx"),0);
//		String bxr = param.get("bxr");
//		Integer rowno = PuPubVO.getInteger_NullAs(param.get("rowno"),0);
		
		nc.vo.ep.bx.JKBusItemVO distBVO = new nc.vo.ep.bx.JKBusItemVO();
		
		distBVO.setDefitem5(sxsm);		// ����˵��
		distBVO.setDr(0);
		distBVO.setCjkbbje(UFDouble.ZERO_DBL);
		distBVO.setCjkybje(UFDouble.ZERO_DBL);
		distBVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalbbye(UFDouble.ZERO_DBL);
		distBVO.setGlobalcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupbbye(UFDouble.ZERO_DBL);
		distBVO.setGroupcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distBVO.setHkbbje(UFDouble.ZERO_DBL);
		distBVO.setHkybje(UFDouble.ZERO_DBL);
		distBVO.setRowno(null);
		distBVO.setSzxmid(szxm);
		distBVO.setTablecode("jk_busitem");
		distBVO.setAmount(jshj);
		distBVO.setBbje(jshj);
		distBVO.setBbye(jshj);
		distBVO.setYbje(jshj);
		distBVO.setYbye(jshj);
		distBVO.setZfbbje(jshj);
		distBVO.setZfybje(jshj);
		
		return distBVO;
	}
}
