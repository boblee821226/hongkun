package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillEVO extends SuperVO {
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
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*����pk
*/
public static final String PK_HK_ARAP_BILL_E="pk_hk_arap_bill_e";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01
*/
public static final String VEDEF01="vedef01";
/**
*�Զ�����02
*/
public static final String VEDEF02="vedef02";
/**
*�Զ�����03
*/
public static final String VEDEF03="vedef03";
/**
*�Զ�����04
*/
public static final String VEDEF04="vedef04";
/**
*�Զ�����05
*/
public static final String VEDEF05="vedef05";
/**
*�Զ�����06
*/
public static final String VEDEF06="vedef06";
/**
*�Զ�����07
*/
public static final String VEDEF07="vedef07";
/**
*�Զ�����08
*/
public static final String VEDEF08="vedef08";
/**
*�Զ�����09
*/
public static final String VEDEF09="vedef09";
/**
*�Զ�����10
*/
public static final String VEDEF10="vedef10";
/**
*�Զ�����11
*/
public static final String VEDEF11="vedef11";
/**
*�Զ�����12
*/
public static final String VEDEF12="vedef12";
/**
*�Զ�����13
*/
public static final String VEDEF13="vedef13";
/**
*�Զ�����14
*/
public static final String VEDEF14="vedef14";
/**
*�Զ�����15
*/
public static final String VEDEF15="vedef15";
/**
*�Զ�����16
*/
public static final String VEDEF16="vedef16";
/**
*�Զ�����17
*/
public static final String VEDEF17="vedef17";
/**
*�Զ�����18
*/
public static final String VEDEF18="vedef18";
/**
*�Զ�����19
*/
public static final String VEDEF19="vedef19";
/**
*�Զ�����20
*/
public static final String VEDEF20="vedef20";
/**
*��ע
*/
public static final String VEMEMO="vememo";
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
return (String) this.getAttributeValue( BillEVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( BillEVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( BillEVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( BillEVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( BillEVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( BillEVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( BillEVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( BillEVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( BillEVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( BillEVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( BillEVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( BillEVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillEVO.PK_HK_ARAP_BILL);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_arap_bill �ϲ㵥������
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillEVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* ��ȡ����pk
*
* @return ����pk
*/
public String getPk_hk_arap_bill_e () {
return (String) this.getAttributeValue( BillEVO.PK_HK_ARAP_BILL_E);
 } 

/** 
* ���ñ���pk
*
* @param pk_hk_arap_bill_e ����pk
*/
public void setPk_hk_arap_bill_e ( String pk_hk_arap_bill_e) {
this.setAttributeValue( BillEVO.PK_HK_ARAP_BILL_E,pk_hk_arap_bill_e);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillEVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillEVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVedef01 () {
return (String) this.getAttributeValue( BillEVO.VEDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vedef01 �Զ�����01
*/
public void setVedef01 ( String vedef01) {
this.setAttributeValue( BillEVO.VEDEF01,vedef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVedef02 () {
return (String) this.getAttributeValue( BillEVO.VEDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vedef02 �Զ�����02
*/
public void setVedef02 ( String vedef02) {
this.setAttributeValue( BillEVO.VEDEF02,vedef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVedef03 () {
return (String) this.getAttributeValue( BillEVO.VEDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vedef03 �Զ�����03
*/
public void setVedef03 ( String vedef03) {
this.setAttributeValue( BillEVO.VEDEF03,vedef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVedef04 () {
return (String) this.getAttributeValue( BillEVO.VEDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vedef04 �Զ�����04
*/
public void setVedef04 ( String vedef04) {
this.setAttributeValue( BillEVO.VEDEF04,vedef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVedef05 () {
return (String) this.getAttributeValue( BillEVO.VEDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vedef05 �Զ�����05
*/
public void setVedef05 ( String vedef05) {
this.setAttributeValue( BillEVO.VEDEF05,vedef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVedef06 () {
return (String) this.getAttributeValue( BillEVO.VEDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vedef06 �Զ�����06
*/
public void setVedef06 ( String vedef06) {
this.setAttributeValue( BillEVO.VEDEF06,vedef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVedef07 () {
return (String) this.getAttributeValue( BillEVO.VEDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vedef07 �Զ�����07
*/
public void setVedef07 ( String vedef07) {
this.setAttributeValue( BillEVO.VEDEF07,vedef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVedef08 () {
return (String) this.getAttributeValue( BillEVO.VEDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vedef08 �Զ�����08
*/
public void setVedef08 ( String vedef08) {
this.setAttributeValue( BillEVO.VEDEF08,vedef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVedef09 () {
return (String) this.getAttributeValue( BillEVO.VEDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vedef09 �Զ�����09
*/
public void setVedef09 ( String vedef09) {
this.setAttributeValue( BillEVO.VEDEF09,vedef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVedef10 () {
return (String) this.getAttributeValue( BillEVO.VEDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vedef10 �Զ�����10
*/
public void setVedef10 ( String vedef10) {
this.setAttributeValue( BillEVO.VEDEF10,vedef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVedef11 () {
return (String) this.getAttributeValue( BillEVO.VEDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vedef11 �Զ�����11
*/
public void setVedef11 ( String vedef11) {
this.setAttributeValue( BillEVO.VEDEF11,vedef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVedef12 () {
return (String) this.getAttributeValue( BillEVO.VEDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vedef12 �Զ�����12
*/
public void setVedef12 ( String vedef12) {
this.setAttributeValue( BillEVO.VEDEF12,vedef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVedef13 () {
return (String) this.getAttributeValue( BillEVO.VEDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vedef13 �Զ�����13
*/
public void setVedef13 ( String vedef13) {
this.setAttributeValue( BillEVO.VEDEF13,vedef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVedef14 () {
return (String) this.getAttributeValue( BillEVO.VEDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vedef14 �Զ�����14
*/
public void setVedef14 ( String vedef14) {
this.setAttributeValue( BillEVO.VEDEF14,vedef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVedef15 () {
return (String) this.getAttributeValue( BillEVO.VEDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vedef15 �Զ�����15
*/
public void setVedef15 ( String vedef15) {
this.setAttributeValue( BillEVO.VEDEF15,vedef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVedef16 () {
return (String) this.getAttributeValue( BillEVO.VEDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vedef16 �Զ�����16
*/
public void setVedef16 ( String vedef16) {
this.setAttributeValue( BillEVO.VEDEF16,vedef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVedef17 () {
return (String) this.getAttributeValue( BillEVO.VEDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vedef17 �Զ�����17
*/
public void setVedef17 ( String vedef17) {
this.setAttributeValue( BillEVO.VEDEF17,vedef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVedef18 () {
return (String) this.getAttributeValue( BillEVO.VEDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vedef18 �Զ�����18
*/
public void setVedef18 ( String vedef18) {
this.setAttributeValue( BillEVO.VEDEF18,vedef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVedef19 () {
return (String) this.getAttributeValue( BillEVO.VEDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vedef19 �Զ�����19
*/
public void setVedef19 ( String vedef19) {
this.setAttributeValue( BillEVO.VEDEF19,vedef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVedef20 () {
return (String) this.getAttributeValue( BillEVO.VEDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vedef20 �Զ�����20
*/
public void setVedef20 ( String vedef20) {
this.setAttributeValue( BillEVO.VEDEF20,vedef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVememo () {
return (String) this.getAttributeValue( BillEVO.VEMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vememo ��ע
*/
public void setVememo ( String vememo) {
this.setAttributeValue( BillEVO.VEMEMO,vememo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( BillEVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( BillEVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( BillEVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( BillEVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( BillEVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( BillEVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billEVO");
  }
}