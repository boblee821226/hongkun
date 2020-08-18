package nc.api_oa.hkjt.vo;

import java.io.Serializable;

public class BillTypeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5972294154680320494L;
	
	public BillTypeVO(){
		super();
	}
	
	public BillTypeVO(String billTypeName,Class billVoClass){
		super();
		this.billTypeName = billTypeName;
		this.billVoClass = billVoClass;
	}
	
	private String billTypeName;
	private Class billVoClass;
	
	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public Class getBillVoClass() {
		return billVoClass;
	}

	public void setBillVoClass(Class billVoClass) {
		this.billVoClass = billVoClass;
	}

}
