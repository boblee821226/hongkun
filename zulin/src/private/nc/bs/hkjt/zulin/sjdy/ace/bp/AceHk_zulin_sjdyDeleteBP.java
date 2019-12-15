package nc.bs.hkjt.zulin.sjdy.ace.bp;

import nc.bs.hkjt.zulin.sjdy.plugin.bpplugin.Hk_zulin_sjdyPluginPoint;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_zulin_sjdyDeleteBP {

	public void delete(SjdyBillVO[] bills) {

		DeleteBPTemplate<SjdyBillVO> bp = new DeleteBPTemplate<SjdyBillVO>(
				Hk_zulin_sjdyPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<SjdyBillVO> processer) {
		// TODO 前规则
		IRule<SjdyBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<SjdyBillVO> processer) {
		// TODO 后规则

	}
}
