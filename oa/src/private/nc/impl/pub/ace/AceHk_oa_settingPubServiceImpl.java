package nc.impl.pub.ace;

import nc.bs.hkjt.oa.setting.ace.bp.AceHk_oa_settingInsertBP;
import nc.bs.hkjt.oa.setting.ace.bp.AceHk_oa_settingUpdateBP;
import nc.bs.hkjt.oa.setting.ace.bp.AceHk_oa_settingDeleteBP;
import nc.bs.hkjt.oa.setting.ace.bp.AceHk_oa_settingSendApproveBP;
import nc.bs.hkjt.oa.setting.ace.bp.AceHk_oa_settingUnSendApproveBP;
import nc.bs.hkjt.oa.setting.ace.bp.AceHk_oa_settingApproveBP;
import nc.bs.hkjt.oa.setting.ace.bp.AceHk_oa_settingUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_oa_settingPubServiceImpl {
	// 新增
	public OaSettingBillVO[] pubinsertBills(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<OaSettingBillVO> transferTool = new BillTransferTool<OaSettingBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_oa_settingInsertBP action = new AceHk_oa_settingInsertBP();
			OaSettingBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_oa_settingDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public OaSettingBillVO[] pubupdateBills(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<OaSettingBillVO> transferTool = new BillTransferTool<OaSettingBillVO>(
					clientFullVOs);
			AceHk_oa_settingUpdateBP bp = new AceHk_oa_settingUpdateBP();
			OaSettingBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public OaSettingBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		OaSettingBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<OaSettingBillVO> query = new BillLazyQuery<OaSettingBillVO>(
					OaSettingBillVO.class);
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
	public OaSettingBillVO[] pubsendapprovebills(
			OaSettingBillVO[] clientFullVOs, OaSettingBillVO[] originBills)
			throws BusinessException {
		AceHk_oa_settingSendApproveBP bp = new AceHk_oa_settingSendApproveBP();
		OaSettingBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public OaSettingBillVO[] pubunsendapprovebills(
			OaSettingBillVO[] clientFullVOs, OaSettingBillVO[] originBills)
			throws BusinessException {
		AceHk_oa_settingUnSendApproveBP bp = new AceHk_oa_settingUnSendApproveBP();
		OaSettingBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public OaSettingBillVO[] pubapprovebills(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_oa_settingApproveBP bp = new AceHk_oa_settingApproveBP();
		OaSettingBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public OaSettingBillVO[] pubunapprovebills(OaSettingBillVO[] clientFullVOs,
			OaSettingBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_oa_settingUnApproveBP bp = new AceHk_oa_settingUnApproveBP();
		OaSettingBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}