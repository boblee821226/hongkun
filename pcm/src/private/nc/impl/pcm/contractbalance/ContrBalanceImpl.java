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
 * ���㵥��̨ʵ����
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
		// �������㵥 �� ������У�鲢 ���º�ͬ��־λ
		action.addBeforeRule(new SaveBeforeRule());
		// �ʱ��� ����Э�� ��У��
		// action.addBeforeRule(new QualMnyPaymentRule());
		// ��дԤ��
		// action.addBeforeRule(new SaveBalance4BudgetRule());
		// ��������ʱ ����Ԥ���� �� ִ������д�� ���� modified by rockzhu 2014��12��24�� �滻Ϊ����rule
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractBalanceBillVO>(
				BudgetCtrlPoint.save_control));
		// ����ҵ��������Ϣ
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
		// �ʱ��� ����Э�� ��У��
		// action.addBeforeRule(new QualMnyPaymentRule());
		// ��дԤ��
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
		// ��дԤ��
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
		// �ʱ��𸶿�Э���У��
		// action.addBeforeRule(new QualMnyPaymentRule());
		// Ԥ����
		//action.addAfterRule(new ApproveBalance4BudgetRule());
		// modified by rockzhu 2014��12��26�� �滻Ϊ��������
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractBalanceBillVO>(
						BudgetCtrlPoint.check_control));
		// ���㵥����ǰ����ͬ�ϵ����У��
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
		// ȡ������ʱ��У���ʱ���ֿ�
		action.addBeforeRule(new UnApproveForDeductRule());
		// ȡ������ʱ��У�������Ƿ����ɸ�������
		action.addBeforeRule(new CheckPayApplyRule<ContractBalanceBillVO>());
		// ���㵥����ǰ���޸ĺ�ͬ
		action.addBeforeRule(new ContrOprBeforeUnApprove());
		// ���ڱ�֤ ��һ�� ����ʱ ��дԤ�㣬�����ڵ���״̬�ı�ǰ ���ô˹���
	//	action.addBeforeRule(new UNApproveBalance4BudgetRule());
		// �滻Ϊ����rule
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<ContractBalanceBillVO>(
						BudgetCtrlPoint.check_control));

		// ɾ����Ӧ��Ӧ����
		action.addBeforeRule(new DeletePayableBillRule<ContractBalanceBillVO>());
		
		action.addBeforeRule(new UnApproveDelFipMesRule<ContractBalanceBillVO>(ContractBalanceHeadVO.CURR_BAL_DIF_MNY));
	
		/**
		 * HK 2020��3��12��11:43:29
		 * ȡ������ʱ��ɾ����ʽ���ɵĸ��
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
