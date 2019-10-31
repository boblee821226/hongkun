package nc.vo.ct.rule;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.List;

import nc.itf.hkjt.IPub_data;
import nc.vo.bd.payment.PaymentVO;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.purdaily.entity.CtPuBVO;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ct.purdaily.entity.PayPlanVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * @since 6.0
 * @version 2011-2-18 下午05:00:45
 * @author wuxla
 */

public class PayPlanDataUtil {

  public static PayPlanVO[] getPayPlanData(AggCtPuVO[] vos) {
	  
	/**
     * HK 2019年10月31日 10点38分
     * 如果是 闭口合同，需要 按照表体数据 生成 付款计划
     */
    String ctrantypeid = vos[0].getParentVO().getCtrantypeid();
    if(IPub_data.BKHT_type.equals(ctrantypeid)) {
    	List<PayPlanVO> list_new = new ArrayList<PayPlanVO>();
    	for(int i = 0; i < vos.length; i++) {
    		CtPuVO     hvo = vos[i].getParentVO();
    		CtPuBVO[] bvos = vos[i].getCtPuBVO();
    		UFDouble last_rate = new UFDouble(100.00);
    		UFDouble total_money = hvo.getNtotalorigmny();
    		for(int j = 0; j < bvos.length; j++) {
    			CtPuBVO bvo = bvos[j];
    			PayPlanVO pvo = new PayPlanVO();
    			pvo.setCrowno(bvo.getCrowno());
    			pvo.setNorigmny(bvo.getNorigtaxmny());
    			pvo.setBpreflag(UFBoolean.FALSE);
    			pvo.setNmny(bvo.getNorigtaxmny());
    			pvo.setPk_financeorg_v(bvo.getPk_financeorg_v());
    			pvo.setPk_financeorg(bvo.getPk_financeorg());
    			pvo.setCcurrencyid(hvo.getCcurrencyid());
    			pvo.setNtotalorigmny(total_money);
    			pvo.setPk_group(bvo.getPk_group());
    			pvo.setNexchangerate(UFDouble.ONE_DBL);
    			pvo.setCorigcurrencyid(hvo.getCcurrencyid());
    			pvo.setPk_ct_pu(hvo.getPk_ct_pu());
    			pvo.setDbegindate(PuPubVO.getUFDate(bvo.getVbdef2()));
    			if(j == bvos.length-1) {
    				pvo.setNrate(last_rate);
    			} else {
    				UFDouble rate = pvo.getNorigmny()
	    					.div(pvo.getNtotalorigmny())
	    					.multiply(100.00)
	    					.setScale(2, UFDouble.ROUND_HALF_UP);
	    			pvo.setNrate(rate);
	    			last_rate = last_rate.sub(rate);
    			}
    			
    			list_new.add(pvo);
    		}
    	}
    	return list_new.toArray(new PayPlanVO[list_new.size()]);
    }
    /***END***/
	  
    ICtPayPlanData[] payplanDatas = new PayPlanData[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      payplanDatas[i] = new PayPlanData(vos[i]);
    }
    CtPayPlan<PayPlanVO, ICtPayPlanData> payplan =
        new CtPayPlan<PayPlanVO, ICtPayPlanData>(PayPlanVO.class, payplanDatas,
            PaymentVO.class);
    List<PayPlanVO[]> payplanList = payplan.getPlan();

    List<PayPlanVO> list = new ArrayList<PayPlanVO>();
    for (int i = 0; i < payplanDatas.length; ++i) {
      PayPlanVO[] payplanVOs = payplanList.get(i);
      for (PayPlanVO vo : payplanVOs) {
        vo.setPk_ct_pu(vos[i].getParentVO().getPk_ct_pu());
        list.add(vo);
      }
    }
    
    return list.toArray(new PayPlanVO[list.size()]);
  }
}
