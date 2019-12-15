package nc.bs.hkjt.huiyuan.kazuofei.ace.bp;

import nc.bs.hkjt.huiyuan.kazuofei.plugin.bpplugin.Hy_kazuofeiPluginPoint;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_kazuofeiDeleteBP {

	public void delete(KazuofeiBillVO[] bills) {

		DeleteBPTemplate<KazuofeiBillVO> bp = new DeleteBPTemplate<KazuofeiBillVO>(
				Hy_kazuofeiPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KazuofeiBillVO> processer) {
		// TODO 前规则
		IRule<KazuofeiBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KazuofeiBillVO> processer) {
		// TODO 后规则

	}
}
