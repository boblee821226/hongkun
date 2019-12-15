package nc.ui.ct.saledaily.editor.after;

import java.util.Map;

import nc.ui.ct.editor.after.body.CastUnitId;
import nc.ui.ct.editor.after.body.ChangeRate;
import nc.ui.ct.editor.after.body.CqtUnitId;
import nc.ui.ct.editor.after.body.CtTaxcode;
import nc.ui.ct.editor.after.body.DelivDate;
import nc.ui.ct.editor.after.body.MarBasClass;
import nc.ui.ct.editor.after.body.ReceiveAddress;
import nc.ui.ct.editor.after.body.Term;
import nc.ui.ct.editor.after.body.TermVhkbdef4;
import nc.ui.ct.editor.after.body.TermVhkbdef6;
import nc.ui.ct.editor.after.body.TermVhkbdef7;
import nc.ui.ct.editor.after.body.TermVhkbdef8;
import nc.ui.ct.editor.handler.AbstractBodyAfterEditEventHandler;
import nc.ui.ct.editor.listener.AbstractRelationCalculateListener;
import nc.ui.ct.editor.listener.IBodyAfterEditEventListener;
import nc.ui.ct.saledaily.editor.after.body.AccountDay;
import nc.ui.ct.saledaily.editor.after.body.Accrate;
import nc.ui.ct.saledaily.editor.after.body.CTSaleCountry;
import nc.ui.ct.saledaily.editor.after.body.CTSaleCustMarInfo;
import nc.ui.ct.saledaily.editor.after.body.CTSaleCustMaterial;
import nc.ui.ct.saledaily.editor.after.body.CTSaleMaterial;
import nc.ui.ct.saledaily.editor.after.body.CheckData;
import nc.ui.ct.saledaily.editor.after.body.Deposit;
import nc.ui.ct.saledaily.editor.after.body.EffectAddMonth;
import nc.ui.ct.saledaily.editor.after.body.EffectDateAddDate;
import nc.ui.ct.saledaily.editor.after.body.EffectMonth;
import nc.ui.ct.saledaily.editor.after.body.Financeorg;
import nc.ui.ct.saledaily.editor.after.body.PaymentDay;
import nc.ui.ct.saledaily.editor.after.body.PlanEffectDate;
import nc.ui.ct.saledaily.editor.after.body.PlanEndDate;
import nc.ui.ct.saledaily.editor.after.body.RealEffectDate;
import nc.ui.ct.saledaily.editor.after.body.RealEndDate;
import nc.vo.ct.entity.CtAbstractBVO;
import nc.vo.ct.entity.CtAbstractPayTermVO;
import nc.vo.ct.entity.CtAbstractTermVO;
import nc.vo.ct.pub.CTVatNameConst;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：销售合同表体编辑后事件处理类</b> 卡片形式下 表体编辑处理类
 * 
 * @author wangfengd
 * @time 2010-6-17 上午09:52:14
 */
