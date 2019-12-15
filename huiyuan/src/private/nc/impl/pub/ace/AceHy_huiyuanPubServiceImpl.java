package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanInsertBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanUpdateBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanDeleteBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanSendApproveBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanApproveBP;
import nc.bs.hkjt.huiyuan.kadangan.ace.bp.AceHy_huiyuanUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_huiyuanPubServiceImpl {
	// ����
	public KadanganBillVO[] pubinsertBills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<KadanganBillVO> transferTool = new BillTransferTool<KadanganBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_huiyuanInsertBP action = new AceHy_huiyuanInsertBP();
			KadanganBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_huiyuanDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public KadanganBillVO[] pubupdateBills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<KadanganBillVO> transferTool = new BillTransferTool<KadanganBillVO>(
					clientFullVOs);
			AceHy_huiyuanUpdateBP bp = new AceHy_huiyuanUpdateBP();
			KadanganBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KadanganBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KadanganBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KadanganBillVO> query = new BillLazyQuery<KadanganBillVO>(
					KadanganBillVO.class);
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
	public KadanganBillVO[] pubsendapprovebills(
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills)
			throws BusinessException {
		AceHy_huiyuanSendApproveBP bp = new AceHy_huiyuanSendApproveBP();
		KadanganBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public KadanganBillVO[] pubunsendapprovebills(
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills)
			throws BusinessException {
		AceHy_huiyuanUnSendApproveBP bp = new AceHy_huiyuanUnSendApproveBP();
		KadanganBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public KadanganBillVO[] pubapprovebills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huiyuanApproveBP bp = new AceHy_huiyuanApproveBP();
		KadanganBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public KadanganBillVO[] pubunapprovebills(KadanganBillVO[] clientFullVOs,
			KadanganBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huiyuanUnApproveBP bp = new AceHy_huiyuanUnApproveBP();
		KadanganBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}