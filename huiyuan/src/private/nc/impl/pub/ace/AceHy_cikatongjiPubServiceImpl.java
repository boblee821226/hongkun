package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiInsertBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiUpdateBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiDeleteBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiSendApproveBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiUnSendApproveBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiApproveBP;
import nc.bs.hkjt.huiyuan.cikatongji.ace.bp.AceHy_cikatongjiUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_cikatongjiPubServiceImpl {
	// ����
	public CikatongjiBillVO[] pubinsertBills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<CikatongjiBillVO> transferTool = new BillTransferTool<CikatongjiBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_cikatongjiInsertBP action = new AceHy_cikatongjiInsertBP();
			CikatongjiBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_cikatongjiDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public CikatongjiBillVO[] pubupdateBills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<CikatongjiBillVO> transferTool = new BillTransferTool<CikatongjiBillVO>(
					clientFullVOs);
			AceHy_cikatongjiUpdateBP bp = new AceHy_cikatongjiUpdateBP();
			CikatongjiBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CikatongjiBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CikatongjiBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CikatongjiBillVO> query = new BillLazyQuery<CikatongjiBillVO>(
					CikatongjiBillVO.class);
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
	public CikatongjiBillVO[] pubsendapprovebills(
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills)
			throws BusinessException {
		AceHy_cikatongjiSendApproveBP bp = new AceHy_cikatongjiSendApproveBP();
		CikatongjiBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public CikatongjiBillVO[] pubunsendapprovebills(
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills)
			throws BusinessException {
		AceHy_cikatongjiUnSendApproveBP bp = new AceHy_cikatongjiUnSendApproveBP();
		CikatongjiBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public CikatongjiBillVO[] pubapprovebills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikatongjiApproveBP bp = new AceHy_cikatongjiApproveBP();
		CikatongjiBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public CikatongjiBillVO[] pubunapprovebills(CikatongjiBillVO[] clientFullVOs,
			CikatongjiBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikatongjiUnApproveBP bp = new AceHy_cikatongjiUnApproveBP();
		CikatongjiBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}