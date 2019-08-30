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
		HashMap<String,UFDouble> map=new HashMap<String,UFDouble>();//���ݱ����˵���pk�Դ���ȯ����ۼ�
		for (SgshujuBVO bvo : bvos) {
			if(!jzfs_djq(bvo)){//������Ǵ���ȯ�򲻼���
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
		for (String key : map.keySet()) {//�����˵��������ȯ��
			ZhangdanBVO bvo=(ZhangdanBVO)getSuperDMO().queryByPrimaryKey(ZhangdanBVO.class, key);
			if(bvo==null)continue;
			bvo.setDaijinquan(map.get(key));
//			bvo.setDaijinquan(map.get(key).compareTo(bvo.getShishou())>0?bvo.getShishou():map.get(key));//�������ȯ������ʵ�ս���ֱ�Ӹ�ֵΪʵ�ս��
			bvoArray[i]=bvo;
			i++;
		}
		
		HashMap<String,ArrayList<ZhangdanBVO>> bodymaps=new HashMap<String,ArrayList<ZhangdanBVO>>();
		for (ZhangdanBVO zhangdanBVO : bvoArray) {//���˵�����pk����
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
		
		
		ArrayList<ZhangdanBillVO> newZhangDanVOS=new ArrayList<ZhangdanBillVO>();//���¼������˵�����
		for (String key : bodymaps.keySet()) {
			ZhangdanHVO hvo=(ZhangdanHVO)getSuperDMO().queryByPrimaryKey(ZhangdanHVO.class, key);
			CircularlyAccessibleValueObject[]zdbvos=(CircularlyAccessibleValueObject[])getSuperDMO().queryAllBodyData("HK01", ZhangdanBVO.class, hvo.getPk_hk_dzpt_hg_zhangdan(), "nvl(dr,0)=0");
			
			
			HashMap<String,ZhangdanBVO> newBodyVos=new HashMap<String,ZhangdanBVO>();
			for (CircularlyAccessibleValueObject circularlyAccessibleValueObject : zdbvos) {
				ZhangdanBVO b=(ZhangdanBVO)circularlyAccessibleValueObject;
				b.setDaijinquan(UFDouble.ZERO_DBL);//������ȯ����Ϊ0
				newBodyVos.put(b.getPk_hk_dzpt_hg_zhangdan_b(), b);
			}
			
			for (ZhangdanBVO b : bodymaps.get(key)) {//���ֹ������еõ��Ĵ���ȯ�����˵�ԭ���ݽ��и���
				newBodyVos.put(b.getPk_hk_dzpt_hg_zhangdan_b(), b);
			}
			
			ZhangdanBillVO zdaggvo=new ZhangdanBillVO();
			zdaggvo.setParentVO(hvo);
			zdaggvo.setChildrenVO(newBodyVos.values().toArray(new ZhangdanBVO[]{}));
			execBodySgYouHui_kz(zdaggvo);
			
			execBodyMoneyFenTan(zdaggvo);//���������ֵ�ķ�̯����
			newZhangDanVOS.add(zdaggvo);
			}
		
		
		for (ZhangdanBillVO zhangdanBillVO : newZhangDanVOS) {
			 ZhangdanBVO[] bodyvos=(ZhangdanBVO[])zhangdanBillVO.getChildrenVO();
			 getSuperDMO().updateArray(bodyvos);
		}
		}
}
