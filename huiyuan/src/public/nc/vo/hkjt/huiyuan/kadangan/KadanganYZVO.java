package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KadanganYZVO extends SuperVO {
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
*�����
*/
public static final String KAYUE="kayue";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
/**
*��Ա��ת����
*/
public static final String PK_HK_HUIYUAN_KADANGAN_YZ="pk_hk_huiyuan_kadangan_yz";
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
*����ʱ��
*/
public static final String YZ_TIME="yz_time";
/**
*ת�����
*/
public static final String ZC_JE="zc_je";
/**
*ת��ʵ��
*/
public static final String ZC_SS="zc_ss";
/**
*�˵���
*/
public static final String ZDH="zdh";
/**
*ת����
*/
public static final String ZR_JE="zr_je";
/**
*ת��ʵ��
*/
public static final String ZR_SS="zr_ss";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KadanganYZVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KadanganYZVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KadanganYZVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KadanganYZVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KadanganYZVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KadanganYZVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KadanganYZVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KadanganYZVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KadanganYZVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KadanganYZVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KadanganYZVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KadanganYZVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public UFDouble getKayue () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.KAYUE);
 } 

/** 
* ���ÿ����
*
* @param kayue �����
*/
public void setKayue ( UFDouble kayue) {
this.setAttributeValue( KadanganYZVO.KAYUE,kayue);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kadangan () {
return (String) this.getAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kadangan �ϲ㵥������
*/
public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
this.setAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
 } 

/** 
* ��ȡ��Ա��ת����
*
* @return ��Ա��ת����
*/
public String getPk_hk_huiyuan_kadangan_yz () {
return (String) this.getAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN_YZ);
 } 

/** 
* ���û�Ա��ת����
*
* @param pk_hk_huiyuan_kadangan_yz ��Ա��ת����
*/
public void setPk_hk_huiyuan_kadangan_yz ( String pk_hk_huiyuan_kadangan_yz) {
this.setAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN_YZ,pk_hk_huiyuan_kadangan_yz);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KadanganYZVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KadanganYZVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KadanganYZVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KadanganYZVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KadanganYZVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KadanganYZVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KadanganYZVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KadanganYZVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KadanganYZVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KadanganYZVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KadanganYZVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KadanganYZVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KadanganYZVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KadanganYZVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KadanganYZVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KadanganYZVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( KadanganYZVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( KadanganYZVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KadanganYZVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KadanganYZVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getYz_time () {
return (UFDateTime) this.getAttributeValue( KadanganYZVO.YZ_TIME);
 } 

/** 
* ��������ʱ��
*
* @param yz_time ����ʱ��
*/
public void setYz_time ( UFDateTime yz_time) {
this.setAttributeValue( KadanganYZVO.YZ_TIME,yz_time);
 } 

/** 
* ��ȡת�����
*
* @return ת�����
*/
public UFDouble getZc_je () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZC_JE);
 } 

/** 
* ����ת�����
*
* @param zc_je ת�����
*/
public void setZc_je ( UFDouble zc_je) {
this.setAttributeValue( KadanganYZVO.ZC_JE,zc_je);
 } 

/** 
* ��ȡת��ʵ��
*
* @return ת��ʵ��
*/
public UFDouble getZc_ss () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZC_SS);
 } 

/** 
* ����ת��ʵ��
*
* @param zc_ss ת��ʵ��
*/
public void setZc_ss ( UFDouble zc_ss) {
this.setAttributeValue( KadanganYZVO.ZC_SS,zc_ss);
 } 

/** 
* ��ȡ�˵���
*
* @return �˵���
*/
public String getZdh () {
return (String) this.getAttributeValue( KadanganYZVO.ZDH);
 } 

/** 
* �����˵���
*
* @param zdh �˵���
*/
public void setZdh ( String zdh) {
this.setAttributeValue( KadanganYZVO.ZDH,zdh);
 } 

/** 
* ��ȡת����
*
* @return ת����
*/
public UFDouble getZr_je () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZR_JE);
 } 

/** 
* ����ת����
*
* @param zr_je ת����
*/
public void setZr_je ( UFDouble zr_je) {
this.setAttributeValue( KadanganYZVO.ZR_JE,zr_je);
 } 

/** 
* ��ȡת��ʵ��
*
* @return ת��ʵ��
*/
public UFDouble getZr_ss () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZR_SS);
 } 

/** 
* ����ת��ʵ��
*
* @param zr_ss ת��ʵ��
*/
public void setZr_ss ( UFDouble zr_ss) {
this.setAttributeValue( KadanganYZVO.ZR_SS,zr_ss);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KadanganYZVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( KadanganYZVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( KadanganYZVO.DR);
	   } 
  
  public static final String DF_KA_CODE="df_ka_code";
  public void setDf_ka_code ( String df_ka_code) {
	  this.setAttributeValue( KadanganYZVO.DF_KA_CODE,df_ka_code);
  }
  public String getDf_ka_code () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KA_CODE);
	   } 
  
  public static final String DF_KA_NAME="df_ka_name";
  public void setDf_ka_name ( String df_ka_name) {
	  this.setAttributeValue( KadanganYZVO.DF_KA_NAME,df_ka_name);
  }
  public String getDf_ka_name () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KA_NAME);
	   } 
  
  public static final String DF_KA_PK="df_ka_pk";
  public void setDf_ka_pk ( String df_ka_pk) {
	  this.setAttributeValue( KadanganYZVO.DF_KA_PK,df_ka_pk);
  }
  public String getDf_ka_pk () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KA_PK);
	   } 
  
  public static final String DF_KAXING_PK="df_kaxing_pk";
  public void setDf_kaxing_pk ( String df_kaxing_pk) {
	  this.setAttributeValue( KadanganYZVO.DF_KAXING_PK,df_kaxing_pk);
  }
  public String getDf_kaxing_pk () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KAXING_PK);
  } 
  
  public static final String DF_KAXING_CODE="df_kaxing_code";
  public void setDf_kaxing_code ( String df_kaxing_code) {
	  this.setAttributeValue( KadanganYZVO.DF_KAXING_CODE,df_kaxing_code);
  }
  public String getDf_kaxing_code () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KAXING_CODE);
	   } 
  
  public static final String DF_KAXING_NAME="df_kaxing_name";
  public void setDf_kaxing_name ( String df_kaxing_name) {
	  this.setAttributeValue( KadanganYZVO.DF_KAXING_NAME,df_kaxing_name);
  }
  public String getDf_kaxing_name () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KAXING_NAME);
	   } 
  
}