package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanInsertBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanUpdateBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanDeleteBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanSendApproveBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanApproveBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_huiyuanPubServiceImpl {
	// 新增
	public KadanganBillVO[] pubinsertBills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<KadanganBillVO> transferTool = new BillTransferTool<KadanganBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_huiyuanInsertBP action = new AceHy_huiyuanInsertBP();
			KadanganBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_huiyuanDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public KadanganBillVO[] pubupdateBills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<KadanganBillVO> transferTool = new BillTransferTool<KadanganBillVO>(
					clientFullVOs);
			AceHy_huiyuanUpdateBP bp = new AceHy_huiyuanUpdateBP();
			KadanganBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KadanganBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KadanganBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KadanganBillVO> query = new BillLazyQuery<KadanganBillVO>(
					KadanganBillVO.class);
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
	public KadanganBillVO[] pubsendapprovebills(
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills)
			throws BusinessException {
		AceHy_huiyuanSendApproveBP bp = new AceHy_huiyuanSendApproveBP();
		KadanganBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public KadanganBillVO[] pubunsendapprovebills(
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills)
			throws BusinessException {
		AceHy_huiyuanUnSendApproveBP bp = new AceHy_huiyuanUnSendApproveBP();
		KadanganBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public KadanganBillVO[] pubapprovebills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huiyuanApproveBP bp = new AceHy_huiyuanApproveBP();
		KadanganBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public KadanganBillVO[] pubunapprovebills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huiyuanUnApproveBP bp = new AceHy_huiyuanUnApproveBP();
		KadanganBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}