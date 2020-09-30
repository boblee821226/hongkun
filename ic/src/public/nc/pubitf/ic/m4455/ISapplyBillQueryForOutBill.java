package nc.pubitf.ic.m4455;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.m4455.entity.SapplyBillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>出库单查询出库申请单接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author jinjya
 * @time 2010-12-17 下午02:10:13
 */
public interface ISapplyBillQueryForOutBill {

  /**
   * 方法功能描述：材料出查询出库申请单接口
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          查询方案
   * @return 查询结果VO
   * @throws BusinessException
   *           业处异常处理类
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-17 下午02:11:06
   */
  public SapplyBillVO[] queryBillFor4D(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 方法功能描述：借出单查询出库申请单接口
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          查询方案
   * @return 查询结果VO
   * @throws BusinessException
   *           业处异常处理类
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-27 下午02:18:07
   */
  public SapplyBillVO[] queryBillFor4H(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 方法功能描述：其它出库单查询出库申请单接口
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          查询方案
   * @return 查询结果VO
   * @throws BusinessException
   *           业处异常处理类
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-27 下午02:17:42
   */
  public SapplyBillVO[] queryBillFor4I(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 方法功能描述：报废单查询出库申请单接口
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          查询方案
   * @return 查询结果VO
   * @throws BusinessException
   *           业处异常处理类
   *           <p>
   * @since 6.0
   * @author jinjya
   * @time 2010-12-27 下午02:18:21
   */
  public SapplyBillVO[] queryBillFor4O(IQueryScheme queryScheme) throws BusinessException;
  
  /**
   * HK 2020年9月28日18:19:14
   * 转库查询出库申请的接口
   */
  public SapplyBillVO[] queryBillFor4K(IQueryScheme queryScheme) throws BusinessException;
  
}
