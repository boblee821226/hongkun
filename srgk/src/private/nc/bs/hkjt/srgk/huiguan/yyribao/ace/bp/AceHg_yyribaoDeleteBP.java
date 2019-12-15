package nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp;

import nc.bs.hkjt.srgk.huiguan.yyribao.plugin.bpplugin.Hg_yyribaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHg_yyribaoDeleteBP {

	public void delete(YyribaoBillVO[] bills) {

		DeleteBPTemplate<YyribaoBillVO> bp = new DeleteBPTemplate<YyribaoBillVO>(
				Hg_yyribaoPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<YyribaoBillVO> processer) {
		// TODO ǰ����
		IRule<YyribaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<YyribaoBillVO> processer) {
		// TODO �����

	}
}
