package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKPVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * ��׼���������BP
 */
public class AceHy_kaipiaoinfoUnApproveBP {

	public KaipiaoinfoBillVO[] unApprove(KaipiaoinfoBillVO[] clientBills,
			KaipiaoinfoBillVO[] originBills)throws BusinessException {
		for (KaipiaoinfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KaipiaoinfoBillVO> update = new BillUpdate<KaipiaoinfoBillVO>();
		KaipiaoinfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// ����Ϣ �� ��Ա�������� ɾ����
		BaseDAO baseDAO = new BaseDAO();
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganKKPVO> updateVOs_kkp = new ArrayList<KadanganKKPVO>();	// Ҫ���µ�VO  �ɿ�Ʊ
			ArrayList<KadanganKKPVO> deleteVOs_kkp = new ArrayList<KadanganKKPVO>();	// Ҫɾ����VO  �ɿ�Ʊ
			
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])returnVos[vos_i].getChildrenVO();	// �ӱ�VO
			KaipiaoinfoHVO kpHVO = returnVos[vos_i].getParentVO();			// ����VO
			
			String whereSQL = " csourcetypecode='HK33' and csourcebillid='"+kpHVO.getPk_hk_huiyuan_kaipiaoinfo()+"' ";
			
			baseDAO.deleteByClause(KadanganKPVO.class, whereSQL);//��Ʊ
			
