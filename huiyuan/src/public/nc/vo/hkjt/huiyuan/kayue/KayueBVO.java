package nc.vo.hkjt.huiyuan.kayue;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KayueBVO extends SuperVO {
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
public static final String CHAE="chae";
/**
*��ֵ
*/
public static final String CHONGZHI="chongzhi";
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
*������
*/
public static final String KA_DIAN="ka_dian";
/**
*������
*/
public static final String KA_NUM="ka_num";
/**
*��pk
*/
public static final String KA_PK="ka_pk";
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
public static final String PK_HK_HUIYUAN_KAYUE="pk_hk_huiyuan_kayue";
/**
*��Ա����ӱ�pk
*/
public static final String PK_HK_HUIYUAN_KAYUE_B="pk_hk_huiyuan_kayue_b";
/**
*�ڳ�
*/
public static final String QICHU="qichu";
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
*����
*/
public static final String XIAOFEI="xiaofei";
/**
*���
*/
public static final String YUE="yue";
/**
*������
*/
public static final String YUE_JG="yue_jg";
/**
*ת��
*/
public static final String ZHUANCHU="zhuanchu";
/**
*ת��
*/
public static final String ZHUANRU="zhuanru";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KayueBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KayueBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KayueBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KayueBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KayueBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KayueBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getChae () {
return (UFDouble) this.getAttributeValue( KayueBVO.CHAE);
 } 

/** 
* ���ò��
*
* @param chae ���
*/
public void setChae ( UFDouble chae) {
this.setAttributeValue( KayueBVO.CHAE,chae);
 } 

/** 
* ��ȡ��ֵ
*
* @return ��ֵ
*/
public UFDouble getChongzhi () {
return (UFDouble) this.getAttributeValue( KayueBVO.CHONGZHI);
 } 

/** 
* ���ó�ֵ
*
* @param chongzhi ��ֵ
*/
public void setChongzhi ( UFDouble chongzhi) {
this.setAttributeValue( KayueBVO.CHONGZHI,chongzhi);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KayueBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KayueBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KayueBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KayueBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KayueBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KayueBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��code
*
* @return ��code
*/
public String getKa_code () {
return (String) this.getAttributeValue( KayueBVO.KA_CODE);
 } 

/** 
* ���ÿ�code
*
* @param ka_code ��code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KayueBVO.KA_CODE,ka_code);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getKa_dian () {
return (String) this.getAttributeValue( KayueBVO.KA_DIAN);
 } 

/** 
* ���ÿ�����
*
* @param ka_dian ������
*/
public void setKa_dian ( String ka_dian) {
this.setAttributeValue( KayueBVO.KA_DIAN,ka_dian);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public Integer getKa_num () {
return (Integer) this.getAttributeValue( KayueBVO.KA_NUM);
 } 

/** 
* ���ÿ�����
*
* @param ka_num ������
*/
public void setKa_num ( Integer ka_num) {
this.setAttributeValue( KayueBVO.KA_NUM,ka_num);
 } 

/** 
* ��ȡ��pk
*
* @return ��pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( KayueBVO.KA_PK);
 } 

/** 
* ���ÿ�pk
*
* @param ka_pk ��pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( KayueBVO.KA_PK,ka_pk);
 } 

/** 
* ��ȡ����code
*
* @return ����code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KayueBVO.KAXING_CODE);
 } 

/** 
* ���ÿ���code
*
* @param kaxing_code ����code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KayueBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* ��ȡ����name
*
* @return ����name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KayueBVO.KAXING_NAME);
 } 

/** 
* ���ÿ���name
*
* @param kaxing_name ����name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KayueBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* ��ȡ����pk
*
* @return ����pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( KayueBVO.KAXING_PK);
 } 

/** 
* ���ÿ���pk
*
* @param kaxing_pk ����pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( KayueBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kayue () {
return (String) this.getAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kayue �ϲ㵥������
*/
public void setPk_hk_huiyuan_kayue ( String pk_hk_huiyuan_kayue) {
this.setAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE,pk_hk_huiyuan_kayue);
 } 

/** 
* ��ȡ��Ա����ӱ�pk
*
* @return ��Ա����ӱ�pk
*/
public String getPk_hk_huiyuan_kayue_b () {
return (String) this.getAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE_B);
 } 

/** 
* ���û�Ա����ӱ�pk
*
* @param pk_hk_huiyuan_kayue_b ��Ա����ӱ�pk
*/
public void setPk_hk_huiyuan_kayue_b ( String pk_hk_huiyuan_kayue_b) {
this.setAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE_B,pk_hk_huiyuan_kayue_b);
 } 

/** 
* ��ȡ�ڳ�
*
* @return �ڳ�
*/
public UFDouble getQichu () {
return (UFDouble) this.getAttributeValue( KayueBVO.QICHU);
 } 

/** 
* �����ڳ�
*
* @param qichu �ڳ�
*/
public void setQichu ( UFDouble qichu) {
this.setAttributeValue( KayueBVO.QICHU,qichu);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KayueBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KayueBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KayueBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KayueBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KayueBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KayueBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KayueBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KayueBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KayueBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KayueBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KayueBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KayueBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KayueBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KayueBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KayueBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KayueBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KayueBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KayueBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KayueBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KayueBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getXiaofei () {
return (UFDouble) this.getAttributeValue( KayueBVO.XIAOFEI);
 } 

/** 
* ��������
*
* @param xiaofei ����
*/
public void setXiaofei ( UFDouble xiaofei) {
this.setAttributeValue( KayueBVO.XIAOFEI,xiaofei);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getYue () {
return (UFDouble) this.getAttributeValue( KayueBVO.YUE);
 } 

/** 
* �������
*
* @param yue ���
*/
public void setYue ( UFDouble yue) {
this.setAttributeValue( KayueBVO.YUE,yue);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getYue_jg () {
return (UFDouble) this.getAttributeValue( KayueBVO.YUE_JG);
 } 

/** 
* ���ý�����
*
* @param yue_jg ������
*/
public void setYue_jg ( UFDouble yue_jg) {
this.setAttributeValue( KayueBVO.YUE_JG,yue_jg);
 } 

/** 
* ��ȡת��
*
* @return ת��
*/
public UFDouble getZhuanchu () {
return (UFDouble) this.getAttributeValue( KayueBVO.ZHUANCHU);
 } 

/** 
* ����ת��
*
* @param zhuanchu ת��
*/
public void setZhuanchu ( UFDouble zhuanchu) {
this.setAttributeValue( KayueBVO.ZHUANCHU,zhuanchu);
 } 

/** 
* ��ȡת��
*
* @return ת��
*/
public UFDouble getZhuanru () {
return (UFDouble) this.getAttributeValue( KayueBVO.ZHUANRU);
 } 

/** 
* ����ת��
*
* @param zhuanru ת��
*/
public void setZhuanru ( UFDouble zhuanru) {
this.setAttributeValue( KayueBVO.ZHUANRU,zhuanru);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KayueBVO");
  }
}