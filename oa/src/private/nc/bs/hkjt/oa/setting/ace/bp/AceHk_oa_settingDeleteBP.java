package nc.bs.hkjt.oa.setting.ace.bp;

import nc.bs.hkjt.oa.setting.plugin.bpplugin.Hk_oa_settingPluginPoint;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceHk_oa_settingDeleteBP {

	public void delete(OaSettingBillVO[] bills) {

		DeleteBPTemplate<OaSettingBillVO> bp = new DeleteBPTemplate<OaSettingBillVO>(
				Hk_oa_settingPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<OaSettingBillVO> processer) {
		// TODO 前规则
		IRule<OaSettingBillVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<OaSettingBillVO> processer) {
		// TODO 后规则

	}
}
