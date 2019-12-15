package nc.itf.hkjt;

import java.util.Map;

import nc.vo.hkjt.srgk.huiguan.hzshuju.HZShuJuVO;
import nc.vo.hkjt.srgk.huiguan.hzshuju.YyrbDeptInfoVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;

public interface IHG_hzshujuMaintain {
	/**
	 * 根据时间和组织处理的底表信息
	 * */
	public HZShuJuVO[] handlerHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception;

	/**
	 * 查询汇总底表数据
	 * 会馆
	 * */
	public SrdibiaoBillVO[] selectHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate,Map<String, String[]> dept_def,boolean isRs) throws Exception;
	/**
	 * 查询下级部门对应的自定义项
	 * */
	public Map<String, String[]> getDept_Vdef(String pk_dept) throws Exception;
	/**
	 *  针对营业日报凭证部门列的查询
	 * */
	public YyrbDeptInfoVO[] getYyrbDeptYingShou(
			String pk_org, String begindate, String enddate)  throws Exception;
	/**
	 * 保存的时候使用basedao处理
	 * */
	public SrdibiaoBillVO insert(SrdibiaoBillVO srdb)  throws Exception;
	
	/**
	 * 保存营业日报使用basedao处理
	 * */
	public YyribaoBillVO insert(YyribaoBillVO srdb)  throws Exception;
}
