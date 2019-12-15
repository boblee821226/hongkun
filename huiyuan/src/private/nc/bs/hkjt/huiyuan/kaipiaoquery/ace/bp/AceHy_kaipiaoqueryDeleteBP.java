package nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp;

import nc.bs.hkjt.huiyuan.kaipiaoquery.plugin.bpplugin.Hy_kaipiaoqueryPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_kaipiaoqueryDeleteBP {

	public void delete(KaipiaoqueryBillVO[] bills) {

		DeleteBPTemplate<KaipiaoqueryBillVO> bp = new DeleteBPTemplate<KaipiaoqueryBillVO>(
				Hy_kaipiaoqueryPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KaipiaoqueryBillVO> processer) {
		// TODO 前规则
		IRule<KaipiaoqueryBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KaipiaoqueryBillVO> processer) {
		// TODO 后规则

	}
}
