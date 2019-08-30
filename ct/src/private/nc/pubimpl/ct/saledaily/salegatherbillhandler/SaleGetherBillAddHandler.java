package nc.pubimpl.ct.saledaily.salegatherbillhandler;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.businessevent.BdUpdateEvent;
import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.IEventType;
import nc.bs.ct.saledaily.rewrite.gatherbill.RewriteZ3ForGatherBillBP;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.ct.saledaily.ISaledailyMaintain;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.ct.entity.CtWritebackVO;
import nc.vo.ct.pub.CTMDValue;
import nc.vo.ct.pub.ICTEventType;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.ct.uitl.ValueUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ��տ��Ӧ�¼�</b>
 * 
 * @author wangfengd
 * @time 2010-6-22 ����03:51:37
 */
public class SaleGetherBillAddHandler implements IBusinessListener {
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_INSERT_AFTER.equals(event.getEventType())) {
        this.isCtUsed();
        BusinessEvent e = (BusinessEvent) event;
        Object value = e.getObject();
        if (value == null) {
          return;
        }

        AggGatheringBillVO[] aggVOs;
        if (value.getClass().isArray()) {
          Object[] obj = (Object[]) value;
          aggVOs = new AggGatheringBillVO[obj.length];
          System.arraycopy(obj, 0, aggVOs, 0, obj.length);
        }
        else {
          aggVOs = new AggGatheringBillVO[] {
            (AggGatheringBillVO) value
          };
        }
        GatheringBillItemVO[] payBillItems = this.getPayBillItems(aggVOs);
        String pk_org = ((GatheringBillVO) aggVOs[0].getParentVO()).getPk_org();

        this.setCtPuIterms(payBillItems, pk_org);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * 
   * @author wangfengd
   * @time 2010-7-8 ����10:50:22
   */
  private GatheringBillItemVO[] getPayBillItems(AggGatheringBillVO[] payBillVOs) {
    Set<GatheringBillItemVO> set = new HashSet<GatheringBillItemVO>();
    for (AggGatheringBillVO payBillVO : payBillVOs) {
      if (null == payBillVO) {
        continue;
      }
      GatheringBillItemVO[] payBillItemVOs =
          (GatheringBillItemVO[]) payBillVO.getChildrenVO();
      if (ValueUtil.isEmpty(payBillItemVOs)) {
        continue;
      }
      for (GatheringBillItemVO payBillItemVO : payBillItemVOs) {
        if (CTBillType.SaleDaily.getCode().equals(
            payBillItemVO.getTop_billtype()) || "F2".equals(
            payBillItemVO.getTop_billtype())) {
          set.add(payBillItemVO);
        }
      }
    }

    if (!set.isEmpty()) {
      return set.toArray(new GatheringBillItemVO[0]);
    }

    return null;
  }

  private void isCtUsed() {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }
  }

  /**
   * ��������������ת��vo, �����ۺ�ͬ��ֵ��
   * 
   * @param getherBillItems
   *          <p>
   * @author wangfengd
   * @throws BusinessException
   * @time 2010-6-30 ����11:13:19
   */
  private void setCtPuIterms(GatheringBillItemVO[] getherBillItems,
      String pk_org) throws BusinessException {
    if (getherBillItems == null || getherBillItems.length < 1) {
      return;
    }
    List<CtWritebackVO> list = new ArrayList<CtWritebackVO>();
    for (int i = 0; i < getherBillItems.length; i++) {
      CtWritebackVO rewriteVo = new CtWritebackVO();
      //�տ���Ӻ���д�����ۺ�ͬ�ж�
      if(getherBillItems[i].getMoney_bal().compareTo(new UFDouble(0)) == -1){
    	  
    	  String top_itemid = getherBillItems[i].getTop_itemid();//�õ�����տ�õ��ϲ㵥�������� 
    	  rewriteVo.setPk_ct_a(getTopbillid(top_itemid));		 //�õ������տ���ϲ㵥������
    	  rewriteVo.setPk_ct_b(gettopitemid(top_itemid));		 //�õ������տ���ϲ㵥��������
    	  
    	  /**
    	   * HK 2019��4��15��17:03:11
    	   * �����տ� ��Ϊû�� ���κ�ͬ��Ϣ�������޷���д�����⡣
    	   */
    	  if(PuPubVO.getString_TrimZeroLenAsNull(rewriteVo.getPk_ct_a())==null)
    		  rewriteVo.setPk_ct_a(getherBillItems[i].getTop_billid());
    	  if(PuPubVO.getString_TrimZeroLenAsNull(rewriteVo.getPk_ct_b())==null)
    		  rewriteVo.setPk_ct_b(getherBillItems[i].getTop_itemid());
    	  /***END***/
    	  
      }
      else{
    	  rewriteVo.setPk_ct_a(getherBillItems[i].getTop_billid());
          rewriteVo.setPk_ct_b(getherBillItems[i].getTop_itemid());
      }
      // ����ԭ�ҽ�� ��д�ۼ��տ���
      rewriteVo.setNoritotalgpmny(getherBillItems[i].getMoney_cr());
      // �������ҽ���д�ۼƱ����տ���
      rewriteVo.setNtotalgpmny(getherBillItems[i].getLocal_money_cr());
      // ��������,Ĭ��ΪӦ�տ�
      rewriteVo.setPrepay(getherBillItems[i].getPrepay());
      // ������ԭ�ұ���
      rewriteVo.setCurrtype(getherBillItems[i].getPk_currtype());
      // ��������
      rewriteVo.setBilldate(getherBillItems[i].getBilldate());
      // ��Դʱ���
      rewriteVo.setTs(getherBillItems[i].getTop_changets());
      rewriteVo.setPk_org(pk_org);
      list.add(rewriteVo);
    }
    if (list.size() > 0) {
      // add by gaorw5 ��ecm���¼� ������ۺ�ͬ�ۼ��տ������ǰ��aggvo�͸��º��aggvo
      Set<String> pk_ct_sales = new HashSet<String>();
      for (CtWritebackVO vo : list) {
        pk_ct_sales.add(vo.getPk_ct_a());
      }
      ISaledailyMaintain service =
          NCLocator.getInstance().lookup(ISaledailyMaintain.class);
      AggCtSaleVO[] origAggVOs =
          service.queryCtApVoByIds(pk_ct_sales.toArray(new String[pk_ct_sales
              .size()]));
      new RewriteZ3ForGatherBillBP().backWriteNum(list
          .toArray(new CtWritebackVO[list.size()]));
      AggCtSaleVO[] newAggVOs =
          service.queryCtApVoByIds(pk_ct_sales.toArray(new String[pk_ct_sales
              .size()]));
      EventDispatcher.fireEvent(new BdUpdateEvent(CTMDValue.SALEDAILY.value(),
          ICTEventType.TYPE_UPDATERECVMONEY_AFTER, origAggVOs, newAggVOs));
    }
  }

