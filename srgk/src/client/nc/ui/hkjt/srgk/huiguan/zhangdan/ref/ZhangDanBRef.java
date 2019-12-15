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
		setRefNodeName("��������˵��ӱ�");/* -=notranslate=- */

		setFieldCode(new String[] { ZhangdanBVO.VROWNO,ZhangdanBVO.KEYID,ZhangdanBVO.SQ_NAME, ZhangdanBVO.SQFL_NAME,
				 ZhangdanBVO.BM_NAME,ZhangdanBVO.YINGSHOU,ZhangdanBVO.SHISHOU });
		setFieldName(new String[] { "�к�","���ƺ�","��Ʒ����", "��Ʒ���� ", "����", "Ӧ��", "ʵ��" });
		setHiddenFieldCode(new String[] { ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B,
				ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN });
		setPkFieldCode(ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B);
		setTableName("hk_srgk_hg_zhangdan_b");

		// �����ù�����������
		setAddEnableStateWherePart(false);
		// ����ʾ˳�򡢱�������
		setOrderPart("to_number("+ZhangdanBVO.VROWNO+")");
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
