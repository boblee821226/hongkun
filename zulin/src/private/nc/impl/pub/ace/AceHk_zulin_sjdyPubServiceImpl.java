package nc.impl.pub.ace;

import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyInsertBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyUpdateBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyDeleteBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdySendApproveBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyUnSendApproveBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyApproveBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_sjdyPubServiceImpl {
	// 新增
	public SjdyBillVO[] pubinsertBills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<SjdyBillVO> transferTool = new BillTransferTool<SjdyBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_zulin_sjdyInsertBP action = new AceHk_zulin_sjdyInsertBP();
			SjdyBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_zulin_sjdyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public SjdyBillVO[] pubupdateBills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<SjdyBillVO> transferTool = new BillTransferTool<SjdyBillVO>(
					clientFullVOs);
			AceHk_zulin_sjdyUpdateBP bp = new AceHk_zulin_sjdyUpdateBP();
			SjdyBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public SjdyBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		SjdyBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<SjdyBillVO> query = new BillLazyQuery<SjdyBillVO>(
					SjdyBillVO.class);
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
	public SjdyBillVO[] pubsendapprovebills(
			SjdyBillVO[] clientFullVOs, SjdyBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sjdySendApproveBP bp = new AceHk_zulin_sjdySendApproveBP();
		SjdyBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public SjdyBillVO[] pubunsendapprovebills(
			SjdyBillVO[] clientFullVOs, SjdyBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sjdyUnSendApproveBP bp = new AceHk_zulin_sjdyUnSendApproveBP();
		SjdyBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public SjdyBillVO[] pubapprovebills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sjdyApproveBP bp = new AceHk_zulin_sjdyApproveBP();
		SjdyBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public SjdyBillVO[] pubunapprovebills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sjdyUnApproveBP bp = new AceHk_zulin_sjdyUnApproveBP();
		SjdyBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}