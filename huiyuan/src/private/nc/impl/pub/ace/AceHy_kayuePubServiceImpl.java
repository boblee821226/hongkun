package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueInsertBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueUpdateBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueDeleteBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueSendApproveBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueApproveBP;
import nc.bs.hkjt.huiyuan.kayue.ace.bp.AceHy_kayueUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kayuePubServiceImpl {
	// ����
	public KayueBillVO[] pubinsertBills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<KayueBillVO> transferTool = new BillTransferTool<KayueBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_kayueInsertBP action = new AceHy_kayueInsertBP();
			KayueBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_kayueDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public KayueBillVO[] pubupdateBills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<KayueBillVO> transferTool = new BillTransferTool<KayueBillVO>(
					clientFullVOs);
			AceHy_kayueUpdateBP bp = new AceHy_kayueUpdateBP();
			KayueBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KayueBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KayueBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KayueBillVO> query = new BillLazyQuery<KayueBillVO>(
					KayueBillVO.class);
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
	public KayueBillVO[] pubsendapprovebills(
			KayueBillVO[] clientFullVOs, KayueBillVO[] originBills)
			throws BusinessException {
		AceHy_kayueSendApproveBP bp = new AceHy_kayueSendApproveBP();
		KayueBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public KayueBillVO[] pubunsendapprovebills(
			KayueBillVO[] clientFullVOs, KayueBillVO[] originBills)
			throws BusinessException {
		AceHy_kayueUnSendApproveBP bp = new AceHy_kayueUnSendApproveBP();
		KayueBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public KayueBillVO[] pubapprovebills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kayueApproveBP bp = new AceHy_kayueApproveBP();
		KayueBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public KayueBillVO[] pubunapprovebills(KayueBillVO[] clientFullVOs,
			KayueBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kayueUnApproveBP bp = new AceHy_kayueUnApproveBP();
		KayueBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}