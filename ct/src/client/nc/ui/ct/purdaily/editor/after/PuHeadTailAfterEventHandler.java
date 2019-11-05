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
 * 采购合同表头表尾编辑后事件的处理类 <b>本类主要完成以下功能：</b> 卡片表头表体编辑后
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-4-14 上午09:01:58
 */
public class PuHeadTailAfterEventHandler extends
    AbstractHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IHeadTailAfterEditEventListener> listenerMap) {
    // 供应商
    listenerMap.put(CtPuVO.CVENDORID, new Vendor());
    // 合同签订日期
    listenerMap.put(CtAbstractVO.SUBSCRIBEDATE, new SubscribeDate());
    // 计划生效日期
    listenerMap.put(CtAbstractVO.VALDATE, new ValDate());
    // 计划终止日期
    listenerMap.put(CtAbstractVO.INVALLIDATE, new InvalliDate());
    // 交易类型
    listenerMap.put(CtAbstractVO.CTRANTYPEID, new CtType());
    // 折本汇率
    listenerMap.put(CtAbstractVO.NEXCHANGERATE, new Changerate());
    // 全局本位币汇率
    listenerMap.put(CtAbstractVO.NGLOBALEXCHGRATE, new Changerate());
    // 集团本位币汇率
    listenerMap.put(CtAbstractVO.NGROUPEXCHGRATE, new Changerate());
    // 币种
    listenerMap.put(CtAbstractVO.CORIGCURRENCYID, new PuCorigcurrencyid());
    // 付款协议
    listenerMap.put(CtAbstractVO.PK_PAYTERM, new PayTerm());
    
    /**
     * 自定义19-涨租方式
     */
    listenerMap.put(CtAbstractVO.VDEF19, new nc.ui.ct.purdaily.editor.after.head.VDEF19());
    /***END***/
  }

}
