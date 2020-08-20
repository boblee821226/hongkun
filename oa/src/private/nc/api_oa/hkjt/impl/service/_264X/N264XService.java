package nc.api_oa.hkjt.impl.service._264X;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._264X.BxVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.pub.IBXBillPublic;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uap.pf.PfProcessBatchRetObject;

public class N264XService {
	
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
	public Object doAction(String account, String billType, Object paramObj, String action, String userId) throws BusinessException  {
		BxVO[] param = (BxVO[])paramObj;
		nc.vo.ep.bx.BXVO[] billVOs = new nc.vo.ep.bx.BXVO[param.length];
		/**
		 * VOת��
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = getBxVO(param[i], account, billType);
		}
		/**
		 * ѭ���������桢���
		 */
		for (int i = 0; i < billVOs.length; i++) {
			nc.vo.ep.bx.BXVO billVO = billVOs[i];
			// ����(�ύ)
//			Object saveRes = getIplatFormEntry().processAction("WRITE", "264X-Cxx-01", null, billVO, null, null);
			nc.vo.ep.bx.JKBXVO[] saveRes = getIBXBillPublic().save(new nc.vo.ep.bx.JKBXVO[]{billVO});
			billVO = (nc.vo.ep.bx.BXVO)saveRes[0];
			// ���
//			Object approveRes = getIplatFormEntry().processAction("APPROVE", "264X", null, ((nc.vo.ep.bx.BXVO[])saveRes)[0], null, null);
		}
		
		return null;
	}
	
	/**
	 * ����ۺ�VO
	 */
	private nc.vo.ep.bx.BXVO getBxVO(
			nc.api_oa.hkjt.vo._264X.BxVO srcBillVO
			, String account
			, String billType
			) 
			throws BusinessException {
		/**
		 * ��ͷ��ͷ�������
		 */
		HashMap<String,String> param = new HashMap<>();
		param.put("account", account); // ����
		param.put("szxm", srcBillVO.getItems()[0].getZcxm()); // ��֧��Ŀ�������һ�е� ��ֵ����ͷ
		/***END***/
		nc.vo.ep.bx.BXVO distBillVO = new nc.vo.ep.bx.BXVO();
		nc.vo.ep.bx.BXHeaderVO distHVO = getBxHeadVO(srcBillVO.getHead(), param);
		nc.vo.ep.bx.BXBusItemVO[] distBVOs = new nc.vo.ep.bx.BXBusItemVO[srcBillVO.getItems().length];
		for (int i = 0; i < srcBillVO.getItems().length; i++) {
			param.put("rowno", ""+(i+1));
			distBVOs[i] = getBxItemVO(srcBillVO.getItems()[i], param);
		}
		
		distBillVO.setParentVO(distHVO);
		distBillVO.setChildrenVO(distBVOs);
		return distBillVO;
	}
	
