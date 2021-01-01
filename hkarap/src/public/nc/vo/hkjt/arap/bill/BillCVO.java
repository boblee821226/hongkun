package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillCVO extends SuperVO {
/**
*Ӧ���˺�
*/
public static final String ACCNT="accnt";
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
*��������
*/
public static final String CHARGE="charge";
/**
*����ʱ��
*/
public static final String CREATE_DATETIME="create_datetime";
/**
*������
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
*����id
*/
public static final String ID="id";
/**
*��������
*/
public static final String PAY="pay";
/**
*�ϲ㵥������
*/
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*��������pk
*/
public static final String PK_HK_ARAP_BILL_C="pk_hk_arap_bill_c";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01
*/
public static final String VCDEF01="vcdef01";
/**
*�Զ�����02
*/
public static final String VCDEF02="vcdef02";
/**
*�Զ�����03
*/
public static final String VCDEF03="vcdef03";
/**
*�Զ�����04
*/
public static final String VCDEF04="vcdef04";
/**
*�Զ�����05
*/
public static final String VCDEF05="vcdef05";
/**
*�Զ�����06
*/
public static final String VCDEF06="vcdef06";
/**
*�Զ�����07
*/
public static final String VCDEF07="vcdef07";
/**
*�Զ�����08
*/
public static final String VCDEF08="vcdef08";
/**
*�Զ�����09
*/
public static final String VCDEF09="vcdef09";
/**
*�Զ�����10
*/
public static final String VCDEF10="vcdef10";
/**
*�Զ�����11
*/
public static final String VCDEF11="vcdef11";
/**
*�Զ�����12
*/
public static final String VCDEF12="vcdef12";
/**
*�Զ�����13
*/
public static final String VCDEF13="vcdef13";
/**
*�Զ�����14
*/
public static final String VCDEF14="vcdef14";
/**
*�Զ�����15
*/
public static final String VCDEF15="vcdef15";
/**
*�Զ�����16
*/
public static final String VCDEF16="vcdef16";
/**
*�Զ�����17
*/
public static final String VCDEF17="vcdef17";
/**
*�Զ�����18
*/
public static final String VCDEF18="vcdef18";
/**
*�Զ�����19
*/
public static final String VCDEF19="vcdef19";
/**
*�Զ�����20
*/
public static final String VCDEF20="vcdef20";
/**
*��ע
*/
public static final String VCMEMO="vcmemo";
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
* ��ȡӦ���˺�
*
* @return Ӧ���˺�
*/
public Integer getAccnt () {
return (Integer) this.getAttributeValue( BillCVO.ACCNT);
 } 

/** 
* ����Ӧ���˺�
*
* @param accnt Ӧ���˺�
*/
public void setAccnt ( Integer accnt) {
this.setAttributeValue( BillCVO.ACCNT,accnt);
 } 

/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( BillCVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( BillCVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( BillCVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( BillCVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( BillCVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( BillCVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getCharge () {
return (UFDouble) this.getAttributeValue( BillCVO.CHARGE);
 } 

/** 
* ���ú�������
*
* @param charge ��������
*/
public void setCharge ( UFDouble charge) {
this.setAttributeValue( BillCVO.CHARGE,charge);
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public String getCreate_datetime () {
return (String) this.getAttributeValue( BillCVO.CREATE_DATETIME);
 } 

/** 
* ���ò���ʱ��
*
* @param create_datetime ����ʱ��
*/
public void setCreate_datetime ( String create_datetime) {
this.setAttributeValue( BillCVO.CREATE_DATETIME,create_datetime);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getCreate_user () {
return (String) this.getAttributeValue( BillCVO.CREATE_USER);
 } 

/** 
* ���ò�����
*
* @param create_user ������
*/
public void setCreate_user ( String create_user) {
this.setAttributeValue( BillCVO.CREATE_USER,create_user);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( BillCVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( BillCVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( BillCVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( BillCVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( BillCVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( BillCVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ����id
*
* @return ����id
*/
public Integer getId () {
return (Integer) this.getAttributeValue( BillCVO.ID);
 } 

/** 
* ��������id
*
* @param id ����id
*/
public void setId ( Integer id) {
this.setAttributeValue( BillCVO.ID,id);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getPay () {
return (UFDouble) this.getAttributeValue( BillCVO.PAY);
 } 

/** 
* ���ú�������
*
* @param pay ��������
*/
public void setPay ( UFDouble pay) {
this.setAttributeValue( BillCVO.PAY,pay);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillCVO.PK_HK_ARAP_BILL);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_arap_bill �ϲ㵥������
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillCVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* ��ȡ��������pk
*
* @return ��������pk
*/
public String getPk_hk_arap_bill_c () {
return (String) this.getAttributeValue( BillCVO.PK_HK_ARAP_BILL_C);
 } 

/** 
* ���ú�������pk
*
* @param pk_hk_arap_bill_c ��������pk
*/
public void setPk_hk_arap_bill_c ( String pk_hk_arap_bill_c) {
this.setAttributeValue( BillCVO.PK_HK_ARAP_BILL_C,pk_hk_arap_bill_c);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillCVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillCVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVcdef01 () {
return (String) this.getAttributeValue( BillCVO.VCDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vcdef01 �Զ�����01
*/
public void setVcdef01 ( String vcdef01) {
this.setAttributeValue( BillCVO.VCDEF01,vcdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVcdef02 () {
return (String) this.getAttributeValue( BillCVO.VCDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vcdef02 �Զ�����02
*/
public void setVcdef02 ( String vcdef02) {
this.setAttributeValue( BillCVO.VCDEF02,vcdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVcdef03 () {
return (String) this.getAttributeValue( BillCVO.VCDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vcdef03 �Զ�����03
*/
public void setVcdef03 ( String vcdef03) {
this.setAttributeValue( BillCVO.VCDEF03,vcdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVcdef04 () {
return (String) this.getAttributeValue( BillCVO.VCDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vcdef04 �Զ�����04
*/
public void setVcdef04 ( String vcdef04) {
this.setAttributeValue( BillCVO.VCDEF04,vcdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVcdef05 () {
return (String) this.getAttributeValue( BillCVO.VCDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vcdef05 �Զ�����05
*/
public void setVcdef05 ( String vcdef05) {
this.setAttributeValue( BillCVO.VCDEF05,vcdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVcdef06 () {
return (String) this.getAttributeValue( BillCVO.VCDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vcdef06 �Զ�����06
*/
public void setVcdef06 ( String vcdef06) {
this.setAttributeValue( BillCVO.VCDEF06,vcdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVcdef07 () {
return (String) this.getAttributeValue( BillCVO.VCDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vcdef07 �Զ�����07
*/
public void setVcdef07 ( String vcdef07) {
this.setAttributeValue( BillCVO.VCDEF07,vcdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVcdef08 () {
return (String) this.getAttributeValue( BillCVO.VCDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vcdef08 �Զ�����08
*/
public void setVcdef08 ( String vcdef08) {
this.setAttributeValue( BillCVO.VCDEF08,vcdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVcdef09 () {
return (String) this.getAttributeValue( BillCVO.VCDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vcdef09 �Զ�����09
*/
public void setVcdef09 ( String vcdef09) {
this.setAttributeValue( BillCVO.VCDEF09,vcdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVcdef10 () {
return (String) this.getAttributeValue( BillCVO.VCDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vcdef10 �Զ�����10
*/
public void setVcdef10 ( String vcdef10) {
this.setAttributeValue( BillCVO.VCDEF10,vcdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVcdef11 () {
return (String) this.getAttributeValue( BillCVO.VCDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vcdef11 �Զ�����11
*/
public void setVcdef11 ( String vcdef11) {
this.setAttributeValue( BillCVO.VCDEF11,vcdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVcdef12 () {
return (String) this.getAttributeValue( BillCVO.VCDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vcdef12 �Զ�����12
*/
public void setVcdef12 ( String vcdef12) {
this.setAttributeValue( BillCVO.VCDEF12,vcdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVcdef13 () {
return (String) this.getAttributeValue( BillCVO.VCDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vcdef13 �Զ�����13
*/
public void setVcdef13 ( String vcdef13) {
this.setAttributeValue( BillCVO.VCDEF13,vcdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVcdef14 () {
return (String) this.getAttributeValue( BillCVO.VCDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vcdef14 �Զ�����14
*/
public void setVcdef14 ( String vcdef14) {
this.setAttributeValue( BillCVO.VCDEF14,vcdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVcdef15 () {
return (String) this.getAttributeValue( BillCVO.VCDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vcdef15 �Զ�����15
*/
public void setVcdef15 ( String vcdef15) {
this.setAttributeValue( BillCVO.VCDEF15,vcdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVcdef16 () {
return (String) this.getAttributeValue( BillCVO.VCDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vcdef16 �Զ�����16
*/
public void setVcdef16 ( String vcdef16) {
this.setAttributeValue( BillCVO.VCDEF16,vcdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVcdef17 () {
return (String) this.getAttributeValue( BillCVO.VCDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vcdef17 �Զ�����17
*/
public void setVcdef17 ( String vcdef17) {
this.setAttributeValue( BillCVO.VCDEF17,vcdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVcdef18 () {
return (String) this.getAttributeValue( BillCVO.VCDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vcdef18 �Զ�����18
*/
public void setVcdef18 ( String vcdef18) {
this.setAttributeValue( BillCVO.VCDEF18,vcdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVcdef19 () {
return (String) this.getAttributeValue( BillCVO.VCDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vcdef19 �Զ�����19
*/
public void setVcdef19 ( String vcdef19) {
this.setAttributeValue( BillCVO.VCDEF19,vcdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVcdef20 () {
return (String) this.getAttributeValue( BillCVO.VCDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vcdef20 �Զ�����20
*/
public void setVcdef20 ( String vcdef20) {
this.setAttributeValue( BillCVO.VCDEF20,vcdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVcmemo () {
return (String) this.getAttributeValue( BillCVO.VCMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vcmemo ��ע
*/
public void setVcmemo ( String vcmemo) {
this.setAttributeValue( BillCVO.VCMEMO,vcmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( BillCVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( BillCVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( BillCVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( BillCVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( BillCVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( BillCVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billCVO");
  }
}