package nc.impl.pcm.contractbalance;

import nc.bs.pcm.contract.bp.rule.CheckPayApplyRule;
import nc.bs.pcm.contract.bp.rule.ValidateContrBeginRule;
import nc.bs.pcm.contractbalance.bp.rule.ApproveAfterRule;
import nc.bs.pcm.contractbalance.bp.rule.ContrOprBeforeUnApprove;
import nc.bs.pcm.contractbalance.bp.rule.DeleteAfterRule;
import nc.bs.pcm.contractbalance.bp.rule.SaveBeforeRule;
import nc.bs.pcm.contractbalance.bp.rule.UnApproveForDeductRule;
import nc.bs.pcm.contractbalance.bp.rule.UpdateBeforeRule;
import nc.bs.pmpub.rule.AppendBusiTypeBeforeRule;
import nc.bs.pmpub.rule.Approve4SendFipRule;
import nc.bs.pmpub.rule.UnApproveDelFipMesRule;
import nc.impl.pcm.billrule.DeletePayBillRule;
import nc.impl.pcm.billrule.DeletePayableBillRule;
import nc.impl.pmpub.servicebase.BillBaseImpl;
import nc.impl.pmpub.servicebase.action.ApproveAction;
import nc.impl.pmpub.servicebase.action.DeleteAction;
import nc.impl.pmpub.servicebase.action.InsertAction;
import nc.impl.pmpub.servicebase.action.UnApproveAction;
import nc.impl.pmpub.servicebase.action.UpdateAction;
import nc.itf.pbm.commonrule.BillDelOrUnApprove4BudgetRule;
import nc.itf.pbm.commonrule.BillSaveOrApprove4BudgetRule;
import nc.itf.pbm.commonrule.BillUpdate4BudgetRule;
import nc.itf.pcm.contractbalance.prv.IContrBalance;
import nc.vo.pbm.budgetctrl.BudgetCtrlPoint;
import nc.vo.pcm.contractbalance.ContractBalanceBillVO;
import nc.vo.pcm.contractbalance.ContractBalanceHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;

/**
 * 结算单后台实现类
 * 
 * @Author qiaohc
 * @Date 2012-9-6
 * @Version NC63
 * 
 */
