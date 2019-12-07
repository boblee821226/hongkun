package nc.api.hkjt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nc.api.hkjt.itf.ApiBusinessItf;
import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.tool.SecurityMocker;
import nc.api.hkjt.vo.LoginVO;
import nc.api.hkjt.vo.RequestParamVO;
import nc.api.hkjt.vo.ResponseResultVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.vo.pub.BusinessException;

import org.codehaus.jackson.map.ObjectMapper;

public class ApiService extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��������uri
		String requesturi = request.getRequestURI();
		//���Զ�̿ͻ��˵�ַ
		String remoteHost = request.getRemoteHost();
		//��������
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
				response.sendError(401, "����������"+ex.getMessage());
			}
		}
		else{
			response.sendError(401, "Content-Type����Ӧ��Ϊapplication/json");
		}
	}
	
	/**
	 * 
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
			throw new Exception("δ�����������ݣ�");
		}
		return data;
	}
	
	/**
	 * 
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
	 * 
	 */
	protected Object doBusiness(RequestParamVO paramVO) throws BusinessException{

		/**
		 * NC ��֤
		 */
		InvocationInfoProxy.getInstance().setUserDataSource("NC65");
		InvocationInfoProxy.getInstance().setUserCode("htjt");
		
//		InvocationInfoProxy.getInstance().setBizCenterCode("hkjt");
//		InvocationInfoProxy.getInstance().setUserId("1001N51000000002G8PK");
//		InvocationInfoProxy.getInstance().setGroupId("0001N510000000000EGY");

		SecurityMocker usm = new SecurityMocker();
		usm.ensureSecurity();
		
		/**
		 * ֮ǰ��Ҫ������ �Ƿ�Ϊ��
		 */
		
		/**
		 * ������ƵĺϷ��ԣ����� �û���Ϣ
		 */
		String userId = null;
		
		/**
		 * ��Ҫ�������ձ�������+����  ��Ӧ�� data��Class ��ʲô��
		 */
		LoginVO tokenVO = null;
		Object data = null;

		String mapperKey = paramVO.getBilltype()+"_"+paramVO.getAction();
		
		/**
		 * ���� ����+���� �Ƿ���Ҫ����
		 */
		if(!ApiPubInfo.NO_TOKEN.containsKey(mapperKey)){
			if(paramVO.getToken()==null)
			{
				throw new BusinessException("���봫������");
			}
			
			tokenVO = ApiPubInfo.check_TOKEN(paramVO.getToken());
			
			if(tokenVO==null)
			{
				throw new BusinessException("���Ʋ��Ϸ�");
			}
			
		}
		/***END***/
		
		Class c = ApiPubInfo.MAPPER.get(mapperKey);
		if(c==null){
			throw new BusinessException("���������޷����ܵ�����["+paramVO.getBilltype()+"]�Ͷ���["+paramVO.getAction()+"]");
		}
		
		try
		{
			if( paramVO.getData()!=null
			&& !paramVO.getData().equals("null")
			&&  paramVO.getData().trim().length()>0
			){
				data = new ObjectMapper().readValue(paramVO.getData(),c);
			}
			
		}catch(Exception ex)
		{
			throw new BusinessException(ex);
		}

		ApiBusinessItf itf = (ApiBusinessItf)NCLocator.getInstance().lookup(ApiBusinessItf.class.getName());
		
		Object result = itf.doBusiness(
							paramVO.getAccount(),	//����
							userId,					//�û�
							paramVO.getBilltype(),	//����
							paramVO.getAction(),	//����
							data,					//����
							paramVO.getToken(),		//����
							tokenVO,				//��¼VO
							null					//����
					);
		
		return result;
		
	}
	
}
