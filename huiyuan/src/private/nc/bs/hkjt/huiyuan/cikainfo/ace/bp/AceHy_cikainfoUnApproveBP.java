package nc.bs.hkjt.huiyuan.cikainfo.ace.bp;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCKXFVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_cikainfoUnApproveBP {

	public CikainfoBillVO[] unApprove(CikainfoBillVO[] clientBills,
			CikainfoBillVO[] originBills)throws BusinessException {
		for (CikainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikainfoBillVO> update = new BillUpdate<CikainfoBillVO>();
		CikainfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// 将信息 从 会员卡档案中 删除。
		BaseDAO baseDAO = new BaseDAO();
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			CikainfoHVO cikainfoHVO = returnVos[vos_i].getParentVO();	// 主表VO
			
			String whereSQL = " csourcetypecode='HK29' and csourcebillid='"+cikainfoHVO.getPk_hk_huiyuan_cikainfo()+"' ";
			
			baseDAO.deleteByClause(KadanganCKXFVO.class, whereSQL);	//次卡消费
			baseDAO.deleteByClause(KadanganCKCZVO.class, whereSQL);	//次卡充值
			
		}
		
		return returnVos;
	}
}
