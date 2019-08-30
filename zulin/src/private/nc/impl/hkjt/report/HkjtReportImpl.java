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
		if( "HYKPXXCXB".equals(type) ){			// ��Ա��Ʊ��Ϣ��ѯ��
			dataset = new Report_HYKPXXCXB().DO(context, param, flag, other);
		}
		
		return dataset;
	}

	@Override
	public Object sendMail(Object data,Object other) throws BusinessException {
		
		String resultMsg = "";	// ���ص���Ϣ
		
		try {
			
			// ��ѯĬ�Ϸ�������Ϣ
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
	    	 * 1����һ��ѭ��  ���ܹ�Ӧ��pk����Ա��Ϣpk
	    	 */
	    	for( int i=0;i<aggVOs_obj.length;i++)
	    	{
	    		SettlementAggVO aggVO = (SettlementAggVO)aggVOs_obj[i];
	    		
	    		SettlementHeadVO   headVO  = (SettlementHeadVO)aggVO.getParentVO();
	    		SettlementBodyVO[] bodyVOs = (SettlementBodyVO[])aggVO.getChildrenVO();
	    		
	    		String 	 pk_org = headVO.getPk_org();			// ������֯pk
	    		String	  df_pk = bodyVOs[0].getPk_trader();	// �Է�pk
	    		// 0-�ͻ� 1-��Ӧ�� 2-���� 3-���� 4-ɢ��
	    		Integer df_type = bodyVOs[0].getTradertype();	// �Է�����
	    		
	    		if(df_type==1)
	    			pk_gys_str += ",'"+df_pk+"'";
	    		else if(df_type==3)
	    			pk_psn_str += ",'"+df_pk+"'";
	    		else if(df_type==0)
	    			pk_kh_str += ",'"+df_pk+"'";
	    		
	    		pk_org_str += ",'"+pk_org+"'";
	    		
	    	}
	    	
	    	/**
	    	 * 2�����ݹ�Ӧ��\��Ա\�ͻ�  ��ѯ��  ����
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
	    	 * 3���ڶ���ѭ�������з��ʹ���
	    	 */
	    	for( int i=0;i<aggVOs_obj.length;i++)
	    	{
	    		SettlementAggVO aggVO = (SettlementAggVO)aggVOs_obj[i];
	    		
	    		SettlementHeadVO   headVO  = (SettlementHeadVO)aggVO.getParentVO();
	    		SettlementBodyVO[] bodyVOs = (SettlementBodyVO[])aggVO.getChildrenVO();
	    		
	    		String billCode = headVO.getBillcode();			// ҵ�񵥺�
	    		String 	 pk_org = headVO.getPk_org();			// ������֯pk
	    		UFDouble 	mny = headVO.getPrimal();			// ���
	    		String 	df_name = bodyVOs[0].getTradername();	// �Է���������
	    		String 	account = bodyVOs[0].getOppaccount();	// �Է��˻�
	    		String	  df_pk = bodyVOs[0].getPk_trader();	// �Է�pk
	    		
	    		String pk_settlement = headVO.getPk_settlement();	// ����pk
	    		
	    		// 0-�ͻ� 1-��Ӧ�� 2-���� 3-���� 4-ɢ��
	    		Integer df_type = bodyVOs[0].getTradertype();	// �Է�����
	    		
	    		String org_name = org_info_MAP.get(pk_org);		// ��֯����
	    		
	    		String email = null;	// Email
	    		if(df_type==1)
	    			email = gys_info_MAP.get(df_pk);
	    		else if(df_type==3)
	    			email = psn_info_MAP.get(df_pk);
	    		else if(df_type==0)
	    			email = kh_info_MAP.get(df_pk);
	    		
	    		// �ʼ�����
	    		String title = "��"+org_name+"������ƾ֤["+billCode+"]";
	    		
	    		// �ʼ�����
	    		StringBuffer memo = new StringBuffer(
	    				"�𾴵�<B>"+df_name+"</B>��" +
	    				"<br>" +
	    				"<p style='text-indent:2em;'>" +
	    				"�Ҽ���<B>"+org_name+"</B>����֧�����Ŀ����ѳɹ����" +
	    				"�տ��˺�<B>"+account+"</B>��" +
	    				"���<B>"+mny+"</B>Ԫ��" +
	    				"��������ݺţ�<B>"+billCode+"</B>��" +
	    				"�������ա�" +
	    				"</p>" +
	    				"<p style='text-indent:2em;'>" +
	    				"��������ϵ�ˡ�������Ϣ������������б�����뼰ʱ֪ͨ����010-53357312��" +
	    				"</p>" +
	    				"<br>" +
	    				"����"
    			);
    			
	    		try
	    		{
	    			if(email==null)
	    			{
	    				throw new Exception("Ŀ�����䲻��Ϊ�ա�");
	    			}
	    			if(accountVO==null
	    			|| accountVO.getVsendaddr()==null
	    			|| accountVO.getVmailaddr()==null
	    			|| accountVO.getVusername()==null
	    			|| accountVO.getVpassword()==null
	    			)
	    			{
	    				throw new Exception("�������䲻��Ϊ�ա�");
	    			}
	    			// �����ʼ�
	    			MailTool.sendHtmlEmail(
	    					accountVO.getVsendaddr(),	// SMTP������IP��ַ
	    					accountVO.getVmailaddr(),	// �������ʼ���ַ������Ϊ��Ч�ĵ��ʵ�ַ
	    					accountVO.getVmailaddr(),	// ���������ƣ������뷢�����ʼ���ַһ��
	    					accountVO.getVusername(),	// �������ʼ��˻���
	    					accountVO.getVpassword(),	// �������˻�����
	    					email,	// Ŀ���ʼ���ַ������","�ָ��Ķ����ַ
	    					title,	// �ʼ����⣬������
	    					memo,	// HTML��ʽ���ʼ�����
	    					null	// �����ļ�����ȫ·��
	    			);
	    			
	    			//���ͳɹ�֮�󣬼�¼�����ʼ�����+1
					String sql = 
							 " update cmp_settlement set def20 = ( "
							+" case when (def20 ='~' or def20 =null or def20 ='') "
							+" then '1' "
							+" else " 
							+" to_char(to_number(def20)+1) "
							+" end "
							+" ) where pk_settlement = '"+pk_settlement+"' "
					;
					dao.setAddTimeStamp(false);	// ������ʱ���
					int returnRow = dao.executeUpdate(sql);
					
					resultMsg += ""+billCode+"���ͳɹ���"+"\r\n";
					
	    		}catch(Exception ex)
	    		{
	    			ex.printStackTrace();
	    			resultMsg += ""+billCode+"ʧ�ܣ�"+ex.getMessage()+"\r\n";
	    		}
    			
	    	}
	    	
	    }
	    catch (Exception e) {
	    	throw new BusinessException(e);
	    }
		
		return resultMsg;
		
	}
	
}
