package nc.impl.pub.ace;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.hkjt.zulin.bkfyft.ace.bp.*;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_yuebaoPubServiceImpl {
	// 新增
	public YuebaoBillVO[] pubinsertBills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<YuebaoBillVO> transferTool = new BillTransferTool<YuebaoBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_zulin_yuebaoInsertBP action = new AceHk_zulin_yuebaoInsertBP();
			YuebaoBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_zulin_yuebaoDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public YuebaoBillVO[] pubupdateBills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<YuebaoBillVO> transferTool = new BillTransferTool<YuebaoBillVO>(
					clientFullVOs);
			AceHk_zulin_yuebaoUpdateBP bp = new AceHk_zulin_yuebaoUpdateBP();
			YuebaoBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public YuebaoBillVO[] pubsendapprovebills(
			YuebaoBillVO[] clientFullVOs, YuebaoBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_yuebaoSendApproveBP bp = new AceHk_zulin_yuebaoSendApproveBP();
		YuebaoBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public YuebaoBillVO[] pubunsendapprovebills(
			YuebaoBillVO[] clientFullVOs, YuebaoBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_yuebaoUnSendApproveBP bp = new AceHk_zulin_yuebaoUnSendApproveBP();
		YuebaoBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public YuebaoBillVO[] pubapprovebills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_yuebaoApproveBP bp = new AceHk_zulin_yuebaoApproveBP();
		YuebaoBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public YuebaoBillVO[] pubunapprovebills(YuebaoBillVO[] clientFullVOs,
			YuebaoBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
			
			/**
			 * HK 2018年11月5日17:43:25
			 * 如果生成了凭证  则不允许删除。 必须先要删除凭证。
			 */
			String PK = clientFullVOs[i].getParentVO().getPk_hk_zulin_yuebao();
			StringBuffer querySQL = 
				new StringBuffer("SELECT ls.src_freedef1 ")
						.append(" FROM fip_operatinglog ls ")
						.append(" WHERE src_relationid in ( '"+PK+"' ) and src_billtype = 'HK37' ")
						.append(" union all ")
						.append(" SELECT zs.src_freedef1 ")
						.append(" FROM fip_relation zs ")
						.append(" WHERE src_relationid in ( '"+PK+"' ) and src_billtype = 'HK37' ")
			;
			BaseDAO dao = new BaseDAO();
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null && list.size()>0)
			{
				throw new BusinessException("请先删除凭证");
			}
			/***END***/
			
		}
		AceHk_zulin_yuebaoUnApproveBP bp = new AceHk_zulin_yuebaoUnApproveBP();
		YuebaoBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}