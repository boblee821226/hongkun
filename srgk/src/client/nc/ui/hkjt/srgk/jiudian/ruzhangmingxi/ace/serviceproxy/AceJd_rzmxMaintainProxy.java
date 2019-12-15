package nc.ui.hkjt.srgk.jiudian.ruzhangmingxi.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceJd_rzmxMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IJd_rzmxMaintain query = NCLocator.getInstance().lookup(
				IJd_rzmxMaintain.class);
		return query.query(queryScheme);
	}

}