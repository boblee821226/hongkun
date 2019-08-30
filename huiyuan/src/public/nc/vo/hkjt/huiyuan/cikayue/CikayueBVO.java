package nc.vo.hkjt.huiyuan.cikayue;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CikayueBVO extends SuperVO {

private static final long serialVersionUID = 234364226984185059L;

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
*��Ŀ����
*/
public static final String ITEMID="itemid";
/**
*��Ŀ����
*/
public static final String ITEMNAME="itemname";
/**
*�������
*/
public static final String JG_SL="jg_sl";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_CIKAYUE="pk_hk_huiyuan_cikayue";
/**
*�ο������pk
*/
public static final String PK_HK_HUIYUAN_CIKAYUE_B="pk_hk_huiyuan_cikayue_b";
/**
*�ڳ����
*/
public static final String QC_JE="qc_je";
/**
*�ڳ�����
*/
public static final String QC_SL="qc_sl";
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
return (String) this.getAttributeValue( CikayueBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CikayueBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CikayueBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CikayueBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CikayueBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CikayueBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getCha_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.CHA_SL);
 } 

/** 
* ���ò��
*
* @param cha_sl ���
*/
public void setCha_sl ( UFDouble cha_sl) {
this.setAttributeValue( CikayueBVO.CHA_SL,cha_sl);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CikayueBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CikayueBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CikayueBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CikayueBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CikayueBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CikayueBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��ֵ���
*
* @return ��ֵ���
*/
public UFDouble getCz_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.CZ_JE);
 } 

/** 
* ���ó�ֵ���
*
* @param cz_je ��ֵ���
*/
public void setCz_je ( UFDouble cz_je) {
this.setAttributeValue( CikayueBVO.CZ_JE,cz_je);
 } 

/** 
* ��ȡ��ֵ����
*
* @return ��ֵ����
*/
public UFDouble getCz_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.CZ_SL);
 } 

/** 
* ���ó�ֵ����
*
* @param cz_sl ��ֵ����
*/
public void setCz_sl ( UFDouble cz_sl) {
this.setAttributeValue( CikayueBVO.CZ_SL,cz_sl);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemid () {
return (String) this.getAttributeValue( CikayueBVO.ITEMID);
 } 

/** 
* ������Ŀ����
*
* @param itemid ��Ŀ����
*/
public void setItemid ( String itemid) {
this.setAttributeValue( CikayueBVO.ITEMID,itemid);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemname () {
return (String) this.getAttributeValue( CikayueBVO.ITEMNAME);
 } 

/** 
* ������Ŀ����
*
* @param itemname ��Ŀ����
*/
public void setItemname ( String itemname) {
this.setAttributeValue( CikayueBVO.ITEMNAME,itemname);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getJg_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.JG_SL);
 } 

/** 
* ���ý������
*
* @param jg_sl �������
*/
public void setJg_sl ( UFDouble jg_sl) {
this.setAttributeValue( CikayueBVO.JG_SL,jg_sl);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_cikayue () {
return (String) this.getAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_cikayue �ϲ㵥������
*/
public void setPk_hk_huiyuan_cikayue ( String pk_hk_huiyuan_cikayue) {
this.setAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE,pk_hk_huiyuan_cikayue);
 } 

/** 
* ��ȡ�ο������pk
*
* @return �ο������pk
*/
public String getPk_hk_huiyuan_cikayue_b () {
return (String) this.getAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE_B);
 } 

/** 
* ���ôο������pk
*
* @param pk_hk_huiyuan_cikayue_b �ο������pk
*/
public void setPk_hk_huiyuan_cikayue_b ( String pk_hk_huiyuan_cikayue_b) {
this.setAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE_B,pk_hk_huiyuan_cikayue_b);
 } 

/** 
* ��ȡ�ڳ����
*
* @return �ڳ����
*/
public UFDouble getQc_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.QC_JE);
 } 

/** 
* �����ڳ����
*
* @param qc_je �ڳ����
*/
public void setQc_je ( UFDouble qc_je) {
this.setAttributeValue( CikayueBVO.QC_JE,qc_je);
 } 

