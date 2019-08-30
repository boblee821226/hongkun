package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiInsertBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiUpdateBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiDeleteBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiSendApproveBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiApproveBP;
import nc.bs.hkjt.huiyuan.kazuofei.ace.bp.AceHy_kazuofeiUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kazuofeiPubServiceImpl {
	// ����
	public KazuofeiBillVO[] pubinsertBills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<KazuofeiBillVO> transferTool = new BillTransferTool<KazuofeiBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_kazuofeiInsertBP action = new AceHy_kazuofeiInsertBP();
			KazuofeiBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_kazuofeiDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public KazuofeiBillVO[] pubupdateBills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<KazuofeiBillVO> transferTool = new BillTransferTool<KazuofeiBillVO>(
					clientFullVOs);
			AceHy_kazuofeiUpdateBP bp = new AceHy_kazuofeiUpdateBP();
			KazuofeiBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KazuofeiBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KazuofeiBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KazuofeiBillVO> query = new BillLazyQuery<KazuofeiBillVO>(
					KazuofeiBillVO.class);
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
	public KazuofeiBillVO[] pubsendapprovebills(
			KazuofeiBillVO[] clientFullVOs, KazuofeiBillVO[] originBills)
			throws BusinessException {
		AceHy_kazuofeiSendApproveBP bp = new AceHy_kazuofeiSendApproveBP();
		KazuofeiBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public KazuofeiBillVO[] pubunsendapprovebills(
			KazuofeiBillVO[] clientFullVOs, KazuofeiBillVO[] originBills)
			throws BusinessException {
		AceHy_kazuofeiUnSendApproveBP bp = new AceHy_kazuofeiUnSendApproveBP();
		KazuofeiBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public KazuofeiBillVO[] pubapprovebills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazuofeiApproveBP bp = new AceHy_kazuofeiApproveBP();
		KazuofeiBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public KazuofeiBillVO[] pubunapprovebills(KazuofeiBillVO[] clientFullVOs,
			KazuofeiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kazuofeiUnApproveBP bp = new AceHy_kazuofeiUnApproveBP();
		KazuofeiBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}