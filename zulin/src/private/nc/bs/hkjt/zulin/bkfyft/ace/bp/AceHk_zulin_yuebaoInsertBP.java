package nc.bs.hkjt.zulin.bkfyft.ace.bp;

import nc.bs.hkjt.zulin.bkfyft.plugin.bpplugin.Hk_zulin_yuebaoPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;

/**
 * ��׼��������BP
 */
public class AceHk_zulin_yuebaoInsertBP {

	public YuebaoBillVO[] insert(YuebaoBillVO[] bills) {

		String vbilltypecode = bills[0].getParentVO().getVbilltypecode();
		
		InsertBPTemplate<YuebaoBillVO> bp = new InsertBPTemplate<YuebaoBillVO>(
				Hk_zulin_yuebaoPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser(),vbilltypecode);
		this.addAfterRule(bp.getAroundProcesser(),vbilltypecode);
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<YuebaoBillVO> processor, String vbilltypecode) {
		// TODO ���������
		IRule<YuebaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype(vbilltypecode);
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
	private void addBeforeRule(AroundProcesser<YuebaoBillVO> processer, String vbilltypecode) {
		// TODO ����ǰ����
		IRule<YuebaoBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype(vbilltypecode);
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillcode");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
	}
}
