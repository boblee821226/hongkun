package nc.ui.hkjt.zulin.znjjs.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IplatFormEntry;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.RefreshSingleAction;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * 生成滞纳金应收单
 *
 */
public class ScZnjysdAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ScZnjysdAction() {
		setBtnName("生成滞纳金应收单");
		setCode("scZnjysdAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BodyAddLineAction addLineAction;
	private RefreshSingleAction refreshCardAction;

	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}
	
	public BodyAddLineAction getAddLineAction() {
		return addLineAction;
	}

	public void setAddLineAction(BodyAddLineAction addLineAction) {
		this.addLineAction = addLineAction;
	}

	public RefreshSingleAction getRefreshCardAction() {
		return refreshCardAction;
	}

	public void setRefreshCardAction(RefreshSingleAction refreshCardAction) {
		this.refreshCardAction = refreshCardAction;
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		if(this.getListview().isShowing()){
			MessageDialog.showErrorDlg(this.getListview(), "", "请进入卡片界面 操作");
			return;
		}
		
		int[] selectRows = this.getEditor().getBillCardPanel().getBillTable().getSelectedRows();
		
		if(selectRows==null || selectRows.length<=0){
			MessageDialog.showErrorDlg(this.getEditor(), "", "请选择表体数据");
			return;
		}
		
		// key：pk_cust
		HashMap<String,ArrayList<ZnjjsBVO>> MAP = new HashMap<String,ArrayList<ZnjjsBVO>>();
		
		for(int i=0;i<selectRows.length;i++){
			int row = selectRows[i];
			UFDouble ys_mny = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "vbdef02") );
			if(ys_mny!=null){
				// 如果界面上 已经有了 应收金额，说明已经推单  则跳过。
				continue;
			}
			UFDouble yq_mny = PuPubVO.getUFDouble_NullAsZero( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_mny") );
			UFDouble jm_mny = PuPubVO.getUFDouble_NullAsZero( getEditor().getBillCardPanel().getBodyValueAt(row, "vbdef01") );
			ys_mny = yq_mny.sub(jm_mny);	// 应收金额 = 逾期金额-减免金额
			if(ys_mny.compareTo(UFDouble.ZERO_DBL)==0){
				// 如果没有 应收金额  则跳过
				continue;
			}
			
			String pk_cust  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_cust") );
			String pk_area  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_area") );
			String pk_room  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_room") );
			String ht_code  = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "ht_code") );
			String ht_rowno = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "ht_rowno") );
			UFDate jf_date  = PuPubVO.getUFDate( getEditor().getBillCardPanel().getBodyValueAt(row, "jf_date") );
			UFDouble jf_mny = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "jf_mny") );
			UFDouble yq_num = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_num") );
			UFDouble yq_bl  = PuPubVO.getUFDouble_ValueAsValue( getEditor().getBillCardPanel().getBodyValueAt(row, "yq_bl") );
			String pk_znjjs_b = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_hk_zulin_znjjs_b") );
			String pk_znjjs   = PuPubVO.getString_TrimZeroLenAsNull( getEditor().getBillCardPanel().getBodyValueAt(row, "pk_hk_zulin_znjjs") );
			
			ZnjjsBVO bvo = new ZnjjsBVO();
			bvo.setPk_cust(pk_cust);
			bvo.setPk_area(pk_area);
			bvo.setPk_room(pk_room);
			bvo.setHt_code(ht_code);
			bvo.setHt_rowno(ht_rowno);
			bvo.setJf_date(jf_date);
			bvo.setJf_mny(jf_mny);
			bvo.setYq_num(yq_num);
			bvo.setYq_bl(yq_bl);
			bvo.setYq_mny(yq_mny);
			bvo.setVbdef02(ys_mny.toString());	// 应收金额
			bvo.setPk_hk_zulin_znjjs(pk_znjjs);
			bvo.setPk_hk_zulin_znjjs_b(pk_znjjs_b);
			
			if(MAP.containsKey(pk_cust)){
				MAP.get(pk_cust).add(bvo);
			}else{
				ArrayList<ZnjjsBVO> value = new ArrayList<ZnjjsBVO>();
				value.add(bvo);
				MAP.put(pk_cust, value);
			}
		}
		
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject() );	// 组织
	    String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(
				getEditor().getBillCardPanel().getHeadItem("pk_org_v").getValueObject() );	// 组织版本
		
		IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		int totalNum = 0;	// 成功的总数
		
		// 根据客户分组情况  生单
		String[] keys = new String[MAP.size()];
		keys = MAP.keySet().toArray(keys);
		for(int keys_i=0;keys_i<keys.length;keys_i++)
		{
			UFDateTime now_dateTime = new UFDateTime();							// 制单时间（固定）
		    UFDate        busi_date = AppContext.getInstance().getBusiDate();	// 业务日期 取 登录日期
		    
		    String[] accperiod = getAccperiod( busi_date );
		    
		    String             year = accperiod[0];					// 会计年（根据业务日期来解析）
		    String           period = accperiod[1];					// 会计月（根据业务日期来解析）
		    String          creator = InvocationInfoProxy.getInstance().getUserId();	// 制单人（固定）
		    String      pk_busitype = "0001N510000000000SLQ";	// 业务流程（固定）
		    String        billclass = "ys";						// 单据大类（固定）
		    String      pk_billtype = "F0";						// 单据类型编码（固定）
		    String     pk_tradetype = "F0-Cxx-05";				// 应收类型（固定）（需要适配）
		    String   pk_tradetypeid = "1001N51000000086BKRS";	// 单据类型（固定）（需要适配）
		    String      pk_currtype = "1002Z0100000000001K1";	// 币种（固定）
		    String         pk_group = "0001N510000000000EGY";	// 集团（固定）
		    String           guojia = "0001Z010000000079UJJ";	// 国家（固定）
		    String      pk_customer = keys[keys_i];				// 客户（取数）
		    						    
		    AggReceivableBillVO aggvo = new AggReceivableBillVO();
		    ReceivableBillVO headVO = new ReceivableBillVO();
		    headVO.setAccessorynum(0);					// 附件张数
		    headVO.setBillclass( billclass );			// 单据大类
		    headVO.setPk_busitype( pk_busitype );		// 业务流程bd_busitype
		    headVO.setPk_billtype( pk_billtype );		// 单据类型编码
		    headVO.setPk_tradetype( pk_tradetype );		// 应收类型code 
		    headVO.setPk_tradetypeid( pk_tradetypeid );	// 单据类型bd_billtype
		    
		    headVO.setApprovestatus(-1);		// 审批状态（-1=自由态，0=未通过态，1=通过态，2=进行中态，3=提交态，）
		    headVO.setBillstatus(-1);			// 单据状态（9=未确认，-1=保存，1=审批通过，2=审批中，-99=暂存，8=签字，）
		    headVO.setEffectstatus(0);			// 生效状态（0=未生效，10=已生效，）
		    
		    // 需要根据 日期来查找  会计年份、期间
		    headVO.setBillyear( year );			// 单据会计年份
		    headVO.setBillperiod( period );		// 单据会计期间
		    
		    headVO.setBilldate( busi_date );		// 单据日期
		    headVO.setCreationtime( now_dateTime );	// 创建时间
		    headVO.setBillmaker( creator );			// 制单人
		    headVO.setCreator( creator );			// 创建人
		    
		    headVO.setGloballocal(UFDouble.ZERO_DBL);	// 全局本币金额
		    headVO.setGrouplocal(UFDouble.ZERO_DBL);	// 集团本币金额
		    
		    headVO.setIsflowbill(UFBoolean.FALSE);		// 是否流程单据
		    headVO.setIsinit(UFBoolean.FALSE);			// 期初标志
		    headVO.setIsreded(UFBoolean.FALSE);			// 是否红冲过
		    
		    headVO.setPk_currtype( pk_currtype );		// 币种
		    headVO.setPk_group( pk_group );				// Pk_group
		    
		    headVO.setPk_fiorg( pk_org );		// 废弃财务组织
		    headVO.setPk_fiorg_v( pk_org_v );	// 废弃财务组织版本
		    headVO.setPk_org( pk_org );			// 应收财务组织
		    headVO.setPk_org_v( pk_org_v );		// 应收财务组织版本
		    headVO.setSett_org( pk_org );		// 结算财务组织
		    headVO.setSett_org_v( pk_org_v );	// 结算财务组织版本
		    
		    headVO.setSrc_syscode(0);		// 单据来源系统（0=应收系统）
		    headVO.setSyscode(0);			// 单据所属系统（0=应收系统）
		    
		    headVO.setSendcountryid( guojia );	// 发货国bd_countryzone
		    headVO.setTaxcountryid( guojia );	// 报税国
		    
		    ArrayList<ZnjjsBVO> vo_list =  MAP.get(keys[keys_i]);
		    ReceivableBillItemVO[] itemVOs = new ReceivableBillItemVO[vo_list.size()];
		    
		    UFDouble total_jine = UFDouble.ZERO_DBL;	// 合计金额
		    
		    for(int i=0;i<itemVOs.length;i++)
		    {
		    	ZnjjsBVO 	item_vo = vo_list.get(i);
		    	UFDouble       jine = PuPubVO.getUFDouble_NullAsZero(item_vo.getVbdef02());// 应收金额
			    String   pk_znjjs_b = item_vo.getPk_hk_zulin_znjjs_b();	// 滞纳金 子表pk
			    String     pk_znjjs = item_vo.getPk_hk_zulin_znjjs();	// 滞纳金 主表pk
			    // 摘要 = 合同编码+交费类型+开始日期
//			    String         scomment = item_vo.getVbillcode()+"【"+item_vo.getVdef01()+"】-"+item_vo.getJflx()+"-"+item_vo.getBusi_date();
		    	String scomment = "滞纳金应收";
			    
			    total_jine = total_jine.add(jine);
			    
		    	itemVOs[i] = new ReceivableBillItemVO();
		    	itemVOs[i].setBillclass( billclass );	// 单据大类
		    	itemVOs[i].setBilldate(busi_date);		// 单据日期
		    	itemVOs[i].setBusidate(busi_date);		// 业务日期
		    	
		    	itemVOs[i].setBuysellflag(1);			// 购销类型（1=国内销售，3=出口销售，）
		    	itemVOs[i].setDirection(1);				// 方向（1=借方，-1=贷方，）
		    	itemVOs[i].setObjtype(0);				// 往来对象（0=客户，2=部门，3=业务员， ）
		    	
		    	itemVOs[i].setGlobalbalance(UFDouble.ZERO_DBL);		// 全局本币余额 
		    	itemVOs[i].setGlobaldebit(UFDouble.ZERO_DBL);		// 全局本币金额(借方)
		    	itemVOs[i].setGlobalnotax_de(UFDouble.ZERO_DBL);	// 全局本币无税金额(借方)
		    	itemVOs[i].setGlobalrate(UFDouble.ZERO_DBL);		// 全局本币汇率
		    	itemVOs[i].setGlobalnotax_de(UFDouble.ZERO_DBL);	// 全局本币无税金额(借方)
		    	itemVOs[i].setGroupbalance(UFDouble.ZERO_DBL);		// 集团本币余额
		    	itemVOs[i].setGroupdebit(UFDouble.ZERO_DBL);		// 集团本币金额(借方)
		    	itemVOs[i].setGroupnotax_de(UFDouble.ZERO_DBL);		// 集团本币无税金额(借方)
		    	itemVOs[i].setGrouprate(UFDouble.ZERO_DBL);			// 集团本币汇率
		    	itemVOs[i].setGrouptax_de(UFDouble.ZERO_DBL);		// ??
		    	itemVOs[i].setLocal_tax_de(UFDouble.ZERO_DBL);		// 税额-借方
		    	itemVOs[i].setCaltaxmny( jine );			// 计税金额
		    	itemVOs[i].setLocal_money_bal( jine );		// 组织本币余额
		    	itemVOs[i].setLocal_money_de( jine );		// 组织本币金额-借方
		    	itemVOs[i].setLocal_notax_de( jine );		// 组织本币无税金额-借方
		    	itemVOs[i].setMoney_bal( jine );			// 原币余额
		    	itemVOs[i].setMoney_de( jine );				// 借方原币金额 
		    	itemVOs[i].setNotax_de( jine );				// 借方原币无税金额
		    	itemVOs[i].setOccupationmny( jine );		// 预占用原币余额
		    	
		    	itemVOs[i].setCustomer( pk_customer );			// 客户
		    	itemVOs[i].setOrdercubasdoc( pk_customer );		// 订单客户
		    	
		    	itemVOs[i].setPausetransact(UFBoolean.FALSE);		// 挂起标志
		    	itemVOs[i].setTriatradeflag(UFBoolean.FALSE);		// 三角贸易
		    	
		    	itemVOs[i].setPk_billtype( pk_billtype );			// 单据类型编码
		    	itemVOs[i].setPk_currtype( pk_currtype );			// 币种
		    	itemVOs[i].setPk_tradetype( pk_tradetype );			// 应收类型code 
		    	itemVOs[i].setPk_tradetypeid( pk_tradetypeid );		// 应收类型
		    	
		    	itemVOs[i].setPk_group( pk_group );		// pk_group
		    	itemVOs[i].setPk_fiorg( pk_org );		// 废弃财务组织
		    	itemVOs[i].setPk_fiorg_v( pk_org_v );	// 废弃财务组织版本
		    	itemVOs[i].setPk_org( pk_org );			// 应收组织
		    	itemVOs[i].setPk_org_v( pk_org_v );		// 应收组织版本
		    	itemVOs[i].setSett_org( pk_org );		// 结算组织
		    	itemVOs[i].setSett_org_v( pk_org_v );	// 结算组织版本
		    	
		    	itemVOs[i].setRececountryid( guojia );				// 收货国
		    	
		    	itemVOs[i].setRowno(-1);							// 单据分录号
		    	itemVOs[i].setTaxtype(1);							// 扣税类别（0=应税内含，1=应税外加，）
		    	itemVOs[i].setQuantity_bal(UFDouble.ZERO_DBL);		// 数量余额
		    	itemVOs[i].setRate(UFDouble.ONE_DBL);				// 组织本币汇率
		    	itemVOs[i].setTaxprice(UFDouble.ZERO_DBL);			// 含税单价
		    	itemVOs[i].setTaxrate(UFDouble.ZERO_DBL);			// 税率
		    	
		    	itemVOs[i].setDef9(item_vo.getPk_area());		// 区域
		    	itemVOs[i].setDef8(item_vo.getPk_room());		// 房间号
		    	itemVOs[i].setDef1(item_vo.getPk_sfxm());		// 收费项目
//		    	itemVOs[i].setDef11(item_vo.getPk_place());		// 位置
//		    	itemVOs[i].setDef2(""+item_vo.getSccb_num());	// 上次抄表数
//		    	itemVOs[i].setDef6(""+item_vo.getBccb_num());	// 本次抄表数
//		    	itemVOs[i].setQuantity_de(item_vo.getUse_num());// 用量（不生效）
//		    	itemVOs[i].setDef5(""+item_vo.getUse_num());	// 用量
//		    	itemVOs[i].setDef7(""+item_vo.getTimes());		// 倍率
		    	itemVOs[i].setPrice(null);		// 单价
		    	
//		    	itemVOs[i].setDef3(hVO.getVdef01());		// 开始日期
//		    	itemVOs[i].setDef4(hVO.getVdef02());		// 截止日期
		    	
		    	itemVOs[i].setDef30(pk_znjjs);		// 合同主表pk（录入单主表pk）（滞纳金主表pk）
		    	itemVOs[i].setDef29(pk_znjjs_b);	// 合同子表pk（录入单子表pk）（滞纳金子表pk）
		    	
		    	itemVOs[i].setScomment(scomment);		// 摘要
		    }
		    
		    headVO.setLocal_money( total_jine );			// 组织本币金额
		    headVO.setMoney( total_jine );					// 原币金额
		    
		    aggvo.setParentVO(headVO);
		    aggvo.setChildrenVO(itemVOs);
		    
		    Object obj_return = iplatFormEntry.processAction("SAVE" , "F0" , null , aggvo , null , null);
		    
		    totalNum++;	// 保存成功 +1
			
		}
		
		MessageDialog.showWarningDlg(this.getEditor(), "", "生成滞纳金应收单，成功【"+totalNum+"】");
		this.getRefreshCardAction().doAction(e);	// 更新完 刷新卡片
		
	}
	
	/**
	   * 根据日期 返回 会计年、月
	   */
	  private String[] getAccperiod(UFDate date)
	  {
		  if(date==null) date = new UFDate();
		  
		  int  year = date.getYear();
		  int month = date.getMonth();
		  int   day = date.getDay();
		  
		  if(day>25)
		  {// 如果 日 大于25 则为下个月
		  month++;
		  }
		  
		  if( month>12 )
		  {// 如果 月 大于12 则年++，月为1
			  year++;
			  month=1;
		  }
		  
		  return new String[]{
				  ""+year,
				  (month<10)?("0"+month):(""+month)
		  };
	  }
}
