package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganJHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganXFVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganYZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganZFVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceHy_kainfoUnApproveBP {

	public KainfoBillVO[] unApprove(KainfoBillVO[] clientBills,
			KainfoBillVO[] originBills)throws BusinessException {
		for (KainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KainfoBillVO> update = new BillUpdate<KainfoBillVO>();
		KainfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// ����Ϣ �� ��Ա�������� ɾ����
		BaseDAO baseDAO = new BaseDAO();
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			KainfoHVO kainfoHVO = returnVos[vos_i].getParentVO();		// ����VO
			KainfoBVO[] kainfoBVO = (KainfoBVO[])returnVos[vos_i].getChildrenVO();	// �ӱ�VO
			
			String whereSQL = " csourcetypecode='HK24' and csourcebillid='"+kainfoHVO.getPk_hk_huiyuan_kainfo()+"' ";
			
			baseDAO.deleteByClause(KadanganXFVO.class, whereSQL);//����
			baseDAO.deleteByClause(KadanganYZVO.class, whereSQL);//��ת
			baseDAO.deleteByClause(KadanganCZVO.class, whereSQL);//��ֵ
			baseDAO.deleteByClause(KadanganZFVO.class, whereSQL);//����
			baseDAO.deleteByClause(KadanganJHVO.class, whereSQL);//����
			baseDAO.deleteByClause(KadanganKKPVO.class, whereSQL);//�ɿ�Ʊ��2016��9��18��16:02:45��
			
			baseDAO.executeUpdate(	// ������  ��ԭΪ  ����
					" update hk_huiyuan_kadangan " +
					" set " +
					" kastatus = '����' " +
					",vdef02 = '~' " +
					",vdef03 = '~' " +
					" where vdef02 = '"+ kainfoHVO.getPk_hk_huiyuan_kainfo() +"' " +
					" and kastatus = '����' "
					);
			
			baseDAO.executeUpdate(	// ������  ��ԭΪ  ����
					" update hk_huiyuan_kadangan " +
					" set " +
					" kastatus = '����' " +
					",vdef02 = '~' " +
					",vdef03 = '~' " +
					" where vdef02 = '"+ kainfoHVO.getPk_hk_huiyuan_kainfo() +"' " +
					" and kastatus = '����' "
					);
			
			for(int b_i=0;b_i<kainfoBVO.length;b_i++)
			{
				if(   "��ֵ".equals( kainfoBVO[b_i].getXmdl() ) 
				 && !"���Ͽ�".equals( kainfoBVO[b_i].getXmlx() )
				 && !"�س�" .equals( kainfoBVO[b_i].getXmlx() )
				 && PuPubVO.getUFDouble_ZeroAsNull( kainfoBVO[b_i].getKa_ss() )!=null 
				  )
				{
//					baseDAO.executeUpdate(		// ȡ����д��ͷ�� ��Ʊ�ֶΣ�2016��9��18��16:01:52��
//							" update hk_huiyuan_kadangan " +
//							" set " +
//							" kkpze = nvl(kkpze,0) - " + kainfoBVO[b_i].getKa_ss() +
//							" where pk_hk_huiyuan_kadangan = '"+kainfoBVO[b_i].getKa_pk()+"' ");
				}
				
				if( ( "��ֵ".equals( kainfoBVO[b_i].getXmdl() ) && !"���Ͽ�".equals( kainfoBVO[b_i].getXmlx() )  )
				 ||	"����".equals( kainfoBVO[b_i].getXmdl() )	
				 )
				{
					// ���� ��Ա�� ��ǰ���
					if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getKa_je())!=null )
					{
						baseDAO.executeUpdate(
								" update hk_huiyuan_kadangan " +
								" set " +
								" dq_ye = nvl(dq_ye,0) - " + kainfoBVO[b_i].getKa_je() +
								" where pk_hk_huiyuan_kadangan = '"+kainfoBVO[b_i].getKa_pk()+"' ");
					}
				}
				else if("��ת".equals( kainfoBVO[b_i].getXmdl() ))
				{
					if( kainfoBVO[b_i].getKa_pk()!=null ){
						// ���� ��Ա�� ��ǰ���
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getKa_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) - " + kainfoBVO[b_i].getKa_je() +
									" where pk_hk_huiyuan_kadangan = '"+ kainfoBVO[b_i].getKa_pk() +"' ");
						}
					}
					
					if( kainfoBVO[b_i].getY_ka_pk()!=null ){
						// ���� ��Ա�� ��ǰ���
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getY_ka_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) + " + kainfoBVO[b_i].getY_ka_je() +
									" where pk_hk_huiyuan_kadangan = '"+ kainfoBVO[b_i].getY_ka_pk() +"' ");
						}
					}
					
					if( "0103001500".equals(kainfoBVO[b_i].getY_ka_code()) )
					{// �Ƿ� �����ߴ� ת���ġ� ��Ҫ�����߿��� ����
						
						// ���� ��Ա�� ��ǰ���
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getY_ka_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) + " + kainfoBVO[b_i].getY_ka_je() +
									" where pk_hk_huiyuan_kadangan = '"+ kainfoBVO[b_i].getXmka_pk() +"' ");
						}
						
					}
				}
			}
			
		}
		
		return returnVos;
	}
}
