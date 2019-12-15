package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganJHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganXFVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganYZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganZFVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kainfoUnApproveBP {

	public KainfoBillVO[] unApprove(KainfoBillVO[] clientBills,
			KainfoBillVO[] originBills)throws BusinessException {
		for (KainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KainfoBillVO> update = new BillUpdate<KainfoBillVO>();
		KainfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// 将信息 从 会员卡档案中 删除。
		BaseDAO baseDAO = new BaseDAO();
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			KainfoHVO kainfoHVO = returnVos[vos_i].getParentVO();		// 主表VO
			KainfoBVO[] kainfoBVO = (KainfoBVO[])returnVos[vos_i].getChildrenVO();	// 子表VO
			
			String whereSQL = " csourcetypecode='HK24' and csourcebillid='"+kainfoHVO.getPk_hk_huiyuan_kainfo()+"' ";
			
			baseDAO.deleteByClause(KadanganXFVO.class, whereSQL);//消费
			baseDAO.deleteByClause(KadanganYZVO.class, whereSQL);//余转
			baseDAO.deleteByClause(KadanganCZVO.class, whereSQL);//充值
			baseDAO.deleteByClause(KadanganZFVO.class, whereSQL);//作废
			baseDAO.deleteByClause(KadanganJHVO.class, whereSQL);//激活
			baseDAO.deleteByClause(KadanganKKPVO.class, whereSQL);//可开票（2016年9月18日16:02:45）
			
			baseDAO.executeUpdate(	// 将作废  还原为  正常
					" update hk_huiyuan_kadangan " +
					" set " +
					" kastatus = '正常' " +
					",vdef02 = '~' " +
					",vdef03 = '~' " +
					" where vdef02 = '"+ kainfoHVO.getPk_hk_huiyuan_kainfo() +"' " +
					" and kastatus = '作废' "
					);
			
			baseDAO.executeUpdate(	// 将激活  还原为  休眠
					" update hk_huiyuan_kadangan " +
					" set " +
					" kastatus = '休眠' " +
					",vdef02 = '~' " +
					",vdef03 = '~' " +
					" where vdef02 = '"+ kainfoHVO.getPk_hk_huiyuan_kainfo() +"' " +
					" and kastatus = '激活' "
					);
			
			for(int b_i=0;b_i<kainfoBVO.length;b_i++)
			{
				if(   "充值".equals( kainfoBVO[b_i].getXmdl() ) 
				 && !"作废卡".equals( kainfoBVO[b_i].getXmlx() )
				 && !"回充" .equals( kainfoBVO[b_i].getXmlx() )
				 && PuPubVO.getUFDouble_ZeroAsNull( kainfoBVO[b_i].getKa_ss() )!=null 
				  )
				{
//					baseDAO.executeUpdate(		// 取消回写表头的 发票字段（2016年9月18日16:01:52）
//							" update hk_huiyuan_kadangan " +
//							" set " +
//							" kkpze = nvl(kkpze,0) - " + kainfoBVO[b_i].getKa_ss() +
//							" where pk_hk_huiyuan_kadangan = '"+kainfoBVO[b_i].getKa_pk()+"' ");
				}
				
				if( ( "充值".equals( kainfoBVO[b_i].getXmdl() ) && !"作废卡".equals( kainfoBVO[b_i].getXmlx() )  )
				 ||	"消费".equals( kainfoBVO[b_i].getXmdl() )	
				 )
				{
					// 更新 会员卡 当前余额
					if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getKa_je())!=null )
					{
						baseDAO.executeUpdate(
								" update hk_huiyuan_kadangan " +
								" set " +
								" dq_ye = nvl(dq_ye,0) - " + kainfoBVO[b_i].getKa_je() +
								" where pk_hk_huiyuan_kadangan = '"+kainfoBVO[b_i].getKa_pk()+"' ");
					}
				}
				else if("余转".equals( kainfoBVO[b_i].getXmdl() ))
				{
					if( kainfoBVO[b_i].getKa_pk()!=null ){
						// 更新 会员卡 当前余额
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getKa_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) - " + kainfoBVO[b_i].getKa_je() +
									" where pk_hk_huiyuan_kadangan = '"+ kainfoBVO[b_i].getKa_pk() +"' ");
						}
					}
					
					if( kainfoBVO[b_i].getY_ka_pk()!=null ){
						// 更新 会员卡 当前余额
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getY_ka_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) + " + kainfoBVO[b_i].getY_ka_je() +
									" where pk_hk_huiyuan_kadangan = '"+ kainfoBVO[b_i].getY_ka_pk() +"' ");
						}
					}
					
					if( "0103001500".equals(kainfoBVO[b_i].getY_ka_code()) )
					{// 是否 由休眠大卡 转出的。 需要做休眠卡的 处理
						
						// 更新 会员卡 当前余额
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVO[b_i].getY_ka_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) + " + kainfoBVO[b_i].getY_ka_je() +
									" where pk_hk_huiyuan_kadangan = '"+ kainfoBVO[b_i].getXmka_pk() +"' ");
						}
						
					}
				}
			}
			
		}
		
		return returnVos;
	}
}
