package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import nc.bs.hkjt.huiyuan.kainfo.plugin.bpplugin.Hy_kainfoPluginPoint;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHy_kainfoDeleteBP {

	public void delete(KainfoBillVO[] bills) {

		DeleteBPTemplate<KainfoBillVO> bp = new DeleteBPTemplate<KainfoBillVO>(
				Hy_kainfoPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KainfoBillVO> processer) {
		// TODO ǰ����
		IRule<KainfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KainfoBillVO> processer) {
		// TODO �����

	}
}
