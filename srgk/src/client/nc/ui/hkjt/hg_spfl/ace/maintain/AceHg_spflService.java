package nc.ui.hkjt.hg_spfl.ace.maintain;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;

/**
 * 示例单据的操作代理
 * 
 * @since 6.0
 * @version 2011-7-6 上午08:31:09
 * @author duy
 */
public class AceHg_spflService
             implements IAppModelService,IQueryService 
                     {
    @Override
    public Object insert(Object object) throws Exception {
        nc.itf.hkjt.IHg_spflMaintain operator = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_spflMaintain.class);
        return operator.insert((SpflHVO) object);
    }
    @Override
    public Object update(Object object) throws Exception {
        nc.itf.hkjt.IHg_spflMaintain operator = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_spflMaintain.class);
        //前台先将修改人赋值
        String pk_user = AppContext.getInstance().getPkUser();
        ((SpflHVO) object).setModifier(pk_user);
        return operator.update((SpflHVO) object);
    }
    @Override
    public void delete(Object object) throws Exception {
        nc.itf.hkjt.IHg_spflMaintain operator = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_spflMaintain.class);
        operator.delete((SpflHVO) object);
    }
    @Override
    public Object[] queryByWhereSql(String whereSql) throws Exception {
        nc.itf.hkjt.IHg_spflMaintain query = NCLocator.getInstance().lookup(nc.itf.hkjt.IHg_spflMaintain.class);
        return query.query(whereSql);
    }
    @Override
	    public Object[] queryByDataVisibilitySetting(LoginContext context)throws Exception {
		        return null;
	    }
}
