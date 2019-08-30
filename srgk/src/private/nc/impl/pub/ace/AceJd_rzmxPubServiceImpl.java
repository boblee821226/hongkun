package nc.impl.pub.ace;

import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp.AceJd_rzmxInsertBP;
import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp.AceJd_rzmxUpdateBP;
import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp.AceJd_rzmxDeleteBP;
import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp.AceJd_rzmxSendApproveBP;
import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp.AceJd_rzmxUnSendApproveBP;
import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp.AceJd_rzmxApproveBP;
import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp.AceJd_rzmxUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceJd_rzmxPubServiceImpl {
	// ����
	public RzmxBillVO[] pubinsertBills(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<RzmxBillVO> transferTool = new BillTransferTool<RzmxBillVO>(
					clientFullVOs);
			// ����BP
			AceJd_rzmxInsertBP action = new AceJd_rzmxInsertBP();
			RzmxBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceJd_rzmxDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public RzmxBillVO[] pubupdateBills(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<RzmxBillVO> transferTool = new BillTransferTool<RzmxBillVO>(
					clientFullVOs);
			AceJd_rzmxUpdateBP bp = new AceJd_rzmxUpdateBP();
			RzmxBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public RzmxBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		RzmxBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<RzmxBillVO> query = new BillLazyQuery<RzmxBillVO>(
					RzmxBillVO.class);
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
	public RzmxBillVO[] pubsendapprovebills(
			RzmxBillVO[] clientFullVOs, RzmxBillVO[] originBills)
			throws BusinessException {
		AceJd_rzmxSendApproveBP bp = new AceJd_rzmxSendApproveBP();
		RzmxBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public RzmxBillVO[] pubunsendapprovebills(
			RzmxBillVO[] clientFullVOs, RzmxBillVO[] originBills)
			throws BusinessException {
		AceJd_rzmxUnSendApproveBP bp = new AceJd_rzmxUnSendApproveBP();
		RzmxBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public RzmxBillVO[] pubapprovebills(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceJd_rzmxApproveBP bp = new AceJd_rzmxApproveBP();
		RzmxBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public RzmxBillVO[] pubunapprovebills(RzmxBillVO[] clientFullVOs,
			RzmxBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceJd_rzmxUnApproveBP bp = new AceJd_rzmxUnApproveBP();
		RzmxBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}