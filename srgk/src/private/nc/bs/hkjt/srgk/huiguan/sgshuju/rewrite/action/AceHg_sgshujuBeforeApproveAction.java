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
			if(!jzfs_djq(bvo)){//������Ǵ���ȯ�򲻼���
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
				errMsg+="�����˵�:["+hvo.getVbillcode()+"]����ȯ�ܺ�("+map.get(key).setScale(2,UFDouble.ROUND_HALF_UP)+")���Ӧ�˵��д���ȯ���("+(hvo.getDaijinquan()==null?UFDouble.ZERO_DBL:hvo.getDaijinquan())+")�����,��������ˣ�\r\n";
			}
			
		}
		if(errMsg.length()>0){
			ExceptionUtils.wrappBusinessException(errMsg);
		}
	}

}
