package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueInsertBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueUpdateBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueDeleteBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueSendApproveBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueApproveBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kayuePubServiceImpl {
	// 新增
	public KayueBillVO[] pubinsertBills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<KayueBillVO> transferTool = new BillTransferTool<KayueBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_kayueInsertBP action = new AceHy_kayueInsertBP();
			KayueBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_kayueDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public KayueBillVO[] pubupdateBills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<KayueBillVO> transferTool = new BillTransferTool<KayueBillVO>(
					clientFullVOs);
			AceHy_kayueUpdateBP bp = new AceHy_kayueUpdateBP();
			KayueBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KayueBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KayueBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KayueBillVO> query = new BillLazyQuery<KayueBillVO>(
					KayueBillVO.class);
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
	public KayueBillVO[] pubsendapprovebills(
			KayueBillVO[] clientFullVOs, KayueBillVO[] originBills)
			throws BusinessException {
		AceHy_kayueSendApproveBP bp = new AceHy_kayueSendApproveBP();
		KayueBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public KayueBillVO[] pubunsendapprovebills(
			KayueBillVO[] clientFullVOs, KayueBillVO[] originBills)
			throws BusinessException {
		AceHy_kayueUnSendApproveBP bp = new AceHy_kayueUnSendApproveBP();
		KayueBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public KayueBillVO[] pubapprovebills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kayueApproveBP bp = new AceHy_kayueApproveBP();
		KayueBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public KayueBillVO[] pubunapprovebills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kayueUnApproveBP bp = new AceHy_kayueUnApproveBP();
		KayueBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}