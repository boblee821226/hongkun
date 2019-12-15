package nc.impl.pub.ace;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrInsertBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrUpdateBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrDeleteBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrSendApproveBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrUnSendApproveBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrApproveBP;
import nc.bs.hkjt.zulin.sdflr.ace.bp.AceHk_zulin_sdflrUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.uap.pf.IplatFormEntry;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.arap.receivable.ReceivableBillItemVO;
import nc.vo.arap.receivable.ReceivableBillVO;
import nc.vo.ct.saledaily.GenJftzdVO;
import nc.vo.hkjt.zulin.sdflr.SdflrBVO;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.hkjt.zulin.sdflr.SdflrHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceHk_zulin_sdflrPubServiceImpl {
	// 新增
	public SdflrBillVO[] pubinsertBills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<SdflrBillVO> transferTool = new BillTransferTool<SdflrBillVO>(
					clientFullVOs);
			// 调用BP
			AceHk_zulin_sdflrInsertBP action = new AceHk_zulin_sdflrInsertBP();
			SdflrBillVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceHk_zulin_sdflrDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public SdflrBillVO[] pubupdateBills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<SdflrBillVO> transferTool = new BillTransferTool<SdflrBillVO>(
					clientFullVOs);
			AceHk_zulin_sdflrUpdateBP bp = new AceHk_zulin_sdflrUpdateBP();
			SdflrBillVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public SdflrBillVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		SdflrBillVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<SdflrBillVO> query = new BillLazyQuery<SdflrBillVO>(
					SdflrBillVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public SdflrBillVO[] pubsendapprovebills(
			SdflrBillVO[] clientFullVOs, SdflrBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sdflrSendApproveBP bp = new AceHk_zulin_sdflrSendApproveBP();
		SdflrBillVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public SdflrBillVO[] pubunsendapprovebills(
			SdflrBillVO[] clientFullVOs, SdflrBillVO[] originBills)
			throws BusinessException {
		AceHk_zulin_sdflrUnSendApproveBP bp = new AceHk_zulin_sdflrUnSendApproveBP();
		SdflrBillVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public SdflrBillVO[] pubapprovebills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sdflrApproveBP bp = new AceHk_zulin_sdflrApproveBP();
		SdflrBillVO[] retvos = bp.approve(clientFullVOs, originBills);
		
		for(int i=0;i<retvos.length;i++)
		{// 存在审批流，所以只有当 审核状态为1  才进行推单 
			SdflrBillVO billVO = retvos[i];
			Integer ibillstatus = billVO.getParentVO().getIbillstatus();
			if(ibillstatus==1)
			{
				this.genSdfYsd(billVO);	// 推 水电费应收单
			}
		}
		
		return retvos;
	}

	// 弃审

	public SdflrBillVO[] pubunapprovebills(SdflrBillVO[] clientFullVOs,
			SdflrBillVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceHk_zulin_sdflrUnApproveBP bp = new AceHk_zulin_sdflrUnApproveBP();
		SdflrBillVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

	/**
	 * 审核推 水电费应收单
	 */
	private void genSdfYsd(SdflrBillVO clientFullVO) throws BusinessException{
		
		SdflrBillVO billVO = clientFullVO;
		SdflrBVO[] 	  bVOs = (SdflrBVO[])billVO.getChildrenVO();
		SdflrHVO	   hVO = billVO.getParentVO();
		
		UFBoolean is_init = hVO.getIs_init();
		UFDate  busi_date = PuPubVO.getUFDate( hVO.getVdef03() );	// 表头-应缴费日期
		
		if(is_init.booleanValue())
		{// 如果为 期初，不推单
			return;
		}
		
		// 根据客户分组
		HashMap<String,ArrayList<SdflrBVO>> MAP = new HashMap<String,ArrayList<SdflrBVO>>();
		for(int i=0;i<bVOs.length;i++)
		{
			SdflrBVO bVO = bVOs[i];
			String key = bVO.getPk_cust();
			if(MAP.containsKey(key)){
				MAP.get(key).add(bVO);
			}else{
				ArrayList<SdflrBVO> value = new ArrayList<SdflrBVO>();
				value.add(bVO);
				MAP.put(key, value);
			}
		}
		
		IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		int totalNum = 0;	// 成功的总数
		
		// 根据客户分组情况  生单
		String[] keys = new String[MAP.size()];
		keys = MAP.keySet().toArray(keys);
		for(int keys_i=0;keys_i<keys.length;keys_i++)
		{
			UFDateTime now_dateTime = new UFDateTime();							// 制单时间（固定）
		    UFDate       login_date = AppContext.getInstance().getBusiDate();	// 登录日期
		    
		    String[] accperiod = getAccperiod( busi_date );
		    
		    String             year = accperiod[0];					// 会计年（根据业务日期来解析）
		    String           period = accperiod[1];					// 会计月（根据业务日期来解析）
		    String          creator = InvocationInfoProxy.getInstance().getUserId();	// 制单人（固定）
		    String      pk_busitype = "0001N510000000000SLQ";	// 业务流程（固定）
		    String        billclass = "ys";						// 单据大类（固定）
		    String      pk_billtype = "F0";						// 单据类型编码（固定）
		    String     pk_tradetype = "F0-Cxx-01";				// 应收类型（固定）
		    String   pk_tradetypeid = "1001N51000000063ZZH4";	// 单据类型（固定）
		    String      pk_currtype = "1002Z0100000000001K1";	// 币种（固定）
		    String         pk_group = "0001N510000000000EGY";	// 集团（固定）
		    String           guojia = "0001Z010000000079UJJ";	// 国家（固定）
		    String           pk_org = hVO.getPk_org();			// 组织（取数）
		    String         pk_org_v = hVO.getPk_org_v();		// 组织版本（取数）
		    String      pk_customer = keys[keys_i];				// 客户（取数）
		    						    
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
		    
		    headVO.setBilldate( login_date );		// 单据日期(登录日期)
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
		    
		    ArrayList<SdflrBVO> vo_list =  MAP.get(keys[keys_i]);
		    ReceivableBillItemVO[] itemVOs = new ReceivableBillItemVO[vo_list.size()];
		    
		    UFDouble total_jine = UFDouble.ZERO_DBL;	// 合计金额
		    
		    for(int i=0;i<itemVOs.length;i++)
		    {
		    	SdflrBVO 		item_vo = vo_list.get(i);
			    UFDouble           jine = item_vo.getUse_mny();				// 金额
			    String       pk_sdflr_b = item_vo.getPk_hk_zulin_sdflr_b();	// 录入单 子表pk
			    String         pk_sdflr = item_vo.getPk_hk_zulin_sdflr();	// 录入单 主表pk
			    // 摘要 = 合同编码+交费类型+开始日期
//			    String         scomment = item_vo.getVbillcode()+"【"+item_vo.getVdef01()+"】-"+item_vo.getJflx()+"-"+item_vo.getBusi_date();
		    	String scomment = "水电费应收";
			    
			    total_jine = total_jine.add(jine);
			    
		    	itemVOs[i] = new ReceivableBillItemVO();
		    	itemVOs[i].setBillclass( billclass );	// 单据大类
		    	itemVOs[i].setBilldate(login_date);		// 单据日期
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
		    	itemVOs[i].setTaxprice(item_vo.getPrice());			// 含税单价
		    	itemVOs[i].setTaxrate(UFDouble.ZERO_DBL);			// 税率
		    	
		    	itemVOs[i].setDef9(item_vo.getPk_area());		// 区域
		    	itemVOs[i].setDef8(item_vo.getPk_room());		// 房间号
		    	itemVOs[i].setDef1(item_vo.getPk_sfxm());		// 收费项目
		    	itemVOs[i].setDef11(item_vo.getPk_place());		// 位置
		    	itemVOs[i].setDef2(""+item_vo.getSccb_num());	// 上次抄表数
		    	itemVOs[i].setDef6(""+item_vo.getBccb_num());	// 本次抄表数
		    	itemVOs[i].setQuantity_de(item_vo.getUse_num());// 用量（不生效）
		    	itemVOs[i].setDef5(""+item_vo.getUse_num());	// 用量
		    	itemVOs[i].setDef7(""+item_vo.getTimes());		// 倍率
		    	itemVOs[i].setPrice(item_vo.getPrice());		// 单价
		    	
		    	itemVOs[i].setDef3(hVO.getVdef01());		// 开始日期
		    	itemVOs[i].setDef4(hVO.getVdef02());		// 截止日期
		    	
		    	itemVOs[i].setDef30(pk_sdflr);		// 合同主表pk（录入单主表pk）
		    	itemVOs[i].setDef29(pk_sdflr_b);	// 合同子表pk（录入单子表pk）
		    	
		    	itemVOs[i].setScomment(scomment);		// 摘要
		    }
		    
		    headVO.setLocal_money( total_jine );			// 组织本币金额
		    headVO.setMoney( total_jine );					// 原币金额
		    
		    aggvo.setParentVO(headVO);
		    aggvo.setChildrenVO(itemVOs);
		    
		    Object obj_return = iplatFormEntry.processAction("SAVE" , "F0" , null , aggvo , null , null);
		    
		    totalNum++;	// 保存成功 +1
			
		}
		
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