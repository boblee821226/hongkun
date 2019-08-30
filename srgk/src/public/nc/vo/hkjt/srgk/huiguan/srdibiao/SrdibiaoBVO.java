package nc.vo.hkjt.srgk.huiguan.srdibiao;

import nc.vo.hkjt.srgk.huiguan.hzshuju.ChildDeptInfoVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SrdibiaoBVO extends SuperVO {
	private ChildDeptInfoVO[] childdeptvos;
	private String pk_fjzfs;
	private String iswanglai;
	private Integer dr;
	
	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getPk_fjzfs() {
		return pk_fjzfs;
	}

	public void setPk_fjzfs(String pk_fjzfs) {
		this.pk_fjzfs = pk_fjzfs;
	}

	public String getIswanglai() {
		return iswanglai;
	}

	public void setIswanglai(String iswanglai) {
		this.iswanglai = iswanglai;
	}

	public ChildDeptInfoVO[] getChilddeptvos() {
		return childdeptvos;
	}

	public void setChilddeptvos(ChildDeptInfoVO[] childdeptvos) {
		this.childdeptvos = childdeptvos;
	}

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
	 * ���
	 */
	public static final String JINE = "jine";
	/**
	 * ���˷�ʽ����
	 */
	public static final String JZFS_CODE = "jzfs_code";
	/**
	 * ���˷�ʽ
	 */
	public static final String JZFS_NAME = "jzfs_name";
	/**
	 * ���˷�ʽpk
	 */
	public static final String JZFS_PK = "jzfs_pk";
	/**
	 * �ϲ㵥������
	 */
	public static final String PK_HK_SRGK_HG_SRDIBIAO = "pk_hk_srgk_hg_srdibiao";
	/**
	 * �ӱ�һ����
	 */
	public static final String PK_HK_SRGK_HG_SRDIBIAO_B = "pk_hk_srgk_hg_srdibiao_b";
	/**
	 * ʵ��
	 */
	public static final String SHISHOU = "shishou";
	/**
	 * ����
	 */
	public static final String SHOURU = "shouru";
	/**
	 * ����-����01
	 */
	public static final String SHOURU_BM01 = "shouru_bm01";
	/**
	 * ����-����02
	 */
	public static final String SHOURU_BM02 = "shouru_bm02";
	/**
	 * ����-����03
	 */
	public static final String SHOURU_BM03 = "shouru_bm03";
	/**
	 * ����-����04
	 */
	public static final String SHOURU_BM04 = "shouru_bm04";
	/**
	 * ����-����05
	 */
	public static final String SHOURU_BM05 = "shouru_bm05";
	/**
	 * ����-����06
	 */
	public static final String SHOURU_BM06 = "shouru_bm06";
	/**
	 * ����-����07
	 */
	public static final String SHOURU_BM07 = "shouru_bm07";
	/**
	 * ����-����08
	 */
	public static final String SHOURU_BM08 = "shouru_bm08";
	/**
	 * ����-����09
	 */
	public static final String SHOURU_BM09 = "shouru_bm09";
	/**
	 * ����-����10
	 */
	public static final String SHOURU_BM10 = "shouru_bm10";
	/**
	 * ������ϸ����
	 */
	public static final String SRMX_CODE = "srmx_code";
	/**
	 * ������ϸ
	 */
	public static final String SRMX_NAME = "srmx_name";
	/**
	 * ������ϸpk
	 */
	public static final String SRMX_PK = "srmx_pk";
	/**
	 * ʱ���
	 */
	public static final String TS = "ts";
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
	 * ���۶Ӧ�գ�
	 */
	public static final String YINGSHOU = "yingshou";
	/**
	 * �������Ż�
	 */
	public static final String YOUHUI_KABILI = "youhui_kabili";
	/**
	 * �ֹ��Ż�
	 */
	public static final String YOUHUI_SHOUGONG = "youhui_shougong";
	/**
	 * �Զ��Ż�
	 */
	public static final String YOUHUI_ZIDONG = "youhui_zidong";
	/**
	 * ռλ��1
	 */
	public static final String ZHANWEI01 = "zhanwei01";
	/**
	 * ռλ��2
	 */
	public static final String ZHANWEI02 = "zhanwei02";

	/**
	 * ����-����11
	 */
	public static final String SHOURU_BM11 = "shouru_bm11";
	/**
	 * ����-����12
	 */
	public static final String SHOURU_BM12 = "shouru_bm12";
	/**
	 * ����-����13
	 */
	public static final String SHOURU_BM13 = "shouru_bm13";
	/**
	 * ����-����14
	 */
	public static final String SHOURU_BM14 = "shouru_bm14";
	/**
	 * ����-����15
	 */
	public static final String SHOURU_BM15 = "shouru_bm15";
	/**
	 * ����-����16
	 */
	public static final String SHOURU_BM16 = "shouru_bm16";
	/**
	 * ����-����17
	 */
	public static final String SHOURU_BM17 = "shouru_bm17";
	/**
	 * ����-����18
	 */
	public static final String SHOURU_BM18 = "shouru_bm18";
	/**
	 * ����-����19
	 */
	public static final String SHOURU_BM19 = "shouru_bm19";
	/**
	 * ����-����20
	 */
	public static final String SHOURU_BM20 = "shouru_bm20";
	/**
	 * ����-����21
	 */
	public static final String SHOURU_BM21 = "shouru_bm21";
	/**
	 * ����-����22
	 */
	public static final String SHOURU_BM22 = "shouru_bm22";
	/**
	 * ����-����23
	 */
	public static final String SHOURU_BM23 = "shouru_bm23";
	/**
	 * ����-����24
	 */
	public static final String SHOURU_BM24 = "shouru_bm24";
	/**
	 * ����-����25
	 */
	public static final String SHOURU_BM25 = "shouru_bm25";
	/**
	 * ����-����26
	 */
	public static final String SHOURU_BM26 = "shouru_bm26";
	/**
	 * ����-����27
	 */
	public static final String SHOURU_BM27 = "shouru_bm27";
	/**
	 * ����-����28
	 */
	public static final String SHOURU_BM28 = "shouru_bm28";
	/**
	 * ����-����29
	 */
	public static final String SHOURU_BM29 = "shouru_bm29";
	/**
	 * ����-����30
	 */
	public static final String SHOURU_BM30 = "shouru_bm30";
	/**
	 * ����-����31
	 */
	public static final String SHOURU_BM31 = "shouru_bm31";
	/**
	 * ����-����32
	 */
	public static final String SHOURU_BM32 = "shouru_bm32";
	/**
	 * ����-����33
	 */
	public static final String SHOURU_BM33 = "shouru_bm33";
	/**
	 * ����-����34
	 */
	public static final String SHOURU_BM34 = "shouru_bm34";
	/**
	 * ����-����35
	 */
	public static final String SHOURU_BM35 = "shouru_bm35";
	/**
	 * ����-����36
	 */
	public static final String SHOURU_BM36 = "shouru_bm36";
	/**
	 * ����-����37
	 */
	public static final String SHOURU_BM37 = "shouru_bm37";
	/**
	 * ����-����38
	 */
	public static final String SHOURU_BM38 = "shouru_bm38";
	/**
	 * ����-����39
	 */
	public static final String SHOURU_BM39 = "shouru_bm39";
	/**
	 * ����-����40
	 */
	public static final String SHOURU_BM40 = "shouru_bm40";
	/**
	 * ����-����41
	 */
	public static final String SHOURU_BM41 = "shouru_bm41";
	/**
	 * ����-����42
	 */
	public static final String SHOURU_BM42 = "shouru_bm42";
	/**
	 * ����-����43
	 */
	public static final String SHOURU_BM43 = "shouru_bm43";
	/**
	 * ����-����44
	 */
	public static final String SHOURU_BM44 = "shouru_bm44";
	/**
	 * ����-����45
	 */
	public static final String SHOURU_BM45 = "shouru_bm45";
	/**
	 * ����-����46
	 */
	public static final String SHOURU_BM46 = "shouru_bm46";
	/**
	 * ����-����47
	 */
	public static final String SHOURU_BM47 = "shouru_bm47";
	/**
	 * ����-����48
	 */
	public static final String SHOURU_BM48 = "shouru_bm48";
	/**
	 * ����-����49
	 */
	public static final String SHOURU_BM49 = "shouru_bm49";
	/**
	 * ����-����50
	 */
	public static final String SHOURU_BM50 = "shouru_bm50";
	
	
	/**
	 * ��ȡ����-����11
	 * 
	 * @return ����-����11
	 */
	public UFDouble getShouru_bm11() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM11);
	}

	/**
	 * ��������-����11
	 * 
	 * @param shouru_bm11
	 *            ����-����11
	 */
	public void setShouru_bm11(UFDouble shouru_bm11) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM11, shouru_bm11);
	}

	/**
	 * ��ȡ����-����12
	 * 
	 * @return ����-����12
	 */
	public UFDouble getShouru_bm12() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM12);
	}

	/**
	 * ��������-����12
	 * 
	 * @param shouru_bm12
	 *            ����-����12
	 */
	public void setShouru_bm12(UFDouble shouru_bm12) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM12, shouru_bm12);
	}

	/**
	 * ��ȡ����-����13
	 * 
	 * @return ����-����13
	 */
	public UFDouble getShouru_bm13() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM13);
	}

	/**
	 * ��������-����13
	 * 
	 * @param shouru_bm13
	 *            ����-����13
	 */
	public void setShouru_bm13(UFDouble shouru_bm13) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM13, shouru_bm13);
	}

	/**
	 * ��ȡ����-����14
	 * 
	 * @return ����-����14
	 */
	public UFDouble getShouru_bm14() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM14);
	}

	/**
	 * ��������-����14
	 * 
	 * @param shouru_bm14
	 *            ����-����14
	 */
	public void setShouru_bm14(UFDouble shouru_bm14) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM14, shouru_bm14);
	}

	/**
	 * ��ȡ����-����15
	 * 
	 * @return ����-����15
	 */
	public UFDouble getShouru_bm15() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM15);
	}

	/**
	 * ��������-����15
	 * 
	 * @param shouru_bm15
	 *            ����-����15
	 */
	public void setShouru_bm15(UFDouble shouru_bm15) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM15, shouru_bm15);
	}

	/**
	 * ��ȡ����-����16
	 * 
	 * @return ����-����16
	 */
	public UFDouble getShouru_bm16() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM16);
	}

	/**
	 * ��������-����16
	 * 
	 * @param shouru_bm16
	 *            ����-����16
	 */
	public void setShouru_bm16(UFDouble shouru_bm16) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM16, shouru_bm16);
	}

	/**
	 * ��ȡ����-����17
	 * 
	 * @return ����-����17
	 */
	public UFDouble getShouru_bm17() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM17);
	}

	/**
	 * ��������-����17
	 * 
	 * @param shouru_bm17
	 *            ����-����17
	 */
	public void setShouru_bm17(UFDouble shouru_bm17) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM17, shouru_bm17);
	}

	/**
	 * ��ȡ����-����18
	 * 
	 * @return ����-����18
	 */
	public UFDouble getShouru_bm18() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM18);
	}

	/**
	 * ��������-����18
	 * 
	 * @param shouru_bm18
	 *            ����-����18
	 */
	public void setShouru_bm18(UFDouble shouru_bm18) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM18, shouru_bm18);
	}

	/**
	 * ��ȡ����-����19
	 * 
	 * @return ����-����19
	 */
	public UFDouble getShouru_bm19() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM19);
	}

	/**
	 * ��������-����19
	 * 
	 * @param shouru_bm19
	 *            ����-����19
	 */
	public void setShouru_bm19(UFDouble shouru_bm19) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM19, shouru_bm19);
	}

	/**
	 * ��ȡ����-����20
	 * 
	 * @return ����-����20
	 */
	public UFDouble getShouru_bm20() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM20);
	}

	/**
	 * ��������-����20
	 * 
	 * @param shouru_bm20
	 *            ����-����20
	 */
	public void setShouru_bm20(UFDouble shouru_bm20) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM20, shouru_bm20);
	}

	/**
	 * ��ȡ����-����21
	 * 
	 * @return ����-����21
	 */
	public UFDouble getShouru_bm21() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM21);
	}

	/**
	 * ��������-����21
	 * 
	 * @param shouru_bm21
	 *            ����-����21
	 */
	public void setShouru_bm21(UFDouble shouru_bm21) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM21, shouru_bm21);
	}

	/**
	 * ��ȡ����-����22
	 * 
	 * @return ����-����22
	 */
	public UFDouble getShouru_bm22() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM22);
	}

	/**
	 * ��������-����22
	 * 
	 * @param shouru_bm22
	 *            ����-����22
	 */
	public void setShouru_bm22(UFDouble shouru_bm22) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM22, shouru_bm22);
	}

	/**
	 * ��ȡ����-����23
	 * 
	 * @return ����-����23
	 */
	public UFDouble getShouru_bm23() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM23);
	}

	/**
	 * ��������-����23
	 * 
	 * @param shouru_bm23
	 *            ����-����23
	 */
	public void setShouru_bm23(UFDouble shouru_bm23) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM23, shouru_bm23);
	}

	/**
	 * ��ȡ����-����24
	 * 
	 * @return ����-����24
	 */
	public UFDouble getShouru_bm24() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM24);
	}

	/**
	 * ��������-����24
	 * 
	 * @param shouru_bm24
	 *            ����-����24
	 */
	public void setShouru_bm24(UFDouble shouru_bm24) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM24, shouru_bm24);
	}

	/**
	 * ��ȡ����-����25
	 * 
	 * @return ����-����25
	 */
	public UFDouble getShouru_bm25() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM25);
	}

	/**
	 * ��������-����25
	 * 
	 * @param shouru_bm25
	 *            ����-����25
	 */
	public void setShouru_bm25(UFDouble shouru_bm25) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM25, shouru_bm25);
	}

	/**
	 * ��ȡ����-����26
	 * 
	 * @return ����-����26
	 */
	public UFDouble getShouru_bm26() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM26);
	}

	/**
	 * ��������-����26
	 * 
	 * @param shouru_bm26
	 *            ����-����26
	 */
	public void setShouru_bm26(UFDouble shouru_bm26) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM26, shouru_bm26);
	}

	/**
	 * ��ȡ����-����27
	 * 
	 * @return ����-����27
	 */
	public UFDouble getShouru_bm27() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM27);
	}

	/**
	 * ��������-����27
	 * 
	 * @param shouru_bm27
	 *            ����-����27
	 */
	public void setShouru_bm27(UFDouble shouru_bm27) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM27, shouru_bm27);
	}

	/**
	 * ��ȡ����-����28
	 * 
	 * @return ����-����28
	 */
	public UFDouble getShouru_bm28() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM28);
	}

	/**
	 * ��������-����28
	 * 
	 * @param shouru_bm28
	 *            ����-����28
	 */
	public void setShouru_bm28(UFDouble shouru_bm28) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM28, shouru_bm28);
	}

	/**
	 * ��ȡ����-����29
	 * 
	 * @return ����-����29
	 */
	public UFDouble getShouru_bm29() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM29);
	}

	/**
	 * ��������-����29
	 * 
	 * @param shouru_bm29
	 *            ����-����29
	 */
	public void setShouru_bm29(UFDouble shouru_bm29) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM29, shouru_bm29);
	}

	/**
	 * ��ȡ����-����30
	 * 
	 * @return ����-����30
	 */
	public UFDouble getShouru_bm30() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM30);
	}

	/**
	 * ��������-����30
	 * 
	 * @param shouru_bm30
	 *            ����-����30
	 */
	public void setShouru_bm30(UFDouble shouru_bm30) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM30, shouru_bm30);
	}

	/**
	 * ��ȡ����-����31
	 * 
	 * @return ����-����31
	 */
	public UFDouble getShouru_bm31() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM31);
	}

	/**
	 * ��������-����31
	 * 
	 * @param shouru_bm31
	 *            ����-����31
	 */
	public void setShouru_bm31(UFDouble shouru_bm31) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM31, shouru_bm31);
	}

	/**
	 * ��ȡ����-����32
	 * 
	 * @return ����-����32
	 */
	public UFDouble getShouru_bm32() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM32);
	}

	/**
	 * ��������-����32
	 * 
	 * @param shouru_bm32
	 *            ����-����32
	 */
	public void setShouru_bm32(UFDouble shouru_bm32) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM32, shouru_bm32);
	}

	/**
	 * ��ȡ����-����33
	 * 
	 * @return ����-����33
	 */
	public UFDouble getShouru_bm33() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM33);
	}

	/**
	 * ��������-����33
	 * 
	 * @param shouru_bm33
	 *            ����-����33
	 */
	public void setShouru_bm33(UFDouble shouru_bm33) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM33, shouru_bm33);
	}

	/**
	 * ��ȡ����-����34
	 * 
	 * @return ����-����34
	 */
	public UFDouble getShouru_bm34() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM34);
	}

	/**
	 * ��������-����34
	 * 
	 * @param shouru_bm34
	 *            ����-����34
	 */
	public void setShouru_bm34(UFDouble shouru_bm34) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM34, shouru_bm34);
	}

	/**
	 * ��ȡ����-����35
	 * 
	 * @return ����-����35
	 */
	public UFDouble getShouru_bm35() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM35);
	}

	/**
	 * ��������-����35
	 * 
	 * @param shouru_bm35
	 *            ����-����35
	 */
	public void setShouru_bm35(UFDouble shouru_bm35) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM35, shouru_bm35);
	}

	/**
	 * ��ȡ����-����36
	 * 
	 * @return ����-����36
	 */
	public UFDouble getShouru_bm36() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM36);
	}

	/**
	 * ��������-����36
	 * 
	 * @param shouru_bm36
	 *            ����-����36
	 */
	public void setShouru_bm36(UFDouble shouru_bm36) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM36, shouru_bm36);
	}

	/**
	 * ��ȡ����-����37
	 * 
	 * @return ����-����37
	 */
	public UFDouble getShouru_bm37() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM37);
	}

	/**
	 * ��������-����37
	 * 
	 * @param shouru_bm37
	 *            ����-����37
	 */
	public void setShouru_bm37(UFDouble shouru_bm37) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM37, shouru_bm37);
	}

	/**
	 * ��ȡ����-����38
	 * 
	 * @return ����-����38
	 */
	public UFDouble getShouru_bm38() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM38);
	}

	/**
	 * ��������-����38
	 * 
	 * @param shouru_bm38
	 *            ����-����38
	 */
	public void setShouru_bm38(UFDouble shouru_bm38) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM38, shouru_bm38);
	}

	/**
	 * ��ȡ����-����39
	 * 
	 * @return ����-����39
	 */
	public UFDouble getShouru_bm39() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM39);
	}

	/**
	 * ��������-����39
	 * 
	 * @param shouru_bm39
	 *            ����-����39
	 */
	public void setShouru_bm39(UFDouble shouru_bm39) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM39, shouru_bm39);
	}

	/**
	 * ��ȡ����-����40
	 * 
	 * @return ����-����40
	 */
	public UFDouble getShouru_bm40() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM40);
	}

	/**
	 * ��������-����40
	 * 
	 * @param shouru_bm40
	 *            ����-����40
	 */
	public void setShouru_bm40(UFDouble shouru_bm40) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM40, shouru_bm40);
	}

	/**
	 * ��ȡ����-����41
	 * 
	 * @return ����-����41
	 */
	public UFDouble getShouru_bm41() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM41);
	}

	/**
	 * ��������-����41
	 * 
	 * @param shouru_bm41
	 *            ����-����41
	 */
	public void setShouru_bm41(UFDouble shouru_bm41) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM41, shouru_bm41);
	}

	/**
	 * ��ȡ����-����42
	 * 
	 * @return ����-����42
	 */
	public UFDouble getShouru_bm42() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM42);
	}

	/**
	 * ��������-����42
	 * 
	 * @param shouru_bm42
	 *            ����-����42
	 */
	public void setShouru_bm42(UFDouble shouru_bm42) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM42, shouru_bm42);
	}

	/**
	 * ��ȡ����-����43
	 * 
	 * @return ����-����43
	 */
	public UFDouble getShouru_bm43() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM43);
	}

	/**
	 * ��������-����43
	 * 
	 * @param shouru_bm43
	 *            ����-����43
	 */
	public void setShouru_bm43(UFDouble shouru_bm43) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM43, shouru_bm43);
	}

	/**
	 * ��ȡ����-����44
	 * 
	 * @return ����-����44
	 */
	public UFDouble getShouru_bm44() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM44);
	}

	/**
	 * ��������-����44
	 * 
	 * @param shouru_bm44
	 *            ����-����44
	 */
	public void setShouru_bm44(UFDouble shouru_bm44) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM44, shouru_bm44);
	}

	/**
	 * ��ȡ����-����45
	 * 
	 * @return ����-����45
	 */
	public UFDouble getShouru_bm45() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM45);
	}

	/**
	 * ��������-����45
	 * 
	 * @param shouru_bm45
	 *            ����-����45
	 */
	public void setShouru_bm45(UFDouble shouru_bm45) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM45, shouru_bm45);
	}

	/**
	 * ��ȡ����-����46
	 * 
	 * @return ����-����46
	 */
	public UFDouble getShouru_bm46() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM46);
	}

	/**
	 * ��������-����46
	 * 
	 * @param shouru_bm46
	 *            ����-����46
	 */
	public void setShouru_bm46(UFDouble shouru_bm46) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM46, shouru_bm46);
	}

	/**
	 * ��ȡ����-����47
	 * 
	 * @return ����-����47
	 */
	public UFDouble getShouru_bm47() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM47);
	}

	/**
	 * ��������-����47
	 * 
	 * @param shouru_bm47
	 *            ����-����47
	 */
	public void setShouru_bm47(UFDouble shouru_bm47) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM47, shouru_bm47);
	}

	/**
	 * ��ȡ����-����48
	 * 
	 * @return ����-����48
	 */
	public UFDouble getShouru_bm48() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM48);
	}

	/**
	 * ��������-����48
	 * 
	 * @param shouru_bm48
	 *            ����-����48
	 */
	public void setShouru_bm48(UFDouble shouru_bm48) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM48, shouru_bm48);
	}

	/**
	 * ��ȡ����-����49
	 * 
	 * @return ����-����49
	 */
	public UFDouble getShouru_bm49() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM49);
	}

	/**
	 * ��������-����49
	 * 
	 * @param shouru_bm49
	 *            ����-����49
	 */
	public void setShouru_bm49(UFDouble shouru_bm49) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM49, shouru_bm49);
	}

	/**
	 * ��ȡ����-����50
	 * 
	 * @return ����-����50
	 */
	public UFDouble getShouru_bm50() {
		return (UFDouble) this.getAttributeValue(YyribaoBVO.SHOURU_BM50);
	}

	/**
	 * ��������-����50
	 * 
	 * @param shouru_bm50
	 *            ����-����50
	 */
	public void setShouru_bm50(UFDouble shouru_bm50) {
		this.setAttributeValue(YyribaoBVO.SHOURU_BM50, shouru_bm50);
	}

	
	/**
	 * ��ȡԴͷ���ݱ���id
	 * 
	 * @return Դͷ���ݱ���id
	 */
	public String getCfirstbillbid() {
		return (String) this.getAttributeValue(SrdibiaoBVO.CFIRSTBILLBID);
	}

	/**
	 * ����Դͷ���ݱ���id
	 * 
	 * @param cfirstbillbid
	 *            Դͷ���ݱ���id
	 */
	public void setCfirstbillbid(String cfirstbillbid) {
		this.setAttributeValue(SrdibiaoBVO.CFIRSTBILLBID, cfirstbillbid);
	}

	/**
	 * ��ȡԴͷ����id
	 * 
	 * @return Դͷ����id
	 */
	public String getCfirstbillid() {
		return (String) this.getAttributeValue(SrdibiaoBVO.CFIRSTBILLID);
	}

	/**
	 * ����Դͷ����id
	 * 
	 * @param cfirstbillid
	 *            Դͷ����id
	 */
	public void setCfirstbillid(String cfirstbillid) {
		this.setAttributeValue(SrdibiaoBVO.CFIRSTBILLID, cfirstbillid);
	}

	/**
	 * ��ȡԴͷ��������
	 * 
	 * @return Դͷ��������
	 */
	public String getCfirsttypecode() {
		return (String) this.getAttributeValue(SrdibiaoBVO.CFIRSTTYPECODE);
	}

	/**
	 * ����Դͷ��������
	 * 
	 * @param cfirsttypecode
	 *            Դͷ��������
	 */
	public void setCfirsttypecode(String cfirsttypecode) {
		this.setAttributeValue(SrdibiaoBVO.CFIRSTTYPECODE, cfirsttypecode);
	}

	/**
	 * ��ȡ�ϲ㵥�ݱ���id
	 * 
	 * @return �ϲ㵥�ݱ���id
	 */
	public String getCsourcebillbid() {
		return (String) this.getAttributeValue(SrdibiaoBVO.CSOURCEBILLBID);
	}

	/**
	 * �����ϲ㵥�ݱ���id
	 * 
	 * @param csourcebillbid
	 *            �ϲ㵥�ݱ���id
	 */
	public void setCsourcebillbid(String csourcebillbid) {
		this.setAttributeValue(SrdibiaoBVO.CSOURCEBILLBID, csourcebillbid);
	}

	/**
	 * ��ȡ�ϲ㵥��id
	 * 
	 * @return �ϲ㵥��id
	 */
	public String getCsourcebillid() {
		return (String) this.getAttributeValue(SrdibiaoBVO.CSOURCEBILLID);
	}

	/**
	 * �����ϲ㵥��id
	 * 
	 * @param csourcebillid
	 *            �ϲ㵥��id
	 */
	public void setCsourcebillid(String csourcebillid) {
		this.setAttributeValue(SrdibiaoBVO.CSOURCEBILLID, csourcebillid);
	}

	/**
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getCsourcetypecode() {
		return (String) this.getAttributeValue(SrdibiaoBVO.CSOURCETYPECODE);
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param csourcetypecode
	 *            �ϲ㵥������
	 */
	public void setCsourcetypecode(String csourcetypecode) {
		this.setAttributeValue(SrdibiaoBVO.CSOURCETYPECODE, csourcetypecode);
	}

	/**
	 * ��ȡ���
	 * 
	 * @return ���
	 */
	public UFDouble getJine() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.JINE);
	}

	/**
	 * ���ý��
	 * 
	 * @param jine
	 *            ���
	 */
	public void setJine(UFDouble jine) {
		this.setAttributeValue(SrdibiaoBVO.JINE, jine);
	}

	/**
	 * ��ȡ���˷�ʽ����
	 * 
	 * @return ���˷�ʽ����
	 */
	public String getJzfs_code() {
		return (String) this.getAttributeValue(SrdibiaoBVO.JZFS_CODE);
	}

	/**
	 * ���ý��˷�ʽ����
	 * 
	 * @param jzfs_code
	 *            ���˷�ʽ����
	 */
	public void setJzfs_code(String jzfs_code) {
		this.setAttributeValue(SrdibiaoBVO.JZFS_CODE, jzfs_code);
	}

	/**
	 * ��ȡ���˷�ʽ
	 * 
	 * @return ���˷�ʽ
	 */
	public String getJzfs_name() {
		return (String) this.getAttributeValue(SrdibiaoBVO.JZFS_NAME);
	}

	/**
	 * ���ý��˷�ʽ
	 * 
	 * @param jzfs_name
	 *            ���˷�ʽ
	 */
	public void setJzfs_name(String jzfs_name) {
		this.setAttributeValue(SrdibiaoBVO.JZFS_NAME, jzfs_name);
	}

	/**
	 * ��ȡ���˷�ʽpk
	 * 
	 * @return ���˷�ʽpk
	 */
	public String getJzfs_pk() {
		return (String) this.getAttributeValue(SrdibiaoBVO.JZFS_PK);
	}

	/**
	 * ���ý��˷�ʽpk
	 * 
	 * @param jzfs_pk
	 *            ���˷�ʽpk
	 */
	public void setJzfs_pk(String jzfs_pk) {
		this.setAttributeValue(SrdibiaoBVO.JZFS_PK, jzfs_pk);
	}

	/**
	 * ��ȡ�ϲ㵥������
	 * 
	 * @return �ϲ㵥������
	 */
	public String getPk_hk_srgk_hg_srdibiao() {
		return (String) this
				.getAttributeValue(SrdibiaoBVO.PK_HK_SRGK_HG_SRDIBIAO);
	}

	/**
	 * �����ϲ㵥������
	 * 
	 * @param pk_hk_srgk_hg_srdibiao
	 *            �ϲ㵥������
	 */
	public void setPk_hk_srgk_hg_srdibiao(String pk_hk_srgk_hg_srdibiao) {
		this.setAttributeValue(SrdibiaoBVO.PK_HK_SRGK_HG_SRDIBIAO,
				pk_hk_srgk_hg_srdibiao);
	}

	/**
	 * ��ȡ�ӱ�һ����
	 * 
	 * @return �ӱ�һ����
	 */
	public String getPk_hk_srgk_hg_srdibiao_b() {
		return (String) this
				.getAttributeValue(SrdibiaoBVO.PK_HK_SRGK_HG_SRDIBIAO_B);
	}

	/**
	 * �����ӱ�һ����
	 * 
	 * @param pk_hk_srgk_hg_srdibiao_b
	 *            �ӱ�һ����
	 */
	public void setPk_hk_srgk_hg_srdibiao_b(String pk_hk_srgk_hg_srdibiao_b) {
		this.setAttributeValue(SrdibiaoBVO.PK_HK_SRGK_HG_SRDIBIAO_B,
				pk_hk_srgk_hg_srdibiao_b);
	}

	/**
	 * ��ȡʵ��
	 * 
	 * @return ʵ��
	 */
	public UFDouble getShishou() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHISHOU);
	}

	/**
	 * ����ʵ��
	 * 
	 * @param shishou
	 *            ʵ��
	 */
	public void setShishou(UFDouble shishou) {
		this.setAttributeValue(SrdibiaoBVO.SHISHOU, shishou);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public UFDouble getShouru() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU);
	}

	/**
	 * ��������
	 * 
	 * @param shouru
	 *            ����
	 */
	public void setShouru(UFDouble shouru) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU, shouru);
	}

	/**
	 * ��ȡ����-����01
	 * 
	 * @return ����-����01
	 */
	public UFDouble getShouru_bm01() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM01);
	}

	/**
	 * ��������-����01
	 * 
	 * @param shouru_bm01
	 *            ����-����01
	 */
	public void setShouru_bm01(UFDouble shouru_bm01) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM01, shouru_bm01);
	}

	/**
	 * ��ȡ����-����02
	 * 
	 * @return ����-����02
	 */
	public UFDouble getShouru_bm02() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM02);
	}

	/**
	 * ��������-����02
	 * 
	 * @param shouru_bm02
	 *            ����-����02
	 */
	public void setShouru_bm02(UFDouble shouru_bm02) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM02, shouru_bm02);
	}

	/**
	 * ��ȡ����-����03
	 * 
	 * @return ����-����03
	 */
	public UFDouble getShouru_bm03() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM03);
	}

	/**
	 * ��������-����03
	 * 
	 * @param shouru_bm03
	 *            ����-����03
	 */
	public void setShouru_bm03(UFDouble shouru_bm03) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM03, shouru_bm03);
	}

	/**
	 * ��ȡ����-����04
	 * 
	 * @return ����-����04
	 */
	public UFDouble getShouru_bm04() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM04);
	}

	/**
	 * ��������-����04
	 * 
	 * @param shouru_bm04
	 *            ����-����04
	 */
	public void setShouru_bm04(UFDouble shouru_bm04) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM04, shouru_bm04);
	}

	/**
	 * ��ȡ����-����05
	 * 
	 * @return ����-����05
	 */
	public UFDouble getShouru_bm05() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM05);
	}

	/**
	 * ��������-����05
	 * 
	 * @param shouru_bm05
	 *            ����-����05
	 */
	public void setShouru_bm05(UFDouble shouru_bm05) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM05, shouru_bm05);
	}

	/**
	 * ��ȡ����-����06
	 * 
	 * @return ����-����06
	 */
	public UFDouble getShouru_bm06() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM06);
	}

	/**
	 * ��������-����06
	 * 
	 * @param shouru_bm06
	 *            ����-����06
	 */
	public void setShouru_bm06(UFDouble shouru_bm06) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM06, shouru_bm06);
	}

	/**
	 * ��ȡ����-����07
	 * 
	 * @return ����-����07
	 */
	public UFDouble getShouru_bm07() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM07);
	}

	/**
	 * ��������-����07
	 * 
	 * @param shouru_bm07
	 *            ����-����07
	 */
	public void setShouru_bm07(UFDouble shouru_bm07) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM07, shouru_bm07);
	}

	/**
	 * ��ȡ����-����08
	 * 
	 * @return ����-����08
	 */
	public UFDouble getShouru_bm08() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM08);
	}

	/**
	 * ��������-����08
	 * 
	 * @param shouru_bm08
	 *            ����-����08
	 */
	public void setShouru_bm08(UFDouble shouru_bm08) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM08, shouru_bm08);
	}

	/**
	 * ��ȡ����-����09
	 * 
	 * @return ����-����09
	 */
	public UFDouble getShouru_bm09() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM09);
	}

	/**
	 * ��������-����09
	 * 
	 * @param shouru_bm09
	 *            ����-����09
	 */
	public void setShouru_bm09(UFDouble shouru_bm09) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM09, shouru_bm09);
	}

	/**
	 * ��ȡ����-����10
	 * 
	 * @return ����-����10
	 */
	public UFDouble getShouru_bm10() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.SHOURU_BM10);
	}

	/**
	 * ��������-����10
	 * 
	 * @param shouru_bm10
	 *            ����-����10
	 */
	public void setShouru_bm10(UFDouble shouru_bm10) {
		this.setAttributeValue(SrdibiaoBVO.SHOURU_BM10, shouru_bm10);
	}

	/**
	 * ��ȡ������ϸ����
	 * 
	 * @return ������ϸ����
	 */
	public String getSrmx_code() {
		return (String) this.getAttributeValue(SrdibiaoBVO.SRMX_CODE);
	}

	/**
	 * ����������ϸ����
	 * 
	 * @param srmx_code
	 *            ������ϸ����
	 */
	public void setSrmx_code(String srmx_code) {
		this.setAttributeValue(SrdibiaoBVO.SRMX_CODE, srmx_code);
	}

	/**
	 * ��ȡ������ϸ
	 * 
	 * @return ������ϸ
	 */
	public String getSrmx_name() {
		return (String) this.getAttributeValue(SrdibiaoBVO.SRMX_NAME);
	}

	/**
	 * ����������ϸ
	 * 
	 * @param srmx_name
	 *            ������ϸ
	 */
	public void setSrmx_name(String srmx_name) {
		this.setAttributeValue(SrdibiaoBVO.SRMX_NAME, srmx_name);
	}

	/**
	 * ��ȡ������ϸpk
	 * 
	 * @return ������ϸpk
	 */
	public String getSrmx_pk() {
		return (String) this.getAttributeValue(SrdibiaoBVO.SRMX_PK);
	}

	/**
	 * ����������ϸpk
	 * 
	 * @param srmx_pk
	 *            ������ϸpk
	 */
	public void setSrmx_pk(String srmx_pk) {
		this.setAttributeValue(SrdibiaoBVO.SRMX_PK, srmx_pk);
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(SrdibiaoBVO.TS);
	}

	/**
	 * ����ʱ���
	 * 
	 * @param ts
	 *            ʱ���
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(SrdibiaoBVO.TS, ts);
	}

	/**
	 * ��ȡ�Զ�����01
	 * 
	 * @return �Զ�����01
	 */
	public String getVbdef01() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF01);
	}

	/**
	 * �����Զ�����01
	 * 
	 * @param vbdef01
	 *            �Զ�����01
	 */
	public void setVbdef01(String vbdef01) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF01, vbdef01);
	}

	/**
	 * ��ȡ�Զ�����02
	 * 
	 * @return �Զ�����02
	 */
	public String getVbdef02() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF02);
	}

	/**
	 * �����Զ�����02
	 * 
	 * @param vbdef02
	 *            �Զ�����02
	 */
	public void setVbdef02(String vbdef02) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF02, vbdef02);
	}

	/**
	 * ��ȡ�Զ�����03
	 * 
	 * @return �Զ�����03
	 */
	public String getVbdef03() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF03);
	}

	/**
	 * �����Զ�����03
	 * 
	 * @param vbdef03
	 *            �Զ�����03
	 */
	public void setVbdef03(String vbdef03) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF03, vbdef03);
	}

	/**
	 * ��ȡ�Զ�����04
	 * 
	 * @return �Զ�����04
	 */
	public String getVbdef04() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF04);
	}

	/**
	 * �����Զ�����04
	 * 
	 * @param vbdef04
	 *            �Զ�����04
	 */
	public void setVbdef04(String vbdef04) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF04, vbdef04);
	}

	/**
	 * ��ȡ�Զ�����05
	 * 
	 * @return �Զ�����05
	 */
	public String getVbdef05() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF05);
	}

	/**
	 * �����Զ�����05
	 * 
	 * @param vbdef05
	 *            �Զ�����05
	 */
	public void setVbdef05(String vbdef05) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF05, vbdef05);
	}

	/**
	 * ��ȡ�Զ�����06
	 * 
	 * @return �Զ�����06
	 */
	public String getVbdef06() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF06);
	}

	/**
	 * �����Զ�����06
	 * 
	 * @param vbdef06
	 *            �Զ�����06
	 */
	public void setVbdef06(String vbdef06) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF06, vbdef06);
	}

	/**
	 * ��ȡ�Զ�����07
	 * 
	 * @return �Զ�����07
	 */
	public String getVbdef07() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF07);
	}

	/**
	 * �����Զ�����07
	 * 
	 * @param vbdef07
	 *            �Զ�����07
	 */
	public void setVbdef07(String vbdef07) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF07, vbdef07);
	}

	/**
	 * ��ȡ�Զ�����08
	 * 
	 * @return �Զ�����08
	 */
	public String getVbdef08() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF08);
	}

	/**
	 * �����Զ�����08
	 * 
	 * @param vbdef08
	 *            �Զ�����08
	 */
	public void setVbdef08(String vbdef08) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF08, vbdef08);
	}

	/**
	 * ��ȡ�Զ�����09
	 * 
	 * @return �Զ�����09
	 */
	public String getVbdef09() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF09);
	}

	/**
	 * �����Զ�����09
	 * 
	 * @param vbdef09
	 *            �Զ�����09
	 */
	public void setVbdef09(String vbdef09) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF09, vbdef09);
	}

	/**
	 * ��ȡ�Զ�����10
	 * 
	 * @return �Զ�����10
	 */
	public String getVbdef10() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBDEF10);
	}

	/**
	 * �����Զ�����10
	 * 
	 * @param vbdef10
	 *            �Զ�����10
	 */
	public void setVbdef10(String vbdef10) {
		this.setAttributeValue(SrdibiaoBVO.VBDEF10, vbdef10);
	}

	/**
	 * ��ȡ��ע
	 * 
	 * @return ��ע
	 */
	public String getVbmemo() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VBMEMO);
	}

	/**
	 * ���ñ�ע
	 * 
	 * @param vbmemo
	 *            ��ע
	 */
	public void setVbmemo(String vbmemo) {
		this.setAttributeValue(SrdibiaoBVO.VBMEMO, vbmemo);
	}

	/**
	 * ��ȡԴͷ���ݺ�
	 * 
	 * @return Դͷ���ݺ�
	 */
	public String getVfirstbillcode() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VFIRSTBILLCODE);
	}

	/**
	 * ����Դͷ���ݺ�
	 * 
	 * @param vfirstbillcode
	 *            Դͷ���ݺ�
	 */
	public void setVfirstbillcode(String vfirstbillcode) {
		this.setAttributeValue(SrdibiaoBVO.VFIRSTBILLCODE, vfirstbillcode);
	}

	/**
	 * ��ȡ�к�
	 * 
	 * @return �к�
	 */
	public String getVrowno() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VROWNO);
	}

	/**
	 * �����к�
	 * 
	 * @param vrowno
	 *            �к�
	 */
	public void setVrowno(String vrowno) {
		this.setAttributeValue(SrdibiaoBVO.VROWNO, vrowno);
	}

	/**
	 * ��ȡ�ϴε��ݺ�
	 * 
	 * @return �ϴε��ݺ�
	 */
	public String getVsourcebillcode() {
		return (String) this.getAttributeValue(SrdibiaoBVO.VSOURCEBILLCODE);
	}

	/**
	 * �����ϴε��ݺ�
	 * 
	 * @param vsourcebillcode
	 *            �ϴε��ݺ�
	 */
	public void setVsourcebillcode(String vsourcebillcode) {
		this.setAttributeValue(SrdibiaoBVO.VSOURCEBILLCODE, vsourcebillcode);
	}

	/**
	 * ��ȡ���۶Ӧ�գ�
	 * 
	 * @return ���۶Ӧ�գ�
	 */
	public UFDouble getYingshou() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.YINGSHOU);
	}

	/**
	 * �������۶Ӧ�գ�
	 * 
	 * @param yingshou
	 *            ���۶Ӧ�գ�
	 */
	public void setYingshou(UFDouble yingshou) {
		this.setAttributeValue(SrdibiaoBVO.YINGSHOU, yingshou);
	}

	/**
	 * ��ȡ�������Ż�
	 * 
	 * @return �������Ż�
	 */
	public UFDouble getYouhui_kabili() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.YOUHUI_KABILI);
	}

	/**
	 * ���ÿ������Ż�
	 * 
	 * @param youhui_kabili
	 *            �������Ż�
	 */
	public void setYouhui_kabili(UFDouble youhui_kabili) {
		this.setAttributeValue(SrdibiaoBVO.YOUHUI_KABILI, youhui_kabili);
	}

	/**
	 * ��ȡ�ֹ��Ż�
	 * 
	 * @return �ֹ��Ż�
	 */
	public UFDouble getYouhui_shougong() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.YOUHUI_SHOUGONG);
	}

	/**
	 * �����ֹ��Ż�
	 * 
	 * @param youhui_shougong
	 *            �ֹ��Ż�
	 */
	public void setYouhui_shougong(UFDouble youhui_shougong) {
		this.setAttributeValue(SrdibiaoBVO.YOUHUI_SHOUGONG, youhui_shougong);
	}

	/**
	 * ��ȡ�Զ��Ż�
	 * 
	 * @return �Զ��Ż�
	 */
	public UFDouble getYouhui_zidong() {
		return (UFDouble) this.getAttributeValue(SrdibiaoBVO.YOUHUI_ZIDONG);
	}

	/**
	 * �����Զ��Ż�
	 * 
	 * @param youhui_zidong
	 *            �Զ��Ż�
	 */
	public void setYouhui_zidong(UFDouble youhui_zidong) {
		this.setAttributeValue(SrdibiaoBVO.YOUHUI_ZIDONG, youhui_zidong);
	}

	/**
	 * ��ȡռλ��1
	 * 
	 * @return ռλ��1
	 */
	public String getZhanwei01() {
		return (String) this.getAttributeValue(SrdibiaoBVO.ZHANWEI01);
	}

	/**
	 * ����ռλ��1
	 * 
	 * @param zhanwei01
	 *            ռλ��1
	 */
	public void setZhanwei01(String zhanwei01) {
		this.setAttributeValue(SrdibiaoBVO.ZHANWEI01, zhanwei01);
	}

	/**
	 * ��ȡռλ��2
	 * 
	 * @return ռλ��2
	 */
	public String getZhanwei02() {
		return (String) this.getAttributeValue(SrdibiaoBVO.ZHANWEI02);
	}

	/**
	 * ����ռλ��2
	 * 
	 * @param zhanwei02
	 *            ռλ��2
	 */
	public void setZhanwei02(String zhanwei02) {
		this.setAttributeValue(SrdibiaoBVO.ZHANWEI02, zhanwei02);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_SrdibiaoBVO");
	}
}