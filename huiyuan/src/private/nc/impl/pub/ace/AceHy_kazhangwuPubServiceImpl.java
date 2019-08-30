package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kazhangwu.ace.bp.AceHy_kazhangwuInsertBP;
import nc.bs.hkjt.huiyuan.kazhangwu.ace.bp.AceHy_kazhangwuUpdateBP;
import nc.bs.hkjt.huiyuan.kazhangwu.ace.bp.AceHy_kazhangwuDeleteBP;
import nc.bs.hkjt.huiyuan.kazhangwu.ace.bp.AceHy_kazhangwuSendApproveBP;
import nc.bs.hkjt.huiyuan.kazhangwu.ace.bp.AceHy_kazhangwuUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kazhangwu.ace.bp.AceHy_kazhangwuApproveBP;
import nc.bs.hkjt.huiyuan.kazhangwu.ace.bp.AceHy_kazhangwuUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kazhangwuPubServiceImpl {
	// ����
	public KazhangwuBillVO[] pubinsertBills(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<KazhangwuBillVO> transferTool = new BillTransferTool<KazhangwuBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_kazhangwuInsertBP action = new AceHy_kazhangwuInsertBP();
			KazhangwuBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_kazhangwuDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public KazhangwuBillVO[] pubupdateBills(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<KazhangwuBillVO> transferTool = new BillTransferTool<KazhangwuBillVO>(
					clientFullVOs);
			AceHy_kazhangwuUpdateBP bp = new AceHy_kazhangwuUpdateBP();
			KazhangwuBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KazhangwuBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KazhangwuBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KazhangwuBillVO> query = new BillLazyQuery<KazhangwuBillVO>(
					KazhangwuBillVO.class);
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
	public KazhangwuBillVO[] pubsendapprovebills(
			KazhangwuBillVO[] clientFullVOs, KazhangwuBillVO[] originBills)
			throws BusinessException {
		AceHy_kazhangwuSendApproveBP bp = new AceHy_kazhangwuSendApproveBP();
		KazhangwuBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public KazhangwuBillVO[] pubunsendapprovebills(
			KazhangwuBillVO[] clientFullVOs, KazhangwuBillVO[] originBills)
			throws BusinessException {
		AceHy_kazhangwuUnSendApproveBP bp = new AceHy_kazhangwuUnSendApproveBP();
		KazhangwuBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public KazhangwuBillVO[] pubapprovebills(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazhangwuApproveBP bp = new AceHy_kazhangwuApproveBP();
		KazhangwuBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public KazhangwuBillVO[] pubunapprovebills(KazhangwuBillVO[] clientFullVOs,
			KazhangwuBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazhangwuUnApproveBP bp = new AceHy_kazhangwuUnApproveBP();
		KazhangwuBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}