package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.kainfo.ace.bp.AceHy_kainfoInsertBP;
import nc.bs.hkjt.huiyuan.kainfo.ace.bp.AceHy_kainfoUpdateBP;
import nc.bs.hkjt.huiyuan.kainfo.ace.bp.AceHy_kainfoDeleteBP;
import nc.bs.hkjt.huiyuan.kainfo.ace.bp.AceHy_kainfoSendApproveBP;
import nc.bs.hkjt.huiyuan.kainfo.ace.bp.AceHy_kainfoUnSendApproveBP;
import nc.bs.hkjt.huiyuan.kainfo.ace.bp.AceHy_kainfoApproveBP;
import nc.bs.hkjt.huiyuan.kainfo.ace.bp.AceHy_kainfoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_kainfoPubServiceImpl {
	// ����
	public KainfoBillVO[] pubinsertBills(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<KainfoBillVO> transferTool = new BillTransferTool<KainfoBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_kainfoInsertBP action = new AceHy_kainfoInsertBP();
			KainfoBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_kainfoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public KainfoBillVO[] pubupdateBills(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<KainfoBillVO> transferTool = new BillTransferTool<KainfoBillVO>(
					clientFullVOs);
			AceHy_kainfoUpdateBP bp = new AceHy_kainfoUpdateBP();
			KainfoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public KainfoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		KainfoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<KainfoBillVO> query = new BillLazyQuery<KainfoBillVO>(
					KainfoBillVO.class);
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
	public KainfoBillVO[] pubsendapprovebills(
			KainfoBillVO[] clientFullVOs, KainfoBillVO[] originBills)
			throws BusinessException {
		AceHy_kainfoSendApproveBP bp = new AceHy_kainfoSendApproveBP();
		KainfoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public KainfoBillVO[] pubunsendapprovebills(
			KainfoBillVO[] clientFullVOs, KainfoBillVO[] originBills)
			throws BusinessException {
		AceHy_kainfoUnSendApproveBP bp = new AceHy_kainfoUnSendApproveBP();
		KainfoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public KainfoBillVO[] pubapprovebills(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kainfoApproveBP bp = new AceHy_kainfoApproveBP();
		KainfoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public KainfoBillVO[] pubunapprovebills(KainfoBillVO[] clientFullVOs,
			KainfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_kainfoUnApproveBP bp = new AceHy_kainfoUnApproveBP();
		KainfoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}