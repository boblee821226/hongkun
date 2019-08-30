package nc.itf.hkjt;

import java.util.Map;

import nc.bs.dao.DAOException;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.hkjt.srgk.jiudian.hzshuju.HZShuJuVO;
import nc.vo.pub.lang.UFBoolean;

public interface IJd_hzshujuMaintain {
	/**
	 * ����ʱ�����֯����ĵױ���Ϣ
	 * */
	public HZShuJuVO[] handlerHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception;

	/**
	 * ��ȡ�Ƶ���Ŀ���ź��Զ�����Ķ�Ӧ��ϵ
	 * */

	Map<String, String[]> getDept_Vdef(String pk_dept) throws DAOException;

	/**
	 * ��ѯ���ܵױ�����
	 * */
	public SrdibiaoBillVO[] selectHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate, Map<String, String[]> dept_def,
			boolean isRs, String isShowDept,UFBoolean isjd) throws Exception;

	/**
	 * ���� U8 ƾ֤
	 */
	public String genU8VoucherInfo(YyribaoBillVO srdbvo) throws Exception;
	/**
	 * ���� NC ƾ֤
	 * flag = 0  ����
	 * flag = 1  ɾ��
	 */
	public String genNCVoucherInfo(YyribaoBillVO srdbvo,int flag) throws Exception;
	
}
