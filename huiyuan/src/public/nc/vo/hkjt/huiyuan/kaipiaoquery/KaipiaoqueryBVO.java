package nc.vo.hkjt.huiyuan.kaipiaoquery;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KaipiaoqueryBVO extends SuperVO {
/**
*Դͷ���ݱ���id
*/
public static final String CFIRSTBILLBID="cfirstbillbid";	// ���� ������
/**
*Դͷ����id
*/
public static final String CFIRSTBILLID="cfirstbillid";		// ��Ʊ����ʱ��
/**
*Դͷ��������
*/
public static final String CFIRSTTYPECODE="cfirsttypecode";	// ���Ͽ�Ʊ���
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
*��Ʊ��
*/
public static final String FPH="fph";
/**
*��Ʊ���
*/
public static final String FPJE="fpje";
/**
*��Ʊʱ��
*/
public static final String FPSJ="fpsj";
/**
*��Ʊ��
*/
public static final String KPR="kpr";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_KAIPIAOQUERY="pk_hk_huiyuan_kaipiaoquery";
/**
*��Ա��Ʊ��ѯ��pk
*/
public static final String PK_HK_HUIYUAN_KAIPIAOQUERY_B="pk_hk_huiyuan_kaipiaoquery_b";
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
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KaipiaoqueryBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KaipiaoqueryBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KaipiaoqueryBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KaipiaoqueryBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KaipiaoqueryBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KaipiaoqueryBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��Ʊ��
*
* @return ��Ʊ��
*/
public String getFph () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.FPH);
 } 

/** 
* ���÷�Ʊ��
*
* @param fph ��Ʊ��
*/
public void setFph ( String fph) {
this.setAttributeValue( KaipiaoqueryBVO.FPH,fph);
 } 

/** 
* ��ȡ��Ʊ���
*
* @return ��Ʊ���
*/
public UFDouble getFpje () {
return (UFDouble) this.getAttributeValue( KaipiaoqueryBVO.FPJE);
 } 

/** 
* ���÷�Ʊ���
*
* @param fpje ��Ʊ���
*/
public void setFpje ( UFDouble fpje) {
this.setAttributeValue( KaipiaoqueryBVO.FPJE,fpje);
 } 

/** 
* ��ȡ��Ʊʱ��
*
* @return ��Ʊʱ��
*/
public UFDateTime getFpsj () {
return (UFDateTime) this.getAttributeValue( KaipiaoqueryBVO.FPSJ);
 } 

/** 
* ���ÿ�Ʊʱ��
*
* @param fpsj ��Ʊʱ��
*/
public void setFpsj ( UFDateTime fpsj) {
this.setAttributeValue( KaipiaoqueryBVO.FPSJ,fpsj);
 } 

/** 
* ��ȡ��Ʊ��
*
* @return ��Ʊ��
*/
public String getKpr () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.KPR);
 } 

/** 
* ���ÿ�Ʊ��
*
* @param kpr ��Ʊ��
*/
public void setKpr ( String kpr) {
this.setAttributeValue( KaipiaoqueryBVO.KPR,kpr);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kaipiaoquery () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kaipiaoquery �ϲ㵥������
*/
public void setPk_hk_huiyuan_kaipiaoquery ( String pk_hk_huiyuan_kaipiaoquery) {
this.setAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY,pk_hk_huiyuan_kaipiaoquery);
 } 

/** 
* ��ȡ��Ա��Ʊ��ѯ��pk
*
* @return ��Ա��Ʊ��ѯ��pk
*/
public String getPk_hk_huiyuan_kaipiaoquery_b () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY_B);
 } 

/** 
* ���û�Ա��Ʊ��ѯ��pk
*
* @param pk_hk_huiyuan_kaipiaoquery_b ��Ա��Ʊ��ѯ��pk
*/
public void setPk_hk_huiyuan_kaipiaoquery_b ( String pk_hk_huiyuan_kaipiaoquery_b) {
this.setAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY_B,pk_hk_huiyuan_kaipiaoquery_b);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KaipiaoqueryBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KaipiaoqueryBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KaipiaoqueryBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KaipiaoqueryBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KaipiaoqueryBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KaipiaoqueryBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KaipiaoqueryBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KaipiaoqueryBVO");
  }
}