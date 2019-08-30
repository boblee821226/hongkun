package nc.ui.hkjt.hg_srxm.ace.action;

import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;

public class DeleteAction extends nc.ui.pubapp.uif2app.actions.DeleteAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6991830581519230474L;

	@Override
	protected boolean isActionEnable() {
		HierachicalDataAppModel model = (HierachicalDataAppModel) this
				.getModel();
		// ��ȡѡ������
		SrxmHVO jzfsvo = (SrxmHVO) model.getSelectedData();
		if (jzfsvo != null) {
			// ��ȡ��������
			Object[] jzfsvos = model.getAllDatas();
			String jzfspk = jzfsvo.getPk_hk_srgk_hg_srxm();
			for (int i = 0; i < jzfsvos.length; i++) {
				SrxmHVO jzfsHVO = (SrxmHVO) jzfsvos[i];
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
