package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class LyOutStoreDVO extends SuperVO {
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
*��Ʒ������
*/
public static final String CP_OUT_AMOUNT="cp_out_amount";
/**
*��Ʒ��������
*/
public static final String CP_OUT_QUANTITY="cp_out_quantity";
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
*���ϳ�����
*/
public static final String INV_OUT_AMOUNT="inv_out_amount";
/**
*���ϳ�������
*/
public static final String INV_OUT_QUANTITY="inv_out_quantity";
/**
*���Ʋ�Ʒcode
*/
public static final String LY_CP_CODE="ly_cp_code";
/**
*���Ʋ�Ʒname
*/
public static final String LY_CP_NAME="ly_cp_name";
/**
*NC�ɱ���
*/
public static final String PK_BOM="pk_bom";
/**
*NC����
*/
public static final String PK_DEPT="pk_dept";
/**
*�ϲ㵥������
*/
public static final String PK_HK_STORE_LVYUN_OUT="pk_hk_store_lvyun_out";
/**
*�ӹ�Ʒ�ɱ�pk
*/
public static final String PK_HK_STORE_LVYUN_OUT_D="pk_hk_store_lvyun_out_d";
/**
*NC����
*/
public static final String PK_INV="pk_inv";
/**
*NC�ֿ�
*/
public static final String PK_STORE="pk_store";
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
return (String) this.getAttributeValue( LyOutStoreDVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( LyOutStoreDVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( LyOutStoreDVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( LyOutStoreDVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( LyOutStoreDVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( LyOutStoreDVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ��Ʒ������
*
* @return ��Ʒ������
*/
public UFDouble getCp_out_amount () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.CP_OUT_AMOUNT);
 } 

/** 
* ���ò�Ʒ������
*
* @param cp_out_amount ��Ʒ������
*/
public void setCp_out_amount ( UFDouble cp_out_amount) {
this.setAttributeValue( LyOutStoreDVO.CP_OUT_AMOUNT,cp_out_amount);
 } 

/** 
* ��ȡ��Ʒ��������
*
* @return ��Ʒ��������
*/
public UFDouble getCp_out_quantity () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.CP_OUT_QUANTITY);
 } 

/** 
* ���ò�Ʒ��������
*
* @param cp_out_quantity ��Ʒ��������
*/
public void setCp_out_quantity ( UFDouble cp_out_quantity) {
this.setAttributeValue( LyOutStoreDVO.CP_OUT_QUANTITY,cp_out_quantity);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( LyOutStoreDVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( LyOutStoreDVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( LyOutStoreDVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( LyOutStoreDVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( LyOutStoreDVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( LyOutStoreDVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ���ϳ�����
*
* @return ���ϳ�����
*/
public UFDouble getInv_out_amount () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.INV_OUT_AMOUNT);
 } 

/** 
* ���ò��ϳ�����
*
* @param inv_out_amount ���ϳ�����
*/
public void setInv_out_amount ( UFDouble inv_out_amount) {
this.setAttributeValue( LyOutStoreDVO.INV_OUT_AMOUNT,inv_out_amount);
 } 

/** 
* ��ȡ���ϳ�������
*
* @return ���ϳ�������
*/
public UFDouble getInv_out_quantity () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.INV_OUT_QUANTITY);
 } 

/** 
* ���ò��ϳ�������
*
* @param inv_out_quantity ���ϳ�������
*/
public void setInv_out_quantity ( UFDouble inv_out_quantity) {
this.setAttributeValue( LyOutStoreDVO.INV_OUT_QUANTITY,inv_out_quantity);
 } 

/** 
* ��ȡ���Ʋ�Ʒcode
*
* @return ���Ʋ�Ʒcode
*/
public String getLy_cp_code () {
return (String) this.getAttributeValue( LyOutStoreDVO.LY_CP_CODE);
 } 

/** 
* �������Ʋ�Ʒcode
*
* @param ly_cp_code ���Ʋ�Ʒcode
*/
public void setLy_cp_code ( String ly_cp_code) {
this.setAttributeValue( LyOutStoreDVO.LY_CP_CODE,ly_cp_code);
 } 

/** 
* ��ȡ���Ʋ�Ʒname
*
* @return ���Ʋ�Ʒname
*/
public String getLy_cp_name () {
return (String) this.getAttributeValue( LyOutStoreDVO.LY_CP_NAME);
 } 

/** 
* �������Ʋ�Ʒname
*
* @param ly_cp_name ���Ʋ�Ʒname
*/
public void setLy_cp_name ( String ly_cp_name) {
this.setAttributeValue( LyOutStoreDVO.LY_CP_NAME,ly_cp_name);
 } 

/** 
* ��ȡNC�ɱ���
*
* @return NC�ɱ���
*/
public String getPk_bom () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_BOM);
 } 

/** 
* ����NC�ɱ���
*
* @param pk_bom NC�ɱ���
*/
public void setPk_bom ( String pk_bom) {
this.setAttributeValue( LyOutStoreDVO.PK_BOM,pk_bom);
 } 

/** 
* ��ȡNC����
*
* @return NC����
*/
public String getPk_dept () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_DEPT);
 } 

/** 
* ����NC����
*
* @param pk_dept NC����
*/
public void setPk_dept ( String pk_dept) {
this.setAttributeValue( LyOutStoreDVO.PK_DEPT,pk_dept);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_store_lvyun_out () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_store_lvyun_out �ϲ㵥������
*/
public void setPk_hk_store_lvyun_out ( String pk_hk_store_lvyun_out) {
this.setAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT,pk_hk_store_lvyun_out);
 } 

/** 
* ��ȡ�ӹ�Ʒ�ɱ�pk
*
* @return �ӹ�Ʒ�ɱ�pk
*/
public String getPk_hk_store_lvyun_out_d () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT_D);
 } 

