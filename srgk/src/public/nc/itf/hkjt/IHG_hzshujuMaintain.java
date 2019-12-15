package nc.itf.hkjt;

import java.util.Map;

import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.YyrbDeptInfoVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;

public interface IHG_hzshujuMaintain {
	/**
	 * ����ʱ�����֯����ĵױ���Ϣ
	 * */
	public HZShuJuVO[] handlerHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception;

	/**
	 * ��ѯ���ܵױ�����
	 * ���
	 * */
	public SrdibiaoBillVO[] selectHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate,Map<String, String[]> dept_def,boolean isRs) throws Exception;
	/**
	 * ��ѯ�¼����Ŷ�Ӧ���Զ�����
	 * */
	public Map<String, String[]> getDept_Vdef(String pk_dept) throws Exception;
	/**
	 *  ���Ӫҵ�ձ�ƾ֤�����еĲ�ѯ
	 * */
	public YyrbDeptInfoVO[] getYyrbDeptYingShou(
			String pk_org, String begindate, String enddate)  throws Exception;
	/**
	 * �����ʱ��ʹ��basedao����
	 * */
	public SrdibiaoBillVO insert(SrdibiaoBillVO srdb)  throws Exception;
	
	/**
	 * ����Ӫҵ�ձ�ʹ��basedao����
	 * */
	public YyribaoBillVO insert(YyribaoBillVO srdb)  throws Exception;
}
