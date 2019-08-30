package nc.vo.hkjt.huiyuan.cikainfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CikainfoBVO extends SuperVO {

	private static final long serialVersionUID = 2503513783647316032L;
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
*����ˮ��
*/
public static final String CONSUMEWATERNUM="consumewaternum";
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
*��ֵ����
*/
public static final String CZLX="czlx";
/**
*����
*/
public static final String DANJIA="danjia";
/**
*�ο���������
*/
public static final String EXPDATA="expdata";
/**
*��Ŀ����
*/
public static final String ITEMID="itemid";
/**
*��Ŀ����
*/
public static final String ITEMNAME="itemname";
/**
*���
*/
public static final String JINE="jine";
/**
*��code
*/
public static final String KA_CODE="ka_code";
/**
*��name
*/
public static final String KA_NAME="ka_name";
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
public static final String PK_HK_HUIYUAN_CIKAINFO="pk_hk_huiyuan_cikainfo";
/**
*�ο���Ϣ�ӱ�pk
*/
public static final String PK_HK_HUIYUAN_CIKAINFO_B="pk_hk_huiyuan_cikainfo_b";
/**
*����
*/
public static final String SHULIANG="shuliang";
/**
*�ο���ʼ����
*/
public static final String STARTDATA="startdata";
/**
*�ο�ˮ��
*/
public static final String TIMESCARDWATERNUM="timescardwaternum";
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
*��Ŀ����
*/
public static final String XMLX="xmlx";
/**
*Դ��code
*/
public static final String Y_KA_CODE="y_ka_code";
/**
*Դ��name
*/
public static final String Y_KA_NAME="y_ka_name";
/**
*Դ��pk
*/
public static final String Y_KA_PK="y_ka_pk";
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
return (String) this.getAttributeValue( CikainfoBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CikainfoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CikainfoBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CikainfoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CikainfoBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CikainfoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ����ˮ��
*
* @return ����ˮ��
*/
public String getConsumewaternum () {
return (String) this.getAttributeValue( CikainfoBVO.CONSUMEWATERNUM);
 } 

/** 
* ��������ˮ��
*
* @param consumewaternum ����ˮ��
*/
public void setConsumewaternum ( String consumewaternum) {
this.setAttributeValue( CikainfoBVO.CONSUMEWATERNUM,consumewaternum);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CikainfoBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CikainfoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CikainfoBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CikainfoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CikainfoBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CikainfoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��ֵ����
*
* @return ��ֵ����
*/
public String getCzlx () {
return (String) this.getAttributeValue( CikainfoBVO.CZLX);
 } 

/** 
* ���ó�ֵ����
*
* @param czlx ��ֵ����
*/
public void setCzlx ( String czlx) {
this.setAttributeValue( CikainfoBVO.CZLX,czlx);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getDanjia () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.DANJIA);
 } 

/** 
* ���õ���
*
* @param danjia ����
*/
public void setDanjia ( UFDouble danjia) {
this.setAttributeValue( CikainfoBVO.DANJIA,danjia);
 } 

/** 
* ��ȡ�ο���������
*
* @return �ο���������
*/
public String getExpdata () {
return (String) this.getAttributeValue( CikainfoBVO.EXPDATA);
 } 

/** 
* ���ôο���������
*
* @param expdata �ο���������
*/
public void setExpdata ( String expdata) {
this.setAttributeValue( CikainfoBVO.EXPDATA,expdata);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemid () {
return (String) this.getAttributeValue( CikainfoBVO.ITEMID);
 } 

/** 
* ������Ŀ����
*
* @param itemid ��Ŀ����
*/
public void setItemid ( String itemid) {
this.setAttributeValue( CikainfoBVO.ITEMID,itemid);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemname () {
return (String) this.getAttributeValue( CikainfoBVO.ITEMNAME);
 } 

/** 
* ������Ŀ����
*
* @param itemname ��Ŀ����
*/
public void setItemname ( String itemname) {
this.setAttributeValue( CikainfoBVO.ITEMNAME,itemname);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getJine () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.JINE);
 } 

/** 
* ���ý��
*
* @param jine ���
*/
public void setJine ( UFDouble jine) {
this.setAttributeValue( CikainfoBVO.JINE,jine);
 } 

/** 
* ��ȡ��code
*
* @return ��code
*/
public String getKa_code () {
return (String) this.getAttributeValue( CikainfoBVO.KA_CODE);
 } 

/** 
* ���ÿ�code
*
* @param ka_code ��code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( CikainfoBVO.KA_CODE,ka_code);
 } 

/** 
* ��ȡ��name
*
* @return ��name
*/
public String getKa_name () {
return (String) this.getAttributeValue( CikainfoBVO.KA_NAME);
 } 

/** 
* ���ÿ�name
*
* @param ka_name ��name
*/
public void setKa_name ( String ka_name) {
this.setAttributeValue( CikainfoBVO.KA_NAME,ka_name);
 } 

/** 
* ��ȡ��pk
*
* @return ��pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( CikainfoBVO.KA_PK);
 } 

/** 
* ���ÿ�pk
*
* @param ka_pk ��pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( CikainfoBVO.KA_PK,ka_pk);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.KABILI);
 } 

/** 
* ���ÿ�����
*
* @param kabili ������
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( CikainfoBVO.KABILI,kabili);
 } 

/** 
* ��ȡ����code
*
* @return ����code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( CikainfoBVO.KAXING_CODE);
 } 

/** 
* ���ÿ���code
*
* @param kaxing_code ����code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( CikainfoBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* ��ȡ����name
*
* @return ����name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( CikainfoBVO.KAXING_NAME);
 } 

/** 
* ���ÿ���name
*
* @param kaxing_name ����name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( CikainfoBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* ��ȡ����pk
*
* @return ����pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( CikainfoBVO.KAXING_PK);
 } 

/** 
* ���ÿ���pk
*
* @param kaxing_pk ����pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( CikainfoBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_cikainfo () {
return (String) this.getAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_cikainfo �ϲ㵥������
*/
public void setPk_hk_huiyuan_cikainfo ( String pk_hk_huiyuan_cikainfo) {
this.setAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO,pk_hk_huiyuan_cikainfo);
 } 

/** 
* ��ȡ�ο���Ϣ�ӱ�pk
*
* @return �ο���Ϣ�ӱ�pk
*/
public String getPk_hk_huiyuan_cikainfo_b () {
return (String) this.getAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO_B);
 } 

/** 
* ���ôο���Ϣ�ӱ�pk
*
* @param pk_hk_huiyuan_cikainfo_b �ο���Ϣ�ӱ�pk
*/
public void setPk_hk_huiyuan_cikainfo_b ( String pk_hk_huiyuan_cikainfo_b) {
this.setAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO_B,pk_hk_huiyuan_cikainfo_b);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getShuliang () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.SHULIANG);
 } 

