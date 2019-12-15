package nc.vo.hkjt.srgk.huiguan.rsbaogao;

import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RsbaogaoCVO extends SuperVO {
/**
*����
*/
public static final String BM_PK="bm_pk";
/**
*����
*/
public static final String CODE="code";
/**
*����ʱ��
*/
public static final String CREATIONTIME="creationtime";
/**
*������
*/
public static final String CREATOR="creator";
/**
*�޸�ʱ��
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*�޸���
*/
public static final String MODIFIER="modifier";
/**
*����
*/
public static final String NAME="name";
/**
*����
*/
public static final String PK_GROUP="pk_group";
/**
*���󱨸�����pk
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO="pk_hk_srgk_hg_rsbaogao";
/**
*���󱨸��ӱ�pk
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO_B="pk_hk_srgk_hg_rsbaogao_b";
/**
*������pk
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO_C="pk_hk_srgk_hg_rsbaogao_c";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯�汾
*/
public static final String PK_ORG_V="pk_org_v";
/**
*ʱ���
*/
public static final String TS="ts";
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
*�Զ�����1
*/
public static final String VDEF1="vdef1";
/**
*�Զ�����2
*/
public static final String VDEF2="vdef2";
/**
*�Զ�����3
*/
public static final String VDEF3="vdef3";
/**
*�Զ�����4
*/
public static final String VDEF4="vdef4";
/**
*�Զ�����5
*/
public static final String VDEF5="vdef5";
/**
*�к�
*/
public static final String VROWNO="vrowno";
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
return (String) this.getAttributeValue( RsbaogaoCVO.BM_PK);
 } 

/** 
* ���ò���
*
* @param bm_pk ����
*/
public void setBm_pk ( String bm_pk) {
this.setAttributeValue( RsbaogaoCVO.BM_PK,bm_pk);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getCode () {
return (String) this.getAttributeValue( RsbaogaoCVO.CODE);
 } 

/** 
* ���ñ���
*
* @param code ����
*/
public void setCode ( String code) {
this.setAttributeValue( RsbaogaoCVO.CODE,code);
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( RsbaogaoCVO.CREATIONTIME);
 } 

/** 
* ���ô���ʱ��
*
* @param creationtime ����ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( RsbaogaoCVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public String getCreator () {
return (String) this.getAttributeValue( RsbaogaoCVO.CREATOR);
 } 

/** 
* ���ô�����
*
* @param creator ������
*/
public void setCreator ( String creator) {
this.setAttributeValue( RsbaogaoCVO.CREATOR,creator);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( RsbaogaoCVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( RsbaogaoCVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( RsbaogaoCVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( RsbaogaoCVO.MODIFIER,modifier);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getName () {
return (String) this.getAttributeValue( RsbaogaoCVO.NAME);
 } 

/** 
* ��������
*
* @param name ����
*/
public void setName ( String name) {
this.setAttributeValue( RsbaogaoCVO.NAME,name);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( RsbaogaoCVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡ���󱨸�����pk
*
* @return ���󱨸�����pk
*/
public String getPk_hk_srgk_hg_rsbaogao () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO);
 } 

/** 
* �������󱨸�����pk
*
* @param pk_hk_srgk_hg_rsbaogao ���󱨸�����pk
*/
public void setPk_hk_srgk_hg_rsbaogao ( String pk_hk_srgk_hg_rsbaogao) {
this.setAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO,pk_hk_srgk_hg_rsbaogao);
 } 

/** 
* ��ȡ���󱨸��ӱ�pk
*
* @return ���󱨸��ӱ�pk
*/
public String getPk_hk_srgk_hg_rsbaogao_b () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_B);
 } 

/** 
* �������󱨸��ӱ�pk
*
* @param pk_hk_srgk_hg_rsbaogao_b ���󱨸��ӱ�pk
*/
public void setPk_hk_srgk_hg_rsbaogao_b ( String pk_hk_srgk_hg_rsbaogao_b) {
this.setAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_B,pk_hk_srgk_hg_rsbaogao_b);
 } 

/** 
* ��ȡ������pk
*
* @return ������pk
*/
public String getPk_hk_srgk_hg_rsbaogao_c () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_C);
 } 

/** 
* ���õ�����pk
*
* @param pk_hk_srgk_hg_rsbaogao_c ������pk
*/
public void setPk_hk_srgk_hg_rsbaogao_c ( String pk_hk_srgk_hg_rsbaogao_c) {
this.setAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_C,pk_hk_srgk_hg_rsbaogao_c);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( RsbaogaoCVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯�汾
*
* @return ��֯�汾
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_ORG_V);
 } 

/** 
* ������֯�汾
*
* @param pk_org_v ��֯�汾
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( RsbaogaoCVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( RsbaogaoCVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( RsbaogaoCVO.TS,ts);
 } 

/** 
* ��ȡ������Ŀ����1
*
* @return ������Ŀ����1
*/
public UFDouble getTz_km_data_1 () {
return (UFDouble) this.getAttributeValue( RsbaogaoCVO.TZ_KM_DATA_1);
 } 

