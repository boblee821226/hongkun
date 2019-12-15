package nc.vo.ct.entity;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 * 合同合同条款VO
 * </p>
 * 创建日期:2010-03-18 19:39:27
 * 
 * @author
 * @version lizhengb
 */
public abstract class CtAbstractTermVO extends SuperVO {

  // dr
  public static final String DR = "dr";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 采购组织最新版本
  public static final String PK_ORG = "pk_org";

  // 采购组织
  public static final String PK_ORG_V = "pk_org_v";

  // 时间戳
  public static final String TS = "ts";

  // 备注
  public static final String VMEMO = "vmemo";

  // 其它信息
  public static final String VOTHERINFO = "votherinfo";

  // 条款编码
  public static final String VTERMCODE = "vtermcode";

  // 条款内容
  public static final String VTERMCONTENT = "vtermcontent";

  // 条款名称
  public static final String VTERMNAME = "vtermname";

  // 条款类型
  public static final String VTERMTYPENAME = "vtermtypename";

  /**
   * 
   */
  private static final long serialVersionUID = 6692806569145475201L;

  /**
   * 按照默认方式创建构造子. 创建日期:2010-03-18 19:39:27
   */
  public CtAbstractTermVO() {
    super();
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(CtAbstractTermVO.DR);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(CtAbstractTermVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(CtAbstractTermVO.PK_ORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(CtAbstractTermVO.PK_ORG_V);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(CtAbstractTermVO.TS);
  }

  public String getVmemo() {
    return (String) this.getAttributeValue(CtAbstractTermVO.VMEMO);
  }

  public String getVotherinfo() {
    return (String) this.getAttributeValue(CtAbstractTermVO.VOTHERINFO);
  }

  public String getVtermcode() {
    return (String) this.getAttributeValue(CtAbstractTermVO.VTERMCODE);
  }

  public String getVtermcontent() {
    return (String) this.getAttributeValue(CtAbstractTermVO.VTERMCONTENT);
  }

  public String getVtermname() {
    return (String) this.getAttributeValue(CtAbstractTermVO.VTERMNAME);
  }

  public String getVtermtypename() {
    return (String) this.getAttributeValue(CtAbstractTermVO.VTERMTYPENAME);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(CtAbstractTermVO.DR, dr);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(CtAbstractTermVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(CtAbstractTermVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(CtAbstractTermVO.PK_ORG_V, pk_org_v);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(CtAbstractTermVO.TS, ts);
  }

  public void setVmemo(String vmemo) {
    this.setAttributeValue(CtAbstractTermVO.VMEMO, vmemo);
  }

  public void setVotherinfo(String votherinfo) {
    this.setAttributeValue(CtAbstractTermVO.VOTHERINFO, votherinfo);
  }

  public void setVtermcode(String vtermcode) {
    this.setAttributeValue(CtAbstractTermVO.VTERMCODE, vtermcode);
  }

  public void setVtermcontent(String vtermcontent) {
    this.setAttributeValue(CtAbstractTermVO.VTERMCONTENT, vtermcontent);
  }

  public void setVtermname(String vtermname) {
    this.setAttributeValue(CtAbstractTermVO.VTERMNAME, vtermname);
  }

  public void setVtermtypename(String vtermtypename) {
    this.setAttributeValue(CtAbstractTermVO.VTERMTYPENAME, vtermtypename);
  }

  
  /**
   * 添加 自定义项
   * 2018年9月28日23:31:20
   */
	//自定义项1
	 public static final String VHKBDEF1 = "vhkbdef1";
	 
	 public String getVhkbdef1() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF1);
	 }
	 public void setVhkbdef1(String vhkbdef1) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF1, vhkbdef1);
	 }
	 
	//自定义项2
	 public static final String VHKBDEF2 = "vhkbdef2";
	 
	 public String getVhkbdef2() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF2);
	 }
	 public void setVhkbdef2(String vhkbdef2) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF2, vhkbdef2);
	 }
	 
	//自定义项3
	 public static final String VHKBDEF3 = "vhkbdef3";
	 
	 public String getVhkbdef3() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF3);
	 }
	 public void setVhkbdef3(String vhkbdef3) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF3, vhkbdef3);
	 }
	 
	//自定义项4
	 public static final String VHKBDEF4 = "vhkbdef4";
	 
	 public String getVhkbdef4() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF4);
	 }
	 public void setVhkbdef4(String vhkbdef4) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF4, vhkbdef4);
	 }
	 
	//自定义项5
	 public static final String VHKBDEF5 = "vhkbdef5";
	 
	 public String getVhkbdef5() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF5);
	 }
	 public void setVhkbdef5(String vhkbdef5) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF5, vhkbdef5);
	 }
	 
	//自定义项6
	 public static final String VHKBDEF6 = "vhkbdef6";
	 
	 public String getVhkbdef6() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF6);
	 }
	 public void setVhkbdef6(String vhkbdef6) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF6, vhkbdef6);
	 }
	
	//自定义项7
	 public static final String VHKBDEF7 = "vhkbdef7";
	 
	 public String getVhkbdef7() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF7);
	 }
	 public void setVhkbdef7(String vhkbdef7) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF7, vhkbdef7);
	 }
	 
	//自定义项8
	 public static final String VHKBDEF8 = "vhkbdef8";
	 
	 public String getVhkbdef8() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF8);
	 }
	 public void setVhkbdef8(String vhkbdef8) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF8, vhkbdef8);
	 }
	 
	//自定义项9
	 public static final String VHKBDEF9 = "vhkbdef9";
	 
	 public String getVhkbdef9() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF9);
	 }
	 public void setVhkbdef9(String vhkbdef9) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF9, vhkbdef9);
	 }
	 
	//自定义项10
	 public static final String VHKBDEF10 = "vhkbdef10";
	 
	 public String getVhkbdef10() {
	    return (String) this.getAttributeValue(CtAbstractTermVO.VHKBDEF10);
	 }
	 public void setVhkbdef10(String vhkbdef10) {
	    this.setAttributeValue(CtAbstractTermVO.VHKBDEF10, vhkbdef10);
	 }
}
