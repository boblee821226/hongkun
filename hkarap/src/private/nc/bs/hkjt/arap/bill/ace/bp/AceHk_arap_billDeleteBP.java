package nc.bs.hkjt.arap.bill.ace.bp;

import nc.bs.hkjt.arap.bill.plugin.bpplugin.Hk_arap_billPluginPoint;
import nc.vo.hkjt.arap.bill.BillBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_arap_billDeleteBP {

	public void delete(BillBillVO[] bills) {

		DeleteBPTemplate<BillBillVO> bp = new DeleteBPTemplate<BillBillVO>(
				Hk_arap_billPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<BillBillVO> processer) {
		// TODO 前规则
		IRule<BillBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<BillBillVO> processer) {
		// TODO 后规则

	}
}
