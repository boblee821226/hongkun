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
	
	private Integer dzYear;
	private Double dzLv;
	private Double dzJe;
	
	public CtZzDzInfoVO () {
	}
	
	public CtZzDzInfoVO (Integer dzYear, Double dzLv, Double dzJe) {
		this.dzYear = dzYear;
		this.dzLv = dzLv;
		this.dzJe = dzJe;
	}
	
	public Integer getDzYear() {
		return dzYear;
	}
	
	public void setDzYear(Integer dzYear) {
		this.dzYear = dzYear;
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
