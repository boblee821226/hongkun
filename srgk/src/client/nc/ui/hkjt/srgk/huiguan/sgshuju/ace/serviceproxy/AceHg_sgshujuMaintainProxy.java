package nc.ui.hkjt.srgk.huiguan.sgshuju.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHg_sgshujuMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHg_sgshujuMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHg_sgshujuMaintain query = NCLocator.getInstance().lookup(
				IHg_sgshujuMaintain.class);
		return query.query(queryScheme);
	}

}