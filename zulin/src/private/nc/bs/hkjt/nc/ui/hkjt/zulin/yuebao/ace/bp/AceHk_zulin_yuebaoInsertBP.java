package nc.bs.hkjt.nc.ui.hkjt.zulin.yuebao.ace.bp;

import nc.bs.hkjt.nc.ui.hkjt.zulin.yuebao.plugin.bpplugin.Hk_zulin_yuebaoPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;

/**
 * ��׼��������BP
 */
public class AceHk_zulin_yuebaoInsertBP {

	public YuebaoBillVO[] insert(YuebaoBillVO[] bills) {

		InsertBPTemplate<YuebaoBillVO> bp = new InsertBPTemplate<YuebaoBillVO>(
				Hk_zulin_yuebaoPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<YuebaoBillVO> processor) {
		// TODO ���������
		IRule<YuebaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("HK37");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<YuebaoBillVO> processer) {
		// TODO ����ǰ����
		IRule<YuebaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("HK37");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
	}
}
