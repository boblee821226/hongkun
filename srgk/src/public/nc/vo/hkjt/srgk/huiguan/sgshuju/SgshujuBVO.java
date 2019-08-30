package nc.vo.hkjt.srgk.huiguan.sgshuju;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SgshujuBVO extends SuperVO {
	/**
	 * 部门
	 */
	public static final String BM_PK = "bm_pk";
	/**
	 * 源头单据表体id
	 */
	public static final String CFIRSTBILLBID = "cfirstbillbid";
	/**
	 * 源头单据id
	 */
	public static final String CFIRSTBILLID = "cfirstbillid";
	/**
	 * 源头单据类型
	 */
	public static final String CFIRSTTYPECODE = "cfirsttypecode";
	/**
	 * 上层单据表体id
	 */
	public static final String CSOURCEBILLBID = "csourcebillbid";
	/**
	 * 上层单据id
	 */
	public static final String CSOURCEBILLID = "csourcebillid";
	/**
	 * 上层单据类型
	 */
	public static final String CSOURCETYPECODE = "csourcetypecode";
	/**
	 * 内容
	 */
	public static final String NEIRONG = "neirong";
	/**
	 * 上层单据主键
	 */
	public static final String PK_HK_SRGK_HG_SGSHUJU = "pk_hk_srgk_hg_sgshuju";
	/**
	 * 子表主键
	 */
	public static final String PK_HK_SRGK_HG_SGSHUJU_B = "pk_hk_srgk_hg_sgshuju_b";
	/**
	 * 事项
	 */
	public static final String SHIXIANG = "shixiang";
	/**
	 * 时间戳
	 */
	public static final String TS = "ts";
	/**
	 * 调整科目数据1
	 */
	public static final String TZ_KM_DATA_1 = "tz_km_data_1";
	/**
	 * 调整科目数据2
	 */
	public static final String TZ_KM_DATA_2 = "tz_km_data_2";
	/**
	 * 调整科目明细1
	 */
	public static final String TZ_KM_INFO_1 = "tz_km_info_1";
	/**
	 * 调整科目明细2
	 */
	public static final String TZ_KM_INFO_2 = "tz_km_info_2";
	/**
	 * 调整科目结账方式1
	 */
	public static final String TZ_KM_JZFS_1 = "tz_km_jzfs_1";
	/**
	 * 调整科目结账方式2
	 */
	public static final String TZ_KM_JZFS_2 = "tz_km_jzfs_2";
	/**
	 * 调整科目收入项目1
	 */
	public static final String TZ_KM_SRXM_1 = "tz_km_srxm_1";
	/**
	 * 调整科目收入项目2
	 */
	public static final String TZ_KM_SRXM_2 = "tz_km_srxm_2";
	/**
	 * 自定义项01
	 */
	public static final String VBDEF01 = "vbdef01";
	/**
	 * 自定义项02
	 */
	public static final String VBDEF02 = "vbdef02";
	/**
	 * 自定义项03
	 */
	public static final String VBDEF03 = "vbdef03";
	/**
	 * 自定义项04
	 */
	public static final String VBDEF04 = "vbdef04";
	/**
	 * 自定义项05
	 */
	public static final String VBDEF05 = "vbdef05";
	/**
	 * 自定义项06
	 */
	public static final String VBDEF06 = "vbdef06";
	/**
	 * 自定义项07
	 */
	public static final String VBDEF07 = "vbdef07";
	/**
	 * 自定义项08
	 */
	public static final String VBDEF08 = "vbdef08";
	/**
	 * 自定义项09
	 */
	public static final String VBDEF09 = "vbdef09";
	/**
	 * 自定义项10
	 */
	public static final String VBDEF10 = "vbdef10";
	/**
	 * 备注
	 */
	public static final String VBMEMO = "vbmemo";
	/**
	 * 源头单据号
	 */
	public static final String VFIRSTBILLCODE = "vfirstbillcode";
	/**
	 * 行号
	 */
	public static final String VROWNO = "vrowno";
	/**
	 * 上次单据号
	 */
	public static final String VSOURCEBILLCODE = "vsourcebillcode";
	/**
	 * 账单日期
	 */
	public static final String ZD_DATE = "zd_date";
	/**
	 * 账单行pk
	 */
	public static final String ZD_ITEM_PK = "zd_item_pk";
	/**
	 * 账单pk
	 */
	public static final String ZD_PK = "zd_pk";

	public static final String TZ_KM_SRXM_TYPE1 = "tz_km_srxm_type1";
	public static final String TZ_KM_SRXM_TYPE2 = "tz_km_srxm_type2";

	public String getTz_km_srxm_type1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1);
	}

	public void setTz_km_srxm_type1(String tz_km_srxm_type1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1, tz_km_srxm_type1);
	}

	public String getTz_km_srxm_type2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2);
	}

	public void setTz_km_srxm_type2(String tz_km_srxm_type2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2, tz_km_srxm_type2);
	}

	/**
	 * 获取部门
	 * 
	 * @return 部门
	 */
	public String getBm_pk() {
		return (String) this.getAttributeValue(SgshujuBVO.BM_PK);
	}

	/**
	 * 设置部门
	 * 
	 * @param bm_pk
	 *            部门
	 */
	public void setBm_pk(String bm_pk) {
		this.setAttributeValue(SgshujuBVO.BM_PK, bm_pk);
	}

	/**
	 * 获取源头单据表体id
	 * 
	 * @return 源头单据表体id
	 */
	public String getCfirstbillbid() {
		return (String) this.getAttributeValue(SgshujuBVO.CFIRSTBILLBID);
	}

	/**
	 * 设置源头单据表体id
	 * 
	 * @param cfirstbillbid
	 *            源头单据表体id
	 */
	public void setCfirstbillbid(String cfirstbillbid) {
		this.setAttributeValue(SgshujuBVO.CFIRSTBILLBID, cfirstbillbid);
	}

	/**
	 * 获取源头单据id
	 * 
	 * @return 源头单据id
	 */
	public String getCfirstbillid() {
		return (String) this.getAttributeValue(SgshujuBVO.CFIRSTBILLID);
	}

	/**
	 * 设置源头单据id
	 * 
	 * @param cfirstbillid
	 *            源头单据id
	 */
	public void setCfirstbillid(String cfirstbillid) {
		this.setAttributeValue(SgshujuBVO.CFIRSTBILLID, cfirstbillid);
	}

	/**
	 * 获取源头单据类型
	 * 
	 * @return 源头单据类型
	 */
	public String getCfirsttypecode() {
		return (String) this.getAttributeValue(SgshujuBVO.CFIRSTTYPECODE);
	}

	/**
	 * 设置源头单据类型
	 * 
	 * @param cfirsttypecode
	 *            源头单据类型
	 */
	public void setCfirsttypecode(String cfirsttypecode) {
		this.setAttributeValue(SgshujuBVO.CFIRSTTYPECODE, cfirsttypecode);
	}

	/**
	 * 获取上层单据表体id
	 * 
	 * @return 上层单据表体id
	 */
	public String getCsourcebillbid() {
		return (String) this.getAttributeValue(SgshujuBVO.CSOURCEBILLBID);
	}

	/**
	 * 设置上层单据表体id
	 * 
	 * @param csourcebillbid
	 *            上层单据表体id
	 */
	public void setCsourcebillbid(String csourcebillbid) {
		this.setAttributeValue(SgshujuBVO.CSOURCEBILLBID, csourcebillbid);
	}

	/**
	 * 获取上层单据id
	 * 
	 * @return 上层单据id
	 */
	public String getCsourcebillid() {
		return (String) this.getAttributeValue(SgshujuBVO.CSOURCEBILLID);
	}

	/**
	 * 设置上层单据id
	 * 
	 * @param csourcebillid
	 *            上层单据id
	 */
	public void setCsourcebillid(String csourcebillid) {
		this.setAttributeValue(SgshujuBVO.CSOURCEBILLID, csourcebillid);
	}

	/**
	 * 获取上层单据类型
	 * 
	 * @return 上层单据类型
	 */
	public String getCsourcetypecode() {
		return (String) this.getAttributeValue(SgshujuBVO.CSOURCETYPECODE);
	}

	/**
	 * 设置上层单据类型
	 * 
	 * @param csourcetypecode
	 *            上层单据类型
	 */
	public void setCsourcetypecode(String csourcetypecode) {
		this.setAttributeValue(SgshujuBVO.CSOURCETYPECODE, csourcetypecode);
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public String getNeirong() {
		return (String) this.getAttributeValue(SgshujuBVO.NEIRONG);
	}

	/**
	 * 设置内容
	 * 
	 * @param neirong
	 *            内容
	 */
	public void setNeirong(String neirong) {
		this.setAttributeValue(SgshujuBVO.NEIRONG, neirong);
	}

	/**
	 * 获取上层单据主键
	 * 
	 * @return 上层单据主键
	 */
	public String getPk_hk_srgk_hg_sgshuju() {
		return (String) this
				.getAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU);
	}

	/**
	 * 设置上层单据主键
	 * 
	 * @param pk_hk_srgk_hg_sgshuju
	 *            上层单据主键
	 */
	public void setPk_hk_srgk_hg_sgshuju(String pk_hk_srgk_hg_sgshuju) {
		this.setAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU,
				pk_hk_srgk_hg_sgshuju);
	}

	/**
	 * 获取子表主键
	 * 
	 * @return 子表主键
	 */
	public String getPk_hk_srgk_hg_sgshuju_b() {
		return (String) this
				.getAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU_B);
	}

	/**
	 * 设置子表主键
	 * 
	 * @param pk_hk_srgk_hg_sgshuju_b
	 *            子表主键
	 */
	public void setPk_hk_srgk_hg_sgshuju_b(String pk_hk_srgk_hg_sgshuju_b) {
		this.setAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU_B,
				pk_hk_srgk_hg_sgshuju_b);
	}

	/**
	 * 获取事项
	 * 
	 * @return 事项
	 * @see String
	 */
	public String getShixiang() {
		return (String) this.getAttributeValue(SgshujuBVO.SHIXIANG);
	}

	/**
	 * 设置事项
	 * 
	 * @param shixiang
	 *            事项
	 * @see String
	 */
	public void setShixiang(String shixiang) {
		this.setAttributeValue(SgshujuBVO.SHIXIANG, shixiang);
	}

	/**
	 * 获取时间戳
	 * 
	 * @return 时间戳
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(SgshujuBVO.TS);
	}

	/**
	 * 设置时间戳
	 * 
	 * @param ts
	 *            时间戳
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(SgshujuBVO.TS, ts);
	}

	/**
	 * 获取调整科目数据1
	 * 
	 * @return 调整科目数据1
	 */
	public UFDouble getTz_km_data_1() {
		return (UFDouble) this.getAttributeValue(SgshujuBVO.TZ_KM_DATA_1);
	}

	/**
	 * 设置调整科目数据1
	 * 
	 * @param tz_km_data_1
	 *            调整科目数据1
	 */
	public void setTz_km_data_1(UFDouble tz_km_data_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_DATA_1, tz_km_data_1);
	}

	/**
	 * 获取调整科目数据2
	 * 
	 * @return 调整科目数据2
	 */
	public UFDouble getTz_km_data_2() {
		return (UFDouble) this.getAttributeValue(SgshujuBVO.TZ_KM_DATA_2);
	}

	/**
	 * 设置调整科目数据2
	 * 
	 * @param tz_km_data_2
	 *            调整科目数据2
	 */
	public void setTz_km_data_2(UFDouble tz_km_data_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_DATA_2, tz_km_data_2);
	}

	/**
	 * 获取调整科目明细1
	 * 
	 * @return 调整科目明细1
	 */
	public String getTz_km_info_1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_INFO_1);
	}

	/**
	 * 设置调整科目明细1
	 * 
	 * @param tz_km_info_1
	 *            调整科目明细1
	 */
	public void setTz_km_info_1(String tz_km_info_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_INFO_1, tz_km_info_1);
	}

	/**
	 * 获取调整科目明细2
	 * 
	 * @return 调整科目明细2
	 */
	public String getTz_km_info_2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_INFO_2);
	}

	/**
	 * 设置调整科目明细2
	 * 
	 * @param tz_km_info_2
	 *            调整科目明细2
	 */
	public void setTz_km_info_2(String tz_km_info_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_INFO_2, tz_km_info_2);
	}

	/**
	 * 获取调整科目结账方式1
	 * 
	 * @return 调整科目结账方式1
	 */
	public String getTz_km_jzfs_1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_JZFS_1);
	}

	/**
	 * 设置调整科目结账方式1
	 * 
	 * @param tz_km_jzfs_1
	 *            调整科目结账方式1
	 */
	public void setTz_km_jzfs_1(String tz_km_jzfs_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_JZFS_1, tz_km_jzfs_1);
	}

	/**
	 * 获取调整科目结账方式2
	 * 
	 * @return 调整科目结账方式2
	 */
	public String getTz_km_jzfs_2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_JZFS_2);
	}

	/**
	 * 设置调整科目结账方式2
	 * 
	 * @param tz_km_jzfs_2
	 *            调整科目结账方式2
	 */
	public void setTz_km_jzfs_2(String tz_km_jzfs_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_JZFS_2, tz_km_jzfs_2);
	}

	/**
	 * 获取调整科目收入项目1
	 * 
	 * @return 调整科目收入项目1
	 */
	public String getTz_km_srxm_1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_1);
	}

	/**
	 * 设置调整科目收入项目1
	 * 
	 * @param tz_km_srxm_1
	 *            调整科目收入项目1
	 */
	public void setTz_km_srxm_1(String tz_km_srxm_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_1, tz_km_srxm_1);
	}

	/**
	 * 获取调整科目收入项目2
	 * 
	 * @return 调整科目收入项目2
	 */
	public String getTz_km_srxm_2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_2);
	}

	/**
	 * 设置调整科目收入项目2
	 * 
	 * @param tz_km_srxm_2
	 *            调整科目收入项目2
	 */
	public void setTz_km_srxm_2(String tz_km_srxm_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_2, tz_km_srxm_2);
	}

	/**
	 * 获取自定义项01
	 * 
	 * @return 自定义项01
	 */
	public String getVbdef01() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF01);
	}

	/**
	 * 设置自定义项01
	 * 
	 * @param vbdef01
	 *            自定义项01
	 */
	public void setVbdef01(String vbdef01) {
		this.setAttributeValue(SgshujuBVO.VBDEF01, vbdef01);
	}

	/**
	 * 获取自定义项02
	 * 
	 * @return 自定义项02
	 */
	public String getVbdef02() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF02);
	}

	/**
	 * 设置自定义项02
	 * 
	 * @param vbdef02
	 *            自定义项02
	 */
	public void setVbdef02(String vbdef02) {
		this.setAttributeValue(SgshujuBVO.VBDEF02, vbdef02);
	}

	/**
	 * 获取自定义项03
	 * 
	 * @return 自定义项03
	 */
	public String getVbdef03() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF03);
	}

	/**
	 * 设置自定义项03
	 * 
	 * @param vbdef03
	 *            自定义项03
	 */
	public void setVbdef03(String vbdef03) {
		this.setAttributeValue(SgshujuBVO.VBDEF03, vbdef03);
	}

	/**
	 * 获取自定义项04
	 * 
	 * @return 自定义项04
	 */
	public String getVbdef04() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF04);
	}

	/**
	 * 设置自定义项04
	 * 
	 * @param vbdef04
	 *            自定义项04
	 */
	public void setVbdef04(String vbdef04) {
		this.setAttributeValue(SgshujuBVO.VBDEF04, vbdef04);
	}

	/**
	 * 获取自定义项05
	 * 
	 * @return 自定义项05
	 */
	public String getVbdef05() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF05);
	}

	/**
	 * 设置自定义项05
	 * 
	 * @param vbdef05
	 *            自定义项05
	 */
	public void setVbdef05(String vbdef05) {
		this.setAttributeValue(SgshujuBVO.VBDEF05, vbdef05);
	}

	/**
	 * 获取自定义项06
	 * 
	 * @return 自定义项06
	 */
	public String getVbdef06() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF06);
	}

	/**
	 * 设置自定义项06
	 * 
	 * @param vbdef06
	 *            自定义项06
	 */
	public void setVbdef06(String vbdef06) {
		this.setAttributeValue(SgshujuBVO.VBDEF06, vbdef06);
	}

	/**
	 * 获取自定义项07
	 * 
	 * @return 自定义项07
	 */
	public String getVbdef07() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF07);
	}

	/**
	 * 设置自定义项07
	 * 
	 * @param vbdef07
	 *            自定义项07
	 */
	public void setVbdef07(String vbdef07) {
		this.setAttributeValue(SgshujuBVO.VBDEF07, vbdef07);
	}

	/**
	 * 获取自定义项08
	 * 
	 * @return 自定义项08
	 */
	public String getVbdef08() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF08);
	}

	/**
	 * 设置自定义项08
	 * 
	 * @param vbdef08
	 *            自定义项08
	 */
	public void setVbdef08(String vbdef08) {
		this.setAttributeValue(SgshujuBVO.VBDEF08, vbdef08);
	}

	/**
	 * 获取自定义项09
	 * 
	 * @return 自定义项09
	 */
	public String getVbdef09() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF09);
	}

	/**
	 * 设置自定义项09
	 * 
	 * @param vbdef09
	 *            自定义项09
	 */
	public void setVbdef09(String vbdef09) {
		this.setAttributeValue(SgshujuBVO.VBDEF09, vbdef09);
	}

	/**
	 * 获取自定义项10
	 * 
	 * @return 自定义项10
	 */
	public String getVbdef10() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF10);
	}

	/**
	 * 设置自定义项10
	 * 
	 * @param vbdef10
	 *            自定义项10
	 */
	public void setVbdef10(String vbdef10) {
		this.setAttributeValue(SgshujuBVO.VBDEF10, vbdef10);
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getVbmemo() {
		return (String) this.getAttributeValue(SgshujuBVO.VBMEMO);
	}

	/**
	 * 设置备注
	 * 
	 * @param vbmemo
	 *            备注
	 */
	public void setVbmemo(String vbmemo) {
		this.setAttributeValue(SgshujuBVO.VBMEMO, vbmemo);
	}

	/**
	 * 获取源头单据号
	 * 
	 * @return 源头单据号
	 */
	public String getVfirstbillcode() {
		return (String) this.getAttributeValue(SgshujuBVO.VFIRSTBILLCODE);
	}

	/**
	 * 设置源头单据号
	 * 
	 * @param vfirstbillcode
	 *            源头单据号
	 */
	public void setVfirstbillcode(String vfirstbillcode) {
		this.setAttributeValue(SgshujuBVO.VFIRSTBILLCODE, vfirstbillcode);
	}

	/**
	 * 获取行号
	 * 
	 * @return 行号
	 */
	public String getVrowno() {
		return (String) this.getAttributeValue(SgshujuBVO.VROWNO);
	}

	/**
	 * 设置行号
	 * 
	 * @param vrowno
	 *            行号
	 */
	public void setVrowno(String vrowno) {
		this.setAttributeValue(SgshujuBVO.VROWNO, vrowno);
	}

	/**
	 * 获取上次单据号
	 * 
	 * @return 上次单据号
	 */
	public String getVsourcebillcode() {
		return (String) this.getAttributeValue(SgshujuBVO.VSOURCEBILLCODE);
	}

	/**
	 * 设置上次单据号
	 * 
	 * @param vsourcebillcode
	 *            上次单据号
	 */
	public void setVsourcebillcode(String vsourcebillcode) {
		this.setAttributeValue(SgshujuBVO.VSOURCEBILLCODE, vsourcebillcode);
	}

	/**
	 * 获取账单日期
	 * 
	 * @return 账单日期
	 */
	public UFDate getZd_date() {
		return (UFDate) this.getAttributeValue(SgshujuBVO.ZD_DATE);
	}

	/**
	 * 设置账单日期
	 * 
	 * @param zd_date
	 *            账单日期
	 */
	public void setZd_date(UFDate zd_date) {
		this.setAttributeValue(SgshujuBVO.ZD_DATE, zd_date);
	}

	/**
	 * 获取账单行pk
	 * 
	 * @return 账单行pk
	 */
	public String getZd_item_pk() {
		return (String) this.getAttributeValue(SgshujuBVO.ZD_ITEM_PK);
	}

	/**
	 * 设置账单行pk
	 * 
	 * @param zd_item_pk
	 *            账单行pk
	 */
	public void setZd_item_pk(String zd_item_pk) {
		this.setAttributeValue(SgshujuBVO.ZD_ITEM_PK, zd_item_pk);
	}

	/**
	 * 获取账单pk
	 * 
	 * @return 账单pk
	 */
	public String getZd_pk() {
		return (String) this.getAttributeValue(SgshujuBVO.ZD_PK);
	}

	/**
	 * 设置账单pk
	 * 
	 * @param zd_pk
	 *            账单pk
	 */
	public void setZd_pk(String zd_pk) {
		this.setAttributeValue(SgshujuBVO.ZD_PK, zd_pk);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_SgshujuBVO");
	}
}