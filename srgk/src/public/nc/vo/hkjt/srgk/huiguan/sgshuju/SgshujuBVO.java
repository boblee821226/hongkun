package nc.vo.hkjt.srgk.huiguan.sgshuju;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SgshujuBVO extends SuperVO {
	/**
	 * ����
	 */
	public static final String BM_PK = "bm_pk";
	/**
	 * Դͷ���ݱ���id
	 */
	public static final String CFIRSTBILLBID = "cfirstbillbid";
	/**
	 * Դͷ����id
	 */
	public static final String CFIRSTBILLID = "cfirstbillid";
	/**
	 * Դͷ��������
	 */
	public static final String CFIRSTTYPECODE = "cfirsttypecode";
	/**
	 * �ϲ㵥�ݱ���id
	 */
	public static final String CSOURCEBILLBID = "csourcebillbid";
	/**
	 * �ϲ㵥��id
	 */
	public static final String CSOURCEBILLID = "csourcebillid";
	/**
	 * �ϲ㵥������
	 */
	public static final String CSOURCETYPECODE = "csourcetypecode";
	/**
	 * ����
	 */
	public static final String NEIRONG = "neirong";
	/**
	 * �ϲ㵥������
	 */
	public static final String PK_HK_SRGK_HG_SGSHUJU = "pk_hk_srgk_hg_sgshuju";
	/**
	 * �ӱ�����
	 */
	public static final String PK_HK_SRGK_HG_SGSHUJU_B = "pk_hk_srgk_hg_sgshuju_b";
	/**
	 * ����
	 */
	public static final String SHIXIANG = "shixiang";
	/**
	 * ʱ���
	 */
	public static final String TS = "ts";
	/**
	 * ������Ŀ����1
	 */
	public static final String TZ_KM_DATA_1 = "tz_km_data_1";
	/**
	 * ������Ŀ����2
	 */
	public static final String TZ_KM_DATA_2 = "tz_km_data_2";
	/**
	 * ������Ŀ��ϸ1
	 */
	public static final String TZ_KM_INFO_1 = "tz_km_info_1";
	/**
	 * ������Ŀ��ϸ2
	 */
	public static final String TZ_KM_INFO_2 = "tz_km_info_2";
	/**
	 * ������Ŀ���˷�ʽ1
	 */
	public static final String TZ_KM_JZFS_1 = "tz_km_jzfs_1";
	/**
	 * ������Ŀ���˷�ʽ2
	 */
	public static final String TZ_KM_JZFS_2 = "tz_km_jzfs_2";
	/**
	 * ������Ŀ������Ŀ1
	 */
	public static final String TZ_KM_SRXM_1 = "tz_km_srxm_1";
	/**
	 * ������Ŀ������Ŀ2
	 */
	public static final String TZ_KM_SRXM_2 = "tz_km_srxm_2";
	/**
	 * �Զ�����01
	 */
	public static final String VBDEF01 = "vbdef01";
	/**
	 * �Զ�����02
	 */
	public static final String VBDEF02 = "vbdef02";
	/**
	 * �Զ�����03
	 */
	public static final String VBDEF03 = "vbdef03";
	/**
	 * �Զ�����04
	 */
	public static final String VBDEF04 = "vbdef04";
	/**
	 * �Զ�����05
	 */
	public static final String VBDEF05 = "vbdef05";
	/**
	 * �Զ�����06
	 */
	public static final String VBDEF06 = "vbdef06";
	/**
	 * �Զ�����07
	 */
	public static final String VBDEF07 = "vbdef07";
	/**
	 * �Զ�����08
	 */
	public static final String VBDEF08 = "vbdef08";
	/**
	 * �Զ�����09
	 */
	public static final String VBDEF09 = "vbdef09";
	/**
	 * �Զ�����10
	 */
	public static final String VBDEF10 = "vbdef10";
	/**
	 * ��ע
	 */
	public static final String VBMEMO = "vbmemo";
	/**
	 * Դͷ���ݺ�
	 */
	public static final String VFIRSTBILLCODE = "vfirstbillcode";
	/**
	 * �к�
	 */
	public static final String VROWNO = "vrowno";
	/**
	 * �ϴε��ݺ�
	 */
	public static final String VSOURCEBILLCODE = "vsourcebillcode";
	/**
	 * �˵�����
	 */
	public static final String ZD_DATE = "zd_date";
	/**
	 * �˵���pk
	 */
	public static final String ZD_ITEM_PK = "zd_item_pk";
	/**
	 * �˵�pk
	 */
	public static final String ZD_PK = "zd_pk";

	public static final String TZ_KM_SRXM_TYPE1 = "tz_km_srxm_type1";
	public static final String TZ_KM_SRXM_TYPE2 = "tz_km_srxm_type2";

	public String getTz_km_srxm_type1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1);
	}

	public void setTz_km_srxm_type1(String tz_km_srxm_type1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1, tz_km_srxm_type1);
	}

	public String getTz_km_srxm_type2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2);
	}

	public void setTz_km_srxm_type2(String tz_km_srxm_type2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2, tz_km_srxm_type2);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getBm_pk() {
		return (String) this.getAttributeValue(SgshujuBVO.BM_PK);
	}

	/**
	 * ���ò���
	 * 
	 * @param bm_pk
	 *            ����
	 */
	public void setBm_pk(String bm_pk) {
		this.setAttributeValue(SgshujuBVO.BM_PK, bm_pk);
	}

	/**
	 * ��ȡԴͷ���ݱ���id
	 * 
	 * @return Դͷ���ݱ���id
	 */
	public String getCfirstbillbid() {
		return (String) this.getAttributeValue(SgshujuBVO.CFIRSTBILLBID);
	}

	/**
	 * ����Դͷ���ݱ���id
	 * 
	 * @param cfirstbillbid
	 *            Դͷ���ݱ���id
	 */
	public void setCfirstbillbid(String cfirstbillbid) {
		this.setAttributeValue(SgshujuBVO.CFIRSTBILLBID, cfirstbillbid);
	}

	/**
	 * ��ȡԴͷ����id
	 * 
	 * @return Դͷ����id
	 */
	public String getCfirstbillid() {
		return (String) this.getAttributeValue(SgshujuBVO.CFIRSTBILLID);
	}

	/**
	 * ����Դͷ����id
	 * 
	 * @param cfirstbillid
	 *            Դͷ����id
	 */
	public void setCfirstbillid(String cfirstbillid) {
		this.setAttributeValue(SgshujuBVO.CFIRSTBILLID, cfirstbillid);
	}

	/**
	 * ��ȡԴͷ��������
	 * 
	 * @return Դͷ��������
	 */
	public String getCfirsttypecode() {
		return (String) this.getAttributeValue(SgshujuBVO.CFIRSTTYPECODE);
	}

	/**
	 * ����Դͷ��������
	 * 
	 * @param cfirsttypecode
	 *            Դͷ��������
	 */
	public void setCfirsttypecode(String cfirsttypecode) {
		this.setAttributeValue(SgshujuBVO.CFIRSTTYPECODE, cfirsttypecode);
	}

	/**
	 * ��ȡ�ϲ㵥�ݱ���id
	 * 
	 * @return �ϲ㵥�ݱ���id
	 */
	public String getCsourcebillbid() {
		return (String) this.getAttributeValue(SgshujuBVO.CSOURCEBILLBID);
	}

	/**
	 * �����ϲ㵥�ݱ���id
	 * 
	 * @param csourcebillbid
	 *            �ϲ㵥�ݱ���id
	 */
	public void setCsourcebillbid(String csourcebillbid) {
		this.setAttributeValue(SgshujuBVO.CSOURCEBILLBID, csourcebillbid);
	}

	/**
	 * ��ȡ�ϲ㵥��id
	 * 
	 * @return �ϲ㵥��id
	 */
	public String getCsourcebillid() {
		return (String) this.getAttributeValue(SgshujuBVO.CSOURCEBILLID);
	}

	/**
	 * �����ϲ㵥��id
	 * 
	 * @param csourcebillid
	 *            �ϲ㵥��id
	 */
	public void setCsourcebillid(String csourcebillid) {
		this.setAttributeValue(SgshujuBVO.CSOURCEBILLID, csourcebillid);
	}

	/**
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getCsourcetypecode() {
		return (String) this.getAttributeValue(SgshujuBVO.CSOURCETYPECODE);
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param csourcetypecode
	 *            �ϲ㵥������
	 */
	public void setCsourcetypecode(String csourcetypecode) {
		this.setAttributeValue(SgshujuBVO.CSOURCETYPECODE, csourcetypecode);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getNeirong() {
		return (String) this.getAttributeValue(SgshujuBVO.NEIRONG);
	}

	/**
	 * ��������
	 * 
	 * @param neirong
	 *            ����
	 */
	public void setNeirong(String neirong) {
		this.setAttributeValue(SgshujuBVO.NEIRONG, neirong);
	}

	/**
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getPk_hk_srgk_hg_sgshuju() {
		return (String) this
				.getAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU);
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param pk_hk_srgk_hg_sgshuju
	 *            �ϲ㵥������
	 */
	public void setPk_hk_srgk_hg_sgshuju(String pk_hk_srgk_hg_sgshuju) {
		this.setAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU,
				pk_hk_srgk_hg_sgshuju);
	}

	/**
	 * ��ȡ�ӱ�����
	 * 
	 * @return �ӱ�����
	 */
	public String getPk_hk_srgk_hg_sgshuju_b() {
		return (String) this
				.getAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU_B);
	}

	/**
	 * �����ӱ�����
	 * 
	 * @param pk_hk_srgk_hg_sgshuju_b
	 *            �ӱ�����
	 */
	public void setPk_hk_srgk_hg_sgshuju_b(String pk_hk_srgk_hg_sgshuju_b) {
		this.setAttributeValue(SgshujuBVO.PK_HK_SRGK_HG_SGSHUJU_B,
				pk_hk_srgk_hg_sgshuju_b);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 * @see String
	 */
	public String getShixiang() {
		return (String) this.getAttributeValue(SgshujuBVO.SHIXIANG);
	}

	/**
	 * ��������
	 * 
	 * @param shixiang
	 *            ����
	 * @see String
	 */
	public void setShixiang(String shixiang) {
		this.setAttributeValue(SgshujuBVO.SHIXIANG, shixiang);
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(SgshujuBVO.TS);
	}

	/**
	 * ����ʱ���
	 * 
	 * @param ts
	 *            ʱ���
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(SgshujuBVO.TS, ts);
	}

	/**
	 * ��ȡ������Ŀ����1
	 * 
	 * @return ������Ŀ����1
	 */
	public UFDouble getTz_km_data_1() {
		return (UFDouble) this.getAttributeValue(SgshujuBVO.TZ_KM_DATA_1);
	}

	/**
	 * ���õ�����Ŀ����1
	 * 
	 * @param tz_km_data_1
	 *            ������Ŀ����1
	 */
	public void setTz_km_data_1(UFDouble tz_km_data_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_DATA_1, tz_km_data_1);
	}

	/**
	 * ��ȡ������Ŀ����2
	 * 
	 * @return ������Ŀ����2
	 */
	public UFDouble getTz_km_data_2() {
		return (UFDouble) this.getAttributeValue(SgshujuBVO.TZ_KM_DATA_2);
	}

	/**
	 * ���õ�����Ŀ����2
	 * 
	 * @param tz_km_data_2
	 *            ������Ŀ����2
	 */
	public void setTz_km_data_2(UFDouble tz_km_data_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_DATA_2, tz_km_data_2);
	}

	/**
	 * ��ȡ������Ŀ��ϸ1
	 * 
	 * @return ������Ŀ��ϸ1
	 */
	public String getTz_km_info_1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_INFO_1);
	}

	/**
	 * ���õ�����Ŀ��ϸ1
	 * 
	 * @param tz_km_info_1
	 *            ������Ŀ��ϸ1
	 */
	public void setTz_km_info_1(String tz_km_info_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_INFO_1, tz_km_info_1);
	}

	/**
	 * ��ȡ������Ŀ��ϸ2
	 * 
	 * @return ������Ŀ��ϸ2
	 */
	public String getTz_km_info_2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_INFO_2);
	}

	/**
	 * ���õ�����Ŀ��ϸ2
	 * 
	 * @param tz_km_info_2
	 *            ������Ŀ��ϸ2
	 */
	public void setTz_km_info_2(String tz_km_info_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_INFO_2, tz_km_info_2);
	}

	/**
	 * ��ȡ������Ŀ���˷�ʽ1
	 * 
	 * @return ������Ŀ���˷�ʽ1
	 */
	public String getTz_km_jzfs_1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_JZFS_1);
	}

	/**
	 * ���õ�����Ŀ���˷�ʽ1
	 * 
	 * @param tz_km_jzfs_1
	 *            ������Ŀ���˷�ʽ1
	 */
	public void setTz_km_jzfs_1(String tz_km_jzfs_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_JZFS_1, tz_km_jzfs_1);
	}

	/**
	 * ��ȡ������Ŀ���˷�ʽ2
	 * 
	 * @return ������Ŀ���˷�ʽ2
	 */
	public String getTz_km_jzfs_2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_JZFS_2);
	}

	/**
	 * ���õ�����Ŀ���˷�ʽ2
	 * 
	 * @param tz_km_jzfs_2
	 *            ������Ŀ���˷�ʽ2
	 */
	public void setTz_km_jzfs_2(String tz_km_jzfs_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_JZFS_2, tz_km_jzfs_2);
	}

	/**
	 * ��ȡ������Ŀ������Ŀ1
	 * 
	 * @return ������Ŀ������Ŀ1
	 */
	public String getTz_km_srxm_1() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_1);
	}

	/**
	 * ���õ�����Ŀ������Ŀ1
	 * 
	 * @param tz_km_srxm_1
	 *            ������Ŀ������Ŀ1
	 */
	public void setTz_km_srxm_1(String tz_km_srxm_1) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_1, tz_km_srxm_1);
	}

	/**
	 * ��ȡ������Ŀ������Ŀ2
	 * 
	 * @return ������Ŀ������Ŀ2
	 */
	public String getTz_km_srxm_2() {
		return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_2);
	}

	/**
	 * ���õ�����Ŀ������Ŀ2
	 * 
	 * @param tz_km_srxm_2
	 *            ������Ŀ������Ŀ2
	 */
	public void setTz_km_srxm_2(String tz_km_srxm_2) {
		this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_2, tz_km_srxm_2);
	}

	/**
	 * ��ȡ�Զ�����01
	 * 
	 * @return �Զ�����01
	 */
	public String getVbdef01() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF01);
	}

	/**
	 * �����Զ�����01
	 * 
	 * @param vbdef01
	 *            �Զ�����01
	 */
	public void setVbdef01(String vbdef01) {
		this.setAttributeValue(SgshujuBVO.VBDEF01, vbdef01);
	}

	/**
	 * ��ȡ�Զ�����02
	 * 
	 * @return �Զ�����02
	 */
	public String getVbdef02() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF02);
	}

	/**
	 * �����Զ�����02
	 * 
	 * @param vbdef02
	 *            �Զ�����02
	 */
	public void setVbdef02(String vbdef02) {
		this.setAttributeValue(SgshujuBVO.VBDEF02, vbdef02);
	}

	/**
	 * ��ȡ�Զ�����03
	 * 
	 * @return �Զ�����03
	 */
	public String getVbdef03() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF03);
	}

	/**
	 * �����Զ�����03
	 * 
	 * @param vbdef03
	 *            �Զ�����03
	 */
	public void setVbdef03(String vbdef03) {
		this.setAttributeValue(SgshujuBVO.VBDEF03, vbdef03);
	}

	/**
	 * ��ȡ�Զ�����04
	 * 
	 * @return �Զ�����04
	 */
	public String getVbdef04() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF04);
	}

	/**
	 * �����Զ�����04
	 * 
	 * @param vbdef04
	 *            �Զ�����04
	 */
	public void setVbdef04(String vbdef04) {
		this.setAttributeValue(SgshujuBVO.VBDEF04, vbdef04);
	}

	/**
	 * ��ȡ�Զ�����05
	 * 
	 * @return �Զ�����05
	 */
	public String getVbdef05() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF05);
	}

	/**
	 * �����Զ�����05
	 * 
	 * @param vbdef05
	 *            �Զ�����05
	 */
	public void setVbdef05(String vbdef05) {
		this.setAttributeValue(SgshujuBVO.VBDEF05, vbdef05);
	}

	/**
	 * ��ȡ�Զ�����06
	 * 
	 * @return �Զ�����06
	 */
	public String getVbdef06() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF06);
	}

	/**
	 * �����Զ�����06
	 * 
	 * @param vbdef06
	 *            �Զ�����06
	 */
	public void setVbdef06(String vbdef06) {
		this.setAttributeValue(SgshujuBVO.VBDEF06, vbdef06);
	}

	/**
	 * ��ȡ�Զ�����07
	 * 
	 * @return �Զ�����07
	 */
	public String getVbdef07() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF07);
	}

	/**
	 * �����Զ�����07
	 * 
	 * @param vbdef07
	 *            �Զ�����07
	 */
	public void setVbdef07(String vbdef07) {
		this.setAttributeValue(SgshujuBVO.VBDEF07, vbdef07);
	}

	/**
	 * ��ȡ�Զ�����08
	 * 
	 * @return �Զ�����08
	 */
	public String getVbdef08() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF08);
	}

	/**
	 * �����Զ�����08
	 * 
	 * @param vbdef08
	 *            �Զ�����08
	 */
	public void setVbdef08(String vbdef08) {
		this.setAttributeValue(SgshujuBVO.VBDEF08, vbdef08);
	}

	/**
	 * ��ȡ�Զ�����09
	 * 
	 * @return �Զ�����09
	 */
	public String getVbdef09() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF09);
	}

	/**
	 * �����Զ�����09
	 * 
	 * @param vbdef09
	 *            �Զ�����09
	 */
	public void setVbdef09(String vbdef09) {
		this.setAttributeValue(SgshujuBVO.VBDEF09, vbdef09);
	}

	/**
	 * ��ȡ�Զ�����10
	 * 
	 * @return �Զ�����10
	 */
	public String getVbdef10() {
		return (String) this.getAttributeValue(SgshujuBVO.VBDEF10);
	}

	/**
	 * �����Զ�����10
	 * 
	 * @param vbdef10
	 *            �Զ�����10
	 */
	public void setVbdef10(String vbdef10) {
		this.setAttributeValue(SgshujuBVO.VBDEF10, vbdef10);
	}

	/**
	 * ��ȡ��ע
	 * 
	 * @return ��ע
	 */
	public String getVbmemo() {
		return (String) this.getAttributeValue(SgshujuBVO.VBMEMO);
	}

	/**
	 * ���ñ�ע
	 * 
	 * @param vbmemo
	 *            ��ע
	 */
	public void setVbmemo(String vbmemo) {
		this.setAttributeValue(SgshujuBVO.VBMEMO, vbmemo);
	}

	/**
	 * ��ȡԴͷ���ݺ�
	 * 
	 * @return Դͷ���ݺ�
	 */
	public String getVfirstbillcode() {
		return (String) this.getAttributeValue(SgshujuBVO.VFIRSTBILLCODE);
	}

	/**
	 * ����Դͷ���ݺ�
	 * 
	 * @param vfirstbillcode
	 *            Դͷ���ݺ�
	 */
	public void setVfirstbillcode(String vfirstbillcode) {
		this.setAttributeValue(SgshujuBVO.VFIRSTBILLCODE, vfirstbillcode);
	}

	/**
	 * ��ȡ�к�
	 * 
	 * @return �к�
	 */
	public String getVrowno() {
		return (String) this.getAttributeValue(SgshujuBVO.VROWNO);
	}

	/**
	 * �����к�
	 * 
	 * @param vrowno
	 *            �к�
	 */
	public void setVrowno(String vrowno) {
		this.setAttributeValue(SgshujuBVO.VROWNO, vrowno);
	}

	/**
	 * ��ȡ�ϴε��ݺ�
	 * 
	 * @return �ϴε��ݺ�
	 */
	public String getVsourcebillcode() {
		return (String) this.getAttributeValue(SgshujuBVO.VSOURCEBILLCODE);
	}

	/**
	 * �����ϴε��ݺ�
	 * 
	 * @param vsourcebillcode
	 *            �ϴε��ݺ�
	 */
	public void setVsourcebillcode(String vsourcebillcode) {
		this.setAttributeValue(SgshujuBVO.VSOURCEBILLCODE, vsourcebillcode);
	}

	/**
	 * ��ȡ�˵�����
	 * 
	 * @return �˵�����
	 */
	public UFDate getZd_date() {
		return (UFDate) this.getAttributeValue(SgshujuBVO.ZD_DATE);
	}

	/**
	 * �����˵�����
	 * 
	 * @param zd_date
	 *            �˵�����
	 */
	public void setZd_date(UFDate zd_date) {
		this.setAttributeValue(SgshujuBVO.ZD_DATE, zd_date);
	}

	/**
	 * ��ȡ�˵���pk
	 * 
	 * @return �˵���pk
	 */
	public String getZd_item_pk() {
		return (String) this.getAttributeValue(SgshujuBVO.ZD_ITEM_PK);
	}

	/**
	 * �����˵���pk
	 * 
	 * @param zd_item_pk
	 *            �˵���pk
	 */
	public void setZd_item_pk(String zd_item_pk) {
		this.setAttributeValue(SgshujuBVO.ZD_ITEM_PK, zd_item_pk);
	}

	/**
	 * ��ȡ�˵�pk
	 * 
	 * @return �˵�pk
	 */
	public String getZd_pk() {
		return (String) this.getAttributeValue(SgshujuBVO.ZD_PK);
	}

	/**
	 * �����˵�pk
	 * 
	 * @param zd_pk
	 *            �˵�pk
	 */
	public void setZd_pk(String zd_pk) {
		this.setAttributeValue(SgshujuBVO.ZD_PK, zd_pk);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_SgshujuBVO");
	}
}