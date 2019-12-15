package nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp;

import nc.bs.hkjt.srgk.huiguan.sgshuju.plugin.bpplugin.Hg_sgshujuPluginPoint;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHg_sgshujuDeleteBP {

	public void delete(SgshujuBillVO[] bills) {

		DeleteBPTemplate<SgshujuBillVO> bp = new DeleteBPTemplate<SgshujuBillVO>(
				Hg_sgshujuPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<SgshujuBillVO> processer) {
		// TODO ǰ����
		IRule<SgshujuBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<SgshujuBillVO> processer) {
		// TODO �����

	}
}
