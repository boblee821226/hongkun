package nc.bs.hkjt.zulin.znjjs.ace.bp;

import nc.bs.hkjt.zulin.znjjs.plugin.bpplugin.Hk_zulin_znjjsPluginPoint;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_zulin_znjjsDeleteBP {

	public void delete(ZnjjsBillVO[] bills) {

		DeleteBPTemplate<ZnjjsBillVO> bp = new DeleteBPTemplate<ZnjjsBillVO>(
				Hk_zulin_znjjsPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<ZnjjsBillVO> processer) {
		// TODO 前规则
		IRule<ZnjjsBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<ZnjjsBillVO> processer) {
		// TODO 后规则

	}
}