/** 
* ���üӹ�Ʒ�ɱ�pk
*
* @param pk_hk_store_lvyun_out_d �ӹ�Ʒ�ɱ�pk
*/
public void setPk_hk_store_lvyun_out_d ( String pk_hk_store_lvyun_out_d) {
this.setAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT_D,pk_hk_store_lvyun_out_d);
 } 

/** 
* ��ȡNC����
*
* @return NC����
*/
public String getPk_inv () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_INV);
 } 

/** 
* ����NC����
*
* @param pk_inv NC����
*/
public void setPk_inv ( String pk_inv) {
this.setAttributeValue( LyOutStoreDVO.PK_INV,pk_inv);
 } 

/** 
* ��ȡNC�ֿ�
*
* @return NC�ֿ�
*/
public String getPk_store () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_STORE);
 } 

/** 
* ����NC�ֿ�
*
* @param pk_store NC�ֿ�
*/
public void setPk_store ( String pk_store) {
this.setAttributeValue( LyOutStoreDVO.PK_STORE,pk_store);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( LyOutStoreDVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( LyOutStoreDVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( LyOutStoreDVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( LyOutStoreDVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( LyOutStoreDVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( LyOutStoreDVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( LyOutStoreDVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( LyOutStoreDVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( LyOutStoreDVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( LyOutStoreDVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( LyOutStoreDVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( LyOutStoreDVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vbdef11 �Զ�����11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( LyOutStoreDVO.VBDEF11,vbdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vbdef12 �Զ�����12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( LyOutStoreDVO.VBDEF12,vbdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vbdef13 �Զ�����13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( LyOutStoreDVO.VBDEF13,vbdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vbdef14 �Զ�����14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( LyOutStoreDVO.VBDEF14,vbdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vbdef15 �Զ�����15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( LyOutStoreDVO.VBDEF15,vbdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vbdef16 �Զ�����16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( LyOutStoreDVO.VBDEF16,vbdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vbdef17 �Զ�����17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( LyOutStoreDVO.VBDEF17,vbdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vbdef18 �Զ�����18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( LyOutStoreDVO.VBDEF18,vbdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vbdef19 �Զ�����19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( LyOutStoreDVO.VBDEF19,vbdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vbdef20 �Զ�����20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( LyOutStoreDVO.VBDEF20,vbdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( LyOutStoreDVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( LyOutStoreDVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( LyOutStoreDVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( LyOutStoreDVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( LyOutStoreDVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( LyOutStoreDVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( LyOutStoreDVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡӪҵ��code
*
* @return Ӫҵ��code
*/
public String getYyd_code () {
return (String) this.getAttributeValue( LyOutStoreDVO.YYD_CODE);
 } 

/** 
* ����Ӫҵ��code
*
* @param yyd_code Ӫҵ��code
*/
public void setYyd_code ( String yyd_code) {
this.setAttributeValue( LyOutStoreDVO.YYD_CODE,yyd_code);
 } 

/** 
* ��ȡӪҵ��name
*
* @return Ӫҵ��name
*/
public String getYyd_name () {
return (String) this.getAttributeValue( LyOutStoreDVO.YYD_NAME);
 } 

/** 
* ����Ӫҵ��name
*
* @param yyd_name Ӫҵ��name
*/
public void setYyd_name ( String yyd_name) {
this.setAttributeValue( LyOutStoreDVO.YYD_NAME,yyd_name);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_store_lvyun_outDVO");
  }
}