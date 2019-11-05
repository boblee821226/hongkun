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
 * <b>������Ҫ������¹��ܣ�</b><br>
 * �ɹ���ͬ��ͷ����Э�鴦���¼�
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.5
 * @since 6.5
 * @author gaorw5
 * @time 2014-10-16 ����9:47:00
 */
public class PayTerm implements IHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    List<Action> bodyActions =
        ((BillCardPanel) event.getBillCardPanel()).getBillForm()
            .getBodyActionMap().get(CtPuTableCode.CTPUPM);
    for (Action action : bodyActions) {
      /**
       * ��ʲô״̬�����ԣ�action���Ǹ���cardpanel����û�и���Э���ֵ���õĿ����ԣ����������action�����ж�һ��
       */
      action.setEnabled(true);
    }
    CardEditorHelper helper =
        CardEditorHelper.getInstance(event.getBillCardPanel());
    String pk_payterm = (String) helper.getHeadValue(CtAbstractVO.PK_PAYTERM);
    if (StringUtils.isEmpty(pk_payterm)) {
      // ��ո���Э��ҳǩ����
      helper.clearBodyTabValue(CtPuTableCode.CTPUPM);
    }
    else {
      // ��ѯ��ͷ����Э���Ӧ����
      SuperVO[] vos = PaymentService.queryPaymentByIds(new String[] {
        pk_payterm
      }, PaymentVO.class);
      if (ArrayUtil.isEmpty(vos)) {
        return;
      }
      PaymentVO pvo = (PaymentVO) vos[0];
      PaymentChVO[] pchvos = pvo.getPaymentch();
      // ��ո���Э��ҳǩ�У�����ѡ��ĸ���Э��������д���
      helper.clearBodyTabValue(CtPuTableCode.CTPUPM);

      // ��ͷ��֯��������Ϣ
      String pk_group = helper.getHeadStringValue(CtAbstractVO.PK_GROUP);
      String pk_org = helper.getHeadStringValue(CtAbstractVO.PK_ORG);
      String pk_org_v = helper.getHeadStringValue(CtAbstractVO.PK_ORG_V);

      String[][] fields = {
        {
          CtPaymentVO.SHOWORDER, PaymentChVO.SHOWORDER
        }, /** ������ */
        {
          CtPaymentVO.ACCRATE, PaymentChVO.ACCRATE
        }/** ������� */
        , {
          CtPaymentVO.PREPAYMENT, PaymentChVO.PREPAYMENT
        }/** Ԥ���� */
        , {
          CtPaymentVO.PK_PAYPERIOD, PaymentChVO.PK_PAYPERIOD
        }/** ��Ч���� */
        , {
          CtPaymentVO.EFFECTDATEADDDATE, PaymentChVO.EFFECTDATEADDDATE
        }/** ��Ч�����ӳ����� */
        , {
          CtPaymentVO.PAYMENTDAY, PaymentChVO.PAYMENTDAY
        }/** �������� */
        , {
          CtPaymentVO.CHECKDATA, PaymentChVO.CHECKDATA
        }/** �̶������� */
        , {
          CtPaymentVO.EFFECTMONTH, PaymentChVO.EFFECTMONTH
        }/** ��Ч�� */
        , {
          CtPaymentVO.EFFECTADDMONTH, PaymentChVO.EFFECTADDMONTH
        }/** ������ */
        , {
          CtPaymentVO.PK_BALATYPE, PaymentChVO.PK_BALATYPE
        }/** ���㷽ʽ */
        , {
          CtPaymentVO.ISDEPOSIT, PaymentChVO.ISDEPOSIT
        }/** �ʱ��� */
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
