package nc.ui.hkjt.zulin.sjdy.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_zulin_sjdyMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHk_zulin_sjdyMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHk_zulin_sjdyMaintain query = NCLocator.getInstance().lookup(
				IHk_zulin_sjdyMaintain.class);
		return query.query(queryScheme);
	}

}