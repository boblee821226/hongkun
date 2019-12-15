package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2016-5-12
 * @author YONYOU NC
 * @version NC65
 */
 
public class ShoudanBVO extends SuperVO {

/**
	 * 
	 */
	private static final long serialVersionUID = 3001239119213204414L;
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
*�ֵ���pk
*/
public String pk_hk_jishi_shoudan_b;
/**
*�к�
*/
public Integer vrowno;
/**
*�ϲ㵥������
*/
public String csourcetypecode;
/**
*�ϴε��ݺ�
*/
public String vsourcebillcode;
/**
*�ϲ㵥��id
*/
public String csourcebillid;
/**
*�ϲ㵥�ݱ���id
*/
public String csourcebillbid;
/**
*Դͷ��������
*/
public String cfirsttypecode;
/**
*Դͷ���ݺ�
*/
public String vfirstbillcode;
/**
*Դͷ����id
*/
public String cfirstbillid;
/**
*Դͷ���ݱ���id
*/
public String cfirstbillbid;
/**
*�Զ�����01
*/
public String vbdef01;
/**
*�Զ�����02
*/
public String vbdef02;
/**
*�Զ�����03
*/
public String vbdef03;
/**
*�Զ�����04
*/
public String vbdef04;
/**
*�Զ�����05
*/
public String vbdef05;
/**
*�Զ�����06
*/
public String vbdef06;
/**
*�Զ�����07
*/
public String vbdef07;
/**
*�Զ�����08
*/
public String vbdef08;
/**
*�Զ�����09
*/
public String vbdef09;
/**
*�Զ�����10
*/
public String vbdef10;
/**
*��ע
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
*�ϲ㵥������
*/
public String pk_hk_jishi_shoudan;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_hk_jishi_shoudan_b��Getter����.���������ֵ���pk
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getPk_hk_jishi_shoudan_b() {
return this.pk_hk_jishi_shoudan_b;
} 

/**
* ����pk_hk_jishi_shoudan_b��Setter����.���������ֵ���pk
* ��������:2016-5-12
* @param newPk_hk_jishi_shoudan_b java.lang.String
*/
public void setPk_hk_jishi_shoudan_b ( String pk_hk_jishi_shoudan_b) {
this.pk_hk_jishi_shoudan_b=pk_hk_jishi_shoudan_b;
} 
 
/**
* ���� vrowno��Getter����.���������к�
*  ��������:2016-5-12
* @return java.lang.Integer
*/
public Integer getVrowno() {
return this.vrowno;
} 

/**
* ����vrowno��Setter����.���������к�
* ��������:2016-5-12
* @param newVrowno java.lang.Integer
*/
public void setVrowno ( Integer vrowno) {
this.vrowno=vrowno;
} 
 
/**
* ���� csourcetypecode��Getter����.���������ϲ㵥������
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getCsourcetypecode() {
return this.csourcetypecode;
} 

/**
* ����csourcetypecode��Setter����.���������ϲ㵥������
* ��������:2016-5-12
* @param newCsourcetypecode java.lang.String
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.csourcetypecode=csourcetypecode;
} 
 
/**
* ���� vsourcebillcode��Getter����.���������ϴε��ݺ�
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVsourcebillcode() {
return this.vsourcebillcode;
} 

/**
* ����vsourcebillcode��Setter����.���������ϴε��ݺ�
* ��������:2016-5-12
* @param newVsourcebillcode java.lang.String
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.vsourcebillcode=vsourcebillcode;
} 
 
/**
* ���� csourcebillid��Getter����.���������ϲ㵥��id
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getCsourcebillid() {
return this.csourcebillid;
} 

/**
* ����csourcebillid��Setter����.���������ϲ㵥��id
* ��������:2016-5-12
* @param newCsourcebillid java.lang.String
*/
public void setCsourcebillid ( String csourcebillid) {
this.csourcebillid=csourcebillid;
} 
 
/**
* ���� csourcebillbid��Getter����.���������ϲ㵥�ݱ���id
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getCsourcebillbid() {
return this.csourcebillbid;
} 

/**
* ����csourcebillbid��Setter����.���������ϲ㵥�ݱ���id
* ��������:2016-5-12
* @param newCsourcebillbid java.lang.String
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.csourcebillbid=csourcebillbid;
} 
 
/**
* ���� cfirsttypecode��Getter����.��������Դͷ��������
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getCfirsttypecode() {
return this.cfirsttypecode;
} 

/**
* ����cfirsttypecode��Setter����.��������Դͷ��������
* ��������:2016-5-12
* @param newCfirsttypecode java.lang.String
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.cfirsttypecode=cfirsttypecode;
} 
 
/**
* ���� vfirstbillcode��Getter����.��������Դͷ���ݺ�
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVfirstbillcode() {
return this.vfirstbillcode;
} 

/**
* ����vfirstbillcode��Setter����.��������Դͷ���ݺ�
* ��������:2016-5-12
* @param newVfirstbillcode java.lang.String
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.vfirstbillcode=vfirstbillcode;
} 
 
/**
* ���� cfirstbillid��Getter����.��������Դͷ����id
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getCfirstbillid() {
return this.cfirstbillid;
} 

/**
* ����cfirstbillid��Setter����.��������Դͷ����id
* ��������:2016-5-12
* @param newCfirstbillid java.lang.String
*/
public void setCfirstbillid ( String cfirstbillid) {
this.cfirstbillid=cfirstbillid;
} 
 
/**
* ���� cfirstbillbid��Getter����.��������Դͷ���ݱ���id
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getCfirstbillbid() {
return this.cfirstbillbid;
} 

/**
* ����cfirstbillbid��Setter����.��������Դͷ���ݱ���id
* ��������:2016-5-12
* @param newCfirstbillbid java.lang.String
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.cfirstbillbid=cfirstbillbid;
} 
 
/**
* ���� vbdef01��Getter����.���������Զ�����01
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef01() {
return this.vbdef01;
} 

/**
* ����vbdef01��Setter����.���������Զ�����01
* ��������:2016-5-12
* @param newVbdef01 java.lang.String
*/
public void setVbdef01 ( String vbdef01) {
this.vbdef01=vbdef01;
} 
 
/**
* ���� vbdef02��Getter����.���������Զ�����02
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef02() {
return this.vbdef02;
} 

/**
* ����vbdef02��Setter����.���������Զ�����02
* ��������:2016-5-12
* @param newVbdef02 java.lang.String
*/
public void setVbdef02 ( String vbdef02) {
this.vbdef02=vbdef02;
} 
 
/**
* ���� vbdef03��Getter����.���������Զ�����03
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef03() {
return this.vbdef03;
} 

/**
* ����vbdef03��Setter����.���������Զ�����03
* ��������:2016-5-12
* @param newVbdef03 java.lang.String
*/
public void setVbdef03 ( String vbdef03) {
this.vbdef03=vbdef03;
} 
 
/**
* ���� vbdef04��Getter����.���������Զ�����04
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef04() {
return this.vbdef04;
} 

/**
* ����vbdef04��Setter����.���������Զ�����04
* ��������:2016-5-12
* @param newVbdef04 java.lang.String
*/
public void setVbdef04 ( String vbdef04) {
this.vbdef04=vbdef04;
} 
 
/**
* ���� vbdef05��Getter����.���������Զ�����05
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef05() {
return this.vbdef05;
} 

/**
* ����vbdef05��Setter����.���������Զ�����05
* ��������:2016-5-12
* @param newVbdef05 java.lang.String
*/
public void setVbdef05 ( String vbdef05) {
this.vbdef05=vbdef05;
} 
 
/**
* ���� vbdef06��Getter����.���������Զ�����06
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef06() {
return this.vbdef06;
} 

/**
* ����vbdef06��Setter����.���������Զ�����06
* ��������:2016-5-12
* @param newVbdef06 java.lang.String
*/
public void setVbdef06 ( String vbdef06) {
this.vbdef06=vbdef06;
} 
 
/**
* ���� vbdef07��Getter����.���������Զ�����07
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef07() {
return this.vbdef07;
} 

/**
* ����vbdef07��Setter����.���������Զ�����07
* ��������:2016-5-12
* @param newVbdef07 java.lang.String
*/
public void setVbdef07 ( String vbdef07) {
this.vbdef07=vbdef07;
} 
 
/**
* ���� vbdef08��Getter����.���������Զ�����08
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef08() {
return this.vbdef08;
} 

/**
* ����vbdef08��Setter����.���������Զ�����08
* ��������:2016-5-12
* @param newVbdef08 java.lang.String
*/
public void setVbdef08 ( String vbdef08) {
this.vbdef08=vbdef08;
} 
 
/**
* ���� vbdef09��Getter����.���������Զ�����09
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef09() {
return this.vbdef09;
} 

/**
* ����vbdef09��Setter����.���������Զ�����09
* ��������:2016-5-12
* @param newVbdef09 java.lang.String
*/
public void setVbdef09 ( String vbdef09) {
this.vbdef09=vbdef09;
} 
 
/**
* ���� vbdef10��Getter����.���������Զ�����10
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbdef10() {
return this.vbdef10;
} 

/**
* ����vbdef10��Setter����.���������Զ�����10
* ��������:2016-5-12
* @param newVbdef10 java.lang.String
*/
public void setVbdef10 ( String vbdef10) {
this.vbdef10=vbdef10;
} 
 
/**
* ���� vbmemo��Getter����.����������ע
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbmemo() {
return this.vbmemo;
} 

/**
* ����vbmemo��Setter����.����������ע
* ��������:2016-5-12
* @param newVbmemo java.lang.String
*/
public void setVbmemo ( String vbmemo) {
this.vbmemo=vbmemo;
} 
 
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
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2016-5-12
* @return String
*/
public String getPk_hk_jishi_shoudan(){
return this.pk_hk_jishi_shoudan;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2016-5-12
* @param newPk_hk_jishi_shoudan String
*/
public void setPk_hk_jishi_shoudan(String pk_hk_jishi_shoudan){
this.pk_hk_jishi_shoudan=pk_hk_jishi_shoudan;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2016-5-12
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
