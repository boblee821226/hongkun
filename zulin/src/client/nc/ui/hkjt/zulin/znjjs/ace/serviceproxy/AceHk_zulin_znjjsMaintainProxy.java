package nc.ui.hkjt.zulin.znjjs.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHk_zulin_znjjsMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHk_zulin_znjjsMaintain query = NCLocator.getInstance().lookup(
				IHk_zulin_znjjsMaintain.class);
		return query.query(queryScheme);
	}

}