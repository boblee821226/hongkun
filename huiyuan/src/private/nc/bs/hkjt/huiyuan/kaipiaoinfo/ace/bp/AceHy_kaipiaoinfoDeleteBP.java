package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import nc.bs.hkjt.huiyuan.kaipiaoinfo.plugin.bpplugin.Hy_kaipiaoinfoPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHy_kaipiaoinfoDeleteBP {

	public void delete(KaipiaoinfoBillVO[] bills) {

		DeleteBPTemplate<KaipiaoinfoBillVO> bp = new DeleteBPTemplate<KaipiaoinfoBillVO>(
				Hy_kaipiaoinfoPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<KaipiaoinfoBillVO> processer) {
		// TODO ǰ����
		IRule<KaipiaoinfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<KaipiaoinfoBillVO> processer) {
		// TODO �����

	}
}
