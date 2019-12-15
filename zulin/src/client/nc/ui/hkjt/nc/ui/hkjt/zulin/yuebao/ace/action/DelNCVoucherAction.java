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
 * 租赁月报 删除凭证
 *
 */
public class DelNCVoucherAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5324889258047696877L;

	public DelNCVoucherAction() {
		setBtnName("删除凭证");
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
			throw new BusinessException("请选中数据!");
		}
		
		String pznum = "";

		pznum = getYbMainTain().genNCVoucherInfo(ybBillVO,1);				// 返回凭证号，（ 临时凭证 没有凭证号）
		
		ybBillVO.getParentVO().setAttributeValue(YuebaoHVO.VDEF09, pznum);	// 将返回的凭证号  放到 表头自定义项9
		
		this.setEnabled(false);
		
		// 提示信息
		ShowStatusBarMsgUtil.showStatusBarMsg("删除完毕!", getEditor().getModel()
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
