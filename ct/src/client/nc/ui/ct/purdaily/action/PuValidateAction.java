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
 * 采购合同生效action <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-5-11 下午01:17:13
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
                                                                 * "是否确定要使该合同生效？"
                                                                 */)) {
    	/**
         * HK
         * 2019年10月21日 10点46分
         * 判断附件个数，是否大于等于2个
         * 2019年10月30日 10点35分
         * 判断改为1个，参数传入最少附件数量
         */
        if (!checkFujian(1)) {
      	  MessageDialog.showErrorDlg(
  	            this.getModel().getContext().getEntranceUI(),
  	            "附件检查",
  	            "请上传附件，附件数量不能小于1个");
      	  return;
        }
        /***END***/
    	
      if (!this.isValdate()) {
        this.sReason =
            (String) MessageDialog.showInputDlg(
                this.getModel().getContext().getEntranceUI(),
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                    "UC000-0000900")/* @res "原因" */,
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4020003_0", "04020003-0024")/*
                                                  * @res "实际生效日期与计划生效日期不符，请输入原因"
                                                  */, null, 120);
      }
      
      super.doAction(e);
    }
  }

  /**
   * HK
   * 2019年10月21日 10点46分
   * 判断附件个数，是否大于等于2个
 * @throws BusinessException 
   */
  private boolean checkFujian(Integer fileNum) throws BusinessException {
	
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
		if(fileCount >= fileNum) {
			return true;
		}
	}
	
	return false;
  }

/**
   * 方法功能描述：判断实际生效日期，是否计划生效日期
   * <p>
   * <b>参数说明</b>
   * 
   * @param valdate
   *          <p>
   * @since 6.0
   * @author lizhengb
   * @time 2010-5-18 上午11:09:02
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
    // modify by liangchen1 港华合同变更生效以及重走审批流需求
    /**
     * 界面vo是一组pk，生效的时候需要设置原始版本pk给新版本，以保证回写联查的正确
     * 前台vo的pk与后台返回pk不同在流程平台进行差异处理时会出现问题（表头直接差异，表体根据pk匹配进行差异化)
     * 本身流程平台差异处理会优先使用伪列进行 ，但非编辑态下从界面得到的vo并没有自动加上伪列，所以加上伪列， 以保证流程平台能够正常差异处理
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
