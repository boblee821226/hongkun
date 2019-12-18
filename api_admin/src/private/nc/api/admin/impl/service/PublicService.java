package nc.api.admin.impl.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api.admin.itf.ApiPubInfo;
import nc.api.admin.tool.PuPubVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;

public class PublicService {
	
	public static Object getUser(String account) throws BusinessException  {
		if (ApiPubInfo.CACHE_USER == null) {
			ApiPubInfo.CACHE_USER = new HashMap<String, HashMap<String, String>>();
		}
		ApiPubInfo.CACHE_USER.put(account, new HashMap<String, String>());
		BaseDAO dao = new BaseDAO(account);
		{// 用户 sm_user
			StringBuffer querySQL = 
				new StringBuffer("select email, cuserid from sm_user where nvl(email,'null') <> 'null'");
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			for (Object obj : list) {
				Object[] row = (Object[])obj;
				String email = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
				String userId = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
				ApiPubInfo.CACHE_USER.get(account).put(email, userId);
			}
		}
		return "更新操作员成功";
	}
	
	public static Object getGroup(String account) throws BusinessException  {
		if (ApiPubInfo.CACHE_GROUP == null) {
			ApiPubInfo.CACHE_GROUP = new HashMap<String, HashMap<String, String>>();
		}
		ApiPubInfo.CACHE_GROUP.put(account, new HashMap<String, String>());
		BaseDAO dao = new BaseDAO(account);
		{// 集团 org_group
			StringBuffer querySQL = 
				new StringBuffer("select name, pk_group from org_group");
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			for (Object obj : list) {
				Object[] row = (Object[])obj;
				String name = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
				String pk_group = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
				ApiPubInfo.CACHE_GROUP.get(account).put(name, pk_group);
			}
		}
		return "更新集团成功";
	}
	
}
