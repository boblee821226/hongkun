package nc.bs.hkjt.huiyuan.cikainfo.ace.bp;

import java.util.ArrayList;
import java.util.Collection;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKXFVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;

/**
 * ��׼������˵�BP
 */
public class AceHy_cikainfoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public CikainfoBillVO[] approve(CikainfoBillVO[] clientBills,
			CikainfoBillVO[] originBills)throws BusinessException {
		for (CikainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikainfoBillVO> update = new BillUpdate<CikainfoBillVO>();
		CikainfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// ����Ϣͬ���� ��Ա�����������д���
		BaseDAO baseDAO = new BaseDAO();
		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk ������
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganCKCZVO> insertVOs_ckcz = new ArrayList<KadanganCKCZVO>();		// ��ֵ
			ArrayList<KadanganCKXFVO> insertVOs_ckxf = new ArrayList<KadanganCKXFVO>();		// ����
			CikainfoBVO[] cikainfoBVOs = (CikainfoBVO[])returnVos[vos_i].getChildrenVO();	// �ӱ�VO
			CikainfoHVO cikainfoHVO = returnVos[vos_i].getParentVO();			// ����VO
			
			// 1��У�飬�Ƿ������������ύʱ �жϣ�
			
			// 2�����д���
			for( int i=0;i<cikainfoBVOs.length;i++ )
			{
				String xmdl = cikainfoBVOs[i].getXmdl();	// ��Ŀ����
				
				if( cikainfoBVOs[i].getKa_pk()!=null )
				{// ���� �����ڿյ�  �Ž��д���
				
					if("��ֵ".equals(xmdl))
					{
						KadanganCKCZVO ckczvo = new KadanganCKCZVO();
						ckczvo.setPk_hk_huiyuan_kadangan( cikainfoBVOs[i].getKa_pk() );	// ��pk
						ckczvo.setCz_time( cikainfoBVOs[i].getYwsj() );		// ��ֵʱ��
						ckczvo.setZdh( cikainfoBVOs[i].getZdh() );			// �˵���
						ckczvo.setKabili( cikainfoBVOs[i].getKabili() );	// ������
						ckczvo.setTotalnum( cikainfoBVOs[i].getShuliang());	// ������
						ckczvo.setItemid( cikainfoBVOs[i].getItemid() );
						ckczvo.setItemname( cikainfoBVOs[i].getItemname() );
						ckczvo.setUsednum( UFDouble.ZERO_DBL );
						ckczvo.setStartdata( cikainfoBVOs[i].getStartdata() );
						ckczvo.setExpdata( cikainfoBVOs[i].getExpdata() );
						ckczvo.setPrice( cikainfoBVOs[i].getDanjia() );		// ����
						ckczvo.setTimescardwaternum( cikainfoBVOs[i].getTimescardwaternum() );		// �ο�ˮ��
						
						ckczvo.setCsourcetypecode("HK29");			// ���ε�������
						ckczvo.setCsourcebillid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo());		// ���ε���id
						ckczvo.setCsourcebillbid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo_b());	// ���ε�����id
						ckczvo.setPk_hk_huiyuan_kadangan_ckcz(pkGenerator.generate());			// ��ֵpk
						ckczvo.setDr(0);
						ckczvo.setVbdef01( cikainfoHVO.getVdef01() );	// �ŵ�
						ckczvo.setVbdef02( cikainfoBVOs[i].getXmlx() );	// ��Ŀ����
						ckczvo.setVbdef03( cikainfoBVOs[i].getVbdef03() );	// ������
						
						/**
						 * HK 2019��1��16��17:04:41
						 * �´ο� �ο�ˮ�� ����ͬ��
						 */
						// �ж�  ��� �����Ŵο�ˮ�ţ� �Ͳ��ٲ���
//						Collection query_result = baseDAO.retrieveByClause(KadanganCKCZVO.class, " nvl(dr,0)=0 and timescardwaternum ='"+ckczvo.getTimescardwaternum()+"' ");
//						if( query_result.size()==0)
//						{
//							insertVOs_ckcz.add(ckczvo);
//						}
						
						insertVOs_ckcz.add(ckczvo);
						
					}
					
					else if("����".equals(xmdl)
						|| "�为��".equals(xmdl)
						)
					{
						KadanganCKXFVO ckxfvo = new KadanganCKXFVO();
						ckxfvo.setPk_hk_huiyuan_kadangan( cikainfoBVOs[i].getKa_pk() );	// ��pk
						ckxfvo.setXf_time( cikainfoBVOs[i].getYwsj() );		// ����ʱ��
						ckxfvo.setZdh( cikainfoBVOs[i].getZdh() );			// �˵���
						ckxfvo.setKabili( cikainfoBVOs[i].getKabili() );	// ������
						ckxfvo.setUsednum( cikainfoBVOs[i].getShuliang());	// ����
						ckxfvo.setPrice( cikainfoBVOs[i].getDanjia() );		// ����
						ckxfvo.setItemid( cikainfoBVOs[i].getItemid() );
						ckxfvo.setItemname( cikainfoBVOs[i].getItemname() );
						
						ckxfvo.setCsourcetypecode("HK29");			// ���ε�������
						ckxfvo.setCsourcebillid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo());		// ���ε���id
						ckxfvo.setCsourcebillbid(cikainfoBVOs[i].getPk_hk_huiyuan_cikainfo_b());	// ���ε�����id
						ckxfvo.setPk_hk_huiyuan_kadangan_ckxf(pkGenerator.generate());			// ��ֵpk
						ckxfvo.setDr(0);
						ckxfvo.setVbdef01( cikainfoHVO.getVdef01() );	// �ŵ�
						ckxfvo.setVbdef02( cikainfoBVOs[i].getXmlx() );	// ��Ŀ����
						ckxfvo.setVbdef03( cikainfoBVOs[i].getVbdef03() );	// ������
						
						insertVOs_ckxf.add(ckxfvo);
						
					}
				}
			}
			baseDAO.insertVOList(insertVOs_ckcz);	// ��ֵ
			baseDAO.insertVOList(insertVOs_ckxf);	// ����
		}
		
		
		return returnVos;
	}

}
