package nc.itf.hkjt.report;

import java.util.HashMap;

import nc.pub.pu.m21.mail.SendMailInfo_dqy;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.vo.pub.BusinessException;

public interface HkjtReportITF {

	/**
	 * 所有报表查询的统一入口方法 
	 */
	public DataSet queryReport(
			SmartContext context,
			HashMap<String, Object> param,
			String type,
			String flag,
			Object other
	) throws Exception;

	/**
	 * 现金管理-结算  发送邮件
	 */
	public Object sendMail(Object data,Object other)
	throws BusinessException;
	
}
