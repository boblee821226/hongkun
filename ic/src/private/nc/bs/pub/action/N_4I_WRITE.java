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