package nc.bs.hkjt.huiyuan.cikayue.ace.bp;

import nc.bs.hkjt.huiyuan.cikayue.plugin.bpplugin.Hy_cikayuePluginPoint;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHy_cikayueDeleteBP {

	public void delete(CikayueBillVO[] bills) {

		DeleteBPTemplate<CikayueBillVO> bp = new DeleteBPTemplate<CikayueBillVO>(
				Hy_cikayuePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<CikayueBillVO> processer) {
		// TODO ǰ����
		IRule<CikayueBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<CikayueBillVO> processer) {
		// TODO �����

	}
}
