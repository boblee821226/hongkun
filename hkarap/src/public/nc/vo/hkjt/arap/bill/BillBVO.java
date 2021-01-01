package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillBVO extends SuperVO {
/**
*Ӧ���˻�
*/
public static final String ACCNT="accnt";
/**
*�����ʶ
*/
public static final String ACT_TAG="act_tag";
/**
*��˱�ʶ
*/
public static final String AUDIT_TAG="audit_tag";
/**
*���
*/
public static final String BALANCE9="balance9";
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
*���ѽ��
*/
public static final String CHARGE="charge";
/**
*���Ѻ���
*/
public static final String CHARGE9="charge9";
/**
*���˵���
*/
public static final String CLOSE_ID="close_id";
/**
*����ʱ��
*/
public static final String CREATE_DATETIME="create_datetime";
/**
*�����û���
*/
public static final String CREATE_USER="create_user";
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
*ҵ������
*/
public static final String GEN_DATE="gen_date";
/**
*��������
*/
public static final String GUEST_NAME="guest_name";
/**
*����id
*/
public static final String ID="id";
/**
*ģ�����
*/
public static final String MODU_CODE="modu_code";
/**
*nc����
*/
public static final String NC_DZ_01="nc_dz_01";
/**
*nc�������
*/
public static final String NC_GZSJ_01="nc_gzsj_01";
/**
*nc����
*/
public static final String NC_HX_01="nc_hx_01";
/**
*nc�������
*/
public static final String NC_JSSJ_01="nc_jssj_01";
/**
*nc�ڲ�����
*/
public static final String NC_NBWL_01="nc_nbwl_01";
/**
*nc�˿�
*/
public static final String NC_TK_01="nc_tk_01";
/**
*ncӶ��Ʊ
*/
public static final String NC_YJFP_01="nc_yjfp_01";
/**
*�˴�
*/
public static final String NUMBER_LY="number_ly";
/**
*����
*/
public static final String PAY="pay";
/**
*�������
*/
public static final String PAY9="pay9";
/**
*�ϲ㵥������
*/
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*��������pk
*/
public static final String PK_HK_ARAP_BILL_B="pk_hk_arap_bill_b";
/**
*�����
*/
public static final String RMNO="rmno";
/**
*���˺�
*/
public static final String SUBACCNT="subaccnt";
/**
*�������
*/
public static final String TA_CODE="ta_code";
/**
*��Ŀ����
*/
public static final String TA_DESCRIPT="ta_descript";
/**
*����
*/
public static final String TA_NO="ta_no";
/**
*ժҪ
*/
public static final String TA_REMARK="ta_remark";
/**
*ת���˺�
*/
public static final String TRANS_ACCNT="trans_accnt";
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
* ��ȡӦ���˻�
*
* @return Ӧ���˻�
*/
public String getAccnt () {
return (String) this.getAttributeValue( BillBVO.ACCNT);
 } 

/** 
* ����Ӧ���˻�
*
* @param accnt Ӧ���˻�
*/
public void setAccnt ( String accnt) {
this.setAttributeValue( BillBVO.ACCNT,accnt);
 } 

/** 
* ��ȡ�����ʶ
*
* @return �����ʶ
*/
public String getAct_tag () {
return (String) this.getAttributeValue( BillBVO.ACT_TAG);
 } 

/** 
* ���������ʶ
*
* @param act_tag �����ʶ
*/
public void setAct_tag ( String act_tag) {
this.setAttributeValue( BillBVO.ACT_TAG,act_tag);
 } 

/** 
* ��ȡ��˱�ʶ
*
* @return ��˱�ʶ
*/
public String getAudit_tag () {
return (String) this.getAttributeValue( BillBVO.AUDIT_TAG);
 } 

/** 
* ������˱�ʶ
*
* @param audit_tag ��˱�ʶ
*/
public void setAudit_tag ( String audit_tag) {
this.setAttributeValue( BillBVO.AUDIT_TAG,audit_tag);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getBalance9 () {
return (UFDouble) this.getAttributeValue( BillBVO.BALANCE9);
 } 

/** 
* �������
*
* @param balance9 ���
*/
public void setBalance9 ( UFDouble balance9) {
this.setAttributeValue( BillBVO.BALANCE9,balance9);
 } 

/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( BillBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( BillBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( BillBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( BillBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( BillBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( BillBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ���ѽ��
*
* @return ���ѽ��
*/
public UFDouble getCharge () {
return (UFDouble) this.getAttributeValue( BillBVO.CHARGE);
 } 

/** 
* �������ѽ��
*
* @param charge ���ѽ��
*/
public void setCharge ( UFDouble charge) {
this.setAttributeValue( BillBVO.CHARGE,charge);
 } 

/** 
* ��ȡ���Ѻ���
*
* @return ���Ѻ���
*/
public UFDouble getCharge9 () {
return (UFDouble) this.getAttributeValue( BillBVO.CHARGE9);
 } 

/** 
* �������Ѻ���
*
* @param charge9 ���Ѻ���
*/
public void setCharge9 ( UFDouble charge9) {
this.setAttributeValue( BillBVO.CHARGE9,charge9);
 } 

/** 
* ��ȡ���˵���
*
* @return ���˵���
*/
public Integer getClose_id () {
return (Integer) this.getAttributeValue( BillBVO.CLOSE_ID);
 } 

/** 
* ���ý��˵���
*
* @param close_id ���˵���
*/
public void setClose_id ( Integer close_id) {
this.setAttributeValue( BillBVO.CLOSE_ID,close_id);
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public String getCreate_datetime () {
return (String) this.getAttributeValue( BillBVO.CREATE_DATETIME);
 } 

/** 
* ��������ʱ��
*
* @param create_datetime ����ʱ��
*/
public void setCreate_datetime ( String create_datetime) {
this.setAttributeValue( BillBVO.CREATE_DATETIME,create_datetime);
 } 

/** 
* ��ȡ�����û���
*
* @return �����û���
*/
public String getCreate_user () {
return (String) this.getAttributeValue( BillBVO.CREATE_USER);
 } 

/** 
* ���ò����û���
*
* @param create_user �����û���
*/
public void setCreate_user ( String create_user) {
this.setAttributeValue( BillBVO.CREATE_USER,create_user);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( BillBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( BillBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( BillBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( BillBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( BillBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( BillBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getGen_date () {
return (String) this.getAttributeValue( BillBVO.GEN_DATE);
 } 

/** 
* ����ҵ������
*
* @param gen_date ҵ������
*/
public void setGen_date ( String gen_date) {
this.setAttributeValue( BillBVO.GEN_DATE,gen_date);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getGuest_name () {
return (String) this.getAttributeValue( BillBVO.GUEST_NAME);
 } 

/** 
* ���ÿ�������
*
* @param guest_name ��������
*/
public void setGuest_name ( String guest_name) {
this.setAttributeValue( BillBVO.GUEST_NAME,guest_name);
 } 

/** 
* ��ȡ����id
*
* @return ����id
*/
public Integer getId () {
return (Integer) this.getAttributeValue( BillBVO.ID);
 } 

/** 
* ��������id
*
* @param id ����id
*/
public void setId ( Integer id) {
this.setAttributeValue( BillBVO.ID,id);
 } 

/** 
* ��ȡģ�����
*
* @return ģ�����
*/
public String getModu_code () {
return (String) this.getAttributeValue( BillBVO.MODU_CODE);
 } 

/** 
* ����ģ�����
*
* @param modu_code ģ�����
*/
public void setModu_code ( String modu_code) {
this.setAttributeValue( BillBVO.MODU_CODE,modu_code);
 } 

/** 
* ��ȡnc����
*
* @return nc����
*/
public String getNc_dz_01 () {
return (String) this.getAttributeValue( BillBVO.NC_DZ_01);
 } 

/** 
* ����nc����
*
* @param nc_dz_01 nc����
*/
public void setNc_dz_01 ( String nc_dz_01) {
this.setAttributeValue( BillBVO.NC_DZ_01,nc_dz_01);
 } 

/** 
* ��ȡnc�������
*
* @return nc�������
*/
public String getNc_gzsj_01 () {
return (String) this.getAttributeValue( BillBVO.NC_GZSJ_01);
 } 

/** 
* ����nc�������
*
* @param nc_gzsj_01 nc�������
*/
public void setNc_gzsj_01 ( String nc_gzsj_01) {
this.setAttributeValue( BillBVO.NC_GZSJ_01,nc_gzsj_01);
 } 

/** 
* ��ȡnc����
*
* @return nc����
*/
public String getNc_hx_01 () {
return (String) this.getAttributeValue( BillBVO.NC_HX_01);
 } 

/** 
* ����nc����
*
* @param nc_hx_01 nc����
*/
public void setNc_hx_01 ( String nc_hx_01) {
this.setAttributeValue( BillBVO.NC_HX_01,nc_hx_01);
 } 

/** 
* ��ȡnc�������
*
* @return nc�������
*/
public String getNc_jssj_01 () {
return (String) this.getAttributeValue( BillBVO.NC_JSSJ_01);
 } 

/** 
* ����nc�������
*
* @param nc_jssj_01 nc�������
*/
public void setNc_jssj_01 ( String nc_jssj_01) {
this.setAttributeValue( BillBVO.NC_JSSJ_01,nc_jssj_01);
 } 

/** 
* ��ȡnc�ڲ�����
*
* @return nc�ڲ�����
*/
public String getNc_nbwl_01 () {
return (String) this.getAttributeValue( BillBVO.NC_NBWL_01);
 } 

/** 
* ����nc�ڲ�����
*
* @param nc_nbwl_01 nc�ڲ�����
*/
public void setNc_nbwl_01 ( String nc_nbwl_01) {
this.setAttributeValue( BillBVO.NC_NBWL_01,nc_nbwl_01);
 } 

/** 
* ��ȡnc�˿�
*
* @return nc�˿�
*/
public String getNc_tk_01 () {
return (String) this.getAttributeValue( BillBVO.NC_TK_01);
 } 

/** 
* ����nc�˿�
*
* @param nc_tk_01 nc�˿�
*/
public void setNc_tk_01 ( String nc_tk_01) {
this.setAttributeValue( BillBVO.NC_TK_01,nc_tk_01);
 } 

/** 
* ��ȡncӶ��Ʊ
*
* @return ncӶ��Ʊ
*/
public String getNc_yjfp_01 () {
return (String) this.getAttributeValue( BillBVO.NC_YJFP_01);
 } 

/** 
* ����ncӶ��Ʊ
*
* @param nc_yjfp_01 ncӶ��Ʊ
*/
public void setNc_yjfp_01 ( String nc_yjfp_01) {
this.setAttributeValue( BillBVO.NC_YJFP_01,nc_yjfp_01);
 } 

/** 
* ��ȡ�˴�
*
* @return �˴�
*/
public Integer getNumber_ly () {
return (Integer) this.getAttributeValue( BillBVO.NUMBER_LY);
 } 

/** 
* �����˴�
*
* @param number_ly �˴�
*/
public void setNumber_ly ( Integer number_ly) {
this.setAttributeValue( BillBVO.NUMBER_LY,number_ly);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getPay () {
return (UFDouble) this.getAttributeValue( BillBVO.PAY);
 } 

/** 
* ���ø���
*
* @param pay ����
*/
public void setPay ( UFDouble pay) {
this.setAttributeValue( BillBVO.PAY,pay);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getPay9 () {
return (UFDouble) this.getAttributeValue( BillBVO.PAY9);
 } 

/** 
* ���ø������
*
* @param pay9 �������
*/
public void setPay9 ( UFDouble pay9) {
this.setAttributeValue( BillBVO.PAY9,pay9);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillBVO.PK_HK_ARAP_BILL);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_arap_bill �ϲ㵥������
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillBVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* ��ȡ��������pk
*
* @return ��������pk
*/
public String getPk_hk_arap_bill_b () {
return (String) this.getAttributeValue( BillBVO.PK_HK_ARAP_BILL_B);
 } 

/** 
* ������������pk
*
* @param pk_hk_arap_bill_b ��������pk
*/
public void setPk_hk_arap_bill_b ( String pk_hk_arap_bill_b) {
this.setAttributeValue( BillBVO.PK_HK_ARAP_BILL_B,pk_hk_arap_bill_b);
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public String getRmno () {
return (String) this.getAttributeValue( BillBVO.RMNO);
 } 

/** 
* ���÷����
*
* @param rmno �����
*/
public void setRmno ( String rmno) {
this.setAttributeValue( BillBVO.RMNO,rmno);
 } 

/** 
* ��ȡ���˺�
*
* @return ���˺�
*/
public Integer getSubaccnt () {
return (Integer) this.getAttributeValue( BillBVO.SUBACCNT);
 } 

/** 
* ���÷��˺�
*
* @param subaccnt ���˺�
*/
public void setSubaccnt ( Integer subaccnt) {
this.setAttributeValue( BillBVO.SUBACCNT,subaccnt);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public String getTa_code () {
return (String) this.getAttributeValue( BillBVO.TA_CODE);
 } 

/** 
* �����������
*
* @param ta_code �������
*/
public void setTa_code ( String ta_code) {
this.setAttributeValue( BillBVO.TA_CODE,ta_code);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getTa_descript () {
return (String) this.getAttributeValue( BillBVO.TA_DESCRIPT);
 } 

/** 
* ������Ŀ����
*
* @param ta_descript ��Ŀ����
*/
public void setTa_descript ( String ta_descript) {
this.setAttributeValue( BillBVO.TA_DESCRIPT,ta_descript);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getTa_no () {
return (String) this.getAttributeValue( BillBVO.TA_NO);
 } 

/** 
* ���õ���
*
* @param ta_no ����
*/
public void setTa_no ( String ta_no) {
this.setAttributeValue( BillBVO.TA_NO,ta_no);
 } 

/** 
* ��ȡժҪ
*
* @return ժҪ
*/
public String getTa_remark () {
return (String) this.getAttributeValue( BillBVO.TA_REMARK);
 } 

/** 
* ����ժҪ
*
* @param ta_remark ժҪ
*/
public void setTa_remark ( String ta_remark) {
this.setAttributeValue( BillBVO.TA_REMARK,ta_remark);
 } 

/** 
* ��ȡת���˺�
*
* @return ת���˺�
*/
public Integer getTrans_accnt () {
return (Integer) this.getAttributeValue( BillBVO.TRANS_ACCNT);
 } 

/** 
* ����ת���˺�
*
* @param trans_accnt ת���˺�
*/
public void setTrans_accnt ( Integer trans_accnt) {
this.setAttributeValue( BillBVO.TRANS_ACCNT,trans_accnt);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( BillBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( BillBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( BillBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( BillBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( BillBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( BillBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( BillBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( BillBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( BillBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( BillBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( BillBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( BillBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( BillBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( BillBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( BillBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( BillBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( BillBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( BillBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( BillBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( BillBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( BillBVO.VBDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vbdef11 �Զ�����11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( BillBVO.VBDEF11,vbdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( BillBVO.VBDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vbdef12 �Զ�����12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( BillBVO.VBDEF12,vbdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( BillBVO.VBDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vbdef13 �Զ�����13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( BillBVO.VBDEF13,vbdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( BillBVO.VBDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vbdef14 �Զ�����14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( BillBVO.VBDEF14,vbdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( BillBVO.VBDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vbdef15 �Զ�����15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( BillBVO.VBDEF15,vbdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( BillBVO.VBDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vbdef16 �Զ�����16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( BillBVO.VBDEF16,vbdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( BillBVO.VBDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vbdef17 �Զ�����17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( BillBVO.VBDEF17,vbdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( BillBVO.VBDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vbdef18 �Զ�����18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( BillBVO.VBDEF18,vbdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( BillBVO.VBDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vbdef19 �Զ�����19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( BillBVO.VBDEF19,vbdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( BillBVO.VBDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vbdef20 �Զ�����20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( BillBVO.VBDEF20,vbdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( BillBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( BillBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( BillBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( BillBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( BillBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( BillBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( BillBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( BillBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billBVO");
  }
}