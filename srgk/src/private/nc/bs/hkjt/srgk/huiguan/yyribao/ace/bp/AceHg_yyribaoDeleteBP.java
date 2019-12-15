package nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp;

import nc.bs.hkjt.srgk.huiguan.yyribao.plugin.bpplugin.Hg_yyribaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHg_yyribaoDeleteBP {

	public void delete(YyribaoBillVO[] bills) {

		DeleteBPTemplate<YyribaoBillVO> bp = new DeleteBPTemplate<YyribaoBillVO>(
				Hg_yyribaoPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<YyribaoBillVO> processer) {
		// TODO 前规则
		IRule<YyribaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<YyribaoBillVO> processer) {
		// TODO 后规则

	}
}
