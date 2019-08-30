package nc.impl.pub.ace;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp.AceHg_yyribaoInsertBP;
import nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp.AceHg_yyribaoUpdateBP;
import nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp.AceHg_yyribaoDeleteBP;
import nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp.AceHg_yyribaoSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp.AceHg_yyribaoUnSendApproveBP;
import nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp.AceHg_yyribaoApproveBP;
import nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp.AceHg_yyribaoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHg_yyribaoPubServiceImpl {
	// ����
	public YyribaoBillVO[] pubinsertBills(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<YyribaoBillVO> transferTool = new BillTransferTool<YyribaoBillVO>(
					clientFullVOs);
			// ����BP
			AceHg_yyribaoInsertBP action = new AceHg_yyribaoInsertBP();
			YyribaoBillVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceHg_yyribaoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public YyribaoBillVO[] pubupdateBills(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<YyribaoBillVO> transferTool = new BillTransferTool<YyribaoBillVO>(
					clientFullVOs);
			AceHg_yyribaoUpdateBP bp = new AceHg_yyribaoUpdateBP();
			YyribaoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public YyribaoBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		YyribaoBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<YyribaoBillVO> query = new BillLazyQuery<YyribaoBillVO>(
					YyribaoBillVO.class);
			query.setOrderAttribute(YyribaoBVO.class, new String[]{"vrowno"});	// ���尴 �к�����
			bills = query.query(queryScheme,null );
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
	public YyribaoBillVO[] pubsendapprovebills(
			YyribaoBillVO[] clientFullVOs, YyribaoBillVO[] originBills)
			throws BusinessException {
		AceHg_yyribaoSendApproveBP bp = new AceHg_yyribaoSendApproveBP();
		YyribaoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public YyribaoBillVO[] pubunsendapprovebills(
			YyribaoBillVO[] clientFullVOs, YyribaoBillVO[] originBills)
			throws BusinessException {
		AceHg_yyribaoUnSendApproveBP bp = new AceHg_yyribaoUnSendApproveBP();
		YyribaoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public YyribaoBillVO[] pubapprovebills(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHg_yyribaoApproveBP bp = new AceHg_yyribaoApproveBP();
		YyribaoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public YyribaoBillVO[] pubunapprovebills(YyribaoBillVO[] clientFullVOs,
			YyribaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
			
			/**
			 * HK 2018��11��5��17:43:25
			 * ���������ƾ֤  ������ɾ���� ������Ҫɾ��ƾ֤��
			 */
			String PK = clientFullVOs[i].getParentVO().getPk_hk_srgk_hg_yyribao();
			StringBuffer querySQL = 
				new StringBuffer("SELECT ls.src_freedef1 ")
						.append(" FROM fip_operatinglog ls ")
						.append(" WHERE src_relationid in ( '"+PK+"' ) and src_billtype = 'HK06' ")
						.append(" union all ")
						.append(" SELECT zs.src_freedef1 ")
						.append(" FROM fip_relation zs ")
						.append(" WHERE src_relationid in ( '"+PK+"' ) and src_billtype = 'HK06' ")
			;
			BaseDAO dao = new BaseDAO();
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null && list.size()>0)
			{
				throw new BusinessException("����ɾ��ƾ֤");
			}
			/***END***/
			
		}
		AceHg_yyribaoUnApproveBP bp = new AceHg_yyribaoUnApproveBP();
		YyribaoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}