package nc.ui.ct.saledaily.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.pubapp.AppBsContext;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleChangeVO;
import nc.vo.ct.saledaily.entity.CtSalePayTermVO;
import nc.vo.ct.saledaily.entity.CtSaleTermVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.ct.uitl.ArrayUtil;
import nc.vo.ct.uitl.ValueUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 销售合同复制action <b>本类主要完成以下功能：</b> 对生效状态合同的复制 但合同的预付款、累计付款总额、累计付款金额信息不复制。
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-5-10 下午02:54:37
 */
public class SaleCopyActionProcessor implements
    ICopyActionProcessor<AggCtSaleVO> {

  /**
   *
   */
  private static final long serialVersionUID = 7527768348321275530L;

  @Override
  public void processVOAfterCopy(AggCtSaleVO billVO, LoginContext context) {
    // 设置表头
    if (!ValueUtil.isEmpty(billVO.getParentVO())) {
      this.setHeadValue(billVO.getParentVO());
    }
    // 设置表体合同基本页签
    if (!ValueUtil.isEmpty(billVO.getCtSaleBVO())) {
      this.setBodySaleValue(billVO.getCtSaleBVO(), billVO.getParentVO());
    }
    // 设置表体变更历史页签
    if (!ValueUtil.isEmpty(billVO.getCtSaleChangeVO())) {
      this.setBodyChangeValue(billVO);
    }
    // 设置表体合同条款页签
    if (!ValueUtil.isEmpty(billVO.getCtSaleTermVO())) {
      this.setBodyTermValue(billVO.getCtSaleTermVO(), billVO.getParentVO());
    }
    // 设置表体合同费用页签
    if (!ValueUtil.isEmpty(billVO.getCtSaleExpVO())) {
      billVO.setCtSaleExpVO(null);
    }
    // 设置表体合同大事记页签
    if (!ValueUtil.isEmpty(billVO.getCtSaleMemoraVO())) {
      billVO.setCtSaleMemoraVO(null);
    }
    // 设置表体执行情况页签
    if (!ValueUtil.isEmpty(billVO.getCtSaleExecVO())) {
      // 复制操作 执行表清空 操作对象是billVO
      billVO.setCtSaleExecVO(null);
    }
    // 设置表体收款协议.
    if (!ValueUtil.isEmpty(billVO.getCtSalePayTermVO())) {
      this.setBodyPayTermValue(billVO);
    }

  }

  private String getDeptVID(String deptid) {
    Map<String, String> deptmap =
        DeptPubService.getLastVIDSByDeptIDS(new String[] {
          deptid
        });
    return deptmap.get(deptid);
  }

  private void setBodyChangeValue(AggCtSaleVO bill) {
    // 先清空执行表的数据
    bill.setCtSaleChangeVO(null);
    // 再set原始版本
    CtSaleChangeVO originChangeVO = new CtSaleChangeVO();
    originChangeVO.setPk_group(bill.getParentVO().getPk_group());
    originChangeVO.setPk_org(bill.getParentVO().getPk_group());
    originChangeVO.setPk_org_v(bill.getParentVO().getPk_org_v());
    originChangeVO.setVmemo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4020003_0", "04020003-0005")/* @res "原始版本" */);
    originChangeVO.setVchangecode(UFDouble.ONE_DBL);
    bill.setCtSaleChangeVO(new CtSaleChangeVO[] {
      originChangeVO
    });
  }

  private void setBodyPayTermValue(AggCtSaleVO billVO) {
    CtSalePayTermVO[] ctSalePayTermVOs = billVO.getCtSalePayTermVO();
    if (ArrayUtil.isEmpty(ctSalePayTermVOs)) {
      return;
    }
    for (CtSalePayTermVO vo : ctSalePayTermVOs) {
      vo.setPk_ct_sale_payterm(null);
      vo.setPk_ct_sale(null);
      vo.setNrealrecymny(null);
      vo.setDrealeffectdate(null);
      vo.setDrealenddate(null);
      vo.setNctrecvmny(null);
      vo.setDplaneffectdate(null);
      vo.setDplanenddate(null);
      vo.setNglobalpanrecymny(null);
      vo.setNgroupplanrecvmny(null);
      vo.setNlocalplanmny(null);
      vo.setNplanrecmny(null);
      vo.setPk_org_v(billVO.getParentVO().getPk_org_v());
    }

  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param ctSaleBVO
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-10 上午11:20:56
   */
  private void setBodySaleValue(CtSaleBVO[] ctSaleBVO, CtSaleVO parentVO) {
    // modify by liangchen1 批量查询财务组织vid
    Set<String> pk_financeorgs = new HashSet<String>();
    for (CtSaleBVO vo : ctSaleBVO) {
      pk_financeorgs.add(vo.getPk_financeorg());
    }
    Map<String, String> orgOidVidMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(pk_financeorgs
            .toArray(new String[pk_financeorgs.size()]));
    for (CtSaleBVO ctBVO : ctSaleBVO) {
      ctBVO.setPk_ct_sale(null);
      ctBVO.setPk_ct_sale_b(null);
      // 订单执行累计数量
      ctBVO.setNordnum(UFDouble.ZERO_DBL);
      // 订单执行累计原币价税合计
      ctBVO.setNordsum(UFDouble.ZERO_DBL);
      // 累计本币收款金额
      ctBVO.setNtotalgpmny(UFDouble.ZERO_DBL);
      // 累计原币收款金额
      ctBVO.setNoritotalgpmny(UFDouble.ZERO_DBL);
      // 来源子子表
      ctBVO.setCsrcbbid(null);
      // 来源单据子表行ID
      ctBVO.setCsrcbid(null);
      // 来源单据主表ID
      ctBVO.setCsrcid(null);
      // //来源交易类型
      ctBVO.setVrstrantypecode(null);
      // 来源单据号
      ctBVO.setVsrccode(null);
      // 来源单据行号
      ctBVO.setVsrcrowno(null);
      // 来源单据类型
      ctBVO.setVsrctype(null);
      ctBVO.setPk_origctb(null);

      // 协同合同
      ctBVO.setVecmctbillcode(null);
      ctBVO.setPk_ecmct(null);
      ctBVO.setPk_ecmct_b(null);
      ctBVO.setPk_org_v(parentVO.getPk_org_v());
      ctBVO.setPk_financeorg_v(orgOidVidMap.get(ctBVO.getPk_financeorg()));
    }

  }

  private void setBodyTermValue(CtSaleTermVO[] ctSaleTermVO, CtSaleVO parentVO) {
    for (CtSaleTermVO termVO : ctSaleTermVO) {
      termVO.setPk_ct_sale(null);
      termVO.setPk_ct_sale_term(null);
      termVO.setPk_org_v(parentVO.getPk_org_v());
    }
  }

  // private void setBodyVID(CtSaleBVO ctBVO) {
  // String pk_financeorg = ctBVO.getPk_financeorg();
  // if (StringUtils.isNotBlank(pk_financeorg)) {
  // String pk_financeorg_v = OrgUnitPubService.getOrgVid(pk_financeorg);
  // ctBVO.setPk_org_v(pk_financeorg_v);
  // }
  // }

  /**
   * 方法功能描述：设置复制的表头信息 合同的预付款、累计付款总额、累计付款金额信息不复制。
   * <p>
   * <b>参数说明</b>
   * 
   * @param parentVO
   * @param context
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-10 上午09:58:44
   */
  private void setHeadValue(CtSaleVO parentVO) {
    parentVO.setPk_ct_sale(null);
    parentVO.setPk_origct(null);
//    parentVO.setVbillcode(null);		// HK（2018年10月12日14:58:51） 去掉清空 合同号
//    parentVO.setCtname(null);			// HK（2018年10月12日14:58:51） 去掉清空 合同名称
    parentVO.setApprover(null);
    // 制单人，制单时间清空
    parentVO.setBillmaker(null);
    parentVO.setDmakedate(null);
    parentVO.setCreator(null);
    parentVO.setTaudittime(null);
    parentVO.setModifiedtime(null);
    parentVO.setModifier(null);
    parentVO.setCreationtime(null);
    parentVO.setActualvalidate(null);
    parentVO.setActualinvalidate(null);
    parentVO.setFstatusflag((Integer) CtFlowEnum.Free.value());
    parentVO.setVersion(UFDouble.ONE_DBL);

    // 最新版本
    parentVO.setBlatest(UFBoolean.TRUE);
    // 原币预付款
    parentVO.setNoriprepaymny(UFDouble.ZERO_DBL);
    // 本币预付款
    parentVO.setNprepaymny(UFDouble.ZERO_DBL);
    // 累计原币收款总额
    parentVO.setNorigpshamount(null);
    // 累计本币收款总额
    parentVO.setNtotalgpamount(null);
    // 已生成订单量作为合同执行 false为普通合同 true为从请购单过来的采购合同
    parentVO.setBordernumexec(UFBoolean.FALSE);
    // 来源协同合同
    parentVO.setBsrcecmct(UFBoolean.FALSE);

    // 636表头签订日期为系统日期、计划生效、计划终止清空
    parentVO.setSubscribedate(AppBsContext.getInstance().getBusiDate());
//    parentVO.setValdate(null);			// HK（2018年10月12日14:58:51） 去掉清空 计划生效日期
//    parentVO.setInvallidate(null);		// HK（2018年10月12日14:58:51） 去掉清空 计划终止日期

    parentVO.setVdef19(null);	// HK（2020年9月5日15:45:49）清空：租金确认截止日
    
    // 设置版本信息
    this.setHeadVID(parentVO);
  }

  private void setHeadVID(CtSaleVO parentVO) {
    String pk_org = parentVO.getPk_org();
    if (StringUtils.isNotBlank(pk_org)) {
      String pk_org_v = OrgUnitPubService.getOrgVid(pk_org);
      parentVO.setPk_org_v(pk_org_v);
    }

    String pk_dept = parentVO.getDepid();
    if (StringUtils.isNotBlank(pk_dept)) {
      String pk_dept_v = this.getDeptVID(pk_dept);
      parentVO.setDepid_v(pk_dept_v);
    }

  }

}
