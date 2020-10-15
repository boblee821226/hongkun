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
 * <b>������Ҫ������¹��ܣ�</b> �ɹ���ͬ����ű�
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-4-21 ����11:02:26
 */
public class N_Z2_SAVEBASE extends AbstractCompiler2 {
  // private java.util.Hashtable m_methodReturnHas=new java.util.Hashtable();
  // private Hashtable m_keyHas=null;
  /**
   * N_25_SAVE ������ע�⡣
   */
  public N_Z2_SAVEBASE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "    nc.vo.ct.purdaily.entity.AggCtPuVO inObject  =(nc.vo.ct.purdaily.entity.AggCtPuVO) getVo();   setParameter(\"INVO\", inObject);\n return null;\n";
  }

  // /*
  // * ��ע�����ýű�������HAS
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
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {

    try {
      super.m_tmpVo = vo;

      Object userObj = vo.m_userObj;

      Object retObj = null;
      /********************** ���²����޸� *******************************************/
      /************* ��ƽ̨ȡ���ɸö����������ڲ�����������ȡ����Ҫ��VO�� ************/
      nc.vo.ct.purdaily.entity.AggCtPuVO[] vos =
          (nc.vo.ct.purdaily.entity.AggCtPuVO[]) this.getVos();
      /**
       * ����ǳɱ���ͬ���� ��ͬ���� ���� ���ŷ�̯
       * HK 2020��10��15��13:31:58
       * ��Ҫ�жϣ������ϼ� �Ƿ�=1�������Ƿ� �ظ�
       */
      for (AggCtPuVO billVO : vos) {
    	  CtPuTermVO[] termVOs = billVO.getCtPuTermVO();
    	  if (termVOs == null || termVOs.length == 0) continue;
    	  UFDouble total_bl = UFDouble.ZERO_DBL;		// ����֮��
    	  Map<String,String> bm_MAP = new HashMap<>();	// ���Ż���
    	  for (CtPuTermVO termVO : termVOs) {
    		  // ����ɾ��������
    		  Integer status = termVO.getStatus();
    		  if (status == VOStatus.DELETED) continue;
    		  UFDouble bl = PuPubVO.getUFDouble_NullAsZero(termVO.getVhkbdef2());	// ����
    		  String bmName = termVO.getVhkbdef1();	// ����name
    		  String bmPk = termVO.getVhkbdef3();	// ����pk
    		  total_bl = total_bl.add(bl);	// �ۼƱ���
    		  if (bm_MAP.containsKey(bmPk)) {
    			  // ��� �����ظ������׳�����
    			  throw new BusinessException("��̯���Ų����ظ���" + bmName);
    		  } else {
    			  // ��ӵ�����
    			  bm_MAP.put(bmPk, bmName);
    		  }
    	  }
    	  if (!bm_MAP.isEmpty()) {
	    	  if (total_bl.compareTo(UFDouble.ONE_DBL) != 0) {
	    		  // �������֮�� ������1�����׳�����
	    		  throw new BusinessException("���ŷ�̯����֮�� ����Ϊ1��");
	    	  }
    	  }
      }
      /***END***/
      /************** ִ�вɹ���ͬ�ı��棨���£����� ********************************/
      PfParameterUtil<AggCtPuVO> util =
          new PfParameterUtil<AggCtPuVO>(this.getPfParameterVO(), vos);
      AggCtPuVO[] clientFullBills = util.getClientFullInfoBill();
      AggCtPuVO[] originBills = null;
      // �޸�ʱȡ��ԭʼVO
      if (!(vos[0].getPrimaryKey() == null)
          || vos[0].getParent().getStatus() == VOStatus.NEW) {
        originBills = util.getOrginBills();
      }
      retObj =
          NCLocator.getInstance()
              .lookup(nc.itf.ct.purdaily.IPurdailyMaintain.class)
              .save(clientFullBills, userObj, originBills);
      /********************** ���ϲ����޸� *******************************************/
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
