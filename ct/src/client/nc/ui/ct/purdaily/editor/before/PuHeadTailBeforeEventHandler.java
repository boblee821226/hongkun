package nc.ui.ct.purdaily.editor.before;

import java.util.Map;

import nc.ui.ct.editor.before.head.CTNoriprepaylimit;
import nc.ui.ct.editor.before.head.DeliaddrFilter;
import nc.ui.ct.editor.before.head.DeptFilter;
import nc.ui.ct.editor.before.head.ExChangeRate;
import nc.ui.ct.editor.before.head.FilterPayterm;
import nc.ui.ct.editor.before.head.GlobalExChangeRate;
import nc.ui.ct.editor.before.head.GroupExChangeRate;
import nc.ui.ct.editor.before.head.Personal;
import nc.ui.ct.editor.before.head.ProjectFilter;
import nc.ui.ct.editor.before.head.PuVdef13;
import nc.ui.ct.editor.before.head.PuVdef17;
import nc.ui.ct.editor.before.head.PuVdef18;
import nc.ui.ct.editor.before.head.Supplier;
import nc.ui.ct.editor.handler.AbstractHeadTailBeforeEditEventHandler;
import nc.ui.ct.editor.listener.IHeadTailBeforeEditEventListener;
import nc.ui.ct.purdaily.editor.before.head.Bsc;
import nc.ui.ct.purdaily.editor.before.head.PurdailyType;
import nc.vo.ct.ap.entity.CtApVO;
import nc.vo.ct.entity.CtAbstractVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b> ��ͷ����༭ǰ�¼��ַ���
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-4-14 ����08:40:37
 */
public class PuHeadTailBeforeEventHandler extends
    AbstractHeadTailBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IHeadTailBeforeEditEventListener> listenerMap) {
    // ��������
    listenerMap.put(CtAbstractVO.CTRANTYPEID, new PurdailyType());
    // �۱�����
    listenerMap.put(CtAbstractVO.NEXCHANGERATE, new ExChangeRate());
    // ���ű�λ�һ���
    listenerMap.put(CtAbstractVO.NGROUPEXCHGRATE, new GroupExChangeRate());
    // ȫ�ֱ�λ�һ���
    listenerMap.put(CtAbstractVO.NGLOBALEXCHGRATE, new GlobalExChangeRate());
    // ��Ա
    listenerMap.put(CtAbstractVO.PERSONNELID, new Personal());
    // �����ص�
    listenerMap.put(CtAbstractVO.DELIADDR, new DeliaddrFilter());
    // ����
    listenerMap.put(CtAbstractVO.DEPID_V, new DeptFilter());
    // ��Ŀ
    listenerMap.put(CtAbstractVO.CPROJECTID, new ProjectFilter());
    // ��Ӧ��
    listenerMap.put(CtApVO.CVENDORID, new Supplier());
    // ����Э��
    listenerMap.put(CtAbstractVO.PK_PAYTERM, new FilterPayterm());
    // ί���ʶ
    listenerMap.put(CtAbstractVO.BSC, new Bsc());
    // Ԥ���޶�
    listenerMap.put(CtAbstractVO.NORIPREPAYLIMITMNY, new CTNoriprepaylimit());
    
    /**
     * HK
     * 2019��10��30�� 10��40��
     * ��ͷ�Զ�����13��18 Ϊ ��֧��Ŀ�����й��� ֻ��ʾ�� ֧���࣬�����Ƿ�ͣ�õ�
     * 2020��1��8��15:04:39
     * 13 �� 17 ��ͬ���ֱ�Ϊ ��֧��Ŀ1��2
     * 18 Ϊ ��֤����Ŀ
     * 2020��12��8��16:40:20
     * ȥ����֧��Ŀ�Ĺ��ˣ�13��18��
     */
//    listenerMap.put(CtAbstractVO.VDEF13, new PuVdef13());
    // 20200225�����ĵ�
//    listenerMap.put(CtAbstractVO.VDEF17, new PuVdef17());
//    listenerMap.put(CtAbstractVO.VDEF18, new PuVdef18());
    /***END***/
  }
}
