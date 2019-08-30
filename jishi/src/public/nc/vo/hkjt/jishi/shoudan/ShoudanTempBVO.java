package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * �յ� �ӱ� ��ʱ��
 */
 
public class ShoudanTempBVO extends SuperVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6519603698833476965L;
	
	/** ��ʦcode	**/
	public String jishicode;
	
	/** �ֵ���	**/
	public String handnumber;
	/** �˵���	**/
	public String billid;
	/** ����	**/
	public UFDouble price;
	/** �ۿ�	**/
	public UFDouble discountprice;
	/** ʵ�ʽ�� **/
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
	* ���� handerid��Getter����.��������handerid
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getHanderid() {
	return this.handerid;
	} 
	
	/**
	* ����handerid��Setter����.��������handerid
	* ��������:2016-5-12
	* @param newHanderid java.lang.String
	*/
	public void setHanderid ( String handerid) {
	this.handerid=handerid;
	} 
	 
	/**
	* ���� roomid��Getter����.��������roomid
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getRoomid() {
	return this.roomid;
	} 
	
	/**
	* ����roomid��Setter����.��������roomid
	* ��������:2016-5-12
	* @param newRoomid java.lang.String
	*/
	public void setRoomid ( String roomid) {
	this.roomid=roomid;
	} 
	 
	/**
	* ���� goodsid��Getter����.��������goodsid
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getGoodsid() {
	return this.goodsid;
	} 
	
	/**
	* ����goodsid��Setter����.��������goodsid
	* ��������:2016-5-12
	* @param newGoodsid java.lang.String
	*/
	public void setGoodsid ( String goodsid) {
	this.goodsid=goodsid;
	} 
	 
	/**
	* ���� goodsname��Getter����.��������goodsname
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getGoodsname() {
	return this.goodsname;
	} 
	
	/**
	* ����goodsname��Setter����.��������goodsname
	* ��������:2016-5-12
	* @param newGoodsname java.lang.String
	*/
	public void setGoodsname ( String goodsname) {
	this.goodsname=goodsname;
	} 
	 
	/**
	* ���� starttime��Getter����.��������starttime
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getStarttime() {
	return this.starttime;
	} 
	
	/**
	* ����starttime��Setter����.��������starttime
	* ��������:2016-5-12
	* @param newStarttime java.lang.String
	*/
	public void setStarttime ( String starttime) {
	this.starttime=starttime;
	} 
	 
	/**
	* ���� endtime��Getter����.��������endtime
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getEndtime() {
	return this.endtime;
	} 
	
	/**
	* ����endtime��Setter����.��������endtime
	* ��������:2016-5-12
	* @param newEndtime java.lang.String
	*/
	public void setEndtime ( String endtime) {
	this.endtime=endtime;
	} 
	 
	/**
	* ���� remark��Getter����.��������remark
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getRemark() {
	return this.remark;
	} 
	
	/**
	* ����remark��Setter����.��������remark
	* ��������:2016-5-12
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
	* ���� typename��Getter����.��������type
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getTypename() {
	return this.typename;
	} 
	
	/**
	* ����typename��Setter����.��������type
	* ��������:2016-5-12
	* @param newTypename java.lang.String
	*/
	public void setTypename ( String typename) {
	this.typename=typename;
	} 
	 
	/**
	* ���� operatorname��Getter����.��������operatorname
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getOperatorname() {
	return this.operatorname;
	} 
	
	/**
	* ����operatorname��Setter����.��������operatorname
	* ��������:2016-5-12
	* @param newOperatorname java.lang.String
	*/
	public void setOperatorname ( String operatorname) {
	this.operatorname=operatorname;
	} 
	 
	/**
	* ���� checkname��Getter����.��������checkname
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getCheckname() {
	return this.checkname;
	} 
	
	/**
	* ����checkname��Setter����.��������checkname
	* ��������:2016-5-12
	* @param newCheckname java.lang.String
	*/
	public void setCheckname ( String checkname) {
	this.checkname=checkname;
	} 
	 
	/**
	* ���� waiter��Getter����.��������waiter
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getWaiter() {
	return this.waiter;
	} 
	
	/**
	* ����waiter��Setter����.��������waiter
	* ��������:2016-5-12
	* @param newWaiter java.lang.String
	*/
	public void setWaiter ( String waiter) {
	this.waiter=waiter;
	} 
	 
	/**
	* ���� checktime��Getter����.��������checktime
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getChecktime() {
	return this.checktime;
	} 
	
	/**
	* ����checktime��Setter����.��������checktime
	* ��������:2016-5-12
	* @param newChecktime java.lang.String
	*/
	public void setChecktime ( String checktime) {
	this.checktime=checktime;
	} 
	 
	/**
	* ���� waittime��Getter����.��������waittime
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getWaittime() {
	return this.waittime;
	} 
	
	/**
	* ����waittime��Setter����.��������waittime
	* ��������:2016-5-12
	* @param newWaittime java.lang.String
	*/
	public void setWaittime ( String waittime) {
	this.waittime=waittime;
	} 
	 
	/**
	* ���� machinename��Getter����.��������machinename
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getMachinename() {
	return this.machinename;
	} 
	
	/**
	* ����machinename��Setter����.��������machinename
	* ��������:2016-5-12
	* @param newMachinename java.lang.String
	*/
	public void setMachinename ( String machinename) {
	this.machinename=machinename;
	} 
	 
	/**
	* ���� waternum��Getter����.��������waternum
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getWaternum() {
	return this.waternum;
	} 
	
	/**
	* ����waternum��Setter����.��������waternum
	* ��������:2016-5-12
	* @param newWaternum java.lang.String
	*/
	public void setWaternum ( String waternum) {
	this.waternum=waternum;
	} 
	 
	/**
	* ���� mainid��Getter����.��������mainid
	*  ��������:2016-5-12
	* @return java.lang.String
	*/
	public String getMainid() {
	return this.mainid;
	} 
	
	/**
	* ����mainid��Setter����.��������mainid
	* ��������:2016-5-12
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