/** 
* ��ȡ�ڳ�����
*
* @return �ڳ�����
*/
public UFDouble getQc_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.QC_SL);
 } 

/** 
* �����ڳ�����
*
* @param qc_sl �ڳ�����
*/
public void setQc_sl ( UFDouble qc_sl) {
this.setAttributeValue( CikayueBVO.QC_SL,qc_sl);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getTj_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.TJ_JE);
 } 

/** 
* ���õ������
*
* @param tj_je �������
*/
public void setTj_je ( UFDouble tj_je) {
this.setAttributeValue( CikayueBVO.TJ_JE,tj_je);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getTj_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.TJ_SL);
 } 

/** 
* ���õ�������
*
* @param tj_sl ��������
*/
public void setTj_sl ( UFDouble tj_sl) {
this.setAttributeValue( CikayueBVO.TJ_SL,tj_sl);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CikayueBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CikayueBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CikayueBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CikayueBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CikayueBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CikayueBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CikayueBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CikayueBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CikayueBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CikayueBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CikayueBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CikayueBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CikayueBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CikayueBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CikayueBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CikayueBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( CikayueBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( CikayueBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CikayueBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CikayueBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ���ѽ��
*
* @return ���ѽ��
*/
public UFDouble getXf_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.XF_JE);
 } 

/** 
* �������ѽ��
*
* @param xf_je ���ѽ��
*/
public void setXf_je ( UFDouble xf_je) {
this.setAttributeValue( CikayueBVO.XF_JE,xf_je);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getXf_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.XF_SL);
 } 

/** 
* ������������
*
* @param xf_sl ��������
*/
public void setXf_sl ( UFDouble xf_sl) {
this.setAttributeValue( CikayueBVO.XF_SL,xf_sl);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getYue_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.YUE_JE);
 } 

/** 
* �������
*
* @param yue_je ���
*/
public void setYue_je ( UFDouble yue_je) {
this.setAttributeValue( CikayueBVO.YUE_JE,yue_je);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getYue_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.YUE_SL);
 } 

/** 
* ��������
*
* @param yue_sl ����
*/
public void setYue_sl ( UFDouble yue_sl) {
this.setAttributeValue( CikayueBVO.YUE_SL,yue_sl);
 } 

/** 
* ��ȡת�����
*
* @return ת�����
*/
public UFDouble getZc_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZC_JE);
 } 

/** 
* ����ת�����
*
* @param zc_je ת�����
*/
public void setZc_je ( UFDouble zc_je) {
this.setAttributeValue( CikayueBVO.ZC_JE,zc_je);
 } 

/** 
* ��ȡת������
*
* @return ת������
*/
public UFDouble getZc_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZC_SL);
 } 

/** 
* ����ת������
*
* @param zc_sl ת������
*/
public void setZc_sl ( UFDouble zc_sl) {
this.setAttributeValue( CikayueBVO.ZC_SL,zc_sl);
 } 

/** 
* ��ȡת����
*
* @return ת����
*/
public UFDouble getZr_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZR_JE);
 } 

/** 
* ����ת����
*
* @param zr_je ת����
*/
public void setZr_je ( UFDouble zr_je) {
this.setAttributeValue( CikayueBVO.ZR_JE,zr_je);
 } 

/** 
* ��ȡת������
*
* @return ת������
*/
public UFDouble getZr_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZR_SL);
 } 

/** 
* ����ת������
*
* @param zr_sl ת������
*/
public void setZr_sl ( UFDouble zr_sl) {
this.setAttributeValue( CikayueBVO.ZR_SL,zr_sl);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_CikayueBVO");
  }
  
  public static final String KA_CODE="ka_code";
  public String getKa_code() {
  return (String) this.getAttributeValue( CikayueBVO.KA_CODE);
   } 
  public void setKa_code( String ka_code) {
  this.setAttributeValue( CikayueBVO.KA_CODE,ka_code);
   } 
  
  public static final String STARTDATA="startdata";
  public String getStartdata() {
  return (String) this.getAttributeValue( CikayueBVO.STARTDATA);
   } 
  public void setStartdata( String startdata) {
  this.setAttributeValue( CikayueBVO.STARTDATA,startdata);
   } 
  
}