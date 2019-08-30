package nc.ui.ct.saledaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.Vector;

import nc.ui.ct.action.HelpAction;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillModel;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class GenHtmxAction extends HelpAction {

	/**
	 * 生成合同明细
	 */
	private static final long serialVersionUID = 3533254360820084816L;

	private SaledailyBillForm cardForm = null;
	
	public SaledailyBillForm getCardForm() {
	    return this.cardForm;
	  }
	
	  public void setCardForm(SaledailyBillForm cardForm) {
	    this.cardForm = cardForm;
	  }
	  
	public GenHtmxAction()
	{
		this.setCode("GenHtmxAction");
	    this.setBtnName("生成租金明细");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
//		UFDate ddd = new UFDate("2018-10-01");
//		
//		String testMsg = getXmonthDate(ddd,8,-1).toString();
//		
//		MessageDialog.showWarningDlg(this.getCardForm(), "", testMsg);
//		
//		if(true) return ;	// 测试
		
		/**
		 * 版本号
		 * 变更时 不能使用此功能
		 */
		UFDouble bbh = PuPubVO.getUFDouble_NullAsZero(
				this.getCardForm().getBillCardPanel().getHeadItem("version").getValue()
		);
		if(bbh.compareTo(UFDouble.ONE_DBL)!=0)
		{// 如果版本号不为1，则退出
			MessageDialog.showErrorDlg(this.getCardForm(), "", "变更时不能使用此功能");
			return;
		}
		
		BillModel model_term = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_term");
		
		BillModel model_b = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_b");
		
		model_b.clearBodyData();		// 清空表体
		
		/**
		 * 根据合同条款 生成合同明细
		 */
		// 表头 缴费方式 vdef7
		// 表头 缴费周期 vdef8
		UIRefPane ref_jffs = (UIRefPane)this.getCardForm().getBillCardPanel().getHeadItem("vdef7").getComponent();
		UIRefPane ref_jfzq = (UIRefPane)this.getCardForm().getBillCardPanel().getHeadItem("vdef8").getComponent();
		
		String  str_jffs = ref_jffs.getRefName();
		Integer int_yjzq = 0;	// 押金周期（0为无押金）
		Integer int_jfzq = PuPubVO.getInteger_NullAs(ref_jfzq.getRefName(), 0);	// 缴费周期-月
		
		String pk_org_v = this.getCardForm().getBillCardPanel().getHeadItem("pk_org_v").getValue();
		String pk_org   = this.getCardForm().getBillCardPanel().getHeadItem("pk_org").getValue();
		String pk_group = this.getCardForm().getBillCardPanel().getHeadItem("pk_group").getValue();
		
		String zj_sfxm = this.getCardForm().getBillCardPanel().getHeadItem("vdef17").getValue();	// 租金-收费项目
		String yj_sfxm = this.getCardForm().getBillCardPanel().getHeadItem("vdef18").getValue();	// 押金-收费项目
		
		String pk_country = "0001Z010000000079UJJ";	// 国家
		
		{// 押金周期的处理
			int int_ya = str_jffs.indexOf("押");
			if(int_ya>=0)
			{// 如果有 押  则需要处理押金周期 (取 押 后面的一个字)
				String str_yjzq = str_jffs.substring(int_ya+1, int_ya+2);
				int_yjzq = getShuZi(str_yjzq);
			}
		}
		
		/**
		 * nc.vo.ct.saledaily.entity.CtSaleBVO
		 * 存放 合同明细的数据
		 * 项目主键	crowno
		 * 项目主键	pk_financeorg_v
		 * 收费项目	vbdef1
		 * 开始日期	vbdef3
		 * 结束日期	vbdef4
		 * 缴费周期	vbdef8
		 * 单价		vbdef5
		 * 面积		vbdef6
		 * 租金		vbdef7
		 */
		Vector<CtSaleBVO> v_bvo = new Vector();
		
		/**
		 * 循环 合同条款页签，生成 合同明细数据
		 * 收入开始日期	vhkbdef1
		 * 收入结束日期	vhkbdef2
		 * 单价		vhkbdef4
		 * 面积		vhkbdef5
		 * 计费天数	vhkbdef3
		 * 实际计费天数  vhkbdef8
		 * 合同金额	vhkbdef6
		 * 实际单价	vhkbdef7
		 */
		for(int row=0;row<model_term.getRowCount();row++)
		{
			// 收入开始日期
			UFDate ksrq = PuPubVO.getUFDate(model_term.getValueAt(row, "vhkbdef1"));
			
			if(ksrq==null) continue;
			
			// 收入结束日期
			UFDate jsrq = PuPubVO.getUFDate(model_term.getValueAt(row, "vhkbdef2"));
			// 面积
			UFDouble mianji = PuPubVO.getUFDouble_NullAsZero(model_term.getValueAt(row, "vhkbdef5"));
			// 实际单价
			UFDouble sjdj = PuPubVO.getUFDouble_NullAsZero(model_term.getValueAt(row, "vhkbdef7"));
			// 合同金额
			UFDouble htje = PuPubVO.getUFDouble_NullAsZero(model_term.getValueAt(row, "vhkbdef6"));
			
			// 计费天数
			Integer   jfts = PuPubVO.getInteger_NullAs(model_term.getValueAt(row, "vhkbdef3"),0);
			// 实际计费天数
			Integer sjjfts = PuPubVO.getInteger_NullAs(model_term.getValueAt(row, "vhkbdef8"),0);
			
			// 没有计收入的 闰年的2月29日
			UFDate LeapYearDate = null;
			
			if(sjjfts<jfts)
			{// 如果 实际计费天数 小于 计费天数， 说明 存在了 闰年少算一天的情况。
			 // 则需要 找出 时间范围内的 2月29日。 然后在 合同明细 这一天的归属行中，结束时间 早一天。
				Integer LeapYear = null;
				     if(ksrq.isLeapYear()) LeapYear = ksrq.getYear();
				else if(jsrq.isLeapYear()) LeapYear = jsrq.getYear();
				     
				if(LeapYear!=null)
				{
					LeapYearDate = new UFDate(""+LeapYear+"-02-29");
				}
			}
			
			/**
			 * HK 2019年4月15日19:05:13
			 * 月数  考虑 多年的情况
			 */
			int month = 0;
			if(sjjfts<365)
			{// 如果天数 小于 365，就除以30 计算 月数。
				month = (int)PuPubVO.getUFDouble_NullAsZero( sjjfts ).div(30.00).setScale(0,UFDouble.ROUND_HALF_UP).getDouble();
			}
			{// 如果天数 大于 365，就先除以365， 算出有几年，然后剩余天数 再模30  算月数。累加得总月数。
				int year_temp  = sjjfts/365;
				int month_temp = (int)PuPubVO.getUFDouble_NullAsZero( sjjfts%365 ).div(30.00).setScale(0,UFDouble.ROUND_HALF_UP).getDouble();
				month = year_temp*12 + month_temp;
			}
			/***END***/
			
			// 月单价 = 合同金额/月份 （8位小数）
			UFDouble yuedj = htje.div(month).setScale(8, UFDouble.ROUND_HALF_UP);
			
			// 月份 / 缴费周期 = 生成 合同明细的行数  （此处认为是 能够整除）
			int htmx_row = month/int_jfzq;
			
			// 获得 期间日期
			Object[][] qijian = genQiJian(ksrq,jsrq,int_jfzq,htmx_row,LeapYearDate);
			
			// 计算累计租金
			UFDouble total_zujin = UFDouble.ZERO_DBL;
			
			if(row==0)
			{// 进行押金的处理(只处理一次)
				if(int_yjzq>0)
				{
					CtSaleBVO bvo = new CtSaleBVO();
					bvo.setPk_financeorg_v(pk_org_v);		// 财务组织v
					bvo.setPk_org(pk_org);			// 组织
					bvo.setPk_org_v(pk_org_v);		// 组织v
					bvo.setPk_group(pk_group);		// 集团
					bvo.setVbdef1(yj_sfxm);			// 缴费类型-押金
					bvo.setVbdef8(int_yjzq.toString());		// 缴费周期
					bvo.setFtaxtypeflag(0);					// 扣税类别
					
					bvo.setVbdef3(ksrq.toString());			// 开始日期
					bvo.setVbdef4(ksrq.toString());			// 结束日期
					bvo.setVbdef6(mianji.toString());			// 面积
					bvo.setVbdef5(sjdj.toString());				// 单价
					
					UFDouble zujin = yuedj.multiply(int_yjzq).setScale(2, UFDouble.ROUND_HALF_UP);
					
					bvo.setVbdef7(zujin.toString());			// 租金
					bvo.setNorigtaxmny(zujin);					// 价税合计
					bvo.setNnum(mianji);						// 数量
					
					bvo.setCrececountryid(pk_country);
					bvo.setCsendcountryid(pk_country);
					bvo.setCtaxcountryid(pk_country);
					bvo.setFbuysellflag(1);
					bvo.setVqtunitrate("1.00/1.00");
					bvo.setVchangerate("1.00/1.00");
					bvo.setPk_financeorg(pk_org);
					bvo.setNtaxmny(zujin);
					bvo.setNorigmny(zujin);
					bvo.setNmny(zujin);
					bvo.setNcaltaxmny(zujin);
					
					v_bvo.add(bvo);
				}
			}
			
			for(int i=0;i<htmx_row;i++)
			{
				CtSaleBVO bvo = new CtSaleBVO();
				bvo.setPk_financeorg_v(pk_org_v);		// 财务组织v
				bvo.setPk_org(pk_org);			// 组织
				bvo.setPk_org_v(pk_org_v);		// 组织v
				bvo.setPk_group(pk_group);		// 集团
				bvo.setVbdef1(zj_sfxm);			// 缴费类型-租金
				bvo.setVbdef8(int_jfzq.toString());		// 缴费周期
				bvo.setFtaxtypeflag(0);					// 扣税类别
				
				bvo.setVbdef3(qijian[i][0].toString());		// 开始日期
				bvo.setVbdef4(qijian[i][1].toString());		// 结束日期
				bvo.setVbdef6(mianji.toString());			// 面积
				bvo.setVbdef5(sjdj.toString());				// 单价
				
				UFDouble zujin = UFDouble.ZERO_DBL;
				if(i==htmx_row-1)
				{// 最后一笔 则 甩差
					zujin = htje.sub(total_zujin);
				}
				else
				{
					zujin = yuedj.multiply(int_jfzq).setScale(2, UFDouble.ROUND_HALF_UP);
					total_zujin = total_zujin.add(zujin);
				}
				
				bvo.setVbdef7(zujin.toString());			// 租金
				bvo.setNorigtaxmny(zujin);					// 价税合计
				bvo.setNnum(mianji);						// 数量
				
				bvo.setCrececountryid(pk_country);
				bvo.setCsendcountryid(pk_country);
				bvo.setCtaxcountryid(pk_country);
				bvo.setFbuysellflag(1);
				bvo.setVqtunitrate("1.00/1.00");
				bvo.setVchangerate("1.00/1.00");
				bvo.setPk_financeorg(pk_org);
				bvo.setNtaxmny(zujin);
				bvo.setNorigmny(zujin);
				bvo.setNmny(zujin);
				bvo.setNcaltaxmny(zujin);
				
				v_bvo.add(bvo);
			}
		}
		
		CtSaleBVO[] bvos = new CtSaleBVO[v_bvo.size()];
		bvos = v_bvo.toArray(bvos);
		
		/**
		 * 针对 生成的合同信息 进行数据处理
		 */
		for(int i=0;i<bvos.length;i++)
		{
			bvos[i].setCrowno(""+(i+1)*10);
		}
		
		model_b.setBodyDataVO(bvos);	// 添加表体数据
		
		model_b.loadLoadRelationItemValue();	// 将 赋值的PK  翻译成档案
		
	}
	
	/**
	 * 将传过来的 字符串 转换成数字，有可能是 大写数字 也有可能是 阿拉伯数字
	 */
	public static Integer getShuZi(String str_shuzi)
	{
		Integer result = 0;
		
		     if("一".equals(str_shuzi)) {result = 1;}
		else if("二".equals(str_shuzi)) {result = 2;}
		else if("三".equals(str_shuzi)) {result = 3;}
		else if("四".equals(str_shuzi)) {result = 4;}
		else if("五".equals(str_shuzi)) {result = 5;}
		else if("六".equals(str_shuzi)) {result = 6;}
		else if("七".equals(str_shuzi)) {result = 6;}
		else if("八".equals(str_shuzi)) {result = 6;}
		else if("九".equals(str_shuzi)) {result = 6;}
		else if("十".equals(str_shuzi)) {result = 6;}
		else if("十一".equals(str_shuzi)) {result = 6;}
		else if("十二".equals(str_shuzi)) {result = 6;}
		else
		{
			try
			{
				result = PuPubVO.getInteger_NullAs(str_shuzi,0);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * 根据开始日期、结束日期、 缴费周期、行数   返回 每一期的 开始结束日期（连续 但 不交叉）
	 * 将 参数所在的 结束日期 提前一天
	 */
	public UFDate[][] genQiJian(UFDate ksrq,UFDate jsrq,int jfzq,int row,UFDate LeapYearDate){
		
		UFDate[][] result = new UFDate[row][];
		
		UFDate execRQ = ksrq;
		
		for(int i=0;i<row;i++)
		{
			result[i] = new UFDate[2];	// 开始日期、结束日期
			
			result[i][0] = execRQ;	// 开始日期
			UFDate endRQ = getXmonthDate(execRQ, jfzq, -1);
			result[i][1] = endRQ;	// 结束日期
			
			execRQ = endRQ.getDateAfter(1);
			
			if( LeapYearDate!=null
			&& ( LeapYearDate.compareTo(result[i][0])>=0 && LeapYearDate.compareTo(result[i][1])<=0 )
			)
			{// 如果 参数日期 不为空， 并在 在 开始日期 和 结束日期 范围内， 则 结束日期 推后一天
				result[i][1] = result[i][1].getDateAfter(-1);
			}
			
		}
		
		return result;
	}
	
	/**
	 * 根据日期  返回  X个月之后的日期，以及 调整天数
	 */
	public static UFDate getXmonthDate(UFDate date,int Xmonth,int Xday){
		
		int year  = date.getYear();		// 年
		int month = date.getMonth();	// 月
		int day   = date.getDay();		// 日
		int dayM  = date.getDaysMonth();	// 该月 总天数
		
		// 开始取天数的 月份 （默认是从本月取 天数）
		int s_month = month;
		int s_year  = year;
		if(day==dayM)
		{// 如果是 最后一天， 就从下个月 开始取 天数
			s_month++;
		}
		
		// 要加的总天数
		int addDays = Xday;
		
		for(int i=0;i<Xmonth;i++)
		{
			if(s_month>12)
			{
				s_year++;
				s_month=1;
			}
			
			UFDate s_date = new UFDate(""+s_year+"-"+(s_month<10?"0"+s_month:""+s_month)+"-01");
			
			addDays += s_date.getDaysMonth();
			
			s_month++;
			
//			System.out.println("=="+s_date+"=="+s_date.getDaysMonth()+"=="+addDays+"==");
		}
		
		return date.getDateAfter(addDays);
	}
	
	@Override
	protected boolean isActionEnable() {
		return true;
	}
	
}
