package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.ic.m45.self.IPurchaseInMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ��ע�����ɹ���ⵥ��ǩ�� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-3-31)
 *
 * @author ƽ̨�ű�����
 */
public class N_45_SIGN extends AbstractCompiler2 {
  private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  private Hashtable m_keyHas = null;

  /**
   * N_45_SIGN ������ע�⡣
   */
  public N_45_SIGN() {
    super();
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.ic.m45.entity.PurchaseInVO[] inVOs =
          (nc.vo.ic.m45.entity.PurchaseInVO[]) getVos();

      inVOs =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.ic.m45.self.IPurchaseInMaintain.class).sign(inVOs);

      /**
       * HK
       * 2019��10��15�� 09��57��
       * ǩ�ֺ󣬽����жϣ�����ǹ̶��ʲ��� �� ����ת�̵���
       */
      inVOs = NCLocator.getInstance().lookup(IPurchaseInMaintain.class)
              .generateFixedAsset(inVOs);
      /***END***/
      
      return inVOs;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /*
  * ��ע��ƽ̨��дԭʼ�ű�
  */
  @Override
  public String getCodeRemark() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008002_0","04008002-0076")/*@res "��֧���޸Ľű�"*/;
  }

  /*
   * ��ע�����ýű�������HAS
   */
  private void setParameter(String key, Object val) {
    if (m_keyHas == null) {
      m_keyHas = new Hashtable();
    }
    if (val != null) {
      m_keyHas.put(key, val);
    }
  }
}