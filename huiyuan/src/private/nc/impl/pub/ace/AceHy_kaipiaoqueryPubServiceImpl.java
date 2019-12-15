package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp.AceHy_kaipiaoqueryInsertBP;
import nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp.AceHy_kaipiaoqueryUpdateBP;
import nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp.AceHy_kaipiaoqueryDeleteBP;
import nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp.AceHy_kaipiaoquerySendApproveBP;
import nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp.AceHy_kaipiaoqueryUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp.AceHy_kaipiaoqueryApproveBP;
import nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp.AceHy_kaipiaoqueryUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kaipiaoqueryPubServiceImpl {
	// 新增
	public KaipiaoqueryBillVO[] pubinsertBills(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<KaipiaoqueryBillVO> transferTool = new BillTransferTool<KaipiaoqueryBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_kaipiaoqueryInsertBP action = new AceHy_kaipiaoqueryInsertBP();
			KaipiaoqueryBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_kaipiaoqueryDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public KaipiaoqueryBillVO[] pubupdateBills(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<KaipiaoqueryBillVO> transferTool = new BillTransferTool<KaipiaoqueryBillVO>(
					clientFullVOs);
			AceHy_kaipiaoqueryUpdateBP bp = new AceHy_kaipiaoqueryUpdateBP();
			KaipiaoqueryBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KaipiaoqueryBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KaipiaoqueryBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KaipiaoqueryBillVO> query = new BillLazyQuery<KaipiaoqueryBillVO>(
					KaipiaoqueryBillVO.class);
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
	public KaipiaoqueryBillVO[] pubsendapprovebills(
			KaipiaoqueryBillVO[] clientFullVOs, KaipiaoqueryBillVO[] originBills)
			throws BusinessException {
		AceHy_kaipiaoquerySendApproveBP bp = new AceHy_kaipiaoquerySendApproveBP();
		KaipiaoqueryBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public KaipiaoqueryBillVO[] pubunsendapprovebills(
			KaipiaoqueryBillVO[] clientFullVOs, KaipiaoqueryBillVO[] originBills)
			throws BusinessException {
		AceHy_kaipiaoqueryUnSendApproveBP bp = new AceHy_kaipiaoqueryUnSendApproveBP();
		KaipiaoqueryBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public KaipiaoqueryBillVO[] pubapprovebills(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaipiaoqueryApproveBP bp = new AceHy_kaipiaoqueryApproveBP();
		KaipiaoqueryBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public KaipiaoqueryBillVO[] pubunapprovebills(KaipiaoqueryBillVO[] clientFullVOs,
			KaipiaoqueryBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaipiaoqueryUnApproveBP bp = new AceHy_kaipiaoqueryUnApproveBP();
		KaipiaoqueryBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}