package nc.vo.hkjt.huiyuan.cikainfo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class CikainfoTempVO extends SuperVO {

	private static final long serialVersionUID = -2472685918468364522L;

	private String xmdl;	// 项目大类
	private String xmlx;	// 项目类型
	private String consumewaternum;		// 消费id
	private String timescardwaternum;	// 次卡id
	private String billid;			// 账单号
	private String operatedate;		// 操作时间
	private UFDouble numbercount;	// 使用次数
	private UFDouble price;		// 单价
	private String memberid;	// 卡号
	private String itemid;		// 次卡项目code
	private String itemname;	// 次卡项目name
	private String startdata;	// 次卡开始日期
	private String expdata;		// 次卡结束日期
	private String dian;		// 店
	private UFDouble totaltimes;// 总次数
	private UFDouble kabili;	// 卡比例
	
	private UFDateTime lastcounttime;	// 最后时间
	private UFDouble times;				// 剩余次数
	
	private String vdef01;
	private String vdef02;	// 账单信息
	private String vdef03;	// 不减次
	private String vdef04;	// 对方卡号  y_ka_code
	private String vdef05;
	
	
	public UFDouble getTotaltimes() {
		return totaltimes;
	}
	public void setTotaltimes(UFDouble totaltimes) {
		this.totaltimes = totaltimes;
	}
	public UFDouble getKabili() {
		return kabili;
	}
	public void setKabili(UFDouble kabili) {
		this.kabili = kabili;
	}
	public String getDian() {
		return dian;
	}
	public void setDian(String dian) {
		this.dian = dian;
	}
	public String getXmdl() {
		return xmdl;
	}
	public void setXmdl(String xmdl) {
		this.xmdl = xmdl;
	}
	public String getXmlx() {
		return xmlx;
	}
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	public String getConsumewaternum() {
		return consumewaternum;
	}
	public void setConsumewaternum(String consumewaternum) {
		this.consumewaternum = consumewaternum;
	}
	public String getTimescardwaternum() {
		return timescardwaternum;
	}
	public void setTimescardwaternum(String timescardwaternum) {
		this.timescardwaternum = timescardwaternum;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getOperatedate() {
		return operatedate;
	}
	public void setOperatedate(String operatedate) {
		this.operatedate = operatedate;
	}
	public UFDouble getNumbercount() {
		return numbercount;
	}
	public void setNumbercount(UFDouble numbercount) {
		this.numbercount = numbercount;
	}
	public UFDouble getPrice() {
		return price;
	}
	public void setPrice(UFDouble price) {
		this.price = price;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getStartdata() {
		return startdata;
	}
	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}
	public String getExpdata() {
		return expdata;
	}
	public void setExpdata(String expdata) {
		this.expdata = expdata;
	}
	
	public UFDateTime getLastcounttime() {
		return lastcounttime;
	}
	public void setLastcounttime(UFDateTime lastcounttime) {
		this.lastcounttime = lastcounttime;
	}
	public UFDouble getTimes() {
		return times;
	}
	public void setTimes(UFDouble times) {
		this.times = times;
	}
	
	public String getVdef01() {
		return vdef01;
	}
	public void setVdef01(String vdef01) {
		this.vdef01 = vdef01;
	}
	public String getVdef02() {
		return vdef02;
	}
	public void setVdef02(String vdef02) {
		this.vdef02 = vdef02;
	}
	public String getVdef03() {
		return vdef03;
	}
	public void setVdef03(String vdef03) {
		this.vdef03 = vdef03;
	}
	public String getVdef04() {
		return vdef04;
	}
	public void setVdef04(String vdef04) {
		this.vdef04 = vdef04;
	}
	public String getVdef05() {
		return vdef05;
	}
	public void setVdef05(String vdef05) {
		this.vdef05 = vdef05;
	}
	
	@Override
	public String getTableName() {
		return "hk_huiyuan_cikainfo_temp";
	}
	@Override
	public String getPrimaryKey() {
		return timescardwaternum;
	}
	
}
