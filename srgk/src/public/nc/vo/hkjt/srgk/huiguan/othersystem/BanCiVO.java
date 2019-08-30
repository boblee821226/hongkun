package nc.vo.hkjt.srgk.huiguan.othersystem;


/**
 * @author zhangjc
 *外系统班次VO
 */
public class BanCiVO extends BanCi_TempVO {
	private static final long serialVersionUID = -2056598380640421460L;
	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "hk_srgk_hg_banci";
	}
}
