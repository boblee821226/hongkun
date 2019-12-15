package nc.impl.pub.ace;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmInsertBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmUpdateBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmDeleteBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmSendApproveBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmUnSendApproveBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmApproveBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_znjjmPubServiceImpl {
	// 新增
	public ZnjjmBillVO[] pubinsertBills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<ZnjjmBillVO> transferTool = new BillTransferTool<ZnjjmBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_zulin_znjjmInsertBP action = new AceHk_zulin_znjjmInsertBP();
			ZnjjmBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_zulin_znjjmDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public ZnjjmBillVO[] pubupdateBills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<ZnjjmBillVO> transferTool = new BillTransferTool<ZnjjmBillVO>(
					clientFullVOs);
			AceHk_zulin_znjjmUpdateBP bp = new AceHk_zulin_znjjmUpdateBP();
			ZnjjmBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public ZnjjmBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		ZnjjmBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<ZnjjmBillVO> query = new BillLazyQuery<ZnjjmBillVO>(
					ZnjjmBillVO.class);
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
	public ZnjjmBillVO[] pubsendapprovebills(
			ZnjjmBillVO[] clientFullVOs, ZnjjmBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjmSendApproveBP bp = new AceHk_zulin_znjjmSendApproveBP();
		ZnjjmBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public ZnjjmBillVO[] pubunsendapprovebills(
			ZnjjmBillVO[] clientFullVOs, ZnjjmBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjmUnSendApproveBP bp = new AceHk_zulin_znjjmUnSendApproveBP();
		ZnjjmBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public ZnjjmBillVO[] pubapprovebills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjmApproveBP bp = new AceHk_zulin_znjjmApproveBP();
		ZnjjmBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		
		return retvos;
	}

	// 弃审
	public ZnjjmBillVO[] pubunapprovebills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjmUnApproveBP bp = new AceHk_zulin_znjjmUnApproveBP();
		ZnjjmBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		
		return retvos;
	}

}