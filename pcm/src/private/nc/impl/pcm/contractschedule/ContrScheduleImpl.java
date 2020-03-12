package nc.impl.pcm.contractschedule;

import nc.bs.pcm.contract.bp.rule.CheckPayApplyRule;
import nc.bs.pcm.contract.bp.rule.ValidateContrBeginRule;
import nc.bs.pcm.contractschedule.bp.rule.ContrOprAfterApprove;
import nc.bs.pcm.contractschedule.bp.rule.ContrOprAfterDel;
import nc.bs.pcm.contractschedule.bp.rule.ContrOprBeforeApprove;
import nc.bs.pcm.contractschedule.bp.rule.ContrOprBeforeSave;
import nc.bs.pcm.contractschedule.bp.rule.ContrOprBeforeUnApprove;
import nc.bs.pcm.contractschedule.bp.rule.SaveCheckSche_mny_propRule;
import nc.bs.pcm.contractschedule.bp.rule.UpdateContrScheOprnumRule;
import nc.bs.pmpub.rule.AppendBusiTypeBeforeRule;
import nc.bs.pmpub.rule.Approve4SendFipRule;
import nc.bs.pmpub.rule.UnApproveDelFipMesRule;
import nc.impl.pcm.billrule.DeletePayBillRule;
import nc.impl.pcm.billrule.DeletePayableBillRule;
import nc.impl.pm.billrule.BillCodeCheckRule;
import nc.impl.pmpub.servicebase.BillBaseImpl;
import nc.impl.pmpub.servicebase.action.ApproveAction;
import nc.impl.pmpub.servicebase.action.DeleteAction;
import nc.impl.pmpub.servicebase.action.InsertAction;
import nc.impl.pmpub.servicebase.action.UnApproveAction;
import nc.impl.pmpub.servicebase.action.UpdateAction;
import nc.itf.pbm.commonrule.BillDelOrUnApprove4BudgetRule;
import nc.itf.pbm.commonrule.BillSaveOrApprove4BudgetRule;
import nc.itf.pbm.commonrule.BillUpdate4BudgetAfterRule;
import nc.itf.pbm.commonrule.BillUpdate4BudgetBeforeRule;
import nc.itf.pcm.contract.pub.ContrOperTypeEnum;
import nc.itf.pcm.contractschedule.prv.IContrSchedule;
import nc.vo.pbm.budgetctrl.BudgetCtrlPoint;
import nc.vo.pcm.contractschedule.ContractScheduleBillVO;
import nc.vo.pcm.contractschedule.ContractScheduleHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;

/**
 * 合同进度款的后台实现
 * 
 * @Author qiaohc
 * @Date 2012-9-6
 * @Version NC63
 * 
 */
public class ContrScheduleImpl extends BillBaseImpl<ContractScheduleBillVO> implements IContrSchedule {

	@Override
	public ContractScheduleBillVO[] insertSchedule(ContractScheduleBillVO[] billVOs) throws BusinessException {
		return insert(billVOs);
	}

