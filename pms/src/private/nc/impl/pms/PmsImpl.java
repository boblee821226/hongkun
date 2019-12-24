package nc.impl.pms;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.impl.pms.http.PmsHttpAction;
import nc.impl.pms.nc.PmsNcAction;
import nc.itf.pms.PmsItf;
import nc.vo.pms.BillingQueryHistoryVO;
import nc.vo.pub.BusinessException;

public class PmsImpl implements PmsItf {

	@Override
	public Object sync_pms(HashMap param, Object other)
			throws BusinessException {
		
		String token = PuPubVO.getString_TrimZeroLenAsNull(param.get("token"));
		String hotelCode = PuPubVO.getString_TrimZeroLenAsNull(param.get("hotelCode"));
		String userName = PuPubVO.getString_TrimZeroLenAsNull(param.get("userName"));
		String passWord = PuPubVO.getString_TrimZeroLenAsNull(param.get("passWord"));
		String channel = PuPubVO.getString_TrimZeroLenAsNull(param.get("channel"));
		
		ArrayList<String> dates = (ArrayList)param.get("dates");
		
//		String token = "Bearer 7a9c8fa7-36e7-3a73-940c-fafd88bbf797";
//		String hotelCode = "110001038";
//		String userName = "CWAPI";
//		String passWord = "58316383D08E49A11E96C72203E61D50";
//		String channel = "TS";
		
		PmsHttpAction action = new PmsHttpAction(
			token, hotelCode, userName, passWord, channel
		);
		
		boolean isLogin = action.login();	// ��¼
		if (isLogin) {
			
			// ��������list��������д���
			for (String queryDate : dates) {
				// ������������������ģ���Ҫ���Լ��Ŀ��� ��ӡ�
				HashMap<String, String> option = new HashMap<>();
				option.put("hotelCode", hotelCode);
				option.put("queryDate", queryDate);
				
				// ��ѯ��ʷ�˵�
				try {
					HashMap billingQueryHistory = action.billing_query_history(queryDate, null);
					ArrayList pmsList = (ArrayList)billingQueryHistory.get("billHistories");
					Object res = PmsNcAction.save(pmsList, BillingQueryHistoryVO.class, option);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// ������ѯҵ��ÿ��ҵ�� һ�� try�飬�������š�
			}
			
			action.logout();	// �ǳ�
		}
		
		return "ok";
		
	}

}
