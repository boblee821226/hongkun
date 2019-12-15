package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_kainfoSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public KainfoBillVO[] sendApprove(KainfoBillVO[] clientBills,
			KainfoBillVO[] originBills)throws BusinessException {
		for (KainfoBillVO clientFullVO : clientBills) {
			
			/**
			 *  �ύǰ У��
			 */
			KainfoBVO[] kainfoBVOs = (KainfoBVO[])clientFullVO.getChildrenVO();
			HashMap<String,String> xmka_MAP = new HashMap<String,String>();	// ������¼���ŵ��ݵ�Ҫ��������߿��ţ������ظ�
			for( int b_index=0;b_index<kainfoBVOs.length;b_index++ )
			{
				KainfoBVO bvo = kainfoBVOs[b_index];
				int rowno = bvo.getVrowno();
				// ��
				String ka_code = bvo.getKa_code();		
				String ka_pk = bvo.getKa_pk();
				// Դ��
				String y_ka_code = bvo.getY_ka_code();	
				String y_ka_pk = bvo.getY_ka_pk();
				// ���߿�
				String xmka_code = PuPubVO.getString_TrimZeroLenAsNull(bvo.getXmka_code());	
				String xmka_pk = bvo.getXmka_pk();
				
				if( PuPubVO.getString_TrimZeroLenAsNull(ka_pk)==null )
				{// ��pk���ܵ��ڿ�
					throw new BusinessException("��"+rowno+"�У���Ա����������NCϵͳ�У����飡");
				}
				if( PuPubVO.getString_TrimZeroLenAsNull(y_ka_code)!=null 
				 && PuPubVO.getString_TrimZeroLenAsNull(y_ka_pk)==null 
				  )
				{// ��� Դ���Ų�Ϊ��  ��  Դ��pkҲ����Ϊ��
					throw new BusinessException("��"+rowno+"�У�Դ����������NCϵͳ�У����飡");
				}
				if( PuPubVO.getString_TrimZeroLenAsNull(xmka_code)!=null 
				 && PuPubVO.getString_TrimZeroLenAsNull(xmka_pk)==null )
				{// ��� ���߿��Ų�Ϊ��  ��  ���߿�pkҲ����Ϊ��
					throw new BusinessException("��"+rowno+"�У����߿���������NCϵͳ�У����飡");
				}
				
				if( "0103001500".equals(y_ka_code)
				 && xmka_code==null
				)
				{// �����  ���ߴ�ת���ģ����߿��� ����
					throw new BusinessException("��"+rowno+"�У����߿��ű�����д�����飡");
				}
				
				if( xmka_code != null )
				{// ������߿� ��Ϊ�գ���Ҫ�ж� �ڱ��ŵ��ݵ�Ψһ��
					if( xmka_MAP.containsKey(xmka_code) )
					{
						throw new BusinessException("��"+rowno+"�У����߿����ظ���"+xmka_code+"�������飡");
					}
					else
					{
						xmka_MAP.put(xmka_code, xmka_pk);
					}
				}
				
			}
			/**END*/
			
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		KainfoBillVO[] returnVos = new BillUpdate<KainfoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
