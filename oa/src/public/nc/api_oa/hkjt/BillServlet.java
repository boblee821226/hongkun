package nc.api_oa.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
			throws ServletException, IOException { // ��ѯ
		// ����ֵ
		String data = "��ѯ";
		
		// �Ӳ���
		// 1����url�Ĳ���
		Map<String, String[]> map = req.getParameterMap();
		System.out.println(map);
		// 2����body����
		String contentType = req.getContentType();
		System.out.println(contentType);
		
		if ("multipart/form-data".equals(contentType)) {
			// �ϴ��ļ�
			Object obj = req.getInputStream();
			System.out.println(obj);
		}
		else if("application/json".equals(contentType)){
			// �ϴ�����
			byte[] paramByte = readData(req.getInputStream());
			String paramStr = new String(paramByte,"UTF-8");
			System.out.println(paramStr);
		}
		
		// ����
		ServletOutputStream outputStream = resp.getOutputStream();//��ȡOutputStream�����
		resp.setHeader("content-type", "text/html;charset=UTF-8");//ͨ��������Ӧͷ�����������UTF-8�ı�����ʾ���ݣ����������仰����ô�������ʾ�Ľ�������
		byte[] dataByteArr = data.getBytes("UTF-8");//���ַ�ת�����ֽ����飬ָ����UTF-8�������ת��
		outputStream.write(dataByteArr);//ʹ��OutputStream����ͻ�������ֽ�����
		outputStream.flush();
		outputStream.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException { // ��ɾ��
		// �Ӳ���
		// 1����body����
		String contentType = req.getContentType();
		System.out.println(contentType);
		
		if (contentType.startsWith("multipart/form-data")) {// �ϴ��ļ�
		    DiskFileItemFactory factoy = new DiskFileItemFactory();
		    ServletFileUpload sfu = new ServletFileUpload(factoy);
		    //����request
		    try {
		        List<FileItem> list = sfu.parseRequest(req);
		        for (FileItem fileItem : list) {
		            fileItem.getFieldName();
		            System.out.println(fileItem.getString());
		            
		            if (fileItem.isFormField()) {// ���ֶ�
		            	String fieldValue = fileItem.getString();// ���ֶε�value
		            	String fieldKey = fileItem.getFieldName();// ���ֶε�key
		            	
		            } else { // �ļ��ֶ�
		            	String fileName = fileItem.getName();// �ļ���
		            	// ��չ��
		            	String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		    	        // ���������һ��32���ַ��� ���ļ���
		    	        String filename = UUID.randomUUID() + suffix;
		    	        // ��ȡ�ϴ����ļ���
		    	        InputStream is = fileItem.getInputStream();
		    	        // ��̬��ȡ��������·��
		    	        String serverpath = req.getServletContext().getRealPath("upload");
		    	        // ��ȡ��������д�ļ���
		    	        FileOutputStream fos = new FileOutputStream(serverpath + "/" + filename);
		    	        // ����д��
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
		else if (contentType.startsWith("application/json")) {// �ϴ�����
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
				resp.sendError(401, "����������"+ex.getMessage());
			}
		}

		// ����
		String data = "��ɾ��";
		ServletOutputStream outputStream = resp.getOutputStream();//��ȡOutputStream�����
		resp.setHeader("content-type", "text/html;charset=UTF-8");//ͨ��������Ӧͷ�����������UTF-8�ı�����ʾ���ݣ����������仰����ô�������ʾ�Ľ�������
		byte[] dataByteArr = data.getBytes("UTF-8");//���ַ�ת�����ֽ����飬ָ����UTF-8�������ת��
		outputStream.write(dataByteArr);//ʹ��OutputStream����ͻ�������ֽ�����
		outputStream.flush();
		outputStream.close();
	}
	
	/**
	 * ��ȡ���ݣ�ת��Ϊbyte[]
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
			throw new IOException("δ�����������ݣ�");
		}
		return data;
	}
	
	/**
	 * ��װ�����ء�
	 */
	protected String doAction(RequestParamVO paramVO)throws Exception{
		
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
			
		}catch(Exception ex)
		{
			resultVO.setCode(500);
			resultVO.setMsg(ex.getMessage());
		}
		return MAPPER.writeValueAsString(resultVO);
	}
	
	/**
	 * ҵ����
	 */
	protected Object doBusiness(RequestParamVO paramVO) throws BusinessException{

		String account = paramVO.getAccount();		// ���ף�����Դ��
		String userCode = paramVO.getUserCode();	// �û�code (���ݹ�������email)
		String billType = paramVO.getBillType();	// �������ͣ����룩�������Ƕ����
		String action = paramVO.getAction();		// ���������룩
		String data = paramVO.getData();			// json���ݣ����룩
		String token = paramVO.getToken();			// ���ƣ����ã�
		String group = null;						// ���ţ�ǰ̨���Դ��ݣ����ֻ��һ�����ţ����Բ��ô���
		String groupId = null;	// ����pk
		String userId = null;	// �û�id
		
		String busiDate = paramVO.getBusiDate();	// ��Ҫǰ̨������ ҵ������
		if (busiDate == null) {
			// ���ǰ̨�������͸���ǰ����
			busiDate = new UFDate().toString().substring(0,10);
		}
		
		String cacheUserId = paramVO.getCacheUserId();	// �����û�id
		if (cacheUserId == null) {
			// ���ǰ̨�������͸�Ĭ��ֵ
			cacheUserId = "NCuser00000000000001";
		}
		
		if (PuPubVO.getString_TrimZeroLenAsNull(account) == null) {
			// TODO Ĭ������ ��Ҫ�޸�
			account = "design";
		}
		
		Long busiDate_Long = PuPubVO.getUFDate(busiDate).getMillis();
		
		String[] billTypeList = billType.split(",");// �ָ�� billType����
		
		if (!ApiPubInfo.ACTION.containsKey(action)) {
			throw new BusinessException("action��������");
		}
		for (String bt : billTypeList) {
			// ��-��ȡ��ֻȡ�������͡�
			String[] btSplit = bt.split("-");
			if (!ApiPubInfo.BILLTYPE.containsKey(btSplit[0])) {
				throw new BusinessException("billtype��������");
			}
		}
		/**
		 * ��ʼ������
		 * �û����û�����NC����У�� �Ϸ��ԣ�ֻҪ����ֵ���У�
		 */
		if (ApiPubInfo.CACHE_USER == null
		|| ApiPubInfo.CACHE_GROUP == null
		|| ApiPubInfo.CACHE_DOC == null
		|| !ApiPubInfo.CACHE_USER.containsKey(account)
		|| !ApiPubInfo.CACHE_GROUP.containsKey(account)
		|| !ApiPubInfo.CACHE_DOC.containsKey(account)
		) {
			this.doNcBusiness(account,
					null,	// ��ʼ�����������ÿա�
					ApiPubInfo.BILLTYPE_INIT,
					userCode,
					cacheUserId,
					groupId,
					busiDate_Long,
					data,
					token);
		}
		/**
		 * ��飺�û�
		 */
		if (ApiPubInfo.CACHE_USER == null 
		|| !ApiPubInfo.CACHE_USER.containsKey(account)
		|| !ApiPubInfo.CACHE_USER.get(account).containsKey(userCode)) {
			throw new BusinessException("�û����Ϸ�");
		} else {
			userId = ApiPubInfo.CACHE_USER.get(account).get(userCode);
		}
		/**
		 * ��飺����
		 */
		if (ApiPubInfo.CACHE_GROUP == null 
		|| !ApiPubInfo.CACHE_GROUP.containsKey(account)
		|| ApiPubInfo.CACHE_GROUP.get(account).size() <= 0 
		) {
			throw new BusinessException("����δ�鵽");
		} else if (ApiPubInfo.CACHE_GROUP.get(account).size() == 1 ){
			groupId = ApiPubInfo.CACHE_GROUP.get(account).values().toArray(new String[0])[0];
		} else if (group != null) {
			groupId = ApiPubInfo.CACHE_GROUP.get(account).get(group);
		} else {
			throw new BusinessException("�봫�뼯�Ų���");
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
	 * ���ýӿ�
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
		 * NC ��֤
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
			if( paramData !=null
			&& !paramData.equals("null")
			&&  paramData.trim().length()>0
			){
				if (action != null) {
					if (ApiPubInfo.ACTION_WRITE.equals(action)) { // ����� ��Ҫ���ݵ������ͣ�ȡ�ҵ�VO
						String[] bt = billType.split("-"); // ��������������263X-Cxx-01��ֻȡǰ��ĵ������ͱ��������жϡ�
						dataObj = MAPPER.readValue(paramData, ApiPubInfo.ACTION.get(action + "#" + bt[0]).getParamClass());
					} else { // �����ģ����ǹ̶�VO
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
							account,	//����
							userId,		//�û�
							billType,	//����
							action,		//����
							dataObj,	//���ݣ�������
							token,		//����
							null,		//��¼VO
							null		//����
					);
		
		return result;
	}
	
}
