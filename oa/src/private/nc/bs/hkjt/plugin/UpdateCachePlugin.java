package nc.bs.hkjt.plugin;

import java.io.IOException;

import hd.vo.pub.tools.PuPubVO;
import nc.api_oa.hkjt.vo.RequestParamVO;
import nc.api_oa.hkjt.vo.ResponseResultVO;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.uap.lock.PKLock;
import nc.vo.hkjt.store.lvyun.in.HttpUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pa.CurrEnvVO;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class UpdateCachePlugin implements IBackgroundWorkPlugin {

	private static String Plugin_Key = "Plugin_HKJT_updateCache";	// 后台任务的标识
	private static ObjectMapper JSON = new ObjectMapper();
	private static String zyURL = "/service/~zyhk/nc.api.zhiyun.ApiServlet";
	private static String oaURL = "/service/~hkjt/nc.api_oa.hkjt.BillServlet";
	@Override
	public PreAlertObject executeTask(BgWorkingContext context)
			throws BusinessException {
		
		boolean lock=PKLock.getInstance().addBatchDynamicLock(new String[]{Plugin_Key});
		if(!lock){
			throw new BusinessException("事务正在处理中,不能重复操作！");
		}
		
		try {
			UFBoolean isZy = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isZy"), UFBoolean.FALSE);
			UFBoolean isOa = PuPubVO.getUFBoolean_NullAs(context.getKeyMap().get("isOa"), UFBoolean.FALSE);
			String urlStr = PuPubVO.getString_TrimZeroLenAsNull(context.getKeyMap().get("url"));
			String[] urlList = urlStr.split("、");
			String errorMsg = "";
			
			if (isZy.booleanValue()) {
				for (String url : urlList) {
					String fillUrl = url + zyURL;
					String resMsg = send (fillUrl);
					errorMsg += resMsg;
				}
			}
			if (isOa.booleanValue()) {
				for (String url : urlList) {
					String fillUrl = url + oaURL;
					String resMsg = send (fillUrl);
					errorMsg += resMsg;
				}
			}
			
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				throw new BusinessException(errorMsg);
			}
			
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
		return null;
	}
	
	private static String send(String url) throws IOException,
			JsonGenerationException, JsonMappingException, BusinessException,
			JsonParseException {
		RequestParamVO paramVO = new RequestParamVO();
		paramVO.setAccount("NC65");
		paramVO.setUserCode("NC");
		paramVO.setBillType("INIT");
		paramVO.setAction("WRITE");
		String sendStr = JSON.writeValueAsString(paramVO);
		
		String resStr = HttpUtil.doPost(url, sendStr);
		ResponseResultVO res = JSON.readValue(resStr, ResponseResultVO.class);
		if (res.getCode() != 200) {
			return url;
		}
		return "";
	}

	public Object test (Object obj) throws BusinessException {
		CurrEnvVO context = new CurrEnvVO();
		context.getKeyMap().put("isOa", UFBoolean.TRUE);
//		context.getKeyMap().put("isZy", UFBoolean.TRUE);
//		context.getKeyMap().put("url", "http://10.0.0.50:9081、http://10.0.0.50:9082、http://10.0.0.50:9083、http://10.0.0.50:9084、http://10.0.0.50:9085、http://10.0.0.50:9086");
		context.getKeyMap().put("url", "http://boblee001:8080");
		this.executeTask(context);
		return "ok";
	}
}
