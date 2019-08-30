package nc.api.hkjt.vo;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * ��ͬ��ϢVO
 * @author lb
 */
public class ContractVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2586668419279949413L;
	
	private String orgCode;			// ������ҵ��˾����
	private String custId;			// �ͻ�ID
	private String custCode;		// �ͻ�����
	private String custName;		// �ͻ�����
	private String roomId;			// ����ID
	private String roomName;		// ��������-�����
	private String ctCode;			// ��ͬ��
	private String signDate;		// ǩ������
	private String startDate;		// ��������
	private String stopDate;		// ��ֹ����
	private String freeBeginDate;	// ���⿪ʼ����
	private String freeEndDate;		// �����ֹ����
	private String payment;			// �ɷѷ�ʽ
	private Double price;			// ����
	private Double area;			// ���
	private String psnId;			// Ա��ID
	private String psnCode;			// Ա������
	private String psnName;			// Ա������
	private String ctStatus;		// ��ͬ״̬
	
	private String region;			// ����
	private String version;			// �汾��
	private Double days;			// �Ʒ�ʱ��
	private String rentalEndDate;	// ���ȷ�Ͻ�������
	private String reallyStopDate;	// ʵ����ֹ����
	private Double ctMny;			// ��ͬ���
	private Double skMny;			// �տ��ܶ�
	private String isAgency;		// �Ƿ��н�����
	private Double commission;		// Ӷ����
	private ArrayList<ContractBVO> bVOs = new ArrayList<ContractBVO>();		// ��ͬ������
	
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
