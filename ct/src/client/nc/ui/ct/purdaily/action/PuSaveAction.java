package nc.ui.ct.purdaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.uif2.IActionCode;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.ct.action.CtScriptPFlowAction;
import nc.ui.ct.model.CTModel;
import nc.ui.ct.view.CtUIState;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.trade.businessaction.IPFACTION;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.purdaily.entity.CtPaymentVO;
import nc.vo.ct.purdaily.entity.CtPuChangeVO;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ct.uitl.ValueUtil;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-4-21 下午01:59:48
 */
public class PuSaveAction extends CtScriptPFlowAction {

  private static final long serialVersionUID = 1L;

  public PuSaveAction() {
    ActionInitializer.initializeAction(this, IActionCode.SAVE);
    this.setActionName(IPFACTION.SAVE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (ValueUtil.isEmpty(this.getModel().getContext().getPk_org())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4020003_0", "04020003-0062")/*
                                                                   * @res
                                                                   * "请先选择采购组织"
                                                                   */);
    }
    // 计划终止日期校验
    // this.checkInvalliDate();
    
    /**
     * HK 2020年1月5日10:15:55
     * 增加 租赁合同必填字段的校验
     */
    String errorMsg = this.checkZulin();
    if (errorMsg != null && errorMsg.length() > 0) {
    	MessageDialog.showErrorDlg(this.getCardForm(), "", errorMsg);
    	return;
    }
    /***END***/
    
    super.doAction(e);
  }

