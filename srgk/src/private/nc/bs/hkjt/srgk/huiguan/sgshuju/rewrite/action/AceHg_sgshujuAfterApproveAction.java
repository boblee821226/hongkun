package nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action;

import java.util.ArrayList;
import java.util.HashMap;

import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;

public class AceHg_sgshujuAfterApproveAction extends ReWriteBaseAction{
	@Override
	public void execute(SgshujuBillVO aggvo) throws BusinessException {
		SgshujuBVO[] bvos=(SgshujuBVO[])aggvo.getChildrenVO();
		HashMap<String,UFDouble> map=new HashMap<String,UFDouble>();//依据表体账单行pk对代金券金额累加
		for (SgshujuBVO bvo : bvos) {
			if(!jzfs_djq(bvo)){//如果不是代金券则不计算
				continue;
			}
			if(map.containsKey(bvo.getZd_item_pk())){
				map.put(bvo.getZd_item_pk(),map.get(bvo.getZd_item_pk()).add(bvo.getTz_km_data_1()==null?UFDouble.ZERO_DBL:bvo.getTz_km_data_1()) );
			}else{
				map.put(bvo.getZd_item_pk(), bvo.getTz_km_data_1()==null?UFDouble.ZERO_DBL:bvo.getTz_km_data_1());
			}
			
		}
		
		
		ZhangdanBVO []bvoArray=new ZhangdanBVO[map.keySet().size()];
		int i=0;
		for (String key : map.keySet()) {//更新账单表体代金券行
			ZhangdanBVO bvo=(ZhangdanBVO)getSuperDMO().queryByPrimaryKey(ZhangdanBVO.class, key);
			if(bvo==null)continue;
			bvo.setDaijinquan(map.get(key));
//			bvo.setDaijinquan(map.get(key).compareTo(bvo.getShishou())>0?bvo.getShishou():map.get(key));//如果代金券金额大于实收金额，则直接赋值为实收金额
			bvoArray[i]=bvo;
			i++;
		}
		
		HashMap<String,ArrayList<ZhangdanBVO>> bodymaps=new HashMap<String,ArrayList<ZhangdanBVO>>();
		for (ZhangdanBVO zhangdanBVO : bvoArray) {//按账单主表pk分组
			if(zhangdanBVO==null)continue;
			if(bodymaps.containsKey(zhangdanBVO.getPk_hk_dzpt_hg_zhangdan())){
				ArrayList<ZhangdanBVO> bvoList=bodymaps.get(zhangdanBVO.getPk_hk_dzpt_hg_zhangdan());
				bvoList.add(zhangdanBVO);
				bodymaps.put(zhangdanBVO.getPk_hk_dzpt_hg_zhangdan(), bvoList);
			}else{
				ArrayList<ZhangdanBVO> bvoList=new ArrayList<ZhangdanBVO>();
				bvoList.add(zhangdanBVO);
				bodymaps.put(zhangdanBVO.getPk_hk_dzpt_hg_zhangdan(), bvoList);
			}
			
		}
		
		
		ArrayList<ZhangdanBillVO> newZhangDanVOS=new ArrayList<ZhangdanBillVO>();//重新计算后的账单数据
		for (String key : bodymaps.keySet()) {
			ZhangdanHVO hvo=(ZhangdanHVO)getSuperDMO().queryByPrimaryKey(ZhangdanHVO.class, key);
			CircularlyAccessibleValueObject[]zdbvos=(CircularlyAccessibleValueObject[])getSuperDMO().queryAllBodyData("HK01", ZhangdanBVO.class, hvo.getPk_hk_dzpt_hg_zhangdan(), "nvl(dr,0)=0");
			
			
			HashMap<String,ZhangdanBVO> newBodyVos=new HashMap<String,ZhangdanBVO>();
			for (CircularlyAccessibleValueObject circularlyAccessibleValueObject : zdbvos) {
				ZhangdanBVO b=(ZhangdanBVO)circularlyAccessibleValueObject;
				b.setDaijinquan(UFDouble.ZERO_DBL);//将代金券设置为0
				newBodyVos.put(b.getPk_hk_dzpt_hg_zhangdan_b(), b);
			}
			
			for (ZhangdanBVO b : bodymaps.get(key)) {//用手工数据中得到的代金券金额对账单原数据进行覆盖
				newBodyVos.put(b.getPk_hk_dzpt_hg_zhangdan_b(), b);
			}
			
			ZhangdanBillVO zdaggvo=new ZhangdanBillVO();
			zdaggvo.setParentVO(hvo);
			zdaggvo.setChildrenVO(newBodyVos.values().toArray(new ZhangdanBVO[]{}));
			execBodySgYouHui_kz(zdaggvo);
			
			execBodyMoneyFenTan(zdaggvo);//表体各项金额值的分摊计算
			newZhangDanVOS.add(zdaggvo);
			}
		
		
		for (ZhangdanBillVO zhangdanBillVO : newZhangDanVOS) {
			 ZhangdanBVO[] bodyvos=(ZhangdanBVO[])zhangdanBillVO.getChildrenVO();
			 getSuperDMO().updateArray(bodyvos);
		}
		}
}
