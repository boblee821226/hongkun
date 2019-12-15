package nc.impl.hkjt.report;

import hd.vo.pub.tools.PuPubVO;
import hd.vo.pub.tools.ReportDataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.pub.smart.metadata.Field;
import nc.pub.smart.metadata.MetaData;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ��Ա��Ʊ��Ϣ��ѯ��
 * @author 
 *
 */
public class Report_HYKPXXCXB extends AbstractReportAction {

	private BaseDAO dao = new BaseDAO();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataSet excute(SmartContext context, HashMap<String, Object> param,
			String flag, Object other) throws Exception {
	
		String v_pk_group      = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_pk_group"));		// ����
		String v_ka_code       = PuPubVO.getString_TrimZeroLenAsNull(param.get("ka_code"));			// ����
		String v_dian  		   = PuPubVO.getString_TrimZeroLenAsNull(param.get("dian"));			// ������
		UFBoolean v_is_ss_kkp  = PuPubVO.getUFBoolean_NullAs(param.get("is_ss_kkp"),UFBoolean.FALSE);	// �Ƿ�ʵ����ɿ�Ʊ�ܶһ��
		UFBoolean v_is_ykp     = PuPubVO.getUFBoolean_NullAs(param.get("is_ykp"),UFBoolean.FALSE);		// �Ƿ��ѿ�Ʊ��һ��
		UFBoolean v_is_sykp    = PuPubVO.getUFBoolean_NullAs(param.get("is_sykp"),UFBoolean.FALSE);	// �Ƿ�ʣ�࿪Ʊ���Ϊ����
		String kkrq_b  		   = PuPubVO.getString_TrimZeroLenAsNull(param.get("kkrq_b"));			// �������ڣ�ʼ��
		String kkrq_e  		   = PuPubVO.getString_TrimZeroLenAsNull(param.get("kkrq_e"));			// �������ڣ��գ�
		
		if( v_pk_group==null 
		 || "��ǰ����".equals(v_pk_group)
		)
		{
			v_pk_group = "0001N510000000000EGY";
		}
		
		String whereSQL_1 = " and (1=1) ";
		
		if(v_ka_code!=null)
		{// ����
			whereSQL_1 += " and ka.ka_code = '"+v_ka_code+"' ";
		}
		
		if(v_dian!=null)
		{// ��
			whereSQL_1 += " and ka.vdef01 = '"+v_dian+"' ";
		}
		
		String whereSQL_2 = null;
		
		if(v_is_ss_kkp.booleanValue())
		{// �Ƿ�ʵ����ɿ�Ʊ�ܶһ��
			whereSQL_2 = " bb.��ֵʵ�ս�� <> bb.�ɿ�Ʊ�ܶ� ";
		}
		if(v_is_ykp.booleanValue())
		{// �Ƿ��ѿ�Ʊ��һ��
			if( whereSQL_2!=null )
				whereSQL_2 += " or bb.�ѿ�Ʊ�ܶ�ka <> bb.�ѿ�Ʊ�ܶ�dj ";
			else 
				whereSQL_2  = " bb.�ѿ�Ʊ�ܶ�ka <> bb.�ѿ�Ʊ�ܶ�dj ";
		}
		if(v_is_sykp.booleanValue())
		{// �Ƿ�ʣ��ɿ�Ʊ�ܶ�Ϊ����
			if( whereSQL_2!=null )
				whereSQL_2 += " or bb.ʣ��ɿ�Ʊ�ܶ� < 0.00 ";
			else 
				whereSQL_2  = " bb.ʣ��ɿ�Ʊ�ܶ� < 0.00 ";
		}
		
		if(whereSQL_2==null)
		{
			whereSQL_2 = " (1=1) ";
		}
		else
		{
			whereSQL_2 = " ( " + whereSQL_2 + " ) ";
			
		}
		
		/**
		 * HK 2019��4��16��10:23:45
		 * ���� �������� �Ĳ�ѯ����
		 */
		if(kkrq_b!=null)
		{// �������ڣ�ʼ��
			kkrq_b = kkrq_b + " 00:00:00";
			whereSQL_2 += " and bb.NC�ƿ�ʱ�� >= '"+kkrq_b+"'";
		}
		
		if(kkrq_e!=null)
		{// �������ڣ��գ�
			kkrq_e = kkrq_e + " 23:59:59";
			whereSQL_2 += " and bb.NC�ƿ�ʱ�� <= '"+kkrq_e+"'";
		}
		/***END***/
		
		/**
		 * ��������ģ��ʱ  ʹ��
		 */
//		if(true)
//		{
//			Object[][] datas_final = new Object[4][];
//			DataSet ds = new DataSet();
//			ds.setDatas(datas_final);
//			ds.setMetaData(getMetaData());
//			
//			return ds;
//		}
		
		StringBuffer querySQL = 
		new StringBuffer("select * ")
				.append(" from ( ")
				.append("  	select  kp_ka.pk_hk_huiyuan_kadangan,")							// 0
				.append("  			kp_ka.����,")											// 1
				.append("  			kp_ka.���ͱ���,")											// 2
				.append("  			kp_ka.��������,")											// 3
				.append("  			kp_ka.������,")											// 4
				.append("  			cz_ka.��ֵʵ�ս��,")										// 5
				.append("  			kp_ka.�ɿ�Ʊ�ܶ�,")										// 6
				.append("  			kp_dj.�����,")											// 7
				.append("  			kp_dj.δ���,")											// 8
				.append("  			nvl(kp_dj.�����,0) + nvl(kp_dj.δ���,0) �ѿ�Ʊ�ܶ�dj,")		// 9
				.append("  			kp_ka.�ѿ�Ʊ�ܶ� �ѿ�Ʊ�ܶ�ka,")								// 10
				.append("  			nvl(kp_ka.�ɿ�Ʊ�ܶ�,0) - ( nvl(kp_dj.�����,0) + nvl(kp_dj.δ���,0) ) ʣ��ɿ�Ʊ�ܶ�,")	// 11
				.append("  			cz_ka.NC�ƿ�ʱ��,")										// 12
				.append("  			kp_ka.��Ʊ��ֹ����,")										// 13
				.append("  			null �ѹ���Ʊ��,")											// 14
				.append("  			cz_ka.��ֵ����,")											// 15
				.append("  			kp_dj.��Ʊ���� ")											// 16
				.append("  	from ( ")
				.append("  			select ")
				.append("  			 ka.pk_hk_huiyuan_kadangan ")
				.append("  			,max(ka.ka_code) ���� ")
				.append("  			,sum(nvl(ka_kkp.ykp_je, 0)) �ѿ�Ʊ�ܶ� ")
				.append("  			,sum(nvl(ka_kkp.kkp_je, 0)) �ɿ�Ʊ�ܶ� ")
				.append("  			,max(kx.kaxing_code) ���ͱ��� ")
				.append("  			,max(kx.kaxing_name) �������� ")
				.append("  			,max(ka.vdef01) ������ ")
				.append("  			,max(ka_kkp.kpjz_time)��Ʊ��ֹ���� ")
				.append("  			from hk_huiyuan_kadangan ka ")
				.append("  			inner join hk_huiyuan_kadangan_kkp ka_kkp ")
				.append("  			on ka.pk_hk_huiyuan_kadangan = ka_kkp.pk_hk_huiyuan_kadangan ")
				.append("  			left join hk_huiyuan_kaxing kx ")
				.append("  			on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
				.append("  			where (1=1) ")
				.append("  			and ka.dr = 0 ")
				.append("  			and ka_kkp.dr = 0 ")
				.append(            whereSQL_1 )
				.append("  			group by ka.pk_hk_huiyuan_kadangan ")
				.append("  		 ) kp_ka ")		// ��Ʊ��Ϣ
				.append("   left join ( ")
				.append("  			select ")
				.append("  			 ka.pk_hk_huiyuan_kadangan ")
				.append("  			,sum(nvl(ka_cz.cz_ss, 0)) ��ֵʵ�ս�� ")
				.append("  			,count(ka_cz.pk_hk_huiyuan_kadangan_cz) ��ֵ���� ")
				.append("  			,min(ka_cz.cz_time) NC�ƿ�ʱ�� ")
				.append("  			from hk_huiyuan_kadangan ka ")
				.append("  			inner join hk_huiyuan_kadangan_cz ka_cz ")
				.append("  			on ka.pk_hk_huiyuan_kadangan = ka_cz.pk_hk_huiyuan_kadangan ")
				.append("  			where (1=1) ")
				.append("  			and ka.dr = 0 ")
				.append("  			and ka_cz.dr = 0 ")
				.append(            whereSQL_1 )
				.append("  			and nvl(ka_cz.cz_ss, 0.0) <> 0.0 ")
				.append("			and ka_cz.vbdef02 <> '�س�' ")
				.append("  			group by ka.pk_hk_huiyuan_kadangan ")
				.append("  		) cz_ka ")	// ��ֵ
				.append("  		on cz_ka.pk_hk_huiyuan_kadangan = kp_ka.pk_hk_huiyuan_kadangan ")
				.append("  	left join (	")
				.append("  			select ")
				.append("  			 kpb.ka_pk ")
				.append("  			,sum(case when kp.ibillstatus = 1 then kpb.fpje else 0 END) ����� ")
				.append("  			,sum(case when kp.ibillstatus <>1 then kpb.fpje else 0 END) δ��� ")
				.append("  			,count(kpb.pk_hk_huiyuan_kaipiaoinfo_b) ��Ʊ���� ")
				.append("			from hk_huiyuan_kaipiaoinfo kp ")
				.append("  			inner join hk_huiyuan_kaipiaoinfo_b kpb ")
				.append("  			on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
				.append("  			where (1 = 1) ")
				.append("  			and kp.dr = 0 ")
				.append("  			and kpb.dr = 0 ")
				.append("  			group by kpb.ka_pk ")
				.append("  		) kp_dj ")	// ���ݿ�Ʊ
				.append("  		on kp_dj.ka_pk = kp_ka.pk_hk_huiyuan_kadangan ")
				.append("  	 where (1 = 1) ")
				.append("  	 ) bb ")	// ���ܱ�
				.append("  where ").append( whereSQL_2 )
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		// ���շ��صĶ�ά����
		Object[][] datas_final = new Object[0][];
		
		if( list!=null && list.size()>0 )
		{
			datas_final = new Object[list.size()][];
			
			String now_date_str = new UFDate().toString();	// ��ǰ������ ʱ��
			
			for(int i=0;i<list.size();i++)
			{
				datas_final[i] = new Object[FIELD_NAMES.length];
				
				Object[] datas_temp = (Object[])list.get(i);
				/**
				    "pk_group",		// ����pk		0
					"pk_org",		// ��֯pk		1
					"ka_pk",		// ��pk			2
					"ka_code",		// ��code		3
					"kx_code",		// ���ͱ���		4
					"kx_name",		// ��������		5
					"dian",			// ������			6
					"ssje",			// ʵ�ս��		7
					"sykkpje",		// ʣ��ɿ�Ʊ���	8
					"ykpje",		// �ѿ�Ʊ�ܶ�		9
					"kkpje",		// �ɿ�Ʊ�ܶ�		10
					"zksj",			// NC�ƿ�ʱ��		11
					"kpjzrq",		// ��Ʊ��ֹʱ��	12
					"ygqje",		// �ѹ���Ʊ��		13
					"vdef01",		// �Զ�����01		14
					"vdef02",		// �Զ�����02		15
					"vdef03",		// �Զ�����03		16
					"vdef04",		// �Զ�����04		17
					"vdef05",		// �Զ�����05		18
					"vdef11",		// �Զ�����11		19
					"vdef12",		// �Զ�����12		20
					"vdef13",		// �Զ�����13		21
					"vdef14",		// �Զ�����14		22
					"vdef15",		// �Զ�����15		23
				 */
				datas_final[i][0] = v_pk_group;
				datas_final[i][1] = null;
				datas_final[i][2] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[0] );
				datas_final[i][3] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[1] );
				datas_final[i][4] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[2] );
				datas_final[i][5] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[3] );
				datas_final[i][6] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[4] );
				datas_final[i][7] = PuPubVO.getUFDouble_NullAsZero( datas_temp[5] );
				datas_final[i][10] = PuPubVO.getUFDouble_NullAsZero( datas_temp[6] );
				datas_final[i][20] = PuPubVO.getUFDouble_NullAsZero( datas_temp[7] );
				datas_final[i][21] = PuPubVO.getUFDouble_NullAsZero( datas_temp[8] );
				datas_final[i][9] = PuPubVO.getUFDouble_NullAsZero( datas_temp[9] );
				datas_final[i][19] = PuPubVO.getUFDouble_NullAsZero( datas_temp[10] );
				datas_final[i][8] = PuPubVO.getUFDouble_NullAsZero( datas_temp[11] );
				datas_final[i][11] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[12] );
				datas_final[i][12] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[13] );
