package nc.impl.pub.ace;

import nc.bs.hkjt.arap.unit.ace.bp.AceHk_arap_unitInsertBP;
import nc.bs.hkjt.arap.unit.ace.bp.AceHk_arap_unitUpdateBP;
import nc.bs.hkjt.arap.unit.ace.bp.AceHk_arap_unitDeleteBP;
import nc.bs.hkjt.arap.unit.ace.bp.AceHk_arap_unitSendApproveBP;
import nc.bs.hkjt.arap.unit.ace.bp.AceHk_arap_unitUnSendApproveBP;
import nc.bs.hkjt.arap.unit.ace.bp.AceHk_arap_unitApproveBP;
import nc.bs.hkjt.arap.unit.ace.bp.AceHk_arap_unitUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_arap_unitPubServiceImpl {
	// ����
	public UnitBillVO[] pubinsertBills(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<UnitBillVO> transferTool = new BillTransferTool<UnitBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_arap_unitInsertBP action = new AceHk_arap_unitInsertBP();
			UnitBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_arap_unitDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public UnitBillVO[] pubupdateBills(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<UnitBillVO> transferTool = new BillTransferTool<UnitBillVO>(
					clientFullVOs);
			AceHk_arap_unitUpdateBP bp = new AceHk_arap_unitUpdateBP();
			UnitBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public UnitBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		UnitBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<UnitBillVO> query = new BillLazyQuery<UnitBillVO>(
					UnitBillVO.class);
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
	public UnitBillVO[] pubsendapprovebills(
			UnitBillVO[] clientFullVOs, UnitBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_unitSendApproveBP bp = new AceHk_arap_unitSendApproveBP();
		UnitBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public UnitBillVO[] pubunsendapprovebills(
			UnitBillVO[] clientFullVOs, UnitBillVO[] originBills)
			throws BusinessException {
		AceHk_arap_unitUnSendApproveBP bp = new AceHk_arap_unitUnSendApproveBP();
		UnitBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public UnitBillVO[] pubapprovebills(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_unitApproveBP bp = new AceHk_arap_unitApproveBP();
		UnitBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public UnitBillVO[] pubunapprovebills(UnitBillVO[] clientFullVOs,
			UnitBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_arap_unitUnApproveBP bp = new AceHk_arap_unitUnApproveBP();
		UnitBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}