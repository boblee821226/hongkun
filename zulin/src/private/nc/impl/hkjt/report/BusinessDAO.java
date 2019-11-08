package nc.impl.hkjt.report;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IPub_data;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.hkjt.pub.JsdXbsqLinkVO;
import nc.vo.pub.BusinessException;
import nc.vo.sf.allocateapply.AggAllocateApplyVO;
import nc.vo.sf.allocateapply.AllocateApplyBVO;

/**
 * ҵ��ʵ����
 */
public class BusinessDAO {
	/**
	 * ���� ���㵥 ���� �²�����
	 */
	public Object genXbsqByJsd(ArrayList<Object[]> list, Object other)
			throws BusinessException
	{
		IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		for (Object[] obj : list) {
			
			AggAllocateApplyVO billVO = (AggAllocateApplyVO)obj[0];
			HashMap<String, ArrayList<String[]>> link = (HashMap<String, ArrayList<String[]>>)obj[1];
			// �����²�����
			iplatFormEntry.processAction("SAVEBASE" , "36K1" , null , billVO , null , null);
			// ���ɹ�ϵ��
			_genLink(link);
			// ���ɽ��㵥״̬
			_genJsd(link);
			
		}
		
		return "ok";
	}
	/**
	 * ɾ�� �²�����ʱ����ԭ ���㵥
	 */
	public Object delXbsqBackJsd(AggAllocateApplyVO billVO, Object other)
			throws BusinessException {
		
		ArrayList<String> linkKey = new ArrayList<String>();
//		linkKey.add("8deff13d-219f-442c-afc7-02b073ddaefa");
		
		AllocateApplyBVO[] bVOs = (AllocateApplyBVO[])billVO.getChildrenVO();
		for (AllocateApplyBVO bvo : bVOs) {
			String link_pk = bvo.getVuserdef1();
			if (link_pk != null && !"~".equals(link_pk)) {
				linkKey.add(bvo.getVuserdef1());
			}
		}
		
		if (linkKey.size() == 0) return "none";
		
		// ɾ�����㵥״̬
		_delJsd(linkKey);
		// ɾ����ϵ��
		_delLink(linkKey);
		
		return "ok";
	}
	
	/**
	 * ���ɹ�ϵ��
	 */
	private Object _genLink(HashMap<String, ArrayList<String[]>> link) throws BusinessException {
		
		ArrayList<JsdXbsqLinkVO> list = new ArrayList<JsdXbsqLinkVO>();
				
		for (Entry<String, ArrayList<String[]>> entry : link.entrySet()) {
			String link_key = entry.getKey();
			ArrayList<String[]> link_value = entry.getValue();
			for (String[] pk : link_value) {
				String pk_jsd = pk[0];
				String pk_jsd_b = pk[1];
				JsdXbsqLinkVO linkVO = new JsdXbsqLinkVO(link_key, pk_jsd, pk_jsd_b);
				list.add(linkVO);
			}
		}
		
		new BaseDAO().insertVOList(list);
		
		return "ok";
	}
	
	/**
	 * ���� ���㵥 ״̬ = ������
	 */
	private Object _genJsd(HashMap<String, ArrayList<String[]>> link) throws BusinessException {
		
		ArrayList<String> list_pk_jsd = new ArrayList<String>();
		
		for (Entry<String, ArrayList<String[]>> entry : link.entrySet()) {
			ArrayList<String[]> link_value = entry.getValue();
			for (String[] pk : link_value) {
				String pk_jsd = pk[0];
				list_pk_jsd.add(pk_jsd);
			}
		}
		
		String pkJsdStr = PuPubVO.getSqlInByList(list_pk_jsd);
		
		StringBuffer updateSQL = 
		new StringBuffer("update cmp_settlement ")
				.append(" set ")
				.append(" def1 = '").append(IPub_data.JSXB_done).append("' ")
				.append(",ts = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ")
				.append(" where pk_settlement in ").append(pkJsdStr)
		;
		
		new BaseDAO().executeUpdate(updateSQL.toString());
		
		return "ok";
	}
	
	/**
	 * ɾ�� ���㵥 ״̬
	 * ���� link-key
	 */
	private Object _delJsd(ArrayList<String> link_key) throws BusinessException {
		
		String pkLinkStr = PuPubVO.getSqlInByList(link_key);
		
		StringBuffer updateSQL = 
			new StringBuffer("update cmp_settlement ")
					.append(" set ")
					.append(" def1 = null ")
					.append(",ts = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ")
					.append(" where pk_settlement in ")
					.append(" ( ")
					.append("    SELECT link.PK_JSD FROM HK_JSD_XBSQ_LINK link ")
					.append("    WHERE link.DR = 0 AND LINK_KEY IN ").append(pkLinkStr)
					.append("    GROUP BY link.PK_JSD ")
					.append(" ) ")
			;
		
		new BaseDAO().executeUpdate(updateSQL.toString());
		
		return "ok";
	}
	
	/**
	 * ɾ����ϵ��
	 * ���� link-key
	 */
	private Object _delLink(ArrayList<String> link_key) throws BusinessException {
		
		String linkKeyStr = PuPubVO.getSqlInByList(link_key);
		
		StringBuffer whereSQL = 
		new StringBuffer(" LINK_KEY in ")
				.append(linkKeyStr)
		;
		
		new BaseDAO().deleteByClause(JsdXbsqLinkVO.class, whereSQL.toString());
		
		return "ok";
	}
	
}
