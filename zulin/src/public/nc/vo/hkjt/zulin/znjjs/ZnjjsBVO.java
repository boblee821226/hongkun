package nc.vo.hkjt.zulin.znjjs;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ZnjjsBVO extends SuperVO {
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
*��ͬ��pk
*/
public static final String HT_BID="ht_bid";
/**
*��ͬ��
*/
public static final String HT_CODE="ht_code";
/**
*��ͬpk
*/
public static final String HT_ID="ht_id";
/**
*��ͬ�к�
*/
public static final String HT_ROWNO="ht_rowno";
/**
*Ӧ�ɷ�����
*/
public static final String JF_DATE="jf_date";
/**
*Ӧ�ɷѽ��
*/
public static final String JF_MNY="jf_mny";
/**
*�ɷ�֪ͨ����pk
*/
public static final String JFTZD_BID="jftzd_bid";
/**
*�ɷ�֪ͨ��pk
*/
public static final String JFTZD_ID="jftzd_id";
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
public static final String PK_HK_ZULIN_ZNJJS="pk_hk_zulin_znjjs";
/**
*���ɽ���㵥��pk
*/
public static final String PK_HK_ZULIN_ZNJJS_B="pk_hk_zulin_znjjs_b";
/**
*����
*/
public static final String PK_ROOM="pk_room";
/**
*�շ���Ŀ
*/
public static final String PK_SFXM="pk_sfxm";
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
*�������룩
*/
public static final String YQ_BL="yq_bl";
/**
*���ɽ�
*/
public static final String YQ_MNY="yq_mny";
/**
*��������
*/
public static final String YQ_NUM="yq_num";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( ZnjjsBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( ZnjjsBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( ZnjjsBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( ZnjjsBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( ZnjjsBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( ZnjjsBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( ZnjjsBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( ZnjjsBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( ZnjjsBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( ZnjjsBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( ZnjjsBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( ZnjjsBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��ͬ��pk
*
* @return ��ͬ��pk
*/
public String getHt_bid () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_BID);
 } 

/** 
* ���ú�ͬ��pk
*
* @param ht_bid ��ͬ��pk
*/
public void setHt_bid ( String ht_bid) {
this.setAttributeValue( ZnjjsBVO.HT_BID,ht_bid);
 } 

/** 
* ��ȡ��ͬ��
*
* @return ��ͬ��
*/
public String getHt_code () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_CODE);
 } 

/** 
* ���ú�ͬ��
*
* @param ht_code ��ͬ��
*/
public void setHt_code ( String ht_code) {
this.setAttributeValue( ZnjjsBVO.HT_CODE,ht_code);
 } 

/** 
* ��ȡ��ͬpk
*
* @return ��ͬpk
*/
public String getHt_id () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_ID);
 } 

/** 
* ���ú�ͬpk
*
* @param ht_id ��ͬpk
*/
public void setHt_id ( String ht_id) {
this.setAttributeValue( ZnjjsBVO.HT_ID,ht_id);
 } 

/** 
* ��ȡ��ͬ�к�
*
* @return ��ͬ�к�
*/
public String getHt_rowno () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_ROWNO);
 } 

/** 
* ���ú�ͬ�к�
*
* @param ht_rowno ��ͬ�к�
*/
public void setHt_rowno ( String ht_rowno) {
this.setAttributeValue( ZnjjsBVO.HT_ROWNO,ht_rowno);
 } 

/** 
* ��ȡӦ�ɷ�����
*
* @return Ӧ�ɷ�����
*/
public UFDate getJf_date () {
return (UFDate) this.getAttributeValue( ZnjjsBVO.JF_DATE);
 } 

/** 
* ����Ӧ�ɷ�����
*
* @param jf_date Ӧ�ɷ�����
*/
public void setJf_date ( UFDate jf_date) {
this.setAttributeValue( ZnjjsBVO.JF_DATE,jf_date);
 } 

/** 
* ��ȡӦ�ɷѽ��
*
* @return Ӧ�ɷѽ��
*/
public UFDouble getJf_mny () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.JF_MNY);
 } 

/** 
* ����Ӧ�ɷѽ��
*
* @param jf_mny Ӧ�ɷѽ��
*/
public void setJf_mny ( UFDouble jf_mny) {
this.setAttributeValue( ZnjjsBVO.JF_MNY,jf_mny);
 } 

/** 
* ��ȡ�ɷ�֪ͨ����pk
*
* @return �ɷ�֪ͨ����pk
*/
public String getJftzd_bid () {
return (String) this.getAttributeValue( ZnjjsBVO.JFTZD_BID);
 } 

/** 
* ���ýɷ�֪ͨ����pk
*
* @param jftzd_bid �ɷ�֪ͨ����pk
*/
public void setJftzd_bid ( String jftzd_bid) {
this.setAttributeValue( ZnjjsBVO.JFTZD_BID,jftzd_bid);
 } 

/** 
* ��ȡ�ɷ�֪ͨ��pk
*
* @return �ɷ�֪ͨ��pk
*/
public String getJftzd_id () {
return (String) this.getAttributeValue( ZnjjsBVO.JFTZD_ID);
 } 

/** 
* ���ýɷ�֪ͨ��pk
*
* @param jftzd_id �ɷ�֪ͨ��pk
*/
public void setJftzd_id ( String jftzd_id) {
this.setAttributeValue( ZnjjsBVO.JFTZD_ID,jftzd_id);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_area () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_AREA);
 } 

/** 
* ��������
*
* @param pk_area ����
*/
public void setPk_area ( String pk_area) {
this.setAttributeValue( ZnjjsBVO.PK_AREA,pk_area);
 } 

/** 
* ��ȡ�ͻ�
*
* @return �ͻ�
*/
public String getPk_cust () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_CUST);
 } 

