package nc.bs.gl.voucher;

import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import nc.bs.logging.Logger;
import nc.vo.glcom.tools.GLPubProxy;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.sm.funcreg.ModuleVO;
/**
 * ϵͳģ�顣 �������ڣ�(2003-9-4 11:57:32)
 *
 * @author��������
 */
public class DapSystemDataCache {
	public static DapSystemDataCache instance = new DapSystemDataCache();

	private static HashMap datacache = new HashMap();

	private static HashMap code_datacache = new HashMap();
	

	/**
	 * DapSystemDataCache ������ע�⡣
	 */
	public DapSystemDataCache() {
		super();
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2003-9-4 12:01:18)
	 */
	public void clear() {
		datacache.clear();
		code_datacache.clear();

	}

	/**
	 * �õ����е�ϵͳ���ݻ��档 �������ڣ�(2002-10-16 14:49:32)
	 *
	 * @return java.util.HashMap
	 */
	public ModuleVO[] getDapSystem() {
		if (datacache.size() == 0) {
			ModuleVO[] vos = null;
			try {
				Collection c = GLPubProxy.getRemoteIUAPQueryBS().retrieveAll(ModuleVO.class);
				vos = new ModuleVO[c.size()];
				c.toArray(vos);
				
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}


			if (vos != null) {
				datacache.put("@@@@", vos);
				for (int i = 0; i < vos.length; i++) {
					//Эͬƾ֤��typecode�������ݷó�ͻ�������ǣ���ʱ����취��ֱ��д��
					if(vos[i].getSystypecode().trim().toUpperCase().equals("CV")){
						vos[i].setResid("M200213");
						vos[i].setSystypename("Эͬƾ֤");
						vos[i].setModuleid("2002Z313");
						vos[i].setIsaccount(UFBoolean.TRUE);
					}
					code_datacache.put(vos[i].getSystypecode(), vos[i]);
				}
				return vos;
			} else {
				return null;
			}

		} else {
			return (ModuleVO[]) datacache.get("@@@@");
		}
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2002-7-1 17:16:53)
	 *
	 * @return java.util.HashMap
	 */
	public ModuleVO getDapsystem(String dapsystemcode) throws Exception {
		if (dapsystemcode == null || dapsystemcode.trim().length() == 0)
			return null;
		if(code_datacache.size()==0)
			getDapSystem();
		StringTokenizer st = new StringTokenizer(dapsystemcode, ",");
		ModuleVO tmp_dapsystem = null;
		while (st.hasMoreTokens()) {
			String system = (String) st.nextToken();
			tmp_dapsystem = (ModuleVO) code_datacache.get(system.toUpperCase());
			if (tmp_dapsystem == null) {
				tmp_dapsystem = (ModuleVO) code_datacache.get(system.toLowerCase());
			}
			
			/**
			 * ����
			 * ģ����� һ��Ҫ ȫ��д  or ȫСд����PS��Ϊʲôϵͳ����ô�����أ���
			 * ��ԭ����  �����ҡ�
			 * ���  2016��7��14��14:59:51
			 */
			if (tmp_dapsystem == null) {
				tmp_dapsystem = (ModuleVO) code_datacache.get(system);
			}
			/***END***/
			
			if (tmp_dapsystem == null) {
				throw new Exception( nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2002gl55","UPP2002gl55-000266")/*@res "û�ж�Ӧ����Դϵͳ��"*/);
			}
		}
		return tmp_dapsystem;
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2002-7-1 17:16:53)
	 *
	 * @return java.util.HashMap
	 */
	public String getDapsystemName(String dapsystemcode) throws Exception {
		if (dapsystemcode == null || dapsystemcode.trim().length() == 0)
			return null;
		if(code_datacache.size()==0)
			getDapSystem();
		ModuleVO tmp_dapsystem = (ModuleVO) code_datacache.get(dapsystemcode);
		if (tmp_dapsystem == null)
			throw new Exception( nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2002gl55","UPP2002gl55-000266")/*@res "û�ж�Ӧ����Դϵͳ��"*/ );
		return tmp_dapsystem.getSystypename();
	}

	/**
	 * �˴����뷽��˵���� �������ڣ�(2002-7-1 11:28:34)
	 *
	 * @return nc.ui.gl.vouchertools.VoucherDataCenter
	 */
	public static DapSystemDataCache getInstance() {
		return instance;
	}

	public static DapSystemDataCache newInstance() {
		instance = new DapSystemDataCache();
		return instance;
	}
}