package nc.ui.hkjt.srgk.huiguan.zhangdan.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHg_zhangdanMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHg_zhangdanMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHg_zhangdanMaintain query = NCLocator.getInstance().lookup(
				IHg_zhangdanMaintain.class);
		return query.query(queryScheme);
	}

}