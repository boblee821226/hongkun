package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_kaipiaoqueryMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHy_kaipiaoqueryMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHy_kaipiaoqueryMaintain query = NCLocator.getInstance().lookup(
				IHy_kaipiaoqueryMaintain.class);
		return query.query(queryScheme);
	}

}