package nc.impl.pub.ace;

import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanInsertBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanUpdateBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanDeleteBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanSendApproveBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanUnSendApproveBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanApproveBP;
import nc.bs.hkjt.jishi.shoudan.ace.bp.AceJs_shoudanUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceJs_shoudanPubServiceImpl {
	// ����
	public ShoudanBillVO[] pubinsertBills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<ShoudanBillVO> transferTool = new BillTransferTool<ShoudanBillVO>(
					clientFullVOs);
			// ����BP
			AceJs_shoudanInsertBP action = new AceJs_shoudanInsertBP();
			ShoudanBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceJs_shoudanDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public ShoudanBillVO[] pubupdateBills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<ShoudanBillVO> transferTool = new BillTransferTool<ShoudanBillVO>(
					clientFullVOs);
			AceJs_shoudanUpdateBP bp = new AceJs_shoudanUpdateBP();
			ShoudanBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public ShoudanBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		ShoudanBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<ShoudanBillVO> query = new BillLazyQuery<ShoudanBillVO>(
					ShoudanBillVO.class);
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
	public ShoudanBillVO[] pubsendapprovebills(
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills)
			throws BusinessException {
		AceJs_shoudanSendApproveBP bp = new AceJs_shoudanSendApproveBP();
		ShoudanBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public ShoudanBillVO[] pubunsendapprovebills(
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills)
			throws BusinessException {
		AceJs_shoudanUnSendApproveBP bp = new AceJs_shoudanUnSendApproveBP();
		ShoudanBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public ShoudanBillVO[] pubapprovebills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceJs_shoudanApproveBP bp = new AceJs_shoudanApproveBP();
		ShoudanBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public ShoudanBillVO[] pubunapprovebills(ShoudanBillVO[] clientFullVOs,
			ShoudanBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceJs_shoudanUnApproveBP bp = new AceJs_shoudanUnApproveBP();
		ShoudanBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}