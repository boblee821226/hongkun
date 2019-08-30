package nc.itf.hkjt;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

public class PUB_kaipiao {

	public static KaipiaoqueryBillVO[] bbcx_data(String ka_code,String fph,boolean isRefresh,String srcFlag) throws BusinessException 
	{
		try
		{
		
			String currTime = new UFDateTime().toString();	// 当前服务器 时间
			
			ArrayList<KaipiaoqueryBillVO> result_list = new ArrayList<KaipiaoqueryBillVO>();
			
			/**
			 * 按照如下顺序 查数据
			 * 1、有开票记录的  会员卡档案
			 * 2、无开票记录的  会员卡档案
			 * 3、有开票记录的  无业务卡档案
			 * 4、无开票记录的  无业务卡档案  
			 */
			if(ka_code!=null && !"".equals(ka_code.trim()))
			{
				String[] ka_code_str = ka_code.split(",");
				
				for(int i=0;i<ka_code_str.length;i++)
				{
					KaipiaoqueryBillVO[] result_temp = PUB_kaipiao.queryData(currTime, ka_code_str[i], null, isRefresh,srcFlag);
					if(result_temp!=null && result_temp.length>0)
					{
						result_list.add(result_temp[0]);
					}
				}
				
			}
			else if(fph!=null && !"".equals(fph.trim()))
			{
				KaipiaoqueryBillVO[] result_temp = PUB_kaipiao.queryData(currTime, null, fph, isRefresh,srcFlag);
				if(result_temp!=null && result_temp.length>0)
				{
					for(int i=0;i<result_temp.length;i++)
					{
						result_list.add(result_temp[i]);
					}
				}
			}
			
			KaipiaoqueryBillVO[] RESULT = null;
			if(result_list.size()>0)
			{
				RESULT = new KaipiaoqueryBillVO[result_list.size()];
				RESULT = result_list.toArray(RESULT);
			}
			return RESULT;
		}
		catch(Exception ex)
		{
			throw new BusinessException(ex);
		}
		
	}
	
	
	/**
	 * 报表数据查询
	 */
	public static KaipiaoqueryBillVO[] queryData(String currTime,String ka_code,String fph,boolean isRefresh,String srcFlag) throws Exception
	{
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		KaipiaoqueryBillVO[] RESULT = null;
		
		// 1、 查询有 开票记录的会员卡 档案
					StringBuffer querySQL = 
							new StringBuffer("select ")
									.append(" kpb.fpje ")		// 发票金额
			//						.append(",kp.fpdm || ' ' ||kp.fph as fph ")	// 发票号
									.append(",kp.fph as fph ")	// 发票号（取消发票代码）
									.append(",u.user_name kpr ")	// 开票人
									.append(",kp.dbilldate fpsj ")		// 开票日期
									.append(",kpb.ka_code vbdef01 ")	// 卡号
									.append(",kpb.kaxing_name vbdef02 ")// 卡型name
									.append(",nvl(ka.kkp_je,0)+nvl(kainfo.kkp_je,0) vbdef03 ")// 可开票总额（卡档案）
									.append(",nvl(ka.ygq_je,0) cfirsttypecode ") // 已过期发票金额
									.append(",ka.ykp_je vbdef04 ")			// 已开票总额（卡档案）
									.append(",kp.ibillstatus vbdef05 ")		// 单据状态 （1=审核态）
									.append(",kpb.ka_pk vbdef06 ")			// 卡pk
									.append(",kpb.kaxing_code vbdef07 ")	// 卡型code
									.append(",kpb.kaxing_pk vbdef08 ")		// 卡型pk
			//						.append(",ka.vdef04 vbdef09 ")			// 转卡金额（卡档案）（无转卡）
									.append(",kp.vdef01 csourcetypecode ")	// 类型（开票、转卡）
									.append(",dfka.ka_code vsourcebillcode ")	// 对方卡号 
									.append(",ka.dbilldate vbdef10 ")		//NC制卡时间
									.append(",kkp.kpjz_time cfirstbillid ")		//开票截至时间
									.append(",kp.pk_hk_huiyuan_kaipiaoinfo vbdef09 ")	// 发票pk（为了联查）
									.append(",ka.vdef01 cfirstbillbid ")	// 发卡店
									.append(" from hk_huiyuan_kaipiaoinfo kp ")		
									.append(" inner join hk_huiyuan_kaipiaoinfo_b kpb on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
									.append(" left join (" +
												" select " +
												" ka.pk_hk_huiyuan_kadangan " +
												",sum(nvl(kkp.kkp_je,0)) kkp_je " +
												",sum(nvl(kkp.ykp_je,0)) ykp_je " +
												",sum( case when kkp.kpjz_time<='"+currTime+"' then nvl(kkp.kkp_je,0)-nvl(kkp.ykp_je,0) else 0 end) ygq_je " + // 已过期的可开票金额 （与 当前时间比较）
												",max(ka.pk_hk_huiyuan_kaxing) pk_hk_huiyuan_kaxing " +
												",max(ka.ka_code) ka_code " +
												",max(ka.dbilldate) dbilldate " +
												",max(ka.vdef01) vdef01 " +	// 发卡店
												" from hk_huiyuan_kadangan ka "  +
												" left join hk_huiyuan_kadangan_kkp kkp on ka.pk_hk_huiyuan_kadangan = kkp.pk_hk_huiyuan_kadangan and kkp.dr=0 " +
												" where ka.dr=0 " +
												(ka_code==null ? "" : 
													isRefresh  ? " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") "
															   : " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") ") + 
												(    fph==null ? "" : " and ka.ka_code in ( " +
																				"select kpb2.ka_code " +
																				"from hk_huiyuan_kaipiaoinfo kp2 " +
																				"inner join hk_huiyuan_kaipiaoinfo_b kpb2 on kp2.pk_hk_huiyuan_kaipiaoinfo = kpb2.pk_hk_huiyuan_kaipiaoinfo " +
																				"where kp2.dr=0 and kpb2.dr=0 and NLS_LOWER(kp2.fph) like '%"+fph.toLowerCase()+"%' " +
																				" ) ") +
												" group by ka.pk_hk_huiyuan_kadangan " +
												") ka on ka.pk_hk_huiyuan_kadangan = kpb.ka_pk ")	// 卡档案可开票-视图
									.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// 卡型
									.append(" left join sm_user u on u.cuserid = kp.creator ")	// 操作员
									.append(" left join hk_huiyuan_kadangan dfka on dfka.pk_hk_huiyuan_kadangan = kpb.vsourcebillcode ")	// 关联 对方卡
									.append(" left join ( " +	
												" select " +
												"  kib.ka_pk pk_hk_huiyuan_kadangan " +
												" ,sum(kib.ka_ss) kkp_je " +
												" from hk_huiyuan_kainfo ki " +
												" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo " +
												" where ki.dr=0 and kib.dr=0 " +
												" and kib.xmdl = '充值' " +
												" and ki.ibillstatus != 1 " +
												(ka_code==null ? "" : 
													isRefresh  ? " and NLS_UPPER(kib.ka_code) in ("+ka_code.toUpperCase()+") "
															   : " and NLS_UPPER(kib.ka_code) in ("+ka_code.toUpperCase()+") ") + 
												(    fph==null ? "" : " and kib.ka_code in ( " +
																				"select kpb2.ka_code " +
																				"from hk_huiyuan_kaipiaoinfo kp2 " +
																				"inner join hk_huiyuan_kaipiaoinfo_b kpb2 on kp2.pk_hk_huiyuan_kaipiaoinfo = kpb2.pk_hk_huiyuan_kaipiaoinfo " +
																				"where kp2.dr=0 and kpb2.dr=0 and NLS_LOWER(kp2.fph) like '%"+fph.toLowerCase()+"%' " +
																				" ) ") +
												" group by kib.ka_pk " +
												" ) kainfo on kainfo.pk_hk_huiyuan_kadangan = kpb.ka_pk ")	// 会员卡信息 未审核的充值数据
									.append(" left join ( " +
												" select "+
												"  ka.pk_hk_huiyuan_kadangan "+
												" ,max(kkp.kpjz_time) kpjz_time "+
												" from hk_huiyuan_kadangan ka "+
												" inner join hk_huiyuan_kadangan_kkp kkp on ka.pk_hk_huiyuan_kadangan = kkp.pk_hk_huiyuan_kadangan "+
												" where ka.dr=0 and kkp.dr=0 " +
												(ka_code==null ? "" : 
													isRefresh  ? " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") "
															   : " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") ") + 
												(    fph==null ? "" : " and ka.ka_code in ( " +
																				"select kpb2.ka_code " +
																				"from hk_huiyuan_kaipiaoinfo kp2 " +
																				"inner join hk_huiyuan_kaipiaoinfo_b kpb2 on kp2.pk_hk_huiyuan_kaipiaoinfo = kpb2.pk_hk_huiyuan_kaipiaoinfo " +
																				"where kp2.dr=0 and kpb2.dr=0 and NLS_LOWER(kp2.fph) like '%"+fph.toLowerCase()+"%' " +
																				" ) ") +
												" group by ka.pk_hk_huiyuan_kadangan " +
												" ) kkp on kkp.pk_hk_huiyuan_kadangan = kpb.ka_pk ")	// 开票截至时间
									.append(" where kp.dr=0 and kpb.dr=0 ")
									.append(ka_code==null ? "" :
											   isRefresh  ? " and NLS_UPPER(kpb.ka_code) in ("+ka_code.toUpperCase()+") "
													   	  : " and NLS_UPPER(kpb.ka_code) in ("+ka_code.toUpperCase()+") ")
									.append(    fph==null ? "" : " and kpb.ka_code in ( " +
																	"select kpb2.ka_code " +
																	"from hk_huiyuan_kaipiaoinfo kp2 " +
																	"inner join hk_huiyuan_kaipiaoinfo_b kpb2 on kp2.pk_hk_huiyuan_kaipiaoinfo = kpb2.pk_hk_huiyuan_kaipiaoinfo " +
																	"where kp2.dr=0 and kpb2.dr=0 and NLS_LOWER(kp2.fph) like '%"+fph.toLowerCase()+"%' " +
																	" ) ")
									.append("checkError".equals(srcFlag)?" and nvl(kp.vdef02,'正常')='正常' ":"")
									.append(" order by kp.fpsj desc ")
					;
					
