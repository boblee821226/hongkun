package nc.impl.pub.ace;

import nc.bs.hkjt.arap.bill.ace.bp.AceHk_arap_billInsertBP;
import nc.bs.hkjt.arap.bill.ace.bp.AceHk_arap_billUpdateBP;
import nc.bs.hkjt.arap.bill.ace.bp.AceHk_arap_billDeleteBP;
import nc.bs.hkjt.arap.bill.ace.bp.AceHk_arap_billSendApproveBP;
import nc.bs.hkjt.arap.bill.ace.bp.AceHk_arap_billUnSendApproveBP;
import nc.bs.hkjt.arap.bill.ace.bp.AceHk_arap_billApproveBP;
import nc.bs.hkjt.arap.bill.ace.bp.AceHk_arap_billUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_arap_billPubServiceImpl {
	// 新增
	public BillBillVO[] pubinsertBills(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<BillBillVO> transferTool = new BillTransferTool<BillBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_arap_billInsertBP action = new AceHk_arap_billInsertBP();
			BillBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_arap_billDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public BillBillVO[] pubupdateBills(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<BillBillVO> transferTool = new BillTransferTool<BillBillVO>(
					clientFullVOs);
			AceHk_arap_billUpdateBP bp = new AceHk_arap_billUpdateBP();
			BillBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public BillBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		BillBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<BillBillVO> query = new BillLazyQuery<BillBillVO>(
					BillBillVO.class);
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
	public BillBillVO[] pubsendapprovebills(
			BillBillVO[] clientFullVOs, BillBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_billSendApproveBP bp = new AceHk_arap_billSendApproveBP();
		BillBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public BillBillVO[] pubunsendapprovebills(
			BillBillVO[] clientFullVOs, BillBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_billUnSendApproveBP bp = new AceHk_arap_billUnSendApproveBP();
		BillBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public BillBillVO[] pubapprovebills(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_billApproveBP bp = new AceHk_arap_billApproveBP();
		BillBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public BillBillVO[] pubunapprovebills(BillBillVO[] clientFullVOs,
			BillBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_billUnApproveBP bp = new AceHk_arap_billUnApproveBP();
		BillBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}