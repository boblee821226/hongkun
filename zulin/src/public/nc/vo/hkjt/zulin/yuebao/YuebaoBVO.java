package nc.vo.hkjt.zulin.yuebao;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class YuebaoBVO extends SuperVO {
/**
*�����տ���
*/
public UFDouble bqskje;
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
public UFDouble danjia;
/**
*��������ȷ�Ͻ��
*/
public UFDouble dysrqrje;
/**
*��������ȷ������
*/
public UFDouble dysrqrts;
/**
*����
*/
public String itype;
/**
*�����������
*/
public UFDate jsjsrq;
/**
*���㿪ʼ����
*/
public UFDate jsksrq;
/**
*�������
*/
public UFDouble mianji;
/**
*�ͻ�
*/
public String pk_cutomer;
/**
*�ϲ㵥������
*/
public static final String PK_HK_ZULIN_YUEBAO="pk_hk_zulin_yuebao";
/**
*�����±��ӱ�pk
*/
public static final String PK_HK_ZULIN_YUEBAO_B="pk_hk_zulin_yuebao_b";
/**
*�ڳ�Ԥ�տ����
*/
public UFDouble qcyskye;
/**
*��ĩԤ�տ����
*/
public UFDouble qmyskye;
/**
*����
*/
public String quyu;
/**
*�����
*/
public String roomno;
/**
*������Ŀ
*/
public String srxm;
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01  ���ͻ����ƣ�
*/
public static final String VBDEF01="vbdef01";
/**
*�Զ�����02  ������ţ�
*/
public static final String VBDEF02="vbdef02";
/**
*�Զ�����03  ��������Ŀ��
*/
public static final String VBDEF03="vbdef03";
/**
*�Զ�����04  ������
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
* ��ȡ�����տ���
*
* @return �����տ���
*/
public UFDouble getBqskje () {
return this.bqskje;
 } 

/** 
* ���ñ����տ���
*
* @param bqskje �����տ���
*/
public void setBqskje ( UFDouble bqskje) {
this.bqskje=bqskje;
 } 

/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( YuebaoBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( YuebaoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( YuebaoBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( YuebaoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( YuebaoBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( YuebaoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( YuebaoBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( YuebaoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( YuebaoBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( YuebaoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( YuebaoBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( YuebaoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getDanjia () {
return this.danjia;
 } 

/** 
* ���õ���
*
* @param danjia ����
*/
public void setDanjia ( UFDouble danjia) {
this.danjia=danjia;
 } 

/** 
* ��ȡ��������ȷ�Ͻ��
*
* @return ��������ȷ�Ͻ��
*/
public UFDouble getDysrqrje () {
return this.dysrqrje;
 } 

/** 
* ���õ�������ȷ�Ͻ��
*
* @param dysrqrje ��������ȷ�Ͻ��
*/
public void setDysrqrje ( UFDouble dysrqrje) {
this.dysrqrje=dysrqrje;
 } 

/** 
* ��ȡ��������ȷ������
*
* @return ��������ȷ������
*/
public UFDouble getDysrqrts () {
return this.dysrqrts;
 } 

/** 
* ���õ�������ȷ������
*
* @param dysrqrts ��������ȷ������
*/
public void setDysrqrts ( UFDouble dysrqrts) {
this.dysrqrts=dysrqrts;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getItype () {
return this.itype;
 } 

/** 
* ��������
*
* @param itype ����
*/
public void setItype ( String itype) {
this.itype=itype;
 } 

/** 
* ��ȡ�����������
*
* @return �����������
*/
public UFDate getJsjsrq () {
return this.jsjsrq;
 } 

/** 
* ���ü����������
*
* @param jsjsrq �����������
*/
public void setJsjsrq ( UFDate jsjsrq) {
this.jsjsrq=jsjsrq;
 } 

/** 
* ��ȡ���㿪ʼ����
*
* @return ���㿪ʼ����
*/
public UFDate getJsksrq () {
return this.jsksrq;
 } 

/** 
* ���ü��㿪ʼ����
*
* @param jsksrq ���㿪ʼ����
*/
public void setJsksrq ( UFDate jsksrq) {
this.jsksrq=jsksrq;
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getMianji () {
return this.mianji;
 } 

/** 
* ���ó������
*
* @param mianji �������
*/
public void setMianji ( UFDouble mianji) {
this.mianji=mianji;
 } 

/** 
* ��ȡ�ͻ�
*
* @return �ͻ�
*/
public String getPk_cutomer () {
return this.pk_cutomer;
 } 

/** 
* ���ÿͻ�
*
* @param pk_cutomer �ͻ�
*/
public void setPk_cutomer ( String pk_cutomer) {
this.pk_cutomer=pk_cutomer;
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_zulin_yuebao () {
return (String) this.getAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_zulin_yuebao �ϲ㵥������
*/
public void setPk_hk_zulin_yuebao ( String pk_hk_zulin_yuebao) {
this.setAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO,pk_hk_zulin_yuebao);
 } 

/** 
* ��ȡ�����±��ӱ�pk
*
* @return �����±��ӱ�pk
*/
public String getPk_hk_zulin_yuebao_b () {
return (String) this.getAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO_B);
 } 

/** 
* ���������±��ӱ�pk
*
* @param pk_hk_zulin_yuebao_b �����±��ӱ�pk
*/
public void setPk_hk_zulin_yuebao_b ( String pk_hk_zulin_yuebao_b) {
this.setAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO_B,pk_hk_zulin_yuebao_b);
 } 

/** 
* ��ȡ�ڳ�Ԥ�տ����
*
* @return �ڳ�Ԥ�տ����
*/
public UFDouble getQcyskye () {
return this.qcyskye;
 } 

/** 
* �����ڳ�Ԥ�տ����
*
* @param qcyskye �ڳ�Ԥ�տ����
*/
public void setQcyskye ( UFDouble qcyskye) {
this.qcyskye=qcyskye;
 } 

/** 
* ��ȡ��ĩԤ�տ����
*
* @return ��ĩԤ�տ����
*/
public UFDouble getQmyskye () {
return this.qmyskye;
 } 

/** 
* ������ĩԤ�տ����
*
* @param qmyskye ��ĩԤ�տ����
*/
public void setQmyskye ( UFDouble qmyskye) {
this.qmyskye=qmyskye;
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getQuyu () {
return this.quyu;
 } 

/** 
* ��������
*
* @param quyu ����
*/
public void setQuyu ( String quyu) {
this.quyu=quyu;
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public String getRoomno () {
return this.roomno;
 } 

/** 
* ���÷����
*
* @param roomno �����
*/
public void setRoomno ( String roomno) {
this.roomno=roomno;
 } 

/** 
* ��ȡ������Ŀ
*
* @return ������Ŀ
*/
public String getSrxm () {
return this.srxm;
 } 

/** 
* ����������Ŀ
*
* @param srxm ������Ŀ
*/
public void setSrxm ( String srxm) {
this.srxm=srxm;
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( YuebaoBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( YuebaoBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( YuebaoBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( YuebaoBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( YuebaoBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( YuebaoBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( YuebaoBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( YuebaoBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( YuebaoBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( YuebaoBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( YuebaoBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( YuebaoBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( YuebaoBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( YuebaoBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( YuebaoBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( YuebaoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( YuebaoBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( YuebaoBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( YuebaoBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( YuebaoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_zulin_yuebaoBVO");
  }
}