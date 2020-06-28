package nc.ui.pm.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.pm.util.BillCardPanelUtil;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.CopyAction;
import nc.ui.uif2.UIState;
import nc.vo.pcm.feebalance.FeeBalanceBillVO;
import nc.vo.pcm.feebalance.FeeBalanceHeadVO;
import nc.vo.pm.constant.CommonKeyConst;
import nc.vo.pm.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.util.CloneUtil;

/**
 * 红冲
 */
@SuppressWarnings("restriction")
public class ReverseAction extends CopyAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * 表头带版本字段
   */
  private List<String> headTailFieldsWithVersionList = new ArrayList<String>();

  /**
   * 表体带版本字段
   */
  private List<List<String>> bodyFieldsWithVersionList;

  public ReverseAction() {
	  super();
	  this.setBtnName("红冲");
	  this.setCode("reverseAction");
  }
  
  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object object = this.getModel().getSelectedData();
    if (object instanceof AbstractBill) {
    	FeeBalanceBillVO aggvo = (FeeBalanceBillVO) CloneUtil.deepClone(object);
    	// 只有是 审核态 和 金额大于0，并且是 非红冲单的，才可以进行红冲。
    	FeeBalanceHeadVO hVO = (FeeBalanceHeadVO)aggvo.getParentVO();
    	if (hVO.getBill_status() != 1 
    	|| hVO.getMoney().compareTo(UFDouble.ZERO_DBL) <= 0
    	|| hVO.getDef20() != null
    	) {
    		throw new Exception("非审核态、金额小于0、红冲单，不能进行红冲操作。");
    	}
      if (this.getCopyActionProcessor() != null) {
    	  // 数据交换
        this.getCopyActionProcessor().processVOAfterCopy(aggvo, this.getModel().getContext());
      }
      this.getModel().setUiState(UIState.ADD);
      this.getEditor().setValue(aggvo);
      this.getEditor().setBodyStatusNew();
      this.loadNewVersion();
      BillCardPanelUtil.setHeadValue(this.getEditor().getBillCardPanel(), "billmaker", AppContext.getInstance().getPkUser());
      BillCardPanelUtil.setHeadValue(this.getEditor().getBillCardPanel(), "check_opinion", null);
    } else {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
      "0pubapp-0126")/* @res "数据错误" */);
    }
  }
  //处理单据号
  protected void dealBillCode(AbstractBill aggvo){
	  
  }

  public List<List<String>> getBodyFieldsWithVersionList() {
    return this.bodyFieldsWithVersionList;
  }

  public List<String> getHeadTailFieldsWithVersionList() {
    // 项目的表头都有”pk_org“，所以直接加上
    this.headTailFieldsWithVersionList.add(CommonKeyConst.PK_ORG);
    return this.headTailFieldsWithVersionList;
  }

  public void loadNewVersion() {
    BillData billData = this.getEditor().getBillCardPanel().getBillData();
    // getHeadTailFieldsWithVersionList().add("pk_org");
    for (String field : this.getHeadTailFieldsWithVersionList()) {
      billData.loadEditHeadRelation(field);
    }

    if (!ListUtil.isEmpty(this.getBodyFieldsWithVersionList())) {

      for (List<String> fieldList : this.getBodyFieldsWithVersionList()) {
        String tabCode = fieldList.get(0);
        String field = fieldList.get(1);
        BillModel bodyModel = billData.getBillModel(tabCode);
        if (bodyModel != null) {
          int rowCount = bodyModel.getRowCount();
          if (rowCount > 0) {
            bodyModel.loadEditRelationItemValue(0, rowCount - 1, field);
          }
        }
      }
    }
  }

  public void setBodyFieldsWithVersionList(List<List<String>> bodyFieldsWithVersionList) {
    this.bodyFieldsWithVersionList = bodyFieldsWithVersionList;
  }

  public void setHeadTailFieldsWithVersionList(List<String> headTailFieldsWithVersionList) {
    this.headTailFieldsWithVersionList = headTailFieldsWithVersionList;
  }
}
