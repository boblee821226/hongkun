package nc.ui.hkjt.srgk.huiguan.jzfs.ref;

import nc.itf.hkjt.HKJT_PUB;
import nc.ui.bd.ref.AbstractRefTreeModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;

public class JZFSRef extends AbstractRefTreeModel {

	public JZFSRef() {
		super();
		reset();
	}

	public void reset() {
		setRefNodeName("结账方式");/* -=notranslate=- */

		setFieldCode(new String[] { JzfsHVO.CODE, JzfsHVO.NAME });
		setFieldName(new String[] { "编码", "名称" });
		setHiddenFieldCode(new String[] { JzfsHVO.PK_HK_SRGK_HG_JZFS,
				JzfsHVO.PK_PARENT });
		setPkFieldCode(JzfsHVO.PK_HK_SRGK_HG_JZFS);
		setRefCodeField(JzfsHVO.CODE);
		setRefNameField(JzfsHVO.NAME);
		setTableName("hk_srgk_hg_jzfs");
		setFatherField(JzfsHVO.PK_PARENT);
		setChildField(JzfsHVO.PK_HK_SRGK_HG_JZFS);
		this.setWherePart(" nvl(dr,0)=0 ", true);
//		addWherePart(" nvl(dr,0)=0 ");
		// 打开启用过滤条件开关
		setAddEnableStateWherePart(false);
		// 按显示顺序、编码排序
		setOrderPart(JzfsHVO.CODE);

		String pk_org = this.getPk_org();
		String pk_org_ref = "";
		if (pk_org.startsWith("LY0-")) {
			pk_org_ref = "0001N510000000001SY3";
		} else {
			if(HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org))
			{
				pk_org_ref = HKJT_PUB.PK_ORG_HUIGUAN;
			}
			else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org))
			{
				pk_org_ref = HKJT_PUB.PK_ORG_JIUDIAN;
			}
		}
		
		this.addWherePart(
				" and pk_org = '" + pk_org_ref + "' ");
		
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
