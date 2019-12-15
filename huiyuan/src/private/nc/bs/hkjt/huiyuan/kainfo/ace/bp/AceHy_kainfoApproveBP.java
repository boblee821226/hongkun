package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganJHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganXFVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganYZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganZFVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoHVO;

/**
 * ��׼������˵�BP
 */
public class AceHy_kainfoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KainfoBillVO[] approve(KainfoBillVO[] clientBills,
			KainfoBillVO[] originBills)throws BusinessException {
		for (KainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KainfoBillVO> update = new BillUpdate<KainfoBillVO>();
		KainfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// ����Ϣͬ���� ��Ա�����������д���
		BaseDAO baseDAO = new BaseDAO();
		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk ������
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganXFVO> insertVOs_xf = new ArrayList<KadanganXFVO>();	// Ҫ�����VO  ����
			ArrayList<KadanganYZVO> insertVOs_yz = new ArrayList<KadanganYZVO>();	// Ҫ�����VO  ��ת
			ArrayList<KadanganCZVO> insertVOs_cz = new ArrayList<KadanganCZVO>();	// Ҫ�����VO  ��ֵ
			ArrayList<KadanganZFVO> insertVOs_zf = new ArrayList<KadanganZFVO>();	// Ҫ�����VO  ����
			ArrayList<KadanganJHVO> insertVOs_jh = new ArrayList<KadanganJHVO>();	// Ҫ�����VO  ����
			ArrayList<KadanganKKPVO> insertVOs_kkp = new ArrayList<KadanganKKPVO>();	// Ҫ�����VO  �ɿ�Ʊ��2016��9��18��15:09:37��
//			ArrayList<KadanganHVO>  updateVOs_ka = new ArrayList<KadanganHVO>();	// Ҫ���µ�VO  ������
			KainfoBVO[] kainfoBVOs = (KainfoBVO[])returnVos[vos_i].getChildrenVO();	// �ӱ�VO
			KainfoHVO kainfoHVO = returnVos[vos_i].getParentVO();		// ����VO
			
			// У�飬�Ƿ��������������ύʱ ��У�飩
			
			// 1��ð�� ���� ��ҵ��ʱ��  ����
			for(int m_i=0;m_i<kainfoBVOs.length-1;m_i++)
			{
				for(int m_j = m_i+1 ; m_j < kainfoBVOs.length ; m_j++)
				{
					UFDateTime ywsj_i = kainfoBVOs[m_i].getYwsj();
					UFDateTime ywsj_j = kainfoBVOs[m_i].getYwsj();
					
					if( ywsj_j.compareTo(ywsj_i)>0 )
					{
						KainfoBVO temp = kainfoBVOs[m_i];
						kainfoBVOs[m_i] = kainfoBVOs[m_j];
						kainfoBVOs[m_j] = temp;
					}
				}
			}
			
			// 2�����д���
			for( int i=0;i<kainfoBVOs.length;i++ )
			{
				String xmdl = kainfoBVOs[i].getXmdl();	// ��Ŀ����
				String xmlx = kainfoBVOs[i].getXmlx();	// ��Ŀ����
				
				if( kainfoBVOs[i].getKa_pk()!=null )
				{// ���� �����ڿյ�  �Ž��д���
					
					if("��ֵ".equals(xmdl))
					{
						// ��ͨ��ֵ
						{
							KadanganCZVO czvo = new KadanganCZVO();	// ��ֵ
							czvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// ��pk
							czvo.setCz_time( kainfoBVOs[i].getYwsj() );	// ��ֵʱ��
							czvo.setZdh( kainfoBVOs[i].getZdh() );		// �˵���
							czvo.setCz_je(kainfoBVOs[i].getKa_je());	// ��ֵ�����
							czvo.setCz_ss(kainfoBVOs[i].getKa_ss());	// ��ֵʵ��
							czvo.setCz_zs(kainfoBVOs[i].getKa_zs());	// ��ֵ����
							czvo.setKayue(kainfoBVOs[i].getKa_yue());	// �����
							
							czvo.setCsourcetypecode("HK24");			// ���ε�������
							czvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// ���ε���id
							czvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
							czvo.setPk_hk_huiyuan_kadangan_cz(pkGenerator.generate());			// ��ֵpk
							czvo.setDr(0);
							czvo.setVbdef01( kainfoHVO.getVdef01() );	// �ŵ�
							czvo.setVbdef02( xmlx );	// ��Ŀ����
							czvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// ҵ������
							
							insertVOs_cz.add(czvo);
							
							
							// ���� �ɿ�Ʊ�ܶ�
							// �س�  ������
							// ����  ������
							if( !"�س�".equals(kainfoBVOs[i].getXmlx()) )
							{
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_ss())!=null )
								{
//									baseDAO.executeUpdate(
//											" update hk_huiyuan_kadangan " +
//											" set " +
//											" kkpze = nvl(kkpze,0) + " + kainfoBVOs[i].getKa_ss() +
//											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
									
									/**
									 *  ������ ��ͷ�Ŀɿ�Ʊ�� ������ɿ�Ʊ-��ҳǩ
									 *  2016��9��18��15:14:23
									 */
									KadanganKKPVO kkpvo = new KadanganKKPVO();	// �ɿ�Ʊ
									kkpvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// ��pk
									kkpvo.setCz_time( kainfoBVOs[i].getYwsj() );// ��ֵʱ��
									kkpvo.setKpjz_time( kainfoBVOs[i].getYwsj().getDateTimeAfter(180) );// ��ֹʱ�� ������ 180�죩
									kkpvo.setZdh( kainfoBVOs[i].getZdh() );		// �˵���
									kkpvo.setKkp_je(kainfoBVOs[i].getKa_ss());	// �ɿ�Ʊ��� = ��ʵ��
									
									kkpvo.setCsourcetypecode("HK24");			// ���ε�������
									kkpvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());	// ���ε���id
									kkpvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
									kkpvo.setPk_hk_huiyuan_kadangan_kkp(pkGenerator.generate());		// ��ֵpk
									kkpvo.setDr(0);
									
									insertVOs_kkp.add(kkpvo);
									
									System.out.println("=="+insertVOs_kkp);
									/**END*/
								}
							}
							
							// ���� ��Ա�� ��ǰ���
							// ���� ��״̬ Ϊ ����
							if( !"���Ͽ�".equals(kainfoBVOs[i].getXmlx()) )
							{// ���Ͽ�  ������
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) + " + kainfoBVOs[i].getKa_je() +
	//										",kastatus = '����' " + 
	//										",vdef02 = '~' " +
	//										",vdef03 = '"+ kainfoHVO.getDbilldate() +"' " +	// ��ֵʱ��
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
								}
							}
							
						}
						
