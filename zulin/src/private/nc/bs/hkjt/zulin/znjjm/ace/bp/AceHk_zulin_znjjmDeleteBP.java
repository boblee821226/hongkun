package nc.bs.hkjt.zulin.znjjm.ace.bp;

import nc.bs.hkjt.zulin.znjjm.plugin.bpplugin.Hk_zulin_znjjmPluginPoint;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_zulin_znjjmDeleteBP {

	public void delete(ZnjjmBillVO[] bills) {

		DeleteBPTemplate<ZnjjmBillVO> bp = new DeleteBPTemplate<ZnjjmBillVO>(
				Hk_zulin_znjjmPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<ZnjjmBillVO> processer) {
		// TODO ǰ����
		IRule<ZnjjmBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<ZnjjmBillVO> processer) {
		// TODO �����

	}
}
