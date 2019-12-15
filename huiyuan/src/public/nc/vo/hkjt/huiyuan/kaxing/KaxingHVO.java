package nc.vo.hkjt.huiyuan.kaxing;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KaxingHVO extends SuperVO {
/**
*�����
*/
public static final String APPROVER="approver";
/**
*cardalias
*/
public static final String CARDALIAS="cardalias";
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
*groupname
*/
public static final String GROUPNAME="groupname";
/**
*����״̬
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*���ͽ��
*/
public static final String KA_JE="ka_je";
/**
*ʵ�ս��
*/
public static final String KA_SS="ka_ss";
/**
*���ͽ��
*/
public static final String KA_ZS="ka_zs";
/**
*������
*/
public static final String KABILI="kabili";
/**
*���ͱ���
*/
public static final String KAXING_CODE="kaxing_code";
/**
*��������
*/
public static final String KAXING_NAME="kaxing_name";
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
*��������
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
*remark
*/
public static final String REMARK="remark";
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
*vpnuse
*/
public static final String VPNUSE="vpnuse";
/**
*��������
*/
public static final String VTRANTYPECODE="vtrantypecode";
/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return (String) this.getAttributeValue( KaxingHVO.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( KaxingHVO.APPROVER,approver);
 } 

/** 
* ��ȡcardalias
*
* @return cardalias
*/
public String getCardalias () {
return (String) this.getAttributeValue( KaxingHVO.CARDALIAS);
 } 

/** 
* ����cardalias
*
* @param cardalias cardalias
*/
public void setCardalias ( String cardalias) {
this.setAttributeValue( KaxingHVO.CARDALIAS,cardalias);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( KaxingHVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( KaxingHVO.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( KaxingHVO.CREATOR,creator);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( KaxingHVO.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( KaxingHVO.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡgroupname
*
* @return groupname
*/
public String getGroupname () {
return (String) this.getAttributeValue( KaxingHVO.GROUPNAME);
 } 

/** 
* ����groupname
*
* @param groupname groupname
*/
public void setGroupname ( String groupname) {
this.setAttributeValue( KaxingHVO.GROUPNAME,groupname);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( KaxingHVO.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( KaxingHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ���ͽ��
*
* @return ���ͽ��
*/
public UFDouble getKa_je () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KA_JE);
 } 

/** 
* ���ÿ��ͽ��
*
* @param ka_je ���ͽ��
*/
public void setKa_je ( UFDouble ka_je) {
this.setAttributeValue( KaxingHVO.KA_JE,ka_je);
 } 

/** 
* ��ȡʵ�ս��
*
* @return ʵ�ս��
*/
public UFDouble getKa_ss () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KA_SS);
 } 

/** 
* ����ʵ�ս��
*
* @param ka_ss ʵ�ս��
*/
public void setKa_ss ( UFDouble ka_ss) {
this.setAttributeValue( KaxingHVO.KA_SS,ka_ss);
 } 

/** 
* ��ȡ���ͽ��
*
* @return ���ͽ��
*/
public UFDouble getKa_zs () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KA_ZS);
 } 

/** 
* �������ͽ��
*
* @param ka_zs ���ͽ��
*/
public void setKa_zs ( UFDouble ka_zs) {
this.setAttributeValue( KaxingHVO.KA_ZS,ka_zs);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KABILI);
 } 

/** 
* ���ÿ�����
*
* @param kabili ������
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KaxingHVO.KABILI,kabili);
 } 

/** 
* ��ȡ���ͱ���
*
* @return ���ͱ���
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KaxingHVO.KAXING_CODE);
 } 

/** 
* ���ÿ��ͱ���
*
* @param kaxing_code ���ͱ���
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KaxingHVO.KAXING_CODE,kaxing_code);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KaxingHVO.KAXING_NAME);
 } 

/** 
* ���ÿ�������
*
* @param kaxing_name ��������
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KaxingHVO.KAXING_NAME,kaxing_name);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( KaxingHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( KaxingHVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( KaxingHVO.MODIFIER,modifier);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( KaxingHVO.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( KaxingHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( KaxingHVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( KaxingHVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_hk_huiyuan_kaxing () {
return (String) this.getAttributeValue( KaxingHVO.PK_HK_HUIYUAN_KAXING);
 } 

/** 
* ���ÿ�������
*
* @param pk_hk_huiyuan_kaxing ��������
*/
public void setPk_hk_huiyuan_kaxing ( String pk_hk_huiyuan_kaxing) {
this.setAttributeValue( KaxingHVO.PK_HK_HUIYUAN_KAXING,pk_hk_huiyuan_kaxing);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( KaxingHVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( KaxingHVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( KaxingHVO.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( KaxingHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡremark
*
* @return remark
*/
public String getRemark () {
return (String) this.getAttributeValue( KaxingHVO.REMARK);
 } 

/** 
* ����remark
*
* @param remark remark
*/
public void setRemark ( String remark) {
this.setAttributeValue( KaxingHVO.REMARK,remark);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( KaxingHVO.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KaxingHVO.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( KaxingHVO.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( KaxingHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( KaxingHVO.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( KaxingHVO.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( KaxingHVO.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( KaxingHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( KaxingHVO.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( KaxingHVO.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( KaxingHVO.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( KaxingHVO.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( KaxingHVO.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( KaxingHVO.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( KaxingHVO.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( KaxingHVO.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( KaxingHVO.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( KaxingHVO.VDEF10,vdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( KaxingHVO.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( KaxingHVO.VMEMO,vmemo);
 } 

/** 
* ��ȡvpnuse
*
* @return vpnuse
*/
public String getVpnuse () {
return (String) this.getAttributeValue( KaxingHVO.VPNUSE);
 } 

/** 
* ����vpnuse
*
* @param vpnuse vpnuse
*/
public void setVpnuse ( String vpnuse) {
this.setAttributeValue( KaxingHVO.VPNUSE,vpnuse);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( KaxingHVO.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( KaxingHVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_KaxingHVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( KaxingHVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( KaxingHVO.DR);
	   } 
  
}