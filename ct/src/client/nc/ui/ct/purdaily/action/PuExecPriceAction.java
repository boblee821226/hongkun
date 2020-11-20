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
		setBtnName("计算单价");
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
		
		// 是否租赁合同
		UIRefPane zlht_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef16").getComponent();
		String zlht_type = zlht_ref.getRefName();
		
		if (!"否".equals(zlht_type)) {
			throw new Exception("只有 非租赁合同，才能计算单价");
		}
		
		// 表体行数
		Integer rowCount = this.getEditor().getBillCardPanel().getBillModel().getRowCount();
		if (rowCount <= 0) {
			// 表体为空，则不操作
			return;
		}
		
		// 付款方式
		UIRefPane fk_type_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef5").getComponent();
		String fk_type = fk_type_ref.getRefName();
		// 付款周期（月）
		Integer fk_month = -1;
			 if("月付".equals(fk_type))	fk_month = 1;
		else if("季付".equals(fk_type))	fk_month = 3;
		else if("半年付".equals(fk_type))	fk_month = 6;
		else if("年付".equals(fk_type))	fk_month = 12;
		else if("两月付".equals(fk_type))	fk_month = 2;
		else throw new Exception("无法处理的付款方式");
		
		// 根据付款周期，来确定出，每年对应的行数。
		Integer yearRow = 12 / fk_month;
		// 查询出 有多少年，也就是要循环多少次，根据总行数 除以 每年的行数
		Integer yearCount = rowCount / yearRow;
		// 如果不足年，则按一年来处理
		if (yearCount == 0) {
			yearCount = 1;
			yearRow = 1;
		}
		// 根据年 来循环计算。
		for (int i = 0; i < yearCount; i++) {
			Integer days = 0;
			UFDouble money = UFDouble.ZERO_DBL;
			for (int row = yearRow * i; row < yearRow * (i + 1); row ++) {
				// 第一次循环：遍历这一年 所在的行数，汇总金额 以及 天数。
				UFDate beginDate = PuPubVO.getUFDate(this.getEditor().getBillCardPanel().getBillModel().getValueAt(row, "vbdef3"));
				UFDate endDate = PuPubVO.getUFDate(this.getEditor().getBillCardPanel().getBillModel().getValueAt(row, "vbdef4"));
				days += endDate.getDaysAfter(beginDate) + 1;
				money = money.add(
					PuPubVO.getUFDouble_NullAsZero(this.getEditor().getBillCardPanel().getBillModel().getValueAt(row, "norigtaxmny")) // 价税合计
				);
			}
			UFDouble price = null;
			if (days != 0) {
				price = money.div(days).setScale(8, UFDouble.ROUND_HALF_UP);
			}
			for (int row = yearRow * i; row < yearRow * (i + 1); row ++) {
				// 第二次循环：将计算好的单价，进行赋值。
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(price, row, "vbdef5");
			}
		}
	}	

}
