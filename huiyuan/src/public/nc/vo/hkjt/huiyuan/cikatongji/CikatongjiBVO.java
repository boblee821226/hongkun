package nc.vo.hkjt.huiyuan.cikatongji;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CikatongjiBVO extends SuperVO {
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
*���
*/
public static final String CHA_SL="cha_sl";
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
*��ֵ���
*/
public static final String CZ_JE="cz_je";
/**
*��ֵ����
*/
public static final String CZ_SL="cz_sl";
/**
*����
*/
public static final String DJ="dj";
/**
*��Ŀ����
*/
public static final String ITEMID="itemid";
/**
*��Ŀ����
*/
public static final String ITEMNAME="itemname";
/**
*��code
*/
public static final String KA_CODE="ka_code";
/**
*��pk
*/
public static final String KA_PK="ka_pk";
/**
*������
*/
public static final String KABILI="kabili";
/**
*����code
*/
public static final String KAXING_CODE="kaxing_code";
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
public static final String PK_HK_HUIYUAN_CIKATONGJI="pk_hk_huiyuan_cikatongji";
/**
*�ο�ͳ����pk
*/
public static final String PK_HK_HUIYUAN_CIKATONGJI_B="pk_hk_huiyuan_cikatongji_b";
/**
*�ڳ����
*/
public static final String QC_JE="qc_je";
/**
*�ڳ�����
*/
public static final String QC_SL="qc_sl";
/**
*�ο���ʼ����
*/
public static final String STARTDATA="startdata";
/**
*�������
*/
public static final String TJ_JE="tj_je";
/**
*��������
*/
public static final String TJ_SL="tj_sl";
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
*���ѽ��
*/
public static final String XF_JE="xf_je";
/**
*��������
*/
public static final String XF_SL="xf_sl";
/**
*���
*/
public static final String YUE_JE="yue_je";
/**
*����
*/
public static final String YUE_SL="yue_sl";
/**
*ת�����
*/
public static final String ZC_JE="zc_je";
/**
*ת������
*/
public static final String ZC_SL="zc_sl";
/**
*ת����
*/
public static final String ZR_JE="zr_je";
/**
*ת������
*/
public static final String ZR_SL="zr_sl";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( CikatongjiBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CikatongjiBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CikatongjiBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CikatongjiBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CikatongjiBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CikatongjiBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getCha_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.CHA_SL);
 } 

/** 
* ���ò��
*
* @param cha_sl ���
*/
public void setCha_sl ( UFDouble cha_sl) {
this.setAttributeValue( CikatongjiBVO.CHA_SL,cha_sl);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CikatongjiBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CikatongjiBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CikatongjiBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CikatongjiBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CikatongjiBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CikatongjiBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��ֵ���
*
* @return ��ֵ���
*/
public UFDouble getCz_je () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.CZ_JE);
 } 

/** 
* ���ó�ֵ���
*
* @param cz_je ��ֵ���
*/
public void setCz_je ( UFDouble cz_je) {
this.setAttributeValue( CikatongjiBVO.CZ_JE,cz_je);
 } 

/** 
* ��ȡ��ֵ����
*
* @return ��ֵ����
*/
public UFDouble getCz_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.CZ_SL);
 } 

