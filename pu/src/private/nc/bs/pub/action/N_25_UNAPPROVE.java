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

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.pu.m25.IInvoiceApprove;
import nc.jdbc.framework.processor.ArrayListProcessor;
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
      
      /**
       * HK  2019��12��19��16:47:11
       * �ж� �����Ƿ���� ԭ��ԭֵ������
       */
      for (int i = 0; i < vos.length; ++i) {
          nc.vo.pu.m25.entity.InvoiceVO invVo = vos[i];
          String pk_invoice = invVo.getParentVO().getPk_invoice();
                    
          StringBuffer querySQL = 
        	new StringBuffer("select ")
          			.append(" distinct a.bill_code ")
          			.append(" from po_settlebill js ")
          			.append(" inner join po_settlebill_b jsb on js.pk_settlebill = jsb.pk_settlebill ")
          			.append(" inner join fa_alter_b ab on jsb.pk_settlebill_b = ab.pk_bill_b_src ")
          			.append(" inner join fa_alter a on ab.pk_alter = a.pk_alter ")
          			.append(" where js.dr=0 and jsb.dr=0 and ab.dr=0 and a.dr=0 ")
          			.append(" and jsb.PK_INVOICE = '").append(pk_invoice).append("' ")
          ;
          BaseDAO dao = new BaseDAO();
          ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
          if (list != null && list.size() > 0) {
        	  StringBuffer msg = new StringBuffer("����ԭ��ԭֵ�������������ֹ�ɾ����");
        	  for (Object obj : list) {
        		  Object[] row = (Object[])obj;
        		  msg.append("[").append(row[0]).append("]");
        	  }
        	  throw new BusinessException(msg.toString());
          }
          
      }
//      if (true) {
//    	  throw new BusinessException("����");
//      }
      /***END***/
      
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
