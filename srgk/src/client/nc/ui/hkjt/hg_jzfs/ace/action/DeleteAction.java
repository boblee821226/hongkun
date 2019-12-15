package nc.ui.hkjt.hg_jzfs.ace.action;

import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3093237840267022334L;

	@Override
	protected boolean isActionEnable() {
		HierachicalDataAppModel model = (HierachicalDataAppModel) this
				.getModel();
		//获取选中数据
		JzfsHVO jzfsvo = (JzfsHVO) model.getSelectedData();
		if (jzfsvo != null) {
			//获取所有数据
			Object[] jzfsvos =  model.getAllDatas();
			String jzfspk = jzfsvo.getPk_hk_srgk_hg_jzfs();
			for (int i = 0; i < jzfsvos.length; i++) {
				JzfsHVO jzfsHVO = (JzfsHVO) jzfsvos[i];
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
