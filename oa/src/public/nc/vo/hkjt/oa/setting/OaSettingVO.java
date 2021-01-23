package nc.vo.hkjt.oa.setting;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class OaSettingVO extends SuperVO {
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
*parentbilltype
*/
public static final String PARENTBILLTYPE="parentbilltype";
/**
*pk_billtypecode
*/
public static final String PK_BILLTYPECODE="pk_billtypecode";
/**
*pk_billtypeid
*/
public static final String PK_BILLTYPEID="pk_billtypeid";
/**
*ҵ������
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*����
*/
public static final String PK_GROUP="pk_group";
/**
*oa����pk
*/
public static final String PK_HK_OA_SETTING="pk_hk_oa_setting";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*��������pk
*/
public static final String PK_TRANTYPE="pk_trantype";
/**
*table_code_1
*/
public static final String TABLE_CODE_1="table_code_1";
/**
*table_code_2
*/
public static final String TABLE_CODE_2="table_code_2";
/**
*table_code_3
*/
public static final String TABLE_CODE_3="table_code_3";
/**
*table_code_4
*/
public static final String TABLE_CODE_4="table_code_4";
/**
*table_code_5
*/
public static final String TABLE_CODE_5="table_code_5";
/**
*���ʱ��
*/
public static final String TAUDITTIME="taudittime";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*url_data
*/
public static final String URL_DATA="url_data";
/**
*url_file
*/
public static final String URL_FILE="url_file";
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
*�Զ�����11
*/
public static final String VDEF11="vdef11";
/**
*�Զ�����12
*/
public static final String VDEF12="vdef12";
/**
*�Զ�����13
*/
public static final String VDEF13="vdef13";
/**
*�Զ�����14
*/
public static final String VDEF14="vdef14";
/**
*�Զ�����15
*/
public static final String VDEF15="vdef15";
/**
*�Զ�����16
*/
public static final String VDEF16="vdef16";
/**
*�Զ�����17
*/
public static final String VDEF17="vdef17";
/**
*�Զ�����18
*/
public static final String VDEF18="vdef18";
/**
*�Զ�����19
*/
public static final String VDEF19="vdef19";
/**
*�Զ�����20
*/
public static final String VDEF20="vdef20";
/**
*��ע
*/
public static final String VMEMO="vmemo";
/**
*��������
*/
public static final String VTRANTYPECODE="vtrantypecode";
/**
*workflowid
*/
public static final String WORKFLOWID="workflowid";
/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return (String) this.getAttributeValue( OaSettingVO.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( OaSettingVO.APPROVER,approver);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( OaSettingVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( OaSettingVO.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( OaSettingVO.CREATOR,creator);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( OaSettingVO.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( OaSettingVO.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( OaSettingVO.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( OaSettingVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( OaSettingVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( OaSettingVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( OaSettingVO.MODIFIER,modifier);
 } 

/** 
* ��ȡparentbilltype
*
* @return parentbilltype
*/
public String getParentbilltype () {
return (String) this.getAttributeValue( OaSettingVO.PARENTBILLTYPE);
 } 

/** 
* ����parentbilltype
*
* @param parentbilltype parentbilltype
*/
public void setParentbilltype ( String parentbilltype) {
this.setAttributeValue( OaSettingVO.PARENTBILLTYPE,parentbilltype);
 } 

/** 
* ��ȡpk_billtypecode
*
* @return pk_billtypecode
*/
public String getPk_billtypecode () {
return (String) this.getAttributeValue( OaSettingVO.PK_BILLTYPECODE);
 } 

/** 
* ����pk_billtypecode
*
* @param pk_billtypecode pk_billtypecode
*/
public void setPk_billtypecode ( String pk_billtypecode) {
this.setAttributeValue( OaSettingVO.PK_BILLTYPECODE,pk_billtypecode);
 } 

/** 
* ��ȡpk_billtypeid
*
* @return pk_billtypeid
*/
public String getPk_billtypeid () {
return (String) this.getAttributeValue( OaSettingVO.PK_BILLTYPEID);
 } 

/** 
* ����pk_billtypeid
*
* @param pk_billtypeid pk_billtypeid
*/
public void setPk_billtypeid ( String pk_billtypeid) {
this.setAttributeValue( OaSettingVO.PK_BILLTYPEID,pk_billtypeid);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( OaSettingVO.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( OaSettingVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( OaSettingVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( OaSettingVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡoa����pk
*
* @return oa����pk
*/
public String getPk_hk_oa_setting () {
return (String) this.getAttributeValue( OaSettingVO.PK_HK_OA_SETTING);
 } 

/** 
* ����oa����pk
*
* @param pk_hk_oa_setting oa����pk
*/
public void setPk_hk_oa_setting ( String pk_hk_oa_setting) {
this.setAttributeValue( OaSettingVO.PK_HK_OA_SETTING,pk_hk_oa_setting);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( OaSettingVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( OaSettingVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( OaSettingVO.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( OaSettingVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡ��������pk
*
* @return ��������pk
*/
public String getPk_trantype () {
return (String) this.getAttributeValue( OaSettingVO.PK_TRANTYPE);
 } 

/** 
* ���ý�������pk
*
* @param pk_trantype ��������pk
*/
public void setPk_trantype ( String pk_trantype) {
this.setAttributeValue( OaSettingVO.PK_TRANTYPE,pk_trantype);
 } 

/** 
* ��ȡtable_code_1
*
* @return table_code_1
*/
public String getTable_code_1 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_1);
 } 

/** 
* ����table_code_1
*
* @param table_code_1 table_code_1
*/
public void setTable_code_1 ( String table_code_1) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_1,table_code_1);
 } 

/** 
* ��ȡtable_code_2
*
* @return table_code_2
*/
public String getTable_code_2 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_2);
 } 

/** 
* ����table_code_2
*
* @param table_code_2 table_code_2
*/
public void setTable_code_2 ( String table_code_2) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_2,table_code_2);
 } 

/** 
* ��ȡtable_code_3
*
* @return table_code_3
*/
public String getTable_code_3 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_3);
 } 

/** 
* ����table_code_3
*
* @param table_code_3 table_code_3
*/
public void setTable_code_3 ( String table_code_3) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_3,table_code_3);
 } 

/** 
* ��ȡtable_code_4
*
* @return table_code_4
*/
public String getTable_code_4 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_4);
 } 

/** 
* ����table_code_4
*
* @param table_code_4 table_code_4
*/
public void setTable_code_4 ( String table_code_4) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_4,table_code_4);
 } 

/** 
* ��ȡtable_code_5
*
* @return table_code_5
*/
public String getTable_code_5 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_5);
 } 

/** 
* ����table_code_5
*
* @param table_code_5 table_code_5
*/
public void setTable_code_5 ( String table_code_5) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_5,table_code_5);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( OaSettingVO.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( OaSettingVO.TS,ts);
 } 

