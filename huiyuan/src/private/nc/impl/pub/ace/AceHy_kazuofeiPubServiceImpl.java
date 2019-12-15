package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiInsertBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiUpdateBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiDeleteBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiSendApproveBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiApproveBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kazuofeiPubServiceImpl {
	// 新增
	public KazuofeiBillVO[] pubinsertBills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<KazuofeiBillVO> transferTool = new BillTransferTool<KazuofeiBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_kazuofeiInsertBP action = new AceHy_kazuofeiInsertBP();
			KazuofeiBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_kazuofeiDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public KazuofeiBillVO[] pubupdateBills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<KazuofeiBillVO> transferTool = new BillTransferTool<KazuofeiBillVO>(
					clientFullVOs);
			AceHy_kazuofeiUpdateBP bp = new AceHy_kazuofeiUpdateBP();
			KazuofeiBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KazuofeiBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KazuofeiBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KazuofeiBillVO> query = new BillLazyQuery<KazuofeiBillVO>(
					KazuofeiBillVO.class);
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
	public KazuofeiBillVO[] pubsendapprovebills(
			KazuofeiBillVO[] clientFullVOs, KazuofeiBillVO[] originBills)
			throws BusinessException {
		AceHy_kazuofeiSendApproveBP bp = new AceHy_kazuofeiSendApproveBP();
		KazuofeiBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public KazuofeiBillVO[] pubunsendapprovebills(
			KazuofeiBillVO[] clientFullVOs, KazuofeiBillVO[] originBills)
			throws BusinessException {
		AceHy_kazuofeiUnSendApproveBP bp = new AceHy_kazuofeiUnSendApproveBP();
		KazuofeiBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public KazuofeiBillVO[] pubapprovebills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazuofeiApproveBP bp = new AceHy_kazuofeiApproveBP();
		KazuofeiBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public KazuofeiBillVO[] pubunapprovebills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazuofeiUnApproveBP bp = new AceHy_kazuofeiUnApproveBP();
		KazuofeiBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}