package nc.bs.hkjt.store.lvyun_out.ace.bp;

import nc.bs.hkjt.store.lvyun_out.plugin.bpplugin.Hk_store_lvyun_outPluginPoint;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_store_lvyun_outDeleteBP {

	public void delete(LyOutStoreBillVO[] bills) {

		DeleteBPTemplate<LyOutStoreBillVO> bp = new DeleteBPTemplate<LyOutStoreBillVO>(
				Hk_store_lvyun_outPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<LyOutStoreBillVO> processer) {
		// TODO ǰ����
		IRule<LyOutStoreBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<LyOutStoreBillVO> processer) {
		// TODO �����

	}
}
