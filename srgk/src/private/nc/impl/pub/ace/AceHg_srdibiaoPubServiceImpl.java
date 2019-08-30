package nc.impl.pub.ace;

import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoDeleteBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoInsertBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoUnApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_srdibiaoPubServiceImpl {
	// 新增
	public SrdibiaoBillVO[] pubinsertBills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<SrdibiaoBillVO> transferTool = new BillTransferTool<SrdibiaoBillVO>(
					clientFullVOs);
			// 调用BP
			AceHg_srdibiaoInsertBP action = new AceHg_srdibiaoInsertBP();
			SrdibiaoBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHg_srdibiaoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public SrdibiaoBillVO[] pubupdateBills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<SrdibiaoBillVO> transferTool = new BillTransferTool<SrdibiaoBillVO>(
					clientFullVOs);
			AceHg_srdibiaoUpdateBP bp = new AceHg_srdibiaoUpdateBP();
			SrdibiaoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public SrdibiaoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		SrdibiaoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<SrdibiaoBillVO> query = new BillLazyQuery<SrdibiaoBillVO>(
					SrdibiaoBillVO.class);
			query.setOrderAttribute(SrdibiaoBVO.class, new String[]{"vrowno"});	// 表体按 行号排序
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
	public SrdibiaoBillVO[] pubsendapprovebills(
			SrdibiaoBillVO[] clientFullVOs, SrdibiaoBillVO[] originBills)
			throws BusinessException {
		AceHg_srdibiaoSendApproveBP bp = new AceHg_srdibiaoSendApproveBP();
		SrdibiaoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public SrdibiaoBillVO[] pubunsendapprovebills(
			SrdibiaoBillVO[] clientFullVOs, SrdibiaoBillVO[] originBills)
			throws BusinessException {
		AceHg_srdibiaoUnSendApproveBP bp = new AceHg_srdibiaoUnSendApproveBP();
		SrdibiaoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public SrdibiaoBillVO[] pubapprovebills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_srdibiaoApproveBP bp = new AceHg_srdibiaoApproveBP();
		SrdibiaoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public SrdibiaoBillVO[] pubunapprovebills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_srdibiaoUnApproveBP bp = new AceHg_srdibiaoUnApproveBP();
		SrdibiaoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}