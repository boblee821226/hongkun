package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * 收单 子表 临时表
 */
 
public class ShoudanTempBVO extends SuperVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6519603698833476965L;
	
	/** 技师code	**/
	public String jishicode;
	
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
	
	public String getJishicode() {
		return jishicode;
	}

	public void setJishicode(String jishicode) {
		this.jishicode = jishicode;
	}

	@Override
	public String getTableName() {
		return "HK_JISHI_SHOUDAN_B_TEMP";
	}
	@Override
	public String getPrimaryKey() {
		return waternum;
	}
	
}
