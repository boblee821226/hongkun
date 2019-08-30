package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaInsertBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaUpdateBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaDeleteBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaSendApproveBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaUnSendApproveBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaApproveBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_huankaPubServiceImpl {
	// 新增
	public HuankaBillVO[] pubinsertBills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<HuankaBillVO> transferTool = new BillTransferTool<HuankaBillVO>(
					clientFullVOs);
			// 调用BP
			AceHy_huankaInsertBP action = new AceHy_huankaInsertBP();
			HuankaBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHy_huankaDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public HuankaBillVO[] pubupdateBills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<HuankaBillVO> transferTool = new BillTransferTool<HuankaBillVO>(
					clientFullVOs);
			AceHy_huankaUpdateBP bp = new AceHy_huankaUpdateBP();
			HuankaBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public HuankaBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		HuankaBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<HuankaBillVO> query = new BillLazyQuery<HuankaBillVO>(
					HuankaBillVO.class);
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
	public HuankaBillVO[] pubsendapprovebills(
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills)
			throws BusinessException {
		AceHy_huankaSendApproveBP bp = new AceHy_huankaSendApproveBP();
		HuankaBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public HuankaBillVO[] pubunsendapprovebills(
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills)
			throws BusinessException {
		AceHy_huankaUnSendApproveBP bp = new AceHy_huankaUnSendApproveBP();
		HuankaBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public HuankaBillVO[] pubapprovebills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huankaApproveBP bp = new AceHy_huankaApproveBP();
		HuankaBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public HuankaBillVO[] pubunapprovebills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huankaUnApproveBP bp = new AceHy_huankaUnApproveBP();
		HuankaBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}