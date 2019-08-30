package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import nc.bs.hkjt.huiyuan.kaipiaoinfo.plugin.bpplugin.Hy_kaipiaoinfoPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;

/**
 * 修改保存的BP
 * 
 */
public class AceHy_kaipiaoinfoUpdateBP {

	public KaipiaoinfoBillVO[] update(KaipiaoinfoBillVO[] bills,
			KaipiaoinfoBillVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<KaipiaoinfoBillVO> bp = new UpdateBPTemplate<KaipiaoinfoBillVO>(
				Hy_kaipiaoinfoPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<KaipiaoinfoBillVO> processer) {
		// TODO 后规则
		IRule<KaipiaoinfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("HK33");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<KaipiaoinfoBillVO> processer) {
		// TODO 前规则
		IRule<KaipiaoinfoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<KaipiaoinfoBillVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("HK33");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
