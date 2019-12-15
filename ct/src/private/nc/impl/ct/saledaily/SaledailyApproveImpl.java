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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * ���ۺ�ͬ��������ʵ�����
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-5-13 ����10:09:49
 */

public class SaledailyApproveImpl implements ISaledailyApprove {

  /**
   * ���ۺ�ͬ��˶��� ���෽����д
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
   * ���ۺ�ͬ�䶳���� ���෽����д
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
   * �����������������ɾ��
   * <p>
   * <b>����˵��</b> �ۻ���ͬ�����Ч�Լ���������������
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-5-5 ����08:38:17
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
   * ���۱������ ���෽����д
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
   * ���ۺ�ͬ������� ���෽����д
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
   * ������ֹ���� ���෽����д
   * 
   * @see nc.itf.ct.Saledaily.ISaledailyApprove#modify(nc.vo.ct.Saledaily.entity.AggCtSaleVO[])
   */
  @Override
  public AggCtSaleVO[] terminate(AggCtSaleVO[] vos, AggCtSaleVO[] originBills)
      throws BusinessException {
    try {
    	
    	/**
    	 * HK 2018��10��17��13:29:18
    	 * ������ �������� ������ֹ
    	 */
    	{
    		for(AggCtSaleVO aggVO : vos)
    		{
    			CtSaleVO ctSaleVO = aggVO.getParentVO();
    			String tzrq = ctSaleVO.getVdef19();		// ��������
    			if( ctSaleVO.getFstatusflag()==1 )
    			{// ��Ч״̬��
	    			if(tzrq==null || "~".equals(tzrq))
	    			{// ��������Ϊ�յ�
	    				throw new BusinessException("��Ч�ĺ�ͬ���������� ������ֹ��");
	    			}
    			}
    		}
//    		System.out.println("==��ͬ��ֹ==");
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
   * ���ۺ�ͬȡ����˲��� ���෽����д
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
   * ���ۺ�ͬ�ⶳ���� ���෽����д
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
   * ���ۺ�ͬȡ����Ч���� ���෽����д
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
   * ���ۺ�ͬ��Ч���� ���෽����д
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
      // modify by liangchen1 �ۻ���ͬ�����Ч�Լ���������������
      /**
       * ԭ�к�ͬ��Ч�߼�ֻ��Ӧԭʼ�汾��ͬ��Ч�����ڸۻ���������ͬ��Ч��������Ҫ���⴦��
       * ����Ϊ�����ر�дһ��action���������ں�֧ͬ��������Ч���������ȸ��ݵ��ݰ汾��vos��Ϊ����:
       * ԭʼ�汾����ԭ���߼����°汾��Ч��дһ��action���д���Ȼ��ͳһ����
       */
      /**
       * ����ȡ����Ч����Ч�������Ҳ��Ҫ��ԭ�е��߼����ۼ�ִ����Ϊ�ղſ���ȡ����Ч��
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
          // �õ���һ��ִ�й���
          CtSaleExecVO newest = execs[0];
          for (int i = 1; i < execs.length; i++) {
            if (execs[i].getStatus() != VOStatus.NEW
                && execs[i].getVexecdate().compareTo(newest.getVexecdate()) > 0) {
              newest = execs[i];
            }
          }
          // ���µ�ִ�й����Ƿ�Ϊȡ����Ч
          if ("ȡ����Ч".equals(newest.getVexecflow())) {/* -=notranslate=- */
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
       * 2018��10��10��20:08:34
	   * ���ӷ����Ψһ��Ч���ж�
	   */
		for (AggCtSaleVO vo : vos) {
		    	  
	    	  CtSaleVO ctSaleVO= vo.getParentVO();
	    	  String pk_room = ctSaleVO.getVdef16();		// �����
	    	  String pk_ct_sale = ctSaleVO.getPk_ct_sale();	// ��ͬpk
	    	  
	    	  StringBuffer querySQL = 
	    		new StringBuffer("select ct.pk_ct_sale ")
	    	  			.append(" from ct_sale ct ")
	    	  			.append(" where ct.dr = 0 ")
//	    	  			.append(" and ct.blatest = 'Y' ")		// �Ƿ����°汾
	    	  			.append(" and ct.bshowlatest  = 'Y' ")	// �Ƿ���ʾ���°汾
	    	  			.append(" and ct.fstatusflag = 1 ")
	    	  			.append(" and ct.pk_ct_sale != '"+pk_ct_sale+"' ")
	    	  			.append(" and ct.vdef16 = '"+pk_room+"' ")
	    	  			.append(" and nvl(ct.vdef19,'~') = '~' ")	// �������ڣ�vdef19��Ϊ��
	    	  ;
	    	  BaseDAO dao = new BaseDAO();
	    	  List list = (List)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
	    	  
	    	  if(list!=null && list.size()>0)
	    	  {
	    		  throw new BusinessException("�÷��Ŵ�������Ч��δ���⣩�ĺ�ͬ�У������ٴ���Ч��");
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
   * ���ۺ�ͬ�ջض��� ���෽����д
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
