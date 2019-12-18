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
	 * ҵ����
	 */
	protected Object doBusiness(RequestParamVO paramVO) throws BusinessException{

		String account = paramVO.getAccount();	// ���ף�����Դ��
		String userCode = paramVO.getUserCode();	// �û�code (���ݹ�������email)
		String billType = paramVO.getBillType();// �������ͣ����룩�������Ƕ����
		String action = paramVO.getAction();	// ���������룩
		String data = paramVO.getData();		// json���ݣ����룩
		String token = paramVO.getToken();		// ���ƣ����ã�
		String group = null;					// ���ţ�ǰ̨���Դ��ݣ����ֻ��һ�����ţ����Բ��ô���
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
		
		Long busiDate_Long = PuPubVO.getUFDate(busiDate).getMillis();
		
		String[] billTypeList = billType.split(",");// �ָ�� billType����
		
		if (!ApiPubInfo.ACTION.containsKey(action)) {
			throw new BusinessException("action��������");
		}
		for (String bt : billTypeList) {
			if (!ApiPubInfo.BILLTYPE.containsKey(bt)) {
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
			throw new BusinessException("�û����Ϸ�");
		} else {
			userId = ApiPubInfo.CACHE_USER.get(account).get(userCode);
		}
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
