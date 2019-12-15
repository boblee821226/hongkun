package nc.impl.pub.ace;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp.AceHg_sgshujuInsertBP;
import nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp.AceHg_sgshujuUpdateBP;
import nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp.AceHg_sgshujuDeleteBP;
import nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp.AceHg_sgshujuSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp.AceHg_sgshujuUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp.AceHg_sgshujuApproveBP;
import nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp.AceHg_sgshujuUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_sgshujuPubServiceImpl {
	// ����
	public SgshujuBillVO[] pubinsertBills(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<SgshujuBillVO> transferTool = new BillTransferTool<SgshujuBillVO>(
					clientFullVOs);
			// ����BP
			AceHg_sgshujuInsertBP action = new AceHg_sgshujuInsertBP();
			SgshujuBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHg_sgshujuDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public SgshujuBillVO[] pubupdateBills(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<SgshujuBillVO> transferTool = new BillTransferTool<SgshujuBillVO>(
					clientFullVOs);
			AceHg_sgshujuUpdateBP bp = new AceHg_sgshujuUpdateBP();
			SgshujuBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public SgshujuBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		SgshujuBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<SgshujuBillVO> query = new BillLazyQuery<SgshujuBillVO>(
					SgshujuBillVO.class);
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
	public SgshujuBillVO[] pubsendapprovebills(
			SgshujuBillVO[] clientFullVOs, SgshujuBillVO[] originBills)
			throws BusinessException {
		AceHg_sgshujuSendApproveBP bp = new AceHg_sgshujuSendApproveBP();
		SgshujuBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public SgshujuBillVO[] pubunsendapprovebills(
			SgshujuBillVO[] clientFullVOs, SgshujuBillVO[] originBills)
			throws BusinessException {
		AceHg_sgshujuUnSendApproveBP bp = new AceHg_sgshujuUnSendApproveBP();
		SgshujuBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public SgshujuBillVO[] pubapprovebills(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_sgshujuApproveBP bp = new AceHg_sgshujuApproveBP();
		SgshujuBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public SgshujuBillVO[] pubunapprovebills(SgshujuBillVO[] clientFullVOs,
			SgshujuBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
			
			/**
			 * ����ʱ�ж�  ͬһ�� �Ƿ���� Ӫҵ�ձ����������  ��������
			 * 2018��11��7��16:16:52
			 */
			String pk_org = clientFullVOs[i].getParentVO().getPk_org();
			String dbilldate = clientFullVOs[i].getParentVO().getDbilldate().toString().substring(0, 10);	// yyyy-mm-dd
			UFBoolean isjd = PuPubVO.getUFBoolean_NullAs(clientFullVOs[i].getParentVO().getVdef10(), UFBoolean.FALSE);	// �Ƿ�Ƶ�
			
			StringBuffer querySQL = 
				new StringBuffer(" select rb.vbillcode ")
						.append(" from hk_srgk_hg_yyribao rb ")
						.append(" where rb.dr=0 ")
						.append(" and rb.pk_org = '"+pk_org+"' ")
						.append(" and substr(rb.dbilldate,1,10) = '"+dbilldate+"' ")
						.append(" and nvl(replace(rb.vdef10,'~',''),'N') ='"+isjd.toString()+"' ")
			;
			BaseDAO dao = new BaseDAO();
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null&&list.size()>0)
			{
				throw new BusinessException("����Ӫҵ�ձ�����������");
			}
			/***END***/
			
		}
		AceHg_sgshujuUnApproveBP bp = new AceHg_sgshujuUnApproveBP();
		SgshujuBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}