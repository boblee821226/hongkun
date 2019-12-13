package nc.ui.ct.editor.before.body;

import nc.ui.ct.editor.listener.IBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.ct.entity.CtAbstractBVO;

public class PuVbdef01 implements IBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    UIRefPane panel =
        (UIRefPane) e.getBillCardPanel().getBillModel()
            .getItemByKey(CtAbstractBVO.VBDEF1).getComponent();
    /**
     * HK
     * 2019��10��21�� 10��14��
     * ��֧��Ŀ ֻ���˳� ֧����
     * 2019��12��13��15:25:12
     * ֻ��ʾ�� �����õ�
     */
    panel.setWhereString(" (innercode like '866S__%' and enablestate = 2) ");
    /***END***/
  }

}