	/**
	 * �����ͷvo
	 * ���㷽ʽ ���� �����˻�  jsfs=="1001N5100000008LDY9M"||jsfs=="1001N5100000009BN9JV","",getcolvalue("org_orgs","def2","pk_org",pk_org)
	 */
	private nc.vo.ep.bx.BXHeaderVO getBxHeadVO(
			  nc.api_oa.hkjt.vo._264X.BxHeadVO srcHVO
			, HashMap<String,String> param 
			)
			throws BusinessException {
		String account = param.get("account");		// ����
		String billTypeCode = "264X-Cxx-01";		// ��������code
		String billTypeId = "1001N51000000004LSR8";	// ��������id
		UFDouble spje = PuPubVO.getUFDouble_NullAsZero(srcHVO.getSpje());	// ���
		UFDateTime currTime = new UFDateTime();	// ��ǰʱ��
		String djbh = srcHVO.getDjbh();			// ���ݱ��
		UFDate djrq = new UFDateTime(srcHVO.getDjrq()).getDate();	// ��������
		String djbt = srcHVO.getDjbt();			// ���ݱ���
		String bz = "1002Z0100000000001K1";		// ����
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();	// ����
		String fycdgsStr = srcHVO.getFycdgs();	// ���óе���˾
		String fycdbmStr = srcHVO.getFycdbm();	// ���óе�����
		String szgsStr = srcHVO.getSzgs();		// ���ڹ�˾
		String szbmStr = srcHVO.getSzbm();		// ���ڲ���
		HashMap<String,String> org_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(szgsStr);
		String pk_org = org_map.get("id");
		String pk_org_v = org_map.get("vid");
		String fkyhzh = org_map.get("account");		// ���������˻�
		HashMap<String,String> dept_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(szbmStr);
		String pk_dept = dept_map.get("id");
		String pk_dept_v = dept_map.get("vid");
		HashMap<String,String> fycdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(fycdgsStr);
		String fycdgs = fycdgs_map.get("id");
		String fycdgs_v = fycdgs_map.get("vid");
		HashMap<String,String> fycdbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(fycdbmStr);
		String fycdbm = fycdbm_map.get("id");
		String fycdbm_v = fycdbm_map.get("vid");
		String userId = InvocationInfoProxy.getInstance().getUserId();
		String zdrStr = srcHVO.getZdr();	// �Ƶ���
		String zdr = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(zdrStr).get("id");
		String jsfs = srcHVO.getJsfs();		// ���㷽ʽ
		String szxm = param.get("szxm");	// ��֧��Ŀ
		String skdxStr = srcHVO.getSkdx();	// �տ����0Ա�� 1��Ӧ�� 2�ͻ�
		Integer skdx = 0;
		if ("��Ӧ��".equals(skdxStr)) {
			skdx = 1;
		} else if ("�ͻ�".equals(skdxStr)) {
			skdx = 2;
		}
		
		String skr = null;
		String skyhzh = null;		// �տ����˻�
		String gys = null;			// ��Ӧ��
		String kh = null;			// �ͻ�
		String custaccount = null; 	// �����˻�
	
		String url = srcHVO.getUrl();	// url
		
		if (0 == skdx) { // Ա��
//			gys = null;
//			kh = null;
//			custaccount = null;
			String skrStr = srcHVO.getSkr();
			skr = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr).get("id");
			skyhzh = srcHVO.getGryhzh();
		} else if (1 == skdx) {	// ��Ӧ��
//			skr = null;
//			skyhzh = null;
//			kh = null;
			gys = srcHVO.getGys();
			custaccount = srcHVO.getKsyhzh();
		} else if (2 == skdx) {	// �ͻ�
//			skr = null;
//			skyhzh = null;
//			gys = null;
			kh = srcHVO.getKh();
			custaccount = srcHVO.getKsyhzh();
		}
		
		nc.vo.ep.bx.BXHeaderVO distHVO = new nc.vo.ep.bx.BXHeaderVO();
		distHVO.setBbhl(UFDouble.ONE_DBL);			// ���һ��� 1
		distHVO.setBbje(spje);						// ���ҽ��
		distHVO.setTotal(spje);
		distHVO.setYbje(spje);
		distHVO.setZfbbje(spje);
		distHVO.setZfybje(spje);
		distHVO.setBzbm(bz);	// ����-�����
		
		distHVO.setCreationtime(currTime);			// ����ʱ��
		distHVO.setCreator(userId);	// ������
		distHVO.setOperator(userId);// ����Ա
		
		distHVO.setDjbh(djbh);				// ���ݱ��
		distHVO.setDjdl("bx");				// ���ݴ���-����
		distHVO.setDjlxbm(billTypeCode);	// ��������
		distHVO.setPk_tradetypeid(billTypeId);// ��������id
		distHVO.setDjrq(djrq);			// ��������
		distHVO.setDjzt(1);				// ����״̬
		distHVO.setDr(0);
		
		distHVO.setPk_group(pk_group);	// ����
		distHVO.setDwbm(pk_org);		// ��˾
		distHVO.setDwbm_v(pk_org_v);	// ��˾v
		distHVO.setPk_org(pk_org);
		distHVO.setPk_org_v(pk_org_v);
		distHVO.setPk_payorg(pk_org);
		distHVO.setPk_payorg_v(pk_org_v);
		distHVO.setPk_fiorg(pk_org);
		distHVO.setFydwbm(fycdgs);		// ���óе���˾
		distHVO.setFydwbm_v(fycdgs_v);
		
		distHVO.setDeptid(pk_dept);		// ����
		distHVO.setDeptid_v(pk_dept_v);	// ����v
		distHVO.setFydeptid(fycdbm);	// ���óе�����
		distHVO.setFydeptid_v(fycdbm_v);
		
