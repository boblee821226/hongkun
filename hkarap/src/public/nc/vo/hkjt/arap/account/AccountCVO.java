package nc.vo.hkjt.arap.account;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class AccountCVO extends SuperVO {
/**
*Դͷ���ݱ���id
*/
public static final String CFIRSTBILLBID="cfirstbillbid";
/**
*Դͷ����id
*/
public static final String CFIRSTBILLID="cfirstbillid";
/**
*Դͷ��������
*/
public static final String CFIRSTTYPECODE="cfirsttypecode";
/**
*�ϲ㵥�ݱ���id
*/
public static final String CSOURCEBILLBID="csourcebillbid";
/**
*�ϲ㵥��id
*/
public static final String CSOURCEBILLID="csourcebillid";
/**
*�ϲ㵥������
*/
public static final String CSOURCETYPECODE="csourcetypecode";
/**
*�ϲ㵥������
*/
public static final String PK_HK_ARAP_ACCOUNT="pk_hk_arap_account";
/**
*Ӷ����Ϣpk
*/
public static final String PK_HK_ARAP_ACCOUT_C="pk_hk_arap_accout_c";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01
*/
public static final String VCDEF01="vcdef01";
/**
*�Զ�����02
*/
public static final String VCDEF02="vcdef02";
/**
*�Զ�����03
*/
public static final String VCDEF03="vcdef03";
/**
*�Զ�����04
*/
public static final String VCDEF04="vcdef04";
/**
*�Զ�����05
*/
public static final String VCDEF05="vcdef05";
/**
*�Զ�����06
*/
public static final String VCDEF06="vcdef06";
/**
*�Զ�����07
*/
public static final String VCDEF07="vcdef07";
/**
*�Զ�����08
*/
public static final String VCDEF08="vcdef08";
/**
*�Զ�����09
*/
public static final String VCDEF09="vcdef09";
/**
*�Զ�����10
*/
public static final String VCDEF10="vcdef10";
/**
*�Զ�����11
*/
public static final String VCDEF11="vcdef11";
/**
*�Զ�����12
*/
public static final String VCDEF12="vcdef12";
/**
*�Զ�����13
*/
public static final String VCDEF13="vcdef13";
/**
*�Զ�����14
*/
public static final String VCDEF14="vcdef14";
/**
*�Զ�����15
*/
public static final String VCDEF15="vcdef15";
/**
*�Զ�����16
*/
public static final String VCDEF16="vcdef16";
/**
*�Զ�����17
*/
public static final String VCDEF17="vcdef17";
/**
*�Զ�����18
*/
public static final String VCDEF18="vcdef18";
/**
*�Զ�����19
*/
public static final String VCDEF19="vcdef19";
/**
*�Զ�����20
*/
public static final String VCDEF20="vcdef20";
/**
*��ע
*/
public static final String VCMEMO="vcmemo";
/**
*Դͷ���ݺ�
*/
public static final String VFIRSTBILLCODE="vfirstbillcode";
/**
*�к�
*/
public static final String VROWNO="vrowno";
/**
*�ϲ㵥�ݺ�
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( AccountCVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( AccountCVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( AccountCVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( AccountCVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( AccountCVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( AccountCVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( AccountCVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( AccountCVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( AccountCVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( AccountCVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( AccountCVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( AccountCVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_arap_account () {
return (String) this.getAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUNT);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_arap_account �ϲ㵥������
*/
public void setPk_hk_arap_account ( String pk_hk_arap_account) {
this.setAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUNT,pk_hk_arap_account);
 } 

/** 
* ��ȡӶ����Ϣpk
*
* @return Ӷ����Ϣpk
*/
public String getPk_hk_arap_accout_c () {
return (String) this.getAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUT_C);
 } 

