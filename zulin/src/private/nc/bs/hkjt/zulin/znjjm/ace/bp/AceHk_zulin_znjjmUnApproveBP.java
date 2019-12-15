package nc.bs.hkjt.zulin.znjjm.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_zulin_znjjmUnApproveBP {

	public ZnjjmBillVO[] unApprove(ZnjjmBillVO[] clientBills,
			ZnjjmBillVO[] originBills) throws BusinessException {
		for (ZnjjmBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ZnjjmBillVO> update = new BillUpdate<ZnjjmBillVO>();
		ZnjjmBillVO[] returnVos = update.update(clientBills, originBills);
		
		AceHk_zulin_znjjmApproveBP approveBP = new AceHk_zulin_znjjmApproveBP();
		
		for(int i=0;i<returnVos.length;i++)
		{// 存在审批流，所以只有当 审核状态为1  才进行推单 
			ZnjjmBillVO billVO = returnVos[i];
			Integer ibillstatus = billVO.getParentVO().getIbillstatus();
			approveBP.updateZnjjmdJmje(billVO,ibillstatus,null);	// 推 水电费应收单
		}
		
		return returnVos;
	}
}
