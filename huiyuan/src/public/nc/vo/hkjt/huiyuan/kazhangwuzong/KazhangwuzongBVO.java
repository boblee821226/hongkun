package nc.vo.hkjt.huiyuan.kazhangwuzong;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KazhangwuzongBVO extends SuperVO {
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
*һ����
*/
public static final String DAKA1="daka1";
/**
*������
*/
public static final String DAKA3="daka3";
/**
*��Ա���������
*/
public static final String KAYUE="kayue";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_KAZHANGWUZONG="pk_hk_huiyuan_kazhangwuzong";
/**
*��Ա�����ܱ���pk
*/
public static final String PK_HK_HUIYUAN_KAZHANGWUZONG_B="pk_hk_huiyuan_kazhangwuzong_b";
/**
*����
*/
public static final String RQ="rq";
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
*��֤
*/
public static final String YANZHENG="yanzheng";
/**
*�����
*/
public static final String YUE="yue";
/**
*������
*/
public static final String YUE_JG="yue_jg";
/**
*���߿����
*/
public static final String YUE_XM="yue_xm";
/**
*Ӧ�������
*/
public static final String YUE_YF="yue_yf";
/**
*���Ͽ����
*/
public static final String ZUOFEI="zuofei";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KazhangwuzongBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KazhangwuzongBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KazhangwuzongBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KazhangwuzongBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KazhangwuzongBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KazhangwuzongBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡһ����
*
* @return һ����
*/
public UFDouble getDaka1 () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.DAKA1);
 } 

/** 
* ����һ����
*
* @param daka1 һ����
*/
public void setDaka1 ( UFDouble daka1) {
this.setAttributeValue( KazhangwuzongBVO.DAKA1,daka1);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getDaka3 () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.DAKA3);
 } 

/** 
* ����������
*
* @param daka3 ������
*/
public void setDaka3 ( UFDouble daka3) {
this.setAttributeValue( KazhangwuzongBVO.DAKA3,daka3);
 } 

/** 
* ��ȡ��Ա���������
*
* @return ��Ա���������
*/
public UFDouble getKayue () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.KAYUE);
 } 

/** 
* ���û�Ա���������
*
* @param kayue ��Ա���������
*/
public void setKayue ( UFDouble kayue) {
this.setAttributeValue( KazhangwuzongBVO.KAYUE,kayue);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kazhangwuzong () {
return (String) this.getAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kazhangwuzong �ϲ㵥������
*/
public void setPk_hk_huiyuan_kazhangwuzong ( String pk_hk_huiyuan_kazhangwuzong) {
this.setAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG,pk_hk_huiyuan_kazhangwuzong);
 } 

/** 
* ��ȡ��Ա�����ܱ���pk
*
* @return ��Ա�����ܱ���pk
*/
public String getPk_hk_huiyuan_kazhangwuzong_b () {
return (String) this.getAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG_B);
 } 

/** 
* ���û�Ա�����ܱ���pk
*
* @param pk_hk_huiyuan_kazhangwuzong_b ��Ա�����ܱ���pk
*/
public void setPk_hk_huiyuan_kazhangwuzong_b ( String pk_hk_huiyuan_kazhangwuzong_b) {
this.setAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG_B,pk_hk_huiyuan_kazhangwuzong_b);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getRq () {
return (String) this.getAttributeValue( KazhangwuzongBVO.RQ);
 } 

/** 
* ��������
*
* @param rq ����
*/
public void setRq ( String rq) {
this.setAttributeValue( KazhangwuzongBVO.RQ,rq);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KazhangwuzongBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KazhangwuzongBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KazhangwuzongBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KazhangwuzongBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KazhangwuzongBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KazhangwuzongBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KazhangwuzongBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ��֤
*
* @return ��֤
*/
public UFDouble getYanzheng () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YANZHENG);
 } 

/** 
* ������֤
*
* @param yanzheng ��֤
*/
public void setYanzheng ( UFDouble yanzheng) {
this.setAttributeValue( KazhangwuzongBVO.YANZHENG,yanzheng);
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public UFDouble getYue () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE);
 } 

/** 
* ���������
*
* @param yue �����
*/
public void setYue ( UFDouble yue) {
this.setAttributeValue( KazhangwuzongBVO.YUE,yue);
 } 

/** 
* ��ȡ������
*
* @return ������
*/
public UFDouble getYue_jg () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE_JG);
 } 

/** 
* ���ý�����
*
* @param yue_jg ������
*/
public void setYue_jg ( UFDouble yue_jg) {
this.setAttributeValue( KazhangwuzongBVO.YUE_JG,yue_jg);
 } 

/** 
* ��ȡ���߿����
*
* @return ���߿����
*/
public UFDouble getYue_xm () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE_XM);
 } 

/** 
* �������߿����
*
* @param yue_xm ���߿����
*/
public void setYue_xm ( UFDouble yue_xm) {
this.setAttributeValue( KazhangwuzongBVO.YUE_XM,yue_xm);
 } 

/** 
* ��ȡӦ�������
*
* @return Ӧ�������
*/
public UFDouble getYue_yf () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE_YF);
 } 

/** 
* ����Ӧ�������
*
* @param yue_yf Ӧ�������
*/
public void setYue_yf ( UFDouble yue_yf) {
this.setAttributeValue( KazhangwuzongBVO.YUE_YF,yue_yf);
 } 

/** 
* ��ȡ���Ͽ����
*
* @return ���Ͽ����
*/
public UFDouble getZuofei () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.ZUOFEI);
 } 

/** 
* �������Ͽ����
*
* @param zuofei ���Ͽ����
*/
public void setZuofei ( UFDouble zuofei) {
this.setAttributeValue( KazhangwuzongBVO.ZUOFEI,zuofei);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KazhangwuzongBVO");
  }
}