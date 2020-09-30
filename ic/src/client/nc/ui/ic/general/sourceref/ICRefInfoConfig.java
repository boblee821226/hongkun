package nc.ui.ic.general.sourceref;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.scmpub.res.billtype.ICBillType;

public class ICRefInfoConfig {

  // <上游单据类型+TO+下游单据类型，配置文件refinfo的路径>
  private static Map<String, String> refInfoMap = new HashMap<String, String>();

  static {
    // 库存采购入库单==》采购发票
    ICRefInfoConfig.refInfoMap.put("45TO25",
        "nc/ui/ic/m45/sourceref/refinfo_for25.xml");
    // 库存采购入库单==》采购订单
    ICRefInfoConfig.refInfoMap.put("45TO21",
        "nc/ui/ic/m45/sourceref/refinfo_for21.xml");
    // 库存采购入库单==》销售出库
    ICRefInfoConfig.refInfoMap.put("45TO4C",
        "nc/ui/ic/m45/sourceref/refinfo_for4C.xml");
    // 库存采购入库单==》采购价格结算单
    ICRefInfoConfig.refInfoMap.put("45TO24",
        "nc/ui/ic/m45/sourceref/refinfo_for24.xml");
    // 库存调拨出库单==》库存调拨入库单
    ICRefInfoConfig.refInfoMap.put("4YTO4E",
        "nc/ui/ic/m4y/sourceref/refinfo4YFor4E.xml");
    // 库存销售出库单==》销售发票
    ICRefInfoConfig.refInfoMap.put("4CTO32",
        "nc/ui/ic/m4c/sourceref/refinfo4CFor32.xml");
    // 库存销售出库单==》销售订单
    ICRefInfoConfig.refInfoMap.put("4CTO30",
        "nc/ui/ic/m4c/sourceref/refinfo4CFor30.xml");
    // 库存销售出库单==》运输单
    ICRefInfoConfig.refInfoMap.put("4CTO4804",
        "nc/ui/ic/m4c/sourceref/refinfo4CFor4804.xml");
    //库存其他出库单==》运输单
    ICRefInfoConfig.refInfoMap.put("4ITO4804",
        "nc/ui/ic/m4i/sourceref/refinfo4IFor4804.xml");
    // 库存销售出库单==》质证书
    ICRefInfoConfig.refInfoMap.put("4CTOC006",
        "nc/ui/ic/m4c/sourceref/refinfo4CForC006.xml");
    // 库存借出 to 销售订单
    ICRefInfoConfig.refInfoMap.put("4HTO30",
        "nc/ui/ic/m4h/sourceref/ref4hto30.xml");
    // 库存借入==》采购订单
    ICRefInfoConfig.refInfoMap.put("49TO21",
        "nc/ui/ic/m49/sourceref/refinfo49For21.xml");
    // 库存销售入库==》签收途损单
    ICRefInfoConfig.refInfoMap.put("4CTO4453",
        "nc/ui/ic/m4c/sourceref/ref4CFor4453.xml");
    // 库存消耗汇总单==》采购发票
    ICRefInfoConfig.refInfoMap.put("50TO25",
        "nc/ui/ic/m50/sourceref/refinfo_for25.xml");
    // 其它入库单==》其它出库单
    ICRefInfoConfig.refInfoMap.put(ICBillType.GeneralIn.getCode(),
        "nc/ui/ic/m4a/sourceref/refinfoInToOut.xml");
    // 调拨出库==>>签收途损
    ICRefInfoConfig.refInfoMap.put("4YTO4453",
        "nc/ui/ic/m4y/sourceref/refinfo4YFor4453.xml");
    // 库存出库单==>>拣货单,使用的同一个配置文件
    ICRefInfoConfig.refInfoMap.put("4CTO4V",
        "nc/ui/ic/m4v/sourceref/ref4CFor4V.xml");
    ICRefInfoConfig.refInfoMap.put("4ITO4V",
        "nc/ui/ic/m4v/sourceref/ref4CFor4V.xml");
    ICRefInfoConfig.refInfoMap.put("4YTO4V",
        "nc/ui/ic/m4v/sourceref/ref4CFor4V.xml");
    ICRefInfoConfig.refInfoMap.put("4DTO4V",
        "nc/ui/ic/m4v/sourceref/ref4CFor4V.xml");
    ICRefInfoConfig.refInfoMap.put("4FTO4V",
        "nc/ui/ic/m4v/sourceref/ref4CFor4V.xml");
    ICRefInfoConfig.refInfoMap.put("4451TO4V",
        "nc/ui/ic/m4v/sourceref/ref4CFor4V.xml");

    // 库存委托加工入库==>>采购发票
    ICRefInfoConfig.refInfoMap.put("47TO25",
        "nc/ui/ic/m47/sourceref/refinfo47For25.xml");
    // 调拨出库==>>运输单
    ICRefInfoConfig.refInfoMap.put("4YTO4804",
        "nc/ui/ic/m4y/sourceref/refinfo4YFor4804.xml");
    // 调拨出库==>>质证书
    ICRefInfoConfig.refInfoMap.put("4YTOC006",
        "nc/ui/ic/m4y/sourceref/refinfo4YForC006.xml");
    // 出库申请单==>>材料出库
    ICRefInfoConfig.refInfoMap.put("4455TO4D",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4D.xml");
    // 出库申请单==>>其他出库
    ICRefInfoConfig.refInfoMap.put("4455TO4I",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4I.xml");
    // 出库申请单==>>借出单
    ICRefInfoConfig.refInfoMap.put("4455TO4H",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4H.xml");
    // 出库申请单==>>报废单
    ICRefInfoConfig.refInfoMap.put("4455TO4O",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4O.xml");
    // 材料出库单==>>质证书
    ICRefInfoConfig.refInfoMap.put("4DTOC006",
        "nc/ui/ic/m4d/sourceref/refinfo4DForC006.xml");
    // 材料出库单==>>工单
    ICRefInfoConfig.refInfoMap.put("4DTO4B36",
        "nc/ui/ic/m4d/sourceref/refinfo4DFor4B36.xml");
    
    /**
     * HK 2020年9月28日18:03:55
     */
    ICRefInfoConfig.refInfoMap.put("4455TO4K",
            "nc/ui/ic/m4455/sourceref/refinfo4455For4K.xml");
    /***END***/
  }

  public static String getRefInfoLocation(String srcBillType,
      String currBillType) {
    // 处理单前单据类型是交易类型的情况(交易类型发布的节点)
    String destBilltype = PfServiceScmUtil.getBillTypeByTransType(currBillType);
    String refInfoLocation =
        ICRefInfoConfig.refInfoMap.get(srcBillType + "TO" + destBilltype);
    if (refInfoLocation == null
        && ICBillType.GeneralIn.getCode().equals(srcBillType)) {
      refInfoLocation = ICRefInfoConfig.refInfoMap.get(srcBillType);
    }
    return refInfoLocation;
  }
}
