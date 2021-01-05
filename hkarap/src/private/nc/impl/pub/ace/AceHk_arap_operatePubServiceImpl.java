package nc.impl.pub.ace;

import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateInsertBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateUpdateBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateDeleteBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateSendApproveBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateUnSendApproveBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateApproveBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_arap_operatePubServiceImpl {
	// 新增
	public OperateBillVO[] pubinsertBills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<OperateBillVO> transferTool = new BillTransferTool<OperateBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_arap_operateInsertBP action = new AceHk_arap_operateInsertBP();
			OperateBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_arap_operateDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public OperateBillVO[] pubupdateBills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<OperateBillVO> transferTool = new BillTransferTool<OperateBillVO>(
					clientFullVOs);
			AceHk_arap_operateUpdateBP bp = new AceHk_arap_operateUpdateBP();
			OperateBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public OperateBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		OperateBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<OperateBillVO> query = new BillLazyQuery<OperateBillVO>(
					OperateBillVO.class);
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
	public OperateBillVO[] pubsendapprovebills(
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_operateSendApproveBP bp = new AceHk_arap_operateSendApproveBP();
		OperateBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public OperateBillVO[] pubunsendapprovebills(
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_operateUnSendApproveBP bp = new AceHk_arap_operateUnSendApproveBP();
		OperateBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public OperateBillVO[] pubapprovebills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_operateApproveBP bp = new AceHk_arap_operateApproveBP();
		OperateBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public OperateBillVO[] pubunapprovebills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_operateUnApproveBP bp = new AceHk_arap_operateUnApproveBP();
		OperateBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}