package nc.bs.pub.action;

import java.util.HashMap;
import java.util.Map;

import hd.vo.pub.tools.PuPubVO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.purdaily.entity.CtPuTermVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uap.pf.PFBusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b> 采购合同保存脚本
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-4-21 上午11:02:26
 */
public class N_Z2_SAVEBASE extends AbstractCompiler2 {
  // private java.util.Hashtable m_methodReturnHas=new java.util.Hashtable();
  // private Hashtable m_keyHas=null;
  /**
   * N_25_SAVE 构造子注解。
   */
  public N_Z2_SAVEBASE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "    nc.vo.ct.purdaily.entity.AggCtPuVO inObject  =(nc.vo.ct.purdaily.entity.AggCtPuVO) getVo();   setParameter(\"INVO\", inObject);\n return null;\n";
  }

  // /*
  // * 备注：设置脚本变量的HAS
  // */
  // private void setParameter(String key,Object val) {
  // if (m_keyHas==null){
  // m_keyHas=new Hashtable();
  // }
  // if (val!=null) {
  // m_keyHas.put(key,val);
  // }
  // }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {

    try {
      super.m_tmpVo = vo;

      Object userObj = vo.m_userObj;

      Object retObj = null;
      /********************** 以下不能修改 *******************************************/
      /************* 从平台取得由该动作传入的入口参数。本方法取得需要的VO。 ************/
      nc.vo.ct.purdaily.entity.AggCtPuVO[] vos =
          (nc.vo.ct.purdaily.entity.AggCtPuVO[]) this.getVos();
      /**
       * 如果是成本合同，将 合同条款 用作 部门分摊
       * HK 2020年10月15日13:31:58
       * 需要判断：比例合计 是否=1，部门是否 重复
       */
      for (AggCtPuVO billVO : vos) {
    	  CtPuTermVO[] termVOs = billVO.getCtPuTermVO();
    	  if (termVOs == null || termVOs.length == 0) continue;
    	  UFDouble total_bl = UFDouble.ZERO_DBL;		// 比例之和
    	  Map<String,String> bm_MAP = new HashMap<>();	// 部门缓存
    	  for (CtPuTermVO termVO : termVOs) {
    		  // 跳过删除的数据
    		  Integer status = termVO.getStatus();
    		  if (status == VOStatus.DELETED) continue;
    		  UFDouble bl = PuPubVO.getUFDouble_NullAsZero(termVO.getVhkbdef2());	// 比例
    		  String bmName = termVO.getVhkbdef1();	// 部门name
    		  String bmPk = termVO.getVhkbdef3();	// 部门pk
    		  total_bl = total_bl.add(bl);	// 累计比例
    		  if (bm_MAP.containsKey(bmPk)) {
    			  // 如果 部门重复，则抛出错误
    			  throw new BusinessException("分摊部门不能重复。" + bmName);
    		  } else {
    			  // 添加到缓存
    			  bm_MAP.put(bmPk, bmName);
    		  }
    	  }
    	  if (!bm_MAP.isEmpty()) {
	    	  if (total_bl.compareTo(UFDouble.ONE_DBL) != 0) {
	    		  // 如果比例之和 不等于1，则抛出错误
	    		  throw new BusinessException("部门分摊比例之和 必须为1。");
	    	  }
    	  }
      }
      /***END***/
      /************** 执行采购合同的保存（更新）方法 ********************************/
      PfParameterUtil<AggCtPuVO> util =
          new PfParameterUtil<AggCtPuVO>(this.getPfParameterVO(), vos);
      AggCtPuVO[] clientFullBills = util.getClientFullInfoBill();
      AggCtPuVO[] originBills = null;
      // 修改时取下原始VO
      if (!(vos[0].getPrimaryKey() == null)
          || vos[0].getParent().getStatus() == VOStatus.NEW) {
        originBills = util.getOrginBills();
      }
      retObj =
          NCLocator.getInstance()
              .lookup(nc.itf.ct.purdaily.IPurdailyMaintain.class)
              .save(clientFullBills, userObj, originBills);
      /********************** 以上不能修改 *******************************************/
      return retObj;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);

    }

  }
}
