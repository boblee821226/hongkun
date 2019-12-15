package nc.vo.hkjt.huiyuan.kaipiaoinfo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class ImpFptzVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1371016835888679382L;
	
	public String ka_code_new;	// 新卡号
	public String ka_code_old;	// 老卡号
	public UFDouble fpje;		// 发票金额
	
	public String getKa_code_new() {
		return ka_code_new;
	}
	public void setKa_code_new(String ka_code_new) {
		this.ka_code_new = ka_code_new;
	}
	public String getKa_code_old() {
		return ka_code_old;
	}
	public void setKa_code_old(String ka_code_old) {
		this.ka_code_old = ka_code_old;
	}
	public UFDouble getFpje() {
		return fpje;
	}
	public void setFpje(UFDouble fpje) {
		this.fpje = fpje;
	}
	
}
