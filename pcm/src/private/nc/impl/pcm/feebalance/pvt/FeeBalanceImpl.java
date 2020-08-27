package nc.impl.pcm.feebalance.pvt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
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
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pbm.budgetctrl.BudgetCtrlPoint;
import nc.vo.pcm.feebalance.FeeBalanceBillVO;
import nc.vo.pcm.feebalance.FeeBalanceBodyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFDouble;

/**
 * ���ý��㵥 ��̨ʵ����
 * 
 * @author minxx1
 * 
 */
public class FeeBalanceImpl extends BillBaseImpl<FeeBalanceBillVO> implements
		IFeeBalance {

	@Override
	public FeeBalanceBillVO[] insertFeeBalance(FeeBalanceBillVO[] billVOs)
			throws BusinessException {
		/**
		 * HK 2020��6��26��14:32:46
		 * ��������ǰ �жϣ������ ���ܳ���ԭ����
		 */
		BaseDAO dao = new BaseDAO();
		for (FeeBalanceBillVO billVO : billVOs) {
			for (ISuperVO childvo : billVO.getChildren(billVO.getMetaData().getChildren()[0])) {
//				System.out.print("======");
				String bid = PuPubVO.getString_TrimZeroLenAsNull(childvo.getAttributeValue("def20"));
//				System.out.print(bid);
				if (bid == null) continue;	// ������Ǻ��ģ��򲻽���������ж�
				UFDouble mny = PuPubVO.getUFDouble_NullAsZero(childvo.getAttributeValue("money"));
				if (mny.compareTo(UFDouble.ZERO_DBL) > 0) {
					// �� ������0  �򱨴� ��Ȼ�Ǻ�壬����С��0
					throw new BusinessException("��������Ϊ������");
				}
				// ����bid ȥ��ѯ�Ѿ����ڵ� ��嵥 �Լ� ԭ�������ܳ�ʣ���
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" sum(fb.money) ")
							.append(" from pm_feebalance_b fb ")
							.append(" where fb.dr = 0 ")
							.append(" and ( ")
							.append(" 	fb.def20 = '").append(bid).append("' ")
							.append(" or ")
							.append(" 	fb.pk_feebalance_b = '").append(bid).append("' ")
							.append(" ) ")
							;
				UFDouble lishi = UFDouble.ZERO_DBL; // ��ʷ���
				ArrayList list = (ArrayList) dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				if (list != null && !list.isEmpty()) {
					lishi = PuPubVO.getUFDouble_NullAsZero(((Object[])list.get(0))[0]);
				}
				// mny + lishi < 0 , ˵�� ������ ԭ�����򱨴�
				UFDouble shenghyu = mny.add(lishi);
				if (shenghyu.compareTo(UFDouble.ZERO_DBL) < 0) {
					throw new BusinessException("���κ�������ԭ����");
				}
			}
		}
		/***END***/
		return this.insert(billVOs);
	}

	@Override
	protected void initInsertAction(InsertAction<FeeBalanceBillVO> action) {
		super.initInsertAction(action);
		// ��������ǰ���� ���ű�&ȫ�ֱ�
		action.addBeforeRule(new InitMutiCurrTypeBeforeRule<FeeBalanceBillVO>());
		// ����ҵ��������Ϣ
		action.addBeforeRule(new AppendBusiTypeBeforeRule<FeeBalanceBillVO>());
	}

	@Override
	public FeeBalanceBillVO[] updateFeeBalance(FeeBalanceBillVO[] billVOs,
			FeeBalanceBillVO[] originBillVOs) throws BusinessException {
		/**
		 * HK 2020��6��26��14:32:46
		 * �޸ı���ǰ �жϣ������ ���ܳ���ԭ����
		 */
		BaseDAO dao = new BaseDAO();
		for (FeeBalanceBillVO billVO : billVOs) {
			for (ISuperVO childvo : billVO.getChildren(billVO.getMetaData().getChildren()[0])) {
				String bid = PuPubVO.getString_TrimZeroLenAsNull(childvo.getAttributeValue("def20"));
				if (bid == null) continue;	// ������Ǻ��ģ��򲻽���������ж�
				String benId = childvo.getAttributeValue("pk_feebalance_b").toString();
				UFDouble mny = PuPubVO.getUFDouble_NullAsZero(childvo.getAttributeValue("money"));
				if (mny.compareTo(UFDouble.ZERO_DBL) > 0) {
					// �� ������0  �򱨴� ��Ȼ�Ǻ�壬����С��0
					throw new BusinessException("��������Ϊ������");
				}
				// ����bid ȥ��ѯ�Ѿ����ڵ� ��嵥 �Լ� ԭ�������ܳ�ʣ���
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" sum(fb.money) ")
							.append(" from pm_feebalance_b fb ")
							.append(" where fb.dr = 0 ")
							.append(" and ( ")
							.append(" 	fb.def20 = '").append(bid).append("' ")
							.append(" or ")
							.append(" 	fb.pk_feebalance_b = '").append(bid).append("' ")
							.append(" ) ")
							.append(" and fb.pk_feebalance_b <> '").append(benId).append("' ") // ��ȡ����
							;
				UFDouble lishi = UFDouble.ZERO_DBL; // ��ʷ���
				ArrayList list = (ArrayList) dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				if (list != null && !list.isEmpty()) {
					lishi = PuPubVO.getUFDouble_NullAsZero(((Object[])list.get(0))[0]);
				}
				// mny + lishi < 0 , ˵�� ������ ԭ�����򱨴�
				UFDouble shenghyu = mny.add(lishi);
				if (shenghyu.compareTo(UFDouble.ZERO_DBL) < 0) {
					throw new BusinessException("���κ�������ԭ����");
				}
			}
		}
		/***END***/
		return this.update(billVOs, originBillVOs);
	}

	@Override
	protected void initUpdateAction(UpdateAction<FeeBalanceBillVO> action) {
		super.initUpdateAction(action);
		// �޸ı���ǰ���� ���ű�&ȫ�ֱ�
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
		// ����ͨ���Ժ�Ż�дԤ��
		// action.addAfterRule(new FeeBalanceApproveRule());
		// ���Ԥ�� modified by rockzhu2014��12��24�� �滻Ϊ����rule
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
		// modified by rockzhu 2014��12��25�� ����ʱ������Ԥ�㹫������
		action.addBeforeRule(new BillDelOrUnApprove4BudgetRule<FeeBalanceBillVO>(
				BudgetCtrlPoint.check_control, true));
		// ȡ������ʱ��У�������Ƿ����ɸ�������
		action.addBeforeRule(new CheckPayApplyRule<FeeBalanceBillVO>());
		// ȡ������ʱ��ɾ����ʽ���ɵ�Ӧ����
		action.addBeforeRule(new DeletePayableBillRule<FeeBalanceBillVO>());
		action.addBeforeRule(new UnApproveDelFipMesRule<FeeBalanceBillVO>(FeeBalanceBodyVO.MONEY));
		/**
		 * HK 2020��3��12��11:43:29
		 * ȡ������ʱ��ɾ����ʽ���ɵĸ��
		 * DeletePayBillRule
		 */
		action.addBeforeRule(new DeletePayBillRule<FeeBalanceBillVO>());
		/***END***/
	}

	@Override
	protected void initCommitAction(CommitAction<FeeBalanceBillVO> action) {

		super.initCommitAction(action);
		// �ύ�жϣ��ʱ���Ϊ0ʱ�����ʱ��ڵ������ڡ�����Ϊ��
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
