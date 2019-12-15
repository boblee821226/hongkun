package nc.bs.hkjt.hg_rsbaogao_tz.ace.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoCVO;

public class AceHg_rsbaogao_tzBP {

	public RsbaogaoCVO[] queryByQueryScheme(IQueryScheme querySheme) {
		QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
		p.appendFuncPermissionOrgSql();
		return new SchemeVOQuery<RsbaogaoCVO>(RsbaogaoCVO.class).query(querySheme,
				null);
	}
}
