package nc.ui.hkjt.hg_spfl.ace.action;

import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8080362244010869166L;

	@Override
	protected boolean isActionEnable() {
		HierachicalDataAppModel model = (HierachicalDataAppModel) this
				.getModel();
		// 获取选中数据
		SpflHVO jzfsvo = (SpflHVO) model.getSelectedData();
		if (jzfsvo != null) {
			// 获取所有数据
			Object[] jzfsvos = model.getAllDatas();
			String jzfspk = jzfsvo.getPk_hk_srgk_hg_spfl();
			for (int i = 0; i < jzfsvos.length; i++) {
				SpflHVO jzfsHVO = (SpflHVO) jzfsvos[i];
				String pkparent = jzfsHVO.getPk_parent();
				if (jzfspk.equals(pkparent)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return super.isActionEnable();

	}
}
