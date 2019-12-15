package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoInsertBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoUpdateBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoDeleteBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoSendApproveBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoUnSendApproveBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoApproveBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_cikainfoPubServiceImpl {
	// 新增
	public CikainfoBillVO[] pubinsertBills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<CikainfoBillVO> transferTool = new BillTransferTool<CikainfoBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_cikainfoInsertBP action = new AceHy_cikainfoInsertBP();
			CikainfoBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_cikainfoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public CikainfoBillVO[] pubupdateBills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<CikainfoBillVO> transferTool = new BillTransferTool<CikainfoBillVO>(
					clientFullVOs);
			AceHy_cikainfoUpdateBP bp = new AceHy_cikainfoUpdateBP();
			CikainfoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CikainfoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CikainfoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CikainfoBillVO> query = new BillLazyQuery<CikainfoBillVO>(
					CikainfoBillVO.class);
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
	public CikainfoBillVO[] pubsendapprovebills(
			CikainfoBillVO[] clientFullVOs, CikainfoBillVO[] originBills)
			throws BusinessException {
		AceHy_cikainfoSendApproveBP bp = new AceHy_cikainfoSendApproveBP();
		CikainfoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public CikainfoBillVO[] pubunsendapprovebills(
			CikainfoBillVO[] clientFullVOs, CikainfoBillVO[] originBills)
			throws BusinessException {
		AceHy_cikainfoUnSendApproveBP bp = new AceHy_cikainfoUnSendApproveBP();
		CikainfoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public CikainfoBillVO[] pubapprovebills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikainfoApproveBP bp = new AceHy_cikainfoApproveBP();
		CikainfoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public CikainfoBillVO[] pubunapprovebills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikainfoUnApproveBP bp = new AceHy_cikainfoUnApproveBP();
		CikainfoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}