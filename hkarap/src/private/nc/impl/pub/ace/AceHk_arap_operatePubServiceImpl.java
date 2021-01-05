package nc.impl.pub.ace;

import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateInsertBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateUpdateBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateDeleteBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateSendApproveBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateUnSendApproveBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateApproveBP;
import nc.bs.hkjt.arap.operate.ace.bp.AceHk_arap_operateUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_arap_operatePubServiceImpl {
	// ����
	public OperateBillVO[] pubinsertBills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<OperateBillVO> transferTool = new BillTransferTool<OperateBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_arap_operateInsertBP action = new AceHk_arap_operateInsertBP();
			OperateBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_arap_operateDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public OperateBillVO[] pubupdateBills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<OperateBillVO> transferTool = new BillTransferTool<OperateBillVO>(
					clientFullVOs);
			AceHk_arap_operateUpdateBP bp = new AceHk_arap_operateUpdateBP();
			OperateBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public OperateBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		OperateBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<OperateBillVO> query = new BillLazyQuery<OperateBillVO>(
					OperateBillVO.class);
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
	public OperateBillVO[] pubsendapprovebills(
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_operateSendApproveBP bp = new AceHk_arap_operateSendApproveBP();
		OperateBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public OperateBillVO[] pubunsendapprovebills(
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_operateUnSendApproveBP bp = new AceHk_arap_operateUnSendApproveBP();
		OperateBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public OperateBillVO[] pubapprovebills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_operateApproveBP bp = new AceHk_arap_operateApproveBP();
		OperateBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public OperateBillVO[] pubunapprovebills(OperateBillVO[] clientFullVOs,
			OperateBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_operateUnApproveBP bp = new AceHk_arap_operateUnApproveBP();
		OperateBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}