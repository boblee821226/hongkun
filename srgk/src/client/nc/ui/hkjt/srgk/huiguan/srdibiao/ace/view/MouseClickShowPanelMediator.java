package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.view;

import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoHVO;

public class MouseClickShowPanelMediator extends
		nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator {
	@Override
	public void setShowUpComponent(IAutoShowUpComponent showUpComponent) {
		super.setShowUpComponent(showUpComponent);

	}

	@Override
	protected void setCardSelectedTabedPaneFromList() {

		super.setCardSelectedTabedPaneFromList();
		Object pk_hk = ((ShowUpableBillForm) getShowUpComponent())
				.getBillCardPanel().getHeadItem("pk_hk_srgk_hg_srdibiao")
				.getValueObject();
		ShowUpableBillForm billform = (ShowUpableBillForm) getShowUpComponent();
		// ���ڲ�ѯ���������ֶ�������
		if (pk_hk != null && !"".equals(pk_hk.toString())) {
			// �Ƚ��ֶ���ʾ����Ϊfalse
			BillColumnHandler
					.handlerBodyShouRuBmFalse((ShowUpableBillForm) getShowUpComponent());
			SrdibiaoBillVO srdibiao = (SrdibiaoBillVO) billform.getModel()
					.getSelectedData();
			SrdibiaoHVO hvo = srdibiao.getParentVO();
			String vdef1 = hvo.getVdef01();
			if (vdef1 != null && !vdef1.equals("") && !"~".equals(vdef1)) {
				String[] vdef1s = vdef1.split(",");
				// ��ȡ���õĲ�����Ϣ
				for (int i = 0; i < vdef1s.length; i++) {
					String string = vdef1s[i];
					String[] vdefinfo = string.split("=");
					String vdefcolumn = vdefinfo[0];
					String bmname = vdefinfo[1].split("��")[2];
					BillColumnHandler.handlerBodyShouRuBmTrue(
							billform.getBillCardPanel(), vdefcolumn, bmname);
				}
			}
			billform.getBillCardPanel().setBillData(
					billform.getBillCardPanel().getBillData());
		}
	}
}
