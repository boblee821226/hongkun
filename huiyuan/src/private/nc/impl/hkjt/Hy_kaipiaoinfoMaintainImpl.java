package nc.impl.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.hkjt.huiyuan.workplugin.HuiyuanPlugin;
import nc.impl.pub.ace.AceHy_kaipiaoinfoPubServiceImpl;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao.bill.BillFpHVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoHVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryHVO;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;
import nc.itf.hkjt.PUB_kaipiao;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.bill.BillVOStatus;

public class Hy_kaipiaoinfoMaintainImpl extends AceHy_kaipiaoinfoPubServiceImpl
		implements IHy_kaipiaoinfoMaintain {

	@Override
	public void delete(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] insert(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2016��9��12��20:10:03
		 * Ҫȷ�� ��Ʊ����+��Ʊ����� Ψһ��
		 * clientFullVOs  ��������  ֻ�� ��������
		 * ��� �Ƿ�Ϊ����Ŀ�
		 */
		HashMap<String,String> MAP_without = new HashMap<String,String>();	// ����Լ������
		MAP_without.put("1001N510000000002VFH", "1001N510000000002VFH");	// Ҧ����
		MAP_without.put("1001N51000000002G8PK", "1001N51000000002G8PK");	// shengji
		String userId = InvocationInfoProxy.getInstance().getUserId();		// ��¼�û�id
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			KaipiaoinfoHVO clientHVO = clientFullVOs[i].getParentVO();
			
			// 1��ֻ�ܿ�  ����Ŀ�
			if(!MAP_without.containsKey(userId))
			{// ����Ա  �������� ���ơ�
				
				String dian = HuiyuanPlugin.MAP_corp_dian.get(clientHVO.getPk_org());	// ����pk_org �õ���������ƣ�
				KaipiaoinfoBVO[] bVOs = (KaipiaoinfoBVO[])clientFullVOs[i].getChildrenVO();
				for(int ii=0;ii<bVOs.length;ii++)
				{
					if( dian!=null && !dian.equals( bVOs[ii].getVbdef02() ) )
					{
						throw new BusinessException("���Ǳ��귢�Ŀ������ܿ�Ʊ��");
					}
				}
			}
			
			// 2����Ʊ�Ų����ظ�
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" fp.pk_hk_huiyuan_kaipiaoinfo ")
						.append(" from hk_huiyuan_kaipiaoinfo fp ")
						.append(" where fp.dr = 0 ")
						.append(" and fp.fph  = '"+clientHVO.getAttributeValue("fph") +"' ")
//						.append(" and fp.pk_hk_fapiao_bill != '"+clientHVO.getPk_hk_fapiao_bill()+"' ")
			;
			ArrayList list = (ArrayList)new BaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
			if(list!=null && list.size()>0)
			{
				throw new BusinessException("��Ʊ�� �����ظ���");
			}
			
		}
		/**END*/
		
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] update(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2016��9��12��20:11:50
		 * Ҫȷ�� ��Ʊ����+��Ʊ����� Ψһ��
		 * clientFullVOs �ǽ��������
		 * originBills ���������
		 */
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			KaipiaoinfoHVO clientHVO = clientFullVOs[i].getParentVO();
			KaipiaoinfoHVO cacheHVO  = originBills[i].getParentVO();
			
			if( 
			   clientHVO.getAttributeValue("fph").equals( cacheHVO.getAttributeValue("fph") )
			)
			{// �����Ʊ���롢����  �� ������ �����һ�£� �� ����������Ҫ����
				continue;
			}
			else
			{// ��� ���� �� ���� ��һ��  ����Ҫ ��ѯ���ݿ� �� �жϡ�
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" fp.pk_hk_huiyuan_kaipiaoinfo ")
							.append(" from hk_huiyuan_kaipiaoinfo fp ")
							.append(" where fp.dr = 0 ")
							.append(" and fp.fph  = '"+clientHVO.getAttributeValue("fph") +"' ")
							.append(" and fp.pk_hk_huiyuan_kaipiaoinfo != '"+clientHVO.getPk_hk_huiyuan_kaipiaoinfo()+"' ")
				;
				ArrayList list = (ArrayList)new BaseDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
				if(list!=null && list.size()>0)
				{
					throw new BusinessException("��Ʊ�� �����ظ���");
				}
			}
		}
		/**END*/
		
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public KaipiaoinfoBillVO[] save(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] unsave(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] approve(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public KaipiaoinfoBillVO[] unapprove(KaipiaoinfoBillVO[] clientFullVOs,
			KaipiaoinfoBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public Object updateSQL(String sql, Object other) throws BusinessException {
		
		Integer result = new BaseDAO().executeUpdate(sql);
		
		return result;
	}

	@Override
	public Object checkErrorFP(ArrayList<String> error_key, Object other)
			throws BusinessException {
		
		BillQuery<KaipiaoinfoBillVO> billQuery = new BillQuery<KaipiaoinfoBillVO>(KaipiaoinfoBillVO.class);
		
		String[] keys = new String[error_key.size()];
		keys = error_key.toArray(keys);
		
		// ��ѯ�� ��Ҫ����� ��ƱVO
		KaipiaoinfoBillVO[] kpBillVOs = billQuery.query(keys);
		
//		String ka_code_str = "''";
//		for( int i=0;i<kpBillVOs.length;i++ )
//		{// ��� ����ƱVO ���д��� ���kaCode
//			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])kpBillVOs[i].getChildrenVO();
//			for(int j=0;j<kpBVOs.length;j++)
//			{
//				KaipiaoinfoBVO kpBVO = kpBVOs[j];
//				
//				ka_code_str = ka_code_str + ",'" + kpBVO.getKa_code() + "'";
//				
//			}
//		}
//		KaipiaoqueryBillVO[] billVO_source = PUB_kaipiao.bbcx_data(ka_code_str, null, true,"checkError");
//		HashMap<String,KaipiaoqueryHVO> MAP_srouce = new HashMap<String,KaipiaoqueryHVO>();	// key-����
//		for(int i=0;billVO_source!=null&&i<billVO_source.length;i++)
//		{
//			KaipiaoqueryHVO hvo = billVO_source[i].getParentVO();
//			MAP_srouce.put(hvo.getKa_code(),hvo);
//		}
		
		HashMap<String,KaipiaoqueryHVO> MAP_srouce = new HashMap<String,KaipiaoqueryHVO>();	// key-����
		for( int i=0;i<kpBillVOs.length;i++ )
		{// ��� ����ƱVO ���д��� ���kaCode
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])kpBillVOs[i].getChildrenVO();
			for(int j=0;j<kpBVOs.length;j++)
			{
				KaipiaoinfoBVO kpBVO = kpBVOs[j];
				String ka_code = kpBVO.getKa_code() ;
				
				if( !MAP_srouce.containsKey(ka_code) )
				{
					String ka_code_str = "'" + ka_code + "'";
					KaipiaoqueryBillVO[] billVO_source = PUB_kaipiao.bbcx_data(ka_code_str, null, true,"checkError");
					
					if(billVO_source!=null && billVO_source.length>0)
					{
						MAP_srouce.put(ka_code, billVO_source[0].getParentVO());
					}
				}
				
			}
		}
		
		String errorMsg = "";
		ArrayList<KaipiaoinfoBillVO> update_fp_list = new ArrayList<KaipiaoinfoBillVO>();	// �޸ĵ�vo
		ArrayList<KaipiaoinfoBillVO>  cache_fp_list = new ArrayList<KaipiaoinfoBillVO>();	// ԭʼ��vo
		
		//�� ����Ʊ ���� ѭ�����
		for( int i=0;i<kpBillVOs.length;i++ )
		{// ��� ����ƱVO ���м��
			
			KaipiaoinfoBillVO kp_clone = (KaipiaoinfoBillVO)kpBillVOs[i].clone();
			boolean isError = false;
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])kp_clone.getChildrenVO();
			for(int j=0;j<kpBVOs.length;j++)
			{
				
				KaipiaoinfoBVO kpBVO = kpBVOs[j];
				
				String kaCode = kpBVO.getKa_code();
				
				if(kaCode==null && kaCode.trim().length()<=0)
				{
					throw new BusinessException(""+kp_clone.getParentVO().getFph()+" �����⣬���顣");
				}
				
				KaipiaoqueryHVO kkp_temp = MAP_srouce.get(kaCode);
				
				if(kkp_temp==null)
				{
					throw new BusinessException(""+kp_clone.getParentVO().getFph()+" �����⣬���顣");
				}
				
//				if(kkp_temp==null)
//				{
//					System.out.println("======"+kkp_temp);
//				}
				
				if( 	PuPubVO.getUFDouble_NullAsZero(kpBVO.getFpje())
					.compareTo( PuPubVO.getUFDouble_NullAsZero(kkp_temp.getKkpze())
						  .sub( PuPubVO.getUFDouble_NullAsZero(kkp_temp.getYkpze()) ) )<=0 )
				{// ����Ƿ�����Ʊ
				 // ��� ��Ʊ��� <= ��Ʊ�ܶ�-�ѿ�Ʊ�ܶ�  ������Ʊ
					
					kpBVO.setKkpze(kkp_temp.getKkpze());
					kpBVO.setZqkpje(kkp_temp.getYkpze());
					kpBVO.setStatus(BillVOStatus.UPDATED);
					
					// ����(���ο�Ʊ���) ���������  �ѿ�Ʊ�ܶ�
					kkp_temp.setYkpze( PuPubVO.getUFDouble_NullAsZero(kpBVO.getFpje())
							     .add( PuPubVO.getUFDouble_NullAsZero(kkp_temp.getYkpze()) ) );
					
				}
				else
				{// ��������㣬 �� ������  ����������ͨ����
					errorMsg = errorMsg + kp_clone.getParentVO().getFpdm()+""+kp_clone.getParentVO().getFph()+"-"+ kaCode + "\r\n";
					isError = true;
					break;
				}
			}
			
			if(!isError)
			{// ������� ��û�д����� ���Խ��и���
				kp_clone.getParentVO().setVdef02("����");
				kp_clone.getParentVO().setStatus(BillVOStatus.UPDATED);
				update_fp_list.add(kp_clone);		// �޸�
				cache_fp_list.add(kpBillVOs[i]);	// ����
			}
		}
		
		//��������
		Integer update_num = 0;	// ���µĵ�����
		if( update_fp_list!=null && update_fp_list.size()>0 )
		{
			KaipiaoinfoBillVO[] updateVOs = new KaipiaoinfoBillVO[update_fp_list.size()];
			updateVOs = update_fp_list.toArray(updateVOs);
			KaipiaoinfoBillVO[] cacheVOs = new KaipiaoinfoBillVO[cache_fp_list.size()];
			cacheVOs = cache_fp_list.toArray(cacheVOs);
			this.update(updateVOs, cacheVOs);
			update_num = updateVOs.length;
		}
		
		return new Object[]{update_num,errorMsg};
	}

}
