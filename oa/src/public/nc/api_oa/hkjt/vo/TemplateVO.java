package nc.api_oa.hkjt.vo;

import java.io.Serializable;

public class TemplateVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -592108921804646535L;
	
	private String billType;
	private String templateList;
	private String templateCard;
	
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getTemplateList() {
		return templateList;
	}
	public void setTemplateList(String templateList) {
		this.templateList = templateList;
	}
	public String getTemplateCard() {
		return templateCard;
	}
	public void setTemplateCard(String templateCard) {
		this.templateCard = templateCard;
	}
	
}
