package nc.vo.hkjt.srgk.huiguan.jzfs;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class JzfsHVO extends SuperVO {
	/**
	 * 编码
	 */
	public static final String CODE = "code";
	/**
	 * 创建时间
	 */
	public static final String CREATIONTIME = "creationtime";
	/**
	 * 创建人
	 */
	public static final String CREATOR = "creator";
	/**
	 * 层级编号
	 */
	public static final String LEVELNO = "levelno";
	/**
	 * 修改时间
	 */
	public static final String MODIFIEDTIME = "modifiedtime";
	/**
	 * 修改人
	 */
	public static final String MODIFIER = "modifier";
	/**
	 * 名称
	 */
	public static final String NAME = "name";
	/**
	 * 部门
	 */
	public static final String PK_DEPT = "pk_dept";
	/**
	 * 部门版本
	 */
	public static final String PK_DEPT_V = "pk_dept_v";
	/**
	 * 集团
	 */
	public static final String PK_GROUP = "pk_group";
	/**
	 * 主键
	 */
	public static final String PK_HK_SRGK_HG_JZFS = "pk_hk_srgk_hg_jzfs";
	/**
	 * 会计科目
	 */
	public static final String PK_KJKM = "pk_kjkm";
	/**
	 * 组织
	 */
	public static final String PK_ORG = "pk_org";
	/**
	 * 组织版本
	 */
	public static final String PK_ORG_V = "pk_org_v";
	/**
	 * 上级结账方式
	 */
	public static final String PK_PARENT = "pk_parent";
	/**
	 * 显示顺序
	 */
	public static final String SHOWORDER = "showorder";
	/**
	 * 时间戳
	 */
	public static final String TS = "ts";
	/**
	 * 自定义项1
	 */
	public static final String VDEF1 = "vdef1";
	/**
	 * 自定义项2
	 */
	public static final String VDEF2 = "vdef2";
	/**
	 * 自定义项3
	 */
	public static final String VDEF3 = "vdef3";
	/**
	 * 自定义项4
	 */
	public static final String VDEF4 = "vdef4";
	/**
	 * 自定义项5
	 */
	public static final String VDEF5 = "vdef5";
	public static final String JDINFO = "jdinfo";

	public Integer getJdinfo() {
		return (Integer) this.getAttributeValue(JzfsHVO.JDINFO);
	}

	public void setJdinfo(Integer jdinfo) {
		this.setAttributeValue(JzfsHVO.JDINFO, jdinfo);
	}

	/**
	 * 获取编码
	 * 
	 * @return 编码
	 */
	public String getCode() {
		return (String) this.getAttributeValue(JzfsHVO.CODE);
	}

	/**
	 * 设置编码
	 * 
	 * @param code
	 *            编码
	 */
	public void setCode(String code) {
		this.setAttributeValue(JzfsHVO.CODE, code);
	}

	/**
	 * 获取创建时间
	 * 
	 * @return 创建时间
	 */
	public UFDateTime getCreationtime() {
		return (UFDateTime) this.getAttributeValue(JzfsHVO.CREATIONTIME);
	}

	/**
	 * 设置创建时间
	 * 
	 * @param creationtime
	 *            创建时间
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.setAttributeValue(JzfsHVO.CREATIONTIME, creationtime);
	}

	/**
	 * 获取创建人
	 * 
	 * @return 创建人
	 */
	public String getCreator() {
		return (String) this.getAttributeValue(JzfsHVO.CREATOR);
	}

	/**
	 * 设置创建人
	 * 
	 * @param creator
	 *            创建人
	 */
	public void setCreator(String creator) {
		this.setAttributeValue(JzfsHVO.CREATOR, creator);
	}

	/**
	 * 获取层级编号
	 * 
	 * @return 层级编号
	 */
	public Integer getLevelno() {
		return (Integer) this.getAttributeValue(JzfsHVO.LEVELNO);
	}

	/**
	 * 设置层级编号
	 * 
	 * @param levelno
	 *            层级编号
	 */
	public void setLevelno(Integer levelno) {
		this.setAttributeValue(JzfsHVO.LEVELNO, levelno);
	}

	/**
	 * 获取修改时间
	 * 
	 * @return 修改时间
	 */
	public UFDateTime getModifiedtime() {
		return (UFDateTime) this.getAttributeValue(JzfsHVO.MODIFIEDTIME);
	}

	/**
	 * 设置修改时间
	 * 
	 * @param modifiedtime
	 *            修改时间
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.setAttributeValue(JzfsHVO.MODIFIEDTIME, modifiedtime);
	}

	/**
	 * 获取修改人
	 * 
	 * @return 修改人
	 */
	public String getModifier() {
		return (String) this.getAttributeValue(JzfsHVO.MODIFIER);
	}

	/**
	 * 设置修改人
	 * 
	 * @param modifier
	 *            修改人
	 */
	public void setModifier(String modifier) {
		this.setAttributeValue(JzfsHVO.MODIFIER, modifier);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return (String) this.getAttributeValue(JzfsHVO.NAME);
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.setAttributeValue(JzfsHVO.NAME, name);
	}

	/**
	 * 获取部门
	 * 
	 * @return 部门
	 */
	public String getPk_dept() {
		return (String) this.getAttributeValue(JzfsHVO.PK_DEPT);
	}

	/**
	 * 设置部门
	 * 
	 * @param pk_dept
	 *            部门
	 */
	public void setPk_dept(String pk_dept) {
		this.setAttributeValue(JzfsHVO.PK_DEPT, pk_dept);
	}

	/**
	 * 获取部门版本
	 * 
	 * @return 部门版本
	 */
	public String getPk_dept_v() {
		return (String) this.getAttributeValue(JzfsHVO.PK_DEPT_V);
	}

	/**
	 * 设置部门版本
	 * 
	 * @param pk_dept_v
	 *            部门版本
	 */
	public void setPk_dept_v(String pk_dept_v) {
		this.setAttributeValue(JzfsHVO.PK_DEPT_V, pk_dept_v);
	}

	/**
	 * 获取集团
	 * 
	 * @return 集团
	 */
	public String getPk_group() {
		return (String) this.getAttributeValue(JzfsHVO.PK_GROUP);
	}

	/**
	 * 设置集团
	 * 
	 * @param pk_group
	 *            集团
	 */
	public void setPk_group(String pk_group) {
		this.setAttributeValue(JzfsHVO.PK_GROUP, pk_group);
	}

	/**
	 * 获取主键
	 * 
	 * @return 主键
	 */
	public String getPk_hk_srgk_hg_jzfs() {
		return (String) this.getAttributeValue(JzfsHVO.PK_HK_SRGK_HG_JZFS);
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_hk_srgk_hg_jzfs
	 *            主键
	 */
	public void setPk_hk_srgk_hg_jzfs(String pk_hk_srgk_hg_jzfs) {
		this.setAttributeValue(JzfsHVO.PK_HK_SRGK_HG_JZFS, pk_hk_srgk_hg_jzfs);
	}

	/**
	 * 获取会计科目
	 * 
	 * @return 会计科目
	 */
	public String getPk_kjkm() {
		return (String) this.getAttributeValue(JzfsHVO.PK_KJKM);
	}

	/**
	 * 设置会计科目
	 * 
	 * @param pk_kjkm
	 *            会计科目
	 */
	public void setPk_kjkm(String pk_kjkm) {
		this.setAttributeValue(JzfsHVO.PK_KJKM, pk_kjkm);
	}

	/**
	 * 获取组织
	 * 
	 * @return 组织
	 */
	public String getPk_org() {
		return (String) this.getAttributeValue(JzfsHVO.PK_ORG);
	}

	/**
	 * 设置组织
	 * 
	 * @param pk_org
	 *            组织
	 */
	public void setPk_org(String pk_org) {
		this.setAttributeValue(JzfsHVO.PK_ORG, pk_org);
	}

	/**
	 * 获取组织版本
	 * 
	 * @return 组织版本
	 */
	public String getPk_org_v() {
		return (String) this.getAttributeValue(JzfsHVO.PK_ORG_V);
	}

	/**
	 * 设置组织版本
	 * 
	 * @param pk_org_v
	 *            组织版本
	 */
	public void setPk_org_v(String pk_org_v) {
		this.setAttributeValue(JzfsHVO.PK_ORG_V, pk_org_v);
	}

	/**
	 * 获取上级结账方式
	 * 
	 * @return 上级结账方式
	 */
	public String getPk_parent() {
		return (String) this.getAttributeValue(JzfsHVO.PK_PARENT);
	}

	/**
	 * 设置上级结账方式
	 * 
	 * @param pk_parent
	 *            上级结账方式
	 */
	public void setPk_parent(String pk_parent) {
		this.setAttributeValue(JzfsHVO.PK_PARENT, pk_parent);
	}

	/**
	 * 获取显示顺序
	 * 
	 * @return 显示顺序
	 */
	public Integer getShoworder() {
		return (Integer) this.getAttributeValue(JzfsHVO.SHOWORDER);
	}

	/**
	 * 设置显示顺序
	 * 
	 * @param showorder
	 *            显示顺序
	 */
	public void setShoworder(Integer showorder) {
		this.setAttributeValue(JzfsHVO.SHOWORDER, showorder);
	}

	/**
	 * 获取时间戳
	 * 
	 * @return 时间戳
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(JzfsHVO.TS);
	}

	/**
	 * 设置时间戳
	 * 
	 * @param ts
	 *            时间戳
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(JzfsHVO.TS, ts);
	}

	/**
	 * 获取自定义项1
	 * 
	 * @return 自定义项1
	 */
	public String getVdef1() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF1);
	}

	/**
	 * 设置自定义项1
	 * 
	 * @param vdef1
	 *            自定义项1
	 */
	public void setVdef1(String vdef1) {
		this.setAttributeValue(JzfsHVO.VDEF1, vdef1);
	}

	/**
	 * 获取自定义项2
	 * 
	 * @return 自定义项2
	 */
	public String getVdef2() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF2);
	}

	/**
	 * 设置自定义项2
	 * 
	 * @param vdef2
	 *            自定义项2
	 */
	public void setVdef2(String vdef2) {
		this.setAttributeValue(JzfsHVO.VDEF2, vdef2);
	}

	/**
	 * 获取自定义项3
	 * 
	 * @return 自定义项3
	 */
	public String getVdef3() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF3);
	}

	/**
	 * 设置自定义项3
	 * 
	 * @param vdef3
	 *            自定义项3
	 */
	public void setVdef3(String vdef3) {
		this.setAttributeValue(JzfsHVO.VDEF3, vdef3);
	}

	/**
	 * 获取自定义项4
	 * 
	 * @return 自定义项4
	 */
	public String getVdef4() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF4);
	}

	/**
	 * 设置自定义项4
	 * 
	 * @param vdef4
	 *            自定义项4
	 */
	public void setVdef4(String vdef4) {
		this.setAttributeValue(JzfsHVO.VDEF4, vdef4);
	}

	/**
	 * 获取自定义项5
	 * 
	 * @return 自定义项5
	 */
	public String getVdef5() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF5);
	}

	/**
	 * 设置自定义项5
	 * 
	 * @param vdef5
	 *            自定义项5
	 */
	public void setVdef5(String vdef5) {
		this.setAttributeValue(JzfsHVO.VDEF5, vdef5);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.jzfsh");
	}
}