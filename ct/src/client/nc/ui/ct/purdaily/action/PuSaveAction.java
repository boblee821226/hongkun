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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-4-21 ����01:59:48
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
                                                                   * "����ѡ��ɹ���֯"
                                                                   */);
    }
    // �ƻ���ֹ����У��
    // this.checkInvalliDate();
    
    /**
     * HK 2020��1��5��10:15:55
     * ���� ���޺�ͬ�����ֶε�У��
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
          })/* ���ԭ����Ϊ�գ����ݺ�Ϊ{0} */);

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
      CtPuVO oldheadvo =
          (CtPuVO) ((AggCtPuVO) ctmodel.getSelectedData()).getParent();
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
        .getStrByID("4020003_0", "04020003-0005")/* @res "ԭʼ�汾" */);
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
      // ��ͷ����Э����ֵ�����帶��Э��ҳǩ����Ϊ��
      if (ArrayUtils.isEmpty(aggvo.getCtPaymentVO())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4020003_0", "04020003-0343")/*
                                                                     * @res
                                                                     * "��ͷ���ڸ���Э�飬���帶��Э��ҳǩ����Ϊ��"
                                                                     */);
      }
    }
    if (!ArrayUtils.isEmpty(aggvo.getCtPaymentVO())) {
      // ����Э�鸶������ϲ��ܴ���100
      UFDouble accrate = UFDouble.ZERO_DBL;
      // �ʱ���������ֻ����һ���ʱ���
      int dcount = 0;

      for (CtPaymentVO ptvo : aggvo.getCtPaymentVO()) {
        if (MathTool.compareTo(UFDouble.ZERO_DBL, ptvo.getAccrate()) >= 0
            || MathTool.compareTo(ptvo.getAccrate(), new UFDouble(100)) > 0) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4020003_0", "04020003-0350")/*
                                                                       * @res
                                                                       * " �������Ӧ����0С�ڵ���100��"
                                                                       */);
        }

        if (ptvo.getPaymentday() == null) {
          if (ValueUtil.isEmpty(ptvo.getOutaccountdate())
              && ValueUtil.isEmpty(ptvo.getCheckdata())) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0347")/*
                                                                         * @res
                                                                         * "��������Ϊ�գ������ա��̶������ղ���ͬʱΪ�գ�"
                                                                         */);
          }

          if (ValueUtil.isEmpty(ptvo.getCheckdata())
              && !ValueUtil.isEmpty(ptvo.getOutaccountdate())) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0415")/*
                                                                         * @res
                                                                         * "�����˳����պ�������ù̶������գ�"
                                                                         */);
          }

          if (ptvo.getCheckdata() != null && ptvo.getEffectmonth() == null
              || ptvo.getEffectaddmonth() == null) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4020003_0", "04020003-0348")/*
                                                                         * @res
                                                                         * "�̶���������ֵ����Ч�¡������²���Ϊ�գ�"
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
                                                                         * "����������ֵ�������ա��̶������ա���Ч�¡������²�����ֵ��"
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
                })/* ����{0}�ı����ܺʹ���100�����飡\n */);
      }
      if (dcount > 1) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4020003_0", "04020003-0396", null, new String[] {
              aggvo.getParentVO().getVbillcode()
            })/* ����{0}ֻ����һ���ʱ������飡 */);
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
      // ǰ̨У���ͬ����Э��ҳǩ����
      this.validateCtPayment(vos);

    }
    else if (ctmodel.getCtUIState() == CtUIState.CTMODIFY) {
      this.setActionName("MODIFY");
      // �������
      // this.setWhenModify(vos);
      this.checkChgReason(vos);
    }
    else if (ctmodel.getUiState() == UIState.EDIT) {
      // ǰ̨У���ͬ����Э��ҳǩ����
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

  @Override
  protected void setFakeRowNO(SuperVO[] vos) {
    for (int i = 0; i < vos.length; i++) {
      // modified by liuchx 2010.07.30 ����new Integer
      vos[i].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
          Integer.valueOf(i));
    }
  }
  
  /**
   * HK 2020��1��5��10:09:48
   * У�� �Ƿ�����
   * ����� ���ޣ��� ���޺�ͬ��Ϣ �Ǳ���ġ�
   */
   private String checkZulin() throws Exception {
	   String errorMsg = null;
	   Map<String, String> mustFieldMap = new HashMap<>();
	   mustFieldMap.put("vdef6", "���⿪ʼ����");
	   mustFieldMap.put("vdef14", "�����ֹ����");
	   mustFieldMap.put("vdef8", "���Ͻ������");
	   mustFieldMap.put("vdef9", "���ϵ��ۣ�ÿ��ÿƽ�ף�");
	   mustFieldMap.put("vdef10", "����¥����");
	   mustFieldMap.put("vdef11", "���½������");
	   mustFieldMap.put("vdef12", "���µ��ۣ�ÿ��ÿƽ�ף�");
	   mustFieldMap.put("vdef15", "����¥����");
	   mustFieldMap.put("vdef19", "��������ʽ");
//	   mustFieldMap.put("vdef20", "ռ�����");
	   mustFieldMap.put("vdef7", "��֤��");
	   String isZulin = PuPubVO.getString_TrimZeroLenAsNull(
			   this.getCardForm().getBillCardPanel().getHeadItem("vdef16").getValueObject());
	   if ("1001N510000000A2THSN".equals(isZulin)) {
		   // ����� ���ޣ����� �����ֶΡ�
		   for (Entry<String, String> field : mustFieldMap.entrySet()) {
			   Object value = this.getCardForm().getBillCardPanel()
						   .getHeadItem(field.getKey())
						   .getValueObject();
			   if (value == null) {
				   throw new Exception("���޺�ͬ����"+field.getValue()+"�� �ֶβ���Ϊ��");
			   }
		   }
	   }
	   return errorMsg;
   }
   
}
