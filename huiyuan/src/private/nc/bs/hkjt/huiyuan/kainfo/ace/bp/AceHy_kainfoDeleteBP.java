package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import nc.bs.hkjt.huiyuan.kainfo.plugin.bpplugin.Hy_kainfoPluginPoint;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_kainfoDeleteBP {

	public void delete(KainfoBillVO[] bills) {

		DeleteBPTemplate<KainfoBillVO> bp = new DeleteBPTemplate<KainfoBillVO>(
				Hy_kainfoPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KainfoBillVO> processer) {
		// TODO 前规则
		IRule<KainfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KainfoBillVO> processer) {
		// TODO 后规则

	}
}
