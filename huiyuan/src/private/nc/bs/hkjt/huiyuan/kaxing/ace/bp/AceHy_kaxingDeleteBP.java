package nc.bs.hkjt.huiyuan.kaxing.ace.bp;

import nc.bs.hkjt.huiyuan.kaxing.plugin.bpplugin.Hy_kaxingPluginPoint;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_kaxingDeleteBP {

	public void delete(KaxingBillVO[] bills) {

		DeleteBPTemplate<KaxingBillVO> bp = new DeleteBPTemplate<KaxingBillVO>(
				Hy_kaxingPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KaxingBillVO> processer) {
		// TODO 前规则
		IRule<KaxingBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KaxingBillVO> processer) {
		// TODO 后规则

	}
}
