package nc.vo.hkjt.huiyuan.kainfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KainfoBVO extends SuperVO {
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
*�����
*/
public static final String KA_JE="ka_je";
/**
*��name
*/
public static final String KA_NAME="ka_name";
/**
*��pk
*/
public static final String KA_PK="ka_pk";
/**
*ʵ�ս��
*/
public static final String KA_SS="ka_ss";
/**
*�����
*/
public static final String KA_YUE="ka_yue";
/**
*���ͽ��
*/
public static final String KA_ZS="ka_zs";
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
public static final String PK_HK_HUIYUAN_KAINFO="pk_hk_huiyuan_kainfo";
/**
*��Ա��Ϣ�ӱ�����
*/
public static final String PK_HK_HUIYUAN_KAINFO_B="pk_hk_huiyuan_kainfo_b";
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
*��Ŀ����
*/
public static final String XMDL="xmdl";
/**
*���߿�code
*/
public static final String XMKA_CODE="xmka_code";
/**
*���߿�pk
*/
public static final String XMKA_PK="xmka_pk";
/**
*��Ŀ����
*/
public static final String XMLX="xmlx";
/**
*Դ��code
*/
public static final String Y_KA_CODE="y_ka_code";
/**
*Դ�����
*/
public static final String Y_KA_JE="y_ka_je";
/**
*Դ��name
*/
public static final String Y_KA_NAME="y_ka_name";
/**
*Դ��pk
*/
public static final String Y_KA_PK="y_ka_pk";
/**
*Դʵ�ս��
*/
public static final String Y_KA_SS="y_ka_ss";
/**
*Դ�����
*/
public static final String Y_KA_YUE="y_ka_yue";
/**
*Դ���ͽ��
*/
public static final String Y_KA_ZS="y_ka_zs";
/**
*Դ������
*/
public static final String Y_KABILI="y_kabili";
/**
*Դ����code
*/
public static final String Y_KAXING_CODE="y_kaxing_code";
/**
*Դ����name
*/
public static final String Y_KAXING_NAME="y_kaxing_name";
/**
*Դ����pk
*/
public static final String Y_KAXING_PK="y_kaxing_pk";
/**
*ҵ��ʱ��
*/
public static final String YWSJ="ywsj";
/**
*�˵���
*/
public static final String ZDH="zdh";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KainfoBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KainfoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KainfoBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KainfoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KainfoBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KainfoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KainfoBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KainfoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KainfoBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KainfoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KainfoBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KainfoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��code
*
* @return ��code
*/
public String getKa_code () {
return (String) this.getAttributeValue( KainfoBVO.KA_CODE);
 } 

/** 
* ���ÿ�code
*
* @param ka_code ��code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KainfoBVO.KA_CODE,ka_code);
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public UFDouble getKa_je () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_JE);
 } 

/** 
* ���ÿ����
*
* @param ka_je �����
*/
public void setKa_je ( UFDouble ka_je) {
this.setAttributeValue( KainfoBVO.KA_JE,ka_je);
 } 

/** 
* ��ȡ��name
*
* @return ��name
*/
public String getKa_name () {
return (String) this.getAttributeValue( KainfoBVO.KA_NAME);
 } 

/** 
* ���ÿ�name
*
* @param ka_name ��name
*/
public void setKa_name ( String ka_name) {
this.setAttributeValue( KainfoBVO.KA_NAME,ka_name);
 } 

/** 
* ��ȡ��pk
*
* @return ��pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( KainfoBVO.KA_PK);
 } 

/** 
* ���ÿ�pk
*
* @param ka_pk ��pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( KainfoBVO.KA_PK,ka_pk);
 } 

/** 
* ��ȡʵ�ս��
*
* @return ʵ�ս��
*/
public UFDouble getKa_ss () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_SS);
 } 

/** 
* ����ʵ�ս��
*
* @param ka_ss ʵ�ս��
*/
public void setKa_ss ( UFDouble ka_ss) {
this.setAttributeValue( KainfoBVO.KA_SS,ka_ss);
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public UFDouble getKa_yue () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_YUE);
 } 

/** 
* ���ÿ����
*
* @param ka_yue �����
*/
public void setKa_yue ( UFDouble ka_yue) {
this.setAttributeValue( KainfoBVO.KA_YUE,ka_yue);
 } 

/** 
* ��ȡ���ͽ��
*
* @return ���ͽ��
*/
public UFDouble getKa_zs () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_ZS);
 } 

