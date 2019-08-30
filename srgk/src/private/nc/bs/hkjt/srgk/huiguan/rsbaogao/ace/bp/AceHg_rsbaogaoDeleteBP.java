package nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp;

import nc.bs.hkjt.srgk.huiguan.rsbaogao.plugin.bpplugin.Hg_rsbaogaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHg_rsbaogaoDeleteBP {

	public void delete(RsbaogaoBillVO[] bills) {

		DeleteBPTemplate<RsbaogaoBillVO> bp = new DeleteBPTemplate<RsbaogaoBillVO>(
				Hg_rsbaogaoPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<RsbaogaoBillVO> processer) {
		// TODO 前规则
		IRule<RsbaogaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<RsbaogaoBillVO> processer) {
		// TODO 后规则

	}
}
