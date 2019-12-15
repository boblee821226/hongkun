package nc.vo.hkjt.srgk.huiguan.cctz;

import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CctzBVO extends SuperVO {
/**
*����
*/
public static final String BM_PK="bm_pk";
/**
*�������
*/
public static final String CC_DATE="cc_date";
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
*�������
*/
public static final String LEIXING="leixing";
/**
*�ϲ㵥������
*/
public static final String PK_HK_SRGK_HG_CCTZ="pk_hk_srgk_hg_cctz";
/**
*�ӱ�����
*/
public static final String PK_HK_SRGK_HG_CCTZ_B="pk_hk_srgk_hg_cctz_b";
/**
*����
*/
public static final String SHIXIANG="shixiang";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*��������
*/
public static final String TZ_DATE="tz_date";
/**
*������Ŀ����1
*/
public static final String TZ_KM_DATA_1="tz_km_data_1";
/**
*������Ŀ����2
*/
public static final String TZ_KM_DATA_2="tz_km_data_2";
/**
*������Ŀ��ϸ1
*/
public static final String TZ_KM_INFO_1="tz_km_info_1";
/**
*������Ŀ��ϸ2
*/
public static final String TZ_KM_INFO_2="tz_km_info_2";
/**
*������Ŀ���˷�ʽ1
*/
public static final String TZ_KM_JZFS_1="tz_km_jzfs_1";
/**
*������Ŀ���˷�ʽ2
*/
public static final String TZ_KM_JZFS_2="tz_km_jzfs_2";
/**
*������Ŀ������Ŀ1
*/
public static final String TZ_KM_SRXM_1="tz_km_srxm_1";
/**
*������Ŀ������Ŀ2
*/
public static final String TZ_KM_SRXM_2="tz_km_srxm_2";
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
*ԭ��
*/
public static final String YUANYIN="yuanyin";
public static final String TZ_KM_SRXM_TYPE1 = "tz_km_srxm_type1";
public static final String TZ_KM_SRXM_TYPE2 = "tz_km_srxm_type2";

public String getTz_km_srxm_type1() {
	return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1);
}

public void setTz_km_srxm_type1(String tz_km_srxm_type1) {
	this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1, tz_km_srxm_type1);
}

public String getTz_km_srxm_type2() {
	return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2);
}

public void setTz_km_srxm_type2(String tz_km_srxm_type2) {
	this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2, tz_km_srxm_type2);
}
/** 
* ��ȡ����
*
* @return ����
*/
public String getBm_pk () {
return (String) this.getAttributeValue( CctzBVO.BM_PK);
 } 

/** 
* ���ò���
*
* @param bm_pk ����
*/
public void setBm_pk ( String bm_pk) {
this.setAttributeValue( CctzBVO.BM_PK,bm_pk);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDate getCc_date () {
return (UFDate) this.getAttributeValue( CctzBVO.CC_DATE);
 } 

/** 
* ���ò������
*
* @param cc_date �������
*/
public void setCc_date ( UFDate cc_date) {
this.setAttributeValue( CctzBVO.CC_DATE,cc_date);
 } 

/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( CctzBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CctzBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CctzBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CctzBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CctzBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CctzBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CctzBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CctzBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CctzBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CctzBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CctzBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CctzBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ�������
*
* @return �������
* @see String
*/
public String getLeixing () {
return (String) this.getAttributeValue( CctzBVO.LEIXING);
 } 

/** 
* ���ò������
*
* @param leixing �������
* @see String
*/
public void setLeixing ( String leixing) {
this.setAttributeValue( CctzBVO.LEIXING,leixing);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_srgk_hg_cctz () {
return (String) this.getAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_srgk_hg_cctz �ϲ㵥������
*/
public void setPk_hk_srgk_hg_cctz ( String pk_hk_srgk_hg_cctz) {
this.setAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ,pk_hk_srgk_hg_cctz);
 } 

/** 
* ��ȡ�ӱ�����
*
* @return �ӱ�����
*/
public String getPk_hk_srgk_hg_cctz_b () {
return (String) this.getAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ_B);
 } 

/** 
* �����ӱ�����
*
* @param pk_hk_srgk_hg_cctz_b �ӱ�����
*/
public void setPk_hk_srgk_hg_cctz_b ( String pk_hk_srgk_hg_cctz_b) {
this.setAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ_B,pk_hk_srgk_hg_cctz_b);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getShixiang () {
return (String) this.getAttributeValue( CctzBVO.SHIXIANG);
 } 

/** 
* ��������
*
* @param shixiang ����
*/
public void setShixiang ( String shixiang) {
this.setAttributeValue( CctzBVO.SHIXIANG,shixiang);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CctzBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CctzBVO.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getTz_date () {
return (UFDate) this.getAttributeValue( CctzBVO.TZ_DATE);
 } 

/** 
* ���õ�������
*
* @param tz_date ��������
*/
public void setTz_date ( UFDate tz_date) {
this.setAttributeValue( CctzBVO.TZ_DATE,tz_date);
 } 

/** 
* ��ȡ������Ŀ����1
*
* @return ������Ŀ����1
*/
public UFDouble getTz_km_data_1 () {
return (UFDouble) this.getAttributeValue( CctzBVO.TZ_KM_DATA_1);
 } 

/** 
* ���õ�����Ŀ����1
*
* @param tz_km_data_1 ������Ŀ����1
*/
public void setTz_km_data_1 ( UFDouble tz_km_data_1) {
this.setAttributeValue( CctzBVO.TZ_KM_DATA_1,tz_km_data_1);
 } 

/** 
* ��ȡ������Ŀ����2
*
* @return ������Ŀ����2
*/
public UFDouble getTz_km_data_2 () {
return (UFDouble) this.getAttributeValue( CctzBVO.TZ_KM_DATA_2);
 } 

/** 
* ���õ�����Ŀ����2
*
* @param tz_km_data_2 ������Ŀ����2
*/
public void setTz_km_data_2 ( UFDouble tz_km_data_2) {
this.setAttributeValue( CctzBVO.TZ_KM_DATA_2,tz_km_data_2);
 } 

/** 
* ��ȡ������Ŀ��ϸ1
*
* @return ������Ŀ��ϸ1
*/
public String getTz_km_info_1 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_INFO_1);
 } 

/** 
* ���õ�����Ŀ��ϸ1
*
* @param tz_km_info_1 ������Ŀ��ϸ1
*/
public void setTz_km_info_1 ( String tz_km_info_1) {
this.setAttributeValue( CctzBVO.TZ_KM_INFO_1,tz_km_info_1);
 } 

/** 
* ��ȡ������Ŀ��ϸ2
*
* @return ������Ŀ��ϸ2
*/
public String getTz_km_info_2 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_INFO_2);
 } 

