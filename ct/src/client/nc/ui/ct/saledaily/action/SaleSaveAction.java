package nc.ui.ct.saledaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.uif2.IActionCode;
import nc.itf.pubapp.pub.exception.IResumeException;
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * *@version 6.0
 * 
 * @since 6.0
 * @author liuchx
 * @time 2010-4-21 下午01:59:48
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
                                                                   * "请先选择销售组织"
                                                                   */);
    }
    // 计划终止日期校验
    // this.checkInvalliDate();
    
    /**
     * HK 2020年9月7日10:39:57
     * 提示：如果 自定义19（租金确认截止日），不为空，则给出提示。
     */
    CardPanelValueUtils utils =
            new CardPanelValueUtils(this.getCardForm().getBillCardPanel());
    String vdef19 = PuPubVO.getString_TrimZeroLenAsNull(utils.getHeadTailStringValue("vdef19"));
    if (vdef19 != null && !"~".equals(vdef19)) {
    	int flag = MessageDialog.showOkCancelDlg(this.getCardForm(), "是否保存？", "租金确认截止日：" + vdef19.substring(0, 10));
    	if (!(flag == MessageDialog.ID_OK)) {
    		// 如果点击的不是ok，则返回。
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
          }));// 变更原因不可为空:单据号为{0}
    }
  }

  /**
   * 方法功能描述： 合同变更时，计划终止日期只能改大，不能改小。
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * <b>参数说明</b>
   * 
   * @param model
   *          <p>
   * @since 6.1
   * @author hugw
   * @time 2012-8-20 上午09:28:24
   */
  private void checkInvalliDate() {
    CTModel ctmodel = (CTModel) this.getModel();
    if (ctmodel.getCtUIState() == CtUIState.CTMODIFY) {
      this.getCardForm().getBillCardPanel().stopEditing();
      CardPanelValueUtils utils =
          new CardPanelValueUtils(this.getCardForm().getBillCardPanel());
      CtSaleVO oldheadvo =
          (CtSaleVO) ((AggCtSaleVO) ctmodel.getSelectedData()).getParent();
      // 修改前计划终止日期
      UFDate oldInvallidate = oldheadvo.getInvallidate();
      // 修改后计划终止日期
      UFDate newInvallidate = utils.getHeadTailUFDateValue("invallidate");
      if (newInvallidate != null
          && new UFDate(oldInvallidate.toStdString()).compareTo(new UFDate(
              newInvallidate.toStdString())) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0294")/*
                                                                     * @res
                                                                     * "计划终止日期应大于原来的计划终止日期。"
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
      // 当表体存在收款协议，且交易类型为展开收款协议时
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
                                                                           * 设置了固定结账日
                                                                           * 后
                                                                           * ，必须设置
                                                                           * 生效月
                                                                           */
              );
            }

            if (termvo.getPaymentday() != null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0399") /*
                                                                            * 账期天数、
                                                                            * 固定结账日不能同时有值
                                                                            */
              );
            }

          }
          else {

            if (termvo.getEffectmonth() != null
                || termvo.getEffectaddmonth() != null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0390") /*
                                                                            * 未设置“
                                                                            * 固定结账日
                                                                            * ，
                                                                            * 不允许设置
                                                                            * 生效月
                                                                            * 和
                                                                            * 附加月
                                                                            */
              );
            }

            if (termvo.getPaymentday() == null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0398") /*
                                                                            * 账期天数、
                                                                            * 固定结账日不能同时为空
                                                                            */
              );
            }

            if (termvo.getAccountday() != null) {
              ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4020003_0", "04020003-0415") /*
                                                                            * 设置了出账日后必须设置固定
                                                                            * 结账日
                                                                            * ！
                                                                            */
              );
            }

          }

          if (termvo.getDplaneffectdate() != null
              && termvo.getDplanenddate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0436") /*
                                                                          * 设置了计划起效日
                                                                          * 必须设置计划到期日
                                                                          */
            );
          }

          if (termvo.getDrealeffectdate() != null
              && termvo.getDrealenddate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0437") /*
                                                                          * 设置了实际起效日
                                                                          * 必须设置实际到期日
                                                                          */
            );
          }

          if (termvo.getDplanenddate() != null
              && termvo.getDplaneffectdate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0438") /*
                                                                          * 设置了计划到期日
                                                                          * 必须设置计划起效日
                                                                          */
            );
          }

          if (termvo.getDrealenddate() != null
              && termvo.getDrealeffectdate() == null) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0439") /*
                                                                          * 设置了实际到期日
                                                                          * 必须设置实际起效日
                                                                          */
            );
          }

          if (termvo.getDplaneffectdate() != null
              && termvo.getDplanenddate() != null
              && termvo.getDplaneffectdate().after(termvo.getDplanenddate())) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0393") /*
                                                                          * 计划到期日必须
                                                                          * 大于等于计划起效日
                                                                          * ！
                                                                          */
            );
          }

          if (termvo.getDrealeffectdate() != null
              && termvo.getDrealenddate() != null
              && termvo.getDrealeffectdate().after(termvo.getDrealenddate())) {
            ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0395") /*
                                                                          * 实际到期日必须
                                                                          * 大于等于实际起效日
                                                                          * ！
                                                                          */
            );
          }

        }

        if (count < 1) {
          ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4020003_0", "04020003-0388") /*
                                                                        * 收款协议至少要有一个收款期
                                                                        */
          );
        }

        if (!MathTool.equals(SaleSaveAction.UF100, rateSum)) {
          ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4020003_0", "04020003-0391") /*
                                                                        * 收款比例之和必须等于100
                                                                        */
          );
        }

        if (depositCount > 1) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4020003_0", "04020003-0396", null, new String[] {
                aggvo.getParentVO().getVbillcode()
              })/* 单据{0}只能有一个质保金，请检查！ */);
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
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-1 下午01:22:04
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
        .getStrByID("4020003_0", "04020003-0005")/* @res "原始版本" */);
    salechangeVo[0].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
        Integer.valueOf(0));
    salechangeVo[0].setPk_org(pk_org);
    salechangeVo[0].setPk_org_v(pk_org_v);
    salechangeVo[0].setPk_group(pk_group);
    ((AggCtSaleVO) vos[0]).setCtSaleChangeVO(salechangeVo);
  }

  private void setWhenModify(Object[] vos) {
    for (Object vo : vos) {
      // modify by liangchen1 港华合同变更生效以及重走审批流需求
      /**
       * 原有变更保存是直接版本号+1，生成生效态新版本合同 这里根据需求，先判断合同版本是否为1.0
       * 如果是，走原来的逻辑，版本号+1，后台save再处理单据状态原来设置为生效态的逻辑，改为自由态
       * 如果不是，vo不做处理，对应场景是自由态新版本合同变更保存（修改）AggCtSaleVO
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
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-6-1 下午06:00:03
   */
  @Override
  protected Object[] ctProcessBefore(AbstractBill[] vos) {

    this.setActionName("SAVEBASE");
    CTModel ctmodel = (CTModel) this.getModel();
    if (ctmodel.getUiState() == UIState.ADD) {
      // 新增处理
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
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#isResume(nc.itf.pubapp.pub.exception.IResumeException)
   */
  @Override
  protected boolean isResume(IResumeException resumeInfo) {

    // String msg = ((BusinessException) resumeInfo).getMessage();
    // if (UIDialog.ID_OK == MessageDialog.showOkCancelDlg(this.getModel()
    // .getContext().getEntranceUI(), null, msg)) {
    // // 合同标志位
    // PfUserObject pfuo = this.getFlowContext().getUserObj();
    // CTUserConfirmContext ctc = new CTUserConfirmContext();
    // if (null == pfuo) {
    // pfuo = new PfUserObject();
    // }
    // ctc.setToleranceConfirm(UFBoolean.TRUE);
    // pfuo.setUserObject(ctc);
    // // 把合同标志位放到PFlowContext里面去
    // this.getFlowContext().setUserObj(pfuo);
    // return true;
    // }
    // return false;
    /**
     * 适配scmpub统一异常弹窗交互方法
     */
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo,
        this.getFlowContext());
  }
}
