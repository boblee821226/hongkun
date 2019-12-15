package nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp;

import nc.bs.hkjt.srgk.huiguan.zhangdan.plugin.bpplugin.Hg_zhangdanPluginPoint;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHg_zhangdanDeleteBP {

	public void delete(ZhangdanBillVO[] bills) {

		DeleteBPTemplate<ZhangdanBillVO> bp = new DeleteBPTemplate<ZhangdanBillVO>(
				Hg_zhangdanPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<ZhangdanBillVO> processer) {
		// TODO 前规则
		IRule<ZhangdanBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<ZhangdanBillVO> processer) {
		// TODO 后规则

	}
}
