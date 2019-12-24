package nc.impl.pms.nc;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.vo.pub.SuperVO;

public class PmsNcAction {

	public static Object save(ArrayList<HashMap> pmsList, Class voClass, HashMap parmas) throws Exception {
		
		ArrayList<SuperVO> voList = new ArrayList();	// ת���ɵ� NC��vo
		
		// oracle�ؼ��ֵ�ת��
		HashMap<String, String> key_change_MAP = new HashMap<String, String>();
		key_change_MAP.put("date", "ddate");
		// ѭ��pms���ݣ���װ��ncVO
		for (Object obj : pmsList) {
			HashMap item = (HashMap)obj;
			SuperVO vo = (SuperVO)voClass.newInstance();
			for (Object keyObj : item.keySet()) {
				String pmsKey = keyObj.toString();
				String ncKey = pmsKey;
				// ���йؼ��ֵ�ת��
				if (key_change_MAP.containsKey(ncKey)) {
					ncKey = key_change_MAP.get(ncKey);
				}
				vo.setAttributeValue(ncKey, item.get(pmsKey));
			}
			// params�Ĵ���
			if (parmas != null && parmas.size() > 0) {
				for (Object keyObj : parmas.keySet()) {
					String key = keyObj.toString();
					vo.setAttributeValue(key, parmas.get(key));
				}
			}
			voList.add(vo);
		}
		
		BaseDAO dao = new BaseDAO();
		// Ӧ���� ��ɾ��������룬 ȷ�����ݲ��ظ�
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
		// ���� parmas�����ֶΣ����в�ѯɾ��
		dao.deleteByClause(voClass, deleteWhere.toString());
		// ����������
		dao.insertVOList(voList);
		
		return "ok";
	}
	
}
