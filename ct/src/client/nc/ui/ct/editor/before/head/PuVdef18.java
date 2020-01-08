package nc.ui.ct.editor.before.head;

import nc.ui.ct.editor.listener.IHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.ct.entity.CtAbstractVO;

public class PuVdef18 implements IHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
	  UIRefPane panel =
        (UIRefPane) e.getBillCardPanel().getHeadItem(CtAbstractVO.VDEF18).getComponent();
    /**
     * HK
     * 2019��10��30�� 10��48��
     * ��֧��Ŀ ֻ���˳� ֧����
     * 2019��12��13��15:25:12
     * ֻ��ʾ�� �����õ�
     * 2020��1��3��17:43:08
     * �����տ��࣬����ѡ���ĩ��
     * 2020��1��8��15:10:06
     * ��֤����Ŀ��ֻȡ��֤���
     */
    panel.setWhereString(" (name like '��֤��%' and enablestate = 2) ");
    panel.getRefModel().setNotLeafSelectedEnabled(false);
    /***END***/
  }
}
