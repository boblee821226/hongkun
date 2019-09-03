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
 * ��ע���ɹ���Ʊ������ ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2009-7-3)
 * 
 * @author ƽ̨�ű�����
 */
public class N_25_APPROVE extends AbstractCompiler2 {

  /**
   * N_25_APPROVE ������ע�⡣
   */
  public N_25_APPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	/*************��ƽ̨ȡ���ɸö����������ڲ�����������ȡ����Ҫ��VO��************/\n      nc.vo.pu.m25.entity.InvoiceVO inObject  =(nc.vo.pu.m25.entity.InvoiceVO) getVo();\n      /**************���ò���******************************************************/\n      setParameter(\"INVO\", inObject);\n	return null;\n";/* -=notranslate=- */
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      /************* �����Ϊ����������������ʼ...���ܽ����޸� *********************/
      Object retValue = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos =
        (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      
      nc.vo.pu.m25.env.InvoiceUIToBSEnv[] envs =
        nc.vo.pu.m25.pub.InvoiceEnvExtractUtil.getEnvs(vo);
      retValue =
        NCLocator.getInstance().lookup(IInvoiceApprove.class)
        .approve(vos, this, envs);
      
      /**
       * HK  2019��8��30��20��09��
       * ����  ���������
       */
      HkjtReportITF itf = (HkjtReportITF)NCLocator.getInstance().lookup(HkjtReportITF.class.getName());
      Object result = itf.genCktzdByPoInvoice(vos, null);
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
