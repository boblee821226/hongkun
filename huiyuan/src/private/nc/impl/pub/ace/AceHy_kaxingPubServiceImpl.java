package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingInsertBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingUpdateBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingDeleteBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingSendApproveBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingApproveBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kaxingPubServiceImpl {
	// 新增
	public KaxingBillVO[] pubinsertBills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<KaxingBillVO> transferTool = new BillTransferTool<KaxingBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_kaxingInsertBP action = new AceHy_kaxingInsertBP();
			KaxingBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_kaxingDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public KaxingBillVO[] pubupdateBills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<KaxingBillVO> transferTool = new BillTransferTool<KaxingBillVO>(
					clientFullVOs);
			AceHy_kaxingUpdateBP bp = new AceHy_kaxingUpdateBP();
			KaxingBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KaxingBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KaxingBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KaxingBillVO> query = new BillLazyQuery<KaxingBillVO>(
					KaxingBillVO.class);
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
	public KaxingBillVO[] pubsendapprovebills(
			KaxingBillVO[] clientFullVOs, KaxingBillVO[] originBills)
			throws BusinessException {
		AceHy_kaxingSendApproveBP bp = new AceHy_kaxingSendApproveBP();
		KaxingBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public KaxingBillVO[] pubunsendapprovebills(
			KaxingBillVO[] clientFullVOs, KaxingBillVO[] originBills)
			throws BusinessException {
		AceHy_kaxingUnSendApproveBP bp = new AceHy_kaxingUnSendApproveBP();
		KaxingBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public KaxingBillVO[] pubapprovebills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaxingApproveBP bp = new AceHy_kaxingApproveBP();
		KaxingBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public KaxingBillVO[] pubunapprovebills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaxingUnApproveBP bp = new AceHy_kaxingUnApproveBP();
		KaxingBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}