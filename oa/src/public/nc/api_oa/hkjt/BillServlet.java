package nc.api_oa.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api_oa.hkjt.itf.ApiBusinessItf;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.tool.SecurityMocker;
import nc.api_oa.hkjt.vo.RequestParamVO;
import nc.api_oa.hkjt.vo.ResponseResultVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.uap.lock.PKLock;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.codehaus.jackson.map.ObjectMapper;

@MultipartConfig
@SuppressWarnings("restriction")
public class BillServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5502958531339801786L;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { // 查询
		// 返回值
		String data = "查询";
		
		// 接参数
		// 1、接url的参数
		Map<String, String[]> map = req.getParameterMap();
		System.out.println(map);
		// 2、接body参数
		String contentType = req.getContentType();
		System.out.println(contentType);
		
		if ("multipart/form-data".equals(contentType)) {
			// 上传文件
			Object obj = req.getInputStream();
			System.out.println(obj);
		}
		else if("application/json".equals(contentType)){
			// 上传单据
			byte[] paramByte = readData(req.getInputStream());
			String paramStr = new String(paramByte,"UTF-8");
			System.out.println(paramStr);
		}
		
		// 返回
		ServletOutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
		resp.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
		outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
		outputStream.flush();
		outputStream.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { // 增删改
		// 接参数
		// 1、接body参数
		String contentType = req.getContentType();
		System.out.println(contentType);
		
		if (contentType.startsWith("multipart/form-data")) {// 接收上传的文件
		    DiskFileItemFactory factoy = new DiskFileItemFactory();
		    ServletFileUpload sfu = new ServletFileUpload(factoy);
		    //解析request
		    try {
		        List<FileItem> list = sfu.parseRequest(req);
		        for (FileItem fileItem : list) {
		            fileItem.getFieldName();
		            System.out.println(fileItem.getString());
		            
		            if (fileItem.isFormField()) {// 表单字段
		            	String fieldValue = fileItem.getString();// 表单字段的value
		            	String fieldKey = fileItem.getFieldName();// 表单字段的key
		            	
		            } else { // 文件字段
		            	String fileName = fileItem.getName();// 文件名
		            	// 扩展名
		            	String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		    	        // 随机的生存一个32的字符串 的文件名
		    	        String filename = UUID.randomUUID() + suffix;
		    	        // 获取上传的文件流
		    	        InputStream is = fileItem.getInputStream();
		    	        // 动态获取服务器的路径
		    	        String serverpath = req.getServletContext().getRealPath("upload");
		    	        // 获取服务器的写文件流
		    	        FileOutputStream fos = new FileOutputStream(serverpath + "/" + filename);
		    	        // 进行写入
		    	        byte[] bty = new byte[1024];
		    	        int length =0;
		    	        while((length=is.read(bty))!=-1){
		    	            fos.write(bty,0,length);
		    	        }
		    	        fos.close();
		    	        is.close();
		            }
		        }

		    } catch (FileUploadException e) {
		        e.printStackTrace();
		        resp.sendError(400, e.getMessage());
		    }
			
		}
		else if (contentType.startsWith("application/json")) {// 接收上传的单据
			try
			{
				RequestParamVO paramVO = null;
				
				byte[] paramByte = readData(req.getInputStream());
				String paramStr = new String(paramByte,"UTF-8");
				paramVO = MAPPER.readValue(paramStr,RequestParamVO.class);
				
				String resultStr = doAction(paramVO);
				
				resp.setContentType("application/json");
				OutputStream os = resp.getOutputStream();
				os.write(resultStr.getBytes("UTF-8"));
				os.flush();
				os.close();
				
			}catch(Exception ex)
			{
				resp.sendError(401, "服务器错误："+ex.getMessage());
			}
		}

		// 返回
//		String data = "增删改";
//		ServletOutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
//		resp.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
//		byte[] dataByteArr = data.getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
//		outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
//		outputStream.flush();
//		outputStream.close();
	}
	
	/**
	 * 读取数据，转换为byte[]
	 * @throws IOException 
	 */
	protected byte[] readData(InputStream is) throws IOException{

		ByteArrayOutputStream bos = new ByteArrayOutputStream();  

		byte[] buffer = new byte[1024*1024];  
		int len;  
		while((len = is.read(buffer)) != -1 ) {  
			bos.write(buffer, 0, len);  
		}  
		byte[] data = bos.toByteArray();  

		if(data==null || data.length == 0){
			throw new IOException("未发送请求数据！");
		}
		return data;
	}
	