			for( int i=0;i<kpBVOs.length;i++ )
			{// ѭ������
				
				String kaxing_name = kpBVOs[i].getKaxing_name();	// �ж��ǲ���  ɾ���� 
				
//				if( null==kpHVO.getVdef01()
//				||	"~".equals(kpHVO.getVdef01())
//				||	"��Ʊ".equals(kpHVO.getVdef01()) 
//				||	"����".equals(kpHVO.getVdef01()) 
//				)
				{
					if( !"ɾ����".equals( kaxing_name ) )
					{
						// ��ԭ �ѿ�Ʊ�ܶ�
//						baseDAO.executeUpdate(
//								" update hk_huiyuan_kadangan " +
//								" set " +
//								" ykpje = nvl(ykpje,0) - " + kpBVOs[i].getFpje() +
//								" where pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' ");
						
						ArrayList<KadanganKKPVO> kkp_list = 
								(ArrayList<KadanganKKPVO>)baseDAO.retrieveByClause(
								KadanganKKPVO.class,
								" 1=1 " +
								" and dr = 0 " +
								" and pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' " +
								" and nvl(ykp_je,0) <> 0.00 ",
//								" and kpjz_time <= '" + (new UFDateTime().toString()) + "'",	// ȡ����������ǰ��
								" kpjz_time desc "
						);
						
						UFDouble fpje = PuPubVO.getUFDouble_NullAsZero( kpBVOs[i].getFpje() );
						
						if( fpje.compareTo(UFDouble.ZERO_DBL)>=0 )
						{//  ��� ��Ʊ��� Ϊ����
							for( KadanganKKPVO kkpVO:kkp_list )
							{
								UFDouble ykp_je = PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() );
	
								if( fpje.compareTo(ykp_je)>=0 )
								{// ��� ��Ʊ��� ���ڵ��� �ѿ�Ʊ�� ��  ���� ʵ����Ʊ��� Ϊ  �ѿ�Ʊ�� ��Ʊ��� ���еֿۡ�
									
									kkpVO.setYkp_je( // �ѿ�Ʊ��� = ֮ǰ�ѿ�Ʊ��� - ������Ʊ���
											  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
										.sub( ykp_je )
									);
									fpje = fpje.sub(ykp_je);
								}
								else
								{// ���� ��  ���� ʵ����Ʊ��� Ϊ  ��Ʊ�� ��Ʊ��� �ֿ�Ϊ0��
									
									kkpVO.setYkp_je( // �ѿ�Ʊ��� = ֮ǰ�ѿ�Ʊ��� - ������Ʊ���
											  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
										.sub( fpje )
									);
									fpje = UFDouble.ZERO_DBL;
								}
								
								updateVOs_kkp.add(kkpVO);	// ���Ҫ���µ�vo
								
								if(fpje.compareTo(UFDouble.ZERO_DBL)==0)
								{// �����Ʊ���  �ֿ�Ϊ0�ˣ� ���ٵֿ� �˳�ѭ��
									break;
								}
							}
						}
						else
						{// ��� ��Ʊ��� Ϊ����
							
							// ���� ��û��ԭ�� �������ɳ����Ŀɿ�Ʊ
							for( KadanganKKPVO kkpVO:kkp_list )
							{
								if( kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b().equals( kkpVO.getCsourcebillbid() ) )	// ���ε�����id
								{
									UFDouble kkp_je = // �ɿ�Ʊ���  = �ɿ�Ʊ��� - �ѿ�Ʊ���
											  PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
										.sub( PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
									);
									
									if( kkp_je.sub(kpBVOs[i].getFpje()).compareTo(UFDouble.ZERO_DBL)<0 )
									{
										throw new BusinessException("��"+kpBVOs[i].getKa_code()+"��  ����Ʊ���㣬�޷�����");
									}
									else if(  kkp_je.sub(kpBVOs[i].getFpje()).compareTo(UFDouble.ZERO_DBL)==0  )
									{
										deleteVOs_kkp.add(kkpVO);	// ���Ҫɾ����vo
										
										fpje = UFDouble.ZERO_DBL;
									}
									else
									{
										kkpVO.setYkp_je( 
											      PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() )
											.sub( PuPubVO.getUFDouble_NullAsZero( kpBVOs[i].getFpje()) )
										);
										
										updateVOs_kkp.add(kkpVO);	// ���Ҫ���µ�vo
										
										fpje = UFDouble.ZERO_DBL;
									}
									
									break;
								}
							}
							
							if( fpje.compareTo(UFDouble.ZERO_DBL)!=0 )
							{
								for(int kkp_i=0;kkp_i<kkp_list.size();kkp_i++)
								{
									KadanganKKPVO kkpVO = kkp_list.get(kkp_i);
									
									UFDouble kkp_je = // �ɿ�Ʊ���  = �ɿ�Ʊ��� - �ѿ�Ʊ���
											  PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
										.sub( PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
									);
										
									if( kkp_je.compareTo(UFDouble.ZERO_DBL)!=0 
									 || kkp_i == kkp_list.size()-1
									  )
									{
										
										if( kkp_je.sub(kpBVOs[i].getFpje()).compareTo(UFDouble.ZERO_DBL)<0 )
										{
											throw new BusinessException("��"+kpBVOs[i].getKa_code()+"��  ����Ʊ���㣬�޷�����");
										}
										else
										{
											kkpVO.setYkp_je( // �ѿ�Ʊ = �ѿ�Ʊ + ��ǰ��Ʊ
													  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
												.sub( PuPubVO.getUFDouble_NullAsZero(kpBVOs[i].getFpje()) )
											);
											
											updateVOs_kkp.add(kkpVO);	// ���Ҫ���µ�vo
											
											fpje = UFDouble.ZERO_DBL;
										}
									}
								}
							}
						}
						
						// ��� ѭ������  ��Ʊ������0����˵��  �ɿ�Ʊ���������׳�����
						if(fpje.compareTo(UFDouble.ZERO_DBL)!=0)
						{
							throw new BusinessException("��"+kpBVOs[i].getKa_code()+"��  ����Ʊ���㣬�޷�����");
						}
						
					}
					else
					{
						{// ����  �ѿ�Ʊ�ܶ� old
							baseDAO.executeUpdate(
									" update hk_huiyuan_kaipiao_old " +
									" set " +
									" ykpze = nvl(ykpze,0) - " + kpBVOs[i].getFpje() +
									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
						}
					}
				}
//				else if( "ת��".equals(kpHVO.getVdef01()) )
//				{
//					if( !"ɾ����".equals( kaxing_name ) )
//					{
//						{// ����  ת�����
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) - " + kpBVOs[i].getFpje() +
//									" where pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' ");
//						}
//					}
//					else
//					{
//						{// ����  �ѿ�Ʊ�ܶ� old
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kaipiao_old " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) - " + kpBVOs[i].getFpje() +
//									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
//						}
//					}
//				}
			}
			
			baseDAO.updateVOList(updateVOs_kkp);	// ���� �ɿ�Ʊ����
		}
		
		return returnVos;
	}
}
