package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp.AceHy_kaipiaoinfoApproveBP;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp.AceHy_kaipiaoinfoDeleteBP;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp.AceHy_kaipiaoinfoInsertBP;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp.AceHy_kaipiaoinfoSendApproveBP;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp.AceHy_kaipiaoinfoUnApproveBP;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp.AceHy_kaipiaoinfoUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp.AceHy_kaipiaoinfoUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kaipiaoinfoPubServiceImpl {
	// 新增
	public KaipiaoinfoBillVO[] pubinsertBills(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<KaipiaoinfoBillVO> transferTool = new BillTransferTool<KaipiaoinfoBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_kaipiaoinfoInsertBP action = new AceHy_kaipiaoinfoInsertBP();
			KaipiaoinfoBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_kaipiaoinfoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public KaipiaoinfoBillVO[] pubupdateBills(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<KaipiaoinfoBillVO> transferTool = new BillTransferTool<KaipiaoinfoBillVO>(
					clientFullVOs);
			AceHy_kaipiaoinfoUpdateBP bp = new AceHy_kaipiaoinfoUpdateBP();
			KaipiaoinfoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KaipiaoinfoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KaipiaoinfoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KaipiaoinfoBillVO> query = new BillLazyQuery<KaipiaoinfoBillVO>(
					KaipiaoinfoBillVO.class);
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
	public KaipiaoinfoBillVO[] pubsendapprovebills(
			KaipiaoinfoBillVO[] clientFullVOs, KaipiaoinfoBillVO[] originBills)
			throws BusinessException {
		AceHy_kaipiaoinfoSendApproveBP bp = new AceHy_kaipiaoinfoSendApproveBP();
		KaipiaoinfoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public KaipiaoinfoBillVO[] pubunsendapprovebills(
			KaipiaoinfoBillVO[] clientFullVOs, KaipiaoinfoBillVO[] originBills)
			throws BusinessException {
		AceHy_kaipiaoinfoUnSendApproveBP bp = new AceHy_kaipiaoinfoUnSendApproveBP();
		KaipiaoinfoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public KaipiaoinfoBillVO[] pubapprovebills(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaipiaoinfoApproveBP bp = new AceHy_kaipiaoinfoApproveBP();
		KaipiaoinfoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public KaipiaoinfoBillVO[] pubunapprovebills(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaipiaoinfoUnApproveBP bp = new AceHy_kaipiaoinfoUnApproveBP();
		KaipiaoinfoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}