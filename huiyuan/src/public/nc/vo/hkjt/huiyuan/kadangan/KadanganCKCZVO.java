package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KadanganCKCZVO extends SuperVO {
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
*��ֵʱ��
*/
public static final String CZ_TIME="cz_time";
/**
*�ο���������
*/
public static final String EXPDATA="expdata";
/**
*��Ŀ����
*/
public static final String ITEMID="itemid";
/**
*��Ŀ����
*/
public static final String ITEMNAME="itemname";
/**
*�ο�������
*/
public static final String KABILI="kabili";
/**
*�ϲ㵥������
*/
public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
/**
*�ο���ֵpk
*/
public static final String PK_HK_HUIYUAN_KADANGAN_CKCZ="pk_hk_huiyuan_kadangan_ckcz";
/**
*����
*/
public static final String PRICE="price";
/**
*�ο���ʼ����
*/
public static final String STARTDATA="startdata";
/**
*�ο�ˮ��
*/
public static final String TIMESCARDWATERNUM="timescardwaternum";
/**
*�ܴ���
*/
public static final String TOTALNUM="totalnum";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*ʹ�ô���
*/
public static final String USEDNUM="usednum";
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
*�˵���
*/
public static final String ZDH="zdh";
/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KadanganCKCZVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KadanganCKCZVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KadanganCKCZVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KadanganCKCZVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KadanganCKCZVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KadanganCKCZVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KadanganCKCZVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KadanganCKCZVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KadanganCKCZVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KadanganCKCZVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KadanganCKCZVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KadanganCKCZVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ��ֵʱ��
*
* @return ��ֵʱ��
*/
public UFDateTime getCz_time () {
return (UFDateTime) this.getAttributeValue( KadanganCKCZVO.CZ_TIME);
 } 

/** 
* ���ó�ֵʱ��
*
* @param cz_time ��ֵʱ��
*/
public void setCz_time ( UFDateTime cz_time) {
this.setAttributeValue( KadanganCKCZVO.CZ_TIME,cz_time);
 } 

/** 
* ��ȡ�ο���������
*
* @return �ο���������
*/
public String getExpdata () {
return (String) this.getAttributeValue( KadanganCKCZVO.EXPDATA);
 } 

/** 
* ���ôο���������
*
* @param expdata �ο���������
*/
public void setExpdata ( String expdata) {
this.setAttributeValue( KadanganCKCZVO.EXPDATA,expdata);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemid () {
return (String) this.getAttributeValue( KadanganCKCZVO.ITEMID);
 } 

/** 
* ������Ŀ����
*
* @param itemid ��Ŀ����
*/
public void setItemid ( String itemid) {
this.setAttributeValue( KadanganCKCZVO.ITEMID,itemid);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItemname () {
return (String) this.getAttributeValue( KadanganCKCZVO.ITEMNAME);
 } 

/** 
* ������Ŀ����
*
* @param itemname ��Ŀ����
*/
public void setItemname ( String itemname) {
this.setAttributeValue( KadanganCKCZVO.ITEMNAME,itemname);
 } 

/** 
* ��ȡ�ο�������
*
* @return �ο�������
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KadanganCKCZVO.KABILI);
 } 

/** 
* ���ôο�������
*
* @param kabili �ο�������
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KadanganCKCZVO.KABILI,kabili);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_huiyuan_kadangan () {
return (String) this.getAttributeValue( KadanganCKCZVO.PK_HK_HUIYUAN_KADANGAN);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_huiyuan_kadangan �ϲ㵥������
*/
public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
this.setAttributeValue( KadanganCKCZVO.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
 } 

/** 
* ��ȡ�ο���ֵpk
*
* @return �ο���ֵpk
*/
public String getPk_hk_huiyuan_kadangan_ckcz () {
return (String) this.getAttributeValue( KadanganCKCZVO.PK_HK_HUIYUAN_KADANGAN_CKCZ);
 } 

/** 
* ���ôο���ֵpk
*
* @param pk_hk_huiyuan_kadangan_ckcz �ο���ֵpk
*/
public void setPk_hk_huiyuan_kadangan_ckcz ( String pk_hk_huiyuan_kadangan_ckcz) {
this.setAttributeValue( KadanganCKCZVO.PK_HK_HUIYUAN_KADANGAN_CKCZ,pk_hk_huiyuan_kadangan_ckcz);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getPrice () {
return (UFDouble) this.getAttributeValue( KadanganCKCZVO.PRICE);
 } 

/** 
* ���õ���
*
* @param price ����
*/
public void setPrice ( UFDouble price) {
this.setAttributeValue( KadanganCKCZVO.PRICE,price);
 } 

/** 
* ��ȡ�ο���ʼ����
*
* @return �ο���ʼ����
*/
public String getStartdata () {
return (String) this.getAttributeValue( KadanganCKCZVO.STARTDATA);
 } 

/** 
* ���ôο���ʼ����
*
* @param startdata �ο���ʼ����
*/
public void setStartdata ( String startdata) {
this.setAttributeValue( KadanganCKCZVO.STARTDATA,startdata);
 } 

/** 
* ��ȡ�ο�ˮ��
*
* @return �ο�ˮ��
*/
public String getTimescardwaternum () {
return (String) this.getAttributeValue( KadanganCKCZVO.TIMESCARDWATERNUM);
 } 

/** 
* ���ôο�ˮ��
*
* @param timescardwaternum �ο�ˮ��
*/
public void setTimescardwaternum ( String timescardwaternum) {
this.setAttributeValue( KadanganCKCZVO.TIMESCARDWATERNUM,timescardwaternum);
 } 

/** 
* ��ȡ�ܴ���
*
* @return �ܴ���
*/
public UFDouble getTotalnum () {
return (UFDouble) this.getAttributeValue( KadanganCKCZVO.TOTALNUM);
 } 

/** 
* �����ܴ���
*
* @param totalnum �ܴ���
*/
public void setTotalnum ( UFDouble totalnum) {
this.setAttributeValue( KadanganCKCZVO.TOTALNUM,totalnum);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KadanganCKCZVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KadanganCKCZVO.TS,ts);
 } 

/** 
* ��ȡʹ�ô���
*
* @return ʹ�ô���
*/
public UFDouble getUsednum () {
return (UFDouble) this.getAttributeValue( KadanganCKCZVO.USEDNUM);
 } 

/** 
* ����ʹ�ô���
*
* @param usednum ʹ�ô���
*/
public void setUsednum ( UFDouble usednum) {
this.setAttributeValue( KadanganCKCZVO.USEDNUM,usednum);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KadanganCKCZVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KadanganCKCZVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KadanganCKCZVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KadanganCKCZVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KadanganCKCZVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KadanganCKCZVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KadanganCKCZVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KadanganCKCZVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KadanganCKCZVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KadanganCKCZVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KadanganCKCZVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KadanganCKCZVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KadanganCKCZVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KadanganCKCZVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KadanganCKCZVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KadanganCKCZVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϲ㵥�ݺ�
*
* @return �ϲ㵥�ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KadanganCKCZVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϲ㵥�ݺ�
*
* @param vsourcebillcode �ϲ㵥�ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KadanganCKCZVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ�˵���
*
* @return �˵���
*/
public String getZdh () {
return (String) this.getAttributeValue( KadanganCKCZVO.ZDH);
 } 

/** 
* �����˵���
*
* @param zdh �˵���
*/
public void setZdh ( String zdh) {
this.setAttributeValue( KadanganCKCZVO.ZDH,zdh);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KadanganCKCZVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( KadanganCKCZVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( KadanganCKCZVO.DR);
  } 
  
  public static final String LASTCOUNTTIME="lastcounttime";
  public void setLastcounttime ( UFDateTime lastcounttime) {
	  this.setAttributeValue( KadanganCKCZVO.LASTCOUNTTIME,lastcounttime);
  }
  public UFDateTime getLastcounttime () {
	  return (UFDateTime) this.getAttributeValue( KadanganCKCZVO.LASTCOUNTTIME);
  } 
  
  public static final String TIMES="times";
  public UFDouble getTimes () {
  return (UFDouble) this.getAttributeValue( KadanganCKCZVO.TIMES);
   } 
  public void setTimes ( UFDouble times) {
  this.setAttributeValue( KadanganCKCZVO.TIMES,times);
   } 
  
  public static final String YWDATE="ywdate";
  public void setYwdate ( String ywdate) {
	  this.setAttributeValue( KadanganCKCZVO.YWDATE,ywdate);
  }
  public String getYwdate () {
	  return (String) this.getAttributeValue( KadanganCKCZVO.YWDATE );
  } 
  
}