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
		
			String currTime = new UFDateTime().toString();	// ��ǰ������ ʱ��
			
			ArrayList<KaipiaoqueryBillVO> result_list = new ArrayList<KaipiaoqueryBillVO>();
			
			/**
			 * ��������˳�� ������
			 * 1���п�Ʊ��¼��  ��Ա������
			 * 2���޿�Ʊ��¼��  ��Ա������
			 * 3���п�Ʊ��¼��  ��ҵ�񿨵���
			 * 4���޿�Ʊ��¼��  ��ҵ�񿨵���  
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
	 * �������ݲ�ѯ
	 */
	public static KaipiaoqueryBillVO[] queryData(String currTime,String ka_code,String fph,boolean isRefresh,String srcFlag) throws Exception
	{
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		
		KaipiaoqueryBillVO[] RESULT = null;
		
		// 1�� ��ѯ�� ��Ʊ��¼�Ļ�Ա�� ����
					StringBuffer querySQL = 
							new StringBuffer("select ")
									.append(" kpb.fpje ")		// ��Ʊ���
			//						.append(",kp.fpdm || ' ' ||kp.fph as fph ")	// ��Ʊ��
									.append(",kp.fph as fph ")	// ��Ʊ�ţ�ȡ����Ʊ���룩
									.append(",u.user_name kpr ")	// ��Ʊ��
									.append(",kp.dbilldate fpsj ")		// ��Ʊ����
									.append(",kpb.ka_code vbdef01 ")	// ����
									.append(",kpb.kaxing_name vbdef02 ")// ����name
									.append(",nvl(ka.kkp_je,0)+nvl(kainfo.kkp_je,0) vbdef03 ")// �ɿ�Ʊ�ܶ��������
									.append(",nvl(ka.ygq_je,0) cfirsttypecode ") // �ѹ��ڷ�Ʊ���
									.append(",ka.ykp_je vbdef04 ")			// �ѿ�Ʊ�ܶ��������
									.append(",kp.ibillstatus vbdef05 ")		// ����״̬ ��1=���̬��
									.append(",kpb.ka_pk vbdef06 ")			// ��pk
									.append(",kpb.kaxing_code vbdef07 ")	// ����code
									.append(",kpb.kaxing_pk vbdef08 ")		// ����pk
			//						.append(",ka.vdef04 vbdef09 ")			// ת����������������ת����
									.append(",kp.vdef01 csourcetypecode ")	// ���ͣ���Ʊ��ת����
									.append(",dfka.ka_code vsourcebillcode ")	// �Է����� 
									.append(",ka.dbilldate vbdef10 ")		//NC�ƿ�ʱ��
									.append(",kkp.kpjz_time cfirstbillid ")		//��Ʊ����ʱ��
									.append(",kp.pk_hk_huiyuan_kaipiaoinfo vbdef09 ")	// ��Ʊpk��Ϊ�����飩
									.append(",ka.vdef01 cfirstbillbid ")	// ������
									.append(" from hk_huiyuan_kaipiaoinfo kp ")		
									.append(" inner join hk_huiyuan_kaipiaoinfo_b kpb on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
									.append(" left join (" +
												" select " +
												" ka.pk_hk_huiyuan_kadangan " +
												",sum(nvl(kkp.kkp_je,0)) kkp_je " +
												",sum(nvl(kkp.ykp_je,0)) ykp_je " +
												",sum( case when kkp.kpjz_time<='"+currTime+"' then nvl(kkp.kkp_je,0)-nvl(kkp.ykp_je,0) else 0 end) ygq_je " + // �ѹ��ڵĿɿ�Ʊ��� ���� ��ǰʱ��Ƚϣ�
												",max(ka.pk_hk_huiyuan_kaxing) pk_hk_huiyuan_kaxing " +
												",max(ka.ka_code) ka_code " +
												",max(ka.dbilldate) dbilldate " +
												",max(ka.vdef01) vdef01 " +	// ������
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
												") ka on ka.pk_hk_huiyuan_kadangan = kpb.ka_pk ")	// �������ɿ�Ʊ-��ͼ
									.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")// ����
									.append(" left join sm_user u on u.cuserid = kp.creator ")	// ����Ա
									.append(" left join hk_huiyuan_kadangan dfka on dfka.pk_hk_huiyuan_kadangan = kpb.vsourcebillcode ")	// ���� �Է���
									.append(" left join ( " +	
												" select " +
												"  kib.ka_pk pk_hk_huiyuan_kadangan " +
												" ,sum(kib.ka_ss) kkp_je " +
												" from hk_huiyuan_kainfo ki " +
												" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo " +
												" where ki.dr=0 and kib.dr=0 " +
												" and kib.xmdl = '��ֵ' " +
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
												" ) kainfo on kainfo.pk_hk_huiyuan_kadangan = kpb.ka_pk ")	// ��Ա����Ϣ δ��˵ĳ�ֵ����
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
												" ) kkp on kkp.pk_hk_huiyuan_kadangan = kpb.ka_pk ")	// ��Ʊ����ʱ��
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
									.append("checkError".equals(srcFlag)?" and nvl(kp.vdef02,'����')='����' ":"")
									.append(" order by kp.fpsj desc ")
					;
					
					ArrayList<KaipiaoqueryBVO> list = null;
					list = (ArrayList<KaipiaoqueryBVO>)iUAPQueryBS.executeQuery(querySQL.toString(), new BeanListProcessor(KaipiaoqueryBVO.class));
					
					if(list!=null && list.size()>0)
					{
						
						/**
						 * ���ܲ�ѯ�� �����Ա��  ����Ҫ�����ֳ�  ��ͷ���С�
						 */
						HashMap<String,ArrayList<KaipiaoqueryBVO>> KP_MAP = new HashMap<String,ArrayList<KaipiaoqueryBVO>>();
						for(int i=0;i<list.size();i++)
						{
							KaipiaoqueryBVO kp_bvo = list.get(i);
							String key = kp_bvo.getVbdef01();	// ���� Ϊkey
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
							
							String flag = kp_list.get(0).getVbdef02();	// �ж��ǲ���  ��ҵ��
							if( !"ɾ����".equals(flag) )
							{
								// ������Ĵ���
								RESULT[keys_i] = new KaipiaoqueryBillVO();
								KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[kp_list.size()];
								result_bvos = kp_list.toArray(result_bvos);
								RESULT[keys_i].setChildrenVO(result_bvos);
								KaipiaoqueryHVO result_hvo = new KaipiaoqueryHVO();
								result_hvo.setIbillstatus(-1);
								result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
								result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
								result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
								result_hvo.setKa_code( result_bvos[0].getVbdef01() );		// ����
								result_hvo.setKaxing_name( result_bvos[0].getVbdef02() );	// ����name
								result_hvo.setKa_pk( result_bvos[0].getVbdef06() );			// ��pk
								result_hvo.setKaxing_code( result_bvos[0].getVbdef07() );	// ����code
								result_hvo.setKaxing_pk( result_bvos[0].getVbdef08() );		// ����pk
								result_hvo.setKkpze( PuPubVO.getUFDouble_NullAsZero(result_bvos[0].getVbdef03()) );	// �ɿ�Ʊ�ܶ� 
//								result_hvo.setVdef03( result_bvos[0].getVbdef09() );		// ת����� 
								result_hvo.setVdef01( result_bvos[0].getVbdef10() );		// NC�ƿ�ʱ��
								result_hvo.setVdef04( result_bvos[0].getCfirstbillid() );	// ��Ʊ����ʱ��
								result_hvo.setVdef02( result_bvos[0].getCfirstbillbid() );	// ������
								RESULT[keys_i].setParentVO(result_hvo);
								
								// �ѿ�Ʊ�ܶ� = ���������ѿ�Ʊ�ܶ�  + �����״̬�� ��Ʊ��ϸ  �ܺ� ��HK 2019��1��17��15:12:13 ��Ϊ�ӿ�Ʊ��ϸ ��ȡ����
								// ת���ܶ�     = ��������ת���ܶ�      + �����״̬�� ��Ʊ��ϸ  �ܺ�
								UFDouble ykpze = UFDouble.ZERO_DBL;	// �ѿ�Ʊ��δ��˵ķ�Ʊ��
								UFDouble zkze  = UFDouble.ZERO_DBL;	// ת��
								for(int i=0;i<result_bvos.length;i++)
								{// ѭ������  ���
									ykpze = ykpze.add( result_bvos[i].getFpje() );
								}
								
								result_hvo.setYkpze( ykpze );	// �ѿ�Ʊ�ܶ�
								
								// ʣ�࿪Ʊ��� = �ɿ�Ʊ�ܶ� - �ѿ�Ʊ�ܶ� 
								result_hvo.setSykpje( 
										  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
									.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) )
								);
								
								// ��� �ѹ��ڣ� �� ʣ�࿪Ʊ���  ת�Ƶ�  �ѹ���Ʊ��
								if(result_hvo.getVdef04()!=null)
								{
									String nowDate_str = new UFDate().toString();
									if(nowDate_str.compareTo(result_hvo.getVdef04())>0)
									{// �������ʱ�� ���� ��Ʊ��ֹ����  ˵���ѹ���
										result_hvo.setVdef05(result_hvo.getSykpje().toString());
										result_hvo.setSykpje(UFDouble.ZERO_DBL);
									}
								}
								
							}
							else
							{
			//					if( ka_code==null ) return null;
								
								// 3���п�Ʊ��¼��  ��ҵ�񿨵�����˵���� ��ʷ��=ɾ������
								StringBuffer querySQL_3 = 
									new StringBuffer("select ")
											.append(" kpb.fpje ")		// ��Ʊ���
			//								.append(",kp.fpdm || ' ' ||kp.fph as fph ")			// ��Ʊ��
											.append(",kp.fph as fph ")			// ��Ʊ�ţ�ȡ����Ʊ���룩
											.append(",u.user_name kpr ")	// ��Ʊ��
											.append(",kp.dbilldate fpsj ")		// ��Ʊ����
											.append(",kpb.ka_code vbdef01 ")	// ����
											.append(",'ɾ����' vbdef02 ")			// ����name
											.append(",ka.kkpze vbdef03 ")		// �ɿ�Ʊ�ܶ��������
											.append(",ka.ykpze vbdef04 ")		// �ѿ�Ʊ�ܶ��������
											.append(",kp.ibillstatus vbdef05 ")		// ����״̬ ��1=���̬��
											.append(",kpb.ka_pk vbdef06 ")			// ��pk
											.append(",'ɾ����' vbdef07 ")				// ����code
											.append(",'DELETE00000000000001' vbdef08 ")		// ����pk
//											.append(",ka.vdef04 vbdef09 ")			// ת�����
//											.append(",kp.vdef01 csourcetypecode ")	// ���ͣ���Ʊ��ת����
//											.append(",dfka.ka_code vsourcebillcode ")	// �Է����� 
											.append(",kp.pk_hk_huiyuan_kaipiaoinfo vbdef09 ")	// ��Ʊpk��Ϊ�����飩
											.append(",ka.vdef01 cfirstbillbid ")	// ������
											.append(",ka.vdef02 cfirstbillid ")		// ��Ʊ����ʱ��
											.append(" from hk_huiyuan_kaipiaoinfo kp ")		
											.append(" inner join hk_huiyuan_kaipiaoinfo_b kpb on kp.pk_hk_huiyuan_kaipiaoinfo = kpb.pk_hk_huiyuan_kaipiaoinfo ")
											.append(" left join HK_HUIYUAN_KAIPIAO_OLD ka on ka.ka_code = kpb.ka_code ")
											.append(" left join sm_user u on u.cuserid = kp.creator ")
											.append(" left join hk_huiyuan_kadangan dfka on dfka.pk_hk_huiyuan_kadangan = kpb.vsourcebillcode ")	// ���� �Է���
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
									// ������Ĵ���
									RESULT = new KaipiaoqueryBillVO[1];
									KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[list.size()];
									result_bvos = list_3.toArray(result_bvos);
									RESULT[0] = new KaipiaoqueryBillVO();
									RESULT[0].setChildrenVO(result_bvos);
									KaipiaoqueryHVO result_hvo = new KaipiaoqueryHVO();
									result_hvo.setIbillstatus(-1);
									result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
									result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
									result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
									result_hvo.setKa_code( result_bvos[0].getVbdef01() );		// ����
									result_hvo.setKaxing_name( result_bvos[0].getVbdef02() );	// ����name
									result_hvo.setKa_pk( result_bvos[0].getVbdef06() );			// ��pk
									result_hvo.setKaxing_code( result_bvos[0].getVbdef07() );	// ����code
									result_hvo.setKaxing_pk( result_bvos[0].getVbdef08() );		// ����pk
									result_hvo.setKkpze( PuPubVO.getUFDouble_NullAsZero(result_bvos[0].getVbdef03()) );	// �ɿ�Ʊ�ܶ� 
//									result_hvo.setVdef03( result_bvos[0].getVbdef09() );		// ת����� 
									result_hvo.setVdef02( result_bvos[0].getCfirstbillbid() );	// ������
									result_hvo.setVdef04( result_bvos[0].getCfirstbillid() );	// ��Ʊ����ʱ��
									RESULT[0].setParentVO(result_hvo);
									
									// �ѿ�Ʊ�ܶ� = ���������ѿ�Ʊ�ܶ� + �����״̬�� ��Ʊ��ϸ  �ܺ�
									UFDouble ykpze = UFDouble.ZERO_DBL;
									for(int i=0;i<result_bvos.length;i++)
									{// ѭ������  ���
										if( !"1".equals( result_bvos[i].getVbdef05() ) )
										{
											ykpze = ykpze.add( result_bvos[i].getFpje() );
										}
									}
									result_hvo.setYkpze( ykpze.add( PuPubVO.getUFDouble_NullAsZero(result_bvos[0].getVbdef04()) ) );	// �ѿ�Ʊ�ܶ�
									
									// ʣ�࿪Ʊ��� = �ɿ�Ʊ�ܶ� - �ѿ�Ʊ�ܶ�
									result_hvo.setSykpje( 
											  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
										.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) )
										.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getVdef03()) )
									);
									
			//						// ������� �ŵ�����
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
						
						// 2�� ��ѯ��Ա����������� �ɿ�Ʊ�ܶ� Ϊ �գ� ��ȡ ���ͽ�
						StringBuffer querySQL_2 = 
								new StringBuffer("select ")
										.append(" ka.ka_code ")			// ����
										.append(",kx.kaxing_name ")		// ����name
										.append(",nvl(ka.kkp_je,0)+nvl(kainfo.kkp_je,0) kkpze ")	// �ɿ�Ʊ�ܶ������������ȥ �ѹ��ڵģ�
										
										.append(",nvl(ka.kkp_je,0)+nvl(kainfo.kkp_je,0) kkpze ")	// �ɿ�Ʊ�ܶ��������
										.append(",nvl(ka.ygq_je,0) vdef05 ") // �ѹ��ڷ�Ʊ���
										
										.append(",ka.ykp_je ykpze ")		// �ѿ�Ʊ�ܶ��������
			//							.append(",ka.vdef04 vdef03 ")		// ת������������
										.append(",ka.pk_hk_huiyuan_kadangan ka_pk ")	// ��pk
										.append(",kx.kaxing_code ")						// ����code
										.append(",kx.pk_hk_huiyuan_kaxing kaxing_pk ")	// ����pk
			//							.append(",kx.ka_ss vdef01 ")			// ����-ʵ�ս��
										.append(",ka.dbilldate vdef01 ")		//NC�ƿ�ʱ��
										.append(",kkp.kpjz_time vdef04 ")		//��Ʊ����ʱ��
										.append(",ka.vdef01 vdef02 ")			// ������
										.append(" from (" +
												" select ka.pk_hk_huiyuan_kadangan " +
												",sum(nvl(kkp.kkp_je,0)) kkp_je " +
												",sum(nvl(kkp.ykp_je,0)) ykp_je " +
												",sum( case when kkp.kpjz_time<='"+currTime+"' then nvl(kkp.kkp_je,0)-nvl(kkp.ykp_je,0) else 0 end) ygq_je " + // �ѹ��ڵĿɿ�Ʊ��� ���� ��ǰʱ��Ƚϣ�
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
												") ka ")	// �������ɿ�Ʊ-��ͼ
										.append(" left join hk_huiyuan_kaxing kx on ka.pk_hk_huiyuan_kaxing = kx.pk_hk_huiyuan_kaxing ")
										.append(" left join ( " +	
												" select " +
												"  kib.ka_pk pk_hk_huiyuan_kadangan " +
												" ,sum(kib.ka_ss) kkp_je " +
												" from hk_huiyuan_kainfo ki " +
												" inner join hk_huiyuan_kainfo_b kib on ki.pk_hk_huiyuan_kainfo = kib.pk_hk_huiyuan_kainfo " +
												" where ki.dr=0 and kib.dr=0 " +
												" and kib.xmdl = '��ֵ' " +
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
												" ) kainfo on kainfo.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")	// ��Ա����Ϣ δ��˵ĳ�ֵ����
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
												" ) kkp on kkp.pk_hk_huiyuan_kadangan = ka.pk_hk_huiyuan_kadangan ")	// ��Ʊ����ʱ��
										.append(" where (1=1)  ")
			//							.append(" and ka.dr= 0  ")
										.append(" and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") ")
						;  
						
						ArrayList<KaipiaoqueryHVO> list_2 = null;
						list_2 = (ArrayList<KaipiaoqueryHVO>)iUAPQueryBS.executeQuery(querySQL_2.toString(), new BeanListProcessor(KaipiaoqueryHVO.class));
						
						if(list_2!=null && list_2.size()>0)
						{
							// ������Ĵ���
							RESULT = new KaipiaoqueryBillVO[1];
							RESULT[0] = new KaipiaoqueryBillVO();
							KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[1];
							result_bvos[0] = new KaipiaoqueryBVO();
							RESULT[0].setChildrenVO(result_bvos);
							KaipiaoqueryHVO result_hvo = list_2.get(0);
							result_hvo.setIbillstatus(-1);
							result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
							result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
							result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
							RESULT[0].setParentVO(result_hvo);
							
							if( result_hvo.getKkpze()==null )
							{// ���  �ɿ�Ʊ�ܶ�  Ϊ�գ� ˵���� �¿��Ŀ���ȡ ����ʵ�ս��
								result_hvo.setKkpze( PuPubVO.getUFDouble_NullAsZero(result_hvo.getVdef01()) );
								result_hvo.setYkpze( UFDouble.ZERO_DBL );
							}
							
							UFDouble ykpze =  UFDouble.ZERO_DBL;	// �ѿ�Ʊ�ܶ� = ��
							
							result_hvo.setYkpze( ykpze );	// �ѿ�Ʊ�ܶ�
							
							// ʣ�࿪Ʊ��� = �ɿ�Ʊ�ܶ� - �ѿ�Ʊ�ܶ� 
							result_hvo.setSykpje( 
									  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
								.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) )
							);
							
							// ��� �ѹ��ڣ� �� ʣ�࿪Ʊ���  ת�Ƶ�  �ѹ���Ʊ��
							if(result_hvo.getVdef04()!=null)
							{
								String nowDate_str = new UFDate().toString();
								if(nowDate_str.compareTo(result_hvo.getVdef04())>0)
								{// �������ʱ�� ���� ��Ʊ��ֹ����  ˵���ѹ���
									result_hvo.setVdef05(result_hvo.getSykpje().toString());
									result_hvo.setSykpje(UFDouble.ZERO_DBL);
								}
							}
							
						}
						else
						{
							
							// 4���޿�Ʊ��¼��  ��ҵ�񿨵���  
							StringBuffer querySQL_4 =
									new StringBuffer("select ")
											.append(" ka.ka_code ")			// ����
											.append(",'ɾ����' kaxing_name ")	// ����name
											.append(",ka.kkpze ")			// �ɿ�Ʊ�ܶ��������
											.append(",ka.ykpze ")			// �ѿ�Ʊ�ܶ��������
//											.append(",ka.vdef04 vdef03 ")	// ת������������
											.append(",ka.vdef01 vdef02 ")	// ������
											.append(",ka.pk_HK_HUIYUAN_KAIPIAO_OLD ka_pk ")	// ��pk
											.append(",ka.vdef02 vdef04 ")	// ��Ʊ����ʱ��
											.append(",'ɾ����' kaxing_code ")					// ����code
											.append(",'DELETE00000000000001' kaxing_pk ")	// ����pk
											.append(" from HK_HUIYUAN_KAIPIAO_OLD ka ")
											.append(" where ka.dr= 0  ")
											.append(" and NLS_UPPER(ka.ka_code) in ("+ka_code.toUpperCase()+") " )
							;  
							
							ArrayList<KaipiaoqueryHVO> list_4 = null;
							list_4 = (ArrayList<KaipiaoqueryHVO>)iUAPQueryBS.executeQuery(querySQL_4.toString(), new BeanListProcessor(KaipiaoqueryHVO.class));
							
							if(list_4!=null && list_4.size()>0)
							{
								// ������Ĵ���
								RESULT = new KaipiaoqueryBillVO[1];
								RESULT[0] = new KaipiaoqueryBillVO();
								KaipiaoqueryBVO[] result_bvos = new KaipiaoqueryBVO[1];
								result_bvos[0] = new KaipiaoqueryBVO();
								RESULT[0].setChildrenVO(result_bvos);
								KaipiaoqueryHVO result_hvo = list_4.get(0);
								result_hvo.setIbillstatus(-1);
								result_hvo.setPk_group( AppContext.getInstance().getPkGroup() );	//����
								result_hvo.setPk_org("0001NC10000000004AXZ");	// pk_org ����pk
								result_hvo.setPk_org_v("0001NC10000000004AXY");	// pk_org_v
								RESULT[0].setParentVO(result_hvo);
								
								// ʣ�࿪Ʊ��� = �ɿ�Ʊ�ܶ� - �ѿ�Ʊ�ܶ�
								result_hvo.setSykpje( 
										  PuPubVO.getUFDouble_NullAsZero(result_hvo.getKkpze())
									.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getYkpze()) ) 
									.sub( PuPubVO.getUFDouble_NullAsZero(result_hvo.getVdef03()) ) 
								);
							}
						}
					}
		/**
		 * ��� ������� ��ѯ�� �������ݣ����д���
		 * 1������ ��֯ ��ѯ�� ���һ�� ��Ա����Ϣ�� ��εĽ���ʱ�䡣
		 * 2������ �ý���ʱ��  �ӽ����� ��ѯ�� ֮������ݣ�ʵ�ս���
		 * 3���� ʵ�ս��  ���ӵ� ������
		 */
		IHy_huiyuanMaintain iHy_huiyuanMaintain = (IHy_huiyuanMaintain)NCLocator.getInstance().lookup(IHy_huiyuanMaintain.class.getName());
		for(int i=0;RESULT!=null&&i<RESULT.length;i++)
		{
			KaipiaoqueryHVO hVO = RESULT[i].getParentVO();
			
			String dian = hVO.getVdef02();
			String kaCode = hVO.getKa_code();
			/**
			 * 1��
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
				 * 2��
				 */
				Object res = iHy_huiyuanMaintain.queryJGchongzhi(kaCode,jssj,dian,null);
				/**
				 * 3��
				 */
				if( res!=null )
				{
					hVO.setKkpze( 
							  PuPubVO.getUFDouble_NullAsZero( hVO.getKkpze() )
						.add( PuPubVO.getUFDouble_NullAsZero( ((Object[])res)[1] ) )
					);
					hVO.setSykpje( // ʣ�࿪Ʊ��� = �ɿ�Ʊ�ܶ� - �ѿ�Ʊ�ܶ�
							  PuPubVO.getUFDouble_NullAsZero( hVO.getKkpze() )
						.sub( PuPubVO.getUFDouble_NullAsZero( hVO.getYkpze() ) )
					);
				}
			}
		}

		return RESULT;
	}
}
