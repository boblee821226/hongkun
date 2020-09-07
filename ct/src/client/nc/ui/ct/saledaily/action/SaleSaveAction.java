package nc.ui.ct.saledaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.uif2.IActionCode;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.uap.ws.message.MessageUtil;
import nc.ui.ct.action.CtScriptPFlowAction;
import nc.ui.ct.model.CTModel;
import nc.ui.ct.view.CtUIState;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.pub.CtSaleTableCode;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.ct.saledaily.entity.CtSaleChangeVO;
import nc.vo.ct.saledaily.entity.CtSalePayTermVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.ct.uitl.ArrayTool;
import nc.vo.ct.uitl.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * *@version 6.0
 * 
 * @since 6.0
 * @author liuchx
 * @time 2010-4-21 ����01:59:48
 */
public class SaleSaveAction extends CtScriptPFlowAction {

  private static final long serialVersionUID = 1L;

  private static final UFDouble UF100 = new UFDouble(100);

  public SaleSaveAction() {
    ActionInitializer.initializeAction(this, IActionCode.SAVE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (StringUtil.isEmptyTrimSpace(this.getModel().getContext().getPk_org())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4020003_0", "04020003-0077")/*
                                                                   * @res
                                                                   * "����ѡ��������֯"
                                                                   */);
    }
    // �ƻ���ֹ����У��
    // this.checkInvalliDate();
    
    /**
     * HK 2020��9��7��10:39:57
     * ��ʾ����� �Զ���19�����ȷ�Ͻ�ֹ�գ�����Ϊ�գ��������ʾ��
     */
    CardPanelValueUtils utils =
            new CardPanelValueUtils(this.getCardForm().getBillCardPanel());
    String vdef19 = PuPubVO.getString_TrimZeroLenAsNull(utils.getHeadTailStringValue("vdef19"));
    if (vdef19 != null && !"~".equals(vdef19)) {
    	int flag = MessageDialog.showOkCancelDlg(this.getCardForm(), "�Ƿ񱣴棿", "���ȷ�Ͻ�ֹ�գ�" + vdef19.substring(0, 10));
    	if (!(flag == MessageDialog.ID_OK)) {
    		// �������Ĳ���ok���򷵻ء�
    		return;
    	}
    }
    /***END***/
    
    super.doAction(e);
  }

