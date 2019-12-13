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
     * 2019年10月21日 10点14分
     * 收支项目 只过滤出 支出的
     * 2019年12月13日15:25:12
     * 只显示出 已启用的
     */
    panel.setWhereString(" (innercode like '866S__%' and enablestate = 2) ");
    /***END***/
  }

}
