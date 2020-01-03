package nc.ui.ct.editor.before.head;

import nc.ui.ct.editor.listener.IHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.ct.entity.CtAbstractVO;

public class PuVdef13 implements IHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
	  UIRefPane panel =
        (UIRefPane) e.getBillCardPanel().getHeadItem(CtAbstractVO.VDEF13).getComponent();
    /**
     * HK
     * 2019��10��30�� 10��48��
     * ��֧��Ŀ ֻ���˳� ֧����
     * 2019��12��13��15:25:12
     * ֻ��ʾ�� �����õ�
     * 2020��1��3��17:43:08
     * �����տ��࣬����ѡ���ĩ��
     */
    panel.setWhereString(" ((innercode like '866S__%' or innercode like 'YKGL__%') and enablestate = 2) ");
    panel.getRefModel().setNotLeafSelectedEnabled(false);
    /***END***/
  }
}
