package nc.bs.hkjt.listener;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.dao.BaseDAO;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 该类没用
 * 隐藏付款单 审批流程的插件类。
 */
public class HiddenWorkFlowListener implements IBusinessListener {

	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		BaseDAO dao = new BaseDAO();
		String eventType = event.getEventType();
		if (IEventType.TYPE_INSERT_AFTER.equals(eventType)) {
			AggPayBillVO[] vos = dealUserObj(event.getUserObject());
			for (AggPayBillVO billVO : vos) {
				PayBillVO headVO = billVO.getHeadVO();
				String id = headVO.getPk_paybill();
				String type = headVO.getPk_tradetype();
				
				String updateSQL_1 = 
					" update sm_msg_content " +
					" set receiver = receiver || '-oa' " +
					" where detail like '" + id + "@" + type + "@%' "
				;
				String updateSQL_2 = 
					" update pub_workflownote " +
					" set checkman = checkman || '-oa' " +
					" where pk_billtype = '" + type + "' and billid = '" + id + "' "
				;
//				Integer res_1 = dao.executeUpdate(updateSQL_1);
//				Integer res_2 = dao.executeUpdate(updateSQL_2);
				System.out.println();
			}
//			if (true) {
//				throw new BusinessException("测试");
//			}
		}
	}
	
	/**
	 * 转换vo
	 */
	protected AggPayBillVO[] dealUserObj(Object obj){
		if(obj instanceof BusinessEvent.BusinessUserObj){
			obj=((BusinessEvent.BusinessUserObj)obj).getUserObj();
		}
		AggPayBillVO[] retvos=null;
		if(obj.getClass().isArray()){
			retvos=(AggPayBillVO[])obj;
		} else {
			retvos=new AggPayBillVO[]{(AggPayBillVO)obj};
		}
		return retvos;
	}
}
