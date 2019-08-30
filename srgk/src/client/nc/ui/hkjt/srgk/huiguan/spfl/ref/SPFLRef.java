package nc.ui.hkjt.srgk.huiguan.spfl.ref;

import nc.ui.bd.ref.AbstractRefTreeModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;

public class SPFLRef extends AbstractRefTreeModel {

	public SPFLRef() {
		super();
		reset();
	}

	public void reset() {
		setRefNodeName("商品分类");/* -=notranslate=- */

		setFieldCode(new String[] { SpflHVO.CODE, SpflHVO.NAME });
		setFieldName(new String[] { "编码", "名称" });
		setHiddenFieldCode(new String[] { SpflHVO.PK_HK_SRGK_HG_SPFL,
				SpflHVO.PK_PARENT });
		setPkFieldCode(SpflHVO.PK_HK_SRGK_HG_SPFL);
		setRefCodeField(SpflHVO.CODE);
		setRefNameField(SpflHVO.NAME);
		setTableName("hk_srgk_hg_spfl");
		setFatherField(SpflHVO.PK_PARENT);
		setChildField(SpflHVO.PK_HK_SRGK_HG_SPFL);
		addWherePart(" and nvl(dr,0)=0 ");
		// 打开启用过滤条件开关
		setAddEnableStateWherePart(false);
		// 按显示顺序、编码排序
		setOrderPart(SpflHVO.CODE);

		resetFieldName();

	}

	@Override
	public void filterValueChanged(ValueChangedEvent changedValue) {
		String[] selectedPKs = (String[]) changedValue.getNewValue();
		if (selectedPKs != null && selectedPKs.length > 0) {
			setPk_org(selectedPKs[0]);
		}
	}

}
