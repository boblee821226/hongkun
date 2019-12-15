package nc.vo.hkjt.huiyuan.cikazong;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CikazongBVO extends SuperVO {
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
*����
*/
public static final String CHALIANG="chaliang";
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
*��ֵ���
*/
public static final String CZ_MONEY="cz_money";
/**
*��ֵ����
*/
public static final String CZ_NUM="cz_num";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_CIKAZONG="pk_hk_huiyuan_cikazong";
/**
*�ο��ܱ��ӱ�pk
*/
public static final String PK_HK_HUIYUAN_CIKAZONG_B="pk_hk_huiyuan_cikazong_b";
/**
*����
*/
public static final String RQ="rq";
/**
*�������
*/
public static final String TJ_MONEY="tj_money";
/**
*��������
*/
public static final String TJ_NUM="tj_num";
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
*���ѽ��
*/
public static final String XF_MONEY="xf_money";
/**
*��������
*/
public static final String XF_NUM="xf_num";
/**
*���
*/
public static final String YUE="yue";
/**
*����
*/
public static final String YULIANG="yuliang";
/**
*�������
*/
public static final String YULIANG_JG="yuliang_jg";
/**
*ת�����
*/
public static final String ZC_MONEY="zc_money";
/**
*ת������
*/
public static final String ZC_NUM="zc_num";
/**
*ת����
*/
public static final String ZR_MONEY="zr_money";
/**
*ת������
*/
public static final String ZR_NUM="zr_num";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( CikazongBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CikazongBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CikazongBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CikazongBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CikazongBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CikazongBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getChaliang () {
return (UFDouble) this.getAttributeValue( CikazongBVO.CHALIANG);
 } 

/** 
* ���ò���
*
* @param chaliang ����
*/
public void setChaliang ( UFDouble chaliang) {
this.setAttributeValue( CikazongBVO.CHALIANG,chaliang);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CikazongBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CikazongBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CikazongBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CikazongBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CikazongBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CikazongBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��ֵ���
*
* @return ��ֵ���
*/
public UFDouble getCz_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.CZ_MONEY);
 } 

/** 
* ���ó�ֵ���
*
* @param cz_money ��ֵ���
*/
public void setCz_money ( UFDouble cz_money) {
this.setAttributeValue( CikazongBVO.CZ_MONEY,cz_money);
 } 

/** 
* ��ȡ��ֵ����
*
* @return ��ֵ����
*/
public UFDouble getCz_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.CZ_NUM);
 } 

/** 
* ���ó�ֵ����
*
* @param cz_num ��ֵ����
*/
public void setCz_num ( UFDouble cz_num) {
this.setAttributeValue( CikazongBVO.CZ_NUM,cz_num);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_cikazong () {
return (String) this.getAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_cikazong �ϲ㵥������
*/
public void setPk_hk_huiyuan_cikazong ( String pk_hk_huiyuan_cikazong) {
this.setAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG,pk_hk_huiyuan_cikazong);
 } 

/** 
* ��ȡ�ο��ܱ��ӱ�pk
*
* @return �ο��ܱ��ӱ�pk
*/
public String getPk_hk_huiyuan_cikazong_b () {
return (String) this.getAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG_B);
 } 

/** 
* ���ôο��ܱ��ӱ�pk
*
* @param pk_hk_huiyuan_cikazong_b �ο��ܱ��ӱ�pk
*/
public void setPk_hk_huiyuan_cikazong_b ( String pk_hk_huiyuan_cikazong_b) {
this.setAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG_B,pk_hk_huiyuan_cikazong_b);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDate getRq () {
return (UFDate) this.getAttributeValue( CikazongBVO.RQ);
 } 

/** 
* ��������
*
* @param rq ����
*/
public void setRq ( UFDate rq) {
this.setAttributeValue( CikazongBVO.RQ,rq);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getTj_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.TJ_MONEY);
 } 

/** 
* ���õ������
*
* @param tj_money �������
*/
public void setTj_money ( UFDouble tj_money) {
this.setAttributeValue( CikazongBVO.TJ_MONEY,tj_money);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getTj_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.TJ_NUM);
 } 

/** 
* ���õ�������
*
* @param tj_num ��������
*/
public void setTj_num ( UFDouble tj_num) {
this.setAttributeValue( CikazongBVO.TJ_NUM,tj_num);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CikazongBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CikazongBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CikazongBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CikazongBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CikazongBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CikazongBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CikazongBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CikazongBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CikazongBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CikazongBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CikazongBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CikazongBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CikazongBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CikazongBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CikazongBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CikazongBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( CikazongBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( CikazongBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CikazongBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CikazongBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ���ѽ��
*
* @return ���ѽ��
*/
public UFDouble getXf_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.XF_MONEY);
 } 

/** 
* �������ѽ��
*
* @param xf_money ���ѽ��
*/
public void setXf_money ( UFDouble xf_money) {
this.setAttributeValue( CikazongBVO.XF_MONEY,xf_money);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDouble getXf_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.XF_NUM);
 } 

/** 
* ������������
*
* @param xf_num ��������
*/
public void setXf_num ( UFDouble xf_num) {
this.setAttributeValue( CikazongBVO.XF_NUM,xf_num);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public UFDouble getYue () {
return (UFDouble) this.getAttributeValue( CikazongBVO.YUE);
 } 

/** 
* �������
*
* @param yue ���
*/
public void setYue ( UFDouble yue) {
this.setAttributeValue( CikazongBVO.YUE,yue);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getYuliang () {
return (UFDouble) this.getAttributeValue( CikazongBVO.YULIANG);
 } 

/** 
* ��������
*
* @param yuliang ����
*/
public void setYuliang ( UFDouble yuliang) {
this.setAttributeValue( CikazongBVO.YULIANG,yuliang);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getYuliang_jg () {
return (UFDouble) this.getAttributeValue( CikazongBVO.YULIANG_JG);
 } 

/** 
* ���ý������
*
* @param yuliang_jg �������
*/
public void setYuliang_jg ( UFDouble yuliang_jg) {
this.setAttributeValue( CikazongBVO.YULIANG_JG,yuliang_jg);
 } 

/** 
* ��ȡת�����
*
* @return ת�����
*/
public UFDouble getZc_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZC_MONEY);
 } 

/** 
* ����ת�����
*
* @param zc_money ת�����
*/
public void setZc_money ( UFDouble zc_money) {
this.setAttributeValue( CikazongBVO.ZC_MONEY,zc_money);
 } 

/** 
* ��ȡת������
*
* @return ת������
*/
public UFDouble getZc_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZC_NUM);
 } 

/** 
* ����ת������
*
* @param zc_num ת������
*/
public void setZc_num ( UFDouble zc_num) {
this.setAttributeValue( CikazongBVO.ZC_NUM,zc_num);
 } 

/** 
* ��ȡת����
*
* @return ת����
*/
public UFDouble getZr_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZR_MONEY);
 } 

/** 
* ����ת����
*
* @param zr_money ת����
*/
public void setZr_money ( UFDouble zr_money) {
this.setAttributeValue( CikazongBVO.ZR_MONEY,zr_money);
 } 

/** 
* ��ȡת������
*
* @return ת������
*/
public UFDouble getZr_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZR_NUM);
 } 

/** 
* ����ת������
*
* @param zr_num ת������
*/
public void setZr_num ( UFDouble zr_num) {
this.setAttributeValue( CikazongBVO.ZR_NUM,zr_num);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_CikazongBVO");
  }
}