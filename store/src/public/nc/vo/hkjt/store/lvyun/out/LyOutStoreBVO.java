package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class LyOutStoreBVO extends SuperVO {
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
*������Ʒcode
*/
public static final String LY_SP_CODE="ly_sp_code";
/**
*������Ʒname
*/
public static final String LY_SP_NAME="ly_sp_name";
/**
*NC����
*/
public static final String PK_DEPT="pk_dept";
/**
*�ϲ㵥������
*/
public static final String PK_HK_STORE_LVYUN_OUT="pk_hk_store_lvyun_out";
/**
*����Ʒ����pk
*/
public static final String PK_HK_STORE_LVYUN_OUT_B="pk_hk_store_lvyun_out_b";
/**
*NC����
*/
public static final String PK_INV="pk_inv";
/**
*NC�ֿ�
*/
public static final String PK_STORE="pk_store";
/**
*������
*/
public static final String SP_OUT_AMOUNT="sp_out_amount";
/**
*��������
*/
public static final String SP_OUT_QUANTITY="sp_out_quantity";
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
*Ӫҵ��code
*/
public static final String YYD_CODE="yyd_code";
/**
*Ӫҵ��name
*/
public static final String YYD_NAME="yyd_name";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( LyOutStoreBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( LyOutStoreBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( LyOutStoreBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( LyOutStoreBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( LyOutStoreBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( LyOutStoreBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( LyOutStoreBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( LyOutStoreBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ������Ʒcode
*
* @return ������Ʒcode
*/
public String getLy_sp_code () {
return (String) this.getAttributeValue( LyOutStoreBVO.LY_SP_CODE);
 } 

/** 
* ����������Ʒcode
*
* @param ly_sp_code ������Ʒcode
*/
public void setLy_sp_code ( String ly_sp_code) {
this.setAttributeValue( LyOutStoreBVO.LY_SP_CODE,ly_sp_code);
 } 

/** 
* ��ȡ������Ʒname
*
* @return ������Ʒname
*/
public String getLy_sp_name () {
return (String) this.getAttributeValue( LyOutStoreBVO.LY_SP_NAME);
 } 

/** 
* ����������Ʒname
*
* @param ly_sp_name ������Ʒname
*/
public void setLy_sp_name ( String ly_sp_name) {
this.setAttributeValue( LyOutStoreBVO.LY_SP_NAME,ly_sp_name);
 } 

/** 
* ��ȡNC����
*
* @return NC����
*/
public String getPk_dept () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_DEPT);
 } 

/** 
* ����NC����
*
* @param pk_dept NC����
*/
public void setPk_dept ( String pk_dept) {
this.setAttributeValue( LyOutStoreBVO.PK_DEPT,pk_dept);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_store_lvyun_out () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_store_lvyun_out �ϲ㵥������
*/
public void setPk_hk_store_lvyun_out ( String pk_hk_store_lvyun_out) {
this.setAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT,pk_hk_store_lvyun_out);
 } 

/** 
* ��ȡ����Ʒ����pk
*
* @return ����Ʒ����pk
*/
public String getPk_hk_store_lvyun_out_b () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT_B);
 } 

/** 
* ��������Ʒ����pk
*
* @param pk_hk_store_lvyun_out_b ����Ʒ����pk
*/
public void setPk_hk_store_lvyun_out_b ( String pk_hk_store_lvyun_out_b) {
this.setAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT_B,pk_hk_store_lvyun_out_b);
 } 

/** 
* ��ȡNC����
*
* @return NC����
*/
public String getPk_inv () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_INV);
 } 

/** 
* ����NC����
*
* @param pk_inv NC����
*/
public void setPk_inv ( String pk_inv) {
this.setAttributeValue( LyOutStoreBVO.PK_INV,pk_inv);
 } 

/** 
* ��ȡNC�ֿ�
*
* @return NC�ֿ�
*/
public String getPk_store () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_STORE);
 } 

/** 
* ����NC�ֿ�
*
* @param pk_store NC�ֿ�
*/
public void setPk_store ( String pk_store) {
this.setAttributeValue( LyOutStoreBVO.PK_STORE,pk_store);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getSp_out_amount () {
return (UFDouble) this.getAttributeValue( LyOutStoreBVO.SP_OUT_AMOUNT);
 } 

/** 
* ���ó�����
*
* @param sp_out_amount ������
*/
public void setSp_out_amount ( UFDouble sp_out_amount) {
this.setAttributeValue( LyOutStoreBVO.SP_OUT_AMOUNT,sp_out_amount);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getSp_out_quantity () {
return (UFDouble) this.getAttributeValue( LyOutStoreBVO.SP_OUT_QUANTITY);
 } 

/** 
* ���ó�������
*
* @param sp_out_quantity ��������
*/
public void setSp_out_quantity ( UFDouble sp_out_quantity) {
this.setAttributeValue( LyOutStoreBVO.SP_OUT_QUANTITY,sp_out_quantity);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( LyOutStoreBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( LyOutStoreBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( LyOutStoreBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( LyOutStoreBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( LyOutStoreBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( LyOutStoreBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( LyOutStoreBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( LyOutStoreBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( LyOutStoreBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( LyOutStoreBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( LyOutStoreBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( LyOutStoreBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vbdef11 �Զ�����11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( LyOutStoreBVO.VBDEF11,vbdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vbdef12 �Զ�����12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( LyOutStoreBVO.VBDEF12,vbdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vbdef13 �Զ�����13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( LyOutStoreBVO.VBDEF13,vbdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vbdef14 �Զ�����14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( LyOutStoreBVO.VBDEF14,vbdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vbdef15 �Զ�����15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( LyOutStoreBVO.VBDEF15,vbdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vbdef16 �Զ�����16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( LyOutStoreBVO.VBDEF16,vbdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vbdef17 �Զ�����17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( LyOutStoreBVO.VBDEF17,vbdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vbdef18 �Զ�����18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( LyOutStoreBVO.VBDEF18,vbdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vbdef19 �Զ�����19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( LyOutStoreBVO.VBDEF19,vbdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vbdef20 �Զ�����20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( LyOutStoreBVO.VBDEF20,vbdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( LyOutStoreBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( LyOutStoreBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( LyOutStoreBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( LyOutStoreBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( LyOutStoreBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( LyOutStoreBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( LyOutStoreBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡӪҵ��code
*
* @return Ӫҵ��code
*/
public String getYyd_code () {
return (String) this.getAttributeValue( LyOutStoreBVO.YYD_CODE);
 } 

/** 
* ����Ӫҵ��code
*
* @param yyd_code Ӫҵ��code
*/
public void setYyd_code ( String yyd_code) {
this.setAttributeValue( LyOutStoreBVO.YYD_CODE,yyd_code);
 } 

/** 
* ��ȡӪҵ��name
*
* @return Ӫҵ��name
*/
public String getYyd_name () {
return (String) this.getAttributeValue( LyOutStoreBVO.YYD_NAME);
 } 

/** 
* ����Ӫҵ��name
*
* @param yyd_name Ӫҵ��name
*/
public void setYyd_name ( String yyd_name) {
this.setAttributeValue( LyOutStoreBVO.YYD_NAME,yyd_name);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_store_lvyun_outBVO");
  }
}