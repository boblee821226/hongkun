package nc.ui.ct.purdaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.ct.action.ActionNameUtil;
import nc.ui.ct.action.CtScriptPFlowAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.rule.ActionStateRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.Calendars;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.util.ArrayUtil;

/**
 * <p>
 * �ɹ���ͬ��Чaction <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-5-11 ����01:17:13
 */
public class PuValidateAction extends CtScriptPFlowAction {
  /**
   *
   */
  private static final long serialVersionUID = 7263688937825522309L;

  private String sReason;

  public PuValidateAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.CT_VALIDATE);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_H,
    // InputEvent.ALT_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.sReason = null;
    if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(this.getModel()
        .getContext().getEntranceUI(), null, nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4020003_0", "04020003-0023")/*
                                                                 * @res
                                                                 * "�Ƿ�ȷ��Ҫʹ�ú�ͬ��Ч��"
                                                                 */)) {
    	/**
         * HK
         * 2019��10��21�� 10��46��
         * �жϸ����������Ƿ���ڵ���2��
         */
        if (!checkFujian()) {
      	  MessageDialog.showErrorDlg(
  	            this.getModel().getContext().getEntranceUI(),
  	            "�������",
  	            "���ϴ�������������������С��2��");
      	  return;
        }
        /***END***/
    	
      if (!this.isValdate()) {
        this.sReason =
            (String) MessageDialog.showInputDlg(
                this.getModel().getContext().getEntranceUI(),
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                    "UC000-0000900")/* @res "ԭ��" */,
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4020003_0", "04020003-0024")/*
                                                  * @res "ʵ����Ч������ƻ���Ч���ڲ�����������ԭ��"
                                                  */, null, 120);
      }
      
      super.doAction(e);
    }
  }

  /**
   * HK
   * 2019��10��21�� 10��46��
   * �жϸ����������Ƿ���ڵ���2��
 * @throws BusinessException 
   */
  private boolean checkFujian() throws BusinessException {
	
	AggCtPuVO ctVo = (AggCtPuVO) this.getModel().getSelectedData();
	String pk_bill = ctVo.getParentVO().getPk_ct_pu();
	  
	StringBuilder querySQL = new StringBuilder();
	querySQL.append(" select count(0) ")
			.append(" from sm_pub_filesystem ")
			.append(" where ")
			.append(" filepath like '").append(pk_bill).append("/%' ")
			.append(" and nvl(isfolder,'n') in ('N','n') ")
	;
	IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
	ArrayList list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
	if(list!=null && list.size() > 0)
	{
		int fileCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
		if(fileCount >= 2) {
			return true;
		}
	}
	
	return false;
  }

/**
   * ���������������ж�ʵ����Ч���ڣ��Ƿ�ƻ���Ч����
   * <p>
   * <b>����˵��</b>
   * 
   * @param valdate
   *          <p>
   * @since 6.0
   * @author lizhengb
   * @time 2010-5-18 ����11:09:02
   */
  private boolean isValdate() {
    AggCtPuVO ctVo = (AggCtPuVO) this.getModel().getSelectedData();
    UFDate valdate = ctVo.getParentVO().getValdate();
    return valdate.isSameDate(AppContext.getInstance().getBusiDate(),
        Calendars.getGMTDefault());

  }

  @Override
  protected Object[] ctProcessBefore(AbstractBill[] vos) {
    List<AbstractBill> listDate = Arrays.asList(vos);
    ListToArrayTool<AbstractBill> tool = new ListToArrayTool<AbstractBill>();
    AbstractBill[] arrayBills = tool.convertToArray(listDate);
    ActionUtil.addNewExecVO((AggCtPuVO[]) arrayBills,
        (Integer) CtFlowEnum.APPROVE.value(), this.sReason,
        ActionNameUtil.getVALIDATE());
    // modify by liangchen1 �ۻ���ͬ�����Ч�Լ���������������
    /**
     * ����vo��һ��pk����Ч��ʱ����Ҫ����ԭʼ�汾pk���°汾���Ա�֤��д�������ȷ
     * ǰ̨vo��pk���̨����pk��ͬ������ƽ̨���в��촦��ʱ��������⣨��ͷֱ�Ӳ��죬�������pkƥ����в��컯)
     * ��������ƽ̨���촦�������ʹ��α�н��� �����Ǳ༭̬�´ӽ���õ���vo��û���Զ�����α�У����Լ���α�У� �Ա�֤����ƽ̨�ܹ��������촦��
     */
    for (AbstractBill vo : vos) {
      AggCtPuVO aggVo = (AggCtPuVO) vo;
      SuperVO[][] allChildren = aggVo.getAllChildren();
      for (SuperVO[] superVOs : allChildren) {
        if (ArrayUtil.isEmpty(superVOs)) {
          continue;
        }
        super.setFakeRowNO(superVOs);
      }
    }
    return arrayBills;
  }

  @Override
  protected boolean isActionEnable() {

    // if (this.getModel().getSelectedOperaDatas() == null) {
    // return false;
    // }
    // else if (this.getModel().getSelectedOperaDatas().length > 1) {
    // return true;
    // }
    // else {
    // ActionStateRule rule = new ActionStateRule();
    // return rule.isHaveApprove(this.getModel().getSelectedData());
    // }
    Object selectedData = this.getModel().getSelectedData();
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != selectedData;
    if (isEnable) {
      if (this.getModel().getSelectedOperaDatas() == null
          || this.getModel().getSelectedOperaDatas().length == 1) {
        ActionStateRule rule = new ActionStateRule();
        return rule.isHaveApprove(selectedData);
      }

      else if (this.getModel().getSelectedOperaDatas().length > 1) {
        return UFBoolean.FALSE.equals(((AggCtPuVO) selectedData).getParentVO()
            .getBsrcecmct());
      }

    }
    return isEnable;
  }

}
