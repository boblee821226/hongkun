package nc.impl.pub.ace;

import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billInsertBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billUpdateBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billDeleteBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billSendApproveBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billUnSendApproveBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billApproveBP;
import nc.bs.hkjt.fapiao_sk.bill.ace.bp.AceHk_fp_sk_billUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_fp_sk_billPubServiceImpl {
	// ����
	public BillSkFpBillVO[] pubinsertBills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<BillSkFpBillVO> transferTool = new BillTransferTool<BillSkFpBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_fp_sk_billInsertBP action = new AceHk_fp_sk_billInsertBP();
			BillSkFpBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_fp_sk_billDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public BillSkFpBillVO[] pubupdateBills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<BillSkFpBillVO> transferTool = new BillTransferTool<BillSkFpBillVO>(
					clientFullVOs);
			AceHk_fp_sk_billUpdateBP bp = new AceHk_fp_sk_billUpdateBP();
			BillSkFpBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public BillSkFpBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		BillSkFpBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<BillSkFpBillVO> query = new BillLazyQuery<BillSkFpBillVO>(
					BillSkFpBillVO.class);
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
	public BillSkFpBillVO[] pubsendapprovebills(
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_sk_billSendApproveBP bp = new AceHk_fp_sk_billSendApproveBP();
		BillSkFpBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public BillSkFpBillVO[] pubunsendapprovebills(
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills)
			throws BusinessException {
		AceHk_fp_sk_billUnSendApproveBP bp = new AceHk_fp_sk_billUnSendApproveBP();
		BillSkFpBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public BillSkFpBillVO[] pubapprovebills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_sk_billApproveBP bp = new AceHk_fp_sk_billApproveBP();
		BillSkFpBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public BillSkFpBillVO[] pubunapprovebills(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_fp_sk_billUnApproveBP bp = new AceHk_fp_sk_billUnApproveBP();
		BillSkFpBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}