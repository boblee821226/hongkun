package nc.ui.pmpub.action.processor;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.ServerTimeProxy;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pcm.feebalance.FeeBalanceHeadVO;
import nc.vo.pm.constant.BillStatusConst;
import nc.vo.pm.constant.CommonKeyConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.uif2.LoginContext;

/**
 * 单据复制时表头表体处理
 * 
 * @Author zhangzhxa
 * @Date 2012-12-28
 * @Version NC63
 * 
 */
public class DefaultReverseActionProcessor implements
		ICopyActionProcessor<AbstractBill> {

	@Override
	public void processVOAfterCopy(AbstractBill billVO, LoginContext context) {
		this.processHeadVO(billVO, context);
		try {
			this.processBodyVO(billVO);
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	private void processBodyVO(AbstractBill vo) throws BusinessException {
		vo.getParent().setAttributeValue(
				vo.getMetaData().getParent().getPrimaryAttribute().getName(),
				null);
		vo.getParent().setAttributeValue(CommonKeyConst.TS, null);
		// CHECK 未考虑多页签以及以后的二次开发扩展页签

		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		for (IVOMeta meta : vo.getMetaData().getChildren()) {
			if (vo.getChildren(meta) == null
					|| vo.getChildren(meta).length == 0) {
				continue;
			}
			for (ISuperVO childvo : vo.getChildren(meta)) {
				/**
				 * HK 2020年6月26日12:01:53
				 * 关联以及处理
				 */
				String bid = childvo.getAttributeValue("pk_feebalance_b").toString();
				childvo.setAttributeValue("def20", bid);
				UFDouble mny = PuPubVO.getUFDouble_NullAsZero(childvo.getAttributeValue("money"));
				// 根据bid 去查询已经存在的 红冲单。汇总金额。
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" sum(fb.money) ")
							.append(" from pm_feebalance_b fb ")
							.append(" where fb.dr = 0 ")
							.append(" and fb.def20 = '").append(bid).append("' ")	
							;
				UFDouble hongchong = UFDouble.ZERO_DBL; // 历史红冲总额
				ArrayList list = (ArrayList) iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
				if (list != null && !list.isEmpty()) {
					hongchong = PuPubVO.getUFDouble_NullAsZero(((Object[])list.get(0))[0]);
				}
				// 本次红冲总额 = 金额 + 历史红冲，取反。
				UFDouble benci = UFDouble.ZERO_DBL.sub(mny.add(hongchong));
				childvo.setAttributeValue("money", benci);
				/***END***/
				
				/**
				 * HK 2020年6月30日15:30:35
				 * 摘要前面加 红冲
				 */
				String memo = PuPubVO.getString_TrimZeroLenAsNull(childvo.getAttributeValue("memo"));
				if (memo == null) {
					memo = "红冲";
				} else {
					memo = "红冲：" + memo;
				}
				childvo.setAttributeValue("memo", memo);
				/***END***/
				
				childvo.setAttributeValue(meta.getPrimaryAttribute().getName(),
						null);
				childvo.setAttributeValue(CommonKeyConst.PK_GROUP, null);
				childvo.setAttributeValue(CommonKeyConst.PK_ORG, null);
				childvo.setAttributeValue(CommonKeyConst.TS, null);
			}
		}
	}

	private void processHeadVO(AbstractBill vo, LoginContext context) {
		UFDateTime datetime = ServerTimeProxy.getInstance().getServerTime();
		FeeBalanceHeadVO hvo = (FeeBalanceHeadVO) vo.getParentVO();
		/**
		 * HK 2020年6月26日12:01:28 
		 * 关联字段
		 */
		hvo.setAttributeValue("def20", hvo.getBill_code());
		
		// 设置空处理
		hvo.setAttributeValue(CommonKeyConst.BILL_CODE, null);
		hvo.setAttributeValue(CommonKeyConst.AUDITOR, null);
		hvo.setAttributeValue(CommonKeyConst.AUDITTIME, null);
		hvo.setAttributeValue(CommonKeyConst.CHECK_OPINION, null);
		hvo.setAttributeValue(CommonKeyConst.MODIFIER, null);
		hvo.setAttributeValue(CommonKeyConst.MODIFIEDTIME, null);
		hvo.setAttributeValue(CommonKeyConst.CREATOR, null);
		hvo.setAttributeValue(CommonKeyConst.CREATIONTIME, null);

		// 设置默认值
		hvo.setAttributeValue(CommonKeyConst.BILLMAKER,
				context.getPk_loginUser());
		hvo.setAttributeValue(CommonKeyConst.BILLMAKETIME, datetime.getDate());
		hvo.setAttributeValue(CommonKeyConst.PK_ORG, context.getPk_org());
		hvo.setAttributeValue(CommonKeyConst.BILL_STATUS, BillStatusConst.free);
	}

}
