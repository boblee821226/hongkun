package nc.impl.pcm.feebalance.pvt;

import nc.bs.pcm.contract.bp.rule.CheckPayApplyRule;
import nc.bs.pmpub.rule.AppendBusiTypeBeforeRule;
import nc.bs.pmpub.rule.Approve4SendFipRule;
import nc.bs.pmpub.rule.UnApproveDelFipMesRule;
import nc.impl.pcm.billrule.DeletePayBillRule;
import nc.impl.pcm.billrule.DeletePayableBillRule;
import nc.impl.pcm.feebalance.rule.FeeBalanceCommitRule;
import nc.impl.pcm.feebalance.rule.InitMutiCurrTypeBeforeRule;
import nc.impl.pmpub.servicebase.BillBaseImpl;
import nc.impl.pmpub.servicebase.action.ApproveAction;
import nc.impl.pmpub.servicebase.action.CommitAction;
import nc.impl.pmpub.servicebase.action.InsertAction;
import nc.impl.pmpub.servicebase.action.UnApproveAction;
import nc.impl.pmpub.servicebase.action.UpdateAction;
import nc.itf.pbm.commonrule.BillDelOrUnApprove4BudgetRule;
import nc.itf.pbm.commonrule.BillSaveOrApprove4BudgetRule;
import nc.itf.pcm.feebalance.prv.IFeeBalance;
import nc.vo.pbm.budgetctrl.BudgetCtrlPoint;
import nc.vo.pcm.feebalance.FeeBalanceBillVO;
import nc.vo.pcm.feebalance.FeeBalanceBodyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;

/**
 * 费用结算单 后台实现类
 * 
 * @author minxx1
 * 
 */
public class FeeBalanceImpl extends BillBaseImpl<FeeBalanceBillVO> implements
		IFeeBalance {

	@Override
	public FeeBalanceBillVO[] insertFeeBalance(FeeBalanceBillVO[] billVOs)
			throws BusinessException {
		return this.insert(billVOs);
	}

	@Override
	protected void initInsertAction(InsertAction<FeeBalanceBillVO> action) {
		super.initInsertAction(action);
		// 新增保存前设置 集团币&全局币
		action.addBeforeRule(new InitMutiCurrTypeBeforeRule<FeeBalanceBillVO>());
		// 设置业务流程信息
		action.addBeforeRule(new AppendBusiTypeBeforeRule<FeeBalanceBillVO>());
	}

	@Override
	public FeeBalanceBillVO[] updateFeeBalance(FeeBalanceBillVO[] billVOs,
			FeeBalanceBillVO[] originBillVOs) throws BusinessException {
		return this.update(billVOs, originBillVOs);
	}

	@Override
	protected void initUpdateAction(UpdateAction<FeeBalanceBillVO> action) {
		super.initUpdateAction(action);
		// 修改保存前设置 集团币&全局币
		action.addBeforeRule(new InitMutiCurrTypeBeforeRule<FeeBalanceBillVO>());
	}

	@Override
	public void deleteFeeBalance(FeeBalanceBillVO[] billVOs)
			throws BusinessException {
		this.delete(billVOs);
	}

	@Override
	public Object approveFeeBalance(FeeBalanceBillVO[] billVOs,
			PfParameterVO pfParamVO) throws BusinessException {
		return this.approve(billVOs, pfParamVO);
	}

	@Override
	protected void initApproveAction(ApproveAction<FeeBalanceBillVO> action) {
		super.initApproveAction(action);
		// 审批通过以后才回写预算
		// action.addAfterRule(new FeeBalanceApproveRule());
		// 检查预算 modified by rockzhu2014年12月24日 替换为公共rule
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<FeeBalanceBillVO>(
				BudgetCtrlPoint.check_control, true));
		action.addAfterRule(new Approve4SendFipRule<FeeBalanceBillVO>(FeeBalanceBodyVO.MONEY));
		
	}

	@Override
	public FeeBalanceBillVO[] unApproveFeeBalance(FeeBalanceBillVO[] billVOs,
			PfParameterVO pfParamVO) throws BusinessException {
		return this.unApprove(billVOs, pfParamVO);
	}

	@Override
	protected void initUnApproveAction(UnApproveAction<FeeBalanceBillVO> action) {
		super.initUnApproveAction(action);
		// action.addBeforeRule(new FeeBalanceUnApproveRule());
		// modified by rockzhu 2014年12月25日 弃审时，调用预算公共规则
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<FeeBalanceBillVO>(
				BudgetCtrlPoint.check_control, true));
		// 取消审批时，校验下游是否生成付款申请
		action.addBeforeRule(new CheckPayApplyRule<FeeBalanceBillVO>());
		// 取消审批时，删除推式生成的应付单
		action.addBeforeRule(new DeletePayableBillRule<FeeBalanceBillVO>());
		action.addBeforeRule(new UnApproveDelFipMesRule<FeeBalanceBillVO>(FeeBalanceBodyVO.MONEY));
		/**
		 * HK 2020年3月12日11:43:29
		 * 取消审批时，删除推式生成的付款单
		 * DeletePayBillRule
		 */
		action.addBeforeRule(new DeletePayBillRule<FeeBalanceBillVO>());
		/***END***/
	}

	@Override
	protected void initCommitAction(CommitAction<FeeBalanceBillVO> action) {

		super.initCommitAction(action);
		// 提交判断：质保金不为0时，“质保期到期日期”不能为空
		action.addBeforeRule(new FeeBalanceCommitRule());
	}

	@Override
	public FeeBalanceBillVO[] commitFeeBalance(FeeBalanceBillVO[] billVOs)
			throws BusinessException {
		return this.commit(billVOs);
	}

	@Override
	public FeeBalanceBillVO[] unCommitFeeBalance(FeeBalanceBillVO[] billVOs,
			PfParameterVO pfParamVO) throws BusinessException {
		return this.unCommit(billVOs, pfParamVO);
	}

}
