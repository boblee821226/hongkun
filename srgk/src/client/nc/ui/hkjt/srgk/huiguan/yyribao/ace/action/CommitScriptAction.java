package nc.ui.hkjt.srgk.huiguan.yyribao.ace.action;

import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class CommitScriptAction extends
		nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction {
	@Override
	protected boolean isActionEnable() {
		YyribaoBillVO billvo = (YyribaoBillVO) this.getModel()
				.getSelectedData();
		if (billvo==null||billvo.getPrimaryKey() == null || "".equals(billvo.getPrimaryKey())) {
			return false;
		}
		return super.isActionEnable();
	}
	@Override
	protected void beforeCheck(Object vo) {
		if(vo!=null){
			YyribaoBillVO aggvo=(nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO)vo;
			if(aggvo.getParentVO().getIscctz().booleanValue()){//如果是差错调整，则不允许提交
				   ExceptionUtils.wrappBusinessException("差错调整为是的单据不允许提交！");
			}
		}
	}
}
