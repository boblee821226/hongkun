package nc.impl.pub.ace;

import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzInsertBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzUpdateBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzDeleteBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzApproveBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_cctzPubServiceImpl {
	// 新增
	public CctzBillVO[] pubinsertBills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<CctzBillVO> transferTool = new BillTransferTool<CctzBillVO>(
					clientFullVOs);
			// 调用BP
			AceHg_cctzInsertBP action = new AceHg_cctzInsertBP();
			CctzBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHg_cctzDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public CctzBillVO[] pubupdateBills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<CctzBillVO> transferTool = new BillTransferTool<CctzBillVO>(
					clientFullVOs);
			AceHg_cctzUpdateBP bp = new AceHg_cctzUpdateBP();
			CctzBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CctzBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CctzBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CctzBillVO> query = new BillLazyQuery<CctzBillVO>(
					CctzBillVO.class);
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
	public CctzBillVO[] pubsendapprovebills(
			CctzBillVO[] clientFullVOs, CctzBillVO[] originBills)
			throws BusinessException {
		AceHg_cctzSendApproveBP bp = new AceHg_cctzSendApproveBP();
		CctzBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public CctzBillVO[] pubunsendapprovebills(
			CctzBillVO[] clientFullVOs, CctzBillVO[] originBills)
			throws BusinessException {
		AceHg_cctzUnSendApproveBP bp = new AceHg_cctzUnSendApproveBP();
		CctzBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public CctzBillVO[] pubapprovebills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_cctzApproveBP bp = new AceHg_cctzApproveBP();
		CctzBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public CctzBillVO[] pubunapprovebills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_cctzUnApproveBP bp = new AceHg_cctzUnApproveBP();
		CctzBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}