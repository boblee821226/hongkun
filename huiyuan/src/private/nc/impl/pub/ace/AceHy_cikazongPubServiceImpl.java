package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongInsertBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongUpdateBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongDeleteBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongSendApproveBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongUnSendApproveBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongApproveBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_cikazongPubServiceImpl {
	// 新增
	public CikazongBillVO[] pubinsertBills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<CikazongBillVO> transferTool = new BillTransferTool<CikazongBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_cikazongInsertBP action = new AceHy_cikazongInsertBP();
			CikazongBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_cikazongDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public CikazongBillVO[] pubupdateBills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<CikazongBillVO> transferTool = new BillTransferTool<CikazongBillVO>(
					clientFullVOs);
			AceHy_cikazongUpdateBP bp = new AceHy_cikazongUpdateBP();
			CikazongBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CikazongBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CikazongBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CikazongBillVO> query = new BillLazyQuery<CikazongBillVO>(
					CikazongBillVO.class);
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
	public CikazongBillVO[] pubsendapprovebills(
			CikazongBillVO[] clientFullVOs, CikazongBillVO[] originBills)
			throws BusinessException {
		AceHy_cikazongSendApproveBP bp = new AceHy_cikazongSendApproveBP();
		CikazongBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public CikazongBillVO[] pubunsendapprovebills(
			CikazongBillVO[] clientFullVOs, CikazongBillVO[] originBills)
			throws BusinessException {
		AceHy_cikazongUnSendApproveBP bp = new AceHy_cikazongUnSendApproveBP();
		CikazongBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public CikazongBillVO[] pubapprovebills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikazongApproveBP bp = new AceHy_cikazongApproveBP();
		CikazongBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public CikazongBillVO[] pubunapprovebills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikazongUnApproveBP bp = new AceHy_cikazongUnApproveBP();
		CikazongBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}