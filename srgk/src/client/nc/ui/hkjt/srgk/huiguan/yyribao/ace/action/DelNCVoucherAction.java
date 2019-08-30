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
		YyribaoBillVO srdbvo = (YyribaoBillVO) getModel().getSelectedData();
		if (srdbvo == null) {
			throw new BusinessException("请选中数据!");
		}
		
//		Object VDEF02 = srdbvo.getParentVO().getAttributeValue(YyribaoHVO.VDEF02);
//		if( PuPubVO.getString_TrimZeroLenAsNull(VDEF02)!=null )
//		{
//			throw new BusinessException("已经生成了凭证，不能再次生成。");
//		}
		
		// 2016年5月10日11:28:33  目前来看  酒店与会馆的 处理方式  是一致的
		YyribaoHVO hvo = srdbvo.getParentVO();
		String pk_org = hvo.getPk_org();
		String pznum = "";

		if (HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org)) {
			 pznum = getJdMainTain().genNCVoucherInfo(srdbvo,1);		// 返回凭证号，（ 临时凭证 没有凭证号）
		} else if (HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org)) {
			 pznum = getJdMainTain().genNCVoucherInfo(srdbvo,1);
		}
		srdbvo.getParentVO().setAttributeValue(YyribaoHVO.VDEF02, pznum);
		this.setEnabled(false);
		// 提示信息
		ShowStatusBarMsgUtil.showStatusBarMsg("删除完毕!", getEditor().getModel()
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
