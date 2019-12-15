package nc.bs.hkjt.huiyuan.huanka.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * 标准单据审核的BP
 */
public class AceHy_huankaApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public HuankaBillVO[] approve(HuankaBillVO[] clientBills,
			HuankaBillVO[] originBills)throws BusinessException {
		for (HuankaBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<HuankaBillVO> update = new BillUpdate<HuankaBillVO>();
		HuankaBillVO[] returnVos = update.update(clientBills, originBills);
		
		// 将信息同步到 会员卡档案，逐行处理
//		BaseDAO baseDAO = new BaseDAO();
//		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk 生成器
//		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
//		{
//			ArrayList<KadanganHKVO> insertVOs_hk = new ArrayList<KadanganHKVO>();	// 要插入的VO  换卡
//			HuankaHVO huankaHVO = returnVos[vos_i].getParentVO();// 主表VO
//			
//			String ka_code   = huankaHVO.getKa_code();
//			String y_ka_code = huankaHVO.getY_ka_code();
//			
//			// 根据原卡号 查询出源卡pk
//			String pk_hk_huiyuan_kadangan = "";
//			ArrayList<KadanganHVO> ka_list = (ArrayList<KadanganHVO>)baseDAO.retrieveByClause(KadanganHVO.class, " dr=0 and ka_code ='"+y_ka_code+"' ");
//			if( ka_list.size()>0 )
//			{
//				pk_hk_huiyuan_kadangan = ka_list.get(0).getPk_hk_huiyuan_kadangan();
//			}
//			
//			// 封装 换卡VO
//			KadanganHKVO kadanganHKVO = new KadanganHKVO();
//			kadanganHKVO.setPk_hk_huiyuan_kadangan(pk_hk_huiyuan_kadangan);	// 卡pk
//			kadanganHKVO.setKa_code_new(huankaHVO.getKa_code());	// 现卡号
//			kadanganHKVO.setKa_code_old(huankaHVO.getY_ka_code());	// 原卡号
//			kadanganHKVO.setHk_time(huankaHVO.getHk_time());	// 换卡时间
//			kadanganHKVO.setKayue(huankaHVO.getKa_je());		// 卡余额
//			
//			kadanganHKVO.setCsourcetypecode("HK23");			// 上游单据类型
//			kadanganHKVO.setCsourcebillid(huankaHVO.getPk_hk_huiyuan_huanka());		// 上游单据id
//			kadanganHKVO.setCsourcebillbid(huankaHVO.getPk_hk_huiyuan_huanka());	// 上游单据行id
//			kadanganHKVO.setPk_hk_huiyuan_kadangan_hk(pkGenerator.generate());		// 赋值pk
//			kadanganHKVO.setDr(0);
//			kadanganHKVO.setVbdef01( huankaHVO.getVdef01() );	// 门店
//			
//			baseDAO.insertVO(kadanganHKVO);	// 插入换卡 VO
//			
//			// 1、会员卡档案
//			baseDAO.executeUpdate(
//					" update hk_huiyuan_kadangan " +
//					" set " +
//					" vbillcode = '" + ka_code + "' " +
//					",ka_code   = '" + ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " + 
//					" and ka_code = '"+y_ka_code+"' " 
//			);
//			
//			// 2、会员卡信息
//			baseDAO.executeUpdate(	// KA_CODE
//					" update hk_huiyuan_kainfo_b " +
//					" set " +
//					" ka_code   = '" + ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and ka_code = '"+y_ka_code+"' " 
//			);
//			baseDAO.executeUpdate(	// Y_KA_CODE
//					" update hk_huiyuan_kainfo_b " +
//					" set " +
//					" Y_KA_CODE   = '" + ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and Y_KA_CODE = '"+y_ka_code+"' " 
//			);
//			
//			// 3、次卡信息
//			baseDAO.executeUpdate(	// KA_CODE
//					" update hk_huiyuan_cikainfo_b " +
//					" set " +
//					" ka_code   = '" + ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and ka_code = '"+y_ka_code+"' " 
//			);
//			baseDAO.executeUpdate(	// Y_KA_CODE
//					" update hk_huiyuan_cikainfo_b " +
//					" set " +
//					" Y_KA_CODE   = '" + ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and Y_KA_CODE = '"+y_ka_code+"' " 
//			);
//			
//			// 4、开票信息
//			baseDAO.executeUpdate(	// KA_CODE
//					" update hk_huiyuan_kaipiaoinfo_b " +
//					" set " +
//					" ka_code   = '" + ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and ka_code = '"+y_ka_code+"' " 
//			);
//			
//			
//		}
		
		return returnVos;
	}

}
