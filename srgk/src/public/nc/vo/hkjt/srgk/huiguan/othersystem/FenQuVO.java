package nc.vo.hkjt.srgk.huiguan.othersystem;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;


/**
 * @author zhangjc
 *外系商品分类VO
 */
public class FenQuVO extends SuperVO {
	private static final long serialVersionUID = -2056598380640421460L;
	private String billid;
	private UFDateTime begintime;
	private UFDateTime endtime;
	private String goodsname;
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public UFDateTime getBegintime() {
		return begintime;
	}
	public void setBegintime(UFDateTime begintime) {
		this.begintime = begintime;
	}
	public UFDateTime getEndtime() {
		return endtime;
	}
	public void setEndtime(UFDateTime endtime) {
		this.endtime = endtime;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	
}
