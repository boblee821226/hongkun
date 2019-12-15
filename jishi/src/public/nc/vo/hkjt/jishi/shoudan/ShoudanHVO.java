package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2016-5-12
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class ShoudanHVO extends SuperVO {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 8519163066782187186L;
/**
*�ֵ���pk
*/
public String pk_hk_jishi_shoudan;
/**
*���ݱ��
*/
public String vbillcode;
/**
*��������
*/
public String vbilltypecode;
/**
*����״̬
*/
public Integer ibillstatus;
/**
*ҵ������
*/
public String pk_busitype;
/**
*��������
*/
public String vtrantypecode;
/**
*��������
*/
public UFDate dbilldate;
/**
*�Ƶ���
*/
public String creator;
/**
*�Ƶ�ʱ��
*/
public UFDateTime creationtime;
/**
*�޸���
*/
public String modifier;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�����
*/
public String approver;
/**
*���ʱ��
*/
public UFDateTime taudittime;
/**
*��������
*/
public String vapprovenote;
/**
*��֯
*/
public String pk_org;
/**
*��֯v
*/
public String pk_org_v;
/**
*����
*/
public String pk_group;
/**
*�Զ�����01
*/
public String vdef01;
/**
*�Զ�����02
*/
public String vdef02;
/**
*�Զ�����03
*/
public String vdef03;
/**
*�Զ�����04
*/
public String vdef04;
/**
*�Զ�����05
*/
public String vdef05;
/**
*�Զ�����06
*/
public String vdef06;
/**
*�Զ�����07
*/
public String vdef07;
/**
*�Զ�����08
*/
public String vdef08;
/**
*�Զ�����09
*/
public String vdef09;
/**
*�Զ�����10
*/
public String vdef10;
/**
*��ע
*/
public String vmemo;
/**
*��ʦ����
*/
public String jishicode;
/**
*��ʦ����
*/
public String jishiname;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_hk_jishi_shoudan��Getter����.���������ֵ���pk
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getPk_hk_jishi_shoudan() {
return this.pk_hk_jishi_shoudan;
} 

/**
* ����pk_hk_jishi_shoudan��Setter����.���������ֵ���pk
* ��������:2016-5-12
* @param newPk_hk_jishi_shoudan java.lang.String
*/
public void setPk_hk_jishi_shoudan ( String pk_hk_jishi_shoudan) {
this.pk_hk_jishi_shoudan=pk_hk_jishi_shoudan;
} 
 
/**
* ���� vbillcode��Getter����.�����������ݱ��
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbillcode() {
return this.vbillcode;
} 

/**
* ����vbillcode��Setter����.�����������ݱ��
* ��������:2016-5-12
* @param newVbillcode java.lang.String
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
} 
 
/**
* ���� vbilltypecode��Getter����.����������������
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVbilltypecode() {
return this.vbilltypecode;
} 

/**
* ����vbilltypecode��Setter����.����������������
* ��������:2016-5-12
* @param newVbilltypecode java.lang.String
*/
public void setVbilltypecode ( String vbilltypecode) {
this.vbilltypecode=vbilltypecode;
} 
 
/**
* ���� ibillstatus��Getter����.������������״̬
*  ��������:2016-5-12
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getIbillstatus() {
return this.ibillstatus;
} 

/**
* ����ibillstatus��Setter����.������������״̬
* ��������:2016-5-12
* @param newIbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setIbillstatus ( Integer ibillstatus) {
this.ibillstatus=ibillstatus;
} 
 
/**
* ���� pk_busitype��Getter����.��������ҵ������
*  ��������:2016-5-12
* @return nc.vo.pf.pub.BusitypeVO
*/
public String getPk_busitype() {
return this.pk_busitype;
} 

/**
* ����pk_busitype��Setter����.��������ҵ������
* ��������:2016-5-12
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( String pk_busitype) {
this.pk_busitype=pk_busitype;
} 
 
/**
* ���� vtrantypecode��Getter����.����������������
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVtrantypecode() {
return this.vtrantypecode;
} 

/**
* ����vtrantypecode��Setter����.����������������
* ��������:2016-5-12
* @param newVtrantypecode java.lang.String
*/
public void setVtrantypecode ( String vtrantypecode) {
this.vtrantypecode=vtrantypecode;
} 
 
/**
* ���� dbilldate��Getter����.����������������
*  ��������:2016-5-12
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.����������������
* ��������:2016-5-12
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* ���� creator��Getter����.���������Ƶ���
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.���������Ƶ���
* ��������:2016-5-12
* @param newCreator java.lang.String
*/
public void setCreator ( String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.���������Ƶ�ʱ��
*  ��������:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.���������Ƶ�ʱ��
* ��������:2016-5-12
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2016-5-12
* @param newModifier java.lang.String
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2016-5-12
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� approver��Getter����.�������������
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getApprover() {
return this.approver;
} 

/**
* ����approver��Setter����.�������������
* ��������:2016-5-12
* @param newApprover java.lang.String
*/
public void setApprover ( String approver) {
this.approver=approver;
} 
 
/**
* ���� taudittime��Getter����.�����������ʱ��
*  ��������:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTaudittime() {
return this.taudittime;
} 

/**
* ����taudittime��Setter����.�����������ʱ��
* ��������:2016-5-12
* @param newTaudittime nc.vo.pub.lang.UFDateTime
*/
public void setTaudittime ( UFDateTime taudittime) {
this.taudittime=taudittime;
} 
 
/**
* ���� vapprovenote��Getter����.����������������
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVapprovenote() {
return this.vapprovenote;
} 

/**
* ����vapprovenote��Setter����.����������������
* ��������:2016-5-12
* @param newVapprovenote java.lang.String
*/
public void setVapprovenote ( String vapprovenote) {
this.vapprovenote=vapprovenote;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2016-5-12
* @return nc.vo.org.OrgVO
*/
public String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2016-5-12
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯v
*  ��������:2016-5-12
* @return nc.vo.vorg.OrgVersionVO
*/
public String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯v
* ��������:2016-5-12
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2016-5-12
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2016-5-12
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� vdef01��Getter����.���������Զ�����01
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef01() {
return this.vdef01;
} 

/**
* ����vdef01��Setter����.���������Զ�����01
* ��������:2016-5-12
* @param newVdef01 java.lang.String
*/
public void setVdef01 ( String vdef01) {
this.vdef01=vdef01;
} 
 
/**
* ���� vdef02��Getter����.���������Զ�����02
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef02() {
return this.vdef02;
} 

/**
* ����vdef02��Setter����.���������Զ�����02
* ��������:2016-5-12
* @param newVdef02 java.lang.String
*/
public void setVdef02 ( String vdef02) {
this.vdef02=vdef02;
} 
 
/**
* ���� vdef03��Getter����.���������Զ�����03
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef03() {
return this.vdef03;
} 

/**
* ����vdef03��Setter����.���������Զ�����03
* ��������:2016-5-12
* @param newVdef03 java.lang.String
*/
public void setVdef03 ( String vdef03) {
this.vdef03=vdef03;
} 
 
/**
* ���� vdef04��Getter����.���������Զ�����04
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef04() {
return this.vdef04;
} 

/**
* ����vdef04��Setter����.���������Զ�����04
* ��������:2016-5-12
* @param newVdef04 java.lang.String
*/
public void setVdef04 ( String vdef04) {
this.vdef04=vdef04;
} 
 
/**
* ���� vdef05��Getter����.���������Զ�����05
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef05() {
return this.vdef05;
} 

/**
* ����vdef05��Setter����.���������Զ�����05
* ��������:2016-5-12
* @param newVdef05 java.lang.String
*/
public void setVdef05 ( String vdef05) {
this.vdef05=vdef05;
} 
 
/**
* ���� vdef06��Getter����.���������Զ�����06
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef06() {
return this.vdef06;
} 

/**
* ����vdef06��Setter����.���������Զ�����06
* ��������:2016-5-12
* @param newVdef06 java.lang.String
*/
public void setVdef06 ( String vdef06) {
this.vdef06=vdef06;
} 
 
/**
* ���� vdef07��Getter����.���������Զ�����07
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef07() {
return this.vdef07;
} 

/**
* ����vdef07��Setter����.���������Զ�����07
* ��������:2016-5-12
* @param newVdef07 java.lang.String
*/
public void setVdef07 ( String vdef07) {
this.vdef07=vdef07;
} 
 
/**
* ���� vdef08��Getter����.���������Զ�����08
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef08() {
return this.vdef08;
} 

/**
* ����vdef08��Setter����.���������Զ�����08
* ��������:2016-5-12
* @param newVdef08 java.lang.String
*/
public void setVdef08 ( String vdef08) {
this.vdef08=vdef08;
} 
 
/**
* ���� vdef09��Getter����.���������Զ�����09
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef09() {
return this.vdef09;
} 

/**
* ����vdef09��Setter����.���������Զ�����09
* ��������:2016-5-12
* @param newVdef09 java.lang.String
*/
public void setVdef09 ( String vdef09) {
this.vdef09=vdef09;
} 
 
/**
* ���� vdef10��Getter����.���������Զ�����10
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVdef10() {
return this.vdef10;
} 

/**
* ����vdef10��Setter����.���������Զ�����10
* ��������:2016-5-12
* @param newVdef10 java.lang.String
*/
public void setVdef10 ( String vdef10) {
this.vdef10=vdef10;
} 
 
/**
* ���� vmemo��Getter����.����������ע
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getVmemo() {
return this.vmemo;
} 

/**
* ����vmemo��Setter����.����������ע
* ��������:2016-5-12
* @param newVmemo java.lang.String
*/
public void setVmemo ( String vmemo) {
this.vmemo=vmemo;
} 
 
/**
* ���� jishicode��Getter����.����������ʦ����
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getJishicode() {
return this.jishicode;
} 

/**
* ����jishicode��Setter����.����������ʦ����
* ��������:2016-5-12
* @param newJishicode java.lang.String
*/
public void setJishicode ( String jishicode) {
this.jishicode=jishicode;
} 
 
/**
* ���� jishiname��Getter����.����������ʦ����
*  ��������:2016-5-12
* @return java.lang.String
*/
public String getJishiname() {
return this.jishiname;
} 

/**
* ����jishiname��Setter����.����������ʦ����
* ��������:2016-5-12
* @param newJishiname java.lang.String
*/
public void setJishiname ( String jishiname) {
this.jishiname=jishiname;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2016-5-12
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2016-5-12
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
    