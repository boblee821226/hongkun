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
      //首先判断 是否存在 固定资产类的物料
      List<PurchaseInVO> voList = new ArrayList();
      for (PurchaseInVO billVo : inVOs) {
    	String pk_org = billVo.getParentVO().getPk_org();
    	PurchaseInBodyVO[] bodyvos = billVo.getBodys();
    	// 表体物料pk集合
		ArrayList list = new ArrayList<String>();
		for (PurchaseInBodyVO bvo : bodyvos) {
			list.add(bvo.getCmaterialvid());
		}
    	// 查询物料、实物物料价值管理模式
		String sql = "select pk_material "
//				+ ",(case materialvaluemgt when 1 then '存货核算' when 2 then '固定资产' when 3 then '费用' else '其他' end)  wltype "
				+ " from bd_materialfi " +
				" where dr = 0 " +
				" and materialvaluemgt = 2 " +	// 固定资产
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