package nc.ui.ct.purdaily.action;

import java.awt.event.ActionEvent;

import nc.ui.ct.model.CTModel;
import nc.ui.ct.util.CardEditorHelper;
import nc.ui.ct.view.CtUIState;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.ct.entity.CtAbstractBVO;
import nc.vo.ct.entity.CtAbstractChangeVO;
import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.pub.CtPuTableCode;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.purdaily.entity.CtPuChangeVO;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ct.rule.ActionStateRule;
import nc.vo.ct.rule.CtVersionRule;
import nc.vo.ct.uitl.ValueUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * <p>
 * 采购合同变更action <b>本类主要完成以下功能：</b> 表头可编辑字段
 * 合同名称、计划生效时间、计划终止时间、对方单位说明、部门、人员、交货地点、收付款协议、预付款限额、是否委外；
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
public class PuModifyAction extends EditAction {
  // 前台是否变更
  public static final String PURDAILY_MODIFY_FLAG = "modify_flag";

  /**
     *
     */
  private static final long serialVersionUID = 7263688937825522309L;

  ShowUpableBillForm cardForm;

  NCAction editAction;

  public PuModifyAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.CT_MODIFY);

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.EditAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 设置变更ui状态
    CTModel model = (CTModel) this.getModel();
    // 数据权限
    this.dataPowerCheck();
    // modify by liangchen1 港华合同变更生效以及重走审批流需求
    // xihy1 检查合同状态，非最新显示版本的合同不允许变更-针对联查到的合同
    this.checkVersion(model);
    // 设置model状态，区分修改和变更
    model.setCtUIState(CtUIState.CTMODIFY);
    super.doAction(e);
    this.cardForm.showMeUp();
    // 设置字段编辑性
    this.setFeildsEnable();

    this.setChangeVOWhenModify(model);
    // 设置默认财务组织
    this.setFinanceOrg();
  }

  public ShowUpableBillForm getCardForm() {
    return this.cardForm;
  }

  public NCAction getEditAction() {
    return this.editAction;
  }

  public void setCardForm(ShowUpableBillForm cardForm) {
    this.cardForm = cardForm;
  }

  public void setEditAction(NCAction editAction) {
    this.editAction = editAction;
  }

  private void checkVersion(BillManageModel model) {
    AggCtPuVO aggvo = (AggCtPuVO) model.getSelectedData();
    UFBoolean bshowlatest = aggvo.getParentVO().getBshowLatest();
    if (UFBoolean.FALSE.equals(bshowlatest)) {
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4020003_0",
          "04020003-0342")/*
                           * @res "合同不是最新版本，不允许变更"
                           */;
    }
  }

  private void dataPowerCheck() {
    AggCtPuVO aggVo = (AggCtPuVO) this.getModel().getSelectedData();
    PowerCheckUtils.checkHasPermission(new AggCtPuVO[] {
      aggVo
    }, CTBillType.PurDaily.getCode(), "modify", CtAbstractVO.VBILLCODE);
  }

  /**
   * 方法功能描述： 变更时，需要新增一行变更记录，并支持录入当前变更原因和备注 <b>参数说明</b>
   * 
   * @param model
   * @since 6.3
   * @version 2013-8-20 下午06:34:58
   * @author xihy1
   */
  private void setChangeVOWhenModify(CTModel model) {
    Object vo = model.getSelectedData();
    AggCtPuVO ctVo = (AggCtPuVO) vo;
    CtPuVO ctHeadVo = ctVo.getParentVO();
    /**
     * 港华需求： 原有变更保存是直接版本号+1，生成生效态新版本合同 这里根据需求，先判断合同状态是否为”生效“
     * 如果是，走原来的逻辑，版本号+1，后台save再处理单据状态原来设置为生效态的逻辑，改为自由态
     * 如果不是，vo不做处理，对应场景是自由态新版本合同变更保存（修改）
     */
    if (CtFlowEnum.VALIDATE.toIntValue() == ctHeadVo.getFstatusflag()
        .intValue()) {
      UFDouble newVersion = ctHeadVo.getVersion().add(UFDouble.ONE_DBL);

      BillCardPanel panel = this.cardForm.getBillCardPanel();
      panel.setHeadItem(CtAbstractVO.VERSION, newVersion);

      int row = ctVo.getCtPuChangeVO().length;
      String tableCode = CtPuChangeVO.PK_CT_PU_CHANGE;
      panel.getBodyPanel(tableCode).addLine();

      panel.setBodyValueAt(ctHeadVo.getPk_group(), row,
          CtAbstractChangeVO.PK_GROUP, tableCode);
      panel.setBodyValueAt(ctHeadVo.getPk_org(), row,
          CtAbstractChangeVO.PK_ORG, tableCode);
      panel.setBodyValueAt(ctHeadVo.getPk_org_v(), row,
          CtAbstractChangeVO.PK_ORG_V, tableCode);
      panel.setBodyValueAt(ctHeadVo.getPk_ct_pu(), row, CtPuChangeVO.PK_CT_PU,
          tableCode);
      panel.setBodyValueAt(newVersion, row, CtAbstractChangeVO.VCHANGECODE,
          tableCode);
      panel.setBodyValueAt(AppContext.getInstance().getBusiDate(), row,
          CtAbstractChangeVO.VCHGDATE, tableCode);
      panel.setBodyValueAt(this.getModel().getContext().getPk_loginUser(), row,
          CtAbstractChangeVO.VCHGPSN, tableCode);

      panel.getBillModel(tableCode).loadLoadRelationItemValue();
    }

  }

  /**
   * 方法功能描述：先设置表头所有字段不能编辑，再设置feilds数组里面的字段能编辑
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author liuchx
   * @time 2010-6-18 下午04:02:57
   */
  private void setFeildsEnable() {
    // 变更 交易类型不让编辑
    CardEditorHelper util =
        CardEditorHelper.getInstance(this.getCardForm().getBillCardPanel());
    // 先表头所有不让编辑
    util.setHeadItemsEnabled(false);
    String[] headfeilds =
        {
          CtAbstractVO.CTNAME,
          CtAbstractVO.PERSONNELID,
          CtAbstractVO.DEPID_V,
          CtAbstractVO.DELIADDR,
          CtAbstractVO.VALDATE,
          // 采购合同走变更的时候，还是处理成不允许修改表头“付款协议”。
          // by diaoxy for lizhengb邮件 on 20130327
          // CtAbstractVO.PK_PAYTERM,
          CtAbstractVO.NORIPREPAYLIMITMNY, CtAbstractVO.INVALLIDATE,
          CtAbstractVO.VDEF1, CtAbstractVO.VDEF2, CtAbstractVO.VDEF3,
          CtAbstractVO.VDEF4, CtAbstractVO.VDEF5, CtAbstractVO.VDEF6,
          CtAbstractVO.VDEF7, CtAbstractVO.VDEF8, CtAbstractVO.VDEF9,
          CtAbstractVO.VDEF10, CtAbstractVO.VDEF11, CtAbstractVO.VDEF12,
          CtAbstractVO.VDEF13, CtAbstractVO.VDEF14, CtAbstractVO.VDEF15,
          CtAbstractVO.VDEF16, CtAbstractVO.VDEF17, CtAbstractVO.VDEF18,
          CtAbstractVO.VDEF19, CtAbstractVO.VDEF20
          /**
           * HK 2020年4月7日17:10:35
           * 增加字段，变更时可编辑
           * 01、责任凭证
           * 01、部门分摊
           */
          , CtPuVO.VHKFIELD01
          , CtPuVO.VHKFIELD02
//          , CtPuVO.VHKFIELD03
//          , CtPuVO.VHKFIELD04
//          , CtPuVO.VHKFIELD05
//          , CtPuVO.VHKFIELD06
//          , CtPuVO.VHKFIELD07
//          , CtPuVO.VHKFIELD08
//          , CtPuVO.VHKFIELD09
//          , CtPuVO.VHKFIELD10
          /***END***/
        };
    String[] bodyfeilds =
        {
          CtAbstractBVO.PK_MARBASCLASS, CtAbstractBVO.PK_MATERIAL,
          CtAbstractBVO.NASTNUM, CtAbstractBVO.NQTUNITNUM,
          CtAbstractBVO.CASTUNITID, CtAbstractBVO.CQTUNITID,
          CtAbstractBVO.FTAXTYPEFLAG, CtAbstractBVO.NTAXRATE,
          CtAbstractBVO.NGLOBALMNY, CtAbstractBVO.NGLOBALTAXMNY,
          CtAbstractBVO.NGPRICE, CtAbstractBVO.NGROUPMNY,
          CtAbstractBVO.NGROUPTAXMNY, CtAbstractBVO.NGTAXPRICE,
          CtAbstractBVO.NMNY, CtAbstractBVO.NORIGMNY, CtAbstractBVO.NORIGPRICE,
          CtAbstractBVO.NORIGTAXMNY, CtAbstractBVO.NORIGTAXPRICE,
          CtAbstractBVO.NQTORIGPRICE, CtAbstractBVO.NQTORIGTAXPRICE,
          CtAbstractBVO.NQTPRICE, CtAbstractBVO.NQTTAXPRICE,
          CtAbstractBVO.NTAX, CtAbstractBVO.NTAXMNY, CtAbstractBVO.VBDEF1,
          CtAbstractBVO.VBDEF2, CtAbstractBVO.VBDEF3, CtAbstractBVO.VBDEF4,
          CtAbstractBVO.VBDEF5, CtAbstractBVO.VBDEF6, CtAbstractBVO.VBDEF7,
          CtAbstractBVO.VBDEF8, CtAbstractBVO.VBDEF9, CtAbstractBVO.VBDEF10,
          CtAbstractBVO.VBDEF11, CtAbstractBVO.VBDEF12, CtAbstractBVO.VBDEF13,
          CtAbstractBVO.VBDEF14, CtAbstractBVO.VBDEF15, CtAbstractBVO.VBDEF16,
          CtAbstractBVO.VBDEF17, CtAbstractBVO.VBDEF18, CtAbstractBVO.VBDEF19,
          CtAbstractBVO.VBDEF20

        };
    // 上面的item能编辑
    util.setHeadEnabled(headfeilds, true);
    util.setBodyEnabled(bodyfeilds, true);

    util.setBodyEnabled(new String[] {
      CtAbstractBVO.PK_FINANCEORG, CtAbstractBVO.PK_FINANCEORG_V
    }, false);

    // 设置付款协议页签不可编辑
    util.setbodyItemsEnabled(CtPuTableCode.CTPUPM, false);
  }

  private void setFinanceOrg() {
    CTModel model = (CTModel) this.cardForm.getModel();
    AggCtPuVO aggvo = (AggCtPuVO) model.getSelectedData();
    model.setPk_financeorg(aggvo.getCtPuBVO()[0].getPk_financeorg());
    model.setPk_financeorg_v(aggvo.getCtPuBVO()[0].getPk_financeorg_v());

  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel model = (BillManageModel) this.getModel();
    AbstractBill ctVo = (AbstractBill) model.getSelectedData();
    if (ValueUtil.isEmpty(ctVo)) {
      return false;
    }

    boolean isEnable =
        model.getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();
    // modify by liangchen1 港华合同变更生效以及重走审批流需求
    // 1. 生效态的合同可以变更
    // 2. 不为1.0自由态、审批不通过态的合同可以变更
    ActionStateRule rule = new ActionStateRule();
    CtVersionRule vl = new CtVersionRule();
    Object selectedData = this.getModel().getSelectedData();
    if (isEnable) {
      if (model.getSelectedOperaDatas() == null
          || model.getSelectedOperaDatas().length == 1) {
        return rule.isHaveValidate(selectedData)
            && UFBoolean.FALSE.equals(((AggCtPuVO) selectedData).getParentVO()
                .getBsrcecmct())
            || !vl.isFirstVersion(selectedData)
            && (rule.isHaveFree(selectedData) || rule
                .isHaveUNApprove(selectedData));
      }
      else if (model.getSelectedOperaDatas().length > 1) {
        // 原逻辑是审批通过或生效的都可以变更
        // ActionStateRule rule = new ActionStateRule();
        // return rule.isHaveApprove(model.getSelectedData())
        // || rule.isHaveValidate(model.getSelectedData());
        return rule.isHaveValidate(selectedData)
            && UFBoolean.FALSE.equals(((AggCtPuVO) selectedData).getParentVO()
                .getBsrcecmct())
            || !vl.isFirstVersion(selectedData)
            && (rule.isHaveFree(selectedData) || rule
                .isHaveUNApprove(selectedData));

      }
    }
    return isEnable;

  }
}
