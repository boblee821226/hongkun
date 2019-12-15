package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoInsertBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoUpdateBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoDeleteBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoSendApproveBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoUnSendApproveBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoApproveBP;
import nc.bs.hkjt.huiyuan.cikainfo.ace.bp.AceHy_cikainfoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_cikainfoPubServiceImpl {
	// ����
	public CikainfoBillVO[] pubinsertBills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<CikainfoBillVO> transferTool = new BillTransferTool<CikainfoBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_cikainfoInsertBP action = new AceHy_cikainfoInsertBP();
			CikainfoBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_cikainfoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public CikainfoBillVO[] pubupdateBills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<CikainfoBillVO> transferTool = new BillTransferTool<CikainfoBillVO>(
					clientFullVOs);
			AceHy_cikainfoUpdateBP bp = new AceHy_cikainfoUpdateBP();
			CikainfoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CikainfoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CikainfoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CikainfoBillVO> query = new BillLazyQuery<CikainfoBillVO>(
					CikainfoBillVO.class);
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
	public CikainfoBillVO[] pubsendapprovebills(
			CikainfoBillVO[] clientFullVOs, CikainfoBillVO[] originBills)
			throws BusinessException {
		AceHy_cikainfoSendApproveBP bp = new AceHy_cikainfoSendApproveBP();
		CikainfoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public CikainfoBillVO[] pubunsendapprovebills(
			CikainfoBillVO[] clientFullVOs, CikainfoBillVO[] originBills)
			throws BusinessException {
		AceHy_cikainfoUnSendApproveBP bp = new AceHy_cikainfoUnSendApproveBP();
		CikainfoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public CikainfoBillVO[] pubapprovebills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikainfoApproveBP bp = new AceHy_cikainfoApproveBP();
		CikainfoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public CikainfoBillVO[] pubunapprovebills(CikainfoBillVO[] clientFullVOs,
			CikainfoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikainfoUnApproveBP bp = new AceHy_cikainfoUnApproveBP();
		CikainfoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}