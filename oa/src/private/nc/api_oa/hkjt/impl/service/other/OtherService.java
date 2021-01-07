package nc.api_oa.hkjt.impl.service.other;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.trade.business.HYPubBO;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.uap.pf.IplatFormEntry;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.hkjt.oa.HkOaInfoVO;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class OtherService {
	
	private IplatFormEntry iplatFormEntry;
	private IplatFormEntry getIplatFormEntry() {
		if (iplatFormEntry == null) {
			iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		}
		return iplatFormEntry;
	}
	
	/**
	 * @param account
	 * @param billType
	 * @param paramObj
	 * @param action
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	public Object doAction(String account, String billType, Object paramObj, String action, String userId) 
			throws BusinessException  {
		HashMap[] param = (HashMap[])paramObj;
		ArrayList<AggregatedValueObject> billVOlist = new ArrayList<>();
		BaseDAO dao = new BaseDAO();
		HYPubBO hyDao = new HYPubBO();
		String workflowid = billType.replace("OA-", "");

		for (HashMap billVO : param) {
			String requestid = PuPubVO.getString_TrimZeroLenAsNull(billVO.get("requestid"));
			String url = PuPubVO.getString_TrimZeroLenAsNull(billVO.get("url"));
			
			// 根据 requestId、billType
			SuperVO[] infoVOs = hyDao.queryByCondition(
					HkOaInfoVO.class,
					" dr = 0 "
					+ " and pk_hk_oa_info = '"+requestid+"' "
					+ " and workflowid = '" + workflowid + "' "
					);
			if (infoVOs == null || infoVOs.length <=0) {
				throw new BusinessException("NC里没有提交信息，无法归档。");
			}
			HkOaInfoVO infoVO = (HkOaInfoVO)infoVOs[0];
			String id = infoVO.getBillid();
			String type = infoVO.getPk_billtypecode();
			String parentType = infoVO.getParentbilltype();
			infoVO.setOa_url(url);
			infoVO.setOa_status("已归档");
			infoVO.setReceive_ts(new UFDateTime());
			
			if ("F3".equals(parentType)) {	// 付款单
				String updateSQL_1 = 
					" update sm_msg_content " +
					" set receiver = substr(receiver, 1, length(receiver)-3) " +
					" where detail like '" + id + "@" + type + "@%' " +
					" and destination = 'inbox' " +
					" and receiver like '%-oa' "
				;
				String updateSQL_2 = 
					" update pub_workflownote " +
					" set checkman = substr(checkman, 1, length(checkman)-3) " +
					" where pk_billtype = '" + type + "' and billid = '" + id + "' " +
					" and checkman like '%-oa' "
				;
				Integer res_1 = dao.executeUpdate(updateSQL_1);
				Integer res_2 = dao.executeUpdate(updateSQL_2);
				
			} else if ("HK38".equals(parentType)) {	// 租赁月报调整单
				BillQuery<TzBillVO> query = new BillQuery<>(TzBillVO.class);
				TzBillVO[] queryBills = query.query(new String[]{id});
				if (queryBills == null || queryBills.length == 0) {
					throw new BusinessException("NC里的单据不存在，无法归档。");
				}
				TzBillVO queryBill = queryBills[0];
				getIplatFormEntry().processAction("SAVE", type, null, queryBill, null, null);
			}
//			String updateSQL_3 = 
//				" update hk_oa_info " +
//				" set oa_url = '"+url+"' " +
//				" , oa_status = '已归档' " +
//				" where dr = 0 " +
//				" and pk_hk_oa_info = '"+requestid+"' " + 
//				" and workflowid = '" + workflowid + "' "
//			;
//			Integer res_3 = dao.executeUpdate(updateSQL_3);
			
			hyDao.update(infoVO);
			
		}
		return "成功";
	}
}
