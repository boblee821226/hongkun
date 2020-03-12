package nc.impl.pcm.billrule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.arap.pay.IArapPayBillPubService;
import nc.pubitf.initgroup.InitGroupQuery;
import nc.vo.pm.constant.BillStatusConst;
import nc.vo.pm.constant.CommonKeyConst;
import nc.vo.pm.constant.ModuleConst;
import nc.vo.pm.proxy.PMProxy;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * 进度款、费用结算单
 * 弃审时需要 删除对应的付款单
 * @author HK
 * 
 */
public class DeletePayBillRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(AbstractBill[] vos) {
		if (ArrayUtils.isEmpty(vos))
			return;
		String pk_group = (String) vos[0].getParent().getAttributeValue(CommonKeyConst.PK_GROUP);
		Integer billStatus = (Integer) vos[0].getParent().getAttributeValue(CommonKeyConst.BILL_STATUS);
		if (billStatus == BillStatusConst.approved) {
			try {
				if (InitGroupQuery.isEnabled(pk_group, ModuleConst.AP_FUNCCODE)) {
					String[] pks = new String[vos.length];
					for (int i = 0; i < pks.length; i++) {
						pks[i] = vos[i].getPrimaryKey();
					}
					PMProxy.lookup(IArapPayBillPubService.class).deleteBillBySourcePK(pks);
				}
			} catch (BusinessException e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}
}
