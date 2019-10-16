/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 ����06:52:08
 */
package nc.bs.pub.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import org.springframework.aop.ThrowsAdvice;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.fa.newasset.view.newasset_config;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uap.pf.PFBusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ����ű�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-27 ����06:52:08
 */
public class N_25_SAVE extends AbstractCompiler2 {
  /**
   * ���෽����д
   * 
   * @see nc.bs.pub.compiler.AbstractCompiler2#runComClass(nc.vo.pub.compiler.PfParameterVO)
   */
  @Override
  public Object runComClass(PfParameterVO paraVo) throws BusinessException {

    try {
      super.m_tmpVo = paraVo;

      nc.vo.pu.m25.entity.InvoiceVO[] retValue = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos =
          (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      
      /**
       * HK
       * 2019��10��15�� 15��20��
       * �����жϣ������� ת�̵����Ƿ������� �ʲ���Ƭ
       * �޸��ж��߼����̶��ʲ����Ե����� �����붼�����˿�Ƭ��
       */
      if(true) {
    	  
    	  String pk_org = vos[0].getParentVO().getPk_org();
    	  
    	  StringBuffer pkInvoices = new StringBuffer("('null'");
    	  for(int i=0;i<vos.length;i++) {
    		  pkInvoices
    		  .append(",'")
    		  .append(vos[i].getParentVO().getPk_invoice())
    		  .append("'");
    	  }
    	  pkInvoices.append(")");
    	  
    	  StringBuffer querySQL = 
    	  new StringBuffer("select ")
		    	  .append(" max(inv.code) ")	//0 ���ϱ���
		    	  .append(",max(inv.name) ")	//1 ���ϱ���
		    	  .append(",max(fpb.nnum) ")	//2 ��Ʊ����
		    	  .append(",max(cgrkb.nnum) ")	//3 �������
		    	  .append(",max(zgb.amount) ")	//4 ת������
		    	  .append(",sum(nvl(cardhis.card_num,0)) ")	//5 ��Ƭ����
		    	  .append("from po_invoice fp ")
		    	  .append(" inner join po_invoice_b fpb on fp.pk_invoice = fpb.pk_invoice ")
		    	  .append(" inner join ic_purchasein_b cgrkb on fpb.csourcebid = cgrkb.cgeneralbid ")
		    	  .append(" inner join fa_transasset_b zgb on  cgrkb.cgeneralbid = zgb.pk_bill_b_src ")
		    	  .append(" inner join bd_material inv on fpb.pk_material = inv.pk_material ")
		    	  .append(" inner join bd_materialfi invfi on (inv.pk_material = invfi.pk_material and invfi.dr = 0 and invfi.pk_org = '").append(pk_org).append("') ")
		    	  .append(" left join fa_card card on (zgb.pk_transasset_b = card.pk_bill_b_src and card.dr = 0) ")
		    	  .append(" left join fa_cardhistory cardhis on (card.pk_card = cardhis.pk_card and cardhis.laststate_flag='Y' and cardhis.dr = 0) ")
		    	  .append(" where fp.dr = 0 and fpb.dr = 0 ")
		    	  .append(" and cgrkb.dr = 0 and zgb.dr = 0 ")
		    	  .append(" and invfi.materialvaluemgt = 2 ")	// ֻȡ �̶��ʲ� ��ġ�
		    	  .append(" and fp.pk_invoice in ").append(pkInvoices)
		    	  .append(" group by fpb.pk_invoice_b ")
		    	  .append(" having max(zgb.amount) <> sum(nvl(cardhis.card_num,0)) ")
    	  ;
    	  
    	  BaseDAO dao = new BaseDAO();
    	  ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
    	  if(list!=null && list.size()>0) {
    		  
    		  StringBuffer errMSg = new StringBuffer();
    		
    		  for(int i=0;i<list.size();i++) {
    			  Object[] obj = (Object[])list.get(i);
        		  String invCode = PuPubVO.getString_TrimZeroLenAsNull(obj[0]);
        		  String invName = PuPubVO.getString_TrimZeroLenAsNull(obj[1]);
        		  UFDouble rkNum = PuPubVO.getUFDouble_NullAsZero(obj[3]);
        		  UFDouble kpNum = PuPubVO.getUFDouble_NullAsZero(obj[5]);
        		  errMSg
        		  .append("��").append(invCode).append(invName).append("��")
        		  .append("{���������").append(rkNum).append("}��")
        		  .append("{��Ƭ������").append(kpNum).append("}")
        		  .append("\r\n")
        		  ;
    		  }
    		  
    		  throw new BusinessException(errMSg.toString());
    	  }
      }
      /***END***/
      
      /************* �����Ϊ����������������ʼ...���ܽ����޸� *********************/
      retValue =
          NCLocator.getInstance().lookup(nc.itf.pu.m25.IInvoiceApprove.class)
              .sendapprove(vos, this);
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
