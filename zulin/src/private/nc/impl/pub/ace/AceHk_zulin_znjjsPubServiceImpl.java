package nc.impl.pub.ace;

import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsInsertBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsUpdateBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsDeleteBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsSendApproveBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsUnSendApproveBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsApproveBP;
import nc.bs.hkjt.zulin.znjjs.ace.bp.AceHk_zulin_znjjsUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_znjjsPubServiceImpl {
	// ����
	public ZnjjsBillVO[] pubinsertBills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<ZnjjsBillVO> transferTool = new BillTransferTool<ZnjjsBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_zulin_znjjsInsertBP action = new AceHk_zulin_znjjsInsertBP();
			ZnjjsBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_zulin_znjjsDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public ZnjjsBillVO[] pubupdateBills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<ZnjjsBillVO> transferTool = new BillTransferTool<ZnjjsBillVO>(
					clientFullVOs);
			AceHk_zulin_znjjsUpdateBP bp = new AceHk_zulin_znjjsUpdateBP();
			ZnjjsBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public ZnjjsBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		ZnjjsBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<ZnjjsBillVO> query = new BillLazyQuery<ZnjjsBillVO>(
					ZnjjsBillVO.class);
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
	public ZnjjsBillVO[] pubsendapprovebills(
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjsSendApproveBP bp = new AceHk_zulin_znjjsSendApproveBP();
		ZnjjsBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public ZnjjsBillVO[] pubunsendapprovebills(
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjsUnSendApproveBP bp = new AceHk_zulin_znjjsUnSendApproveBP();
		ZnjjsBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public ZnjjsBillVO[] pubapprovebills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjsApproveBP bp = new AceHk_zulin_znjjsApproveBP();
		ZnjjsBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public ZnjjsBillVO[] pubunapprovebills(ZnjjsBillVO[] clientFullVOs,
			ZnjjsBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjsUnApproveBP bp = new AceHk_zulin_znjjsUnApproveBP();
		ZnjjsBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}