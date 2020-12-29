package nc.vo.hkjt.oa;

public class HkOaSettingBillVO {

	private HkOaSettingVO vo;
	private HkOaSettingHVO[] hVOs;
	private HkOaSettingB1VO[] b1VOs;
	private HkOaSettingB2VO[] b2VOs;
	
	public HkOaSettingVO getVo() {
		return vo;
	}
	public void setVo(HkOaSettingVO vo) {
		this.vo = vo;
	}
	public HkOaSettingHVO[] getHVOs() {
		return hVOs;
	}
	public void setHVOs(HkOaSettingHVO[] hVOs) {
		this.hVOs = hVOs;
	}
	public HkOaSettingB1VO[] getB1VOs() {
		return b1VOs;
	}
	public void setB1VOs(HkOaSettingB1VO[] b1vOs) {
		b1VOs = b1vOs;
	}
	public HkOaSettingB2VO[] getB2VOs() {
		return b2VOs;
	}
	public void setB2VOs(HkOaSettingB2VO[] b2vOs) {
		b2VOs = b2vOs;
	}
 	
}
