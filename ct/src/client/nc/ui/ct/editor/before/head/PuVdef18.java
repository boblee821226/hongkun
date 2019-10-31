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
     */
    panel.setWhereString(" (innercode like '866S__%') ");
    /***END***/
  }
}
