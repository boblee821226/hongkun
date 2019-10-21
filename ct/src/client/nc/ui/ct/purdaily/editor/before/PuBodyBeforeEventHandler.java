package nc.ui.ct.purdaily.editor.before;

import java.util.Map;

import nc.ui.ct.editor.before.body.Caltaxmny;
import nc.ui.ct.editor.before.body.Castunit;
import nc.ui.ct.editor.before.body.Cffileid;
import nc.ui.ct.editor.before.body.ChgReason;
import nc.ui.ct.editor.before.body.Cqtunitid;
import nc.ui.ct.editor.before.body.CtTaxcode;
import nc.ui.ct.editor.before.body.ExecReason;
import nc.ui.ct.editor.before.body.Marbasclass;
import nc.ui.ct.editor.before.body.Material;
import nc.ui.ct.editor.before.body.Memo;
import nc.ui.ct.editor.before.body.NastNum;
import nc.ui.ct.editor.before.body.Nqtunitrate;
import nc.ui.ct.editor.before.body.PriceScheme;
import nc.ui.ct.editor.before.body.Productor;
import nc.ui.ct.editor.before.body.Qualitylevel;
import nc.ui.ct.editor.before.body.Term;
import nc.ui.ct.editor.before.body.Transpmodeid;
import nc.ui.ct.editor.before.body.PuVbdef01;
import nc.ui.ct.editor.before.body.VChangeRate;
import nc.ui.ct.editor.handler.AbstractBodyBeforeEditEventHandler;
import nc.ui.ct.editor.listener.IBodyBeforeEditEventListener;
import nc.ui.ct.purdaily.editor.before.body.CtPriceInfo;
import nc.ui.ct.purdaily.editor.before.body.ctpayment.CTEffectAddMonth;
import nc.ui.ct.purdaily.editor.before.body.ctpayment.CTEffectMonth;
import nc.vo.ct.entity.CtAbstractBVO;
import nc.vo.ct.entity.CtAbstractChangeVO;
import nc.vo.ct.entity.CtAbstractExecVO;
import nc.vo.ct.entity.CtAbstractTermVO;
import nc.vo.ct.pub.CTVatNameConst;
import nc.vo.ct.pub.CtPuTableCode;
import nc.vo.ct.purdaily.entity.CtPaymentVO;
import nc.vo.ct.purdaily.entity.CtPuBVO;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * <p>
 * <b>采购合同表体编辑前事件的处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-1-24 下午05:00:09
 */
public class PuBodyBeforeEventHandler extends
    AbstractBodyBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IBodyBeforeEditEventListener> listenerMap) {
    // 物料基本-物料
    listenerMap.put(CtAbstractBVO.PK_MATERIAL, new Material());
    // 物料基本-物料基本分类
    listenerMap.put(CtAbstractBVO.PK_MARBASCLASS, new Marbasclass());
    // 物料基本-运输方式
    listenerMap.put(CtAbstractBVO.CTRANSPMODEID, new Transpmodeid());
    // 物料基本-生产厂商
    listenerMap.put(CtAbstractBVO.CPRODUCTORID, new Productor());
    // 物料基本-质量等级
    listenerMap.put(CtAbstractBVO.CQUALITYLEVELID, new Qualitylevel());
    // 报价单位换算率
    listenerMap.put(CtAbstractBVO.VQTUNITRATE, new Nqtunitrate());
    // 物料基本-换算率
    listenerMap.put(CtAbstractBVO.VCHANGERATE, new VChangeRate());
    // 物料基本-单位
    listenerMap.put(CtAbstractBVO.CASTUNITID, new Castunit());
    // 物料基本-数量
    listenerMap.put(CtAbstractBVO.NASTNUM, new NastNum());
    // 物料基本-报价单位
    listenerMap.put(CtAbstractBVO.CQTUNITID, new Cqtunitid());
    // 变更历史-变更原因
    listenerMap.put(CtAbstractChangeVO.VMEMO,
        new Memo(CtPuTableCode.CTPUCHANGE));
    // 变更历史-备注
    listenerMap.put(CtAbstractChangeVO.VCHGREASON, new ChgReason());
    // 执行过程-原因
    listenerMap.put(CtAbstractExecVO.VEXECREASON, new ExecReason());
    // 优质优价方案
    listenerMap.put(CtPuBVO.CQPBASESCHEMEID, new PriceScheme());
    // 税码
    listenerMap.put(CTVatNameConst.CTAXCODEID, new CtTaxcode(
        CTBillType.PurDaily.getCode()));
    // 计税金额
    listenerMap.put(CTVatNameConst.NCALTAXMNY, new Caltaxmny());
    // 合同价格信息表
    listenerMap.put(CtPuBVO.PK_CT_PRICE, new CtPriceInfo());

    // 生效月
    listenerMap.put(CtPaymentVO.EFFECTMONTH, new CTEffectMonth());
    // 附加月
    listenerMap.put(CtPaymentVO.EFFECTADDMONTH, new CTEffectAddMonth());
    // 合同条款
    listenerMap.put(CtAbstractTermVO.VTERMCODE, new Term());
    // 特征码
    listenerMap.put(CtAbstractBVO.CFFILEID, new Cffileid());
    
    /**
     * HK
     * 2019年10月21日 09点42分
     * 表体自定义项01 为 收支项目，进行过滤 只显示出 支出类，并且是非停用的
     */
    listenerMap.put(CtAbstractBVO.VBDEF1, new PuVbdef01());
    /***END***/
  }
}
