package nc.bs.hkjt.zulin.znjjs.ace.bp;

import nc.bs.hkjt.zulin.znjjs.plugin.bpplugin.Hk_zulin_znjjsPluginPoint;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHk_zulin_znjjsDeleteBP {

	public void delete(ZnjjsBillVO[] bills) {

		DeleteBPTemplate<ZnjjsBillVO> bp = new DeleteBPTemplate<ZnjjsBillVO>(
				Hk_zulin_znjjsPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<ZnjjsBillVO> processer) {
		// TODO ǰ����
		IRule<ZnjjsBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<ZnjjsBillVO> processer) {
		// TODO �����

	}
}
