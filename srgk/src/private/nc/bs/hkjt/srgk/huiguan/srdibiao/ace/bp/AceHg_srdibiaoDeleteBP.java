package nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp;

import nc.bs.hkjt.srgk.huiguan.srdibiao.plugin.bpplugin.Hg_srdibiaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHg_srdibiaoDeleteBP {

	public void delete(SrdibiaoBillVO[] bills) {

		DeleteBPTemplate<SrdibiaoBillVO> bp = new DeleteBPTemplate<SrdibiaoBillVO>(
				Hg_srdibiaoPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<SrdibiaoBillVO> processer) {
		// TODO 前规则
		IRule<SrdibiaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<SrdibiaoBillVO> processer) {
		// TODO 后规则

	}
}
