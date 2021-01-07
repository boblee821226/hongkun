package nc.api_oa.hkjt.impl.service;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;

public class PublicService {
	
	public static Object getUser(String account) throws BusinessException  {
//		ApiPubInfo.unLock();
		ApiPubInfo.lock();	// 同步加锁
		
		try {
			if (ApiPubInfo.CACHE_USER == null) {
				ApiPubInfo.CACHE_USER = new HashMap<String, HashMap<String, String>>();
			}
			ApiPubInfo.CACHE_USER.put(account, new HashMap<String, String>());
			BaseDAO dao = new BaseDAO(account);
			{// 用户 sm_user
	//			StringBuffer querySQL = 
	//				new StringBuffer("select email, cuserid from sm_user where nvl(email,'null') <> 'null'");
				/**
				 * select -- bd_psndoc.code,bd_psndoc.name,
					 bd_psndoc.glbdef26
					,bd_psndoc.pk_psndoc
					,sm_user.cuserid
					from bd_psndoc
					left join sm_user on bd_psndoc.pk_psndoc = sm_user.pk_psndoc
					where nvl(glbdef26,'~') <> '~'
				 */
				StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" bd_psndoc.glbdef26 ")
	//					.append(",bd_psndoc.pk_psndoc ")
						.append(",sm_user.cuserid ")
						.append(" from bd_psndoc ")
						.append(" left join sm_user on bd_psndoc.pk_psndoc = sm_user.pk_psndoc ")
						.append(" where nvl(bd_psndoc.glbdef26,'~') <> '~' ")
						.append(" and sm_user.cuserid is not null ")
				;
				
				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String lcId = PuPubVO.getString_TrimZeroLenAsNull(row[0]);		// 乐才id
					String userId = PuPubVO.getString_TrimZeroLenAsNull(row[1]);	// nc ID
					ApiPubInfo.CACHE_USER.get(account).put(lcId, userId);
				}
			}
		} catch (Exception ex) {
			ApiPubInfo.unLock();	// 同步解锁
			throw new BusinessException(ex);
		}
		
		ApiPubInfo.unLock();	// 同步解锁
		
		return "更新操作员成功";
	}
	
	public static Object getGroup(String account) throws BusinessException  {
		
		ApiPubInfo.lock();	// 同步加锁
		
		try {
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
		} catch (Exception ex) {
			ApiPubInfo.unLock();	// 同步解锁
			throw new BusinessException(ex);
		}
		
		ApiPubInfo.unLock();	// 同步解锁
		
		return "更新集团成功";
	}
	
}
