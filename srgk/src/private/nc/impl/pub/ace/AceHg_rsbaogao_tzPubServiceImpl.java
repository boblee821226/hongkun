package nc.impl.pub.ace;
import nc.bs.hkjt.hg_rsbaogao_tz.ace.bp.AceHg_rsbaogao_tzBP;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.ISuperVO;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;
import nc.vo.uif2.LoginContext;

public abstract class AceHg_rsbaogao_tzPubServiceImpl extends SmartServiceImpl {
	public RsbaogaoCVO[] pubquerybasedoc(IQueryScheme querySheme)
			throws nc.vo.pub.BusinessException {
		return new AceHg_rsbaogao_tzBP().queryByQueryScheme(querySheme);
	}
}