/** 
* ���ÿͻ�
*
* @param pk_cust �ͻ�
*/
public void setPk_cust ( String pk_cust) {
this.setAttributeValue( ZnjjsBVO.PK_CUST,pk_cust);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_zulin_znjjs () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_zulin_znjjs �ϲ㵥������
*/
public void setPk_hk_zulin_znjjs ( String pk_hk_zulin_znjjs) {
this.setAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS,pk_hk_zulin_znjjs);
 } 

/** 
* ��ȡ���ɽ���㵥��pk
*
* @return ���ɽ���㵥��pk
*/
public String getPk_hk_zulin_znjjs_b () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS_B);
 } 

/** 
* �������ɽ���㵥��pk
*
* @param pk_hk_zulin_znjjs_b ���ɽ���㵥��pk
*/
public void setPk_hk_zulin_znjjs_b ( String pk_hk_zulin_znjjs_b) {
this.setAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS_B,pk_hk_zulin_znjjs_b);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_room () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_ROOM);
 } 

/** 
* ���÷���
*
* @param pk_room ����
*/
public void setPk_room ( String pk_room) {
this.setAttributeValue( ZnjjsBVO.PK_ROOM,pk_room);
 } 

/** 
* ��ȡ�շ���Ŀ
*
* @return �շ���Ŀ
*/
public String getPk_sfxm () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_SFXM);
 } 

/** 
* �����շ���Ŀ
*
* @param pk_sfxm �շ���Ŀ
*/
public void setPk_sfxm ( String pk_sfxm) {
this.setAttributeValue( ZnjjsBVO.PK_SFXM,pk_sfxm);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( ZnjjsBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( ZnjjsBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( ZnjjsBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( ZnjjsBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( ZnjjsBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( ZnjjsBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( ZnjjsBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( ZnjjsBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( ZnjjsBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( ZnjjsBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( ZnjjsBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( ZnjjsBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vbdef11 �Զ�����11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( ZnjjsBVO.VBDEF11,vbdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vbdef12 �Զ�����12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( ZnjjsBVO.VBDEF12,vbdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vbdef13 �Զ�����13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( ZnjjsBVO.VBDEF13,vbdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vbdef14 �Զ�����14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( ZnjjsBVO.VBDEF14,vbdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vbdef15 �Զ�����15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( ZnjjsBVO.VBDEF15,vbdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vbdef16 �Զ�����16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( ZnjjsBVO.VBDEF16,vbdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vbdef17 �Զ�����17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( ZnjjsBVO.VBDEF17,vbdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vbdef18 �Զ�����18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( ZnjjsBVO.VBDEF18,vbdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vbdef19 �Զ�����19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( ZnjjsBVO.VBDEF19,vbdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vbdef20 �Զ�����20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( ZnjjsBVO.VBDEF20,vbdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( ZnjjsBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( ZnjjsBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( ZnjjsBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( ZnjjsBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( ZnjjsBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( ZnjjsBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( ZnjjsBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( ZnjjsBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ�������룩
*
* @return �������룩
*/
public UFDouble getYq_bl () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.YQ_BL);
 } 

/** 
* ���ñ������룩
*
* @param yq_bl �������룩
*/
public void setYq_bl ( UFDouble yq_bl) {
this.setAttributeValue( ZnjjsBVO.YQ_BL,yq_bl);
 } 

/** 
* ��ȡ���ɽ�
*
* @return ���ɽ�
*/
public UFDouble getYq_mny () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.YQ_MNY);
 } 

/** 
* �������ɽ�
*
* @param yq_mny ���ɽ�
*/
public void setYq_mny ( UFDouble yq_mny) {
this.setAttributeValue( ZnjjsBVO.YQ_MNY,yq_mny);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getYq_num () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.YQ_NUM);
 } 

/** 
* ������������
*
* @param yq_num ��������
*/
public void setYq_num ( UFDouble yq_num) {
this.setAttributeValue( ZnjjsBVO.YQ_NUM,yq_num);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_zulin_znjjsBVO");
  }
}