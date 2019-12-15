package nc.impl.pub.ace;

import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billInsertBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billUpdateBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billDeleteBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billSendApproveBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billUnSendApproveBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billApproveBP;
import nc.bs.hkjt.fapiao.bill.ace.bp.AceHk_fp_billUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_fp_billPubServiceImpl {
	// ����
	public BillFpBillVO[] pubinsertBills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<BillFpBillVO> transferTool = new BillTransferTool<BillFpBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_fp_billInsertBP action = new AceHk_fp_billInsertBP();
			BillFpBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_fp_billDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public BillFpBillVO[] pubupdateBills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<BillFpBillVO> transferTool = new BillTransferTool<BillFpBillVO>(
					clientFullVOs);
			AceHk_fp_billUpdateBP bp = new AceHk_fp_billUpdateBP();
			BillFpBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public BillFpBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		BillFpBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<BillFpBillVO> query = new BillLazyQuery<BillFpBillVO>(
					BillFpBillVO.class);
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
	public BillFpBillVO[] pubsendapprovebills(
			BillFpBillVO[] clientFullVOs, BillFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_billSendApproveBP bp = new AceHk_fp_billSendApproveBP();
		BillFpBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public BillFpBillVO[] pubunsendapprovebills(
			BillFpBillVO[] clientFullVOs, BillFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_billUnSendApproveBP bp = new AceHk_fp_billUnSendApproveBP();
		BillFpBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public BillFpBillVO[] pubapprovebills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_billApproveBP bp = new AceHk_fp_billApproveBP();
		BillFpBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public BillFpBillVO[] pubunapprovebills(BillFpBillVO[] clientFullVOs,
			BillFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_billUnApproveBP bp = new AceHk_fp_billUnApproveBP();
		BillFpBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}