/** 
* ��������
*
* @param shuliang ����
*/
public void setShuliang ( UFDouble shuliang) {
this.setAttributeValue( CikainfoBVO.SHULIANG,shuliang);
 } 

/** 
* ��ȡ�ο���ʼ����
*
* @return �ο���ʼ����
*/
public String getStartdata () {
return (String) this.getAttributeValue( CikainfoBVO.STARTDATA);
 } 

/** 
* ���ôο���ʼ����
*
* @param startdata �ο���ʼ����
*/
public void setStartdata ( String startdata) {
this.setAttributeValue( CikainfoBVO.STARTDATA,startdata);
 } 

/** 
* ��ȡ�ο�ˮ��
*
* @return �ο�ˮ��
*/
public String getTimescardwaternum () {
return (String) this.getAttributeValue( CikainfoBVO.TIMESCARDWATERNUM);
 } 

/** 
* ���ôο�ˮ��
*
* @param timescardwaternum �ο�ˮ��
*/
public void setTimescardwaternum ( String timescardwaternum) {
this.setAttributeValue( CikainfoBVO.TIMESCARDWATERNUM,timescardwaternum);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CikainfoBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CikainfoBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CikainfoBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CikainfoBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CikainfoBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CikainfoBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CikainfoBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CikainfoBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CikainfoBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CikainfoBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CikainfoBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CikainfoBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CikainfoBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CikainfoBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CikainfoBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CikainfoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( CikainfoBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( CikainfoBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CikainfoBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CikainfoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getXmdl () {
return (String) this.getAttributeValue( CikainfoBVO.XMDL);
 } 

/** 
* ������Ŀ����
*
* @param xmdl ��Ŀ����
*/
public void setXmdl ( String xmdl) {
this.setAttributeValue( CikainfoBVO.XMDL,xmdl);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
* @see String
*/
public String getXmlx () {
return (String) this.getAttributeValue( CikainfoBVO.XMLX);
 } 

/** 
* ������Ŀ����
*
* @param xmlx ��Ŀ����
* @see String
*/
public void setXmlx ( String xmlx) {
this.setAttributeValue( CikainfoBVO.XMLX,xmlx);
 } 

/** 
* ��ȡԴ��code
*
* @return Դ��code
*/
public String getY_ka_code () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KA_CODE);
 } 

/** 
* ����Դ��code
*
* @param y_ka_code Դ��code
*/
public void setY_ka_code ( String y_ka_code) {
this.setAttributeValue( CikainfoBVO.Y_KA_CODE,y_ka_code);
 } 

/** 
* ��ȡԴ��name
*
* @return Դ��name
*/
public String getY_ka_name () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KA_NAME);
 } 

