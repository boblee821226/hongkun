package nc.vo.hkjt.huiyuan.kazuofei;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KazuofeiBVO extends SuperVO {
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
*��code
*/
public static final String KA_CODE="ka_code";
/**
*��pk
*/
public static final String KA_PK="ka_pk";
/**
*����code
*/
public static final String KAXING_CODE="kaxing_code";
/**
*���ͽ��
*/
public static final String KAXING_JE="kaxing_je";
/**
*����name
*/
public static final String KAXING_NAME="kaxing_name";
/**
*����pk
*/
public static final String KAXING_PK="kaxing_pk";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_KAZUOFEI="pk_hk_huiyuan_kazuofei";
/**
*��Ա������ϸ��pk
*/
public static final String PK_HK_HUIYUAN_KAZUOFEI_B="pk_hk_huiyuan_kazuofei_b";
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
*��ת���
*/
public static final String YZ_JE="yz_je";
/**
*��תʱ��
*/
public static final String YZ_TIME="yz_time";
/**
*����ʱ��
*/
public static final String ZF_TIME="zf_time";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KazuofeiBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KazuofeiBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KazuofeiBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KazuofeiBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KazuofeiBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KazuofeiBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KazuofeiBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KazuofeiBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KazuofeiBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KazuofeiBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KazuofeiBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KazuofeiBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��code
*
* @return ��code
*/
public String getKa_code () {
return (String) this.getAttributeValue( KazuofeiBVO.KA_CODE);
 } 

/** 
* ���ÿ�code
*
* @param ka_code ��code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KazuofeiBVO.KA_CODE,ka_code);
 } 

/** 
* ��ȡ��pk
*
* @return ��pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( KazuofeiBVO.KA_PK);
 } 

/** 
* ���ÿ�pk
*
* @param ka_pk ��pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( KazuofeiBVO.KA_PK,ka_pk);
 } 

/** 
* ��ȡ����code
*
* @return ����code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KazuofeiBVO.KAXING_CODE);
 } 

/** 
* ���ÿ���code
*
* @param kaxing_code ����code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KazuofeiBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* ��ȡ���ͽ��
*
* @return ���ͽ��
*/
public UFDouble getKaxing_je () {
return (UFDouble) this.getAttributeValue( KazuofeiBVO.KAXING_JE);
 } 

/** 
* ���ÿ��ͽ��
*
* @param kaxing_je ���ͽ��
*/
public void setKaxing_je ( UFDouble kaxing_je) {
this.setAttributeValue( KazuofeiBVO.KAXING_JE,kaxing_je);
 } 

/** 
* ��ȡ����name
*
* @return ����name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KazuofeiBVO.KAXING_NAME);
 } 

/** 
* ���ÿ���name
*
* @param kaxing_name ����name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KazuofeiBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* ��ȡ����pk
*
* @return ����pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( KazuofeiBVO.KAXING_PK);
 } 

/** 
* ���ÿ���pk
*
* @param kaxing_pk ����pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( KazuofeiBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kazuofei () {
return (String) this.getAttributeValue( KazuofeiBVO.PK_HK_HUIYUAN_KAZUOFEI);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kazuofei �ϲ㵥������
*/
public void setPk_hk_huiyuan_kazuofei ( String pk_hk_huiyuan_kazuofei) {
this.setAttributeValue( KazuofeiBVO.PK_HK_HUIYUAN_KAZUOFEI,pk_hk_huiyuan_kazuofei);
 } 

/** 
* ��ȡ��Ա������ϸ��pk
*
* @return ��Ա������ϸ��pk
*/
public String getPk_hk_huiyuan_kazuofei_b () {
return (String) this.getAttributeValue( KazuofeiBVO.PK_HK_HUIYUAN_KAZUOFEI_B);
 } 

/** 
* ���û�Ա������ϸ��pk
*
* @param pk_hk_huiyuan_kazuofei_b ��Ա������ϸ��pk
*/
public void setPk_hk_huiyuan_kazuofei_b ( String pk_hk_huiyuan_kazuofei_b) {
this.setAttributeValue( KazuofeiBVO.PK_HK_HUIYUAN_KAZUOFEI_B,pk_hk_huiyuan_kazuofei_b);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KazuofeiBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KazuofeiBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KazuofeiBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KazuofeiBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KazuofeiBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KazuofeiBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KazuofeiBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KazuofeiBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KazuofeiBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KazuofeiBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KazuofeiBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KazuofeiBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KazuofeiBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KazuofeiBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KazuofeiBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KazuofeiBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KazuofeiBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KazuofeiBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KazuofeiBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KazuofeiBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KazuofeiBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ��ת���
*
* @return ��ת���
*/
public UFDouble getYz_je () {
return (UFDouble) this.getAttributeValue( KazuofeiBVO.YZ_JE);
 } 

/** 
* ������ת���
*
* @param yz_je ��ת���
*/
public void setYz_je ( UFDouble yz_je) {
this.setAttributeValue( KazuofeiBVO.YZ_JE,yz_je);
 } 

/** 
* ��ȡ��תʱ��
*
* @return ��תʱ��
*/
public String getYz_time () {
return (String) this.getAttributeValue( KazuofeiBVO.YZ_TIME);
 } 

/** 
* ������תʱ��
*
* @param yz_time ��תʱ��
*/
public void setYz_time ( String yz_time) {
this.setAttributeValue( KazuofeiBVO.YZ_TIME,yz_time);
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public String getZf_time () {
return (String) this.getAttributeValue( KazuofeiBVO.ZF_TIME);
 } 

/** 
* ��������ʱ��
*
* @param zf_time ����ʱ��
*/
public void setZf_time ( String zf_time) {
this.setAttributeValue( KazuofeiBVO.ZF_TIME,zf_time);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KazuofeiBVO");
  }
}