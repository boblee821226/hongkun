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
 * ���ۺ�ͬ����action <b>������Ҫ������¹��ܣ�</b> ����Ч״̬��ͬ�ĸ��� ����ͬ��Ԥ����ۼƸ����ܶ�ۼƸ�������Ϣ�����ơ�
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-5-10 ����02:54:37
 */
public class SaleCopyActionProcessor implements
    ICopyActionProcessor<AggCtSaleVO> {

  /**
   *
   */
  private static final long serialVersionUID = 7527768348321275530L;

  @Override
  public void processVOAfterCopy(AggCtSaleVO billVO, LoginContext context) {
    // ���ñ�ͷ
    if (!ValueUtil.isEmpty(billVO.getParentVO())) {
      this.setHeadValue(billVO.getParentVO());
    }
    // ���ñ����ͬ����ҳǩ
    if (!ValueUtil.isEmpty(billVO.getCtSaleBVO())) {
      this.setBodySaleValue(billVO.getCtSaleBVO(), billVO.getParentVO());
    }
    // ���ñ�������ʷҳǩ
    if (!ValueUtil.isEmpty(billVO.getCtSaleChangeVO())) {
      this.setBodyChangeValue(billVO);
    }
    // ���ñ����ͬ����ҳǩ
    if (!ValueUtil.isEmpty(billVO.getCtSaleTermVO())) {
      this.setBodyTermValue(billVO.getCtSaleTermVO(), billVO.getParentVO());
    }
    // ���ñ����ͬ����ҳǩ
    if (!ValueUtil.isEmpty(billVO.getCtSaleExpVO())) {
      billVO.setCtSaleExpVO(null);
    }
    // ���ñ����ͬ���¼�ҳǩ
    if (!ValueUtil.isEmpty(billVO.getCtSaleMemoraVO())) {
      billVO.setCtSaleMemoraVO(null);
    }
    // ���ñ���ִ�����ҳǩ
    if (!ValueUtil.isEmpty(billVO.getCtSaleExecVO())) {
      // ���Ʋ��� ִ�б���� ����������billVO
      billVO.setCtSaleExecVO(null);
    }
    // ���ñ����տ�Э��.
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
    // �����ִ�б������
    bill.setCtSaleChangeVO(null);
    // ��setԭʼ�汾
    CtSaleChangeVO originChangeVO = new CtSaleChangeVO();
    originChangeVO.setPk_group(bill.getParentVO().getPk_group());
    originChangeVO.setPk_org(bill.getParentVO().getPk_group());
    originChangeVO.setPk_org_v(bill.getParentVO().getPk_org_v());
    originChangeVO.setVmemo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4020003_0", "04020003-0005")/* @res "ԭʼ�汾" */);
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
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param ctSaleBVO
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-10 ����11:20:56
   */
  private void setBodySaleValue(CtSaleBVO[] ctSaleBVO, CtSaleVO parentVO) {
    // modify by liangchen1 ������ѯ������֯vid
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
      // ����ִ���ۼ�����
      ctBVO.setNordnum(UFDouble.ZERO_DBL);
      // ����ִ���ۼ�ԭ�Ҽ�˰�ϼ�
      ctBVO.setNordsum(UFDouble.ZERO_DBL);
      // �ۼƱ����տ���
      ctBVO.setNtotalgpmny(UFDouble.ZERO_DBL);
      // �ۼ�ԭ���տ���
      ctBVO.setNoritotalgpmny(UFDouble.ZERO_DBL);
      // ��Դ���ӱ�
      ctBVO.setCsrcbbid(null);
      // ��Դ�����ӱ���ID
      ctBVO.setCsrcbid(null);
      // ��Դ��������ID
      ctBVO.setCsrcid(null);
      // //��Դ��������
      ctBVO.setVrstrantypecode(null);
      // ��Դ���ݺ�
      ctBVO.setVsrccode(null);
      // ��Դ�����к�
      ctBVO.setVsrcrowno(null);
      // ��Դ��������
      ctBVO.setVsrctype(null);
      ctBVO.setPk_origctb(null);

      // Эͬ��ͬ
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
   * �����������������ø��Ƶı�ͷ��Ϣ ��ͬ��Ԥ����ۼƸ����ܶ�ۼƸ�������Ϣ�����ơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param parentVO
   * @param context
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-10 ����09:58:44
   */
  private void setHeadValue(CtSaleVO parentVO) {
    parentVO.setPk_ct_sale(null);
    parentVO.setPk_origct(null);
//    parentVO.setVbillcode(null);		// HK��2018��10��12��14:58:51�� ȥ����� ��ͬ��
//    parentVO.setCtname(null);			// HK��2018��10��12��14:58:51�� ȥ����� ��ͬ����
    parentVO.setApprover(null);
    // �Ƶ��ˣ��Ƶ�ʱ�����
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

    // ���°汾
    parentVO.setBlatest(UFBoolean.TRUE);
    // ԭ��Ԥ����
    parentVO.setNoriprepaymny(UFDouble.ZERO_DBL);
    // ����Ԥ����
    parentVO.setNprepaymny(UFDouble.ZERO_DBL);
    // �ۼ�ԭ���տ��ܶ�
    parentVO.setNorigpshamount(null);
    // �ۼƱ����տ��ܶ�
    parentVO.setNtotalgpamount(null);
    // �����ɶ�������Ϊ��ִͬ�� falseΪ��ͨ��ͬ trueΪ���빺�������Ĳɹ���ͬ
    parentVO.setBordernumexec(UFBoolean.FALSE);
    // ��ԴЭͬ��ͬ
    parentVO.setBsrcecmct(UFBoolean.FALSE);

    // 636��ͷǩ������Ϊϵͳ���ڡ��ƻ���Ч���ƻ���ֹ���
    parentVO.setSubscribedate(AppBsContext.getInstance().getBusiDate());
//    parentVO.setValdate(null);			// HK��2018��10��12��14:58:51�� ȥ����� �ƻ���Ч����
//    parentVO.setInvallidate(null);		// HK��2018��10��12��14:58:51�� ȥ����� �ƻ���ֹ����

    parentVO.setVdef19(null);	// HK��2020��9��5��15:45:49����գ����ȷ�Ͻ�ֹ��
    
    // ���ð汾��Ϣ
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