public class SaleBodyAfterEventHandler extends
    AbstractBodyAfterEditEventHandler {

  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  @Override
  public void registerEventListener(
      Map<String, IBodyAfterEditEventListener> listenerMap) {
    // 计划收发货日期
    listenerMap.put(CtAbstractBVO.DELIVDATE, new DelivDate());
    // 物料
    listenerMap.put(CtAbstractBVO.PK_MATERIAL, new CTSaleMaterial());
    // 物料分类
    listenerMap.put(CtAbstractBVO.PK_MARBASCLASS, new MarBasClass());
    // 收货地址
    listenerMap.put(CtAbstractBVO.PK_RECEIVEADDRESS, new ReceiveAddress());
    // 报价换算率
    listenerMap.put(CtAbstractBVO.VQTUNITRATE, new ChangeRate(
        CtAbstractBVO.VQTUNITRATE));
    // 换算率
    listenerMap.put(CtAbstractBVO.VCHANGERATE, new ChangeRate(
        CtAbstractBVO.VCHANGERATE));
    // 单位
    listenerMap.put(CtAbstractBVO.CASTUNITID, new CastUnitId());
    // 报价单位
    listenerMap.put(CtAbstractBVO.CQTUNITID, new CqtUnitId());
    // 国家
    listenerMap.put(CTVatNameConst.CSENDCOUNTRYID, new CTSaleCountry(
        CTVatNameConst.CSENDCOUNTRYID));
    listenerMap.put(CTVatNameConst.CRECECOUNTRYID, new CTSaleCountry(
        CTVatNameConst.CRECECOUNTRYID));
    listenerMap.put(CTVatNameConst.CTAXCOUNTRYID, new CTSaleCountry(
        CTVatNameConst.CTAXCOUNTRYID));
    // 税码
    listenerMap.put(CTVatNameConst.CTAXCODEID, new CtTaxcode(
        CTBillType.SaleDaily.getCode()));
    // 财务组织改变，报税国家变动
    listenerMap.put(CtAbstractBVO.PK_FINANCEORG_V, new Financeorg());
    // 客户物料码
    listenerMap.put(CtSaleBVO.CCUSTMATERIALID, new CTSaleCustMaterial());
    // 生产厂商
    listenerMap.put(CtAbstractBVO.CPRODUCTORID, new CTSaleCustMarInfo());

    // 固定结账日
    listenerMap.put(CtAbstractPayTermVO.CHECKDATA, new CheckData());
    // 生效月
    listenerMap.put(CtAbstractPayTermVO.EFFECTMONTH, new EffectMonth());
    // 附加月
    listenerMap.put(CtAbstractPayTermVO.EFFECTADDMONTH, new EffectAddMonth());
    // 账期天数
    listenerMap.put(CtAbstractPayTermVO.PAYMENTDAY, new PaymentDay());
    // 质保金
    listenerMap.put(CtAbstractPayTermVO.ISDEPOSIT, new Deposit());
    // 收款比例
    listenerMap.put(CtAbstractPayTermVO.ACCRATE, new Accrate());
    // 计划起效日
    listenerMap.put(CtAbstractPayTermVO.DPLANEFFECTDATE, new PlanEffectDate());
    // 计划到期日
    listenerMap.put(CtAbstractPayTermVO.DPLANENDDATE, new PlanEndDate());
    // 实际生效日
    listenerMap.put(CtAbstractPayTermVO.DREALEFFECTDATE, new RealEffectDate());
    // 实际到期日
    listenerMap.put(CtAbstractPayTermVO.DREALENDDATE, new RealEndDate());
    // 延期天数
    listenerMap.put(CtAbstractPayTermVO.EFFECTDATEADDDATE,
        new EffectDateAddDate());
    // 出账日
    listenerMap.put(CtAbstractPayTermVO.ACCOUNTDAY, new AccountDay());

    // 合同条款
    listenerMap.put(CtAbstractTermVO.VTERMCODE, new Term());

    // 自由项1-10
    listenerMap.put(CtAbstractBVO.VFREE1, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE2, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE3, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE4, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE5, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE6, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE7, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE8, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE9, new CTSaleCustMarInfo());
    listenerMap.put(CtAbstractBVO.VFREE10, new CTSaleCustMarInfo());
    
    /**
     * HK
     * 2018年10月8日16:14:42
     * 实际合同金额（vhkbdef6）编辑后 = 计算 实际单价
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF6, new TermVhkbdef6());
    /**
     * HK
     * 2018年10月8日23:41:02
     * 标准单价（vhkbdef4）编辑后 = 计算 标准 和 实际 合同金额，赋值 实际单价
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF4, new TermVhkbdef4());
    /**
     * HK
     * 2018年10月16日11:04:57
     * 实际计费天数（vhkbdef8）编辑后 = 计算 实际合同金额
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF8, new TermVhkbdef8());
    /**
     * HK
     * 2018年10月16日14:11:52
     * 实际单价（vhkbdef7）编辑后 = 计算 实际合同金额
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF7, new TermVhkbdef7());
  }
}
