package nc.impl.hkjt;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pub.ace.AceHk_fp_sk_billPubServiceImpl;
import nc.itf.hkjt.IHk_fp_sk_billMaintain;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpHVO;
import nc.vo.pub.BusinessException;

public class Hk_fp_sk_billMaintainImpl extends AceHk_fp_sk_billPubServiceImpl
		implements IHk_fp_sk_billMaintain {

	@Override
	public void delete(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public BillSkFpBillVO[] insert(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2018��10��23��10:11:28
		 * Ҫȷ�� ��Ʊ���� Ψһ��
		 * clientFullVOs  ��������  ֻ�� ��������
		 */
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			BillSkFpHVO clientHVO = clientFullVOs[i].getParentVO();
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" fp.pk_hk_fapiao_sk_bill ")
						.append(" from hk_fapiao_sk_bill fp ")
						.append(" where fp.dr = 0 ")
						.append(" and fp.fphm = '"+clientHVO.getFphm()+"' ")
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
	public BillSkFpBillVO[] update(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2018��10��23��10:13:54
		 * Ҫȷ�� ��Ʊ����+��Ʊ����� Ψһ��
		 * clientFullVOs �ǽ��������
		 * originBills ���������
		 */
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			BillSkFpHVO clientHVO = clientFullVOs[i].getParentVO();
			BillSkFpHVO cacheHVO  = originBills[i].getParentVO();
			
			if( 
				clientHVO.getFphm().equals( cacheHVO.getFphm() )
			)
			{// �����Ʊ���롢����  �� ������ �����һ�£� �� ����������Ҫ����
				continue;
			}
			else
			{// ��� ���� �� ���� ��һ��  ����Ҫ ��ѯ���ݿ� �� �жϡ�
				StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" fp.pk_hk_fapiao_sk_bill ")
							.append(" from hk_fapiao_sk_bill fp ")
							.append(" where fp.dr = 0 ")
							.append(" and fp.fphm = '"+clientHVO.getFphm()+"' ")
							.append(" and fp.pk_hk_fapiao_sk_bill != '"+clientHVO.getPk_hk_fapiao_sk_bill()+"' ")
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
	public BillSkFpBillVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public BillSkFpBillVO[] save(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillSkFpBillVO[] unsave(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillSkFpBillVO[] approve(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BillSkFpBillVO[] unapprove(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