public class ContrBalanceImpl extends BillBaseImpl<ContractBalanceBillVO>
		implements IContrBalance {

	@Override
	public ContractBalanceBillVO[] insertBala(ContractBalanceBillVO[] billVOs)
			throws BusinessException {
		return this.insert(billVOs);
	}

	@Override
	protected void initInsertAction(InsertAction<ContractBalanceBillVO> action) {
		super.initInsertAction(action);
		action.addBeforeRule(new ValidateContrBeginRule<ContractBalanceBillVO>());
		// 新增结算单 后 ，进行校验并 更新合同标志位
		action.addBeforeRule(new SaveBeforeRule());
		// 质保金 付款协议 的校验
		// action.addBeforeRule(new QualMnyPaymentRule());
		// 回写预算
		// action.addBeforeRule(new SaveBalance4BudgetRule());
		// 新增保存时 进行预算检查 和 执行数回写的 规则 modified by rockzhu 2014年12月24日 替换为公共rule
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractBalanceBillVO>(
				BudgetCtrlPoint.save_control));
		// 设置业务流程信息
		action.addBeforeRule(new AppendBusiTypeBeforeRule<ContractBalanceBillVO>());
	}

	@Override
	public ContractBalanceBillVO[] updateBala(ContractBalanceBillVO[] billVOs,
			ContractBalanceBillVO[] originBillVOs) throws BusinessException {
		return this.update(billVOs, originBillVOs);
	}

	@Override
	protected void initUpdateAction(UpdateAction<ContractBalanceBillVO> action) {
		super.initUpdateAction(action);
		action.addBeforeRule(new ValidateContrBeginRule<ContractBalanceBillVO>());
		action.addBeforeRule(new UpdateBeforeRule());
		// 质保金 付款协议 的校验
		// action.addBeforeRule(new QualMnyPaymentRule());
		// 回写预算
	//	action.addBeforeRule(new UpdatBalance4BudgetRule());
		action.addBeforeRule(new BillUpdate4BudgetRule<ContractBalanceBillVO>(
				BudgetCtrlPoint.save_control));
	}

	@Override
	public void deleteBala(ContractBalanceBillVO[] billVOs)
			throws BusinessException {
		this.delete(billVOs);
	}

	@Override
	protected void initDeleteAction(DeleteAction<ContractBalanceBillVO> action) {
		super.initDeleteAction(action);
		action.addAfterRule(new DeleteAfterRule());
		// 回写预算
		//action.addAfterRule(new DeleteBalance4BudgetRule());
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<ContractBalanceBillVO>(
				BudgetCtrlPoint.save_control));
	}

	@Override
	public Object approveBala(ContractBalanceBillVO[] billVOs,
			PfParameterVO pfParamVO) throws BusinessException {
		return this.approve(billVOs, pfParamVO);
	}

	@Override
	protected void initApproveAction(ApproveAction<ContractBalanceBillVO> action) {
		super.initApproveAction(action);
		// 质保金付款协议的校验
		// action.addBeforeRule(new QualMnyPaymentRule());
		// 预算检查
		//action.addAfterRule(new ApproveBalance4BudgetRule());
		// modified by rockzhu 2014年12月26日 替换为公共规则
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractBalanceBillVO>(
						BudgetCtrlPoint.check_control));
		// 结算单审批前，合同上的相关校验
		action.addAfterRule(new ApproveAfterRule());
		action.addAfterRule(new Approve4SendFipRule<ContractBalanceBillVO>(ContractBalanceHeadVO.CURR_BAL_DIF_MNY));

	}

	@Override
	public ContractBalanceBillVO[] unapproveBala(
			ContractBalanceBillVO[] billVOs, PfParameterVO pfParamVO)
			throws BusinessException {
		return this.unApprove(billVOs, pfParamVO);
	}

	@Override
	protected void initUnApproveAction(
			UnApproveAction<ContractBalanceBillVO> action) {
		super.initUnApproveAction(action);
		// 取消审批时，校验质保金抵扣
		action.addBeforeRule(new UnApproveForDeductRule());
		// 取消审批时，校验下游是否生成付款申请
		action.addBeforeRule(new CheckPayApplyRule<ContractBalanceBillVO>());
		// 结算单弃审前，修改合同
		action.addBeforeRule(new ContrOprBeforeUnApprove());
		// 由于保证 第一次 弃审时 回写预算，所以在单据状态改变前 调用此规则
	//	action.addBeforeRule(new UNApproveBalance4BudgetRule());
		// 替换为公共rule
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<ContractBalanceBillVO>(
						BudgetCtrlPoint.check_control));

		// 删除对应的应付单
		action.addBeforeRule(new DeletePayableBillRule<ContractBalanceBillVO>());
		
		action.addBeforeRule(new UnApproveDelFipMesRule<ContractBalanceBillVO>(ContractBalanceHeadVO.CURR_BAL_DIF_MNY));
	
		/**
		 * HK 2020年3月12日11:43:29
		 * 取消审批时，删除推式生成的付款单
		 * DeletePayBillRule
		 */
		action.addBeforeRule(new DeletePayBillRule<ContractBalanceBillVO>());
		/***END***/
	}

	@Override
	public ContractBalanceBillVO[] commitBala(ContractBalanceBillVO[] billVOs)
			throws BusinessException {
		// TODO Auto-generated method stub
		return this.commit(billVOs);
	}

	@Override
	public ContractBalanceBillVO[] unCommitBala(
			ContractBalanceBillVO[] billVOs, PfParameterVO pfParamVO)
			throws BusinessException {
		// TODO Auto-generated method stub
		return this.unCommit(billVOs, pfParamVO);
	}
}
