package nc.pubitf.ic.m4455;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.m4455.entity.SapplyBillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ⵥ��ѯ�������뵥�ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author jinjya
 * @time 2010-12-17 ����02:10:13
 */
public interface ISapplyBillQueryForOutBill {

  /**
   * �����������������ϳ���ѯ�������뵥�ӿ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param queryScheme
   *          ��ѯ����
   * @return ��ѯ���VO
   * @throws BusinessException
   *           ҵ���쳣������
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-17 ����02:11:06
   */
  public SapplyBillVO[] queryBillFor4D(IQueryScheme queryScheme) throws BusinessException;

  /**
   * ���������������������ѯ�������뵥�ӿ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param queryScheme
   *          ��ѯ����
   * @return ��ѯ���VO
   * @throws BusinessException
   *           ҵ���쳣������
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-27 ����02:18:07
   */
  public SapplyBillVO[] queryBillFor4H(IQueryScheme queryScheme) throws BusinessException;

  /**
   * ���������������������ⵥ��ѯ�������뵥�ӿ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param queryScheme
   *          ��ѯ����
   * @return ��ѯ���VO
   * @throws BusinessException
   *           ҵ���쳣������
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-27 ����02:17:42
   */
  public SapplyBillVO[] queryBillFor4I(IQueryScheme queryScheme) throws BusinessException;

  /**
   * �����������������ϵ���ѯ�������뵥�ӿ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param queryScheme
   *          ��ѯ����
   * @return ��ѯ���VO
   * @throws BusinessException
   *           ҵ���쳣������
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-27 ����02:18:21
   */
  public SapplyBillVO[] queryBillFor4O(IQueryScheme queryScheme) throws BusinessException;
  
  /**
   * HK 2020��9��28��18:19:14
   * ת���ѯ��������Ľӿ�
   */
  public SapplyBillVO[] queryBillFor4K(IQueryScheme queryScheme) throws BusinessException;
  
}
