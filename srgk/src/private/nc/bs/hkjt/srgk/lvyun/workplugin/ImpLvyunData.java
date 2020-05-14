package nc.bs.hkjt.srgk.lvyun.workplugin;

import hd.vo.pub.tools.PuPubVO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.hkjt.HKJT_PUB;
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.hkjt.srgk.tools.sqlserver.JDBCUtils;
import nc.vo.bd.defdoc.DefdocVO;
import nc.vo.bd.defdoc.DefdoclistVO;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
/**
 * ������������
 */
public class ImpLvyunData implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
		// ��֯
		String[] pk_orgs = context.getPk_orgs();
		// ͬ�� ������ϸ
		UFBoolean isBill = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isBill"),UFBoolean.FALSE);
		// ͬ�� ��Ʒ����
		UFBoolean isSpfl = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isSpfl"),UFBoolean.FALSE);
		// ͬ�� ���˷�ʽ
		UFBoolean isJzfs = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isJzfs"),UFBoolean.FALSE);
		// ͬ�� �г�
		UFBoolean isShichang = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isShichang"),UFBoolean.FALSE);
		// ͬ�� ��Դ
		UFBoolean isLaiyuan = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isLaiyuan"),UFBoolean.FALSE);
		// ͬ�� ����
		UFBoolean isQudao = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isQudao"),UFBoolean.FALSE);
		
		UFDate bdate = PuPubVO.getUFDate( context.getKeyMap().get("bdate") );	// ��ʼ����
		UFDate edate = PuPubVO.getUFDate( context.getKeyMap().get("edate") );	// ��������

		UFDate now = new UFDate().getDateBefore(1);	// Ĭ��Ϊ ��ǰ���� ǰһ��
		
		if (bdate == null) {
			bdate = now;
		}
		if (edate == null) {
			edate = now;
		}
		if (edate.compareTo(bdate) < 0) {
			edate = bdate;
		}
		
		ArrayList<String> day_list = new ArrayList<String>();
		Integer days = edate.getDaysAfter(bdate);
		for (int i = 0; i <= days; i++) {
			day_list.add(bdate.getDateAfter(i).toString().substring(0, 10));
		}
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("pk_org", pk_orgs);
		param.put("date", day_list.toArray(new String[0]));
		
		if (isQudao.booleanValue()) {
			this.tongbu_qudao(param, null);		// ���������ţ�
		}
		if (isLaiyuan.booleanValue()) {
			this.tongbu_laiyuan(param, null);	// ��Դ�����ţ�
		}
		if (isShichang.booleanValue()) {
			this.tongbu_shichang(param, null);	// �г������ţ�
		}
		if (isJzfs.booleanValue()) {
			this.tongbu_jzfs(param, null);	// ���˷�ʽ�����ţ����ϲ��ʽ�����ˣ���PMS�ﲻһ�£�
		}
		if (isSpfl.booleanValue()) {
			this.tongbu_spfl(param, null);	// ��Ʒ���ࣨ��˾��
		}
		if (isBill.booleanValue()) {
			this.import_bill(param, null);	// ������ϸ
		}
		
		return null;
	}
	
	/**
	 * ���ڴ������
	 * ��̨�����޷�ִ��
	 */
	public Object executeTest(Object obj) throws BusinessException
	{
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		String[] pk_org_list = new String[]{
//				"0001N510000000001SY3", // ������ 9
				"0001N510000000001SY5", // ���� 11
//				"0001N510000000001SY7", // ��ɽ��Ȫ 10
//				"0001N510000000001SY1", // ѧԺ·16
		};
		String[] date_list = new String[]{
			"2020-05-13",
		};
		
		param.put("pk_org", pk_org_list);
		param.put("date", date_list);
		
//		this.tongbu_jzfs(param, null);	// ���˷�ʽ�����ţ����ϲ��ʽ�����ˣ���PMS�ﲻһ�£�
//		this.tongbu_spfl(param, null);	// ��Ʒ���ࣨ��˾��
//		this.tongbu_shichang(param, null);	// �г������ţ�
//		this.tongbu_laiyuan(param, null);	// ��Դ�����ţ�
//		this.tongbu_qudao(param, null);		// ���������ţ�
		this.import_bill(param, null);	// ������ϸ
		return null;
	}
	
	/**
	 * ҵ�񵥾�
	 */
	public Object import_bill(HashMap<String, Object> param, Object other) throws BusinessException {
//		String[] pk_org_list = new String[]{
//			"0001N510000000001SY3", // ������
//				"0001N510000000001SY5", // ����
////				"0001N510000000001SY7", // ��ɽ��Ȫ
//		};
//		String[] date_list = new String[]{
//			"2020-03-12",
////				"2020-03-03"
//		};
		
		String[] pk_org_list = (String[])param.get("pk_org");
		String[] date_list = (String[])param.get("date");
		
		String pk_group = "0001N510000000000EGY";
		String billType = "HK11";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
		
		// ȡ�� ���ż�����
		// ���˷�ʽ��ͨ������ ȥƥ�䣩
		HashMap<String, JzfsHVO> DOC_JZFS = new HashMap<String, JzfsHVO>();
		{
			String whereSQL = " dr = 0 and pk_org = '0001N510000000001SY3' ";
			ArrayList<JzfsHVO> list = (ArrayList<JzfsHVO>)dao.retrieveByClause(JzfsHVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (JzfsHVO vo : list) {
//					String code = vo.getCode();
//					if (code.indexOf("-") > 0) {
//						code = code.substring(code.indexOf("-") + 1);
//					}
//					DOC_JZFS.put(code, vo);
					DOC_JZFS.put(vo.getName(), vo);
				}
			}
		}
		// �г�(ͨ������ ȥƥ��)
		HashMap<String, DefdocVO> DOC_SHICHANG = new HashMap<String, DefdocVO>();
		{
			String whereSQL = " dr = 0 and pk_defdoclist = " +
					"( select max(pk_defdoclist) from bd_defdoclist " +
					"  where dr = 0 and code = '200' ) ";
			ArrayList<DefdocVO> list = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (DefdocVO vo : list) {
					DOC_SHICHANG.put(vo.getName(), vo);
				}
			}
		}
		// ��Դ(ͨ�� ����ȥƥ��)
		HashMap<String, DefdocVO> DOC_LAIYUAN = new HashMap<String, DefdocVO>();
		{
			String whereSQL = " dr = 0 and pk_defdoclist = " +
					"( select max(pk_defdoclist) from bd_defdoclist " +
					"  where dr = 0 and code = '201' ) ";
			ArrayList<DefdocVO> list = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (DefdocVO vo : list) {
					DOC_LAIYUAN.put(vo.getName(), vo);
				}
			}
		}
		// ����(ͨ�� ����ȥƥ��)
		HashMap<String, DefdocVO> DOC_QUDAO = new HashMap<String, DefdocVO>();
		{
			String whereSQL = " dr = 0 and pk_defdoclist = " +
					"( select max(pk_defdoclist) from bd_defdoclist " +
					"  where dr = 0 and code = '202' ) ";
			ArrayList<DefdocVO> list = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
			if (list != null && list.size() > 0) {
				for (DefdocVO vo : list) {
					DOC_QUDAO.put(vo.getName(), vo);
				}
			}
		}
		
		// ��ѭ����˾����ѭ����
		for (String pk_org : pk_org_list) {
			// ��ȡ���ñ�
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
			Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
//				String org_code = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("org_code"));
//				String org_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("org_name"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));
			
			// ȡ����˾���ĵ���
			// ��Ʒ����(ͨ�� ����ȥƥ�䣬ֻȡLY01��LY02��LY03�µ�)
			HashMap<String, SpflHVO> DOC_SPFL = new HashMap<String, SpflHVO>();
			// ��Ʒ����-������ͨ������ ȥ��Ӧ LY04 �����µģ�
			HashMap<String, SpflHVO> DOC_SPFL_BAOFANG = new HashMap<String, SpflHVO>();
			// ��Ʒ����-Ӫҵ�㣨ͨ������ ȥ��Ӧ LY05 �����µģ�
			HashMap<String, SpflHVO> DOC_SPFL_YINGYEDIAN = new HashMap<String, SpflHVO>();
			// ��Ʒ����-¥�㣨ͨ������ ȥ��Ӧ LY06 �����µģ�
			HashMap<String, SpflHVO> DOC_SPFL_LOUCENG = new HashMap<String, SpflHVO>();
			{
				String whereSQL = " dr = 0 and code like 'LY0%' and pk_org = '" + pk_org + "' ";
				ArrayList<SpflHVO> list = (ArrayList<SpflHVO>)dao.retrieveByClause(SpflHVO.class, whereSQL);
				if (list != null && list.size() > 0) {
					for (SpflHVO vo : list) {
						String code = vo.getCode();
						String name = vo.getName();
						
						if (code.startsWith("LY04-")) {
							DOC_SPFL_BAOFANG.put(code.substring(5), vo);
						} else if (code.startsWith("LY05-")) {
							DOC_SPFL_YINGYEDIAN.put(name, vo);
						} else if (code.startsWith("LY06-")) {
							DOC_SPFL_LOUCENG.put(name, vo);
						} else {
							DOC_SPFL.put(name, vo); // �������ϣ������Ķ�����DOC_SPFL��
						}
					}
				}
			}
			
			// ȡ����˾���ĵ���
			// ������Ŀ��ͨ�� ����ȥƥ�䣩
			HashMap<String, SrxmHVO> DOC_SRXM = new HashMap<String, SrxmHVO>();
			HashMap<String, SrxmHVO> DOC_SRXM_KEY = new HashMap<String, SrxmHVO>();
			{
				String whereSQL = " dr = 0 and pk_org = '" + pk_org + "' ";
				ArrayList<SrxmHVO> list = (ArrayList<SrxmHVO>)dao.retrieveByClause(SrxmHVO.class, whereSQL);
				if (list != null && list.size() > 0) {
					for (SrxmHVO vo : list) {
						DOC_SRXM.put(vo.getName(), vo);
						DOC_SRXM_KEY.put(vo.getPk_hk_srgk_hg_srxm(), vo);
					}
				}
			}
			
			for (String date : date_list) {
				// ���ж� ����֯�������£��Ƿ���� ���ݣ� ������� �򲻴���
				String sql2 = "select pk_hk_srgk_jd_rzmx " +
						" from hk_srgk_jd_rzmx " +
						" where dr = 0 " +
						" and pk_org = '" + pk_org + "' " +
						" and substr(dbilldate, 1, 10) = '" + date + "' "
						;
				ArrayList<String> list2 = (ArrayList<String>)dao.executeQuery(sql2, new ColumnListProcessor());
				if (list2 != null && list2.size() > 0) {
					continue;
				}
				
				// ��ȡ�м��ҵ���
				/**
					 ��������ϸ�ֶ�	PMS	POS	ԭ������ϸ�ֶ�
					�к�	id	id	ACCID��accid��
					Ӫҵ����	biz_date	biz_date	����ʱ�䡾transdate��
					����	dept	pccode_des	���š�bm_name��
					NC����	������Ʒ����Ĭ�ϲ���	NC���š�bm_id��
					�˵��˺�	accnt	accnt	����vbdef04��
					����/����	rmno	tableno	����š�rmno��
					����/̨��	room_type_des	tableno_des	�������͡�rmtype_name��
					���۴���	package	��	����vbdef05��
					�г�	market_des	market_des	�г���vbdef01��
					����	channel_des	��	������vbdef02��
					��Դ	source_des	source_des	��Դ��vbdef03��
					����ʱ��	create_datetime	close_datetime	����vbdef06��
					������Ŀ����	ta_code	code	������Ŀ���롾item_code��
					������Ŀ����	ta_descript	code_descript	������Ŀ���ơ�item_name��
					NC��Ʒ����	������Ʒ��������ƥ��	NC��Ʒ���ࡾspfl_id��
					���ѽ��	charge	charge	���ѽ�charge��
					������	pay	credit	���˽�payment��
					NC������Ŀ	������Ʒ����������Ŀ	NC������Ŀ��srxm_id��
					NC���˷�ʽ	���ݽ��˷�ʽ����ƥ��	NC���˷�ʽ��jzfs_id��
					Ӧ���˻�	ar_name	ar_name	�ͻ����֡�khmz��
					��ֵ��ID	card_no	ar_accnt	����vbdef07��
					������ʶ	act_flag	��	����vbdef08��
					ת�Ʒ���	trans_flag	car_posting	����vbdef09��
					ת���˻����	trans_accnt	��	����vbdef10��
					��ϢժҪ	ta_remark	��	��ע��vbmemo��
					����Ա	create_user	close_user	����Ա��syy_code��
					������	card_account_type	��csourcetypecode��
				 */
				StringBuffer querySQL_bill = 
				new StringBuffer("")
					// PMS
						.append(" select ")
						.append(" id as accid,")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,")
//						.append(" case when IFNULL(floor_des, 'NULL') in ('NULL', '') then dept else floor_des end as bm_name,")
						.append(" dept bm_name, ")
						.append(" accnt as vbdef04,")
						.append(" rmno as rmno,")
						.append(" room_type_des as rmtype_name,")
						.append(" package as vbdef05,")
						.append(" market_des as vbdef01,")
						.append(" channel_des as vbdef02,")
						.append(" source_des as vbdef03,")
						.append(" DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,")
						.append(" ta_code as item_code,")
						.append(" ta_descript as item_name,")
						.append(" charge as charge,")
						.append(" pay as payment,")
						.append(" ar_name as khmz,")
						.append(" card_no as vbdef07,")
						.append(" act_flag as vbdef08,")
						.append(" trans_flag as vbdef09,")
						.append(" trans_accnt as vbdef10,")
						.append(" ta_remark as vbmemo,")
						.append(" create_user as syy_code,")
						.append(" card_account_type as csourcetypecode, ")
						.append(" modu_code as vsourcebillcode, ")
						.append(" floor_des as csourcebillid ")
						.append(" from account_pms ")
						.append(" where (1=1) ")
//							.append(" and (charge != 0 or pay != 0)")	// ���ѽ�� �� ���˽�� ������һ����Ϊ0
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
//						.append(" and ( IFNULL(trans_flag, 'NULL') not in ('TO', 'FM') or (IFNULL(trans_flag, 'NULL') in ('TO', 'FM') and charge = 0)) ")
//							.append(" order by id ")
					.append(" union all ")
					// POS
						.append(" select ")
						.append(" id as accid,")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,")
						.append(" pccode_des as bm_name,")
						.append(" accnt as vbdef04,")
						.append(" tableno as rmno,")
						.append(" tableno_des as rmtype_name,")
						.append(" null as vbdef05,")
						.append(" market_des as vbdef01,")
						.append(" null as vbdef02,")
						.append(" source_des as vbdef03,")
						.append(" DATE_FORMAT(close_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,")
						.append(" code as item_code,")
						.append(" code_descript as item_name,")
						.append(" charge as charge,")
						.append(" credit as payment,")
						.append(" ar_name as khmz,")
						.append(" card_no as vbdef07,")
						.append(" null as vbdef08,")
						.append(" cat_posting as vbdef09,")
						.append(" null as vbdef10,")
						.append(" null as vbmemo,")
						.append(" close_user as syy_code,")
						.append(" null as csourcetypecode, ")
						.append(" null as vsourcebillcode, ")
						.append(" null as csourcebillid ")
						.append(" from account_pos ")
						.append(" where (1=1) ")
//							.append(" and (charge != 0 or credit != 0)")	// ���ѽ�� �� ���˽�� ������һ����Ϊ0
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
						
				;
				ArrayList<RzmxBVO> list = null;
				
				Connection hkjt_jd_conn= null;
				JdbcSession session = null;
				hkjt_jd_conn = new JDBCUtils(db_name + "_bill").getConn(JDBCUtils.HKJT_LY);
				session = new JdbcSession(hkjt_jd_conn);
				
				try {	
					list = (ArrayList)session.executeQuery(querySQL_bill.toString(), new BeanListProcessor(RzmxBVO.class));					
				} catch (Exception ex) {
					System.out.println(ex);
				} finally{
					session.closeAll();
					JDBCUtils.closeConn(hkjt_jd_conn);
				}
				
				/**
				 *  select 
					 id as accid,
					 DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,
					 '����' as bm_name,
					 accnt as vbdef04,
					 foliono as rmno,
					 DATE_FORMAT( create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,
					 pccode as item_code,
					 descript as item_name,
					 credit as payment,
					 cardcode as vbdef07,
					 sta as vbdef08,
					 info1 as vbmemo,
					 create_user as syy_code
					 from pos_pay
				 */
				StringBuffer querySQL_bill_2 = 
					new StringBuffer("")
						.append(" select ")
						.append(" id as accid, ")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate, ")
						.append(" '����' as bm_name, ")
						.append(" accnt as vbdef04, ")
						.append(" foliono as rmno, ")
						.append(" DATE_FORMAT( create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06, ")
						.append(" pccode as item_code, ")
						.append(" descript as item_name, ")
						.append(" credit as payment, ")
						.append(" 0.0 as charge, ")
						.append(" cardcode as vbdef07, ")
						.append(" sta as vbdef08, ")
						.append(" info1 as vbmemo, ")
						.append(" create_user as syy_code ")
						.append(" from pos_pay ")
						.append(" where (1=1) ")
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
						.append(" and sta not in ('X') ") // ��ȡ X
				;
				ArrayList<RzmxBVO> list_2 = null;
				hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
				session = new JdbcSession(hkjt_jd_conn);
				try {	
					list_2 = (ArrayList)session.executeQuery(querySQL_bill_2.toString(), new BeanListProcessor(RzmxBVO.class));					
				} catch (Exception ex) {
					System.out.println(ex);
				} finally{
					session.closeAll();
					JDBCUtils.closeConn(hkjt_jd_conn);
				}
				if (list_2 != null && list_2.size() > 0) {
					if (list == null) {
						list = new ArrayList<RzmxBVO>();
					}
					for (RzmxBVO bVO : list_2) {
						list.add(bVO);
					}
				}
				
				/**
				 * ����� ���������ݿ⣬��Ҫ����һ�� ���ſ⣬����ȡ ��ֵ��������
				 */
				if ("hkjt_ly_liansuo".equals(db_name)) {
					StringBuffer querySQL_bill_3 = 
					new StringBuffer("")
						// PMS
						.append(" select ")
						.append(" id as accid,")
						.append(" DATE_FORMAT(biz_date, '%Y-%m-%d %H:%i:%s') as transdate,")
						.append(" dept as bm_name,")
						.append(" accnt as vbdef04,")
						.append(" rmno as rmno,")
						.append(" room_type_des as rmtype_name,")
						.append(" package as vbdef05,")
						.append(" market_des as vbdef01,")
						.append(" channel_des as vbdef02,")
						.append(" source_des as vbdef03,")
						.append(" DATE_FORMAT(create_datetime, '%Y-%m-%d %H:%i:%s') as vbdef06,")
						.append(" ta_code as item_code,")
						.append(" ta_descript as item_name,")
						.append(" charge as charge,")
						.append(" pay as payment,")
						.append(" ar_name as khmz,")
						.append(" card_no as vbdef07,")
						.append(" act_flag as vbdef08,")
						.append(" trans_flag as vbdef09,")
						.append(" trans_accnt as vbdef10,")
						.append(" ta_remark as vbmemo,")
						.append(" create_user as syy_code,")
						.append(" card_account_type as csourcetypecode, ")
						.append(" modu_code as vsourcebillcode ")
						.append(" from account_pms ")
						.append(" where (1=1) ")
//										.append(" and (charge != 0 or pay != 0)")	// ���ѽ�� �� ���˽�� ������һ����Ϊ0
						.append(" and hotel_id = ").append(hotel_id).append(" ")
						.append(" and biz_date = '").append(date).append("' ")
					;
					ArrayList<RzmxBVO> list_3 = null;
					hkjt_jd_conn = new JDBCUtils("hkjt_ly_feiliansuo_bill").getConn(JDBCUtils.HKJT_LY);
					session = new JdbcSession(hkjt_jd_conn);
					try {	
						list_3 = (ArrayList)session.executeQuery(querySQL_bill_3.toString(), new BeanListProcessor(RzmxBVO.class));					
					} catch (Exception ex) {
						System.out.println(ex);
					} finally{
						session.closeAll();
						JDBCUtils.closeConn(hkjt_jd_conn);
					}
					if (list_3 != null && list_3.size() > 0) {
						if (list == null) {
							list = new ArrayList<RzmxBVO>();
						}
						for (RzmxBVO bVO : list_3) {
							list.add(bVO);
						}
					}
				}
				/***END***/
				
				if (list != null && list.size() > 0) {
					IJd_rzmxMaintain itf = NCLocator.getInstance().lookup(IJd_rzmxMaintain.class);
					
					RzmxHVO hVO = new RzmxHVO();
					hVO.setPk_org(pk_org);
					hVO.setPk_org_v(pk_org_v);
					hVO.setPk_group(pk_group);
					hVO.setIbillstatus(-1);
					hVO.setVbilltypecode(billType);
					hVO.setDbilldate(PuPubVO.getUFDate(date));
					
					UFDouble charge = UFDouble.ZERO_DBL;
					UFDouble pay = UFDouble.ZERO_DBL;
					RzmxBVO[] bVOs = list.toArray(new RzmxBVO[0]);
					
					/**
					 * ��ѭ��һ�Σ��ҳ� �˵���Ӧ������
					 */
					HashMap<String, ArrayList<RzmxBVO>> MAP_ZHANGDAN = new HashMap<String, ArrayList<RzmxBVO>>();
					for (RzmxBVO bVO: bVOs) {
						String vbdef04 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef04());	// �˵���
						UFDouble charge_temp = PuPubVO.getUFDouble_ZeroAsNull(bVO.getCharge());	// ���ѽ��
						if (vbdef04 != null && charge_temp != null) {
							if (!MAP_ZHANGDAN.containsKey(vbdef04)) {
								MAP_ZHANGDAN.put(vbdef04, new ArrayList<RzmxBVO>());
							}
							MAP_ZHANGDAN.get(vbdef04).add(bVO);
						}
					}
					
					ArrayList<RzmxBVO> bVO_list = new ArrayList<RzmxBVO>(); // ����Ҫ����ı���
					Integer rowCount = 0;
					for (int i = 0; i < bVOs.length; i++) {
						// ����ѭ�� Ҫ���������
						ArrayList<RzmxBVO> bVO_list_temp = new ArrayList<RzmxBVO>();
						
						RzmxBVO bVO = bVOs[i];
						/**
						 * ���ݼӹ�
						 *  dept = AR ���� trans_flag in ('TO','FM') ��ΪתӦ��
						 *  ��� pay <> 0 ��ȡ��
						 *  ��� charge <> 0 �� ת�Ƶ� pay��
						 *  ���ͣ������Ȼ�԰�׷�
						 *  ��Դ������
						 *  ����������
						 *  �г�������-����
						 *  9800��תӦ��
						 */
						String itemName = PuPubVO.getString_TrimZeroLenAsNull(bVO.getItem_name());	// ������Ŀ-����
						String bmName = PuPubVO.getString_TrimZeroLenAsNull(bVO.getBm_name()); // ����
						String vbdef09 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef09()); // TO FM
						String csourcetypecode = PuPubVO.getString_TrimZeroLenAsNull(bVO.getCsourcetypecode()); // ������
						String vbdef08 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef08()); // ������ʶ
						String vsourcebillcode = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVsourcebillcode());	// ��Դ����
						String csourcebillid = PuPubVO.getString_TrimZeroLenAsNull(bVO.getCsourcebillid());	// ¥��
						
						if (csourcebillid != null
						&& ("TO".equals(vbdef09) 
						 || "FM".equals(vbdef09))
						) {// ǰ̨������floor_des�ٿ�ֵ����trans_flag=to��fm�� ��ȡ�����ݲ�ȡ��Ƶ�������ϸ��
							continue;
						} else if ("ǰ̨".equals(bmName) 
						&& ("TO".equals(vbdef09) 
						 || "FM".equals(vbdef09))
						) {// dept = ǰ̨ ����trans_flag=to��fm�� ��ȡ������
							continue;
						}
						else if ("AR".equals(bmName) 
						&& ("TO".equals(vbdef09) 
						 || "FM".equals(vbdef09))) {
							if (PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null) {
								// pay <> 0
								bVO.setPayment(bVO.getPayment().multiply(-1.0));
								bVO.setVbmemo("NCתӦ��1��"+bVO.getVbmemo());
								bVO.setItem_code("9800");
								bVO.setItem_name("תӦ��");
							}
							if (PuPubVO.getUFDouble_ZeroAsNull(bVO.getCharge()) != null) {
								// charge <> 0
								bVO.setPayment(bVO.getCharge());
								bVO.setCharge(UFDouble.ZERO_DBL);
								bVO.setVbmemo("NCת����2��"+bVO.getVbmemo());
								bVO.setItem_code("9800");
								bVO.setItem_name("תӦ��");
								bVO.setVbdef01("����-����"); // sc
								bVO.setVbdef02("����"); // qd
								bVO.setVbdef03("����"); // ly
								bVO.setRmtype_name("�����Ȼ�԰�׷�"); // rmType
							}
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
						}
						/***END***/
						/**
						 * �������ݣ�1
						 * dept=arֵ��trans_flagֵΪ�գ�pay��0�ģ������������ݣ���Ҫ��������һ����ֵ��
						 *  ������һ�����������ֶ�֮����Ҫ��ta_descript�ֶ���ֵ��
						 *  �滻�ɼ��˻���(תӦ��)��Pay�ֶ���ֵ����Ϊԭ������-1
						 */
						else if ("AR".equals(bmName)
						&& PuPubVO.getString_TrimZeroLenAsNull(vbdef09) == null
						&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null
						) {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����3��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.0));
							bVO_clone.setItem_code("9800");
							bVO_clone.setItem_name("תӦ��");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
							
						}
						/***END***/
						/**
						 * ��Ա�������⴦��
						 * ****** �������ݵķ���ǰ�� A
						 * 1. dept=��ֵ����act_flag=CL���������в�ȡ��Ҳ�������κμӹ������ļ���
						 * 2. dept=��ֵ����trans_flag=CL���������в�ȡ��Ҳ�������κμӹ������ļ���
						 * 3. dept=��ֵ����act_flag=PP���������в�ȡ��Ҳ�������κμӹ������ļ���
						 * 4. dept=��ֵ����card_account_type=BASE����act_flag=CH���������в�ȡ��Ҳ�������κμӹ������ļ���
						 * 5. dept=��ֵ������trans_flag=TO����FM�ģ��������в�ȡ��Ҳ�������κμӹ������ļ���
						 * ****** ��������� B
						    1. card_account_type=BASE����act_flag=PA����������ԭ����һ�У�������ԭʼ�����ݣ���PAY�ֶν�����-1���滻ta_descript�ֶ�ֵΪ��ֵ�����ۡ�
							2. card_account_type=TIMES����act_flag=PA����������ԭ����һ�У�������ԭʼ�����ݣ���PAY�ֶν�����-1���滻ta_descript�ֶ�ֵΪ��ֵ�ο����ۡ�
							3. card_account_type�ֶ�ΪTIMESֵ����act_flag�ֶ�ΪCHֵ����������ԭ����һ�У�������ԭʼ�����ݣ�����charge�ֶ��н����Ƶ�PAY�ֶ��У��滻ta_descript�ֶ�ֵΪ��ֵ�ο�
							4. dept=��ֵ����act_flag=AD��card_account_type=TIMES��charge��0��, ��������ԭ����һ�У�������ԭʼ�����ݣ�����charge�ֶ��н����Ƶ�pay�ֶ��У��滻ta_descript�ֶ�ֵΪ��ֵ�ο�
							5. dept=��ֵ����act_flag=AD�� card_account_type=TIMES��pay��0�ģ���������ԭ����һ�У�������ԭʼ�����ݣ�����PAY�ֶν�����-1���滻ta_descript�ֶ�ֵΪ��ֵ�ο�����
							6. dept=��ֵ����act_flag=AD�� card_account_type=BASE��pay��0�ģ���������ԭ����һ�У�������ԭʼ�����ݣ�����PAY�ֶν�����-1���滻ta_descript�ֶ�ֵΪ��ֵ�����ۡ�
						 */
						else if ("��ֵ��".equals(bmName)
							  && "CL".equals(vbdef08)
						) {// A-1
							continue;
						}
						else if ("��ֵ��".equals(bmName)
							  && "CL".equals(vbdef09)
						) {// A-2
							continue;
						}
						else if ("��ֵ��".equals(bmName)
							  && "PP".equals(vbdef08)
						) {// A-3
							continue;
						}
						else if ("��ֵ��".equals(bmName)
							&& "BASE".equals(csourcetypecode)
							&& "CH".equals(vbdef08)
						) {// A-4
							continue;
						}
						else if ("��ֵ��".equals(bmName)
							&& ("TO".equals(vbdef09) 
							 || "FM".equals(vbdef09))
						) {// A-5
							continue;
						}
						else if ("BASE".equals(csourcetypecode)
							&& "PA".equals(vbdef08)
						) {// B-1
							// 2��card_account_type�ֶ�ΪBASEֵ����act_flag�ֶ�ΪPAֵ����������ԭ����һ�У�������ԭʼ�����ݣ���PAY�ֶγ���-1���滻ta_descript�ֶ�ֵΪ��ֵ�����ۡ�
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����4��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("0301");
							bVO_clone.setItem_name("��ֵ������");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
							
						}
						else if ("TIMES".equals(csourcetypecode)
							&& "PA".equals(vbdef08)
						) {// B-2
							// 3��card_account_type�ֶ�ΪTIMESֵ����act_flag�ֶ�ΪPAֵ����������ԭ����һ�У�������ԭʼ�����ݣ���PAY�ֶγ���-1���滻ta_descript�ֶ�ֵΪ��ֵ�ο����ۡ�
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����5��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("0302");
							bVO_clone.setItem_name("��ֵ�ο�����");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("TIMES".equals(csourcetypecode)
							&& "CH".equals(vbdef08)
						) {// B-3
							// 4��
							//  1. card_account_type�ֶ�ΪTIMESֵ����act_flag�ֶ�ΪCHֵ��ta_descript�����ֵ����Ʒ����������������һ���ģ���Ӧƥ�䲿�ź�NC������Ŀ����
							//  2. ��������ԭ����һ�У�������ԭʼ�����ݣ�����PAY�ֶΣ�=charge�Ľ�ta_descript�ֶ�ֵΪ��ֵ�ο�
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����6��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getCharge());
							bVO_clone.setCharge(UFDouble.ZERO_DBL);
							bVO_clone.setItem_code("0303");
							bVO_clone.setItem_name("��ֵ�ο�");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("��ֵ��".equals(bmName)
							&& "AD".equals(vbdef08)
							&& "TIMES".equals(csourcetypecode)
							&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getCharge()) != null
						) {// B-4
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����10��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getCharge());
							bVO_clone.setCharge(UFDouble.ZERO_DBL);
							bVO_clone.setItem_code("0303");
							bVO_clone.setItem_name("��ֵ�ο�");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("��ֵ��".equals(bmName)
							&& "AD".equals(vbdef08)
							&& "TIMES".equals(csourcetypecode)
							&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null
						) {// B-5
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����11��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.0));
							bVO_clone.setItem_code("0302");
							bVO_clone.setItem_name("��ֵ�ο�����");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						else if ("��ֵ��".equals(bmName)
							&& "AD".equals(vbdef08)
							&& "BASE".equals(csourcetypecode)
							&& PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null
						) {// B-6
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����12��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.0));
							bVO_clone.setItem_code("0301");
							bVO_clone.setItem_name("��ֵ������");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						/***END***/
						/**
						 * �ж�����: 
						 * code_descript=POS-����д������Ƴ�һ�����ݣ��滻code_descript=����-POS-����д���pay�ֶν�����-1��
						 * ��Σ�����accnt�ֶε��˵��ţ��ж����˵��ŵ�charge����Ϣ�����Ƴ����������֮��charge������-1��
						 */
						else if ("POS-����д�".equals(itemName)) {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����7��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("918101");
							bVO_clone.setItem_name("����-POS-����д�");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
							
							// �����˵��ţ��ҵ� ��������
							String vbdef04 = PuPubVO.getString_TrimZeroLenAsNull(bVO.getVbdef04());
							ArrayList<RzmxBVO> zhangdanVOs = MAP_ZHANGDAN.get(vbdef04);
							if (zhangdanVOs != null && zhangdanVOs.size() > 0) {
								for (RzmxBVO zdVO : zhangdanVOs) {
									RzmxBVO zdVO_clone = (RzmxBVO)zdVO.clone();
									zdVO_clone.setVbmemo("NC����8��"+zdVO_clone.getVbmemo());
									zdVO_clone.setCharge(zdVO_clone.getCharge().multiply(-1.00));
									zdVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
									bVO_list_temp.add(zdVO_clone);
								}
							}
						}
						/**
						 * 1. ��������Ҫ��PMSϵͳ���棬�����������ز�ȡ����������������modu_code=04����trans_flagΪ��ֵ�ġ�
						 * 2. ���ǵ�ɢ��Ѻ���ڼ����ʱ����Ҫ����POSϵͳ����������{תӦ��}��credit�Ǹ�����������ȷ��ɢ��Ѻ�𡣣�֮ǰ����Ҳ�������������
						 * ** ������ �ڶ���
						 */
						else if ("04".equals(vsourcebillcode)
							  && null == vbdef09
						) {
							continue;
						}
						/***END***/
						/**
						 * ����I״̬���ݣ���������ԭ����һ�У�������ԭʼ�����ݣ���descript�ֶ��滻�� ����Ԥ����credit�ֶγ���-1
						 * ����T״̬���ݣ���������ԭ����һ�У�������ԭʼ�����ݣ���descript�ֶ��滻�� ����Ԥ����credit�ֶγ���-1
						 */
						else if ("I".equals(vbdef08)
							 ||  "T".equals(vbdef08)
							 ||  "O".equals(vbdef08)
						) {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
							
							RzmxBVO bVO_clone = (RzmxBVO)bVO.clone();
							bVO_clone.setVbmemo("NC����9��"+bVO_clone.getVbmemo());
							bVO_clone.setPayment(bVO_clone.getPayment().multiply(-1.00));
							bVO_clone.setItem_code("992001");
							bVO_clone.setItem_name("����Ԥ����");
							bVO_clone.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO_clone);
						}
						
						/**
						 * �������
						 */
						else {
							bVO.setVrowno("" + (((rowCount++)+1) * 10));
							bVO_list_temp.add(bVO);
						}
						
						/**
						 * ���¥����ֵ���򽫲��Ÿ��滻��
						 */
						if (csourcebillid != null) {
							bVO.setBm_name(csourcebillid);
						}
						/***END***/
						
						Object[] res = (Object[])this.doBodyVO(
							bVO_list, 
							bVO_list_temp, 
							charge, 
							pay, 
							DOC_JZFS, 
							DOC_SPFL_BAOFANG, 
							DOC_SPFL, 
							DOC_SRXM, 
							DOC_SRXM_KEY, 
							DOC_SPFL_YINGYEDIAN, 
							DOC_QUDAO, 
							DOC_SHICHANG, 
							DOC_LAIYUAN,
							DOC_SPFL_LOUCENG
						);
						
						bVO_list = (ArrayList<RzmxBVO>)res[0];
						charge = (UFDouble)res[1];
						pay = (UFDouble)res[2];
					}
					
					hVO.setXfje(charge);
					hVO.setJzje(pay);
					
					RzmxBillVO billVO = new RzmxBillVO();
					billVO.setParentVO(hVO);
					billVO.setChildrenVO(bVO_list.toArray(new RzmxBVO[0]));
					
					for (Object one : billVO.getChildrenVO()) {
						RzmxBVO bVO = (RzmxBVO)one;
						if (bVO.getPk_hk_srgk_jd_rzmx_b() != null) {
							System.out.println(bVO);
						}
					}
					
					/**
					 * ��ѯ5��ָ��
					 * ������	ffl			150000
					 * ƽ������	pjfj	140000
					 * REVPAR	revpar	160000
					 * ����	kfsr		120000
					 * ������	kczfs		110000
					 * 
					 *  110000	a���ͷ�����	224.00
						120000	b������(���г���)	22.00
						140000	d��ƽ������(���г���)	736.09
						150000	e��������%(��������)	9.82
						160000	f��Revpar	72.29
					 * 
					 */
					{
						StringBuffer querySQL_5 = 
							new StringBuffer("SELECT ")
									.append(" code,")
									.append(" descript,")
									.append(" day ")
									.append(" FROM rep_jour_history ")
									.append(" WHERE (1=1) ")
									.append(" AND CODE IN ('140000','150000','160000','110000','120000') ")
									.append(" and hotel_id = ").append(hotel_id).append(" ")
									.append(" and biz_date = '").append(date).append("' ")
						;
						ArrayList<Object> list_5 = null;
						hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
						session = new JdbcSession(hkjt_jd_conn);
						try {	
							list_5 = (ArrayList)session.executeQuery(querySQL_5.toString(), new ArrayListProcessor());					
						} catch (Exception ex) {
							System.out.println(ex);
						} finally{
							session.closeAll();
							JDBCUtils.closeConn(hkjt_jd_conn);
						}
						if (list_5 != null && list_5.size() > 0) {
							for (Object obj : list_5) {
								Object[] item = (Object[])obj;
								String code = PuPubVO.getString_TrimZeroLenAsNull(item[0]);
								UFDouble day = PuPubVO.getUFDouble_ValueAsValue(item[2]);
								if ("150000".equals(code)) { // ������
									hVO.setFfl(day);
								} else if ("140000".equals(code)) { // ƽ������
									hVO.setPjfj(day);
								} else if ("160000".equals(code)) { // Revpar
									hVO.setRevpar(day);
								} else if ("120000".equals(code)) { // ����
									hVO.setKfsr(day);
								} else if ("110000".equals(code)) { // �ͷ�����
									hVO.setKczfs(day);
								}
							}
						}
					}
					
					RzmxBillVO[] res = itf.insert(new RzmxBillVO[]{billVO}, null);
					System.out.println(res);
				}
				// ��ȡ�м��������
				
			}
		}
		return null;
	}
	
	/**
	 * ���б������ݵĵ������룬�Լ����ĵ���
	 */
	private Object doBodyVO(
			ArrayList<RzmxBVO> bVO_list,
			ArrayList<RzmxBVO> bVO_list_temp, 
			UFDouble charge,
			UFDouble pay,
			HashMap<String, JzfsHVO> DOC_JZFS,
			HashMap<String, SpflHVO> DOC_SPFL_BAOFANG,
			HashMap<String, SpflHVO> DOC_SPFL,
			HashMap<String, SrxmHVO> DOC_SRXM,
			HashMap<String, SrxmHVO> DOC_SRXM_KEY,
			HashMap<String, SpflHVO> DOC_SPFL_YINGYEDIAN,
			HashMap<String, DefdocVO> DOC_QUDAO,
			HashMap<String, DefdocVO> DOC_SHICHANG,
			HashMap<String, DefdocVO> DOC_LAIYUAN,
			HashMap<String, SpflHVO> DOC_SPFL_LOUCENG
			
	) throws BusinessException {
		
		for (RzmxBVO bVO : bVO_list_temp) {
		
			charge = charge.add(bVO.getCharge());	// �ۼ�����
			pay = pay.add(bVO.getPayment());		// �ۼƽ���
			/** ���е����ķ���
			* ����������Ŀ���룬ȥ�� NC����Ӧ���ֶ�
			* ��� ���˽�� ��Ϊ�㣬˵�������� ������Ϣ����Ҫ �� NC���˷�ʽ
			* ���� ��˵�������� ������Ϣ����Ҫ�� NC����Ʒ���࣬�Լ���Ӧ��������Ŀ �Լ����� 
			*/
			String bmName = bVO.getBm_name();	// ����name
//			String itemCode = bVO.getItem_code();	// ������Ŀ����
			String itemName = bVO.getItem_name();	// ������Ŀ����
			if (PuPubVO.getUFDouble_ZeroAsNull(bVO.getPayment()) != null) {
				// ���� => NC���˷�ʽ
				JzfsHVO doc = DOC_JZFS.get(itemName);
				if (doc != null) {
					bVO.setJzfs_id(doc.getPk_hk_srgk_hg_jzfs());
				}
			} else {
				SpflHVO doc = null;
				// ���� => NC��Ʒ���� => NC������Ŀ & NC���� => NC�ϼ�����
				// ��� ������Ŀ���� �� ���ѣ��򰴷������� ȥ ��������Ŀ��
				// ��� ������Ŀ���� �� �����ѣ���ȥ ����� ȥ�� ��̨����Ʒ����
	//				String rmtypeName = bVO.getRmtype_name();
				if ("������".equals(itemName)) {
					String rmno = bVO.getRmno();
					doc = DOC_SPFL_BAOFANG.get(rmno);
				}
	//			else if ("����".equalsIgnoreCase(itemName)) {
				else if (itemName.indexOf("����") >= 0
					|| itemName.indexOf("�Ӵ���") >= 0
				) {
					String rmtypeName = bVO.getRmtype_name();
					doc = DOC_SPFL.get(rmtypeName);
				} else {
					doc = DOC_SPFL.get(itemName);
				}
				if (doc != null) {
					bVO.setSpfl_id(doc.getPk_hk_srgk_hg_spfl());
					bVO.setSrxm_id(doc.getPk_hk_srgk_hg_srxm());
					String pk_dept = doc.getPk_dept();
					String srxmId = doc.getPk_hk_srgk_hg_srxm();
					SrxmHVO srxmVO = DOC_SRXM_KEY.get(srxmId);
					if (srxmVO != null) {
						String srxmName = srxmVO.getName();
						if (srxmName.endsWith("���ܣ�")) {
	//						String bmName = bVO.getBm_name();
							String srxmName2 = srxmName.replaceFirst("���ܣ�", "") + "-" + bmName;
							SrxmHVO srxmVO2 = DOC_SRXM.get(srxmName2);
							if (srxmVO2 != null) {
								bVO.setSrxm_id(srxmVO2.getPk_hk_srgk_hg_srxm());
							}
						} else if (srxmName.endsWith("������")) {
							String rmno = bVO.getRmno();
							String srxmName2 = srxmName.replaceFirst("������", "") + "-" + rmno;
							SrxmHVO srxmVO2 = DOC_SRXM.get(srxmName2);
							if (srxmVO2 != null) {
								bVO.setSrxm_id(srxmVO2.getPk_hk_srgk_hg_srxm());
							}
						} else {
							bVO.setSrxm_id(srxmId);
						}
					}
					if (pk_dept == null) {
						// ��� ��Ʒ�����ϵ� ����Ϊ�գ���ȥ ���Ŷ�Ӧ����Ʒ����Ӫҵ�㣨LY05������Ĳ���
						SpflHVO doc_yyd = DOC_SPFL_YINGYEDIAN.get(bmName);
						if (doc_yyd != null) {
							pk_dept = doc_yyd.getPk_dept();
						}
					}
					if (pk_dept == null) {
						// ��� ��Ʒ�����ϵ� ����Ϊ�գ���ȥ ���Ŷ�Ӧ����Ʒ����¥�㵵����LY06������Ĳ���
						SpflHVO doc_lc = DOC_SPFL_LOUCENG.get(bmName);
						if (doc_lc != null) {
							pk_dept = doc_lc.getPk_dept();
						}
					}
					bVO.setBm_id(pk_dept);
					bVO.setBm_fid(pk_dept);
				}
			}
			// �����Զ��嵵���� ����
			if (bVO.getVbdef01() != null) {
				// �г�
				DefdocVO doc = DOC_SHICHANG.get(bVO.getVbdef01());
				if (doc != null) {
					bVO.setVbdef01(doc.getPk_defdoc());
				}
			}
			if (bVO.getVbdef02() != null) {
				// ����
				DefdocVO doc = DOC_QUDAO.get(bVO.getVbdef02());
				if (doc != null) {
					bVO.setVbdef02(doc.getPk_defdoc());
				}
			}
			if (bVO.getVbdef03() != null) {
				// ��Դ
				DefdocVO doc = DOC_LAIYUAN.get(bVO.getVbdef03());
				if (doc != null) {
					bVO.setVbdef03(doc.getPk_defdoc());
				}
			}
			bVO_list.add(bVO);	// ��ӵ���������
		}
		
		return new Object[]{
			bVO_list,
			charge,
			pay,
		};
	}
	
	/**
	 * ���˷�ʽ�����ţ�
	 * ȡ �������Ŀ⼴��
	 * ���� �����ȡ�0001N510000000001SY3
	 * vdef5 ʱ���
	 */
	public Object tongbu_jzfs(HashMap<String, Object> param, Object other) throws BusinessException {
			
		String[] pk_org_list = new String[]{
			"0001N510000000001SY3" // ������
		};
		
		String pk_group = "0001N510000000000EGY";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
		
		for (String pk_org : pk_org_list) {
			// ��ȡ���ñ�
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
//				Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));

			StringBuffer querySQL = 
			new StringBuffer(" SELECT ")
					.append(" code, ")
					.append(" descript as name, ")
					.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') as vdef5 ")
					.append(" from code_transaction ")
					.append(" where ")
					.append(" code like '9%' ")
					.append(" and hotel_id = 0 ")
					.append(" and is_halt = 'F' ") // ��ͣ��
					.append(" order by code ")
			;
			
			ArrayList<JzfsHVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {	
				list_source = (ArrayList)session.executeQuery(querySQL.toString(), new BeanListProcessor(JzfsHVO.class));					
			} catch (Exception ex) {
				System.out.println(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// ȡ��֮�󣬷ŵ� ���档(����Ϊ׼)
				HashMap<String, JzfsHVO> MAP_source = new HashMap<>();
				for (JzfsHVO vo : list_source) {
					MAP_source.put(vo.getCode(), vo);
				}
				// ȡNC�ĵ���
				String whereSQL = " dr = 0 and pk_org = '" + pk_org + "' ";
				ArrayList<JzfsHVO> list_des = (ArrayList<JzfsHVO>)dao.retrieveByClause(JzfsHVO.class, whereSQL);
				HashMap<String, JzfsHVO> MAP_des = new HashMap<>();
				if (list_des != null && list_des.size() > 0) {
					for (JzfsHVO vo : list_des) {
						String code = vo.getCode();
						if (code.indexOf("-") > 0) {
							code = code.substring(code.indexOf("-") + 1);
						}
						MAP_des.put(code, vo);
					}
				}
				// �����ļ���
				ArrayList<JzfsHVO> list_add = new ArrayList<JzfsHVO>();
				// �޸ĵļ���
				ArrayList<JzfsHVO> list_mod = new ArrayList<JzfsHVO>();
				// ����source ȥѭ���жϣ������� ���Ǹ��¡�
				for (Entry<String, JzfsHVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					JzfsHVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						JzfsHVO vo_des = MAP_des.get(code);
						if (vo_source.getVdef5().compareTo(vo_des.getVdef5()) > 0) {
							// ��� ��Դ��ʱ��� ���� Ŀ�ĵ�ʱ������� ����
							// ������ ��Ҫ���µ��ֶ�
							JzfsHVO vo_mod = (JzfsHVO)vo_des.clone();
							vo_mod.setVdef5(vo_source.getVdef5());
							vo_mod.setName(vo_source.getName());
							list_mod.add(vo_mod);
						}
					} else {
						// ����
						JzfsHVO vo_add = new JzfsHVO();
						vo_add.setCode(code);
						vo_add.setName(vo_source.getName());
						vo_add.setVdef5(vo_source.getVdef5());
						vo_add.setPk_org(pk_org);
						vo_add.setPk_org_v(pk_org_v);
						vo_add.setPk_group(pk_group);
						vo_add.setAttributeValue("dr", 0);
						vo_add.setLevelno(1);
						list_add.add(vo_add);
					}
				}
				if (list_add.size() > 0) {
					String[] res = dao.insertVOList(list_add);
				}
				if (list_mod.size() > 0) {
					dao.updateVOList(list_mod);
				}
			}
		}
		return null;
	}
	
	/**
	 *  ��Ʒ���ࣨ��˾��
	 *  vdef4 ʱ���
	 */
	public Object tongbu_spfl(HashMap<String, Object> param, Object other) throws BusinessException {
		
//		String[] pk_org_list = new String[]{
//			"0001N510000000001SY3", // ������
//			"0001N510000000001SY7", // ��ɽ��Ȫ
//			"0001N510000000001SY5", // ����
//		};
		
		String[] pk_org_list = (String[])param.get("pk_org");
		
		String pk_group = "0001N510000000000EGY";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
		
		// ѭ����˾
		for (String pk_org : pk_org_list) {
			// ��ȡ���ñ�
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
			Integer hotel_id = PuPubVO.getInteger_NullAs(INFO_MAP.get("hotel_id"), -1);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));

			StringBuffer querySQL = 
			new StringBuffer("")
				// �������
				.append(" SELECT ")
				.append(" 'LY01' as pk_parent, ")
				.append(" CONCAT('LY01-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" FROM  code_transaction ")
				.append(" where code not like '9%' ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // ��ͣ��
			.append(" union all ")
				// ����������
				.append(" SELECT ")
				.append(" 'LY02' as pk_parent, ")
				.append(" CONCAT('LY02-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" FROM code_base ")
				.append(" WHERE parent_code='pos_rep_item' ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // ��ͣ��
			.append(" union all ")
				// ��������
				.append(" SELECT ")
				.append(" 'LY03' as pk_parent, ")
				.append(" CONCAT('LY03-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" FROM room_type ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // ��ͣ��
			.append(" union all ")
				// ������̨
				.append(" SELECT ")
				.append(" 'LY04' as pk_parent, ")
				.append(" CONCAT('LY04-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" from pos_pccode_table ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // ��ͣ��
			.append(" union all ")
				// Ӫҵ��
				.append(" SELECT ")
				.append(" 'LY05' as pk_parent, ")
				.append(" CONCAT('LY05-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" from pos_pccode ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // ��ͣ��
			.append(" union all ")
				// ¥��
				.append(" SELECT ")
				.append(" 'LY06' as pk_parent, ")
				.append(" CONCAT('LY06-', code) as code, ")
				.append(" descript as name, ")
				.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') vdef4 ")
				.append(" from room_floor ")
				.append(" where (1=1) ")
				.append(" and hotel_id = ").append(hotel_id).append(" ")
				.append(" and is_halt = 'F' ") // ��ͣ��
			;
			
			ArrayList<SpflHVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {
				list_source = (ArrayList)session.executeQuery(querySQL.toString(), new BeanListProcessor(SpflHVO.class));					
			} catch (Exception ex) {
				System.out.println(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// ȡ��֮�󣬷ŵ� ���档(����Ϊ׼)
				HashMap<String, SpflHVO> MAP_source = new HashMap<>();
				for (SpflHVO vo : list_source) {
					MAP_source.put(vo.getCode(), vo);
				}
				// ȡNC�ĵ���
				String whereSQL = " dr = 0 and code like 'LY0%' and pk_org = '" + pk_org + "' ";
				ArrayList<SpflHVO> list_des = (ArrayList<SpflHVO>)dao.retrieveByClause(SpflHVO.class, whereSQL);
				HashMap<String, SpflHVO> MAP_des = new HashMap<>();
				if (list_des != null && list_des.size() > 0) {
					for (SpflHVO vo : list_des) {
						MAP_des.put(vo.getCode(), vo);
					}
				}
				// �����ļ���
				ArrayList<SpflHVO> list_add = new ArrayList<SpflHVO>();
				// �޸ĵļ���
				ArrayList<SpflHVO> list_mod = new ArrayList<SpflHVO>();
				// ����source ȥѭ���жϣ������� ���Ǹ��¡�
				for (Entry<String, SpflHVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					SpflHVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						SpflHVO vo_des = MAP_des.get(code);
						if (vo_source.getVdef4().compareTo(vo_des.getVdef4()) > 0) {
							// ��� ��Դ��ʱ��� ���� Ŀ�ĵ�ʱ������� ����
							// ������ ��Ҫ���µ��ֶ�
							SpflHVO vo_mod = (SpflHVO)vo_des.clone();
							vo_mod.setVdef4(vo_source.getVdef4());
							vo_mod.setName(vo_source.getName());
							list_mod.add(vo_mod);
						}
					} else {
						// ����
						SpflHVO vo_add = new SpflHVO();
						vo_add.setCode(code);
						vo_add.setName(vo_source.getName());
						vo_add.setVdef4(vo_source.getVdef4());
						vo_add.setPk_org(pk_org);
						vo_add.setPk_org_v(pk_org_v);
						vo_add.setPk_group(pk_group);
						vo_add.setAttributeValue("dr", 0);
//						if (MAP_des.get(vo_source.getPk_parent()) == null) {
//							System.out.println("======");
//						}
						vo_add.setPk_parent(MAP_des.get(vo_source.getPk_parent()).getPk_hk_srgk_hg_spfl());
						list_add.add(vo_add);
					}
				}
				if (list_add.size() > 0) {
					String[] res = dao.insertVOList(list_add);
				}
				if (list_mod.size() > 0) {
					dao.updateVOList(list_mod);
				}
			}
		}
		return null;
	}
	
	/**
	 * �г����������ţ�
	 * ȡ �������Ŀ⼴�� 0001N510000000001SY3
	 * ���� �Զ��嵵�� 200
	 * def1 ʱ���
	 * market_code
	 */
	public Object tongbu_shichang(HashMap<String, Object> param, Object other) throws BusinessException {
		param.put("ncTypeCode", "200");
		param.put("lyTypeCode", "market_code");
		return tongbu_DefDoc(param, other);
	}

	/**
	 * ��Դ���������ţ�
	 * ȡ �������Ŀ⼴�� 0001N510000000001SY3
	 * ���� �Զ��嵵�� 201
	 * src_code
	 */
	public Object tongbu_laiyuan(HashMap<String, Object> param, Object other) throws BusinessException {
		param.put("ncTypeCode", "201");
		param.put("lyTypeCode", "src_code");
		return tongbu_DefDoc(param, other);
	}
	
	/**
	 * �������������ţ�
	 * ȡ �������Ŀ⼴�� 0001N510000000001SY3
	 * ���� �Զ��嵵�� 202
	 * channel
	 */
	public Object tongbu_qudao(HashMap<String, Object> param, Object other) throws BusinessException {
		param.put("ncTypeCode", "202");
		param.put("lyTypeCode", "channel");
		return tongbu_DefDoc(param, other);
	}
	
	/**
	 * �Զ��嵵����ͳһ������
	 */
	private Object tongbu_DefDoc(HashMap<String, Object> param, Object other) throws BusinessException {
		
		String ncTypeCode = PuPubVO.getString_TrimZeroLenAsNull(param.get("ncTypeCode"));
		String lyTypeCode = PuPubVO.getString_TrimZeroLenAsNull(param.get("lyTypeCode"));
		
		String[] pk_org_list = new String[]{
				"0001N510000000001SY3" // ������
			};
			
		String pk_group = "0001N510000000000EGY";
		BaseDAO dao = new BaseDAO();
		InvocationInfoProxy.getInstance().setUserId(HKJT_PUB.MAKER);//�����Ƶ���
		
		for (String pk_org : pk_org_list) {
			// ��ȡ���ñ�
			String sql = "select * from HK_SRGK_LVYUN_INFO where pk_org = '"+pk_org+"' ";
			HashMap<String, Object> INFO_MAP = (HashMap<String, Object>)dao.executeQuery(sql, new MapProcessor());
//				String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("pk_org_v"));
			String db_name = PuPubVO.getString_TrimZeroLenAsNull(INFO_MAP.get("db_name"));
			
			StringBuffer querySQL = 
			new StringBuffer(" SELECT ")
					.append(" code, ")
					.append(" descript as name, ")
					.append(" DATE_FORMAT(modify_datetime, '%Y-%m-%d %H:%i:%s') def1 ")
					.append(" from code_base ")
					.append(" where ")
					.append(" parent_code = '").append(lyTypeCode).append("' ")
					.append(" and hotel_id = 0 ")	// ����
					.append(" and is_halt = 'F' ") 	// ��ͣ��
					.append(" order by list_order ")
			;
			
			ArrayList<DefdocVO> list_source = null;
			Connection hkjt_jd_conn= null;
			JdbcSession session = null;
			hkjt_jd_conn = new JDBCUtils(db_name + "_bd").getConn(JDBCUtils.HKJT_LY);
			session = new JdbcSession(hkjt_jd_conn);
			
			try {	
				list_source = (ArrayList<DefdocVO>)session.executeQuery(querySQL.toString(), new BeanListProcessor(DefdocVO.class));					
			} catch (Exception ex) {
				System.out.println(ex);
			} finally{
				session.closeAll();
				JDBCUtils.closeConn(hkjt_jd_conn);
			}
			
			if (list_source != null && list_source.size() > 0) {
				// ȡ��֮�󣬷ŵ� ���档(����Ϊ׼)
				HashMap<String, DefdocVO> MAP_source = new HashMap<>();
				for (DefdocVO vo : list_source) {
					MAP_source.put(vo.getCode(), vo);
				}
				// ȡNC�ĵ���
				// ��ȡ ���࣬��ȡ ����
				HashMap<String, DefdocVO> MAP_des = new HashMap<>();
				ArrayList<DefdoclistVO> defdoclistVO = (ArrayList<DefdoclistVO>)dao.retrieveByClause(DefdoclistVO.class, " dr = 0 and code = '" + ncTypeCode + "' ");
				String pk_defdoclist = null;
				if (defdoclistVO != null && defdoclistVO.size() > 0) {
					pk_defdoclist = defdoclistVO.get(0).getPk_defdoclist();
					String whereSQL = " dr = 0 and pk_defdoclist = '" + pk_defdoclist + "' ";
					ArrayList<DefdocVO> list_des = (ArrayList<DefdocVO>)dao.retrieveByClause(DefdocVO.class, whereSQL);
					if (list_des != null && list_des.size() > 0) {
						for (DefdocVO vo : list_des) {
							MAP_des.put(vo.getCode(), vo);
						}
					}
				}
				// �����ļ���
				ArrayList<DefdocVO> list_add = new ArrayList<DefdocVO>();
				// �޸ĵļ���
				ArrayList<DefdocVO> list_mod = new ArrayList<DefdocVO>();
				// ����source ȥѭ���жϣ������� ���Ǹ��¡�
				for (Entry<String, DefdocVO> source : MAP_source.entrySet()) {
					String code = source.getKey();
					DefdocVO vo_source = source.getValue();
					if (MAP_des.containsKey(code)) {
						DefdocVO vo_des = MAP_des.get(code);
						if (vo_source.getDef1().compareTo(vo_des.getDef1()) > 0) {
							// ��� ��Դ��ʱ��� ���� Ŀ�ĵ�ʱ������� ����
							// ������ ��Ҫ���µ��ֶ�
							DefdocVO vo_mod = (DefdocVO)vo_des.clone();
							vo_mod.setDef1(vo_source.getDef1());
							vo_mod.setName(vo_source.getName());
							list_mod.add(vo_mod);
						}
					} else {
						// ����
						DefdocVO vo_add = new DefdocVO();
						vo_add.setCode(code);
						vo_add.setName(vo_source.getName());
						vo_add.setDef1(vo_source.getDef1());
						vo_add.setPk_org(pk_group);	// ���ż�����
						vo_add.setPk_group(pk_group);
						vo_add.setPk_defdoclist(pk_defdoclist);
						vo_add.setDatatype(1);
						vo_add.setEnablestate(2);
						vo_add.setAttributeValue("dr", 0);
						list_add.add(vo_add);
					}
				}
				if (list_add.size() > 0) {
					String[] res = dao.insertVOList(list_add);
				}
				if (list_mod.size() > 0) {
					dao.updateVOList(list_mod);
				}
			}
		}
		
		return null;
	}
}