private String gettopitemid(String newtop_billid) throws BusinessException {
	// TODO Auto-generated method stub
	BaseDAO ba = new BaseDAO();
	String top_itemids  =new String();
	String  sql = "select top_itemid from  ar_gatheritem ar where ar.pk_gatheritem = ? and dr = 0;";
	SQLParameter sp= new SQLParameter();
	sp.addParam(newtop_billid);
	try{
		
		Object result = ba.executeQuery(sql, sp, new ColumnProcessor());
		if(result != null){
			top_itemids = result.toString();
		}
		
	}catch(Exception e){
		ExceptionUtils.marsh(e);
	}
	return top_itemids;
}

private String getTopbillid(String top_billid) throws BusinessException {
	// TODO Auto-generated method stub
	BaseDAO ba = new BaseDAO();
	String top_billids =new String();
	String  sql = "select top_billid from  ar_gatheritem ar where ar.pk_gatheritem = ? and dr = 0;";
	SQLParameter sp= new SQLParameter();
	sp.addParam(top_billid);
	try{
		
		Object result = ba.executeQuery(sql, sp, new ColumnProcessor());
		if(result != null){
			top_billids = result.toString();
		}
		
	}catch(Exception e){
		ExceptionUtils.marsh(e);
	}
	return top_billids;
	
}

}
