package nc.bs.hkjt.zulin.tiaozheng.ace.bp;

import nc.bs.hkjt.zulin.tiaozheng.plugin.bpplugin.Hk_zulin_tiaozhengPluginPoint;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_zulin_tiaozhengDeleteBP {

	public void delete(TzBillVO[] bills) {

		DeleteBPTemplate<TzBillVO> bp = new DeleteBPTemplate<TzBillVO>(
				Hk_zulin_tiaozhengPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<TzBillVO> processer) {
		// TODO 前规则
		IRule<TzBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<TzBillVO> processer) {
		// TODO 后规则

	}
}
