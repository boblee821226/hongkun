package nc.api.admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api.admin.itf.ApiBusinessItf;
import nc.api.admin.itf.ApiPubInfo;
import nc.api.admin.tool.PuPubVO;
import nc.api.admin.tool.SecurityMocker;
import nc.api.admin.vo.RequestParamVO;
import nc.api.admin.vo.ResponseResultVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

import org.codehaus.jackson.map.ObjectMapper;

@SuppressWarnings("restriction")
public class ApiService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7680969130982863754L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获得请求的uri
		String requesturi = request.getRequestURI();
		//获得远程客户端地址
		String remoteHost = request.getRemoteHost();
		//参数类型
		String contentType = request.getContentType();

		if("application/json".equals(contentType)){
			try
			{
				RequestParamVO paramVO = null;
				ObjectMapper mapper = new ObjectMapper();
				
				byte[] paramByte = readData(request.getInputStream());
				String paramStr = new String(paramByte,"UTF-8");
				paramVO = mapper.readValue(paramStr,RequestParamVO.class);
				
				String resultStr = doAction(paramVO);
				
				OutputStream os = response.getOutputStream();
				os.write(resultStr.getBytes("UTF-8"));
				os.flush();
				os.close();
				
			}catch(Exception ex)
			{
				response.sendError(401, "服务器错误："+ex.getMessage());
			}
		}
		else{
			response.sendError(401, "Content-Type错误，应该为application/json");
		}
	}
	
	/**
	 * 读取输入流
	 */
	protected byte[] readData(InputStream is)throws Exception{

		ByteArrayOutputStream bos = new ByteArrayOutputStream();  

		byte[] buffer = new byte[1024*1024];  
		int len;  
		while((len = is.read(buffer)) != -1 ) {  
			bos.write(buffer, 0, len);  
		}  
		byte[] data = bos.toByteArray();  

		if(data==null||data.length==0){
			throw new Exception("未发送请求数据！");
		}
		return data;
	}
	
	/**
	 * 封装，返回。
	 */
	protected String doAction(RequestParamVO paramVO)throws Exception{
		
		ResponseResultVO resultVO = new ResponseResultVO();
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			Object resultDataVO = doBusiness(paramVO);
			if(resultDataVO!=null){
				String resultDataJson = mapper.writeValueAsString(resultDataVO);
				resultVO.setData(resultDataJson);
			}
			resultVO.setCode(200);
			resultVO.setMsg("ok");
			
		}catch(Exception ex)
		{
			resultVO.setCode(10000);
			resultVO.setMsg(ex.getMessage());
		}
		return mapper.writeValueAsString(resultVO);
	}
	
	/**
	 * 业务处理
	 */
	protected Object doBusiness(RequestParamVO paramVO) throws BusinessException{

		String account = paramVO.getAccount();	// 账套（数据源）
		String userCode = paramVO.getUserCode();	// 用户code (传递过来的是email)
		String billType = paramVO.getBillType();// 单据类型（必须）（可能是多个）
		String action = paramVO.getAction();	// 动作（必须）
		String data = paramVO.getData();		// json数据（必须）
		String token = paramVO.getToken();		// 令牌（备用）
		String group = null;					// 集团（前台可以传递，如果只有一个集团，可以不用传）
		String groupId = null;	// 集团pk
		String userId = null;	// 用户id
		
		String busiDate = paramVO.getBusiDate();	// 需要前台传过来 业务日期
		if (busiDate == null) {
			// 如果前台不传，就赋当前日期
			busiDate = new UFDate().toString().substring(0,10);
		}
		
		String cacheUserId = paramVO.getCacheUserId();	// 缓存用户id
		if (cacheUserId == null) {
			// 如果前台不传，就赋默认值
			cacheUserId = "NCuser00000000000001";
		}
		
		Long busiDate_Long = PuPubVO.getUFDate(busiDate).getMillis();
		
		String[] billTypeList = billType.split(",");// 分割成 billType数组
		
		if (!ApiPubInfo.ACTION.containsKey(action)) {
			throw new BusinessException("action参数错误");
		}
		for (String bt : billTypeList) {
			if (!ApiPubInfo.BILLTYPE.containsKey(bt)) {
				throw new BusinessException("billtype参数错误");
			}
		}
		/**
		 * 初始化缓存
		 * 用缓存用户。（NC不会校验 合法性，只要是有值就行）
		 */
		if (ApiPubInfo.CACHE_USER == null
		|| ApiPubInfo.CACHE_GROUP == null
		|| ApiPubInfo.CACHE_DOC == null
		|| !ApiPubInfo.CACHE_USER.containsKey(account)
		|| !ApiPubInfo.CACHE_GROUP.containsKey(account)
		|| !ApiPubInfo.CACHE_DOC.containsKey(account)
		) {
			this.doNcBusiness(account,
					action,
					ApiPubInfo.BILLTYPE_INIT,
					userCode,
					cacheUserId,
					groupId,
					busiDate_Long,
					data,
					token);
		}
		
		if (ApiPubInfo.CACHE_USER == null 
		|| !ApiPubInfo.CACHE_USER.containsKey(account)
		|| !ApiPubInfo.CACHE_USER.get(account).containsKey(userCode)) {
			throw new BusinessException("用户不合法");
		} else {
			userId = ApiPubInfo.CACHE_USER.get(account).get(userCode);
		}
		if (ApiPubInfo.CACHE_GROUP == null 
		|| !ApiPubInfo.CACHE_GROUP.containsKey(account)
		|| ApiPubInfo.CACHE_GROUP.get(account).size() <= 0 
		) {
			throw new BusinessException("集团未查到");
		} else if (ApiPubInfo.CACHE_GROUP.get(account).size() == 1 ){
			groupId = ApiPubInfo.CACHE_GROUP.get(account).values().toArray(new String[0])[0];
		} else if (group != null) {
			groupId = ApiPubInfo.CACHE_GROUP.get(account).get(group);
		} else {
			throw new BusinessException("请传入集团参数");
		}
		
		if (PuPubVO.getString_TrimZeroLenAsNull(account) == null) {
			account = "design";
		}
		
		return this.doNcBusiness(
				account,
				action,
				billType,
				userCode,
				userId,
				groupId,
				busiDate_Long,
				data, token
				);
		
	}
	
	/**
	 * 调用接口
	 */
	private Object doNcBusiness(
			String account,
			String action,
			String billType,
			String userCode,
			String userId,
			String groupId,
			Long busiDate_Long,
			String paramData,
			String token
	) throws BusinessException {
		/**
		 * NC 验证
		 */
		InvocationInfoProxy.getInstance().setUserDataSource(account);
		InvocationInfoProxy.getInstance().setUserCode(userCode);
		InvocationInfoProxy.getInstance().setUserId(userId);
		InvocationInfoProxy.getInstance().setGroupId(groupId);
		InvocationInfoProxy.getInstance().setBizDateTime(busiDate_Long);
		
		SecurityMocker usm = new SecurityMocker();
		usm.ensureSecurity();

		Object dataObj = null;

		try
		{
			if( paramData !=null
			&& !paramData.equals("null")
			&&  paramData.trim().length()>0
			){
				dataObj = new ObjectMapper().readValue(paramData, ApiPubInfo.ACTION.get(action).getParamClass());
			}
			
		}catch(Exception ex)
		{
			throw new BusinessException(ex);
		}

		ApiBusinessItf itf = (ApiBusinessItf)NCLocator.getInstance().lookup(ApiBusinessItf.class.getName());
		
		Object result = itf.doBusiness(
							account,	//账套
							userId,		//用户
							billType,	//类型
							action,		//动作
							dataObj,	//数据（参数）
							token,		//令牌
							null,		//登录VO
							null		//其它
					);
		
		return result;
	}
	
}
