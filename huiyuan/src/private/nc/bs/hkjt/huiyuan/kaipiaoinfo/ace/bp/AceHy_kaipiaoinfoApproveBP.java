package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.generator.SequenceGenerator;
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
 * ��׼������˵�BP
 */
public class AceHy_kaipiaoinfoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KaipiaoinfoBillVO[] approve(KaipiaoinfoBillVO[] clientBills,
			KaipiaoinfoBillVO[] originBills)throws BusinessException {
		for (KaipiaoinfoBillVO clientBill : clientBills) {
			
			/**
			 * ��У��
			 * 1������״̬ �Ĳ������
			 */
			KaipiaoinfoHVO kpHVO = clientBill.getParentVO();	// ����VO
			String fpzt = kpHVO.getVdef02();	// ��Ʊ״̬
			if(  fpzt!=null
			  && fpzt.length()>0
			  &&!fpzt.equals("����")
			){
				throw new BusinessException("���� �쳣״̬�ķ�Ʊ��������ˣ�");
			}
			/**END*/
			
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		
		BillUpdate<KaipiaoinfoBillVO> update = new BillUpdate<KaipiaoinfoBillVO>();
		KaipiaoinfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		
		// ����Ϣͬ���� ��Ա�����������д���
		BaseDAO baseDAO = new BaseDAO();
		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk ������
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganKPVO>  insertVOs_kp  = new ArrayList<KadanganKPVO>();		// Ҫ�����VO  ��Ʊ
			ArrayList<KadanganKKPVO> updateVOs_kkp = new ArrayList<KadanganKKPVO>();	// Ҫ���µ�VO  �ɿ�Ʊ
			ArrayList<KadanganKKPVO> insertVOs_kkp = new ArrayList<KadanganKKPVO>();	// Ҫ�����VO  �ɿ�Ʊ��������
			
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])returnVos[vos_i].getChildrenVO();	// �ӱ�VO
			KaipiaoinfoHVO kpHVO = returnVos[vos_i].getParentVO();			// ����VO
			
			for( int i=0;i<kpBVOs.length;i++ )
			{
				String kaxing_name = kpBVOs[i].getKaxing_name();	// �ж��ǲ���  ɾ���� 
				
//				if( null==kpHVO.getVdef01()
//				||	"~".equals(kpHVO.getVdef01())
//				||	"��Ʊ".equals(kpHVO.getVdef01()) 
//				||  "����".equals(kpHVO.getVdef01())
//				)
				{
					if( !"ɾ����".equals( kaxing_name ) )
					{
						/**
						 * ���� ��Ʊ��Ϣ
						 */
						KadanganKPVO kpvo = new KadanganKPVO();
						kpvo.setPk_hk_huiyuan_kadangan( kpBVOs[i].getKa_pk() );	// ��pk
						kpvo.setKp_time( kpHVO.getFpsj() );		// ��Ʊʱ��
						kpvo.setKp_je( kpBVOs[i].getFpje() );	// ��Ʊ���
						kpvo.setFph( kpHVO.getFph() );			// ��Ʊ��
						
						kpvo.setCsourcetypecode("HK33");			// ���ε�������
						kpvo.setCsourcebillid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo());	// ���ε���id
						kpvo.setCsourcebillbid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b());	// ���ε�����id
						kpvo.setPk_hk_huiyuan_kadangan_kp(pkGenerator.generate());			// ��ֵpk
						kpvo.setDr(0);
						kpvo.setVbdef01( kpHVO.getVdef01() );	// �ŵ�
						
						insertVOs_kp.add(kpvo);
						/**END*/
						
						{// ����  �ѿ�Ʊ�ܶ�
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" ykpje = nvl(ykpje,0) + " + kpvo.getKp_je() +
//									" where pk_hk_huiyuan_kadangan = '"+kpvo.getPk_hk_huiyuan_kadangan()+"' ");
							
							ArrayList<KadanganKKPVO> kkp_list = 
									(ArrayList<KadanganKKPVO>)baseDAO.retrieveByClause(
									KadanganKKPVO.class,
									" 1=1 " +
									" and dr = 0 " +
									" and pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' " +
									" and nvl(kkp_je,0) - nvl(ykp_je,0) <> 0.00 " ,
//									" and kpjz_time <= '" + (new UFDateTime().toString()) + "'",	// ȡ����������ǰ��
									" kpjz_time "	// order by ��Ʊ����ʱ��
							);
							
							UFDouble fpje = PuPubVO.getUFDouble_NullAsZero( kpBVOs[i].getFpje() );
							
							if( fpje.compareTo(UFDouble.ZERO_DBL)>=0 )
							{//  ��� ��Ʊ��� Ϊ������ ������ �ֿ�
								for( KadanganKKPVO kkpVO:kkp_list )
								{
									UFDouble kkp_je = // �ɿ�Ʊ���  = �ɿ�Ʊ��� - �ѿ�Ʊ���
											 PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
										.sub(PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
									);
									if( fpje.compareTo(kkp_je)>=0 )
									{// ��� ��Ʊ��� ���ڵ��� �ɿ�Ʊ�� ��  ���� ʵ�ʿ�Ʊ��� Ϊ  �ɿ�Ʊ�� ��Ʊ��� ���еֿۡ�
										
										kkpVO.setYkp_je( // �ѿ�Ʊ��� = ֮ǰ�ѿ�Ʊ��� + ���ο�Ʊ���
												  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
											.add( kkp_je )
										);
										fpje = fpje.sub(kkp_je);
									}
									else
									{// ���� ��  ���� ʵ�ʿ�Ʊ��� Ϊ  ��Ʊ�� ��Ʊ��� �ֿ�Ϊ0��
										
										kkpVO.setYkp_je( // �ѿ�Ʊ��� = ֮ǰ�ѿ�Ʊ��� + ���ο�Ʊ���
												  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
											.add( fpje )
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
							{// ��� ��Ʊ��� Ϊ��������ֱ���ۼ�
								if( kkp_list!=null && kkp_list.size()>0 )
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
											kkpVO.setYkp_je( // �ѿ�Ʊ = �ѿ�Ʊ + ��ǰ��Ʊ
													  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
												.add( PuPubVO.getUFDouble_NullAsZero(kpBVOs[i].getFpje()) )
											);
											
											updateVOs_kkp.add(kkpVO);	// ���Ҫ���µ�vo
											
											fpje = UFDouble.ZERO_DBL;
											
											break;
										}
									}
								}
								else
								{// ���û�� �ɿ�Ʊ����  ������һ����¼
									KadanganKKPVO kkpvo = new KadanganKKPVO();
									kkpvo.setPk_hk_huiyuan_kadangan( kpBVOs[i].getKa_pk() );	// ��pk
									kkpvo.setCz_time( new UFDateTime(kpHVO.getDbilldate().toDate()) );	// ��ֵʱ��
									kkpvo.setKpjz_time( new UFDateTime(kpHVO.getDbilldate().getDateAfter(180).toDate()) );	// ��Ʊ����ʱ��
									kkpvo.setKkp_je( UFDouble.ZERO_DBL );	// �ɿ�Ʊ���
									kkpvo.setYkp_je( kpBVOs[i].getFpje() );	// �ѿ�Ʊ���
									
									kkpvo.setCsourcetypecode("HK33");			// ���ε�������
									kkpvo.setCsourcebillid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo());		// ���ε���id
									kkpvo.setCsourcebillbid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b());	// ���ε�����id
									kkpvo.setPk_hk_huiyuan_kadangan_kkp(pkGenerator.generate());			// ��ֵpk
									kkpvo.setDr(0);
									kkpvo.setVbdef01( kpHVO.getVdef01() );	// �ŵ�
									
									insertVOs_kkp.add(kkpvo);
									
									fpje = UFDouble.ZERO_DBL;
								}
							}
							
							// ��� ѭ������  ��Ʊ������0����˵��  �ɿ�Ʊ���������׳�����
							if(fpje.compareTo(UFDouble.ZERO_DBL)!=0)
							{
								throw new BusinessException("��"+kpBVOs[i].getKa_code()+"��  �ɿ�Ʊ���㣬������ˡ�");
							}
						}
					}
					else
					{
						{// ����  �ѿ�Ʊ�ܶ� old
							baseDAO.executeUpdate(
									" update hk_huiyuan_kaipiao_old " +
									" set " +
									" ykpze = nvl(ykpze,0) + " + kpBVOs[i].getFpje() +
									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
						}
					}
				}
