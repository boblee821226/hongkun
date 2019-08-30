package nc.ui.hkjt.srgk.huiguan.zhangdan.ace.handler;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHg_zhangdanDataCompute;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;

public class ComputeAction_zd extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915395509783382608L;

	public ComputeAction_zd() {
		setBtnName("金额分摊");
		setCode("computeAction");
	}
	

	private BillManageModel model;
	private ShowUpableBillForm editor;

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj=editor.getBillCardPanel().getHeadItem("pk_org").getValueObject();
		String pk_org=obj==null?"":obj.toString();
		Object data = this.editor.getValue();
		ZhangdanBillVO[] billvos=data==null?new ZhangdanBillVO[]{}:new ZhangdanBillVO[]{(ZhangdanBillVO)data};
		ZhangdanBillVO[] aggvo=getZdComputeItf().computeBodyDate(billvos, pk_org);
	
		this.editor.setValue(aggvo[0]);
//		MessageDialog.showHintDlg(editor, "提示", "计算成功！");
	}

	

	IHg_zhangdanDataCompute zdComputeItf = null;

	public IHg_zhangdanDataCompute getZdComputeItf() {
		if (zdComputeItf == null) {
			zdComputeItf = NCLocator.getInstance().lookup(IHg_zhangdanDataCompute.class);
		}
		return zdComputeItf;
	}
	@Override
	protected boolean isActionEnable() {
		return model.getUiState()==UIState.EDIT;
	}
}
