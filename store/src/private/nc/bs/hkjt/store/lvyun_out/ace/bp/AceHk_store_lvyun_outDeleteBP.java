package nc.bs.hkjt.store.lvyun_out.ace.bp;

import nc.bs.hkjt.store.lvyun_out.plugin.bpplugin.Hk_store_lvyun_outPluginPoint;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_store_lvyun_outDeleteBP {

	public void delete(LyOutStoreBillVO[] bills) {

		DeleteBPTemplate<LyOutStoreBillVO> bp = new DeleteBPTemplate<LyOutStoreBillVO>(
				Hk_store_lvyun_outPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<LyOutStoreBillVO> processer) {
		// TODO 前规则
		IRule<LyOutStoreBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<LyOutStoreBillVO> processer) {
		// TODO 后规则

	}
}
