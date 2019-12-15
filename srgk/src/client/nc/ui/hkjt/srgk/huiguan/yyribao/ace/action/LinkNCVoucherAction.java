package nc.ui.hkjt.srgk.huiguan.yyribao.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IJd_hzshujuMaintain;
import nc.ui.pub.link.FipBillLinkQueryCenter;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class LinkNCVoucherAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6739253981231809862L;

	public LinkNCVoucherAction() {
		setBtnName("Áª²éÆ¾Ö¤");
		setCode("linkNCVoucherAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		AggregatedValueObject aggVO = null;
		aggVO = (AggregatedValueObject) getModel().getSelectedData();

		CircularlyAccessibleValueObject hvo = aggVO.getParentVO();

		FipRelationInfoVO infovo = new FipRelationInfoVO();
		infovo.setPk_billtype("HK06");
		infovo.setRelationID(hvo.getPrimaryKey());

		FipBillLinkQueryCenter.queryDesBillBySrcInfoInDlg(getModel()
				.getContext().getEntranceUI(), infovo);
		
	}

	IJd_hzshujuMaintain jdmaintain = null;

	private IJd_hzshujuMaintain getJdMainTain() {
		if (jdmaintain == null) {
			jdmaintain = NCLocator.getInstance().lookup(
					IJd_hzshujuMaintain.class);
		}
		return jdmaintain;
	}

	@Override
	protected boolean isActionEnable() {
		YyribaoBillVO yyrb = (YyribaoBillVO) this.getModel().getSelectedData();
		if (yyrb == null) {
			return false;
		} else {
			String pk = yyrb.getParentVO().getPrimaryKey();
			Integer ibillstatus = yyrb.getParentVO().getIbillstatus();
			if (pk == null || "".equals(pk)||ibillstatus!=1) {
				return false;
			} else {
				return true;
			}
		}
	}
}
