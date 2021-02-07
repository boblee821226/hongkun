package nc.ui.pubapp.uif2app.actions;

import java.awt.event.ActionEvent;

import nc.bs.pubapp.pf.util.ApproveFlowUtil;
import nc.md.data.access.NCObject;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.hkjt.oa.HkOaInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.pf.BillStatusEnum;

public class EditAction extends nc.ui.uif2.actions.EditAction {
	private static final long serialVersionUID = 8701036831410390735L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		/**
		 * HK 检查是否是发送给oa的单据
		 */
		Object billVO_obj = this.getModel().getSelectedData();
		String idValue = null;
		String pkBillTypeCode = null;
		if (billVO_obj instanceof nc.vo.hkjt.zulin.tiaozheng.TzBillVO) {
			nc.vo.hkjt.zulin.tiaozheng.TzBillVO billVO = (nc.vo.hkjt.zulin.tiaozheng.TzBillVO)billVO_obj;
			idValue = billVO.getParentVO().getPk_hk_zulin_tiaozheng();
			pkBillTypeCode = billVO.getParentVO().getVbilltypecode();
		} else if (billVO_obj instanceof nc.vo.pcm.contractschedule.ContractScheduleBillVO) {
			nc.vo.pcm.contractschedule.ContractScheduleBillVO billVO = (nc.vo.pcm.contractschedule.ContractScheduleBillVO)billVO_obj;
			idValue = billVO.getParentVO().getPk_contr_sche();
			pkBillTypeCode = billVO.getParentVO().getTransi_type();
		}
		if (idValue != null && pkBillTypeCode != null) {
			SuperVO[] infoVOs = HYPubBO_Client.queryByCondition(
					HkOaInfoVO.class,
					" dr = 0 " +
					" and billid = '" + idValue + "' " +
					" and pk_billtypecode = '" + pkBillTypeCode + "' "
					);
			if (infoVOs != null && infoVOs.length > 0) {
				throw new BusinessException("已经提交到oa，不能修改。");
			}
		}
		/***END***/
		super.doAction(e);
	}

	@Override
	protected boolean isActionEnable() {
		boolean isEnable = super.isActionEnable();
		if (isEnable && this.getModel().getSelectedData() != null) {
			NCObject obj = NCObject.newInstance(this.getModel()
					.getSelectedData());
			// 自由态时可以修改
			if (obj != null) {
				Integer fstatusflag = ApproveFlowUtil.getBillStatus(obj);
				if (this.tryMakeFlow(fstatusflag)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean tryMakeFlow(Integer fstatusflag) {
		return fstatusflag == null || BillStatusEnum.FREE.equalsValue(fstatusflag);
	}

}
