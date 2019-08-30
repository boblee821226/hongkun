package nc.impl.pub.ace;

import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsInsertBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsUpdateBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsDeleteBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsSendApproveBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsUnSendApproveBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsApproveBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_znjjsPubServiceImpl {
	// 新增
	public ZnjjsBillVO[] pubinsertBills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<ZnjjsBillVO> transferTool = new BillTransferTool<ZnjjsBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_zulin_znjjsInsertBP action = new AceHk_zulin_znjjsInsertBP();
			ZnjjsBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_zulin_znjjsDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public ZnjjsBillVO[] pubupdateBills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<ZnjjsBillVO> transferTool = new BillTransferTool<ZnjjsBillVO>(
					clientFullVOs);
			AceHk_zulin_znjjsUpdateBP bp = new AceHk_zulin_znjjsUpdateBP();
			ZnjjsBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public ZnjjsBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		ZnjjsBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<ZnjjsBillVO> query = new BillLazyQuery<ZnjjsBillVO>(
					ZnjjsBillVO.class);
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
	public ZnjjsBillVO[] pubsendapprovebills(
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjsSendApproveBP bp = new AceHk_zulin_znjjsSendApproveBP();
		ZnjjsBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public ZnjjsBillVO[] pubunsendapprovebills(
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjsUnSendApproveBP bp = new AceHk_zulin_znjjsUnSendApproveBP();
		ZnjjsBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public ZnjjsBillVO[] pubapprovebills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjsApproveBP bp = new AceHk_zulin_znjjsApproveBP();
		ZnjjsBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public ZnjjsBillVO[] pubunapprovebills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjsUnApproveBP bp = new AceHk_zulin_znjjsUnApproveBP();
		ZnjjsBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}