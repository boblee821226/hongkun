package nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp;

import nc.bs.hkjt.srgk.huiguan.zhangdan.plugin.bpplugin.Hg_zhangdanPluginPoint;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHg_zhangdanDeleteBP {

	public void delete(ZhangdanBillVO[] bills) {

		DeleteBPTemplate<ZhangdanBillVO> bp = new DeleteBPTemplate<ZhangdanBillVO>(
				Hg_zhangdanPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<ZhangdanBillVO> processer) {
		// TODO ǰ����
		IRule<ZhangdanBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<ZhangdanBillVO> processer) {
		// TODO �����

	}
}
