package nc.ui.hkjt.zulin.bkfyft.ace.action;

import hd.vo.pub.tools.PuPubVO;

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
 * ���÷�̯ ����ƾ֤
 *
 */
public class GenNCVoucherAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4591232971481831556L;

	public GenNCVoucherAction() {
		setBtnName("����ƾ֤");
		setCode("genNCVoucherAction");
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
		
		Object VDEF09 = ybBillVO.getParentVO().getAttributeValue(YuebaoHVO.VDEF09);	// ��ʱƾ֤
		Object VDEF10 = ybBillVO.getParentVO().getAttributeValue(YuebaoHVO.VDEF10);	// ��ʽƾ֤
		if( PuPubVO.getString_TrimZeroLenAsNull(VDEF09)!=null 
		 || PuPubVO.getString_TrimZeroLenAsNull(VDEF10)!=null 
		)
		{
			throw new BusinessException("�Ѿ�������ƾ֤�������ٴ����ɡ�");
		}
		
		String pznum = "";

		pznum = getYbMainTain().genNCVoucherInfo(ybBillVO,0);		// ����ƾ֤�ţ��� ��ʱƾ֤ û��ƾ֤�ţ�
		
		ybBillVO.getParentVO().setAttributeValue(YuebaoHVO.VDEF09, pznum);	// �����ص�ƾ֤��  �ŵ� ��ͷ�Զ�����9
		
		this.setEnabled(false);
		
		// ��ʾ��Ϣ
		ShowStatusBarMsgUtil.showStatusBarMsg("�������!", getEditor().getModel()
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
		YuebaoBillVO yb = (YuebaoBillVO) this.getModel().getSelectedData();
		if (yb == null) {
			return false;
		} else {
			String pk = yb.getParentVO().getPrimaryKey();
			Integer ibillstatus = yb.getParentVO().getIbillstatus();
			if (pk == null || "".equals(pk)||ibillstatus!=1) {
				return false;
			} else {
				return true;
			}
		}
	}
}
