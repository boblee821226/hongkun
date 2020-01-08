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
     * 2019年10月30日 10点48分
     * 收支项目 只过滤出 支出的
     * 2019年12月13日15:25:12
     * 只显示出 已启用的
     * 2020年1月3日17:43:08
     * 增加收款类，不让选择非末级
     * 2020年1月8日15:10:01
     * 支出项目1
     */
    panel.setWhereString(" ((innercode like '866S__%' or innercode like 'YKGL__%') and enablestate = 2) ");
    panel.getRefModel().setNotLeafSelectedEnabled(false);
    /***END***/
  }
}
