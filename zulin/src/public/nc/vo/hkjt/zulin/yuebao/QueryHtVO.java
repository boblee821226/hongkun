package nc.vo.hkjt.zulin.yuebao;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ��ѯ��ͬVO�������±�
 *
 */
public class QueryHtVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402875174487316565L;

	private UFDate ksrq;			// ��ͬ��ϸ-��ʼ����		��ʼ����
	private UFDate jsrq;			// ��ͬ��ϸ-��������		��������
	private UFDouble danjia;		// ��ͬ��ϸ-����		����
	private UFDouble mianji;		// ��ͬ��ϸ-���		���
	private String pk_customer;	// ��ͬ��ͷ-�ͻ�		�Է�pk
	private String pk_room;		// ��ͬ��ͷ-�����
	private String pk_srxm;		// ��ͬ��ϸ-������Ŀ		֧����Ŀ
	private String pk_quyu;		// ��ͬ��ͷ-����
	
	private UFDate zzrq;			// ��ͬ��ͷ-��ֹ���ڣ��������ڣ�
	
	private UFDate yb_ksrq;		// �±�-��ʼ����		��ʼ����
	private UFDate yb_jsrq;		// �±�-��������		��������
	private Integer yb_days;		// �±�-�Ʒ�����		��������
	private UFDouble yb_mny;		// �±�-������		����
	
	private String vdef01;	// �ͻ�-����			�Է�����
	private String vdef02;	// �����-����			��Ʊ����-����
	private String vdef03;	// ������Ŀ-����		֧����Ŀ-����
	private String vdef04;	// ����-����			��������
	private String vdef05;	// ����pk				����pk
	private String vdef06;
	private String vdef07;	// ʵ�ʺ�ͬ���			��ͬ�ܶ�
	private String vdef08;	//					�����ͬ��ʼ����
	private String vdef09;	//					�����ͬ��������
	private String vdef10;	// ��ͬ��				��ͬ��
	private String vdef21;
	private String vdef22;
	private String vdef23;
	private String vdef24;
	private String vdef25;
	
	private UFDouble vdef11;	//					��˰���
	private UFDouble vdef12;	//					��˰���
	private UFDouble vdef13;	//					���㵥��
	private UFDouble vdef14;	//					˰�ʣ�ӡ��˰�ʣ�
	private UFDouble vdef15;
	
	public String getPk_customer() {
		return pk_customer;
	}
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}
	public UFDate getKsrq() {
		return ksrq;
	}
	public void setKsrq(UFDate ksrq) {
		this.ksrq = ksrq;
	}
	public UFDate getJsrq() {
		return jsrq;
	}
	public void setJsrq(UFDate jsrq) {
		this.jsrq = jsrq;
	}
	public UFDouble getDanjia() {
		return danjia;
	}
	public void setDanjia(UFDouble danjia) {
		this.danjia = danjia;
	}
	public UFDouble getMianji() {
		return mianji;
	}
	public void setMianji(UFDouble mianji) {
		this.mianji = mianji;
	}
	public UFDate getYb_ksrq() {
		return yb_ksrq;
	}
	public void setYb_ksrq(UFDate yb_ksrq) {
		this.yb_ksrq = yb_ksrq;
	}
	public UFDate getYb_jsrq() {
		return yb_jsrq;
	}
	public void setYb_jsrq(UFDate yb_jsrq) {
		this.yb_jsrq = yb_jsrq;
	}
	public Integer getYb_days() {
		return yb_days;
	}
	public void setYb_days(Integer yb_days) {
		this.yb_days = yb_days;
	}
	public UFDouble getYb_mny() {
		return yb_mny;
	}
	public void setYb_mny(UFDouble yb_mny) {
		this.yb_mny = yb_mny;
	}
	public String getVdef01() {
		return vdef01;
	}
	public void setVdef01(String vdef01) {
		this.vdef01 = vdef01;
	}
	public String getVdef02() {
		return vdef02;
	}
	public void setVdef02(String vdef02) {
		this.vdef02 = vdef02;
	}
	public String getVdef03() {
		return vdef03;
	}
	public void setVdef03(String vdef03) {
		this.vdef03 = vdef03;
	}
	public String getVdef04() {
		return vdef04;
	}
	public void setVdef04(String vdef04) {
		this.vdef04 = vdef04;
	}
	public String getVdef05() {
		return vdef05;
	}
	public void setVdef05(String vdef05) {
		this.vdef05 = vdef05;
	}
	public UFDouble getVdef11() {
		return vdef11;
	}
	public void setVdef11(UFDouble vdef11) {
		this.vdef11 = vdef11;
	}
	public UFDouble getVdef12() {
		return vdef12;
	}
	public void setVdef12(UFDouble vdef12) {
		this.vdef12 = vdef12;
	}
	public UFDouble getVdef13() {
		return vdef13;
	}
	public void setVdef13(UFDouble vdef13) {
		this.vdef13 = vdef13;
	}
	public UFDouble getVdef14() {
		return vdef14;
	}
	public void setVdef14(UFDouble vdef14) {
		this.vdef14 = vdef14;
	}
	public UFDouble getVdef15() {
		return vdef15;
	}
	public void setVdef15(UFDouble vdef15) {
		this.vdef15 = vdef15;
	}
	public String getVdef06() {
		return vdef06;
	}
	public void setVdef06(String vdef06) {
		this.vdef06 = vdef06;
	}
	public String getVdef07() {
		return vdef07;
	}
	public void setVdef07(String vdef07) {
		this.vdef07 = vdef07;
	}
	public String getVdef08() {
		return vdef08;
	}
	public void setVdef08(String vdef08) {
		this.vdef08 = vdef08;
	}
	public String getVdef09() {
		return vdef09;
	}
	public void setVdef09(String vdef09) {
		this.vdef09 = vdef09;
	}
	public String getVdef10() {
		return vdef10;
	}
	public void setVdef10(String vdef10) {
		this.vdef10 = vdef10;
	}
	public String getPk_room() {
		return pk_room;
	}
	public void setPk_room(String pk_room) {
		this.pk_room = pk_room;
	}
	public String getPk_srxm() {
		return pk_srxm;
	}
	public void setPk_srxm(String pk_srxm) {
		this.pk_srxm = pk_srxm;
	}
	public String getPk_quyu() {
		return pk_quyu;
	}
	public void setPk_quyu(String pk_quyu) {
		this.pk_quyu = pk_quyu;
	}
	public UFDate getZzrq() {
		return zzrq;
	}
	public void setZzrq(UFDate zzrq) {
		this.zzrq = zzrq;
	}
	public String getVdef21() {
		return vdef21;
	}
	public void setVdef21(String vdef21) {
		this.vdef21 = vdef21;
	}
	public String getVdef22() {
		return vdef22;
	}
	public void setVdef22(String vdef22) {
		this.vdef22 = vdef22;
	}
	public String getVdef23() {
		return vdef23;
	}
	public void setVdef23(String vdef23) {
		this.vdef23 = vdef23;
	}
	public String getVdef24() {
		return vdef24;
	}
	public void setVdef24(String vdef24) {
		this.vdef24 = vdef24;
	}
	public String getVdef25() {
		return vdef25;
	}
	public void setVdef25(String vdef25) {
		this.vdef25 = vdef25;
	}
	
}
