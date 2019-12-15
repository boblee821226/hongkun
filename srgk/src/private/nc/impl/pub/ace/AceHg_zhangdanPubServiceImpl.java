package nc.impl.pub.ace;

import nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp.AceHg_zhangdanInsertBP;
import nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp.AceHg_zhangdanUpdateBP;
import nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp.AceHg_zhangdanDeleteBP;
import nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp.AceHg_zhangdanSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp.AceHg_zhangdanUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp.AceHg_zhangdanApproveBP;
import nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp.AceHg_zhangdanUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_zhangdanPubServiceImpl {
	// ����
	public ZhangdanBillVO[] pubinsertBills(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<ZhangdanBillVO> transferTool = new BillTransferTool<ZhangdanBillVO>(
					clientFullVOs);
			// ����BP
			AceHg_zhangdanInsertBP action = new AceHg_zhangdanInsertBP();
			ZhangdanBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHg_zhangdanDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public ZhangdanBillVO[] pubupdateBills(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<ZhangdanBillVO> transferTool = new BillTransferTool<ZhangdanBillVO>(
					clientFullVOs);
			AceHg_zhangdanUpdateBP bp = new AceHg_zhangdanUpdateBP();
			ZhangdanBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public ZhangdanBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		ZhangdanBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<ZhangdanBillVO> query = new BillLazyQuery<ZhangdanBillVO>(
					ZhangdanBillVO.class);
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
	public ZhangdanBillVO[] pubsendapprovebills(
			ZhangdanBillVO[] clientFullVOs, ZhangdanBillVO[] originBills)
			throws BusinessException {
		AceHg_zhangdanSendApproveBP bp = new AceHg_zhangdanSendApproveBP();
		ZhangdanBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public ZhangdanBillVO[] pubunsendapprovebills(
			ZhangdanBillVO[] clientFullVOs, ZhangdanBillVO[] originBills)
			throws BusinessException {
		AceHg_zhangdanUnSendApproveBP bp = new AceHg_zhangdanUnSendApproveBP();
		ZhangdanBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public ZhangdanBillVO[] pubapprovebills(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_zhangdanApproveBP bp = new AceHg_zhangdanApproveBP();
		ZhangdanBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public ZhangdanBillVO[] pubunapprovebills(ZhangdanBillVO[] clientFullVOs,
			ZhangdanBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_zhangdanUnApproveBP bp = new AceHg_zhangdanUnApproveBP();
		ZhangdanBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}