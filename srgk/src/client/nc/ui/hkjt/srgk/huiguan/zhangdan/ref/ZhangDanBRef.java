package nc.ui.hkjt.srgk.huiguan.zhangdan.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBVO;

public class ZhangDanBRef extends AbstractRefModel {

	public ZhangDanBRef() {
		super();
		reset();
	}

	public void reset() {
		setRefNodeName("会馆收入账单子表");/* -=notranslate=- */

		setFieldCode(new String[] { ZhangdanBVO.VROWNO,ZhangdanBVO.KEYID,ZhangdanBVO.SQ_NAME, ZhangdanBVO.SQFL_NAME,
				 ZhangdanBVO.BM_NAME,ZhangdanBVO.YINGSHOU,ZhangdanBVO.SHISHOU });
		setFieldName(new String[] { "行号","手牌号","商品名称", "商品分类 ", "部门", "应收", "实收" });
		setHiddenFieldCode(new String[] { ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B,
				ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN });
		setPkFieldCode(ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B);
		setTableName("hk_srgk_hg_zhangdan_b");

		// 打开启用过滤条件开关
		setAddEnableStateWherePart(false);
		// 按显示顺序、编码排序
		setOrderPart("to_number("+ZhangdanBVO.VROWNO+")");
		// 设置显示的字段列数
		this.setDefaultFieldCount(this.getFieldCode().length);
		
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
