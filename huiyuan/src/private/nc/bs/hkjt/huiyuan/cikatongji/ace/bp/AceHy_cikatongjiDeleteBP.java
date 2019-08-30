package nc.bs.hkjt.huiyuan.cikatongji.ace.bp;

import nc.bs.hkjt.huiyuan.cikatongji.plugin.bpplugin.Hy_cikatongjiPluginPoint;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHy_cikatongjiDeleteBP {

	public void delete(CikatongjiBillVO[] bills) {

		DeleteBPTemplate<CikatongjiBillVO> bp = new DeleteBPTemplate<CikatongjiBillVO>(
				Hy_cikatongjiPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<CikatongjiBillVO> processer) {
		// TODO 前规则
		IRule<CikatongjiBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<CikatongjiBillVO> processer) {
		// TODO 后规则

	}
}
