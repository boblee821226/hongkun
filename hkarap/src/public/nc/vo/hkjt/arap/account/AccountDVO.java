package nc.vo.hkjt.arap.account;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class AccountDVO extends SuperVO {
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
*���Ʊ��Ϣpk
*/
public static final String PK_HK_ARAP_ACCOUT_D="pk_hk_arap_accout_d";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01
*/
public static final String VDDEF01="vddef01";
/**
*�Զ�����02
*/
public static final String VDDEF02="vddef02";
/**
*�Զ�����03
*/
public static final String VDDEF03="vddef03";
/**
*�Զ�����04
*/
public static final String VDDEF04="vddef04";
/**
*�Զ�����05
*/
public static final String VDDEF05="vddef05";
/**
*�Զ�����06
*/
public static final String VDDEF06="vddef06";
/**
*�Զ�����07
*/
public static final String VDDEF07="vddef07";
/**
*�Զ�����08
*/
public static final String VDDEF08="vddef08";
/**
*�Զ�����09
*/
public static final String VDDEF09="vddef09";
/**
*�Զ�����10
*/
public static final String VDDEF10="vddef10";
/**
*�Զ�����11
*/
public static final String VDDEF11="vddef11";
/**
*�Զ�����12
*/
public static final String VDDEF12="vddef12";
/**
*�Զ�����13
*/
public static final String VDDEF13="vddef13";
/**
*�Զ�����14
*/
public static final String VDDEF14="vddef14";
/**
*�Զ�����15
*/
public static final String VDDEF15="vddef15";
/**
*�Զ�����16
*/
public static final String VDDEF16="vddef16";
/**
*�Զ�����17
*/
public static final String VDDEF17="vddef17";
/**
*�Զ�����18
*/
public static final String VDDEF18="vddef18";
/**
*�Զ�����19
*/
public static final String VDDEF19="vddef19";
/**
*�Զ�����20
*/
public static final String VDDEF20="vddef20";
/**
*��ע
*/
public static final String VDMEMO="vdmemo";
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
return (String) this.getAttributeValue( AccountDVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( AccountDVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( AccountDVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( AccountDVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( AccountDVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( AccountDVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( AccountDVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( AccountDVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( AccountDVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( AccountDVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( AccountDVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( AccountDVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_arap_account () {
return (String) this.getAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUNT);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_arap_account �ϲ㵥������
*/
public void setPk_hk_arap_account ( String pk_hk_arap_account) {
this.setAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUNT,pk_hk_arap_account);
 } 

/** 
* ��ȡ���Ʊ��Ϣpk
*
* @return ���Ʊ��Ϣpk
*/
public String getPk_hk_arap_accout_d () {
return (String) this.getAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUT_D);
 } 

/** 
* �������Ʊ��Ϣpk
*
* @param pk_hk_arap_accout_d ���Ʊ��Ϣpk
*/
public void setPk_hk_arap_accout_d ( String pk_hk_arap_accout_d) {
this.setAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUT_D,pk_hk_arap_accout_d);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( AccountDVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( AccountDVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVddef01 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vddef01 �Զ�����01
*/
public void setVddef01 ( String vddef01) {
this.setAttributeValue( AccountDVO.VDDEF01,vddef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVddef02 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vddef02 �Զ�����02
*/
public void setVddef02 ( String vddef02) {
this.setAttributeValue( AccountDVO.VDDEF02,vddef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVddef03 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vddef03 �Զ�����03
*/
public void setVddef03 ( String vddef03) {
this.setAttributeValue( AccountDVO.VDDEF03,vddef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVddef04 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vddef04 �Զ�����04
*/
public void setVddef04 ( String vddef04) {
this.setAttributeValue( AccountDVO.VDDEF04,vddef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVddef05 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vddef05 �Զ�����05
*/
public void setVddef05 ( String vddef05) {
this.setAttributeValue( AccountDVO.VDDEF05,vddef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVddef06 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vddef06 �Զ�����06
*/
public void setVddef06 ( String vddef06) {
this.setAttributeValue( AccountDVO.VDDEF06,vddef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVddef07 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vddef07 �Զ�����07
*/
public void setVddef07 ( String vddef07) {
this.setAttributeValue( AccountDVO.VDDEF07,vddef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVddef08 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vddef08 �Զ�����08
*/
public void setVddef08 ( String vddef08) {
this.setAttributeValue( AccountDVO.VDDEF08,vddef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVddef09 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vddef09 �Զ�����09
*/
public void setVddef09 ( String vddef09) {
this.setAttributeValue( AccountDVO.VDDEF09,vddef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVddef10 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vddef10 �Զ�����10
*/
public void setVddef10 ( String vddef10) {
this.setAttributeValue( AccountDVO.VDDEF10,vddef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVddef11 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vddef11 �Զ�����11
*/
public void setVddef11 ( String vddef11) {
this.setAttributeValue( AccountDVO.VDDEF11,vddef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVddef12 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vddef12 �Զ�����12
*/
public void setVddef12 ( String vddef12) {
this.setAttributeValue( AccountDVO.VDDEF12,vddef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVddef13 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vddef13 �Զ�����13
*/
public void setVddef13 ( String vddef13) {
this.setAttributeValue( AccountDVO.VDDEF13,vddef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVddef14 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vddef14 �Զ�����14
*/
public void setVddef14 ( String vddef14) {
this.setAttributeValue( AccountDVO.VDDEF14,vddef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVddef15 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vddef15 �Զ�����15
*/
public void setVddef15 ( String vddef15) {
this.setAttributeValue( AccountDVO.VDDEF15,vddef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVddef16 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vddef16 �Զ�����16
*/
public void setVddef16 ( String vddef16) {
this.setAttributeValue( AccountDVO.VDDEF16,vddef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVddef17 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vddef17 �Զ�����17
*/
public void setVddef17 ( String vddef17) {
this.setAttributeValue( AccountDVO.VDDEF17,vddef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVddef18 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vddef18 �Զ�����18
*/
public void setVddef18 ( String vddef18) {
this.setAttributeValue( AccountDVO.VDDEF18,vddef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVddef19 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vddef19 �Զ�����19
*/
public void setVddef19 ( String vddef19) {
this.setAttributeValue( AccountDVO.VDDEF19,vddef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVddef20 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vddef20 �Զ�����20
*/
public void setVddef20 ( String vddef20) {
this.setAttributeValue( AccountDVO.VDDEF20,vddef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVdmemo () {
return (String) this.getAttributeValue( AccountDVO.VDMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vdmemo ��ע
*/
public void setVdmemo ( String vdmemo) {
this.setAttributeValue( AccountDVO.VDMEMO,vdmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( AccountDVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( AccountDVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( AccountDVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( AccountDVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( AccountDVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( AccountDVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_accoutDVO");
  }
}