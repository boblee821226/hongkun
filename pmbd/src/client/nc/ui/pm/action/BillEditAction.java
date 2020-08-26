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
 * @author �������޸� 2020 ǰ�����ã� 1.�ڶ�Ӧ�ĵ������ͽڵ� ��ѡ �ɱ༭�������� 2.�������������� ���������� ѡ�пɱ༭�ĵ�������
 *         �������: 1.����XML
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
		setBtnName("�������޸�");
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
			throw new BusinessException("δѡ������");
		}
		AbstractBill aggVO = (AbstractBill) obj;
		boolean enabled = false;
		Object approvestatus = aggVO.getParentVO().getAttributeValue(
				"bill_status");
		// ��������
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
				throw new BusinessException("��ǰ�û������޸ĸõ���");
			}
			List<String> retList = ((IWorkflowService) NCLocator.getInstance()
					.lookup(IWorkflowService.class)).getEditablePreoperties(
					getModel().getContext().getPk_loginUser(), billtype,
					primrykey, 2);
			if ((retList == null) || (retList.size() <= 0)) {
				throw new BusinessException(
						"��ǰ�û������޸�Ȩ�޵���û�����ÿ��޸ĵĵ����ֶ�,��鿴���������û򵥾�ģ��");
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
