package nc.vo.hkjt.srgk.huiguan.sgshuju;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SgshujuHVO extends SuperVO {
/**
*�����
*/
public static final String APPROVER="approver";
/**
*�Ƶ�ʱ��
*/
public static final String CREATIONTIME="creationtime";
/**
*�Ƶ���
*/
public static final String CREATOR="creator";
/**
*��������
*/
public static final String DBILLDATE="dbilldate";
/**
*����״̬
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*�޸�ʱ��
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*�޸���
*/
public static final String MODIFIER="modifier";
/**
*ҵ������
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*����
*/
public static final String PK_GROUP="pk_group";
/**
*��������
*/
public static final String PK_HK_SRGK_HG_SGSHUJU="pk_hk_srgk_hg_sgshuju";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*���ʱ��
*/
public static final String TAUDITTIME="taudittime";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*��������
*/
public static final String VAPPROVENOTE="vapprovenote";
/**
*���ݱ��
*/
public static final String VBILLCODE="vbillcode";
/**
*��������
*/
public static final String VBILLTYPECODE="vbilltypecode";
/**
*�Զ�����01
*/
public static final String VDEF01="vdef01";
/**
*�Զ�����02
*/
public static final String VDEF02="vdef02";
/**
*�Զ�����03
*/
public static final String VDEF03="vdef03";
/**
*�Զ�����04
*/
public static final String VDEF04="vdef04";
/**
*�Զ�����05
*/
public static final String VDEF05="vdef05";
/**
*�Զ�����06
*/
public static final String VDEF06="vdef06";
/**
*�Զ�����07
*/
public static final String VDEF07="vdef07";
/**
*�Զ�����08
*/
public static final String VDEF08="vdef08";
/**
*�Զ�����09
*/
public static final String VDEF09="vdef09";
/**
*�Զ�����10
*/
public static final String VDEF10="vdef10";
/**
*��ע
*/
public static final String VMEMO="vmemo";
/**
*��������
*/
public static final String VTRANTYPECODE="vtrantypecode";
/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return (String) this.getAttributeValue( SgshujuHVO.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( SgshujuHVO.APPROVER,approver);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( SgshujuHVO.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( SgshujuHVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( SgshujuHVO.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( SgshujuHVO.CREATOR,creator);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( SgshujuHVO.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( SgshujuHVO.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( SgshujuHVO.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( SgshujuHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( SgshujuHVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( SgshujuHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( SgshujuHVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( SgshujuHVO.MODIFIER,modifier);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( SgshujuHVO.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( SgshujuHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( SgshujuHVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( SgshujuHVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_hk_srgk_hg_sgshuju () {
return (String) this.getAttributeValue( SgshujuHVO.PK_HK_SRGK_HG_SGSHUJU);
 } 

/** 
* ������������
*
* @param pk_hk_srgk_hg_sgshuju ��������
*/
public void setPk_hk_srgk_hg_sgshuju ( String pk_hk_srgk_hg_sgshuju) {
this.setAttributeValue( SgshujuHVO.PK_HK_SRGK_HG_SGSHUJU,pk_hk_srgk_hg_sgshuju);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( SgshujuHVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( SgshujuHVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( SgshujuHVO.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( SgshujuHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( SgshujuHVO.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( SgshujuHVO.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( SgshujuHVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( SgshujuHVO.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( SgshujuHVO.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( SgshujuHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( SgshujuHVO.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( SgshujuHVO.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( SgshujuHVO.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( SgshujuHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( SgshujuHVO.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( SgshujuHVO.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( SgshujuHVO.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( SgshujuHVO.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( SgshujuHVO.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( SgshujuHVO.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( SgshujuHVO.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( SgshujuHVO.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( SgshujuHVO.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( SgshujuHVO.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( SgshujuHVO.VDEF10,vdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( SgshujuHVO.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( SgshujuHVO.VMEMO,vmemo);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( SgshujuHVO.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( SgshujuHVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_SgshujuHVO");
  }
}