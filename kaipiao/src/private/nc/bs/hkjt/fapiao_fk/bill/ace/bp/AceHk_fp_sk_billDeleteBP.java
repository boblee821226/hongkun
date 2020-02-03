package nc.bs.hkjt.fapiao_fk.bill.ace.bp;

import nc.bs.hkjt.fapiao_fk.bill.plugin.bpplugin.Hk_fp_sk_billPluginPoint;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_fp_sk_billDeleteBP {

	public void delete(BillSkFpBillVO[] bills) {

		DeleteBPTemplate<BillSkFpBillVO> bp = new DeleteBPTemplate<BillSkFpBillVO>(
				Hk_fp_sk_billPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<BillSkFpBillVO> processer) {
		// TODO 前规则
		IRule<BillSkFpBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<BillSkFpBillVO> processer) {
		// TODO 后规则

	}
}
