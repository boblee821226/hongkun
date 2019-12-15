package nc.ui.ct.saledaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JOptionPane;

import nc.ui.ct.action.HelpAction;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillModel;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleTermVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class GenHttkAction extends HelpAction {

	/**
	 * 生成合同条款
	 */
	private static final long serialVersionUID = -8527791943376209507L;

	private SaledailyBillForm cardForm = null;
	
	public SaledailyBillForm getCardForm() {
	    return this.cardForm;
	  }
	
	  public void setCardForm(SaledailyBillForm cardForm) {
	    this.cardForm = cardForm;
	  }
	  
	public GenHttkAction()
	{
		this.setCode("GenHtmxAction");
	    this.setBtnName("生成计租参数");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
//		MessageDialog.showWarningDlg(this.getCardForm(), "", "生成合同条款");
		
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
		
		String str_pre_month = JOptionPane.showInputDialog(this.getCardForm(), "请输入每隔几个月涨租",12);
		
		int pre_month = PuPubVO.getInteger_NullAs(str_pre_month, 12);	// 定义参数  几个月  生成一行 合同条款 （默认为12个月）
		
		BillModel model_term = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_term");
		
		model_term.clearBodyData();		// 清空表体
	
		/****************** 合同表头字段
		 * 合同生效日期	valdate
		 * 合同结束日期	invallidate
		 * 单价		vdef4
		 * 面积		vdef5
		 * 缴费方式	vdef7
		 * 收费周期	vdef8
		 */
		UIRefPane ref_jffs = (UIRefPane)this.getCardForm().getBillCardPanel().getHeadItem("vdef7").getComponent();
		UFDate htksrq = PuPubVO.getUFDate( this.getCardForm().getBillCardPanel().getHeadItem("valdate").getValue() );
		UFDate htjsrq = PuPubVO.getUFDate( this.getCardForm().getBillCardPanel().getHeadItem("invallidate").getValue() );
		UFDouble danjia = PuPubVO.getUFDouble_NullAsZero( this.getCardForm().getBillCardPanel().getHeadItem("vdef4").getValue() );
		UFDouble mianji = PuPubVO.getUFDouble_NullAsZero( this.getCardForm().getBillCardPanel().getHeadItem("vdef5").getValue() );
	
		String  str_jffs = ref_jffs.getRefName();
		Integer int_jfzq = 0;	// 缴费周期
		
		String pk_org_v = this.getCardForm().getBillCardPanel().getHeadItem("pk_org_v").getValue();
		String pk_org   = this.getCardForm().getBillCardPanel().getHeadItem("pk_org").getValue();
		String pk_group = this.getCardForm().getBillCardPanel().getHeadItem("pk_group").getValue();
		
		{// 缴费周期的处理
			int int_fu = str_jffs.indexOf("付");
			if(int_fu>=0)
			{// 如果有 付  则需要处理缴费周期 (取 付 后面的 到最后) (押X付XX)
				String str_yjzq = str_jffs.substring(int_fu+1, str_jffs.length());
				int_jfzq = GenHtmxAction.getShuZi(str_yjzq);
			}
		}
		
		/*******************合同条款页签
		 * 收入开始日期	vhkbdef1
		 * 收入结束日期	vhkbdef2
		 * 标准单价		vhkbdef4
		 * 面积			vhkbdef5
		 * 标准计费天数	vhkbdef3
		 * 实际计费天数  	vhkbdef8
		 * 实际合同金额	vhkbdef6
		 * 实际单价		vhkbdef7
		 * 标准合同金额	vhkbdef9
		 */
		
		// 计算  从开始日期 到 结束日期 之前的月（用相差的天数/30.4  取整数）
		int month = (int)PuPubVO.getUFDouble_NullAsZero( htjsrq.getDaysAfter(htksrq) ).div(30.40).setScale(0,UFDouble.ROUND_HALF_UP).getDouble();
		
		// 计算需要生成 合同条款的 行数
		int httk_row = month/pre_month + (month%pre_month!=0?1:0);
		
		Vector<CtSaleTermVO> v_tvo = new Vector();
		
		// 获得 期间日期
		Object[][] qijian = genQiJian(htksrq,htjsrq,pre_month,month,httk_row);
		
//		System.out.println("==");
		
		/**
		 * 循环处理 合同条款数据
		 */
		for(int i=0;i<httk_row;i++)
		{
			Object[] obj_qijian = qijian[i];
			
			CtSaleTermVO tvo = new CtSaleTermVO();
			
			// 合同金额 = 面积*单价*计费天数
			UFDouble htje = mianji.multiply(danjia).multiply(PuPubVO.getUFDouble_NullAsZero(obj_qijian[2])).setScale(2, UFDouble.ROUND_HALF_UP);
			
			tvo.setVhkbdef1(obj_qijian[0].toString());	// 收入开始日期
			tvo.setVhkbdef2(obj_qijian[1].toString());	// 收入结束日期
			tvo.setVhkbdef3(obj_qijian[2].toString());	// 实际计费天数
			tvo.setVhkbdef4(danjia.toString());			// 实际单价
			tvo.setVhkbdef5(mianji.toString());			// 面积
			tvo.setVhkbdef6(htje.toString());			// 实际合同金额
			tvo.setVhkbdef7(danjia.toString());			// 实际单价
			tvo.setVhkbdef8(obj_qijian[2].toString());	// 实际计费天数
			tvo.setVhkbdef9(htje.toString());			// 标准合同金额
			
			tvo.setPk_group(pk_group);	// 集团
			tvo.setPk_org(pk_org);		// 组织
			tvo.setPk_org_v(pk_org_v);	// 组织v
			
			tvo.setVmemo(""+(i+1));		// 备注
			
			v_tvo.add(tvo);
		}
		
		CtSaleTermVO[] tvos = new CtSaleTermVO[v_tvo.size()];
		tvos = v_tvo.toArray(tvos);
		
//		/**
//		 * 针对 生成的合同条款 进行数据处理
//		 */
//		for(int i=0;i<tvos.length;i++)
//		{
//			tvos[i]..setCrowno(""+(i+1)*10);
//		}
		
		model_term.setBodyDataVO(tvos);	// 添加表体数据
		
		model_term.loadLoadRelationItemValue();		// 将 赋值的PK  翻译成档案
	}
	
	/**
	 * 根据合同开始日期、合同结束日期、 每行月数、总月数、行数   返回 每一期的 开始结束日期、天数（连续 但 不交叉）
	 */
	public Object[][] genQiJian(UFDate ksrq,UFDate jsrq,int pre_month,int month,int row){
		
		Object[][] result = new Object[row][];
		
		UFDate execRQ = ksrq;
		
		for(int i=0;i<row;i++)
		{
			int exec_month = pre_month;
			
			if(i==row-1)
			{// 如果是最后一行  exec_month = 总月数-之前运行的月数
				
				exec_month = month - ( pre_month*(i) );
			}
			
			result[i] = new Object[3];	// 开始日期、结束日期、天数
			
			result[i][0] = execRQ;
			UFDate endRQ = GenHtmxAction.getXmonthDate(execRQ, exec_month, -1);
			result[i][1] = endRQ;
			result[i][2] = endRQ.getDaysAfter(execRQ)+1;
			execRQ = endRQ.getDateAfter(1);
			
		}
		
		return result;
	}
	
	@Override
	protected boolean isActionEnable() {
		return true;
	}
	
}
