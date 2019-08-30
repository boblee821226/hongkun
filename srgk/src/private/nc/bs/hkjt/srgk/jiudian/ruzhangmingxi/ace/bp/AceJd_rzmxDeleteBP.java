package nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp;

import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.plugin.bpplugin.Jd_rzmxPluginPoint;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceJd_rzmxDeleteBP {

	public void delete(RzmxBillVO[] bills) {

		DeleteBPTemplate<RzmxBillVO> bp = new DeleteBPTemplate<RzmxBillVO>(
				Jd_rzmxPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<RzmxBillVO> processer) {
		// TODO ǰ����
		IRule<RzmxBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<RzmxBillVO> processer) {
		// TODO �����

	}
}
