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
 * ���
 */
@SuppressWarnings("restriction")
public class ReverseAction extends CopyAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * ��ͷ���汾�ֶ�
   */
  private List<String> headTailFieldsWithVersionList = new ArrayList<String>();

  /**
   * ������汾�ֶ�
   */
  private List<List<String>> bodyFieldsWithVersionList;

  public ReverseAction() {
	  super();
	  this.setBtnName("���");
	  this.setCode("reverseAction");
  }
  
  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object object = this.getModel().getSelectedData();
    if (object instanceof AbstractBill) {
    	FeeBalanceBillVO aggvo = (FeeBalanceBillVO) CloneUtil.deepClone(object);
    	// ֻ���� ���̬ �� ������0�������� �Ǻ�嵥�ģ��ſ��Խ��к�塣
    	FeeBalanceHeadVO hVO = (FeeBalanceHeadVO)aggvo.getParentVO();
    	if (hVO.getBill_status() != 1 
    	|| hVO.getMoney().compareTo(UFDouble.ZERO_DBL) <= 0
    	|| hVO.getDef20() != null
    	) {
    		throw new Exception("�����̬�����С��0����嵥�����ܽ��к�������");
    	}
      if (this.getCopyActionProcessor() != null) {
    	  // ���ݽ���
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
      "0pubapp-0126")/* @res "���ݴ���" */);
    }
  }
  //�����ݺ�
  protected void dealBillCode(AbstractBill aggvo){
	  
  }

  public List<List<String>> getBodyFieldsWithVersionList() {
    return this.bodyFieldsWithVersionList;
  }

  public List<String> getHeadTailFieldsWithVersionList() {
    // ��Ŀ�ı�ͷ���С�pk_org��������ֱ�Ӽ���
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
