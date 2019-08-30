package nc.bs.hkjt.fapiao.bill.ace.bp;

import nc.bs.hkjt.fapiao.bill.plugin.bpplugin.Hk_fp_billPluginPoint;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_fp_billDeleteBP {

	public void delete(BillFpBillVO[] bills) {

		DeleteBPTemplate<BillFpBillVO> bp = new DeleteBPTemplate<BillFpBillVO>(
				Hk_fp_billPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<BillFpBillVO> processer) {
		// TODO 前规则
		IRule<BillFpBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<BillFpBillVO> processer) {
		// TODO 后规则

	}
}
