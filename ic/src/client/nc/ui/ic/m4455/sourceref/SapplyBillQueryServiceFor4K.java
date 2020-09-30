package nc.ui.ic.m4455.sourceref;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4455.ISapplyBillQueryForOutBill;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 为转库提供参照查询
 * HK 2020年9月30日13:31:26
 */
public class SapplyBillQueryServiceFor4K implements IRefQueryService {

  @Override
  public Object[] queryByWhereSql(String whereSql) throws Exception {
    return null;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return NCLocator.getInstance().lookup(ISapplyBillQueryForOutBill.class)
      .queryBillFor4K(queryScheme);
  }
}
