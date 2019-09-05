package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.ic.pub.util.ICBillVOQuery;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.vo.ic.general.util.ICLocationUtil;
import nc.vo.ic.m4i.entity.GeneralOutVO;
import nc.vo.ic.m4v.util.PickBillVOUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 备注：库存其它出库单的保存
 * 单据动作执行中的动态执行类的动态执行类。
 * 创建日期：(2010-6-10)
 *
 * @author 平台脚本生成
 */
public class N_4I_WRITE extends AbstractCompiler2 {
  private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();

  private Hashtable m_keyHas = null;

  /**
   * N_4I_WRITE 构造子注解。
   */
  public N_4I_WRITE() {
    super();
  }

  /*
   * 备注：平台编写规则类
   * 接口执行类
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
  * 备注：平台编写原始脚本
  */
  @Override
  public String getCodeRemark() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008003_0","04008003-0108")/*@res "不支持修改脚本"*/;
  }

  /*
   * 备注：设置脚本变量的HAS
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