  private void checkChgReason(Object[] vos) {
    CtSaleChangeVO chgVO =
        this.getLastVersionVO(((AggCtSaleVO) vos[0]).getCtSaleChangeVO());
    if (chgVO != null && StringUtils.isEmpty(chgVO.getVchgreason())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4020003_0", "04020003-0336", null, new String[] {
            ((AggCtSaleVO) vos[0]).getParentVO().getVbillcode()
          }));// ���ԭ�򲻿�Ϊ��:���ݺ�Ϊ{0}
    }
  }

  /**
   * �������������� ��ͬ���ʱ���ƻ���ֹ����ֻ�ܸĴ󣬲��ܸ�С��
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * <b>����˵��</b>
   * 
   * @param model
   *          <p>
   * @since 6.1
   * @author hugw
   * @time 2012-8-20 ����09:28:24
   */
  private void checkInvalliDate() {
    CTModel ctmodel = (CTModel) this.getModel();
    if (ctmodel.getCtUIState() == CtUIState.CTMODIFY) {
      this.getCardForm().getBillCardPanel().stopEditing();
      CardPanelValueUtils utils =
          new CardPanelValueUtils(this.getCardForm().getBillCardPanel());
      CtSaleVO oldheadvo =
          (CtSaleVO) ((AggCtSaleVO) ctmodel.getSelectedData()).getParent();
      // �޸�ǰ�ƻ���ֹ����
      UFDate oldInvallidate = oldheadvo.getInvallidate();
      // �޸ĺ�ƻ���ֹ����
      UFDate newInvallidate = utils.getHeadTailUFDateValue("invallidate");
      if (newInvallidate != null
          && new UFDate(oldInvallidate.toStdString()).compareTo(new UFDate(
              newInvallidate.toStdString())) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0294")/*
                                                                     * @res
                                                                     * "�ƻ���ֹ����Ӧ����ԭ���ļƻ���ֹ���ڡ�"
                                                                     */);
      }
    }
  }

  private void checkPayTerm(Object[] vos) {
    BillCardPanel panel = this.getCardForm().getBillCardPanel();
    for (Object vo : vos) {
      AggCtSaleVO aggvo = (AggCtSaleVO) vo;
      CtSaleVO ctSaleVO = aggvo.getParentVO();
      UFDouble rateSum = new UFDouble(0);
      int count = 0;
      int depositCount = 0;
      CtSalePayTermVO[] paytermvos = aggvo.getCtSalePayTermVO();
      // ����������տ�Э�飬�ҽ�������Ϊչ���տ�Э��ʱ
      if (!StringUtil.isEmptyTrimSpace(ctSaleVO.getPk_payterm())
          && panel.getBodyPanel(CtSaleTableCode.CTSALEPAYTERM).getTable()
              .isShowing()) {

        for (CtSalePayTermVO termvo : paytermvos) {
          if (termvo.getStatus() == VOStatus.DELETED) {
            continue;
          }
          count++;
          rateSum = rateSum.add(termvo.getAccrate());
          if (termvo.getIsdeposit() != null
              && termvo.getIsdeposit().booleanValue()) {
            depositCount = depositCount + 1;
          }

          if (termvo.getCheckdata() != null) {

            if (termvo.getEffectmonth() == null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0389")/*
                                                                           * �����˹̶�������
                                                                           * ��
                                                                           * ����������
                                                                           * ��Ч��
                                                                           */
              );
            }

            if (termvo.getPaymentday() != null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0399") /*
                                                                            * ����������
                                                                            * �̶������ղ���ͬʱ��ֵ
                                                                            */
              );
            }

          }
          else {

            if (termvo.getEffectmonth() != null
                || termvo.getEffectaddmonth() != null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0390") /*
                                                                            * δ���á�
                                                                            * �̶�������
                                                                            * ��
                                                                            * ����������
                                                                            * ��Ч��
                                                                            * ��
                                                                            * ������
                                                                            */
              );
            }

            if (termvo.getPaymentday() == null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0398") /*
                                                                            * ����������
                                                                            * �̶������ղ���ͬʱΪ��
                                                                            */
              );
            }

            if (termvo.getAccountday() != null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0415") /*
                                                                            * �����˳����պ�������ù̶�
                                                                            * ������
                                                                            * ��
                                                                            */
              );
            }

          }

          if (termvo.getDplaneffectdate() != null
              && termvo.getDplanenddate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0436") /*
                                                                          * �����˼ƻ���Ч��
                                                                          * �������üƻ�������
                                                                          */
            );
          }

          if (termvo.getDrealeffectdate() != null
              && termvo.getDrealenddate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0437") /*
                                                                          * ������ʵ����Ч��
                                                                          * ��������ʵ�ʵ�����
                                                                          */
            );
          }

          if (termvo.getDplanenddate() != null
              && termvo.getDplaneffectdate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0438") /*
                                                                          * �����˼ƻ�������
                                                                          * �������üƻ���Ч��
                                                                          */
            );
          }

          if (termvo.getDrealenddate() != null
              && termvo.getDrealeffectdate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0439") /*
                                                                          * ������ʵ�ʵ�����
                                                                          * ��������ʵ����Ч��
                                                                          */
            );
          }

          if (termvo.getDplaneffectdate() != null
              && termvo.getDplanenddate() != null
              && termvo.getDplaneffectdate().after(termvo.getDplanenddate())) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0393") /*
                                                                          * �ƻ������ձ���
                                                                          * ���ڵ��ڼƻ���Ч��
                                                                          * ��
                                                                          */
            );
          }

          if (termvo.getDrealeffectdate() != null
              && termvo.getDrealenddate() != null
              && termvo.getDrealeffectdate().after(termvo.getDrealenddate())) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0395") /*
                                                                          * ʵ�ʵ����ձ���
                                                                          * ���ڵ���ʵ����Ч��
                                                                          * ��
                                                                          */
            );
          }

        }

        if (count < 1) {
          ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4020003_0", "04020003-0388") /*
                                                                        * �տ�Э������Ҫ��һ���տ���
                                                                        */
          );
        }

        if (!MathTool.equals(SaleSaveAction.UF100, rateSum)) {
          ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4020003_0", "04020003-0391") /*
                                                                        * �տ����֮�ͱ������100
                                                                        */
          );
        }

        if (depositCount > 1) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4020003_0", "04020003-0396", null, new String[] {
                aggvo.getParentVO().getVbillcode()
              })/* ����{0}ֻ����һ���ʱ������飡 */);
        }

      }
    }
  }

  private CtSaleChangeVO getLastVersionVO(CtSaleChangeVO[] CtSaleChangeVOs) {
    UFDouble version = UFDouble.ONE_DBL;
    CtSaleChangeVO resultVO = CtSaleChangeVOs[0];
    for (CtSaleChangeVO chgVO : CtSaleChangeVOs) {
      if (chgVO == null) {
        continue;
      }
      if (MathTool.compareTo(chgVO.getVchangecode(), version) > 0) {
        version = chgVO.getVchangecode();
        resultVO = chgVO;
      }
    }
    return resultVO;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-1 ����01:22:04
   */
  private void setWhenAdd(Object[] vos) {
    CtSaleChangeVO[] salechangeVo = new CtSaleChangeVO[] {
      new CtSaleChangeVO()
    };
    String pk_org = ((AggCtSaleVO) vos[0]).getParentVO().getPk_org();
    String pk_org_v = ((AggCtSaleVO) vos[0]).getParentVO().getPk_org_v();
    String pk_group = ((AggCtSaleVO) vos[0]).getParentVO().getPk_group();
    salechangeVo[0].setVchangecode(UFDouble.ONE_DBL);
    salechangeVo[0].setVmemo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4020003_0", "04020003-0005")/* @res "ԭʼ�汾" */);
    salechangeVo[0].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
        Integer.valueOf(0));
    salechangeVo[0].setPk_org(pk_org);
    salechangeVo[0].setPk_org_v(pk_org_v);
    salechangeVo[0].setPk_group(pk_group);
    ((AggCtSaleVO) vos[0]).setCtSaleChangeVO(salechangeVo);
  }

  private void setWhenModify(Object[] vos) {
    for (Object vo : vos) {
      // modify by liangchen1 �ۻ���ͬ�����Ч�Լ���������������
      /**
       * ԭ�б��������ֱ�Ӱ汾��+1��������Ч̬�°汾��ͬ ��������������жϺ�ͬ�汾�Ƿ�Ϊ1.0
       * ����ǣ���ԭ�����߼����汾��+1����̨save�ٴ�����״̬ԭ������Ϊ��Ч̬���߼�����Ϊ����̬
       * ������ǣ�vo����������Ӧ����������̬�°汾��ͬ������棨�޸ģ�AggCtSaleVO
       */
      AggCtSaleVO ctVo = (AggCtSaleVO) vo;
      CtSaleVO ctHeadVo = ctVo.getParentVO();
      if (CtFlowEnum.VALIDATE.toIntValue() == ctHeadVo.getFstatusflag()
          .intValue()) {
        UFDouble oldVersion = ctHeadVo.getVersion();
        UFDouble newVersion = oldVersion.add(UFDouble.ONE_DBL);
        ctHeadVo.setVersion(newVersion);
        CtSaleChangeVO[] oldChangeVo = ctVo.getCtSaleChangeVO();
        CtSaleChangeVO[] newChangeVo = new CtSaleChangeVO[] {
          new CtSaleChangeVO()
        };
        newChangeVo[0].setPk_group(ctHeadVo.getPk_group());
        newChangeVo[0].setPk_org(ctHeadVo.getPk_org());
        newChangeVo[0].setPk_org_v(ctHeadVo.getPk_org_v());
        newChangeVo[0].setPk_ct_sale(ctHeadVo.getPk_ct_sale());
        newChangeVo[0].setVchangecode(newVersion);
        newChangeVo[0].setVchgdate(AppContext.getInstance().getBusiDate());
        newChangeVo[0].setVchgpsn(this.getModel().getContext()
            .getPk_loginUser());
        CtSaleChangeVO[] changeVo =
            ArrayTool.arrayToCombin(oldChangeVo, newChangeVo);
        this.setFakeRowNO(changeVo);
        ctVo.setChildren(CtSaleChangeVO.class, changeVo);
      }
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-1 ����06:00:03
   */
  @Override
  protected Object[] ctProcessBefore(AbstractBill[] vos) {

    this.setActionName("SAVEBASE");
    CTModel ctmodel = (CTModel) this.getModel();
    if (ctmodel.getUiState() == UIState.ADD) {
      // ��������
      this.setWhenAdd(vos);
    }
    else if (ctmodel.getCtUIState() == CtUIState.CTMODIFY) {
      this.setActionName("MODIFY");
      this.checkChgReason(vos);
    }

    this.checkPayTerm(vos);

    return vos;
  }

  @Override
  protected boolean isActionEnable() {
    return UIState.ADD == this.model.getUiState()
        || UIState.EDIT == this.model.getUiState();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#isResume(nc.itf.pubapp.pub.exception.IResumeException)
   */
  @Override
  protected boolean isResume(IResumeException resumeInfo) {

    // String msg = ((BusinessException) resumeInfo).getMessage();
    // if (UIDialog.ID_OK == MessageDialog.showOkCancelDlg(this.getModel()
    // .getContext().getEntranceUI(), null, msg)) {
    // // ��ͬ��־λ
    // PfUserObject pfuo = this.getFlowContext().getUserObj();
    // CTUserConfirmContext ctc = new CTUserConfirmContext();
    // if (null == pfuo) {
    // pfuo = new PfUserObject();
    // }
    // ctc.setToleranceConfirm(UFBoolean.TRUE);
    // pfuo.setUserObject(ctc);
    // // �Ѻ�ͬ��־λ�ŵ�PFlowContext����ȥ
    // this.getFlowContext().setUserObj(pfuo);
    // return true;
    // }
    // return false;
    /**
     * ����scmpubͳһ�쳣������������
     */
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo,
        this.getFlowContext());
  }
}
