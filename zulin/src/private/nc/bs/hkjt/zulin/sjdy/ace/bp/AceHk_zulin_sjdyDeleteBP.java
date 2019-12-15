package nc.bs.hkjt.zulin.sjdy.ace.bp;

import nc.bs.hkjt.zulin.sjdy.plugin.bpplugin.Hk_zulin_sjdyPluginPoint;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_zulin_sjdyDeleteBP {

	public void delete(SjdyBillVO[] bills) {

		DeleteBPTemplate<SjdyBillVO> bp = new DeleteBPTemplate<SjdyBillVO>(
				Hk_zulin_sjdyPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<SjdyBillVO> processer) {
		// TODO ǰ����
		IRule<SjdyBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<SjdyBillVO> processer) {
		// TODO �����

	}
}
