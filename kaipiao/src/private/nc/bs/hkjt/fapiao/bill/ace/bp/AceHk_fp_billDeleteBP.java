package nc.bs.hkjt.fapiao.bill.ace.bp;

import nc.bs.hkjt.fapiao.bill.plugin.bpplugin.Hk_fp_billPluginPoint;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_fp_billDeleteBP {

	public void delete(BillFpBillVO[] bills) {

		DeleteBPTemplate<BillFpBillVO> bp = new DeleteBPTemplate<BillFpBillVO>(
				Hk_fp_billPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<BillFpBillVO> processer) {
		// TODO ǰ����
		IRule<BillFpBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<BillFpBillVO> processer) {
		// TODO �����

	}
}
