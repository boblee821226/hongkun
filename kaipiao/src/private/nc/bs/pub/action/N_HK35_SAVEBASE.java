package nc.bs.pub.action;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.fapiao.bill.plugin.bpplugin.Hk_fp_billPluginPoint;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.hkjt.fapiao.bill.BillFpHVO;
import nc.itf.hkjt.IHk_fp_billMaintain;

public class N_HK35_SAVEBASE extends AbstractPfAction<BillFpBillVO> {

	@Override
	protected CompareAroundProcesser<BillFpBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillFpBillVO> processor = null;
		BillFpBillVO[] clientFullVOs = (BillFpBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<BillFpBillVO>(
					Hk_fp_billPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<BillFpBillVO>(
					Hk_fp_billPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<BillFpBillVO> rule = null;

		return processor;
	}

	@Override
	protected BillFpBillVO[] processBP(Object userObj,
			BillFpBillVO[] clientFullVOs, BillFpBillVO[] originBills) {

		BillFpBillVO[] bills = null;
		try {
			
			/**
			 * 2018年8月10日10:17:54
			 * 保存前校验，发票号码的长度  与  参数的长度 是否一致
			 * 2018年8月17日10:40:52
			 * 发票号的参数 会存 多个长度的。用逗号分隔。
			 */
			for(BillFpBillVO billVO : clientFullVOs)
			{
				BillFpHVO hVO = billVO.getParentVO();
				String fphm = hVO.getFphm();
				
//				Integer fpLength = PuPubVO.getInteger_NullAs( hVO.getVdef10() , 18 );
//				
//				if(fphm.length()!=fpLength)
//				{
//					throw new BusinessException("发票号码的长度必须为【"+fpLength+"】位。");
//				}
				
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
			
			IHk_fp_billMaintain operator = NCLocator.getInstance()
					.lookup(IHk_fp_billMaintain.class);
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
