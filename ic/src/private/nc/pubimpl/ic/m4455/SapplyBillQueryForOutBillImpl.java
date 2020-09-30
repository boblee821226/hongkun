package nc.pubimpl.ic.m4455;

import nc.pubimpl.ic.m4455.action.SapplyBillQueryAction;
import nc.pubitf.ic.m4455.ISapplyBillQueryForOutBill;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.m4455.entity.SapplyBillVO;
import nc.vo.ic.transtype.OutBType;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ⵥ��ѯ�������뵥�ӿ�ʵ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author jinjya
 * @time 2010-12-17 ����02:12:45
 */
public class SapplyBillQueryForOutBillImpl implements
    ISapplyBillQueryForOutBill {

  @Override
  public SapplyBillVO[] queryBillFor4D(IQueryScheme queryScheme) throws BusinessException {
    try {
      SapplyBillQueryAction sapplyBillQueryAction = new SapplyBillQueryAction(String.valueOf(OutBType.Bill4D.value()));
      return sapplyBillQueryAction.queryBills(queryScheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SapplyBillVO[] queryBillFor4H(IQueryScheme queryScheme) throws BusinessException {
    try {
      SapplyBillQueryAction sapplyBillQueryAction = new SapplyBillQueryAction(String.valueOf(OutBType.Bill4H.value()));
      return sapplyBillQueryAction.queryBills(queryScheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SapplyBillVO[] queryBillFor4I(IQueryScheme queryScheme) throws BusinessException {
    try {
      SapplyBillQueryAction sapplyBillQueryAction = new SapplyBillQueryAction(String.valueOf(OutBType.Bill4I.value()));
      return sapplyBillQueryAction.queryBills(queryScheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SapplyBillVO[] queryBillFor4O(IQueryScheme queryScheme) throws BusinessException {
    try {
      SapplyBillQueryAction sapplyBillQueryAction = new SapplyBillQueryAction(String.valueOf(OutBType.Bill4O.value()));
      return sapplyBillQueryAction.queryBills(queryScheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * HK 2020��9��28��18:24:45
   * ת���ѯ�������Ľӿ�
   */
	@Override
	public SapplyBillVO[] queryBillFor4K(IQueryScheme queryScheme)
			throws BusinessException {
		try {
	      SapplyBillQueryAction sapplyBillQueryAction = new SapplyBillQueryAction("4K");
	      return sapplyBillQueryAction.queryBills(queryScheme);
	    }
	    catch (Exception ex) {
	      ExceptionUtils.marsh(ex);
	    }
		return null;
	}
}
