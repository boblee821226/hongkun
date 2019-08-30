package nc.bs.hkjt.huiyuan.huanka.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * ��׼������˵�BP
 */
public class AceHy_huankaApproveBP {

	/**
	 * ��˶���
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
		
		// ����Ϣͬ���� ��Ա�����������д���
//		BaseDAO baseDAO = new BaseDAO();
//		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk ������
//		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
//		{
//			ArrayList<KadanganHKVO> insertVOs_hk = new ArrayList<KadanganHKVO>();	// Ҫ�����VO  ����
//			HuankaHVO huankaHVO = returnVos[vos_i].getParentVO();// ����VO
//			
//			String ka_code   = huankaHVO.getKa_code();
//			String y_ka_code = huankaHVO.getY_ka_code();
//			
//			// ����ԭ���� ��ѯ��Դ��pk
//			String pk_hk_huiyuan_kadangan = "";
//			ArrayList<KadanganHVO> ka_list = (ArrayList<KadanganHVO>)baseDAO.retrieveByClause(KadanganHVO.class, " dr=0 and ka_code ='"+y_ka_code+"' ");
//			if( ka_list.size()>0 )
//			{
//				pk_hk_huiyuan_kadangan = ka_list.get(0).getPk_hk_huiyuan_kadangan();
//			}
//			
//			// ��װ ����VO
//			KadanganHKVO kadanganHKVO = new KadanganHKVO();
//			kadanganHKVO.setPk_hk_huiyuan_kadangan(pk_hk_huiyuan_kadangan);	// ��pk
//			kadanganHKVO.setKa_code_new(huankaHVO.getKa_code());	// �ֿ���
//			kadanganHKVO.setKa_code_old(huankaHVO.getY_ka_code());	// ԭ����
//			kadanganHKVO.setHk_time(huankaHVO.getHk_time());	// ����ʱ��
//			kadanganHKVO.setKayue(huankaHVO.getKa_je());		// �����
//			
//			kadanganHKVO.setCsourcetypecode("HK23");			// ���ε�������
//			kadanganHKVO.setCsourcebillid(huankaHVO.getPk_hk_huiyuan_huanka());		// ���ε���id
//			kadanganHKVO.setCsourcebillbid(huankaHVO.getPk_hk_huiyuan_huanka());	// ���ε�����id
//			kadanganHKVO.setPk_hk_huiyuan_kadangan_hk(pkGenerator.generate());		// ��ֵpk
//			kadanganHKVO.setDr(0);
//			kadanganHKVO.setVbdef01( huankaHVO.getVdef01() );	// �ŵ�
//			
//			baseDAO.insertVO(kadanganHKVO);	// ���뻻�� VO
//			
//			// 1����Ա������
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
//			// 2����Ա����Ϣ
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
//			// 3���ο���Ϣ
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
//			// 4����Ʊ��Ϣ
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
