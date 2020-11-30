package nc.api_oa.hkjt.impl.service._264X;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.impl.service.DocService;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._264X.BxVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.pub.IBXBillPublic;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

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
//		nc.vo.ep.bx.BXVO[] billVOs = new nc.vo.ep.bx.BXVO[param.length];
		ArrayList<nc.vo.ep.bx.BXVO> billVOlist = new ArrayList<>();
		/**
		 * VOת��
		 */
		for (int i = 0; i < param.length; i++) {
			/**
			 * �Ȳ�ѯ���Ƿ���ڵ���
			 */
			nc.vo.ep.bx.BXVO dbVO = N264XServiceQUERY.getBxVO(param[i], account, billType);
			if (dbVO != null) {
				// ������ڣ��Ͳ���Ҫ�ٱ��棬ֱ�ӷ���200
			} else {
				nc.vo.ep.bx.BXVO billVO = getBxVO(param[i], account, billType);
				billVOlist.add(billVO);
			}
		}
		/**
		 * ѭ���������桢���
		 */
		for (int i = 0; i < billVOlist.size(); i++) {
			nc.vo.ep.bx.BXVO billVO = billVOlist.get(i);
			// ����
//			Object saveRes = getIplatFormEntry().processAction("WRITE", "264X-Cxx-01", null, billVO, null, null);
			nc.vo.ep.bx.JKBXVO[] writeRes = getIBXBillPublic().save(new nc.vo.ep.bx.JKBXVO[]{billVO});
			billVO = (nc.vo.ep.bx.BXVO)writeRes[0];
			// �ύ
			Object saveRes = getIplatFormEntry().processAction("SAVE", billType, null, billVO, null, null);
			billVO = (nc.vo.ep.bx.BXVO)saveRes;
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
		 * ��������
		 */
		HashMap<String, String> djlx_MAP = new HashMap<>();
		djlx_MAP.put("264X-Cxx-01", "1001N51000000004LSR8");	// �ճ�����������
		djlx_MAP.put("264X-Cxx-02", "1001N51000000004MN92");	// ��������������
		djlx_MAP.put("264X-Cxx-03", "1001N51000000004MN9R");	// ���ʸ���������
		djlx_MAP.put("264X-Cxx-04", "1001N51000000004MNGQ");	// ���̸���������
		djlx_MAP.put("264X-Cxx-05", "1001N5100000006NLZTC");	// ��̯����������
		/**
		 * ��ͷ��ͷ�������
		 */
		HashMap<String,String> param = new HashMap<>();
		param.put("account", account); // ����
		param.put("szxm", srcBillVO.getItems()[0].getZcxm()); // ��֧��Ŀ�������һ�е� ��ֵ����ͷ
		param.put("billTypeCode", billType);			// ��������code
		param.put("billTypeId", djlx_MAP.get(billType));// ��������id
		/***END***/
		nc.vo.ep.bx.BXVO distBillVO = new nc.vo.ep.bx.BXVO();
		nc.vo.ep.bx.BXHeaderVO distHVO = getBxHeadVO(srcBillVO.getHead(), param);
		nc.vo.ep.bx.BXBusItemVO[] distBVOs = new nc.vo.ep.bx.BXBusItemVO[srcBillVO.getItems().length];
		for (int i = 0; i < srcBillVO.getItems().length; i++) {
			param.put("rowno", ""+(i+1));
			distBVOs[i] = getBxItemVO(srcBillVO.getItems()[i], param);
		}
		nc.vo.erm.costshare.CShareDetailVO[] distSVOs = null;
		if (srcBillVO.getShares() != null && srcBillVO.getShares().length > 0) {
			distSVOs = new nc.vo.erm.costshare.CShareDetailVO[srcBillVO.getShares().length];
			for (int i = 0; i < srcBillVO.getShares().length; i++) {
				param.put("rowno", ""+(i+1));
				distSVOs[i] = getBxShareVO(srcBillVO.getShares()[i], param);
			}
		}
		
		distBillVO.setParentVO(distHVO);
		distBillVO.setChildrenVO(distBVOs);		// ��ϸ
		distBillVO.setcShareDetailVo(distSVOs);	// ��̯ҳǩ
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
		String billTypeCode = param.get("billTypeCode");// ��������code
		String billTypeId = param.get("billTypeId");	// ��������id
//		String billTypeCode = "264X-Cxx-01";		// ��������code
//		String billTypeId = "1001N51000000004LSR8";	// ��������id
		UFDouble spje = PuPubVO.getUFDouble_NullAsZero(srcHVO.getSpje());	// ���
		UFDateTime currTime = new UFDateTime();	// ��ǰʱ��
		String djbh = srcHVO.getDjbh();			// ���ݱ��
		UFDate djrq = new UFDateTime(srcHVO.getDjrq()).getDate();	// ��������
		String djbt = srcHVO.getDjbt();			// ���ݱ���
		String bz = "1002Z0100000000001K1";		// ����
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();	// ����
		String fycdgsStr = srcHVO.getFycdgs();	// ���óе���˾��������Ա���ڹ�˾�������Ķ��÷��óе���˾��
		String fycdbmStr = srcHVO.getFycdbm();	// ���óе�����
		String szgsStr = srcHVO.getSzgs();		// ���ڹ�˾��ֻ�� ��Ա���ڹ�˾��
		String szbmStr = srcHVO.getSzbm();		// ���ڲ���
		UFBoolean isShare = PuPubVO.getUFBoolean_NullAs(srcHVO.getFt(), UFBoolean.FALSE);	// �Ƿ��̯
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
		if (zdr_map == null) { // �������Ϊ�գ�����Ҫ���»��� ���²��ң���ʱ�ȼ�����Ա�����ϣ��������� �������˵��
			DocService.doAction(account, "bd_psndoc");
			zdr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(zdrStr);
		}
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
			String skrStr = srcHVO.getSkr();
			HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
			if (skr_map == null) {throw new BusinessException("�տ��˲�ƥ�䣺" + skrStr);}
			skr = skr_map.get("id");
			skyhzh = srcHVO.getGryhzh();
		} else if (1 == skdx) {	// ��Ӧ��
			gys = srcHVO.getGys();
			custaccount = srcHVO.getKsyhzh();
			// �տ���
			String skrStr = PuPubVO.getString_TrimZeroLenAsNull(srcHVO.getSkr());
			if (skrStr != null) {
				HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
				if (skr_map == null) {throw new BusinessException("�տ��˲�ƥ�䣺" + skrStr);}
				skr = skr_map.get("id");
			}
		} else if (2 == skdx) {	// �ͻ�
			kh = srcHVO.getKh();
			custaccount = srcHVO.getKsyhzh();
			// �տ��ˣ���Ϊ�գ����Ϊ�վͲ�������
			String skrStr = PuPubVO.getString_TrimZeroLenAsNull(srcHVO.getSkr());
			if (skrStr != null) {
				HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
				if (skr_map == null) {throw new BusinessException("�տ��˲�ƥ�䣺" + skrStr);}
				skr = skr_map.get("id");
			}
		}
		
		/**
		 * ���̸��� ר��
		 */
		String kgrq = srcHVO.getKgrq();		// ��������
		String jgrq = srcHVO.getJgrq();		// ��������
		String gcwxsqd = srcHVO.getGcwxsqd();	// ����ά�����뵥
		String xmmc = srcHVO.getXmmc();	// ��Ŀ����
		/***END***/
		
		nc.vo.ep.bx.BXHeaderVO distHVO = new nc.vo.ep.bx.BXHeaderVO();
		distHVO.setBbhl(UFDouble.ONE_DBL);			// ���һ��� 1
		distHVO.setBbje(spje);						// ���ҽ��
		distHVO.setTotal(spje);
		distHVO.setYbje(spje);
		distHVO.setZfbbje(spje);
		distHVO.setZfybje(spje);
		distHVO.setBzbm(bz);	// ����-�����
		
		distHVO.setCreationtime(currTime);			// ����ʱ��
		distHVO.setCreator(zdrUserId);	// ������
		distHVO.setOperator(userId);// ����Ա
		
		distHVO.setDjbh(djbh);				// ���ݱ��
		distHVO.setDjdl("bx");				// ���ݴ���-����
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
		distHVO.setIscostshare(isShare);	// �Ƿ��̯
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
		
		distHVO.setZyx16(kgrq);	// ��������
		distHVO.setZyx17(jgrq);	// ��������
		distHVO.setZyx1(xmmc);	// ��Ŀ����
		
		/**
		 * param
		 */
		param.put("pk_group", pk_group);// ����
		param.put("pk_org", fycdgs);	// ��˾
		param.put("pk_dept", fycdbm);	// ����
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
		String dkfs = null;
		if (dkfsStr != null) {
			HashMap<String,String> dkfs_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_defdoc_dkfs").get(dkfsStr);
			if (dkfs_map == null) {throw new BusinessException("�ֿ۷�ʽ��ƥ�䣺" + dkfsStr);}
			dkfs = dkfs_map.get("id");
		}
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
		String billTypeCode = PuPubVO.getString_TrimZeroLenAsNull(param.get("billTypeCode"));
		
		nc.vo.ep.bx.BXBusItemVO distBVO = new nc.vo.ep.bx.BXBusItemVO();
		
		distBVO.setDefitem5(sxsm);				// ����˵��
		distBVO.setDefitem7(jshj.toString());	// ��˰�ϼ�
		
		// �� ��̯�� ���������Ž��и�ֵ��
		if (!"264X-Cxx-05".equals(billTypeCode)) {
			distBVO.setDefitem20(sl.toString());	// ˰��
			distBVO.setDefitem21(dkfs);				// �ֿ۷�ʽ
			distBVO.setDefitem22(se.toString());	// ˰��
			distBVO.setDefitem23(wsje.toString());	// ��˰���
		}
		
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
	
	/**
	 * ���� ���÷�̯vo
	 */
	private nc.vo.erm.costshare.CShareDetailVO getBxShareVO(
			  nc.api_oa.hkjt.vo._264X.BxShareVO srcSVO
			, HashMap<String,String> param 
			) 
			throws BusinessException {
		/**
		 *  assume_amount=600.00
			assume_dept=1001N5100000006VD3BB
			assume_org=0001N510000000001SXL
			bbhl=1
			bbje=600.00
			dr=0
			globalbbhl=0.00000000
			globalbbje=0.00000000
			groupbbhl=0.00000000
			groupbbje=0.00000000
			hbbm=1001N510000000000NW6
			pk_group=0001N510000000000EGY
			pk_iobsclass=1001N5100000000EYKBS
			share_ratio=60.00
		 */
		String account = param.get("account");	// ����
		UFDouble cdje = PuPubVO.getUFDouble_NullAsZero(srcSVO.getCdje());	// �е����
		UFDouble ftbl = PuPubVO.getUFDouble_NullAsZero(srcSVO.getFtbl());	// ��̯����
		Double se = srcSVO.getSe();	// ˰��
		Double sl = srcSVO.getSl();	// ˰��
		String cdgsStr = srcSVO.getCdgs();	// �е���˾
		HashMap<String,String> cdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(cdgsStr);
		if (cdgs_map == null) {throw new BusinessException("�е���˾��ƥ�䣺" + cdgsStr);}
		String cdgs = cdgs_map.get("id");
		String cdbmStr = srcSVO.getCdbm();	// �е�����
		HashMap<String,String> cdbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(cdbmStr);
		if (cdbm_map == null) {throw new BusinessException("�е����Ų�ƥ�䣺" + cdbmStr);}
		String cdbm = cdbm_map.get("id");
		String zcxm = srcSVO.getZcxm();			// ��֧��Ŀ
		String kh = param.get("kh");			// �ͻ�
		String gys = param.get("gys");			// ��Ӧ��
		String pk_group = param.get("pk_group");// ����
		/**
		 * 
		 * HashMap<String,String> dept_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(szbmStr);
		String pk_dept = dept_map.get("id");
		String pk_dept_v = dept_map.get("vid");
		HashMap<String,String> fycdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(fycdgsStr);
		String fycdgs = fycdgs_map.get("id");
		 */
		nc.vo.erm.costshare.CShareDetailVO distSVO = new nc.vo.erm.costshare.CShareDetailVO();
		distSVO.setAssume_amount(cdje);
		distSVO.setAssume_dept(cdbm);
		distSVO.setAssume_org(cdgs);
		distSVO.setBbhl(UFDouble.ONE_DBL);
		distSVO.setBbje(cdje);
		distSVO.setDr(0);
		distSVO.setGlobalbbhl(UFDouble.ZERO_DBL);
		distSVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distSVO.setGroupbbhl(UFDouble.ZERO_DBL);
		distSVO.setGroupbbje(UFDouble.ZERO_DBL);
		distSVO.setHbbm(gys);
		distSVO.setCustomer(kh);
		distSVO.setPk_group(pk_group);
		distSVO.setShare_ratio(ftbl);
		distSVO.setPk_iobsclass(zcxm);
		distSVO.setStatus(VOStatus.NEW);	// ����vo״̬ΪNEW�����ܽ��б���
		distSVO.setDefitem1(sl == null ? null : sl.toString());	// ˰��
		distSVO.setDefitem2(se == null ? null : se.toString());	// ˰��
		
		return distSVO;
	}
}