/** 
* ���ó�ֵ����
*
* @param cz_sl ��ֵ����
*/
public void setCz_sl ( UFDouble cz_sl) {
this.setAttributeValue( CikatongjiBVO.CZ_SL,cz_sl);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getDj () {
return (String) this.getAttributeValue( CikatongjiBVO.DJ);
 } 

/** 
* ���õ���
*
* @param dj ����
*/
public void setDj ( String dj) {
this.setAttributeValue( CikatongjiBVO.DJ,dj);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemid () {
return (String) this.getAttributeValue( CikatongjiBVO.ITEMID);
 } 

/** 
* ������Ŀ����
*
* @param itemid ��Ŀ����
*/
public void setItemid ( String itemid) {
this.setAttributeValue( CikatongjiBVO.ITEMID,itemid);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemname () {
return (String) this.getAttributeValue( CikatongjiBVO.ITEMNAME);
 } 

/** 
* ������Ŀ����
*
* @param itemname ��Ŀ����
*/
public void setItemname ( String itemname) {
this.setAttributeValue( CikatongjiBVO.ITEMNAME,itemname);
 } 

/** 
* ��ȡ��code
*
* @return ��code
*/
public String getKa_code () {
return (String) this.getAttributeValue( CikatongjiBVO.KA_CODE);
 } 

/** 
* ���ÿ�code
*
* @param ka_code ��code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( CikatongjiBVO.KA_CODE,ka_code);
 } 

/** 
* ��ȡ��pk
*
* @return ��pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( CikatongjiBVO.KA_PK);
 } 

/** 
* ���ÿ�pk
*
* @param ka_pk ��pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( CikatongjiBVO.KA_PK,ka_pk);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getKabili () {
return (String) this.getAttributeValue( CikatongjiBVO.KABILI);
 } 

/** 
* ���ÿ�����
*
* @param kabili ������
*/
public void setKabili ( String kabili) {
this.setAttributeValue( CikatongjiBVO.KABILI,kabili);
 } 

/** 
* ��ȡ����code
*
* @return ����code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( CikatongjiBVO.KAXING_CODE);
 } 

/** 
* ���ÿ���code
*
* @param kaxing_code ����code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( CikatongjiBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* ��ȡ����name
*
* @return ����name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( CikatongjiBVO.KAXING_NAME);
 } 

/** 
* ���ÿ���name
*
* @param kaxing_name ����name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( CikatongjiBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* ��ȡ����pk
*
* @return ����pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( CikatongjiBVO.KAXING_PK);
 } 

/** 
* ���ÿ���pk
*
* @param kaxing_pk ����pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( CikatongjiBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_cikatongji () {
return (String) this.getAttributeValue( CikatongjiBVO.PK_HK_HUIYUAN_CIKATONGJI);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_cikatongji �ϲ㵥������
*/
public void setPk_hk_huiyuan_cikatongji ( String pk_hk_huiyuan_cikatongji) {
this.setAttributeValue( CikatongjiBVO.PK_HK_HUIYUAN_CIKATONGJI,pk_hk_huiyuan_cikatongji);
 } 

/** 
* ��ȡ�ο�ͳ����pk
*
* @return �ο�ͳ����pk
*/
public String getPk_hk_huiyuan_cikatongji_b () {
return (String) this.getAttributeValue( CikatongjiBVO.PK_HK_HUIYUAN_CIKATONGJI_B);
 } 

/** 
* ���ôο�ͳ����pk
*
* @param pk_hk_huiyuan_cikatongji_b �ο�ͳ����pk
*/
public void setPk_hk_huiyuan_cikatongji_b ( String pk_hk_huiyuan_cikatongji_b) {
this.setAttributeValue( CikatongjiBVO.PK_HK_HUIYUAN_CIKATONGJI_B,pk_hk_huiyuan_cikatongji_b);
 } 

/** 
* ��ȡ�ڳ����
*
* @return �ڳ����
*/
public UFDouble getQc_je () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.QC_JE);
 } 

/** 
* �����ڳ����
*
* @param qc_je �ڳ����
*/
public void setQc_je ( UFDouble qc_je) {
this.setAttributeValue( CikatongjiBVO.QC_JE,qc_je);
 } 

/** 
* ��ȡ�ڳ�����
*
* @return �ڳ�����
*/
public UFDouble getQc_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.QC_SL);
 } 

/** 
* �����ڳ�����
*
* @param qc_sl �ڳ�����
*/
public void setQc_sl ( UFDouble qc_sl) {
this.setAttributeValue( CikatongjiBVO.QC_SL,qc_sl);
 } 

