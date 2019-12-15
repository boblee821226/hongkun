package nc.vo.hkjt.srgk.huiguan.srdibiao;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SrdibiaoHVO extends SuperVO {
	// 部门主键对应的自定义字段信息
	private Map<String, String[]> vdefdeptinfo = new HashMap<String, String[]>();

	public Map<String, String[]> getVdefdeptinfo() {
		return vdefdeptinfo;
	}

	public void setVdefdeptinfo(Map<String, String[]> vdefdeptinfo) {
		this.vdefdeptinfo = vdefdeptinfo;
	}

	private Integer dr;
	
	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * 审核人
	 */
	public static final String APPROVER = "approver";
	/**
	 * 制单时间
	 */
	public static final String CREATIONTIME = "creationtime";
	/**
	 * 制单人
	 */
	public static final String CREATOR = "creator";
	/**
	 * 单据日期
	 */
	public static final String DBILLDATE = "dbilldate";
	/**
	 * 单据状态
	 */
	public static final String IBILLSTATUS = "ibillstatus";
	/**
	 * 修改时间
	 */
	public static final String MODIFIEDTIME = "modifiedtime";
	/**
	 * 修改人
	 */
	public static final String MODIFIER = "modifier";
	/**
	 * 业务类型
	 */
	public static final String PK_BUSITYPE = "pk_busitype";
	/**
	 * 部门id
	 */
	public static final String PK_DEPT = "pk_dept";
	/**
	 * 集团
	 */
	public static final String PK_GROUP = "pk_group";
	/**
	 * 主表主键
	 */
	public static final String PK_HK_SRGK_HG_SRDIBIAO = "pk_hk_srgk_hg_srdibiao";
	/**
	 * 组织
	 */
	public static final String PK_ORG = "pk_org";
	/**
	 * 组织v
	 */
	public static final String PK_ORG_V = "pk_org_v";
	/**
	 * 审核时间
	 */
	public static final String TAUDITTIME = "taudittime";
	/**
	 * 时间戳
	 */
	public static final String TS = "ts";
	/**
	 * 审批批语
	 */
	public static final String VAPPROVENOTE = "vapprovenote";
	/**
	 * 单据编号
	 */
	public static final String VBILLCODE = "vbillcode";
	/**
	 * 单据类型
	 */
	public static final String VBILLTYPECODE = "vbilltypecode";
	/**
	 * 自定义项01
	 */
	public static final String VDEF01 = "vdef01";
	/**
	 * 自定义项02
	 */
	public static final String VDEF02 = "vdef02";
	/**
	 * 自定义项03
	 */
	public static final String VDEF03 = "vdef03";
	/**
	 * 自定义项04
	 */
	public static final String VDEF04 = "vdef04";
	/**
	 * 自定义项05
	 */
	public static final String VDEF05 = "vdef05";
	/**
	 * 自定义项06
	 */
	public static final String VDEF06 = "vdef06";
	/**
	 * 自定义项07
	 */
	public static final String VDEF07 = "vdef07";
	/**
	 * 自定义项08
	 */
	public static final String VDEF08 = "vdef08";
	/**
	 * 自定义项09
	 */
	public static final String VDEF09 = "vdef09";
	/**
	 * 自定义项10
	 */
	public static final String VDEF10 = "vdef10";
	/**
	 * 备注
	 */
	public static final String VMEMO = "vmemo";
	/**
	 * 交易类型
	 */
	public static final String VTRANTYPECODE = "vtrantypecode";

	/**
	 * 获取审核人
	 * 
	 * @return 审核人
	 */
	public String getApprover() {
		return (String) this.getAttributeValue(SrdibiaoHVO.APPROVER);
	}

	/**
	 * 设置审核人
	 * 
	 * @param approver
	 *            审核人
	 */
	public void setApprover(String approver) {
		this.setAttributeValue(SrdibiaoHVO.APPROVER, approver);
	}

	/**
	 * 获取制单时间
	 * 
	 * @return 制单时间
	 */
	public UFDateTime getCreationtime() {
		return (UFDateTime) this.getAttributeValue(SrdibiaoHVO.CREATIONTIME);
	}

	/**
	 * 设置制单时间
	 * 
	 * @param creationtime
	 *            制单时间
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.setAttributeValue(SrdibiaoHVO.CREATIONTIME, creationtime);
	}

	/**
	 * 获取制单人
	 * 
	 * @return 制单人
	 */
	public String getCreator() {
		return (String) this.getAttributeValue(SrdibiaoHVO.CREATOR);
	}

	/**
	 * 设置制单人
	 * 
	 * @param creator
	 *            制单人
	 */
	public void setCreator(String creator) {
		this.setAttributeValue(SrdibiaoHVO.CREATOR, creator);
	}

	/**
	 * 获取单据日期
	 * 
	 * @return 单据日期
	 */
	public UFDate getDbilldate() {
		return (UFDate) this.getAttributeValue(SrdibiaoHVO.DBILLDATE);
	}

	/**
	 * 设置单据日期
	 * 
	 * @param dbilldate
	 *            单据日期
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.setAttributeValue(SrdibiaoHVO.DBILLDATE, dbilldate);
	}

	/**
	 * 获取单据状态
	 * 
	 * @return 单据状态
	 * @see String
	 */
	public Integer getIbillstatus() {
		return (Integer) this.getAttributeValue(SrdibiaoHVO.IBILLSTATUS);
	}

	/**
	 * 设置单据状态
	 * 
	 * @param ibillstatus
	 *            单据状态
	 * @see String
	 */
	public void setIbillstatus(Integer ibillstatus) {
		this.setAttributeValue(SrdibiaoHVO.IBILLSTATUS, ibillstatus);
	}

	/**
	 * 获取修改时间
	 * 
	 * @return 修改时间
	 */
	public UFDateTime getModifiedtime() {
		return (UFDateTime) this.getAttributeValue(SrdibiaoHVO.MODIFIEDTIME);
	}

	/**
	 * 设置修改时间
	 * 
	 * @param modifiedtime
	 *            修改时间
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.setAttributeValue(SrdibiaoHVO.MODIFIEDTIME, modifiedtime);
	}

	/**
	 * 获取修改人
	 * 
	 * @return 修改人
	 */
	public String getModifier() {
		return (String) this.getAttributeValue(SrdibiaoHVO.MODIFIER);
	}

	/**
	 * 设置修改人
	 * 
	 * @param modifier
	 *            修改人
	 */
	public void setModifier(String modifier) {
		this.setAttributeValue(SrdibiaoHVO.MODIFIER, modifier);
	}

	/**
	 * 获取业务类型
	 * 
	 * @return 业务类型
	 */
	public String getPk_busitype() {
		return (String) this.getAttributeValue(SrdibiaoHVO.PK_BUSITYPE);
	}

	/**
	 * 设置业务类型
	 * 
	 * @param pk_busitype
	 *            业务类型
	 */
	public void setPk_busitype(String pk_busitype) {
		this.setAttributeValue(SrdibiaoHVO.PK_BUSITYPE, pk_busitype);
	}

	/**
	 * 获取部门id
	 * 
	 * @return 部门id
	 */
	public String getPk_dept() {
		return (String) this.getAttributeValue(SrdibiaoHVO.PK_DEPT);
	}

	/**
	 * 设置部门id
	 * 
	 * @param pk_dept
	 *            部门id
	 */
	public void setPk_dept(String pk_dept) {
		this.setAttributeValue(SrdibiaoHVO.PK_DEPT, pk_dept);
	}

	/**
	 * 获取集团
	 * 
	 * @return 集团
	 */
	public String getPk_group() {
		return (String) this.getAttributeValue(SrdibiaoHVO.PK_GROUP);
	}

	/**
	 * 设置集团
	 * 
	 * @param pk_group
	 *            集团
	 */
	public void setPk_group(String pk_group) {
		this.setAttributeValue(SrdibiaoHVO.PK_GROUP, pk_group);
	}

	/**
	 * 获取主表主键
	 * 
	 * @return 主表主键
	 */
	public String getPk_hk_srgk_hg_srdibiao() {
		return (String) this
				.getAttributeValue(SrdibiaoHVO.PK_HK_SRGK_HG_SRDIBIAO);
	}

	/**
	 * 设置主表主键
	 * 
	 * @param pk_hk_srgk_hg_srdibiao
	 *            主表主键
	 */
	public void setPk_hk_srgk_hg_srdibiao(String pk_hk_srgk_hg_srdibiao) {
		this.setAttributeValue(SrdibiaoHVO.PK_HK_SRGK_HG_SRDIBIAO,
				pk_hk_srgk_hg_srdibiao);
	}

	/**
	 * 获取组织
	 * 
	 * @return 组织
	 */
	public String getPk_org() {
		return (String) this.getAttributeValue(SrdibiaoHVO.PK_ORG);
	}

	/**
	 * 设置组织
	 * 
	 * @param pk_org
	 *            组织
	 */
	public void setPk_org(String pk_org) {
		this.setAttributeValue(SrdibiaoHVO.PK_ORG, pk_org);
	}

	/**
	 * 获取组织v
	 * 
	 * @return 组织v
	 */
	public String getPk_org_v() {
		return (String) this.getAttributeValue(SrdibiaoHVO.PK_ORG_V);
	}

	/**
	 * 设置组织v
	 * 
	 * @param pk_org_v
	 *            组织v
	 */
	public void setPk_org_v(String pk_org_v) {
		this.setAttributeValue(SrdibiaoHVO.PK_ORG_V, pk_org_v);
	}

	/**
	 * 获取审核时间
	 * 
	 * @return 审核时间
	 */
	public UFDateTime getTaudittime() {
		return (UFDateTime) this.getAttributeValue(SrdibiaoHVO.TAUDITTIME);
	}

	/**
	 * 设置审核时间
	 * 
	 * @param taudittime
	 *            审核时间
	 */
	public void setTaudittime(UFDateTime taudittime) {
		this.setAttributeValue(SrdibiaoHVO.TAUDITTIME, taudittime);
	}

	/**
	 * 获取时间戳
	 * 
	 * @return 时间戳
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(SrdibiaoHVO.TS);
	}

	/**
	 * 设置时间戳
	 * 
	 * @param ts
	 *            时间戳
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(SrdibiaoHVO.TS, ts);
	}

	/**
	 * 获取审批批语
	 * 
	 * @return 审批批语
	 */
	public String getVapprovenote() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VAPPROVENOTE);
	}

	/**
	 * 设置审批批语
	 * 
	 * @param vapprovenote
	 *            审批批语
	 */
	public void setVapprovenote(String vapprovenote) {
		this.setAttributeValue(SrdibiaoHVO.VAPPROVENOTE, vapprovenote);
	}

	/**
	 * 获取单据编号
	 * 
	 * @return 单据编号
	 */
	public String getVbillcode() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VBILLCODE);
	}

	/**
	 * 设置单据编号
	 * 
	 * @param vbillcode
	 *            单据编号
	 */
	public void setVbillcode(String vbillcode) {
		this.setAttributeValue(SrdibiaoHVO.VBILLCODE, vbillcode);
	}

	/**
	 * 获取单据类型
	 * 
	 * @return 单据类型
	 */
	public String getVbilltypecode() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VBILLTYPECODE);
	}

	/**
	 * 设置单据类型
	 * 
	 * @param vbilltypecode
	 *            单据类型
	 */
	public void setVbilltypecode(String vbilltypecode) {
		this.setAttributeValue(SrdibiaoHVO.VBILLTYPECODE, vbilltypecode);
	}

	/**
	 * 获取自定义项01
	 * 
	 * @return 自定义项01
	 */
	public String getVdef01() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF01);
	}

	/**
	 * 设置自定义项01
	 * 
	 * @param vdef01
	 *            自定义项01
	 */
	public void setVdef01(String vdef01) {
		this.setAttributeValue(SrdibiaoHVO.VDEF01, vdef01);
	}

	/**
	 * 获取自定义项02
	 * 
	 * @return 自定义项02
	 */
	public String getVdef02() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF02);
	}

	/**
	 * 设置自定义项02
	 * 
	 * @param vdef02
	 *            自定义项02
	 */
	public void setVdef02(String vdef02) {
		this.setAttributeValue(SrdibiaoHVO.VDEF02, vdef02);
	}

	/**
	 * 获取自定义项03
	 * 
	 * @return 自定义项03
	 */
	public String getVdef03() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF03);
	}

	/**
	 * 设置自定义项03
	 * 
	 * @param vdef03
	 *            自定义项03
	 */
	public void setVdef03(String vdef03) {
		this.setAttributeValue(SrdibiaoHVO.VDEF03, vdef03);
	}

	/**
	 * 获取自定义项04
	 * 
	 * @return 自定义项04
	 */
	public String getVdef04() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF04);
	}

	/**
	 * 设置自定义项04
	 * 
	 * @param vdef04
	 *            自定义项04
	 */
	public void setVdef04(String vdef04) {
		this.setAttributeValue(SrdibiaoHVO.VDEF04, vdef04);
	}

	/**
	 * 获取自定义项05
	 * 
	 * @return 自定义项05
	 */
	public String getVdef05() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF05);
	}

	/**
	 * 设置自定义项05
	 * 
	 * @param vdef05
	 *            自定义项05
	 */
	public void setVdef05(String vdef05) {
		this.setAttributeValue(SrdibiaoHVO.VDEF05, vdef05);
	}

	/**
	 * 获取自定义项06
	 * 
	 * @return 自定义项06
	 */
	public String getVdef06() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF06);
	}

	/**
	 * 设置自定义项06
	 * 
	 * @param vdef06
	 *            自定义项06
	 */
	public void setVdef06(String vdef06) {
		this.setAttributeValue(SrdibiaoHVO.VDEF06, vdef06);
	}

	/**
	 * 获取自定义项07
	 * 
	 * @return 自定义项07
	 */
	public String getVdef07() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF07);
	}

	/**
	 * 设置自定义项07
	 * 
	 * @param vdef07
	 *            自定义项07
	 */
	public void setVdef07(String vdef07) {
		this.setAttributeValue(SrdibiaoHVO.VDEF07, vdef07);
	}

	/**
	 * 获取自定义项08
	 * 
	 * @return 自定义项08
	 */
	public String getVdef08() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF08);
	}

	/**
	 * 设置自定义项08
	 * 
	 * @param vdef08
	 *            自定义项08
	 */
	public void setVdef08(String vdef08) {
		this.setAttributeValue(SrdibiaoHVO.VDEF08, vdef08);
	}

	/**
	 * 获取自定义项09
	 * 
	 * @return 自定义项09
	 */
	public String getVdef09() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF09);
	}

	/**
	 * 设置自定义项09
	 * 
	 * @param vdef09
	 *            自定义项09
	 */
	public void setVdef09(String vdef09) {
		this.setAttributeValue(SrdibiaoHVO.VDEF09, vdef09);
	}

	/**
	 * 获取自定义项10
	 * 
	 * @return 自定义项10
	 */
	public String getVdef10() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VDEF10);
	}

	/**
	 * 设置自定义项10
	 * 
	 * @param vdef10
	 *            自定义项10
	 */
	public void setVdef10(String vdef10) {
		this.setAttributeValue(SrdibiaoHVO.VDEF10, vdef10);
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getVmemo() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VMEMO);
	}

	/**
	 * 设置备注
	 * 
	 * @param vmemo
	 *            备注
	 */
	public void setVmemo(String vmemo) {
		this.setAttributeValue(SrdibiaoHVO.VMEMO, vmemo);
	}

	/**
	 * 获取交易类型
	 * 
	 * @return 交易类型
	 */
	public String getVtrantypecode() {
		return (String) this.getAttributeValue(SrdibiaoHVO.VTRANTYPECODE);
	}

	/**
	 * 设置交易类型
	 * 
	 * @param vtrantypecode
	 *            交易类型
	 */
	public void setVtrantypecode(String vtrantypecode) {
		this.setAttributeValue(SrdibiaoHVO.VTRANTYPECODE, vtrantypecode);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_SrdibiaoHVO");
	}
}