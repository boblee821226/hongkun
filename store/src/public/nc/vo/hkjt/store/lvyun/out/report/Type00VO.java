package nc.vo.hkjt.store.lvyun.out.report;

import nc.vo.pub.SuperVO;
/**
 * ���������ŵ�������Ϣ���
 */
public class Type00VO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5144645958596290129L;
	
	// ��֯
	private String pk_org;
	// ����
	private String pk_dept;
	// ��ʼ����
	private String begin_date;
	// ��������
	private String end_date;
	// ϵͳ�ر�
	private String ts;
	private Integer dr;
	
	@Override
	public String getTableName() {
		return "hk_report_type00";
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getPk_dept() {
		return pk_dept;
	}

	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}
	
}
