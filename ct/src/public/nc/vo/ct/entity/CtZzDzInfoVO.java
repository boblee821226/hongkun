package nc.vo.ct.entity;

import java.io.Serializable;

/**
 * ÕÇ×âµÝÔöÐÅÏ¢
 */
public class CtZzDzInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5059746724694484041L;
	
	private Integer dzMonth;
	private Double dzLv;
	private Double dzJe;
	
	public CtZzDzInfoVO () {
	}
	
	public CtZzDzInfoVO (Integer dzMonth, Double dzLv, Double dzJe) {
		this.dzMonth = dzMonth;
		this.dzLv = dzLv;
		this.dzJe = dzJe;
	}
	
	public Integer getDzMonth() {
		return dzMonth;
	}
	
	public void setDzMonth(Integer dzMonth) {
		this.dzMonth = dzMonth;
	}

	public Double getDzLv() {
		return dzLv;
	}

	public void setDzLv(Double dzLv) {
		this.dzLv = dzLv;
	}

	public Double getDzJe() {
		return dzJe;
	}

	public void setDzJe(Double dzJe) {
		this.dzJe = dzJe;
	}
	
}
