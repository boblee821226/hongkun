package nc.impl.pub.ace;

import nc.bs.hkjt.store.lvyun_out.ace.bp.AceHk_store_lvyun_outInsertBP;
import nc.bs.hkjt.store.lvyun_out.ace.bp.AceHk_store_lvyun_outUpdateBP;
import nc.bs.hkjt.store.lvyun_out.ace.bp.AceHk_store_lvyun_outDeleteBP;
import nc.bs.hkjt.store.lvyun_out.ace.bp.AceHk_store_lvyun_outSendApproveBP;
import nc.bs.hkjt.store.lvyun_out.ace.bp.AceHk_store_lvyun_outUnSendApproveBP;
import nc.bs.hkjt.store.lvyun_out.ace.bp.AceHk_store_lvyun_outApproveBP;
import nc.bs.hkjt.store.lvyun_out.ace.bp.AceHk_store_lvyun_outUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_store_lvyun_outPubServiceImpl {
	// 新增
	public LyOutStoreBillVO[] pubinsertBills(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<LyOutStoreBillVO> transferTool = new BillTransferTool<LyOutStoreBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_store_lvyun_outInsertBP action = new AceHk_store_lvyun_outInsertBP();
			LyOutStoreBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_store_lvyun_outDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public LyOutStoreBillVO[] pubupdateBills(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<LyOutStoreBillVO> transferTool = new BillTransferTool<LyOutStoreBillVO>(
					clientFullVOs);
			AceHk_store_lvyun_outUpdateBP bp = new AceHk_store_lvyun_outUpdateBP();
			LyOutStoreBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public LyOutStoreBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		LyOutStoreBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<LyOutStoreBillVO> query = new BillLazyQuery<LyOutStoreBillVO>(
					LyOutStoreBillVO.class);
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
	public LyOutStoreBillVO[] pubsendapprovebills(
			LyOutStoreBillVO[] clientFullVOs, LyOutStoreBillVO[] originBills)
			throws BusinessException {
		AceHk_store_lvyun_outSendApproveBP bp = new AceHk_store_lvyun_outSendApproveBP();
		LyOutStoreBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public LyOutStoreBillVO[] pubunsendapprovebills(
			LyOutStoreBillVO[] clientFullVOs, LyOutStoreBillVO[] originBills)
			throws BusinessException {
		AceHk_store_lvyun_outUnSendApproveBP bp = new AceHk_store_lvyun_outUnSendApproveBP();
		LyOutStoreBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public LyOutStoreBillVO[] pubapprovebills(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_store_lvyun_outApproveBP bp = new AceHk_store_lvyun_outApproveBP();
		LyOutStoreBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public LyOutStoreBillVO[] pubunapprovebills(LyOutStoreBillVO[] clientFullVOs,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_store_lvyun_outUnApproveBP bp = new AceHk_store_lvyun_outUnApproveBP();
		LyOutStoreBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}