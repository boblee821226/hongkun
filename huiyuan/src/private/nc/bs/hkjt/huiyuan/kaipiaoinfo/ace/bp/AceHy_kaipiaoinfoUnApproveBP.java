package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKPVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kaipiaoinfoUnApproveBP {

	public KaipiaoinfoBillVO[] unApprove(KaipiaoinfoBillVO[] clientBills,
			KaipiaoinfoBillVO[] originBills)throws BusinessException {
		for (KaipiaoinfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KaipiaoinfoBillVO> update = new BillUpdate<KaipiaoinfoBillVO>();
		KaipiaoinfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// 将信息 从 会员卡档案中 删除。
		BaseDAO baseDAO = new BaseDAO();
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganKKPVO> updateVOs_kkp = new ArrayList<KadanganKKPVO>();	// 要更新的VO  可开票
			ArrayList<KadanganKKPVO> deleteVOs_kkp = new ArrayList<KadanganKKPVO>();	// 要删除的VO  可开票
			
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])returnVos[vos_i].getChildrenVO();	// 子表VO
			KaipiaoinfoHVO kpHVO = returnVos[vos_i].getParentVO();			// 主表VO
			
			String whereSQL = " csourcetypecode='HK33' and csourcebillid='"+kpHVO.getPk_hk_huiyuan_kaipiaoinfo()+"' ";
			
			baseDAO.deleteByClause(KadanganKPVO.class, whereSQL);//开票
			
			for( int i=0;i<kpBVOs.length;i++ )
			{// 循环表体
				
				String kaxing_name = kpBVOs[i].getKaxing_name();	// 判断是不是  删除卡 
				
//				if( null==kpHVO.getVdef01()
//				||	"~".equals(kpHVO.getVdef01())
//				||	"开票".equals(kpHVO.getVdef01()) 
//				||	"续卡".equals(kpHVO.getVdef01()) 
//				)
				{
					if( !"删除卡".equals( kaxing_name ) )
					{
						// 还原 已开票总额
//						baseDAO.executeUpdate(
//								" update hk_huiyuan_kadangan " +
//								" set " +
//								" ykpje = nvl(ykpje,0) - " + kpBVOs[i].getFpje() +
//								" where pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' ");
						
						ArrayList<KadanganKKPVO> kkp_list = 
								(ArrayList<KadanganKKPVO>)baseDAO.retrieveByClause(
								KadanganKKPVO.class,
								" 1=1 " +
								" and dr = 0 " +
								" and pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' " +
								" and nvl(ykp_je,0) <> 0.00 ",
//								" and kpjz_time <= '" + (new UFDateTime().toString()) + "'",	// 取截至日期以前的
								" kpjz_time desc "
						);
						
						UFDouble fpje = PuPubVO.getUFDouble_NullAsZero( kpBVOs[i].getFpje() );
						
						if( fpje.compareTo(UFDouble.ZERO_DBL)>=0 )
						{//  如果 发票金额 为正数
							for( KadanganKKPVO kkpVO:kkp_list )
							{
								UFDouble ykp_je = PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() );
	
								if( fpje.compareTo(ykp_je)>=0 )
								{// 如果 发票金额 大于等于 已开票金额， 则  本行 实际退票金额 为  已开票金额。 发票金额 进行抵扣。
									
									kkpVO.setYkp_je( // 已开票金额 = 之前已开票金额 - 本次退票金额
											  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
										.sub( ykp_je )
									);
									fpje = fpje.sub(ykp_je);
								}
								else
								{// 否则， 则  本行 实际退票金额 为  发票金额。 发票金额 抵扣为0。
									
									kkpVO.setYkp_je( // 已开票金额 = 之前已开票金额 - 本次退票金额
											  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
										.sub( fpje )
									);
									fpje = UFDouble.ZERO_DBL;
								}
								
								updateVOs_kkp.add(kkpVO);	// 添加要更新的vo
								
								if(fpje.compareTo(UFDouble.ZERO_DBL)==0)
								{// 如果发票金额  抵扣为0了， 则不再抵扣 退出循环
									break;
								}
							}
						}
						else
						{// 如果 发票金额 为负数
							
							// 先找 有没有原先 负数生成出来的可开票
							for( KadanganKKPVO kkpVO:kkp_list )
							{
								if( kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b().equals( kkpVO.getCsourcebillbid() ) )	// 上游单据行id
								{
									UFDouble kkp_je = // 可开票余额  = 可开票金额 - 已开票金额
											  PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
										.sub( PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
									);
									
									if( kkp_je.sub(kpBVOs[i].getFpje()).compareTo(UFDouble.ZERO_DBL)<0 )
									{
										throw new BusinessException("【"+kpBVOs[i].getKa_code()+"】  可退票余额不足，无法弃审。");
									}
									else if(  kkp_je.sub(kpBVOs[i].getFpje()).compareTo(UFDouble.ZERO_DBL)==0  )
									{
										deleteVOs_kkp.add(kkpVO);	// 添加要删除的vo
										
										fpje = UFDouble.ZERO_DBL;
									}
									else
									{
										kkpVO.setYkp_je( 
											      PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() )
											.sub( PuPubVO.getUFDouble_NullAsZero( kpBVOs[i].getFpje()) )
										);
										
										updateVOs_kkp.add(kkpVO);	// 添加要更新的vo
										
										fpje = UFDouble.ZERO_DBL;
									}
									
									break;
								}
							}
							
							if( fpje.compareTo(UFDouble.ZERO_DBL)!=0 )
							{
								for(int kkp_i=0;kkp_i<kkp_list.size();kkp_i++)
								{
									KadanganKKPVO kkpVO = kkp_list.get(kkp_i);
									
									UFDouble kkp_je = // 可开票余额  = 可开票金额 - 已开票金额
											  PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
										.sub( PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
									);
										
									if( kkp_je.compareTo(UFDouble.ZERO_DBL)!=0 
									 || kkp_i == kkp_list.size()-1
									  )
									{
										
										if( kkp_je.sub(kpBVOs[i].getFpje()).compareTo(UFDouble.ZERO_DBL)<0 )
										{
											throw new BusinessException("【"+kpBVOs[i].getKa_code()+"】  可退票余额不足，无法弃审。");
										}
										else
										{
											kkpVO.setYkp_je( // 已开票 = 已开票 + 当前开票
													  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
												.sub( PuPubVO.getUFDouble_NullAsZero(kpBVOs[i].getFpje()) )
											);
											
											updateVOs_kkp.add(kkpVO);	// 添加要更新的vo
											
											fpje = UFDouble.ZERO_DBL;
										}
									}
								}
							}
						}
						
						// 如果 循环完了  发票金额不等于0，则说明  可开票金额不够，则抛出错误
						if(fpje.compareTo(UFDouble.ZERO_DBL)!=0)
						{
							throw new BusinessException("【"+kpBVOs[i].getKa_code()+"】  可退票余额不足，无法弃审。");
						}
						
					}
					else
					{
						{// 更新  已开票总额 old
							baseDAO.executeUpdate(
									" update hk_huiyuan_kaipiao_old " +
									" set " +
									" ykpze = nvl(ykpze,0) - " + kpBVOs[i].getFpje() +
									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
						}
					}
				}
//				else if( "转卡".equals(kpHVO.getVdef01()) )
//				{
//					if( !"删除卡".equals( kaxing_name ) )
//					{
//						{// 更新  转卡金额
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) - " + kpBVOs[i].getFpje() +
//									" where pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' ");
//						}
//					}
//					else
//					{
//						{// 更新  已开票总额 old
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kaipiao_old " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) - " + kpBVOs[i].getFpje() +
//									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
//						}
//					}
//				}
			}
			
			baseDAO.updateVOList(updateVOs_kkp);	// 更新 可开票数据
		}
		
		return returnVos;
	}
}
