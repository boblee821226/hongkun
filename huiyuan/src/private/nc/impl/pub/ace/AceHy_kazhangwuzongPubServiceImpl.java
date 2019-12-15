package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp.AceHy_kazhangwuzongInsertBP;
import nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp.AceHy_kazhangwuzongUpdateBP;
import nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp.AceHy_kazhangwuzongDeleteBP;
import nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp.AceHy_kazhangwuzongSendApproveBP;
import nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp.AceHy_kazhangwuzongUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp.AceHy_kazhangwuzongApproveBP;
import nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp.AceHy_kazhangwuzongUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kazhangwuzongPubServiceImpl {
	// 新增
	public KazhangwuzongBillVO[] pubinsertBills(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<KazhangwuzongBillVO> transferTool = new BillTransferTool<KazhangwuzongBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_kazhangwuzongInsertBP action = new AceHy_kazhangwuzongInsertBP();
			KazhangwuzongBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_kazhangwuzongDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public KazhangwuzongBillVO[] pubupdateBills(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<KazhangwuzongBillVO> transferTool = new BillTransferTool<KazhangwuzongBillVO>(
					clientFullVOs);
			AceHy_kazhangwuzongUpdateBP bp = new AceHy_kazhangwuzongUpdateBP();
			KazhangwuzongBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KazhangwuzongBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KazhangwuzongBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KazhangwuzongBillVO> query = new BillLazyQuery<KazhangwuzongBillVO>(
					KazhangwuzongBillVO.class);
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
	public KazhangwuzongBillVO[] pubsendapprovebills(
			KazhangwuzongBillVO[] clientFullVOs, KazhangwuzongBillVO[] originBills)
			throws BusinessException {
		AceHy_kazhangwuzongSendApproveBP bp = new AceHy_kazhangwuzongSendApproveBP();
		KazhangwuzongBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public KazhangwuzongBillVO[] pubunsendapprovebills(
			KazhangwuzongBillVO[] clientFullVOs, KazhangwuzongBillVO[] originBills)
			throws BusinessException {
		AceHy_kazhangwuzongUnSendApproveBP bp = new AceHy_kazhangwuzongUnSendApproveBP();
		KazhangwuzongBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public KazhangwuzongBillVO[] pubapprovebills(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazhangwuzongApproveBP bp = new AceHy_kazhangwuzongApproveBP();
		KazhangwuzongBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public KazhangwuzongBillVO[] pubunapprovebills(KazhangwuzongBillVO[] clientFullVOs,
			KazhangwuzongBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazhangwuzongUnApproveBP bp = new AceHy_kazhangwuzongUnApproveBP();
		KazhangwuzongBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}