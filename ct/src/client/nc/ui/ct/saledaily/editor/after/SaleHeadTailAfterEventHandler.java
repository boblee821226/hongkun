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
 * <b>本类主要完成以下功能：销售合同表头表尾编辑后事件</b>
 * 
 * @author wangfengd
 * @time 2010-6-17 上午09:56:37
 */
public class SaleHeadTailAfterEventHandler extends
    AbstractHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IHeadTailAfterEditEventListener> listenerMap) {
    // 客户
    listenerMap.put(CtSaleVO.PK_CUSTOMER, new Customers());
    // 人员
    listenerMap.put(CtAbstractVO.PERSONNELID, new Person());
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
    listenerMap.put(CtAbstractVO.CORIGCURRENCYID, new CBuyOrigcurrency());
    // 收款协议
    listenerMap.put(CtAbstractVO.PK_PAYTERM, new PayTerm());
    /**
     * HK
     * 2018年10月8日14:06:04
     * 缴费方式（VDEF7）编辑后
     */
    listenerMap.put(CtAbstractVO.VDEF7, new CtVdef7());
    /***END***/
  }

}
