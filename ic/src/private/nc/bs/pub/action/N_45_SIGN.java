package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.ic.m45.self.IPurchaseInMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;

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
      //�����ж� �Ƿ���� �̶��ʲ��������
      List<PurchaseInVO> voList = new ArrayList();
      for (PurchaseInVO billVo_source : inVOs) {
    	  /**
    	   * 2020��7��13��10:43:12
    	   * ��Ҫclone���������к���������Ϊת�̺��Ӱ�� ���ص����ݣ����º�������
    	   */
    	PurchaseInVO billVo = (PurchaseInVO)billVo_source.clone();
    	String pk_org = billVo.getParentVO().getPk_org();
    	PurchaseInBodyVO[] bodyvos = billVo.getBodys();
    	// ��������pk����
		ArrayList list = new ArrayList<String>();
		for (PurchaseInBodyVO bvo : bodyvos) {
			list.add(bvo.getCmaterialvid());
		}
    	// ��ѯ���ϡ�ʵ�����ϼ�ֵ����ģʽ
		String sql = "select pk_material "
//				+ ",(case materialvaluemgt when 1 then '�������' when 2 then '�̶��ʲ�' when 3 then '����' else '����' end)  wltype "
				+ " from bd_materialfi " +
				" where dr = 0 " +
				" and materialvaluemgt = 2 " +	// �̶��ʲ�
				" and pk_material in ( '' ";
		for (int i = 0; i < list.size(); i++) {
			sql = sql + ",'" + list.get(i) + "'";
		}
		sql += " ) and pk_org = (select pk_financeorg "
				+ " from org_stockorg " + " where pk_stockorg = '" + pk_org
				+ "') ";
		BaseDAO dao = new BaseDAO();
		ArrayList<Object[]> retObj = (ArrayList<Object[]>) dao
				.executeQuery(sql, new ArrayListProcessor());
		if(retObj!=null && retObj.size()>0) {
			voList.add(billVo);
		}
      }
      
      if(voList.size() > 0)
      {
    	  PurchaseInVO[] voList_arr = new PurchaseInVO[voList.size()];
    	  voList_arr = voList.toArray(voList_arr);
    	  
		  NCLocator.getInstance().lookup(IPurchaseInMaintain.class)
				  .generateFixedAsset(voList_arr);
      }
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