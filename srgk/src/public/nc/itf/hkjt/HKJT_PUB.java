package nc.itf.hkjt;

import java.util.HashMap;

public class HKJT_PUB {
	
	/**
	 * 升级改 PK 
	 * 李彬  2016年5月5日20:55:32
	 */
	
	public static final String MAKER = "0001N510000000000EHF";	// 制单人 -宏昆集团管理员2
	
	public static final String PK_ORG_HUIGUAN = "0001N510000000001SY7";	// 参照默认pk-会馆  西山温泉
	public static final String PK_ORG_JIUDIAN = "0001N510000000001SY5";	// 参照默认pk-酒店  康福瑞西山店
	
	//酒店
	public static HashMap<String,String> PK_ORG_JIUDIAN_MAP = null;
	public static final String PK_ORG_JIUDIAN_kfrxsd  = "0001N510000000001SY5";	// 康福瑞西山店
	public static final String PK_ORG_JIUDIAN_kfrxyld = "0001N510000000001SY1";	// 康福瑞酒店
	public static final String PK_ORG_JIUDIAN_llzjd   = "0001N510000000001SY3";	// 朗丽兹酒店
	//会馆
	public static HashMap<String,String> PK_ORG_HUIGUAN_MAP = null;
	public static final String PK_ORG_HUIGUAN_xswq = "0001N510000000001SY7";	// 西山温泉
	public static final String PK_ORG_HUIGUAN_gjhg = "0001N510000000001SXV";	// 国际会馆
	public static final String PK_ORG_HUIGUAN_gbl  = "0001N510000000001SXX";	// 贵宾楼
	
	// 既有 酒店 和 会馆
	public static HashMap<String,String> PK_ORG_HUIGUAN_JIUDIAN_MAP = null;
	
	// pk_corp  对应  店
	public static final HashMap<String,String> MAP_corp_dianCode = new HashMap<String,String>();
	
	static 
	{
		PK_ORG_HUIGUAN_MAP = new HashMap<String,String>();	// 会馆
		PK_ORG_HUIGUAN_MAP.put("base", PK_ORG_HUIGUAN);			// 会馆基础
		// TODO
//		PK_ORG_HUIGUAN_MAP.put("llzjd", PK_ORG_JIUDIAN_llzjd);	// 西山温泉 转移给 朗丽兹酒店（2019年5月）
		PK_ORG_HUIGUAN_MAP.put("xswq", PK_ORG_HUIGUAN_xswq);	// 朗丽兹 转移回 西山温泉（2020年1月20日11:06:27）
		/***END***/
		PK_ORG_HUIGUAN_MAP.put("gjhg", PK_ORG_HUIGUAN_gjhg);	// 国际会馆
		PK_ORG_HUIGUAN_MAP.put("gbl", PK_ORG_HUIGUAN_gbl);		// 贵宾楼
		
		PK_ORG_JIUDIAN_MAP = new HashMap<String,String>();		// 酒店
		PK_ORG_JIUDIAN_MAP.put("base", PK_ORG_JIUDIAN);				// 酒店基础
		PK_ORG_JIUDIAN_MAP.put("gjhg", PK_ORG_HUIGUAN_gjhg);		// 康西 转移给 国际会馆
		PK_ORG_JIUDIAN_MAP.put("kfrxyld", PK_ORG_JIUDIAN_kfrxyld);	// 康福瑞酒店
		PK_ORG_JIUDIAN_MAP.put("llzjd", PK_ORG_JIUDIAN_llzjd);		// 朗丽兹酒店
		
		PK_ORG_HUIGUAN_JIUDIAN_MAP = new HashMap<String,String>();	// 会馆 & 酒店
		PK_ORG_HUIGUAN_JIUDIAN_MAP.put("gjhg",  PK_ORG_HUIGUAN_gjhg);
		PK_ORG_HUIGUAN_JIUDIAN_MAP.put("llzjd", PK_ORG_JIUDIAN_llzjd);
		
		MAP_corp_dianCode.put(PK_ORG_HUIGUAN_gjhg    , "0302");	// 国际
		MAP_corp_dianCode.put(PK_ORG_HUIGUAN_gbl     , "0303");	// 贵宾楼
		MAP_corp_dianCode.put(PK_ORG_JIUDIAN_kfrxyld , "0305");	// 康福瑞酒店
		MAP_corp_dianCode.put(PK_ORG_JIUDIAN_llzjd   , "0308");	// 朗丽兹酒店 0308改成0701了
		MAP_corp_dianCode.put(PK_ORG_JIUDIAN_kfrxsd  , "0309");	// 康福瑞西山店
		MAP_corp_dianCode.put(PK_ORG_HUIGUAN_xswq    , "0310");	// 西山温泉  0310改成0801了
		
	}
	
	
	
}
