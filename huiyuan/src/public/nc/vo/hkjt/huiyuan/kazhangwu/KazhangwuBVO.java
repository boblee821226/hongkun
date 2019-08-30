package nc.vo.hkjt.huiyuan.kazhangwu;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KazhangwuBVO extends SuperVO {
	

	private static final long serialVersionUID = 1765353176166515910L;
	
	public KazhangwuBVO()
	{
		super();
	}
	public KazhangwuBVO(String xmdl,String xmlx)
	{
		super();
		this.setXmdl(xmdl);
		this.setXmlx(xmlx);
	}
	
/**
*源头单据表体id
*/
public static final String CFIRSTBILLBID="cfirstbillbid";
/**
*源头单据id
*/
public static final String CFIRSTBILLID="cfirstbillid";
/**
*源头单据类型
*/
public static final String CFIRSTTYPECODE="cfirsttypecode";
/**
*上层单据表体id
*/
public static final String CSOURCEBILLBID="csourcebillbid";
/**
*上层单据id
*/
public static final String CSOURCEBILLID="csourcebillid";
/**
*上层单据类型
*/
public static final String CSOURCETYPECODE="csourcetypecode";
/**
*卡结
*/
public static final String KAJIE="kajie";
/**
*卡比例卡结
*/
public static final String KAJIE_KBL="kajie_kbl";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KAZHANGWU="pk_hk_huiyuan_kazhangwu";
/**
*会员账务子表pk
*/
public static final String PK_HK_HUIYUAN_KAZHANGWU_B="pk_hk_huiyuan_kazhangwu_b";
/**
*时间戳
*/
public static final String TS="ts";
/**
*自定义项01
*/
public static final String VBDEF01="vbdef01";
/**
*自定义项02
*/
public static final String VBDEF02="vbdef02";
/**
*自定义项03
*/
public static final String VBDEF03="vbdef03";
/**
*自定义项04
*/
public static final String VBDEF04="vbdef04";
/**
*自定义项05
*/
public static final String VBDEF05="vbdef05";
/**
*自定义项06
*/
public static final String VBDEF06="vbdef06";
/**
*自定义项07
*/
public static final String VBDEF07="vbdef07";
/**
*自定义项08
*/
public static final String VBDEF08="vbdef08";
/**
*自定义项09
*/
public static final String VBDEF09="vbdef09";
/**
*自定义项10
*/
public static final String VBDEF10="vbdef10";
/**
*备注
*/
public static final String VBMEMO="vbmemo";
/**
*源头单据号
*/
public static final String VFIRSTBILLCODE="vfirstbillcode";
/**
*行号
*/
public static final String VROWNO="vrowno";
/**
*上层单据号
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/**
*项目大类
*/
public static final String XMDL="xmdl";
/**
*项目类型
*/
public static final String XMLX="xmlx";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KazhangwuBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KazhangwuBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KazhangwuBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KazhangwuBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KazhangwuBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KazhangwuBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KazhangwuBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KazhangwuBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KazhangwuBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KazhangwuBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KazhangwuBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KazhangwuBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取卡结
*
* @return 卡结
*/
public UFDouble getKajie () {
return (UFDouble) this.getAttributeValue( KazhangwuBVO.KAJIE);
 } 

/** 
* 设置卡结
*
* @param kajie 卡结
*/
public void setKajie ( UFDouble kajie) {
this.setAttributeValue( KazhangwuBVO.KAJIE,kajie);
 } 

/** 
* 获取卡比例卡结
*
* @return 卡比例卡结
*/
public UFDouble getKajie_kbl () {
return (UFDouble) this.getAttributeValue( KazhangwuBVO.KAJIE_KBL);
 } 

/** 
* 设置卡比例卡结
*
* @param kajie_kbl 卡比例卡结
*/
public void setKajie_kbl ( UFDouble kajie_kbl) {
this.setAttributeValue( KazhangwuBVO.KAJIE_KBL,kajie_kbl);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kazhangwu () {
return (String) this.getAttributeValue( KazhangwuBVO.PK_HK_HUIYUAN_KAZHANGWU);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kazhangwu 上层单据主键
*/
public void setPk_hk_huiyuan_kazhangwu ( String pk_hk_huiyuan_kazhangwu) {
this.setAttributeValue( KazhangwuBVO.PK_HK_HUIYUAN_KAZHANGWU,pk_hk_huiyuan_kazhangwu);
 } 

/** 
* 获取会员账务子表pk
*
* @return 会员账务子表pk
*/
public String getPk_hk_huiyuan_kazhangwu_b () {
return (String) this.getAttributeValue( KazhangwuBVO.PK_HK_HUIYUAN_KAZHANGWU_B);
 } 

/** 
* 设置会员账务子表pk
*
* @param pk_hk_huiyuan_kazhangwu_b 会员账务子表pk
*/
public void setPk_hk_huiyuan_kazhangwu_b ( String pk_hk_huiyuan_kazhangwu_b) {
this.setAttributeValue( KazhangwuBVO.PK_HK_HUIYUAN_KAZHANGWU_B,pk_hk_huiyuan_kazhangwu_b);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KazhangwuBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KazhangwuBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KazhangwuBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KazhangwuBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KazhangwuBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KazhangwuBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KazhangwuBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KazhangwuBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KazhangwuBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KazhangwuBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KazhangwuBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KazhangwuBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KazhangwuBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KazhangwuBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KazhangwuBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KazhangwuBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KazhangwuBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KazhangwuBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KazhangwuBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KazhangwuBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KazhangwuBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取项目大类
*
* @return 项目大类
*/
public String getXmdl () {
return (String) this.getAttributeValue( KazhangwuBVO.XMDL);
 } 

/** 
* 设置项目大类
*
* @param xmdl 项目大类
*/
public void setXmdl ( String xmdl) {
this.setAttributeValue( KazhangwuBVO.XMDL,xmdl);
 } 

/** 
* 获取项目类型
*
* @return 项目类型
*/
public String getXmlx () {
return (String) this.getAttributeValue( KazhangwuBVO.XMLX);
 } 

/** 
* 设置项目类型
*
* @param xmlx 项目类型
*/
public void setXmlx ( String xmlx) {
this.setAttributeValue( KazhangwuBVO.XMLX,xmlx);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KazhangwuBVO");
  }
}