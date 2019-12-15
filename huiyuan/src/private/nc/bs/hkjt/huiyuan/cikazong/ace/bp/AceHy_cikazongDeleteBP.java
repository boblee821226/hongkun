package nc.bs.hkjt.huiyuan.cikazong.ace.bp;

import nc.bs.hkjt.huiyuan.cikazong.plugin.bpplugin.Hy_cikazongPluginPoint;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_cikazongDeleteBP {

	public void delete(CikazongBillVO[] bills) {

		DeleteBPTemplate<CikazongBillVO> bp = new DeleteBPTemplate<CikazongBillVO>(
				Hy_cikazongPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<CikazongBillVO> processer) {
		// TODO 前规则
		IRule<CikazongBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<CikazongBillVO> processer) {
		// TODO 后规则

	}
}
