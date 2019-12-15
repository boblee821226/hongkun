package nc.vo.hkjt.srgk.huiguan.yyribao;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class YyribaoBVO extends SuperVO {
	
	/**
	 * 生成凭证 相关字段
	 * 李彬  2016年5月10日10:06:04
	 */
	/********** 会计科目 **********/
	public static final String PK_KJKM = "pk_kjkm";
	public String getPk_kjkm() {
		return (String) this.getAttributeValue(YyribaoBVO.PK_KJKM);
	}
	public void setPk_kjkm(String Pk_kjkm) {
		this.setAttributeValue(YyribaoBVO.PK_KJKM, Pk_kjkm);
	}
	/********** 部门 **********/
	public static final String PK_DEPT = "pk_dept";
	public String getPk_dept() {
		return (String) this.getAttributeValue(YyribaoBVO.PK_DEPT);
	}
	public void setPk_dept(String Pk_dept) {
		this.setAttributeValue(YyribaoBVO.PK_DEPT, Pk_dept);
	}
	/********** 客商 **********/
	public static final String PK_KS = "pk_ks";
	public String getPk_ks() {
		return (String) this.getAttributeValue(YyribaoBVO.PK_KS);
	}
	public void setPk_ks(String Pk_ks) {
		this.setAttributeValue(YyribaoBVO.PK_KS, Pk_ks);
	}
	/*********** 借方金额 ***********/
	public static final String JIEFANG = "jiefang";
	public UFDouble getJiefang() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.JIEFANG);
	}
	public void setJiefang(UFDouble jiefang) {
		this.setAttributeValue(YyribaoBVO.JIEFANG, jiefang);
	}
	/*********** 贷方金额 ***********/
	public static final String DAIFANG = "daifang";
	public UFDouble getDaifang() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.DAIFANG);
	}
	public void setDaifang(UFDouble daifang) {
		this.setAttributeValue(YyribaoBVO.DAIFANG, daifang);
	}
	/**END*/
	
	private String pk_fjzfs;
	private String iswanglai;
	private Integer dr;
	
	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getPk_fjzfs() {

		return (String) this.getAttributeValue("pk_fjzfs");
	}

	public void setPk_fjzfs(String pk_fjzfs) {
		this.pk_fjzfs = pk_fjzfs;
		this.setAttributeValue("pk_fjzfs", pk_fjzfs);
	}

	public String getIswanglai() {
		return (String) this.getAttributeValue("iswanglai");
	}

	public void setIswanglai(String iswanglai) {
		this.iswanglai = iswanglai;
		this.setAttributeValue("iswanglai", iswanglai);
	}

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
	 * 金额
	 */
	public static final String JINE = "jine";
	/**
	 * 结账方式编码
	 */
	public static final String JZFS_CODE = "jzfs_code";
	/**
	 * 结账方式
	 */
	public static final String JZFS_NAME = "jzfs_name";
	/**
	 * 结账方式pk
	 */
	public static final String JZFS_PK = "jzfs_pk";
	/**
	 * 上层单据主键
	 */
	public static final String PK_HK_SRGK_HG_YYRIBAO = "pk_hk_srgk_hg_yyribao";
	/**
	 * 子表主键
	 */
	public static final String PK_HK_SRGK_HG_YYRIBAO_B = "pk_hk_srgk_hg_yyribao_b";
	/**
	 * 实收
	 */
	public static final String SHISHOU = "shishou";
	/**
	 * 收入
	 */
	public static final String SHOURU = "shouru";
	/**
	 * 收入-部门01
	 */
	public static final String SHOURU_BM01 = "shouru_bm01";
	/**
	 * 收入-部门02
	 */
	public static final String SHOURU_BM02 = "shouru_bm02";
	/**
	 * 收入-部门03
	 */
	public static final String SHOURU_BM03 = "shouru_bm03";
	/**
	 * 收入-部门04
	 */
	public static final String SHOURU_BM04 = "shouru_bm04";
	/**
	 * 收入-部门05
	 */
	public static final String SHOURU_BM05 = "shouru_bm05";
	/**
	 * 收入-部门06
	 */
	public static final String SHOURU_BM06 = "shouru_bm06";
	/**
	 * 收入-部门07
	 */
	public static final String SHOURU_BM07 = "shouru_bm07";
	/**
	 * 收入-部门08
	 */
	public static final String SHOURU_BM08 = "shouru_bm08";
	/**
	 * 收入-部门09
	 */
	public static final String SHOURU_BM09 = "shouru_bm09";
	/**
	 * 收入-部门10
	 */
	public static final String SHOURU_BM10 = "shouru_bm10";
	/**
	 * 收入-部门11
	 */
	public static final String SHOURU_BM11 = "shouru_bm11";
	/**
	 * 收入-部门12
	 */
	public static final String SHOURU_BM12 = "shouru_bm12";
	/**
	 * 收入-部门13
	 */
	public static final String SHOURU_BM13 = "shouru_bm13";
	/**
	 * 收入-部门14
	 */
	public static final String SHOURU_BM14 = "shouru_bm14";
	/**
	 * 收入-部门15
	 */
	public static final String SHOURU_BM15 = "shouru_bm15";
	/**
	 * 收入-部门16
	 */
	public static final String SHOURU_BM16 = "shouru_bm16";
	/**
	 * 收入-部门17
	 */
	public static final String SHOURU_BM17 = "shouru_bm17";
	/**
	 * 收入-部门18
	 */
	public static final String SHOURU_BM18 = "shouru_bm18";
	/**
	 * 收入-部门19
	 */
	public static final String SHOURU_BM19 = "shouru_bm19";
	/**
	 * 收入-部门20
	 */
	public static final String SHOURU_BM20 = "shouru_bm20";
	/**
	 * 收入-部门21
	 */
	public static final String SHOURU_BM21 = "shouru_bm21";
	/**
	 * 收入-部门22
	 */
	public static final String SHOURU_BM22 = "shouru_bm22";
	/**
	 * 收入-部门23
	 */
	public static final String SHOURU_BM23 = "shouru_bm23";
	/**
	 * 收入-部门24
	 */
	public static final String SHOURU_BM24 = "shouru_bm24";
	/**
	 * 收入-部门25
	 */
	public static final String SHOURU_BM25 = "shouru_bm25";
	/**
	 * 收入-部门26
	 */
	public static final String SHOURU_BM26 = "shouru_bm26";
	/**
	 * 收入-部门27
	 */
	public static final String SHOURU_BM27 = "shouru_bm27";
	/**
	 * 收入-部门28
	 */
	public static final String SHOURU_BM28 = "shouru_bm28";
	/**
	 * 收入-部门29
	 */
	public static final String SHOURU_BM29 = "shouru_bm29";
	/**
	 * 收入-部门30
	 */
	public static final String SHOURU_BM30 = "shouru_bm30";
	/**
	 * 收入-部门31
	 */
	public static final String SHOURU_BM31 = "shouru_bm31";
	/**
	 * 收入-部门32
	 */
	public static final String SHOURU_BM32 = "shouru_bm32";
	/**
	 * 收入-部门33
	 */
	public static final String SHOURU_BM33 = "shouru_bm33";
	/**
	 * 收入-部门34
	 */
	public static final String SHOURU_BM34 = "shouru_bm34";
	/**
	 * 收入-部门35
	 */
	public static final String SHOURU_BM35 = "shouru_bm35";
	/**
	 * 收入-部门36
	 */
	public static final String SHOURU_BM36 = "shouru_bm36";
	/**
	 * 收入-部门37
	 */
	public static final String SHOURU_BM37 = "shouru_bm37";
	/**
	 * 收入-部门38
	 */
	public static final String SHOURU_BM38 = "shouru_bm38";
	/**
	 * 收入-部门39
	 */
	public static final String SHOURU_BM39 = "shouru_bm39";
	/**
	 * 收入-部门40
	 */
	public static final String SHOURU_BM40 = "shouru_bm40";
	/**
	 * 收入-部门41
	 */
	public static final String SHOURU_BM41 = "shouru_bm41";
	/**
	 * 收入-部门42
	 */
	public static final String SHOURU_BM42 = "shouru_bm42";
	/**
	 * 收入-部门43
	 */
	public static final String SHOURU_BM43 = "shouru_bm43";
	/**
	 * 收入-部门44
	 */
	public static final String SHOURU_BM44 = "shouru_bm44";
	/**
	 * 收入-部门45
	 */
	public static final String SHOURU_BM45 = "shouru_bm45";
	/**
	 * 收入-部门46
	 */
	public static final String SHOURU_BM46 = "shouru_bm46";
	/**
	 * 收入-部门47
	 */
	public static final String SHOURU_BM47 = "shouru_bm47";
	/**
	 * 收入-部门48
	 */
	public static final String SHOURU_BM48 = "shouru_bm48";
	/**
	 * 收入-部门49
	 */
	public static final String SHOURU_BM49 = "shouru_bm49";
	/**
	 * 收入-部门50
	 */
	public static final String SHOURU_BM50 = "shouru_bm50";
	/**
	 * 收入明细编码
	 */
	public static final String SRMX_CODE = "srmx_code";
	/**
	 * 收入明细
	 */
	public static final String SRMX_NAME = "srmx_name";
	/**
	 * 收入明细pk
	 */
	public static final String SRMX_PK = "srmx_pk";
	/**
	 * 时间戳
	 */
	public static final String TS = "ts";
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
	 * 销售额（应收）
	 */
	public static final String YINGSHOU = "yingshou";
	/**
	 * 卡比例优惠
	 */
	public static final String YOUHUI_KABILI = "youhui_kabili";
	/**
	 * 手工优惠
	 */
	public static final String YOUHUI_SHOUGONG = "youhui_shougong";
	/**
	 * 自动优惠
	 */
	public static final String YOUHUI_ZIDONG = "youhui_zidong";
	/**
	 * 占位列1
	 */
	public static final String ZHANWEI01 = "zhanwei01";
	/**
	 * 占位列2
	 */
	public static final String ZHANWEI02 = "zhanwei02";

	/**
	 * 获取源头单据表体id
	 * 
	 * @return 源头单据表体id
	 */
	public String getCfirstbillbid() {
		return (String) this.getAttributeValue(YyribaoBVO.CFIRSTBILLBID);
	}

	/**
	 * 设置源头单据表体id
	 * 
	 * @param cfirstbillbid
	 *            源头单据表体id
	 */
	public void setCfirstbillbid(String cfirstbillbid) {
		this.setAttributeValue(YyribaoBVO.CFIRSTBILLBID, cfirstbillbid);
	}

	/**
	 * 获取源头单据id
	 * 
	 * @return 源头单据id
	 */
	public String getCfirstbillid() {
		return (String) this.getAttributeValue(YyribaoBVO.CFIRSTBILLID);
	}

	/**
	 * 设置源头单据id
	 * 
	 * @param cfirstbillid
	 *            源头单据id
	 */
	public void setCfirstbillid(String cfirstbillid) {
		this.setAttributeValue(YyribaoBVO.CFIRSTBILLID, cfirstbillid);
	}

	/**
	 * 获取源头单据类型
	 * 
	 * @return 源头单据类型
	 */
	public String getCfirsttypecode() {
		return (String) this.getAttributeValue(YyribaoBVO.CFIRSTTYPECODE);
	}

	/**
	 * 设置源头单据类型
	 * 
	 * @param cfirsttypecode
	 *            源头单据类型
	 */
	public void setCfirsttypecode(String cfirsttypecode) {
		this.setAttributeValue(YyribaoBVO.CFIRSTTYPECODE, cfirsttypecode);
	}

	/**
	 * 获取上层单据表体id
	 * 
	 * @return 上层单据表体id
	 */
	public String getCsourcebillbid() {
		return (String) this.getAttributeValue(YyribaoBVO.CSOURCEBILLBID);
	}

	/**
	 * 设置上层单据表体id
	 * 
	 * @param csourcebillbid
	 *            上层单据表体id
	 */
	public void setCsourcebillbid(String csourcebillbid) {
		this.setAttributeValue(YyribaoBVO.CSOURCEBILLBID, csourcebillbid);
	}

	/**
	 * 获取上层单据id
	 * 
	 * @return 上层单据id
	 */
	public String getCsourcebillid() {
		return (String) this.getAttributeValue(YyribaoBVO.CSOURCEBILLID);
	}

	/**
	 * 设置上层单据id
	 * 
	 * @param csourcebillid
	 *            上层单据id
	 */
	public void setCsourcebillid(String csourcebillid) {
		this.setAttributeValue(YyribaoBVO.CSOURCEBILLID, csourcebillid);
	}

	/**
	 * 获取上层单据类型
	 * 
	 * @return 上层单据类型
	 */
	public String getCsourcetypecode() {
		return (String) this.getAttributeValue(YyribaoBVO.CSOURCETYPECODE);
	}

	/**
	 * 设置上层单据类型
	 * 
	 * @param csourcetypecode
	 *            上层单据类型
	 */
	public void setCsourcetypecode(String csourcetypecode) {
		this.setAttributeValue(YyribaoBVO.CSOURCETYPECODE, csourcetypecode);
	}

	/**
	 * 获取金额
	 * 
	 * @return 金额
	 */
	public UFDouble getJine() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.JINE);
	}

	/**
	 * 设置金额
	 * 
	 * @param jine
	 *            金额
	 */
	public void setJine(UFDouble jine) {
		this.setAttributeValue(YyribaoBVO.JINE, jine);
	}

	/**
	 * 获取结账方式编码
	 * 
	 * @return 结账方式编码
	 */
	public String getJzfs_code() {
		return (String) this.getAttributeValue(YyribaoBVO.JZFS_CODE);
	}

	/**
	 * 设置结账方式编码
	 * 
	 * @param jzfs_code
	 *            结账方式编码
	 */
	public void setJzfs_code(String jzfs_code) {
		this.setAttributeValue(YyribaoBVO.JZFS_CODE, jzfs_code);
	}

	/**
	 * 获取结账方式
	 * 
	 * @return 结账方式
	 */
	public String getJzfs_name() {
		return (String) this.getAttributeValue(YyribaoBVO.JZFS_NAME);
	}

	/**
	 * 设置结账方式
	 * 
	 * @param jzfs_name
	 *            结账方式
	 */
	public void setJzfs_name(String jzfs_name) {
		this.setAttributeValue(YyribaoBVO.JZFS_NAME, jzfs_name);
	}

	/**
	 * 获取结账方式pk
	 * 
	 * @return 结账方式pk
	 */
	public String getJzfs_pk() {
		return (String) this.getAttributeValue(YyribaoBVO.JZFS_PK);
	}

	/**
	 * 设置结账方式pk
	 * 
	 * @param jzfs_pk
	 *            结账方式pk
	 */
	public void setJzfs_pk(String jzfs_pk) {
		this.setAttributeValue(YyribaoBVO.JZFS_PK, jzfs_pk);
	}

	/**
	 * 获取上层单据主键
	 * 
	 * @return 上层单据主键
	 */
	public String getPk_hk_srgk_hg_yyribao() {
		return (String) this
				.getAttributeValue(YyribaoBVO.PK_HK_SRGK_HG_YYRIBAO);
	}

	/**
	 * 设置上层单据主键
	 * 
	 * @param pk_hk_srgk_hg_yyribao
	 *            上层单据主键
	 */
	public void setPk_hk_srgk_hg_yyribao(String pk_hk_srgk_hg_yyribao) {
		this.setAttributeValue(YyribaoBVO.PK_HK_SRGK_HG_YYRIBAO,
				pk_hk_srgk_hg_yyribao);
	}

	/**
	 * 获取子表主键
	 * 
	 * @return 子表主键
	 */
	public String getPk_hk_srgk_hg_yyribao_b() {
		return (String) this
				.getAttributeValue(YyribaoBVO.PK_HK_SRGK_HG_YYRIBAO_B);
	}

	/**
	 * 设置子表主键
	 * 
	 * @param pk_hk_srgk_hg_yyribao_b
	 *            子表主键
	 */
	public void setPk_hk_srgk_hg_yyribao_b(String pk_hk_srgk_hg_yyribao_b) {
		this.setAttributeValue(YyribaoBVO.PK_HK_SRGK_HG_YYRIBAO_B,
				pk_hk_srgk_hg_yyribao_b);
	}

	/**
	 * 获取实收
	 * 
	 * @return 实收
	 */
	public UFDouble getShishou() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHISHOU);
	}

	/**
	 * 设置实收
	 * 
	 * @param shishou
	 *            实收
	 */
	public void setShishou(UFDouble shishou) {
		this.setAttributeValue(YyribaoBVO.SHISHOU, shishou);
	}

	/**
	 * 获取收入
	 * 
	 * @return 收入
	 */
	public UFDouble getShouru() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU);
	}

	/**
	 * 设置收入
	 * 
	 * @param shouru
	 *            收入
	 */
	public void setShouru(UFDouble shouru) {
		this.setAttributeValue(YyribaoBVO.SHOURU, shouru);
	}

	/**
	 * 获取收入-部门01
	 * 
	 * @return 收入-部门01
	 */
	public UFDouble getShouru_bm01() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM01);
	}

	/**
	 * 设置收入-部门01
	 * 
	 * @param shouru_bm01
	 *            收入-部门01
	 */
	public void setShouru_bm01(UFDouble shouru_bm01) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM01, shouru_bm01);
	}

	/**
	 * 获取收入-部门02
	 * 
	 * @return 收入-部门02
	 */
	public UFDouble getShouru_bm02() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM02);
	}

	/**
	 * 设置收入-部门02
	 * 
	 * @param shouru_bm02
	 *            收入-部门02
	 */
	public void setShouru_bm02(UFDouble shouru_bm02) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM02, shouru_bm02);
	}

	/**
	 * 获取收入-部门03
	 * 
	 * @return 收入-部门03
	 */
	public UFDouble getShouru_bm03() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM03);
	}

	/**
	 * 设置收入-部门03
	 * 
	 * @param shouru_bm03
	 *            收入-部门03
	 */
	public void setShouru_bm03(UFDouble shouru_bm03) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM03, shouru_bm03);
	}

	/**
	 * 获取收入-部门04
	 * 
	 * @return 收入-部门04
	 */
	public UFDouble getShouru_bm04() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM04);
	}

	/**
	 * 设置收入-部门04
	 * 
	 * @param shouru_bm04
	 *            收入-部门04
	 */
	public void setShouru_bm04(UFDouble shouru_bm04) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM04, shouru_bm04);
	}

	/**
	 * 获取收入-部门05
	 * 
	 * @return 收入-部门05
	 */
	public UFDouble getShouru_bm05() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM05);
	}

	/**
	 * 设置收入-部门05
	 * 
	 * @param shouru_bm05
	 *            收入-部门05
	 */
	public void setShouru_bm05(UFDouble shouru_bm05) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM05, shouru_bm05);
	}

	/**
	 * 获取收入-部门06
	 * 
	 * @return 收入-部门06
	 */
	public UFDouble getShouru_bm06() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM06);
	}

	/**
	 * 设置收入-部门06
	 * 
	 * @param shouru_bm06
	 *            收入-部门06
	 */
	public void setShouru_bm06(UFDouble shouru_bm06) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM06, shouru_bm06);
	}

	/**
	 * 获取收入-部门07
	 * 
	 * @return 收入-部门07
	 */
	public UFDouble getShouru_bm07() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM07);
	}

	/**
	 * 设置收入-部门07
	 * 
	 * @param shouru_bm07
	 *            收入-部门07
	 */
	public void setShouru_bm07(UFDouble shouru_bm07) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM07, shouru_bm07);
	}

	/**
	 * 获取收入-部门08
	 * 
	 * @return 收入-部门08
	 */
	public UFDouble getShouru_bm08() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM08);
	}

	/**
	 * 设置收入-部门08
	 * 
	 * @param shouru_bm08
	 *            收入-部门08
	 */
	public void setShouru_bm08(UFDouble shouru_bm08) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM08, shouru_bm08);
	}

	/**
	 * 获取收入-部门09
	 * 
	 * @return 收入-部门09
	 */
	public UFDouble getShouru_bm09() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM09);
	}

	/**
	 * 设置收入-部门09
	 * 
	 * @param shouru_bm09
	 *            收入-部门09
	 */
	public void setShouru_bm09(UFDouble shouru_bm09) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM09, shouru_bm09);
	}

	/**
	 * 获取收入-部门10
	 * 
	 * @return 收入-部门10
	 */
	public UFDouble getShouru_bm10() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM10);
	}

	/**
	 * 设置收入-部门10
	 * 
	 * @param shouru_bm10
	 *            收入-部门10
	 */
	public void setShouru_bm10(UFDouble shouru_bm10) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM10, shouru_bm10);
	}

	/**
	 * 获取收入-部门11
	 * 
	 * @return 收入-部门11
	 */
	public UFDouble getShouru_bm11() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM11);
	}

	/**
	 * 设置收入-部门11
	 * 
	 * @param shouru_bm11
	 *            收入-部门11
	 */
	public void setShouru_bm11(UFDouble shouru_bm11) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM11, shouru_bm11);
	}

	/**
	 * 获取收入-部门12
	 * 
	 * @return 收入-部门12
	 */
	public UFDouble getShouru_bm12() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM12);
	}

	/**
	 * 设置收入-部门12
	 * 
	 * @param shouru_bm12
	 *            收入-部门12
	 */
	public void setShouru_bm12(UFDouble shouru_bm12) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM12, shouru_bm12);
	}

	/**
	 * 获取收入-部门13
	 * 
	 * @return 收入-部门13
	 */
	public UFDouble getShouru_bm13() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM13);
	}

	/**
	 * 设置收入-部门13
	 * 
	 * @param shouru_bm13
	 *            收入-部门13
	 */
	public void setShouru_bm13(UFDouble shouru_bm13) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM13, shouru_bm13);
	}

	/**
	 * 获取收入-部门14
	 * 
	 * @return 收入-部门14
	 */
	public UFDouble getShouru_bm14() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM14);
	}

	/**
	 * 设置收入-部门14
	 * 
	 * @param shouru_bm14
	 *            收入-部门14
	 */
	public void setShouru_bm14(UFDouble shouru_bm14) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM14, shouru_bm14);
	}

	/**
	 * 获取收入-部门15
	 * 
	 * @return 收入-部门15
	 */
	public UFDouble getShouru_bm15() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM15);
	}

	/**
	 * 设置收入-部门15
	 * 
	 * @param shouru_bm15
	 *            收入-部门15
	 */
	public void setShouru_bm15(UFDouble shouru_bm15) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM15, shouru_bm15);
	}

	/**
	 * 获取收入-部门16
	 * 
	 * @return 收入-部门16
	 */
	public UFDouble getShouru_bm16() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM16);
	}

	/**
	 * 设置收入-部门16
	 * 
	 * @param shouru_bm16
	 *            收入-部门16
	 */
	public void setShouru_bm16(UFDouble shouru_bm16) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM16, shouru_bm16);
	}

	/**
	 * 获取收入-部门17
	 * 
	 * @return 收入-部门17
	 */
	public UFDouble getShouru_bm17() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM17);
	}

	/**
	 * 设置收入-部门17
	 * 
	 * @param shouru_bm17
	 *            收入-部门17
	 */
	public void setShouru_bm17(UFDouble shouru_bm17) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM17, shouru_bm17);
	}

	/**
	 * 获取收入-部门18
	 * 
	 * @return 收入-部门18
	 */
	public UFDouble getShouru_bm18() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM18);
	}

	/**
	 * 设置收入-部门18
	 * 
	 * @param shouru_bm18
	 *            收入-部门18
	 */
	public void setShouru_bm18(UFDouble shouru_bm18) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM18, shouru_bm18);
	}

	/**
	 * 获取收入-部门19
	 * 
	 * @return 收入-部门19
	 */
	public UFDouble getShouru_bm19() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM19);
	}

	/**
	 * 设置收入-部门19
	 * 
	 * @param shouru_bm19
	 *            收入-部门19
	 */
	public void setShouru_bm19(UFDouble shouru_bm19) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM19, shouru_bm19);
	}

	/**
	 * 获取收入-部门20
	 * 
	 * @return 收入-部门20
	 */
	public UFDouble getShouru_bm20() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM20);
	}

	/**
	 * 设置收入-部门20
	 * 
	 * @param shouru_bm20
	 *            收入-部门20
	 */
	public void setShouru_bm20(UFDouble shouru_bm20) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM20, shouru_bm20);
	}

	/**
	 * 获取收入-部门21
	 * 
	 * @return 收入-部门21
	 */
	public UFDouble getShouru_bm21() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM21);
	}

	/**
	 * 设置收入-部门21
	 * 
	 * @param shouru_bm21
	 *            收入-部门21
	 */
	public void setShouru_bm21(UFDouble shouru_bm21) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM21, shouru_bm21);
	}

	/**
	 * 获取收入-部门22
	 * 
	 * @return 收入-部门22
	 */
	public UFDouble getShouru_bm22() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM22);
	}

	/**
	 * 设置收入-部门22
	 * 
	 * @param shouru_bm22
	 *            收入-部门22
	 */
	public void setShouru_bm22(UFDouble shouru_bm22) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM22, shouru_bm22);
	}

	/**
	 * 获取收入-部门23
	 * 
	 * @return 收入-部门23
	 */
	public UFDouble getShouru_bm23() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM23);
	}

	/**
	 * 设置收入-部门23
	 * 
	 * @param shouru_bm23
	 *            收入-部门23
	 */
	public void setShouru_bm23(UFDouble shouru_bm23) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM23, shouru_bm23);
	}

	/**
	 * 获取收入-部门24
	 * 
	 * @return 收入-部门24
	 */
	public UFDouble getShouru_bm24() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM24);
	}

	/**
	 * 设置收入-部门24
	 * 
	 * @param shouru_bm24
	 *            收入-部门24
	 */
	public void setShouru_bm24(UFDouble shouru_bm24) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM24, shouru_bm24);
	}

	/**
	 * 获取收入-部门25
	 * 
	 * @return 收入-部门25
	 */
	public UFDouble getShouru_bm25() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM25);
	}

	/**
	 * 设置收入-部门25
	 * 
	 * @param shouru_bm25
	 *            收入-部门25
	 */
	public void setShouru_bm25(UFDouble shouru_bm25) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM25, shouru_bm25);
	}

	/**
	 * 获取收入-部门26
	 * 
	 * @return 收入-部门26
	 */
	public UFDouble getShouru_bm26() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM26);
	}

	/**
	 * 设置收入-部门26
	 * 
	 * @param shouru_bm26
	 *            收入-部门26
	 */
	public void setShouru_bm26(UFDouble shouru_bm26) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM26, shouru_bm26);
	}

	/**
	 * 获取收入-部门27
	 * 
	 * @return 收入-部门27
	 */
	public UFDouble getShouru_bm27() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM27);
	}

	/**
	 * 设置收入-部门27
	 * 
	 * @param shouru_bm27
	 *            收入-部门27
	 */
	public void setShouru_bm27(UFDouble shouru_bm27) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM27, shouru_bm27);
	}

	/**
	 * 获取收入-部门28
	 * 
	 * @return 收入-部门28
	 */
	public UFDouble getShouru_bm28() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM28);
	}

	/**
	 * 设置收入-部门28
	 * 
	 * @param shouru_bm28
	 *            收入-部门28
	 */
	public void setShouru_bm28(UFDouble shouru_bm28) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM28, shouru_bm28);
	}

	/**
	 * 获取收入-部门29
	 * 
	 * @return 收入-部门29
	 */
	public UFDouble getShouru_bm29() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM29);
	}

	/**
	 * 设置收入-部门29
	 * 
	 * @param shouru_bm29
	 *            收入-部门29
	 */
	public void setShouru_bm29(UFDouble shouru_bm29) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM29, shouru_bm29);
	}

	/**
	 * 获取收入-部门30
	 * 
	 * @return 收入-部门30
	 */
	public UFDouble getShouru_bm30() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM30);
	}

	/**
	 * 设置收入-部门30
	 * 
	 * @param shouru_bm30
	 *            收入-部门30
	 */
	public void setShouru_bm30(UFDouble shouru_bm30) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM30, shouru_bm30);
	}

	/**
	 * 获取收入-部门31
	 * 
	 * @return 收入-部门31
	 */
	public UFDouble getShouru_bm31() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM31);
	}

	/**
	 * 设置收入-部门31
	 * 
	 * @param shouru_bm31
	 *            收入-部门31
	 */
	public void setShouru_bm31(UFDouble shouru_bm31) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM31, shouru_bm31);
	}

	/**
	 * 获取收入-部门32
	 * 
	 * @return 收入-部门32
	 */
	public UFDouble getShouru_bm32() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM32);
	}

	/**
	 * 设置收入-部门32
	 * 
	 * @param shouru_bm32
	 *            收入-部门32
	 */
	public void setShouru_bm32(UFDouble shouru_bm32) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM32, shouru_bm32);
	}

	/**
	 * 获取收入-部门33
	 * 
	 * @return 收入-部门33
	 */
	public UFDouble getShouru_bm33() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM33);
	}

	/**
	 * 设置收入-部门33
	 * 
	 * @param shouru_bm33
	 *            收入-部门33
	 */
	public void setShouru_bm33(UFDouble shouru_bm33) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM33, shouru_bm33);
	}

	/**
	 * 获取收入-部门34
	 * 
	 * @return 收入-部门34
	 */
	public UFDouble getShouru_bm34() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM34);
	}

	/**
	 * 设置收入-部门34
	 * 
	 * @param shouru_bm34
	 *            收入-部门34
	 */
	public void setShouru_bm34(UFDouble shouru_bm34) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM34, shouru_bm34);
	}

	/**
	 * 获取收入-部门35
	 * 
	 * @return 收入-部门35
	 */
	public UFDouble getShouru_bm35() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM35);
	}

	/**
	 * 设置收入-部门35
	 * 
	 * @param shouru_bm35
	 *            收入-部门35
	 */
	public void setShouru_bm35(UFDouble shouru_bm35) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM35, shouru_bm35);
	}

	/**
	 * 获取收入-部门36
	 * 
	 * @return 收入-部门36
	 */
	public UFDouble getShouru_bm36() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM36);
	}

	/**
	 * 设置收入-部门36
	 * 
	 * @param shouru_bm36
	 *            收入-部门36
	 */
	public void setShouru_bm36(UFDouble shouru_bm36) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM36, shouru_bm36);
	}

	/**
	 * 获取收入-部门37
	 * 
	 * @return 收入-部门37
	 */
	public UFDouble getShouru_bm37() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM37);
	}

	/**
	 * 设置收入-部门37
	 * 
	 * @param shouru_bm37
	 *            收入-部门37
	 */
	public void setShouru_bm37(UFDouble shouru_bm37) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM37, shouru_bm37);
	}

	/**
	 * 获取收入-部门38
	 * 
	 * @return 收入-部门38
	 */
	public UFDouble getShouru_bm38() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM38);
	}

	/**
	 * 设置收入-部门38
	 * 
	 * @param shouru_bm38
	 *            收入-部门38
	 */
	public void setShouru_bm38(UFDouble shouru_bm38) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM38, shouru_bm38);
	}

	/**
	 * 获取收入-部门39
	 * 
	 * @return 收入-部门39
	 */
	public UFDouble getShouru_bm39() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM39);
	}

	/**
	 * 设置收入-部门39
	 * 
	 * @param shouru_bm39
	 *            收入-部门39
	 */
	public void setShouru_bm39(UFDouble shouru_bm39) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM39, shouru_bm39);
	}

	/**
	 * 获取收入-部门40
	 * 
	 * @return 收入-部门40
	 */
	public UFDouble getShouru_bm40() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM40);
	}

	/**
	 * 设置收入-部门40
	 * 
	 * @param shouru_bm40
	 *            收入-部门40
	 */
	public void setShouru_bm40(UFDouble shouru_bm40) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM40, shouru_bm40);
	}

	/**
	 * 获取收入-部门41
	 * 
	 * @return 收入-部门41
	 */
	public UFDouble getShouru_bm41() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM41);
	}

	/**
	 * 设置收入-部门41
	 * 
	 * @param shouru_bm41
	 *            收入-部门41
	 */
	public void setShouru_bm41(UFDouble shouru_bm41) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM41, shouru_bm41);
	}

	/**
	 * 获取收入-部门42
	 * 
	 * @return 收入-部门42
	 */
	public UFDouble getShouru_bm42() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM42);
	}

	/**
	 * 设置收入-部门42
	 * 
	 * @param shouru_bm42
	 *            收入-部门42
	 */
	public void setShouru_bm42(UFDouble shouru_bm42) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM42, shouru_bm42);
	}

	/**
	 * 获取收入-部门43
	 * 
	 * @return 收入-部门43
	 */
	public UFDouble getShouru_bm43() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM43);
	}

	/**
	 * 设置收入-部门43
	 * 
	 * @param shouru_bm43
	 *            收入-部门43
	 */
	public void setShouru_bm43(UFDouble shouru_bm43) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM43, shouru_bm43);
	}

	/**
	 * 获取收入-部门44
	 * 
	 * @return 收入-部门44
	 */
	public UFDouble getShouru_bm44() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM44);
	}

	/**
	 * 设置收入-部门44
	 * 
	 * @param shouru_bm44
	 *            收入-部门44
	 */
	public void setShouru_bm44(UFDouble shouru_bm44) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM44, shouru_bm44);
	}

	/**
	 * 获取收入-部门45
	 * 
	 * @return 收入-部门45
	 */
	public UFDouble getShouru_bm45() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM45);
	}

	/**
	 * 设置收入-部门45
	 * 
	 * @param shouru_bm45
	 *            收入-部门45
	 */
	public void setShouru_bm45(UFDouble shouru_bm45) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM45, shouru_bm45);
	}

	/**
	 * 获取收入-部门46
	 * 
	 * @return 收入-部门46
	 */
	public UFDouble getShouru_bm46() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM46);
	}

	/**
	 * 设置收入-部门46
	 * 
	 * @param shouru_bm46
	 *            收入-部门46
	 */
	public void setShouru_bm46(UFDouble shouru_bm46) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM46, shouru_bm46);
	}

	/**
	 * 获取收入-部门47
	 * 
	 * @return 收入-部门47
	 */
	public UFDouble getShouru_bm47() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM47);
	}

	/**
	 * 设置收入-部门47
	 * 
	 * @param shouru_bm47
	 *            收入-部门47
	 */
	public void setShouru_bm47(UFDouble shouru_bm47) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM47, shouru_bm47);
	}

	/**
	 * 获取收入-部门48
	 * 
	 * @return 收入-部门48
	 */
	public UFDouble getShouru_bm48() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM48);
	}

	/**
	 * 设置收入-部门48
	 * 
	 * @param shouru_bm48
	 *            收入-部门48
	 */
	public void setShouru_bm48(UFDouble shouru_bm48) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM48, shouru_bm48);
	}

	/**
	 * 获取收入-部门49
	 * 
	 * @return 收入-部门49
	 */
	public UFDouble getShouru_bm49() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM49);
	}

	/**
	 * 设置收入-部门49
	 * 
	 * @param shouru_bm49
	 *            收入-部门49
	 */
	public void setShouru_bm49(UFDouble shouru_bm49) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM49, shouru_bm49);
	}

	/**
	 * 获取收入-部门50
	 * 
	 * @return 收入-部门50
	 */
	public UFDouble getShouru_bm50() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM50);
	}

	/**
	 * 设置收入-部门50
	 * 
	 * @param shouru_bm50
	 *            收入-部门50
	 */
	public void setShouru_bm50(UFDouble shouru_bm50) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM50, shouru_bm50);
	}

	/**
	 * 获取收入明细编码
	 * 
	 * @return 收入明细编码
	 */
	public String getSrmx_code() {
		return (String) this.getAttributeValue(YyribaoBVO.SRMX_CODE);
	}

	/**
	 * 设置收入明细编码
	 * 
	 * @param srmx_code
	 *            收入明细编码
	 */
	public void setSrmx_code(String srmx_code) {
		this.setAttributeValue(YyribaoBVO.SRMX_CODE, srmx_code);
	}

	/**
	 * 获取收入明细
	 * 
	 * @return 收入明细
	 */
	public String getSrmx_name() {
		return (String) this.getAttributeValue(YyribaoBVO.SRMX_NAME);
	}

	/**
	 * 设置收入明细
	 * 
	 * @param srmx_name
	 *            收入明细
	 */
	public void setSrmx_name(String srmx_name) {
		this.setAttributeValue(YyribaoBVO.SRMX_NAME, srmx_name);
	}

	/**
	 * 获取收入明细pk
	 * 
	 * @return 收入明细pk
	 */
	public String getSrmx_pk() {
		return (String) this.getAttributeValue(YyribaoBVO.SRMX_PK);
	}

	/**
	 * 设置收入明细pk
	 * 
	 * @param srmx_pk
	 *            收入明细pk
	 */
	public void setSrmx_pk(String srmx_pk) {
		this.setAttributeValue(YyribaoBVO.SRMX_PK, srmx_pk);
	}

	/**
	 * 获取时间戳
	 * 
	 * @return 时间戳
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(YyribaoBVO.TS);
	}

	/**
	 * 设置时间戳
	 * 
	 * @param ts
	 *            时间戳
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(YyribaoBVO.TS, ts);
	}

	/**
	 * 获取自定义项01
	 * 
	 * @return 自定义项01
	 */
	public String getVbdef01() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF01);
	}

	/**
	 * 设置自定义项01
	 * 
	 * @param vbdef01
	 *            自定义项01
	 */
	public void setVbdef01(String vbdef01) {
		this.setAttributeValue(YyribaoBVO.VBDEF01, vbdef01);
	}

	/**
	 * 获取自定义项02
	 * 
	 * @return 自定义项02
	 */
	public String getVbdef02() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF02);
	}

	/**
	 * 设置自定义项02
	 * 
	 * @param vbdef02
	 *            自定义项02
	 */
	public void setVbdef02(String vbdef02) {
		this.setAttributeValue(YyribaoBVO.VBDEF02, vbdef02);
	}

	/**
	 * 获取自定义项03
	 * 
	 * @return 自定义项03
	 */
	public String getVbdef03() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF03);
	}

	/**
	 * 设置自定义项03
	 * 
	 * @param vbdef03
	 *            自定义项03
	 */
	public void setVbdef03(String vbdef03) {
		this.setAttributeValue(YyribaoBVO.VBDEF03, vbdef03);
	}

	/**
	 * 获取自定义项04
	 * 
	 * @return 自定义项04
	 */
	public String getVbdef04() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF04);
	}

	/**
	 * 设置自定义项04
	 * 
	 * @param vbdef04
	 *            自定义项04
	 */
	public void setVbdef04(String vbdef04) {
		this.setAttributeValue(YyribaoBVO.VBDEF04, vbdef04);
	}

	/**
	 * 获取自定义项05
	 * 
	 * @return 自定义项05
	 */
	public String getVbdef05() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF05);
	}

	/**
	 * 设置自定义项05
	 * 
	 * @param vbdef05
	 *            自定义项05
	 */
	public void setVbdef05(String vbdef05) {
		this.setAttributeValue(YyribaoBVO.VBDEF05, vbdef05);
	}

	/**
	 * 获取自定义项06
	 * 
	 * @return 自定义项06
	 */
	public String getVbdef06() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF06);
	}

	/**
	 * 设置自定义项06
	 * 
	 * @param vbdef06
	 *            自定义项06
	 */
	public void setVbdef06(String vbdef06) {
		this.setAttributeValue(YyribaoBVO.VBDEF06, vbdef06);
	}

	/**
	 * 获取自定义项07
	 * 
	 * @return 自定义项07
	 */
	public String getVbdef07() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF07);
	}

	/**
	 * 设置自定义项07
	 * 
	 * @param vbdef07
	 *            自定义项07
	 */
	public void setVbdef07(String vbdef07) {
		this.setAttributeValue(YyribaoBVO.VBDEF07, vbdef07);
	}

	/**
	 * 获取自定义项08
	 * 
	 * @return 自定义项08
	 */
	public String getVbdef08() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF08);
	}

	/**
	 * 设置自定义项08
	 * 
	 * @param vbdef08
	 *            自定义项08
	 */
	public void setVbdef08(String vbdef08) {
		this.setAttributeValue(YyribaoBVO.VBDEF08, vbdef08);
	}

	/**
	 * 获取自定义项09
	 * 
	 * @return 自定义项09
	 */
	public String getVbdef09() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF09);
	}

	/**
	 * 设置自定义项09
	 * 
	 * @param vbdef09
	 *            自定义项09
	 */
	public void setVbdef09(String vbdef09) {
		this.setAttributeValue(YyribaoBVO.VBDEF09, vbdef09);
	}

	/**
	 * 获取自定义项10
	 * 
	 * @return 自定义项10
	 */
	public String getVbdef10() {
		return (String) this.getAttributeValue(YyribaoBVO.VBDEF10);
	}

	/**
	 * 设置自定义项10
	 * 
	 * @param vbdef10
	 *            自定义项10
	 */
	public void setVbdef10(String vbdef10) {
		this.setAttributeValue(YyribaoBVO.VBDEF10, vbdef10);
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getVbmemo() {
		return (String) this.getAttributeValue(YyribaoBVO.VBMEMO);
	}

	/**
	 * 设置备注
	 * 
	 * @param vbmemo
	 *            备注
	 */
	public void setVbmemo(String vbmemo) {
		this.setAttributeValue(YyribaoBVO.VBMEMO, vbmemo);
	}

	/**
	 * 获取源头单据号
	 * 
	 * @return 源头单据号
	 */
	public String getVfirstbillcode() {
		return (String) this.getAttributeValue(YyribaoBVO.VFIRSTBILLCODE);
	}

	/**
	 * 设置源头单据号
	 * 
	 * @param vfirstbillcode
	 *            源头单据号
	 */
	public void setVfirstbillcode(String vfirstbillcode) {
		this.setAttributeValue(YyribaoBVO.VFIRSTBILLCODE, vfirstbillcode);
	}

	/**
	 * 获取行号
	 * 
	 * @return 行号
	 */
	public Integer getVrowno() {
		return (Integer) this.getAttributeValue(YyribaoBVO.VROWNO);
	}

	/**
	 * 设置行号
	 * 
	 * @param vrowno
	 *            行号
	 */
	public void setVrowno(Integer vrowno) {
		this.setAttributeValue(YyribaoBVO.VROWNO, vrowno);
	}

	/**
	 * 获取上次单据号
	 * 
	 * @return 上次单据号
	 */
	public String getVsourcebillcode() {
		return (String) this.getAttributeValue(YyribaoBVO.VSOURCEBILLCODE);
	}

	/**
	 * 设置上次单据号
	 * 
	 * @param vsourcebillcode
	 *            上次单据号
	 */
	public void setVsourcebillcode(String vsourcebillcode) {
		this.setAttributeValue(YyribaoBVO.VSOURCEBILLCODE, vsourcebillcode);
	}

	/**
	 * 获取销售额（应收）
	 * 
	 * @return 销售额（应收）
	 */
	public UFDouble getYingshou() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.YINGSHOU);
	}

	/**
	 * 设置销售额（应收）
	 * 
	 * @param yingshou
	 *            销售额（应收）
	 */
	public void setYingshou(UFDouble yingshou) {
		this.setAttributeValue(YyribaoBVO.YINGSHOU, yingshou);
	}

	/**
	 * 获取卡比例优惠
	 * 
	 * @return 卡比例优惠
	 */
	public UFDouble getYouhui_kabili() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.YOUHUI_KABILI);
	}

	/**
	 * 设置卡比例优惠
	 * 
	 * @param youhui_kabili
	 *            卡比例优惠
	 */
	public void setYouhui_kabili(UFDouble youhui_kabili) {
		this.setAttributeValue(YyribaoBVO.YOUHUI_KABILI, youhui_kabili);
	}

	/**
	 * 获取手工优惠
	 * 
	 * @return 手工优惠
	 */
	public UFDouble getYouhui_shougong() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.YOUHUI_SHOUGONG);
	}

	/**
	 * 设置手工优惠
	 * 
	 * @param youhui_shougong
	 *            手工优惠
	 */
	public void setYouhui_shougong(UFDouble youhui_shougong) {
		this.setAttributeValue(YyribaoBVO.YOUHUI_SHOUGONG, youhui_shougong);
	}

	/**
	 * 获取自动优惠
	 * 
	 * @return 自动优惠
	 */
	public UFDouble getYouhui_zidong() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.YOUHUI_ZIDONG);
	}

	/**
	 * 设置自动优惠
	 * 
	 * @param youhui_zidong
	 *            自动优惠
	 */
	public void setYouhui_zidong(UFDouble youhui_zidong) {
		this.setAttributeValue(YyribaoBVO.YOUHUI_ZIDONG, youhui_zidong);
	}

	/**
	 * 获取占位列1
	 * 
	 * @return 占位列1
	 */
	public String getZhanwei01() {
		return (String) this.getAttributeValue(YyribaoBVO.ZHANWEI01);
	}

	/**
	 * 设置占位列1
	 * 
	 * @param zhanwei01
	 *            占位列1
	 */
	public void setZhanwei01(String zhanwei01) {
		this.setAttributeValue(YyribaoBVO.ZHANWEI01, zhanwei01);
	}

	/**
	 * 获取占位列2
	 * 
	 * @return 占位列2
	 */
	public String getZhanwei02() {
		return (String) this.getAttributeValue(YyribaoBVO.ZHANWEI02);
	}

	/**
	 * 设置占位列2
	 * 
	 * @param zhanwei02
	 *            占位列2
	 */
	public void setZhanwei02(String zhanwei02) {
		this.setAttributeValue(YyribaoBVO.ZHANWEI02, zhanwei02);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_YyribaoBVO");
	}
}