//				datas_final[i][7] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[14] );
				datas_final[i][14] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[15] );
				datas_final[i][15] = PuPubVO.getString_TrimZeroLenAsNull( datas_temp[16] );
				
				String kpjesj = PuPubVO.getString_TrimZeroLenAsNull(datas_final[i][12]);	// ��Ʊ��ֹʱ��
				UFDouble sykpje = PuPubVO.getUFDouble_ZeroAsNull(datas_final[i][8]);		// ʣ�࿪Ʊ���
				
				if(
					   kpjesj!=null 
					&& sykpje!=null
					&& kpjesj.compareTo(now_date_str)<0
				)
				{
					datas_final[i][8] = UFDouble.ZERO_DBL;
					datas_final[i][13]= sykpje;
				}
				
			}
			
		}
		
		// �������ݼ�
		DataSet ds = new DataSet();
		ds.setDatas(datas_final);
		ds.setMetaData(getMetaData());

		return ds;
	}

	private MetaData getMetaData() {

		List<Field> list = new ArrayList<Field>();

		for (int i = 0; i < FIELD_NAMES.length; i++) {
			String field_type = FIELD_TYPES[i];
			String field_name = FIELD_NAMES[i];

			if (STRING.equals(field_type))
				list.add(ReportDataUtil.createStringFiled(field_name));
			else if (INTEGER.equals(field_type))
				list.add(ReportDataUtil.createIntegerFiled(field_name));
			else if (DOUBLE.equals(field_type))
				list.add(ReportDataUtil.createDoubleField(field_name));
		}

		Field[] fields = new Field[list.size()];
		list.toArray(fields);
		return new MetaData(fields);
	}
	
	/**
	 * �����ַ�����ʵ���ȣ�һ������ �� 2���ַ�����
	 * @param value
	 * @return
	 */
	public static int String_length(String value) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

	public final static String STRING = "String";
	public final static String INTEGER = "Integer";
	public final static String DOUBLE = "Double";
	
	String[] FIELD_NAMES = new String[]{
			"pk_group",		// ����pk
			"pk_org",		// ��֯pk	��������֯��
			"ka_pk",		// ��pk
			"ka_code",		// ��code
			"kx_code",		// ���ͱ���
			"kx_name",		// ��������
			"dian",			// ������
			"ssje",			// ʵ�ս��
			"sykkpje",		// ʣ��ɿ�Ʊ���
			"ykpje",		// �ѿ�Ʊ�ܶ�
			"kkpje",		// �ɿ�Ʊ�ܶ�
			"zksj",			// NC�ƿ�ʱ��
			"kpjzrq",		// ��Ʊ��ֹʱ��
			"ygqje",		// �ѹ���Ʊ��
			"vdef01",		// �Զ�����01
			"vdef02",		// �Զ�����02
			"vdef03",		// �Զ�����03
			"vdef04",		// �Զ�����04
			"vdef05",		// �Զ�����05
			"vdef11",		// �Զ�����11
			"vdef12",		// �Զ�����12
			"vdef13",		// �Զ�����13
			"vdef14",		// �Զ�����14
			"vdef15",		// �Զ�����15
	};
	
	String[] FIELD_TYPES = new String[]{
			STRING,		// ����pk
			STRING,		// ��֯pk
			STRING,		// ��pk
			STRING,		// ��code
			STRING,		// ���ͱ���
			STRING,		// ��������
			STRING,		// ������
			DOUBLE,		// ʵ�ս��
			DOUBLE,		// ʣ��ɿ�Ʊ���
			DOUBLE,		// �ѿ�Ʊ�ܶ�
			DOUBLE,		// �ɿ�Ʊ�ܶ�
			STRING,		// NC�ƿ�ʱ��
			STRING,		// ��Ʊ��ֹʱ��
			DOUBLE,		// �ѹ���Ʊ��
			STRING,		// �Զ�����01
			STRING,		// �Զ�����02
			STRING,		// �Զ�����03
			STRING,		// �Զ�����04
			STRING,		// �Զ�����05
			DOUBLE,		// �Զ�����11
			DOUBLE,		// �Զ�����12
			DOUBLE,		// �Զ�����13
			DOUBLE,		// �Զ�����14
			DOUBLE,		// �Զ�����15
	};
}
