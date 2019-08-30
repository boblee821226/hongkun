package nc.impl.pub.ace;

import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanInsertBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanUpdateBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanDeleteBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanSendApproveBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanUnSendApproveBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanApproveBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceJs_shoudanPubServiceImpl {
	// 新增
	public ShoudanBillVO[] pubinsertBills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<ShoudanBillVO> transferTool = new BillTransferTool<ShoudanBillVO>(
					clientFullVOs);
			// 调用BP
			AceJs_shoudanInsertBP action = new AceJs_shoudanInsertBP();
			ShoudanBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceJs_shoudanDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public ShoudanBillVO[] pubupdateBills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<ShoudanBillVO> transferTool = new BillTransferTool<ShoudanBillVO>(
					clientFullVOs);
			AceJs_shoudanUpdateBP bp = new AceJs_shoudanUpdateBP();
			ShoudanBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public ShoudanBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		ShoudanBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<ShoudanBillVO> query = new BillLazyQuery<ShoudanBillVO>(
					ShoudanBillVO.class);
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
	public ShoudanBillVO[] pubsendapprovebills(
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills)
			throws BusinessException {
		AceJs_shoudanSendApproveBP bp = new AceJs_shoudanSendApproveBP();
		ShoudanBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public ShoudanBillVO[] pubunsendapprovebills(
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills)
			throws BusinessException {
		AceJs_shoudanUnSendApproveBP bp = new AceJs_shoudanUnSendApproveBP();
		ShoudanBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public ShoudanBillVO[] pubapprovebills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceJs_shoudanApproveBP bp = new AceJs_shoudanApproveBP();
		ShoudanBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public ShoudanBillVO[] pubunapprovebills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceJs_shoudanUnApproveBP bp = new AceJs_shoudanUnApproveBP();
		ShoudanBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}