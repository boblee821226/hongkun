package nc.vo.hkjt.oa;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

/**
 * OA��־vo
 */
public class HkOaInfoVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6178854173564274682L;
	
	private String pk_hk_oa_info;	// oa����id
	private String workflowid;		// oa������id
	private String parentbilltype;
	private String pk_billtypecode;
	private String pk_billtypeid;
	private String billid;
	private String oa_status;	// �ѷ��͡��ѹ鵵
	private String oa_msg;
	private String oa_url;
	private UFDateTime send_ts;
	private UFDateTime receive_ts;
	private UFDateTime ts;
	private Integer dr;
	
	public HkOaInfoVO () {
		super();
	}
	
	public HkOaInfoVO (
			 String pk_hk_oa_info
			,String workflowid
			,String parentbilltype
			,String pk_billtypecode
			,String pk_billtypeid
			,String billid
			) {
		super();
		this.pk_hk_oa_info = pk_hk_oa_info;		// OA-id
		this.workflowid = workflowid;			// OA-��������id
		this.parentbilltype = parentbilltype;	// ��������
		this.pk_billtypecode = pk_billtypecode;	// ��������
		this.pk_billtypeid = pk_billtypeid;		// ��������id
		this.billid = billid;					// ����id
		UFDateTime now = new UFDateTime();		// ��ǰʱ��
		this.send_ts = now;
		this.ts = now;
		this.dr = 0;
		this.oa_status = "�ѷ���";	// ��ʼ״̬Ϊ���ѷ���
	}
	
	@Override
	public String getPrimaryKey() {
		return "pk_hk_oa_info";
	}
	@Override
	public String getTableName() {
		return "hk_oa_info";
	}
	public String getPk_hk_oa_info() {
		return pk_hk_oa_info;
	}
	public void setPk_hk_oa_info(String pk_hk_oa_info) {
		this.pk_hk_oa_info = pk_hk_oa_info;
	}
	public String getParentbilltype() {
		return parentbilltype;
	}
	public void setParentbilltype(String parentbilltype) {
		this.parentbilltype = parentbilltype;
	}
	public String getPk_billtypecode() {
		return pk_billtypecode;
	}
	public void setPk_billtypecode(String pk_billtypecode) {
		this.pk_billtypecode = pk_billtypecode;
	}
	public String getPk_billtypeid() {
		return pk_billtypeid;
	}
	public void setPk_billtypeid(String pk_billtypeid) {
		this.pk_billtypeid = pk_billtypeid;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getOa_status() {
		return oa_status;
	}
	public void setOa_status(String oa_status) {
		this.oa_status = oa_status;
	}
	public String getOa_msg() {
		return oa_msg;
	}
	public void setOa_msg(String oa_msg) {
		this.oa_msg = oa_msg;
	}
	public String getOa_url() {
		return oa_url;
	}
	public void setOa_url(String oa_url) {
		this.oa_url = oa_url;
	}
	public UFDateTime getSend_ts() {
		return send_ts;
	}
	public void setSend_ts(UFDateTime send_ts) {
		this.send_ts = send_ts;
	}
	public UFDateTime getReceive_ts() {
		return receive_ts;
	}
	public void setReceive_ts(UFDateTime receive_ts) {
		this.receive_ts = receive_ts;
	}
	public UFDateTime getTs() {
		return ts;
	}
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	public Integer getDr() {
		return dr;
	}
	public void setDr(Integer dr) {
		this.dr = dr;
	}
	public String getWorkflowid() {
		return workflowid;
	}
	public void setWorkflowid(String workflowid) {
		this.workflowid = workflowid;
	}

}
