package nc.ui.hkjt.hg_jzfs.ace.maintain;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;

/**
 * ʾ�����ݵĲ�������
 * 
 * @since 6.0
 * @version 2011-7-6 ����08:31:09
 * @author duy
 */
public class AceHg_jzfsService
             implements IAppModelService,IQueryService 
                     {
    @Override
    public Object insert(Object object) throws Exception {
        nc.itf.hkjt.IHg_jzfsMaintain operator = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_jzfsMaintain.class);
        return operator.insert((JzfsHVO) object);
    }
    @Override
    public Object update(Object object) throws Exception {
        nc.itf.hkjt.IHg_jzfsMaintain operator = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_jzfsMaintain.class);
        //ǰ̨�Ƚ��޸��˸�ֵ
        String pk_user = AppContext.getInstance().getPkUser();
        ((JzfsHVO) object).setModifier(pk_user);
        return operator.update((JzfsHVO) object);
    }
    @Override
    public void delete(Object object) throws Exception {
        nc.itf.hkjt.IHg_jzfsMaintain operator = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_jzfsMaintain.class);
        operator.delete((JzfsHVO) object);
    }
    @Override
    public Object[] queryByWhereSql(String whereSql) throws Exception {
        nc.itf.hkjt.IHg_jzfsMaintain query = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_jzfsMaintain.class);
        return query.query(whereSql);
    }
    @Override
	    public Object[] queryByDataVisibilitySetting(LoginContext context)throws Exception {
		        return null;
	    }
}
