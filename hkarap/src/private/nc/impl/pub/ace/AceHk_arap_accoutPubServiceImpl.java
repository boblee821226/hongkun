package nc.impl.pub.ace;

import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutInsertBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutUpdateBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutDeleteBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutSendApproveBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutUnSendApproveBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutApproveBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_arap_accoutPubServiceImpl {
	// 新增
	public AccountBillVO[] pubinsertBills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AccountBillVO> transferTool = new BillTransferTool<AccountBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_arap_accoutInsertBP action = new AceHk_arap_accoutInsertBP();
			AccountBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_arap_accoutDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AccountBillVO[] pubupdateBills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AccountBillVO> transferTool = new BillTransferTool<AccountBillVO>(
					clientFullVOs);
			AceHk_arap_accoutUpdateBP bp = new AceHk_arap_accoutUpdateBP();
			AccountBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AccountBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AccountBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AccountBillVO> query = new BillLazyQuery<AccountBillVO>(
					AccountBillVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AccountBillVO[] pubsendapprovebills(
			AccountBillVO[] clientFullVOs, AccountBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_accoutSendApproveBP bp = new AceHk_arap_accoutSendApproveBP();
		AccountBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AccountBillVO[] pubunsendapprovebills(
			AccountBillVO[] clientFullVOs, AccountBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_accoutUnSendApproveBP bp = new AceHk_arap_accoutUnSendApproveBP();
		AccountBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AccountBillVO[] pubapprovebills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_accoutApproveBP bp = new AceHk_arap_accoutApproveBP();
		AccountBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AccountBillVO[] pubunapprovebills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_accoutUnApproveBP bp = new AceHk_arap_accoutUnApproveBP();
		AccountBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}