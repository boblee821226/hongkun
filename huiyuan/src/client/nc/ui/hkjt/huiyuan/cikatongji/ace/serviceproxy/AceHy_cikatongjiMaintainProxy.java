package nc.ui.hkjt.huiyuan.cikatongji.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_cikatongjiMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHy_cikatongjiMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHy_cikatongjiMaintain query = NCLocator.getInstance().lookup(
				IHy_cikatongjiMaintain.class);
		return query.query(queryScheme);
	}

}