package nc.ui.ct.purdaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nc.bs.logging.Logger;
import nc.ui.ct.saledaily.action.GenHtmxAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.uif2.NCAction;
import nc.vo.ct.entity.CtZzDzInfoVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

import org.codehaus.jackson.map.ObjectMapper;

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
		
		boolean hasBody = this.getEditor().getBillCardPanel().getBillModel().getRowCount() > 0;
		if (hasBody) {
			Integer isFugai = MessageDialog.showYesNoDlg(this.getEditor(), "", "表体存在数据，是否覆盖。");
			if (isFugai == MessageDialog.ID_YES) {
				// 清空表体
				this.getEditor().getBillCardPanel().getBillModel().clearBodyData();
			} else {
				return;
			}
		}
		
		// pk_group
		String pk_group = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_group").getValueObject());
		// pk_org
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject());
		// pk_org_v
		String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_org_v").getValueObject());
		
		// 涨租信息-递增方式
		UIRefPane dz_type_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef19").getComponent();
		String dz_type = dz_type_ref.getRefName();
		// 涨租信息-涨租数据
		String vdef1 = PuPubVO.getString_TrimZeroLenAsNull(this.getEditor().getBillCardPanel().getHeadItem("vdef1").getValueObject());
		ObjectMapper objectMapper = new ObjectMapper();
		CtZzDzInfoVO dzInfoVO = null;
		if (vdef1 != null) {
			dzInfoVO = objectMapper.readValue(vdef1, CtZzDzInfoVO.class);
		}
		
		// 合同开始日期
		UFDate ht_date_begin = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("valdate").getValueObject());
		// 合同终止日期
		UFDate ht_date_end = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("invallidate").getValueObject());
//		// 免租开始日期
//		UFDate mz_date_begin = PuPubVO.getUFDate(
//			this.getEditor().getBillCardPanel().getHeadItem("vdef6").getValueObject());
//		// 免租结束日期
//		UFDate mz_date_end = PuPubVO.getUFDate(
//			this.getEditor().getBillCardPanel().getHeadItem("vdef14").getValueObject());
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
		
		// 履约保证金
		String lybzj = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("vdef7").getValueObject());
		// 支出项目，保证金
		String zcxm_bzj = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("vdef18").getValueObject());
//		Map<String, String> lybzjMap = new HashMap<>();
//		lybzjMap.put("2005", "1001N5100000000EYKBT");	// 保证金（经营）
//		lybzjMap.put("2022", "1001N510000000AEFM6J");	// 保证金（新项目）
//		String lybzj_szxm = null;
//		UFDouble lybzj_mny = null;
//		if (!"无".equals(lybzj)) {
//			// 将 全角逗号，转换成 半角
//			lybzj.replaceAll("，", ",");
//			
//			if (lybzj.contains(",")) {
//				String[] splitStr = lybzj.split(",");
//				lybzj_szxm = lybzjMap.get(splitStr[0]);
//				lybzj_mny = PuPubVO.getUFDouble_NullAsZero(splitStr[1]);
//			}
//		}
		String lybzj_szxm = zcxm_bzj;
		UFDouble lybzj_mny = UFDouble.ZERO_DBL;
		try {
			lybzj_mny = PuPubVO.getUFDouble_NullAsZero(lybzj);
		} catch (Exception ex) {
			Logger.error(ex.getMessage());
		}
		
		// 总合同总额（所有年的之和 包括涨租）
		UFDouble total_ht_money = UFDouble.ZERO_DBL;
		
		// 从第几月之后开始涨租，默认为12，意义为从第二年开始
		Integer zzBeginMonth = 12;
		
		// 将 合同期限（月）， 按照涨租次数， 来拆分。
		Integer[] exec_time = getExecTime(ht_month, zzBeginMonth, dzInfoVO);
