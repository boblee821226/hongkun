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

import org.codehaus.jackson.map.ObjectMapper;

public class ApiService extends HttpServlet {

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
			resultVO.setStatus("S");
			
		}catch(Exception ex)
		{
			resultVO.setStatus("F");
			resultVO.setMessage(ex.getMessage());
		}
		return mapper.writeValueAsString(resultVO);
	}
	
	/**
	 * 业务处理
	 */
	protected Object doBusiness(RequestParamVO paramVO) throws BusinessException{

		String account = paramVO.getAccount();	// 账套
		String userId = paramVO.getUserId();	// 用户id
		String billType = paramVO.getBillType();// 单据类型（必须）（可能是多个）
		String action = paramVO.getAction();	// 动作（必须）
		String data = paramVO.getData();		// json数据（必须）
		String token = paramVO.getToken();		// 令牌（备用）
		String userCode = "admin";				// 用户名
		String groupId = "0001N510000000000EGY";	// 集团pk
		
		String[] billTypeList = billType.split(",");// 分割成 billType数组
		
		if (!ApiPubInfo.ACTION.containsKey(action)) {
			throw new BusinessException("action参数错误");
		}
		for (String bt : billTypeList) {
			if (!ApiPubInfo.BILLTYPE.containsKey(bt)) {
				throw new BusinessException("billtype参数错误");
			}
		}
		if (true) {
			// 判断用户 是否合法,合法后，赋值用户名
		}
		if (true) {
			// 需要查询出 当前的集团
		}
		if (PuPubVO.getString_TrimZeroLenAsNull(account) == null) {
			account = "design";
		}
		/**
		 * NC 验证
		 */
		InvocationInfoProxy.getInstance().setUserDataSource(account);
		InvocationInfoProxy.getInstance().setUserCode(userCode);
		InvocationInfoProxy.getInstance().setUserId(userId);
		InvocationInfoProxy.getInstance().setGroupId(groupId);
//		InvocationInfoProxy.getInstance().setBizDateTime(bizDateTime);
		
		SecurityMocker usm = new SecurityMocker();
		usm.ensureSecurity();

		Object dataObj = null;

		try
		{
			if( paramVO.getData()!=null
			&& !paramVO.getData().equals("null")
			&&  paramVO.getData().trim().length()>0
			){
				dataObj = new ObjectMapper().readValue(data, ApiPubInfo.ACTION.get(action).getParamClass());
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
