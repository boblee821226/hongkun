package nc.ui.ct.purdaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.logging.Logger;
import nc.ui.ct.saledaily.action.GenHtmxAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.uif2.NCAction;
import nc.vo.ct.entity.CtZzDzInfoVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

import org.codehaus.jackson.map.ObjectMapper;

public class PuExecPriceAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1051151601078510030L;
	
	public PuExecPriceAction() {
		super();
		setBtnName("���㵥��");
		setCode("execPriceAction");
	}

	private nc.ui.ct.model.CTModel model;
	private nc.ui.ct.purdaily.view.PurdailyBillForm editor;
	
	public nc.ui.ct.model.CTModel getModel() {
		return model;
	}
	public void setModel(nc.ui.ct.model.CTModel model) {
		this.model = model;
	}
	public nc.ui.ct.purdaily.view.PurdailyBillForm getEditor() {
		return editor;
	}
	public void setEditor(nc.ui.ct.purdaily.view.PurdailyBillForm editor) {
		this.editor = editor;
	}

	@Override
	public void doAction(ActionEvent event) throws Exception {
		
		// �Ƿ����޺�ͬ
		UIRefPane zlht_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef16").getComponent();
		String zlht_type = zlht_ref.getRefName();
		
		if (!"��".equals(zlht_type)) {
			throw new Exception("ֻ�� �����޺�ͬ�����ܼ��㵥��");
		}
		
		// ��������
		Integer rowCount = this.getEditor().getBillCardPanel().getBillModel().getRowCount();
		if (rowCount <= 0) {
			// ����Ϊ�գ��򲻲���
			return;
		}
		
		// ���ʽ
		UIRefPane fk_type_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef5").getComponent();
		String fk_type = fk_type_ref.getRefName();
		// �������ڣ��£�
		Integer fk_month = -1;
			 if("�¸�".equals(fk_type))	fk_month = 1;
		else if("����".equals(fk_type))	fk_month = 3;
		else if("���긶".equals(fk_type))	fk_month = 6;
		else if("�긶".equals(fk_type))	fk_month = 12;
		else if("���¸�".equals(fk_type))	fk_month = 2;
		else throw new Exception("�޷�����ĸ��ʽ");
		
		// ���ݸ������ڣ���ȷ������ÿ���Ӧ��������
		Integer yearRow = 12 / fk_month;
		// ��ѯ�� �ж����꣬Ҳ����Ҫѭ�����ٴΣ����������� ���� ÿ�������
		Integer yearCount = rowCount / yearRow;
		// ������ ��ѭ�����㡣
		for (int i = 0; i < yearCount; i++) {
			Integer days = 0;
			UFDouble money = UFDouble.ZERO_DBL;
			for (int row = yearRow * i; row < yearRow * (i + 1); row ++) {
				// ��һ��ѭ����������һ�� ���ڵ����������ܽ�� �Լ� ������
				UFDate beginDate = PuPubVO.getUFDate(this.getEditor().getBillCardPanel().getBillModel().getValueAt(row, "vbdef3"));
				UFDate endDate = PuPubVO.getUFDate(this.getEditor().getBillCardPanel().getBillModel().getValueAt(row, "vbdef4"));
				days += endDate.getDaysAfter(beginDate) + 1;
				money = money.add(
					PuPubVO.getUFDouble_NullAsZero(this.getEditor().getBillCardPanel().getBillModel().getValueAt(row, "norigtaxmny")) // ��˰�ϼ�
				);
			}
			UFDouble price = null;
			if (days != 0) {
				price = money.div(days).setScale(8, UFDouble.ROUND_HALF_UP);
			}
			for (int row = yearRow * i; row < yearRow * (i + 1); row ++) {
				// �ڶ���ѭ����������õĵ��ۣ����и�ֵ��
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(price, row, "vbdef5");
			}
		}
	}	

}
