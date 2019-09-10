package nc.bs.pub.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import nc.bs.dao.BaseDAO;
import nc.bs.ic.pub.util.ICBillVOQuery;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.ic.general.util.ICLocationUtil;
import nc.vo.ic.m4i.entity.GeneralOutBodyVO;
import nc.vo.ic.m4i.entity.GeneralOutHeadVO;
import nc.vo.ic.m4i.entity.GeneralOutVO;
import nc.vo.ic.m4v.util.PickBillVOUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ��ע������������ⵥ�ı���
 * ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * �������ڣ�(2010-6-10)
 *
 * @author ƽ̨�ű�����
 */
public class N_4I_WRITE extends AbstractCompiler2 {
  private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  private Hashtable m_keyHas = null;

  /**
   * N_4I_WRITE ������ע�⡣
   */
  public N_4I_WRITE() {
    super();
  }

  /*
   * ��ע��ƽ̨��д������
   * �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.bs.ic.pub.env.ICBSContext context = new nc.bs.ic.pub.env.ICBSContext();
      context.setICPFParameter((nc.vo.ic.pub.pf.ICPFParameter) this
          .getUserObj());
      nc.vo.ic.m4i.entity.GeneralOutVO[] outVOs =
          (nc.vo.ic.m4i.entity.GeneralOutVO[]) this.getVos();
      
//      /**
//       * HK
//       * 2019��9��5��18��53��
//       * �����жϣ�������ϵĲ��������� �̶��ʲ������������εĲɹ���ⵥ��
//       */
//      for(GeneralOutVO billVO : outVOs) {
//    	  GeneralOutHeadVO 	  hVO = billVO.getHead();
//    	  GeneralOutBodyVO[] bVOs = billVO.getBodys();
//    	  String pk_org = hVO.getPk_org();
//    	  String pkInvs = " ('null'";
//    	  /**
//    	   * 1�� ���ȹ���� ����pk�� �ַ���
//    	   */
//    	  for(GeneralOutBodyVO bVO : bVOs) {
//    		  String pk_inv = PuPubVO.getString_TrimZeroLenAsNull(bVO.getAttributeValue("cmaterialvid"));
//    		  pkInvs += ",'" + pk_inv + "'";
//    	  }
//    	  pkInvs += ")";
//    	  
//    	  /**
//    	   * 2�� ��ѯ �����Ƿ�Ϊ �̶��ʲ����ԡ� ֻ���� ��һ�������У��� �̶��ʲ������ϡ�
//    	   */
//    	  HashMap<String,String> MAP_gdzc = new HashMap<String,String>(); // key-����pk��value-����pk
//    	  StringBuffer querySQL = 
//    	  new StringBuffer("select distinct ")
//    	  		  .append(" m.pk_material ")
//    	  		  .append(" from bd_materialfi m ")
//    	  		  .append(" where m.dr = 0 ")
//    	  		  .append(" and m.materialvaluemgt = 2 ")
//    	  		  .append(" and m.pk_org = '"+pk_org+"' ")
//    	  		  .append(" and m.pk_material in "+pkInvs+" ")
//    	  ;
//    	  BaseDAO dao = new BaseDAO();
//    	  ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
//    	  for(Object obj : list) {
//    		  Object[] value = (Object[])obj;
//    		  String pk_inv = PuPubVO.getString_TrimZeroLenAsNull(value[0]);
//    		  MAP_gdzc.put(pk_inv, pk_inv);
//    	  }
//    	  /**
//    	   * 3���������壬����ǵڶ��������ϣ����ж� ���ε����Ƿ�Ϊ �ɹ���ⵥ��
//    	   */
//    	  for(int i=0;i<bVOs.length;i++) {
//    		  GeneralOutBodyVO bVO = bVOs[i];
//    		  String pk_inv = PuPubVO.getString_TrimZeroLenAsNull(bVO.getAttributeValue("cmaterialvid"));
//    		  if(MAP_gdzc.containsKey(pk_inv)) {
//    			  String csourcetype    = PuPubVO.getString_TrimZeroLenAsNull(bVO.getAttributeValue("csourcetype"));
//    			  String csourcebillhid = PuPubVO.getString_TrimZeroLenAsNull(bVO.getAttributeValue("csourcebillhid"));
//    			  String csourcebillbid = PuPubVO.getString_TrimZeroLenAsNull(bVO.getAttributeValue("csourcebillbid"));
//    			  if(
//    				 !"45".equals(csourcetype)
//    			  || csourcebillhid==null
//    			  || csourcebillbid==null
//    			  ) {
//    				  throw new BusinessException("�̶��ʲ����Ե����ϣ�������ղɹ���ⵥ��");
//    			  }
//    		  }
//    	  }
//      }
//      /***END***/
      
      if (null != outVOs[0] && null != outVOs[0].getHead()
          && null != outVOs[0].getHead().getCgeneralhid()) {
        PfParameterUtil<GeneralOutVO> util =
          new PfParameterUtil<GeneralOutVO>(this.getPfParameterVO(), outVOs);
        GeneralOutVO[] originBills =util.getOrginBills();
        if(originBills==null){
          originBills= new ICBillVOQuery().fetchVOWithLoc(outVOs);
        }
        GeneralOutVO[] clientFullBills=util.getClientFullInfoBill(); 
        UFBoolean isFromPick = (UFBoolean)context.getSession(PickBillVOUtil.ISFROMPICKBill);
        if (ValueCheckUtil.isTrue(isFromPick)) {
          ICLocationUtil.fillLocationVOs(originBills);
          ICLocationUtil.fillLocationVOs(clientFullBills);
          context.setSession(PickBillVOUtil.ISFROMPICKBill,null);
        }
        return nc.bs.framework.common.NCLocator.getInstance()
            .lookup(nc.itf.ic.m4i.IGeneralOutMaintain.class).update(clientFullBills,originBills);
      }
      nc.vo.ic.m4i.entity.GeneralOutVO[] retunVos =
          new nc.vo.ic.m4i.entity.GeneralOutVO[outVOs.length];
      nc.itf.ic.m4i.IGeneralOutMaintain service =
          nc.bs.framework.common.NCLocator.getInstance().lookup(
              nc.itf.ic.m4i.IGeneralOutMaintain.class);
      for (int i = 0; i < outVOs.length; i++) {
        retunVos[i] = service.insert(new nc.vo.ic.m4i.entity.GeneralOutVO[] {
          outVOs[i]
        })[0];
      }
      return retunVos;
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
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008003_0","04008003-0108")/*@res "��֧���޸Ľű�"*/;
  }

  /*
   * ��ע�����ýű�������HAS
   */
  private void setParameter(String key, Object val) {
    if (this.m_keyHas == null) {
      this.m_keyHas = new Hashtable();
    }
    if (val != null) {
      this.m_keyHas.put(key, val);
    }
  }
}