/** 
* ��ȡ�ο���ʼ����
*
* @return �ο���ʼ����
*/
public String getStartdata () {
return (String) this.getAttributeValue( CikatongjiBVO.STARTDATA);
 } 

/** 
* ���ôο���ʼ����
*
* @param startdata �ο���ʼ����
*/
public void setStartdata ( String startdata) {
this.setAttributeValue( CikatongjiBVO.STARTDATA,startdata);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getTj_je () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.TJ_JE);
 } 

/** 
* ���õ������
*
* @param tj_je �������
*/
public void setTj_je ( UFDouble tj_je) {
this.setAttributeValue( CikatongjiBVO.TJ_JE,tj_je);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getTj_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.TJ_SL);
 } 

/** 
* ���õ�������
*
* @param tj_sl ��������
*/
public void setTj_sl ( UFDouble tj_sl) {
this.setAttributeValue( CikatongjiBVO.TJ_SL,tj_sl);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CikatongjiBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CikatongjiBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CikatongjiBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CikatongjiBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CikatongjiBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CikatongjiBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CikatongjiBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CikatongjiBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CikatongjiBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CikatongjiBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CikatongjiBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CikatongjiBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CikatongjiBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CikatongjiBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CikatongjiBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CikatongjiBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CikatongjiBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( CikatongjiBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( CikatongjiBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CikatongjiBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CikatongjiBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ���ѽ��
*
* @return ���ѽ��
*/
public UFDouble getXf_je () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.XF_JE);
 } 

/** 
* �������ѽ��
*
* @param xf_je ���ѽ��
*/
public void setXf_je ( UFDouble xf_je) {
this.setAttributeValue( CikatongjiBVO.XF_JE,xf_je);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getXf_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.XF_SL);
 } 

/** 
* ������������
*
* @param xf_sl ��������
*/
public void setXf_sl ( UFDouble xf_sl) {
this.setAttributeValue( CikatongjiBVO.XF_SL,xf_sl);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getYue_je () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.YUE_JE);
 } 

/** 
* �������
*
* @param yue_je ���
*/
public void setYue_je ( UFDouble yue_je) {
this.setAttributeValue( CikatongjiBVO.YUE_JE,yue_je);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getYue_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.YUE_SL);
 } 

/** 
* ��������
*
* @param yue_sl ����
*/
public void setYue_sl ( UFDouble yue_sl) {
this.setAttributeValue( CikatongjiBVO.YUE_SL,yue_sl);
 } 

/** 
* ��ȡת�����
*
* @return ת�����
*/
public UFDouble getZc_je () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.ZC_JE);
 } 

/** 
* ����ת�����
*
* @param zc_je ת�����
*/
public void setZc_je ( UFDouble zc_je) {
this.setAttributeValue( CikatongjiBVO.ZC_JE,zc_je);
 } 

/** 
* ��ȡת������
*
* @return ת������
*/
public UFDouble getZc_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.ZC_SL);
 } 

/** 
* ����ת������
*
* @param zc_sl ת������
*/
public void setZc_sl ( UFDouble zc_sl) {
this.setAttributeValue( CikatongjiBVO.ZC_SL,zc_sl);
 } 

/** 
* ��ȡת����
*
* @return ת����
*/
public UFDouble getZr_je () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.ZR_JE);
 } 

/** 
* ����ת����
*
* @param zr_je ת����
*/
public void setZr_je ( UFDouble zr_je) {
this.setAttributeValue( CikatongjiBVO.ZR_JE,zr_je);
 } 

/** 
* ��ȡת������
*
* @return ת������
*/
public UFDouble getZr_sl () {
return (UFDouble) this.getAttributeValue( CikatongjiBVO.ZR_SL);
 } 

/** 
* ����ת������
*
* @param zr_sl ת������
*/
public void setZr_sl ( UFDouble zr_sl) {
this.setAttributeValue( CikatongjiBVO.ZR_SL,zr_sl);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_CikatongjiBVO");
  }
}