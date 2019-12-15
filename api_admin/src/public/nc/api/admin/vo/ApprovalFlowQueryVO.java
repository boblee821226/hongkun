package nc.api.admin.vo;

import java.io.Serializable;

public class ApprovalFlowQueryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5341345418256720800L;

	/**
	 * 查询审批流-要做、已做
	 * {
	 * 	paging: { page:int, limit:int }
	 * }
	 */
	private TemplateVO[] templateList;
	private PagingVO paging;
	private String[] option;
	
	public TemplateVO[] getTemplateList() {
		return templateList;
	}
	public void setTemplateList(TemplateVO[] templateList) {
		this.templateList = templateList;
	}
	public PagingVO getPaging() {
		return paging;
	}
	public void setPaging(PagingVO paging) {
		this.paging = paging;
	}
	public String[] getOption() {
		return option;
	}
	public void setOption(String[] option) {
		this.option = option;
	}
	
}
