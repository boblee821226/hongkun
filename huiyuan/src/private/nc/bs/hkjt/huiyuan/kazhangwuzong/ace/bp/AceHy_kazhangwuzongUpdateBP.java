package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.bs.hkjt.huiyuan.kazhangwuzong.plugin.bpplugin.Hy_kazhangwuzongPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;

/**
 * 修改保存的BP
 * 
 */
public class AceHy_kazhangwuzongUpdateBP {

	public KazhangwuzongBillVO[] update(KazhangwuzongBillVO[] bills,
			KazhangwuzongBillVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<KazhangwuzongBillVO> bp = new UpdateBPTemplate<KazhangwuzongBillVO>(
				Hy_kazhangwuzongPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<KazhangwuzongBillVO> processer) {
		// TODO 后规则
		IRule<KazhangwuzongBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("HK27");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

	}

	private void addBeforeRule(CompareAroundProcesser<KazhangwuzongBillVO> processer) {
		// TODO 前规则
		IRule<KazhangwuzongBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<KazhangwuzongBillVO> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("HK27");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
	}

}
