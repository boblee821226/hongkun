package nc.bs.hkjt.zulin.sdflr.ace.bp;

import nc.bs.hkjt.zulin.sdflr.plugin.bpplugin.Hk_zulin_sdflrPluginPoint;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_zulin_sdflrDeleteBP {

	public void delete(SdflrBillVO[] bills) {

		DeleteBPTemplate<SdflrBillVO> bp = new DeleteBPTemplate<SdflrBillVO>(
				Hk_zulin_sdflrPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<SdflrBillVO> processer) {
		// TODO 前规则
		IRule<SdflrBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<SdflrBillVO> processer) {
		// TODO 后规则

	}
}
