package nc.bs.hkjt.arap.account.ace.bp;

import nc.bs.hkjt.arap.account.plugin.bpplugin.Hk_arap_accoutPluginPoint;
import nc.vo.hkjt.arap.account.AccountBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_arap_accoutDeleteBP {

	public void delete(AccountBillVO[] bills) {

		DeleteBPTemplate<AccountBillVO> bp = new DeleteBPTemplate<AccountBillVO>(
				Hk_arap_accoutPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AccountBillVO> processer) {
		// TODO ǰ����
		IRule<AccountBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AccountBillVO> processer) {
		// TODO �����

	}
}