					ArrayList<KaipiaoqueryBVO> list = null;
					list = (ArrayList<KaipiaoqueryBVO>)iUAPQueryBS.executeQuery(querySQL.toString(), new BeanListProcessor(KaipiaoqueryBVO.class));
					
					if(list!=null && list.size()>0)
					{
						
						/**
						 * 可能查询出 多个会员卡  所以要能体现出  表头多行。
						 */
						HashMap<String,ArrayList<KaipiaoqueryBVO>> KP_MAP = new HashMap<String,ArrayList<KaipiaoqueryBVO>>();
						for(int i=0;i<list.size();i++)
						{
							KaipiaoqueryBVO kp_bvo = list.get(i);
							String key = kp_bvo.getVbdef01();	// 卡号 为key
							if( KP_MAP.containsKey(key) )
							{
								ArrayList<KaipiaoqueryBVO> value = KP_MAP.get(key);
								value.add(kp_bvo);
								KP_MAP.put(key, value);
							}
							else
							{
								ArrayList<KaipiaoqueryBVO> value = new ArrayList<KaipiaoqueryBVO>();
								value.add(kp_bvo);
								KP_MAP.put(key, value);
							}
						}
						/**END*/
						
						String[] KP_MAP_keys = new String[KP_MAP.size()];
						KP_MAP_keys = KP_MAP.keySet().toArray(KP_MAP_keys);
						RESULT = new KaipiaoqueryBillVO[KP_MAP_keys.length];
						for( int keys_i=0;keys_i<KP_MAP_keys.length;keys_i++ )
						{
							
							ArrayList<KaipiaoqueryBVO> kp_list = KP_MAP.get(KP_MAP_keys[keys_i]);
							
							String flag = kp_list.get(0).getVbdef02();	// 判断是不是  非业务卡
							if( !"删除卡".equals(flag) )
							{
								// 结果集的处理
								RESULT[keys_i] = new KaipiaoqueryBillVO();
								KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[kp_list.size()];
								result_bvos = kp_list.toArray(result_bvos);
								RESULT[keys_i].setChildrenVO(result_bvos);
								KaipiaoqueryHVO result_hvo = new KaipiaoqueryHVO();
								result_hvo.setIbillstatus(-1);
								result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
								result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org 正德pk
								result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
								result_hvo.setKa_code( result_bvos[0].getVbdef01() );		// 卡号
								result_hvo.setKaxing_name( result_bvos[0].getVbdef02() );	// 卡型name
								result_hvo.setKa_pk( result_bvos[0].getVbdef06() );			// 卡pk
								result_hvo.setKaxing_code( result_bvos[0].getVbdef07() );	// 卡型code
								result_hvo.setKaxing_pk( result_bvos[0].getVbdef08() );		// 卡型pk
								result_hvo.setKkpze( PuPubVO.getUFDouble_NullAsZero(result_bvos[0].getVbdef03()) );	// 可开票总额 
//								result_hvo.setVdef03( result_bvos[0].getVbdef09() );		// 转卡金额 
								result_hvo.setVdef01( result_bvos[0].getVbdef10() );		// NC制卡时间
								result_hvo.setVdef04( result_bvos[0].getCfirstbillid() );	// 开票截至时间
								result_hvo.setVdef02( result_bvos[0].getCfirstbillbid() );	// 发卡店
								RESULT[keys_i].setParentVO(result_hvo);
								
								// 已开票总额 = 卡档案的已开票总额  + 非审核状态的 开票明细  总和 （HK 2019年1月17日15:12:13 改为从开票明细 里取数）
								// 转卡总额     = 卡档案的转卡总额      + 非审核状态的 开票明细  总和
								UFDouble ykpze = UFDouble.ZERO_DBL;	// 已开票（未审核的发票）
								UFDouble zkze  = UFDouble.ZERO_DBL;	// 转卡
								for(int i=0;i<result_bvos.length;i++)
								{// 循环表体  求和
									ykpze = ykpze.add( result_bvos[i].getFpje() );
								}
								
								result_hvo.setYkpze( ykpze );	// 已开票总额
								
								// 剩余开票金额 = 可开票总额 - 已开票总额 
								result_hvo.setSykpje( 
										  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
									.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) )
								);
								
								// 如果 已过期， 将 剩余开票金额  转移到  已过期票额
								if(result_hvo.getVdef04()!=null)
								{
									String nowDate_str = new UFDate().toString();
									if(nowDate_str.compareTo(result_hvo.getVdef04())>0)
									{// 如果当期时间 大于 开票截止日期  说明已过期
										result_hvo.setVdef05(result_hvo.getSykpje().toString());
										result_hvo.setSykpje(UFDouble.ZERO_DBL);
									}
								}
								
							}
							else
							{
			//					if( ka_code==null ) return null;
								
								// 3、有开票记录的  无业务卡档案（说明是 历史卡=删除卡）
								StringBuffer querySQL_3 = 
									new StringBuffer("select ")
											.append(" kpb.fpje ")		// 发票金额
			//								.append(",kp.fpdm || ' ' ||kp.fph as fph ")			// 发票号
											.append(",kp.fph as fph ")			// 发票号（取消发票代码）
											.append(",u.user_name kpr ")	// 开票人
											.append(",kp.dbilldate fpsj ")		// 开票日期
											.append(",kpb.ka_code vbdef01 ")	// 卡号
											.append(",'删除卡' vbdef02 ")			// 卡型name
											.append(",ka.kkpze vbdef03 ")		// 可开票总额（卡档案）
											.append(",ka.ykpze vbdef04 ")		// 已开票总额（卡档案）
											.append(",kp.ibillstatus vbdef05 ")		// 单据状态 （1=审核态）
											.append(",kpb.ka_pk vbdef06 ")			// 卡pk
											.append(",'删除卡' vbdef07 ")				// 卡型code
											.append(",'DELETE00000000000001' vbdef08 ")		// 卡型pk
//											.append(",ka.vdef04 vbdef09 ")			// 转卡金额
//											.append(",kp.vdef01 csourcetypecode ")	// 类型（开票、转卡）
//											.append(",dfka.ka_code vsourcebillcode ")	// 对方卡号 
											.append(",kp.pk_hk_huiyuan_kaipiaoinfo vbdef09 ")	// 发票pk（为了联查）
											.append(",ka.vdef01 cfirstbillbid ")	// 发卡店
											.append(",ka.vdef02 cfirstbillid ")		// 开票截至时间
											.append(" from hk_huiyuan_kaipiaoinfo kp ")		
											.append(" inner join hk_huiyuan_kaipiaoinfo_b kpb on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
											.append(" left join HK_HUIYUAN_KAIPIAO_OLD ka on ka.ka_code = kpb.ka_code ")
											.append(" left join sm_user u on u.cuserid = kp.creator ")
											.append(" left join hk_huiyuan_kadangan dfka on dfka.pk_hk_huiyuan_kadangan = kpb.vsourcebillcode ")	// 关联 对方卡
											.append(" where kp.dr=0 and kpb.dr=0 ")
			//								.append(" and NLS_UPPER(kpb.ka_code) = NLS_UPPER('"+ka_code+"') ")
											.append(ka_code==null ? "" :
													   isRefresh  ? " and NLS_UPPER(kpb.ka_code) in ("+ka_code.toUpperCase()+") "
															      : " and NLS_UPPER(kpb.ka_code) in ("+ka_code.toUpperCase()+") ")
											.append(    fph==null ? "" : " and kpb.ka_code in ( " +
																	"select kpb2.ka_code " +
																	"from hk_huiyuan_kaipiaoinfo kp2 " +
																	"inner join hk_huiyuan_kaipiaoinfo_b kpb2 on kp2.pk_hk_huiyuan_kaipiaoinfo = kpb2.pk_hk_huiyuan_kaipiaoinfo " +
																	"where kp2.dr=0 and kpb2.dr=0 and NLS_LOWER(kp2.fph) like '%"+fph.toLowerCase()+"%' " +
																	" ) ")
											.append(" order by kp.fpsj ")
								;
								ArrayList<KaipiaoqueryBVO> list_3 = null;
								list_3 = (ArrayList<KaipiaoqueryBVO>)iUAPQueryBS.executeQuery(querySQL_3.toString(), new BeanListProcessor(KaipiaoqueryBVO.class));
								
								if( list_3!=null && list_3.size()>0 )
								{
									// 结果集的处理
									RESULT = new KaipiaoqueryBillVO[1];
									KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[list.size()];
									result_bvos = list_3.toArray(result_bvos);
									RESULT[0] = new KaipiaoqueryBillVO();
									RESULT[0].setChildrenVO(result_bvos);
									KaipiaoqueryHVO result_hvo = new KaipiaoqueryHVO();
									result_hvo.setIbillstatus(-1);
									result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
									result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org 正德pk
									result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
									result_hvo.setKa_code( result_bvos[0].getVbdef01() );		// 卡号
									result_hvo.setKaxing_name( result_bvos[0].getVbdef02() );	// 卡型name
									result_hvo.setKa_pk( result_bvos[0].getVbdef06() );			// 卡pk
									result_hvo.setKaxing_code( result_bvos[0].getVbdef07() );	// 卡型code
									result_hvo.setKaxing_pk( result_bvos[0].getVbdef08() );		// 卡型pk
									result_hvo.setKkpze( PuPubVO.getUFDouble_NullAsZero(result_bvos[0].getVbdef03()) );	// 可开票总额 
//									result_hvo.setVdef03( result_bvos[0].getVbdef09() );		// 转卡金额 
									result_hvo.setVdef02( result_bvos[0].getCfirstbillbid() );	// 发卡店
									result_hvo.setVdef04( result_bvos[0].getCfirstbillid() );	// 开票截至时间
									RESULT[0].setParentVO(result_hvo);
									
									// 已开票总额 = 卡档案的已开票总额 + 非审核状态的 开票明细  总和
									UFDouble ykpze = UFDouble.ZERO_DBL;
									for(int i=0;i<result_bvos.length;i++)
									{// 循环表体  求和
										if( !"1".equals( result_bvos[i].getVbdef05() ) )
										{
											ykpze = ykpze.add( result_bvos[i].getFpje() );
										}
									}
									result_hvo.setYkpze( ykpze.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[0].getVbdef04()) ) );	// 已开票总额
									
									// 剩余开票金额 = 可开票总额 - 已开票总额
									result_hvo.setSykpje( 
											  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
										.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) )
										.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getVdef03()) )
									);
									
			//						// 将结果集 放到界面
			//						getEditor().getBillCardPanel().setBillData(
			//								getEditor().getBillCardPanel().getBillData());
			//						getEditor().getModel().initModel(result_vos);
			//						
			//						return result_vos;
								}
							}
						}
						
					}
					else
					{
						if( ka_code==null ) return null;
						
						// 2、 查询会员卡档案（如果 可开票总额 为 空， 则取 卡型金额）
						StringBuffer querySQL_2 = 
								new StringBuffer("select ")
										.append(" ka.ka_code ")			// 卡号
										.append(",kx.kaxing_name ")		// 卡型name
										.append(",nvl(ka.kkp_je,0)+nvl(kainfo.kkp_je,0) kkpze ")	// 可开票总额（卡档案）（抛去 已过期的）
										
										.append(",nvl(ka.kkp_je,0)+nvl(kainfo.kkp_je,0) kkpze ")	// 可开票总额（卡档案）
										.append(",nvl(ka.ygq_je,0) vdef05 ") // 已过期发票金额
										
										.append(",ka.ykp_je ykpze ")		// 已开票总额（卡档案）
			//							.append(",ka.vdef04 vdef03 ")		// 转卡金额（卡档案）
										.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")	// 卡pk
										.append(",kx.kaxing_code ")						// 卡型code
										.append(",kx.pk_hk_huiyuan_kaxing kaxing_pk ")	// 卡型pk
			//							.append(",kx.ka_ss vdef01 ")			// 卡型-实收金额
										.append(",ka.dbilldate vdef01 ")		//NC制卡时间
										.append(",kkp.kpjz_time vdef04 ")		//开票截至时间
										.append(",ka.vdef01 vdef02 ")			// 发卡店
										.append(" from (" +
												" select ka.pk_hk_huiyuan_kadangan " +
												",sum(nvl(kkp.kkp_je,0)) kkp_je " +
												",sum(nvl(kkp.ykp_je,0)) ykp_je " +
												",sum( case when kkp.kpjz_time<='"+currTime+"' then nvl(kkp.kkp_je,0)-nvl(kkp.ykp_je,0) else 0 end) ygq_je " + // 已过期的可开票金额 （与 当前时间比较）
												",max(ka.pk_hk_huiyuan_kaxing) pk_hk_huiyuan_kaxing " +
												",max(ka.ka_code) ka_code " +
												",max(ka.dbilldate) dbilldate " +
												",max(ka.vdef01) vdef01 " +
												" from hk_huiyuan_kadangan ka "  +
												" left join hk_huiyuan_kadangan_kkp kkp on ka.pk_hk_huiyuan_kadangan = kkp.pk_hk_huiyuan_kadangan and kkp.dr=0 " +
												" where ka.dr=0 " +
												(ka_code==null ? "" :
													isRefresh  ? " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") "
															   : " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") ") + 
												" group by ka.pk_hk_huiyuan_kadangan " +
												") ka ")	// 卡档案可开票-视图
										.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
										.append(" left join ( " +	
												" select " +
												"  kib.ka_pk pk_hk_huiyuan_kadangan " +
												" ,sum(kib.ka_ss) kkp_je " +
												" from hk_huiyuan_kainfo ki " +
												" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo " +
												" where ki.dr=0 and kib.dr=0 " +
												" and kib.xmdl = '充值' " +
												" and ki.ibillstatus != 1 " +
												(ka_code==null ? "" : 
													isRefresh  ? " and NLS_UPPER(kib.ka_code) in ("+ka_code.toUpperCase()+") "
															   : " and NLS_UPPER(kib.ka_code) in ("+ka_code.toUpperCase()+") ") + 
												(    fph==null ? "" : " and kib.ka_code in ( " +
																				"select kpb2.ka_code " +
																				"from hk_huiyuan_kaipiaoinfo kp2 " +
																				"inner join hk_huiyuan_kaipiaoinfo_b kpb2 on kp2.pk_hk_huiyuan_kaipiaoinfo = kpb2.pk_hk_huiyuan_kaipiaoinfo " +
																				"where kp2.dr=0 and kpb2.dr=0 and NLS_LOWER(kp2.fph) like '%"+fph.toLowerCase()+"%' " +
																				" ) ") +
												" group by kib.ka_pk " +
												" ) kainfo on kainfo.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")	// 会员卡信息 未审核的充值数据
										.append(" left join ( " +
												" select "+
												"  ka.pk_hk_huiyuan_kadangan "+
												" ,max(kkp.kpjz_time) kpjz_time "+
												" from hk_huiyuan_kadangan ka "+
												" inner join hk_huiyuan_kadangan_kkp kkp on ka.pk_hk_huiyuan_kadangan = kkp.pk_hk_huiyuan_kadangan "+
												" where ka.dr=0 and kkp.dr=0 " +
												(ka_code==null ? "" : 
													isRefresh  ? " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") "
															   : " and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") ") + 
												(    fph==null ? "" : " and ka.ka_code in ( " +
																				"select kpb2.ka_code " +
																				"from hk_huiyuan_kaipiaoinfo kp2 " +
																				"inner join hk_huiyuan_kaipiaoinfo_b kpb2 on kp2.pk_hk_huiyuan_kaipiaoinfo = kpb2.pk_hk_huiyuan_kaipiaoinfo " +
																				"where kp2.dr=0 and kpb2.dr=0 and NLS_LOWER(kp2.fph) like '%"+fph.toLowerCase()+"%' " +
																				" ) ") +
												" group by ka.pk_hk_huiyuan_kadangan " +
												" ) kkp on kkp.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")	// 开票截至时间
										.append(" where (1=1)  ")
			//							.append(" and ka.dr= 0  ")
										.append(" and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") ")
						;  
						
						ArrayList<KaipiaoqueryHVO> list_2 = null;
						list_2 = (ArrayList<KaipiaoqueryHVO>)iUAPQueryBS.executeQuery(querySQL_2.toString(), new BeanListProcessor(KaipiaoqueryHVO.class));
						
						if(list_2!=null && list_2.size()>0)
						{
							// 结果集的处理
							RESULT = new KaipiaoqueryBillVO[1];
							RESULT[0] = new KaipiaoqueryBillVO();
							KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[1];
							result_bvos[0] = new KaipiaoqueryBVO();
							RESULT[0].setChildrenVO(result_bvos);
							KaipiaoqueryHVO result_hvo = list_2.get(0);
							result_hvo.setIbillstatus(-1);
							result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
							result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org 正德pk
							result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
							RESULT[0].setParentVO(result_hvo);
							
							if( result_hvo.getKkpze()==null )
							{// 如果  可开票总额  为空， 说明是 新开的卡，取 卡型实收金额
								result_hvo.setKkpze( PuPubVO.getUFDouble_NullAsZero(result_hvo.getVdef01()) );
								result_hvo.setYkpze( UFDouble.ZERO_DBL );
							}
							
							UFDouble ykpze =  UFDouble.ZERO_DBL;	// 已开票总额 = 零
							
							result_hvo.setYkpze( ykpze );	// 已开票总额
							
							// 剩余开票金额 = 可开票总额 - 已开票总额 
							result_hvo.setSykpje( 
									  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
								.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) )
							);
							
							// 如果 已过期， 将 剩余开票金额  转移到  已过期票额
							if(result_hvo.getVdef04()!=null)
							{
								String nowDate_str = new UFDate().toString();
								if(nowDate_str.compareTo(result_hvo.getVdef04())>0)
								{// 如果当期时间 大于 开票截止日期  说明已过期
									result_hvo.setVdef05(result_hvo.getSykpje().toString());
									result_hvo.setSykpje(UFDouble.ZERO_DBL);
								}
							}
							
						}
						else
						{
							
							// 4、无开票记录的  无业务卡档案  
							StringBuffer querySQL_4 =
									new StringBuffer("select ")
											.append(" ka.ka_code ")			// 卡号
											.append(",'删除卡' kaxing_name ")	// 卡型name
											.append(",ka.kkpze ")			// 可开票总额（卡档案）
											.append(",ka.ykpze ")			// 已开票总额（卡档案）
//											.append(",ka.vdef04 vdef03 ")	// 转卡金额（卡档案）
											.append(",ka.vdef01 vdef02 ")	// 发卡店
											.append(",ka.pk_HK_HUIYUAN_KAIPIAO_OLD ka_pk ")	// 卡pk
											.append(",ka.vdef02 vdef04 ")	// 开票截至时间
											.append(",'删除卡' kaxing_code ")					// 卡型code
											.append(",'DELETE00000000000001' kaxing_pk ")	// 卡型pk
											.append(" from HK_HUIYUAN_KAIPIAO_OLD ka ")
											.append(" where ka.dr= 0  ")
											.append(" and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") " )
							;  
							
							ArrayList<KaipiaoqueryHVO> list_4 = null;
							list_4 = (ArrayList<KaipiaoqueryHVO>)iUAPQueryBS.executeQuery(querySQL_4.toString(), new BeanListProcessor(KaipiaoqueryHVO.class));
							
							if(list_4!=null && list_4.size()>0)
							{
								// 结果集的处理
								RESULT = new KaipiaoqueryBillVO[1];
								RESULT[0] = new KaipiaoqueryBillVO();
								KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[1];
								result_bvos[0] = new KaipiaoqueryBVO();
								RESULT[0].setChildrenVO(result_bvos);
								KaipiaoqueryHVO result_hvo = list_4.get(0);
								result_hvo.setIbillstatus(-1);
								result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//集团
								result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org 正德pk
								result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
								RESULT[0].setParentVO(result_hvo);
								
								// 剩余开票金额 = 可开票总额 - 已开票总额
								result_hvo.setSykpje( 
										  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
									.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) ) 
									.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getVdef03()) ) 
								);
							}
						}
					}
		/**
		 * 针对 结果集， 查询出 金贵的数据，进行处理。
		 * 1、依据 组织 查询出 最后一笔 会员卡信息的 班次的结束时间。
		 * 2、依据 该结束时间  从金贵库里 查询出 之后的数据（实收金额）。
		 * 3、将 实收金额  叠加到 结果集里。
		 */
		IHy_huiyuanMaintain iHy_huiyuanMaintain = (IHy_huiyuanMaintain)NCLocator.getInstance().lookup(IHy_huiyuanMaintain.class.getName());
		for(int i=0;RESULT!=null&&i<RESULT.length;i++)
		{
			KaipiaoqueryHVO hVO = RESULT[i].getParentVO();
			
			String dian = hVO.getVdef02();
			String kaCode = hVO.getKa_code();
			/**
			 * 1、
			 */
			StringBuffer querySQL_1 = 
				new StringBuffer(" select max(ki.jssj) ")
						.append(" from hk_huiyuan_kainfo ki ")
						.append(" where ki.dr=0 ")
						.append(" and ki.vdef01 = '"+dian+"' ")
			;
			ArrayList list_1 = (ArrayList)iUAPQueryBS.executeQuery(querySQL_1.toString(), new ArrayListProcessor());
			if(list_1!=null && list_1.size()>0)
			{
				String jssj = PuPubVO.getString_TrimZeroLenAsNull( ((Object[])list_1.get(0))[0] );
				
				/**
				 * 2、
				 */
				Object res = iHy_huiyuanMaintain.queryJGchongzhi(kaCode,jssj,dian,null);
				/**
				 * 3、
				 */
				if( res!=null )
				{
					hVO.setKkpze( 
							  PuPubVO.getUFDouble_NullAsZero( hVO.getKkpze() )
						.add( PuPubVO.getUFDouble_NullAsZero( ((Object[])res)[1] ) )
					);
					hVO.setSykpje( // 剩余开票金额 = 可开票总额 - 已开票总额
							  PuPubVO.getUFDouble_NullAsZero( hVO.getKkpze() )
						.sub( PuPubVO.getUFDouble_NullAsZero( hVO.getYkpze() ) )
					);
				}
			}
		}

		return RESULT;
	}
}
