package nc.vo.hkjt.srgk.huiguan.rsbaogao;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RsbaogaoBVO extends SuperVO {
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
public static final String DALEI="dalei";
/**
*����˵��
*/
public static final String FJSM="fjsm";
/**
*���˷�Χ
*/
public static final String JHFW="jhfw";
/**
*����Ҫ��
*/
public static final String JHYQ="jhyq";
/**
*���˵�
*/
public static final String JIHEDIAN="jihedian";
/**
*�ϲ㵥������
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO="pk_hk_srgk_hg_rsbaogao";
/**
*�ӱ�����
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO_B="pk_hk_srgk_hg_rsbaogao_b";
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
*�ϴε��ݺ�
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/**
*�쳣ԭ��
*/
public static final String YCYY="ycyy";
/**
*״̬
*/
public static final String ZHANGTAI="zhangtai";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( RsbaogaoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( RsbaogaoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( RsbaogaoBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( RsbaogaoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( RsbaogaoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( RsbaogaoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( RsbaogaoBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( RsbaogaoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ����
*
* @return ����
* @see String
*/
public String getDalei () {
return (String) this.getAttributeValue( RsbaogaoBVO.DALEI);
 } 

/** 
* ���ô���
*
* @param dalei ����
* @see String
*/
public void setDalei ( String dalei) {
this.setAttributeValue( RsbaogaoBVO.DALEI,dalei);
 } 

/** 
* ��ȡ����˵��
*
* @return ����˵��
*/
public String getFjsm () {
return (String) this.getAttributeValue( RsbaogaoBVO.FJSM);
 } 

/** 
* ���ø���˵��
*
* @param fjsm ����˵��
*/
public void setFjsm ( String fjsm) {
this.setAttributeValue( RsbaogaoBVO.FJSM,fjsm);
 } 

/** 
* ��ȡ���˷�Χ
*
* @return ���˷�Χ
*/
public String getJhfw () {
return (String) this.getAttributeValue( RsbaogaoBVO.JHFW);
 } 

/** 
* ���û��˷�Χ
*
* @param jhfw ���˷�Χ
*/
public void setJhfw ( String jhfw) {
this.setAttributeValue( RsbaogaoBVO.JHFW,jhfw);
 } 

/** 
* ��ȡ����Ҫ��
*
* @return ����Ҫ��
* @see String
*/
public String getJhyq () {
return (String) this.getAttributeValue( RsbaogaoBVO.JHYQ);
 } 

/** 
* ���û���Ҫ��
*
* @param jhyq ����Ҫ��
* @see String
*/
public void setJhyq ( String jhyq) {
this.setAttributeValue( RsbaogaoBVO.JHYQ,jhyq);
 } 

/** 
* ��ȡ���˵�
*
* @return ���˵�
*/
public String getJihedian () {
return (String) this.getAttributeValue( RsbaogaoBVO.JIHEDIAN);
 } 

/** 
* ���û��˵�
*
* @param jihedian ���˵�
*/
public void setJihedian ( String jihedian) {
this.setAttributeValue( RsbaogaoBVO.JIHEDIAN,jihedian);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_srgk_hg_rsbaogao () {
return (String) this.getAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_srgk_hg_rsbaogao �ϲ㵥������
*/
public void setPk_hk_srgk_hg_rsbaogao ( String pk_hk_srgk_hg_rsbaogao) {
this.setAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO,pk_hk_srgk_hg_rsbaogao);
 } 

/** 
* ��ȡ�ӱ�����
*
* @return �ӱ�����
*/
public String getPk_hk_srgk_hg_rsbaogao_b () {
return (String) this.getAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO_B);
 } 

/** 
* �����ӱ�����
*
* @param pk_hk_srgk_hg_rsbaogao_b �ӱ�����
*/
public void setPk_hk_srgk_hg_rsbaogao_b ( String pk_hk_srgk_hg_rsbaogao_b) {
this.setAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO_B,pk_hk_srgk_hg_rsbaogao_b);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( RsbaogaoBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( RsbaogaoBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( RsbaogaoBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( RsbaogaoBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( RsbaogaoBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( RsbaogaoBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( RsbaogaoBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( RsbaogaoBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( RsbaogaoBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( RsbaogaoBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( RsbaogaoBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( RsbaogaoBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( RsbaogaoBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( RsbaogaoBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( RsbaogaoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( RsbaogaoBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( RsbaogaoBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϴε��ݺ�
*
* @return �ϴε��ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( RsbaogaoBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϴε��ݺ�
*
* @param vsourcebillcode �ϴε��ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( RsbaogaoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ�쳣ԭ��
*
* @return �쳣ԭ��
*/
public String getYcyy () {
return (String) this.getAttributeValue( RsbaogaoBVO.YCYY);
 } 

/** 
* �����쳣ԭ��
*
* @param ycyy �쳣ԭ��
*/
public void setYcyy ( String ycyy) {
this.setAttributeValue( RsbaogaoBVO.YCYY,ycyy);
 } 

/** 
* ��ȡ״̬
*
* @return ״̬
* @see String
*/
public String getZhangtai () {
return (String) this.getAttributeValue( RsbaogaoBVO.ZHANGTAI);
 } 

/** 
* ����״̬
*
* @param zhangtai ״̬
* @see String
*/
public void setZhangtai ( String zhangtai) {
this.setAttributeValue( RsbaogaoBVO.ZHANGTAI,zhangtai);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_RsbaogaoBVO");
  }
}