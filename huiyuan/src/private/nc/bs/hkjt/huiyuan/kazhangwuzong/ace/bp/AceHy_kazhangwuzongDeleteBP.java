package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.bs.hkjt.huiyuan.kazhangwuzong.plugin.bpplugin.Hy_kazhangwuzongPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_kazhangwuzongDeleteBP {

	public void delete(KazhangwuzongBillVO[] bills) {

		DeleteBPTemplate<KazhangwuzongBillVO> bp = new DeleteBPTemplate<KazhangwuzongBillVO>(
				Hy_kazhangwuzongPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KazhangwuzongBillVO> processer) {
		// TODO 前规则
		IRule<KazhangwuzongBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KazhangwuzongBillVO> processer) {
		// TODO 后规则

	}
}
