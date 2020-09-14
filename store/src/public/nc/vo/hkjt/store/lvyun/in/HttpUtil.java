package nc.vo.hkjt.store.lvyun.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import nc.vo.pub.BusinessException;
import org.apache.http.HttpStatus;
/**
 * HttpClient工具类
 */
public class HttpUtil {

	/**
	 * @Title: doPost
	 * @Description: post请求（用于请求json格式的参数）
	 * @param uri
	 * @param params
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 *             String
	 */
	public static String doPost(String uri, String params) throws BusinessException {
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String jsonString = null;
		try {
			URL url = new URL(uri);
			//打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//请求方式
			conn.setRequestMethod("POST"); //"POST" "GET"
			// 设置headers参数
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			//DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//调用第三方http接口
			//获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			//发送请求参数即数据
			out.write(params);
			//flush输出流的缓冲
			out.flush();
			//获取调用第三方http接口后返回的结果
			int state = conn.getResponseCode();
			if (state == HttpStatus.SC_OK) {
				//获取URLConnection对象对应的输入流
				StringBuilder retData = new StringBuilder();
				InputStream is = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String line;
				while ((line = br.readLine()) != null) {
					retData.append(line);
				}
				//关闭流
				is.close();
				//解析结果
				jsonString = retData.toString();
			} else {
//				String errorMsgJson = "{\\\"code\\\": \\\"" + state + "\\\",\\\"data\\\": [],\\\"msg\\\": \\\"接口错误! \\\"}";
//				//jsonString = StringEscapeUtils.unescapeJava(errorMsgJson);
//				throw new BusinessException(errorMsgJson);
				throw new BusinessException("接口调用失败：" + state);
			}
			//断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new BusinessException(e);
			}
		}
		return jsonString;
	}
}