package nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AceHg_sgshujuAfterUNApproveAction extends ReWriteBaseAction{

	@Override
	public void execute(SgshujuBillVO aggvo) throws BusinessException {
		SgshujuBVO[] bvos=(SgshujuBVO[])aggvo.getChildrenVO();
		HashMap<String,String> map=new HashMap<String,String>();//依据表体账单主表pk，提取出更新过的账单数据
		for (SgshujuBVO bvo : bvos) {
			if(!jzfs_djq(bvo)){//如果不是代金券则不计算
				continue;
			}
				map.put(bvo.getZd_pk(),bvo.getZd_pk());
		}
		
		
		ArrayList<ZhangdanBillVO> newZhangDanVOS=new ArrayList<ZhangdanBillVO>();//重新计算后的账单数据
		for (String key : map.keySet()) {//账单VO
			ZhangdanHVO hvo=(ZhangdanHVO)getSuperDMO().queryByPrimaryKey(ZhangdanHVO.class, key);
			if(hvo==null){continue;}
			String sql="select * from hk_srgk_hg_zhangdan_b where nvl(dr,0)=0 and pk_hk_dzpt_hg_zhangdan='"+hvo.getPk_hk_dzpt_hg_zhangdan()+"' order by to_number(vrowno)";
			ArrayList<ZhangdanBVO> zdbvos=(ArrayList<ZhangdanBVO>)getBaseDAO().executeQuery(sql, new BeanListProcessor(ZhangdanBVO.class));
			LinkedHashMap<String,ZhangdanBVO> newBodyVos=new LinkedHashMap<String,ZhangdanBVO>();
			for (int i=0;i<zdbvos.size();i++) {
				ZhangdanBVO b=(ZhangdanBVO)zdbvos.get(i);
				b.setDaijinquan(UFDouble.ZERO_DBL);//将代金券设置为0
				b.setShishou(b.getYingshou().sub(b.getYouhui_zidong()).setScale(2, UFDouble.ROUND_HALF_UP));//实收=应收-自动优惠
				newBodyVos.put(b.getPk_hk_dzpt_hg_zhangdan_b(), b);
			}
			
			ZhangdanBillVO zdaggvo=new ZhangdanBillVO();
			zdaggvo.setParentVO(hvo);
			zdaggvo.setChildrenVO(newBodyVos.values().toArray(new ZhangdanBVO[]{}));
			execBodyDaijinquanFentan(zdaggvo);//代金券金额分摊，手工优惠中其他字段不变
			setShiShou(zdaggvo);//重新设置实收值
			
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
