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
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * 收款单删除，销售合同相应事件
 * 
 * @since 6.0
 * @version 2010-12-20 下午06:15:40
 * @author wangfengd
 */
public class SaleGatherbillDeleteHandler implements IBusinessListener {
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    try {
      if (IEventType.TYPE_DELETE_AFTER.equals(event.getEventType())) {
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
   * 方法功能描述：简要描述本方法的功能。
   * 
   * @author wangfengd
   * @time 2010-7-8 上午10:50:22
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
            payBillItemVO.getTop_billtype())
            //begin 红冲收款单删除没有回写销售合同累计收款金额
            ||(MathTool.compareTo(payBillItemVO.getMoney_cr(), UFDouble.ZERO_DBL)<0)&&payBillItemVO.getTop_billtype().equals("F2")) {
        	//end 红冲收款单删除回写销售合同累计收款金额
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
   * 方法功能描述：转换vo, 给销售合同赋值。
   * 
   * @param getherBillItems
   *          <p>
   * @author wangfengd
   * @throws BusinessException
   * @time 2010-6-30 上午11:13:19
   */
  private void setCtPuIterms(GatheringBillItemVO[] getherBillItems,
      String pk_org) throws BusinessException {
    if (getherBillItems == null || getherBillItems.length < 1) {
      return;
    }
    List<CtWritebackVO> list = new ArrayList<CtWritebackVO>();
    for (int i = 0; i < getherBillItems.length; i++) {
        CtWritebackVO rewriteVo = new CtWritebackVO();
//        //begin  红冲收款单删除回写销售合同累计收款金额
//        if(getherBillItems[i].getMoney_bal().compareTo(new UFDouble(0)) == -1){
//      	  
//      	  String top_itemid = getherBillItems[i].getTop_itemid();//得到红冲收款得到上层单据行主键 
//      	  rewriteVo.setPk_ct_a(getTopbillid(top_itemid));//得到蓝冲收款单的上层单据主键
//      	  rewriteVo.setPk_ct_b(gettopitemid(top_itemid));//得到蓝冲收款单的上层单据行主键
//        }
//        //end 红冲收款单删除回写销售合同累计收款金额
//        else{
//      	  rewriteVo.setPk_ct_a(getherBillItems[0].getTop_billid());
//
//            rewriteVo.setPk_ct_b(getherBillItems[i].getTop_itemid());
//        }

        //收款单增加红冲回写，销售合同判断
        if(getherBillItems[i].getMoney_bal().compareTo(new UFDouble(0)) == -1){
      	  
      	  String top_itemid = getherBillItems[i].getTop_itemid();//得到红冲收款得到上层单据行主键 
      	  rewriteVo.setPk_ct_a(getTopbillid(top_itemid));		 //得到蓝冲收款单的上层单据主键
      	  rewriteVo.setPk_ct_b(gettopitemid(top_itemid));		 //得到蓝冲收款单的上层单据行主键
      	  
      	  /**
      	   * HK 2019年4月15日17:03:11
      	   * 负数收款 因为没有 上游合同信息，导致无法回写的问题。
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
        
      // 贷方原币金额 回写累计收款金额
      rewriteVo.setNoritotalgpmny(MathTool.oppose(getherBillItems[i]
          .getMoney_cr()));
      // 贷方本币金额回写累计本币收款金额
      rewriteVo.setNtotalgpmny(MathTool.oppose(getherBillItems[i]
          .getLocal_money_cr()));
      // 付款性质,默认为应收款
      rewriteVo.setPrepay(getherBillItems[i].getPrepay());
      // 表体行原币币种
      rewriteVo.setCurrtype(getherBillItems[i].getPk_currtype());
      // 单据日期
      rewriteVo.setBilldate(getherBillItems[i].getBilldate());
      rewriteVo.setPk_org(pk_org);
      list.add(rewriteVo);
    }
    if (list.size() > 0) {
      // add by gaorw5 给ecm发事件 获得销售合同累计收款金额更新前的aggvo和更新后的aggvo
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

  //begin 红冲收款单删除回写销售合同累计收款金额
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
	//end 红冲收款单删除回写销售合同累计收款金额
}
