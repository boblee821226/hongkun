package nc.ui.ct.saledaily.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.IplatFormEntry;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.ct.action.HelpAction;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.tools.BannerDialog;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.ct.saledaily.GenJftzdVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

public class GenJftzdAction extends HelpAction {

	/**
	 * 生成缴费通知单
	 */
	private static final long serialVersionUID = 3201550356497809091L;
	
	private SaledailyBillForm cardForm = null;

	  public GenJftzdAction() {
	    this.setCode("GenJftzdAction");
	    this.setBtnName("生成缴费通知单");
	  }

	  @Override
	  public void doAction(ActionEvent e) throws Exception {
		
		// 组织
		final BillItem zuzhi_item = new BillItem();	
		zuzhi_item.setName("组织");
		zuzhi_item.setKey("zuzhi");
		zuzhi_item.setDataType(IBillItem.UFREF);
		zuzhi_item.setRefType("销售组织");
		zuzhi_item.setEdit(true);
		zuzhi_item.setValue( OrgSettingAccessor.getDefaultOrgUnit() );	// 默认值
		zuzhi_item.setNull(true);	// 是否非空  false 不是非空
		// 开始日期
		final BillItem ksrq_item = new BillItem();	
		ksrq_item.setName("开始日期");
		ksrq_item.setKey("ksrq");
		ksrq_item.setDataType(IBillItem.UFREF);
		ksrq_item.setRefType("日历");
		ksrq_item.setEdit(true);
		ksrq_item.setValue("");		// 默认值
		ksrq_item.setNull(false);	// 是否非空  false 不是非空
		// 结束日期
		final BillItem jsrq_item = new BillItem();	
		jsrq_item.setName("结束日期");
		jsrq_item.setKey("jsrq");
		jsrq_item.setDataType(IBillItem.UFREF);
		jsrq_item.setRefType("日历");
		jsrq_item.setEdit(true);
		jsrq_item.setValue("");		// 默认值
		jsrq_item.setNull(false);	// 是否非空  false 不是非空
		// 合同号
		final BillItem htcode_item = new BillItem();	
		htcode_item.setName("合同号");
		htcode_item.setKey("htcode");
		htcode_item.setDataType(IBillItem.STRING);
		htcode_item.setEdit(true);
		htcode_item.setValue("");	// 默认值
		htcode_item.setNull(false);	// 是否非空  false 不是非空
		// 提前天数
		final BillItem tqts_item = new BillItem();
		tqts_item.setName("提前天数");
		tqts_item.setKey("tqts");
		tqts_item.setDataType(IBillItem.INTEGER);
		tqts_item.setEdit(true);
		tqts_item.setValue(10);		// 默认值-提前10天
		tqts_item.setNull(true);	// 是否非空  false 不是非空
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				 this.getCardForm()
				,new BillItem[]{
					 zuzhi_item,
					 ksrq_item,
					 jsrq_item,
					 htcode_item,
					 tqts_item,
				});
		dlg.setTitle("选择");
		
		if( UIDialog.ID_OK != dlg.showModal()) return;
		
