package nc.bs.hkjt.arap.operate.ace.bp;

import nc.bs.hkjt.arap.operate.plugin.bpplugin.Hk_arap_operatePluginPoint;
import nc.vo.hkjt.arap.operate.OperateBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_arap_operateDeleteBP {

	public void delete(OperateBillVO[] bills) {

		DeleteBPTemplate<OperateBillVO> bp = new DeleteBPTemplate<OperateBillVO>(
				Hk_arap_operatePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<OperateBillVO> processer) {
		// TODO ǰ����
		IRule<OperateBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<OperateBillVO> processer) {
		// TODO �����

	}
}
