package nc.impl.pub.ace;

import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaInsertBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaUpdateBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaDeleteBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaSendApproveBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaUnSendApproveBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaApproveBP;
import nc.bs.hkjt.huiyuan.huanka.ace.bp.AceHy_huankaUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHy_huankaPubServiceImpl {
	// ����
	public HuankaBillVO[] pubinsertBills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<HuankaBillVO> transferTool = new BillTransferTool<HuankaBillVO>(
					clientFullVOs);
			// ����BP
			AceHy_huankaInsertBP action = new AceHy_huankaInsertBP();
			HuankaBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHy_huankaDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public HuankaBillVO[] pubupdateBills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<HuankaBillVO> transferTool = new BillTransferTool<HuankaBillVO>(
					clientFullVOs);
			AceHy_huankaUpdateBP bp = new AceHy_huankaUpdateBP();
			HuankaBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public HuankaBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		HuankaBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<HuankaBillVO> query = new BillLazyQuery<HuankaBillVO>(
					HuankaBillVO.class);
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
	public HuankaBillVO[] pubsendapprovebills(
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills)
			throws BusinessException {
		AceHy_huankaSendApproveBP bp = new AceHy_huankaSendApproveBP();
		HuankaBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public HuankaBillVO[] pubunsendapprovebills(
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills)
			throws BusinessException {
		AceHy_huankaUnSendApproveBP bp = new AceHy_huankaUnSendApproveBP();
		HuankaBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public HuankaBillVO[] pubapprovebills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huankaApproveBP bp = new AceHy_huankaApproveBP();
		HuankaBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public HuankaBillVO[] pubunapprovebills(HuankaBillVO[] clientFullVOs,
			HuankaBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHy_huankaUnApproveBP bp = new AceHy_huankaUnApproveBP();
		HuankaBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}