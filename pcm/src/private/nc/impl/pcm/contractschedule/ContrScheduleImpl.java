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
 * ��ͬ���ȿ�ĺ�̨ʵ��
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
		 // ����ҵ��������Ϣ
	    action.addBeforeRule(new AppendBusiTypeBeforeRule<ContractScheduleBillVO>());
		action.addBeforeRule(new ContrOprBeforeSave(ContrOperTypeEnum.OPER_ADD));
		action.addAfterRule(new BillCodeCheckRule<ContractScheduleBillVO>());
		// ��дԤ��
		//action.addAfterRule(new SaveContrSche4BudgetRule());
		// ��������ʱ ����Ԥ���� �� ִ������д�� ���� modified by rockzhu 2014��12��24�� �滻Ϊ����rule
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractScheduleBillVO>(
						BudgetCtrlPoint.save_control));
		//���ȿ����У�飬���ȸ�����Ʊ���
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
		// ��дԤ��
		//action.addAfterRule(new UpdateContrSche4BudgetRule());
	//	action.addAfterRule(new BillUpdate4BudgetRule<ContractScheduleBillVO>(
	//			BudgetCtrlPoint.save_control));
		action.addBeforeRule(new BillUpdate4BudgetBeforeRule<ContractScheduleBillVO>(
				BudgetCtrlPoint.save_control));
		action.addAfterRule(new BillUpdate4BudgetAfterRule<ContractScheduleBillVO>(
				BudgetCtrlPoint.save_control));
		//���ȿ����У�飬���ȸ�����Ʊ���
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
		// ɾ���󣬶Ժ�ͬ���в���
		action.addBeforeRule(new ContrOprAfterDel());
	}

	@Override
	public Object approveSchedule(ContractScheduleBillVO[] billVOs, PfParameterVO pfParamVO) throws BusinessException {
		
		return this.approve(billVOs, pfParamVO);
	}

	@Override
	protected void initApproveAction(ApproveAction<ContractScheduleBillVO> action) {
		super.initApproveAction(action);
		// ���ͨ��ǰ����Ҫ�Ժ�ͬ��У��
		action.addBeforeRule(new ContrOprBeforeApprove());
		
		//����ͨ���󣬸��½��ȿ�����������
		action.addAfterRule(new UpdateContrScheOprnumRule());
		// ����ͨ��ʱ���Ƚ��� Ԥ���� �͸��� Ԥ�㣬�ٻ�д��ͬ
		//action.addAfterRule(new ApproveContrSche4BudgetRule());
		// ���ݱ���ǰ�����Ԥ�� modified by rockzhu2014��12��24�� �滻Ϊ����rule
		action.addAfterRule(new BillSaveOrApprove4BudgetRule<ContractScheduleBillVO>(
						BudgetCtrlPoint.check_control));
		// ��ˣ���д��ͬ
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
		//ȡ������ʱ��У�������Ƿ����ɸ�������
		action.addBeforeRule(new CheckPayApplyRule<ContractScheduleBillVO>());
		super.initUnApproveAction(action);
		action.addBeforeRule(new ContrOprBeforeUnApprove());
	//	action.addBeforeRule(new UNApproveContrSche4BudgetRule());
		// modified by rockzhu 2014��12��25�� ����ʱ������Ԥ�㹫������
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<ContractScheduleBillVO>(
				BudgetCtrlPoint.check_control));
		// ɾ����Ӧ��Ӧ����
		action.addBeforeRule(new DeletePayableBillRule<ContractScheduleBillVO>());
		action.addBeforeRule(new UnApproveDelFipMesRule<ContractScheduleBillVO>(ContractScheduleHeadVO.CURR_SHOU_PAY));
		/**
		 * HK 2020��3��12��11:43:29
		 * ȡ������ʱ��ɾ����ʽ���ɵĸ��
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
