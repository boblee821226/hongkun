package nc.impl.pub.ace;

import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutInsertBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutUpdateBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutDeleteBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutSendApproveBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutUnSendApproveBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutApproveBP;
import nc.bs.hkjt.arap.account.ace.bp.AceHk_arap_accoutUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_arap_accoutPubServiceImpl {
	// ����
	public AccountBillVO[] pubinsertBills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AccountBillVO> transferTool = new BillTransferTool<AccountBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_arap_accoutInsertBP action = new AceHk_arap_accoutInsertBP();
			AccountBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_arap_accoutDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AccountBillVO[] pubupdateBills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AccountBillVO> transferTool = new BillTransferTool<AccountBillVO>(
					clientFullVOs);
			AceHk_arap_accoutUpdateBP bp = new AceHk_arap_accoutUpdateBP();
			AccountBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AccountBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AccountBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AccountBillVO> query = new BillLazyQuery<AccountBillVO>(
					AccountBillVO.class);
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
	public AccountBillVO[] pubsendapprovebills(
			AccountBillVO[] clientFullVOs, AccountBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_accoutSendApproveBP bp = new AceHk_arap_accoutSendApproveBP();
		AccountBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AccountBillVO[] pubunsendapprovebills(
			AccountBillVO[] clientFullVOs, AccountBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_accoutUnSendApproveBP bp = new AceHk_arap_accoutUnSendApproveBP();
		AccountBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AccountBillVO[] pubapprovebills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_accoutApproveBP bp = new AceHk_arap_accoutApproveBP();
		AccountBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AccountBillVO[] pubunapprovebills(AccountBillVO[] clientFullVOs,
			AccountBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_accoutUnApproveBP bp = new AceHk_arap_accoutUnApproveBP();
		AccountBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}