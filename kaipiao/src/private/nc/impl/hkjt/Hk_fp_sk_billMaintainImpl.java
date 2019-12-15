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
		 * 2018年10月23日10:11:28
		 * 要确保 发票号码 唯一性
		 * clientFullVOs  新增数据  只有 界面数据
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
				throw new BusinessException("发票号 不能重复！");
			}
			
		}
		/**END*/
		
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public BillSkFpBillVO[] update(BillSkFpBillVO[] clientFullVOs,
			BillSkFpBillVO[] originBills) throws BusinessException {
		
		/**
		 * 2018年10月23日10:13:54
		 * 要确保 发票代码+发票号码的 唯一性
		 * clientFullVOs 是界面的数据
		 * originBills 缓存的数据
		 */
		for(int i=0;clientFullVOs!=null&&i<clientFullVOs.length;i++)
		{
			BillSkFpHVO clientHVO = clientFullVOs[i].getParentVO();
			BillSkFpHVO cacheHVO  = originBills[i].getParentVO();
			
			if( 
				clientHVO.getFphm().equals( cacheHVO.getFphm() )
			)
			{// 如果发票代码、号码  ， 界面与 缓存的一致， 则 跳过、不需要处理。
				continue;
			}
			else
			{// 如果 界面 与 缓存 不一致  则需要 查询数据库 来 判断。
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
					throw new BusinessException("发票号 不能重复！");
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
