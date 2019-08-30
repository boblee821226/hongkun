/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2012-5-2 下午04:47:10
 */
package nc.impl.ct.saledaily.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.ct.rule.MarByFinanceOrgCheckRule;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.ct.uitl.ValueUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author gaogr
 * @time 2012-5-2 下午04:47:10
 */
public class MakePaybillAction {

  /**
   * 方法功能描述：
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * <b>参数说明</b>
   * 
   * @param srcVos
   * @return <p>
   * @since 6.1
   * @author gaogr
   * @time 2012-5-2 下午04:48:14
   */
  public AggregatedValueObject[] makePaybill(AggCtSaleVO[] srcVos) {
    for (AggCtSaleVO ctsaleVo : srcVos) {
      this.checkOuVOForPaybill(ctsaleVo);
      this.filtrateBvo(ctsaleVo);

    }

    return PfServiceScmUtil.exeVOChangeByBillItfDef(
        CTBillType.SaleDaily.getCode(), IArapBillTypeCons.F2, srcVos);

  }

  private void checkOuVOForPaybill(AggCtSaleVO ctsaleVo) {
    if (ctsaleVo == null) {
      return;
    }
    CtSaleVO ctvo = ctsaleVo.getParentVO();
    CtSaleBVO[] ctbVo = ctsaleVo.getCtSaleBVO();
    String pk_group = ctvo.getPk_group();
    String financeorg = ctbVo[0].getPk_financeorg();
    Set<String> marSet = new HashSet<String>();
    String vbillcode = ctvo.getVbillcode();

    // 累计付款金额>=价税合计
    if (MathTool.compareTo(ctvo.getNtotalorigmny(), ctvo.getNorigpshamount()) <= 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4020003_0", "04020003-0030", null,
              new String[] {
                vbillcode
              })/*
                 * @res "合同号:{0}实际收款金额已达到合同价税合计,不允许再收款！"
                 */);
    }

    for (int i = 0; i < ctbVo.length; i++) {

      if (ValueUtil.isEmpty(ctbVo[i].getPk_financeorg_v())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0012")/*
                                                                     * @res
                                                                     * "此合同没有财务组织，不能生成付款单！"
                                                                     */);
      }
      if (!financeorg.equals(ctbVo[i].getPk_financeorg())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0260")/*
                                                                     * @res
                                                                     * "合同表体财务组织不一致，不支持该操作！"
                                                                     */);
      }
      marSet.add(ctbVo[i].getPk_material());
    }
    CustomerVO[] custVOs = CustomerPubService.getCustomerVO(new String[] {
      ctvo.getPk_customer()
    }, new String[] {
      "pk_customer", "pk_org", "pk_group"
    });
    for (CustomerVO custVO : custVOs) {
      if (!custVO.getPk_org().equals(financeorg)) {
        if (!custVO.getPk_org().equals(pk_group)
            && !custVO.getPk_org().equals("GLOBLE00000000000000")) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4020003_0", "04020003-0261")/*
                                                                       * @res
                                                                       * "合同表头客户为表体财务组织不可见，不支持该操作！"
                                                                       */);
        }
      }
    }
    MarByFinanceOrgCheckRule.checkMarByFinanceorg(pk_group, financeorg,
        marSet.toArray(new String[marSet.size()]));

  }

  /**
   * 方法功能描述： 过滤已经收款>价税合计的行
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * <b>参数说明</b>
   * 
   * @param ctsaleVo
   *          <p>
   * @since 6.1
   * @author gaogr
   * @time 2012-5-25 上午10:52:07
   */
  private void filtrateBvo(AggCtSaleVO ctsaleVo) {
    CtSaleBVO[] ctbVo = ctsaleVo.getCtSaleBVO();
    // 原币价税合计
    UFDouble norigtaxmny = UFDouble.ZERO_DBL;
    // 累计付款金额
    UFDouble noritotalgpmny = UFDouble.ZERO_DBL;
    ArrayList<CtSaleBVO> bvos = new ArrayList<CtSaleBVO>();
    for (int i = 0; i < ctbVo.length; i++) {
      if (ctbVo[i].getNorigtaxmny() != null) {
        norigtaxmny = ctbVo[i].getNorigtaxmny();
      }
      if (ctbVo[i].getNoritotalgpmny() != null) {
        noritotalgpmny = ctbVo[i].getNoritotalgpmny();
      }
      /**
       * HK 2018年11月26日18:28:23  
       * 累计收款金额  不为0的  就生成（之前系统的逻辑是 大于0 才生成）
       */
//      if (norigtaxmny.compareTo(noritotalgpmny) > 0) {
      if (norigtaxmny.compareTo(noritotalgpmny) != 0) {
        bvos.add(ctbVo[i]);
      }
    }

    ctsaleVo.setCtSaleBVO(bvos.toArray(new CtSaleBVO[bvos.size()]));
  }
}
