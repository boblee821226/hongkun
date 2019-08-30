package nc.impl.pub.ace;

import nc.bs.hkjt.zulin.tiaozheng.ace.bp.AceHk_zulin_tiaozhengInsertBP;
import nc.bs.hkjt.zulin.tiaozheng.ace.bp.AceHk_zulin_tiaozhengUpdateBP;
import nc.bs.hkjt.zulin.tiaozheng.ace.bp.AceHk_zulin_tiaozhengDeleteBP;
import nc.bs.hkjt.zulin.tiaozheng.ace.bp.AceHk_zulin_tiaozhengSendApproveBP;
import nc.bs.hkjt.zulin.tiaozheng.ace.bp.AceHk_zulin_tiaozhengUnSendApproveBP;
import nc.bs.hkjt.zulin.tiaozheng.ace.bp.AceHk_zulin_tiaozhengApproveBP;
import nc.bs.hkjt.zulin.tiaozheng.ace.bp.AceHk_zulin_tiaozhengUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_tiaozhengPubServiceImpl {
	// ����
	public TzBillVO[] pubinsertBills(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<TzBillVO> transferTool = new BillTransferTool<TzBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_zulin_tiaozhengInsertBP action = new AceHk_zulin_tiaozhengInsertBP();
			TzBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_zulin_tiaozhengDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public TzBillVO[] pubupdateBills(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<TzBillVO> transferTool = new BillTransferTool<TzBillVO>(
					clientFullVOs);
			AceHk_zulin_tiaozhengUpdateBP bp = new AceHk_zulin_tiaozhengUpdateBP();
			TzBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public TzBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		TzBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<TzBillVO> query = new BillLazyQuery<TzBillVO>(
					TzBillVO.class);
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
	public TzBillVO[] pubsendapprovebills(
			TzBillVO[] clientFullVOs, TzBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_tiaozhengSendApproveBP bp = new AceHk_zulin_tiaozhengSendApproveBP();
		TzBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public TzBillVO[] pubunsendapprovebills(
			TzBillVO[] clientFullVOs, TzBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_tiaozhengUnSendApproveBP bp = new AceHk_zulin_tiaozhengUnSendApproveBP();
		TzBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public TzBillVO[] pubapprovebills(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_tiaozhengApproveBP bp = new AceHk_zulin_tiaozhengApproveBP();
		TzBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public TzBillVO[] pubunapprovebills(TzBillVO[] clientFullVOs,
			TzBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_tiaozhengUnApproveBP bp = new AceHk_zulin_tiaozhengUnApproveBP();
		TzBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}