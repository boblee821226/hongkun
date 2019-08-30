package nc.bs.hkjt.huiyuan.cikatongji.ace.bp;

import nc.bs.hkjt.huiyuan.cikatongji.plugin.bpplugin.Hy_cikatongjiPluginPoint;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHy_cikatongjiDeleteBP {

	public void delete(CikatongjiBillVO[] bills) {

		DeleteBPTemplate<CikatongjiBillVO> bp = new DeleteBPTemplate<CikatongjiBillVO>(
				Hy_cikatongjiPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<CikatongjiBillVO> processer) {
		// TODO ǰ����
		IRule<CikatongjiBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<CikatongjiBillVO> processer) {
		// TODO �����

	}
}
