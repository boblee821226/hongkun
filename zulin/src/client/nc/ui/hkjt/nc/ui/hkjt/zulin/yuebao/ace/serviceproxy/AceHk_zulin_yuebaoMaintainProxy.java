package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_zulin_yuebaoMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHk_zulin_yuebaoMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHk_zulin_yuebaoMaintain query = NCLocator.getInstance().lookup(
				IHk_zulin_yuebaoMaintain.class);
		return query.query(queryScheme);
	}

}