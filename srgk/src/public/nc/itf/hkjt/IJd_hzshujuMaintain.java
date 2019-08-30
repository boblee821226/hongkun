package nc.itf.hkjt;

import java.util.Map;

import nc.bs.dao.DAOException;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.hkjt.srgk.jiudian.hzshuju.HZShuJuVO;
import nc.vo.pub.lang.UFBoolean;

public interface IJd_hzshujuMaintain {
	/**
	 * 根据时间和组织处理的底表信息
	 * */
	public HZShuJuVO[] handlerHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate) throws Exception;

	/**
	 * 获取酒店项目部门和自定义项的对应关系
	 * */

	Map<String, String[]> getDept_Vdef(String pk_dept) throws DAOException;

	/**
	 * 查询汇总底表数据
	 * */
	public SrdibiaoBillVO[] selectHZShuJuInfo(String pk_org, String pk_dept,
			String begindate, String enddate, Map<String, String[]> dept_def,
			boolean isRs, String isShowDept,UFBoolean isjd) throws Exception;

	/**
	 * 生成 U8 凭证
	 */
	public String genU8VoucherInfo(YyribaoBillVO srdbvo) throws Exception;
	/**
	 * 生成 NC 凭证
	 * flag = 0  生成
	 * flag = 1  删除
	 */
	public String genNCVoucherInfo(YyribaoBillVO srdbvo,int flag) throws Exception;
	
}
