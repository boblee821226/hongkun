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
 * �ɹ���ͬ���action <b>������Ҫ������¹��ܣ�</b> ��ͷ�ɱ༭�ֶ�
 * ��ͬ���ơ��ƻ���Чʱ�䡢�ƻ���ֹʱ�䡢�Է���λ˵�������š���Ա�������ص㡢�ո���Э�顢Ԥ�����޶�Ƿ�ί�⣻
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
public class PuModifyAction extends EditAction {
  // ǰ̨�Ƿ���
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
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.EditAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ���ñ��ui״̬
    CTModel model = (CTModel) this.getModel();
    // ����Ȩ��
    this.dataPowerCheck();
    // modify by liangchen1 �ۻ���ͬ�����Ч�Լ���������������
    // xihy1 ����ͬ״̬����������ʾ�汾�ĺ�ͬ��������-������鵽�ĺ�ͬ
    this.checkVersion(model);
    // ����model״̬�������޸ĺͱ��
    model.setCtUIState(CtUIState.CTMODIFY);
    super.doAction(e);
    this.cardForm.showMeUp();
    // �����ֶα༭��
    this.setFeildsEnable();

    this.setChangeVOWhenModify(model);
    // ����Ĭ�ϲ�����֯
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
                           * @res "��ͬ�������°汾����������"
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
   * �������������� ���ʱ����Ҫ����һ�б����¼����֧��¼�뵱ǰ���ԭ��ͱ�ע <b>����˵��</b>
   * 
   * @param model
   * @since 6.3
   * @version 2013-8-20 ����06:34:58
   * @author xihy1
   */
  private void setChangeVOWhenModify(CTModel model) {
    Object vo = model.getSelectedData();
    AggCtPuVO ctVo = (AggCtPuVO) vo;
    CtPuVO ctHeadVo = ctVo.getParentVO();
    /**
     * �ۻ����� ԭ�б��������ֱ�Ӱ汾��+1��������Ч̬�°汾��ͬ ��������������жϺ�ͬ״̬�Ƿ�Ϊ����Ч��
     * ����ǣ���ԭ�����߼����汾��+1����̨save�ٴ�����״̬ԭ������Ϊ��Ч̬���߼�����Ϊ����̬
     * ������ǣ�vo����������Ӧ����������̬�°汾��ͬ������棨�޸ģ�
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
   * �������������������ñ�ͷ�����ֶβ��ܱ༭��������feilds����������ֶ��ܱ༭
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author liuchx
   * @time 2010-6-18 ����04:02:57
   */
  private void setFeildsEnable() {
    // ��� �������Ͳ��ñ༭
    CardEditorHelper util =
        CardEditorHelper.getInstance(this.getCardForm().getBillCardPanel());
    // �ȱ�ͷ���в��ñ༭
    util.setHeadItemsEnabled(false);
    String[] headfeilds =
        {
          CtAbstractVO.CTNAME,
          CtAbstractVO.PERSONNELID,
          CtAbstractVO.DEPID_V,
          CtAbstractVO.DELIADDR,
          CtAbstractVO.VALDATE,
          // �ɹ���ͬ�߱����ʱ�򣬻��Ǵ���ɲ������޸ı�ͷ������Э�顱��
          // by diaoxy for lizhengb�ʼ� on 20130327
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
           * HK 2020��4��7��17:10:35
           * �����ֶΣ����ʱ�ɱ༭
           * 01������ƾ֤
           * 01�����ŷ�̯
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
    // �����item�ܱ༭
    util.setHeadEnabled(headfeilds, true);
    util.setBodyEnabled(bodyfeilds, true);

    util.setBodyEnabled(new String[] {
      CtAbstractBVO.PK_FINANCEORG, CtAbstractBVO.PK_FINANCEORG_V
    }, false);

    // ���ø���Э��ҳǩ���ɱ༭
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
    // modify by liangchen1 �ۻ���ͬ�����Ч�Լ���������������
    // 1. ��Ч̬�ĺ�ͬ���Ա��
    // 2. ��Ϊ1.0����̬��������ͨ��̬�ĺ�ͬ���Ա��
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
        // ԭ�߼�������ͨ������Ч�Ķ����Ա��
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
