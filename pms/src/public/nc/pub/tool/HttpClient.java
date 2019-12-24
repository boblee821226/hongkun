package nc.pub.tool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class HttpClient {
	private static TLSManager tlsManager;
	
	public static enum RequestMethod{
		POST,GET
	}
	
	public static boolean isEmpty(String source){
		return source == null || source.isEmpty();
	}
	
	/**
	 * HttpGET/POST
	 * @param requestUrl
	 * @param reuqestMethod
	 * @param requestData
	 * @param requestHeader
	 * @return
	 * @throws Exception
	 */
	public static String doRequest(String requestUrl, RequestMethod reuqestMethod, String requestData, Map<String, String> requestHeader) throws Exception {
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			if (!isEmpty(requestData) && reuqestMethod==RequestMethod.GET) {
				requestUrl += "?" + requestData;
			}
			URL httpurl = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) httpurl
					.openConnection();
			if (httpurl.getProtocol().equalsIgnoreCase("HTTPS")) {
				HttpsURLConnection sConn = (HttpsURLConnection) connection;
				sConn.setHostnameVerifier(getTlsManager());
				sConn.setSSLSocketFactory(getTlsManager().getSSLContext()
						.getSocketFactory());
			}
			int timeout = 30 * 1000;
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestMethod(reuqestMethod==null ? "POST" : reuqestMethod.name());
			if (null != requestHeader) {
				for (Entry<String, String> entry : requestHeader.entrySet()) {
					connection.setRequestProperty(entry.getKey(),
							entry.getValue());
				}
			}
			if (!isEmpty(requestData)
					&& reuqestMethod==RequestMethod.POST) {
				connection.setDoOutput(true);
				connection.getOutputStream().write(requestData.getBytes("UTF-8"));
				connection.getOutputStream().flush();
				connection.getOutputStream().close();
			}
			StringBuffer resultBuffer = new StringBuffer();
			try {
				inputStream = connection.getInputStream();
			} catch (Exception e) {
				inputStream = connection.getErrorStream();
				e.printStackTrace();
			}
			if (inputStream != null) {
				reader = new BufferedReader(
						inputStreamReader = new InputStreamReader(inputStream,
								"UTF-8"));
				String tempLine = null;
				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}
			}
			return resultBuffer.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			if (reader != null)
				reader.close();
			if (inputStreamReader != null)
				inputStreamReader.close();
			if (inputStream != null)
				inputStream.close();
		}

	}
	
	/**
	 * @param paramMap
	 * @param subKey
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getQueryString(Map<String, Object> paramMap, String subKey) throws Exception{
		StringBuffer result = new StringBuffer();
		for (String key : paramMap.keySet()) {
			if (!result.toString().isEmpty()) {
				result.append("&");
			}
			Object object = paramMap.get(key);
			if (object instanceof Map) {
				Map<String, Object> subMap = (Map<String, Object>) object;
				result.append(getQueryString(subMap, key));
			}else{
				object = object == null ? "" : object;
				if (subKey != null && !subKey.trim().isEmpty()) {
					result.append(subKey + "["+key+"]=" + URLEncoder.encode(object.toString(), "UTF-8"));
				} else {
					result.append(key + "=" + URLEncoder.encode(object.toString(), "UTF-8"));
				}
			}
		}
		return result.toString();
	}

	public static TLSManager getTlsManager() throws Exception {
		if (tlsManager == null) {
			tlsManager = new TLSManager();
		}
		return tlsManager;
	}
	
	public static String doHttp() {
//		String requestUrl = "https://cambridge-api.shijicloud.com:443/login/1.0/api/user/login";
//		Map<String, String> requestHeader = new HashMap<String, String>();
//		requestHeader.put("hotelcode", "710001038");
//		requestHeader.put("authorization", "Bearer ecdd8429-b0d6-38da-95f5-4a91c34f2191");
//		Map<String, String> requestData = new HashMap<String, String>();
//		requestData.put("username", "train");
//		requestData.put("password", "31528198109743225FF9D0CF04D1FDD1");
//		String res = "";
//		try {
//			res = doRequest(requestUrl, RequestMethod.POST, new Gson().toJson(requestData), requestHeader);
//			System.out.println("==成功执行==");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return res;
		
		String requestUrl = "https://cambridge-api.shijicloud.com/NResource/2.0/api/channel/resource/rate-price/query";
		Map<String, String> requestHeader = new HashMap<String, String>();
		// 请求头
		requestHeader.put("Accept", "application/json");
		requestHeader.put("channel", "TS");
		requestHeader.put("credential", "1576050686607857");
		requestHeader.put("authorization", "Bearer 19f99c33-fd73-35cf-a7b0-c1dae60f1962");
		// 请求体
//		Map<String, String> requestData = new HashMap<String, String>();
//		requestData.put("username", "train");
//		requestData.put("password", "31528198109743225FF9D0CF04D1FDD1");
		String reqQuery = "startDate=2019-12-31&endDate=2019-12-31";
		String res = "";
		try {
			res = doRequest(requestUrl, RequestMethod.GET, reqQuery, requestHeader);
			System.out.println("==成功执行==");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	public static void main(String[] args) {
		System.out.println(doHttp());
	}
}
