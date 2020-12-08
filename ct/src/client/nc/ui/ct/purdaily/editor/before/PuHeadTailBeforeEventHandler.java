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
 * <b>本类主要完成以下功能：</b> 表头表体编辑前事件分发器
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-4-14 上午08:40:37
 */
public class PuHeadTailBeforeEventHandler extends
    AbstractHeadTailBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IHeadTailBeforeEditEventListener> listenerMap) {
    // 交易类型
    listenerMap.put(CtAbstractVO.CTRANTYPEID, new PurdailyType());
    // 折本汇率
    listenerMap.put(CtAbstractVO.NEXCHANGERATE, new ExChangeRate());
    // 集团本位币汇率
    listenerMap.put(CtAbstractVO.NGROUPEXCHGRATE, new GroupExChangeRate());
    // 全局本位币汇率
    listenerMap.put(CtAbstractVO.NGLOBALEXCHGRATE, new GlobalExChangeRate());
    // 人员
    listenerMap.put(CtAbstractVO.PERSONNELID, new Personal());
    // 交货地点
    listenerMap.put(CtAbstractVO.DELIADDR, new DeliaddrFilter());
    // 部门
    listenerMap.put(CtAbstractVO.DEPID_V, new DeptFilter());
    // 项目
    listenerMap.put(CtAbstractVO.CPROJECTID, new ProjectFilter());
    // 供应商
    listenerMap.put(CtApVO.CVENDORID, new Supplier());
    // 付款协议
    listenerMap.put(CtAbstractVO.PK_PAYTERM, new FilterPayterm());
    // 委外标识
    listenerMap.put(CtAbstractVO.BSC, new Bsc());
    // 预付限额
    listenerMap.put(CtAbstractVO.NORIPREPAYLIMITMNY, new CTNoriprepaylimit());
    
    /**
     * HK
     * 2019年10月30日 10点40分
     * 表头自定义项13、18 为 收支项目，进行过滤 只显示出 支出类，并且是非停用的
     * 2020年1月8日15:04:39
     * 13 与 17 相同，分别为 收支项目1、2
     * 18 为 保证金项目
     * 2020年12月8日16:40:20
     * 去掉收支项目的过滤（13、18）
     */
//    listenerMap.put(CtAbstractVO.VDEF13, new PuVdef13());
    // 20200225问题文档
//    listenerMap.put(CtAbstractVO.VDEF17, new PuVdef17());
//    listenerMap.put(CtAbstractVO.VDEF18, new PuVdef18());
    /***END***/
  }
}
