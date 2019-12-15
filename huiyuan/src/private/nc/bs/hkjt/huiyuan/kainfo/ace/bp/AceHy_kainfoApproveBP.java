package nc.bs.hkjt.huiyuan.kainfo.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.hkjt.huiyuan.kadangan.KadanganCZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganJHVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganXFVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganYZVO;
import nc.vo.hkjt.huiyuan.kadangan.KadanganZFVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.vo.hkjt.huiyuan.kainfo.KainfoHVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_kainfoApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KainfoBillVO[] approve(KainfoBillVO[] clientBills,
			KainfoBillVO[] originBills)throws BusinessException {
		for (KainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KainfoBillVO> update = new BillUpdate<KainfoBillVO>();
		KainfoBillVO[] returnVos = update.update(clientBills, originBills);
		
		// 将信息同步到 会员卡档案，逐行处理
		BaseDAO baseDAO = new BaseDAO();
		SequenceGenerator pkGenerator = new SequenceGenerator();	// pk 生成器
		for(int vos_i=0;vos_i<returnVos.length;vos_i++)
		{
			ArrayList<KadanganXFVO> insertVOs_xf = new ArrayList<KadanganXFVO>();	// 要插入的VO  消费
			ArrayList<KadanganYZVO> insertVOs_yz = new ArrayList<KadanganYZVO>();	// 要插入的VO  余转
			ArrayList<KadanganCZVO> insertVOs_cz = new ArrayList<KadanganCZVO>();	// 要插入的VO  充值
			ArrayList<KadanganZFVO> insertVOs_zf = new ArrayList<KadanganZFVO>();	// 要插入的VO  作废
			ArrayList<KadanganJHVO> insertVOs_jh = new ArrayList<KadanganJHVO>();	// 要插入的VO  激活
			ArrayList<KadanganKKPVO> insertVOs_kkp = new ArrayList<KadanganKKPVO>();	// 要插入的VO  可开票（2016年9月18日15:09:37）
//			ArrayList<KadanganHVO>  updateVOs_ka = new ArrayList<KadanganHVO>();	// 要更新的VO  卡档案
			KainfoBVO[] kainfoBVOs = (KainfoBVO[])returnVos[vos_i].getChildrenVO();	// 子表VO
			KainfoHVO kainfoHVO = returnVos[vos_i].getParentVO();		// 主表VO
			
			// 校验，是否数据完整（在提交时 做校验）
			
			// 1、冒泡 排序， 按业务时间  升序。
			for(int m_i=0;m_i<kainfoBVOs.length-1;m_i++)
			{
				for(int m_j = m_i+1 ; m_j < kainfoBVOs.length ; m_j++)
				{
					UFDateTime ywsj_i = kainfoBVOs[m_i].getYwsj();
					UFDateTime ywsj_j = kainfoBVOs[m_i].getYwsj();
					
					if( ywsj_j.compareTo(ywsj_i)>0 )
					{
						KainfoBVO temp = kainfoBVOs[m_i];
						kainfoBVOs[m_i] = kainfoBVOs[m_j];
						kainfoBVOs[m_j] = temp;
					}
				}
			}
			
			// 2、进行处理
			for( int i=0;i<kainfoBVOs.length;i++ )
			{
				String xmdl = kainfoBVOs[i].getXmdl();	// 项目大类
				String xmlx = kainfoBVOs[i].getXmlx();	// 项目类型
				
				if( kainfoBVOs[i].getKa_pk()!=null )
				{// 卡号 不等于空的  才进行处理
					
					if("充值".equals(xmdl))
					{
						// 普通充值
						{
							KadanganCZVO czvo = new KadanganCZVO();	// 充值
							czvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// 卡pk
							czvo.setCz_time( kainfoBVOs[i].getYwsj() );	// 充值时间
							czvo.setZdh( kainfoBVOs[i].getZdh() );		// 账单号
							czvo.setCz_je(kainfoBVOs[i].getKa_je());	// 充值卡金额
							czvo.setCz_ss(kainfoBVOs[i].getKa_ss());	// 充值实收
							czvo.setCz_zs(kainfoBVOs[i].getKa_zs());	// 充值赠送
							czvo.setKayue(kainfoBVOs[i].getKa_yue());	// 卡余额
							
							czvo.setCsourcetypecode("HK24");			// 上游单据类型
							czvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// 上游单据id
							czvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
							czvo.setPk_hk_huiyuan_kadangan_cz(pkGenerator.generate());			// 赋值pk
							czvo.setDr(0);
							czvo.setVbdef01( kainfoHVO.getVdef01() );	// 门店
							czvo.setVbdef02( xmlx );	// 项目类型
							czvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// 业务日期
							
							insertVOs_cz.add(czvo);
							
							
							// 更新 可开票总额
							// 回充  不更新
							// 作废  不更新
							if( !"回充".equals(kainfoBVOs[i].getXmlx()) )
							{
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_ss())!=null )
								{
//									baseDAO.executeUpdate(
//											" update hk_huiyuan_kadangan " +
//											" set " +
//											" kkpze = nvl(kkpze,0) + " + kainfoBVOs[i].getKa_ss() +
//											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
									
									/**
									 *  不更新 表头的可开票， 而插入可开票-子页签
									 *  2016年9月18日15:14:23
									 */
									KadanganKKPVO kkpvo = new KadanganKKPVO();	// 可开票
									kkpvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// 卡pk
									kkpvo.setCz_time( kainfoBVOs[i].getYwsj() );// 充值时间
									kkpvo.setKpjz_time( kainfoBVOs[i].getYwsj().getDateTimeAfter(180) );// 截止时间 （半年 180天）
									kkpvo.setZdh( kainfoBVOs[i].getZdh() );		// 账单号
									kkpvo.setKkp_je(kainfoBVOs[i].getKa_ss());	// 可开票金额 = 卡实收
									
									kkpvo.setCsourcetypecode("HK24");			// 上游单据类型
									kkpvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());	// 上游单据id
									kkpvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
									kkpvo.setPk_hk_huiyuan_kadangan_kkp(pkGenerator.generate());		// 赋值pk
									kkpvo.setDr(0);
									
									insertVOs_kkp.add(kkpvo);
									
									System.out.println("=="+insertVOs_kkp);
									/**END*/
								}
							}
							
							// 更新 会员卡 当前余额
							// 更新 卡状态 为 正常
							if( !"作废卡".equals(kainfoBVOs[i].getXmlx()) )
							{// 作废卡  不更新
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) + " + kainfoBVOs[i].getKa_je() +
	//										",kastatus = '正常' " + 
	//										",vdef02 = '~' " +
	//										",vdef03 = '"+ kainfoHVO.getDbilldate() +"' " +	// 充值时间
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
								}
							}
							
						}
						
//						if("作废卡".equals(xmlx))
//						{
//							baseDAO.executeUpdate(
//									" update hk_huiyuan_kadangan " +
//									" set " +
//									" kastatus = '作废' " +
//									",vdef02 = '"+ kainfoBVOs[i].getPk_hk_huiyuan_kainfo() +"' " +
//									",vdef03 = '"+ kainfoHVO.getDbilldate() +"' " +	// 作废时间
//									" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
//						}
						
					}
					else if("消费".equals(xmdl))
					{
						KadanganXFVO xfvo = new KadanganXFVO();
						xfvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// 卡pk
						xfvo.setXf_time( kainfoBVOs[i].getYwsj() );	// 消费时间
						xfvo.setZdh( kainfoBVOs[i].getZdh() );		// 账单号
						xfvo.setXf_je(kainfoBVOs[i].getKa_je());	// 消费卡金额
						xfvo.setXf_ss(kainfoBVOs[i].getKa_ss());	// 消费实收
						xfvo.setKayue(kainfoBVOs[i].getKa_yue());	// 卡余额
						
						xfvo.setCsourcetypecode("HK24");			// 上游单据类型
						xfvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// 上游单据id
						xfvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
						xfvo.setPk_hk_huiyuan_kadangan_xf(pkGenerator.generate());			// 赋值pk
						xfvo.setDr(0);
						xfvo.setVbdef01( kainfoHVO.getVdef01() );	// 门店
						xfvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// 业务日期
						
						insertVOs_xf.add(xfvo);
						
						// 更新 会员卡 当前余额
						if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_je())!=null )
						{
							baseDAO.executeUpdate(
									" update hk_huiyuan_kadangan " +
									" set " +
									" dq_ye = nvl(dq_ye,0) + " + kainfoBVOs[i].getKa_je() +
									" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
						}
						
					}
					else if("余转".equals(xmdl))
					{
						if( kainfoBVOs[i].getKa_pk()!=null ){
							
							if( "0203005888".equals(kainfoBVOs[i].getY_ka_code()) 
							&& !(kainfoBVOs[i].getKa_code().compareTo("0302101237")>=0 && kainfoBVOs[i].getKa_code().compareTo("0302101245")<=0)	// 如果是 一级大卡  转到  三级大卡， 则 不算为 作废，算为  余转。
							  )
							{// 如果是从 三级大卡 转出来的，做 作废卡的  回冲处理。 （金额为负数）
							 // 如果是由 三级财务大卡 转出地  则 同步到 卡档案的作废子页签里
								KadanganZFVO zfvo = new KadanganZFVO();	// 作废
								zfvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// 卡pk
								zfvo.setZf_time( kainfoBVOs[i].getYwsj() );	//作废余转时间
								zfvo.setZc_je( UFDouble.ZERO_DBL.sub( PuPubVO.getUFDouble_NullAsZero( kainfoBVOs[i].getKa_je() ) ) );	//作废回冲余额（取负数）
//								zfvo.setZf_ss( kainfoBVOs[i].getY_ka_ss() );	//转出实收
							
								zfvo.setKayue( kainfoBVOs[i].getKa_yue() );	//卡余额
								zfvo.setZdh( kainfoBVOs[i].getZdh() );		//账单号
								
								zfvo.setCsourcetypecode("HK24");			// 上游单据类型
								zfvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// 上游单据id
								zfvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
								zfvo.setPk_hk_huiyuan_kadangan_zf(pkGenerator.generate());			// 赋值pk
								zfvo.setDr(0);
								zfvo.setVbdef01( kainfoHVO.getVdef01() );	// 门店
								zfvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// 业务日期
								
								insertVOs_zf.add(zfvo);
								
								// 更新 会员卡 当前余额
								if( PuPubVO.getUFDouble_ZeroAsNull( zfvo.getZc_je() )!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + zfvo.getZc_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
								}
								
							}
							else
							{
								KadanganYZVO zrvo = new KadanganYZVO();	// 转入
								zrvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getKa_pk() );	// 卡pk
								zrvo.setYz_time( kainfoBVOs[i].getYwsj() );	//余转时间
								zrvo.setZr_je( kainfoBVOs[i].getKa_je() );	//转入金额
								zrvo.setZr_ss( kainfoBVOs[i].getKa_ss() );	//转入实收
								zrvo.setKayue( kainfoBVOs[i].getKa_yue() );	//卡余额
								zrvo.setZdh( kainfoBVOs[i].getZdh() );		//账单号
								// 对方
								zrvo.setDf_ka_code( kainfoBVOs[i].getY_ka_code() );
								zrvo.setDf_ka_name( kainfoBVOs[i].getY_ka_name() );
								zrvo.setDf_ka_pk( kainfoBVOs[i].getY_ka_pk() );
								zrvo.setDf_kaxing_code( kainfoBVOs[i].getY_kaxing_code() );
								zrvo.setDf_kaxing_name( kainfoBVOs[i].getY_kaxing_name() );
								zrvo.setDf_kaxing_pk( kainfoBVOs[i].getY_kaxing_pk() );
								
								zrvo.setCsourcetypecode("HK24");			// 上游单据类型
								zrvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// 上游单据id
								zrvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
								zrvo.setPk_hk_huiyuan_kadangan_yz(pkGenerator.generate());			// 赋值pk
								zrvo.setDr(0);
								zrvo.setVbdef01( kainfoHVO.getVdef01() );	// 门店
								zrvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// 业务日期
								
								insertVOs_yz.add(zrvo);
								
								// 更新 会员卡 当前余额
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getKa_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) + " + kainfoBVOs[i].getKa_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getKa_pk()+"' ");
								}
								
							}
							
						}
						
						if( kainfoBVOs[i].getY_ka_pk()!=null ){
							
							if( "0203005888".equals(kainfoBVOs[i].getKa_code()) 
							|| "虚拟卡".equals(kainfoBVOs[i].getKa_code())	// 往  虚拟卡里转
							)
							{// 是否转向  三级财务大卡
							 // 如果转向  三级财务大卡  则 同步到 卡档案的作废子页签里
								KadanganZFVO zfvo = new KadanganZFVO();	// 作废
								zfvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getY_ka_pk() );	// 卡pk
								zfvo.setZf_time( kainfoBVOs[i].getYwsj() );	//作废余转时间
								zfvo.setZc_je( kainfoBVOs[i].getKa_je() );	//转出金额
//								zfvo.setZf_ss( kainfoBVOs[i].getY_ka_ss() );	//转出实收
							
								zfvo.setKayue( kainfoBVOs[i].getY_ka_yue() );	//卡余额
								zfvo.setZdh( kainfoBVOs[i].getZdh() );		//账单号
								
								zfvo.setCsourcetypecode("HK24");			// 上游单据类型
								zfvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// 上游单据id
								zfvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
								zfvo.setPk_hk_huiyuan_kadangan_zf(pkGenerator.generate());			// 赋值pk
								zfvo.setDr(0);
								zfvo.setVbdef01( kainfoHVO.getVdef01() );	// 门店
								zfvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// 业务日期
								
								insertVOs_zf.add(zfvo);
								
								// 更新 会员卡 当前余额
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getY_ka_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + kainfoBVOs[i].getY_ka_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getY_ka_pk()+"' ");
								}
							}
							else
							{
								KadanganYZVO zcvo = new KadanganYZVO();	// 转出
								zcvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getY_ka_pk() );	// 卡pk
								zcvo.setYz_time( kainfoBVOs[i].getYwsj() );	//余转时间
								zcvo.setZc_je( kainfoBVOs[i].getKa_je() );	//转出金额
								zcvo.setZc_ss( kainfoBVOs[i].getY_ka_ss() );	//转出实收
								zcvo.setKayue( kainfoBVOs[i].getY_ka_yue() );	//卡余额
								zcvo.setZdh( kainfoBVOs[i].getZdh() );		//账单号
								// 对方
								zcvo.setDf_ka_code( kainfoBVOs[i].getKa_code() );
								zcvo.setDf_ka_name( kainfoBVOs[i].getKa_name() );
								zcvo.setDf_ka_pk( kainfoBVOs[i].getKa_pk() );
								zcvo.setDf_kaxing_code( kainfoBVOs[i].getKaxing_code() );
								zcvo.setDf_kaxing_name( kainfoBVOs[i].getKaxing_name() );
								zcvo.setDf_kaxing_pk( kainfoBVOs[i].getKaxing_pk() );
								
								zcvo.setCsourcetypecode("HK24");			// 上游单据类型
								zcvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// 上游单据id
								zcvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
								zcvo.setPk_hk_huiyuan_kadangan_yz(pkGenerator.generate());			// 赋值pk
								zcvo.setDr(0);
								zcvo.setVbdef01( kainfoHVO.getVdef01() );	// 门店
								zcvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// 业务日期
								
								insertVOs_yz.add(zcvo);
								
								// 更新 会员卡 当前余额
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getY_ka_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + kainfoBVOs[i].getY_ka_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getY_ka_pk()+"' ");
								}
								
							}
							
							if( "0103001500".equals(kainfoBVOs[i].getY_ka_code()) )
							{// 是否 由休眠大卡 转出的。 需要做休眠卡的 处理
								
								KadanganJHVO jhvo = new KadanganJHVO();	// 激活
								jhvo.setPk_hk_huiyuan_kadangan( kainfoBVOs[i].getXmka_pk() );	// 卡pk
								jhvo.setJh_time( kainfoBVOs[i].getYwsj() );			// 激活时间
								jhvo.setKa_code_jh( kainfoBVOs[i].getKa_code() );	// 激活卡号
								
								jhvo.setCsourcetypecode("HK24");			// 上游单据类型
								jhvo.setCsourcebillid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo());		// 上游单据id
								jhvo.setCsourcebillbid(kainfoBVOs[i].getPk_hk_huiyuan_kainfo_b());	// 上游单据行id
								jhvo.setPk_hk_huiyuan_kadangan_jh(pkGenerator.generate());			// 赋值pk
								jhvo.setDr(0);
								jhvo.setVbdef01( kainfoHVO.getVdef01() );	// 门店
								jhvo.setVbdef03( kainfoHVO.getDbilldate().toString() );	// 业务日期
								
								insertVOs_jh.add(jhvo);
								
								baseDAO.executeUpdate(
										" update hk_huiyuan_kadangan " +
										" set " +
										" kastatus = '激活' " +
										",vdef02 = '"+ kainfoBVOs[i].getPk_hk_huiyuan_kainfo() +"' " +
										",vdef03 = '"+ kainfoHVO.getDbilldate() +"' " +	// 激活日期
										" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getXmka_pk()+"' ");
								
								// 更新 会员卡 当前余额
								if( PuPubVO.getUFDouble_ZeroAsNull(kainfoBVOs[i].getY_ka_je())!=null )
								{
									baseDAO.executeUpdate(
											" update hk_huiyuan_kadangan " +
											" set " +
											" dq_ye = nvl(dq_ye,0) - " + kainfoBVOs[i].getY_ka_je() +
											" where pk_hk_huiyuan_kadangan = '"+kainfoBVOs[i].getXmka_pk()+"' ");
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
			baseDAO.insertVOList(insertVOs_xf);	// 消费
			baseDAO.insertVOList(insertVOs_yz);	// 余转
			baseDAO.insertVOList(insertVOs_cz); // 充值
			baseDAO.insertVOList(insertVOs_zf); // 作废
			baseDAO.insertVOList(insertVOs_jh);	// 激活
			baseDAO.insertVOList(insertVOs_kkp);// 可开票
			
		}
		
		return returnVos;
	}
	
	

}
