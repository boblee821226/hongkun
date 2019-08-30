package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2016-5-12
 * @author YONYOU NC
 * @version NC65
 */
 
public class ShoudanBVO extends SuperVO {

/**
	 * 
	 */
	private static final long serialVersionUID = 3001239119213204414L;
/** 手单号	**/
public String handnumber;
/** 账单号	**/
public String billid;
/** 单价	**/
public UFDouble price;
/** 折扣	**/
public UFDouble discountprice;
/** 实际金额 **/
public UFDouble realmoney;

/**
*手单子pk
*/
public String pk_hk_jishi_shoudan_b;
/**
*行号
*/
public Integer vrowno;
/**
*上层单据类型
*/
public String csourcetypecode;
/**
*上次单据号
*/
public String vsourcebillcode;
/**
*上层单据id
*/
public String csourcebillid;
/**
*上层单据表体id
*/
public String csourcebillbid;
/**
*源头单据类型
*/
public String cfirsttypecode;
/**
*源头单据号
*/
public String vfirstbillcode;
/**
*源头单据id
*/
public String cfirstbillid;
/**
*源头单据表体id
*/
public String cfirstbillbid;
/**
*自定义项01
*/
public String vbdef01;
/**
*自定义项02
*/
public String vbdef02;
/**
*自定义项03
*/
public String vbdef03;
/**
*自定义项04
*/
public String vbdef04;
/**
*自定义项05
*/
public String vbdef05;
/**
*自定义项06
*/
public String vbdef06;
/**
*自定义项07
*/
public String vbdef07;
/**
*自定义项08
*/
public String vbdef08;
/**
*自定义项09
*/
public String vbdef09;
/**
*自定义项10
*/
public String vbdef10;
/**
*备注
*/
public String vbmemo;
/**
*handerid
*/
public String handerid;
/**
*roomid
*/
public String roomid;
/**
*goodsid
*/
public String goodsid;
/**
*goodsname
*/
public String goodsname;
/**
*starttime
*/
public String starttime;
/**
*endtime
*/
public String endtime;
/**
*remark
*/
public String remark;
/**
*number_add
*/
public String num_add;
/**
*number_sub
*/
public String num_sub;
/**
*type
*/
public String typename;
/**
*operatorname
*/
public String operatorname;
/**
*checkname
*/
public String checkname;
/**
*waiter
*/
public String waiter;
/**
*checktime
*/
public String checktime;
/**
*waittime
*/
public String waittime;
/**
*machinename
*/
public String machinename;
/**
*waternum
*/
public String waternum;
/**
*mainid
*/
public String mainid;
/**
*上层单据主键
*/
public String pk_hk_jishi_shoudan;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_hk_jishi_shoudan_b的Getter方法.属性名：手单子pk
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getPk_hk_jishi_shoudan_b() {
return this.pk_hk_jishi_shoudan_b;
} 

/**
* 属性pk_hk_jishi_shoudan_b的Setter方法.属性名：手单子pk
* 创建日期:2016-5-12
* @param newPk_hk_jishi_shoudan_b java.lang.String
*/
public void setPk_hk_jishi_shoudan_b ( String pk_hk_jishi_shoudan_b) {
this.pk_hk_jishi_shoudan_b=pk_hk_jishi_shoudan_b;
} 
 
/**
* 属性 vrowno的Getter方法.属性名：行号
*  创建日期:2016-5-12
* @return java.lang.Integer
*/
public Integer getVrowno() {
return this.vrowno;
} 

/**
* 属性vrowno的Setter方法.属性名：行号
* 创建日期:2016-5-12
* @param newVrowno java.lang.Integer
*/
public void setVrowno ( Integer vrowno) {
this.vrowno=vrowno;
} 
 
/**
* 属性 csourcetypecode的Getter方法.属性名：上层单据类型
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCsourcetypecode() {
return this.csourcetypecode;
} 

/**
* 属性csourcetypecode的Setter方法.属性名：上层单据类型
* 创建日期:2016-5-12
* @param newCsourcetypecode java.lang.String
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.csourcetypecode=csourcetypecode;
} 
 
/**
* 属性 vsourcebillcode的Getter方法.属性名：上次单据号
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVsourcebillcode() {
return this.vsourcebillcode;
} 

/**
* 属性vsourcebillcode的Setter方法.属性名：上次单据号
* 创建日期:2016-5-12
* @param newVsourcebillcode java.lang.String
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.vsourcebillcode=vsourcebillcode;
} 
 
/**
* 属性 csourcebillid的Getter方法.属性名：上层单据id
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCsourcebillid() {
return this.csourcebillid;
} 

/**
* 属性csourcebillid的Setter方法.属性名：上层单据id
* 创建日期:2016-5-12
* @param newCsourcebillid java.lang.String
*/
public void setCsourcebillid ( String csourcebillid) {
this.csourcebillid=csourcebillid;
} 
 
/**
* 属性 csourcebillbid的Getter方法.属性名：上层单据表体id
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCsourcebillbid() {
return this.csourcebillbid;
} 

/**
* 属性csourcebillbid的Setter方法.属性名：上层单据表体id
* 创建日期:2016-5-12
* @param newCsourcebillbid java.lang.String
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.csourcebillbid=csourcebillbid;
} 
 
/**
* 属性 cfirsttypecode的Getter方法.属性名：源头单据类型
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCfirsttypecode() {
return this.cfirsttypecode;
} 

/**
* 属性cfirsttypecode的Setter方法.属性名：源头单据类型
* 创建日期:2016-5-12
* @param newCfirsttypecode java.lang.String
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.cfirsttypecode=cfirsttypecode;
} 
 
/**
* 属性 vfirstbillcode的Getter方法.属性名：源头单据号
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVfirstbillcode() {
return this.vfirstbillcode;
} 

/**
* 属性vfirstbillcode的Setter方法.属性名：源头单据号
* 创建日期:2016-5-12
* @param newVfirstbillcode java.lang.String
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.vfirstbillcode=vfirstbillcode;
} 
 
/**
* 属性 cfirstbillid的Getter方法.属性名：源头单据id
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCfirstbillid() {
return this.cfirstbillid;
} 

/**
* 属性cfirstbillid的Setter方法.属性名：源头单据id
* 创建日期:2016-5-12
* @param newCfirstbillid java.lang.String
*/
public void setCfirstbillid ( String cfirstbillid) {
this.cfirstbillid=cfirstbillid;
} 
 
/**
* 属性 cfirstbillbid的Getter方法.属性名：源头单据表体id
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCfirstbillbid() {
return this.cfirstbillbid;
} 

/**
* 属性cfirstbillbid的Setter方法.属性名：源头单据表体id
* 创建日期:2016-5-12
* @param newCfirstbillbid java.lang.String
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.cfirstbillbid=cfirstbillbid;
} 
 
/**
* 属性 vbdef01的Getter方法.属性名：自定义项01
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef01() {
return this.vbdef01;
} 

/**
* 属性vbdef01的Setter方法.属性名：自定义项01
* 创建日期:2016-5-12
* @param newVbdef01 java.lang.String
*/
public void setVbdef01 ( String vbdef01) {
this.vbdef01=vbdef01;
} 
 
/**
* 属性 vbdef02的Getter方法.属性名：自定义项02
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef02() {
return this.vbdef02;
} 

/**
* 属性vbdef02的Setter方法.属性名：自定义项02
* 创建日期:2016-5-12
* @param newVbdef02 java.lang.String
*/
public void setVbdef02 ( String vbdef02) {
this.vbdef02=vbdef02;
} 
 
/**
* 属性 vbdef03的Getter方法.属性名：自定义项03
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef03() {
return this.vbdef03;
} 

/**
* 属性vbdef03的Setter方法.属性名：自定义项03
* 创建日期:2016-5-12
* @param newVbdef03 java.lang.String
*/
public void setVbdef03 ( String vbdef03) {
this.vbdef03=vbdef03;
} 
 
/**
* 属性 vbdef04的Getter方法.属性名：自定义项04
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef04() {
return this.vbdef04;
} 

/**
* 属性vbdef04的Setter方法.属性名：自定义项04
* 创建日期:2016-5-12
* @param newVbdef04 java.lang.String
*/
public void setVbdef04 ( String vbdef04) {
this.vbdef04=vbdef04;
} 
 
/**
* 属性 vbdef05的Getter方法.属性名：自定义项05
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef05() {
return this.vbdef05;
} 

/**
* 属性vbdef05的Setter方法.属性名：自定义项05
* 创建日期:2016-5-12
* @param newVbdef05 java.lang.String
*/
public void setVbdef05 ( String vbdef05) {
this.vbdef05=vbdef05;
} 
 
/**
* 属性 vbdef06的Getter方法.属性名：自定义项06
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef06() {
return this.vbdef06;
} 

/**
* 属性vbdef06的Setter方法.属性名：自定义项06
* 创建日期:2016-5-12
* @param newVbdef06 java.lang.String
*/
public void setVbdef06 ( String vbdef06) {
this.vbdef06=vbdef06;
} 
 
/**
* 属性 vbdef07的Getter方法.属性名：自定义项07
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef07() {
return this.vbdef07;
} 

/**
* 属性vbdef07的Setter方法.属性名：自定义项07
* 创建日期:2016-5-12
* @param newVbdef07 java.lang.String
*/
public void setVbdef07 ( String vbdef07) {
this.vbdef07=vbdef07;
} 
 
/**
* 属性 vbdef08的Getter方法.属性名：自定义项08
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef08() {
return this.vbdef08;
} 

/**
* 属性vbdef08的Setter方法.属性名：自定义项08
* 创建日期:2016-5-12
* @param newVbdef08 java.lang.String
*/
public void setVbdef08 ( String vbdef08) {
this.vbdef08=vbdef08;
} 
 
/**
* 属性 vbdef09的Getter方法.属性名：自定义项09
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef09() {
return this.vbdef09;
} 

/**
* 属性vbdef09的Setter方法.属性名：自定义项09
* 创建日期:2016-5-12
* @param newVbdef09 java.lang.String
*/
public void setVbdef09 ( String vbdef09) {
this.vbdef09=vbdef09;
} 
 
/**
* 属性 vbdef10的Getter方法.属性名：自定义项10
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbdef10() {
return this.vbdef10;
} 

/**
* 属性vbdef10的Setter方法.属性名：自定义项10
* 创建日期:2016-5-12
* @param newVbdef10 java.lang.String
*/
public void setVbdef10 ( String vbdef10) {
this.vbdef10=vbdef10;
} 
 
/**
* 属性 vbmemo的Getter方法.属性名：备注
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbmemo() {
return this.vbmemo;
} 

/**
* 属性vbmemo的Setter方法.属性名：备注
* 创建日期:2016-5-12
* @param newVbmemo java.lang.String
*/
public void setVbmemo ( String vbmemo) {
this.vbmemo=vbmemo;
} 
 
/**
* 属性 handerid的Getter方法.属性名：handerid
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getHanderid() {
return this.handerid;
} 

/**
* 属性handerid的Setter方法.属性名：handerid
* 创建日期:2016-5-12
* @param newHanderid java.lang.String
*/
public void setHanderid ( String handerid) {
this.handerid=handerid;
} 
 
/**
* 属性 roomid的Getter方法.属性名：roomid
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getRoomid() {
return this.roomid;
} 

/**
* 属性roomid的Setter方法.属性名：roomid
* 创建日期:2016-5-12
* @param newRoomid java.lang.String
*/
public void setRoomid ( String roomid) {
this.roomid=roomid;
} 
 
/**
* 属性 goodsid的Getter方法.属性名：goodsid
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getGoodsid() {
return this.goodsid;
} 

/**
* 属性goodsid的Setter方法.属性名：goodsid
* 创建日期:2016-5-12
* @param newGoodsid java.lang.String
*/
public void setGoodsid ( String goodsid) {
this.goodsid=goodsid;
} 
 
/**
* 属性 goodsname的Getter方法.属性名：goodsname
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getGoodsname() {
return this.goodsname;
} 

/**
* 属性goodsname的Setter方法.属性名：goodsname
* 创建日期:2016-5-12
* @param newGoodsname java.lang.String
*/
public void setGoodsname ( String goodsname) {
this.goodsname=goodsname;
} 
 
/**
* 属性 starttime的Getter方法.属性名：starttime
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getStarttime() {
return this.starttime;
} 

/**
* 属性starttime的Setter方法.属性名：starttime
* 创建日期:2016-5-12
* @param newStarttime java.lang.String
*/
public void setStarttime ( String starttime) {
this.starttime=starttime;
} 
 
/**
* 属性 endtime的Getter方法.属性名：endtime
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getEndtime() {
return this.endtime;
} 

/**
* 属性endtime的Setter方法.属性名：endtime
* 创建日期:2016-5-12
* @param newEndtime java.lang.String
*/
public void setEndtime ( String endtime) {
this.endtime=endtime;
} 
 
/**
* 属性 remark的Getter方法.属性名：remark
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getRemark() {
return this.remark;
} 

/**
* 属性remark的Setter方法.属性名：remark
* 创建日期:2016-5-12
* @param newRemark java.lang.String
*/
public void setRemark ( String remark) {
this.remark=remark;
} 
 
public String getNum_add() {
	return num_add;
}

public void setNum_add(String num_add) {
	this.num_add = num_add;
}

public String getNum_sub() {
	return num_sub;
}

public void setNum_sub(String num_sub) {
	this.num_sub = num_sub;
}

/**
* 属性 typename的Getter方法.属性名：type
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getTypename() {
return this.typename;
} 

/**
* 属性typename的Setter方法.属性名：type
* 创建日期:2016-5-12
* @param newTypename java.lang.String
*/
public void setTypename ( String typename) {
this.typename=typename;
} 
 
/**
* 属性 operatorname的Getter方法.属性名：operatorname
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getOperatorname() {
return this.operatorname;
} 

/**
* 属性operatorname的Setter方法.属性名：operatorname
* 创建日期:2016-5-12
* @param newOperatorname java.lang.String
*/
public void setOperatorname ( String operatorname) {
this.operatorname=operatorname;
} 
 
/**
* 属性 checkname的Getter方法.属性名：checkname
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCheckname() {
return this.checkname;
} 

/**
* 属性checkname的Setter方法.属性名：checkname
* 创建日期:2016-5-12
* @param newCheckname java.lang.String
*/
public void setCheckname ( String checkname) {
this.checkname=checkname;
} 
 
/**
* 属性 waiter的Getter方法.属性名：waiter
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getWaiter() {
return this.waiter;
} 

/**
* 属性waiter的Setter方法.属性名：waiter
* 创建日期:2016-5-12
* @param newWaiter java.lang.String
*/
public void setWaiter ( String waiter) {
this.waiter=waiter;
} 
 
/**
* 属性 checktime的Getter方法.属性名：checktime
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getChecktime() {
return this.checktime;
} 

/**
* 属性checktime的Setter方法.属性名：checktime
* 创建日期:2016-5-12
* @param newChecktime java.lang.String
*/
public void setChecktime ( String checktime) {
this.checktime=checktime;
} 
 
/**
* 属性 waittime的Getter方法.属性名：waittime
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getWaittime() {
return this.waittime;
} 

/**
* 属性waittime的Setter方法.属性名：waittime
* 创建日期:2016-5-12
* @param newWaittime java.lang.String
*/
public void setWaittime ( String waittime) {
this.waittime=waittime;
} 
 
/**
* 属性 machinename的Getter方法.属性名：machinename
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getMachinename() {
return this.machinename;
} 

/**
* 属性machinename的Setter方法.属性名：machinename
* 创建日期:2016-5-12
* @param newMachinename java.lang.String
*/
public void setMachinename ( String machinename) {
this.machinename=machinename;
} 
 
/**
* 属性 waternum的Getter方法.属性名：waternum
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getWaternum() {
return this.waternum;
} 

/**
* 属性waternum的Setter方法.属性名：waternum
* 创建日期:2016-5-12
* @param newWaternum java.lang.String
*/
public void setWaternum ( String waternum) {
this.waternum=waternum;
} 
 
/**
* 属性 mainid的Getter方法.属性名：mainid
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getMainid() {
return this.mainid;
} 

/**
* 属性mainid的Setter方法.属性名：mainid
* 创建日期:2016-5-12
* @param newMainid java.lang.String
*/
public void setMainid ( String mainid) {
this.mainid=mainid;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2016-5-12
* @return String
*/
public String getPk_hk_jishi_shoudan(){
return this.pk_hk_jishi_shoudan;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2016-5-12
* @param newPk_hk_jishi_shoudan String
*/
public void setPk_hk_jishi_shoudan(String pk_hk_jishi_shoudan){
this.pk_hk_jishi_shoudan=pk_hk_jishi_shoudan;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2016-5-12
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.js_ShoudanBVO");
    }

	public String getHandnumber() {
		return handnumber;
	}

	public void setHandnumber(String handnumber) {
		this.handnumber = handnumber;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public UFDouble getPrice() {
		return price;
	}

	public void setPrice(UFDouble price) {
		this.price = price;
	}

	public UFDouble getDiscountprice() {
		return discountprice;
	}

	public void setDiscountprice(UFDouble discountprice) {
		this.discountprice = discountprice;
	}

	public UFDouble getRealmoney() {
		return realmoney;
	}

	public void setRealmoney(UFDouble realmoney) {
		this.realmoney = realmoney;
	}
    
}
