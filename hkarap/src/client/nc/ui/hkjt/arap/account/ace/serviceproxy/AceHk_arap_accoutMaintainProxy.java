package nc.ui.hkjt.arap.account.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_arap_accoutMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHk_arap_accoutMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHk_arap_accoutMaintain query = NCLocator.getInstance().lookup(
				IHk_arap_accoutMaintain.class);
		return query.query(queryScheme);
	}

}