/** 
* ����Դ��name
*
* @param y_ka_name Դ��name
*/
public void setY_ka_name ( String y_ka_name) {
this.setAttributeValue( CikainfoBVO.Y_KA_NAME,y_ka_name);
 } 

/** 
* ��ȡԴ��pk
*
* @return Դ��pk
*/
public String getY_ka_pk () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KA_PK);
 } 

/** 
* ����Դ��pk
*
* @param y_ka_pk Դ��pk
*/
public void setY_ka_pk ( String y_ka_pk) {
this.setAttributeValue( CikainfoBVO.Y_KA_PK,y_ka_pk);
 } 

/** 
* ��ȡԴ������
*
* @return Դ������
*/
public UFDouble getY_kabili () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.Y_KABILI);
 } 

/** 
* ����Դ������
*
* @param y_kabili Դ������
*/
public void setY_kabili ( UFDouble y_kabili) {
this.setAttributeValue( CikainfoBVO.Y_KABILI,y_kabili);
 } 

/** 
* ��ȡԴ����code
*
* @return Դ����code
*/
public String getY_kaxing_code () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KAXING_CODE);
 } 

/** 
* ����Դ����code
*
* @param y_kaxing_code Դ����code
*/
public void setY_kaxing_code ( String y_kaxing_code) {
this.setAttributeValue( CikainfoBVO.Y_KAXING_CODE,y_kaxing_code);
 } 

/** 
* ��ȡԴ����name
*
* @return Դ����name
*/
public String getY_kaxing_name () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KAXING_NAME);
 } 

/** 
* ����Դ����name
*
* @param y_kaxing_name Դ����name
*/
public void setY_kaxing_name ( String y_kaxing_name) {
this.setAttributeValue( CikainfoBVO.Y_KAXING_NAME,y_kaxing_name);
 } 

/** 
* ��ȡԴ����pk
*
* @return Դ����pk
*/
public String getY_kaxing_pk () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KAXING_PK);
 } 

/** 
* ����Դ����pk
*
* @param y_kaxing_pk Դ����pk
*/
public void setY_kaxing_pk ( String y_kaxing_pk) {
this.setAttributeValue( CikainfoBVO.Y_KAXING_PK,y_kaxing_pk);
 } 

/** 
* ��ȡҵ��ʱ��
*
* @return ҵ��ʱ��
*/
public UFDateTime getYwsj () {
return (UFDateTime) this.getAttributeValue( CikainfoBVO.YWSJ);
 } 

/** 
* ����ҵ��ʱ��
*
* @param ywsj ҵ��ʱ��
*/
public void setYwsj ( UFDateTime ywsj) {
this.setAttributeValue( CikainfoBVO.YWSJ,ywsj);
 } 

/** 
* ��ȡ�˵���
*
* @return �˵���
*/
public String getZdh () {
return (String) this.getAttributeValue( CikainfoBVO.ZDH);
 } 

/** 
* �����˵���
*
* @param zdh �˵���
*/
public void setZdh ( String zdh) {
this.setAttributeValue( CikainfoBVO.ZDH,zdh);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_CikainfoBVO");
  }
}