		Runnable checkRun =new Runnable(){
	        public void run()
	        {
		        //线程对话框：系统运行提示框
	            BannerDialog dialog = new BannerDialog(getCardForm());
	            String message="";
	            dialog.start();
		
	            int totalNum = 0;	// 成功的总数
	            
	            try
	            {
	            	
	            	Integer tqts = PuPubVO.getInteger_NullAs(tqts_item.getValueObject(),0);		 // 参数-提前天数
					String  cs_ksrq = PuPubVO.getString_TrimZeroLenAsNull(ksrq_item.getValue()); // 参数-开始日期
					String  cs_jsrq = PuPubVO.getString_TrimZeroLenAsNull(jsrq_item.getValue()); // 参数-结束日期
					String htcode = htcode_item.getValue();
					String  zuzhi = zuzhi_item.getValue();
					
					UFDate nowDate = new UFDate();
					String ksrq = nowDate.toString().substring(0,10);
					String jsrq = nowDate.getDateAfter(tqts).toString().substring(0,10);
					
					if(cs_ksrq!=null) ksrq = cs_ksrq;	// 如果参数-开始日期 不为空  则赋值给 开始日期
					if(cs_jsrq!=null) jsrq = cs_jsrq;	// 如果参数-结束日期 不为空  则赋值给 结束日期
					
					IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
					IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
					
					/**
					 * 先查询 到期后 为生成过 缴费通知单的数据
					 */
					ArrayList<GenJftzdVO> list_1 = null;
					{
						StringBuffer querySQL_1 = 
							new StringBuffer(" select ")
									.append(" ctb.pk_ct_sale_b ")	// 合同子pk
									.append(",ct.pk_ct_sale ")		// 合同主pk
	//								.append(",ctb.norigtaxmny ")	// 合同金额
									.append(",nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0) norigtaxmny ")	// 合同金额 减去 收款金额
									.append(",substr(ctb.vbdef3,0,10) busi_date ")	// 业务日期
									.append(",ct.pk_customer ")		// 客户
									.append(",ct.pk_org ")			// 组织
									.append(",ct.pk_org_v ")		// 组织版本
									.append(",ct.vbillcode ")		// 合同号
									.append(",doc.name jflx ")		// 交费类型
									.append(",case when instr(ct.vbillcode,'#')>0 then substr(ct.vbillcode,1,instr(ct.vbillcode,'#')-1) else ct.vbillcode end vbillcode2 ")	// 合同号2
									.append(",room.name vdef01 ")	// 房间号
									.append(" from ct_sale_b ctb ")
									.append(" inner join ct_sale ct on (ctb.pk_ct_sale = ct.pk_ct_sale) ")
									.append(" left join ar_recitem ysb on (ysb.def29 = ctb.pk_ct_sale_b and ysb.dr = 0) ")	// 应收单自定义30存主表pk，29存主表pk
									.append(" left join bd_defdoc doc on (doc.pk_defdoc = ctb.vbdef1) ")
									.append(" left join bd_defdoc room on (room.pk_defdoc = ct.vdef16) ")	// 房号
									.append(" where ct.dr=0 and ctb.dr=0 ")
									.append(" and ct.blatest = 'Y' ")		// 合同最新版
									.append(" and ct.fstatusflag = 1 ")		// 合同状态 = 生效
									.append(" and ysb.pk_recitem is null ")	// 没有生成应收单的
									.append(" and nvl(ctb.norigtaxmny,0)>nvl(ctb.noritotalgpmny,0) ")// 只取 合同金额 大于 收款金额 的
									.append(" and substr(ctb.vbdef3,0,10) between '"+ksrq+"' and '"+jsrq+"' ")	// 过滤 日期范围
									.append(" and substr(ctb.vbdef3,0,10) <= substr(nvl(ct.vdef19,'2099-12-31 23:59:59'),0,10) ")	// 只取 表体-开始日期 小于等于 租金截止日期 的数据（HK 2019年1月23日17:04:07）
									.append(htcode==null?"":" and ct.vbillcode = '"+htcode+"' ")	// 过滤 合同号
									.append(" and ct.pk_org = '"+zuzhi+"' ")	// 过滤 组织
						;
						
						list_1 = (ArrayList<GenJftzdVO>)iUAPQueryBS.executeQuery(
								 querySQL_1.toString()
								,new BeanListProcessor(GenJftzdVO.class)
						);
					}
					
					/**
					 * 再查询  以上数据中， 之前月份 没有缴费完全的数据。
					 */
					ArrayList<GenJftzdVO> list_2 = null;
					{
						String where_pk_ct_sale   = "'null'";
						String where_pk_ct_sale_b = "'null'";
						
						for( GenJftzdVO vo : list_1 )
						{// 组合出 合同pk的where
							String pk_ct_sale   = vo.getPk_ct_sale();
							String pk_ct_sale_b = vo.getPk_ct_sale_b();
							
							where_pk_ct_sale   += ",'"+pk_ct_sale+"'";
							where_pk_ct_sale_b += ",'"+pk_ct_sale_b+"'";
						}
						
						StringBuffer querySQL_2 = 
							new StringBuffer(" select ")
									.append(" ctb.pk_ct_sale_b ")	// 合同子pk
									.append(",ct.pk_ct_sale ")		// 合同主pk
	//								.append(",ctb.norigtaxmny ")	// 合同金额
									.append(",nvl(ctb.norigtaxmny,0)-nvl(ctb.noritotalgpmny,0) norigtaxmny ")	// 合同金额 减去 收款金额
									.append(",substr(ctb.vbdef3,0,10) busi_date ")	// 业务日期
									.append(",ct.pk_customer ")		// 客户
									.append(",ct.pk_org ")			// 组织
									.append(",ct.pk_org_v ")		// 组织版本
									.append(",ct.vbillcode ")		// 合同号
									.append(",doc.name jflx ")		// 交费类型
									.append(",case when instr(ct.vbillcode,'#')>0 then substr(ct.vbillcode,1,instr(ct.vbillcode,'#')-1) else ct.vbillcode end vbillcode2 ")	// 合同号2
									.append(",room.name vdef01 ")	// 房间号
									.append(" from ct_sale_b ctb ")
									.append(" inner join ct_sale ct on (ctb.pk_ct_sale = ct.pk_ct_sale) ")
									.append(" left join ar_recitem ysb on (ysb.def29 = ctb.pk_ct_sale_b and ysb.dr = 0) ")	// 应收单自定义30存主表pk，29存主表pk
									.append(" left join bd_defdoc doc on (doc.pk_defdoc = ctb.vbdef1) ")
									.append(" left join bd_defdoc room on (room.pk_defdoc = ct.vdef16) ")	// 房号
									.append(" where ct.dr=0 and ctb.dr=0 ")
									.append(" and ct.blatest = 'Y' ")		// 合同最新版
									.append(" and ct.fstatusflag = 1 ")		// 合同状态 = 生效
//									.append(" and ysb.pk_recitem is null ")	// 没有生成应收单的
									.append(" and nvl(ctb.norigtaxmny,0)>nvl(ctb.noritotalgpmny,0) ")// 只取 合同金额 大于 收款金额 的
									.append(" and substr(ctb.vbdef3,0,10) <= '"+jsrq+"' ")	// 过滤 开始日期<=结束日期
//									.append(htcode==null?"":" and ct.vbillcode = '"+htcode+"' ")	// 过滤 合同号
//									.append(" and ct.pk_org = '"+zuzhi+"' ")	// 过滤 组织
									.append(" and  ct.pk_ct_sale       in ( "+where_pk_ct_sale+" )")	// 按 第一步的合同pk 去查询
									.append(" and ctb.pk_ct_sale_b not in ( "+where_pk_ct_sale_b+" )")	// 不查出 第一步的合同行pk
									.append(" and substr(ctb.vbdef3,0,10) <= substr(nvl(ct.vdef19,'2099-12-31 23:59:59'),0,10) ")	// 只取 表体-开始日期 小于等于 租金截止日期 的数据（HK 2019年1月23日17:04:07）
							;
							
							list_2 = (ArrayList<GenJftzdVO>)iUAPQueryBS.executeQuery(
									 querySQL_2.toString()
									,new BeanListProcessor(GenJftzdVO.class)
							);
						
					}
					
					/**
					 *  合并 1 和 2
					 */
					ArrayList<GenJftzdVO> list = new ArrayList<GenJftzdVO>();
					list.addAll(list_1);
					list.addAll(list_2);
					
				    /**
				     * 按照 合同号2  来汇总
				     */
				    HashMap<String,ArrayList<GenJftzdVO>> MAP_list = new HashMap<String,ArrayList<GenJftzdVO>>();
				    for(GenJftzdVO vo : list)
				    {
				    	String key = vo.getVbillcode2();
				    	ArrayList<GenJftzdVO> value = MAP_list.get(key);
				    	if(value==null)
				    	{
				    		value = new ArrayList<GenJftzdVO>();
				    	}
				    	value.add(vo);
				    	MAP_list.put(key , value);
				    }
				    /***END***/
				    
				    /**
				     * HK 2019年1月9日19:51:23
				     * 按照 客户  来汇总
				     */
//				    HashMap<String,ArrayList<GenJftzdVO>> MAP_list = new HashMap<String,ArrayList<GenJftzdVO>>();
//				    for(GenJftzdVO vo : list)
//				    {
//				    	String key = vo.getPk_customer();
//				    	ArrayList<GenJftzdVO> value = MAP_list.get(key);
//				    	if(value==null)
//				    	{
//				    		value = new ArrayList<GenJftzdVO>();
//				    	}
//				    	value.add(vo);
//				    	MAP_list.put(key , value);
//				    }
				    /***END***/
				    
				    String[] Keys = new String[MAP_list.size()];
				    Keys = MAP_list.keySet().toArray(Keys);
				    
//					for( int list_i=0;list_i<list.size();list_i++ )
				    for( int Keys_i=0;Keys_i<Keys.length;Keys_i++ )
					{
						try
						{
							ArrayList<GenJftzdVO> vo_list = MAP_list.get(Keys[Keys_i]);
							
							GenJftzdVO ctSaleVO = vo_list.get(0);	// 取第一个vo  来填充表头数据
							
						    /**
						     * 推单
						     */
						    UFDateTime now_dateTime = new UFDateTime();			// 制单时间（固定）
				//		    UFDate         now_date = now_dateTime.getDate();	// 制单日期（固定）
//						    UFDate        busi_date = PuPubVO.getUFDate(ctSaleVO.getBusi_date());	// 业务日期（取数）
						    UFDate        busi_date = AppContext.getInstance().getBusiDate();		// 业务日期 取 登录日期
						    
						    String[] accperiod = getAccperiod( busi_date );
						    
						    String             year = accperiod[0];					// 会计年（根据业务日期来解析）
						    String           period = accperiod[1];					// 会计月（根据业务日期来解析）
						    String          creator = InvocationInfoProxy.getInstance().getUserId();	// 制单人（固定）
						    String      pk_busitype = "0001N510000000000SLQ";	// 业务流程（固定）
						    String        billclass = "ys";						// 单据大类（固定）
						    String      pk_billtype = "F0";						// 单据类型编码（固定）
						    String     pk_tradetype = "F0-Cxx-03";				// 应收类型（固定）
						    String   pk_tradetypeid = "1001N5100000006402AK";	// 单据类型（固定）
						    String      pk_currtype = "1002Z0100000000001K1";	// 币种（固定）
						    String         pk_group = "0001N510000000000EGY";	// 集团（固定）
						    String           guojia = "0001Z010000000079UJJ";	// 国家（固定）
						    String           pk_org = ctSaleVO.getPk_org();		// 组织（取数）
						    String         pk_org_v = ctSaleVO.getPk_org_v();	// 组织版本（取数）
						    String      pk_customer = ctSaleVO.getPk_customer();// 客户（取数）
						    
//						    UFDouble           jine = ctSaleVO.getNorigtaxmny();	// 金额
						    
//						    String     pk_ct_sale_b = ctSaleVO.getPk_ct_sale_b();	// 合同子表pk
//						    String       pk_ct_sale = ctSaleVO.getPk_ct_sale();		// 合同主表pk
						    						    
						    AggReceivableBillVO aggvo = new AggReceivableBillVO();
						    ReceivableBillVO headVO = new ReceivableBillVO();
						    headVO.setAccessorynum(0);				// 附件张数
						    headVO.setBillclass( billclass );		// 单据大类
						    headVO.setPk_busitype( pk_busitype );	// 业务流程bd_busitype
						    headVO.setPk_billtype( pk_billtype );	// 单据类型编码
						    headVO.setPk_tradetype( pk_tradetype );	// 应收类型code 
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
						    
						    headVO.setPk_fiorg( pk_org );		// 废弃财务组织 0001N510000000001SYX
						    headVO.setPk_fiorg_v( pk_org_v );	// 废弃财务组织版本 0001N510000000001SYW
						    headVO.setPk_org( pk_org );			// 应收财务组织
						    headVO.setPk_org_v( pk_org_v );		// 应收财务组织版本
						    headVO.setSett_org( pk_org );		// 结算财务组织
						    headVO.setSett_org_v( pk_org_v );	// 结算财务组织版本
						    
						    headVO.setSrc_syscode(0);		// 单据来源系统（0=应收系统）
						    headVO.setSyscode(0);			// 单据所属系统（0=应收系统）
						    
						    headVO.setSendcountryid( guojia );	// 发货国bd_countryzone
						    headVO.setTaxcountryid( guojia );	// 报税国
						    
//						    headVO.setDef30(pk_ct_sale);	// 合同主表pk（待定）
//						    headVO.setScomment(scomment);	// 摘要（待定）
						    
						    ReceivableBillItemVO[] itemVOs = new ReceivableBillItemVO[vo_list.size()];
						    
						    UFDouble total_jine = UFDouble.ZERO_DBL;	// 合计金额
						    
						    for(int i=0;i<itemVOs.length;i++)
						    {
						    	GenJftzdVO item_vo = vo_list.get(i);
							    UFDouble           jine = item_vo.getNorigtaxmny();		// 金额
							    String     pk_ct_sale_b = item_vo.getPk_ct_sale_b();	// 合同子表pk
							    String       pk_ct_sale = item_vo.getPk_ct_sale();		// 合同主表pk
							    // 摘要 = 合同编码+交费类型+开始日期
							    String         scomment = item_vo.getVbillcode()+"【"+item_vo.getVdef01()+"】-"+item_vo.getJflx()+"-"+item_vo.getBusi_date();
						    	
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
						    	
						    	itemVOs[i].setCustomer( pk_customer );			// 客户 1001N510000000001UAQ
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
						    	
						    	itemVOs[i].setDef30(pk_ct_sale);		// 合同主表pk
						    	itemVOs[i].setDef29(pk_ct_sale_b);		// 合同子表pk
						    	
						    	itemVOs[i].setScomment(scomment);		// 摘要
						    }
						    
						    headVO.setLocal_money( total_jine );			// 组织本币金额
						    headVO.setMoney( total_jine );					// 原币金额
						    
						    aggvo.setParentVO(headVO);
						    aggvo.setChildrenVO(itemVOs);
						    
						    Object obj_return = iplatFormEntry.processAction("SAVE" , "F0" , null , aggvo , null , null);
						    
						    totalNum++;	// 保存成功 +1
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					    /***END***/
					}
					
	            } catch(Exception e) {
	                e.printStackTrace();
	            } finally {
	            	// 销毁系统运行提示框
	                dialog.end(); 
	                MessageDialog.showWarningDlg(getCardForm(), "", "生成完毕。总共生成【"+totalNum+"】单。");
	            }
	        }
	    };
	    //启用线程
	    new Thread(checkRun).start();
	    
	  }

	  public SaledailyBillForm getCardForm() {
	    return this.cardForm;
	  }

	  public void setCardForm(SaledailyBillForm cardForm) {
	    this.cardForm = cardForm;
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
	  
	  @Override
	  protected boolean isActionEnable() {
		  return true;
	  }
}
