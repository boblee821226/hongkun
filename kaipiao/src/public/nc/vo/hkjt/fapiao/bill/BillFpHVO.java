package nc.vo.hkjt.fapiao.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillFpHVO extends SuperVO {
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
*��Ʊ����
*/
public static final String FPDM="fpdm";
/**
*��Ʊ����
*/
public static final String FPHM="fphm";
/**
*��Ʊ���
*/
public static final String FPJE="fpje";
/**
*��Ʊ����
*/
public static final String FPLX="fplx";
/**
*��Ʊ״̬
*/
public static final String FPZT="fpzt";
/**
*�ϼƽ��
*/
public static final String I_JE="i_je";
/**
*��˰�ϼ�
*/
public static final String I_JSHJ="i_jshj";
/**
*�ͻ�ʶ���
*/
public static final String I_KHSBH="i_khsbh";
/**
*��Ʊ�ͻ�
*/
public static final String I_KPKH="i_kpkh";
/**
*��Ʊ����
*/
public static final String I_KPRQ="i_kprq";
/**
*˰��
*/
public static final String I_SE="i_se";
/**
*��Ʒ����
*/
public static final String I_SPMC="i_spmc";
/**
*ԭ��Ʊ����
*/
public static final String I_YFPDM="i_yfpdm";
/**
*ԭ��Ʊ����
*/
public static final String I_YFPHM="i_yfphm";
/**
*��������
*/
public static final String I_ZFRQ="i_zfrq";
/**
*����״̬
*/
public static final String IBILLSTATUS="ibillstatus";
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
*�˵���Ʊ��pk
*/
public static final String PK_HK_FAPIAO_BILL="pk_hk_fapiao_bill";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯v
*/
public static final String PK_ORG_V="pk_org_v";
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
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return (String) this.getAttributeValue( BillFpHVO.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( BillFpHVO.APPROVER,approver);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( BillFpHVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( BillFpHVO.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( BillFpHVO.CREATOR,creator);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( BillFpHVO.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( BillFpHVO.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡ��Ʊ����
*
* @return ��Ʊ����
*/
public String getFpdm () {
return (String) this.getAttributeValue( BillFpHVO.FPDM);
 } 

/** 
* ���÷�Ʊ����
*
* @param fpdm ��Ʊ����
*/
public void setFpdm ( String fpdm) {
this.setAttributeValue( BillFpHVO.FPDM,fpdm);
 } 

/** 
* ��ȡ��Ʊ����
*
* @return ��Ʊ����
*/
public String getFphm () {
return (String) this.getAttributeValue( BillFpHVO.FPHM);
 } 

/** 
* ���÷�Ʊ����
*
* @param fphm ��Ʊ����
*/
public void setFphm ( String fphm) {
this.setAttributeValue( BillFpHVO.FPHM,fphm);
 } 

/** 
* ��ȡ��Ʊ���
*
* @return ��Ʊ���
*/
public UFDouble getFpje () {
return (UFDouble) this.getAttributeValue( BillFpHVO.FPJE);
 } 

/** 
* ���÷�Ʊ���
*
* @param fpje ��Ʊ���
*/
public void setFpje ( UFDouble fpje) {
this.setAttributeValue( BillFpHVO.FPJE,fpje);
 } 

/** 
* ��ȡ��Ʊ����
*
* @return ��Ʊ����
*/
public String getFplx () {
return (String) this.getAttributeValue( BillFpHVO.FPLX);
 } 

/** 
* ���÷�Ʊ����
*
* @param fplx ��Ʊ����
*/
public void setFplx ( String fplx) {
this.setAttributeValue( BillFpHVO.FPLX,fplx);
 } 

/** 
* ��ȡ��Ʊ״̬
*
* @return ��Ʊ״̬
* @see String
*/
public String getFpzt () {
return (String) this.getAttributeValue( BillFpHVO.FPZT);
 } 

/** 
* ���÷�Ʊ״̬
*
* @param fpzt ��Ʊ״̬
* @see String
*/
public void setFpzt ( String fpzt) {
this.setAttributeValue( BillFpHVO.FPZT,fpzt);
 } 

/** 
* ��ȡ�ϼƽ��
*
* @return �ϼƽ��
*/
public UFDouble getI_je () {
return (UFDouble) this.getAttributeValue( BillFpHVO.I_JE);
 } 

/** 
* ���úϼƽ��
*
* @param i_je �ϼƽ��
*/
public void setI_je ( UFDouble i_je) {
this.setAttributeValue( BillFpHVO.I_JE,i_je);
 } 

/** 
* ��ȡ��˰�ϼ�
*
* @return ��˰�ϼ�
*/
public UFDouble getI_jshj () {
return (UFDouble) this.getAttributeValue( BillFpHVO.I_JSHJ);
 } 

/** 
* ���ü�˰�ϼ�
*
* @param i_jshj ��˰�ϼ�
*/
public void setI_jshj ( UFDouble i_jshj) {
this.setAttributeValue( BillFpHVO.I_JSHJ,i_jshj);
 } 

/** 
* ��ȡ�ͻ�ʶ���
*
* @return �ͻ�ʶ���
*/
public String getI_khsbh () {
return (String) this.getAttributeValue( BillFpHVO.I_KHSBH);
 } 

/** 
* ���ÿͻ�ʶ���
*
* @param i_khsbh �ͻ�ʶ���
*/
public void setI_khsbh ( String i_khsbh) {
this.setAttributeValue( BillFpHVO.I_KHSBH,i_khsbh);
 } 

/** 
* ��ȡ��Ʊ�ͻ�
*
* @return ��Ʊ�ͻ�
*/
public String getI_kpkh () {
return (String) this.getAttributeValue( BillFpHVO.I_KPKH);
 } 

/** 
* ���ÿ�Ʊ�ͻ�
*
* @param i_kpkh ��Ʊ�ͻ�
*/
public void setI_kpkh ( String i_kpkh) {
this.setAttributeValue( BillFpHVO.I_KPKH,i_kpkh);
 } 

/** 
* ��ȡ��Ʊ����
*
* @return ��Ʊ����
*/
public UFDate getI_kprq () {
return (UFDate) this.getAttributeValue( BillFpHVO.I_KPRQ);
 } 

/** 
* ���ÿ�Ʊ����
*
* @param i_kprq ��Ʊ����
*/
public void setI_kprq ( UFDate i_kprq) {
this.setAttributeValue( BillFpHVO.I_KPRQ,i_kprq);
 } 

/** 
* ��ȡ˰��
*
* @return ˰��
*/
public UFDouble getI_se () {
return (UFDouble) this.getAttributeValue( BillFpHVO.I_SE);
 } 

/** 
* ����˰��
*
* @param i_se ˰��
*/
public void setI_se ( UFDouble i_se) {
this.setAttributeValue( BillFpHVO.I_SE,i_se);
 } 

/** 
* ��ȡ��Ʒ����
*
* @return ��Ʒ����
*/
public String getI_spmc () {
return (String) this.getAttributeValue( BillFpHVO.I_SPMC);
 } 

/** 
* ������Ʒ����
*
* @param i_spmc ��Ʒ����
*/
public void setI_spmc ( String i_spmc) {
this.setAttributeValue( BillFpHVO.I_SPMC,i_spmc);
 } 

/** 
* ��ȡԭ��Ʊ����
*
* @return ԭ��Ʊ����
*/
public String getI_yfpdm () {
return (String) this.getAttributeValue( BillFpHVO.I_YFPDM);
 } 

/** 
* ����ԭ��Ʊ����
*
* @param i_yfpdm ԭ��Ʊ����
*/
public void setI_yfpdm ( String i_yfpdm) {
this.setAttributeValue( BillFpHVO.I_YFPDM,i_yfpdm);
 } 

/** 
* ��ȡԭ��Ʊ����
*
* @return ԭ��Ʊ����
*/
public String getI_yfphm () {
return (String) this.getAttributeValue( BillFpHVO.I_YFPHM);
 } 

/** 
* ����ԭ��Ʊ����
*
* @param i_yfphm ԭ��Ʊ����
*/
public void setI_yfphm ( String i_yfphm) {
this.setAttributeValue( BillFpHVO.I_YFPHM,i_yfphm);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getI_zfrq () {
return (UFDate) this.getAttributeValue( BillFpHVO.I_ZFRQ);
 } 

/** 
* ������������
*
* @param i_zfrq ��������
*/
public void setI_zfrq ( UFDate i_zfrq) {
this.setAttributeValue( BillFpHVO.I_ZFRQ,i_zfrq);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( BillFpHVO.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( BillFpHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( BillFpHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( BillFpHVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( BillFpHVO.MODIFIER,modifier);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( BillFpHVO.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( BillFpHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( BillFpHVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( BillFpHVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡ�˵���Ʊ��pk
*
* @return �˵���Ʊ��pk
*/
public String getPk_hk_fapiao_bill () {
return (String) this.getAttributeValue( BillFpHVO.PK_HK_FAPIAO_BILL);
 } 

/** 
* �����˵���Ʊ��pk
*
* @param pk_hk_fapiao_bill �˵���Ʊ��pk
*/
public void setPk_hk_fapiao_bill ( String pk_hk_fapiao_bill) {
this.setAttributeValue( BillFpHVO.PK_HK_FAPIAO_BILL,pk_hk_fapiao_bill);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( BillFpHVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( BillFpHVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( BillFpHVO.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( BillFpHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( BillFpHVO.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillFpHVO.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( BillFpHVO.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( BillFpHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( BillFpHVO.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( BillFpHVO.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( BillFpHVO.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( BillFpHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( BillFpHVO.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( BillFpHVO.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( BillFpHVO.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( BillFpHVO.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( BillFpHVO.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( BillFpHVO.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( BillFpHVO.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( BillFpHVO.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( BillFpHVO.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( BillFpHVO.VDEF10,vdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( BillFpHVO.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( BillFpHVO.VMEMO,vmemo);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( BillFpHVO.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( BillFpHVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_fp_BillFpHVO");
  }
}