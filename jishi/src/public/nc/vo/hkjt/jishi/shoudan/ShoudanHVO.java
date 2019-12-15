package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2016-5-12
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class ShoudanHVO extends SuperVO {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 8519163066782187186L;
/**
*手单主pk
*/
public String pk_hk_jishi_shoudan;
/**
*单据编号
*/
public String vbillcode;
/**
*单据类型
*/
public String vbilltypecode;
/**
*单据状态
*/
public Integer ibillstatus;
/**
*业务类型
*/
public String pk_busitype;
/**
*交易类型
*/
public String vtrantypecode;
/**
*单据日期
*/
public UFDate dbilldate;
/**
*制单人
*/
public String creator;
/**
*制单时间
*/
public UFDateTime creationtime;
/**
*修改人
*/
public String modifier;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*审核人
*/
public String approver;
/**
*审核时间
*/
public UFDateTime taudittime;
/**
*审批批语
*/
public String vapprovenote;
/**
*组织
*/
public String pk_org;
/**
*组织v
*/
public String pk_org_v;
/**
*集团
*/
public String pk_group;
/**
*自定义项01
*/
public String vdef01;
/**
*自定义项02
*/
public String vdef02;
/**
*自定义项03
*/
public String vdef03;
/**
*自定义项04
*/
public String vdef04;
/**
*自定义项05
*/
public String vdef05;
/**
*自定义项06
*/
public String vdef06;
/**
*自定义项07
*/
public String vdef07;
/**
*自定义项08
*/
public String vdef08;
/**
*自定义项09
*/
public String vdef09;
/**
*自定义项10
*/
public String vdef10;
/**
*备注
*/
public String vmemo;
/**
*技师编码
*/
public String jishicode;
/**
*技师名称
*/
public String jishiname;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_hk_jishi_shoudan的Getter方法.属性名：手单主pk
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getPk_hk_jishi_shoudan() {
return this.pk_hk_jishi_shoudan;
} 

/**
* 属性pk_hk_jishi_shoudan的Setter方法.属性名：手单主pk
* 创建日期:2016-5-12
* @param newPk_hk_jishi_shoudan java.lang.String
*/
public void setPk_hk_jishi_shoudan ( String pk_hk_jishi_shoudan) {
this.pk_hk_jishi_shoudan=pk_hk_jishi_shoudan;
} 
 
/**
* 属性 vbillcode的Getter方法.属性名：单据编号
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbillcode() {
return this.vbillcode;
} 

/**
* 属性vbillcode的Setter方法.属性名：单据编号
* 创建日期:2016-5-12
* @param newVbillcode java.lang.String
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
} 
 
/**
* 属性 vbilltypecode的Getter方法.属性名：单据类型
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVbilltypecode() {
return this.vbilltypecode;
} 

/**
* 属性vbilltypecode的Setter方法.属性名：单据类型
* 创建日期:2016-5-12
* @param newVbilltypecode java.lang.String
*/
public void setVbilltypecode ( String vbilltypecode) {
this.vbilltypecode=vbilltypecode;
} 
 
/**
* 属性 ibillstatus的Getter方法.属性名：单据状态
*  创建日期:2016-5-12
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getIbillstatus() {
return this.ibillstatus;
} 

/**
* 属性ibillstatus的Setter方法.属性名：单据状态
* 创建日期:2016-5-12
* @param newIbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setIbillstatus ( Integer ibillstatus) {
this.ibillstatus=ibillstatus;
} 
 
/**
* 属性 pk_busitype的Getter方法.属性名：业务类型
*  创建日期:2016-5-12
* @return nc.vo.pf.pub.BusitypeVO
*/
public String getPk_busitype() {
return this.pk_busitype;
} 

/**
* 属性pk_busitype的Setter方法.属性名：业务类型
* 创建日期:2016-5-12
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* 属性 vtrantypecode的Getter方法.属性名：交易类型
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVtrantypecode() {
return this.vtrantypecode;
} 

/**
* 属性vtrantypecode的Setter方法.属性名：交易类型
* 创建日期:2016-5-12
* @param newVtrantypecode java.lang.String
*/
public void setVtrantypecode ( String vtrantypecode) {
this.vtrantypecode=vtrantypecode;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：单据日期
*  创建日期:2016-5-12
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：单据日期
* 创建日期:2016-5-12
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 creator的Getter方法.属性名：制单人
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：制单人
* 创建日期:2016-5-12
* @param newCreator java.lang.String
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：制单时间
*  创建日期:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：制单时间
* 创建日期:2016-5-12
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2016-5-12
* @param newModifier java.lang.String
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2016-5-12
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 approver的Getter方法.属性名：审核人
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getApprover() {
return this.approver;
} 

/**
* 属性approver的Setter方法.属性名：审核人
* 创建日期:2016-5-12
* @param newApprover java.lang.String
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* 属性 taudittime的Getter方法.属性名：审核时间
*  创建日期:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaudittime() {
return this.taudittime;
} 

/**
* 属性taudittime的Setter方法.属性名：审核时间
* 创建日期:2016-5-12
* @param newTaudittime nc.vo.pub.lang.UFDateTime
*/
public void setTaudittime ( UFDateTime taudittime) {
this.taudittime=taudittime;
} 
 
/**
* 属性 vapprovenote的Getter方法.属性名：审批批语
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVapprovenote() {
return this.vapprovenote;
} 

/**
* 属性vapprovenote的Setter方法.属性名：审批批语
* 创建日期:2016-5-12
* @param newVapprovenote java.lang.String
*/
public void setVapprovenote ( String vapprovenote) {
this.vapprovenote=vapprovenote;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2016-5-12
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2016-5-12
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织v
*  创建日期:2016-5-12
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织v
* 创建日期:2016-5-12
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2016-5-12
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2016-5-12
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 vdef01的Getter方法.属性名：自定义项01
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef01() {
return this.vdef01;
} 

/**
* 属性vdef01的Setter方法.属性名：自定义项01
* 创建日期:2016-5-12
* @param newVdef01 java.lang.String
*/
public void setVdef01 ( String vdef01) {
this.vdef01=vdef01;
} 
 
/**
* 属性 vdef02的Getter方法.属性名：自定义项02
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef02() {
return this.vdef02;
} 

/**
* 属性vdef02的Setter方法.属性名：自定义项02
* 创建日期:2016-5-12
* @param newVdef02 java.lang.String
*/
public void setVdef02 ( String vdef02) {
this.vdef02=vdef02;
} 
 
/**
* 属性 vdef03的Getter方法.属性名：自定义项03
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef03() {
return this.vdef03;
} 

/**
* 属性vdef03的Setter方法.属性名：自定义项03
* 创建日期:2016-5-12
* @param newVdef03 java.lang.String
*/
public void setVdef03 ( String vdef03) {
this.vdef03=vdef03;
} 
 
/**
* 属性 vdef04的Getter方法.属性名：自定义项04
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef04() {
return this.vdef04;
} 

/**
* 属性vdef04的Setter方法.属性名：自定义项04
* 创建日期:2016-5-12
* @param newVdef04 java.lang.String
*/
public void setVdef04 ( String vdef04) {
this.vdef04=vdef04;
} 
 
/**
* 属性 vdef05的Getter方法.属性名：自定义项05
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef05() {
return this.vdef05;
} 

/**
* 属性vdef05的Setter方法.属性名：自定义项05
* 创建日期:2016-5-12
* @param newVdef05 java.lang.String
*/
public void setVdef05 ( String vdef05) {
this.vdef05=vdef05;
} 
 
/**
* 属性 vdef06的Getter方法.属性名：自定义项06
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef06() {
return this.vdef06;
} 

/**
* 属性vdef06的Setter方法.属性名：自定义项06
* 创建日期:2016-5-12
* @param newVdef06 java.lang.String
*/
public void setVdef06 ( String vdef06) {
this.vdef06=vdef06;
} 
 
/**
* 属性 vdef07的Getter方法.属性名：自定义项07
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef07() {
return this.vdef07;
} 

/**
* 属性vdef07的Setter方法.属性名：自定义项07
* 创建日期:2016-5-12
* @param newVdef07 java.lang.String
*/
public void setVdef07 ( String vdef07) {
this.vdef07=vdef07;
} 
 
/**
* 属性 vdef08的Getter方法.属性名：自定义项08
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef08() {
return this.vdef08;
} 

/**
* 属性vdef08的Setter方法.属性名：自定义项08
* 创建日期:2016-5-12
* @param newVdef08 java.lang.String
*/
public void setVdef08 ( String vdef08) {
this.vdef08=vdef08;
} 
 
/**
* 属性 vdef09的Getter方法.属性名：自定义项09
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef09() {
return this.vdef09;
} 

/**
* 属性vdef09的Setter方法.属性名：自定义项09
* 创建日期:2016-5-12
* @param newVdef09 java.lang.String
*/
public void setVdef09 ( String vdef09) {
this.vdef09=vdef09;
} 
 
/**
* 属性 vdef10的Getter方法.属性名：自定义项10
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVdef10() {
return this.vdef10;
} 

/**
* 属性vdef10的Setter方法.属性名：自定义项10
* 创建日期:2016-5-12
* @param newVdef10 java.lang.String
*/
public void setVdef10 ( String vdef10) {
this.vdef10=vdef10;
} 
 
/**
* 属性 vmemo的Getter方法.属性名：备注
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getVmemo() {
return this.vmemo;
} 

/**
* 属性vmemo的Setter方法.属性名：备注
* 创建日期:2016-5-12
* @param newVmemo java.lang.String
*/
public void setVmemo ( String vmemo) {
this.vmemo=vmemo;
} 
 
/**
* 属性 jishicode的Getter方法.属性名：技师编码
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getJishicode() {
return this.jishicode;
} 

/**
* 属性jishicode的Setter方法.属性名：技师编码
* 创建日期:2016-5-12
* @param newJishicode java.lang.String
*/
public void setJishicode ( String jishicode) {
this.jishicode=jishicode;
} 
 
/**
* 属性 jishiname的Getter方法.属性名：技师名称
*  创建日期:2016-5-12
* @return java.lang.String
*/
public String getJishiname() {
return this.jishiname;
} 

/**
* 属性jishiname的Setter方法.属性名：技师名称
* 创建日期:2016-5-12
* @param newJishiname java.lang.String
*/
public void setJishiname ( String jishiname) {
this.jishiname=jishiname;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2016-5-12
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.js_ShoudanHVO");
    }
   }
    