  private void checkChgReason(Object[] vos) {
    CtPuChangeVO chgVO =
        this.getLastVersionVO(((AggCtPuVO) vos[0]).getCtPuChangeVO());
    if (chgVO != null && StringUtils.isEmpty(chgVO.getVchgreason())) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4020003_0", "04020003-0402", null, new String[] {
            ((AggCtPuVO) vos[0]).getParentVO().getVbillcode()
          })/* 变更原因不能为空，单据号为{0} */);

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
      CtPuVO oldheadvo =
          (CtPuVO) ((AggCtPuVO) ctmodel.getSelectedData()).getParent();
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

  private CtPuChangeVO getLastVersionVO(CtPuChangeVO[] ctPuChangeVOs) {
    UFDouble version = UFDouble.ONE_DBL;
    CtPuChangeVO resultVO = ctPuChangeVOs[0];
    for (CtPuChangeVO chgVO : ctPuChangeVOs) {
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
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_org = ((AggCtPuVO) vos[0]).getParentVO().getPk_org();
    String pk_org_v = ((AggCtPuVO) vos[0]).getParentVO().getPk_org_v();
    String pk_group = ((AggCtPuVO) vos[0]).getParentVO().getPk_group();
    CtPuChangeVO[] puchangeVo = new CtPuChangeVO[] {
      new CtPuChangeVO()
    };
    puchangeVo[0].setVchangecode(UFDouble.ONE_DBL);
    puchangeVo[0].setVmemo(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4020003_0", "04020003-0005")/* @res "原始版本" */);
    puchangeVo[0].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
        Integer.valueOf(0));
    puchangeVo[0].setPk_org(pk_org);
    puchangeVo[0].setPk_org_v(pk_org_v);
    puchangeVo[0].setPk_group(pk_group);
    ((AggCtPuVO) vos[0]).setCtPuChangeVO(puchangeVo);
  }

  private void validateCtPayment(Object[] vos) {
    AggCtPuVO aggvo = (AggCtPuVO) vos[0];
    if (!StringUtils.isEmpty(aggvo.getParentVO().getPk_payterm())) {
      // 表头付款协议有值，表体付款协议页签不能为空
      if (ArrayUtils.isEmpty(aggvo.getCtPaymentVO())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0343")/*
                                                                     * @res
                                                                     * "表头存在付款协议，表体付款协议页签不能为空"
                                                                     */);
      }
    }
    if (!ArrayUtils.isEmpty(aggvo.getCtPaymentVO())) {
      // 付款协议付款比例合不能大于100
      UFDouble accrate = UFDouble.ZERO_DBL;
      // 质保金数量（只能有一个质保金）
      int dcount = 0;

      for (CtPaymentVO ptvo : aggvo.getCtPaymentVO()) {
        if (MathTool.compareTo(UFDouble.ZERO_DBL, ptvo.getAccrate()) >= 0
            || MathTool.compareTo(ptvo.getAccrate(), new UFDouble(100)) > 0) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4020003_0", "04020003-0350")/*
                                                                       * @res
                                                                       * " 付款比例应大于0小于等于100！"
                                                                       */);
        }

        if (ptvo.getPaymentday() == null) {
          if (ValueUtil.isEmpty(ptvo.getOutaccountdate())
              && ValueUtil.isEmpty(ptvo.getCheckdata())) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0347")/*
                                                                         * @res
                                                                         * "账期天数为空，出账日、固定结账日不能同时为空！"
                                                                         */);
          }

          if (ValueUtil.isEmpty(ptvo.getCheckdata())
              && !ValueUtil.isEmpty(ptvo.getOutaccountdate())) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0415")/*
                                                                         * @res
                                                                         * "设置了出账日后必须设置固定结账日！"
                                                                         */);
          }

          if (ptvo.getCheckdata() != null && ptvo.getEffectmonth() == null
              || ptvo.getEffectaddmonth() == null) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0348")/*
                                                                         * @res
                                                                         * "固定结账日有值，生效月、附加月不能为空！"
                                                                         */);
          }
        }
        else {
          if (!ValueUtil.isEmpty(ptvo.getOutaccountdate())
              || !ValueUtil.isEmpty(ptvo.getCheckdata())
              || !ValueUtil.isEmpty(ptvo.getEffectmonth())
              || !ValueUtil.isEmpty(ptvo.getEffectaddmonth())) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0349")/*
                                                                         * @res
                                                                         * "账期天数有值，出账日、固定结账日、生效月、附加月不能有值！"
                                                                         */);
          }
        }

        accrate = MathTool.add(accrate, ptvo.getAccrate());
        if (UFBoolean.TRUE.equals(ptvo.getIsdeposit())) {
          dcount++;
        }
      }
      if (!MathTool.equals(accrate, new UFDouble(100))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0344", null,
                new String[] {
                  aggvo.getParentVO().getVbillcode()
                })/* 单据{0}的比率总和大于100，请检查！\n */);
      }
      if (dcount > 1) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4020003_0", "04020003-0396", null, new String[] {
              aggvo.getParentVO().getVbillcode()
            })/* 单据{0}只能有一个质保金，请检查！ */);
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
      // 前台校验合同付款协议页签数据
      this.validateCtPayment(vos);

    }
    else if (ctmodel.getCtUIState() == CtUIState.CTMODIFY) {
      this.setActionName("MODIFY");
      // 变更处理
      // this.setWhenModify(vos);
      this.checkChgReason(vos);
    }
    else if (ctmodel.getUiState() == UIState.EDIT) {
      // 前台校验合同付款协议页签数据
      this.validateCtPayment(vos);
    }
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

  @Override
  protected void setFakeRowNO(SuperVO[] vos) {
    for (int i = 0; i < vos.length; i++) {
      // modified by liuchx 2010.07.30 不用new Integer
      vos[i].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
          Integer.valueOf(i));
    }
  }
  
  /**
   * HK 2020年1月5日10:09:48
   * 校验 是否租赁
   * 如果是 租赁，则 租赁合同信息 是必填的。
   */
   private String checkZulin() throws Exception {
	   String errorMsg = null;
	   Map<String, String> mustFieldMap = new HashMap<>();
	   mustFieldMap.put("vdef6", "免租开始日期");
	   mustFieldMap.put("vdef14", "免租截止日期");
	   mustFieldMap.put("vdef8", "地上建筑面积");
	   mustFieldMap.put("vdef9", "地上单价（每天每平米）");
	   mustFieldMap.put("vdef10", "地上楼层数");
	   mustFieldMap.put("vdef11", "免租开始日期");
	   mustFieldMap.put("vdef12", "地下单价（每天每平米）");
	   mustFieldMap.put("vdef15", "地下楼层数");
	   mustFieldMap.put("vdef19", "租金递增方式");
//	   mustFieldMap.put("vdef20", "占地面积");
	   mustFieldMap.put("vdef7", "履约保证金");
	   String isZulin = PuPubVO.getString_TrimZeroLenAsNull(
			   this.getCardForm().getBillCardPanel().getHeadItem("vdef16").getValueObject());
	   if ("1001N510000000A2THSN".equals(isZulin)) {
		   // 如果是 租赁，则检查 必填字段。
		   for (Entry<String, String> field : mustFieldMap.entrySet()) {
			   Object value = this.getCardForm().getBillCardPanel()
						   .getHeadItem(field.getKey())
						   .getValueObject();
			   if (value == null) {
				   throw new Exception("租赁合同：【"+field.getValue()+"】 字段不能为空");
			   }
		   }
	   }
	   return errorMsg;
   }
   
}
