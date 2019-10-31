package nc.ui.ct.purdaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.ui.ct.saledaily.action.GenHtmxAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.uif2.NCAction;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class PuGenBodyAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1051151601078510030L;
	
	public PuGenBodyAction() {
		super();
		setBtnName("生成明细数据");
		setCode("genBodyAction");
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
		
		// pk_group
		String pk_group = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_group").getValueObject());
		// pk_org
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject());
		// pk_org_v
		String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_org_v").getValueObject());
		
		// 合同开始日期
		UFDate ht_date_begin = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("valdate").getValueObject());
		// 合同终止日期
		UFDate ht_date_end = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("invallidate").getValueObject());
		// 免租开始日期
		UFDate mz_date_begin = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("vdef6").getValueObject());
		// 免租结束日期
		UFDate mz_date_end = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("vdef14").getValueObject());
		// 单价-地上
		UFDouble price_up = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef9").getValueObject());
		// 面积-地上
		UFDouble area_up = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef8").getValueObject());
		// 单价-地下
		UFDouble price_down = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef12").getValueObject());
		// 面积-地下
		UFDouble area_down = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef11").getValueObject());
		
		// 每天的金额 = 单价-地上 * 面积-地上 + 单价-地下 * 面积-地下
		UFDouble day_money = 
					price_up.multiply(area_up)
				.add(
					price_down.multiply(area_down)
				);
		// 计算租金的天数 = 合同终止日期 - 合同开始日期 + 1 (包含 头 和 尾)
		Integer days = ht_date_end.getDaysAfter(ht_date_begin) + 1;
		
		// 合同总金额 ntotalorigmny
		UFDouble ht_money = day_money.multiply(days).setScale(2, UFDouble.ROUND_HALF_UP);
		this.getEditor().getBillCardPanel().getHeadItem("ntotalorigmny").setValue(ht_money);
		
		// 付款方式
		UIRefPane fk_type_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef5").getComponent();
		String fk_type = fk_type_ref.getRefName();
		// 付款周期（月）
		Integer fk_month = -1;
			 if("月付".equals(fk_type))	fk_month = 1;
		else if("季付".equals(fk_type))	fk_month = 3;
		else if("半年付".equals(fk_type))	fk_month = 6;
		else if("年付".equals(fk_type))	fk_month = 12;
		else throw new Exception("无法处理的付款方式");
		
		// 支出项目1
		String zcxm_1 = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("vdef13").getValueObject());
		// 合同期限（月）
		Integer ht_month = PuPubVO.getInteger_NullAs(
			this.getEditor().getBillCardPanel().getHeadItem("vdef2").getValueObject(), 0);
		
		// 付款次数 = 合同期限（月） / 付款周期（月）
		Integer fk_count = ht_month / fk_month;
		
		Object[][] qijian = GenHtmxAction.genQiJian(ht_date_begin, ht_date_end, fk_month, fk_count, null);
		
		UFDouble total_money = UFDouble.ZERO_DBL;	// 已分配的合同金额
		
		// 税率 vdef4
		UIRefPane taxrate_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef4").getComponent();
		String taxrate = taxrate_ref.getRefName().replaceAll("%", "");
		
		// 根据客户 找到税码税率
//		String ctaxcodeid = "1001N5100000005N6HJG";
		UFDouble ntaxrate = new UFDouble(taxrate);
		Integer ftaxtypeflag = 0;	// 0：应税内含		1：应税外加
		
		// 根据付款次数，生成表体数据
		for(int i = 0; i < fk_count; i++) {
			UFDate body_date_begin = PuPubVO.getUFDate(qijian[i][0]);
			UFDate body_date_end = PuPubVO.getUFDate(qijian[i][1]);
			UFDate body_date_fk = body_date_begin;
			String body_rowno = "" + ((i+1) * 10);
			UFDouble body_money = UFDouble.ZERO_DBL;
			if (i == fk_count - 1) {
				body_money = ht_money.sub(total_money);
			} else {
				body_money = ht_money.div(fk_count).setScale(2, UFDouble.ROUND_HALF_UP);
				total_money = total_money.add(body_money);
			}
			
			UFDouble body_tax = body_money.multiply(ntaxrate).div(100.00).setScale(2, UFDouble.ROUND_HALF_UP);
			UFDouble body_mny = body_money.sub(body_tax);	// 无税金额
			
			this.getEditor().getBillCardPanel().getBillModel().addLine();
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_rowno, i, "crowno");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(zcxm_1, i, "vbdef1");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_group, i, "pk_group");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, i, "pk_org");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, i, "pk_org_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, i, "pk_financeorg");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, i, "pk_financeorg_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, i, "pk_arrvstock");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, i, "pk_arrvstock_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_begin, i, "vbdef3");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_end, i, "vbdef4");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_fk, i, "vbdef2");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, i, "norigtaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, i, "ntaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, i, "ncaltaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", i, "vqtunitrate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", i, "vchangerate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", i, "csendcountryid");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", i, "crececountryid");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", i, "ctaxcountryid");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(2, i, "fbuysellflag");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ftaxtypeflag, i, "ftaxtypeflag");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_tax, i, "ntax");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ntaxrate, i, "ntaxrate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, i, "ncalcostmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, i, "nmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, i, "norigmny");
			
		}
		
		// 将 参照翻译过来
		getEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		
//		MessageDialog.showErrorDlg(
//			this.getEditor(),
//			"测试",
//			"" + days 
//			+ "-" + day_money 
//			+ "-" + ht_money 
//			+ "-" + fk_type 
//			+ "-" + fk_month 
//			+ "-" + fk_count
//		);
		
	}

}
