package nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp;

import nc.bs.hkjt.srgk.huiguan.sgshuju.plugin.bpplugin.Hg_sgshujuPluginPoint;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHg_sgshujuDeleteBP {

	public void delete(SgshujuBillVO[] bills) {

		DeleteBPTemplate<SgshujuBillVO> bp = new DeleteBPTemplate<SgshujuBillVO>(
				Hg_sgshujuPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<SgshujuBillVO> processer) {
		// TODO 前规则
		IRule<SgshujuBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<SgshujuBillVO> processer) {
		// TODO 后规则

	}
}
