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
 * <b>������Ҫ������¹��ܣ����ۺ�ͬ����༭���¼�������</b> ��Ƭ��ʽ�� ����༭������
 * 
 * @author wangfengd
 * @time 2010-6-17 ����09:52:14
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
    // �ƻ��շ�������
    listenerMap.put(CtAbstractBVO.DELIVDATE, new DelivDate());
    // ����
    listenerMap.put(CtAbstractBVO.PK_MATERIAL, new CTSaleMaterial());
    // ���Ϸ���
    listenerMap.put(CtAbstractBVO.PK_MARBASCLASS, new MarBasClass());
    // �ջ���ַ
    listenerMap.put(CtAbstractBVO.PK_RECEIVEADDRESS, new ReceiveAddress());
    // ���ۻ�����
    listenerMap.put(CtAbstractBVO.VQTUNITRATE, new ChangeRate(
        CtAbstractBVO.VQTUNITRATE));
    // ������
    listenerMap.put(CtAbstractBVO.VCHANGERATE, new ChangeRate(
        CtAbstractBVO.VCHANGERATE));
    // ��λ
    listenerMap.put(CtAbstractBVO.CASTUNITID, new CastUnitId());
    // ���۵�λ
    listenerMap.put(CtAbstractBVO.CQTUNITID, new CqtUnitId());
    // ����
    listenerMap.put(CTVatNameConst.CSENDCOUNTRYID, new CTSaleCountry(
        CTVatNameConst.CSENDCOUNTRYID));
    listenerMap.put(CTVatNameConst.CRECECOUNTRYID, new CTSaleCountry(
        CTVatNameConst.CRECECOUNTRYID));
    listenerMap.put(CTVatNameConst.CTAXCOUNTRYID, new CTSaleCountry(
        CTVatNameConst.CTAXCOUNTRYID));
    // ˰��
    listenerMap.put(CTVatNameConst.CTAXCODEID, new CtTaxcode(
        CTBillType.SaleDaily.getCode()));
    // ������֯�ı䣬��˰���ұ䶯
    listenerMap.put(CtAbstractBVO.PK_FINANCEORG_V, new Financeorg());
    // �ͻ�������
    listenerMap.put(CtSaleBVO.CCUSTMATERIALID, new CTSaleCustMaterial());
    // ��������
    listenerMap.put(CtAbstractBVO.CPRODUCTORID, new CTSaleCustMarInfo());

    // �̶�������
    listenerMap.put(CtAbstractPayTermVO.CHECKDATA, new CheckData());
    // ��Ч��
    listenerMap.put(CtAbstractPayTermVO.EFFECTMONTH, new EffectMonth());
    // ������
    listenerMap.put(CtAbstractPayTermVO.EFFECTADDMONTH, new EffectAddMonth());
    // ��������
    listenerMap.put(CtAbstractPayTermVO.PAYMENTDAY, new PaymentDay());
    // �ʱ���
    listenerMap.put(CtAbstractPayTermVO.ISDEPOSIT, new Deposit());
    // �տ����
    listenerMap.put(CtAbstractPayTermVO.ACCRATE, new Accrate());
    // �ƻ���Ч��
    listenerMap.put(CtAbstractPayTermVO.DPLANEFFECTDATE, new PlanEffectDate());
    // �ƻ�������
    listenerMap.put(CtAbstractPayTermVO.DPLANENDDATE, new PlanEndDate());
    // ʵ����Ч��
    listenerMap.put(CtAbstractPayTermVO.DREALEFFECTDATE, new RealEffectDate());
    // ʵ�ʵ�����
    listenerMap.put(CtAbstractPayTermVO.DREALENDDATE, new RealEndDate());
    // ��������
    listenerMap.put(CtAbstractPayTermVO.EFFECTDATEADDDATE,
        new EffectDateAddDate());
    // ������
    listenerMap.put(CtAbstractPayTermVO.ACCOUNTDAY, new AccountDay());

    // ��ͬ����
    listenerMap.put(CtAbstractTermVO.VTERMCODE, new Term());

    // ������1-10
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
     * 2018��10��8��16:14:42
     * ʵ�ʺ�ͬ��vhkbdef6���༭�� = ���� ʵ�ʵ���
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF6, new TermVhkbdef6());
    /**
     * HK
     * 2018��10��8��23:41:02
     * ��׼���ۣ�vhkbdef4���༭�� = ���� ��׼ �� ʵ�� ��ͬ����ֵ ʵ�ʵ���
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF4, new TermVhkbdef4());
    /**
     * HK
     * 2018��10��16��11:04:57
     * ʵ�ʼƷ�������vhkbdef8���༭�� = ���� ʵ�ʺ�ͬ���
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF8, new TermVhkbdef8());
    /**
     * HK
     * 2018��10��16��14:11:52
     * ʵ�ʵ��ۣ�vhkbdef7���༭�� = ���� ʵ�ʺ�ͬ���
     */
    listenerMap.put(CtAbstractTermVO.VHKBDEF7, new TermVhkbdef7());
  }
}