/** 
* ���õ�����Ŀ��ϸ2
*
* @param tz_km_info_2 ������Ŀ��ϸ2
*/
public void setTz_km_info_2 ( String tz_km_info_2) {
this.setAttributeValue( CctzBVO.TZ_KM_INFO_2,tz_km_info_2);
 } 

/** 
* ��ȡ������Ŀ���˷�ʽ1
*
* @return ������Ŀ���˷�ʽ1
*/
public String getTz_km_jzfs_1 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_JZFS_1);
 } 

/** 
* ���õ�����Ŀ���˷�ʽ1
*
* @param tz_km_jzfs_1 ������Ŀ���˷�ʽ1
*/
public void setTz_km_jzfs_1 ( String tz_km_jzfs_1) {
this.setAttributeValue( CctzBVO.TZ_KM_JZFS_1,tz_km_jzfs_1);
 } 

/** 
* ��ȡ������Ŀ���˷�ʽ2
*
* @return ������Ŀ���˷�ʽ2
*/
public String getTz_km_jzfs_2 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_JZFS_2);
 } 

/** 
* ���õ�����Ŀ���˷�ʽ2
*
* @param tz_km_jzfs_2 ������Ŀ���˷�ʽ2
*/
public void setTz_km_jzfs_2 ( String tz_km_jzfs_2) {
this.setAttributeValue( CctzBVO.TZ_KM_JZFS_2,tz_km_jzfs_2);
 } 

/** 
* ��ȡ������Ŀ������Ŀ1
*
* @return ������Ŀ������Ŀ1
*/
public String getTz_km_srxm_1 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_SRXM_1);
 } 

/** 
* ���õ�����Ŀ������Ŀ1
*
* @param tz_km_srxm_1 ������Ŀ������Ŀ1
*/
public void setTz_km_srxm_1 ( String tz_km_srxm_1) {
this.setAttributeValue( CctzBVO.TZ_KM_SRXM_1,tz_km_srxm_1);
 } 

/** 
* ��ȡ������Ŀ������Ŀ2
*
* @return ������Ŀ������Ŀ2
*/
public String getTz_km_srxm_2 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_SRXM_2);
 } 

/** 
* ���õ�����Ŀ������Ŀ2
*
* @param tz_km_srxm_2 ������Ŀ������Ŀ2
*/
public void setTz_km_srxm_2 ( String tz_km_srxm_2) {
this.setAttributeValue( CctzBVO.TZ_KM_SRXM_2,tz_km_srxm_2);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CctzBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CctzBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CctzBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CctzBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CctzBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CctzBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CctzBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CctzBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CctzBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CctzBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CctzBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CctzBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CctzBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CctzBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( CctzBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( CctzBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϴε��ݺ�
*
* @return �ϴε��ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CctzBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϴε��ݺ�
*
* @param vsourcebillcode �ϴε��ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CctzBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡԭ��
*
* @return ԭ��
*/
public String getYuanyin () {
return (String) this.getAttributeValue( CctzBVO.YUANYIN);
 } 

/** 
* ����ԭ��
*
* @param yuanyin ԭ��
*/
public void setYuanyin ( String yuanyin) {
this.setAttributeValue( CctzBVO.YUANYIN,yuanyin);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_CctzBVO");
  }
}