package nc.impl.pub.ace;

import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billInsertBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billUpdateBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billDeleteBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billSendApproveBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billUnSendApproveBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billApproveBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_fp_sk_billPubServiceImpl {
	// 新增
	public BillSkFpBillVO[] pubinsertBills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<BillSkFpBillVO> transferTool = new BillTransferTool<BillSkFpBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_fp_sk_billInsertBP action = new AceHk_fp_sk_billInsertBP();
			BillSkFpBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_fp_sk_billDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public BillSkFpBillVO[] pubupdateBills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<BillSkFpBillVO> transferTool = new BillTransferTool<BillSkFpBillVO>(
					clientFullVOs);
			AceHk_fp_sk_billUpdateBP bp = new AceHk_fp_sk_billUpdateBP();
			BillSkFpBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public BillSkFpBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		BillSkFpBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<BillSkFpBillVO> query = new BillLazyQuery<BillSkFpBillVO>(
					BillSkFpBillVO.class);
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
	public BillSkFpBillVO[] pubsendapprovebills(
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_sk_billSendApproveBP bp = new AceHk_fp_sk_billSendApproveBP();
		BillSkFpBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public BillSkFpBillVO[] pubunsendapprovebills(
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_sk_billUnSendApproveBP bp = new AceHk_fp_sk_billUnSendApproveBP();
		BillSkFpBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public BillSkFpBillVO[] pubapprovebills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_sk_billApproveBP bp = new AceHk_fp_sk_billApproveBP();
		BillSkFpBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public BillSkFpBillVO[] pubunapprovebills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_sk_billUnApproveBP bp = new AceHk_fp_sk_billUnApproveBP();
		BillSkFpBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}