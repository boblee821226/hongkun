package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_zulin_yuebaoMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.hkjt.zulin.yuebao.YuebaoHVO;
import nc.vo.pub.BusinessException;

/**
 * �����±� ɾ��ƾ֤
 *
 */
public class DelNCVoucherAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5324889258047696877L;

	public DelNCVoucherAction() {
		setBtnName("ɾ��ƾ֤");
		setCode("delNCVoucherAction");
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
		
		YuebaoBillVO ybBillVO = (YuebaoBillVO) getModel().getSelectedData();
		
		if (ybBillVO == null) {
			throw new BusinessException("��ѡ������!");
		}
		
		String pznum = "";

		pznum = getYbMainTain().genNCVoucherInfo(ybBillVO,1);				// ����ƾ֤�ţ��� ��ʱƾ֤ û��ƾ֤�ţ�
		
		ybBillVO.getParentVO().setAttributeValue(YuebaoHVO.VDEF09, pznum);	// �����ص�ƾ֤��  �ŵ� ��ͷ�Զ�����9
		
		this.setEnabled(false);
		
		// ��ʾ��Ϣ
		ShowStatusBarMsgUtil.showStatusBarMsg("ɾ�����!", getEditor().getModel()
				.getContext());
	}

	IHk_zulin_yuebaoMaintain ybmaintain = null;

	private IHk_zulin_yuebaoMaintain getYbMainTain() {
		if (ybmaintain == null) {
			ybmaintain = NCLocator.getInstance().lookup(
					IHk_zulin_yuebaoMaintain.class);
		}
		return ybmaintain;
	}

	@Override
	protected boolean isActionEnable() {
		YuebaoBillVO yyrb = (YuebaoBillVO) this.getModel().getSelectedData();
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
