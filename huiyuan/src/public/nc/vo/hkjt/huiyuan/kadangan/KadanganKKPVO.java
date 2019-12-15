package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2016-9-11
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class KadanganKKPVO extends SuperVO {
	
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
	*上层单据主键
	*/
	public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
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
	 *上层单据号
	 */
	public static final String VSOURCEBILLCODE="vsourcebillcode";
	/**
	*会员可开票主键
	*/
	public static final String PK_HK_HUIYUAN_KADANGAN_KKP="pk_hk_huiyuan_kadangan_kkp";
	/**
	*行号
	*/
	public static final String VROWNO="vrowno";
	
	/**
	*账单号
	*/
	public static final String ZDH="zdh";
	
	/**
	*充值时间
	*/
	public static final String CZ_TIME="cz_time";
	/**
	 *开票截止时间
	 */
	public static final String KPJZ_TIME="kpjz_time";
    
	/**
	 * 可开票金额
	 */
	public static final String KKP_JE="kkp_je";
	
	/**
	 * 已开票金额
	 */
	public static final String YKP_JE="ykp_je";
	
	/** 
	* 获取源头单据表体id
	*
	* @return 源头单据表体id
	*/
	public String getCfirstbillbid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CFIRSTBILLBID);
	 } 

	/** 
	* 设置源头单据表体id
	*
	* @param cfirstbillbid 源头单据表体id
	*/
	public void setCfirstbillbid ( String cfirstbillbid) {
	this.setAttributeValue( KadanganKKPVO.CFIRSTBILLBID,cfirstbillbid);
	 } 

	/** 
	* 获取源头单据id
	*
	* @return 源头单据id
	*/
	public String getCfirstbillid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CFIRSTBILLID);
	 } 

	/** 
	* 设置源头单据id
	*
	* @param cfirstbillid 源头单据id
	*/
	public void setCfirstbillid ( String cfirstbillid) {
	this.setAttributeValue( KadanganKKPVO.CFIRSTBILLID,cfirstbillid);
	 } 

	/** 
	* 获取源头单据类型
	*
	* @return 源头单据类型
	*/
	public String getCfirsttypecode () {
	return (String) this.getAttributeValue( KadanganKKPVO.CFIRSTTYPECODE);
	 } 

	/** 
	* 设置源头单据类型
	*
	* @param cfirsttypecode 源头单据类型
	*/
	public void setCfirsttypecode ( String cfirsttypecode) {
	this.setAttributeValue( KadanganKKPVO.CFIRSTTYPECODE,cfirsttypecode);
	 } 

	/** 
	* 获取上层单据表体id
	*
	* @return 上层单据表体id
	*/
	public String getCsourcebillbid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CSOURCEBILLBID);
	 } 

	/** 
	* 设置上层单据表体id
	*
	* @param csourcebillbid 上层单据表体id
	*/
	public void setCsourcebillbid ( String csourcebillbid) {
	this.setAttributeValue( KadanganKKPVO.CSOURCEBILLBID,csourcebillbid);
	 } 

	/** 
	* 获取上层单据id
	*
	* @return 上层单据id
	*/
	public String getCsourcebillid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CSOURCEBILLID);
	 } 

	/** 
	* 设置上层单据id
	*
	* @param csourcebillid 上层单据id
	*/
	public void setCsourcebillid ( String csourcebillid) {
	this.setAttributeValue( KadanganKKPVO.CSOURCEBILLID,csourcebillid);
	 } 

	/** 
	* 获取上层单据类型
	*
	* @return 上层单据类型
	*/
	public String getCsourcetypecode () {
	return (String) this.getAttributeValue( KadanganKKPVO.CSOURCETYPECODE);
	 } 

	/** 
	* 设置上层单据类型
	*
	* @param csourcetypecode 上层单据类型
	*/
	public void setCsourcetypecode ( String csourcetypecode) {
	this.setAttributeValue( KadanganKKPVO.CSOURCETYPECODE,csourcetypecode);
	 } 

	/** 
	* 获取可开票金额
	*
	* @return 可开票金额
	*/
	public UFDouble getKkp_je () {
	return (UFDouble) this.getAttributeValue( KadanganKKPVO.KKP_JE);
	 } 

	/** 
	* 设置可开票金额
	*
	* @param kkp_je 可开票金额
	*/
	public void setKkp_je ( UFDouble kkp_je) {
	this.setAttributeValue( KadanganKKPVO.KKP_JE,kkp_je);
	 } 
	
	/** 
	* 获取已开票金额
	*
	* @return 已开票金额
	*/
	public UFDouble getYkp_je () {
	return (UFDouble) this.getAttributeValue( KadanganKKPVO.YKP_JE);
	 } 

	/** 
	* 设置已开票金额
	*
	* @param ykp_je 已开票金额
	*/
	public void setYkp_je ( UFDouble ykp_je) {
	this.setAttributeValue( KadanganKKPVO.YKP_JE,ykp_je);
	 } 

	/** 
	* 获取充值时间
	*
	* @return 充值时间
	*/
	public UFDateTime getCz_time () {
	return (UFDateTime) this.getAttributeValue( KadanganKKPVO.CZ_TIME);
	 } 

	/** 
	* 设置充值时间
	*
	* @param cz_time 充值时间
	*/
	public void setCz_time ( UFDateTime cz_time) {
	this.setAttributeValue( KadanganKKPVO.CZ_TIME,cz_time);
	 } 
	
	/** 
	* 获取开票截止时间
	*
	* @return 开票截止时间
	*/
	public UFDateTime getKpjz_time () {
	return (UFDateTime) this.getAttributeValue( KadanganKKPVO.KPJZ_TIME);
	 } 

	/** 
	* 设置开票截止时间
	*
	* @param kpjz_time 开票截止时间
	*/
	public void setKpjz_time ( UFDateTime kpjz_time) {
	this.setAttributeValue( KadanganKKPVO.KPJZ_TIME,kpjz_time);
	 } 

	/** 
	* 获取上层单据主键
	*
	* @return 上层单据主键
	*/
	public String getPk_hk_huiyuan_kadangan () {
	return (String) this.getAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN);
	 } 

	/** 
	* 设置上层单据主键
	*
	* @param pk_hk_huiyuan_kadangan 上层单据主键
	*/
	public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
	this.setAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
	 } 

	/** 
	* 获取会员充值主键
	*
	* @return 会员充值主键
	*/
	public String getPk_hk_huiyuan_kadangan_kkp () {
	return (String) this.getAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN_KKP);
	 } 

	/** 
	* 设置会员充值主键
	*
	* @param pk_hk_huiyuan_kadangan_cz 会员充值主键
	*/
	public void setPk_hk_huiyuan_kadangan_kkp ( String pk_hk_huiyuan_kadangan_kkp) {
	this.setAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN_KKP,pk_hk_huiyuan_kadangan_kkp);
	 } 

	/** 
	* 获取时间戳
	*
	* @return 时间戳
	*/
	public UFDateTime getTs () {
	return (UFDateTime) this.getAttributeValue( KadanganKKPVO.TS);
	 } 

	/** 
	* 设置时间戳
	*
	* @param ts 时间戳
	*/
	public void setTs ( UFDateTime ts) {
	this.setAttributeValue( KadanganKKPVO.TS,ts);
	 } 

	/** 
	* 获取自定义项01
	*
	* @return 自定义项01
	*/
	public String getVbdef01 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF01);
	 } 

	/** 
	* 设置自定义项01
	*
	* @param vbdef01 自定义项01
	*/
	public void setVbdef01 ( String vbdef01) {
	this.setAttributeValue( KadanganKKPVO.VBDEF01,vbdef01);
	 } 

	/** 
	* 获取自定义项02
	*
	* @return 自定义项02
	*/
	public String getVbdef02 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF02);
	 } 

	/** 
	* 设置自定义项02
	*
	* @param vbdef02 自定义项02
	*/
	public void setVbdef02 ( String vbdef02) {
	this.setAttributeValue( KadanganKKPVO.VBDEF02,vbdef02);
	 } 

	/** 
	* 获取自定义项03
	*
	* @return 自定义项03
	*/
	public String getVbdef03 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF03);
	 } 

	/** 
	* 设置自定义项03
	*
	* @param vbdef03 自定义项03
	*/
	public void setVbdef03 ( String vbdef03) {
	this.setAttributeValue( KadanganKKPVO.VBDEF03,vbdef03);
	 } 

	/** 
	* 获取自定义项04
	*
	* @return 自定义项04
	*/
	public String getVbdef04 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF04);
	 } 

	/** 
	* 设置自定义项04
	*
	* @param vbdef04 自定义项04
	*/
	public void setVbdef04 ( String vbdef04) {
	this.setAttributeValue( KadanganKKPVO.VBDEF04,vbdef04);
	 } 

	/** 
	* 获取自定义项05
	*
	* @return 自定义项05
	*/
	public String getVbdef05 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF05);
	 } 

	/** 
	* 设置自定义项05
	*
	* @param vbdef05 自定义项05
	*/
	public void setVbdef05 ( String vbdef05) {
	this.setAttributeValue( KadanganKKPVO.VBDEF05,vbdef05);
	 } 

	/** 
	* 获取自定义项06
	*
	* @return 自定义项06
	*/
	public String getVbdef06 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF06);
	 } 

	/** 
	* 设置自定义项06
	*
	* @param vbdef06 自定义项06
	*/
	public void setVbdef06 ( String vbdef06) {
	this.setAttributeValue( KadanganKKPVO.VBDEF06,vbdef06);
	 } 

	/** 
	* 获取自定义项07
	*
	* @return 自定义项07
	*/
	public String getVbdef07 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF07);
	 } 

	/** 
	* 设置自定义项07
	*
	* @param vbdef07 自定义项07
	*/
	public void setVbdef07 ( String vbdef07) {
	this.setAttributeValue( KadanganKKPVO.VBDEF07,vbdef07);
	 } 

	/** 
	* 获取自定义项08
	*
	* @return 自定义项08
	*/
	public String getVbdef08 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF08);
	 } 

	/** 
	* 设置自定义项08
	*
	* @param vbdef08 自定义项08
	*/
	public void setVbdef08 ( String vbdef08) {
	this.setAttributeValue( KadanganKKPVO.VBDEF08,vbdef08);
	 } 

	/** 
	* 获取自定义项09
	*
	* @return 自定义项09
	*/
	public String getVbdef09 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF09);
	 } 

	/** 
	* 设置自定义项09
	*
	* @param vbdef09 自定义项09
	*/
	public void setVbdef09 ( String vbdef09) {
	this.setAttributeValue( KadanganKKPVO.VBDEF09,vbdef09);
	 } 

	/** 
	* 获取自定义项10
	*
	* @return 自定义项10
	*/
	public String getVbdef10 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF10);
	 } 

	/** 
	* 设置自定义项10
	*
	* @param vbdef10 自定义项10
	*/
	public void setVbdef10 ( String vbdef10) {
	this.setAttributeValue( KadanganKKPVO.VBDEF10,vbdef10);
	 } 

	/** 
	* 获取备注
	*
	* @return 备注
	*/
	public String getVbmemo () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBMEMO);
	 } 

	/** 
	* 设置备注
	*
	* @param vbmemo 备注
	*/
	public void setVbmemo ( String vbmemo) {
	this.setAttributeValue( KadanganKKPVO.VBMEMO,vbmemo);
	 } 

	/** 
	* 获取源头单据号
	*
	* @return 源头单据号
	*/
	public String getVfirstbillcode () {
	return (String) this.getAttributeValue( KadanganKKPVO.VFIRSTBILLCODE);
	 } 

	/** 
	* 设置源头单据号
	*
	* @param vfirstbillcode 源头单据号
	*/
	public void setVfirstbillcode ( String vfirstbillcode) {
	this.setAttributeValue( KadanganKKPVO.VFIRSTBILLCODE,vfirstbillcode);
	 } 

	/** 
	* 获取行号
	*
	* @return 行号
	*/
	public String getVrowno () {
	return (String) this.getAttributeValue( KadanganKKPVO.VROWNO);
	 } 

	/** 
	* 设置行号
	*
	* @param vrowno 行号
	*/
	public void setVrowno ( String vrowno) {
	this.setAttributeValue( KadanganKKPVO.VROWNO,vrowno);
	 } 

	/** 
	* 获取上层单据号
	*
	* @return 上层单据号
	*/
	public String getVsourcebillcode () {
	return (String) this.getAttributeValue( KadanganKKPVO.VSOURCEBILLCODE);
	 } 

	/** 
	* 设置上层单据号
	*
	* @param vsourcebillcode 上层单据号
	*/
	public void setVsourcebillcode ( String vsourcebillcode) {
	this.setAttributeValue( KadanganKKPVO.VSOURCEBILLCODE,vsourcebillcode);
	 } 

	/** 
	* 获取账单号
	*
	* @return 账单号
	*/
	public String getZdh () {
	return (String) this.getAttributeValue( KadanganKKPVO.ZDH);
	 } 

	/** 
	* 设置账单号
	*
	* @param zdh 账单号
	*/
	public void setZdh ( String zdh) {
	this.setAttributeValue( KadanganKKPVO.ZDH,zdh);
	 } 

     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KadanganKKPVO");
    }
    
    public static final String DR="dr";
    public void setDr ( Integer dr) {
  	  this.setAttributeValue( KadanganKKPVO.DR,dr);
    }
    public Integer getDr () {
  	  return (Integer) this.getAttributeValue( KadanganKKPVO.DR);
  	   } 
    
   }
