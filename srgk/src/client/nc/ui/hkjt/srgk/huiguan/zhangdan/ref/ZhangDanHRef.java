package nc.ui.hkjt.srgk.huiguan.zhangdan.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO;

public class ZhangDanHRef extends AbstractRefModel {

	public ZhangDanHRef() {
		super();
		reset();
	}

	public void reset() {
		setRefNodeName("��������˵�����");/* -=notranslate=- */

		setFieldCode(new String[] { ZhangdanHVO.VBILLCODE, ZhangdanHVO.BANCI,
				ZhangdanHVO.YINGSHOU,ZhangdanHVO.SHISHOU });
		setFieldName(new String[] { "�˵���", "���", "Ӧ��", "ʵ��" });
		setHiddenFieldCode(new String[] { ZhangdanHVO.PK_HK_DZPT_HG_ZHANGDAN,
				ZhangdanHVO.PK_GROUP, ZhangdanHVO.PK_ORG });
		setPkFieldCode(ZhangdanHVO.PK_HK_DZPT_HG_ZHANGDAN);
		setTableName("hk_srgk_hg_zhangdan");

		// �����ù�����������
		setAddEnableStateWherePart(false);
		// ����ʾ˳�򡢱�������
		setOrderPart(ZhangdanHVO.VBILLCODE);
		// ������ʾ���ֶ�����
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
