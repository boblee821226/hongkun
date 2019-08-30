package nc.bs.hkjt.huiyuan.kadangan.ace.bp;

import nc.bs.hkjt.huiyuan.kadangan.plugin.bpplugin.Hy_huiyuanPluginPoint;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_huiyuanDeleteBP {

	public void delete(KadanganBillVO[] bills) {

		DeleteBPTemplate<KadanganBillVO> bp = new DeleteBPTemplate<KadanganBillVO>(
				Hy_huiyuanPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KadanganBillVO> processer) {
		// TODO 前规则
		IRule<KadanganBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KadanganBillVO> processer) {
		// TODO 后规则

	}
}
