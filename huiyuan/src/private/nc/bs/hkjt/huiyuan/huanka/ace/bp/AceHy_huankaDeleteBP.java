package nc.bs.hkjt.huiyuan.huanka.ace.bp;

import nc.bs.hkjt.huiyuan.huanka.plugin.bpplugin.Hy_huankaPluginPoint;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_huankaDeleteBP {

	public void delete(HuankaBillVO[] bills) {

		DeleteBPTemplate<HuankaBillVO> bp = new DeleteBPTemplate<HuankaBillVO>(
				Hy_huankaPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<HuankaBillVO> processer) {
		// TODO 前规则
		IRule<HuankaBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<HuankaBillVO> processer) {
		// TODO 后规则

	}
}
