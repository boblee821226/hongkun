package nc.impl.pub.ace;

import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzInsertBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzUpdateBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzDeleteBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzApproveBP;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_cctzPubServiceImpl {
	// ����
	public CctzBillVO[] pubinsertBills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<CctzBillVO> transferTool = new BillTransferTool<CctzBillVO>(
					clientFullVOs);
			// ����BP
			AceHg_cctzInsertBP action = new AceHg_cctzInsertBP();
			CctzBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHg_cctzDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public CctzBillVO[] pubupdateBills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<CctzBillVO> transferTool = new BillTransferTool<CctzBillVO>(
					clientFullVOs);
			AceHg_cctzUpdateBP bp = new AceHg_cctzUpdateBP();
			CctzBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CctzBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CctzBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CctzBillVO> query = new BillLazyQuery<CctzBillVO>(
					CctzBillVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public CctzBillVO[] pubsendapprovebills(
			CctzBillVO[] clientFullVOs, CctzBillVO[] originBills)
			throws BusinessException {
		AceHg_cctzSendApproveBP bp = new AceHg_cctzSendApproveBP();
		CctzBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public CctzBillVO[] pubunsendapprovebills(
			CctzBillVO[] clientFullVOs, CctzBillVO[] originBills)
			throws BusinessException {
		AceHg_cctzUnSendApproveBP bp = new AceHg_cctzUnSendApproveBP();
		CctzBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public CctzBillVO[] pubapprovebills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_cctzApproveBP bp = new AceHg_cctzApproveBP();
		CctzBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public CctzBillVO[] pubunapprovebills(CctzBillVO[] clientFullVOs,
			CctzBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_cctzUnApproveBP bp = new AceHg_cctzUnApproveBP();
		CctzBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}