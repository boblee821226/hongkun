package nc.bs.hkjt.huiyuan.huanka.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceHy_huankaUnApproveBP {

	public HuankaBillVO[] unApprove(HuankaBillVO[] clientBills,
			HuankaBillVO[] originBills)throws BusinessException {
		for (HuankaBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<HuankaBillVO> update = new BillUpdate<HuankaBillVO>();
		HuankaBillVO[] returnVos = update.update(clientBills, originBills);
		
//		// ����Ϣͬ���� ��Ա�����������д���
//		BaseDAO baseDAO = new BaseDAO();
//		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
//		{
//			HuankaHVO huankaHVO = returnVos[vos_i].getParentVO();// ����VO
//			
//			String ka_code   = huankaHVO.getKa_code();
//			String y_ka_code = huankaHVO.getY_ka_code();
//			
//			String whereSQL = " csourcetypecode='HK23' and csourcebillid='"+huankaHVO.getPk_hk_huiyuan_huanka()+"' ";
//			
//			baseDAO.deleteByClause(KadanganHKVO.class, whereSQL);// ɾ�� ����
//			
//			// 1����Ա������
//			baseDAO.executeUpdate(
//					" update hk_huiyuan_kadangan " +
//					" set " +
//					" vbillcode = '" + y_ka_code + "' " +
//					",ka_code   = '" + y_ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " + 
//					" and ka_code = '"+ ka_code +"' " 
//			);
//			
//			// 2����Ա����Ϣ
//			baseDAO.executeUpdate(	// KA_CODE
//					" update hk_huiyuan_kainfo_b " +
//					" set " +
//					" ka_code   = '" + y_ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and ka_code = '"+ ka_code +"' " 
//			);
//			baseDAO.executeUpdate(	// Y_KA_CODE
//					" update hk_huiyuan_kainfo_b " +
//					" set " +
//					" Y_KA_CODE   = '" + y_ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and Y_KA_CODE = '"+ ka_code +"' " 
//			);
//			
//			// 3���ο���Ϣ
//			baseDAO.executeUpdate(	// KA_CODE
//					" update hk_huiyuan_cikainfo_b " +
//					" set " +
//					" ka_code   = '" + y_ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and ka_code = '"+ka_code+"' " 
//			);
//			baseDAO.executeUpdate(	// Y_KA_CODE
//					" update hk_huiyuan_cikainfo_b " +
//					" set " +
//					" Y_KA_CODE   = '" + y_ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and Y_KA_CODE = '"+ka_code+"' " 
//			);
//			
//			// 4����Ʊ��Ϣ
//			baseDAO.executeUpdate(	// KA_CODE
//					" update hk_huiyuan_kaipiaoinfo_b " +
//					" set " +
//					" ka_code   = '" + y_ka_code + "' " +
//					" where (1=1) " +
//					" and dr=0 " +
//					" and ka_code = '"+ka_code+"' " 
//			);
//			
//			
//		}
		
		return returnVos;
	}
}
