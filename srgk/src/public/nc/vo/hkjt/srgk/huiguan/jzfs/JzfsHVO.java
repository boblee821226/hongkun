package nc.vo.hkjt.srgk.huiguan.jzfs;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class JzfsHVO extends SuperVO {
	/**
	 * ����
	 */
	public static final String CODE = "code";
	/**
	 * ����ʱ��
	 */
	public static final String CREATIONTIME = "creationtime";
	/**
	 * ������
	 */
	public static final String CREATOR = "creator";
	/**
	 * �㼶���
	 */
	public static final String LEVELNO = "levelno";
	/**
	 * �޸�ʱ��
	 */
	public static final String MODIFIEDTIME = "modifiedtime";
	/**
	 * �޸���
	 */
	public static final String MODIFIER = "modifier";
	/**
	 * ����
	 */
	public static final String NAME = "name";
	/**
	 * ����
	 */
	public static final String PK_DEPT = "pk_dept";
	/**
	 * ���Ű汾
	 */
	public static final String PK_DEPT_V = "pk_dept_v";
	/**
	 * ����
	 */
	public static final String PK_GROUP = "pk_group";
	/**
	 * ����
	 */
	public static final String PK_HK_SRGK_HG_JZFS = "pk_hk_srgk_hg_jzfs";
	/**
	 * ��ƿ�Ŀ
	 */
	public static final String PK_KJKM = "pk_kjkm";
	/**
	 * ��֯
	 */
	public static final String PK_ORG = "pk_org";
	/**
	 * ��֯�汾
	 */
	public static final String PK_ORG_V = "pk_org_v";
	/**
	 * �ϼ����˷�ʽ
	 */
	public static final String PK_PARENT = "pk_parent";
	/**
	 * ��ʾ˳��
	 */
	public static final String SHOWORDER = "showorder";
	/**
	 * ʱ���
	 */
	public static final String TS = "ts";
	/**
	 * �Զ�����1
	 */
	public static final String VDEF1 = "vdef1";
	/**
	 * �Զ�����2
	 */
	public static final String VDEF2 = "vdef2";
	/**
	 * �Զ�����3
	 */
	public static final String VDEF3 = "vdef3";
	/**
	 * �Զ�����4
	 */
	public static final String VDEF4 = "vdef4";
	/**
	 * �Զ�����5
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
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getCode() {
		return (String) this.getAttributeValue(JzfsHVO.CODE);
	}

	/**
	 * ���ñ���
	 * 
	 * @param code
	 *            ����
	 */
	public void setCode(String code) {
		this.setAttributeValue(JzfsHVO.CODE, code);
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ��
	 */
	public UFDateTime getCreationtime() {
		return (UFDateTime) this.getAttributeValue(JzfsHVO.CREATIONTIME);
	}

	/**
	 * ���ô���ʱ��
	 * 
	 * @param creationtime
	 *            ����ʱ��
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.setAttributeValue(JzfsHVO.CREATIONTIME, creationtime);
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public String getCreator() {
		return (String) this.getAttributeValue(JzfsHVO.CREATOR);
	}

	/**
	 * ���ô�����
	 * 
	 * @param creator
	 *            ������
	 */
	public void setCreator(String creator) {
		this.setAttributeValue(JzfsHVO.CREATOR, creator);
	}

	/**
	 * ��ȡ�㼶���
	 * 
	 * @return �㼶���
	 */
	public Integer getLevelno() {
		return (Integer) this.getAttributeValue(JzfsHVO.LEVELNO);
	}

	/**
	 * ���ò㼶���
	 * 
	 * @param levelno
	 *            �㼶���
	 */
	public void setLevelno(Integer levelno) {
		this.setAttributeValue(JzfsHVO.LEVELNO, levelno);
	}

	/**
	 * ��ȡ�޸�ʱ��
	 * 
	 * @return �޸�ʱ��
	 */
	public UFDateTime getModifiedtime() {
		return (UFDateTime) this.getAttributeValue(JzfsHVO.MODIFIEDTIME);
	}

	/**
	 * �����޸�ʱ��
	 * 
	 * @param modifiedtime
	 *            �޸�ʱ��
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.setAttributeValue(JzfsHVO.MODIFIEDTIME, modifiedtime);
	}

	/**
	 * ��ȡ�޸���
	 * 
	 * @return �޸���
	 */
	public String getModifier() {
		return (String) this.getAttributeValue(JzfsHVO.MODIFIER);
	}

	/**
	 * �����޸���
	 * 
	 * @param modifier
	 *            �޸���
	 */
	public void setModifier(String modifier) {
		this.setAttributeValue(JzfsHVO.MODIFIER, modifier);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getName() {
		return (String) this.getAttributeValue(JzfsHVO.NAME);
	}

	/**
	 * ��������
	 * 
	 * @param name
	 *            ����
	 */
	public void setName(String name) {
		this.setAttributeValue(JzfsHVO.NAME, name);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_dept() {
		return (String) this.getAttributeValue(JzfsHVO.PK_DEPT);
	}

	/**
	 * ���ò���
	 * 
	 * @param pk_dept
	 *            ����
	 */
	public void setPk_dept(String pk_dept) {
		this.setAttributeValue(JzfsHVO.PK_DEPT, pk_dept);
	}

	/**
	 * ��ȡ���Ű汾
	 * 
	 * @return ���Ű汾
	 */
	public String getPk_dept_v() {
		return (String) this.getAttributeValue(JzfsHVO.PK_DEPT_V);
	}

	/**
	 * ���ò��Ű汾
	 * 
	 * @param pk_dept_v
	 *            ���Ű汾
	 */
	public void setPk_dept_v(String pk_dept_v) {
		this.setAttributeValue(JzfsHVO.PK_DEPT_V, pk_dept_v);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_group() {
		return (String) this.getAttributeValue(JzfsHVO.PK_GROUP);
	}

	/**
	 * ���ü���
	 * 
	 * @param pk_group
	 *            ����
	 */
	public void setPk_group(String pk_group) {
		this.setAttributeValue(JzfsHVO.PK_GROUP, pk_group);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_hk_srgk_hg_jzfs() {
		return (String) this.getAttributeValue(JzfsHVO.PK_HK_SRGK_HG_JZFS);
	}

	/**
	 * ��������
	 * 
	 * @param pk_hk_srgk_hg_jzfs
	 *            ����
	 */
	public void setPk_hk_srgk_hg_jzfs(String pk_hk_srgk_hg_jzfs) {
		this.setAttributeValue(JzfsHVO.PK_HK_SRGK_HG_JZFS, pk_hk_srgk_hg_jzfs);
	}

	/**
	 * ��ȡ��ƿ�Ŀ
	 * 
	 * @return ��ƿ�Ŀ
	 */
	public String getPk_kjkm() {
		return (String) this.getAttributeValue(JzfsHVO.PK_KJKM);
	}

	/**
	 * ���û�ƿ�Ŀ
	 * 
	 * @param pk_kjkm
	 *            ��ƿ�Ŀ
	 */
	public void setPk_kjkm(String pk_kjkm) {
		this.setAttributeValue(JzfsHVO.PK_KJKM, pk_kjkm);
	}

	/**
	 * ��ȡ��֯
	 * 
	 * @return ��֯
	 */
	public String getPk_org() {
		return (String) this.getAttributeValue(JzfsHVO.PK_ORG);
	}

	/**
	 * ������֯
	 * 
	 * @param pk_org
	 *            ��֯
	 */
	public void setPk_org(String pk_org) {
		this.setAttributeValue(JzfsHVO.PK_ORG, pk_org);
	}

	/**
	 * ��ȡ��֯�汾
	 * 
	 * @return ��֯�汾
	 */
	public String getPk_org_v() {
		return (String) this.getAttributeValue(JzfsHVO.PK_ORG_V);
	}

	/**
	 * ������֯�汾
	 * 
	 * @param pk_org_v
	 *            ��֯�汾
	 */
	public void setPk_org_v(String pk_org_v) {
		this.setAttributeValue(JzfsHVO.PK_ORG_V, pk_org_v);
	}

	/**
	 * ��ȡ�ϼ����˷�ʽ
	 * 
	 * @return �ϼ����˷�ʽ
	 */
	public String getPk_parent() {
		return (String) this.getAttributeValue(JzfsHVO.PK_PARENT);
	}

	/**
	 * �����ϼ����˷�ʽ
	 * 
	 * @param pk_parent
	 *            �ϼ����˷�ʽ
	 */
	public void setPk_parent(String pk_parent) {
		this.setAttributeValue(JzfsHVO.PK_PARENT, pk_parent);
	}

	/**
	 * ��ȡ��ʾ˳��
	 * 
	 * @return ��ʾ˳��
	 */
	public Integer getShoworder() {
		return (Integer) this.getAttributeValue(JzfsHVO.SHOWORDER);
	}

	/**
	 * ������ʾ˳��
	 * 
	 * @param showorder
	 *            ��ʾ˳��
	 */
	public void setShoworder(Integer showorder) {
		this.setAttributeValue(JzfsHVO.SHOWORDER, showorder);
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(JzfsHVO.TS);
	}

	/**
	 * ����ʱ���
	 * 
	 * @param ts
	 *            ʱ���
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(JzfsHVO.TS, ts);
	}

	/**
	 * ��ȡ�Զ�����1
	 * 
	 * @return �Զ�����1
	 */
	public String getVdef1() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF1);
	}

	/**
	 * �����Զ�����1
	 * 
	 * @param vdef1
	 *            �Զ�����1
	 */
	public void setVdef1(String vdef1) {
		this.setAttributeValue(JzfsHVO.VDEF1, vdef1);
	}

	/**
	 * ��ȡ�Զ�����2
	 * 
	 * @return �Զ�����2
	 */
	public String getVdef2() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF2);
	}

	/**
	 * �����Զ�����2
	 * 
	 * @param vdef2
	 *            �Զ�����2
	 */
	public void setVdef2(String vdef2) {
		this.setAttributeValue(JzfsHVO.VDEF2, vdef2);
	}

	/**
	 * ��ȡ�Զ�����3
	 * 
	 * @return �Զ�����3
	 */
	public String getVdef3() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF3);
	}

	/**
	 * �����Զ�����3
	 * 
	 * @param vdef3
	 *            �Զ�����3
	 */
	public void setVdef3(String vdef3) {
		this.setAttributeValue(JzfsHVO.VDEF3, vdef3);
	}

	/**
	 * ��ȡ�Զ�����4
	 * 
	 * @return �Զ�����4
	 */
	public String getVdef4() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF4);
	}

	/**
	 * �����Զ�����4
	 * 
	 * @param vdef4
	 *            �Զ�����4
	 */
	public void setVdef4(String vdef4) {
		this.setAttributeValue(JzfsHVO.VDEF4, vdef4);
	}

	/**
	 * ��ȡ�Զ�����5
	 * 
	 * @return �Զ�����5
	 */
	public String getVdef5() {
		return (String) this.getAttributeValue(JzfsHVO.VDEF5);
	}

	/**
	 * �����Զ�����5
	 * 
	 * @param vdef5
	 *            �Զ�����5
	 */
	public void setVdef5(String vdef5) {
		this.setAttributeValue(JzfsHVO.VDEF5, vdef5);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.jzfsh");
	}
}