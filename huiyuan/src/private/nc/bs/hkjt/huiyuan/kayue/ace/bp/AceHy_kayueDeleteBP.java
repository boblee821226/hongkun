package nc.bs.hkjt.huiyuan.kayue.ace.bp;

import nc.bs.hkjt.huiyuan.kayue.plugin.bpplugin.Hy_kayuePluginPoint;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHy_kayueDeleteBP {

	public void delete(KayueBillVO[] bills) {

		DeleteBPTemplate<KayueBillVO> bp = new DeleteBPTemplate<KayueBillVO>(
				Hy_kayuePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KayueBillVO> processer) {
		// TODO ǰ����
		IRule<KayueBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KayueBillVO> processer) {
		// TODO �����

	}
}
