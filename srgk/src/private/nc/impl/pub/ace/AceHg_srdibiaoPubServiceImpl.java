package nc.impl.pub.ace;

import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoDeleteBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoInsertBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoUnApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp.AceHg_srdibiaoUpdateBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_srdibiaoPubServiceImpl {
	// ����
	public SrdibiaoBillVO[] pubinsertBills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<SrdibiaoBillVO> transferTool = new BillTransferTool<SrdibiaoBillVO>(
					clientFullVOs);
			// ����BP
			AceHg_srdibiaoInsertBP action = new AceHg_srdibiaoInsertBP();
			SrdibiaoBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHg_srdibiaoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public SrdibiaoBillVO[] pubupdateBills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<SrdibiaoBillVO> transferTool = new BillTransferTool<SrdibiaoBillVO>(
					clientFullVOs);
			AceHg_srdibiaoUpdateBP bp = new AceHg_srdibiaoUpdateBP();
			SrdibiaoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public SrdibiaoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		SrdibiaoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<SrdibiaoBillVO> query = new BillLazyQuery<SrdibiaoBillVO>(
					SrdibiaoBillVO.class);
			query.setOrderAttribute(SrdibiaoBVO.class, new String[]{"vrowno"});	// ���尴 �к�����
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
	public SrdibiaoBillVO[] pubsendapprovebills(
			SrdibiaoBillVO[] clientFullVOs, SrdibiaoBillVO[] originBills)
			throws BusinessException {
		AceHg_srdibiaoSendApproveBP bp = new AceHg_srdibiaoSendApproveBP();
		SrdibiaoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public SrdibiaoBillVO[] pubunsendapprovebills(
			SrdibiaoBillVO[] clientFullVOs, SrdibiaoBillVO[] originBills)
			throws BusinessException {
		AceHg_srdibiaoUnSendApproveBP bp = new AceHg_srdibiaoUnSendApproveBP();
		SrdibiaoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public SrdibiaoBillVO[] pubapprovebills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_srdibiaoApproveBP bp = new AceHg_srdibiaoApproveBP();
		SrdibiaoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public SrdibiaoBillVO[] pubunapprovebills(SrdibiaoBillVO[] clientFullVOs,
			SrdibiaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_srdibiaoUnApproveBP bp = new AceHg_srdibiaoUnApproveBP();
		SrdibiaoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}