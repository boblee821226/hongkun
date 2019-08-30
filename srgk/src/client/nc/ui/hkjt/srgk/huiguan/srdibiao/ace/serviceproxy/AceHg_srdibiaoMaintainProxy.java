package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHg_srdibiaoMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHg_srdibiaoMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHg_srdibiaoMaintain query = NCLocator.getInstance().lookup(
				IHg_srdibiaoMaintain.class);
		return query.query(queryScheme);
	}

}