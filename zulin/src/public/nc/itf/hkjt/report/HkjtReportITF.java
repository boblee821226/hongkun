package nc.itf.hkjt.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.data.DataSet;
import nc.vo.ic.m4d.entity.MaterialOutVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.sf.allocateapply.AggAllocateApplyVO;

/**
 * ����ӿڡ��Լ��������߰���Ľӿ� ���ڴ�ʵ�֡�
 */
public interface HkjtReportITF {

	/**
	 * ���б����ѯ��ͳһ��ڷ��� 
	 */
	public DataSet queryReport(
			SmartContext context,
			HashMap<String, Object> param,
			String type,
			String flag,
			Object other
	) throws Exception;

	/**
	 * �ֽ����-����  �����ʼ�
	 */
	public Object sendMail(Object data,Object other)
	throws BusinessException;
	
	/**
	 * ���ݷ�ƱVO���ж��Ƿ�Ӧ������ ���������
	 * ��Ʊ��� �� ����� ��һ�£����ң��ڼ��г��������£���Ҫ���ɵ�������
	 */
	public Object genCktzdByPoInvoice(InvoiceVO[] poInvoiceVOs, Object ohter) 
	throws BusinessException;
	/**
	 * ���ݷ�ƱVO���ж��Ƿ�Ӧ������ ԭ��ԭֵ������
	 * ���㵥 ��һ�£���������Ϊ �̶��ʲ����ԣ�����Ҫ����
	 */
	public Object genYbyztzByPoInvoice(InvoiceVO[] poInvoiceVOs, Object ohter) 
			throws BusinessException;
	
	/**
	 * ���� ���㵥������ �²�����
	 */
	public Object genXbsqByJsd(ArrayList<Object[]> list, Object other) throws BusinessException;
	/**
	 * ɾ�� �²�����ʱ����ԭ ���㵥��״̬
	 */
	public Object delXbsqBackJsd(AggAllocateApplyVO billVO, Object other) throws BusinessException;
	/**
	 * ����ת��
	 */
	public Object ckZhuanGu(MaterialOutVO outVO, Map<String, Object> other) 
			throws BusinessException;
}
