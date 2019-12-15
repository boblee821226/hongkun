package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.cikayue.ace.bp.AceHy_cikayueInsertBP;
import nc.bs.hkjt.huiyuan.cikayue.ace.bp.AceHy_cikayueUpdateBP;
import nc.bs.hkjt.huiyuan.cikayue.ace.bp.AceHy_cikayueDeleteBP;
import nc.bs.hkjt.huiyuan.cikayue.ace.bp.AceHy_cikayueSendApproveBP;
import nc.bs.hkjt.huiyuan.cikayue.ace.bp.AceHy_cikayueUnSendApproveBP;
import nc.bs.hkjt.huiyuan.cikayue.ace.bp.AceHy_cikayueApproveBP;
import nc.bs.hkjt.huiyuan.cikayue.ace.bp.AceHy_cikayueUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_cikayuePubServiceImpl {
	// 新增
	public CikayueBillVO[] pubinsertBills(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<CikayueBillVO> transferTool = new BillTransferTool<CikayueBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_cikayueInsertBP action = new AceHy_cikayueInsertBP();
			CikayueBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_cikayueDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public CikayueBillVO[] pubupdateBills(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<CikayueBillVO> transferTool = new BillTransferTool<CikayueBillVO>(
					clientFullVOs);
			AceHy_cikayueUpdateBP bp = new AceHy_cikayueUpdateBP();
			CikayueBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CikayueBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CikayueBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CikayueBillVO> query = new BillLazyQuery<CikayueBillVO>(
					CikayueBillVO.class);
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
	public CikayueBillVO[] pubsendapprovebills(
			CikayueBillVO[] clientFullVOs, CikayueBillVO[] originBills)
			throws BusinessException {
		AceHy_cikayueSendApproveBP bp = new AceHy_cikayueSendApproveBP();
		CikayueBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public CikayueBillVO[] pubunsendapprovebills(
			CikayueBillVO[] clientFullVOs, CikayueBillVO[] originBills)
			throws BusinessException {
		AceHy_cikayueUnSendApproveBP bp = new AceHy_cikayueUnSendApproveBP();
		CikayueBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public CikayueBillVO[] pubapprovebills(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikayueApproveBP bp = new AceHy_cikayueApproveBP();
		CikayueBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public CikayueBillVO[] pubunapprovebills(CikayueBillVO[] clientFullVOs,
			CikayueBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikayueUnApproveBP bp = new AceHy_cikayueUnApproveBP();
		CikayueBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}