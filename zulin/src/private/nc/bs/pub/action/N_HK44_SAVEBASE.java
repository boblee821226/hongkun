package nc.bs.pub.action;

import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.bkfytz.plugin.bpplugin.Hk_zulin_tiaozhengPluginPoint;
import nc.vo.hkjt.zulin.tiaozheng.TzBVO;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.vo.hkjt.zulin.tiaozheng.TzHVO;
import nc.itf.hkjt.IHk_zulin_tiaozhengMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;

public class N_HK44_SAVEBASE extends AbstractPfAction<TzBillVO> {

	@Override
	protected CompareAroundProcesser<TzBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<TzBillVO> processor = null;
		TzBillVO[] clientFullVOs = (TzBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<TzBillVO>(
					Hk_zulin_tiaozhengPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<TzBillVO>(
					Hk_zulin_tiaozhengPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<TzBillVO> rule = null;

		return processor;
	}

	@Override
	protected TzBillVO[] processBP(Object userObj,
			TzBillVO[] clientFullVOs, TzBillVO[] originBills) {

		TzBillVO[] bills = null;
		try {
			
			/**
			 * HK 2019年12月15日10:37:59
			 * 增加校验
			 * 1、表体不能为空
			 * 2、同组织同期间  只能存在一张单据
			 */
			for(int i=0;i<clientFullVOs.length;i++)
			{
				TzHVO    hvo = clientFullVOs[i].getParentVO();
				TzBVO[] bvos = (TzBVO[])clientFullVOs[i].getChildrenVO();
				
				hvo.setVbilltypecode("HK44");
				
				if(bvos==null||bvos.length<=0)
					throw new BusinessException("表体数据不能为空");
				
				String pk = hvo.getPk_hk_zulin_tiaozheng();	// pk
				String pk_org = hvo.getPk_org();			// 组织
				String qijian = hvo.getVdef01();			// 期间
				
				String isZrpz = hvo.getVdef03();	// 是否责任凭证
				if (null == isZrpz
				 || "~".equals(isZrpz)
				) {
					isZrpz = "N";
				}
				
				if(pk==null) pk="null";
				
				BaseDAO dao = new BaseDAO();
				StringBuffer querySQL = 
					new StringBuffer(" select tz.pk_hk_zulin_tiaozheng ")
							.append(" from hk_zulin_tiaozheng tz ")
							.append(" where tz.dr=0 ")
							.append(" and tz.vbilltypecode = 'HK44' ")
							.append(" and tz.pk_org = '"+pk_org+"' ")
							.append(" and tz.vdef01 = '"+qijian+"' ")
							.append(" and tz.pk_hk_zulin_tiaozheng != '"+pk+"' ")
							// 责任凭证
							.append(" and replace(nvl(tz.vdef03, 'N'), '~', 'N') = '").append(isZrpz).append("' ")
				;
				List list = (List)dao.executeQuery(querySQL.toString(),new ArrayListProcessor());
				if(list!=null && list.size()>0)
				{
					throw new BusinessException("同组织同期间，不能保存多份月报调整（责任凭证）。");
				}
				
			}
			/***END***/
			
			IHk_zulin_tiaozhengMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_tiaozhengMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