	/**
	 * 封装，返回。
	 */
	protected String doAction(RequestParamVO paramVO) throws Exception{
		
		ResponseResultVO resultVO = new ResponseResultVO();
		
		try
		{
			Object resultDataVO = doBusiness(paramVO);
			if(resultDataVO!=null){
				String resultDataJson = MAPPER.writeValueAsString(resultDataVO);
				resultVO.setData(resultDataJson);
			}
			resultVO.setCode(200);
			resultVO.setMsg("ok");
			
		} catch(Exception ex)
		{
			resultVO.setCode(500);
			resultVO.setMsg(ex.getMessage());
		} finally {
			// 接口执行完毕后：释放锁
			PKLock.getInstance().releaseDynamicLocks();
		}
		return MAPPER.writeValueAsString(resultVO);
	}
	
	/**
	 * 业务处理
	 */
	protected Object doBusiness(RequestParamVO paramVO) throws BusinessException{

		String account = paramVO.getAccount();		// 账套（数据源）
		String userCode = paramVO.getUserCode();	// 用户code (传递过来的是email)
		String billType = paramVO.getBillType();	// 单据类型（必须）（可能是多个）
		String action = paramVO.getAction();		// 动作（必须）
		String data = paramVO.getData();			// json数据（必须）
		String token = paramVO.getToken();			// 令牌（备用）
		String group = null;						// 集团（前台可以传递，如果只有一个集团，可以不用传）
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
			cacheUserId = ApiPubInfo.USER;
		}
		
		if (PuPubVO.getString_TrimZeroLenAsNull(account) == null) {
			// TODO 默认账套 需要修改
			account = "design";
		}
		
		Long busiDate_Long = PuPubVO.getUFDate(busiDate).getMillis();
		
		String[] billTypeList = billType.split(",");// 分割成 billType数组
		
		if (!ApiPubInfo.ACTION.containsKey(action)) {
			throw new BusinessException("action参数错误");
		}
		for (String bt : billTypeList) {
			// 按-截取，只取单据类型。
			String[] btSplit = bt.split("-");
			if (!ApiPubInfo.BILLTYPE.containsKey(btSplit[0])) {
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
					null,	// 初始化，将动作置空。
					ApiPubInfo.BILLTYPE_INIT,
					userCode,
					cacheUserId,
					groupId,
					busiDate_Long,
					data,
					token);
		}
		/**
		 * 检查：用户
		 */
		if ("NC".equals(userCode)) {
			userId = ApiPubInfo.USER;
		} else if (ApiPubInfo.CACHE_USER == null 
		|| !ApiPubInfo.CACHE_USER.containsKey(account)
		|| !ApiPubInfo.CACHE_USER.get(account).containsKey(userCode)) {
			throw new BusinessException("用户不合法");
		} else {
			userId = ApiPubInfo.CACHE_USER.get(account).get(userCode);
		}
		/**
		 * 检查：集团
		 */
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
		
		return this.doNcBusiness(
				account,
				action,
				billType,
				userCode,
				userId,
				groupId,
				busiDate_Long,
				data,
				token
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
//		InvocationInfoProxy.getInstance().setUserCode("gsl");
//		InvocationInfoProxy.getInstance().setUserId("1001N51000000003QLYF");
		InvocationInfoProxy.getInstance().setUserCode(userCode);
		InvocationInfoProxy.getInstance().setUserId(userId);
		InvocationInfoProxy.getInstance().setGroupId(groupId);
		InvocationInfoProxy.getInstance().setBizDateTime(busiDate_Long);
		
		SecurityMocker usm = new SecurityMocker();
		usm.ensureSecurity();

		Object dataObj = null;

		try
		{
			/**
			 * TEST、INIT、DOC 类型的，传参设置为空
			 */
			if(ApiPubInfo.BILLTYPE_TEST.equals(billType)) {
				paramData = null;
			} else if(ApiPubInfo.BILLTYPE_INIT.equals(billType)) {
				paramData = null;
			} else if(ApiPubInfo.BILLTYPE_DOC.equals(billType)) {
				paramData = null;
			}
			/***END***/
			
			if( paramData !=null
			&& !paramData.equals("null")
			&&  paramData.trim().length()>0
			){
				if (action != null) {
					if (ApiPubInfo.ACTION_WRITE.equals(action)
					 || ApiPubInfo.ACTION_DELETE.equals(action)
					 || ApiPubInfo.ACTION_QUERY.equals(action)
					) { // 保存、删除的 需要根据单据类型，取找到VO
						if (billType.startsWith("OA-")) {
							dataObj = MAPPER.readValue(paramData, HashMap[].class);
						} else {
							String[] bt = billType.split("-"); // 交易类型类似于263X-Cxx-01，只取前面的单据类型编码来做判断。
							dataObj = MAPPER.readValue(paramData, ApiPubInfo.ACTION.get(action + "#" + bt[0]).getParamClass());
						}
					} else { // 其它的，都是固定VO
						dataObj = MAPPER.readValue(paramData, ApiPubInfo.ACTION.get(action).getParamClass());
					}
				}
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
