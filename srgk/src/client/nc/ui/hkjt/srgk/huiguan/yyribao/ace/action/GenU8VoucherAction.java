package nc.ui.hkjt.srgk.huiguan.yyribao.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IJd_hzshujuMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoHVO;
import nc.vo.pub.BusinessException;

public class GenU8VoucherAction extends NCAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4262228952593728009L;

	public GenU8VoucherAction() {
		setBtnName("生成U8凭证");
		setCode("genU8VoucherAction");
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
		YyribaoBillVO srdbvo = (YyribaoBillVO) getModel().getSelectedData();
		if (srdbvo == null) {
			throw new BusinessException("请选中数据!");
		}
		
		Object VDEF02 = srdbvo.getParentVO().getAttributeValue(YyribaoHVO.VDEF02);
		if( PuPubVO.getString_TrimZeroLenAsNull(VDEF02)!=null )
		{
			throw new BusinessException("已经生成了凭证，不能再次生成。");
		}
		
		// 因为酒店和会馆针对部门的取值不同，所以要判断是生成是会馆凭证还是酒店凭证
		YyribaoHVO hvo = srdbvo.getParentVO();
		String pk_org = hvo.getPk_org();
		String pznum = "";
//		Integer billstatus = hvo.getIbillstatus();
//		if(billstatus!=1){
//			throw new BusinessException("未审核完成不能生成凭证!");
//		}
		if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
			 pznum = getJdMainTain().genU8VoucherInfo(srdbvo);
		} else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
			 pznum = getJdMainTain().genU8VoucherInfo(srdbvo);
		}
		srdbvo.getParentVO().setAttributeValue(YyribaoHVO.VDEF02, pznum);
		this.setEnabled(false);
		// 提示信息
		ShowStatusBarMsgUtil.showStatusBarMsg("生成完毕!", getEditor().getModel()
				.getContext());
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
