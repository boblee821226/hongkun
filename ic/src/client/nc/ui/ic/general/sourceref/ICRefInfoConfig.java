package nc.ui.ic.general.sourceref;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.scmpub.res.billtype.ICBillType;

public class ICRefInfoConfig {

  // <���ε�������+TO+���ε������ͣ������ļ�refinfo��·��>
  private static Map<String, String> refInfoMap = new HashMap<String, String>();

  static {
    // ���ɹ���ⵥ==���ɹ���Ʊ
    ICRefInfoConfig.refInfoMap.put("45TO25",
        "nc/ui/ic/m45/sourceref/refinfo_for25.xml");
    // ���ɹ���ⵥ==���ɹ�����
    ICRefInfoConfig.refInfoMap.put("45TO21",
        "nc/ui/ic/m45/sourceref/refinfo_for21.xml");
    // ���ɹ���ⵥ==�����۳���
    ICRefInfoConfig.refInfoMap.put("45TO4C",
        "nc/ui/ic/m45/sourceref/refinfo_for4C.xml");
    // ���ɹ���ⵥ==���ɹ��۸���㵥
    ICRefInfoConfig.refInfoMap.put("45TO24",
        "nc/ui/ic/m45/sourceref/refinfo_for24.xml");
    // ���������ⵥ==����������ⵥ
    ICRefInfoConfig.refInfoMap.put("4YTO4E",
        "nc/ui/ic/m4y/sourceref/refinfo4YFor4E.xml");
    // ������۳��ⵥ==�����۷�Ʊ
    ICRefInfoConfig.refInfoMap.put("4CTO32",
        "nc/ui/ic/m4c/sourceref/refinfo4CFor32.xml");
    // ������۳��ⵥ==�����۶���
    ICRefInfoConfig.refInfoMap.put("4CTO30",
        "nc/ui/ic/m4c/sourceref/refinfo4CFor30.xml");
    // ������۳��ⵥ==�����䵥
    ICRefInfoConfig.refInfoMap.put("4CTO4804",
        "nc/ui/ic/m4c/sourceref/refinfo4CFor4804.xml");
    //����������ⵥ==�����䵥
    ICRefInfoConfig.refInfoMap.put("4ITO4804",
        "nc/ui/ic/m4i/sourceref/refinfo4IFor4804.xml");
    // ������۳��ⵥ==����֤��
    ICRefInfoConfig.refInfoMap.put("4CTOC006",
        "nc/ui/ic/m4c/sourceref/refinfo4CForC006.xml");
    // ����� to ���۶���
    ICRefInfoConfig.refInfoMap.put("4HTO30",
        "nc/ui/ic/m4h/sourceref/ref4hto30.xml");
    // ������==���ɹ�����
    ICRefInfoConfig.refInfoMap.put("49TO21",
        "nc/ui/ic/m49/sourceref/refinfo49For21.xml");
    // ����������==��ǩ��;��
    ICRefInfoConfig.refInfoMap.put("4CTO4453",
        "nc/ui/ic/m4c/sourceref/ref4CFor4453.xml");
    // ������Ļ��ܵ�==���ɹ���Ʊ
    ICRefInfoConfig.refInfoMap.put("50TO25",
        "nc/ui/ic/m50/sourceref/refinfo_for25.xml");
    // ������ⵥ==���������ⵥ
    ICRefInfoConfig.refInfoMap.put(ICBillType.GeneralIn.getCode(),
        "nc/ui/ic/m4a/sourceref/refinfoInToOut.xml");
    // ��������==>>ǩ��;��
    ICRefInfoConfig.refInfoMap.put("4YTO4453",
        "nc/ui/ic/m4y/sourceref/refinfo4YFor4453.xml");
    // �����ⵥ==>>�����,ʹ�õ�ͬһ�������ļ�
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

    // ���ί�мӹ����==>>�ɹ���Ʊ
    ICRefInfoConfig.refInfoMap.put("47TO25",
        "nc/ui/ic/m47/sourceref/refinfo47For25.xml");
    // ��������==>>���䵥
    ICRefInfoConfig.refInfoMap.put("4YTO4804",
        "nc/ui/ic/m4y/sourceref/refinfo4YFor4804.xml");
    // ��������==>>��֤��
    ICRefInfoConfig.refInfoMap.put("4YTOC006",
        "nc/ui/ic/m4y/sourceref/refinfo4YForC006.xml");
    // �������뵥==>>���ϳ���
    ICRefInfoConfig.refInfoMap.put("4455TO4D",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4D.xml");
    // �������뵥==>>��������
    ICRefInfoConfig.refInfoMap.put("4455TO4I",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4I.xml");
    // �������뵥==>>�����
    ICRefInfoConfig.refInfoMap.put("4455TO4H",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4H.xml");
    // �������뵥==>>���ϵ�
    ICRefInfoConfig.refInfoMap.put("4455TO4O",
        "nc/ui/ic/m4455/sourceref/refinfo4455For4O.xml");
    // ���ϳ��ⵥ==>>��֤��
    ICRefInfoConfig.refInfoMap.put("4DTOC006",
        "nc/ui/ic/m4d/sourceref/refinfo4DForC006.xml");
    // ���ϳ��ⵥ==>>����
    ICRefInfoConfig.refInfoMap.put("4DTO4B36",
        "nc/ui/ic/m4d/sourceref/refinfo4DFor4B36.xml");
    
    /**
     * HK 2020��9��28��18:03:55
     */
    ICRefInfoConfig.refInfoMap.put("4455TO4K",
            "nc/ui/ic/m4455/sourceref/refinfo4455For4K.xml");
    /***END***/
  }

  public static String getRefInfoLocation(String srcBillType,
      String currBillType) {
    // ����ǰ���������ǽ������͵����(�������ͷ����Ľڵ�)
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
