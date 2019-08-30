package nc.impl.pub.ace;

import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyInsertBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyUpdateBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyDeleteBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdySendApproveBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyUnSendApproveBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyApproveBP;
import nc.bs.hkjt.zulin.sjdy.ace.bp.AceHk_zulin_sjdyUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_sjdyPubServiceImpl {
	// ����
	public SjdyBillVO[] pubinsertBills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<SjdyBillVO> transferTool = new BillTransferTool<SjdyBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_zulin_sjdyInsertBP action = new AceHk_zulin_sjdyInsertBP();
			SjdyBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_zulin_sjdyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public SjdyBillVO[] pubupdateBills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<SjdyBillVO> transferTool = new BillTransferTool<SjdyBillVO>(
					clientFullVOs);
			AceHk_zulin_sjdyUpdateBP bp = new AceHk_zulin_sjdyUpdateBP();
			SjdyBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public SjdyBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		SjdyBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<SjdyBillVO> query = new BillLazyQuery<SjdyBillVO>(
					SjdyBillVO.class);
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
	public SjdyBillVO[] pubsendapprovebills(
			SjdyBillVO[] clientFullVOs, SjdyBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sjdySendApproveBP bp = new AceHk_zulin_sjdySendApproveBP();
		SjdyBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public SjdyBillVO[] pubunsendapprovebills(
			SjdyBillVO[] clientFullVOs, SjdyBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sjdyUnSendApproveBP bp = new AceHk_zulin_sjdyUnSendApproveBP();
		SjdyBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public SjdyBillVO[] pubapprovebills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sjdyApproveBP bp = new AceHk_zulin_sjdyApproveBP();
		SjdyBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public SjdyBillVO[] pubunapprovebills(SjdyBillVO[] clientFullVOs,
			SjdyBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sjdyUnApproveBP bp = new AceHk_zulin_sjdyUnApproveBP();
		SjdyBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}