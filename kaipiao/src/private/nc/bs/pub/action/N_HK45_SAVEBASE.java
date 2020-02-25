package nc.bs.pub.action;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.fapiao_fk.bill.plugin.bpplugin.Hk_fp_sk_billPluginPoint;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpHVO;
import nc.itf.hkjt.IHk_fp_sk_billMaintain;

public class N_HK45_SAVEBASE extends AbstractPfAction<BillSkFpBillVO> {

	@Override
	protected CompareAroundProcesser<BillSkFpBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillSkFpBillVO> processor = null;
		BillSkFpBillVO[] clientFullVOs = (BillSkFpBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<BillSkFpBillVO>(
					Hk_fp_sk_billPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<BillSkFpBillVO>(
					Hk_fp_sk_billPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<BillSkFpBillVO> rule = null;

		return processor;
	}

	@Override
	protected BillSkFpBillVO[] processBP(Object userObj,
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills) {

		BillSkFpBillVO[] bills = null;
		try {
			
			/**
			 * 2020年2月25日16:17:19
			 * 保存前校验，发票号码的长度  与  参数的长度 是否一致
			 * 发票号的参数 会存 多个长度的。用逗号分隔。
			 */
			for(BillSkFpBillVO billVO : clientFullVOs)
			{
				BillSkFpHVO hVO = billVO.getParentVO();
				String fphm = hVO.getFphm();
				
				String[] str_fpLength = hVO.getVdef10().split(",");
				HashMap<String,String> MAP_fpLength = new HashMap<String,String>();
				for(int i=0;i<str_fpLength.length;i++)
				{
					MAP_fpLength.put(str_fpLength[i], str_fpLength[i]);
				}
				
				String fpLength = ""+fphm.length();
				
				if( !MAP_fpLength.containsKey(fpLength) )
				{
					throw new BusinessException("发票号码的长度必须为【"+hVO.getVdef10()+"】位。");
				}
				
			}
			/***END***/
			
			IHk_fp_sk_billMaintain operator = NCLocator.getInstance()
					.lookup(IHk_fp_sk_billMaintain.class);
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
