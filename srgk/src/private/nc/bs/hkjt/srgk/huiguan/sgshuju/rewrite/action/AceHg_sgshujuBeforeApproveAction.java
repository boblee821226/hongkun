package nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action;

import java.util.HashMap;

import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class AceHg_sgshujuBeforeApproveAction extends ReWriteBaseAction{

	@Override
	public void execute(SgshujuBillVO aggvo) throws BusinessException {
		SgshujuBVO[] bvos=(SgshujuBVO[])aggvo.getChildrenVO();
		HashMap<String,UFDouble> map=new HashMap<String,UFDouble>();
		for (SgshujuBVO bvo : bvos) {
			if(!jzfs_djq(bvo)){//如果不是代金券则不计算
				continue;
			}
			if(map.containsKey(bvo.getZd_pk())){
				map.put(bvo.getZd_pk(),map.get(bvo.getZd_pk()).add(bvo.getTz_km_data_1()==null?UFDouble.ZERO_DBL:bvo.getTz_km_data_1()) );
			}else{
				map.put(bvo.getZd_pk(), bvo.getTz_km_data_1()==null?UFDouble.ZERO_DBL:bvo.getTz_km_data_1());
			}
			
		}
		
		String errMsg="";
		for (String key : map.keySet()) {
			ZhangdanHVO hvo=(ZhangdanHVO)getSuperDMO().queryByPrimaryKey(ZhangdanHVO.class, key);
			if(hvo==null)continue;
			if((hvo.getDaijinquan()==null?UFDouble.ZERO_DBL:hvo.getDaijinquan()).compareTo(map.get(key))!=0){
				errMsg+="表体账单:["+hvo.getVbillcode()+"]代金券总和("+map.get(key).setScale(2,UFDouble.ROUND_HALF_UP)+")与对应账单中代金券金额("+(hvo.getDaijinquan()==null?UFDouble.ZERO_DBL:hvo.getDaijinquan())+")不相等,不允许审核！\r\n";
			}
			
		}
		if(errMsg.length()>0){
			ExceptionUtils.wrappBusinessException(errMsg);
		}
	}

}
