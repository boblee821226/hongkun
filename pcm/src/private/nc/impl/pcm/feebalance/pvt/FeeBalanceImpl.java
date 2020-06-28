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
		/**
		 * HK 2020年6月26日14:32:46
		 * 新增保存前 判断，红冲金额 不能超过原单。
		 */
		BaseDAO dao = new BaseDAO();
		for (FeeBalanceBillVO billVO : billVOs) {
			for (ISuperVO childvo : billVO.getChildren(billVO.getMetaData().getChildren()[0])) {
				String bid = childvo.getAttributeValue("def20").toString();
				UFDouble mny = PuPubVO.getUFDouble_NullAsZero(childvo.getAttributeValue("money"));
				if (mny.compareTo(UFDouble.ZERO_DBL) > 0) {
					// 若 金额大于0  则报错。 既然是红冲，必须小于0
					throw new BusinessException("红冲金额，必须为负数。");
				}
				// 根据bid 去查询已经存在的 红冲单 以及 原单。汇总出剩余金额。
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
				UFDouble lishi = UFDouble.ZERO_DBL; // 历史余额
				ArrayList list = (ArrayList) dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				if (list != null && !list.isEmpty()) {
					lishi = PuPubVO.getUFDouble_NullAsZero(((Object[])list.get(0))[0]);
				}
				// mny + lishi < 0 , 说明 红冲金额超过 原单，则报错。
				UFDouble shenghyu = mny.add(lishi);
				if (shenghyu.compareTo(UFDouble.ZERO_DBL) < 0) {
					throw new BusinessException("本次红冲金额，超过原单金额。");
				}
			}
		}
		/***END***/
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
		/**
		 * HK 2020年6月26日14:32:46
		 * 修改保存前 判断，红冲金额 不能超过原单。
		 */
		BaseDAO dao = new BaseDAO();
		for (FeeBalanceBillVO billVO : billVOs) {
			for (ISuperVO childvo : billVO.getChildren(billVO.getMetaData().getChildren()[0])) {
				String bid = childvo.getAttributeValue("def20").toString();
				String benId = childvo.getAttributeValue("pk_feebalance_b").toString();
				UFDouble mny = PuPubVO.getUFDouble_NullAsZero(childvo.getAttributeValue("money"));
				if (mny.compareTo(UFDouble.ZERO_DBL) > 0) {
					// 若 金额大于0  则报错。 既然是红冲，必须小于0
					throw new BusinessException("红冲金额，必须为负数。");
				}
				// 根据bid 去查询已经存在的 红冲单 以及 原单。汇总出剩余金额。
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
							.append(" and fb.pk_feebalance_b <> '").append(benId).append("' ") // 不取本单
							;
				UFDouble lishi = UFDouble.ZERO_DBL; // 历史余额
				ArrayList list = (ArrayList) dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				if (list != null && !list.isEmpty()) {
					lishi = PuPubVO.getUFDouble_NullAsZero(((Object[])list.get(0))[0]);
				}
				// mny + lishi < 0 , 说明 红冲金额超过 原单，则报错。
				UFDouble shenghyu = mny.add(lishi);
				if (shenghyu.compareTo(UFDouble.ZERO_DBL) < 0) {
					throw new BusinessException("本次红冲金额，超过原单金额。");
				}
			}
		}
		/***END***/
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
