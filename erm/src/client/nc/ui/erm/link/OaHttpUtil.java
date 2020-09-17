package nc.ui.erm.link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class OaHttpUtil {

	/**
	 * ��ַ��http://111.204.181.93:93
		���� appid��36ac4080-6bc5-4b1a-a061-ae4810112d6e
		����loginid�����ֻ���
	 */
	/** ���� **/
//	private static String baseUrl = "http://111.204.181.93:93";
//	private static String appid = "36ac4080-6bc5-4b1a-a061-ae4810112d6e";
	/** ��ʽ **/
	private static String baseUrl = "http://39.102.46.51";
	private static String appid = "02b76fba-8121-428f-8628-642507e6fe71";
	
	public static String getToken(String loginid) {
        String url = baseUrl + "/ssologin/getToken";
        Map<String, String> params = new HashMap<>();
        params.put("appid", appid);
        params.put("loginid", loginid);
        String token = doPost(url, params);
        return token;
    }
	
	@SuppressWarnings("deprecation")
	public static String doPost(String url, Map params){

        BufferedReader in = null;
        try {
            // ����HttpClient
            HttpClient client = new DefaultHttpClient();
            // ʵ����HTTP����
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //���ò���
            List<NameValuePair> nvps = new ArrayList<>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){    //����ɹ�
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(),"utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                return sb.toString();
            } else {
                System.out.println("״̬�룺" + code);
                return null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post������������json��ʽ�Ĳ�����
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// ����httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


	
}