//				else if( "ת��".equals(kpHVO.getVdef01()) )
//				{
//					if( !"ɾ����".equals( kaxing_name ) )
//					{
//						KadanganKPVO kpvo = new KadanganKPVO();
//						kpvo.setPk_hk_huiyuan_kadangan( kpBVOs[i].getKa_pk() );	// ��pk
//						kpvo.setKp_time( kpHVO.getFpsj() );		// ��Ʊʱ��
//						kpvo.setKp_je( kpBVOs[i].getFpje() );	// ��Ʊ���
//						kpvo.setFph( kpHVO.getFph() );			// ��Ʊ��
//						
//						kpvo.setCsourcetypecode("HK33");			// ���ε�������
//						kpvo.setCsourcebillid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo());	// ���ε���id
//						kpvo.setCsourcebillbid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b());	// ���ε�����id
//						kpvo.setPk_hk_huiyuan_kadangan_kp(pkGenerator.generate());			// ��ֵpk
//						kpvo.setDr(0);
//						kpvo.setVbdef01( kpHVO.getVdef01() );	// �ŵ�
//						
//						insertVOs_kp.add(kpvo);
//						
//						{// ����  ת�����
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) + " + kpvo.getKp_je() +
//									" where pk_hk_huiyuan_kadangan = '"+kpvo.getPk_hk_huiyuan_kadangan()+"' ");
//						}
//					}
//					else
//					{
//						{// ����  �ѿ�Ʊ�ܶ� old
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kaipiao_old " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) + " + kpBVOs[i].getFpje() +
//									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
//						}
//					}
//				}
			}
			
			baseDAO.insertVOList(insertVOs_kp);		// ���� ��Ʊ����
			baseDAO.updateVOList(updateVOs_kkp);	// ���� �ɿ�Ʊ����
			baseDAO.insertVOList(insertVOs_kkp);	// ���� �ɿ�Ʊ����
		}
		
		return returnVos;
	}

}
