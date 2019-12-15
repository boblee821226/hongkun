package nc.ui.hkjt.fapiao_sk.bill.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_fp_sk_billMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceHk_fp_sk_billMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHk_fp_sk_billMaintain query = NCLocator.getInstance().lookup(
				IHk_fp_sk_billMaintain.class);
		return query.query(queryScheme);
	}

}