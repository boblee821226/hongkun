package nc.bs.hkjt.arap.unit.ace.bp;

import nc.bs.hkjt.arap.unit.plugin.bpplugin.Hk_arap_unitPluginPoint;
import nc.vo.hkjt.arap.unit.UnitBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_arap_unitDeleteBP {

	public void delete(UnitBillVO[] bills) {

		DeleteBPTemplate<UnitBillVO> bp = new DeleteBPTemplate<UnitBillVO>(
				Hk_arap_unitPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<UnitBillVO> processer) {
		// TODO ǰ����
		IRule<UnitBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<UnitBillVO> processer) {
		// TODO �����

	}
}
