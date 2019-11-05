package nc.ui.ct.purdaily.editor.after.head;

import java.util.List;

import javax.swing.Action;

import nc.itf.scmpub.reference.uap.bd.payment.PaymentService;
import nc.ui.ct.editor.listener.IHeadTailAfterEditEventListener;
import nc.ui.ct.util.CardEditorHelper;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.bd.payment.PaymentChVO;
import nc.vo.bd.payment.PaymentVO;
import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ct.pub.CtPuTableCode;
import nc.vo.ct.purdaily.entity.CtPaymentVO;
import nc.vo.pub.SuperVO;
import nc.vo.scmpub.util.ArrayUtil;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b><br>
 * 采购合同表头付款协议处理事件
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.5
 * @since 6.5
 * @author gaorw5
 * @time 2014-10-16 上午9:47:00
 */
public class PayTerm implements IHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    List<Action> bodyActions =
        ((BillCardPanel) event.getBillCardPanel()).getBillForm()
            .getBodyActionMap().get(CtPuTableCode.CTPUPM);
    for (Action action : bodyActions) {
      /**
       * 传什么状态都可以，action中是根据cardpanel中有没有付款协议的值设置的可用性，这里就是让action重新判断一下
       */
      action.setEnabled(true);
    }
    CardEditorHelper helper =
        CardEditorHelper.getInstance(event.getBillCardPanel());
    String pk_payterm = (String) helper.getHeadValue(CtAbstractVO.PK_PAYTERM);
    if (StringUtils.isEmpty(pk_payterm)) {
      // 清空付款协议页签表体
      helper.clearBodyTabValue(CtPuTableCode.CTPUPM);
    }
    else {
      // 查询表头付款协议对应表体
      SuperVO[] vos = PaymentService.queryPaymentByIds(new String[] {
        pk_payterm
      }, PaymentVO.class);
      if (ArrayUtil.isEmpty(vos)) {
        return;
      }
      PaymentVO pvo = (PaymentVO) vos[0];
      PaymentChVO[] pchvos = pvo.getPaymentch();
      // 清空付款协议页签行，按新选择的付款协议进行增行处理
      helper.clearBodyTabValue(CtPuTableCode.CTPUPM);

      // 表头组织、集团信息
      String pk_group = helper.getHeadStringValue(CtAbstractVO.PK_GROUP);
      String pk_org = helper.getHeadStringValue(CtAbstractVO.PK_ORG);
      String pk_org_v = helper.getHeadStringValue(CtAbstractVO.PK_ORG_V);

      String[][] fields = {
        {
          CtPaymentVO.SHOWORDER, PaymentChVO.SHOWORDER
        }, /** 付款期 */
        {
          CtPaymentVO.ACCRATE, PaymentChVO.ACCRATE
        }/** 付款比例 */
        , {
          CtPaymentVO.PREPAYMENT, PaymentChVO.PREPAYMENT
        }/** 预付款 */
        , {
          CtPaymentVO.PK_PAYPERIOD, PaymentChVO.PK_PAYPERIOD
        }/** 起效日期 */
        , {
          CtPaymentVO.EFFECTDATEADDDATE, PaymentChVO.EFFECTDATEADDDATE
        }/** 起效日期延迟天数 */
        , {
          CtPaymentVO.PAYMENTDAY, PaymentChVO.PAYMENTDAY
        }/** 账期天数 */
        , {
          CtPaymentVO.CHECKDATA, PaymentChVO.CHECKDATA
        }/** 固定结账日 */
        , {
          CtPaymentVO.EFFECTMONTH, PaymentChVO.EFFECTMONTH
        }/** 生效月 */
        , {
          CtPaymentVO.EFFECTADDMONTH, PaymentChVO.EFFECTADDMONTH
        }/** 附加月 */
        , {
          CtPaymentVO.PK_BALATYPE, PaymentChVO.PK_BALATYPE
        }/** 结算方式 */
        , {
          CtPaymentVO.ISDEPOSIT, PaymentChVO.ISDEPOSIT
        }/** 质保金 */
        , {
          CtPaymentVO.PK_RATE, PaymentChVO.PK_RATE
        }, {
          CtPaymentVO.OUTACCOUNTDATE, PaymentChVO.ACCOUNTDAY
        }

      };
      for (int row = 0; row < pchvos.length; row++) {
        helper.addBodyLine(CtPuTableCode.CTPUPM);
        for (String[] field : fields) {
          helper.setBodyValue(row, field[0],
              pchvos[row].getAttributeValue(field[1]), CtPuTableCode.CTPUPM);
        }
        helper.setBodyValue(CtAbstractVO.PK_GROUP, pk_group,
            CtPuTableCode.CTPUPM);
        helper.setBodyValue(CtAbstractVO.PK_ORG, pk_org, CtPuTableCode.CTPUPM);
        helper.setBodyValue(CtAbstractVO.PK_ORG_V, pk_org_v,
            CtPuTableCode.CTPUPM);

        helper.getEditor().getBillModel(CtPuTableCode.CTPUPM)
            .loadLoadRelationItemValue(row, CtPaymentVO.PK_PAYPERIOD);
        helper.getEditor().getBillModel(CtPuTableCode.CTPUPM)
            .loadLoadRelationItemValue(row, CtPaymentVO.PK_BALATYPE);
      }
    }
  }
}
