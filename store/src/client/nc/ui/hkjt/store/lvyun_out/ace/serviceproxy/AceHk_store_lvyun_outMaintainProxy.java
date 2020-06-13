package nc.ui.hkjt.store.lvyun_out.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_store_lvyun_outMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHk_store_lvyun_outMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHk_store_lvyun_outMaintain query = NCLocator.getInstance().lookup(
				IHk_store_lvyun_outMaintain.class);
		return query.query(queryScheme);
	}

}