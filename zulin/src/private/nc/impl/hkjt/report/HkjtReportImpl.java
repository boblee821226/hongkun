package nc.impl.hkjt.report;

import hd.vo.pub.tools.PuPubVO;
import hd.vo.pub.tools.ReportDataUtil;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.mail.MailTool;
import nc.itf.fa.prv.IAlter;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.itf.ia.mia.IIAMaintain;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pub.billcode.itf.IBillcodeManage;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pubitf.pp.m29.IAccountQuery;
import nc.vo.am.common.TransportBillVO;
import nc.vo.cmp.settlement.SettlementAggVO;
import nc.vo.cmp.settlement.SettlementBodyVO;
import nc.vo.cmp.settlement.SettlementHeadVO;
import nc.vo.fa.alter.AlterBodyVO;
import nc.vo.fa.alter.AlterHeadVO;
import nc.vo.fa.alter.AlterVO;
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
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sf.allocateapply.AggAllocateApplyVO;

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

	@Override
	public Object genCktzdByPoInvoice(InvoiceVO[] poInvoiceVOs, Object ohter)
			throws BusinessException {
		
		try {
			
			BaseDAO dao = new BaseDAO();
			IIAMaintain IAitf = (IIAMaintain)NCLocator.getInstance().lookup(IIAMaintain.class.getName());
			
			for(InvoiceVO billVO : poInvoiceVOs) {
				
				InvoiceHeaderVO hVO  = billVO.getParentVO();
				InvoiceItemVO[] bVOs = billVO.getChildrenVO();
				
				String pk_group = hVO.getPk_group();
				
				/**
				 * 1�����ݷ�Ʊ ��ѯ �ɹ����㵥���Լ�������ⵥ����Ϣ��
				 */
				// ������Ϣ��MAP  key:��Ʊbid  value:���code\�������\�����\�˲�\����id\�������\�ɱ���\����ڼ��������\����bid\���㵥��\�����к�\�ɹ������ڼ�
				HashMap<String,ArrayList<Object[]>> MAP_JS = new HashMap<String,ArrayList<Object[]>>();	
				{
					StringBuffer querySQL_1 = 
					new StringBuffer("select ")
							.append(" js.PK_SETTLEBILL")		// 0������id
							.append(",jsb.PK_SETTLEBILL_B ")	// 1������bid
							.append(",jsb.PK_INVOICE ")			// 2����Ʊid
							.append(",jsb.PK_INVOICE_B ")		// 3����Ʊbid
							.append(",rk.cbillid ")				// 4���������id
							.append(",rkb.cbill_bid ")			// 5���������bid
							.append(",rk.VBILLCODE ")			// 6���������code
							.append(",rkb.nnum ")				// 7�������������
							.append(",rkb.nmny ")				// 8�����������
							.append(",rk.pk_book ")				// 9���˲�
							.append(",rk.dbilldate ")			//10�������������
							.append(",rk.pk_org ")				//11���ɱ���
							.append(",rkb.daccountdate ")		//12������������ڼ�������� 
							.append(",js.vbillcode ")			//13�����㵥��
							.append(",jsb.crowno ")				//14�������к�	
							.append(",rk.caccountperiod ")		//15���ɹ������ڼ�
							// ���㵥
							.append(" from po_settlebill js ")	
							.append(" inner join po_settlebill_b jsb on js.pk_settlebill = jsb.pk_settlebill ")
							// ��ⵥ
							.append(" inner join IA_I2BILL_B rkb on rkb.csrcbid = jsb.pk_settlebill_b ")
							.append(" inner join IA_I2BILL rk on rkb.cbillid = rk.cbillid ")
							// where
							.append(" where js.dr=0 and jsb.dr=0 and rkb.dr=0 and rk.dr=0 ")
							.append(" and jsb.PK_INVOICE = '"+hVO.getPk_invoice()+"' ")	// ��Ʊid
					;
					ArrayList list = (ArrayList)dao.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
					
					if(list==null || list.size()<=0) continue;	// �����ѯ���� ��Ҫ��������ݣ�������
					
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
						UFDate rkDate	= PuPubVO.getUFDate(value[10]);		// �������-����
						String pk_org 	= PuPubVO.getString_TrimZeroLenAsNull(value[11]);	// �ɱ���
						UFDate rkkjDate = PuPubVO.getUFDate(value[12]);		// �������-����ڼ�������� 
						String jsCode 	= PuPubVO.getString_TrimZeroLenAsNull(value[13]);	// ���㵥��
						String jsRowno 	= PuPubVO.getString_TrimZeroLenAsNull(value[14]);	// �����к�
						String yyyymm 	= PuPubVO.getString_TrimZeroLenAsNull(value[15]);	// �ڼ䣨�����ɵĵ������Ϊ׼��
						
						Object[] value_item = 
							new Object[]{
								rkCode,		// 0��������ⵥ��
								rkNum,		// 1���������num
								rkMny,		// 2���������mny
								pk_book,	// 3���˲�
								pk_js,		// 4������id
								rkDate,		// 5��������� ��������
								pk_org,		// 6���ɱ���
								rkkjDate,	// 7��������� �������
								pk_js_b,	// 8������bid
								jsCode,		// 9�����㵥��
								jsRowno,	//10�������к�
								yyyymm,		//11���ڼ�
							};
						
						String MAP_key = pk_fp_b;	// Map��key = ��Ʊbid
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
				 * 2�����ݱ������ѭ��
				 */
				for(InvoiceItemVO bVO : bVOs) {
					String pk_fp_b = bVO.getPk_invoice_b();
					ArrayList<Object[]> MAP_value = MAP_JS.get(pk_fp_b);
					if(MAP_value!=null && MAP_value.size()==2) {
						
						UFDouble xx_mny = // �������ܽ��
							  PuPubVO.getUFDouble_NullAsZero(MAP_value.get(0)[2])
						.add( PuPubVO.getUFDouble_NullAsZero(MAP_value.get(1)[2]) );
						
						UFDouble xx_num = // ���������������ǲɹ���Ʊ����������
							PuPubVO.getUFDouble_NullAsZero(MAP_value.get(0)[1]).abs();	// ȡ����ֵ
						
						if(
							PuPubVO.getUFDouble_ZeroAsNull(xx_mny)!=null
						&&	PuPubVO.getUFDouble_ZeroAsNull(xx_num)!=null
						) {
							// ֻ�� ���ڽ������Ҫ���½��С�
							UFDouble xx_price = null;	// ��Ҫ�����ĵ��ۣ�������ȷ���ˣ�
							String pk_book 	  = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[3]);	// �˲�
							String pk_js      = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[4]);	// ����id
							UFDate rkDate	  = PuPubVO.getUFDate(MAP_value.get(0)[5]);						// �����������
							String pk_org     = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[6]);	// �ɱ���
							UFDate rkkjDate	  = PuPubVO.getUFDate(MAP_value.get(0)[7]);						// ��������Ƽ�������
							String pk_js_b    = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[8]);	// �ɱ���
							String jsCode     = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[9]);	// �ɱ���
							String jsRowno    = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[10]);	// �ɱ���
							String yyyymm     = PuPubVO.getString_TrimZeroLenAsNull(MAP_value.get(0)[11]);	// �ڼ�
							String pk_inv  	  = bVO.getPk_material();				// ����v
							String pk_inv_src = bVO.getPk_srcmaterial();			// ����src
							String pk_cgrk 	  = bVO.getCsourceid();					// ��Ʊ��Ӧ�Ĳɹ����id
							/**
							 ****** 3����ѯ �����ϸ��
							 */
							/**
							 * 3.1 �ڳ�
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
							UFDouble QC_num = UFDouble.ZERO_DBL;	// �ڳ�����
//							UFDouble QC_mny = UFDouble.ZERO_DBL;	// �ڳ�����ʱ���ã���ȥ����
							if(list_3_1!=null&&list_3_1.size()>0) {
								Object[] value = (Object[])list_3_1.get(0);
								QC_num = PuPubVO.getUFDouble_NullAsZero(value[0]);
//								QC_mny = PuPubVO.getUFDouble_NullAsZero(value[1]);
							}
							/**
							 * 3.2 ���ڷ���
							 */
							StringBuffer querySQL_3_2 = 
							new StringBuffer("select ")
									.append(" ia_detailledger.csrcid ")			// 0����Դid������ǽ��㵥���ɵ���⣬����ԴidΪ����id��
									.append(",ia_detailledger.vbillcode ")		// 1������
									.append(",ia_detailledger.cbilltypecode ")	// 2������
									.append(",ia_detailledger.fdispatchflag ")	// 3��0-���  1-����
									.append(",ia_detailledger.nnum ")			// 4������
									.append(",case when fpricemodeflag = 5 then nplanedprice else nprice end nprice ")	// 5������
									.append(",ia_detailledger.nmny ")			// 6�����
									.append(",ia_detailledger.cdeptid ")		// 7������id
									.append(",ia_detailledger.cdeptvid ")		// 8������vid
									.append(",ia_detailledger.cstockorgid ")	// 9�������֯id
									.append(",ia_detailledger.cstockorgvid ")	//10�������֯vid
									.append(",ia_detailledger.cstordocid ")		//11���ֿ�id
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
							Integer index_cgrk = list_3_2.size();	// ��Ʊ�ɹ���ⵥ �� �����С�֮��ĳ��� ����Ҫ������ ��������ⵥΪֹ��
							boolean find_cgrk = false;				// �Ƿ�λ���� �ɹ����
							Integer index_tzrk = -1;				// ��Ʊ������ⵥ �������У�֮ǰ�ĲŴ���֮��������
							// ��� �����ϸ�� �ı��ڷ���  ��ѭ������
							for(int i=0 ; list_3_2!=null&&i<list_3_2.size() ; i++) {
								
								Object[] value = (Object[])list_3_2.get(i);
								String cbillid 		 = PuPubVO.getString_TrimZeroLenAsNull(value[0]);
								String vbillcode 	 = PuPubVO.getString_TrimZeroLenAsNull(value[1]);
								String cbilltypecode = PuPubVO.getString_TrimZeroLenAsNull(value[2]);
								Integer fdispatchflag= PuPubVO.getInteger_NullAs(value[3],0);
								UFDouble ch_nnum 	 = PuPubVO.getUFDouble_NullAsZero(value[4]);
								UFDouble ch_nprice 	 = PuPubVO.getUFDouble_NullAsZero(value[5]);
								UFDouble ch_nmny 	 = PuPubVO.getUFDouble_NullAsZero(value[6]);
								String cdeptid 		 = PuPubVO.getString_TrimZeroLenAsNull(value[7]);
								String cdeptvid 	 = PuPubVO.getString_TrimZeroLenAsNull(value[8]);
								String cstockorgid 	 = PuPubVO.getString_TrimZeroLenAsNull(value[9]);
								String cstockorgvid  = PuPubVO.getString_TrimZeroLenAsNull(value[10]);
								String cstordocid	 = PuPubVO.getString_TrimZeroLenAsNull(value[11]);
								
								/**
								 * a�������λ�� ��Ʊ��Ӧ�Ĳɹ���ⵥ��
								 *    ��ʼ�����ڳ��Ѿ�ȡ���ˡ���������ִ�У�������
								 *    ��ʱ ȷ����  �������� �� ��������
								 *    �������� = �ɹ��������� + �ڳ�����
								 *    �������� = �������/��������
								 */
								if( pk_cgrk.equals(cbillid) ) {
									index_cgrk = i;
									find_cgrk = true;
									
									xx_num = xx_num.add(QC_num);
									xx_price = xx_mny.div(xx_num);
									
									continue;
								}
								/**
								 * b�������λ�� ��Ʊ��Ӧ�ĵ�����ⵥ��
								 * 	     ��ֹͣ���������Ѿ�������ϣ��˳���
								 */
								else if( pk_js.equals(cbillid) ) {
									index_tzrk = i;
									break;
								}
								/**
								 * c�����֮ǰ��Ϊ�ڳ�����������
								 *     ֻ�е� δ�ҵ� �ɹ���� ʱ���Ž����ڳ��ķ�װ��
								 */
								else if( !find_cgrk ){
									if( fdispatchflag==0 ) {		// ���
										QC_num = QC_num.add(ch_nnum);
//										QC_mny = QC_num.add(ch_nmny);
									}else if(  fdispatchflag==1  ) {// ����
										QC_num = QC_num.sub(ch_nnum);
//										QC_mny = QC_num.sub(ch_nmny);
									}
								}
								
								if( fdispatchflag==1 && ch_nnum!=null) {
									// ���� ���� ������Ϊ��  ����Ҫ����
									if(i>index_cgrk) {
										// ���� ��ǰ�������� index_cgrk  �Ž��д���
										{
											
											UFDouble tz_mny = null;	// ����Ҫ�����Ľ� ��Ϊ�գ���������
											
											// �����ܶ� ��Ϊ�գ��Ž��д���
											if( xx_mny != null ) {
												/**
												 * ֮ǰ���㷨���� �ȵֿ��ڳ���Ȼ���ٷ�̯��
												 * ���ڸĳɣ��ڳ��� Ӱ�쵽��̯���ۣ�Ȼ�� ȫ����̯��
												 */
												if ( xx_num != null ) {
													UFDouble do_nnum = ch_nnum;						// ��Ҫ���������
													UFDouble temp_xxxx_num = xx_num.sub(do_nnum);	// ��ɹ����ֿ۵Ĳ�� = ���-���г�������
													if(temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)>0) {
														xx_num = temp_xxxx_num;		// ����� ��ֵ�� �ɹ��������
														// ����������㣬 ˵���������һ�Σ����� �����*��������   ������ ������
														tz_mny = xx_price.multiply(do_nnum).setScale(2, UFDouble.ROUND_HALF_UP);
														xx_mny = xx_mny.sub(tz_mny);	// �ۼ� �����ܶ
													}
													else if (temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)<=0) {
														// ������С�ڵ����㣬˵�������һ�Σ�ֱ�ӽ�ʣ���� �ù�����
														tz_mny = xx_mny;
														xx_mny = null;	// ������ϣ��� �����ܶ� �ÿա�
														xx_num = null;	// ������ϣ��� �������� �ÿա�
													}
												}
//												// ����ڳ����� ����0  ���ȵֿ��ڳ�
//												if( QC_num.compareTo(UFDouble.ZERO_DBL)>=0 ) {
//													UFDouble temp_xx_num = QC_num.sub(ch_nnum);	// ���ڳ��ֿ۵Ĳ�� = �ڳ�-���г�������
//													if(temp_xx_num.compareTo(UFDouble.ZERO_DBL)>=0) {
//														// ������ ����0��˵�� �ڳ����� ����Ҫ����ĳ��⣬��������
//														// ����� ��ֵ�� �ڳ� ���ɡ�
//														QC_num = temp_xx_num;
//													}
//													else {
//														QC_num = UFDouble.ZERO_DBL;	// �ڳ� ��Ϊ 0
//														// ������ С��0��˵�� �ڳ������ˣ� Ҫ�� �ɹ������ȡ��������Ҫ���� �������ˡ�
//														UFDouble do_nnum = temp_xx_num.abs();			// ��Ҫ���������
//														UFDouble temp_xxxx_num = xx_num.sub(do_nnum);	// �� �ɹ����ֿ۵Ĳ��
//														if(temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)>0) {
//															xx_num = temp_xxxx_num;		// ����� ��ֵ�� �ɹ��������
//															// ����������㣬 ˵���������һ�Σ����� �����*��������   ������ ������
//															tz_mny = xx_price.multiply(do_nnum).setScale(2, UFDouble.ROUND_HALF_UP);
//															xx_mny = xx_mny.sub(tz_mny);	// �ۼ� �����ܶ
//														}
//														else if (temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)<=0) {
//															// ������С�ڵ����㣬˵�������һ�Σ�ֱ�ӽ�ʣ���� �ù�����
//															tz_mny = xx_mny;
//															xx_mny = null;	// ������ϣ��� �����ܶ� �ÿա�
//															xx_num = null;	// ������ϣ��� �������� �ÿա�
//														}
//													}
//												}
//												// ����ڳ�����Ϊ0�ˣ����ж� �ɹ��������,�����Ϊ�գ�����д���
//												else if ( xx_num != null ) {
//													UFDouble do_nnum = ch_nnum;						// ��Ҫ���������
//													UFDouble temp_xxxx_num = xx_num.sub(do_nnum);	// ��ɹ����ֿ۵Ĳ�� = ���-���г�������
//													if(temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)>0) {
//														xx_num = temp_xxxx_num;		// ����� ��ֵ�� �ɹ��������
//														// ����������㣬 ˵���������һ�Σ����� �����*��������   ������ ������
//														tz_mny = xx_price.multiply(do_nnum).setScale(2, UFDouble.ROUND_HALF_UP);
//														xx_mny = xx_mny.sub(tz_mny);	// �ۼ� �����ܶ
//													}
//													else if (temp_xxxx_num.compareTo(UFDouble.ZERO_DBL)<=0) {
//														// ������С�ڵ����㣬˵�������һ�Σ�ֱ�ӽ�ʣ���� �ù�����
//														tz_mny = xx_mny;
//														xx_mny = null;	// ������ϣ��� �����ܶ� �ÿա�
//														xx_num = null;	// ������ϣ��� �������� �ÿա�
//													}
//												}
											}
											
											if( tz_mny==null ) continue;	// ������ε������ Ϊ�գ���������
											
											// ��װ ���������������
											IABillVO iaBillVO = new IABillVO();
											// ��ͷ
											IAHeadVO iaHVO = new IAHeadVO();
											iaHVO.setBconvertflag(UFBoolean.FALSE);
											iaHVO.setBsystemflag(UFBoolean.FALSE);
											iaHVO.setCaccountperiod(yyyymm);
											iaHVO.setCdeptid(cdeptid);			// �ҳ��ⵥ�Ĳ���
											iaHVO.setCdeptvid(cdeptvid);		// �ҳ��ⵥ�Ĳ���v
											iaHVO.setCstockorgid(cstockorgid);	// �ҳ��ⵥ�Ŀ����֯
											iaHVO.setCstockorgvid(cstockorgvid);// �ҳ��ⵥ�Ŀ����֯v
											iaHVO.setCstordocid(cstordocid);	// �ҳ��ⵥ�Ĳֿ�
											iaHVO.setDbilldate(rkDate);	// �������ⵥ��һ��
											iaHVO.setDr(0);
											iaHVO.setFintransitflag(-1);
											iaHVO.setPk_book(pk_book);
											iaHVO.setPk_group(pk_group);
											iaHVO.setPk_org(pk_org);
											iaHVO.setVnote("������"+vbillcode+"���ĳɱ�");
											// ����
											IAItemVO iaBVO = new IAItemVO();
											iaBVO.setBreworkflag(UFBoolean.FALSE);
											iaBVO.setCaccountperiod(yyyymm);
											iaBVO.setCcurrencyid("1002Z0100000000001K1");	// ���֣����Թ̶���
											iaBVO.setCinventoryid(pk_inv_src);	// ����src
											iaBVO.setCinventoryvid(pk_inv);		// ����v
											iaBVO.setCrowno("10");
											iaBVO.setDaccountdate(rkkjDate);	// ����ڼ�������� �� ������� ��һ��
											iaBVO.setDbilldate(rkDate);
											iaBVO.setDbizdate(rkDate);
											iaBVO.setDr(0);
											iaBVO.setNmny(tz_mny);	// ��Ҫ���� = ��������*��������
											iaBVO.setPk_book(pk_book);
											iaBVO.setPk_group(pk_group);
											iaBVO.setPk_org(pk_org);
											iaBVO.setAttributeValue("pseudocolumn", 0);
											// ��Դ��Ϣ
											iaBVO.setVsrctype("27");		// ��Դ��������  = ���㵥
											iaBVO.setVsrcrowno(jsRowno);	// ��Դ�к�
											iaBVO.setVsrccode(jsCode);		// ��Դ����
											iaBVO.setCsrcid(pk_js);			// ��Դid
											iaBVO.setCsrcbid(pk_js_b);		// ��Դbid
											// ��ϱ���
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

	/**
	 * ��̯���㷨��ÿ�ſ�Ƭ�ĵ������ = ��Ƭ�ϵ��ʲ����� * ���㵥�� - ��Ƭ�ϵĵ���ǰ��ԭ��ԭֵ
	 * ��� ����Ƭ�� ���� ������ ��� �� ֱ�ӷ�̯ʣ����
	 */
	@Override
	public Object genYbyztzByPoInvoice(InvoiceVO[] poInvoiceVOs, Object ohter)
			throws BusinessException {
		
		try {
			// �������ݿ�
			BaseDAO dao = new BaseDAO();
			// ����������
			IBillcodeManage codeManage = (IBillcodeManage)NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
			// pk������
			SequenceGenerator pkGen = new SequenceGenerator();	
			
			for(InvoiceVO fpBillVO : poInvoiceVOs) {// һ�Ž��㵥 ����һ�� ԭ��ԭֵ������
				
				String billCode = null;	// ���ݺţ�����ǰ ȡ�ţ���� ʧ�� ��Ҫ�˺š�
				String billType = null;	
				String pk_group = null;
				String pk_org 	= null;
				
				try {
					
					InvoiceHeaderVO fpHVO  = fpBillVO.getParentVO();
					
					// ���ݷ�Ʊ �����ν��㵥������ ֻȡ �̶��ʲ������ϡ�
					StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" jsb.nclashestmoney ")		// 0�����ݹ����
							.append(",jsb.nmoney ")				// 1��������
							.append(",jsb.nsettlenum ")			// 2����������
							.append(",js.PK_SETTLEBILL ")		// 3�����㵥id
							.append(",jsb.PK_SETTLEBILL_B ")	// 4�����㵥bid
							.append(",js.dbilldate ")			// 5����������
							.append(",card.pk_card ")			// 6����Ƭid
							.append(",cardhis.card_num ")			// 7����Ƭ����
							.append(",cardhis.originvalue ")		// 8����Ƭԭ��ԭֵ
							.append(",cardhis.localoriginvalue ")	// 9����Ƭ����ԭֵ
							.append(",jsb.nprice ")					// 10�����㵥��
							.append(" from po_settlebill js ")
							.append(" inner join po_settlebill_b jsb on js.pk_settlebill = jsb.pk_settlebill ")
							.append(" inner join ic_purchasein_b cgrkb on jsb.pk_purchasein_b = cgrkb.cgeneralbid ")
							.append(" inner join fa_transasset_b zgb on  cgrkb.cgeneralbid = zgb.pk_bill_b_src ")
							.append(" inner join fa_card card on zgb.pk_transasset_b = card.pk_bill_b_src ")
							.append(" inner join fa_cardhistory cardhis on (card.pk_card = cardhis.pk_card and cardhis.laststate_flag='Y') ")
							.append(" where js.dr = 0 and jsb.dr = 0 ")
							.append(" and jsb.dr = 0 and cgrkb.dr = 0 and zgb.dr = 0 and card.dr = 0 and cardhis.dr = 0 ")
							.append(" and jsb.PK_INVOICE = '"+fpHVO.getPk_invoice()+"' ")	// ���� �ɹ���Ʊpk ��ѯ
							.append(" and nvl(jsb.nclashestmoney,0) <> nvl(jsb.nmoney,0) ")	// ֻȡ ������� �� ������ ����ȵ�
					;
					ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
					
					if(list==null||list.size()<=0) continue;
					
					// ������Ϣ��MAP  
					// key:���㵥 bid
					// value:
					HashMap<String,ArrayList<Object[]>> MAP_JS = new HashMap<String,ArrayList<Object[]>>();
					// �������������
					// key:���㵥 bid
					// value:�������漰��������
					HashMap<String,UFDouble[]> MAP_jstz = new HashMap<String,UFDouble[]>();
					for(Object obj : list) {
						Object[] value = (Object[])obj;
						UFDouble cxMny	= PuPubVO.getUFDouble_NullAsZero(value[0]);
						UFDouble jsMny	= PuPubVO.getUFDouble_NullAsZero(value[1]);
						UFDouble jsNum	= PuPubVO.getUFDouble_NullAsZero(value[2]);
						String jsdId	= PuPubVO.getString_TrimZeroLenAsNull(value[3]);
						String jsdBid	= PuPubVO.getString_TrimZeroLenAsNull(value[4]);
						UFDate jsDate	= PuPubVO.getUFDate(value[5]);
						String cardPk	= PuPubVO.getString_TrimZeroLenAsNull(value[6]);
						UFDouble cardNum	= PuPubVO.getUFDouble_NullAsZero(value[7]);
						UFDouble cardYbyz	= PuPubVO.getUFDouble_NullAsZero(value[8]);
						UFDouble cardBbyz	= PuPubVO.getUFDouble_NullAsZero(value[9]);
						UFDouble jsPrice	= PuPubVO.getUFDouble_NullAsZero(value[10]);	// ���㵥��
						
						Object[] value_item_JS = 
							new Object[]{
								jsdId,		// 0�����㵥id
								jsdBid,		// 1�����㵥bid
								jsDate,		// 2����������
								cardPk,		// 3����Ƭid
								cardNum,	// 4����Ƭ����
								cardYbyz,	// 5����Ƭԭ��
								cardBbyz,	// 6����Ƭ����
							};
						
						String MAP_key = jsdBid;
						
						if(MAP_JS.containsKey(MAP_key)) {
							MAP_JS.get(MAP_key).add( value_item_JS );
						}else {
							ArrayList<Object[]> MAP_value = new ArrayList<Object[]>();
							MAP_value.add( value_item_JS );
							MAP_JS.put(MAP_key, MAP_value);
						}
						
						UFDouble[] MAP_value_jstz = 
							new UFDouble[]{
								jsMny.sub(cxMny),	// ������� = ����-����
								jsNum,				// �漰��������
								jsPrice,			// ���㵥��
						};
						
						if(!MAP_jstz.containsKey(MAP_key)) {
							MAP_jstz.put(MAP_key, MAP_value_jstz);
						}
					}
					
					if(MAP_JS.size()<=0) continue;
					
					pk_group  = fpHVO.getPk_group();
					pk_org    = fpHVO.getPk_org();
					String pk_org_v  = fpHVO.getPk_org_v();
					String billMaker = fpHVO.getApprover();		// ��Ʊ����� Ϊ �������Ĵ����ˡ�
					String currPk 	= "1002Z0100000000001K1";	// ����
					String remark	= "�ɹ��������ԭֵ";				// ��ע
					billType = "HG";							// ԭ��ԭֵ������-��������
					String transiType = "HG-01";				// ԭ��ԭֵ������-��������
					String transiPk = "0001N510000000001IXK";	// ԭ��ԭֵ������-��������pk
					String pkAlter 	= pkGen.generate();			// �䶯��pk ����Ҫ������pk��
					billCode = codeManage.getPreBillCode_RequiresNew(billType, pk_group, pk_org);	// ����ǰ Ԥȡ����
					String srcBillType 	= "27";	// ��Դ-��������
					String srcBillId = null;	// ��Դ-����id
					UFDate busiDate = null;		// ҵ������=���㵥����
					
					ArrayList<String> showAlterKeyList = new ArrayList<String>();
					showAlterKeyList.add("originvalue");
					
					ArrayList<AlterBodyVO> tzBVOs_list = new ArrayList<AlterBodyVO>();	// ��������list
					
					String[] MAP_keys = new String[MAP_JS.size()];
					MAP_keys = MAP_JS.keySet().toArray(MAP_keys);
					for(String key : MAP_keys) {
						
						ArrayList<Object[]> jsArray = MAP_JS.get(key);
						UFDouble[] jstzData = MAP_jstz.get(key);
						
						UFDouble jstzMny   = jstzData[0];			// �������
						UFDouble jstzNum   = jstzData[1];			// ��������
						UFDouble jstzPrice = jstzMny.div(jstzNum);	// ��������
						UFDouble jsPrice   = jstzData[2];			// ���㵥��
						
						if(jsArray==null||jsArray.size()<=0) continue;
						
						for(int i=0;i<jsArray.size();i++) {
							Object[] value = jsArray.get(i);
							String jsdId	= PuPubVO.getString_TrimZeroLenAsNull(value[0]);// ����id
							String jsdBid	= PuPubVO.getString_TrimZeroLenAsNull(value[1]);// ����bid
							UFDate jsDate	= PuPubVO.getUFDate(value[2]);					// ��������
							String cardPk	= PuPubVO.getString_TrimZeroLenAsNull(value[3]);// ��Ƭpk
							UFDouble cardNum	= PuPubVO.getUFDouble_NullAsZero(value[4]);	// ��Ƭ����
							UFDouble cardYbyz	= PuPubVO.getUFDouble_NullAsZero(value[5]);	// ԭ��
							UFDouble cardBbyz	= PuPubVO.getUFDouble_NullAsZero(value[6]);	// ����
							
							if(srcBillId==null) {
								srcBillId = jsdId;
								busiDate = jsDate;
							}
							
							UFDouble alterMny = null;	// ���ε������
							
							if( PuPubVO.getUFDouble_ZeroAsNull(jstzMny)!=null ) {
								if(cardNum.compareTo(jstzNum)>=0) {
									// ��� ��Ƭ���� ���ڵ��� ������������ ������� ȫ����Ϊ  ���ε������
									alterMny = jstzMny;
									jstzMny = UFDouble.ZERO_DBL;	// ������� �� ���������Ϊ0
									jstzNum = UFDouble.ZERO_DBL;	// ������� �� ����������Ϊ0
								} else {
									// ��� ��Ƭ���� С�� ������������ ���������� * ��Ƭ����  ȥ��
									// 20190919 ��Ϊ ��ÿ�ſ�Ƭ�ĵ������ = ��Ƭ�ϵ��ʲ����� * ���㵥�� - ��Ƭ�ϵĵ���ǰ��ԭ��ԭֵ��
//									alterMny = jstzPrice.multiply(cardNum).setScale(2, UFDouble.ROUND_HALF_UP);
									alterMny = jsPrice.multiply(cardNum)
												.setScale(2, UFDouble.ROUND_HALF_UP)
												.sub(cardYbyz);
									jstzMny = jstzMny.sub(alterMny);	// ������� �� ������� ��ȥ ���ε������
									jstzNum = jstzNum.sub(cardNum);		// ������� �� �������� ��ȥ ���ο�Ƭ����
								}
							}
							
							if(alterMny==null) continue;	// ������ε������ Ϊ �գ�������
							
							AlterBodyVO tzBVO = new AlterBodyVO();
							tzBVOs_list.add(tzBVO);
							tzBVO.setPk_alter(pkAlter);		// �䶯��id
							tzBVO.setPk_card(cardPk);		// ��Ƭ
							tzBVO.setPk_currency(currPk);	// ����
							tzBVO.setRemark(remark);		// ��ע
							tzBVO.setStatus(2);				// vo״̬
							
							// ԭ��ԭֵ
							tzBVO.setAttributeValue("originvalue_before", cardYbyz);				// ����ǰ
							tzBVO.setAttributeValue("originvalue_alter", alterMny);					// ���ε���
							tzBVO.setAttributeValue("originvalue_after", cardYbyz.add(alterMny));	// ������ = ����ǰ + ���ε���
							// ����ԭֵ
							tzBVO.setAttributeValue("localoriginvalue_before", cardBbyz);	
							tzBVO.setAttributeValue("localoriginvalue_alter", alterMny);	
							tzBVO.setAttributeValue("localoriginvalue_after", cardBbyz.add(alterMny));
							
							//// ��Դ��Ϣ����
							tzBVO.setPk_bill_src(jsdId);		// ��Դ-��ͷid = ���㵥id
							tzBVO.setPk_bill_b_src(jsdBid);		// ��Դ-����id = ���㵥bid
						}
					}
					
					if(tzBVOs_list.size()<=0) continue;
					
					AlterBodyVO[] tzBVOs = new AlterBodyVO[tzBVOs_list.size()];
					tzBVOs = tzBVOs_list.toArray(tzBVOs);
					
					AlterHeadVO tzHVO = new AlterHeadVO();
					tzHVO.setPk_alter(pkAlter);				// �䶯��pk
					tzHVO.setBill_code(billCode);			// ���ݺ�
					tzHVO.setBusiness_date(busiDate);		// ҵ������
					tzHVO.setPk_group(pk_group);			// ����
					tzHVO.setPk_org(pk_org);				// ��֯
					tzHVO.setPk_org_v(pk_org_v);			// ��֯V
					tzHVO.setBill_status(0);				// ����״̬-����̬
					tzHVO.setBillmaker(billMaker);			// �Ƶ���
					tzHVO.setBillmaketime(new UFDateTime());// �Ƶ�ʱ��-��ǰʱ��
					tzHVO.setDr(0);					// ɾ����־
					tzHVO.setDirty(false);			// �Ƿ�ɾ��
					tzHVO.setBill_type(billType);		// ��������
					tzHVO.setTransi_type(transiType);	// ��������
					tzHVO.setPk_transitype(transiPk);	// ��������pk
					tzHVO.setRemark(remark);		// ��ע
					tzHVO.setStatus(1);				// vo״̬
					//// ��Դ��Ϣ����
					tzHVO.setPk_bill_src(srcBillId);			// ��Դ-����id
					tzHVO.setBill_type_src(srcBillType);		// ��Դ-��������
					tzHVO.setTransi_type_src(srcBillType);		// ��Դ-��������
					tzHVO.setPk_transitype_src(srcBillType);	// ��Դ-��������pk
					
					AlterVO tzBillVO = new AlterVO();
					tzBillVO.setParentVO(tzHVO);
					tzBillVO.setChildrenVO(tzBVOs);
					
					TransportBillVO result = 
							NCLocator.getInstance().lookup(IAlter.class)
							.insert(null, tzBillVO, showAlterKeyList);
					
					return result;
				}
				catch(Exception ex) {
					ex.printStackTrace();
					if(billCode!=null) {
						codeManage.rollbackPreBillCode(billType, pk_group, pk_org, billCode);
					}
					throw new Exception(ex);
				}
			}
		} 
		catch(Exception ex) {
			throw new BusinessException(ex);
		}
		return null;
	}

	@Override
	public Object genXbsqByJsd(ArrayList<Object[]> list, Object other)
			throws BusinessException {
		return new BusinessDAO().genXbsqByJsd(list, other);
	}

	@Override
	public Object delXbsqBackJsd(AggAllocateApplyVO billVO, Object other)
			throws BusinessException {
		return new BusinessDAO().delXbsqBackJsd(billVO, other);
	}
	
}
