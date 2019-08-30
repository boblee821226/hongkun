package nc.api.hkjt.vo;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 合同信息VO
 * @author lb
 */
public class ContractVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2586668419279949413L;
	
	private String orgCode;			// 所属物业公司编码
	private String custId;			// 客户ID
	private String custCode;		// 客户编码
	private String custName;		// 客户名称
	private String roomId;			// 房间ID
	private String roomName;		// 房间名称-房间号
	private String ctCode;			// 合同号
	private String signDate;		// 签订日期
	private String startDate;		// 起租日期
	private String stopDate;		// 终止日期
	private String freeBeginDate;	// 免租开始日期
	private String freeEndDate;		// 免租截止日期
	private String payment;			// 缴费方式
	private Double price;			// 单价
	private Double area;			// 面积
	private String psnId;			// 员工ID
	private String psnCode;			// 员工编码
	private String psnName;			// 员工名称
	private String ctStatus;		// 合同状态
	
	private String region;			// 区域
	private String version;			// 版本号
	private Double days;			// 计费时长
	private String rentalEndDate;	// 租金确认截至日期
	private String reallyStopDate;	// 实际终止日期
	private Double ctMny;			// 合同金额
	private Double skMny;			// 收款总额
	private String isAgency;		// 是否中介渠道
	private Double commission;		// 佣金倍数
	private ArrayList<ContractBVO> bVOs = new ArrayList<ContractBVO>();		// 合同子数据
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getCtCode() {
		return ctCode;
	}
	public void setCtCode(String ctCode) {
		this.ctCode = ctCode;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStopDate() {
		return stopDate;
	}
	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}
	public String getFreeBeginDate() {
		return freeBeginDate;
	}
	public void setFreeBeginDate(String freeBeginDate) {
		this.freeBeginDate = freeBeginDate;
	}
	public String getFreeEndDate() {
		return freeEndDate;
	}
	public void setFreeEndDate(String freeEndDate) {
		this.freeEndDate = freeEndDate;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getPsnId() {
		return psnId;
	}
	public void setPsnId(String psnId) {
		this.psnId = psnId;
	}
	public String getPsnCode() {
		return psnCode;
	}
	public void setPsnCode(String psnCode) {
		this.psnCode = psnCode;
	}
	public String getPsnName() {
		return psnName;
	}
	public void setPsnName(String psnName) {
		this.psnName = psnName;
	}
	public String getCtStatus() {
		return ctStatus;
	}
	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Double getDays() {
		return days;
	}
	public void setDays(Double days) {
		this.days = days;
	}
	public String getRentalEndDate() {
		return rentalEndDate;
	}
	public void setRentalEndDate(String rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}
	public String getReallyStopDate() {
		return reallyStopDate;
	}
	public void setReallyStopDate(String reallyStopDate) {
		this.reallyStopDate = reallyStopDate;
	}
	public Double getCtMny() {
		return ctMny;
	}
	public void setCtMny(Double ctMny) {
		this.ctMny = ctMny;
	}
	public Double getSkMny() {
		return skMny;
	}
	public void setSkMny(Double skMny) {
		this.skMny = skMny;
	}
	public String getIsAgency() {
		return isAgency;
	}
	public void setIsAgency(String isAgency) {
		this.isAgency = isAgency;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public ArrayList<ContractBVO> getbVOs() {
		return bVOs;
	}
	public void setbVOs(ArrayList<ContractBVO> bVOs) {
		this.bVOs = bVOs;
	}
}
