package nc.impl.pub.ace;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmInsertBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmUpdateBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmDeleteBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmSendApproveBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmUnSendApproveBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmApproveBP;
import nc.bs.hkjt.zulin.znjjm.ace.bp.AceHk_zulin_znjjmUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBVO;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_znjjmPubServiceImpl {
	// ����
	public ZnjjmBillVO[] pubinsertBills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<ZnjjmBillVO> transferTool = new BillTransferTool<ZnjjmBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_zulin_znjjmInsertBP action = new AceHk_zulin_znjjmInsertBP();
			ZnjjmBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_zulin_znjjmDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public ZnjjmBillVO[] pubupdateBills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<ZnjjmBillVO> transferTool = new BillTransferTool<ZnjjmBillVO>(
					clientFullVOs);
			AceHk_zulin_znjjmUpdateBP bp = new AceHk_zulin_znjjmUpdateBP();
			ZnjjmBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public ZnjjmBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		ZnjjmBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<ZnjjmBillVO> query = new BillLazyQuery<ZnjjmBillVO>(
					ZnjjmBillVO.class);
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
	public ZnjjmBillVO[] pubsendapprovebills(
			ZnjjmBillVO[] clientFullVOs, ZnjjmBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjmSendApproveBP bp = new AceHk_zulin_znjjmSendApproveBP();
		ZnjjmBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public ZnjjmBillVO[] pubunsendapprovebills(
			ZnjjmBillVO[] clientFullVOs, ZnjjmBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_znjjmUnSendApproveBP bp = new AceHk_zulin_znjjmUnSendApproveBP();
		ZnjjmBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public ZnjjmBillVO[] pubapprovebills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjmApproveBP bp = new AceHk_zulin_znjjmApproveBP();
		ZnjjmBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		
		return retvos;
	}

	// ����
	public ZnjjmBillVO[] pubunapprovebills(ZnjjmBillVO[] clientFullVOs,
			ZnjjmBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_znjjmUnApproveBP bp = new AceHk_zulin_znjjmUnApproveBP();
		ZnjjmBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		
		return retvos;
	}

}