//						if("���Ͽ�".equals(xmlx))
//						{
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" kastatus = '����' " +
//									",vdef02 = '"+ kainfoBVOs[i].getPk_hk_huiyuan_kainfo() +"' " +
//									",vdef03 = '"+ kainfoHVO.getDbilldate() +"' " +	// ����ʱ��
//									" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
//						}
						
					}
					else if("����".equals(xmdl))
					{
						KadanganXFVO xfvo = new KadanganXFVO();
						xfvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// ��pk
						xfvo.setXf_time( kainfoBVOs[i].getYwsj() );	// ����ʱ��
						xfvo.setZdh( kainfoBVOs[i].getZdh() );		// �˵���
						xfvo.setXf_je(kainfoBVOs[i].getKa_je());	// ���ѿ����
						xfvo.setXf_ss(kainfoBVOs[i].getKa_ss());	// ����ʵ��
						xfvo.setKayue(kainfoBVOs[i].getKa_yue());	// �����
						
						xfvo.setCsourcetypecode("HK24");			// ���ε�������
						xfvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// ���ε���id
						xfvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
						xfvo.setPk_hk_huiyuan_kadangan_xf(pkGenerator.generate());			// ��ֵpk
						xfvo.setDr(0);
						xfvo.setVbdef01( kainfoHVO.getVdef01() );	// �ŵ�
						xfvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// ҵ������
						
						insertVOs_xf.add(xfvo);
						
						// ���� ��Ա�� ��ǰ���
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) + " + kainfoBVOs[i].getKa_je() +
									" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
						}
						
					}
					else if("��ת".equals(xmdl))
					{
						if( kainfoBVOs[i].getKa_pk()!=null ){
							
							if( "0203005888".equals(kainfoBVOs[i].getY_ka_code()) 
							&& !(kainfoBVOs[i].getKa_code().compareTo("0302101237")>=0 && kainfoBVOs[i].getKa_code().compareTo("0302101245")<=0)	// ����� һ����  ת��  �����󿨣� �� ����Ϊ ���ϣ���Ϊ  ��ת��
							  )
							{// ����Ǵ� ������ ת�����ģ��� ���Ͽ���  �س崦�� �����Ϊ������
							 // ������� ��������� ת����  �� ͬ���� ��������������ҳǩ��
								KadanganZFVO zfvo = new KadanganZFVO();	// ����
								zfvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// ��pk
								zfvo.setZf_time( kainfoBVOs[i].getYwsj() );	//������תʱ��
								zfvo.setZc_je( UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( kainfoBVOs[i].getKa_je() ) ) );	//���ϻس���ȡ������
//								zfvo.setZf_ss( kainfoBVOs[i].getY_ka_ss() );	//ת��ʵ��
							
								zfvo.setKayue( kainfoBVOs[i].getKa_yue() );	//�����
								zfvo.setZdh( kainfoBVOs[i].getZdh() );		//�˵���
								
								zfvo.setCsourcetypecode("HK24");			// ���ε�������
								zfvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// ���ε���id
								zfvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
								zfvo.setPk_hk_huiyuan_kadangan_zf(pkGenerator.generate());			// ��ֵpk
								zfvo.setDr(0);
								zfvo.setVbdef01( kainfoHVO.getVdef01() );	// �ŵ�
								zfvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// ҵ������
								
								insertVOs_zf.add(zfvo);
								
								// ���� ��Ա�� ��ǰ���
								if( PuPubVO.getUFDouble_ZeroAsNull( zfvo.getZc_je() )!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + zfvo.getZc_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
								}
								
							}
							else
							{
								KadanganYZVO zrvo = new KadanganYZVO();	// ת��
								zrvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// ��pk
								zrvo.setYz_time( kainfoBVOs[i].getYwsj() );	//��תʱ��
								zrvo.setZr_je( kainfoBVOs[i].getKa_je() );	//ת����
								zrvo.setZr_ss( kainfoBVOs[i].getKa_ss() );	//ת��ʵ��
								zrvo.setKayue( kainfoBVOs[i].getKa_yue() );	//�����
								zrvo.setZdh( kainfoBVOs[i].getZdh() );		//�˵���
								// �Է�
								zrvo.setDf_ka_code( kainfoBVOs[i].getY_ka_code() );
								zrvo.setDf_ka_name( kainfoBVOs[i].getY_ka_name() );
								zrvo.setDf_ka_pk( kainfoBVOs[i].getY_ka_pk() );
								zrvo.setDf_kaxing_code( kainfoBVOs[i].getY_kaxing_code() );
								zrvo.setDf_kaxing_name( kainfoBVOs[i].getY_kaxing_name() );
								zrvo.setDf_kaxing_pk( kainfoBVOs[i].getY_kaxing_pk() );
								
								zrvo.setCsourcetypecode("HK24");			// ���ε�������
								zrvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// ���ε���id
								zrvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
								zrvo.setPk_hk_huiyuan_kadangan_yz(pkGenerator.generate());			// ��ֵpk
								zrvo.setDr(0);
								zrvo.setVbdef01( kainfoHVO.getVdef01() );	// �ŵ�
								zrvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// ҵ������
								
								insertVOs_yz.add(zrvo);
								
								// ���� ��Ա�� ��ǰ���
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) + " + kainfoBVOs[i].getKa_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
								}
								
							}
							
						}
						
						if( kainfoBVOs[i].getY_ka_pk()!=null ){
							
							if( "0203005888".equals(kainfoBVOs[i].getKa_code()) 
							|| "���⿨".equals(kainfoBVOs[i].getKa_code())	// ��  ���⿨��ת
							)
							{// �Ƿ�ת��  ���������
							 // ���ת��  ���������  �� ͬ���� ��������������ҳǩ��
								KadanganZFVO zfvo = new KadanganZFVO();	// ����
								zfvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getY_ka_pk() );	// ��pk
								zfvo.setZf_time( kainfoBVOs[i].getYwsj() );	//������תʱ��
								zfvo.setZc_je( kainfoBVOs[i].getKa_je() );	//ת�����
//								zfvo.setZf_ss( kainfoBVOs[i].getY_ka_ss() );	//ת��ʵ��
							
								zfvo.setKayue( kainfoBVOs[i].getY_ka_yue() );	//�����
								zfvo.setZdh( kainfoBVOs[i].getZdh() );		//�˵���
								
								zfvo.setCsourcetypecode("HK24");			// ���ε�������
								zfvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// ���ε���id
								zfvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
								zfvo.setPk_hk_huiyuan_kadangan_zf(pkGenerator.generate());			// ��ֵpk
								zfvo.setDr(0);
								zfvo.setVbdef01( kainfoHVO.getVdef01() );	// �ŵ�
								zfvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// ҵ������
								
								insertVOs_zf.add(zfvo);
								
								// ���� ��Ա�� ��ǰ���
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getY_ka_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + kainfoBVOs[i].getY_ka_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getY_ka_pk()+"' ");
								}
							}
							else
							{
								KadanganYZVO zcvo = new KadanganYZVO();	// ת��
								zcvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getY_ka_pk() );	// ��pk
								zcvo.setYz_time( kainfoBVOs[i].getYwsj() );	//��תʱ��
								zcvo.setZc_je( kainfoBVOs[i].getKa_je() );	//ת�����
								zcvo.setZc_ss( kainfoBVOs[i].getY_ka_ss() );	//ת��ʵ��
								zcvo.setKayue( kainfoBVOs[i].getY_ka_yue() );	//�����
								zcvo.setZdh( kainfoBVOs[i].getZdh() );		//�˵���
								// �Է�
								zcvo.setDf_ka_code( kainfoBVOs[i].getKa_code() );
								zcvo.setDf_ka_name( kainfoBVOs[i].getKa_name() );
								zcvo.setDf_ka_pk( kainfoBVOs[i].getKa_pk() );
								zcvo.setDf_kaxing_code( kainfoBVOs[i].getKaxing_code() );
								zcvo.setDf_kaxing_name( kainfoBVOs[i].getKaxing_name() );
								zcvo.setDf_kaxing_pk( kainfoBVOs[i].getKaxing_pk() );
								
								zcvo.setCsourcetypecode("HK24");			// ���ε�������
								zcvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// ���ε���id
								zcvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
								zcvo.setPk_hk_huiyuan_kadangan_yz(pkGenerator.generate());			// ��ֵpk
								zcvo.setDr(0);
								zcvo.setVbdef01( kainfoHVO.getVdef01() );	// �ŵ�
								zcvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// ҵ������
								
								insertVOs_yz.add(zcvo);
								
								// ���� ��Ա�� ��ǰ���
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getY_ka_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + kainfoBVOs[i].getY_ka_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getY_ka_pk()+"' ");
								}
								
							}
							
							if( "0103001500".equals(kainfoBVOs[i].getY_ka_code()) )
							{// �Ƿ� �����ߴ� ת���ġ� ��Ҫ�����߿��� ����
								
								KadanganJHVO jhvo = new KadanganJHVO();	// ����
								jhvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getXmka_pk() );	// ��pk
								jhvo.setJh_time( kainfoBVOs[i].getYwsj() );			// ����ʱ��
								jhvo.setKa_code_jh( kainfoBVOs[i].getKa_code() );	// �����
								
								jhvo.setCsourcetypecode("HK24");			// ���ε�������
								jhvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// ���ε���id
								jhvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// ���ε�����id
								jhvo.setPk_hk_huiyuan_kadangan_jh(pkGenerator.generate());			// ��ֵpk
								jhvo.setDr(0);
								jhvo.setVbdef01( kainfoHVO.getVdef01() );	// �ŵ�
								jhvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// ҵ������
								
								insertVOs_jh.add(jhvo);
								
								baseDAO.executeUpdate(
										" update hk_huiyuan_kadangan " +
										" set " +
										" kastatus = '����' " +
										",vdef02 = '"+ kainfoBVOs[i].getPk_hk_huiyuan_kainfo() +"' " +
										",vdef03 = '"+ kainfoHVO.getDbilldate() +"' " +	// ��������
										" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getXmka_pk()+"' ");
								
								// ���� ��Ա�� ��ǰ���
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getY_ka_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + kainfoBVOs[i].getY_ka_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getXmka_pk()+"' ");
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
			baseDAO.insertVOList(insertVOs_xf);	// ����
			baseDAO.insertVOList(insertVOs_yz);	// ��ת
			baseDAO.insertVOList(insertVOs_cz); // ��ֵ
			baseDAO.insertVOList(insertVOs_zf); // ����
			baseDAO.insertVOList(insertVOs_jh);	// ����
			baseDAO.insertVOList(insertVOs_kkp);// �ɿ�Ʊ
			
		}
		
		return returnVos;
	}
	
	

}
