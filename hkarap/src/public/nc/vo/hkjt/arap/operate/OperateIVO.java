package nc.vo.hkjt.arap.operate;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class OperateIVO extends SuperVO {
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
public static final String PK_HK_ARAP_OPERATE="pk_hk_arap_operate";
/**
*����pk
*/
public static final String PK_HK_ARAP_OPERATE_I="pk_hk_arap_operate_i";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01
*/
public static final String VBDEF01="vbdef01";
/**
*�Զ�����02
*/
public static final String VBDEF02="vbdef02";
/**
*�Զ�����03
*/
public static final String VBDEF03="vbdef03";
/**
*�Զ�����04
*/
public static final String VBDEF04="vbdef04";
/**
*�Զ�����05
*/
public static final String VBDEF05="vbdef05";
/**
*�Զ�����06
*/
public static final String VBDEF06="vbdef06";
/**
*�Զ�����07
*/
public static final String VBDEF07="vbdef07";
/**
*�Զ�����08
*/
public static final String VBDEF08="vbdef08";
/**
*�Զ�����09
*/
public static final String VBDEF09="vbdef09";
/**
*�Զ�����10
*/
public static final String VBDEF10="vbdef10";
/**
*�Զ�����11
*/
public static final String VBDEF11="vbdef11";
/**
*�Զ�����12
*/
public static final String VBDEF12="vbdef12";
/**
*�Զ�����13
*/
public static final String VBDEF13="vbdef13";
/**
*�Զ�����14
*/
public static final String VBDEF14="vbdef14";
/**
*�Զ�����15
*/
public static final String VBDEF15="vbdef15";
/**
*�Զ�����16
*/
public static final String VBDEF16="vbdef16";
/**
*�Զ�����17
*/
public static final String VBDEF17="vbdef17";
/**
*�Զ�����18
*/
public static final String VBDEF18="vbdef18";
/**
*�Զ�����19
*/
public static final String VBDEF19="vbdef19";
/**
*�Զ�����20
*/
public static final String VBDEF20="vbdef20";
/**
*��ע
*/
public static final String VBMEMO="vbmemo";
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
return (String) this.getAttributeValue( OperateIVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( OperateIVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( OperateIVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( OperateIVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( OperateIVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( OperateIVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( OperateIVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( OperateIVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( OperateIVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( OperateIVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( OperateIVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( OperateIVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_arap_operate () {
return (String) this.getAttributeValue( OperateIVO.PK_HK_ARAP_OPERATE);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_arap_operate �ϲ㵥������
*/
public void setPk_hk_arap_operate ( String pk_hk_arap_operate) {
this.setAttributeValue( OperateIVO.PK_HK_ARAP_OPERATE,pk_hk_arap_operate);
 } 

/** 
* ��ȡ����pk
*
* @return ����pk
*/
public String getPk_hk_arap_operate_i () {
return (String) this.getAttributeValue( OperateIVO.PK_HK_ARAP_OPERATE_I);
 } 

/** 
* ���ú���pk
*
* @param pk_hk_arap_operate_i ����pk
*/
public void setPk_hk_arap_operate_i ( String pk_hk_arap_operate_i) {
this.setAttributeValue( OperateIVO.PK_HK_ARAP_OPERATE_I,pk_hk_arap_operate_i);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( OperateIVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( OperateIVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( OperateIVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( OperateIVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( OperateIVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( OperateIVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( OperateIVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( OperateIVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( OperateIVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( OperateIVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( OperateIVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( OperateIVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vbdef11 �Զ�����11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( OperateIVO.VBDEF11,vbdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vbdef12 �Զ�����12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( OperateIVO.VBDEF12,vbdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vbdef13 �Զ�����13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( OperateIVO.VBDEF13,vbdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vbdef14 �Զ�����14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( OperateIVO.VBDEF14,vbdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vbdef15 �Զ�����15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( OperateIVO.VBDEF15,vbdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vbdef16 �Զ�����16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( OperateIVO.VBDEF16,vbdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vbdef17 �Զ�����17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( OperateIVO.VBDEF17,vbdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vbdef18 �Զ�����18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( OperateIVO.VBDEF18,vbdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vbdef19 �Զ�����19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( OperateIVO.VBDEF19,vbdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( OperateIVO.VBDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vbdef20 �Զ�����20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( OperateIVO.VBDEF20,vbdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( OperateIVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( OperateIVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( OperateIVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( OperateIVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( OperateIVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( OperateIVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( OperateIVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( OperateIVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_operateIVO");
  }
}