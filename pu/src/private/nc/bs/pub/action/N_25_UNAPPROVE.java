/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 ����01:17:46
 */
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.itf.pu.m25.IInvoiceApprove;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ű�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-27 ����01:17:46
 */
public class N_25_UNAPPROVE extends AbstractCompiler2 {

  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      nc.vo.pu.m25.entity.InvoiceVO[] vos =
          (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      nc.vo.pu.m25.entity.InvoiceVO[] retValue =
          new nc.vo.pu.m25.entity.InvoiceVO[vos.length];
      /************* �����Ϊ����������������ʼ...���ܽ����޸� *********************/
      for (int i = 0; i < vos.length; ++i) {
        nc.vo.pu.m25.entity.InvoiceVO invVo = vos[i];
        this.setVo(invVo);
        nc.vo.pu.m25.entity.InvoiceVO[] retInvVos =
            NCLocator.getInstance().lookup(IInvoiceApprove.class)
                .unapprove(new nc.vo.pu.m25.entity.InvoiceVO[] {
                  invVo
                }, this);
        retValue[i] = retInvVos[0];
      }
      /**************************************************************************/

//      /**
//       * HK  2019��9��2��16��31��
//       * ɾ��  ���������
//       */
//      HkjtReportITF itf = (HkjtReportITF)NCLocator.getInstance().lookup(HkjtReportITF.class.getName());
//      Object result = itf.genCktzdByPoInvoice(vos, null);
//      /***END***/
      
      /************** ���ؽ�� *************************************************/
      return retValue;

    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }

}
