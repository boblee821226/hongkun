package nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp;

import nc.bs.hkjt.srgk.huiguan.tiaozheng.plugin.bpplugin.Hg_cctzPluginPoint;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHg_cctzDeleteBP {

	public void delete(CctzBillVO[] bills) {

		DeleteBPTemplate<CctzBillVO> bp = new DeleteBPTemplate<CctzBillVO>(
				Hg_cctzPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<CctzBillVO> processer) {
		// TODO 前规则
		IRule<CctzBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<CctzBillVO> processer) {
		// TODO 后规则

	}
}
