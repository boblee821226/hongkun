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
 * 会员开票信息查询表
 * @author 
 *
 */
public class Report_HYKPXXCXB extends AbstractReportAction {

	private BaseDAO dao = new BaseDAO();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DataSet excute(SmartContext context, HashMap<String, Object> param,
			String flag, Object other) throws Exception {
	
		String v_pk_group      = PuPubVO.getString_TrimZeroLenAsNull(param.get("v_pk_group"));		// 集团
		String v_ka_code       = PuPubVO.getString_TrimZeroLenAsNull(param.get("ka_code"));			// 卡号
		String v_dian  		   = PuPubVO.getString_TrimZeroLenAsNull(param.get("dian"));			// 发卡店
		UFBoolean v_is_ss_kkp  = PuPubVO.getUFBoolean_NullAs(param.get("is_ss_kkp"),UFBoolean.FALSE);	// 是否实收与可开票总额不一致
		UFBoolean v_is_ykp     = PuPubVO.getUFBoolean_NullAs(param.get("is_ykp"),UFBoolean.FALSE);		// 是否已开票金额不一致
		UFBoolean v_is_sykp    = PuPubVO.getUFBoolean_NullAs(param.get("is_sykp"),UFBoolean.FALSE);	// 是否剩余开票金额为负数
		String kkrq_b  		   = PuPubVO.getString_TrimZeroLenAsNull(param.get("kkrq_b"));			// 开卡日期（始）
		String kkrq_e  		   = PuPubVO.getString_TrimZeroLenAsNull(param.get("kkrq_e"));			// 开卡日期（终）
		
		if( v_pk_group==null 
		 || "当前集团".equals(v_pk_group)
		)
		{
			v_pk_group = "0001N510000000000EGY";
		}
		
		String whereSQL_1 = " and (1=1) ";
		
		if(v_ka_code!=null)
		{// 卡号
			whereSQL_1 += " and ka.ka_code = '"+v_ka_code+"' ";
		}
		
		if(v_dian!=null)
		{// 店
			whereSQL_1 += " and ka.vdef01 = '"+v_dian+"' ";
		}
		
		String whereSQL_2 = null;
		
		if(v_is_ss_kkp.booleanValue())
		{// 是否实收与可开票总额不一致
			whereSQL_2 = " bb.充值实收金额 <> bb.可开票总额 ";
		}
		if(v_is_ykp.booleanValue())
		{// 是否已开票金额不一致
			if( whereSQL_2!=null )
				whereSQL_2 += " or bb.已开票总额ka <> bb.已开票总额dj ";
			else 
				whereSQL_2  = " bb.已开票总额ka <> bb.已开票总额dj ";
		}
		if(v_is_sykp.booleanValue())
		{// 是否剩余可开票总额为负数
			if( whereSQL_2!=null )
				whereSQL_2 += " or bb.剩余可开票总额 < 0.00 ";
			else 
				whereSQL_2  = " bb.剩余可开票总额 < 0.00 ";
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
		 * HK 2019年4月16日10:23:45
		 * 增加 开卡日期 的查询条件
		 */
		if(kkrq_b!=null)
		{// 开卡日期（始）
			kkrq_b = kkrq_b + " 00:00:00";
			whereSQL_2 += " and bb.NC制卡时间 >= '"+kkrq_b+"'";
		}
		
		if(kkrq_e!=null)
		{// 开卡日期（终）
			kkrq_e = kkrq_e + " 23:59:59";
			whereSQL_2 += " and bb.NC制卡时间 <= '"+kkrq_e+"'";
		}
		/***END***/
		
		/**
		 * 保存语义模型时  使用
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
				.append("  			kp_ka.卡号,")											// 1
				.append("  			kp_ka.卡型编码,")											// 2
				.append("  			kp_ka.卡型名称,")											// 3
				.append("  			kp_ka.发卡店,")											// 4
				.append("  			cz_ka.充值实收金额,")										// 5
				.append("  			kp_ka.可开票总额,")										// 6
				.append("  			kp_dj.已审核,")											// 7
				.append("  			kp_dj.未审核,")											// 8
				.append("  			nvl(kp_dj.已审核,0) + nvl(kp_dj.未审核,0) 已开票总额dj,")		// 9
				.append("  			kp_ka.已开票总额 已开票总额ka,")								// 10
				.append("  			nvl(kp_ka.可开票总额,0) - ( nvl(kp_dj.已审核,0) + nvl(kp_dj.未审核,0) ) 剩余可开票总额,")	// 11
				.append("  			cz_ka.NC制卡时间,")										// 12
				.append("  			kp_ka.开票截止日期,")										// 13
				.append("  			null 已过期票额,")											// 14
				.append("  			cz_ka.充值次数,")											// 15
				.append("  			kp_dj.开票次数 ")											// 16
				.append("  	from ( ")
				.append("  			select ")
				.append("  			 ka.pk_hk_huiyuan_kadangan ")
				.append("  			,max(ka.ka_code) 卡号 ")
				.append("  			,sum(nvl(ka_kkp.ykp_je, 0)) 已开票总额 ")
				.append("  			,sum(nvl(ka_kkp.kkp_je, 0)) 可开票总额 ")
				.append("  			,max(kx.kaxing_code) 卡型编码 ")
				.append("  			,max(kx.kaxing_name) 卡型名称 ")
				.append("  			,max(ka.vdef01) 发卡店 ")
				.append("  			,max(ka_kkp.kpjz_time)开票截止日期 ")
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
				.append("  		 ) kp_ka ")		// 开票信息
				.append("   left join ( ")
				.append("  			select ")
				.append("  			 ka.pk_hk_huiyuan_kadangan ")
				.append("  			,sum(nvl(ka_cz.cz_ss, 0)) 充值实收金额 ")
				.append("  			,count(ka_cz.pk_hk_huiyuan_kadangan_cz) 充值次数 ")
				.append("  			,min(ka_cz.cz_time) NC制卡时间 ")
				.append("  			from hk_huiyuan_kadangan ka ")
				.append("  			inner join hk_huiyuan_kadangan_cz ka_cz ")
				.append("  			on ka.pk_hk_huiyuan_kadangan = ka_cz.pk_hk_huiyuan_kadangan ")
				.append("  			where (1=1) ")
				.append("  			and ka.dr = 0 ")
				.append("  			and ka_cz.dr = 0 ")
				.append(            whereSQL_1 )
				.append("  			and nvl(ka_cz.cz_ss, 0.0) <> 0.0 ")
				.append("			and ka_cz.vbdef02 <> '回充' ")
				.append("  			group by ka.pk_hk_huiyuan_kadangan ")
				.append("  		) cz_ka ")	// 充值
				.append("  		on cz_ka.pk_hk_huiyuan_kadangan = kp_ka.pk_hk_huiyuan_kadangan ")
				.append("  	left join (	")
				.append("  			select ")
				.append("  			 kpb.ka_pk ")
				.append("  			,sum(case when kp.ibillstatus = 1 then kpb.fpje else 0 END) 已审核 ")
				.append("  			,sum(case when kp.ibillstatus <>1 then kpb.fpje else 0 END) 未审核 ")
				.append("  			,count(kpb.pk_hk_huiyuan_kaipiaoinfo_b) 开票次数 ")
				.append("			from hk_huiyuan_kaipiaoinfo kp ")
				.append("  			inner join hk_huiyuan_kaipiaoinfo_b kpb ")
				.append("  			on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
				.append("  			where (1 = 1) ")
				.append("  			and kp.dr = 0 ")
				.append("  			and kpb.dr = 0 ")
				.append("  			group by kpb.ka_pk ")
				.append("  		) kp_dj ")	// 单据开票
				.append("  		on kp_dj.ka_pk = kp_ka.pk_hk_huiyuan_kadangan ")
				.append("  	 where (1 = 1) ")
				.append("  	 ) bb ")	// 汇总表
				.append("  where ").append( whereSQL_2 )
		;
		
		BaseDAO dao = new BaseDAO();
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		// 最终返回的二维数组
		Object[][] datas_final = new Object[0][];
		
		if( list!=null && list.size()>0 )
		{
			datas_final = new Object[list.size()][];
			
			String now_date_str = new UFDate().toString();	// 当前服务器 时间
			
			for(int i=0;i<list.size();i++)
			{
				datas_final[i] = new Object[FIELD_NAMES.length];
				
				Object[] datas_temp = (Object[])list.get(i);
				/**
				    "pk_group",		// 集团pk		0
					"pk_org",		// 组织pk		1
					"ka_pk",		// 卡pk			2
					"ka_code",		// 卡code		3
					"kx_code",		// 卡型编码		4
					"kx_name",		// 卡型名称		5
					"dian",			// 发卡店			6
					"ssje",			// 实收金额		7
					"sykkpje",		// 剩余可开票金额	8
					"ykpje",		// 已开票总额		9
					"kkpje",		// 可开票总额		10
					"zksj",			// NC制卡时间		11
					"kpjzrq",		// 开票截止时间	12
					"ygqje",		// 已过期票额		13
					"vdef01",		// 自定义项01		14
					"vdef02",		// 自定义项02		15
					"vdef03",		// 自定义项03		16
					"vdef04",		// 自定义项04		17
					"vdef05",		// 自定义项05		18
					"vdef11",		// 自定义项11		19
					"vdef12",		// 自定义项12		20
					"vdef13",		// 自定义项13		21
					"vdef14",		// 自定义项14		22
					"vdef15",		// 自定义项15		23
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
				
				String kpjesj = PuPubVO.getString_TrimZeroLenAsNull(datas_final[i][12]);	// 开票截止时间
				UFDouble sykpje = PuPubVO.getUFDouble_ZeroAsNull(datas_final[i][8]);		// 剩余开票金额
				
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
		
		// 构造数据集
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
	 * 返回字符的真实长度（一个中文 是 2个字符。）
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
			"pk_group",		// 集团pk
			"pk_org",		// 组织pk	（财务组织）
			"ka_pk",		// 卡pk
			"ka_code",		// 卡code
			"kx_code",		// 卡型编码
			"kx_name",		// 卡型名称
			"dian",			// 发卡店
			"ssje",			// 实收金额
			"sykkpje",		// 剩余可开票金额
			"ykpje",		// 已开票总额
			"kkpje",		// 可开票总额
			"zksj",			// NC制卡时间
			"kpjzrq",		// 开票截止时间
			"ygqje",		// 已过期票额
			"vdef01",		// 自定义项01
			"vdef02",		// 自定义项02
			"vdef03",		// 自定义项03
			"vdef04",		// 自定义项04
			"vdef05",		// 自定义项05
			"vdef11",		// 自定义项11
			"vdef12",		// 自定义项12
			"vdef13",		// 自定义项13
			"vdef14",		// 自定义项14
			"vdef15",		// 自定义项15
	};
	
	String[] FIELD_TYPES = new String[]{
			STRING,		// 集团pk
			STRING,		// 组织pk
			STRING,		// 卡pk
			STRING,		// 卡code
			STRING,		// 卡型编码
			STRING,		// 卡型名称
			STRING,		// 发卡店
			DOUBLE,		// 实收金额
			DOUBLE,		// 剩余可开票金额
			DOUBLE,		// 已开票总额
			DOUBLE,		// 可开票总额
			STRING,		// NC制卡时间
			STRING,		// 开票截止时间
			DOUBLE,		// 已过期票额
			STRING,		// 自定义项01
			STRING,		// 自定义项02
			STRING,		// 自定义项03
			STRING,		// 自定义项04
			STRING,		// 自定义项05
			DOUBLE,		// 自定义项11
			DOUBLE,		// 自定义项12
			DOUBLE,		// 自定义项13
			DOUBLE,		// 自定义项14
			DOUBLE,		// 自定义项15
	};
}
