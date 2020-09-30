package nc.vo.hkjt.store.lvyun.in;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

/**
 * @version 1.0
 */
public class GcSignUtils {
	/**
	 * 秘钥
	 * 与 appKey是配套的
	 */
//	public static final String hotelGroupCode = "GCBZG";
//	public static final String appKey = "12000";
//	public static final String SECRET = "526198bf3d36a545d9b16cf934e90bc5";
	public static final String hotelGroupCode = "HKG";
	public static final String appKey = "12528";
	public static final String SECRET = "314524d7a7442ddf3705461d7148bcd0";
    public static final String UTF8 = "UTF-8";
    public static final String FORMAT = "json";
    public static final String V = "4.0";
    /**
     * 签名
     */
    public static String sign(Map<String, String> paramValues) {
    	return sign(paramValues, null, SECRET);
    }
    
    /**
     * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
     * uppercase(hex(sha1(secretkey1value1key2value2...secret))
     *
     * @param paramValues 参数列表
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> paramValues, String secret) {
        return sign(paramValues,null,secret);
    }

    /**
     * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
     */
    private static String sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if(ignoreParamNames != null && ignoreParamNames.size() > 0){
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            sb.append(secret);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes(UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

}

