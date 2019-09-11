package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.itf.ia.mia.IIAMaintain;
import nc.itf.pu.m25.IInvoiceApprove;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：采购发票的审批 单据动作执行中的动态执行类的动态执行类。 创建日期：(2009-7-3)
 * 
 * @author 平台脚本生成
 */
public class N_25_APPROVE extends AbstractCompiler2 {

  /**
   * N_25_APPROVE 构造子注解。
   */
  public N_25_APPROVE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "	/*************从平台取得由该动作传入的入口参数。本方法取得需要的VO。************/\n      nc.vo.pu.m25.entity.InvoiceVO inObject  =(nc.vo.pu.m25.entity.InvoiceVO) getVo();\n      /**************设置参数******************************************************/\n      setParameter(\"INVO\", inObject);\n	return null;\n";/* -=notranslate=- */
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      /************* 该组件为批动作工作流处理开始...不能进行修改 *********************/
      Object retValue = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos =
        (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      
      nc.vo.pu.m25.env.InvoiceUIToBSEnv[] envs =
        nc.vo.pu.m25.pub.InvoiceEnvExtractUtil.getEnvs(vo);
      retValue =
        NCLocator.getInstance().lookup(IInvoiceApprove.class)
        .approve(vos, this, envs);
      
      /**
       * HK  2019年8月30日20点09分
       * 生成  出库调整单
       */
      HkjtReportITF itf = (HkjtReportITF)NCLocator.getInstance().lookup(HkjtReportITF.class.getName());
      {
    	  Object result = itf.genCktzdByPoInvoice(vos, null);
      }
      /***END***/
      
      /**
       * HK 2019年9月6日10点54分
       * 生成 固定资产的原币原值调整单
       */
      {
    	  Object result = itf.genYbyztzByPoInvoice(vos, null);
      }
      /***END***/
      
      return retValue;
      /**************************************************************************/
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
