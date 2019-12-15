package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.generator.SequenceGenerator;
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
 * 标准单据审核的BP
 */
public class AceHy_kaipiaoinfoApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KaipiaoinfoBillVO[] approve(KaipiaoinfoBillVO[] clientBills,
			KaipiaoinfoBillVO[] originBills)throws BusinessException {
		for (KaipiaoinfoBillVO clientBill : clientBills) {
			
			/**
			 * 先校验
			 * 1、错误状态 的不能审核
			 */
			KaipiaoinfoHVO kpHVO = clientBill.getParentVO();	// 主表VO
			String fpzt = kpHVO.getVdef02();	// 发票状态
			if(  fpzt!=null
			  && fpzt.length()>0
			  &&!fpzt.equals("正常")
			){
				throw new BusinessException("存在 异常状态的发票，不能审核！");
			}
			/**END*/
			
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		
		BillUpdate<KaipiaoinfoBillVO> update = new BillUpdate<KaipiaoinfoBillVO>();
		KaipiaoinfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		
		// 将信息同步到 会员卡档案，逐行处理
		BaseDAO baseDAO = new BaseDAO();
		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk 生成器
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganKPVO>  insertVOs_kp  = new ArrayList<KadanganKPVO>();		// 要插入的VO  开票
			ArrayList<KadanganKKPVO> updateVOs_kkp = new ArrayList<KadanganKKPVO>();	// 要更新的VO  可开票
			ArrayList<KadanganKKPVO> insertVOs_kkp = new ArrayList<KadanganKKPVO>();	// 要插入的VO  可开票（负数）
			
			KaipiaoinfoBVO[] kpBVOs = (KaipiaoinfoBVO[])returnVos[vos_i].getChildrenVO();	// 子表VO
			KaipiaoinfoHVO kpHVO = returnVos[vos_i].getParentVO();			// 主表VO
			
			for( int i=0;i<kpBVOs.length;i++ )
			{
				String kaxing_name = kpBVOs[i].getKaxing_name();	// 判断是不是  删除卡 
				
//				if( null==kpHVO.getVdef01()
//				||	"~".equals(kpHVO.getVdef01())
//				||	"开票".equals(kpHVO.getVdef01()) 
//				||  "续卡".equals(kpHVO.getVdef01())
//				)
				{
					if( !"删除卡".equals( kaxing_name ) )
					{
						/**
						 * 插入 开票信息
						 */
						KadanganKPVO kpvo = new KadanganKPVO();
						kpvo.setPk_hk_huiyuan_kadangan( kpBVOs[i].getKa_pk() );	// 卡pk
						kpvo.setKp_time( kpHVO.getFpsj() );		// 开票时间
						kpvo.setKp_je( kpBVOs[i].getFpje() );	// 开票金额
						kpvo.setFph( kpHVO.getFph() );			// 发票号
						
						kpvo.setCsourcetypecode("HK33");			// 上游单据类型
						kpvo.setCsourcebillid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo());	// 上游单据id
						kpvo.setCsourcebillbid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b());	// 上游单据行id
						kpvo.setPk_hk_huiyuan_kadangan_kp(pkGenerator.generate());			// 赋值pk
						kpvo.setDr(0);
						kpvo.setVbdef01( kpHVO.getVdef01() );	// 门店
						
						insertVOs_kp.add(kpvo);
						/**END*/
						
						{// 更新  已开票总额
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" ykpje = nvl(ykpje,0) + " + kpvo.getKp_je() +
//									" where pk_hk_huiyuan_kadangan = '"+kpvo.getPk_hk_huiyuan_kadangan()+"' ");
							
							ArrayList<KadanganKKPVO> kkp_list = 
									(ArrayList<KadanganKKPVO>)baseDAO.retrieveByClause(
									KadanganKKPVO.class,
									" 1=1 " +
									" and dr = 0 " +
									" and pk_hk_huiyuan_kadangan = '"+kpBVOs[i].getKa_pk()+"' " +
									" and nvl(kkp_je,0) - nvl(ykp_je,0) <> 0.00 " ,
//									" and kpjz_time <= '" + (new UFDateTime().toString()) + "'",	// 取截至日期以前的
									" kpjz_time "	// order by 开票截至时间
							);
							
							UFDouble fpje = PuPubVO.getUFDouble_NullAsZero( kpBVOs[i].getFpje() );
							
							if( fpje.compareTo(UFDouble.ZERO_DBL)>=0 )
							{//  如果 发票金额 为正数， 则正常 抵扣
								for( KadanganKKPVO kkpVO:kkp_list )
								{
									UFDouble kkp_je = // 可开票余额  = 可开票金额 - 已开票金额
											 PuPubVO.getUFDouble_NullAsZero( kkpVO.getKkp_je() )
										.sub(PuPubVO.getUFDouble_NullAsZero( kkpVO.getYkp_je() ) 
									);
									if( fpje.compareTo(kkp_je)>=0 )
									{// 如果 发票金额 大于等于 可开票金额， 则  本行 实际开票金额 为  可开票金额。 发票金额 进行抵扣。
										
										kkpVO.setYkp_je( // 已开票金额 = 之前已开票金额 + 本次开票金额
												  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
											.add( kkp_je )
										);
										fpje = fpje.sub(kkp_je);
									}
									else
									{// 否则， 则  本行 实际开票金额 为  发票金额。 发票金额 抵扣为0。
										
										kkpVO.setYkp_je( // 已开票金额 = 之前已开票金额 + 本次开票金额
												  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
											.add( fpje )
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
							{// 如果 发票金额 为负数，则直接累加
								if( kkp_list!=null && kkp_list.size()>0 )
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
											kkpVO.setYkp_je( // 已开票 = 已开票 + 当前开票
													  PuPubVO.getUFDouble_NullAsZero(kkpVO.getYkp_je())
												.add( PuPubVO.getUFDouble_NullAsZero(kpBVOs[i].getFpje()) )
											);
											
											updateVOs_kkp.add(kkpVO);	// 添加要更新的vo
											
											fpje = UFDouble.ZERO_DBL;
											
											break;
										}
									}
								}
								else
								{// 如果没有 可开票数据  则新增一条记录
									KadanganKKPVO kkpvo = new KadanganKKPVO();
									kkpvo.setPk_hk_huiyuan_kadangan( kpBVOs[i].getKa_pk() );	// 卡pk
									kkpvo.setCz_time( new UFDateTime(kpHVO.getDbilldate().toDate()) );	// 充值时间
									kkpvo.setKpjz_time( new UFDateTime(kpHVO.getDbilldate().getDateAfter(180).toDate()) );	// 开票截至时间
									kkpvo.setKkp_je( UFDouble.ZERO_DBL );	// 可开票金额
									kkpvo.setYkp_je( kpBVOs[i].getFpje() );	// 已开票金额
									
									kkpvo.setCsourcetypecode("HK33");			// 上游单据类型
									kkpvo.setCsourcebillid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo());		// 上游单据id
									kkpvo.setCsourcebillbid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b());	// 上游单据行id
									kkpvo.setPk_hk_huiyuan_kadangan_kkp(pkGenerator.generate());			// 赋值pk
									kkpvo.setDr(0);
									kkpvo.setVbdef01( kpHVO.getVdef01() );	// 门店
									
									insertVOs_kkp.add(kkpvo);
									
									fpje = UFDouble.ZERO_DBL;
								}
							}
							
							// 如果 循环完了  发票金额不等于0，则说明  可开票金额不够，则抛出错误
							if(fpje.compareTo(UFDouble.ZERO_DBL)!=0)
							{
								throw new BusinessException("【"+kpBVOs[i].getKa_code()+"】  可开票余额不足，不能审核。");
							}
						}
					}
					else
					{
						{// 更新  已开票总额 old
							baseDAO.executeUpdate(
									" update hk_huiyuan_kaipiao_old " +
									" set " +
									" ykpze = nvl(ykpze,0) + " + kpBVOs[i].getFpje() +
									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
						}
					}
				}
//				else if( "转卡".equals(kpHVO.getVdef01()) )
//				{
//					if( !"删除卡".equals( kaxing_name ) )
//					{
//						KadanganKPVO kpvo = new KadanganKPVO();
//						kpvo.setPk_hk_huiyuan_kadangan( kpBVOs[i].getKa_pk() );	// 卡pk
//						kpvo.setKp_time( kpHVO.getFpsj() );		// 开票时间
//						kpvo.setKp_je( kpBVOs[i].getFpje() );	// 开票金额
//						kpvo.setFph( kpHVO.getFph() );			// 发票号
//						
//						kpvo.setCsourcetypecode("HK33");			// 上游单据类型
//						kpvo.setCsourcebillid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo());	// 上游单据id
//						kpvo.setCsourcebillbid(kpBVOs[i].getPk_hk_huiyuan_kaipiaoinfo_b());	// 上游单据行id
//						kpvo.setPk_hk_huiyuan_kadangan_kp(pkGenerator.generate());			// 赋值pk
//						kpvo.setDr(0);
//						kpvo.setVbdef01( kpHVO.getVdef01() );	// 门店
//						
//						insertVOs_kp.add(kpvo);
//						
//						{// 更新  转卡金额
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) + " + kpvo.getKp_je() +
//									" where pk_hk_huiyuan_kadangan = '"+kpvo.getPk_hk_huiyuan_kadangan()+"' ");
//						}
//					}
//					else
//					{
//						{// 更新  已开票总额 old
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kaipiao_old " +
//									" set " +
//									" vdef04 = to_number(replace(vdef04,'~','0')) + " + kpBVOs[i].getFpje() +
//									" where ka_code = '"+kpBVOs[i].getKa_code()+"' ");
//						}
//					}
//				}
			}
			
			baseDAO.insertVOList(insertVOs_kp);		// 新增 开票数据
			baseDAO.updateVOList(updateVOs_kkp);	// 更新 可开票数据
			baseDAO.insertVOList(insertVOs_kkp);	// 新增 可开票数据
		}
		
		return returnVos;
	}

}
