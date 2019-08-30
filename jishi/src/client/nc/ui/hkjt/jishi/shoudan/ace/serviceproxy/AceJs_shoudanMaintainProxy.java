package nc.ui.hkjt.jishi.shoudan.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IJs_shoudanMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceJs_shoudanMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IJs_shoudanMaintain query = NCLocator.getInstance().lookup(
				IJs_shoudanMaintain.class);
		return query.query(queryScheme);
	}

}