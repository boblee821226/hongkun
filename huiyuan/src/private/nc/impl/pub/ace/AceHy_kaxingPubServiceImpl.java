package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingInsertBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingUpdateBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingDeleteBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingSendApproveBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingApproveBP;
import nc.bs.hkjt.huiyuan.kaxing.ace.bp.AceHy_kaxingUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kaxingPubServiceImpl {
	// ����
	public KaxingBillVO[] pubinsertBills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<KaxingBillVO> transferTool = new BillTransferTool<KaxingBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_kaxingInsertBP action = new AceHy_kaxingInsertBP();
			KaxingBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_kaxingDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public KaxingBillVO[] pubupdateBills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<KaxingBillVO> transferTool = new BillTransferTool<KaxingBillVO>(
					clientFullVOs);
			AceHy_kaxingUpdateBP bp = new AceHy_kaxingUpdateBP();
			KaxingBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KaxingBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KaxingBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KaxingBillVO> query = new BillLazyQuery<KaxingBillVO>(
					KaxingBillVO.class);
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
	public KaxingBillVO[] pubsendapprovebills(
			KaxingBillVO[] clientFullVOs, KaxingBillVO[] originBills)
			throws BusinessException {
		AceHy_kaxingSendApproveBP bp = new AceHy_kaxingSendApproveBP();
		KaxingBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public KaxingBillVO[] pubunsendapprovebills(
			KaxingBillVO[] clientFullVOs, KaxingBillVO[] originBills)
			throws BusinessException {
		AceHy_kaxingUnSendApproveBP bp = new AceHy_kaxingUnSendApproveBP();
		KaxingBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public KaxingBillVO[] pubapprovebills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaxingApproveBP bp = new AceHy_kaxingApproveBP();
		KaxingBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public KaxingBillVO[] pubunapprovebills(KaxingBillVO[] clientFullVOs,
			KaxingBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kaxingUnApproveBP bp = new AceHy_kaxingUnApproveBP();
		KaxingBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}