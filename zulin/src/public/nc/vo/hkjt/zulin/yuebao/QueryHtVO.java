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

	public UFDate ksrq;			// ��ͬ��ϸ-��ʼ����		��ʼ����
	public UFDate jsrq;			// ��ͬ��ϸ-��������		��������
	public UFDouble danjia;		// ��ͬ��ϸ-����		����
	public UFDouble mianji;		// ��ͬ��ϸ-���		���
	public String pk_customer;	// ��ͬ��ͷ-�ͻ�		�Է�pk
	public String pk_room;		// ��ͬ��ͷ-�����
	public String pk_srxm;		// ��ͬ��ϸ-������Ŀ		֧����Ŀ
	public String pk_quyu;		// ��ͬ��ͷ-����
	
	public UFDate zzrq;			// ��ͬ��ͷ-��ֹ���ڣ��������ڣ�
	
	public UFDate yb_ksrq;		// �±�-��ʼ����		��ʼ����
	public UFDate yb_jsrq;		// �±�-��������		��������
	public Integer yb_days;		// �±�-�Ʒ�����		��������
	public UFDouble yb_mny;		// �±�-������		����
	
	public String vdef01;	// �ͻ�-����			�Է�����
	public String vdef02;	// �����-����			��Ʊ����-����
	public String vdef03;	// ������Ŀ-����		֧����Ŀ-����
	public String vdef04;	// ����-����			��������
	public String vdef05;	// ����pk				����pk
	public String vdef06;
	public String vdef07;	// ʵ�ʺ�ͬ���			��ͬ�ܶ�
	public String vdef08;
	public String vdef09;	
	public String vdef10;	// ��ͬ��				��ͬ��
	
	public UFDouble vdef11;	//					��˰���
	public UFDouble vdef12;	//					��˰���
	public UFDouble vdef13;
	public UFDouble vdef14;
	public UFDouble vdef15;
	
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
	
}
