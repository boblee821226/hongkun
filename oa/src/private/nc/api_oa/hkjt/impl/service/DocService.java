package nc.api_oa.hkjt.impl.service;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;

public class DocService {
	
	public static Object doAction(String account, String table) throws BusinessException  {
//		ApiPubInfo.unLock();
		ApiPubInfo.lock();	// 同步加锁
		
		try {
			if (ApiPubInfo.CACHE_DOC == null) {
				ApiPubInfo.CACHE_DOC = new HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>>();
			}
			if (table == null) {
				ApiPubInfo.CACHE_DOC.put(account, new HashMap<String,HashMap<String,HashMap<String,String>>>());
			}
			String   ID = "id";
			String  VID = "vid";
			String CODE = "code";
			String NAME = "name";
			String ACCOUNT = "account";
			BaseDAO dao = new BaseDAO(account);
	//		{// 用户 sm_user
	//			String TABLE = "sm_user";
	//			StringBuffer querySQL = 
	//				new StringBuffer("select cuserid, user_code, user_name from sm_user");
	//			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
	//			ApiPubInfo.CACHE_DOC.get(account).put(TABLE, new HashMap<String,HashMap<String,String>>());
	//			for (Object obj : list) {
	//				Object[] row = (Object[])obj;
	//				String id = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
	//				String code = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
	//				String name = PuPubVO.getString_TrimZeroLenAsNull(row[2]);
	//				HashMap<String,String> value = new HashMap<String,String>();
	//				value.put(CODE, code);
	//				value.put(NAME, name);
	//				ApiPubInfo.CACHE_DOC.get(account).get(TABLE).put(id, value);
	//			}
	//		}
			if (table == null || "bd_psndoc".equals(table)) {// 人员 bd_psndoc
				String TABLE = "bd_psndoc";
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" bd_psndoc.glbdef26 ")
						.append(",bd_psndoc.pk_psndoc ")
						.append(",bd_psndoc.code ")
						.append(",bd_psndoc.name ")
						.append(",sm_user.cuserid ")
						.append(" from bd_psndoc ")
						.append(" left join sm_user on bd_psndoc.pk_psndoc = sm_user.pk_psndoc ")
						.append(" where nvl(bd_psndoc.glbdef26,'~') <> '~' ")
				;
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				ApiPubInfo.CACHE_DOC.get(account).put(TABLE, new HashMap<String,HashMap<String,String>>());
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String lcId = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String id 	= PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					String code = PuPubVO.getString_TrimZeroLenAsNull(row[2]);
					String name = PuPubVO.getString_TrimZeroLenAsNull(row[3]);
					String userId = PuPubVO.getString_TrimZeroLenAsNull(row[4]);
					HashMap<String,String> value = new HashMap<String,String>();
					value.put(ID, id);
					value.put(CODE, code);
					value.put(NAME, name);
					value.put("userId", userId);
					ApiPubInfo.CACHE_DOC.get(account).get(TABLE).put(lcId, value);
				}
			}
			if (table == null || "org_orgs".equals(table)) {// 公司 org_orgs
				/**
				 * select code,name,pk_org,pk_vid,def20 from org_orgs
					where nvl(def20,'~') <> '~'
					;
				 */
				String TABLE = "org_orgs";
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" def20 ")
						.append(",pk_org ")
						.append(",pk_vid ")
						.append(",code ")
						.append(",name ")
						.append(",def2 ") // 默认付款账号
						.append(" from org_orgs ")
						.append(" where nvl(def20,'~') <> '~' ")
				;
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				ApiPubInfo.CACHE_DOC.get(account).put(TABLE, new HashMap<String,HashMap<String,String>>());
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String lcId = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String id 	= PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					String vid 	= PuPubVO.getString_TrimZeroLenAsNull(row[2]);
					String code = PuPubVO.getString_TrimZeroLenAsNull(row[3]);
					String name = PuPubVO.getString_TrimZeroLenAsNull(row[4]);
					String def2 = PuPubVO.getString_TrimZeroLenAsNull(row[5]);
					HashMap<String,String> value = new HashMap<String,String>();
					value.put(ID, id);
					value.put(VID, vid);
					value.put(CODE, code);
					value.put(NAME, name);
					value.put(ACCOUNT, def2);
					ApiPubInfo.CACHE_DOC.get(account).get(TABLE).put(lcId, value);
				}
			}
			if (table == null || "org_dept".equals(table)) {// 部门 org_orgs
				/**
				 * select code,name,pk_dept,pk_vid,pk_org,def18 from org_dept
					where nvl(def18,'~') <> '~'
					;
				 */
				String TABLE = "org_dept";
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" def18 ")
						.append(",pk_dept ")
						.append(",pk_vid ")
						.append(",pk_org")
						.append(",code ")
						.append(",name ")
						.append(" from org_dept ")
						.append(" where nvl(def18,'~') <> '~' ")
				;
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				ApiPubInfo.CACHE_DOC.get(account).put(TABLE, new HashMap<String,HashMap<String,String>>());
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String lcId = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String id 	= PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					String vid 	= PuPubVO.getString_TrimZeroLenAsNull(row[2]);
					String code = PuPubVO.getString_TrimZeroLenAsNull(row[3]);
					String name = PuPubVO.getString_TrimZeroLenAsNull(row[4]);
					HashMap<String,String> value = new HashMap<String,String>();
					value.put(ID, id);
					value.put(VID, vid);
					value.put(CODE, code);
					value.put(NAME, name);
					ApiPubInfo.CACHE_DOC.get(account).get(TABLE).put(lcId, value);
				}
			}
			if (table == null || "bd_defdoc_dkfs".equals(table)) {// 抵扣方式 dkfs
				/**
				 * select code, name, pk_defdoc from bd_defdoc 
					where enablestate = 2 and pk_defdoclist = '1001N510000000818QAH'
				 */
				String TABLE = "bd_defdoc_dkfs";
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" pk_defdoc ")
						.append(",code ")
						.append(",name ")
						.append(" from bd_defdoc ")
						.append(" where enablestate = 2 and pk_defdoclist = '1001N510000000818QAH' ")
				;
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				ApiPubInfo.CACHE_DOC.get(account).put(TABLE, new HashMap<String,HashMap<String,String>>());
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String id 	= PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String code = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					String name = PuPubVO.getString_TrimZeroLenAsNull(row[2]);
					HashMap<String,String> value = new HashMap<String,String>();
					value.put(ID, id);
					value.put(CODE, code);
					value.put(NAME, name);
					ApiPubInfo.CACHE_DOC.get(account).get(TABLE).put(name, value);
				}
			}
			if (table == null || "bd_balatype".equals(table)) {// 结算方式
				/**
				 * select 
				    code
				   ,name
				   ,pk_balatype id
				   from bd_balatype
				  where 11 = 11
				    and (enablestate = 2)
				  order by code
				 */
				String TABLE = "bd_balatype";
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" pk_balatype ")
						.append(",code ")
						.append(",name ")
						.append(" from bd_balatype ")
						.append(" where enablestate = 2 ")
				;
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				ApiPubInfo.CACHE_DOC.get(account).put(TABLE, new HashMap<String,HashMap<String,String>>());
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String id 	= PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String code = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					String name = PuPubVO.getString_TrimZeroLenAsNull(row[2]);
					HashMap<String,String> value = new HashMap<String,String>();
					value.put(ID, id);
					value.put(CODE, code);
					value.put(NAME, name);
					ApiPubInfo.CACHE_DOC.get(account).get(TABLE).put(name, value);
				}
			}
		} catch (Exception ex) {
			ApiPubInfo.unLock();	// 同步解锁
			throw new BusinessException(ex);
		}
		
		ApiPubInfo.unLock();	// 同步解锁
		
		return "更新档案成功";
	}
	
}