/** 
* �������ͽ��
*
* @param ka_zs ���ͽ��
*/
public void setKa_zs ( UFDouble ka_zs) {
this.setAttributeValue( KainfoBVO.KA_ZS,ka_zs);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KABILI);
 } 

/** 
* ���ÿ�����
*
* @param kabili ������
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KainfoBVO.KABILI,kabili);
 } 

/** 
* ��ȡ����code
*
* @return ����code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KainfoBVO.KAXING_CODE);
 } 

/** 
* ���ÿ���code
*
* @param kaxing_code ����code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KainfoBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* ��ȡ����name
*
* @return ����name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KainfoBVO.KAXING_NAME);
 } 

/** 
* ���ÿ���name
*
* @param kaxing_name ����name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KainfoBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* ��ȡ����pk
*
* @return ����pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( KainfoBVO.KAXING_PK);
 } 

/** 
* ���ÿ���pk
*
* @param kaxing_pk ����pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( KainfoBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kainfo () {
return (String) this.getAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kainfo �ϲ㵥������
*/
public void setPk_hk_huiyuan_kainfo ( String pk_hk_huiyuan_kainfo) {
this.setAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO,pk_hk_huiyuan_kainfo);
 } 

/** 
* ��ȡ��Ա��Ϣ�ӱ�����
*
* @return ��Ա��Ϣ�ӱ�����
*/
public String getPk_hk_huiyuan_kainfo_b () {
return (String) this.getAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO_B);
 } 

/** 
* ���û�Ա��Ϣ�ӱ�����
*
* @param pk_hk_huiyuan_kainfo_b ��Ա��Ϣ�ӱ�����
*/
public void setPk_hk_huiyuan_kainfo_b ( String pk_hk_huiyuan_kainfo_b) {
this.setAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO_B,pk_hk_huiyuan_kainfo_b);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KainfoBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KainfoBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KainfoBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KainfoBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KainfoBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KainfoBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KainfoBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KainfoBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KainfoBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KainfoBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KainfoBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KainfoBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KainfoBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KainfoBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KainfoBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KainfoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KainfoBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KainfoBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KainfoBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KainfoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getXmdl () {
return (String) this.getAttributeValue( KainfoBVO.XMDL);
 } 

/** 
* ������Ŀ����
*
* @param xmdl ��Ŀ����
*/
public void setXmdl ( String xmdl) {
this.setAttributeValue( KainfoBVO.XMDL,xmdl);
 } 

/** 
* ��ȡ���߿�code
*
* @return ���߿�code
*/
public String getXmka_code () {
return (String) this.getAttributeValue( KainfoBVO.XMKA_CODE);
 } 

/** 
* �������߿�code
*
* @param xmka_code ���߿�code
*/
public void setXmka_code ( String xmka_code) {
this.setAttributeValue( KainfoBVO.XMKA_CODE,xmka_code);
 } 

/** 
* ��ȡ���߿�pk
*
* @return ���߿�pk
*/
public String getXmka_pk () {
return (String) this.getAttributeValue( KainfoBVO.XMKA_PK);
 } 

/** 
* �������߿�pk
*
* @param xmka_pk ���߿�pk
*/
public void setXmka_pk ( String xmka_pk) {
this.setAttributeValue( KainfoBVO.XMKA_PK,xmka_pk);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
* @see String
*/
public String getXmlx () {
return (String) this.getAttributeValue( KainfoBVO.XMLX);
 } 

/** 
* ������Ŀ����
*
* @param xmlx ��Ŀ����
* @see String
*/
public void setXmlx ( String xmlx) {
this.setAttributeValue( KainfoBVO.XMLX,xmlx);
 } 

/** 
* ��ȡԴ��code
*
* @return Դ��code
*/
public String getY_ka_code () {
return (String) this.getAttributeValue( KainfoBVO.Y_KA_CODE);
 } 

/** 
* ����Դ��code
*
* @param y_ka_code Դ��code
*/
public void setY_ka_code ( String y_ka_code) {
this.setAttributeValue( KainfoBVO.Y_KA_CODE,y_ka_code);
 } 

/** 
* ��ȡԴ�����
*
* @return Դ�����
*/
public UFDouble getY_ka_je () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_JE);
 } 

/** 
* ����Դ�����
*
* @param y_ka_je Դ�����
*/
public void setY_ka_je ( UFDouble y_ka_je) {
this.setAttributeValue( KainfoBVO.Y_KA_JE,y_ka_je);
 } 

/** 
* ��ȡԴ��name
*
* @return Դ��name
*/
public String getY_ka_name () {
return (String) this.getAttributeValue( KainfoBVO.Y_KA_NAME);
 } 

