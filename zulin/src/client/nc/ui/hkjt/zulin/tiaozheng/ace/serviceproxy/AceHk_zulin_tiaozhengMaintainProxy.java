package nc.ui.hkjt.zulin.tiaozheng.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_zulin_tiaozhengMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHk_zulin_tiaozhengMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHk_zulin_tiaozhengMaintain query = NCLocator.getInstance().lookup(
				IHk_zulin_tiaozhengMaintain.class);
		return query.query(queryScheme);
	}

}