	@Override
	protected void initInsertAction(InsertAction<ContractScheduleBillVO> action) {
		super.initInsertAction(action);
		action.addBeforeRule(new ValidateContrBeginRule<ContractScheduleBillVO>());
		 // 设置业务流程信息
	    action.addBeforeRule(new AppendBusiTypeBeforeRule<ContractScheduleBillVO>());
		action.addBeforeRule(new ContrOprBeforeSave(ContrOperTypeEnum.OPER_ADD));
		action.addAfterRule(new BillCodeCheckRule<ContractScheduleBillVO>());
		// 回写预算
		//action.addAfterRule(new SaveContrSche4BudgetRule());
		// 新增保存时 进行预算检查 和 执行数回写的 规则 modified by rockzhu 2014年12月24日 替换为公共rule
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractScheduleBillVO>(
						BudgetCtrlPoint.save_control));
		//进度款单保存校验，进度付款控制比例
		action.addBeforeRule(new SaveCheckSche_mny_propRule());
	}

	@Override
	public ContractScheduleBillVO[] updateSchedule(ContractScheduleBillVO[] billVOs,
			ContractScheduleBillVO[] originBillVOs) throws BusinessException {
		return this.update(billVOs, originBillVOs);
	}

	@Override
	protected void initUpdateAction(UpdateAction<ContractScheduleBillVO> action) {
		super.initUpdateAction(action);
		action.addBeforeRule(new ValidateContrBeginRule<ContractScheduleBillVO>());
		action.addBeforeRule(new ContrOprBeforeSave(ContrOperTypeEnum.OPER_UPDATE));
		// 回写预算
		//action.addAfterRule(new UpdateContrSche4BudgetRule());
	//	action.addAfterRule(new BillUpdate4BudgetRule<ContractScheduleBillVO>(
	//			BudgetCtrlPoint.save_control));
		action.addBeforeRule(new BillUpdate4BudgetBeforeRule<ContractScheduleBillVO>(
				BudgetCtrlPoint.save_control));
		action.addAfterRule(new BillUpdate4BudgetAfterRule<ContractScheduleBillVO>(
				BudgetCtrlPoint.save_control));
		//进度款单保存校验，进度付款控制比例
		action.addBeforeRule(new SaveCheckSche_mny_propRule());
	}

	@Override
	public void deleteSchedule(ContractScheduleBillVO[] billVOs) throws BusinessException {
		this.delete(billVOs);
	}

	@Override
	protected void initDeleteAction(DeleteAction<ContractScheduleBillVO> action) {
		super.initDeleteAction(action);
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<ContractScheduleBillVO>(
				BudgetCtrlPoint.save_control));
		// 删除后，对合同进行操作
		action.addBeforeRule(new ContrOprAfterDel());
	}

	@Override
	public Object approveSchedule(ContractScheduleBillVO[] billVOs, PfParameterVO pfParamVO) throws BusinessException {
		
		return this.approve(billVOs, pfParamVO);
	}

	@Override
	protected void initApproveAction(ApproveAction<ContractScheduleBillVO> action) {
		super.initApproveAction(action);
		// 审核通过前，需要对合同的校验
		action.addBeforeRule(new ContrOprBeforeApprove());
		
		//审批通过后，更新进度款表体操作次数
		action.addAfterRule(new UpdateContrScheOprnumRule());
		// 审批通过时，先进行 预算检查 和更新 预算，再回写合同
		//action.addAfterRule(new ApproveContrSche4BudgetRule());
		// 单据保存前，检查预算 modified by rockzhu2014年12月24日 替换为公共rule
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractScheduleBillVO>(
						BudgetCtrlPoint.check_control));
		// 审核，回写合同
		action.addAfterRule(new ContrOprAfterApprove());
		action.addAfterRule(new Approve4SendFipRule<ContractScheduleBillVO>(ContractScheduleHeadVO.CURR_SHOU_PAY));

	}

	@Override
	public ContractScheduleBillVO[] unapproveSchedule(ContractScheduleBillVO[] billVOs, PfParameterVO pfParamVO)
			throws BusinessException {
		return this.unApprove(billVOs, pfParamVO);
	}

	@Override
	protected void initUnApproveAction(UnApproveAction<ContractScheduleBillVO> action) {
		//取消审批时，校验下游是否生成付款申请
		action.addBeforeRule(new CheckPayApplyRule<ContractScheduleBillVO>());
		super.initUnApproveAction(action);
		action.addBeforeRule(new ContrOprBeforeUnApprove());
	//	action.addBeforeRule(new UNApproveContrSche4BudgetRule());
		// modified by rockzhu 2014年12月25日 弃审时，调用预算公共规则
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<ContractScheduleBillVO>(
				BudgetCtrlPoint.check_control));
		// 删除对应的应付单
		action.addBeforeRule(new DeletePayableBillRule<ContractScheduleBillVO>());
		action.addBeforeRule(new UnApproveDelFipMesRule<ContractScheduleBillVO>(ContractScheduleHeadVO.CURR_SHOU_PAY));
		/**
		 * HK 2020年3月12日11:43:29
		 * 取消审批时，删除推式生成的付款单
		 * DeletePayBillRule
		 */
		action.addBeforeRule(new DeletePayBillRule<ContractScheduleBillVO>());
		/***END***/
	}

	@Override
	public ContractScheduleBillVO[] commitSchedule(ContractScheduleBillVO[] billVOs) throws BusinessException {
		return this.commit(billVOs);
	}

	@Override
	public ContractScheduleBillVO[] unCommitSchedule(ContractScheduleBillVO[] billVOs, PfParameterVO pfParamVO)
			throws BusinessException {
		return this.unCommit(billVOs, pfParamVO);
	}

}
