package nc.api.admin.impl.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api.admin.itf.ApiPubInfo;
import nc.api.admin.tool.PuPubVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;

public class DocService {
	
	public static Object doAction(String account) throws BusinessException  {
		if (ApiPubInfo.CACHE_DOC == null) {
			ApiPubInfo.CACHE_DOC = new HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>>();
		}
		ApiPubInfo.CACHE_DOC.put(account, new HashMap<String,HashMap<String,HashMap<String,String>>>());
		String CODE = "code";
		String NAME = "name";
		BaseDAO dao = new BaseDAO(account);
		{// 用户 sm_user
			String TABLE = "sm_user";
			StringBuffer querySQL = 
				new StringBuffer("select cuserid, user_code, user_name from sm_user");
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			ApiPubInfo.CACHE_DOC.get(account).put(TABLE, new HashMap<String,HashMap<String,String>>());
			for (Object obj : list) {
				Object[] row = (Object[])obj;
				String id = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
				String code = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
				String name = PuPubVO.getString_TrimZeroLenAsNull(row[2]);
				HashMap<String,String> value = new HashMap<String,String>();
				value.put(CODE, code);
				value.put(NAME, name);
				ApiPubInfo.CACHE_DOC.get(account).get(TABLE).put(id, value);
			}
		}
		return "更新档案成功";
	}
	
}
