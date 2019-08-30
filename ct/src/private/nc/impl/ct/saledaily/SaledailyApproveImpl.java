package nc.impl.ct.saledaily;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.ct.saledaily.action.SaledailyApproveAction;
import nc.impl.ct.saledaily.action.SaledailyFreezeAction;
import nc.impl.ct.saledaily.action.SaledailyModiDeleteAction;
import nc.impl.ct.saledaily.action.SaledailyModifySpAction;
import nc.impl.ct.saledaily.action.SaledailySendApproveAction;
import nc.impl.ct.saledaily.action.SaledailyTerminateAction;
import nc.impl.ct.saledaily.action.SaledailyUnApproveAction;
import nc.impl.ct.saledaily.action.SaledailyUnFreezeAction;
import nc.impl.ct.saledaily.action.SaledailyUnTerminateAction;
import nc.impl.ct.saledaily.action.SaledailyUnValidateAction;
import nc.impl.ct.saledaily.action.SaledailyValidateAction;
import nc.impl.ct.saledaily.action.SaledailyValidateSpAction;
import nc.impl.ct.saledaily.action.SaledailyWithDrawAction;
import nc.itf.ct.saledaily.ISaledailyApprove;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.ct.saledaily.entity.CtSaleExecVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.util.ArrayUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * 销售合同审批操作实现组件
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-5-13 上午10:09:49
 */

public class SaledailyApproveImpl implements ISaledailyApprove {

