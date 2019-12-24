package nc.impl.pms.http;

import hd.vo.pub.tools.PuPubVO;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import nc.pub.tool.HttpClient;
import nc.pub.tool.HttpClient.RequestMethod;

public class PmsHttpAction {
	private static String baseUrl = "https://cambridge-api.shijicloud.com:443";
	private static String loginUrl = "/login/1.0";
	private static String billingUrl = "/NBilling/2.0";
	private static Gson gson = new Gson();
	
	private String credential = null;
	
	private String token, hotelCode, userName, passWord, channel;
	
	public PmsHttpAction(String token, String hotelCode, String userName, String passWord, String channel) {
		this.token = token;
		this.hotelCode = hotelCode;
		this.userName = userName;
		this.passWord = passWord;
		this.channel = channel;
	}
	
	/**
	 * ��¼ POST
	 * code = 1000
	 * credential = "1576406272975092"
	 */
	public boolean login() {
		String actionUrl = "/api/user/login";
		String requestUrl = baseUrl + loginUrl + actionUrl;
		// ͷ
		Map<String, String> reqHeader = new HashMap<String, String>();
		reqHeader.put("authorization", token);
		reqHeader.put("hotelcode", hotelCode);
		// ��
		Map<String, String> reqBody = new HashMap<String, String>();
		reqBody.put("username", userName);
		reqBody.put("password", passWord);
		// ���
		boolean isLogin = false;
		try {
			String res = HttpClient.doRequest(requestUrl, HttpClient.RequestMethod.POST, gson.toJson(reqBody), reqHeader);
			HashMap result = gson.fromJson(res, HashMap.class);
			if (1000 == PuPubVO.getInteger_NullAs(result.get("code"), -1)) {
				credential = PuPubVO.getString_TrimZeroLenAsNull(result.get("credential"));
				isLogin = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isLogin;
	}
	
	/**
	 * �ǳ� POST
	 * "code": 0
	 */
	public boolean logout() {
		String actionUrl = "/api/user/logout";
		String requestUrl = baseUrl + loginUrl + actionUrl;
		// ͷ
		Map<String, String> reqHeader = new HashMap<String, String>();
		reqHeader.put("authorization", this.token);
		reqHeader.put("hotelcode", this.hotelCode);
		reqHeader.put("credential", this.credential);
		boolean lsLogont = false;
		try {
			String res = HttpClient.doRequest(requestUrl, HttpClient.RequestMethod.POST, null, reqHeader);
			HashMap result = gson.fromJson(res, HashMap.class);
			if (1000 == PuPubVO.getInteger_NullAs(result.get("code"), -1)) {
				lsLogont = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lsLogont;
	}
	
	/**
	 * ������ʷ��ѯ GET
	 * 11��28�� ��ʼ��ҵ��
	 * 
	 * "code": 1000,
	  "msg": "ok",
	  "billHistories": ArrayList
	  		{
		      "reservationNumber": "1911280005",
		      "date": "2019-11-28T18:55:00Z",
		      "roomNumber": "B2056",
		      "transactionCode": "9102",
		      "transactionDesc": "���𽻸�(�й�������)",
		      "remark": "428662******4387 - 11/19",
		      "amount": 1892
		    }
	 * 
	 */
	public HashMap billing_query_history(String date, HashMap params) {
		String actionUrl = "/api/channel/billing/query-history";
		String requestUrl = baseUrl + billingUrl + actionUrl;
		// ͷ
		Map<String, String> reqHeader = new HashMap<String, String>();
		reqHeader.put("authorization", this.token);
		reqHeader.put("credential", this.credential);
		reqHeader.put("channel", this.channel);
		// ��
		String reqQuery = "date=" + date;
		// ���
		HashMap result = null;
		try {
			String res = HttpClient.doRequest(requestUrl, RequestMethod.GET, reqQuery, reqHeader);
			result = gson.fromJson(res, HashMap.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
