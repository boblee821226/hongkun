package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import nc.bs.hkjt.huiyuan.kaipiaoinfo.plugin.bpplugin.Hy_kaipiaoinfoPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_kaipiaoinfoDeleteBP {

	public void delete(KaipiaoinfoBillVO[] bills) {

		DeleteBPTemplate<KaipiaoinfoBillVO> bp = new DeleteBPTemplate<KaipiaoinfoBillVO>(
				Hy_kaipiaoinfoPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KaipiaoinfoBillVO> processer) {
		// TODO 前规则
		IRule<KaipiaoinfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KaipiaoinfoBillVO> processer) {
		// TODO 后规则

	}
}