/** 
* ����Ӷ����Ϣpk
*
* @param pk_hk_arap_accout_c Ӷ����Ϣpk
*/
public void setPk_hk_arap_accout_c ( String pk_hk_arap_accout_c) {
this.setAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUT_C,pk_hk_arap_accout_c);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( AccountCVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( AccountCVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVcdef01 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vcdef01 �Զ�����01
*/
public void setVcdef01 ( String vcdef01) {
this.setAttributeValue( AccountCVO.VCDEF01,vcdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVcdef02 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vcdef02 �Զ�����02
*/
public void setVcdef02 ( String vcdef02) {
this.setAttributeValue( AccountCVO.VCDEF02,vcdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVcdef03 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vcdef03 �Զ�����03
*/
public void setVcdef03 ( String vcdef03) {
this.setAttributeValue( AccountCVO.VCDEF03,vcdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVcdef04 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vcdef04 �Զ�����04
*/
public void setVcdef04 ( String vcdef04) {
this.setAttributeValue( AccountCVO.VCDEF04,vcdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVcdef05 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vcdef05 �Զ�����05
*/
public void setVcdef05 ( String vcdef05) {
this.setAttributeValue( AccountCVO.VCDEF05,vcdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVcdef06 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vcdef06 �Զ�����06
*/
public void setVcdef06 ( String vcdef06) {
this.setAttributeValue( AccountCVO.VCDEF06,vcdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVcdef07 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vcdef07 �Զ�����07
*/
public void setVcdef07 ( String vcdef07) {
this.setAttributeValue( AccountCVO.VCDEF07,vcdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVcdef08 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vcdef08 �Զ�����08
*/
public void setVcdef08 ( String vcdef08) {
this.setAttributeValue( AccountCVO.VCDEF08,vcdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVcdef09 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vcdef09 �Զ�����09
*/
public void setVcdef09 ( String vcdef09) {
this.setAttributeValue( AccountCVO.VCDEF09,vcdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVcdef10 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vcdef10 �Զ�����10
*/
public void setVcdef10 ( String vcdef10) {
this.setAttributeValue( AccountCVO.VCDEF10,vcdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVcdef11 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vcdef11 �Զ�����11
*/
public void setVcdef11 ( String vcdef11) {
this.setAttributeValue( AccountCVO.VCDEF11,vcdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVcdef12 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vcdef12 �Զ�����12
*/
public void setVcdef12 ( String vcdef12) {
this.setAttributeValue( AccountCVO.VCDEF12,vcdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVcdef13 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vcdef13 �Զ�����13
*/
public void setVcdef13 ( String vcdef13) {
this.setAttributeValue( AccountCVO.VCDEF13,vcdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVcdef14 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vcdef14 �Զ�����14
*/
public void setVcdef14 ( String vcdef14) {
this.setAttributeValue( AccountCVO.VCDEF14,vcdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVcdef15 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vcdef15 �Զ�����15
*/
public void setVcdef15 ( String vcdef15) {
this.setAttributeValue( AccountCVO.VCDEF15,vcdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVcdef16 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vcdef16 �Զ�����16
*/
public void setVcdef16 ( String vcdef16) {
this.setAttributeValue( AccountCVO.VCDEF16,vcdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVcdef17 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vcdef17 �Զ�����17
*/
public void setVcdef17 ( String vcdef17) {
this.setAttributeValue( AccountCVO.VCDEF17,vcdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVcdef18 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vcdef18 �Զ�����18
*/
public void setVcdef18 ( String vcdef18) {
this.setAttributeValue( AccountCVO.VCDEF18,vcdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVcdef19 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vcdef19 �Զ�����19
*/
public void setVcdef19 ( String vcdef19) {
this.setAttributeValue( AccountCVO.VCDEF19,vcdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVcdef20 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vcdef20 �Զ�����20
*/
public void setVcdef20 ( String vcdef20) {
this.setAttributeValue( AccountCVO.VCDEF20,vcdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVcmemo () {
return (String) this.getAttributeValue( AccountCVO.VCMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vcmemo ��ע
*/
public void setVcmemo ( String vcmemo) {
this.setAttributeValue( AccountCVO.VCMEMO,vcmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( AccountCVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( AccountCVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( AccountCVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( AccountCVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( AccountCVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( AccountCVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_accoutCVO");
  }
}