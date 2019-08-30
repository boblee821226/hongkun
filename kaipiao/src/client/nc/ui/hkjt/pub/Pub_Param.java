package nc.ui.hkjt.pub;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.busibean.ISysInitQry;
import nc.jdbc.framework.processor.ArrayListProcessor;

public class Pub_Param {

	public static String FPDM;	// 发票代码
	public static String FPHM;	// 发票号码
	public static String FPLENGTH;// 票号长度(可能是多个，用逗号分隔)
	
	public static HashMap<String,String> SXSJ_MAP;	// 上线时间
	
	public static Integer KPQX = 6;	// 开票期限（默认为 6个月）
	
	{
		try
		{
			IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
			String cuserid = WorkbenchEnvironment.getInstance().getLoginUser().getCuserid();	// 用户id
			
			ISysInitQry iSysInitQry = (ISysInitQry)NCLocator.getInstance().lookup(ISysInitQry.class);
			
			/**
			 * 1、发票代码、发票号码
			 */
			{
				StringBuffer querySQL = 
					new StringBuffer(" select ")
							.append(" fpdm ")
							.append(",fphm ")
							.append(" from hk_fapiao_setdata ")
							.append(" where cuserid ='"+cuserid+"' ")
				;
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
				if(list!=null && list.size()>0)
				{
					Object[] obj = (Object[])list.get(0);
					FPDM = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
					FPHM = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
				}
			}
			/**
			 * 1.1、取 票号长度
			 */
			{
				try
				{
					FPLENGTH = iSysInitQry.getParaString("0001N510000000000EGY","HK0001");
					
				}catch(Exception ex)
				{
					FPLENGTH = "18,20";
				}
			}
			/**
			 * 2、上线时间 、开票期限
			 */
			{
				StringBuffer querySQL = 
					new StringBuffer(" select ")
							.append(" pk_org ")
							.append(",initvalue ")
							.append(",initcode ")
							.append(" from HK_FAPIAO_SYSINIT ")
							.append(" where nvl(dr,0)=0 ")
				;
				ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
				if(list!=null && list.size()>0)
				{
					SXSJ_MAP = new HashMap<String,String>();
					
					for(int i=0;i<list.size();i++)
					{
						Object[] obj = (Object[])list.get(i);
						String initcode = PuPubVO.getString_TrimZeroLenAsNull(obj[2]);
						if( "kpqx".equals(initcode) )
						{
							KPQX = PuPubVO.getInteger_NullAs(obj[1],6);		// 默认为 6
						}
						else if( "sxsj".equals(initcode) )
						{
							SXSJ_MAP.put(
									PuPubVO.getString_TrimZeroLenAsNull(obj[0])
								   ,PuPubVO.getString_TrimZeroLenAsNull(obj[1])
							);
						}
					}
				}
			}
		}
		catch(Exception ex)
		{ex.printStackTrace();}
	}
	
	private Pub_Param(){}
	private static Pub_Param instance = null;
	public static Pub_Param getInstance()
	{
		synchronized(nc.ui.hkjt.pub.Pub_Param.class)
	    {
	        if(instance == null)
	            instance = new Pub_Param();
	    }
	    return instance;
	}
}
