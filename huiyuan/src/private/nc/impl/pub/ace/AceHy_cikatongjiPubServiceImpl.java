package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiInsertBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiUpdateBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiDeleteBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiSendApproveBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiUnSendApproveBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiApproveBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_cikatongjiPubServiceImpl {
	// 新增
	public CikatongjiBillVO[] pubinsertBills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<CikatongjiBillVO> transferTool = new BillTransferTool<CikatongjiBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_cikatongjiInsertBP action = new AceHy_cikatongjiInsertBP();
			CikatongjiBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_cikatongjiDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public CikatongjiBillVO[] pubupdateBills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<CikatongjiBillVO> transferTool = new BillTransferTool<CikatongjiBillVO>(
					clientFullVOs);
			AceHy_cikatongjiUpdateBP bp = new AceHy_cikatongjiUpdateBP();
			CikatongjiBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CikatongjiBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CikatongjiBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CikatongjiBillVO> query = new BillLazyQuery<CikatongjiBillVO>(
					CikatongjiBillVO.class);
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
	public CikatongjiBillVO[] pubsendapprovebills(
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills)
			throws BusinessException {
		AceHy_cikatongjiSendApproveBP bp = new AceHy_cikatongjiSendApproveBP();
		CikatongjiBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public CikatongjiBillVO[] pubunsendapprovebills(
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills)
			throws BusinessException {
		AceHy_cikatongjiUnSendApproveBP bp = new AceHy_cikatongjiUnSendApproveBP();
		CikatongjiBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public CikatongjiBillVO[] pubapprovebills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikatongjiApproveBP bp = new AceHy_cikatongjiApproveBP();
		CikatongjiBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public CikatongjiBillVO[] pubunapprovebills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikatongjiUnApproveBP bp = new AceHy_cikatongjiUnApproveBP();
		CikatongjiBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}