package nc.impl.pub.ace;

import nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp.AceHg_rsbaogaoInsertBP;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp.AceHg_rsbaogaoUpdateBP;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp.AceHg_rsbaogaoDeleteBP;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp.AceHg_rsbaogaoSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp.AceHg_rsbaogaoUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp.AceHg_rsbaogaoApproveBP;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp.AceHg_rsbaogaoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_rsbaogaoPubServiceImpl {
	// 新增
	public RsbaogaoBillVO[] pubinsertBills(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<RsbaogaoBillVO> transferTool = new BillTransferTool<RsbaogaoBillVO>(
					clientFullVOs);
			// 调用BP
			AceHg_rsbaogaoInsertBP action = new AceHg_rsbaogaoInsertBP();
			RsbaogaoBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHg_rsbaogaoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public RsbaogaoBillVO[] pubupdateBills(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<RsbaogaoBillVO> transferTool = new BillTransferTool<RsbaogaoBillVO>(
					clientFullVOs);
			AceHg_rsbaogaoUpdateBP bp = new AceHg_rsbaogaoUpdateBP();
			RsbaogaoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public RsbaogaoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		RsbaogaoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<RsbaogaoBillVO> query = new BillLazyQuery<RsbaogaoBillVO>(
					RsbaogaoBillVO.class);
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
	public RsbaogaoBillVO[] pubsendapprovebills(
			RsbaogaoBillVO[] clientFullVOs, RsbaogaoBillVO[] originBills)
			throws BusinessException {
		AceHg_rsbaogaoSendApproveBP bp = new AceHg_rsbaogaoSendApproveBP();
		RsbaogaoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public RsbaogaoBillVO[] pubunsendapprovebills(
			RsbaogaoBillVO[] clientFullVOs, RsbaogaoBillVO[] originBills)
			throws BusinessException {
		AceHg_rsbaogaoUnSendApproveBP bp = new AceHg_rsbaogaoUnSendApproveBP();
		RsbaogaoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public RsbaogaoBillVO[] pubapprovebills(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_rsbaogaoApproveBP bp = new AceHg_rsbaogaoApproveBP();
		RsbaogaoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public RsbaogaoBillVO[] pubunapprovebills(RsbaogaoBillVO[] clientFullVOs,
			RsbaogaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_rsbaogaoUnApproveBP bp = new AceHg_rsbaogaoUnApproveBP();
		RsbaogaoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}