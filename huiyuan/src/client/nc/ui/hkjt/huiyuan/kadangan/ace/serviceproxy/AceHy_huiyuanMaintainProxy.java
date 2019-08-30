package nc.ui.hkjt.huiyuan.kadangan.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_huiyuanMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHy_huiyuanMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHy_huiyuanMaintain query = NCLocator.getInstance().lookup(
				IHy_huiyuanMaintain.class);
		return query.query(queryScheme);
	}

}