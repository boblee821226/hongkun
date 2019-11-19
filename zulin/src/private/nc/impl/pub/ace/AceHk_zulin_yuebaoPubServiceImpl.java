package nc.impl.pub.ace;

import nc.bs.hkjt.zulin.bkfyft.ace.bp.AceHk_zulin_yuebaoInsertBP;
import nc.bs.hkjt.zulin.bkfyft.ace.bp.AceHk_zulin_yuebaoUpdateBP;
import nc.bs.hkjt.zulin.bkfyft.ace.bp.AceHk_zulin_yuebaoDeleteBP;
import nc.bs.hkjt.zulin.bkfyft.ace.bp.AceHk_zulin_yuebaoSendApproveBP;
import nc.bs.hkjt.zulin.bkfyft.ace.bp.AceHk_zulin_yuebaoUnSendApproveBP;
import nc.bs.hkjt.zulin.bkfyft.ace.bp.AceHk_zulin_yuebaoApproveBP;
import nc.bs.hkjt.zulin.bkfyft.ace.bp.AceHk_zulin_yuebaoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_yuebaoPubServiceImpl {
	// ����
	public YuebaoBillVO[] pubinsertBills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<YuebaoBillVO> transferTool = new BillTransferTool<YuebaoBillVO>(
					clientFullVOs);
			// ����BP
			AceHk_zulin_yuebaoInsertBP action = new AceHk_zulin_yuebaoInsertBP();
			YuebaoBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHk_zulin_yuebaoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public YuebaoBillVO[] pubupdateBills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<YuebaoBillVO> transferTool = new BillTransferTool<YuebaoBillVO>(
					clientFullVOs);
			AceHk_zulin_yuebaoUpdateBP bp = new AceHk_zulin_yuebaoUpdateBP();
			YuebaoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public YuebaoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		YuebaoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<YuebaoBillVO> query = new BillLazyQuery<YuebaoBillVO>(
					YuebaoBillVO.class);
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
	public YuebaoBillVO[] pubsendapprovebills(
			YuebaoBillVO[] clientFullVOs, YuebaoBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_yuebaoSendApproveBP bp = new AceHk_zulin_yuebaoSendApproveBP();
		YuebaoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public YuebaoBillVO[] pubunsendapprovebills(
			YuebaoBillVO[] clientFullVOs, YuebaoBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_yuebaoUnSendApproveBP bp = new AceHk_zulin_yuebaoUnSendApproveBP();
		YuebaoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public YuebaoBillVO[] pubapprovebills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_yuebaoApproveBP bp = new AceHk_zulin_yuebaoApproveBP();
		YuebaoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public YuebaoBillVO[] pubunapprovebills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_yuebaoUnApproveBP bp = new AceHk_zulin_yuebaoUnApproveBP();
		YuebaoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}