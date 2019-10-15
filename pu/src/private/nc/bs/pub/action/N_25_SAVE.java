/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 下午06:52:08
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票送审脚本
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-27 下午06:52:08
 */
public class N_25_SAVE extends AbstractCompiler2 {
  /**
   * 父类方法重写
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
       * 2019年10月15日 15点20分
       * 增加判断， 看下游 转固单，是否都生成了 资产卡片
       */
      if(true) {
    	  
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
		    	  .append(" max(inv.code) ")	//0 物料编码
		    	  .append(",max(inv.name) ")	//1 物料编码
		    	  .append(",max(fpb.nnum) ")	//2 发票数量
		    	  .append(",max(cgrkb.nnum) ")	//3 入库数量
		    	  .append(",max(zgb.amount) ")	//4 转固数量
		    	  .append(",sum(nvl(cardhis.card_num,0)) ")	//5 卡片数量
		    	  .append("from po_invoice fp ")
		    	  .append(" inner join po_invoice_b fpb on fp.pk_invoice = fpb.pk_invoice ")
		    	  .append(" inner join ic_purchasein_b cgrkb on fpb.csourcebid = cgrkb.cgeneralbid ")
		    	  .append(" inner join fa_transasset_b zgb on  cgrkb.cgeneralbid = zgb.pk_bill_b_src ")
		    	  .append(" inner join bd_material inv on fpb.pk_material = inv.pk_material ")
		    	  .append(" left join fa_card card on (zgb.pk_transasset_b = card.pk_bill_b_src and card.dr = 0) ")
		    	  .append(" left join fa_cardhistory cardhis on (card.pk_card = cardhis.pk_card and cardhis.laststate_flag='Y' and cardhis.dr = 0) ")
		    	  .append(" where fp.dr = 0 and fpb.dr = 0 ")
		    	  .append(" and cgrkb.dr = 0 and zgb.dr = 0 ")
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
        		  UFDouble zgNum = PuPubVO.getUFDouble_NullAsZero(obj[4]);
        		  UFDouble kpNum = PuPubVO.getUFDouble_NullAsZero(obj[5]);
        		  errMSg
        		  .append("【").append(invCode).append(invName).append("】")
        		  .append("{转固单数量：").append(zgNum).append("}、")
        		  .append("{卡片数量：").append(kpNum).append("}")
        		  .append("\r\n")
        		  ;
    		  }
    		  
    		  throw new BusinessException(errMSg.toString());
    	  }
      }
      /***END***/
      
      /************* 该组件为批动作工作流处理开始...不能进行修改 *********************/
      retValue =
          NCLocator.getInstance().lookup(nc.itf.pu.m25.IInvoiceApprove.class)
              .sendapprove(vos, this);
      /**************************************************************************/

      /************** 返回结果 *************************************************/
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
