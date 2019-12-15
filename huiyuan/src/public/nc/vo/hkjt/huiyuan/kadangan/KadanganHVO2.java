package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KadanganHVO2 extends SuperVO {
/**
*�����
*/
public static final String APPROVER="approver";
/**
*�Ƶ�ʱ��
*/
public static final String CREATIONTIME="creationtime";
/**
*�Ƶ���
*/
public static final String CREATOR="creator";
/**
*��������
*/
public static final String DBILLDATE="dbilldate";
/**
*��ǰ���
*/
public static final String DQ_YE="dq_ye";
/**
*����״̬
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*��Ա����
*/
public static final String KA_CODE="ka_code";
/**
*��Ա����
*/
public static final String KA_NAME="ka_name";
/**
*������
*/
public static final String KABILI="kabili";
/**
*��״̬
*/
public static final String KASTATUS="kastatus";
/**
*�ɿ�Ʊ�ܶ�
*/
public static final String KKPZE="kkpze";
/**
*�޸�ʱ��
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*�޸���
*/
public static final String MODIFIER="modifier";
/**
*ҵ������
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*����
*/
public static final String PK_GROUP="pk_group";
/**
*��Ա������
*/
public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_KAXING="pk_hk_huiyuan_kaxing";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*�ڳ����
*/
public static final String QC_YE="qc_ye";
/**
*���ʱ��
*/
public static final String TAUDITTIME="taudittime";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*��������
*/
public static final String VAPPROVENOTE="vapprovenote";
/**
*���ݱ��
*/
public static final String VBILLCODE="vbillcode";
/**
*��������
*/
public static final String VBILLTYPECODE="vbilltypecode";
/**
*�Զ�����01
*/
public static final String VDEF01="vdef01";
/**
*�Զ�����02
*/
public static final String VDEF02="vdef02";
/**
*�Զ�����03
*/
public static final String VDEF03="vdef03";
/**
*�Զ�����04
*/
public static final String VDEF04="vdef04";
/**
*�Զ�����05
*/
public static final String VDEF05="vdef05";
/**
*�Զ�����06
*/
public static final String VDEF06="vdef06";
/**
*�Զ�����07
*/
public static final String VDEF07="vdef07";
/**
*�Զ�����08
*/
public static final String VDEF08="vdef08";
/**
*�Զ�����09
*/
public static final String VDEF09="vdef09";
/**
*�Զ�����10
*/
public static final String VDEF10="vdef10";
/**
*��ע
*/
public static final String VMEMO="vmemo";
/**
*��������
*/
public static final String VTRANTYPECODE="vtrantypecode";
/**
*�ѿ�Ʊ���
*/
public static final String YKPJE="ykpje";
/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return (String) this.getAttributeValue( KadanganHVO2.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( KadanganHVO2.APPROVER,approver);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( KadanganHVO2.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( KadanganHVO2.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( KadanganHVO2.CREATOR,creator);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( KadanganHVO2.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( KadanganHVO2.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡ��ǰ���
*
* @return ��ǰ���
*/
public UFDouble getDq_ye () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.DQ_YE);
 } 

/** 
* ���õ�ǰ���
*
* @param dq_ye ��ǰ���
*/
public void setDq_ye ( UFDouble dq_ye) {
this.setAttributeValue( KadanganHVO2.DQ_YE,dq_ye);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( KadanganHVO2.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( KadanganHVO2.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ��Ա����
*
* @return ��Ա����
*/
public String getKa_code () {
return (String) this.getAttributeValue( KadanganHVO2.KA_CODE);
 } 

/** 
* ���û�Ա����
*
* @param ka_code ��Ա����
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KadanganHVO2.KA_CODE,ka_code);
 } 

/** 
* ��ȡ��Ա����
*
* @return ��Ա����
*/
public String getKa_name () {
return (String) this.getAttributeValue( KadanganHVO2.KA_NAME);
 } 

/** 
* ���û�Ա����
*
* @param ka_name ��Ա����
*/
public void setKa_name ( String ka_name) {
this.setAttributeValue( KadanganHVO2.KA_NAME,ka_name);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.KABILI);
 } 

/** 
* ���ÿ�����
*
* @param kabili ������
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KadanganHVO2.KABILI,kabili);
 } 

/** 
* ��ȡ��״̬
*
* @return ��״̬
* @see String
*/
public String getKastatus () {
return (String) this.getAttributeValue( KadanganHVO2.KASTATUS);
 } 

/** 
* ���ÿ�״̬
*
* @param kastatus ��״̬
* @see String
*/
public void setKastatus ( String kastatus) {
this.setAttributeValue( KadanganHVO2.KASTATUS,kastatus);
 } 

/** 
* ��ȡ�ɿ�Ʊ�ܶ�
*
* @return �ɿ�Ʊ�ܶ�
*/
public UFDouble getKkpze () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.KKPZE);
 } 

/** 
* ���ÿɿ�Ʊ�ܶ�
*
* @param kkpze �ɿ�Ʊ�ܶ�
*/
public void setKkpze ( UFDouble kkpze) {
this.setAttributeValue( KadanganHVO2.KKPZE,kkpze);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( KadanganHVO2.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( KadanganHVO2.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( KadanganHVO2.MODIFIER,modifier);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( KadanganHVO2.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( KadanganHVO2.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( KadanganHVO2.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( KadanganHVO2.PK_GROUP,pk_group);
 } 

/** 
* ��ȡ��Ա������
*
* @return ��Ա������
*/
public String getPk_hk_huiyuan_kadangan () {
return (String) this.getAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KADANGAN);
 } 

/** 
* ���û�Ա������
*
* @param pk_hk_huiyuan_kadangan ��Ա������
*/
public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
this.setAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kaxing () {
return (String) this.getAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KAXING);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kaxing �ϲ㵥������
*/
public void setPk_hk_huiyuan_kaxing ( String pk_hk_huiyuan_kaxing) {
this.setAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KAXING,pk_hk_huiyuan_kaxing);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( KadanganHVO2.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( KadanganHVO2.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( KadanganHVO2.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( KadanganHVO2.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡ�ڳ����
*
* @return �ڳ����
*/
public UFDouble getQc_ye () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.QC_YE);
 } 

/** 
* �����ڳ����
*
* @param qc_ye �ڳ����
*/
public void setQc_ye ( UFDouble qc_ye) {
this.setAttributeValue( KadanganHVO2.QC_YE,qc_ye);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( KadanganHVO2.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KadanganHVO2.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( KadanganHVO2.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( KadanganHVO2.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( KadanganHVO2.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( KadanganHVO2.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( KadanganHVO2.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( KadanganHVO2.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( KadanganHVO2.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( KadanganHVO2.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( KadanganHVO2.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( KadanganHVO2.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( KadanganHVO2.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( KadanganHVO2.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( KadanganHVO2.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( KadanganHVO2.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( KadanganHVO2.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( KadanganHVO2.VDEF10,vdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( KadanganHVO2.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( KadanganHVO2.VMEMO,vmemo);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( KadanganHVO2.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( KadanganHVO2.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* ��ȡ�ѿ�Ʊ���
*
* @return �ѿ�Ʊ���
*/
public UFDouble getYkpje () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.YKPJE);
 } 

/** 
* �����ѿ�Ʊ���
*
* @param ykpje �ѿ�Ʊ���
*/
public void setYkpje ( UFDouble ykpje) {
this.setAttributeValue( KadanganHVO2.YKPJE,ykpje);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_KadanganHVO2");
  }
}