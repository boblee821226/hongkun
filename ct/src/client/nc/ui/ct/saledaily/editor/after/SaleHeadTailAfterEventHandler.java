package nc.ui.ct.saledaily.editor.after;

import java.util.Map;

import nc.ui.ct.editor.after.head.CBuyOrigcurrency;
import nc.ui.ct.editor.after.head.Changerate;
import nc.ui.ct.editor.after.head.CtType;
import nc.ui.ct.editor.after.head.CtVdef7;
import nc.ui.ct.editor.after.head.InvalliDate;
import nc.ui.ct.editor.after.head.Person;
import nc.ui.ct.editor.after.head.SubscribeDate;
import nc.ui.ct.editor.after.head.ValDate;
import nc.ui.ct.editor.handler.AbstractHeadTailAfterEditEventHandler;
import nc.ui.ct.editor.listener.IHeadTailAfterEditEventListener;
import nc.ui.ct.saledaily.editor.after.head.Customers;
import nc.ui.ct.saledaily.editor.after.head.PayTerm;
import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ����ۺ�ͬ��ͷ��β�༭���¼�</b>
 * 
 * @author wangfengd
 * @time 2010-6-17 ����09:56:37
 */
public class SaleHeadTailAfterEventHandler extends
    AbstractHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IHeadTailAfterEditEventListener> listenerMap) {
    // �ͻ�
    listenerMap.put(CtSaleVO.PK_CUSTOMER, new Customers());
    // ��Ա
    listenerMap.put(CtAbstractVO.PERSONNELID, new Person());
    // ��ͬǩ������
    listenerMap.put(CtAbstractVO.SUBSCRIBEDATE, new SubscribeDate());
    // �ƻ���Ч����
    listenerMap.put(CtAbstractVO.VALDATE, new ValDate());
    // �ƻ���ֹ����
    listenerMap.put(CtAbstractVO.INVALLIDATE, new InvalliDate());
    // ��������
    listenerMap.put(CtAbstractVO.CTRANTYPEID, new CtType());
    // �۱�����
    listenerMap.put(CtAbstractVO.NEXCHANGERATE, new Changerate());
    // ȫ�ֱ�λ�һ���
    listenerMap.put(CtAbstractVO.NGLOBALEXCHGRATE, new Changerate());
    // ���ű�λ�һ���
    listenerMap.put(CtAbstractVO.NGROUPEXCHGRATE, new Changerate());
    // ����
    listenerMap.put(CtAbstractVO.CORIGCURRENCYID, new CBuyOrigcurrency());
    // �տ�Э��
    listenerMap.put(CtAbstractVO.PK_PAYTERM, new PayTerm());
    /**
     * HK
     * 2018��10��8��14:06:04
     * �ɷѷ�ʽ��VDEF7���༭��
     */
    listenerMap.put(CtAbstractVO.VDEF7, new CtVdef7());
    /***END***/
  }

}
