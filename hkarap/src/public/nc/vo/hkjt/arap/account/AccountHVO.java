package nc.vo.hkjt.arap.account;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class AccountHVO extends SuperVO {
/**
*�˻�����
*/
public static final String ACC_CODE="acc_code";
/**
*�˻�����
*/
public static final String ACC_NAME="acc_name";
/**
*�˻�״̬
*/
public static final String ACC_STATUS="acc_status";
/**
*�˻����
*/
public static final String ACC_TYPE="acc_type";
/**
*��˾��ַ
*/
public static final String ADDRESS="address";
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
*Email
*/
public static final String EMAIL="email";
/**
*��ͬ������
*/
public static final String HTLLR="htllr";
/**
*��ͬ��ϵ��ʽ
*/
public static final String HTLLR_PHONE="htllr_phone";
/**
*����״̬
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*���㷽ʽ
*/
public static final String JSFS="jsfs";
/**
*����������
*/
public static final String JSLLR="jsllr";
/**
*������ϵ��ʽ
*/
public static final String JSLLR_PHONE="jsllr_phone";
/**
*�޸�ʱ��
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*�޸���
*/
public static final String MODIFIER="modifier";
/**
*OTA�Ƶ�ID
*/
public static final String OTA_ID="ota_id";
/**
*ҵ������
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*�ͻ�pk
*/
public static final String PK_CUSTOMER="pk_customer";
/**
*����
*/
public static final String PK_GROUP="pk_group";
/**
*Ӧ���˻�pk
*/
public static final String PK_HK_ARAP_ACCOUNT="pk_hk_arap_account";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*��˾ǩ����A
*/
public static final String QDR01="qdr01";
/**
*��˾ǩ����B
*/
public static final String QDR02="qdr02";
/**
*��˾ǩ����C
*/
public static final String QDR03="qdr03";
/**
*��˾ǩ����D
*/
public static final String QDR04="qdr04";
/**
*��˾ǩ����E
*/
public static final String QDR05="qdr05";
/**
*��ݵ�ַ
*/
public static final String SEND="send";
/**
*ǩ������A
*/
public static final String SIGN01="sign01";
/**
*ǩ������B
*/
public static final String SIGN02="sign02";
/**
*ǩ������C
*/
public static final String SIGN03="sign03";
/**
*ǩ������D
*/
public static final String SIGN04="sign04";
/**
*ǩ������E
*/
public static final String SIGN05="sign05";
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
public static final String UNIT_FK="unit_fk";
/**
*ǩ������
*/
public static final String UNIT_QZ="unit_qz";
/**
*Э������
*/
public static final String UNIT_XY="unit_xy";
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
*����Ա
*/
public static final String XSY="xsy";
/**
*������ϵ��ʽ
*/
public static final String XSY_PHONE="xsy_phone";
/**
*Э����Ч��
*/
public static final String YXQ="yxq";
/** 
* ��ȡ�˻�����
*
* @return �˻�����
*/
public String getAcc_code () {
return (String) this.getAttributeValue( AccountHVO.ACC_CODE);
 } 

/** 
* �����˻�����
*
* @param acc_code �˻�����
*/
public void setAcc_code ( String acc_code) {
this.setAttributeValue( AccountHVO.ACC_CODE,acc_code);
 } 

/** 
* ��ȡ�˻�����
*
* @return �˻�����
*/
public String getAcc_name () {
return (String) this.getAttributeValue( AccountHVO.ACC_NAME);
 } 

/** 
* �����˻�����
*
* @param acc_name �˻�����
*/
public void setAcc_name ( String acc_name) {
this.setAttributeValue( AccountHVO.ACC_NAME,acc_name);
 } 

/** 
* ��ȡ�˻�״̬
*
* @return �˻�״̬
*/
public String getAcc_status () {
return (String) this.getAttributeValue( AccountHVO.ACC_STATUS);
 } 

/** 
* �����˻�״̬
*
* @param acc_status �˻�״̬
*/
public void setAcc_status ( String acc_status) {
this.setAttributeValue( AccountHVO.ACC_STATUS,acc_status);
 } 

/** 
* ��ȡ�˻����
*
* @return �˻����
*/
public String getAcc_type () {
return (String) this.getAttributeValue( AccountHVO.ACC_TYPE);
 } 

/** 
* �����˻����
*
* @param acc_type �˻����
*/
public void setAcc_type ( String acc_type) {
this.setAttributeValue( AccountHVO.ACC_TYPE,acc_type);
 } 

/** 
* ��ȡ��˾��ַ
*
* @return ��˾��ַ
*/
public String getAddress () {
return (String) this.getAttributeValue( AccountHVO.ADDRESS);
 } 

/** 
* ���ù�˾��ַ
*
* @param address ��˾��ַ
*/
public void setAddress ( String address) {
this.setAttributeValue( AccountHVO.ADDRESS,address);
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return (String) this.getAttributeValue( AccountHVO.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( AccountHVO.APPROVER,approver);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( AccountHVO.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( AccountHVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( AccountHVO.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( AccountHVO.CREATOR,creator);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( AccountHVO.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( AccountHVO.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡEmail
*
* @return Email
*/
public String getEmail () {
return (String) this.getAttributeValue( AccountHVO.EMAIL);
 } 

/** 
* ����Email
*
* @param email Email
*/
public void setEmail ( String email) {
this.setAttributeValue( AccountHVO.EMAIL,email);
 } 

/** 
* ��ȡ��ͬ������
*
* @return ��ͬ������
*/
public String getHtllr () {
return (String) this.getAttributeValue( AccountHVO.HTLLR);
 } 

/** 
* ���ú�ͬ������
*
* @param htllr ��ͬ������
*/
public void setHtllr ( String htllr) {
this.setAttributeValue( AccountHVO.HTLLR,htllr);
 } 

/** 
* ��ȡ��ͬ��ϵ��ʽ
*
* @return ��ͬ��ϵ��ʽ
*/
public String getHtllr_phone () {
return (String) this.getAttributeValue( AccountHVO.HTLLR_PHONE);
 } 

/** 
* ���ú�ͬ��ϵ��ʽ
*
* @param htllr_phone ��ͬ��ϵ��ʽ
*/
public void setHtllr_phone ( String htllr_phone) {
this.setAttributeValue( AccountHVO.HTLLR_PHONE,htllr_phone);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( AccountHVO.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( AccountHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ���㷽ʽ
*
* @return ���㷽ʽ
*/
public String getJsfs () {
return (String) this.getAttributeValue( AccountHVO.JSFS);
 } 

/** 
* ���ý��㷽ʽ
*
* @param jsfs ���㷽ʽ
*/
public void setJsfs ( String jsfs) {
this.setAttributeValue( AccountHVO.JSFS,jsfs);
 } 

/** 
* ��ȡ����������
*
* @return ����������
*/
public String getJsllr () {
return (String) this.getAttributeValue( AccountHVO.JSLLR);
 } 

/** 
* ���ý���������
*
* @param jsllr ����������
*/
public void setJsllr ( String jsllr) {
this.setAttributeValue( AccountHVO.JSLLR,jsllr);
 } 

/** 
* ��ȡ������ϵ��ʽ
*
* @return ������ϵ��ʽ
*/
public String getJsllr_phone () {
return (String) this.getAttributeValue( AccountHVO.JSLLR_PHONE);
 } 

/** 
* ���ý�����ϵ��ʽ
*
* @param jsllr_phone ������ϵ��ʽ
*/
public void setJsllr_phone ( String jsllr_phone) {
this.setAttributeValue( AccountHVO.JSLLR_PHONE,jsllr_phone);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( AccountHVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( AccountHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( AccountHVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( AccountHVO.MODIFIER,modifier);
 } 

/** 
* ��ȡOTA�Ƶ�ID
*
* @return OTA�Ƶ�ID
*/
public String getOta_id () {
return (String) this.getAttributeValue( AccountHVO.OTA_ID);
 } 

/** 
* ����OTA�Ƶ�ID
*
* @param ota_id OTA�Ƶ�ID
*/
public void setOta_id ( String ota_id) {
this.setAttributeValue( AccountHVO.OTA_ID,ota_id);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( AccountHVO.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( AccountHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ�ͻ�pk
*
* @return �ͻ�pk
*/
public String getPk_customer () {
return (String) this.getAttributeValue( AccountHVO.PK_CUSTOMER);
 } 

/** 
* ���ÿͻ�pk
*
* @param pk_customer �ͻ�pk
*/
public void setPk_customer ( String pk_customer) {
this.setAttributeValue( AccountHVO.PK_CUSTOMER,pk_customer);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( AccountHVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( AccountHVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡӦ���˻�pk
*
* @return Ӧ���˻�pk
*/
public String getPk_hk_arap_account () {
return (String) this.getAttributeValue( AccountHVO.PK_HK_ARAP_ACCOUNT);
 } 

/** 
* ����Ӧ���˻�pk
*
* @param pk_hk_arap_account Ӧ���˻�pk
*/
public void setPk_hk_arap_account ( String pk_hk_arap_account) {
this.setAttributeValue( AccountHVO.PK_HK_ARAP_ACCOUNT,pk_hk_arap_account);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( AccountHVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( AccountHVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( AccountHVO.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( AccountHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡ��˾ǩ����A
*
* @return ��˾ǩ����A
*/
public String getQdr01 () {
return (String) this.getAttributeValue( AccountHVO.QDR01);
 } 

/** 
* ���ù�˾ǩ����A
*
* @param qdr01 ��˾ǩ����A
*/
public void setQdr01 ( String qdr01) {
this.setAttributeValue( AccountHVO.QDR01,qdr01);
 } 

/** 
* ��ȡ��˾ǩ����B
*
* @return ��˾ǩ����B
*/
public String getQdr02 () {
return (String) this.getAttributeValue( AccountHVO.QDR02);
 } 

/** 
* ���ù�˾ǩ����B
*
* @param qdr02 ��˾ǩ����B
*/
public void setQdr02 ( String qdr02) {
this.setAttributeValue( AccountHVO.QDR02,qdr02);
 } 

/** 
* ��ȡ��˾ǩ����C
*
* @return ��˾ǩ����C
*/
public String getQdr03 () {
return (String) this.getAttributeValue( AccountHVO.QDR03);
 } 

/** 
* ���ù�˾ǩ����C
*
* @param qdr03 ��˾ǩ����C
*/
public void setQdr03 ( String qdr03) {
this.setAttributeValue( AccountHVO.QDR03,qdr03);
 } 

/** 
* ��ȡ��˾ǩ����D
*
* @return ��˾ǩ����D
*/
public String getQdr04 () {
return (String) this.getAttributeValue( AccountHVO.QDR04);
 } 

/** 
* ���ù�˾ǩ����D
*
* @param qdr04 ��˾ǩ����D
*/
public void setQdr04 ( String qdr04) {
this.setAttributeValue( AccountHVO.QDR04,qdr04);
 } 

/** 
* ��ȡ��˾ǩ����E
*
* @return ��˾ǩ����E
*/
public String getQdr05 () {
return (String) this.getAttributeValue( AccountHVO.QDR05);
 } 

/** 
* ���ù�˾ǩ����E
*
* @param qdr05 ��˾ǩ����E
*/
public void setQdr05 ( String qdr05) {
this.setAttributeValue( AccountHVO.QDR05,qdr05);
 } 

/** 
* ��ȡ��ݵ�ַ
*
* @return ��ݵ�ַ
*/
public String getSend () {
return (String) this.getAttributeValue( AccountHVO.SEND);
 } 

/** 
* ���ÿ�ݵ�ַ
*
* @param send ��ݵ�ַ
*/
public void setSend ( String send) {
this.setAttributeValue( AccountHVO.SEND,send);
 } 

/** 
* ��ȡǩ������A
*
* @return ǩ������A
*/
public String getSign01 () {
return (String) this.getAttributeValue( AccountHVO.SIGN01);
 } 

/** 
* ����ǩ������A
*
* @param sign01 ǩ������A
*/
public void setSign01 ( String sign01) {
this.setAttributeValue( AccountHVO.SIGN01,sign01);
 } 

/** 
* ��ȡǩ������B
*
* @return ǩ������B
*/
public String getSign02 () {
return (String) this.getAttributeValue( AccountHVO.SIGN02);
 } 

/** 
* ����ǩ������B
*
* @param sign02 ǩ������B
*/
public void setSign02 ( String sign02) {
this.setAttributeValue( AccountHVO.SIGN02,sign02);
 } 

/** 
* ��ȡǩ������C
*
* @return ǩ������C
*/
public String getSign03 () {
return (String) this.getAttributeValue( AccountHVO.SIGN03);
 } 

/** 
* ����ǩ������C
*
* @param sign03 ǩ������C
*/
public void setSign03 ( String sign03) {
this.setAttributeValue( AccountHVO.SIGN03,sign03);
 } 

/** 
* ��ȡǩ������D
*
* @return ǩ������D
*/
public String getSign04 () {
return (String) this.getAttributeValue( AccountHVO.SIGN04);
 } 

/** 
* ����ǩ������D
*
* @param sign04 ǩ������D
*/
public void setSign04 ( String sign04) {
this.setAttributeValue( AccountHVO.SIGN04,sign04);
 } 

/** 
* ��ȡǩ������E
*
* @return ǩ������E
*/
public String getSign05 () {
return (String) this.getAttributeValue( AccountHVO.SIGN05);
 } 

/** 
* ����ǩ������E
*
* @param sign05 ǩ������E
*/
public void setSign05 ( String sign05) {
this.setAttributeValue( AccountHVO.SIGN05,sign05);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( AccountHVO.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( AccountHVO.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( AccountHVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( AccountHVO.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getUnit_fk () {
return (String) this.getAttributeValue( AccountHVO.UNIT_FK);
 } 

/** 
* ���ø�������
*
* @param unit_fk ��������
*/
public void setUnit_fk ( String unit_fk) {
this.setAttributeValue( AccountHVO.UNIT_FK,unit_fk);
 } 

/** 
* ��ȡǩ������
*
* @return ǩ������
*/
public String getUnit_qz () {
return (String) this.getAttributeValue( AccountHVO.UNIT_QZ);
 } 

/** 
* ����ǩ������
*
* @param unit_qz ǩ������
*/
public void setUnit_qz ( String unit_qz) {
this.setAttributeValue( AccountHVO.UNIT_QZ,unit_qz);
 } 

/** 
* ��ȡЭ������
*
* @return Э������
*/
public String getUnit_xy () {
return (String) this.getAttributeValue( AccountHVO.UNIT_XY);
 } 

/** 
* ����Э������
*
* @param unit_xy Э������
*/
public void setUnit_xy ( String unit_xy) {
this.setAttributeValue( AccountHVO.UNIT_XY,unit_xy);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( AccountHVO.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( AccountHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( AccountHVO.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( AccountHVO.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( AccountHVO.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( AccountHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( AccountHVO.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( AccountHVO.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( AccountHVO.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( AccountHVO.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( AccountHVO.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( AccountHVO.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( AccountHVO.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( AccountHVO.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( AccountHVO.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( AccountHVO.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( AccountHVO.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( AccountHVO.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( AccountHVO.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( AccountHVO.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( AccountHVO.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( AccountHVO.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( AccountHVO.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( AccountHVO.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( AccountHVO.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( AccountHVO.VDEF10,vdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVdef11 () {
return (String) this.getAttributeValue( AccountHVO.VDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vdef11 �Զ�����11
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( AccountHVO.VDEF11,vdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVdef12 () {
return (String) this.getAttributeValue( AccountHVO.VDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vdef12 �Զ�����12
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( AccountHVO.VDEF12,vdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVdef13 () {
return (String) this.getAttributeValue( AccountHVO.VDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vdef13 �Զ�����13
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( AccountHVO.VDEF13,vdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVdef14 () {
return (String) this.getAttributeValue( AccountHVO.VDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vdef14 �Զ�����14
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( AccountHVO.VDEF14,vdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVdef15 () {
return (String) this.getAttributeValue( AccountHVO.VDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vdef15 �Զ�����15
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( AccountHVO.VDEF15,vdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVdef16 () {
return (String) this.getAttributeValue( AccountHVO.VDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vdef16 �Զ�����16
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( AccountHVO.VDEF16,vdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVdef17 () {
return (String) this.getAttributeValue( AccountHVO.VDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vdef17 �Զ�����17
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( AccountHVO.VDEF17,vdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVdef18 () {
return (String) this.getAttributeValue( AccountHVO.VDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vdef18 �Զ�����18
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( AccountHVO.VDEF18,vdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVdef19 () {
return (String) this.getAttributeValue( AccountHVO.VDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vdef19 �Զ�����19
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( AccountHVO.VDEF19,vdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVdef20 () {
return (String) this.getAttributeValue( AccountHVO.VDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vdef20 �Զ�����20
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( AccountHVO.VDEF20,vdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( AccountHVO.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( AccountHVO.VMEMO,vmemo);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( AccountHVO.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( AccountHVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* ��ȡ����Ա
*
* @return ����Ա
*/
public String getXsy () {
return (String) this.getAttributeValue( AccountHVO.XSY);
 } 

/** 
* ��������Ա
*
* @param xsy ����Ա
*/
public void setXsy ( String xsy) {
this.setAttributeValue( AccountHVO.XSY,xsy);
 } 

/** 
* ��ȡ������ϵ��ʽ
*
* @return ������ϵ��ʽ
*/
public String getXsy_phone () {
return (String) this.getAttributeValue( AccountHVO.XSY_PHONE);
 } 

/** 
* ����������ϵ��ʽ
*
* @param xsy_phone ������ϵ��ʽ
*/
public void setXsy_phone ( String xsy_phone) {
this.setAttributeValue( AccountHVO.XSY_PHONE,xsy_phone);
 } 

/** 
* ��ȡЭ����Ч��
*
* @return Э����Ч��
*/
public String getYxq () {
return (String) this.getAttributeValue( AccountHVO.YXQ);
 } 

/** 
* ����Э����Ч��
*
* @param yxq Э����Ч��
*/
public void setYxq ( String yxq) {
this.setAttributeValue( AccountHVO.YXQ,yxq);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_accoutHVO");
  }
}