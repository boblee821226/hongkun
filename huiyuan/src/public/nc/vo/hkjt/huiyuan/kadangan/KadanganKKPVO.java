package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2016-9-11
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class KadanganKKPVO extends SuperVO {
	
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
	*�ϲ㵥������
	*/
	public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
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
	 *�ϲ㵥�ݺ�
	 */
	public static final String VSOURCEBILLCODE="vsourcebillcode";
	/**
	*��Ա�ɿ�Ʊ����
	*/
	public static final String PK_HK_HUIYUAN_KADANGAN_KKP="pk_hk_huiyuan_kadangan_kkp";
	/**
	*�к�
	*/
	public static final String VROWNO="vrowno";
	
	/**
	*�˵���
	*/
	public static final String ZDH="zdh";
	
	/**
	*��ֵʱ��
	*/
	public static final String CZ_TIME="cz_time";
	/**
	 *��Ʊ��ֹʱ��
	 */
	public static final String KPJZ_TIME="kpjz_time";
    
	/**
	 * �ɿ�Ʊ���
	 */
	public static final String KKP_JE="kkp_je";
	
	/**
	 * �ѿ�Ʊ���
	 */
	public static final String YKP_JE="ykp_je";
	
	/** 
	* ��ȡԴͷ���ݱ���id
	*
	* @return Դͷ���ݱ���id
	*/
	public String getCfirstbillbid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CFIRSTBILLBID);
	 } 

	/** 
	* ����Դͷ���ݱ���id
	*
	* @param cfirstbillbid Դͷ���ݱ���id
	*/
	public void setCfirstbillbid ( String cfirstbillbid) {
	this.setAttributeValue( KadanganKKPVO.CFIRSTBILLBID,cfirstbillbid);
	 } 

	/** 
	* ��ȡԴͷ����id
	*
	* @return Դͷ����id
	*/
	public String getCfirstbillid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CFIRSTBILLID);
	 } 

	/** 
	* ����Դͷ����id
	*
	* @param cfirstbillid Դͷ����id
	*/
	public void setCfirstbillid ( String cfirstbillid) {
	this.setAttributeValue( KadanganKKPVO.CFIRSTBILLID,cfirstbillid);
	 } 

	/** 
	* ��ȡԴͷ��������
	*
	* @return Դͷ��������
	*/
	public String getCfirsttypecode () {
	return (String) this.getAttributeValue( KadanganKKPVO.CFIRSTTYPECODE);
	 } 

	/** 
	* ����Դͷ��������
	*
	* @param cfirsttypecode Դͷ��������
	*/
	public void setCfirsttypecode ( String cfirsttypecode) {
	this.setAttributeValue( KadanganKKPVO.CFIRSTTYPECODE,cfirsttypecode);
	 } 

	/** 
	* ��ȡ�ϲ㵥�ݱ���id
	*
	* @return �ϲ㵥�ݱ���id
	*/
	public String getCsourcebillbid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CSOURCEBILLBID);
	 } 

	/** 
	* �����ϲ㵥�ݱ���id
	*
	* @param csourcebillbid �ϲ㵥�ݱ���id
	*/
	public void setCsourcebillbid ( String csourcebillbid) {
	this.setAttributeValue( KadanganKKPVO.CSOURCEBILLBID,csourcebillbid);
	 } 

	/** 
	* ��ȡ�ϲ㵥��id
	*
	* @return �ϲ㵥��id
	*/
	public String getCsourcebillid () {
	return (String) this.getAttributeValue( KadanganKKPVO.CSOURCEBILLID);
	 } 

	/** 
	* �����ϲ㵥��id
	*
	* @param csourcebillid �ϲ㵥��id
	*/
	public void setCsourcebillid ( String csourcebillid) {
	this.setAttributeValue( KadanganKKPVO.CSOURCEBILLID,csourcebillid);
	 } 

	/** 
	* ��ȡ�ϲ㵥������
	*
	* @return �ϲ㵥������
	*/
	public String getCsourcetypecode () {
	return (String) this.getAttributeValue( KadanganKKPVO.CSOURCETYPECODE);
	 } 

	/** 
	* �����ϲ㵥������
	*
	* @param csourcetypecode �ϲ㵥������
	*/
	public void setCsourcetypecode ( String csourcetypecode) {
	this.setAttributeValue( KadanganKKPVO.CSOURCETYPECODE,csourcetypecode);
	 } 

	/** 
	* ��ȡ�ɿ�Ʊ���
	*
	* @return �ɿ�Ʊ���
	*/
	public UFDouble getKkp_je () {
	return (UFDouble) this.getAttributeValue( KadanganKKPVO.KKP_JE);
	 } 

	/** 
	* ���ÿɿ�Ʊ���
	*
	* @param kkp_je �ɿ�Ʊ���
	*/
	public void setKkp_je ( UFDouble kkp_je) {
	this.setAttributeValue( KadanganKKPVO.KKP_JE,kkp_je);
	 } 
	
	/** 
	* ��ȡ�ѿ�Ʊ���
	*
	* @return �ѿ�Ʊ���
	*/
	public UFDouble getYkp_je () {
	return (UFDouble) this.getAttributeValue( KadanganKKPVO.YKP_JE);
	 } 

	/** 
	* �����ѿ�Ʊ���
	*
	* @param ykp_je �ѿ�Ʊ���
	*/
	public void setYkp_je ( UFDouble ykp_je) {
	this.setAttributeValue( KadanganKKPVO.YKP_JE,ykp_je);
	 } 

	/** 
	* ��ȡ��ֵʱ��
	*
	* @return ��ֵʱ��
	*/
	public UFDateTime getCz_time () {
	return (UFDateTime) this.getAttributeValue( KadanganKKPVO.CZ_TIME);
	 } 

	/** 
	* ���ó�ֵʱ��
	*
	* @param cz_time ��ֵʱ��
	*/
	public void setCz_time ( UFDateTime cz_time) {
	this.setAttributeValue( KadanganKKPVO.CZ_TIME,cz_time);
	 } 
	
	/** 
	* ��ȡ��Ʊ��ֹʱ��
	*
	* @return ��Ʊ��ֹʱ��
	*/
	public UFDateTime getKpjz_time () {
	return (UFDateTime) this.getAttributeValue( KadanganKKPVO.KPJZ_TIME);
	 } 

	/** 
	* ���ÿ�Ʊ��ֹʱ��
	*
	* @param kpjz_time ��Ʊ��ֹʱ��
	*/
	public void setKpjz_time ( UFDateTime kpjz_time) {
	this.setAttributeValue( KadanganKKPVO.KPJZ_TIME,kpjz_time);
	 } 

	/** 
	* ��ȡ�ϲ㵥������
	*
	* @return �ϲ㵥������
	*/
	public String getPk_hk_huiyuan_kadangan () {
	return (String) this.getAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN);
	 } 

	/** 
	* �����ϲ㵥������
	*
	* @param pk_hk_huiyuan_kadangan �ϲ㵥������
	*/
	public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
	this.setAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
	 } 

	/** 
	* ��ȡ��Ա��ֵ����
	*
	* @return ��Ա��ֵ����
	*/
	public String getPk_hk_huiyuan_kadangan_kkp () {
	return (String) this.getAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN_KKP);
	 } 

	/** 
	* ���û�Ա��ֵ����
	*
	* @param pk_hk_huiyuan_kadangan_cz ��Ա��ֵ����
	*/
	public void setPk_hk_huiyuan_kadangan_kkp ( String pk_hk_huiyuan_kadangan_kkp) {
	this.setAttributeValue( KadanganKKPVO.PK_HK_HUIYUAN_KADANGAN_KKP,pk_hk_huiyuan_kadangan_kkp);
	 } 

	/** 
	* ��ȡʱ���
	*
	* @return ʱ���
	*/
	public UFDateTime getTs () {
	return (UFDateTime) this.getAttributeValue( KadanganKKPVO.TS);
	 } 

	/** 
	* ����ʱ���
	*
	* @param ts ʱ���
	*/
	public void setTs ( UFDateTime ts) {
	this.setAttributeValue( KadanganKKPVO.TS,ts);
	 } 

	/** 
	* ��ȡ�Զ�����01
	*
	* @return �Զ�����01
	*/
	public String getVbdef01 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF01);
	 } 

	/** 
	* �����Զ�����01
	*
	* @param vbdef01 �Զ�����01
	*/
	public void setVbdef01 ( String vbdef01) {
	this.setAttributeValue( KadanganKKPVO.VBDEF01,vbdef01);
	 } 

	/** 
	* ��ȡ�Զ�����02
	*
	* @return �Զ�����02
	*/
	public String getVbdef02 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF02);
	 } 

	/** 
	* �����Զ�����02
	*
	* @param vbdef02 �Զ�����02
	*/
	public void setVbdef02 ( String vbdef02) {
	this.setAttributeValue( KadanganKKPVO.VBDEF02,vbdef02);
	 } 

	/** 
	* ��ȡ�Զ�����03
	*
	* @return �Զ�����03
	*/
	public String getVbdef03 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF03);
	 } 

	/** 
	* �����Զ�����03
	*
	* @param vbdef03 �Զ�����03
	*/
	public void setVbdef03 ( String vbdef03) {
	this.setAttributeValue( KadanganKKPVO.VBDEF03,vbdef03);
	 } 

	/** 
	* ��ȡ�Զ�����04
	*
	* @return �Զ�����04
	*/
	public String getVbdef04 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF04);
	 } 

	/** 
	* �����Զ�����04
	*
	* @param vbdef04 �Զ�����04
	*/
	public void setVbdef04 ( String vbdef04) {
	this.setAttributeValue( KadanganKKPVO.VBDEF04,vbdef04);
	 } 

	/** 
	* ��ȡ�Զ�����05
	*
	* @return �Զ�����05
	*/
	public String getVbdef05 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF05);
	 } 

	/** 
	* �����Զ�����05
	*
	* @param vbdef05 �Զ�����05
	*/
	public void setVbdef05 ( String vbdef05) {
	this.setAttributeValue( KadanganKKPVO.VBDEF05,vbdef05);
	 } 

	/** 
	* ��ȡ�Զ�����06
	*
	* @return �Զ�����06
	*/
	public String getVbdef06 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF06);
	 } 

	/** 
	* �����Զ�����06
	*
	* @param vbdef06 �Զ�����06
	*/
	public void setVbdef06 ( String vbdef06) {
	this.setAttributeValue( KadanganKKPVO.VBDEF06,vbdef06);
	 } 

	/** 
	* ��ȡ�Զ�����07
	*
	* @return �Զ�����07
	*/
	public String getVbdef07 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF07);
	 } 

	/** 
	* �����Զ�����07
	*
	* @param vbdef07 �Զ�����07
	*/
	public void setVbdef07 ( String vbdef07) {
	this.setAttributeValue( KadanganKKPVO.VBDEF07,vbdef07);
	 } 

	/** 
	* ��ȡ�Զ�����08
	*
	* @return �Զ�����08
	*/
	public String getVbdef08 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF08);
	 } 

	/** 
	* �����Զ�����08
	*
	* @param vbdef08 �Զ�����08
	*/
	public void setVbdef08 ( String vbdef08) {
	this.setAttributeValue( KadanganKKPVO.VBDEF08,vbdef08);
	 } 

	/** 
	* ��ȡ�Զ�����09
	*
	* @return �Զ�����09
	*/
	public String getVbdef09 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF09);
	 } 

	/** 
	* �����Զ�����09
	*
	* @param vbdef09 �Զ�����09
	*/
	public void setVbdef09 ( String vbdef09) {
	this.setAttributeValue( KadanganKKPVO.VBDEF09,vbdef09);
	 } 

	/** 
	* ��ȡ�Զ�����10
	*
	* @return �Զ�����10
	*/
	public String getVbdef10 () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBDEF10);
	 } 

	/** 
	* �����Զ�����10
	*
	* @param vbdef10 �Զ�����10
	*/
	public void setVbdef10 ( String vbdef10) {
	this.setAttributeValue( KadanganKKPVO.VBDEF10,vbdef10);
	 } 

	/** 
	* ��ȡ��ע
	*
	* @return ��ע
	*/
	public String getVbmemo () {
	return (String) this.getAttributeValue( KadanganKKPVO.VBMEMO);
	 } 

	/** 
	* ���ñ�ע
	*
	* @param vbmemo ��ע
	*/
	public void setVbmemo ( String vbmemo) {
	this.setAttributeValue( KadanganKKPVO.VBMEMO,vbmemo);
	 } 

	/** 
	* ��ȡԴͷ���ݺ�
	*
	* @return Դͷ���ݺ�
	*/
	public String getVfirstbillcode () {
	return (String) this.getAttributeValue( KadanganKKPVO.VFIRSTBILLCODE);
	 } 

	/** 
	* ����Դͷ���ݺ�
	*
	* @param vfirstbillcode Դͷ���ݺ�
	*/
	public void setVfirstbillcode ( String vfirstbillcode) {
	this.setAttributeValue( KadanganKKPVO.VFIRSTBILLCODE,vfirstbillcode);
	 } 

	/** 
	* ��ȡ�к�
	*
	* @return �к�
	*/
	public String getVrowno () {
	return (String) this.getAttributeValue( KadanganKKPVO.VROWNO);
	 } 

	/** 
	* �����к�
	*
	* @param vrowno �к�
	*/
	public void setVrowno ( String vrowno) {
	this.setAttributeValue( KadanganKKPVO.VROWNO,vrowno);
	 } 

	/** 
	* ��ȡ�ϲ㵥�ݺ�
	*
	* @return �ϲ㵥�ݺ�
	*/
	public String getVsourcebillcode () {
	return (String) this.getAttributeValue( KadanganKKPVO.VSOURCEBILLCODE);
	 } 

	/** 
	* �����ϲ㵥�ݺ�
	*
	* @param vsourcebillcode �ϲ㵥�ݺ�
	*/
	public void setVsourcebillcode ( String vsourcebillcode) {
	this.setAttributeValue( KadanganKKPVO.VSOURCEBILLCODE,vsourcebillcode);
	 } 

	/** 
	* ��ȡ�˵���
	*
	* @return �˵���
	*/
	public String getZdh () {
	return (String) this.getAttributeValue( KadanganKKPVO.ZDH);
	 } 

	/** 
	* �����˵���
	*
	* @param zdh �˵���
	*/
	public void setZdh ( String zdh) {
	this.setAttributeValue( KadanganKKPVO.ZDH,zdh);
	 } 

     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KadanganKKPVO");
    }
    
    public static final String DR="dr";
    public void setDr ( Integer dr) {
  	  this.setAttributeValue( KadanganKKPVO.DR,dr);
    }
    public Integer getDr () {
  	  return (Integer) this.getAttributeValue( KadanganKKPVO.DR);
  	   } 
    
   }
