package nc.bs.hkjt.zulin.tiaozheng.ace.bp;

import nc.bs.hkjt.zulin.tiaozheng.plugin.bpplugin.Hk_zulin_tiaozhengPluginPoint;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_zulin_tiaozhengDeleteBP {

	public void delete(TzBillVO[] bills) {

		DeleteBPTemplate<TzBillVO> bp = new DeleteBPTemplate<TzBillVO>(
				Hk_zulin_tiaozhengPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<TzBillVO> processer) {
		// TODO ǰ����
		IRule<TzBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<TzBillVO> processer) {
		// TODO �����

	}
}
