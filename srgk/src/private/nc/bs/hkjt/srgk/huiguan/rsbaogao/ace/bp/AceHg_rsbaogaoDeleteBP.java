package nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp;

import nc.bs.hkjt.srgk.huiguan.rsbaogao.plugin.bpplugin.Hg_rsbaogaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceHg_rsbaogaoDeleteBP {

	public void delete(RsbaogaoBillVO[] bills) {

		DeleteBPTemplate<RsbaogaoBillVO> bp = new DeleteBPTemplate<RsbaogaoBillVO>(
				Hg_rsbaogaoPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<RsbaogaoBillVO> processer) {
		// TODO ǰ����
		IRule<RsbaogaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<RsbaogaoBillVO> processer) {
		// TODO �����

	}
}
