package nc.impl.hkjt.report;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.mail.MailTool;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pubitf.pp.m29.IAccountQuery;
import nc.vo.cmp.settlement.SettlementAggVO;
import nc.vo.cmp.settlement.SettlementBodyVO;
import nc.vo.cmp.settlement.SettlementHeadVO;
import nc.vo.pp.m29.account.entity.AccountVO;
import nc.vo.pub.BusinessException;
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
	
}
