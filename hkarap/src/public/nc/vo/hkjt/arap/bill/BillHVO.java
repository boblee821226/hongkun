package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillHVO extends SuperVO {
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
*Ӧ������pk
*/
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*��������pk
*/
public static final String PK_TRANTYPE="pk_trantype";
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
*�Զ�����11
*/
public static final String VDEF11="vdef11";
/**
*�Զ�����12
*/
public static final String VDEF12="vdef12";
/**
*�Զ�����13
*/
public static final String VDEF13="vdef13";
/**
*�Զ�����14
*/
public static final String VDEF14="vdef14";
/**
*�Զ�����15
*/
public static final String VDEF15="vdef15";
/**
*�Զ�����16
*/
public static final String VDEF16="vdef16";
/**
*�Զ�����17
*/
public static final String VDEF17="vdef17";
/**
*�Զ�����18
*/
public static final String VDEF18="vdef18";
/**
*�Զ�����19
*/
public static final String VDEF19="vdef19";
/**
*�Զ�����20
*/
public static final String VDEF20="vdef20";
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
return (String) this.getAttributeValue( BillHVO.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( BillHVO.APPROVER,approver);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( BillHVO.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( BillHVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( BillHVO.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( BillHVO.CREATOR,creator);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( BillHVO.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( BillHVO.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( BillHVO.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( BillHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( BillHVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( BillHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( BillHVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( BillHVO.MODIFIER,modifier);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( BillHVO.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( BillHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( BillHVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( BillHVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡӦ������pk
*
* @return Ӧ������pk
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillHVO.PK_HK_ARAP_BILL);
 } 

/** 
* ����Ӧ������pk
*
* @param pk_hk_arap_bill Ӧ������pk
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillHVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( BillHVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( BillHVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( BillHVO.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( BillHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡ��������pk
*
* @return ��������pk
*/
public String getPk_trantype () {
return (String) this.getAttributeValue( BillHVO.PK_TRANTYPE);
 } 

/** 
* ���ý�������pk
*
* @param pk_trantype ��������pk
*/
public void setPk_trantype ( String pk_trantype) {
this.setAttributeValue( BillHVO.PK_TRANTYPE,pk_trantype);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( BillHVO.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( BillHVO.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillHVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillHVO.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( BillHVO.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( BillHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( BillHVO.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( BillHVO.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( BillHVO.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( BillHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( BillHVO.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( BillHVO.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( BillHVO.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( BillHVO.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( BillHVO.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( BillHVO.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( BillHVO.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( BillHVO.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( BillHVO.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( BillHVO.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( BillHVO.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( BillHVO.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( BillHVO.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( BillHVO.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( BillHVO.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( BillHVO.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( BillHVO.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( BillHVO.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( BillHVO.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( BillHVO.VDEF10,vdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVdef11 () {
return (String) this.getAttributeValue( BillHVO.VDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vdef11 �Զ�����11
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( BillHVO.VDEF11,vdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVdef12 () {
return (String) this.getAttributeValue( BillHVO.VDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vdef12 �Զ�����12
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( BillHVO.VDEF12,vdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVdef13 () {
return (String) this.getAttributeValue( BillHVO.VDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vdef13 �Զ�����13
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( BillHVO.VDEF13,vdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVdef14 () {
return (String) this.getAttributeValue( BillHVO.VDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vdef14 �Զ�����14
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( BillHVO.VDEF14,vdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVdef15 () {
return (String) this.getAttributeValue( BillHVO.VDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vdef15 �Զ�����15
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( BillHVO.VDEF15,vdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVdef16 () {
return (String) this.getAttributeValue( BillHVO.VDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vdef16 �Զ�����16
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( BillHVO.VDEF16,vdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVdef17 () {
return (String) this.getAttributeValue( BillHVO.VDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vdef17 �Զ�����17
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( BillHVO.VDEF17,vdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVdef18 () {
return (String) this.getAttributeValue( BillHVO.VDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vdef18 �Զ�����18
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( BillHVO.VDEF18,vdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVdef19 () {
return (String) this.getAttributeValue( BillHVO.VDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vdef19 �Զ�����19
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( BillHVO.VDEF19,vdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVdef20 () {
return (String) this.getAttributeValue( BillHVO.VDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vdef20 �Զ�����20
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( BillHVO.VDEF20,vdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( BillHVO.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( BillHVO.VMEMO,vmemo);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( BillHVO.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( BillHVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billHVO");
  }
}