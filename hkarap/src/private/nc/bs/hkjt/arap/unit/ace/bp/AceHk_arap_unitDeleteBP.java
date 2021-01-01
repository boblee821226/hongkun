package nc.bs.hkjt.arap.unit.ace.bp;

import nc.bs.hkjt.arap.unit.plugin.bpplugin.Hk_arap_unitPluginPoint;
import nc.vo.hkjt.arap.unit.UnitBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_arap_unitDeleteBP {

	public void delete(UnitBillVO[] bills) {

		DeleteBPTemplate<UnitBillVO> bp = new DeleteBPTemplate<UnitBillVO>(
				Hk_arap_unitPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<UnitBillVO> processer) {
		// TODO 前规则
		IRule<UnitBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<UnitBillVO> processer) {
		// TODO 后规则

	}
}
