package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongInsertBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongUpdateBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongDeleteBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongSendApproveBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongUnSendApproveBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongApproveBP;
import nc.bs.hkjt.huiyuan.cikazong.ace.bp.AceHy_cikazongUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_cikazongPubServiceImpl {
	// ����
	public CikazongBillVO[] pubinsertBills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<CikazongBillVO> transferTool = new BillTransferTool<CikazongBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_cikazongInsertBP action = new AceHy_cikazongInsertBP();
			CikazongBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_cikazongDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public CikazongBillVO[] pubupdateBills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<CikazongBillVO> transferTool = new BillTransferTool<CikazongBillVO>(
					clientFullVOs);
			AceHy_cikazongUpdateBP bp = new AceHy_cikazongUpdateBP();
			CikazongBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public CikazongBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		CikazongBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<CikazongBillVO> query = new BillLazyQuery<CikazongBillVO>(
					CikazongBillVO.class);
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
	public CikazongBillVO[] pubsendapprovebills(
			CikazongBillVO[] clientFullVOs, CikazongBillVO[] originBills)
			throws BusinessException {
		AceHy_cikazongSendApproveBP bp = new AceHy_cikazongSendApproveBP();
		CikazongBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public CikazongBillVO[] pubunsendapprovebills(
			CikazongBillVO[] clientFullVOs, CikazongBillVO[] originBills)
			throws BusinessException {
		AceHy_cikazongUnSendApproveBP bp = new AceHy_cikazongUnSendApproveBP();
		CikazongBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public CikazongBillVO[] pubapprovebills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikazongApproveBP bp = new AceHy_cikazongApproveBP();
		CikazongBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public CikazongBillVO[] pubunapprovebills(CikazongBillVO[] clientFullVOs,
			CikazongBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_cikazongUnApproveBP bp = new AceHy_cikazongUnApproveBP();
		CikazongBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}