/**
 * $�ļ�˵��$
 * 
 * @author gaogr
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2012-5-2 ����04:47:10
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author gaogr
 * @time 2012-5-2 ����04:47:10
 */
public class MakePaybillAction {

  /**
   * ��������������
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * <b>����˵��</b>
   * 
   * @param srcVos
   * @return <p>
   * @since 6.1
   * @author gaogr
   * @time 2012-5-2 ����04:48:14
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

    // �ۼƸ�����>=��˰�ϼ�
    if (MathTool.compareTo(ctvo.getNtotalorigmny(), ctvo.getNorigpshamount()) <= 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4020003_0", "04020003-0030", null,
              new String[] {
                vbillcode
              })/*
                 * @res "��ͬ��:{0}ʵ���տ����Ѵﵽ��ͬ��˰�ϼ�,���������տ"
                 */);
    }

    for (int i = 0; i < ctbVo.length; i++) {

      if (ValueUtil.isEmpty(ctbVo[i].getPk_financeorg_v())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0012")/*
                                                                     * @res
                                                                     * "�˺�ͬû�в�����֯���������ɸ����"
                                                                     */);
      }
      if (!financeorg.equals(ctbVo[i].getPk_financeorg())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0260")/*
                                                                     * @res
                                                                     * "��ͬ���������֯��һ�£���֧�ָò�����"
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
                                                                       * "��ͬ��ͷ�ͻ�Ϊ���������֯���ɼ�����֧�ָò�����"
                                                                       */);
        }
      }
    }
    MarByFinanceOrgCheckRule.checkMarByFinanceorg(pk_group, financeorg,
        marSet.toArray(new String[marSet.size()]));

  }

  /**
   * �������������� �����Ѿ��տ�>��˰�ϼƵ���
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * <b>����˵��</b>
   * 
   * @param ctsaleVo
   *          <p>
   * @since 6.1
   * @author gaogr
   * @time 2012-5-25 ����10:52:07
   */
  private void filtrateBvo(AggCtSaleVO ctsaleVo) {
    CtSaleBVO[] ctbVo = ctsaleVo.getCtSaleBVO();
    // ԭ�Ҽ�˰�ϼ�
    UFDouble norigtaxmny = UFDouble.ZERO_DBL;
    // �ۼƸ�����
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
       * HK 2018��11��26��18:28:23  
       * �ۼ��տ���  ��Ϊ0��  �����ɣ�֮ǰϵͳ���߼��� ����0 �����ɣ�
       */
//      if (norigtaxmny.compareTo(noritotalgpmny) > 0) {
      if (norigtaxmny.compareTo(noritotalgpmny) != 0) {
        bvos.add(ctbVo[i]);
      }
    }

    ctsaleVo.setCtSaleBVO(bvos.toArray(new CtSaleBVO[bvos.size()]));
  }
}
