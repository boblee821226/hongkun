package nc.impl.hkjt.report;

import hd.vo.pub.tools.PuPubVO;
import hd.vo.pub.tools.ReportDataUtil;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.mail.MailTool;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.itf.ia.mia.IIAMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pubitf.pp.m29.IAccountQuery;
import nc.vo.cmp.settlement.SettlementAggVO;
import nc.vo.cmp.settlement.SettlementBodyVO;
import nc.vo.cmp.settlement.SettlementHeadVO;
import nc.vo.ia.mia.entity.IABillVO;
import nc.vo.ia.mia.entity.IAHeadVO;
import nc.vo.ia.mia.entity.IAItemVO;
import nc.vo.pp.m29.account.entity.AccountVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class HkjtReportImpl implements HkjtReportITF {

	@Override
	public DataSet queryReport(SmartContext context,
			HashMap<String, Object> param, String type, String flag,
			Object other) throws Exception {
		
		DataSet dataset = null;
		if( "HYKPXXCXB".equals(type) ){			// 会员开票信息查询表
			dataset = new Report_HYKPXXCXB().DO(context, param, flag, other);
		}
		
		return dataset;
	}

	@Override
	public Object sendMail(Object data,Object other) throws BusinessException {
		
		String resultMsg = "";	// 返回的信息
		
		try {
			
			// 查询默认发件人信息
			IAccountQuery iAccountQuery = NCLocator.getInstance().lookup(IAccountQuery.class);
			AccountVO accountVO = iAccountQuery.queryDefaultAccount();
			
			BaseDAO dao = new BaseDAO();
			
			Object[] aggVOs_obj = (Object[])data;
			
			String pk_gys_str = "'null'";
	    	String pk_psn_str = "'null'";
	    	String pk_kh_str  = "'null'";
	    	String pk_org_str = "'null'";
	    	
	    	HashMap<String,String> gys_info_MAP = new HashMap<String,String>();
	    	HashMap<String,String> psn_info_MAP = new HashMap<String,String>();
	    	HashMap<String,String> kh_info_MAP  = new HashMap<String,String>();
	    	HashMap<String,String> org_info_MAP = new HashMap<String,String>();
	    	
	    	/**
	    	 * 1、第一次循环  汇总供应商pk、人员信息pk
	    	 */
	    	for( int i=0;i<aggVOs_obj.length;i++)
	    	{
	    		SettlementAggVO aggVO = (SettlementAggVO)aggVOs_obj[i];
	    		
	    		SettlementHeadVO   headVO  = (SettlementHeadVO)aggVO.getParentVO();
	    		SettlementBodyVO[] bodyVOs = (SettlementBodyVO[])aggVO.getChildrenVO();
	    		
	    		String 	 pk_org = headVO.getPk_org();			// 财务组织pk
	    		String	  df_pk = bodyVOs[0].getPk_trader();	// 对方pk
	    		// 0-客户 1-供应商 2-部门 3-个人 4-散户
	    		Integer df_type = bodyVOs[0].getTradertype();	// 对方类型
	    		
	    		if(df_type==1)
	    			pk_gys_str += ",'"+df_pk+"'";
	    		else if(df_type==3)
	    			pk_psn_str += ",'"+df_pk+"'";
	    		else if(df_type==0)
	    			pk_kh_str += ",'"+df_pk+"'";
	    		
	    		pk_org_str += ",'"+pk_org+"'";
	    		
	    	}
	    	
	    	/**
	    	 * 2、根据供应商\人员\客户  查询出  邮箱
	    	 */
	    	if(pk_gys_str.length()>20)
	    	{
	    		String querySQL = 
	    			"select pk_supplier,email " +
	    			"from bd_supplier " +
	    			"where pk_supplier in (" + pk_gys_str + ") "
	    		;
	    		ArrayList list = (ArrayList)dao.executeQuery(querySQL, new ArrayListProcessor());
	    		if(list!=null && list.size()>0)
	    		{
	    			for(int i=0;i<list.size();i++)
	    			{
	    				Object[] obj = (Object[])list.get(i);
	    				gys_info_MAP.put(
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[0]),
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[1])
	    				);
	    			}
	    		}
	    		
	    	}
	    	if(pk_psn_str.length()>20)
	    	{
	    		String querySQL = 
		    			"select pk_psndoc,email " +
		    			"from bd_psndoc " +
		    			"where pk_psndoc in (" + pk_psn_str + ") "
	    		;
	    		ArrayList list = (ArrayList)dao.executeQuery(querySQL, new ArrayListProcessor());
	    		if(list!=null && list.size()>0)
	    		{
	    			for(int i=0;i<list.size();i++)
	    			{
	    				Object[] obj = (Object[])list.get(i);
	    				psn_info_MAP.put(
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[0]),
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[1])
	    				);
	    			}
	    		}
	    	}
	    	if(pk_kh_str.length()>20)
	    	{
	    		String querySQL = 
		    			"select c.pk_customer,c.email " +
		    			"from bd_customer c " +
		    			"where c.pk_customer in (" + pk_kh_str + ") "
	    		;
	    		ArrayList list = (ArrayList)dao.executeQuery(querySQL, new ArrayListProcessor());
	    		if(list!=null && list.size()>0)
	    		{
	    			for(int i=0;i<list.size();i++)
	    			{
	    				Object[] obj = (Object[])list.get(i);
	    				kh_info_MAP.put(
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[0]),
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[1])
	    				);
	    			}
	    		}
	    	}
	    	
	    	if(pk_org_str.length()>20)
	    	{
	    		String querySQL = 
		    			"select pk_financeorg,name " +
		    			"from org_financeorg " +
		    			"where pk_financeorg in (" + pk_org_str + ") "
	    		;
	    		ArrayList list = (ArrayList)dao.executeQuery(querySQL, new ArrayListProcessor());
	    		if(list!=null && list.size()>0)
	    		{
	    			for(int i=0;i<list.size();i++)
	    			{
	    				Object[] obj = (Object[])list.get(i);
	    				org_info_MAP.put(
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[0]),
	    					PuPubVO.getString_TrimZeroLenAsNull(obj[1])
	    				);
	    			}
	    		}
	    	}
	    	
	    	/**
	    	 * 3、第二次循环，进行发送处理
	    	 */
	    	for( int i=0;i<aggVOs_obj.length;i++)
	    	{
	    		SettlementAggVO aggVO = (SettlementAggVO)aggVOs_obj[i];
	    		
	    		SettlementHeadVO   headVO  = (SettlementHeadVO)aggVO.getParentVO();
	    		SettlementBodyVO[] bodyVOs = (SettlementBodyVO[])aggVO.getChildrenVO();
	    		
	    		String billCode = headVO.getBillcode();			// 业务单号
	    		String 	 pk_org = headVO.getPk_org();			// 财务组织pk
	    		UFDouble 	mny = headVO.getPrimal();			// 金额
	    		String 	df_name = bodyVOs[0].getTradername();	// 对方对象名称
	    		String 	account = bodyVOs[0].getOppaccount();	// 对方账户
	    		String	  df_pk = bodyVOs[0].getPk_trader();	// 对方pk
	    		
	    		String pk_settlement = headVO.getPk_settlement();	// 单据pk
	    		
	    		// 0-客户 1-供应商 2-部门 3-个人 4-散户
	    		Integer df_type = bodyVOs[0].getTradertype();	// 对方类型
	    		
	    		String org_name = org_info_MAP.get(pk_org);		// 组织名称
	    		
	    		String email = null;	// Email
	    		if(df_type==1)
	    			email = gys_info_MAP.get(df_pk);
	    		else if(df_type==3)
	    			email = psn_info_MAP.get(df_pk);
	    		else if(df_type==0)
	    			email = kh_info_MAP.get(df_pk);
	    		
	    		// 邮件标题
	    		String title = "【"+org_name+"】付款凭证["+billCode+"]";
	    		
	    		// 邮件正文
	    		StringBuffer memo = new StringBuffer(
	    				"尊敬的<B>"+df_name+"</B>：" +
	    				"<br>" +
	    				"<p style='text-indent:2em;'>" +
	    				"我集团<B>"+org_name+"</B>本次支付您的款项已成功付款，" +
	    				"收款账号<B>"+account+"</B>，" +
	    				"金额<B>"+mny+"</B>元，" +
	    				"宏昆付款单据号：<B>"+billCode+"</B>，" +
	    				"请您查收。" +
	    				"</p>" +
	    				"<p style='text-indent:2em;'>" +
	    				"若您的联系人、银行信息、收信邮箱等有变更，请及时通知我们010-53357312。" +
	    				"</p>" +
	    				"<br>" +
	    				"致礼！"
    			);
    			
	    		try
	    		{
	    			if(email==null)
	    			{
	    				throw new Exception("目的邮箱不能为空。");
	    			}
	    			if(accountVO==null
	    			|| accountVO.getVsendaddr()==null
	    			|| accountVO.getVmailaddr()==null
	    			|| accountVO.getVusername()==null
	    			|| accountVO.getVpassword()==null
	    			)
	    			{
	    				throw new Exception("发送邮箱不能为空。");
	    			}
	    			// 发送邮件
	    			MailTool.sendHtmlEmail(
	    					accountVO.getVsendaddr(),	// SMTP服务器IP地址
	    					accountVO.getVmailaddr(),	// 发送人邮件地址，必须为有效的电邮地址
	    					accountVO.getVmailaddr(),	// 发送人名称，可以与发送人邮件地址一样
	    					accountVO.getVusername(),	// 发送者邮件账户名
	    					accountVO.getVpassword(),	// 发送者账户密码
	    					email,	// 目的邮件地址，可以","分隔的多个地址
	    					title,	// 邮件主题，即标题
	    					memo,	// HTML格式的邮件内容
	    					null	// 附件文件名，全路径
	    			);
	    			
	    			//发送成功之后，记录发送邮件次数+1
					String sql = 
							 " update cmp_settlement set def20 = ( "
							+" case when (def20 ='~' or def20 =null or def20 ='') "
							+" then '1' "
							+" else " 
							+" to_char(to_number(def20)+1) "
							+" end "
							+" ) where pk_settlement = '"+pk_settlement+"' "
					;
					dao.setAddTimeStamp(false);	// 不更新时间戳
					int returnRow = dao.executeUpdate(sql);
					
					resultMsg += ""+billCode+"发送成功。"+"\r\n";
					
	    		}catch(Exception ex)
	    		{
	    			ex.printStackTrace();
	    			resultMsg += ""+billCode+"失败！"+ex.getMessage()+"\r\n";
	    		}
    			
	    	}
	    	
	    }
	    catch (Exception e) {
	    	throw new BusinessException(e);
	    }
		
		return resultMsg;
		
	}

	@Override
	public Object genCktzdByPoInvoice(InvoiceVO[] poInvoiceVOs, Object ohter)
			throws BusinessException {
		
		try {
			
			BaseDAO dao = new BaseDAO();
			IIAMaintain IAitf = (IIAMaintain)NCLocator.getInstance().lookup(IIAMaintain.class.getName());
			
			for(InvoiceVO billVO : poInvoiceVOs) {
				
				InvoiceHeaderVO hVO  = billVO.getParentVO();
				InvoiceItemVO[] bVOs = billVO.getChildrenVO();
				
//				String pk_org = hVO.getPk_org();
				String pk_group = hVO.getPk_group();
//				String yyyymm = "2019-05";
				
				/**
				 * 1、根据发票 查询 采购结算单、以及下游入库单的信息。
				 */
				// 结算信息的MAP  key:发票bid  value:入库code\入库数量\入库金额\账簿\结算id\入库日期\成本域\会计期间计算日期\结算bid\结算单号\结算行号\采购入库的期间
				HashMap<String,ArrayList<Object[]>> MAP_JS = new HashMap<String,ArrayList<Object[]>>();	
				{
					StringBuffer querySQL_1 = 
					new StringBuffer("select ")
							.append(" js.PK_SETTLEBILL")		// 0、结算id
							.append(",jsb.PK_SETTLEBILL_B ")	// 1、结算bid
							.append(",jsb.PK_INVOICE ")			// 2、发票id
							.append(",jsb.PK_INVOICE_B ")		// 3、发票bid
							.append(",rk.cbillid ")				// 4、调整入库id
							.append(",rkb.cbill_bid ")			// 5、调整入库bid
							.append(",rk.VBILLCODE ")			// 6、调整入库code
							.append(",rkb.nnum ")				// 7、调整入库数量
							.append(",rkb.nmny ")				// 8、调整入库金额
							.append(",rk.pk_book ")				// 9、账簿
							.append(",rk.dbilldate ")			//10、调整入库日期
							.append(",rk.pk_org ")				//11、成本域
							.append(",rkb.daccountdate ")		//12、调整入库会计期间计算日期 
							.append(",js.vbillcode ")			//13、结算单号
							.append(",jsb.crowno ")				//14、结算行号	
							.append(",rk.caccountperiod ")		//15、采购入库的期间
							// 结算单
							.append(" from po_settlebill js ")	
							.append(" inner join po_settlebill_b jsb on js.pk_settlebill = jsb.pk_settlebill ")
							// 入库单
							.append(" inner join IA_I2BILL_B rkb on rkb.csrcbid = jsb.pk_settlebill_b ")
							.append(" inner join IA_I2BILL rk on rkb.cbillid = rk.cbillid ")
							// where
							.append(" where js.dr=0 and jsb.dr=0 and rkb.dr=0 and rk.dr=0 ")
							.append(" and jsb.PK_INVOICE = '"+hVO.getPk_invoice()+"' ")	// 发票id
					;
					ArrayList list = (ArrayList)dao.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
					for(Object obj : list) {
						Object[] value = (Object[])obj;
						String pk_js 	= PuPubVO.getString_TrimZeroLenAsNull(value[0]);
						String pk_js_b 	= PuPubVO.getString_TrimZeroLenAsNull(value[1]);
						String pk_fp 	= PuPubVO.getString_TrimZeroLenAsNull(value[2]);
						String pk_fp_b 	= PuPubVO.getString_TrimZeroLenAsNull(value[3]);
						String pk_rk 	= PuPubVO.getString_TrimZeroLenAsNull(value[4]);
						String pk_rk_b 	= PuPubVO.getString_TrimZeroLenAsNull(value[5]);
						String rkCode 	= PuPubVO.getString_TrimZeroLenAsNull(value[6]);
						UFDouble rkNum 	= PuPubVO.getUFDouble_NullAsZero(value[7]);
						UFDouble rkMny 	= PuPubVO.getUFDouble_NullAsZero(value[8]);
						String pk_book 	= PuPubVO.getString_TrimZeroLenAsNull(value[9]);
						UFDate rkDate	= PuPubVO.getUFDate(value[10]);		// 调整入库-日期
						String pk_org 	= PuPubVO.getString_TrimZeroLenAsNull(value[11]);	// 成本域
						UFDate rkkjDate = PuPubVO.getUFDate(value[12]);		// 调整入库-会计期间计算日期 
						String jsCode 	= PuPubVO.getString_TrimZeroLenAsNull(value[13]);	// 结算单号
						String jsRowno 	= PuPubVO.getString_TrimZeroLenAsNull(value[14]);	// 结算行号
						String yyyymm 	= PuPubVO.getString_TrimZeroLenAsNull(value[15]);	// 期间（以生成的调整入库为准）
						
						Object[] value_item = 
							new Object[]{
								rkCode,		// 0、调整入库单号
								rkNum,		// 1、调整入库num
								rkMny,		// 2、调整入库mny
								pk_book,	// 3、账簿
								pk_js,		// 4、结算id
								rkDate,		// 5、调整入库 单据日期
								pk_org,		// 6、成本域
								rkkjDate,	// 7、调整入库 会计日期
								pk_js_b,	// 8、结算bid
								jsCode,		// 9、结算单号
								jsRowno,	//10、结算行号
								yyyymm,		//11、期间
							};
						
						String MAP_key = pk_fp_b;	// Map的key = 发票bid
						if(MAP_JS.containsKey(MAP_key)) {
							MAP_JS.get(MAP_key).add(
								value_item
							);
						}
						else {
							ArrayList<Object[]> MAP_value = new ArrayList<Object[]>();
							MAP_value.add(
								value_item
							);
							MAP_JS.put(MAP_key, MAP_value);
						}
					}
				}
				
				/**
				 * 2、根据表体进行循环
				 */
				for(InvoiceItemVO bVO : bVOs) {
					String pk_fp_b = bVO.getPk_invoice_b();
					ArrayList<Object[]> MAP_value = MAP_JS.get(pk_fp_b);
					if(MAP_value!=null && MAP_value.size()==2) {
						
						UFDouble xx_mny = // 调整的总金额
							  PuPubVO.getUFDouble_NullAsZero(MAP_value.get(0)[2])
						.add( PuPubVO.getUFDouble_NullAsZero(MAP_value.get(1)[2]) );
						
						UFDouble xx_num = // 调整的总数量
							PuPubVO.getUFDouble_NullAsZero(MAP_value.get(0)[1]).abs();	// 取绝对值
						
						if(
							PuPubVO.getUFDouble_ZeroAsNull(xx_mny)!=null
						&&	PuPubVO.getUFDouble_ZeroAsNull(xx_num)!=null
						) {
							// 只有 存在金额差，才需要往下进行。
							UFDouble xx_price = xx_mny.div(xx_num);	// 需要调整的单价
							String pk_book 	  = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[3]);	// 账簿
							String pk_js      = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[4]);	// 结算id
							UFDate rkDate	  = PuPubVO.getUFDate(MAP_value.get(0)[5]);						// 调整入库日期
							String pk_org     = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[6]);	// 成本域
							UFDate rkkjDate	  = PuPubVO.getUFDate(MAP_value.get(0)[7]);						// 调整入库会计计算日期
							String pk_js_b    = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[8]);	// 成本域
							String jsCode     = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[9]);	// 成本域
							String jsRowno    = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[10]);	// 成本域
							String yyyymm     = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[11]);	// 期间
							String pk_inv  	  = bVO.getPk_material();				// 物料v
							String pk_inv_src = bVO.getPk_srcmaterial();			// 物料src
							String pk_cgrk 	  = bVO.getCsourceid();					// 发票对应的采购入库id
							/**
							 ****** 3、查询 存货明细账
							 */
							/**
							 * 3.1 期初
							 */
							StringBuffer querySQL_3_1 = 
							new StringBuffer("select ")
									.append(" ia_monthnab.nabnum ")
									.append(",ia_monthnab.nabmny ")
									.append(" from ia_monthnab ")
									.append(" inner join ia_calcrange on ia_monthnab.ccalcrangeid = ia_calcrange.ccalcrangeid ")
									.append(" where ia_monthnab.dr = 0 ")
									.append(" and ia_monthnab.caccountperiod = '"+ReportDataUtil.getYyyymmAddMm(yyyymm,-1)+"' ")
									.append(" and ia_monthnab.pk_org = '"+pk_org+"' ")
									.append(" and ia_monthnab.pk_book = '"+pk_book+"' ")
									.append(" and ia_monthnab.cinventoryid = '"+pk_inv+"' ")
									.append(" and ia_monthnab.pk_group = '"+pk_group+"' ")
							;
							ArrayList list_3_1 = (ArrayList)dao.executeQuery(querySQL_3_1.toString(), new ArrayListProcessor());
							UFDouble QC_num = UFDouble.ZERO_DBL;	// 期初数量
							UFDouble QC_mny = UFDouble.ZERO_DBL;	// 期初金额
							if(list_3_1!=null&&list_3_1.size()>0) {
								Object[] value = (Object[])list_3_1.get(0);
								QC_num = PuPubVO.getUFDouble_NullAsZero(value[0]);
								QC_mny = PuPubVO.getUFDouble_NullAsZero(value[1]);
							}
							/**
							 * 3.2 本期发生
							 */
							StringBuffer querySQL_3_2 = 
							new StringBuffer("select ")
									.append(" ia_detailledger.csrcid ")			// 0、来源id（如果是结算单生成的入库，则来源id为结算id）
									.append(",ia_detailledger.vbillcode ")		// 1、单号
									.append(",ia_detailledger.cbilltypecode ")	// 2、类型
									.append(",ia_detailledger.fdispatchflag ")	// 3、0-入库  1-出库
									.append(",ia_detailledger.nnum ")			// 4、数量
									.append(",case when fpricemodeflag = 5 then nplanedprice else nprice end nprice ")	// 5、单价
									.append(",ia_detailledger.nmny ")			// 6、金额
									.append(",ia_detailledger.cdeptid ")		// 7、部门id
									.append(",ia_detailledger.cdeptvid ")		// 8、部门vid
									.append(",ia_detailledger.cstockorgid ")	// 9、库存组织id
									.append(",ia_detailledger.cstockorgvid ")	//10、库存组织vid
									.append(",ia_detailledger.cstordocid ")		//11、仓库id
									.append(" from ia_detailledger ")
									.append(" inner join ia_calcrange on ia_detailledger.ccalcrangeid = ia_calcrange.ccalcrangeid ")
									.append(" where ")
									.append(" ia_detailledger.dr = 0 ")
									.append(" and ia_detailledger.caccountperiod >= '"+yyyymm+"' and ia_detailledger.caccountperiod <= '"+yyyymm+"' ")
									.append(" and ia_detailledger.pk_org = '"+pk_org+"' ")
									.append(" and ia_detailledger.pk_book = '"+pk_book+"' ")
									.append(" and ia_detailledger.cinventoryid = '"+pk_inv+"' ")
									.append(" and ia_detailledger.pk_group = '"+pk_group+"' ")
									.append(" and ia_detailledger.fintransitflag in ( - 1, 0 ) ")
									.append(" and ia_detailledger.cbilltypecode not in ( 'IG' ) ")
									.append(" order by ")
									.append(" ia_detailledger.cinventoryid, ia_calcrange.vbatchcode, ia_calcrange.ccalcrangeid, ia_detailledger.caccountperiod ")
									.append(",case when ia_detailledger.iauditsequence = - 1 then 2147483647 else ia_detailledger.iauditsequence end ")
									.append(",ia_detailledger.dbizdate, ia_detailledger.dbilldate, ia_detailledger.creationtime ")
							;
							ArrayList list_3_2 = (ArrayList)dao.executeQuery(querySQL_3_2.toString(), new ArrayListProcessor());
							Integer index_cgrk = list_3_2.size();	// 发票采购入库单 的 所在行。之后的出库 才需要处理。到 调整的入库单为止。
							boolean find_cgrk = false;				// 是否定位到了 采购入库
							Integer index_tzrk = -1;				// 发票调整入库单 的所在行，之前的才处理，之后不做处理。
							// 针对 存货明细账 的本期发生  来循环处理
							for(int i=0 ; list_3_2!=null&&i<list_3_2.size() ; i++) {
								
								Object[] value = (Object[])list_3_2.get(i);
								String cbillid 		 = PuPubVO.getString_TrimZeroLenAsNull(value[0]);
								String vbillcode 	 = PuPubVO.getString_TrimZeroLenAsNull(value[1]);
								String cbilltypecode = PuPubVO.getString_TrimZeroLenAsNull(value[2]);
								Integer fdispatchflag= PuPubVO.getInteger_NullAs(value[3],0);
								UFDouble ch_nnum 	 = PuPubVO.getUFDouble_ZeroAsNull(value[4]);
								UFDouble ch_nprice 	 = PuPubVO.getUFDouble_ZeroAsNull(value[5]);
								UFDouble ch_nmny 	 = PuPubVO.getUFDouble_ZeroAsNull(value[6]);
								String cdeptid 		 = PuPubVO.getString_TrimZeroLenAsNull(value[7]);
								String cdeptvid 	 = PuPubVO.getString_TrimZeroLenAsNull(value[8]);
								String cstockorgid 	 = PuPubVO.getString_TrimZeroLenAsNull(value[9]);
								String cstockorgvid  = PuPubVO.getString_TrimZeroLenAsNull(value[10]);
								String cstordocid	 = PuPubVO.getString_TrimZeroLenAsNull(value[11]);
								
								/**
								 * a、如果定位到 发票对应的采购入库单。
								 *    则开始处理。期初已经取完了。不再往下执行，跳过。
								 */
								if( pk_cgrk.equals(cbillid) ) {
									index_cgrk = i;
									find_cgrk = true;
									continue;
								}
								/**
								 * b、如果定位到 发票对应的调整入库单。
								 * 	     则停止处理。出库已经处理完毕，退出。
								 */
								else if( pk_js.equals(cbillid) ) {
									index_tzrk = i;
									break;
								}
								/**
								 * c、入库之前的为期初（数量、金额）
								 *     只有当 未找到 采购入库 时，才进行期初的封装。
								 */
								else if( !find_cgrk ){
									if( fdispatchflag==0 ) {		// 入库
										QC_num = QC_num.add(ch_nnum);
										QC_mny = QC_num.add(ch_nmny);
									}else if(  fdispatchflag==1  ) {// 出库
										QC_num = QC_num.sub(ch_nnum);
										QC_mny = QC_num.sub(ch_nmny);
									}
								}
								
								if( fdispatchflag==1 && ch_nnum!=null) {
									// 出库 并且 数量不为空  则需要处理。
									if(i>index_cgrk) {
										// 并且 当前索引大于 index_cgrk  才进行处理
										{
											
											UFDouble tz_mny = null;	// 本次要调整的金额， 不为空，才生单。
											
											// 调整总额 不为空，才进行处理。
											if( xx_mny != null ) {
												// 如果期初数量 大于0  则先抵扣期初
												if( QC_num.compareTo(UFDouble.ZERO_DBL)>=0 ) {
													UFDouble temp_xx_num = QC_num.sub(ch_nnum);	// 与期初抵扣的差额 = 期初-本行出库数量
													if(temp_xx_num.compareTo(UFDouble.ZERO_DBL)>=0) {
														// 如果差额 大于0，说明 期初大于 本次要处理的出库，不做处理。
														// 将差额 赋值给 期初 即可。
														QC_num = temp_xx_num;
													}
													else {
														QC_num = UFDouble.ZERO_DBL;	// 期初 置为 0
														// 如果差额 小于0，说明 期初不够了， 要从 采购入库里取，并且需要生成 调整单了。
														UFDouble do_nnum = temp_xx_num.abs();			// 需要处理的数量
														UFDouble temp_xxxx_num = xx_num.sub(do_nnum);	// 与 采购入库抵扣的差额
														if(temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)>0) {
															xx_num = temp_xxxx_num;		// 将差额 赋值给 采购入库数量
															// 如果差额大于零， 说明不是最后一次，则用 调差单价*处理数量   来计算 调整金额。
															tz_mny = xx_price.multiply(do_nnum).setScale(2, UFDouble.ROUND_HALF_UP);
															xx_mny = xx_mny.sub(tz_mny);	// 扣减 调整总额。
														}
														else if (temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)<=0) {
															// 如果差额小于等于零，说明是最后一次，直接将剩余金额 拿过来。
															tz_mny = xx_mny;
															xx_mny = null;	// 调整完毕，将 调整总额 置空。
															xx_num = null;	// 调整完毕，将 调整数量 置空。
														}
													}
												}
												// 如果期初数量为0了，则判断 采购入库数量,如果不为空，则进行处理。
												else if ( xx_num != null ) {
													UFDouble do_nnum = ch_nnum;						// 需要处理的数量
													UFDouble temp_xxxx_num = xx_num.sub(do_nnum);	// 与采购入库抵扣的差额 = 入库-本行出库数量
													if(temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)>0) {
														xx_num = temp_xxxx_num;		// 将差额 赋值给 采购入库数量
														// 如果差额大于零， 说明不是最后一次，则用 调差单价*处理数量   来计算 调整金额。
														tz_mny = xx_price.multiply(do_nnum).setScale(2, UFDouble.ROUND_HALF_UP);
														xx_mny = xx_mny.sub(tz_mny);	// 扣减 调整总额。
													}
													else if (temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)<=0) {
														// 如果差额小于等于零，说明是最后一次，直接将剩余金额 拿过来。
														tz_mny = xx_mny;
														xx_mny = null;	// 调整完毕，将 调整总额 置空。
														xx_num = null;	// 调整完毕，将 调整数量 置空。
													}
												}
											}
											
											if( tz_mny==null ) continue;	// 如果本次调整金额 为空，则跳过。
											
											// 封装 出库调整单的数据
											IABillVO iaBillVO = new IABillVO();
											// 表头
											IAHeadVO iaHVO = new IAHeadVO();
											iaHVO.setBconvertflag(UFBoolean.FALSE);
											iaHVO.setBsystemflag(UFBoolean.FALSE);
											iaHVO.setCaccountperiod(yyyymm);
											iaHVO.setCdeptid(cdeptid);			// 找出库单的部门
											iaHVO.setCdeptvid(cdeptvid);		// 找出库单的部门v
											iaHVO.setCstockorgid(cstockorgid);	// 找出库单的库存组织
											iaHVO.setCstockorgvid(cstockorgvid);// 找出库单的库存组织v
											iaHVO.setCstordocid(cstordocid);	// 找出库单的仓库
											iaHVO.setDbilldate(rkDate);	// 与调整入库单的一致
											iaHVO.setDr(0);
											iaHVO.setFintransitflag(-1);
											iaHVO.setPk_book(pk_book);
											iaHVO.setPk_group(pk_group);
											iaHVO.setPk_org(pk_org);
											iaHVO.setVnote("调整【"+vbillcode+"】的成本");
											// 表体
											IAItemVO iaBVO = new IAItemVO();
											iaBVO.setBreworkflag(UFBoolean.FALSE);
											iaBVO.setCaccountperiod(yyyymm);
											iaBVO.setCcurrencyid("1002Z0100000000001K1");	// 币种（可以固定）
											iaBVO.setCinventoryid(pk_inv_src);	// 物料src
											iaBVO.setCinventoryvid(pk_inv);		// 物料v
											iaBVO.setCrowno("10");
											iaBVO.setDaccountdate(rkkjDate);	// 会计期间计算日期 与 调整入库 的一致
											iaBVO.setDbilldate(rkDate);
											iaBVO.setDbizdate(rkDate);
											iaBVO.setDr(0);
											iaBVO.setNmny(tz_mny);	// 需要计算 = 调整单价*出库数量
											iaBVO.setPk_book(pk_book);
											iaBVO.setPk_group(pk_group);
											iaBVO.setPk_org(pk_org);
											iaBVO.setAttributeValue("pseudocolumn", 0);
											// 来源信息
											iaBVO.setVsrctype("27");		// 来源单据类型  = 结算单
											iaBVO.setVsrcrowno(jsRowno);	// 来源行号
											iaBVO.setVsrccode(jsCode);		// 来源单号
											iaBVO.setCsrcid(pk_js);			// 来源id
											iaBVO.setCsrcbid(pk_js_b);		// 来源bid
											// 组合保存
											iaBillVO.setParentVO(iaHVO);
											iaBillVO.setChildrenVO(new IAItemVO[]{iaBVO});
											IABillVO[] result = IAitf.insertIA(new IABillVO[]{iaBillVO});
										}
									}
								}
							}
						}
					}
				}
			}
		} catch(Exception ex) {
			throw new BusinessException(ex);
		}
		return null;
	}
}
