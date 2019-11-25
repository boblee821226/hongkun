package nc.bs.hkjt.zulin.bkfyft.ace.bp;

import nc.bs.hkjt.zulin.bkfyft.plugin.bpplugin.Hk_zulin_yuebaoPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;

/**
 * �޸ı����BP
 * 
 */
public class AceHk_zulin_yuebaoUpdateBP {

	public YuebaoBillVO[] update(YuebaoBillVO[] bills,
			YuebaoBillVO[] originBills) {
		
		String vbilltypecode = bills[0].getParentVO().getVbilltypecode();
		
		// �����޸�ģ��
		UpdateBPTemplate<YuebaoBillVO> bp = new UpdateBPTemplate<YuebaoBillVO>(
				Hk_zulin_yuebaoPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser(), vbilltypecode);
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser(), vbilltypecode);
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<YuebaoBillVO> processer, String vbilltypecode) {
		// TODO �����
		IRule<YuebaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype(vbilltypecode);
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<YuebaoBillVO> processer, String vbilltypecode) {
		// TODO ǰ����
		IRule<YuebaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<YuebaoBillVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype(vbilltypecode);
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
