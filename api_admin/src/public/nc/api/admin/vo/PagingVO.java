package nc.api.admin.vo;

import java.io.Serializable;

public class PagingVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2620172701979912384L;
	// paging: { page:int, limit:int }
	private Integer page;
	private Integer limit;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
