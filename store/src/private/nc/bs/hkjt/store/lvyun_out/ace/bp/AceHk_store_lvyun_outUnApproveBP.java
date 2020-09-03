package nc.bs.hkjt.store.lvyun_out.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceHk_store_lvyun_outUnApproveBP {

	public LyOutStoreBillVO[] unApprove(LyOutStoreBillVO[] clientBills,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		// ѭ����װ���� ��ͷid
		ArrayList<String> head_id_list = new ArrayList<String>();// ��ͷid list
		for (LyOutStoreBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			// ��ӱ�ͷid
			head_id_list.add(clientBill.getParentVO().getPk_hk_store_lvyun_out());
		}
		
		/**
		 * 2020��7��30��11:13:57
		 * ȡ�����ʱ��ɾ�����ε���
		 */
		{
			StringBuffer querySQL = 
			new StringBuffer(" select distinct h.vbillcode ")
				.append(" from ic_material_b b ")
				.append(" inner join ic_material_h h on b.cgeneralhid = h.cgeneralhid ")
				.append(" where b.dr = 0 and h.dr = 0 ")
				.append(" and b.csourcetype = '0001ZZ100000001P7OHF' ")
				.append(" and b.csourcebillhid in ").append(PuPubVO.getSqlInByList(head_id_list)).append(" ")
			;
			ArrayList list = (ArrayList)this.getDAO().executeQuery(
					querySQL.toString()
					, new ArrayListProcessor()
					);
			if (list != null && !list.isEmpty()) {
				String codes = "";
				for (Object item : list) {
					Object[] obj = (Object[])item;
					codes += "��" + obj[0];
				}
				throw new BusinessException("�������εĿ����ϳ��ⵥ" + codes);
			}
		}
		/***END***/
		
		// ��׼��Ʒ�ĸ���״̬
		BillUpdate<LyOutStoreBillVO> update = new BillUpdate<LyOutStoreBillVO>();
		LyOutStoreBillVO[] returnVos = update.update(clientBills, originBills);
		
		return returnVos;
	}
	
	/**
	 * DAO
	 */
	private BaseDAO DAO = null;
	private BaseDAO getDAO() {
		if (DAO == null) {
			DAO = new BaseDAO();
		}
		return DAO;
	}
}
