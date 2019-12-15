package nc.vo.hkjt.srgk.huiguan.srxm;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SrxmHVO extends SuperVO {
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
	public static final String PK_HK_SRGK_HG_SRXM = "pk_hk_srgk_hg_srxm";
	/**
	 * ��֯
	 */
	public static final String PK_ORG = "pk_org";
	/**
	 * ��֯�汾
	 */
	public static final String PK_ORG_V = "pk_org_v";
	/**
	 * �ϼ�������Ŀ
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
	/**
	 * �㼶���
	 */
	public static final String LEVELNO = "levelno";

	public Integer getLevelno() {
		return (Integer) this.getAttributeValue(SrxmHVO.LEVELNO);
	}

	public void setLevelno(Integer levelno) {
		this.setAttributeValue(SrxmHVO.LEVELNO, levelno);
	}

	/**
	 * ��ƿ�Ŀ
	 */
	public static final String PK_KJKM = "pk_kjkm";

	public String getPk_kjkm() {
		return (String) this.getAttributeValue(SrxmHVO.PK_KJKM);
	}

	public void setPk_kjkm(String pk_kjkm) {
		this.setAttributeValue(SrxmHVO.PK_KJKM, pk_kjkm);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getCode() {
		return (String) this.getAttributeValue(SrxmHVO.CODE);
	}

	/**
	 * ���ñ���
	 * 
	 * @param code
	 *            ����
	 */
	public void setCode(String code) {
		this.setAttributeValue(SrxmHVO.CODE, code);
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ��
	 */
	public UFDateTime getCreationtime() {
		return (UFDateTime) this.getAttributeValue(SrxmHVO.CREATIONTIME);
	}

	/**
	 * ���ô���ʱ��
	 * 
	 * @param creationtime
	 *            ����ʱ��
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.setAttributeValue(SrxmHVO.CREATIONTIME, creationtime);
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public String getCreator() {
		return (String) this.getAttributeValue(SrxmHVO.CREATOR);
	}

	/**
	 * ���ô�����
	 * 
	 * @param creator
	 *            ������
	 */
	public void setCreator(String creator) {
		this.setAttributeValue(SrxmHVO.CREATOR, creator);
	}

	/**
	 * ��ȡ�޸�ʱ��
	 * 
	 * @return �޸�ʱ��
	 */
	public UFDateTime getModifiedtime() {
		return (UFDateTime) this.getAttributeValue(SrxmHVO.MODIFIEDTIME);
	}

	/**
	 * �����޸�ʱ��
	 * 
	 * @param modifiedtime
	 *            �޸�ʱ��
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.setAttributeValue(SrxmHVO.MODIFIEDTIME, modifiedtime);
	}

	/**
	 * ��ȡ�޸���
	 * 
	 * @return �޸���
	 */
	public String getModifier() {
		return (String) this.getAttributeValue(SrxmHVO.MODIFIER);
	}

	/**
	 * �����޸���
	 * 
	 * @param modifier
	 *            �޸���
	 */
	public void setModifier(String modifier) {
		this.setAttributeValue(SrxmHVO.MODIFIER, modifier);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getName() {
		return (String) this.getAttributeValue(SrxmHVO.NAME);
	}

	/**
	 * ��������
	 * 
	 * @param name
	 *            ����
	 */
	public void setName(String name) {
		this.setAttributeValue(SrxmHVO.NAME, name);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_dept() {
		return (String) this.getAttributeValue(SrxmHVO.PK_DEPT);
	}

	/**
	 * ���ò���
	 * 
	 * @param pk_dept
	 *            ����
	 */
	public void setPk_dept(String pk_dept) {
		this.setAttributeValue(SrxmHVO.PK_DEPT, pk_dept);
	}

	/**
	 * ��ȡ���Ű汾
	 * 
	 * @return ���Ű汾
	 */
	public String getPk_dept_v() {
		return (String) this.getAttributeValue(SrxmHVO.PK_DEPT_V);
	}

	/**
	 * ���ò��Ű汾
	 * 
	 * @param pk_dept_v
	 *            ���Ű汾
	 */
	public void setPk_dept_v(String pk_dept_v) {
		this.setAttributeValue(SrxmHVO.PK_DEPT_V, pk_dept_v);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_group() {
		return (String) this.getAttributeValue(SrxmHVO.PK_GROUP);
	}

	/**
	 * ���ü���
	 * 
	 * @param pk_group
	 *            ����
	 */
	public void setPk_group(String pk_group) {
		this.setAttributeValue(SrxmHVO.PK_GROUP, pk_group);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_hk_srgk_hg_srxm() {
		return (String) this.getAttributeValue(SrxmHVO.PK_HK_SRGK_HG_SRXM);
	}

	/**
	 * ��������
	 * 
	 * @param pk_hk_srgk_hg_xrxm
	 *            ����
	 */
	public void setPk_hk_srgk_hg_srxm(String pk_hk_srgk_hg_srxm) {
		this.setAttributeValue(SrxmHVO.PK_HK_SRGK_HG_SRXM, pk_hk_srgk_hg_srxm);
	}

	/**
	 * ��ȡ��֯
	 * 
	 * @return ��֯
	 */
	public String getPk_org() {
		return (String) this.getAttributeValue(SrxmHVO.PK_ORG);
	}

	/**
	 * ������֯
	 * 
	 * @param pk_org
	 *            ��֯
	 */
	public void setPk_org(String pk_org) {
		this.setAttributeValue(SrxmHVO.PK_ORG, pk_org);
	}

	/**
	 * ��ȡ��֯�汾
	 * 
	 * @return ��֯�汾
	 */
	public String getPk_org_v() {
		return (String) this.getAttributeValue(SrxmHVO.PK_ORG_V);
	}

	/**
	 * ������֯�汾
	 * 
	 * @param pk_org_v
	 *            ��֯�汾
	 */
	public void setPk_org_v(String pk_org_v) {
		this.setAttributeValue(SrxmHVO.PK_ORG_V, pk_org_v);
	}

	/**
	 * ��ȡ�ϼ�������Ŀ
	 * 
	 * @return �ϼ�������Ŀ
	 */
	public String getPk_parent() {
		return (String) this.getAttributeValue(SrxmHVO.PK_PARENT);
	}

	/**
	 * �����ϼ�������Ŀ
	 * 
	 * @param pk_parent
	 *            �ϼ�������Ŀ
	 */
	public void setPk_parent(String pk_parent) {
		this.setAttributeValue(SrxmHVO.PK_PARENT, pk_parent);
	}

	/**
	 * ��ȡ��ʾ˳��
	 * 
	 * @return ��ʾ˳��
	 */
	public Integer getShoworder() {
		return (Integer) this.getAttributeValue(SrxmHVO.SHOWORDER);
	}

	/**
	 * ������ʾ˳��
	 * 
	 * @param showorder
	 *            ��ʾ˳��
	 */
	public void setShoworder(Integer showorder) {
		this.setAttributeValue(SrxmHVO.SHOWORDER, showorder);
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(SrxmHVO.TS);
	}

	/**
	 * ����ʱ���
	 * 
	 * @param ts
	 *            ʱ���
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(SrxmHVO.TS, ts);
	}

	/**
	 * ��ȡ�Զ�����1
	 * 
	 * @return �Զ�����1
	 */
	public String getVdef1() {
		return (String) this.getAttributeValue(SrxmHVO.VDEF1);
	}

	/**
	 * �����Զ�����1
	 * 
	 * @param vdef1
	 *            �Զ�����1
	 */
	public void setVdef1(String vdef1) {
		this.setAttributeValue(SrxmHVO.VDEF1, vdef1);
	}

	/**
	 * ��ȡ�Զ�����2
	 * 
	 * @return �Զ�����2
	 */
	public String getVdef2() {
		return (String) this.getAttributeValue(SrxmHVO.VDEF2);
	}

	/**
	 * �����Զ�����2
	 * 
	 * @param vdef2
	 *            �Զ�����2
	 */
	public void setVdef2(String vdef2) {
		this.setAttributeValue(SrxmHVO.VDEF2, vdef2);
	}

	/**
	 * ��ȡ�Զ�����3
	 * 
	 * @return �Զ�����3
	 */
	public String getVdef3() {
		return (String) this.getAttributeValue(SrxmHVO.VDEF3);
	}

	/**
	 * �����Զ�����3
	 * 
	 * @param vdef3
	 *            �Զ�����3
	 */
	public void setVdef3(String vdef3) {
		this.setAttributeValue(SrxmHVO.VDEF3, vdef3);
	}

	/**
	 * ��ȡ�Զ�����4
	 * 
	 * @return �Զ�����4
	 */
	public String getVdef4() {
		return (String) this.getAttributeValue(SrxmHVO.VDEF4);
	}

	/**
	 * �����Զ�����4
	 * 
	 * @param vdef4
	 *            �Զ�����4
	 */
	public void setVdef4(String vdef4) {
		this.setAttributeValue(SrxmHVO.VDEF4, vdef4);
	}

	/**
	 * ��ȡ�Զ�����5
	 * 
	 * @return �Զ�����5
	 */
	public String getVdef5() {
		return (String) this.getAttributeValue(SrxmHVO.VDEF5);
	}

	/**
	 * �����Զ�����5
	 * 
	 * @param vdef5
	 *            �Զ�����5
	 */
	public void setVdef5(String vdef5) {
		this.setAttributeValue(SrxmHVO.VDEF5, vdef5);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.srxmh");
	}
}