/** 
* ����Դ��name
*
* @param y_ka_name Դ��name
*/
public void setY_ka_name ( String y_ka_name) {
this.setAttributeValue( KainfoBVO.Y_KA_NAME,y_ka_name);
 } 

/** 
* ��ȡԴ��pk
*
* @return Դ��pk
*/
public String getY_ka_pk () {
return (String) this.getAttributeValue( KainfoBVO.Y_KA_PK);
 } 

/** 
* ����Դ��pk
*
* @param y_ka_pk Դ��pk
*/
public void setY_ka_pk ( String y_ka_pk) {
this.setAttributeValue( KainfoBVO.Y_KA_PK,y_ka_pk);
 } 

/** 
* ��ȡԴʵ�ս��
*
* @return Դʵ�ս��
*/
public UFDouble getY_ka_ss () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_SS);
 } 

/** 
* ����Դʵ�ս��
*
* @param y_ka_ss Դʵ�ս��
*/
public void setY_ka_ss ( UFDouble y_ka_ss) {
this.setAttributeValue( KainfoBVO.Y_KA_SS,y_ka_ss);
 } 

/** 
* ��ȡԴ�����
*
* @return Դ�����
*/
public UFDouble getY_ka_yue () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_YUE);
 } 

/** 
* ����Դ�����
*
* @param y_ka_yue Դ�����
*/
public void setY_ka_yue ( UFDouble y_ka_yue) {
this.setAttributeValue( KainfoBVO.Y_KA_YUE,y_ka_yue);
 } 

/** 
* ��ȡԴ���ͽ��
*
* @return Դ���ͽ��
*/
public UFDouble getY_ka_zs () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_ZS);
 } 

/** 
* ����Դ���ͽ��
*
* @param y_ka_zs Դ���ͽ��
*/
public void setY_ka_zs ( UFDouble y_ka_zs) {
this.setAttributeValue( KainfoBVO.Y_KA_ZS,y_ka_zs);
 } 

/** 
* ��ȡԴ������
*
* @return Դ������
*/
public UFDouble getY_kabili () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KABILI);
 } 

/** 
* ����Դ������
*
* @param y_kabili Դ������
*/
public void setY_kabili ( UFDouble y_kabili) {
this.setAttributeValue( KainfoBVO.Y_KABILI,y_kabili);
 } 

/** 
* ��ȡԴ����code
*
* @return Դ����code
*/
public String getY_kaxing_code () {
return (String) this.getAttributeValue( KainfoBVO.Y_KAXING_CODE);
 } 

/** 
* ����Դ����code
*
* @param y_kaxing_code Դ����code
*/
public void setY_kaxing_code ( String y_kaxing_code) {
this.setAttributeValue( KainfoBVO.Y_KAXING_CODE,y_kaxing_code);
 } 

/** 
* ��ȡԴ����name
*
* @return Դ����name
*/
public String getY_kaxing_name () {
return (String) this.getAttributeValue( KainfoBVO.Y_KAXING_NAME);
 } 

/** 
* ����Դ����name
*
* @param y_kaxing_name Դ����name
*/
public void setY_kaxing_name ( String y_kaxing_name) {
this.setAttributeValue( KainfoBVO.Y_KAXING_NAME,y_kaxing_name);
 } 

/** 
* ��ȡԴ����pk
*
* @return Դ����pk
*/
public String getY_kaxing_pk () {
return (String) this.getAttributeValue( KainfoBVO.Y_KAXING_PK);
 } 

/** 
* ����Դ����pk
*
* @param y_kaxing_pk Դ����pk
*/
public void setY_kaxing_pk ( String y_kaxing_pk) {
this.setAttributeValue( KainfoBVO.Y_KAXING_PK,y_kaxing_pk);
 } 

/** 
* ��ȡҵ��ʱ��
*
* @return ҵ��ʱ��
*/
public UFDateTime getYwsj () {
return (UFDateTime) this.getAttributeValue( KainfoBVO.YWSJ);
 } 

/** 
* ����ҵ��ʱ��
*
* @param ywsj ҵ��ʱ��
*/
public void setYwsj ( UFDateTime ywsj) {
this.setAttributeValue( KainfoBVO.YWSJ,ywsj);
 } 

/** 
* ��ȡ�˵���
*
* @return �˵���
*/
public String getZdh () {
return (String) this.getAttributeValue( KainfoBVO.ZDH);
 } 

/** 
* �����˵���
*
* @param zdh �˵���
*/
public void setZdh ( String zdh) {
this.setAttributeValue( KainfoBVO.ZDH,zdh);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KainfoBVO");
  }
}