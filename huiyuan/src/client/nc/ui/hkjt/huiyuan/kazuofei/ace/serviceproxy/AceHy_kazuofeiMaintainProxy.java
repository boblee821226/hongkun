package nc.ui.hkjt.huiyuan.kazuofei.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHy_kazuofeiMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ʾ�����ݵĲ�������
 * 
 * @author author
 * @version tempProject version
 */
public class AceHy_kazuofeiMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IHy_kazuofeiMaintain query = NCLocator.getInstance().lookup(
				IHy_kazuofeiMaintain.class);
		return query.query(queryScheme);
	}

}