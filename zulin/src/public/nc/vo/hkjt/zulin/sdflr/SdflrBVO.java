package nc.vo.hkjt.zulin.sdflr;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SdflrBVO extends SuperVO {
/**
*���γ�����
*/
public static final String BCCB_NUM="bccb_num";
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
*����
*/
public static final String PK_AREA="pk_area";
/**
*�ͻ�
*/
public static final String PK_CUST="pk_cust";
/**
*�ϲ㵥������
*/
public static final String PK_HK_ZULIN_SDFLR="pk_hk_zulin_sdflr";
/**
*ˮ���¼����pk
*/
public static final String PK_HK_ZULIN_SDFLR_B="pk_hk_zulin_sdflr_b";
/**
*λ��
*/
public static final String PK_PLACE="pk_place";
/**
*����
*/
public static final String PK_ROOM="pk_room";
/**
*�շ���Ŀ
*/
public static final String PK_SFXM="pk_sfxm";
/**
*����
*/
public static final String PRICE="price";
/**
*�ϴγ�������
*/
public static final String SCCB_DATE="sccb_date";
/**
*�ϴγ�����
*/
public static final String SCCB_NUM="sccb_num";
/**
*����
*/
public static final String TIMES="times";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*Ӧ�ɽ��
*/
public static final String USE_MNY="use_mny";
/**
*����
*/
public static final String USE_NUM="use_num";
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
* ��ȡ���γ�����
*
* @return ���γ�����
*/
public UFDouble getBccb_num () {
return (UFDouble) this.getAttributeValue( SdflrBVO.BCCB_NUM);
 } 

/** 
* ���ñ��γ�����
*
* @param bccb_num ���γ�����
*/
public void setBccb_num ( UFDouble bccb_num) {
this.setAttributeValue( SdflrBVO.BCCB_NUM,bccb_num);
 } 

/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( SdflrBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( SdflrBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( SdflrBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( SdflrBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( SdflrBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( SdflrBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( SdflrBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( SdflrBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( SdflrBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( SdflrBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( SdflrBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( SdflrBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_area () {
return (String) this.getAttributeValue( SdflrBVO.PK_AREA);
 } 

/** 
* ��������
*
* @param pk_area ����
*/
public void setPk_area ( String pk_area) {
this.setAttributeValue( SdflrBVO.PK_AREA,pk_area);
 } 

/** 
* ��ȡ�ͻ�
*
* @return �ͻ�
*/
public String getPk_cust () {
return (String) this.getAttributeValue( SdflrBVO.PK_CUST);
 } 

/** 
* ���ÿͻ�
*
* @param pk_cust �ͻ�
*/
public void setPk_cust ( String pk_cust) {
this.setAttributeValue( SdflrBVO.PK_CUST,pk_cust);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_zulin_sdflr () {
return (String) this.getAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_zulin_sdflr �ϲ㵥������
*/
public void setPk_hk_zulin_sdflr ( String pk_hk_zulin_sdflr) {
this.setAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR,pk_hk_zulin_sdflr);
 } 

/** 
* ��ȡˮ���¼����pk
*
* @return ˮ���¼����pk
*/
public String getPk_hk_zulin_sdflr_b () {
return (String) this.getAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR_B);
 } 

/** 
* ����ˮ���¼����pk
*
* @param pk_hk_zulin_sdflr_b ˮ���¼����pk
*/
public void setPk_hk_zulin_sdflr_b ( String pk_hk_zulin_sdflr_b) {
this.setAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR_B,pk_hk_zulin_sdflr_b);
 } 

/** 
* ��ȡλ��
*
* @return λ��
*/
public String getPk_place () {
return (String) this.getAttributeValue( SdflrBVO.PK_PLACE);
 } 

/** 
* ����λ��
*
* @param pk_place λ��
*/
public void setPk_place ( String pk_place) {
this.setAttributeValue( SdflrBVO.PK_PLACE,pk_place);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_room () {
return (String) this.getAttributeValue( SdflrBVO.PK_ROOM);
 } 

/** 
* ���÷���
*
* @param pk_room ����
*/
public void setPk_room ( String pk_room) {
this.setAttributeValue( SdflrBVO.PK_ROOM,pk_room);
 } 

/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getPk_sfxm () {
return (String) this.getAttributeValue( SdflrBVO.PK_SFXM);
 } 

/** 
* �����շ���Ŀ
*
* @param pk_sfxm �շ���Ŀ
*/
public void setPk_sfxm ( String pk_sfxm) {
this.setAttributeValue( SdflrBVO.PK_SFXM,pk_sfxm);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getPrice () {
return (UFDouble) this.getAttributeValue( SdflrBVO.PRICE);
 } 

/** 
* ���õ���
*
* @param price ����
*/
public void setPrice ( UFDouble price) {
this.setAttributeValue( SdflrBVO.PRICE,price);
 } 

/** 
* ��ȡ�ϴγ�������
*
* @return �ϴγ�������
*/
public UFDate getSccb_date () {
return (UFDate) this.getAttributeValue( SdflrBVO.SCCB_DATE);
 } 

/** 
* �����ϴγ�������
*
* @param sccb_date �ϴγ�������
*/
public void setSccb_date ( UFDate sccb_date) {
this.setAttributeValue( SdflrBVO.SCCB_DATE,sccb_date);
 } 

/** 
* ��ȡ�ϴγ�����
*
* @return �ϴγ�����
*/
public UFDouble getSccb_num () {
return (UFDouble) this.getAttributeValue( SdflrBVO.SCCB_NUM);
 } 

/** 
* �����ϴγ�����
*
* @param sccb_num �ϴγ�����
*/
public void setSccb_num ( UFDouble sccb_num) {
this.setAttributeValue( SdflrBVO.SCCB_NUM,sccb_num);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getTimes () {
return (UFDouble) this.getAttributeValue( SdflrBVO.TIMES);
 } 

/** 
* ���ñ���
*
* @param times ����
*/
public void setTimes ( UFDouble times) {
this.setAttributeValue( SdflrBVO.TIMES,times);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( SdflrBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( SdflrBVO.TS,ts);
 } 

/** 
* ��ȡӦ�ɽ��
*
* @return Ӧ�ɽ��
*/
public UFDouble getUse_mny () {
return (UFDouble) this.getAttributeValue( SdflrBVO.USE_MNY);
 } 

/** 
* ����Ӧ�ɽ��
*
* @param use_mny Ӧ�ɽ��
*/
public void setUse_mny ( UFDouble use_mny) {
this.setAttributeValue( SdflrBVO.USE_MNY,use_mny);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getUse_num () {
return (UFDouble) this.getAttributeValue( SdflrBVO.USE_NUM);
 } 

/** 
* ��������
*
* @param use_num ����
*/
public void setUse_num ( UFDouble use_num) {
this.setAttributeValue( SdflrBVO.USE_NUM,use_num);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( SdflrBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( SdflrBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( SdflrBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( SdflrBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( SdflrBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( SdflrBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( SdflrBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( SdflrBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( SdflrBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( SdflrBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vbdef11 �Զ�����11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( SdflrBVO.VBDEF11,vbdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vbdef12 �Զ�����12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( SdflrBVO.VBDEF12,vbdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vbdef13 �Զ�����13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( SdflrBVO.VBDEF13,vbdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vbdef14 �Զ�����14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( SdflrBVO.VBDEF14,vbdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vbdef15 �Զ�����15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( SdflrBVO.VBDEF15,vbdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vbdef16 �Զ�����16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( SdflrBVO.VBDEF16,vbdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vbdef17 �Զ�����17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( SdflrBVO.VBDEF17,vbdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vbdef18 �Զ�����18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( SdflrBVO.VBDEF18,vbdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vbdef19 �Զ�����19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( SdflrBVO.VBDEF19,vbdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vbdef20 �Զ�����20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( SdflrBVO.VBDEF20,vbdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( SdflrBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( SdflrBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( SdflrBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( SdflrBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( SdflrBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( SdflrBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( SdflrBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( SdflrBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_zulin_sdflrBVO");
  }
}