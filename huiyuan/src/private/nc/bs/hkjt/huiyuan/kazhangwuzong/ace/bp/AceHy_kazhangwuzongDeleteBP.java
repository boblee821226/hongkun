package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.bs.hkjt.huiyuan.kazhangwuzong.plugin.bpplugin.Hy_kazhangwuzongPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHy_kazhangwuzongDeleteBP {

	public void delete(KazhangwuzongBillVO[] bills) {

		DeleteBPTemplate<KazhangwuzongBillVO> bp = new DeleteBPTemplate<KazhangwuzongBillVO>(
				Hy_kazhangwuzongPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KazhangwuzongBillVO> processer) {
		// TODO ǰ����
		IRule<KazhangwuzongBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KazhangwuzongBillVO> processer) {
		// TODO �����

	}
}
