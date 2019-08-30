package nc.bs.hkjt.huiyuan.cikainfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_cikainfoSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public CikainfoBillVO[] sendApprove(CikainfoBillVO[] clientBills,
			CikainfoBillVO[] originBills)throws BusinessException {
		for (CikainfoBillVO clientFullVO : clientBills) {
			
			/**
			 *  �ύǰ У��
			 */
			CikainfoBVO[] ckinfoBVOs = (CikainfoBVO[])clientFullVO.getChildrenVO();
			for( int b_index=0;b_index<ckinfoBVOs.length;b_index++ )
			{
				CikainfoBVO bvo = ckinfoBVOs[b_index];
//				int rowno = bvo.getVrowno();
				String ka_pk = bvo.getKa_pk();
				UFDouble kabili = bvo.getKabili();
				UFDouble dj = bvo.getDanjia();
				UFDouble je = bvo.getJine();
				
				if( PuPubVO.getString_TrimZeroLenAsNull(ka_pk)==null )
				{// ��pk���ܵ��ڿ�
					throw new BusinessException("��"+(b_index+1)+"�У���Ա����������NCϵͳ�У����飡");
				}
				
				if( kabili==null )
				{// ������ ����Ϊ��
					throw new BusinessException("��"+(b_index+1)+"�У�����������Ϊ�գ����飡");
				}
				
				if( dj==null )
				{// ���� ����Ϊ��
					throw new BusinessException("��"+(b_index+1)+"�У����۲���Ϊ�գ����飡");
				}
				
//				if( je==null )
//				{// ��� ����Ϊ��
//					throw new BusinessException("��"+rowno+"�У�����Ϊ�գ����飡");
//				}
				
			}
			
			
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		CikainfoBillVO[] returnVos = new BillUpdate<CikainfoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