/** 
* ���õ�����Ŀ����1
*
* @param tz_km_data_1 ������Ŀ����1
*/
public void setTz_km_data_1 ( UFDouble tz_km_data_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_DATA_1,tz_km_data_1);
 } 

/** 
* ��ȡ������Ŀ����2
*
* @return ������Ŀ����2
*/
public UFDouble getTz_km_data_2 () {
return (UFDouble) this.getAttributeValue( RsbaogaoCVO.TZ_KM_DATA_2);
 } 

/** 
* ���õ�����Ŀ����2
*
* @param tz_km_data_2 ������Ŀ����2
*/
public void setTz_km_data_2 ( UFDouble tz_km_data_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_DATA_2,tz_km_data_2);
 } 

/** 
* ��ȡ������Ŀ��ϸ1
*
* @return ������Ŀ��ϸ1
*/
public String getTz_km_info_1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_INFO_1);
 } 

/** 
* ���õ�����Ŀ��ϸ1
*
* @param tz_km_info_1 ������Ŀ��ϸ1
*/
public void setTz_km_info_1 ( String tz_km_info_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_INFO_1,tz_km_info_1);
 } 

/** 
* ��ȡ������Ŀ��ϸ2
*
* @return ������Ŀ��ϸ2
*/
public String getTz_km_info_2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_INFO_2);
 } 

/** 
* ���õ�����Ŀ��ϸ2
*
* @param tz_km_info_2 ������Ŀ��ϸ2
*/
public void setTz_km_info_2 ( String tz_km_info_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_INFO_2,tz_km_info_2);
 } 

/** 
* ��ȡ������Ŀ���˷�ʽ1
*
* @return ������Ŀ���˷�ʽ1
*/
public String getTz_km_jzfs_1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_1);
 } 

/** 
* ���õ�����Ŀ���˷�ʽ1
*
* @param tz_km_jzfs_1 ������Ŀ���˷�ʽ1
*/
public void setTz_km_jzfs_1 ( String tz_km_jzfs_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_1,tz_km_jzfs_1);
 } 

/** 
* ��ȡ������Ŀ���˷�ʽ2
*
* @return ������Ŀ���˷�ʽ2
*/
public String getTz_km_jzfs_2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_2);
 } 

/** 
* ���õ�����Ŀ���˷�ʽ2
*
* @param tz_km_jzfs_2 ������Ŀ���˷�ʽ2
*/
public void setTz_km_jzfs_2 ( String tz_km_jzfs_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_2,tz_km_jzfs_2);
 } 

/** 
* ��ȡ������Ŀ������Ŀ1
*
* @return ������Ŀ������Ŀ1
*/
public String getTz_km_srxm_1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_1);
 } 

/** 
* ���õ�����Ŀ������Ŀ1
*
* @param tz_km_srxm_1 ������Ŀ������Ŀ1
*/
public void setTz_km_srxm_1 ( String tz_km_srxm_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_1,tz_km_srxm_1);
 } 

/** 
* ��ȡ������Ŀ������Ŀ2
*
* @return ������Ŀ������Ŀ2
*/
public String getTz_km_srxm_2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_2);
 } 

/** 
* ���õ�����Ŀ������Ŀ2
*
* @param tz_km_srxm_2 ������Ŀ������Ŀ2
*/
public void setTz_km_srxm_2 ( String tz_km_srxm_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_2,tz_km_srxm_2);
 } 

/** 
* ��ȡ�Զ�����1
*
* @return �Զ�����1
*/
public String getVdef1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF1);
 } 

/** 
* �����Զ�����1
*
* @param vdef1 �Զ�����1
*/
public void setVdef1 ( String vdef1) {
this.setAttributeValue( RsbaogaoCVO.VDEF1,vdef1);
 } 

/** 
* ��ȡ�Զ�����2
*
* @return �Զ�����2
*/
public String getVdef2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF2);
 } 

/** 
* �����Զ�����2
*
* @param vdef2 �Զ�����2
*/
public void setVdef2 ( String vdef2) {
this.setAttributeValue( RsbaogaoCVO.VDEF2,vdef2);
 } 

/** 
* ��ȡ�Զ�����3
*
* @return �Զ�����3
*/
public String getVdef3 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF3);
 } 

/** 
* �����Զ�����3
*
* @param vdef3 �Զ�����3
*/
public void setVdef3 ( String vdef3) {
this.setAttributeValue( RsbaogaoCVO.VDEF3,vdef3);
 } 

/** 
* ��ȡ�Զ�����4
*
* @return �Զ�����4
*/
public String getVdef4 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF4);
 } 

/** 
* �����Զ�����4
*
* @param vdef4 �Զ�����4
*/
public void setVdef4 ( String vdef4) {
this.setAttributeValue( RsbaogaoCVO.VDEF4,vdef4);
 } 

/** 
* ��ȡ�Զ�����5
*
* @return �Զ�����5
*/
public String getVdef5 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF5);
 } 

/** 
* �����Զ�����5
*
* @param vdef5 �Զ�����5
*/
public void setVdef5 ( String vdef5) {
this.setAttributeValue( RsbaogaoCVO.VDEF5,vdef5);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( RsbaogaoCVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( RsbaogaoCVO.VROWNO,vrowno);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_RsbaogaoCVO");
  }
}