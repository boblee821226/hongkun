package nc.itf.hkjt.report;

import java.util.HashMap;

import nc.pub.pu.m21.mail.SendMailInfo_dqy;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.vo.pub.BusinessException;

public interface HkjtReportITF {

	/**
	 * ���б����ѯ��ͳһ��ڷ��� 
	 */
	public DataSet queryReport(
			SmartContext context,
			HashMap<String, Object> param,
			String type,
			String flag,
			Object other
	) throws Exception;

	/**
	 * �ֽ����-����  �����ʼ�
	 */
	public Object sendMail(Object data,Object other)
	throws BusinessException;
	
}
