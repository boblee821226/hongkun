package nc.impl.pms.nc;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.vo.pub.SuperVO;

public class PmsNcAction {

	public static Object save(ArrayList<HashMap> pmsList, Class voClass, HashMap parmas) throws Exception {
		
		ArrayList<SuperVO> voList = new ArrayList();	// 转换成的 NC的vo
		
		// oracle关键字的转换
		HashMap<String, String> key_change_MAP = new HashMap<String, String>();
		key_change_MAP.put("date", "ddate");
		// 循环pms数据，封装成ncVO
		for (Object obj : pmsList) {
			HashMap item = (HashMap)obj;
			SuperVO vo = (SuperVO)voClass.newInstance();
			for (Object keyObj : item.keySet()) {
				String pmsKey = keyObj.toString();
				String ncKey = pmsKey;
				// 进行关键字的转换
				if (key_change_MAP.containsKey(ncKey)) {
					ncKey = key_change_MAP.get(ncKey);
				}
				vo.setAttributeValue(ncKey, item.get(pmsKey));
			}
			// params的处理
			if (parmas != null && parmas.size() > 0) {
				for (Object keyObj : parmas.keySet()) {
					String key = keyObj.toString();
					vo.setAttributeValue(key, parmas.get(key));
				}
			}
			voList.add(vo);
		}
		
		BaseDAO dao = new BaseDAO();
		// 应该是 先删除，后插入， 确保数据不重复
		StringBuffer deleteWhere = new StringBuffer("(1=1)");
		if (parmas != null && parmas.size() > 0) {
			for (Object keyObj : parmas.keySet()) {
				String key = keyObj.toString();
				String value = parmas.get(key).toString();
				deleteWhere
					.append(" and ")
					.append(key).append(" = '").append(value).append("' ")
				;
			}
		}
		// 根据 parmas，的字段，进行查询删除
		dao.deleteByClause(voClass, deleteWhere.toString());
		// 插入新数据
		dao.insertVOList(voList);
		
		return "ok";
	}
	
}
