package nc.vo.hkjt.srgk.jiudian.ruzhangmingxi;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RzmxBVO extends SuperVO {
/**
*accid
*/
public static final String ACCID="accid";
/**
*���Ÿ�ID
*/
public static final String BM_FID="bm_fid";
/**
*����id
*/
public static final String BM_ID="bm_id";
/**
*��������
*/
public static final String BM_NAME="bm_name";
/**
*Դͷ���ݱ���id
*/
public static final String CFIRSTBILLBID="cfirstbillbid";
/**
*Դͷ����id
*/
public static final String CFIRSTBILLID="cfirstbillid";
/**
*Դͷ��������
*/
public static final String CFIRSTTYPECODE="cfirsttypecode";
/**
*���ѽ��
*/
public static final String CHARGE="charge";
/**
*�ϲ㵥�ݱ���id
*/
public static final String CSOURCEBILLBID="csourcebillbid";
/**
*�ϲ㵥��id
*/
public static final String CSOURCEBILLID="csourcebillid";
/**
*�ϲ㵥������
*/
public static final String CSOURCETYPECODE="csourcetypecode";
/**
*descript
*/
public static final String DESCRIPT="descript";
/**
*��Ŀ����
*/
public static final String ITEM_CODE="item_code";
/**
*��Ŀ����
*/
public static final String ITEM_NAME="item_name";
/**
*����������
*/
public String jylb_code;
/**
*�������
*/
public static final String JYLB_NAME="jylb_name";
/**
*���˷�ʽid
*/
public static final String JZFS_ID="jzfs_id";
/**
*���˷�ʽ����
*/
public String jzfs_name;
/**
*�ͻ�����
*/
public static final String KHMZ="khmz";
/**
*���˽��
*/
public static final String PAYMENT="payment";
/**
*�ϲ㵥������
*/
public static final String PK_HK_SRGK_JD_RZMX="pk_hk_srgk_jd_rzmx";
/**
*������ϸ�ӱ�����
*/
public static final String PK_HK_SRGK_JD_RZMX_B="pk_hk_srgk_jd_rzmx_b";
/**
*refno
*/
public static final String REFNO="refno";
/**
*�����
*/
public static final String RMNO="rmno";
/**
*��������
*/
public static final String RMTYPE_NAME="rmtype_name";
/**
*��Ʒ����id
*/
public static final String SPFL_ID="spfl_id";
/**
*��Ʒ��������
*/
public static final String SPFL_NAME="spfl_name";
/**
*������Ŀid
*/
public static final String SRXM_ID="srxm_id";
/**
*����Ա
*/
public static final String SYY_CODE="syy_code";
/**
*����ʱ��
*/
public static final String TRANSDATE="transdate";
/**
*transid
*/
public static final String TRANSID="transid";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01
*/
public static final String VBDEF01="vbdef01";
/**
*�Զ�����02
*/
public static final String VBDEF02="vbdef02";
/**
*�Զ�����03
*/
public static final String VBDEF03="vbdef03";
/**
*�Զ�����04
*/
public static final String VBDEF04="vbdef04";
/**
*�Զ�����05
*/
public static final String VBDEF05="vbdef05";
/**
*�Զ�����06
*/
public static final String VBDEF06="vbdef06";
/**
*�Զ�����07
*/
public static final String VBDEF07="vbdef07";
/**
*�Զ�����08
*/
public static final String VBDEF08="vbdef08";
/**
*�Զ�����09
*/
public static final String VBDEF09="vbdef09";
/**
*�Զ�����10
*/
public static final String VBDEF10="vbdef10";
/**
*��ע
*/
public static final String VBMEMO="vbmemo";
/**
*Դͷ���ݺ�
*/
public static final String VFIRSTBILLCODE="vfirstbillcode";
/**
*�к�
*/
public static final String VROWNO="vrowno";
/**
*�ϴε��ݺ�
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/** 
* ��ȡaccid
*
* @return accid
*/
public String getAccid () {
return (String) this.getAttributeValue( RzmxBVO.ACCID);
 } 

/** 
* ����accid
*
* @param accid accid
*/
public void setAccid ( String accid) {
this.setAttributeValue( RzmxBVO.ACCID,accid);
 } 

/** 
* ��ȡ���Ÿ�ID
*
* @return ���Ÿ�ID
*/
public String getBm_fid () {
return (String) this.getAttributeValue( RzmxBVO.BM_FID);
 } 

/** 
* ���ò��Ÿ�ID
*
* @param bm_fid ���Ÿ�ID
*/
public void setBm_fid ( String bm_fid) {
this.setAttributeValue( RzmxBVO.BM_FID,bm_fid);
 } 

/** 
* ��ȡ����id
*
* @return ����id
*/
public String getBm_id () {
return (String) this.getAttributeValue( RzmxBVO.BM_ID);
 } 

/** 
* ���ò���id
*
* @param bm_id ����id
*/
public void setBm_id ( String bm_id) {
this.setAttributeValue( RzmxBVO.BM_ID,bm_id);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getBm_name () {
return (String) this.getAttributeValue( RzmxBVO.BM_NAME);
 } 

/** 
* ���ò�������
*
* @param bm_name ��������
*/
public void setBm_name ( String bm_name) {
this.setAttributeValue( RzmxBVO.BM_NAME,bm_name);
 } 

/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( RzmxBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( RzmxBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( RzmxBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( RzmxBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( RzmxBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( RzmxBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ���ѽ��
*
* @return ���ѽ��
*/
public UFDouble getCharge () {
return (UFDouble) this.getAttributeValue( RzmxBVO.CHARGE);
 } 

/** 
* �������ѽ��
*
* @param charge ���ѽ��
*/
public void setCharge ( UFDouble charge) {
this.setAttributeValue( RzmxBVO.CHARGE,charge);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( RzmxBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( RzmxBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( RzmxBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( RzmxBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( RzmxBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( RzmxBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡdescript
*
* @return descript
*/
public String getDescript () {
return (String) this.getAttributeValue( RzmxBVO.DESCRIPT);
 } 

/** 
* ����descript
*
* @param descript descript
*/
public void setDescript ( String descript) {
this.setAttributeValue( RzmxBVO.DESCRIPT,descript);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItem_code () {
return (String) this.getAttributeValue( RzmxBVO.ITEM_CODE);
 } 

/** 
* ������Ŀ����
*
* @param item_code ��Ŀ����
*/
public void setItem_code ( String item_code) {
this.setAttributeValue( RzmxBVO.ITEM_CODE,item_code);
 } 

/** 
* ��ȡ��Ŀ����
*
* @return ��Ŀ����
*/
public String getItem_name () {
return (String) this.getAttributeValue( RzmxBVO.ITEM_NAME);
 } 

/** 
* ������Ŀ����
*
* @param item_name ��Ŀ����
*/
public void setItem_name ( String item_name) {
this.setAttributeValue( RzmxBVO.ITEM_NAME,item_name);
 } 

/** 
* ��ȡ����������
*
* @return ����������
*/
public String getJylb_code () {
return this.jylb_code;
 } 

/** 
* ���ý���������
*
* @param jylb_code ����������
*/
public void setJylb_code ( String jylb_code) {
this.jylb_code=jylb_code;
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public String getJylb_name () {
return (String) this.getAttributeValue( RzmxBVO.JYLB_NAME);
 } 

/** 
* ���ý������
*
* @param jylb_name �������
*/
public void setJylb_name ( String jylb_name) {
this.setAttributeValue( RzmxBVO.JYLB_NAME,jylb_name);
 } 

/** 
* ��ȡ���˷�ʽid
*
* @return ���˷�ʽid
*/
public String getJzfs_id () {
return (String) this.getAttributeValue( RzmxBVO.JZFS_ID);
 } 

/** 
* ���ý��˷�ʽid
*
* @param jzfs_id ���˷�ʽid
*/
public void setJzfs_id ( String jzfs_id) {
this.setAttributeValue( RzmxBVO.JZFS_ID,jzfs_id);
 } 

/** 
* ��ȡ���˷�ʽ����
*
* @return ���˷�ʽ����
*/
public String getJzfs_name () {
return this.jzfs_name;
 } 

/** 
* ���ý��˷�ʽ����
*
* @param jzfs_name ���˷�ʽ����
*/
public void setJzfs_name ( String jzfs_name) {
this.jzfs_name=jzfs_name;
 } 

/** 
* ��ȡ�ͻ�����
*
* @return �ͻ�����
*/
public String getKhmz () {
return (String) this.getAttributeValue( RzmxBVO.KHMZ);
 } 

/** 
* ���ÿͻ�����
*
* @param khmz �ͻ�����
*/
public void setKhmz ( String khmz) {
this.setAttributeValue( RzmxBVO.KHMZ,khmz);
 } 

/** 
* ��ȡ���˽��
*
* @return ���˽��
*/
public UFDouble getPayment () {
return (UFDouble) this.getAttributeValue( RzmxBVO.PAYMENT);
 } 

/** 
* ���ý��˽��
*
* @param payment ���˽��
*/
public void setPayment ( UFDouble payment) {
this.setAttributeValue( RzmxBVO.PAYMENT,payment);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_srgk_jd_rzmx () {
return (String) this.getAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_srgk_jd_rzmx �ϲ㵥������
*/
public void setPk_hk_srgk_jd_rzmx ( String pk_hk_srgk_jd_rzmx) {
this.setAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX,pk_hk_srgk_jd_rzmx);
 } 

/** 
* ��ȡ������ϸ�ӱ�����
*
* @return ������ϸ�ӱ�����
*/
public String getPk_hk_srgk_jd_rzmx_b () {
return (String) this.getAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX_B);
 } 

/** 
* ����������ϸ�ӱ�����
*
* @param pk_hk_srgk_jd_rzmx_b ������ϸ�ӱ�����
*/
public void setPk_hk_srgk_jd_rzmx_b ( String pk_hk_srgk_jd_rzmx_b) {
this.setAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX_B,pk_hk_srgk_jd_rzmx_b);
 } 

/** 
* ��ȡrefno
*
* @return refno
*/
public String getRefno () {
return (String) this.getAttributeValue( RzmxBVO.REFNO);
 } 

/** 
* ����refno
*
* @param refno refno
*/
public void setRefno ( String refno) {
this.setAttributeValue( RzmxBVO.REFNO,refno);
 } 

/** 
* ��ȡ�����
*
* @return �����
*/
public String getRmno () {
return (String) this.getAttributeValue( RzmxBVO.RMNO);
 } 

/** 
* ���÷����
*
* @param rmno �����
*/
public void setRmno ( String rmno) {
this.setAttributeValue( RzmxBVO.RMNO,rmno);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getRmtype_name () {
return (String) this.getAttributeValue( RzmxBVO.RMTYPE_NAME);
 } 

/** 
* ���÷�������
*
* @param rmtype_name ��������
*/
public void setRmtype_name ( String rmtype_name) {
this.setAttributeValue( RzmxBVO.RMTYPE_NAME,rmtype_name);
 } 

/** 
* ��ȡ��Ʒ����id
*
* @return ��Ʒ����id
*/
public String getSpfl_id () {
return (String) this.getAttributeValue( RzmxBVO.SPFL_ID);
 } 

/** 
* ������Ʒ����id
*
* @param spfl_id ��Ʒ����id
*/
public void setSpfl_id ( String spfl_id) {
this.setAttributeValue( RzmxBVO.SPFL_ID,spfl_id);
 } 

/** 
* ��ȡ��Ʒ��������
*
* @return ��Ʒ��������
*/
public String getSpfl_name () {
return (String) this.getAttributeValue( RzmxBVO.SPFL_NAME);
 } 

/** 
* ������Ʒ��������
*
* @param spfl_name ��Ʒ��������
*/
public void setSpfl_name ( String spfl_name) {
this.setAttributeValue( RzmxBVO.SPFL_NAME,spfl_name);
 } 

/** 
* ��ȡ������Ŀid
*
* @return ������Ŀid
*/
public String getSrxm_id () {
return (String) this.getAttributeValue( RzmxBVO.SRXM_ID);
 } 

/** 
* ����������Ŀid
*
* @param srxm_id ������Ŀid
*/
public void setSrxm_id ( String srxm_id) {
this.setAttributeValue( RzmxBVO.SRXM_ID,srxm_id);
 } 

/** 
* ��ȡ����Ա
*
* @return ����Ա
*/
public String getSyy_code () {
return (String) this.getAttributeValue( RzmxBVO.SYY_CODE);
 } 

/** 
* ��������Ա
*
* @param syy_code ����Ա
*/
public void setSyy_code ( String syy_code) {
this.setAttributeValue( RzmxBVO.SYY_CODE,syy_code);
 } 

/** 
* ��ȡ����ʱ��
*
* @return ����ʱ��
*/
public UFDateTime getTransdate () {
return (UFDateTime) this.getAttributeValue( RzmxBVO.TRANSDATE);
 } 

/** 
* ���ý���ʱ��
*
* @param transdate ����ʱ��
*/
public void setTransdate ( UFDateTime transdate) {
this.setAttributeValue( RzmxBVO.TRANSDATE,transdate);
 } 

/** 
* ��ȡtransid
*
* @return transid
*/
public String getTransid () {
return (String) this.getAttributeValue( RzmxBVO.TRANSID);
 } 

/** 
* ����transid
*
* @param transid transid
*/
public void setTransid ( String transid) {
this.setAttributeValue( RzmxBVO.TRANSID,transid);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( RzmxBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( RzmxBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( RzmxBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( RzmxBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( RzmxBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( RzmxBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( RzmxBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( RzmxBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( RzmxBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( RzmxBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( RzmxBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( RzmxBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( RzmxBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( RzmxBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( RzmxBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( RzmxBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( RzmxBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( RzmxBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϴε��ݺ�
*
* @return �ϴε��ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( RzmxBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϴε��ݺ�
*
* @param vsourcebillcode �ϴε��ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( RzmxBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.RzmxBVO");
  }
}