  /**
   * 销售合同审核动作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#approve(nc.vo.ct.Saledaily.entity.AggCtSaleVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public AggCtSaleVO[] approve(AggCtSaleVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new SaledailyApproveAction().approve(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售合同冷冻动作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#freeze(nc.vo.ct.Saledaily.entity.AggCtSaleVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public AggCtSaleVO[] freeze(AggCtSaleVO[] vos, AggCtSaleVO[] originBills)
      throws BusinessException {
    try {
      return new SaledailyFreezeAction().freeze(vos, originBills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 方法功能描述：变更删除
   * <p>
   * <b>参数说明</b> 港华合同变更生效以及重走审批流需求
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-5 上午08:38:17
   */
  @Override
  public AggCtSaleVO[] modiDelete(AggCtSaleVO[] vos) throws BusinessException {
    try {
      return new SaledailyModiDeleteAction().modiDelete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售变更操作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#modify(nc.vo.ct.Saledaily.entity.AggCtSaleVO[])
   */
  @Override
  public AggCtSaleVO[] modify(AggCtSaleVO[] vos, PfUserObject userConfirm,
      AggCtSaleVO[] originBills) throws BusinessException {
    try {
      return new SaledailyModifySpAction()
          .modify(vos, userConfirm, originBills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售合同送审操作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#sendapprove(nc.vo.ct.Saledaily.entity.AggCtSaleVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public AggCtSaleVO[] sendapprove(AggCtSaleVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new SaledailySendApproveAction().sendapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售终止操作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#modify(nc.vo.ct.Saledaily.entity.AggCtSaleVO[])
   */
  @Override
  public AggCtSaleVO[] terminate(AggCtSaleVO[] vos, AggCtSaleVO[] originBills)
      throws BusinessException {
    try {
    	
    	/**
    	 * HK 2018年10月17日13:29:18
    	 * 必须有 退租日期 才能终止
    	 */
    	{
    		for(AggCtSaleVO aggVO : vos)
    		{
    			CtSaleVO ctSaleVO = aggVO.getParentVO();
    			String tzrq = ctSaleVO.getVdef19();		// 退租日期
    			if( ctSaleVO.getFstatusflag()==1 )
    			{// 生效状态的
	    			if(tzrq==null || "~".equals(tzrq))
	    			{// 退组日期为空的
	    				throw new BusinessException("生效的合同必须先退组 才能终止。");
	    			}
    			}
    		}
//    		System.out.println("==合同终止==");
    	}
    	/***END***/
    	
      return new SaledailyTerminateAction().terminate(vos, originBills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售合同取消审核操作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#unapprove(nc.vo.ct.Saledaily.entity.AggCtSaleVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public AggCtSaleVO[] unapprove(AggCtSaleVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new SaledailyUnApproveAction().unApprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售合同解冻动作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#unfreeze(nc.vo.ct.Saledaily.entity.AggCtSaleVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public AggCtSaleVO[] unfreeze(AggCtSaleVO[] vos, AggCtSaleVO[] originBills)
      throws BusinessException {
    try {
      return new SaledailyUnFreezeAction().unfreeze(vos, originBills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggCtSaleVO[] unterminate(AggCtSaleVO[] vos, AggCtSaleVO[] originBills)
      throws BusinessException {
    try {
      return new SaledailyUnTerminateAction().unterminate(vos, originBills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售合同取消生效动作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#unvalidate(nc.vo.ct.Saledaily.entity.AggCtSaleVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public AggCtSaleVO[] unvalidate(AggCtSaleVO[] vos, AggCtSaleVO[] originBills)
      throws BusinessException {
    try {
      return new SaledailyUnValidateAction().unvalidate(vos, originBills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 销售合同生效动作 父类方法重写
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#validate(nc.vo.ct.Saledaily.entity.AggCtSaleVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  // public AggCtSaleVO[] validate(AggCtSaleVO[] vos) throws BusinessException {
  // try {
  //
  // return new SaledailyValidateAction().validate(vos);
  //
  // }
  // catch (Exception e) {
  // ExceptionUtils.marsh(e);
  // }
  // return null;
  // }
  public AggCtSaleVO[] validate(AggCtSaleVO[] vos, AggCtSaleVO[] originBills)
      throws BusinessException {

    try {
      // modify by liangchen1 港华合同变更生效以及重走审批流需求
      /**
       * 原有合同生效逻辑只对应原始版本合同生效，对于港华变更保存合同生效的需求需要特殊处理
       * 这里为需求特别写一个action，这里由于合同支持批量生效，所以首先根据单据版本将vos分为两类:
       * 原始版本的走原有逻辑，新版本生效新写一个action进行处理，然后统一返回
       */
      /**
       * 对于取消生效后生效的情况，也需要走原有的逻辑（累计执行量为空才可以取消生效）
       */
      List<AggCtSaleVO> firstAndCancelVersion = new ArrayList<AggCtSaleVO>();
      List<AggCtSaleVO> firstAndCancelVersionOrig =
          new ArrayList<AggCtSaleVO>();
      List<AggCtSaleVO> otherVersion = new ArrayList<AggCtSaleVO>();
      List<AggCtSaleVO> otherVersionOrig = new ArrayList<AggCtSaleVO>();
      AggCtSaleVO[] firstAndCancelReturnVOs = null;
      AggCtSaleVO[] otherReturnVOs = null;
      int cursor = 0;
      for (AggCtSaleVO vo : vos) {
    	  
        CtSaleExecVO[] execs = vo.getCtSaleExecVO();
        if (!ArrayUtil.isEmpty(execs)) {
          // 得到上一次执行过程
          CtSaleExecVO newest = execs[0];
          for (int i = 1; i < execs.length; i++) {
            if (execs[i].getStatus() != VOStatus.NEW
                && execs[i].getVexecdate().compareTo(newest.getVexecdate()) > 0) {
              newest = execs[i];
            }
          }
          // 最新的执行过程是否为取消生效
          if ("取消生效".equals(newest.getVexecflow())) {/* -=notranslate=- */
            firstAndCancelVersion.add(vo);
            firstAndCancelVersionOrig.add(originBills[cursor]);
            cursor++;
            continue;
          }
        }
        if (MathTool.equals(UFDouble.ONE_DBL, vo.getParentVO().getVersion())) {
          firstAndCancelVersion.add(vo);
          firstAndCancelVersionOrig.add(originBills[cursor]);
        }
        else {
          otherVersion.add(vo);
          otherVersionOrig.add(originBills[cursor]);
        }
        cursor++;
      }
      if (firstAndCancelVersion.size() > 0) {
        firstAndCancelReturnVOs =
            new SaledailyValidateAction()
                .validate(firstAndCancelVersion
                    .toArray(new AggCtSaleVO[firstAndCancelVersion.size()]),
                    firstAndCancelVersionOrig
                        .toArray(new AggCtSaleVO[firstAndCancelVersionOrig
                            .size()]));
      }
      if (otherVersion.size() > 0) {
        otherReturnVOs =
            new SaledailyValidateSpAction().validate(otherVersion
                .toArray(new AggCtSaleVO[otherVersion.size()]),
                otherVersionOrig.toArray(new AggCtSaleVO[otherVersionOrig
                    .size()]));
      }
      
      /**
       * HK
       * 2018年10月10日20:08:34
	   * 增加房间号唯一生效的判断
	   */
		for (AggCtSaleVO vo : vos) {
		    	  
	    	  CtSaleVO ctSaleVO= vo.getParentVO();
	    	  String pk_room = ctSaleVO.getVdef16();		// 房间号
	    	  String pk_ct_sale = ctSaleVO.getPk_ct_sale();	// 合同pk
	    	  
	    	  StringBuffer querySQL = 
	    		new StringBuffer("select ct.pk_ct_sale ")
	    	  			.append(" from ct_sale ct ")
	    	  			.append(" where ct.dr = 0 ")
//	    	  			.append(" and ct.blatest = 'Y' ")		// 是否最新版本
	    	  			.append(" and ct.bshowlatest  = 'Y' ")	// 是否显示最新版本
	    	  			.append(" and ct.fstatusflag = 1 ")
	    	  			.append(" and ct.pk_ct_sale != '"+pk_ct_sale+"' ")
	    	  			.append(" and ct.vdef16 = '"+pk_room+"' ")
	    	  			.append(" and nvl(ct.vdef19,'~') = '~' ")	// 退租日期（vdef19）为空
	    	  ;
	    	  BaseDAO dao = new BaseDAO();
	    	  List list = (List)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
	    	  
	    	  if(list!=null && list.size()>0)
	    	  {
	    		  throw new BusinessException("该房号存在于生效（未退租）的合同中，不能再次生效。");
	    	  }
		    	  
		}
		/***END***/
      
      return ArrayUtil.combinArrays(firstAndCancelReturnVOs, otherReturnVOs);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

  /**
   * 销售合同收回动作 父类方法重写
   * 
   * @see nc.itf.ct.saledaily.ISaledailyApprove#withDraw(nc.vo.ct.saledaily.entity.AggCtSaleVO[])
   */
  @Override
  public AggCtSaleVO[] withDraw(AggCtSaleVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new SaledailyWithDrawAction().withDraw(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
