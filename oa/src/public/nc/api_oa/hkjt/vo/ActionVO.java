package nc.api_oa.hkjt.vo;

import java.io.Serializable;

public class ActionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8207772955448235183L;
	
	public ActionVO(String actionName, Class paramClass) {
		super();
		this.actionName = actionName;
		this.paramClass = paramClass;
	}
	
	private String actionName;
	private Class paramClass;

	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Class getParamClass() {
		return paramClass;
	}
	public void setParamClass(Class paramClass) {
		this.paramClass = paramClass;
	}
	
}
