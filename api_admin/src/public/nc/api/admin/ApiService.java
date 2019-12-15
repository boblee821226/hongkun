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
	 * ��ȡ������
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
	 * ��װ�����ء�
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
	 * ҵ����
	 */
	protected Object doBusiness(RequestParamVO paramVO) throws BusinessException{

		String account = paramVO.getAccount();	// ����
		String userId = paramVO.getUserId();	// �û�id
		String billType = paramVO.getBillType();// �������ͣ����룩�������Ƕ����
		String action = paramVO.getAction();	// ���������룩
		String data = paramVO.getData();		// json���ݣ����룩
		String token = paramVO.getToken();		// ���ƣ����ã�
		String userCode = "admin";				// �û���
		String groupId = "0001N510000000000EGY";	// ����pk
		
		String[] billTypeList = billType.split(",");// �ָ�� billType����
		
		if (!ApiPubInfo.ACTION.containsKey(action)) {
			throw new BusinessException("action��������");
		}
		for (String bt : billTypeList) {
			if (!ApiPubInfo.BILLTYPE.containsKey(bt)) {
				throw new BusinessException("billtype��������");
			}
		}
		if (true) {
			// �ж��û� �Ƿ�Ϸ�,�Ϸ��󣬸�ֵ�û���
		}
		if (true) {
			// ��Ҫ��ѯ�� ��ǰ�ļ���
		}
		if (PuPubVO.getString_TrimZeroLenAsNull(account) == null) {
			account = "design";
		}
		/**
		 * NC ��֤
		 */
		InvocationInfoProxy.getInstance().setUserDataSource(account);
		InvocationInfoProxy.getInstance().setUserCode(userCode);
		InvocationInfoProxy.getInstance().setUserId(userId);
		InvocationInfoProxy.getInstance().setGroupId(groupId);
		
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