/** 
* ��ȡurl_data
*
* @return url_data
*/
public String getUrl_data () {
return (String) this.getAttributeValue( OaSettingVO.URL_DATA);
 } 

/** 
* ����url_data
*
* @param url_data url_data
*/
public void setUrl_data ( String url_data) {
this.setAttributeValue( OaSettingVO.URL_DATA,url_data);
 } 

/** 
* ��ȡurl_file
*
* @return url_file
*/
public String getUrl_file () {
return (String) this.getAttributeValue( OaSettingVO.URL_FILE);
 } 

/** 
* ����url_file
*
* @param url_file url_file
*/
public void setUrl_file ( String url_file) {
this.setAttributeValue( OaSettingVO.URL_FILE,url_file);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( OaSettingVO.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( OaSettingVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( OaSettingVO.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( OaSettingVO.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( OaSettingVO.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( OaSettingVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( OaSettingVO.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( OaSettingVO.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( OaSettingVO.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( OaSettingVO.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( OaSettingVO.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( OaSettingVO.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( OaSettingVO.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( OaSettingVO.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( OaSettingVO.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( OaSettingVO.VDEF10,vdef10);
 } 

/** 
* ��ȡ�Զ�����11
*
* @return �Զ�����11
*/
public String getVdef11 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF11);
 } 

/** 
* �����Զ�����11
*
* @param vdef11 �Զ�����11
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( OaSettingVO.VDEF11,vdef11);
 } 

/** 
* ��ȡ�Զ�����12
*
* @return �Զ�����12
*/
public String getVdef12 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF12);
 } 

/** 
* �����Զ�����12
*
* @param vdef12 �Զ�����12
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( OaSettingVO.VDEF12,vdef12);
 } 

/** 
* ��ȡ�Զ�����13
*
* @return �Զ�����13
*/
public String getVdef13 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF13);
 } 

/** 
* �����Զ�����13
*
* @param vdef13 �Զ�����13
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( OaSettingVO.VDEF13,vdef13);
 } 

/** 
* ��ȡ�Զ�����14
*
* @return �Զ�����14
*/
public String getVdef14 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF14);
 } 

/** 
* �����Զ�����14
*
* @param vdef14 �Զ�����14
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( OaSettingVO.VDEF14,vdef14);
 } 

/** 
* ��ȡ�Զ�����15
*
* @return �Զ�����15
*/
public String getVdef15 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF15);
 } 

/** 
* �����Զ�����15
*
* @param vdef15 �Զ�����15
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( OaSettingVO.VDEF15,vdef15);
 } 

/** 
* ��ȡ�Զ�����16
*
* @return �Զ�����16
*/
public String getVdef16 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF16);
 } 

/** 
* �����Զ�����16
*
* @param vdef16 �Զ�����16
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( OaSettingVO.VDEF16,vdef16);
 } 

/** 
* ��ȡ�Զ�����17
*
* @return �Զ�����17
*/
public String getVdef17 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF17);
 } 

/** 
* �����Զ�����17
*
* @param vdef17 �Զ�����17
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( OaSettingVO.VDEF17,vdef17);
 } 

/** 
* ��ȡ�Զ�����18
*
* @return �Զ�����18
*/
public String getVdef18 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF18);
 } 

/** 
* �����Զ�����18
*
* @param vdef18 �Զ�����18
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( OaSettingVO.VDEF18,vdef18);
 } 

/** 
* ��ȡ�Զ�����19
*
* @return �Զ�����19
*/
public String getVdef19 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF19);
 } 

/** 
* �����Զ�����19
*
* @param vdef19 �Զ�����19
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( OaSettingVO.VDEF19,vdef19);
 } 

/** 
* ��ȡ�Զ�����20
*
* @return �Զ�����20
*/
public String getVdef20 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF20);
 } 

/** 
* �����Զ�����20
*
* @param vdef20 �Զ�����20
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( OaSettingVO.VDEF20,vdef20);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( OaSettingVO.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( OaSettingVO.VMEMO,vmemo);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( OaSettingVO.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( OaSettingVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* ��ȡworkflowid
*
* @return workflowid
*/
public String getWorkflowid () {
return (String) this.getAttributeValue( OaSettingVO.WORKFLOWID);
 } 

/** 
* ����workflowid
*
* @param workflowid workflowid
*/
public void setWorkflowid ( String workflowid) {
this.setAttributeValue( OaSettingVO.WORKFLOWID,workflowid);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_oa_settingVO");
  }
}