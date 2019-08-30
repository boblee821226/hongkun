package nc.vo.hkjt.srgk.huiguan.yyribao;

import java.util.HashMap;
import java.util.Map;

import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoHVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class YyribaoHVO extends SuperVO {
	// ����������Ӧ���Զ����ֶ���Ϣ
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
	 * �����
	 */
	public static final String APPROVER = "approver";
	/**
	 * �Ƶ�ʱ��
	 */
	public static final String CREATIONTIME = "creationtime";
	/**
	 * �Ƶ���
	 */
	public static final String CREATOR = "creator";
	/**
	 * ��������
	 */
	public static final String DBILLDATE = "dbilldate";
	/**
	 * ����״̬
	 */
	public static final String IBILLSTATUS = "ibillstatus";
	/**
	 * �޸�ʱ��
	 */
	public static final String MODIFIEDTIME = "modifiedtime";
	/**
	 * �޸���
	 */
	public static final String MODIFIER = "modifier";
	/**
	 * ҵ������
	 */
	public static final String PK_BUSITYPE = "pk_busitype";
	/**
	 * ����id
	 */
	public static final String PK_DEPT = "pk_dept";
	/**
	 * ����
	 */
	public static final String PK_GROUP = "pk_group";
	/**
	 * ��������
	 */
	public static final String PK_HK_SRGK_HG_YYRIBAO = "pk_hk_srgk_hg_yyribao";
	/**
	 * ��֯
	 */
	public static final String PK_ORG = "pk_org";
	/**
	 * ��֯v
	 */
	public static final String PK_ORG_V = "pk_org_v";
	/**
	 * ���ʱ��
	 */
	public static final String TAUDITTIME = "taudittime";
	/**
	 * ʱ���
	 */
	public static final String TS = "ts";
	/**
	 * ��������
	 */
	public static final String VAPPROVENOTE = "vapprovenote";
	/**
	 * ���ݱ��
	 */
	public static final String VBILLCODE = "vbillcode";
	/**
	 * ��������
	 */
	public static final String VBILLTYPECODE = "vbilltypecode";
	/**
	 * �Զ�����01
	 */
	public static final String VDEF01 = "vdef01";
	/**
	 * �Զ�����02
	 */
	public static final String VDEF02 = "vdef02";
	/**
	 * �Զ�����03
	 */
	public static final String VDEF03 = "vdef03";
	/**
	 * �Զ�����04
	 */
	public static final String VDEF04 = "vdef04";
	/**
	 * �Զ�����05
	 */
	public static final String VDEF05 = "vdef05";
	/**
	 * �Զ�����06
	 */
	public static final String VDEF06 = "vdef06";
	/**
	 * �Զ�����07
	 */
	public static final String VDEF07 = "vdef07";
	/**
	 * �Զ�����08
	 */
	public static final String VDEF08 = "vdef08";
	/**
	 * �Զ�����09
	 */
	public static final String VDEF09 = "vdef09";
	/**
	 * �Զ�����10
	 */
	public static final String VDEF10 = "vdef10";
	/**
	 * ��ע
	 */
	public static final String VMEMO = "vmemo";
	/**
	 * ��������
	 */
	public static final String VTRANTYPECODE = "vtrantypecode";
	
	/**
	 * ��ȡ�����
	 * 
	 * @return �����
	 */
	public String getApprover() {
		return (String) this.getAttributeValue(YyribaoHVO.APPROVER);
	}

	/**
	 * ���������
	 * 
	 * @param approver
	 *            �����
	 */
	public void setApprover(String approver) {
		this.setAttributeValue(YyribaoHVO.APPROVER, approver);
	}

	/**
	 * ��ȡ�Ƶ�ʱ��
	 * 
	 * @return �Ƶ�ʱ��
	 */
	public UFDateTime getCreationtime() {
		return (UFDateTime) this.getAttributeValue(YyribaoHVO.CREATIONTIME);
	}

	/**
	 * �����Ƶ�ʱ��
	 * 
	 * @param creationtime
	 *            �Ƶ�ʱ��
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.setAttributeValue(YyribaoHVO.CREATIONTIME, creationtime);
	}

	/**
	 * ��ȡ�Ƶ���
	 * 
	 * @return �Ƶ���
	 */
	public String getCreator() {
		return (String) this.getAttributeValue(YyribaoHVO.CREATOR);
	}

	/**
	 * �����Ƶ���
	 * 
	 * @param creator
	 *            �Ƶ���
	 */
	public void setCreator(String creator) {
		this.setAttributeValue(YyribaoHVO.CREATOR, creator);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public UFDate getDbilldate() {
		return (UFDate) this.getAttributeValue(YyribaoHVO.DBILLDATE);
	}

	/**
	 * ���õ�������
	 * 
	 * @param dbilldate
	 *            ��������
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.setAttributeValue(YyribaoHVO.DBILLDATE, dbilldate);
	}

	/**
	 * ��ȡ����״̬
	 * 
	 * @return ����״̬
	 * @see String
	 */
	public Integer getIbillstatus() {
		return (Integer) this.getAttributeValue(YyribaoHVO.IBILLSTATUS);
	}

	/**
	 * ���õ���״̬
	 * 
	 * @param ibillstatus
	 *            ����״̬
	 * @see String
	 */
	public void setIbillstatus(Integer ibillstatus) {
		this.setAttributeValue(YyribaoHVO.IBILLSTATUS, ibillstatus);
	}

	/**
	 * ��ȡ�޸�ʱ��
	 * 
	 * @return �޸�ʱ��
	 */
	public UFDateTime getModifiedtime() {
		return (UFDateTime) this.getAttributeValue(YyribaoHVO.MODIFIEDTIME);
	}

	/**
	 * �����޸�ʱ��
	 * 
	 * @param modifiedtime
	 *            �޸�ʱ��
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.setAttributeValue(YyribaoHVO.MODIFIEDTIME, modifiedtime);
	}

	/**
	 * ��ȡ�޸���
	 * 
	 * @return �޸���
	 */
	public String getModifier() {
		return (String) this.getAttributeValue(YyribaoHVO.MODIFIER);
	}

	/**
	 * �����޸���
	 * 
	 * @param modifier
	 *            �޸���
	 */
	public void setModifier(String modifier) {
		this.setAttributeValue(YyribaoHVO.MODIFIER, modifier);
	}

	/**
	 * ��ȡҵ������
	 * 
	 * @return ҵ������
	 */
	public String getPk_busitype() {
		return (String) this.getAttributeValue(YyribaoHVO.PK_BUSITYPE);
	}

	/**
	 * ����ҵ������
	 * 
	 * @param pk_busitype
	 *            ҵ������
	 */
	public void setPk_busitype(String pk_busitype) {
		this.setAttributeValue(YyribaoHVO.PK_BUSITYPE, pk_busitype);
	}

	/**
	 * ��ȡ����id
	 * 
	 * @return ����id
	 */
	public String getPk_dept() {
		return (String) this.getAttributeValue(YyribaoHVO.PK_DEPT);
	}

	/**
	 * ���ò���id
	 * 
	 * @param pk_dept
	 *            ����id
	 */
	public void setPk_dept(String pk_dept) {
		this.setAttributeValue(YyribaoHVO.PK_DEPT, pk_dept);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_group() {
		return (String) this.getAttributeValue(YyribaoHVO.PK_GROUP);
	}

	/**
	 * ���ü���
	 * 
	 * @param pk_group
	 *            ����
	 */
	public void setPk_group(String pk_group) {
		this.setAttributeValue(YyribaoHVO.PK_GROUP, pk_group);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getPk_hk_srgk_hg_yyribao() {
		return (String) this
				.getAttributeValue(YyribaoHVO.PK_HK_SRGK_HG_YYRIBAO);
	}

	/**
	 * ������������
	 * 
	 * @param pk_hk_srgk_hg_yyribao
	 *            ��������
	 */
	public void setPk_hk_srgk_hg_yyribao(String pk_hk_srgk_hg_yyribao) {
		this.setAttributeValue(YyribaoHVO.PK_HK_SRGK_HG_YYRIBAO,
				pk_hk_srgk_hg_yyribao);
	}

	/**
	 * ��ȡ��֯
	 * 
	 * @return ��֯
	 */
	public String getPk_org() {
		return (String) this.getAttributeValue(YyribaoHVO.PK_ORG);
	}

	/**
	 * ������֯
	 * 
	 * @param pk_org
	 *            ��֯
	 */
	public void setPk_org(String pk_org) {
		this.setAttributeValue(YyribaoHVO.PK_ORG, pk_org);
	}

	/**
	 * ��ȡ��֯v
	 * 
	 * @return ��֯v
	 */
	public String getPk_org_v() {
		return (String) this.getAttributeValue(YyribaoHVO.PK_ORG_V);
	}

	/**
	 * ������֯v
	 * 
	 * @param pk_org_v
	 *            ��֯v
	 */
	public void setPk_org_v(String pk_org_v) {
		this.setAttributeValue(YyribaoHVO.PK_ORG_V, pk_org_v);
	}

	/**
	 * ��ȡ���ʱ��
	 * 
	 * @return ���ʱ��
	 */
	public UFDateTime getTaudittime() {
		return (UFDateTime) this.getAttributeValue(YyribaoHVO.TAUDITTIME);
	}

	/**
	 * �������ʱ��
	 * 
	 * @param taudittime
	 *            ���ʱ��
	 */
	public void setTaudittime(UFDateTime taudittime) {
		this.setAttributeValue(YyribaoHVO.TAUDITTIME, taudittime);
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(YyribaoHVO.TS);
	}

	/**
	 * ����ʱ���
	 * 
	 * @param ts
	 *            ʱ���
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(YyribaoHVO.TS, ts);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getVapprovenote() {
		return (String) this.getAttributeValue(YyribaoHVO.VAPPROVENOTE);
	}

	/**
	 * ������������
	 * 
	 * @param vapprovenote
	 *            ��������
	 */
	public void setVapprovenote(String vapprovenote) {
		this.setAttributeValue(YyribaoHVO.VAPPROVENOTE, vapprovenote);
	}

	/**
	 * ��ȡ���ݱ��
	 * 
	 * @return ���ݱ��
	 */
	public String getVbillcode() {
		return (String) this.getAttributeValue(YyribaoHVO.VBILLCODE);
	}

	/**
	 * ���õ��ݱ��
	 * 
	 * @param vbillcode
	 *            ���ݱ��
	 */
	public void setVbillcode(String vbillcode) {
		this.setAttributeValue(YyribaoHVO.VBILLCODE, vbillcode);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getVbilltypecode() {
		return (String) this.getAttributeValue(YyribaoHVO.VBILLTYPECODE);
	}

	/**
	 * ���õ�������
	 * 
	 * @param vbilltypecode
	 *            ��������
	 */
	public void setVbilltypecode(String vbilltypecode) {
		this.setAttributeValue(YyribaoHVO.VBILLTYPECODE, vbilltypecode);
	}

	/**
	 * ��ȡ�Զ�����01
	 * 
	 * @return �Զ�����01
	 */
	public String getVdef01() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF01);
	}

	/**
	 * �����Զ�����01
	 * 
	 * @param vdef01
	 *            �Զ�����01
	 */
	public void setVdef01(String vdef01) {
		this.setAttributeValue(YyribaoHVO.VDEF01, vdef01);
	}

	/**
	 * ��ȡ�Զ�����02
	 * 
	 * @return �Զ�����02
	 */
	public String getVdef02() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF02);
	}

	/**
	 * �����Զ�����02
	 * 
	 * @param vdef02
	 *            �Զ�����02
	 */
	public void setVdef02(String vdef02) {
		this.setAttributeValue(YyribaoHVO.VDEF02, vdef02);
	}

	/**
	 * ��ȡ�Զ�����03
	 * 
	 * @return �Զ�����03
	 */
	public String getVdef03() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF03);
	}

	/**
	 * �����Զ�����03
	 * 
	 * @param vdef03
	 *            �Զ�����03
	 */
	public void setVdef03(String vdef03) {
		this.setAttributeValue(YyribaoHVO.VDEF03, vdef03);
	}

	/**
	 * ��ȡ�Զ�����04
	 * 
	 * @return �Զ�����04
	 */
	public String getVdef04() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF04);
	}

	/**
	 * �����Զ�����04
	 * 
	 * @param vdef04
	 *            �Զ�����04
	 */
	public void setVdef04(String vdef04) {
		this.setAttributeValue(YyribaoHVO.VDEF04, vdef04);
	}

	/**
	 * ��ȡ�Զ�����05
	 * 
	 * @return �Զ�����05
	 */
	public String getVdef05() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF05);
	}

	/**
	 * �����Զ�����05
	 * 
	 * @param vdef05
	 *            �Զ�����05
	 */
	public void setVdef05(String vdef05) {
		this.setAttributeValue(YyribaoHVO.VDEF05, vdef05);
	}

	/**
	 * ��ȡ�Զ�����06
	 * 
	 * @return �Զ�����06
	 */
	public String getVdef06() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF06);
	}

	/**
	 * �����Զ�����06
	 * 
	 * @param vdef06
	 *            �Զ�����06
	 */
	public void setVdef06(String vdef06) {
		this.setAttributeValue(YyribaoHVO.VDEF06, vdef06);
	}

	/**
	 * ��ȡ�Զ�����07
	 * 
	 * @return �Զ�����07
	 */
	public String getVdef07() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF07);
	}

	/**
	 * �����Զ�����07
	 * 
	 * @param vdef07
	 *            �Զ�����07
	 */
	public void setVdef07(String vdef07) {
		this.setAttributeValue(YyribaoHVO.VDEF07, vdef07);
	}

	/**
	 * ��ȡ�Զ�����08
	 * 
	 * @return �Զ�����08
	 */
	public String getVdef08() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF08);
	}

	/**
	 * �����Զ�����08
	 * 
	 * @param vdef08
	 *            �Զ�����08
	 */
	public void setVdef08(String vdef08) {
		this.setAttributeValue(YyribaoHVO.VDEF08, vdef08);
	}

	/**
	 * ��ȡ�Զ�����09
	 * 
	 * @return �Զ�����09
	 */
	public String getVdef09() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF09);
	}

	/**
	 * �����Զ�����09
	 * 
	 * @param vdef09
	 *            �Զ�����09
	 */
	public void setVdef09(String vdef09) {
		this.setAttributeValue(YyribaoHVO.VDEF09, vdef09);
	}

	/**
	 * ��ȡ�Զ�����10
	 * 
	 * @return �Զ�����10
	 */
	public String getVdef10() {
		return (String) this.getAttributeValue(YyribaoHVO.VDEF10);
	}

	/**
	 * �����Զ�����10
	 * 
	 * @param vdef10
	 *            �Զ�����10
	 */
	public void setVdef10(String vdef10) {
		this.setAttributeValue(YyribaoHVO.VDEF10, vdef10);
	}

	/**
	 * ��ȡ��ע
	 * 
	 * @return ��ע
	 */
	public String getVmemo() {
		return (String) this.getAttributeValue(YyribaoHVO.VMEMO);
	}

	/**
	 * ���ñ�ע
	 * 
	 * @param vmemo
	 *            ��ע
	 */
	public void setVmemo(String vmemo) {
		this.setAttributeValue(YyribaoHVO.VMEMO, vmemo);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getVtrantypecode() {
		return (String) this.getAttributeValue(YyribaoHVO.VTRANTYPECODE);
	}

	/**
	 * ���ý�������
	 * 
	 * @param vtrantypecode
	 *            ��������
	 */
	public void setVtrantypecode(String vtrantypecode) {
		this.setAttributeValue(YyribaoHVO.VTRANTYPECODE, vtrantypecode);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_YyribaoHVO");
	}
	
	public static final String ISCCTZ = "iscctz";
	/** 
	* @return �Ƿ������
	*/
	public UFBoolean getIscctz () {
	return (UFBoolean) this.getAttributeValue( YyribaoHVO.ISCCTZ);
	 } 

	/** 
	*  �����Ƿ������
	*/
	public void setIscctz ( UFBoolean iscctz) {
	this.setAttributeValue( YyribaoHVO.ISCCTZ,iscctz);
	 } 
	
}