package nc.bs.hkjt.fapiao_sk.bill.ace.bp;

import nc.bs.hkjt.fapiao_sk.bill.plugin.bpplugin.Hk_fp_sk_billPluginPoint;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_fp_sk_billDeleteBP {

	public void delete(BillSkFpBillVO[] bills) {

		DeleteBPTemplate<BillSkFpBillVO> bp = new DeleteBPTemplate<BillSkFpBillVO>(
				Hk_fp_sk_billPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<BillSkFpBillVO> processer) {
		// TODO ǰ����
		IRule<BillSkFpBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<BillSkFpBillVO> processer) {
		// TODO �����

	}
}
