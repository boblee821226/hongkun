package nc.ui.ct.purdaily.editor.after;

import java.util.Map;

import nc.ui.ct.editor.after.head.Changerate;
import nc.ui.ct.editor.after.head.CtType;
import nc.ui.ct.editor.after.head.InvalliDate;
import nc.ui.ct.editor.after.head.SubscribeDate;
import nc.ui.ct.editor.after.head.ValDate;
import nc.ui.ct.editor.handler.AbstractHeadTailAfterEditEventHandler;
import nc.ui.ct.editor.listener.IHeadTailAfterEditEventListener;
import nc.ui.ct.purdaily.editor.after.head.PayTerm;
import nc.ui.ct.purdaily.editor.after.head.PuCorigcurrencyid;
import nc.ui.ct.purdaily.editor.after.head.Vendor;
import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ct.purdaily.entity.CtPuVO;

/**
 * <p>
 * �ɹ���ͬ��ͷ��β�༭���¼��Ĵ����� <b>������Ҫ������¹��ܣ�</b> ��Ƭ��ͷ����༭��
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-4-14 ����09:01:58
 */
public class PuHeadTailAfterEventHandler extends
    AbstractHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IHeadTailAfterEditEventListener> listenerMap) {
    // ��Ӧ��
    listenerMap.put(CtPuVO.CVENDORID, new Vendor());
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
    listenerMap.put(CtAbstractVO.CORIGCURRENCYID, new PuCorigcurrencyid());
    // ����Э��
    listenerMap.put(CtAbstractVO.PK_PAYTERM, new PayTerm());
    
    /**
     * �Զ���19-���ⷽʽ
     */
    listenerMap.put(CtAbstractVO.VDEF19, new nc.ui.ct.purdaily.editor.after.head.VDEF19());
    /***END***/
  }

}
