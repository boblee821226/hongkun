package nc.ui.pm.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IWorkflowService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.trade.pub.IBillStatus;

/**
 * @author 审批中修改 2020 前端配置： 1.在对应的单据类型节点 勾选 可编辑单据属性 2.在审批流定义中 流程属性里 选中可编辑的单据属性
 *         后端配置: 1.配置XML
 */
public class BillEditAction extends EditAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 339225309320721763L;

	private String billType;
	private String transType;
	private String billCodeName;
	private boolean powercheck;
	private BillForm editor;

	public BillEditAction() {
		setCode("BillEditAction");
		setBtnName("审批中修改");
	}

	public void doAction(ActionEvent e) throws Exception {
		String billtype = getTransType() == null ? getBillType()
				: getTransType();
		if (isPowercheck()) {
			IBill bill = (IBill) getModel().getSelectedData();
			PowerCheckUtils.checkHasPermission(new IBill[] { bill }, billtype,
					PowerActionEnum.EDIT.getActioncode(), getBillCodeName());
		}
		Object obj = this.getModel().getSelectedData();
		if (obj == null) {
			throw new BusinessException("未选中数据");
		}
		AbstractBill aggVO = (AbstractBill) obj;
		boolean enabled = false;
		Object approvestatus = aggVO.getParentVO().getAttributeValue(
				"bill_status");
		// 交易类型
		billtype = PuPubVO.getString_TrimZeroLenAsNull(aggVO.getParentVO()
				.getAttributeValue("transi_type"));
		if (approvestatus != null) {
			int status = Integer.parseInt(approvestatus.toString());
			enabled = ((status == IBillStatus.CHECKGOING) || (status == IBillStatus.COMMIT))
					&& (this.getModel().getAppUiState() == AppUiState.NOT_EDIT);
		}
		BillCardPanel billcard = getEditor().getBillCardPanel();
		if (enabled) {
			String primrykey = aggVO.getParentVO().getPrimaryKey();
			List<Object[]> listWorkFlow = ((IWorkflowService) NCLocator
					.getInstance().lookup(IWorkflowService.class))
					.getAvailableWorkitemInfo(getModel().getContext()
							.getPk_loginUser(), billtype, primrykey);
			if ((listWorkFlow == null) || (listWorkFlow.size() <= 0)) {
				throw new BusinessException("当前用户不可修改该单据");
			}
			List<String> retList = ((IWorkflowService) NCLocator.getInstance()
					.lookup(IWorkflowService.class)).getEditablePreoperties(
					getModel().getContext().getPk_loginUser(), billtype,
					primrykey, 2);
			if ((retList == null) || (retList.size() <= 0)) {
				throw new BusinessException(
						"当前用户具有修改权限但是没有配置可修改的单据字段,请查看审批流设置或单据模板");
			}

			BillItem[] headItems = billcard.getHeadShowItems();
			for (int i = 0; i < headItems.length; i++) {
				BillItem headItem = headItems[i];
				if (retList.contains(headItem.getKey())) {
					headItem.setEdit(true);
				} else {
					headItem.setEdit(false);
				}
			}
			String[] tablecodes = billcard.getBillData().getBodyTableCodes();
			for (String tablecode : tablecodes) {
				BillItem[] bodyitems = billcard.getBillData().getShowItems(
						IBillItem.BODY, tablecode);
				for (BillItem billItem : bodyitems) {
					if (retList.contains(tablecode + "." + billItem.getKey())) {
						billItem.setEdit(true);
					} else {
						billItem.setEdit(false);
					}
				}

			}
		}
		super.doAction(e);
	}

	protected boolean isActionEnable() {
		Object obj = this.getModel().getSelectedData();
		boolean enabled = false;
		if (obj != null) {
			AbstractBill absobj = (AbstractBill) obj;
			Object approvestatus = absobj.getParentVO().getAttributeValue(
					"bill_status");
			if (approvestatus != null) {
				int status = Integer.parseInt(approvestatus.toString());
				enabled = ((status == IBillStatus.CHECKGOING) || (status == IBillStatus.COMMIT))
						&& (this.getModel().getAppUiState() == AppUiState.NOT_EDIT);
			}
		}
		return enabled;
	}

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getBillCodeName() {
		return billCodeName;
	}

	public void setBillCodeName(String billCodeName) {
		this.billCodeName = billCodeName;
	}

	public boolean isPowercheck() {
		return powercheck;
	}

	public void setPowercheck(boolean powercheck) {
		this.powercheck = powercheck;
	}
}
