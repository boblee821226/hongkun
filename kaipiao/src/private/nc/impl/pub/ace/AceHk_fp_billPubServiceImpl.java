package nc.impl.pub.ace;

import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billInsertBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billUpdateBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billDeleteBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billSendApproveBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billUnSendApproveBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billApproveBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_fp_billPubServiceImpl {
	// 新增
	public BillFpBillVO[] pubinsertBills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<BillFpBillVO> transferTool = new BillTransferTool<BillFpBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_fp_billInsertBP action = new AceHk_fp_billInsertBP();
			BillFpBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_fp_billDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public BillFpBillVO[] pubupdateBills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<BillFpBillVO> transferTool = new BillTransferTool<BillFpBillVO>(
					clientFullVOs);
			AceHk_fp_billUpdateBP bp = new AceHk_fp_billUpdateBP();
			BillFpBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public BillFpBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		BillFpBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<BillFpBillVO> query = new BillLazyQuery<BillFpBillVO>(
					BillFpBillVO.class);
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
	public BillFpBillVO[] pubsendapprovebills(
			BillFpBillVO[] clientFullVOs, BillFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_billSendApproveBP bp = new AceHk_fp_billSendApproveBP();
		BillFpBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public BillFpBillVO[] pubunsendapprovebills(
			BillFpBillVO[] clientFullVOs, BillFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_billUnSendApproveBP bp = new AceHk_fp_billUnSendApproveBP();
		BillFpBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public BillFpBillVO[] pubapprovebills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_billApproveBP bp = new AceHk_fp_billApproveBP();
		BillFpBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public BillFpBillVO[] pubunapprovebills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_billUnApproveBP bp = new AceHk_fp_billUnApproveBP();
		BillFpBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}