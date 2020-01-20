package nc.itf.hkjt;

import java.util.HashMap;

public class HKJT_PUB {
	
	/**
	 * ������ PK 
	 * ���  2016��5��5��20:55:32
	 */
	
	public static final String MAKER = "0001N510000000000EHF";	// �Ƶ��� -�������Ź���Ա2
	
	public static final String PK_ORG_HUIGUAN = "0001N510000000001SY7";	// ����Ĭ��pk-���  ��ɽ��Ȫ
	public static final String PK_ORG_JIUDIAN = "0001N510000000001SY5";	// ����Ĭ��pk-�Ƶ�  ��������ɽ��
	
	//�Ƶ�
	public static HashMap<String,String> PK_ORG_JIUDIAN_MAP = null;
	public static final String PK_ORG_JIUDIAN_kfrxsd  = "0001N510000000001SY5";	// ��������ɽ��
	public static final String PK_ORG_JIUDIAN_kfrxyld = "0001N510000000001SY1";	// ������Ƶ�
	public static final String PK_ORG_JIUDIAN_llzjd   = "0001N510000000001SY3";	// �����ȾƵ�
	//���
	public static HashMap<String,String> PK_ORG_HUIGUAN_MAP = null;
	public static final String PK_ORG_HUIGUAN_xswq = "0001N510000000001SY7";	// ��ɽ��Ȫ
	public static final String PK_ORG_HUIGUAN_gjhg = "0001N510000000001SXV";	// ���ʻ��
	public static final String PK_ORG_HUIGUAN_gbl  = "0001N510000000001SXX";	// ���¥
	
	// ���� �Ƶ� �� ���
	public static HashMap<String,String> PK_ORG_HUIGUAN_JIUDIAN_MAP = null;
	
	// pk_corp  ��Ӧ  ��
	public static final HashMap<String,String> MAP_corp_dianCode = new HashMap<String,String>();
	
	static 
	{
		PK_ORG_HUIGUAN_MAP = new HashMap<String,String>();	// ���
		PK_ORG_HUIGUAN_MAP.put("base", PK_ORG_HUIGUAN);			// ��ݻ���
		// TODO
//		PK_ORG_HUIGUAN_MAP.put("llzjd", PK_ORG_JIUDIAN_llzjd);	// ��ɽ��Ȫ ת�Ƹ� �����ȾƵ꣨2019��5�£�
		PK_ORG_HUIGUAN_MAP.put("xswq", PK_ORG_HUIGUAN_xswq);	// ������ ת�ƻ� ��ɽ��Ȫ��2020��1��20��11:06:27��
		/***END***/
		PK_ORG_HUIGUAN_MAP.put("gjhg", PK_ORG_HUIGUAN_gjhg);	// ���ʻ��
		PK_ORG_HUIGUAN_MAP.put("gbl", PK_ORG_HUIGUAN_gbl);		// ���¥
		
		PK_ORG_JIUDIAN_MAP = new HashMap<String,String>();	// �Ƶ�
		PK_ORG_JIUDIAN_MAP.put("base", PK_ORG_JIUDIAN);				// �Ƶ����
		PK_ORG_JIUDIAN_MAP.put("gjhg", PK_ORG_HUIGUAN_gjhg);		// ���� ת�Ƹ� ���ʻ��
		PK_ORG_JIUDIAN_MAP.put("kfrxyld", PK_ORG_JIUDIAN_kfrxyld);	// ������Ƶ�
		PK_ORG_JIUDIAN_MAP.put("llzjd", PK_ORG_JIUDIAN_llzjd);		// �����ȾƵ�
		
		PK_ORG_HUIGUAN_JIUDIAN_MAP = new HashMap<String,String>();	// ��� & �Ƶ�
		PK_ORG_HUIGUAN_JIUDIAN_MAP.put("gjhg",  PK_ORG_HUIGUAN_gjhg);
		PK_ORG_HUIGUAN_JIUDIAN_MAP.put("llzjd", PK_ORG_JIUDIAN_llzjd);
		
		MAP_corp_dianCode.put(PK_ORG_HUIGUAN_gjhg    , "0302");	// ����
		MAP_corp_dianCode.put(PK_ORG_HUIGUAN_gbl     , "0303");	// ���¥
		MAP_corp_dianCode.put(PK_ORG_JIUDIAN_kfrxyld , "0305");	// ������Ƶ�
		MAP_corp_dianCode.put(PK_ORG_JIUDIAN_llzjd   , "0308");	// �����ȾƵ�
		MAP_corp_dianCode.put(PK_ORG_JIUDIAN_kfrxsd  , "0309");	// ��������ɽ��
		MAP_corp_dianCode.put(PK_ORG_HUIGUAN_xswq    , "0310");	// ��ɽ��Ȫ
		
	}
	
	
	
}
