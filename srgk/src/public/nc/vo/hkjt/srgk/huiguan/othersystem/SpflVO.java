package nc.vo.hkjt.srgk.huiguan.othersystem;

import nc.vo.pub.SuperVO;


/**
 * @author zhangjc
 *��ϵ��Ʒ����VO
 */
public class SpflVO extends SuperVO {
	private static final long serialVersionUID = -2056598380640421460L;
	private String catalogid;
	private String parentid;
	private String parentnodename;
	private String nodename;
	@Override
	public String getTableName() {
		// TODO �Զ����ɵķ������
		return "Dt_GoodCatalog";
	}
	@Override
	public String getPrimaryKey() {
		// TODO �Զ����ɵķ������
		return "catalogid";
	}
	public String getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(String catalogid) {
		this.catalogid = catalogid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getParentnodename() {
		return parentnodename;
	}
	public void setParentnodename(String parentnodename) {
		this.parentnodename = parentnodename;
	}
}
