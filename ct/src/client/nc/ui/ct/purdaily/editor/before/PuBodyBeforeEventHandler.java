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
 * <b>�ɹ���ͬ����༭ǰ�¼��Ĵ����࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-1-24 ����05:00:09
 */
public class PuBodyBeforeEventHandler extends
    AbstractBodyBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, IBodyBeforeEditEventListener> listenerMap) {
    // ���ϻ���-����
    listenerMap.put(CtAbstractBVO.PK_MATERIAL, new Material());
    // ���ϻ���-���ϻ�������
    listenerMap.put(CtAbstractBVO.PK_MARBASCLASS, new Marbasclass());
    // ���ϻ���-���䷽ʽ
    listenerMap.put(CtAbstractBVO.CTRANSPMODEID, new Transpmodeid());
    // ���ϻ���-��������
    listenerMap.put(CtAbstractBVO.CPRODUCTORID, new Productor());
    // ���ϻ���-�����ȼ�
    listenerMap.put(CtAbstractBVO.CQUALITYLEVELID, new Qualitylevel());
    // ���۵�λ������
    listenerMap.put(CtAbstractBVO.VQTUNITRATE, new Nqtunitrate());
    // ���ϻ���-������
    listenerMap.put(CtAbstractBVO.VCHANGERATE, new VChangeRate());
    // ���ϻ���-��λ
    listenerMap.put(CtAbstractBVO.CASTUNITID, new Castunit());
    // ���ϻ���-����
    listenerMap.put(CtAbstractBVO.NASTNUM, new NastNum());
    // ���ϻ���-���۵�λ
    listenerMap.put(CtAbstractBVO.CQTUNITID, new Cqtunitid());
    // �����ʷ-���ԭ��
    listenerMap.put(CtAbstractChangeVO.VMEMO,
        new Memo(CtPuTableCode.CTPUCHANGE));
    // �����ʷ-��ע
    listenerMap.put(CtAbstractChangeVO.VCHGREASON, new ChgReason());
    // ִ�й���-ԭ��
    listenerMap.put(CtAbstractExecVO.VEXECREASON, new ExecReason());
    // �����ż۷���
    listenerMap.put(CtPuBVO.CQPBASESCHEMEID, new PriceScheme());
    // ˰��
    listenerMap.put(CTVatNameConst.CTAXCODEID, new CtTaxcode(
        CTBillType.PurDaily.getCode()));
    // ��˰���
    listenerMap.put(CTVatNameConst.NCALTAXMNY, new Caltaxmny());
    // ��ͬ�۸���Ϣ��
    listenerMap.put(CtPuBVO.PK_CT_PRICE, new CtPriceInfo());

    // ��Ч��
    listenerMap.put(CtPaymentVO.EFFECTMONTH, new CTEffectMonth());
    // ������
    listenerMap.put(CtPaymentVO.EFFECTADDMONTH, new CTEffectAddMonth());
    // ��ͬ����
    listenerMap.put(CtAbstractTermVO.VTERMCODE, new Term());
    // ������
    listenerMap.put(CtAbstractBVO.CFFILEID, new Cffileid());
    
    /**
     * HK
     * 2019��10��21�� 09��42��
     * �����Զ�����01 Ϊ ��֧��Ŀ�����й��� ֻ��ʾ�� ֧���࣬�����Ƿ�ͣ�õ�
     */
    listenerMap.put(CtAbstractBVO.VBDEF1, new PuVbdef01());
    /***END***/
  }
}
