package nc.ui.hkjt.srgk.huiguan.rsbaogao.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHg_rsbaogaoMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHg_rsbaogaoMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHg_rsbaogaoMaintain query = NCLocator.getInstance().lookup(
				IHg_rsbaogaoMaintain.class);
		return query.query(queryScheme);
	}

}