		distHVO.setCjkbbje(UFDouble.ZERO_DBL);
		distHVO.setCjkybje(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbhl(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalcjkbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupbbhl(UFDouble.ZERO_DBL);
		distHVO.setGroupbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupcjkbbje(UFDouble.ZERO_DBL);
		distHVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distHVO.setHkbbje(UFDouble.ZERO_DBL);
		distHVO.setHkybje(UFDouble.ZERO_DBL);
		
		distHVO.setFlexible_flag(UFBoolean.FALSE);
		distHVO.setIscheck(UFBoolean.FALSE);
		distHVO.setIscostshare(UFBoolean.FALSE);
		distHVO.setIsexpamt(UFBoolean.FALSE);
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
		distHVO.setCustaccount(custaccount);// �����˻�
		distHVO.setCustomer(kh);			// �ͻ�
		distHVO.setHbbm(gys);				// ��Ӧ��
		
		distHVO.setSpzt(-1);			// ����״̬��3=�ύ��-1=���ɣ�������ʱ����Ϊ�ύ״̬��
		distHVO.setSxbz(0);			// ��Ч��־
		distHVO.setSzxmid(szxm);	// ��֧��Ŀ
		
		distHVO.setZyx12(djbt);		// ����
		distHVO.setZyx18("1001N51000000011M2L4"); // �Ƿ�������ĸ����ʱĬ��Ϊ��
		distHVO.setZyx26(url);	// url
		
		/**
		 * param
		 */
		param.put("pk_org", pk_org);	// ��˾
		param.put("pk_dept", pk_dept);	// ����
		param.put("skr", skr);			// �տ���
		param.put("kh", kh);			// �ͻ�
		param.put("gys", gys);			// ��Ӧ��
		param.put("custaccount", custaccount);	// ���������˻�
		param.put("skyhzh", skyhzh);	// �����տ��˻�
		param.put("skdx", ""+skdx);		// �տ����
		param.put("bxr", zdr);			// �����ˣ���Ա��
		/***END***/
		
		return distHVO;
	}
	
	/**
	 * �������vo
	 */
	private nc.vo.ep.bx.BXBusItemVO getBxItemVO(
			  nc.api_oa.hkjt.vo._264X.BxItemVO srcBVO
			, HashMap<String,String> param 
			) 
			throws BusinessException {
		String account = param.get("account");	// ����
		String dkfsStr = srcBVO.getDkfs();		// �ֿ۷�ʽ
		String dkfs = ApiPubInfo.CACHE_DOC.get(account).get("bd_defdoc_dkfs").get(dkfsStr).get("id");
		String szxm = srcBVO.getZcxm();			// ��֧��Ŀ
		UFDouble jshj = PuPubVO.getUFDouble_NullAsZero(srcBVO.getJshjje());	// ��˰�ϼ�
		UFDouble sl = PuPubVO.getUFDouble_NullAsZero(srcBVO.getSl());		// ˰��
		UFDouble se = PuPubVO.getUFDouble_NullAsZero(srcBVO.getSe());		// ˰��
		UFDouble wsje = PuPubVO.getUFDouble_NullAsZero(srcBVO.getWsje());	// ��˰���
		String sxsm = srcBVO.getSxsm();	// ����˵��
		String pk_org = param.get("pk_org");
		String pk_dept = param.get("pk_dept");
		String skr = param.get("skr");
		String kh = param.get("kh");
		String gys = param.get("gys");
		String custaccount = param.get("custaccount");
		String skyhzh = param.get("skyhzh");
		Integer skdx = PuPubVO.getInteger_NullAs(param.get("skdx"),0);
		String bxr = param.get("bxr");
		Integer rowno = PuPubVO.getInteger_NullAs(param.get("rowno"),0);
		
		nc.vo.ep.bx.BXBusItemVO distBVO = new nc.vo.ep.bx.BXBusItemVO();
		distBVO.setDefitem12(sl.toString());	// ˰��
		distBVO.setDefitem13(wsje.toString());	// ��˰���
		distBVO.setDefitem15(se.toString());	// ˰��
		distBVO.setDefitem7(jshj.toString());	// ��˰�ϼ�
		distBVO.setDefitem14(dkfs);		// �ֿ۷�ʽ
		distBVO.setDefitem5(sxsm);		// ����˵��
		distBVO.setDwbm(pk_org);		// ��˾
		distBVO.setDeptid(pk_dept);		// ����
		distBVO.setDr(0);
		distBVO.setCjkbbje(UFDouble.ZERO_DBL);
		distBVO.setCjkybje(UFDouble.ZERO_DBL);
		distBVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distBVO.setHkbbje(UFDouble.ZERO_DBL);
		distBVO.setHkybje(UFDouble.ZERO_DBL);
		distBVO.setPaytarget(skdx);			// �տ����
		distBVO.setCustomer(kh);			// �ͻ�
		distBVO.setHbbm(gys);				// ��Ӧ��
		distBVO.setCustaccount(custaccount);// �����˻�
		distBVO.setSkyhzh(skyhzh);			// �����˻�
		distBVO.setReceiver(skr);	// �տ���
		distBVO.setJkbxr(bxr);		// ������
		distBVO.setRowno(rowno);	// �к�
		distBVO.setSzxmid(szxm);	// ��֧��Ŀ
		distBVO.setTablecode("arap_bxbusitem");
		distBVO.setAmount(jshj);	// ���
		distBVO.setBbje(jshj);
		distBVO.setYbje(jshj);
		distBVO.setZfbbje(jshj);
		distBVO.setZfybje(jshj);
		
		return distBVO;
	}
}