//				new Integer[]{
////			12, 12, 12, 12, 12
//			12, 6,6, 6,6, 6,6, 6,6
//		};
		// 找到 每个涨租期间的 开始、结束日期。
		UFDate[][] exec_date = exec_date(ht_date_begin, ht_date_end, exec_time);
		
		// 返回涨租的租金递增信息
		UFDouble[][] dzInfo = getZzDzInfo(dz_type, dzInfoVO, exec_time);
		
		// 找到 每个期间的涨租比例-单价
		UFDouble[] dzLvPrice = dzInfo[0];

		// 找到 每个期间的涨租金额-单价
		UFDouble[] dzJePrice = dzInfo[1];
		
		// 找到 每个期间的涨租比例-总额
		UFDouble[] dzLvMoney = dzInfo[2];

		// 找到 每个期间的涨租金额-总额
		UFDouble[] dzJeMoney = dzInfo[3];

		// 保存第一年的租金，作为 后续 年总额涨租的 基数
		UFDouble FirstYearMny = null;
		
		Integer ftaxtypeflag = 0;	// 0：应税内含		1：应税外加
		
		// 判断，是否需要生成 履约金行
		if (lybzj_szxm != null || lybzj_mny != null) {
			this.getEditor().getBillCardPanel().getBillModel().addLine();
			Integer rowNo = this.getEditor().getBillCardPanel().getBillModel().getRowCount() - 1;	// 当前操作的行
			String body_rowno = "" + ((rowNo+1) * 10);
			// 保证金 是无税的
			UFDouble body_tax = UFDouble.ZERO_DBL;
			UFDouble body_mny = PuPubVO.getUFDouble_NullAsZero(lybzj_mny).sub(body_tax);	// 无税金额
			
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_rowno, rowNo, "crowno");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_szxm, rowNo, "vbdef1");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_group, rowNo, "pk_group");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_org");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_org_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_financeorg");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_financeorg_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_arrvstock");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_arrvstock_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_date_begin, rowNo, "vbdef3");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_date_end, rowNo, "vbdef4");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_date_begin, rowNo, "vbdef2");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_mny, rowNo, "norigtaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_mny, rowNo, "ntaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_mny, rowNo, "ncaltaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vqtunitrate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vchangerate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "csendcountryid");	// 国家相关
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "crececountryid");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "ctaxcountryid");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(2, rowNo, "fbuysellflag");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ftaxtypeflag, rowNo, "ftaxtypeflag");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_tax, rowNo, "ntax");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(UFDouble.ZERO_DBL, rowNo, "ntaxrate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "ncalcostmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "nmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "norigmny");
		}
		
		/**
		 * 按照涨租次数，循环处理， 因为每次涨租的 租金不同。
		 */
		for (int time_i = 0; time_i < exec_time.length; time_i++)
		{
			UFDate date_begin = exec_date[time_i][0];
			UFDate date_end = exec_date[time_i][1];
			
			Integer time_month = exec_time[time_i];
			
			// 每天的金额 = 单价-地上 * 面积-地上 + 单价-地下 * 面积-地下
			UFDouble day_money = UFDouble.ZERO_DBL;
			
			// 涨租比例-单价
			if (dzLvPrice != null) {
				UFDouble dzLv_temp = dzLvPrice[time_i];
				day_money = 
					price_up.multiply(dzLv_temp).multiply(area_up)
				.add(
					price_down.multiply(dzLv_temp).multiply(area_down)
				);
			} else if (dzJePrice != null) {	// 涨租金额-单价
				UFDouble dzJe_temp = dzJePrice[time_i];
				day_money = 
					price_up.add(dzJe_temp).multiply(area_up)
				.add(
					price_down.add(dzJe_temp).multiply(area_down)
				);
			} else { // 不涨租
				day_money = 
					price_up.multiply(area_up)
				.add(
					price_down.multiply(area_down)
				);
			}
			
			// 计算租金的天数 = 合同终止日期 - 合同开始日期 + 1 (包含 头 和 尾)
			Integer days = date_end.getDaysAfter(date_begin) + 1;
			
			// 先计算出 每次涨租期间的合同总额。
			// 合同总金额 ntotalorigmny
			// 正常计算 = 单价 * 实际天数
			UFDouble ht_money = day_money.multiply(days).setScale(2, UFDouble.ROUND_HALF_UP);
			
			// 记录第一年的 年租金
			if (time_i==0) {
				FirstYearMny = ht_money;
			}
			
			// 如果是 合同总额的递增方式， 那么 就需要以首年金额，为基数
			// 因为涨租期间 不定，所以要计算出 实际期间的 合同基数。用 涨租月数 / 一年的12个月 ，得出比例，然后乘以 首年金额。
			if (dzLvMoney != null || dzJeMoney != null) {
				ht_money = FirstYearMny.multiply(
							new UFDouble(time_month).div(12.00)
						);
			}
			// 再进行 合同金额的递增处理
			if (dzLvMoney != null) {
				UFDouble dzLv_temp = dzLvMoney[time_i];
				ht_money = ht_money.multiply(dzLv_temp);
			} else if (dzJeMoney != null) {
				UFDouble dzJe_temp = dzJeMoney[time_i];
				ht_money = ht_money.add(dzJe_temp);
			}
			
			// 记录 合同总额
			total_ht_money = total_ht_money.add(ht_money);
			
			// 付款次数 = 合同期限（月） / 付款周期（月）
			Integer fk_count = time_month / fk_month;
			
			Object[][] qijian = GenHtmxAction.genQiJian(date_begin, date_end, fk_month, fk_count, null);
			
			UFDouble total_money = UFDouble.ZERO_DBL;	// 已分配的合同金额
			
			// 税率 vdef4
			UIRefPane taxrate_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef4").getComponent();
			String taxrate = taxrate_ref.getRefName().replaceAll("%", "");
			
			// 根据客户 找到税码税率
	//		String ctaxcodeid = "1001N5100000005N6HJG";
			UFDouble ntaxrate = new UFDouble(taxrate);
			
			// 根据付款次数，生成表体数据
			for(int i = 0; i < fk_count; i++) {
				UFDate body_date_begin = PuPubVO.getUFDate(qijian[i][0]);
				UFDate body_date_end = PuPubVO.getUFDate(qijian[i][1]);
				UFDate body_date_fk = body_date_begin;
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
				Integer rowNo = this.getEditor().getBillCardPanel().getBillModel().getRowCount() - 1;	// 当前操作的行
				String body_rowno = "" + ((rowNo+1) * 10);
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_rowno, rowNo, "crowno");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(zcxm_1, rowNo, "vbdef1");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_group, rowNo, "pk_group");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_org");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_org_v");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_financeorg");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_financeorg_v");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_arrvstock");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_arrvstock_v");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_begin, rowNo, "vbdef3");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_end, rowNo, "vbdef4");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_fk, rowNo, "vbdef2");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, rowNo, "norigtaxmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, rowNo, "ntaxmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, rowNo, "ncaltaxmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vqtunitrate");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vchangerate");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "csendcountryid");	// 国家相关
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "crececountryid");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "ctaxcountryid");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(2, rowNo, "fbuysellflag");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ftaxtypeflag, rowNo, "ftaxtypeflag");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_tax, rowNo, "ntax");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ntaxrate, rowNo, "ntaxrate");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "ncalcostmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "nmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "norigmny");
				
			}
			
		}
		
		// 将 参照翻译过来
		getEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		// 表头- 合同总额
		this.getEditor().getBillCardPanel().getHeadItem("ntotalorigmny").setValue(total_ht_money);
				
	}
	
	/**
	 * 获取涨租期间段的 开始，结束日期。
	 */
	private static UFDate[][] exec_date(UFDate ht_date_begin, UFDate ht_date_end, Integer[] exec_time) {
		UFDate[][] result = new UFDate[exec_time.length][];
		
		UFDate date_begin = ht_date_begin;
		UFDate date_end = null;
		
		for(int i = 0; i < exec_time.length; i++) {
			
			int addMonth = exec_time[i];
			
			date_end = addMonth(date_begin, addMonth);
			
			result[i] = new UFDate[]{
				PuPubVO.getUFDate(date_begin.clone()),
				PuPubVO.getUFDate(date_end.clone()),
			};
			
			date_begin = date_end.getDateAfter(1);
		}
		
		return result;
	}

	/**
	 * 根据 日期，增加的月份， 返回 之后的日期(注意要减一天)。
	 */
	public static UFDate addMonth(UFDate date, Integer addMonth_parm) {
		
		if(addMonth_parm == 0) {
			return date;
		}
		
		UFDate result = null;
		
		Integer year = date.getYear();
		Integer month = date.getMonth();
		Integer day = date.getDay();
		
		Integer newMonth = month + addMonth_parm;
		
		Integer addYear  = newMonth / 12;
		Integer addMonth = newMonth % 12;
		
		Integer afterYear = year+addYear;
		Integer afterMonth = addMonth;
		
		if (addMonth == 0) {
			afterYear--;
			afterMonth = 12;
		}
		
		result = new UFDate(""+(afterYear)+"-"+(afterMonth)+"-"+day);
		
		return result.getDateBefore(1);
	}
	
	/**
	 * 根据 合同期限（月）、涨租方式、涨租数据 ， 返回 拆分的阶段 涨租信息。
	 */
	public static UFDouble[][] getZzDzInfo(String dz_type, CtZzDzInfoVO dzInfoVO, Integer[] exec_time) {
		
		// 找到 每个期间的涨租比例-单价
		UFDouble[] dzLvPrice = null;

		// 找到 每个期间的涨租金额-单价
		UFDouble[] dzJePrice = null;

		// 找到 每个期间的涨租比例-总额
		UFDouble[] dzLvMoney = null;

		// 找到 每个期间的涨租金额-总额
		UFDouble[] dzJeMoney = null;
		
		if(dz_type != null
		&& dzInfoVO != null
		&& exec_time != null
		) {
//			Integer dzMonth = dzInfoVO.getDzMonth();
			UFDouble dzLv = PuPubVO.getUFDouble_ZeroAsNull(dzInfoVO.getDzLv());
			UFDouble dzJe = PuPubVO.getUFDouble_ZeroAsNull(dzInfoVO.getDzJe());
	
			if ("单价".equals(dz_type)) {
				if (dzLv != null) {// 递增的比例 默认为1
					dzLvPrice = new UFDouble[exec_time.length];
					dzLvPrice[0] = UFDouble.ONE_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// 先取过来过来 上一期的值
						UFDouble temp = dzLvPrice[i-1];
//						if (i % dzMonth == 0 ) { // 如果跨到了递增月限， 说明要进行 增加处理
							temp = temp.add(
								temp.multiply(dzLv).div(100.00)
							);
//						}
						dzLvPrice[i] = temp;
					}
				} else if (dzJe != null) {// 递增的金额 默认为0
					dzJePrice = new UFDouble[exec_time.length];
					dzJePrice[0] = UFDouble.ZERO_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// 先取过来过来 上一期的值
						UFDouble temp = dzJePrice[i-1];
//						if (i % dzMonth == 0 ) { // 如果跨到了递增月限， 说明要进行 增加处理
							temp = temp.add(
								dzJe
							);
//						}
						dzJePrice[i] = temp;
					}
				}
			} else if ("年总价".equals(dz_type)) {
				if (dzLv != null) {// 递增的比例 默认为1
					dzLvMoney = new UFDouble[exec_time.length];
					dzLvMoney[0] = UFDouble.ONE_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// 先取过来过来 上一期的值
						UFDouble temp = dzLvMoney[i-1];
//						if (i % dzMonth == 0 ) { // 如果跨到了递增月限， 说明要进行 增加处理
							temp = temp.add(
								temp.multiply(dzLv).div(100.00)
							);
//						}
						dzLvMoney[i] = temp;
					}
				} else if (dzJe != null) {// 递增的金额 默认为0
					dzJeMoney = new UFDouble[exec_time.length];
					dzJeMoney[0] = UFDouble.ZERO_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// 先取过来过来 上一期的值
						UFDouble temp = dzJeMoney[i-1];
//						if (i % dzMonth == 0 ) { // 如果跨到了递增月限， 说明要进行 增加处理
							temp = temp.add(
								dzJe
							);
//						}
						dzJeMoney[i] = temp;
					}
				}
			}
		}
		
		return new UFDouble[][]{
			dzLvPrice,
			dzJePrice,
			dzLvMoney,
			dzJeMoney
		};
	}
	
	/**
	 * 根据 合同期限（总月数），开始涨租月数，涨租频率，来返回租金期间段
	 */
	public static Integer[] getExecTime(Integer total, Integer begin, CtZzDzInfoVO zzInfo) {
		
		// 如果涨租信息为空，意义为不涨租，直接返回  合同期限
		if (zzInfo == null) {
			return new Integer[]{
				total
			};
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		// 添加首期
		list.add(begin);
		// 只有频率大于0 才能处理
		Integer rate = zzInfo.getDzMonth();
		if (rate <=0 ) {
			return new Integer[]{
				total
			};
		}
		
		Integer sub = total - begin;
		// 循环添加，剩余期间数， 按 涨租频率
		while (sub > 0) {
			if (sub >= rate) {
				list.add(rate);
				sub = sub - rate;
			} else {
				list.add(sub);
				sub = 0;
			}
		}
		
		return list.toArray(new Integer[0]);
	}
	
}
