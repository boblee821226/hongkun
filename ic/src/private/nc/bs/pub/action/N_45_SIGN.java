package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.ic.m45.self.IPurchaseInMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 备注：库存采购入库单的签字 单据动作执行中的动态执行类的动态执行类。 创建日期：(2010-3-31)
 *
 * @author 平台脚本生成
 */
public class N_45_SIGN extends AbstractCompiler2 {
  private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  private Hashtable m_keyHas = null;

  /**
   * N_45_SIGN 构造子注解。
   */
  public N_45_SIGN() {
    super();
  }

  /*
   * 备注：平台编写规则类 接口执行类
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
       * 2019年10月15日 09点57分
       * 签字后，进行判断：如果是固定资产类 则 生成转固单。
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
  * 备注：平台编写原始脚本
  */
  @Override
  public String getCodeRemark() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008002_0","04008002-0076")/*@res "不支持修改脚本"*/;
  }

  /*
   * 备注：设置脚本变量的HAS
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