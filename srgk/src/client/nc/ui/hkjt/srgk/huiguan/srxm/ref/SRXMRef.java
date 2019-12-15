package nc.ui.hkjt.srgk.huiguan.srxm.ref;

import nc.itf.hkjt.HKJT_PUB;
import nc.ui.bd.ref.AbstractRefTreeModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;

public class SRXMRef extends AbstractRefTreeModel {
	public SRXMRef() {
		super();
		reset();
	}

	public void reset() {
		setRefNodeName("收入项目");/* -=notranslate=- */

		setFieldCode(new String[] { SrxmHVO.CODE, SrxmHVO.NAME });
		setFieldName(new String[] { "编码", "名称" });
		setHiddenFieldCode(new String[] { SrxmHVO.PK_HK_SRGK_HG_SRXM,
				SrxmHVO.PK_PARENT });
		setPkFieldCode(SrxmHVO.PK_HK_SRGK_HG_SRXM);
		setRefCodeField(SrxmHVO.CODE);
		setRefNameField(SrxmHVO.NAME);
		setTableName("hk_srgk_hg_srxm");
		setFatherField(SrxmHVO.PK_PARENT);
		setChildField(SrxmHVO.PK_HK_SRGK_HG_SRXM);
		this.setWherePart(" nvl(dr,0)=0 ", true);
//		addWherePart(" and nvl(dr,0)=0 ");
		// 打开启用过滤条件开关
		setAddEnableStateWherePart(false);
		// 按显示顺序、编码排序
		setOrderPart(SrxmHVO.CODE);

		String pk_org = this.getPk_org();
		String pk_org_ref = "";
		if(HKJT_PUB.PK_ORG_HUIGUAN_MAP.containsValue(pk_org))
		{
			pk_org_ref = HKJT_PUB.PK_ORG_HUIGUAN;
		}
		else if(HKJT_PUB.PK_ORG_JIUDIAN_MAP.containsValue(pk_org))
		{
			pk_org_ref = HKJT_PUB.PK_ORG_JIUDIAN;
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
